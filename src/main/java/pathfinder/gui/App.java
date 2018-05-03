package pathfinder.gui;

import pathfinder.logic.*;

public class App {

    public static void main(String[] args) {
        Pair dimensions = new Pair(30, 15);
        Pair start = new Pair(8, 7);
        Pair end = new Pair(17, 12);
        Graph g = new Graph(dimensions, start, end);
        Pathfinder pathfinder = new Dijkstra(g);

        createBigGrid(g);

        UserInterface gui = new UserInterface(g, pathfinder);

        javax.swing.SwingUtilities.invokeLater(gui);
    }

    private static void createBigGrid(Graph g) {
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
    }

}
