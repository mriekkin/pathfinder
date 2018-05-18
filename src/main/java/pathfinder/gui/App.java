package pathfinder.gui;

import javax.swing.UIManager;
import pathfinder.logic.*;

public class App {

    public static void main(String[] args) {
        try {
            // Use the system menu bar on Mac OS
            System.setProperty("apple.laf.useScreenMenuBar", "true");

            // Set System L&F
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        Graph g = createDefaultGrid();

        UserInterface gui = new UserInterface(g);

        javax.swing.SwingUtilities.invokeLater(gui);
    }

    private static Graph createDefaultGrid() {
        Pair dimensions = new Pair(30, 15);
        Pair start = new Pair(8, 7);
        Pair end = new Pair(17, 12);
        Graph g = new Graph(dimensions, start, end);

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

        return g;
    }

}
