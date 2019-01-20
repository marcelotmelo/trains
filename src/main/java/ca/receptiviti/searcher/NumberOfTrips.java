package ca.receptiviti.searcher;

import ca.receptiviti.model.City;
import ca.receptiviti.model.Grid;
import ca.receptiviti.model.Route;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BooleanSupplier;

public class NumberOfTrips {

    private final Grid grid;

    public NumberOfTrips(Grid grid) {
        this.grid = grid;
    }

    public int maxStops(City source, City destination, int stops) {
        List<Route> routes = new ArrayList<>();
        Route route = new Route(source);
        walk(destination, route, routes, stops, true, () -> true);

        return (int) routes.stream().filter(r -> r.stops() <= stops).count();
    }

    public int exactStops(City source, City destination, int stops) {
        List<Route> routes = new ArrayList<>();
        Route route = new Route(source);
        walk(destination, route, routes, stops, true, () -> true);

        return (int) routes.stream().filter(r -> r.stops() == stops).count();
    }

    private void walk(City destination, Route route, List<Route> routes, int stops, boolean canCycle, BooleanSupplier booleanSupplier) {
        City city = route.last();
        for (Map.Entry<City, Integer> entry : grid.routesFromCity(city).entrySet()) {
            City neighbour = entry.getKey();
            Integer distance = entry.getValue();
            if (destination.equals(neighbour)) {
                routes.add(Route.builder().wihtRoute(route).addRoute(neighbour, distance).build());
            }
            if (booleanSupplier.getAsBoolean()) {
                if (canCycle || !route.contains(neighbour)) {
                    if (route.stops() <= stops) {
                        Route current = Route.builder().wihtRoute(route).addRoute(neighbour, distance).build();
                        walk(destination, current, routes, stops, canCycle, booleanSupplier);
                    }
                }
            }
        }
    }

}
