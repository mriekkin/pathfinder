package pathfinder.logic;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CurrentGraphTest {

    Graph graph;
    Graph graph2;
    CurrentGraph current;

    private class GraphListener implements PropertyChangeListener {

        boolean received;

        public GraphListener() {
            received = false;
        }

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            if ("graph".equals(evt.getPropertyName())) {
                received = true;
            }
        }

    }

    @Before
    public void setUp() {
        Pair dimensions = new Pair(10, 10);
        Pair start = new Pair(0, 0);
        Pair end = new Pair(9, 9);
        graph = new Graph(dimensions, start, end);
        graph2 = new Graph(dimensions, start, end);
        current = new CurrentGraph(graph);
    }

    @Test
    public void addPropertyChangeListenerRegistersSpecifiedListener() {
        GraphListener listener = new GraphListener();
        current.addPropertyChangeListener("graph", listener);
        current.setGraph(graph2);
        assertTrue(listener.received);
    }

    @Test
    public void removePropertyChangeListenerRemovesSpecifiedListener() {
        GraphListener listener = new GraphListener();
        current.addPropertyChangeListener("graph", listener);
        current.removePropertyChangeListener("graph", listener);
        current.setGraph(graph2);
        assertFalse(listener.received);
    }

    @Test
    public void getGraphReturnsTheCurrentGraph() {
        assertEquals(graph, current.getGraph());
    }

    @Test
    public void setGraphUpdatesTheCurrentGraph() {
        Pair dimensions = new Pair(30, 15);
        Pair start = new Pair(0, 0);
        Pair end = new Pair(29, 14);
        Graph another = new Graph(dimensions, start, end);
        current.setGraph(another);
        assertEquals(another, current.getGraph());
    }

    @Test
    public void getStartReturnsTheStartNode() {
        assertEquals(graph.getStart(), current.getStart());
    }

    @Test
    public void getEndReturnsTheEndNode() {
        assertEquals(graph.getEnd(), current.getEnd());
    }

    @Test
    public void setStartUpdatesTheStartNode() {
        current.setStart(graph.getNode(1, 1));
        assertEquals("(1, 1)", current.getStart().toString());
    }

    @Test
    public void setEndUpdatesTheEndNode() {
        current.setEnd(graph.getNode(5, 5));
        assertEquals("(5, 5)", current.getEnd().toString());
    }

    @Test
    public void getColsReturnsTheNumberOfColumns() {
        assertEquals(10, current.getCols());
    }

    @Test
    public void getRowsReturnsTheNumberOfRows() {
        assertEquals(10, current.getRows());
    }

}
