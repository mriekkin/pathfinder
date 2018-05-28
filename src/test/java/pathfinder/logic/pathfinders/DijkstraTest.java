package pathfinder.logic.pathfinders;

import org.junit.Test;
import pathfinder.logic.Graph;
import pathfinder.logic.Pair;
import static org.junit.Assert.*;

public class DijkstraTest {

    @Test
    public void findReturnsCorrectPathLength() {
        Pair dimensions = new Pair(10, 10);
        Pair start = new Pair(0, 0);
        Pair end = new Pair(9, 9);
        Graph g = new Graph(dimensions, start, end);
        Pathfinder pathfinder = new Dijkstra(g);
        assertEquals(18, pathfinder.run());
    }

    @Test
    public void findReturnsMinusOneWhenNoPathExists() {
        Pair dimensions = new Pair(10, 10);
        Pair start = new Pair(0, 0);
        Pair end = new Pair(9, 9);
        Graph g = new Graph(dimensions, start, end);

        for (int y = 0; y < 10; y++) {
            g.getNode(5, y).setWalkable(false);
        }

        Pathfinder pathfinder = new Dijkstra(g);
        assertEquals(-1, pathfinder.run());
    }

}
