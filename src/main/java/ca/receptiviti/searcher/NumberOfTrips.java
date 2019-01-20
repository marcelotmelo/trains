package ca.receptiviti.searcher;

import ca.receptiviti.model.City;
import ca.receptiviti.model.Grid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NumberOfTrips {

    private final Grid grid;

    public NumberOfTrips(Grid grid) {
        this.grid = grid;
    }

    public int maxStops(City source, City destination, int stops) {
        List<List<City>> allPaths = new ArrayList<>();

        List<City> currentPath = new ArrayList<>(Arrays.asList(source));
        walk(destination, currentPath, allPaths, stops);

        return allPaths.stream().filter(list -> list.size()-1 <= stops).collect(Collectors.toList()).size();
    }

    public int exactStops(City source, City destination, int stops) {
        List<List<City>> allPaths = new ArrayList<>();

        List<City> currentPath = new ArrayList<>(Arrays.asList(source));
        walk(destination, currentPath, allPaths, stops);

        return allPaths.stream().filter(list -> list.size()-1 == stops).collect(Collectors.toList()).size();
    }

    private void walk(City destination, List<City> path, List<List<City>> allPaths, int stops) {
        City city = path.get(path.size() - 1);
        for (City neighbour : grid.getNeighbours(city)) {
            if (destination.equals(neighbour)) {
                List<City> currentPath = new ArrayList<>(path);
                currentPath.add(neighbour);
                allPaths.add(currentPath);
            }
            if (path.size() <= stops) {
                List<City> currentPath = new ArrayList<>(path);
                currentPath.add(neighbour);
                walk(destination, currentPath, allPaths, stops);
            }
        }
    }

}
