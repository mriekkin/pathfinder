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
        frame.setVisible(true);
    }

    private void addComponents(final Container pane) {
        GridPanel grid = new GridPanel(g, pathfinder);

        JButton find = new JButton("Find");
        JButton reset = new JButton("Reset");

        find.addActionListener((e) -> {
            pathfinder.find();
            grid.repaint();
        });

        reset.addActionListener((e) -> {
            pathfinder = new Dijkstra(g, g.getNode(8, 7), g.getNode(17, 12));
            grid.setPathfinder(pathfinder);
            grid.repaint();
        });

        JPanel top = new JPanel();
        top.setLayout(new FlowLayout());
        top.add(find);
        top.add(reset);

        pane.add(top, BorderLayout.PAGE_START);
        pane.add(grid, BorderLayout.CENTER);
    }

}
