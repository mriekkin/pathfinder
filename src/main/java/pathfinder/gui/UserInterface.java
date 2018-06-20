package pathfinder.gui;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;
import pathfinder.gui.actions.*;
import pathfinder.gui.grid.*;
import pathfinder.logic.*;
import pathfinder.logic.pathfinders.*;

/**
 * The main window for this application.
 * <p>
 * The layout of this window consists of a large panel which occupies most of
 * the window (the grid panel) and a row of buttons on top of the panel (the
 * buttons panel). The grid panel displays the grid, which is the main view of
 * the application. The buttons panel contains the main controls used to control
 * the application. The buttons panel is laid out horizontally in a single row.
 * The window also contains a menu bar, which contains commands, for example,
 * for creating a new grid or loading a grid from file.
 * <p>
 * This class is the main class for the user interface. It utilizes many classes
 * and wires them together.
 */
public class UserInterface implements Runnable, PropertyChangeListener {

    private static final String[] ALGORITHMS = new String[]{"BFS", "Dijkstra", "A*"};

    private final CurrentGraph current;
    private Pathfinder pathfinder;

    private JFrame frame;
    private JMenuItem create;
    private JMenuItem open;
    private JMenuItem settings;
    private JButton find;
    private JButton reset;
    private JComboBox algorithm;
    private GridPanel grid;
    private JScrollPane scroll;
    private ToggleObstacleListener mouseListener1;
    private MoveEndpointListener mouseListener2;
    private final PreferencesEditor prefs;

    /**
     * Creates a new <code>UserInterface</code> with the specified graph and
     * preferences editor.
     *
     * @param current the current graph used by this application
     * @param prefs the preferences editor used by this application
     */
    public UserInterface(CurrentGraph current, PreferencesEditor prefs) {
        this.current = current;
        this.pathfinder = new BFS(current.getGraph());
        this.prefs = prefs;
    }

    @Override
    public void run() {
        frame = new JFrame("Pathfinder");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addMenuBar(frame);
        addComponents(frame.getContentPane());

        frame.pack();
        frame.setResizable(true);
        frame.setVisible(true);
    }

    private void addMenuBar(JFrame frame) {
        Action createAction = new ShowNewGridAction(frame, current);
        Action openAction = new OpenFileAction(frame, current);
        Action settingsAction = new ShowPreferencesAction(frame, prefs);

        create = new JMenuItem(createAction);
        open = new JMenuItem(openAction);
        settings = new JMenuItem(settingsAction);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu1 = new JMenu("File");
        JMenu menu2 = new JMenu("Preferences");
        menu1.add(create);
        menu1.add(open);
        menu2.add(settings);
        menuBar.add(menu1);
        menuBar.add(menu2);
        frame.setJMenuBar(menuBar);
    }

    private void addComponents(final Container pane) {
        addButtonRowOnTop(pane);
        addGridPanel(pane);
    }

    private void addButtonRowOnTop(final Container pane) {
        find = new JButton("Find");
        reset = new JButton("Reset");
        algorithm = new JComboBox(ALGORITHMS);

        addActionListeners();

        JPanel top = new JPanel();
        top.setLayout(new FlowLayout());
        top.add(find);
        top.add(reset);
        top.add(algorithm);

        pane.add(top, BorderLayout.PAGE_START);
    }

    private void addActionListeners() {
        find.addActionListener((e) -> {
            pathfinder.run();
            grid.repaint();
            //find.setEnabled(false);
        });

        reset.addActionListener((e) -> {
            pathfinder = createPathfinder(current.getGraph());
            grid.setPathfinder(pathfinder);
            grid.repaint();
            find.setEnabled(true);
        });

        algorithm.addActionListener((e) -> {
            pathfinder = createPathfinder(current.getGraph());
            grid.setPathfinder(pathfinder);
        });
    }

    private void addGridPanel(final Container pane) {
        grid = new GridPanel(current.getGraph(), pathfinder, prefs.getCellSize());
        scroll = new JScrollPane(grid);

        //scroll.setMaximumSize(new Dimension(600, 600));
        addMouseListeners();

        pane.add(scroll, BorderLayout.CENTER);
    }

    private void addMouseListeners() {
        mouseListener1 = new ToggleObstacleListener(grid, current);
        mouseListener2 = new MoveEndpointListener(grid, current);
        grid.addMouseListener(mouseListener1);
        grid.addMouseListener(mouseListener2);
        grid.addMouseMotionListener(mouseListener1);
        grid.addMouseMotionListener(mouseListener2);
    }

    private Pathfinder createPathfinder(Graph graph) {
        switch (algorithm.getSelectedIndex()) {
            case 0: return new BFS(graph);
            case 1: return new Dijkstra(graph);
            case 2: return new AStar(graph);
            default: return null;
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String propertyName = evt.getPropertyName();
        if ("graph".equals(propertyName)) {
            // The current graph has changed
            // This happends when the user either loads a file or creates a new empty graph
            // The GUI will resize to fit the new graph
            // Also, the pathfinder needs to re-created
            pathfinder = createPathfinder(current.getGraph());
            resize();
            return;
        }

        if ("cellSize".equals(propertyName)) {
            // The cell size option has been updated to a new value
            // The view will update to reflect the new cell size
            resize();
        }
    }

    private void resize() {
        // Solution A
        // Dimension size = scroll.getSize();
        // grid.reset(g, pathfinder, prefs.getCellSize());
        // scroll.setPreferredSize(size);

        grid.reset(current.getGraph(), pathfinder, prefs.getCellSize());

        // Solution B
        // resizeScrollPane();
        frame.pack();
        grid.revalidate();
        grid.repaint();
        scroll.revalidate();
        scroll.repaint();
    }

    // Solution B
//    private void resizeScrollPane() {
//        frame.pack();
//        Dimension size = scroll.getSize();
//        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
//        
//        if (size.getWidth() > 0.8 * screen.getWidth() ||
//            size.getHeight() > 0.8 * screen.getHeight()) {
//            int width = (int) Math.min(0.8 * screen.getWidth(), size.getWidth());
//            int height = (int) Math.min(0.8 * screen.getHeight(), size.getHeight());
//            scroll.setPreferredSize(new Dimension(width, height));
//        }
//    }
}
