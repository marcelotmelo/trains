package ca.receptiviti.searcher;

import ca.receptiviti.model.City;
import ca.receptiviti.model.Grid;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class NumberOfTrips {

    private final Grid grid;

    private Stack<City> stack;

    private List<List<City>> paths;

    private Set<City> visited;

    private boolean first;

    public NumberOfTrips(Grid grid) {
        this.grid = grid;
    }

    public long maxStops(City origin, City destination, long stops) {
        buildPaths(origin, destination);
        return paths.stream()
                .filter(list -> list.size() - 1 <= stops)
                .count();
    }

    public long exactStops(City origin, City destination, long stops) {
        buildPaths(origin, destination);
        return paths.stream()
                .filter(list -> list.size() - 1 == stops)
                .count();
    }

    private void buildPaths(City origin, City destination) {
        first = true;
        stack = new Stack<>();
        visited = new HashSet<>();
        paths = new ArrayList<>();

        dfs(origin, destination);
    }

    private void dfs(City origin, City destination) {
        stack.push(origin);
        visited.add(origin);
        if (origin.equals(destination) && first) {
            visited.remove(origin);
        }
        if (origin.equals(destination) && !first) {
            addCurrentPath();
        } else {
            for (City city : grid.getNeighbours(origin)) {
                if (!visited.contains(city)) {
                    first = false;
                    dfs(city, destination);
                }
            }
        }
        stack.pop();
        visited.remove(origin);
    }

    private void addCurrentPath() {
        List<City> path = new ArrayList<>(stack);
        paths.add(path);
    }

}
