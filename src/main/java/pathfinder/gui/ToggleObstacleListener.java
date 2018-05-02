package pathfinder.gui;

import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;
import pathfinder.logic.Graph;
import pathfinder.logic.Node;
import pathfinder.logic.Pathfinder;

class ToggleObstacleListener extends MouseInputAdapter {

    private GridPanel panel;
    private Graph g;
    private Pathfinder pathfinder;
    private boolean isActive;
    private boolean walkable;

    public ToggleObstacleListener(GridPanel panel, Graph g, Pathfinder pathfinder) {
        this.panel = panel;
        this.g = g;
        this.pathfinder = pathfinder;
        this.isActive = false;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Node node = panel.getNode(e.getX(), e.getY());

        if (node == null) return;
        if (node.equals(pathfinder.getStart())) return;
        if (node.equals(pathfinder.getEnd())) return;

        isActive = true;
        walkable = !node.isWalkable();

        node.setWalkable(walkable);
        panel.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (!isActive) return;

        Node node = panel.getNode(e.getX(), e.getY());

        if (node == null) return;
        if (node.equals(pathfinder.getStart())) return;
        if (node.equals(pathfinder.getEnd())) return;
        if (node.isWalkable() == walkable) return;

        node.setWalkable(walkable);
        panel.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        isActive = false;
    }

}
