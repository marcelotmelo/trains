package ca.receptiviti.searcher;

import ca.receptiviti.exception.RouteNotFoundException;
import ca.receptiviti.model.City;
import ca.receptiviti.model.Grid;

import java.util.List;

/**
 * Calculates the distance traveled between an informed route.
 */
public class SimpleDistance {

    private final Grid grid;

    /**
     * Creates a new calculator based on a given Grid.
     *
     * @param grid The grid with all possible travel paths between cities.
     */
    public SimpleDistance(Grid grid) {
        this.grid = grid;
    }

    /**
     * Calculates the traveled distance between the informed list of cities, sequentially.
     *
     * @param cities The list of cities to be traveled.
     * @return The sum of the distance between the traveled cities.
     * @throws RouteNotFoundException If the informed list of cities can not be traveled. A direct path does not exist between at list two of the informed cities.
     */
    public int getDistance(List<City> cities) throws RouteNotFoundException {
        int distance = 0;
        for (int i = 0; i < cities.size() - 1; i++) {
            City origin = cities.get(i);
            City destination = cities.get(i + 1);
            distance += grid.distance(origin, destination);
        }
        return distance;
    }

}
