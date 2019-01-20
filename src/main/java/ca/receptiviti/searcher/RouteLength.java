package ca.receptiviti.searcher;

import ca.receptiviti.exception.RouteNotFoundException;
import ca.receptiviti.model.City;
import ca.receptiviti.model.Grid;
import ca.receptiviti.model.Route;

import java.util.Comparator;
import java.util.List;

/**
 * Calculates travel attributes based on the distance of a travel path.
 */
public class RouteLength extends AbstractTripCalculator {

    /**
     * Creates a new calculator based on a pre-defined grid of cities and paths between them.
     *
     * @param grid A pre-defined grid of cities and paths.
     */
    public RouteLength(Grid grid) {
        super(RouteWalker.builder().withLengthLimit(grid));
    }

    /**
     * Returns the length of the shortest possible path between two cities.
     *
     * @param source      The source city - the starting point of the travel.
     * @param destination The destination city - the final point of the travel.
     * @return The shortest possible path between source and destination.
     * @throws RouteNotFoundException if there are no possible trips between the cities.
     */
    public int shortest(City source, City destination) throws RouteNotFoundException {
        List<Route> routes = walk(source, destination, false, 0);
        return routes.stream().min(Comparator.comparing(Route::length)).orElseThrow(RouteNotFoundException::new).length();
    }

    /**
     * Returns the number of trips possible between two cities where the distance traveled is less than an informed distance.
     *
     * @param source      The source city - the starting point of the travel.
     * @param destination The destination city - the final point of the travel.
     * @param maxLength   The maximum length that the trip must have.
     * @return The existing number of trips where the traveled distance is smaller than the given maxLength.
     * @throws RouteNotFoundException if there are no possible trips between the cities.
     */
    public int tripsWithMaxLength(City source, City destination, int maxLength) throws RouteNotFoundException {
        List<Route> routes = walk(source, destination, true, maxLength);
        return (int) routes.stream().filter(r -> r.length() < 30).count();
    }

}
