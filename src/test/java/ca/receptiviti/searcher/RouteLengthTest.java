package ca.receptiviti.searcher;

import ca.receptiviti.Kiwiland;
import ca.receptiviti.builder.GridBuilder;
import ca.receptiviti.model.City;
import ca.receptiviti.model.Grid;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RouteLengthTest {

    private RouteLength routeLength;

    @Before
    public void setup() {
        Grid grid = GridBuilder.buildFromPaths(Kiwiland.PATHS);
        routeLength = new RouteLength(grid);
    }

    @Test
    public void givenSourceADestinationCShortestExpect9() {
        City a = new City("A");
        City c = new City("C");
        int expectedLength = 9;

        int length = routeLength.shortest(a, c);
        assertThat(length, equalTo(expectedLength));
    }

    @Test
    public void givenSourceBDestinationBShortestExpect9() {
        City b = new City("B");
        int expectedLength = 9;

        int length = routeLength.shortest(b, b);
        assertThat(length, equalTo(expectedLength));
    }

    @Test
    public void givenSourceCDestinationCMaxLengthExpect7() {
        City c = new City("C");
        int expected = 7;

        int trips = routeLength.tripsWithMaxLength(c, c, 30);
        assertThat(trips, equalTo(expected));
    }

}