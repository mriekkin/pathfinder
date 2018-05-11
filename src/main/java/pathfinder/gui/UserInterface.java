package pathfinder.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import pathfinder.logic.Graph;
import pathfinder.logic.pathfinders.AStar;
import pathfinder.logic.pathfinders.BFS;
import pathfinder.logic.pathfinders.Dijkstra;
import pathfinder.logic.pathfinders.Pathfinder;

public class UserInterface implements Runnable {

    private static final String[] ALGORITHMS = new String[]{"BFS", "Dijkstra", "A*"};
    private static final int DEFAULT_SQUARE_SIZE = 20;

    private Graph g;
    private Pathfinder pathfinder;

    private JFrame frame;
    private JButton open;
    private JButton find;
    private JButton reset;
    private JComboBox algorithm;
    private GridPanel grid;
    private JScrollPane scroll;
    private ToggleObstacleListener mouseListener1;
    private MoveEndpointListener mouseListener2;

    public UserInterface(Graph g) {
        this.g = g;
        this.pathfinder = new BFS(g);
    }

    @Override
    public void run() {
        frame = new JFrame("Pathfinder");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addComponents(frame.getContentPane());

        frame.pack();
        frame.setResizable(true);
        frame.setVisible(true);
    }

    private void addComponents(final Container pane) {
        open = new JButton("Open");
        find = new JButton("Find");
        reset = new JButton("Reset");
        algorithm = new JComboBox(ALGORITHMS);
        grid = new GridPanel(g, pathfinder, DEFAULT_SQUARE_SIZE);
        scroll = new JScrollPane(grid);

        addActionListeners();
        addMouseListeners();

        JPanel top = new JPanel();
        top.setLayout(new FlowLayout());
        top.add(open);
        top.add(Box.createHorizontalStrut(10));
        top.add(find);
        top.add(reset);
        top.add(algorithm);

        pane.add(top, BorderLayout.PAGE_START);
        pane.add(scroll, BorderLayout.CENTER);
    }

    private void addActionListeners() {
        open.addActionListener(new OpenFileListener(this, frame));

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

        grid.reset(g, pathfinder, DEFAULT_SQUARE_SIZE);
        grid.repaint();
        frame.pack();
    }

}
