package pathfinder.logic;

import org.junit.Test;
import static org.junit.Assert.*;
import pathfinder.console.GraphWriter;

/**
 * Tests graph setup using a few examples of small and large graphs
 * 
 */
public class GraphSetupTest {

    @Test
    public void testSetupSmallGrid() {
        Graph g = new Graph(10, 10);
        Node start = g.getNode(0, 0);
        Node end = g.getNode(9, 0);

        for (int y = 0; y < 9; y++) {
            g.getNode(5, y).setWalkable(false);
        }

        assertEquals(""
                + ". . . . . . . . . . \n"
                + ". . . . . ##. . . . \n"
                + ". . . . . ##. . . . \n"
                + ". . . . . ##. . . . \n"
                + ". . . . . ##. . . . \n"
                + ". . . . . ##. . . . \n"
                + ". . . . . ##. . . . \n"
                + ". . . . . ##. . . . \n"
                + ". . . . . ##. . . . \n"
                + "S . . . . ##. . . E ",
                GraphWriter.plotGrid(g, start, end));
    }

    @Test
    public void testSetupBigGrid() {
        Graph g = new Graph(30, 15);
        Node start = g.getNode(8, 7);
        Node end = g.getNode(17, 12);

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
                + ". . . . . . . . . . . . . . . . . . . . . ####. . . . . . . \n"
                + ". . . . . . . . . . . . . . . . . . . . . ####. . . . . . . \n"
                + ". . . . . . . . . . . . . . . . . E . . . ####. . . . . . . \n"
                + ". . . ####. . . . . . . . . . . . . . . . ####. . . . . . . \n"
                + ". . . ####. . . . . . . . ####. . . . . . ####. . . . . . . \n"
                + ". . . ####. . . . . . . . ####. . . . . . ##########. . . . \n"
                + ". . . ####. . . . . . . . ####. . . . . . ##########. . . . \n"
                + ". . . ####. . . S . . . . ####. . . . . . . . . . . . . . . \n"
                + ". . . ####. . . . . . . . ####. . . . . . . . . . . . . . . \n"
                + ". . . ####. . . . . . . . ####. . . . . . . . . . . . . . . \n"
                + ". . . ####. . . . . . . . ####. . . . . . . . . . . . . . . \n"
                + ". . . ####. . . . . . . . ####. . . . . . . . . . . . . . . \n"
                + ". . . . . . . . . . . . . ####. . . . . . . . . . . . . . . \n"
                + ". . . . . . . . . . . . . ####. . . . . . . . . . . . . . . \n"
                + ". . . . . . . . . . . . . ####. . . . . . . . . . . . . . . ",
                GraphWriter.plotGrid(g, start, end));
    }

}
