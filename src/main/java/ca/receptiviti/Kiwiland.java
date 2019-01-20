package ca.receptiviti;

import ca.receptiviti.builder.GridBuilder;
import ca.receptiviti.exception.RouteNotFoundException;
import ca.receptiviti.model.City;
import ca.receptiviti.model.Constants;
import ca.receptiviti.model.Grid;
import ca.receptiviti.searcher.NumberOfTrips;
import ca.receptiviti.searcher.RouteLength;
import ca.receptiviti.searcher.SimpleDistance;

import java.util.List;

/**
 * Main execution class. Executes a series of pre-defined steps and outputs its results.
 */
public class Kiwiland {

    public static void main(String[] args) {
        Kiwiland kiwiland = new Kiwiland();
        kiwiland.run(Constants.PATHS);
    }

    private void run(String paths) {
        Grid grid = GridBuilder.buildFromPaths(paths);

        SimpleDistance simpleDistance = new SimpleDistance(grid);
        simpleDistance(Constants.ABC, simpleDistance);
        simpleDistance(Constants.AD, simpleDistance);
        simpleDistance(Constants.ADC, simpleDistance);
        simpleDistance(Constants.AEBCD, simpleDistance);
        simpleDistance(Constants.AED, simpleDistance);

        City c = new City(Constants.C);
        NumberOfTrips numberOfTrips = new NumberOfTrips(grid);
        try {
            int stops = numberOfTrips.maxStops(c, c, 3);
            System.out.println(stops);
        } catch (RouteNotFoundException e) {
            System.out.println("NO SUCH ROUTE");
        }
        City a = new City(Constants.A);
        try {
            int stops = numberOfTrips.exactStops(a, c, 4);
            System.out.println(stops);
        } catch (RouteNotFoundException e) {
            System.out.println("NO SUCH ROUTE");
        }

        RouteLength routeLength = new RouteLength(grid);
        shortest(a, c, routeLength);
        City b = new City(Constants.B);
        shortest(b, b, routeLength);
        try {
            int trips = routeLength.tripsWithMaxLength(c, c, 30);
            System.out.println(trips);
        } catch (RouteNotFoundException e) {
            System.out.println("NO SUCH ROUTE");
        }
    }

    private void simpleDistance(String route, SimpleDistance simpleDistance) {
        List<City> cities = GridBuilder.citiesFromString(route);
        try {
            int distance = simpleDistance.getDistance(cities);
            System.out.println(distance);
        } catch (RouteNotFoundException e) {
            System.out.println("NO SUCH ROUTE");
        }
    }

    private void shortest(City source, City destination, RouteLength routeLength) {
        try {
            int shortest = routeLength.shortest(source, destination);
            System.out.println(shortest);
        } catch (RouteNotFoundException e) {
            System.out.println("NO SUCH ROUTE");
        }
    }
}
