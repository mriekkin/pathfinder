package pathfinder.logic;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CurrentGraphTest {

    Graph graph;
    CurrentGraph current;

    @Before
    public void setUp() {
        Pair dimensions = new Pair(10, 10);
        Pair start = new Pair(0, 0);
        Pair end = new Pair(9, 9);
        graph = new Graph(dimensions, start, end);
        current = new CurrentGraph(graph);
    }

    @Test
    public void testAddPropertyChangeListener() {
    }

    @Test
    public void testRemovePropertyChangeListener() {
    }

    @Test
    public void testGetGraph() {
        assertEquals(graph, current.getGraph());
    }

    @Test
    public void testSetGraph() {
        Pair dimensions = new Pair(30, 15);
        Pair start = new Pair(0, 0);
        Pair end = new Pair(29, 14);
        Graph another = new Graph(dimensions, start, end);
        current.setGraph(another);
        assertEquals(another, current.getGraph());
    }

    @Test
    public void testGetStart() {
        assertEquals(graph.getStart(), current.getStart());
    }

    @Test
    public void testGetEnd() {
        assertEquals(graph.getEnd(), current.getEnd());
    }

    @Test
    public void testSetStart() {
        current.setStart(graph.getNode(1, 1));
        assertEquals("(1, 1)", current.getStart().toString());
    }

    @Test
    public void testSetEnd() {
        current.setEnd(graph.getNode(5, 5));
        assertEquals("(5, 5)", current.getEnd().toString());
    }

    @Test
    public void testGetCols() {
        assertEquals(10, current.getCols());
    }

    @Test
    public void testGetRows() {
        assertEquals(10, current.getRows());
    }

}
