package ca.receptiviti.searcher;

import ca.receptiviti.model.Route;

import java.util.function.BiFunction;

interface LimitStrategy {

    BiFunction<Route, Integer, Boolean> limit();

}
