package pathfinder.logic.pathfinders;

import java.nio.file.Paths;
import org.junit.Test;
import static org.junit.Assert.*;
import pathfinder.io.GraphReader;
import pathfinder.logic.Graph;
import pathfinder.logic.Pair;
import pathfinder.logic.neighbours.Neighbours;

public class AStarTest {

    public static final double eps = 0.000001;

    private static Neighbours getNeighbours(Graph g) {
        return new Neighbours(g, false);
    }

    @Test
    public void returnsCorrectPathLength() {
        Pair dimensions = new Pair(10, 10);
        Pair source = new Pair(0, 0);
        Pair dest = new Pair(9, 9);
        Graph g = new Graph(dimensions, source, dest);
        Pathfinder pathfinder = new AStar(g, getNeighbours(g));
        assertEquals(9 * Math.sqrt(2), pathfinder.run(), eps);
    }

    @Test
    public void returnsCorrectPathLengthForSmallGrid() throws Exception {
        Graph small = GraphReader.readFile(Paths.get("grids/tests/small.map"));
        Pathfinder pathfinder = new AStar(small, getNeighbours(small));
        assertEquals(4 + 5 * Math.sqrt(2), pathfinder.run(), eps);
    }

    @Test
    public void returnsCorrectPathLengthForBigGrid() throws Exception {
        Graph big = GraphReader.readFile(Paths.get("grids/tests/lak100d.map"));
        Pathfinder pathfinder = new AStar(big, getNeighbours(big));
        assertEquals(812.06810913, pathfinder.run(), eps);
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

        Pathfinder pathfinder = new AStar(g, getNeighbours(g));
        assertEquals(-1, pathfinder.run(), eps);
    }

}
