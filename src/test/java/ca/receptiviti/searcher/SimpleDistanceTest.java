package ca.receptiviti.searcher;

import ca.receptiviti.Kiwiland;
import ca.receptiviti.builder.GridBuilder;
import ca.receptiviti.exception.RouteNotFoundException;
import ca.receptiviti.model.City;
import ca.receptiviti.model.Grid;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class SimpleDistanceTest {

    private SimpleDistance simpleDistance;

    @Before
    public void setup() {
        Grid grid = GridBuilder.buildFromPaths(Kiwiland.PATHS);
        simpleDistance = new SimpleDistance(grid);
    }


    @Test
    public void givenABCGetDistance() throws Exception {
        List<City> cities = GridBuilder.citiesFromString("ABC");
        int distance = simpleDistance.getDistance(cities);
        assertThat(distance, equalTo(9));
    }

    @Test
    public void givenADGetDistance() throws Exception {
        List<City> cities = GridBuilder.citiesFromString("AD");
        int distance = simpleDistance.getDistance(cities);
        assertThat(distance, equalTo(5));
    }

    @Test
    public void givenADCDGetDistance() throws Exception {
        List<City> cities = GridBuilder.citiesFromString("ADC");
        int distance = simpleDistance.getDistance(cities);
        assertThat(distance, equalTo(13));
    }

    @Test
    public void givenAEBCDGetDistance() throws Exception {
        List<City> cities = GridBuilder.citiesFromString("AEBCD");
        int distance = simpleDistance.getDistance(cities);
        assertThat(distance, equalTo(22));
    }

    @Test(expected = RouteNotFoundException.class)
    public void givenAEDThrowRouteNotFoundException() throws Exception {
        List<City> cities = GridBuilder.citiesFromString("AED");
        simpleDistance.getDistance(cities);
    }

    @Test(expected = RouteNotFoundException.class)
    public void givenCEBAThrowRouteNotFoundException() throws Exception {
        List<City> cities = GridBuilder.citiesFromString("CEBA");
        simpleDistance.getDistance(cities);
    }

    @Test(expected = RouteNotFoundException.class)
    public void givenAEBCEDThrowRouteNotFoundException() throws Exception {
        List<City> cities = GridBuilder.citiesFromString("AEBCED");
        simpleDistance.getDistance(cities);
    }

}