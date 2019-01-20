package ca.receptiviti.searcher;

import ca.receptiviti.builder.GridBuilder;
import ca.receptiviti.model.City;
import ca.receptiviti.model.Constants;
import ca.receptiviti.model.Grid;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RouteLengthTest {

    private RouteLength routeLength;

    @Before
    public void setup() {
        Grid grid = GridBuilder.buildFromPaths(Constants.PATHS);
        routeLength = new RouteLength(grid);
    }

    @Test
    public void givenSourceADestinationCShortest() throws Exception {
        City a = new City(Constants.A);
        City c = new City(Constants.C);
        int expected = 9;
        assertShortest(expected, a, c);
    }

    @Test
    public void givenSourceADestinationEShortest() throws Exception {
        City a = new City(Constants.A);
        City e = new City("E");
        int expected = 7;

        assertShortest(expected, a, e);
    }


    @Test
    public void givenSourceBDestinationBShortest() throws Exception {
        City b = new City(Constants.B);
        int expected = 9;
        assertShortest(expected, b, b);
    }

    @Test
    public void givenSourceCDestinationCMaxLength() throws Exception {
        City c = new City(Constants.C);
        int expected = 7;

        int trips = routeLength.tripsWithMaxLength(c, c, 30);
        assertThat(trips, equalTo(expected));
    }

    @Test
    public void givenSourceADestinationDMaxLength() throws Exception {
        City a = new City(Constants.A);
        City d = new City("D");
        int expected = 2;

        int trips = routeLength.tripsWithMaxLength(a, d, 20);
        assertThat(trips, equalTo(expected));
    }


    @Test
    public void givenSourceADestinationEMaxLength() throws Exception {
        City a = new City(Constants.A);
        City e = new City("E");
        int expected = 9;

        int trips = routeLength.tripsWithMaxLength(a, e, 25);
        assertThat(trips, equalTo(expected));
    }

    private void assertShortest(int expected, City source, City destination) throws Exception {
        int length = routeLength.shortest(source, destination);
        assertThat(length, equalTo(expected));
    }

}