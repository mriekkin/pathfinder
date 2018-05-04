package pathfinder.logic.pathfinders;

import org.junit.Test;
import pathfinder.logic.Graph;
import pathfinder.logic.Pair;
import static org.junit.Assert.*;

public class DijkstraTest {

    public DijkstraTest() {
    }

    @Test
    public void testFind() {
        Pair dimensions = new Pair(10, 10);
        Pair start = new Pair(0, 0);
        Pair end = new Pair(9, 9);
        Graph g = new Graph(dimensions, start, end);
        Dijkstra pathfinder = new Dijkstra(g);
        assertEquals(18, pathfinder.find());
    }

    @Test
    public void testGetVisited() {
    }

    @Test
    public void testGetDist() {
    }

    @Test
    public void testGetPred() {
    }

}