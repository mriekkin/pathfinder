package pathfinder.logic.pathfinders;

import java.io.IOException;
import java.io.StringReader;
import org.junit.Test;
import static org.junit.Assert.*;
import pathfinder.io.GraphReader;
import pathfinder.logic.Graph;
import pathfinder.logic.Node;

public class JumpTest {

    @Test
    public void returnsJumpPointSuccessorForHorizontalJump() throws IOException {
        Graph g = GraphReader.read(new StringReader(""
                + "type octile\n"
                + "height 10\n"
                + "width 10\n"
                + "map\n"
                + "..........\n"
                + "..........\n"
                + "..........\n"
                + "..........\n"
                + "x....y....\n"
                + ".....#....\n"
                + "..........\n"
                + "..........\n"
                + "..........\n"
                + "..........\n"));
        NeighbourPruningRules prune = new NeighbourPruningRules(g);
        Jump jump = new Jump(g, prune);
        Node x = g.getNode(0, 4);
        int dx = 1;
        int dy = 0;
        assertEquals("(5, 4)", jump.jump(x, dx, dy).toString());
    }

    @Test
    public void returnsJumpPointSuccessorForDiagonalJump() throws IOException {
        Graph g = GraphReader.read(new StringReader(""
                + "type octile\n"
                + "height 10\n"
                + "width 10\n"
                + "map\n"
                + "x.........\n"
                + "..........\n"
                + "..........\n"
                + "..........\n"
                + "....y.....\n"
                + ".....#....\n"
                + "..........\n"
                + "..........\n"
                + "..........\n"
                + "..........\n"));
        NeighbourPruningRules prune = new NeighbourPruningRules(g);
        Jump jump = new Jump(g, prune);
        Node x = g.getNode(0, 0);
        int dx = 1;
        int dy = 1;
        assertEquals("(4, 4)", jump.jump(x, dx, dy).toString());
    }

    @Test
    public void returnsJumpPointSuccessorForExampleA() throws IOException {
        // This is from Figure 1(a) in the JPS paper by Harabor and Grastien
        Graph g = GraphReader.read(new StringReader(""
                + "type octile\n"
                + "height 5\n"
                + "width 7\n"
                + "map\n"
                + ".......\n"
                + ".......\n"
                + "#....#.\n"
                + ".x...y.\n"
                + "#......\n"));
        NeighbourPruningRules prune = new NeighbourPruningRules(g);
        Jump jump = new Jump(g, prune);
        Node x = g.getNode(1, 3);
        int dx = 1;
        int dy = 0;
        assertEquals("(5, 3)", jump.jump(x, dx, dy).toString());
    }

    @Test
    public void returnsJumpPointSuccessorForExampleB() throws IOException {
        // This is from Figure 1(b) in the JPS paper by Harabor and Grastien
        Graph g = GraphReader.read(new StringReader(""
                + "type octile\n"
                + "height 5\n"
                + "width 7\n"
                + "map\n"
                + "...y...\n"
                + ".....#.\n"
                + ".x...#.\n"
                + ".#...#.\n"
                + ".#.....\n"));
        NeighbourPruningRules prune = new NeighbourPruningRules(g);
        Jump jump = new Jump(g, prune);
        Node x = g.getNode(1, 2);
        int dx = 1;
        int dy = -1;
        assertEquals("(3, 0)", jump.jump(x, dx, dy).toString());
    }

}
