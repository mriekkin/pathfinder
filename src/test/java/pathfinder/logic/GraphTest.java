package pathfinder.logic;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GraphTest {

    Graph g;

    @Before
    public void setUp() {
        g = new Graph(new Pair(10, 20), new Pair(0, 0), new Pair(9, 19));
    }

    @Test
    public void getNodeReturnsTheRequestedNode() {
        assertEquals("(0, 0)", g.getNode(0, 0).toString());
        assertEquals("(5, 10)", g.getNode(5, 10).toString());
        assertEquals("(9, 19)", g.getNode(9, 19).toString());
    }

    @Test
    public void getNodeReturnsNullForNegativeX() {
        assertNull(g.getNode(-1, 0));
    }

    @Test
    public void getNodeReturnsNullForNegativeY() {
        assertNull(g.getNode(0, -1));
    }

    @Test
    public void getNodeReturnsNullForTooLargeX() {
        assertNull(g.getNode(10, 19));
    }

    @Test
    public void getNodeReturnsNullForTooLargeY() {
        assertNull(g.getNode(9, 20));
    }

    @Test
    public void getColsReturnsTheNumberOfColumns() {
        assertEquals(10, g.getCols());
    }

    @Test
    public void getRowsReturnsTheNumberOfRows() {
        assertEquals(20, g.getRows());
    }

}
