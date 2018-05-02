package pathfinder.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import pathfinder.logic.Graph;
import pathfinder.logic.Dijkstra;
import pathfinder.logic.Pathfinder;

public class UserInterface implements Runnable {

    private Graph g;
    private Pathfinder pathfinder;

    private JButton find;
    private JButton reset;
    private GridPanel grid;
    private ToggleObstacleListener mouseListener1;
    private MoveEndpointListener mouseListener2;

    public UserInterface(Graph g, Pathfinder pathfinder) {
        this.g = g;
        this.pathfinder = pathfinder;
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
        grid = new GridPanel(g, pathfinder);

        reset.setEnabled(false); // This is temporary

        addActionListeners();
        addMouseListeners();

        JPanel top = new JPanel();
        top.setLayout(new FlowLayout());
        top.add(find);
        top.add(reset);

        pane.add(top, BorderLayout.PAGE_START);
        pane.add(grid, BorderLayout.CENTER);
    }

    private void addActionListeners() {
        find.addActionListener((e) -> {
            pathfinder.find();
            grid.repaint();
            find.setEnabled(false);
        });

        reset.addActionListener((e) -> {
            pathfinder = new Dijkstra(g, g.getNode(8, 7), g.getNode(17, 12));
            grid.setPathfinder(pathfinder);
            grid.repaint();
            find.setEnabled(true);
        });
    }

    private void addMouseListeners() {
        mouseListener1 = new ToggleObstacleListener(grid, g, pathfinder);
        mouseListener2 = new MoveEndpointListener(grid, g, pathfinder);
        grid.addMouseListener(mouseListener1);
        grid.addMouseListener(mouseListener2);
        grid.addMouseMotionListener(mouseListener1);
        grid.addMouseMotionListener(mouseListener2);
    }

}
