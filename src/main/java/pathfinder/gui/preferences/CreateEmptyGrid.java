package pathfinder.gui.preferences;

import pathfinder.logic.Graph;
import pathfinder.logic.Pair;

/**
 * Creates an empty <code>Graph</code> and places the start and end points to
 * default locations.
 * 
 */
public class CreateEmptyGrid {

    /**
     * Creates an empty <code>Graph</code> and places the start and end points
     * to default locations.
     * 
     * @param cols number of columns
     * @param rows number of rows
     * @return an empty graph created as specified
     */
    public static Graph create(int cols, int rows) {
        Pair dimensions = new Pair(cols, rows);
        Pair start = new Pair(1, rows/2);
        Pair end = new Pair(cols-2, rows/2);

        return new Graph(dimensions, start, end);
    }

}
