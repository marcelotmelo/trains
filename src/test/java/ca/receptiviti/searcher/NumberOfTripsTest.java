package ca.receptiviti.searcher;

import ca.receptiviti.builder.GridBuilder;
import ca.receptiviti.model.City;
import ca.receptiviti.model.Constants;
import ca.receptiviti.model.Grid;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class NumberOfTripsTest {

    private NumberOfTrips numberOfTrips;

    @Before
    public void setup() {
        Grid grid = GridBuilder.buildFromPaths(Constants.PATHS);
        numberOfTrips = new NumberOfTrips(grid);
    }

    @Test
    public void givenCtoCgetMaxStops() throws Exception {
        City c = new City(Constants.C);
        int stops = 3;
        int tripsToBeFound = 2;

        int trips = numberOfTrips.maxStops(c, c, stops);
        assertThat(trips, equalTo(tripsToBeFound));
    }

    @Test
    public void givenAtoCgetMaxStops() throws Exception {
        City a = new City(Constants.A);
        City c = new City(Constants.C);
        int stops = 4;
        int tripsToBeFound = 3;

        int trips = numberOfTrips.exactStops(a, c, stops);
        assertThat(trips, equalTo(tripsToBeFound));
    }

    @Test
    public void givenAtoDgetMaxStops() throws Exception {
        City a = new City(Constants.A);
        City d = new City("D");
        int stops = 7;
        int tripsToBeFound = 6;

        int trips = numberOfTrips.exactStops(a, d, stops);
        assertThat(trips, equalTo(tripsToBeFound));
    }

}
