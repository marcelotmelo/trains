package ca.receptiviti.model;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Optional;

/**
 * Represents a valid travel path between cities.
 */
public class Route implements HasLength, HasStops {

    private int distance;

    private final Deque<City> cities;

    /**
     * Creates a new Route with a starting point.
     *
     * @param source
     */
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

    /**
     * Returns the last added city.
     *
     * @return the last city or null if the travel path is empty.
     */
    public Optional<City> last() {
        return Optional.of(cities.peekLast());
    }

    /**
     * Checks if the city is present in the travel path.
     *
     * @param city The city to be searched for.
     * @return {@code true} if the city is in the travel path.
     */
    public boolean contains(City city) {
        return cities.contains(city);
    }

    /**
     * Returns the total distance that would be traveled if the path was followed from the first to the last city.
     *
     * @return the sum of distance between each city.
     */
    @Override
    public int length() {
        return this.distance;
    }

    /**
     * Returns the number of stops or hops that exist in the travel path.
     *
     * @return the number of stops or 0 if there are less than 2 cities in the travel path.
     */
    @Override
    public int stops() {
        return cities.isEmpty() ? 0 : cities.size() - 1;
    }

    /**
     * Helper method to build a new Route.
     *
     * @return The builder class.
     */
    public static RouterBuilder builder() {
        return new RouterBuilder();
    }

    /**
     * Helper builder class to build a new Route.
     */
    public static class RouterBuilder {

        Deque<City> cities = new LinkedList<>();

        int distance = 0;

        /**
         * Creates a new RouterBuilder with the same travel path as the given route.
         *
         * @param route The existing route.
         * @return A new route with the same travel path as the given route.
         */
        public RouterBuilder withRoute(Route route) {
            this.cities = new LinkedList<>();
            this.cities.addAll(route.cities);
            this.distance = route.distance;
            return this;
        }

        /**
         * Create a new RouterBuilder with a new route added. If a previous travel path existed, the given city will be
         * added to the travel path, otherwise it will be the first city.
         *
         * @param city     The city that will be added as the last element in the travel path.
         * @param distance The distance between the added city and the last city in the travel path.
         * @return The RouterBuilder.
         * @throws IllegalArgumentException if the travel path is empty and the informed distance is not zero.
         */
        public RouterBuilder addRoute(City city, int distance) {
            if (this.cities.isEmpty() && distance != 0) {
                throw new IllegalArgumentException("The informed distance must be zero since there is no other city to travel from.");
            }
            this.cities.add(city);
            this.distance += distance;
            return this;
        }

        /**
         * Returns a new Route.
         *
         * @return a new Route.
         */
        public Route build() {
            return new Route(this.cities, this.distance);
        }
    }
}
