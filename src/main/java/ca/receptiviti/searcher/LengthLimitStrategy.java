package ca.receptiviti.searcher;

import ca.receptiviti.model.Route;

import java.util.function.BiFunction;

public class LengthLimitStrategy implements LimitStrategy {

    @Override
    public BiFunction<Route, Integer, Boolean> limit() {
        return (route, limit) -> route.length() <= limit;
    }
}
