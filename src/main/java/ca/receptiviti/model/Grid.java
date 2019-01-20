package ca.receptiviti.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Class that represents the railroad Grid containing its cities and the paths or routes
 * between them.
 */
public class Grid {

    private final Map<City, Map<City, Integer>> cities;

    /**
     * Default constructor.
     */
    public Grid() {
        cities = new HashMap<>();
    }

    /**
     * Adds a unidirectional path or route to the existing grid.
     *
     * @param origin      The origin city.
     * @param destination The destination city.
     * @param distance    The distance between origin and destination.
     */
    public void addPath(City origin, City destination, int distance) {
        cities.computeIfAbsent(origin, k -> new HashMap<>()).put(destination, distance);
    }

    /**
     * Returns a list of existing paths for a given city.
     *
     * @param city The city from which the paths will be returned.
     * @return The existing paths from the city.
     */
    public Map<City, Integer> routesFromCity(City city) {
        return new HashMap<>(cities.get(city));
    }

    /**
     * Returns the list of all existing cities in the grid.
     *
     * @return The list of existing cities.
     */
    public Set<City> getCities() {
        return new HashSet<>(cities.keySet());
    }

    /**
     * Returns all the cities directly accessible from a given city.
     *
     * @param city The origin city.
     * @return A list of cities accessible from the origin.
     */
    public Set<City> getNeighbours(City city) {
        Set<City> neighbours = new HashSet<>();
        Map<City, Integer> routes = cities.get(city);
        if (routes != null) {
            neighbours.addAll(routes.keySet());
        }
        return neighbours;
    }

}
