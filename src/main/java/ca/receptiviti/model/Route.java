package ca.receptiviti.model;

import java.util.Deque;
import java.util.LinkedList;

public class Route implements HasLength, HasStops {

    private int distance;

    private final Deque<City> cities;

    public Route(City source) {
        cities = new LinkedList<>();
        addCity(source, 0);
    }

    private Route(Deque<City> cities, int distance) {
        this.cities = cities;
        this.distance = distance;
    }

    private void addCity(City city, int distance) {
        cities.add(city);
        this.distance += distance;
    }

    public City last() {
        return cities.peekLast();
    }

    public boolean contains(City city) {
        return cities.contains(city);
    }

    @Override
    public int length() {
        return this.distance;
    }

    @Override
    public int stops() {
        return cities.isEmpty() ? 0 : cities.size() - 1;
    }

    public static RouterBuilder builder() {
        return new RouterBuilder();
    }

    public static class RouterBuilder {

        final Deque<City> cities = new LinkedList<>();

        int distance = 0;

        public RouterBuilder withRoute(Route route) {
            this.cities.addAll(route.cities);
            this.distance = route.distance;
            return this;
        }

        public RouterBuilder addRoute(City city, int distance) {
            this.cities.add(city);
            this.distance += distance;
            return this;
        }

        public Route build() {
            return new Route(this.cities, this.distance);
        }
    }
}
