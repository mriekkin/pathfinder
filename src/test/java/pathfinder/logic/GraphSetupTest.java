package pathfinder.logic;

import org.junit.Test;
import static org.junit.Assert.*;
import pathfinder.io.GraphWriter;

/**
 * Tests graph setup using a few examples
 */
public class GraphSetupTest {

    @Test
    public void testSetupSmallGrid() {
        Pair dimensions = new Pair(10, 10);
        Pair source = new Pair(0, 0);
        Pair dest = new Pair(9, 0);
        Graph g = new Graph(dimensions, source, dest);

        for (int y = 0; y < 9; y++) {
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
                + "S....#...E",
                GraphWriter.plotGrid(g));
    }

    @Test
    public void testSetupBigGrid() {
        Pair dimensions = new Pair(30, 15);
        Pair source = new Pair(8, 7);
        Pair dest = new Pair(17, 12);
        Graph g = new Graph(dimensions, source, dest);

        for (int y = 3; y <= 11; y++) {
            g.getNode(3, y).setWalkable(false);
            g.getNode(4, y).setWalkable(false);
        }

        for (int y = 0; y <= 10; y++) {
            g.getNode(13, y).setWalkable(false);
            g.getNode(14, y).setWalkable(false);
        }

        for (int y = 8; y < 15; y++) {
            g.getNode(21, y).setWalkable(false);
            g.getNode(22, y).setWalkable(false);
        }

        for (int y = 8; y <= 9; y++) {
            g.getNode(23, y).setWalkable(false);
            g.getNode(24, y).setWalkable(false);
            g.getNode(25, y).setWalkable(false);
        }

        assertEquals(""
                + ".....................##.......\n"
                + ".....................##.......\n"
                + ".................E...##.......\n"
                + "...##................##.......\n"
                + "...##........##......##.......\n"
                + "...##........##......#####....\n"
                + "...##........##......#####....\n"
                + "...##...S....##...............\n"
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
