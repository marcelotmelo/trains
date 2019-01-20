package ca.receptiviti.searcher;

import ca.receptiviti.exception.RouteNotFoundException;
import ca.receptiviti.model.City;
import ca.receptiviti.model.Grid;
import ca.receptiviti.model.Route;

import java.util.List;
import java.util.Map;

/**
 * The class defines a method to traverse and build a list of routes @see ca.receptiviti.model.Route possible within a grid @see ca.receptiviti.model.Grid.
 */
class RouteWalker {

    private final Grid grid;

    private final LimitStrategy strategy;

    private RouteWalker(Grid grid, LimitStrategy strategy) {
        this.grid = grid;
        this.strategy = strategy;
    }

    /**
     * Traverses one city down from the last city of the existing travel path, until it reaches the destination or the
     * threshold limits are reached.
     *
     * @param destination  The destination city.
     * @param route        The path traveled so far.
     * @param routes       The list of existing paths.
     * @param canCycle     Whether a path can be cyclic or not.
     * @param limit        The numeric threshold to be observed.
     * @param definedLimit A boolean expression that indicates if the threshold was reached, based on the LimitStrategy.
     * @throws RouteNotFoundException If a path can not be found to destination within the given limit.
     */
    public void walk(City destination, Route route, List<Route> routes, boolean canCycle, int limit, Boolean definedLimit) throws RouteNotFoundException {
        City city = route.last().orElseThrow(RouteNotFoundException::new);
        for (Map.Entry<City, Integer> entry : grid.neighbours(city).entrySet()) {
            City neighbour = entry.getKey();
            Integer distance = entry.getValue();
            if (destination.equals(neighbour)) {
                routes.add(Route.builder().withRoute(route).addRoute(neighbour, distance).build());
            }
            if (definedLimit) {
                if (canCycle || !route.contains(neighbour)) {
                    Route current = Route.builder().withRoute(route).addRoute(neighbour, distance).build();
                    walk(destination, current, routes, canCycle, limit, strategy.limit().apply(route, limit));
                }
            }
        }
    }

    /**
     * Creates a new RouteWalkerBuilder.
     *
     * @return a RouteWalkerBuilder.
     */
    public static RouteWalkerBuilder builder() {
        return new RouteWalkerBuilder();
    }

    /**
     * Builder class of RouteWalker.
     */
    static class RouteWalkerBuilder {

        private RouteWalkerBuilder() {
        }

        /**
         * Creates a new RouteWalker with a StopLimitStrategy.
         *
         * @param grid The pre-defined grid that will be traversed by the walker.
         * @return A new RouteWalker with a StopLimitStrategy.
         */
        public RouteWalker withStopLimit(Grid grid) {
            return new RouteWalker(grid, new StopLimitStrategy());
        }

        /**
         * Creates a new RouteWalker with a LengthLimitStrategy.
         *
         * @param grid The pre-defined grid that will be traversed by the walker.
         * @return A new RouteWalker with a LengthLimitStrategy.
         */
        public RouteWalker withLengthLimit(Grid grid) {
            return new RouteWalker(grid, new LengthLimitStrategy());
        }

    }
}
