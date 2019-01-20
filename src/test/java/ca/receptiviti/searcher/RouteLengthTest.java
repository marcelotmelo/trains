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
        City a = new City("A");
        City c = new City("C");
        int expected = 9;
        assertShortest(expected, a, c);
    }

    @Test
    public void givenSourceBDestinationBShortest() throws Exception {
        City b = new City("B");
        int expected = 9;
        assertShortest(expected, b, b);
    }

    @Test
    public void givenSourceCDestinationCMaxLength() throws Exception {
        City c = new City("C");
        int expected = 7;

        int trips = routeLength.tripsWithMaxLength(c, c, 30);
        assertThat(trips, equalTo(expected));
    }

    private void assertShortest(int expected, City source, City destination) throws Exception {
        int length = routeLength.shortest(source, destination);
        assertThat(length, equalTo(expected));
    }

}