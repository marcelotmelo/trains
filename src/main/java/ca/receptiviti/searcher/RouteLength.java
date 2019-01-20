package ca.receptiviti.searcher;

import ca.receptiviti.model.City;
import ca.receptiviti.model.Grid;
import ca.receptiviti.model.Route;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.BooleanSupplier;

public class RouteLength {

    private final Grid grid;

    public RouteLength(Grid grid) {
        this.grid = grid;
    }

    public int shortest(City source, City destination) {
        List<Route> routes = new ArrayList<>();
        Route route = new Route(source);

        walk(destination, route, routes, false, () -> true);

        return routes.stream().sorted(Comparator.comparing(Route::length)).findFirst().get().length();
    }

    public int tripsWithMaxLength(City source, City destination, int maxLength) {
        List<Route> routes = new ArrayList<>();
        Route route = new Route(source);

        walk(destination, route, routes, true, () -> route.length() < 30);

        return (int) routes.stream().filter(r -> r.length() < 30).count();
    }

    public void walk(City destination, Route route, List<Route> routes, boolean canCycle, BooleanSupplier booleanSupplier) {
        City city = route.last();
        for (Map.Entry<City, Integer> fromCity : grid.routesFromCity(city).entrySet()) {
            City neighbour = fromCity.getKey();
            Integer distance = fromCity.getValue();
            if (destination.equals(neighbour)) {
                routes.add(Route.builder().wihtRoute(route).addRoute(neighbour, distance).build());
            }
            if (booleanSupplier.getAsBoolean()) {
                if (canCycle || !route.contains(neighbour)) {
                    Route current = Route.builder().wihtRoute(route).addRoute(neighbour, distance).build();
                    walk(destination, current, routes, canCycle, () -> route.length() < 30);
                }
            }
        }
    }

}
