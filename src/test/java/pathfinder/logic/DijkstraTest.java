package pathfinder.logic;

import org.junit.Test;
import static org.junit.Assert.*;

public class DijkstraTest {

    public DijkstraTest() {
    }

    @Test
    public void testFind() {
        Graph g = new Graph(10, 10);
        Dijkstra pathfinder = new Dijkstra(g, g.getNode(0, 0), g.getNode(9, 9));
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