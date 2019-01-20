package ca.receptiviti.builder;

import ca.receptiviti.model.City;
import ca.receptiviti.model.Constants;
import ca.receptiviti.model.Grid;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

public class GridBuilderTest {

    @Rule
    public final ErrorCollector collector = new ErrorCollector();

    City a = new City(Constants.A);
    City b = new City(Constants.B);
    City c = new City(Constants.C);
    City d = new City("D");
    City e = new City("E");

    @Test
    public void givenConstantsPathBuild() {
        Grid grid = GridBuilder.buildFromPaths(Constants.PATHS);
        collector.checkThat(grid, notNullValue());
        Set<City> cities = grid.getCities();
        collector.checkThat(cities.size(), is(5));
        collector.checkThat(cities.contains(a), is(true));
        collector.checkThat(cities.contains(b), is(true));
        collector.checkThat(cities.contains(c), is(true));
        collector.checkThat(cities.contains(d), is(true));
        collector.checkThat(cities.contains(e), is(true));
    }

    @Test
    public void givenRouteCheckANeighbours() {
        Grid grid = GridBuilder.buildFromPaths(Constants.PATHS);
        Map<City, Integer> fromA = new HashMap<>();
        fromA.put(b, 5);
        fromA.put(d, 5);
        fromA.put(e, 7);

        Map<City, Integer> routesFromA = grid.neighbours(a);
        collector.checkThat(routesFromA, equalTo(fromA));
    }

    @Test
    public void givenRouteCheckCNeighbours() {
        Grid grid = GridBuilder.buildFromPaths(Constants.PATHS);
        Map<City, Integer> fromC = new HashMap<>();
        fromC.put(d, 8);
        fromC.put(e, 2);
        Map<City, Integer> routesFromC = grid.neighbours(c);

        collector.checkThat(routesFromC, equalTo(fromC));
        routesFromC.forEach(
                (k, v) -> {
                    if (k.equals(d)) {
                        collector.checkThat(v, is(8));
                    } else if (k.equals(e)) {
                        collector.checkThat(v, is(2));
                    } else {
                        throw new IllegalStateException("Wrong Path present");
                    }
                });
    }

    @Test
    public void givenStringOfCitiesCheckCreatedList() {
        String abcdef = "ABCDEF";

        List<City> abcdefFromString = GridBuilder.citiesFromString(abcdef);
        City A = new City(Constants.A);
        City B = new City(Constants.B);
        City C = new City(Constants.C);
        City D = new City("D");
        City E = new City("E");
        City F = new City("F");
        collector.checkThat(abcdefFromString.size(), is(6));
        collector.checkThat(abcdefFromString.contains(A), is(true));
        collector.checkThat(abcdefFromString.contains(B), is(true));
        collector.checkThat(abcdefFromString.contains(C), is(true));
        collector.checkThat(abcdefFromString.contains(D), is(true));
        collector.checkThat(abcdefFromString.contains(E), is(true));
        collector.checkThat(abcdefFromString.contains(F), is(true));
    }

    @Test
    public void givenStringOfCitiesLowerCaseCheckCreatedList() {
        String tclszfgthbnm = "tclszfghbnm";
        List<City> tclszFromString = GridBuilder.citiesFromString(tclszfgthbnm);

        City t = new City("t");
        City c = new City("c");
        City l = new City("l");
        City s = new City("s");
        City z = new City("z");
        City f = new City("f");
        City g = new City("g");
        City h = new City("h");
        City b = new City("b");
        City n = new City("n");
        City m = new City("m");
        collector.checkThat(tclszFromString.size(), is(11));
        collector.checkThat(tclszFromString.contains(t), is(true));
        collector.checkThat(tclszFromString.contains(c), is(true));
        collector.checkThat(tclszFromString.contains(l), is(true));
        collector.checkThat(tclszFromString.contains(s), is(true));
        collector.checkThat(tclszFromString.contains(z), is(true));
        collector.checkThat(tclszFromString.contains(f), is(true));
        collector.checkThat(tclszFromString.contains(g), is(true));
        collector.checkThat(tclszFromString.contains(h), is(true));
        collector.checkThat(tclszFromString.contains(b), is(true));
        collector.checkThat(tclszFromString.contains(n), is(true));
        collector.checkThat(tclszFromString.contains(m), is(true));
    }

}
