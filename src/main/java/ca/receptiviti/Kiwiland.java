package ca.receptiviti;

import ca.receptiviti.builder.GridBuilder;
import ca.receptiviti.model.Grid;

public class Kiwiland {

    public static final String PATHS = "AB5,BC4,CD8,DC8,DE6,AD5,CE2,EB3,AE7";

    public static void main(String[] args) {
        Kiwiland kiwiland = new Kiwiland();
        kiwiland.run(PATHS);
    }

    private void run(String paths) {
        Grid grid = GridBuilder.buildFromPaths(paths);

        int distance = simpleDistance("ABC");
    }

    private int simpleDistance(String route) {
        return 0;
    }

}
