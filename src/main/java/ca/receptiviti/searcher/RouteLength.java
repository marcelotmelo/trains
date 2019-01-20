package ca.receptiviti.searcher;

import ca.receptiviti.model.City;
import ca.receptiviti.model.Grid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BooleanSupplier;

public class RouteLength {

    private final Grid grid;

    public RouteLength(Grid grid) {
        this.grid = grid;
    }

    public int shortest(City source, City destination) {

        Map<Integer, List<City>> pathLengths = new HashMap<>();
        List<City> path = new ArrayList<>(Arrays.asList(source));

        walk(destination, path, pathLengths, 0, false, () -> true);

        return pathLengths.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getKey)).findFirst().get().getKey();
    }

    public int tripsWithMaxLength(City source, City destination, int maxLength) {
        Map<Integer, List<City>> pathLengths = new HashMap<>();
        List<City> path = new ArrayList<>(Arrays.asList(source));

        int distance = 0;
        walk(destination, path, pathLengths, distance, true, () -> distance <= 30);

        return (int) pathLengths.keySet().stream().filter(length -> length <= 30).count();
    }

    public void walk(City destination, List<City> path, Map<Integer, List<City>> pathLengths, int distance, boolean canCycle, BooleanSupplier booleanSupplier) {
        City city = path.get(path.size() - 1);
        for (Map.Entry<City, Integer> route : grid.routesFromCity(city).entrySet()) {
            City neighbour = route.getKey();
            Integer edge = route.getValue();
            if (destination.equals(neighbour)) {
                List<City> currentPath = new ArrayList<>(path);
                currentPath.add(neighbour);
                pathLengths.put(distance + edge, currentPath);
            }
            if (booleanSupplier.getAsBoolean()) {
                if(canCycle || !path.contains(neighbour)) {
                    List<City> currentPath = new ArrayList<>(path);
                    currentPath.add(neighbour);
                    int currentDistance = distance + edge;
                    walk(destination, currentPath, pathLengths, currentDistance, canCycle, () -> currentDistance <= 30);
                }
            }
        }
    }

}
