package ca.receptiviti.searcher;

import ca.receptiviti.model.City;
import ca.receptiviti.model.Grid;
import ca.receptiviti.model.Route;

import java.util.ArrayList;
import java.util.List;

public class NumberOfTrips {

    private final RouteWalker walker;

    public NumberOfTrips(Grid grid) {
        this.walker = RouteWalker.builder().withStopLimit(grid);
    }

    public int maxStops(City source, City destination, int stops) {
        List<Route> routes = new ArrayList<>();
        Route route = new Route(source);

        walker.walk(destination, route, routes, true, stops, true);

        return (int) routes.stream().filter(r -> r.stops() <= stops).count();
    }

    public int exactStops(City source, City destination, int stops) {
        List<Route> routes = new ArrayList<>();
        Route route = new Route(source);

        walker.walk(destination, route, routes, true, stops, true);

        return (int) routes.stream().filter(r -> r.stops() == stops).count();
    }

}
