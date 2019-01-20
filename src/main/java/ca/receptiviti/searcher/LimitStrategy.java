package ca.receptiviti.searcher;

import ca.receptiviti.model.Route;

import java.util.function.BiFunction;

/**
 * Strategy that will be used to define a limit based on a property of the @see ca.receptiviti.model.Route.
 */
@FunctionalInterface
interface LimitStrategy {

    /**
     * Returns whether the limit was reached based on a property of the  @see ca.receptiviti.model.Route class.
     *
     * @return
     */
    BiFunction<Route, Integer, Boolean> limit();

}
