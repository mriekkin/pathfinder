package pathfinder.logic.pathfinders;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pathfinder.logic.Graph;
import pathfinder.logic.Node;
import pathfinder.logic.Pair;

public class JumpTest {

    Graph g;
    NeighbourPruningRules prune;

    @Before
    public void setUp() {
        Pair dimensions = new Pair(10, 10);
        Pair source = new Pair(0, 0);
        Pair dest = new Pair(9, 9);
        g = new Graph(dimensions, source, dest);

        g.getNode(5, 5).setWalkable(false);

        prune = new NeighbourPruningRules(g);
    }

    @Test
    public void returnsJumpPointSuccessorForHorizontalJump() {
        Jump jump = new Jump(g, prune);
        Node x = g.getNode(0, 4);
        int dx = 1;
        int dy = 0;
        assertEquals("(5, 4)", jump.jump(x, dx, dy).toString());
    }

    @Test
    public void returnsJumpPointSuccessorForDiagonalJump() {
        Jump jump = new Jump(g, prune);
        Node x = g.getNode(0, 0);
        int dx = 1;
        int dy = 1;
        assertEquals("(4, 4)", jump.jump(x, dx, dy).toString());
    }

}
