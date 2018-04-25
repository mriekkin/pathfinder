
package pathfinder.logic;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class GraphTest {

    public GraphTest() {
    }

    @Test
    public void testGetNode() {
        Graph g = new Graph(10, 10);
        assertEquals(0, g.getNode(0, 0).x());
        assertEquals(0, g.getNode(0, 0).y());
        assertEquals(8, g.getNode(8, 9).x());
        assertEquals(9, g.getNode(8, 9).y());
    }

    @Test
    public void testGetWidth() {
        Graph g = new Graph(10, 20);
        assertEquals(10, g.getWidth());
    }

    @Test
    public void testGetHeight() {
        Graph g = new Graph(10, 20);
        assertEquals(20, g.getHeight());
    }

    @Test
    public void testNeighbours_Node() {
    }

    @Test
    public void testNeighboursMiddle() {
        Graph g = new Graph(10, 10);
        List<Node> n = g.neighbours(5, 5);
        assertEquals(4, n.size());
        assertEquals("(5, 4)", n.get(0).toString());
        assertEquals("(6, 5)", n.get(1).toString());
        assertEquals("(5, 6)", n.get(2).toString());
        assertEquals("(4, 5)", n.get(3).toString());
    }
    
    @Test
    public void testNeighboursLowerLeftCorner() {
        Graph g = new Graph(10, 10);
        List<Node> n = g.neighbours(0, 0);
        assertEquals(2, n.size());
        assertEquals("(1, 0)", n.get(0).toString());
        assertEquals("(0, 1)", n.get(1).toString());
    }
    
    @Test
    public void testNeighboursUpperRightCorner() {
        Graph g = new Graph(10, 10);
        List<Node> n = g.neighbours(9, 9);
        assertEquals(2, n.size());
        assertEquals("(9, 8)", n.get(0).toString());
        assertEquals("(8, 9)", n.get(1).toString());
    }

}