package ca.receptiviti.searcher;

import ca.receptiviti.model.Route;

import java.util.function.BiFunction;

/**
 * A strategy that defines the limit based on the {@see ca.receptiviti.model.Route#stops()}.
 */
class StopLimitStrategy implements LimitStrategy {

    @Override
    public BiFunction<Route, Integer, Boolean> limit() {
        return (route, limit) -> route.stops() <= limit;
    }
}
