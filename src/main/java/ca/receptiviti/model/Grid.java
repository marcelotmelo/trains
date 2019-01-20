package ca.receptiviti.model;

import ca.receptiviti.exception.RouteNotFoundException;

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
    public Map<City, Integer> neighbours(City city) {
        return new HashMap<>(cities.get(city));
    }

    /**
     * Returns a unique list of all existing cities in the grid.
     *
     * @return The list of existing cities.
     */
    public Set<City> getCities() {
        return new HashSet<>(cities.keySet());
    }

    /**
     * Returns the distance between two directly connected cities (destination is a immediate neighbour of source).
     *
     * @param source The source City.
     * @param destination The destination City.
     * @return The distance.
     * @throws RouteNotFoundException If cities are not directly connected or do not exist.
     */
    public int distance(City source, City destination) throws RouteNotFoundException {
        Map<City, Integer> routes = neighbours(source);
        Integer distance = routes.get(destination);
        if (distance == null) {
            throw new RouteNotFoundException();
        }
        return distance;
    }

}
