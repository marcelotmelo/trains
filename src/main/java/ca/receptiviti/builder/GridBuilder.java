package ca.receptiviti.builder;

import ca.receptiviti.model.City;
import ca.receptiviti.model.Grid;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class to build a graph data structure based on a string of paths.
 */
public class GridBuilder {

    /**
     * Builds a grid based on a string of paths.
     * @param paths String representing the grid, comma separated, containing single letters as
     * origin and destinations and a single number as distance.
     * e.g. AD5 - origin: A; destination: D; distance: 5
     * AD5, DC4, AB2, BC3
     * @return Grid based on paths informed
     */
    public static Grid buildFromPaths(String paths) {
        Grid grid = new Grid();

        String[] splitPaths = paths.split(",");
        for (String path : splitPaths) {
            if (path.length() == 3) {
                City origin = cityFromChar(path.charAt(0));
                City destination = cityFromChar(path.charAt(1));
                grid.addPath(origin, destination, path.charAt(2) - '0');
            } else {
                throw new IllegalArgumentException(String.format("Invalid path %s", path));
            }
        }
        return grid;
    }

    /**
     * Creates a list of cities from a string.
     * @param cities A string with each city represented by a single letter, not separated.
     *               e.g. ABC would return a list containing cities A, B and C
     * @return a list of cities.
     */
    public static List<City> citiesFromString(String cities) {
        if(cities == null || cities.isEmpty()) {
            throw new IllegalArgumentException("Cities must be informed.");
        }
        List<City> cityList = new ArrayList<>();
        for(char city : cities.toCharArray()) {
            cityList.add(cityFromChar(city));
        }
        return cityList;
    }

    private static City cityFromChar(char c) {
        return new City(String.valueOf(c));
    }

}
