package pathfinder.logic.pathfinders;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pathfinder.logic.Graph;
import pathfinder.logic.Node;
import pathfinder.logic.Pair;

public class HeuristicsTest {

    public static final double eps = 0.00001;

    Graph g;

    @Before
    public void setUp() {
        Pair dimensions = new Pair(10, 10);
        Pair source = new Pair(0, 0);
        Pair dest = new Pair(9, 9);
        g = new Graph(dimensions, source, dest);
    }

    @Test
    public void heuristicsReturnCorrectHorizontalDist() {
        Node a = g.getNode(0, 0);
        Node b = g.getNode(9, 0);
        assertEquals(9, Heuristics.manhattanDist(a, b), eps);
        assertEquals(9, Heuristics.octileDist(a, b), eps);
    }

    @Test
    public void heuristicsReturnCorrectVerticalDist() {
        Node a = g.getNode(0, 0);
        Node b = g.getNode(0, 9);
        assertEquals(9, Heuristics.manhattanDist(a, b), eps);
        assertEquals(9, Heuristics.octileDist(a, b), eps);
    }

    @Test
    public void manhattanDistReturnsCorrectDiagonalDist() {
        Node a = g.getNode(0, 0);
        Node b = g.getNode(9, 9);
        assertEquals(18, Heuristics.manhattanDist(a, b), eps);
    }

    @Test
    public void octileDistReturnsCorrectDiagonalDist() {
        Node a = g.getNode(0, 0);
        Node b = g.getNode(9, 9);
        assertEquals(9 * Math.sqrt(2), Heuristics.octileDist(a, b), eps);
    }

}
