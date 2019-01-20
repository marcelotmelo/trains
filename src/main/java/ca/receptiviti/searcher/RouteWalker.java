package ca.receptiviti.searcher;

import ca.receptiviti.model.City;
import ca.receptiviti.model.Grid;
import ca.receptiviti.model.Route;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class RouteWalker {

    private final Grid grid;

    private final LimitStrategy strategy;

    private RouteWalker(Grid grid, LimitStrategy strategy) {
        this.grid = grid;
        this.strategy = strategy;
    }

    public Grid getGrid() {
        return grid;
    }

    public Set<Map.Entry<City, Integer>> routes(City city) {
        return grid.routesFromCity(city).entrySet();
    }

    public void walk(City destination, Route route, List<Route> routes, boolean canCycle, int limit, Boolean definedLimit) {
        City city = route.last();
        for (Map.Entry<City, Integer> entry : grid.routesFromCity(city).entrySet()) {
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

    public static RouteWalkerBuilder builder() {
        return new RouteWalkerBuilder();
    }

    static class RouteWalkerBuilder {

        private final StopLimitStrategy stopLimitStrategy = new StopLimitStrategy();

        private final LengthLimitStrategy lengthLimitStrategy = new LengthLimitStrategy();

        private RouteWalkerBuilder() {

        }

        public RouteWalker withStopLimit(Grid grid) {
            return new RouteWalker(grid, stopLimitStrategy);
        }

        public RouteWalker withLengthLimit(Grid grid) {
            return new RouteWalker(grid, lengthLimitStrategy);
        }

    }
}
