package ca.receptiviti.searcher;

import ca.receptiviti.Kiwiland;
import ca.receptiviti.builder.GridBuilder;
import ca.receptiviti.model.City;
import ca.receptiviti.model.Grid;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class NumberOfTripsTest {

    private NumberOfTrips numberOfTrips;

    @Before
    public void setup() {
        Grid grid = GridBuilder.buildFromPaths(Kiwiland.PATHS);
        numberOfTrips = new NumberOfTrips(grid);
    }

    @Test
    public void givenCtoCgetMaxStops() {
        City c = new City("C");
        int stops = 3;
        int tripsToBeFound = 2;

        int trips = numberOfTrips.maxStops(c, c, stops);
        assertThat(trips, equalTo(tripsToBeFound));
    }

    @Test
    public void givenAtoCgetMaxStops() {
        City a = new City("A");
        City c = new City("C");
        int stops = 4;
        int tripsToBeFound = 3;

        int trips = numberOfTrips.exactStops(a, c, stops);
        assertThat(trips, equalTo(tripsToBeFound));
    }
}