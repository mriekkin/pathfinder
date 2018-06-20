package pathfinder.gui;

import pathfinder.logic.Graph;
import pathfinder.logic.Pair;

/**
 * Creates an empty graph, and places the source and destination nodes to
 * default locations. It is the responsibility of this class to encode unified
 * default positions for the source and destination nodes.
 */
public class CreateNewGrid {

    /**
     * Creates an empty graph, and places the source and destination nodes to
     * default locations.
     *
     * @param cols number of columns
     * @param rows number of rows
     * @return an empty graph created as specified
     */
    public static Graph create(int cols, int rows) {
        Pair dimensions = new Pair(cols, rows);
        Pair start = new Pair(0, 0);
        Pair end = new Pair(cols - 1, rows - 1);

        return new Graph(dimensions, start, end);
    }

}
