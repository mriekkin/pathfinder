package pathfinder.logic.pathfinders;

import java.io.IOException;
import java.nio.file.Paths;
import org.junit.Test;
import static org.junit.Assert.*;
import pathfinder.io.GraphReader;
import pathfinder.logic.Graph;
import pathfinder.logic.Pair;

public class AStarTest {

    public static final double eps = 0.00001;

    @Test
    public void testInit() {
    }

    @Test
    public void returnsCorrectPathLength() {
        Pair dimensions = new Pair(10, 10);
        Pair source = new Pair(0, 0);
        Pair dest = new Pair(9, 9);
        Graph g = new Graph(dimensions, source, dest);
        Pathfinder pathfinder = new AStar(g);
        assertEquals(9 * Math.sqrt(2), pathfinder.run(), eps);
    }

    @Test
    public void returnsCorrectPathLengthForSmallGrid() throws IOException {
        Graph small = GraphReader.readFile(Paths.get("grids/tests/small.map"));
        Pathfinder pathfinder = new AStar(small);
        assertEquals(4 + 5 * Math.sqrt(2), pathfinder.run(), eps);
    }

    @Test
    public void returnsMinusOneWhenNoPathExists() {
        Pair dimensions = new Pair(10, 10);
        Pair source = new Pair(0, 0);
        Pair dest = new Pair(9, 9);
        Graph g = new Graph(dimensions, source, dest);

        for (int y = 0; y < 10; y++) {
            g.getNode(5, y).setWalkable(false);
        }

        Pathfinder pathfinder = new AStar(g);
        assertEquals(-1, pathfinder.run(), eps);
    }

}
