package pathfinder;

import javax.swing.UIManager;
import pathfinder.benchmark.Benchmark;
import pathfinder.gui.PreferencesEditor;
import pathfinder.gui.UserInterface;
import pathfinder.logic.*;

/**
 * Application entry point. Performs initial setup and launches the application.
 */
public class App {

    /**
     * The default size of a single cell on screen in pixels. A larger cell
     * size, naturally, allows one to see more detail. A smaller cell size
     * allows one to view much larger grids without the need for scrolling.
     * <p>
     * The user can change the value of this option in the preferences dialog.
     */
    public static final int GUI_DEFAULT_CELL_SIZE = 20;

    /**
     * Whether corner-cutting is allowed in the visualization mode. If
     * corner-cutting is allowed, it's possible to take a diagonal shortcut
     * around each corner.
     * <p>
     * The user can change the value of this option in the preferences dialog.
     */
    public static final boolean GUI_DEFAULT_CORNER_CUTTING = false;

    /**
     * Whether corner-cutting is allowed in the benchmark mode. If
     * corner-cutting is allowed, it's possible to take a diagonal shortcut
     * around each corner.
     * <p>
     * Corner-cutting should be disabled to get standardized results. The
     * problem sets from Sturtevant assume that corner-cutting is disabled.
     */
    public static final boolean BENCHMARK_CORNER_CUTTING = false;

    /**
     * The number of times each experiment is replicated in the benchmark mode.
     */
    public static final int BENCHMARK_REPLICATES = 10;

    /**
     * Application entry point. Performs initial setup and launches the
     * application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase("-b")) {
            runBenchmarkingMode(args);
            return;
        }

        runVisualizationMode();
    }

    private static void runBenchmarkingMode(String[] args) {
        if (args.length < 2) {
            System.out.println("No scenario file specified");
            return;
        }

        new Benchmark(args[1], BENCHMARK_REPLICATES, BENCHMARK_CORNER_CUTTING, System.out)
                .run();
    }

    private static void runVisualizationMode() {
        // This needs to be done first
        setSystemLaf();

        Graph graph = createDefaultGrid();
        CurrentGraph current = new CurrentGraph(graph);
        PreferencesEditor prefs = new PreferencesEditor(GUI_DEFAULT_CELL_SIZE, GUI_DEFAULT_CORNER_CUTTING);

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
        // In practice this happends when one of the available options is changed
        // The view will update to reflect the new cell size
        prefs.addPropertyChangeListener("cellSize", gui);
        prefs.addPropertyChangeListener("corner-cutting", gui);
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

        return g;
    }

}
