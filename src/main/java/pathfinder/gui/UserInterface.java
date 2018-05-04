package pathfinder.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import pathfinder.logic.Graph;
import pathfinder.logic.pathfinders.AStar;
import pathfinder.logic.pathfinders.BFS;
import pathfinder.logic.pathfinders.Dijkstra;
import pathfinder.logic.pathfinders.Pathfinder;

public class UserInterface implements Runnable {

    private static final String[] ALGORITHMS = new String[]{"BFS", "Dijkstra", "A*"};

    private Graph g;
    private Pathfinder pathfinder;

    private JButton find;
    private JButton reset;
    private JComboBox algorithm;
    private GridPanel grid;
    private ToggleObstacleListener mouseListener1;
    private MoveEndpointListener mouseListener2;

    public UserInterface(Graph g) {
        this.g = g;
        this.pathfinder = new BFS(g);
    }

    @Override
    public void run() {
        JFrame frame = new JFrame("Pathfinder");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addComponents(frame.getContentPane());

        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }

    private void addComponents(final Container pane) {
        find = new JButton("Find");
        reset = new JButton("Reset");
        algorithm = new JComboBox(ALGORITHMS);
        grid = new GridPanel(g, pathfinder);

        addActionListeners();
        addMouseListeners();

        JPanel top = new JPanel();
        top.setLayout(new FlowLayout());
        top.add(find);
        top.add(reset);
        top.add(algorithm);

        pane.add(top, BorderLayout.PAGE_START);
        pane.add(grid, BorderLayout.CENTER);
    }

    private void addActionListeners() {
        find.addActionListener((e) -> {
            pathfinder.find();
            grid.repaint();
            //find.setEnabled(false);
        });

        reset.addActionListener((e) -> {
            pathfinder = createPathfinder();
            grid.setPathfinder(pathfinder);
            grid.repaint();
            find.setEnabled(true);
        });

        algorithm.addActionListener((e) -> {
            pathfinder = createPathfinder();
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

    private Pathfinder createPathfinder() {
        switch (algorithm.getSelectedIndex()) {
            case 0: return new BFS(g);
            case 1: return new Dijkstra(g);
            case 2: return new AStar(g);
            default: return null;
        }
    }

}
