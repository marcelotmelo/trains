package ca.receptiviti.exception;

/**
 * Exception if a route is not found between two cities.
 */
public class RouteNotFoundException extends Exception {

    public RouteNotFoundException() {
        super("NO SUCH ROUTE");
    }
}
