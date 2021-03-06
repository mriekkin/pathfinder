package pathfinder.logic.neighbours;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pathfinder.datastructures.List;
import pathfinder.logic.Graph;
import pathfinder.logic.Node;
import pathfinder.logic.Pair;

public class NeighboursCcAllowedTest {

    Graph g;
    Neighbours neighbours;

    @Before
    public void setUp() {
        g = new Graph(new Pair(10, 20), new Pair(0, 0), new Pair(9, 19));

        // For these tests we do allow corner-cutting
        neighbours = new Neighbours(g, true);
    }

    @Test
    public void allowsCornerCutting() {
        g.getNode(5, 4).setWalkable(false); // Block non-diagonal neighbours
        g.getNode(5, 6).setWalkable(false); // Diagonal neighbours remain unblocked
        g.getNode(4, 5).setWalkable(false);
        g.getNode(6, 5).setWalkable(false);
        List<Node> n = neighbours.getNeighbours(5, 5);
        assertEquals(4, n.size());
        assertEquals("(4, 4)", n.get(0).toString()); // Diagonal neighbours should be returned
        assertEquals("(6, 4)", n.get(1).toString()); // because corner-cutting is allowed
        assertEquals("(4, 6)", n.get(2).toString());
        assertEquals("(6, 6)", n.get(3).toString());
    }

    //==========================================================================
    //
    // The following tests are the same for both: corner-cutting enabled or disabled
    //
    //==========================================================================

    @Test
    public void returnsListOfAdjacentWalkableCells() {
        List<Node> n = neighbours.getNeighbours(5, 5);
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
    public void excludesUnwalkableCells() {
        g.getNode(4, 4).setWalkable(false);
        g.getNode(5, 4).setWalkable(false);
        g.getNode(6, 4).setWalkable(false);
        g.getNode(4, 5).setWalkable(false);
        g.getNode(6, 5).setWalkable(false);
        g.getNode(4, 6).setWalkable(false);
        g.getNode(5, 6).setWalkable(false);
        g.getNode(6, 6).setWalkable(false);
        List<Node> n = neighbours.getNeighbours(5, 5);
        assertTrue(n.isEmpty());
    }

    @Test
    public void worksInTheUpperLeftCorner() {
        List<Node> n = neighbours.getNeighbours(0, 0);
        assertEquals(3, n.size());
        assertEquals("(1, 0)", n.get(0).toString());
        assertEquals("(0, 1)", n.get(1).toString());
        assertEquals("(1, 1)", n.get(2).toString());
    }

    @Test
    public void worksInTheLowerRightCorner() {
        List<Node> n = neighbours.getNeighbours(9, 19);
        assertEquals(3, n.size());
        assertEquals("(8, 18)", n.get(0).toString());
        assertEquals("(9, 18)", n.get(1).toString());
        assertEquals("(8, 19)", n.get(2).toString());
    }

    @Test
    public void acceptsNodeObjectAsArgument() {
        List<Node> list1 = neighbours.getNeighbours(5, 5);
        List<Node> list2 = neighbours.getNeighbours(g.getNode(5, 5));
        assertEquals(list1, list2);
    }

}
