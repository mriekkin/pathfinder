package pathfinder.logic;

import org.junit.Test;
import static org.junit.Assert.*;
import pathfinder.io.GraphWriter;

/**
 * Tests setting up a graph using a few examples
 */
public class GraphSetupTest {

    @Test
    public void setUpTinyGrid() {
        Pair dimensions = new Pair(10, 10);
        Pair source = new Pair(0, 9);
        Pair dest = new Pair(9, 9);
        Graph g = new Graph(dimensions, source, dest);

        for (int y = 1; y < 10; y++) {
            g.getNode(5, y).setWalkable(false);
        }

        assertEquals(""
                + "..........\n"
                + ".....#....\n"
                + ".....#....\n"
                + ".....#....\n"
                + ".....#....\n"
                + ".....#....\n"
                + ".....#....\n"
                + ".....#....\n"
                + ".....#....\n"
                + "A....#...B",
                GraphWriter.plotGrid(g));
    }

    @Test
    public void setUpSmallGrid() {
        Pair dimensions = new Pair(30, 15);
        Pair source = new Pair(8, 7);
        Pair dest = new Pair(17, 2);
        Graph g = new Graph(dimensions, source, dest);

        for (int y = 3; y <= 11; y++) {
            g.getNode(3, y).setWalkable(false);
            g.getNode(4, y).setWalkable(false);
        }

        for (int y = 4; y <= 14; y++) {
            g.getNode(13, y).setWalkable(false);
            g.getNode(14, y).setWalkable(false);
        }

        for (int y = 0; y <= 6; y++) {
            g.getNode(21, y).setWalkable(false);
            g.getNode(22, y).setWalkable(false);
        }

        for (int y = 5; y <= 6; y++) {
            g.getNode(23, y).setWalkable(false);
            g.getNode(24, y).setWalkable(false);
            g.getNode(25, y).setWalkable(false);
        }

        assertEquals(""
                + ".....................##.......\n"
                + ".....................##.......\n"
                + ".................B...##.......\n"
                + "...##................##.......\n"
                + "...##........##......##.......\n"
                + "...##........##......#####....\n"
                + "...##........##......#####....\n"
                + "...##...A....##...............\n"
                + "...##........##...............\n"
                + "...##........##...............\n"
                + "...##........##...............\n"
                + "...##........##...............\n"
                + ".............##...............\n"
                + ".............##...............\n"
                + ".............##...............",
                GraphWriter.plotGrid(g));
    }

}
