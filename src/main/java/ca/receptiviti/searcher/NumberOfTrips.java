package ca.receptiviti.searcher;

import ca.receptiviti.exception.RouteNotFoundException;
import ca.receptiviti.model.City;
import ca.receptiviti.model.Grid;
import ca.receptiviti.model.Route;

import java.util.ArrayList;
import java.util.List;

/**
 * Calculates travel attributes based on the number of trips that exist in a travel path.
 */
public class NumberOfTrips extends AbstractTripCalculator {

    /**
     * Creates a new calculator based on a pre-defined grid of cities and paths between them.
     *
     * @param grid A pre-defined grid of cities and paths.
     */
    public NumberOfTrips(Grid grid) {
        super(RouteWalker.builder().withStopLimit(grid));
    }

    /**
     * Returns the number of existing trips between two given cities that are within a limit of maximum given stops between them.
     *
     * @param source      The source city - the starting point of the travel.
     * @param destination The destination city - the final point of the travel.
     * @param stops       The maximum number of stops that must exist between the cities.
     * @return The existing number of trips possible between the two cities with the maximum number of stops.
     * @throws RouteNotFoundException if there are no possible trips between the cities.
     */
    public int maxStops(City source, City destination, int stops) throws RouteNotFoundException {
        List<Route> routes = walk(source, destination, stops);
        return (int) routes.stream().filter(r -> r.stops() <= stops).count();
    }

    /**
     * Returns the number of existing trips between two given cities that have the exact number of given stops between them.
     *
     * @param source      The source city - the starting point of the travel.
     * @param destination The destination city - the final point of the travel.
     * @param stops       The exact number of stops that must exist between the cities.
     * @return he existing number of trips possible between the two cities with the exact number of stops.
     * @throws RouteNotFoundException if there are no possible trips between the cities.
     */
    public int exactStops(City source, City destination, int stops) throws RouteNotFoundException {
        List<Route> routes = walk(source, destination, stops);

        return (int) routes.stream().filter(r -> r.stops() == stops).count();
    }

    private List<Route> walk(City source, City destination, int stops) throws RouteNotFoundException {
        List<Route> routes = new ArrayList<>();
        Route route = new Route(source);

        walker.walk(destination, route, routes, true, stops, true);
        if (routes.isEmpty()) {
            throw new RouteNotFoundException();
        }
        return routes;
    }

}
