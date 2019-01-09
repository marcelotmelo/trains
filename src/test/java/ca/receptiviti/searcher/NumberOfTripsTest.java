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
        City C = new City("C");
        long stops = 3;
        long tripsToBeFound = 2;

        long trips = numberOfTrips.maxStops(C, C, stops);
        assertThat(trips, equalTo(tripsToBeFound));
    }

    @Test
    public void givenAtoCgetMaxStops() {
        City A = new City("A");
        City C = new City("C");
        long stops = 4;
        long tripsToBeFound = 1;

        long trips = numberOfTrips.exactStops(A, C, stops);
        assertThat(trips, equalTo(tripsToBeFound));
    }
}