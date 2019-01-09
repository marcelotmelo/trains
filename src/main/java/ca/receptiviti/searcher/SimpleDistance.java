package ca.receptiviti.searcher;

import ca.receptiviti.exception.RouteNotFoundException;
import ca.receptiviti.model.City;
import ca.receptiviti.model.Grid;

import java.util.List;
import java.util.Map;

class SimpleDistance {

    private final Grid grid;

    public SimpleDistance(Grid grid) {
        this.grid = grid;
    }

    public int getDistance(List<City> cities) throws RouteNotFoundException {
        int distance = 0;
        for (int i = 0; i < cities.size() - 1; i++) {
            City origin = cities.get(i);
            City destination = cities.get(i + 1);
            distance += getDistance(origin, destination);
        }
        return distance;
    }

    private int getDistance(City origin, City destination) throws RouteNotFoundException {
        Map<City, Integer> routesFromCity = grid.routesFromCity(origin);
        Integer distance = routesFromCity.get(destination);
        if (distance == null) {
            throw new RouteNotFoundException();
        }
        return distance;
    }

}
