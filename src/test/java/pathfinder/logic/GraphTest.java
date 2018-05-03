
package pathfinder.logic;

import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GraphTest {

    Graph g;

    @Before
    public void setup() {
        g = new Graph(new Pair(10, 20), new Pair(0, 0), new Pair(9, 19));
    }

    @Test
    public void testGetNode() {
        assertEquals("(0, 0)", g.getNode(0, 0).toString());
        assertEquals("(5, 10)", g.getNode(5, 10).toString());
        assertEquals("(9, 19)", g.getNode(9, 19).toString());
    }

    @Test
    public void testGetWidth() {
        assertEquals(10, g.getCols());
    }

    @Test
    public void testGetHeight() {
        assertEquals(20, g.getRows());
    }

    @Test
    public void testNeighboursMiddle() {
        List<Node> n = g.neighbours(5, 5);
        assertEquals(4, n.size());
        assertEquals("(5, 4)", n.get(0).toString());
        assertEquals("(6, 5)", n.get(1).toString());
        assertEquals("(5, 6)", n.get(2).toString());
        assertEquals("(4, 5)", n.get(3).toString());
    }
    
    @Test
    public void testNeighboursLowerLeftCorner() {
        List<Node> n = g.neighbours(0, 0);
        assertEquals(2, n.size());
        assertEquals("(1, 0)", n.get(0).toString());
        assertEquals("(0, 1)", n.get(1).toString());
    }
    
    @Test
    public void testNeighboursUpperRightCorner() {
        List<Node> n = g.neighbours(9, 19);
        assertEquals(2, n.size());
        assertEquals("(9, 18)", n.get(0).toString());
        assertEquals("(8, 19)", n.get(1).toString());
    }

    @Test
    public void testNeighbours_Node() {
        List<Node> list1 = g.neighbours(5, 5);
        List<Node> list2 = g.neighbours(g.getNode(5, 5));
        assertEquals(list1, list2);
    }

}