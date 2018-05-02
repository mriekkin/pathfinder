package pathfinder.gui;

import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;
import pathfinder.logic.Graph;
import pathfinder.logic.Node;
import pathfinder.logic.Pathfinder;

public class MoveEndpointListener extends MouseInputAdapter {

    private GridPanel panel;
    private Graph g;
    private Pathfinder pathfinder;
    private boolean isActive;
    private boolean isStart;

    public MoveEndpointListener(GridPanel panel, Graph g, Pathfinder pathfinder) {
        this.panel = panel;
        this.g = g;
        this.pathfinder = pathfinder;
        this.isActive = false;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Node node = panel.getNode(e.getX(), e.getY());
        if (node == null) return;
        
        if (node.equals(pathfinder.getStart())) {
            isStart = true;
            isActive = true;
            return;
        }
        
        if (node.equals(pathfinder.getEnd())) {
            isStart = false;
            isActive = true;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (!isActive) return;

        Node toNode = panel.getNode(e.getX(), e.getY());
        if (toNode == null) return;
        if (toNode.equals(pathfinder.getStart())) return;
        if (toNode.equals(pathfinder.getEnd())) return;
        if (!toNode.isWalkable()) return;

        if (isStart)
            pathfinder.setStart(toNode);
        else
            pathfinder.setEnd(toNode);

        panel.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        isActive = false;
    }

}
