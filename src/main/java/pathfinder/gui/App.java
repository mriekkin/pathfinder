package pathfinder.gui;

import javax.swing.UIManager;
import pathfinder.logic.*;

/**
 * Application entry point. Performs initial setup and launches the application.
 */
public class App {

    private static final int DEFAULT_CELL_SIZE = 20;

    /**
     * Application entry point. Performs initial setup and launches the
     * application.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        // This needs to be done first
        setSystemLaf();

        Graph graph = createDefaultGrid();
        CurrentGraph current = new CurrentGraph(graph);
        PreferencesEditor prefs = new PreferencesEditor(DEFAULT_CELL_SIZE);

        UserInterface gui = new UserInterface(current, prefs);

        addPropertyChangeListeners(current, prefs, gui);

        javax.swing.SwingUtilities.invokeLater(gui);
    }

    private static void addPropertyChangeListeners(CurrentGraph current, PreferencesEditor prefs, UserInterface gui) {
        // The GUI needs to be notified when the current graph changes
        // This happends when the user either loads a file or creates a new empty graph
        // The GUI will resize to fit the new graph
        current.addPropertyChangeListener("graph", gui);

        // The GUI needs to be notified when application preferences are updated
        // In practice this happends when the cell size option is changed
        // The view will update to reflect the new cell size
        prefs.addPropertyChangeListener("cellSize", gui);
    }

    private static void setSystemLaf() {
        try {
            // Use the system menu bar on Mac OS
            System.setProperty("apple.laf.useScreenMenuBar", "true");

            // Set System L&F
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    private static Graph createDefaultGrid() {
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

        return g;
    }

}
