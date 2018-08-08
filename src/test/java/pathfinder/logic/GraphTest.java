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

    @Test
    public void neighboursReturnsListOfAdjacentWalkableCells() {
        List<Node> n = g.neighbours(5, 5);
        assertEquals(8, n.size());
        assertEquals("(4, 4)", n.get(0).toString());
        assertEquals("(5, 4)", n.get(1).toString());
        assertEquals("(6, 4)", n.get(2).toString());
        assertEquals("(4, 5)", n.get(3).toString());
        assertEquals("(6, 5)", n.get(4).toString());
        assertEquals("(4, 6)", n.get(5).toString());
        assertEquals("(5, 6)", n.get(6).toString());
        assertEquals("(6, 6)", n.get(7).toString());
    }

    @Test
    public void neighboursExcludesUnwalkableCells() {
        g.getNode(4, 4).setWalkable(false);
        g.getNode(5, 4).setWalkable(false);
        g.getNode(6, 4).setWalkable(false);
        g.getNode(4, 5).setWalkable(false);
        g.getNode(6, 5).setWalkable(false);
        g.getNode(4, 6).setWalkable(false);
        g.getNode(5, 6).setWalkable(false);
        g.getNode(6, 6).setWalkable(false);
        List<Node> n = g.neighbours(5, 5);
        assertTrue(n.isEmpty());
    }

    @Test
    public void neighboursWorksInTheUpperLeftCorner() {
        List<Node> n = g.neighbours(0, 0);
        assertEquals(3, n.size());
        assertEquals("(1, 0)", n.get(0).toString());
        assertEquals("(0, 1)", n.get(1).toString());
        assertEquals("(1, 1)", n.get(2).toString());
    }

    @Test
    public void neighboursWorksInTheLowerRightCorner() {
        List<Node> n = g.neighbours(9, 19);
        assertEquals(3, n.size());
        assertEquals("(8, 18)", n.get(0).toString());
        assertEquals("(9, 18)", n.get(1).toString());
        assertEquals("(8, 19)", n.get(2).toString());
    }

    @Test
    public void neighboursPreventsCornerCutting() {
        g.getNode(5, 4).setWalkable(false); // Block non-diagonal neighbours
        g.getNode(5, 6).setWalkable(false); // Diagonal neighbours remain unblocked
        g.getNode(4, 5).setWalkable(false);
        g.getNode(6, 5).setWalkable(false);
        List<Node> n = g.neighbours(5, 5); // Still, diagonal neighbours should not be returned
        assertTrue(n.isEmpty());           // because that would enable corner-cutting
    }

    @Test
    public void neighboursAcceptsNodeObjectAsArgument() {
        List<Node> list1 = g.neighbours(5, 5);
        List<Node> list2 = g.neighbours(g.getNode(5, 5));
        assertEquals(list1, list2);
    }

}
