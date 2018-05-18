package pathfinder.gui;

import java.awt.*;
import javax.swing.*;
import pathfinder.gui.grid.*;
import pathfinder.gui.preferences.*;
import pathfinder.logic.*;
import pathfinder.logic.pathfinders.*;

public class UserInterface implements Runnable {

    private static final String[] ALGORITHMS = new String[]{"BFS", "Dijkstra", "A*"};
    private static final int DEFAULT_CELL_SIZE = 20;

    private Graph g;
    private Pathfinder pathfinder;

    private ShowNewGridAction createAction;

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
    private PreferencesView prefs;

    public UserInterface(Graph g) {
        this.g = g;
        this.pathfinder = new BFS(g);
        this.prefs = new PreferencesView(this, DEFAULT_CELL_SIZE);
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
        createAction = new ShowNewGridAction(this, frame, g.getCols(), g.getRows());
        Action openAction = new OpenFileAction(this, frame);
        Action settingsAction = new ShowPreferencesAction(this, frame, prefs);

        create = new JMenuItem(createAction);
        open = new JMenuItem(openAction);
        settings = new JMenuItem(settingsAction);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu1 = new JMenu("File");
        JMenu menu2 = new JMenu("Settings");
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
            pathfinder.find();
            grid.repaint();
            //find.setEnabled(false);
        });

        reset.addActionListener((e) -> {
            pathfinder = createPathfinder(g);
            grid.setPathfinder(pathfinder);
            grid.repaint();
            find.setEnabled(true);
        });

        algorithm.addActionListener((e) -> {
            pathfinder = createPathfinder(g);
            grid.setPathfinder(pathfinder);
        });
    }

    private void addGridPanel(final Container pane) {
        grid = new GridPanel(g, pathfinder, prefs.getCellSize());
        scroll = new JScrollPane(grid);

        //scroll.setMaximumSize(new Dimension(600, 600));

        addMouseListeners();

        pane.add(scroll, BorderLayout.CENTER);
    }

    private void addMouseListeners() {
        mouseListener1 = new ToggleObstacleListener(grid, g);
        mouseListener2 = new MoveEndpointListener(grid, g);
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

    public void setGraph(Graph graph) {
        g = graph;
        pathfinder = createPathfinder(g);

        resize();
    }

    public void resize() {
        createAction.setInitialValues(g.getCols(), g.getRows());

        // Solution A
        // Dimension size = scroll.getSize();
        // grid.reset(g, pathfinder, prefs.getCellSize());
        // scroll.setPreferredSize(size);

        grid.reset(g, pathfinder, prefs.getCellSize());

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
