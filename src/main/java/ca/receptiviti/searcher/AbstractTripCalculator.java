package ca.receptiviti.searcher;

import ca.receptiviti.exception.RouteNotFoundException;
import ca.receptiviti.model.City;
import ca.receptiviti.model.Route;

import java.util.ArrayList;
import java.util.List;

/**
 * Calculates travel attributes based on a given @see ca.receptiviti.searcher.RouteWalker.
 */
class AbstractTripCalculator {

    final RouteWalker walker;

    /**
     * Creates a new calculator based on a RouteWalker.
     *
     * @param walker A RouteWalker.
     */
    public AbstractTripCalculator(RouteWalker walker) {
        this.walker = walker;
    }

    /**
     * Creates all the possible routes between the source and destination based on the
     *
     * @param source      The source city - the starting point of the travel.
     * @param destination The destination city - the final point of the travel.
     * @param canCycle    Indicates whether the walker can travel in cycles.
     * @param limit       The limit or threshold to be observed in the @see ca.receptiviti.searcher.LimitStrategy.
     * @return The existing Routes between the source and destination/
     * @throws RouteNotFoundException IF no route exists between source and destination.
     * @see ca.receptiviti.searcher.RouteWalker defined @see ca.receptiviti.searcher.LimitStrategy.
     */
    public List<Route> walk(City source, City destination, boolean canCycle, int limit) throws RouteNotFoundException {
        List<Route> routes = new ArrayList<>();
        Route route = new Route(source);

        walker.walk(destination, route, routes, canCycle, limit, true);
        if (routes.isEmpty()) {
            throw new RouteNotFoundException();
        }
        return routes;
    }

}
