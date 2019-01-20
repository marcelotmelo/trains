package ca.receptiviti.searcher;

import ca.receptiviti.exception.RouteNotFoundException;
import ca.receptiviti.model.City;
import ca.receptiviti.model.Grid;
import ca.receptiviti.model.Route;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RouteLength {

    private final RouteWalker walker;

    public RouteLength(Grid grid) {
        this.walker = RouteWalker.builder().withLengthLimit(grid);
    }

    public int shortest(City source, City destination) throws RouteNotFoundException {
        List<Route> routes = new ArrayList<>();
        Route route = new Route(source);

        walker.walk(destination, route, routes, false, 0, true);
        return routes.stream().min(Comparator.comparing(Route::length)).orElseThrow(RouteNotFoundException::new).length();
    }

    public int tripsWithMaxLength(City source, City destination, int maxLength) {
        List<Route> routes = new ArrayList<>();
        Route route = new Route(source);

        walker.walk(destination, route, routes, true, maxLength, true);
        return (int) routes.stream().filter(r -> r.length() < 30).count();
    }

}
