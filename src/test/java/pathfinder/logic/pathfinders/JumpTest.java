package pathfinder.logic.pathfinders;

import java.io.IOException;
import java.io.StringReader;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pathfinder.io.GraphReader;
import pathfinder.logic.Graph;
import pathfinder.logic.Node;
import pathfinder.logic.Pair;

public class JumpTest {

    Graph g;
    NeighbourPruningRules prune;

    @Before
    public void setUp() throws IOException {
        Pair dimensions = new Pair(10, 10);
        Pair source = new Pair(0, 0);
        Pair dest = new Pair(9, 9);
        g = new Graph(dimensions, source, dest);

//        g = GraphReader.read(new StringReader(""
//                + "type octile\n"
//                + "height 10\n"
//                + "width 10\n"
//                + "map\n"
//                + "..........\n"
//                + "..........\n"
//                + "..........\n"
//                + "..........\n"
//                + "..........\n"
//                + ".....#....\n"
//                + "..........\n"
//                + "..........\n"
//                + "..........\n"
//                + "..........\n"));

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

    @Test
    public void returnsJumpPointSuccessorForExampleA() throws IOException {
        // This is from Figure 1(a) in the JPS paper by Harabor and Grastien
        g = GraphReader.read(new StringReader(""
                + "type octile\n"
                + "height 5\n"
                + "width 7\n"
                + "map\n"
                + ".......\n"
                + ".......\n"
                + "#....#.\n"
                + ".x...y.\n"
                + "#......\n"));
        prune = new NeighbourPruningRules(g);
        Jump jump = new Jump(g, prune);
        Node x = g.getNode(1, 3);
        int dx = 1;
        int dy = 0;
        assertEquals("(5, 3)", jump.jump(x, dx, dy).toString());
    }

    @Test
    public void returnsJumpPointSuccessorForExampleB() throws IOException {
        // This is from Figure 1(b) in the JPS paper by Harabor and Grastien
        g = GraphReader.read(new StringReader(""
                + "type octile\n"
                + "height 5\n"
                + "width 7\n"
                + "map\n"
                + "...y...\n"
                + ".....#.\n"
                + ".x...#.\n"
                + ".#...#.\n"
                + ".#.....\n"));
        prune = new NeighbourPruningRules(g);
        Jump jump = new Jump(g, prune);
        Node x = g.getNode(1, 2);
        int dx = 1;
        int dy = -1;
        assertEquals("(3, 0)", jump.jump(x, dx, dy).toString());
    }

}
