package pathfinder.logic;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class NodeTest {

    Node node;
    Node node2;

    @Before
    public void setUp() {
        node = new Node(1, 2, true);
        node2 = new Node(1, 3, false);
    }

    @Test
    public void xReturnsTheXCoordinate() {
        assertEquals(1, node.x());
    }

    @Test
    public void yReturnsTheYCoordinate() {
        assertEquals(2, node.y());
    }

    @Test
    public void isWalkableReturnsTrueForWalkableNode() {
        assertTrue(node.isWalkable());
    }

    @Test
    public void isWalkableReturnsFalseForUnwalkableNode() {
        assertFalse(node2.isWalkable());
    }

    @Test
    public void setWalkableSetsWalkability() {
        node.setWalkable(false);
        assertFalse(node.isWalkable());
    }

    @Test
    public void toStringWorks() {
        assertEquals("(1, 2)", node.toString());
    }

    @Test
    public void equalsWorks() {
        assertTrue(node.equals(node));
        assertTrue(node.equals(new Node(1, 2, true)));
        assertTrue(node.equals(new Node(1, 2, false))); // Compares only coordinates
        assertFalse(node.equals(null));
        assertFalse(node.equals(1));
        assertFalse(node.equals(new Node(1, 3, true)));
        assertFalse(node.equals(new Node(2, 3, true)));
    }

}
