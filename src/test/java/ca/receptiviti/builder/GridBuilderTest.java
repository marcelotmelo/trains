package ca.receptiviti.builder;

import ca.receptiviti.Kiwiland;
import ca.receptiviti.model.City;
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

    @Test
    public void buildFromPaths() {
        City A = new City("A");
        City B = new City("B");
        City C = new City("C");
        City D = new City("D");
        City E = new City("E");

        Grid grid = GridBuilder.buildFromPaths(Kiwiland.PATHS);
        collector.checkThat(grid, notNullValue());
        Set<City> cities = grid.getAllCities();
        collector.checkThat(cities.size(), is(5));
        collector.checkThat(cities.contains(A), is(true));
        collector.checkThat(cities.contains(B), is(true));
        collector.checkThat(cities.contains(C), is(true));
        collector.checkThat(cities.contains(D), is(true));
        collector.checkThat(cities.contains(E), is(true));

        Map<City, Integer> fromA = new HashMap<>();
        fromA.put(B, 5);
        fromA.put(D, 5);
        fromA.put(E, 7);

        Map<City, Integer> routesFromA = grid.routesFromCity(A);
        collector.checkThat(routesFromA, equalTo(fromA));

        Map<City, Integer> fromC = new HashMap<>();
        fromC.put(D, 8);
        fromC.put(E, 2);
        Map<City, Integer> routesFromC = grid.routesFromCity(C);

        collector.checkThat(routesFromC, equalTo(fromC));
        routesFromC.forEach(
                (k, v) -> {
                    if (k.equals(D)) {
                        collector.checkThat(v, is(8));
                    } else if (k.equals(E)) {
                        collector.checkThat(v, is(2));
                    } else {
                        throw new IllegalStateException("Wrong Path present");
                    }
                });
    }

    @Test
    public void citiesFromString() {
        String abcdef = "ABCDEF";
        String tclszfgthbnm = "tclszfghbnm";

        List<City> abcdefFromString = GridBuilder.citiesFromString(abcdef);
        City A = new City("A");
        City B = new City("B");
        City C = new City("C");
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