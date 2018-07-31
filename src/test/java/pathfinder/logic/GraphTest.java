package pathfinder.logic;

import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;

public class GraphTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

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
    public void getNodeThrowsExceptionForNegativeX() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Coordinates out of range (-1, 0)");
        g.getNode(-1, 0);
    }

    @Test
    public void getNodeThrowsExceptionForNegativeY() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Coordinates out of range (0, -1)");
        g.getNode(0, -1);
    }

    @Test
    public void getNodeThrowsExceptionTooLargeX() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Coordinates out of range (10, 19)");
        g.getNode(10, 19);
    }

    @Test
    public void getNodeThrowsExceptionTooLargeY() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Coordinates out of range (9, 20)");
        g.getNode(9, 20);
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
    public void neighboursAcceptsNodeObjectAsArgument() {
        List<Node> list1 = g.neighbours(5, 5);
        List<Node> list2 = g.neighbours(g.getNode(5, 5));
        assertEquals(list1, list2);
    }

}
