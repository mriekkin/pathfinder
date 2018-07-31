package pathfinder.logic.pathfinders;

import org.junit.Test;
import pathfinder.logic.Graph;
import pathfinder.logic.Pair;
import static org.junit.Assert.*;

public class DijkstraTest {

    public static final double eps = 0.00001;

    @Test
    public void findReturnsCorrectPathLength() {
        Pair dimensions = new Pair(10, 10);
        Pair source = new Pair(0, 0);
        Pair dest = new Pair(9, 9);
        Graph g = new Graph(dimensions, source, dest);
        Pathfinder pathfinder = new Dijkstra(g);
        assertEquals(9 * Math.sqrt(2), pathfinder.run(), eps);
    }

    @Test
    public void findReturnsMinusOneWhenNoPathExists() {
        Pair dimensions = new Pair(10, 10);
        Pair source = new Pair(0, 0);
        Pair dest = new Pair(9, 9);
        Graph g = new Graph(dimensions, source, dest);

        for (int y = 0; y < 10; y++) {
            g.getNode(5, y).setWalkable(false);
        }

        Pathfinder pathfinder = new Dijkstra(g);
        assertEquals(-1, pathfinder.run(), eps);
    }

}
