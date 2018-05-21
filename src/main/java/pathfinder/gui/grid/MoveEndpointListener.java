package pathfinder.gui.grid;

import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;
import pathfinder.logic.CurrentGraph;
import pathfinder.logic.Node;

public class MoveEndpointListener extends MouseInputAdapter {

    private GridPanel panel;
    private CurrentGraph g;
    private boolean isActive;
    private boolean isStart;

    public MoveEndpointListener(GridPanel panel, CurrentGraph g) {
        this.panel = panel;
        this.g = g;
        this.isActive = false;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Node node = panel.getNode(e.getX(), e.getY());
        if (node == null) return;
        
        if (node.equals(g.getStart())) {
            isStart = true;
            isActive = true;
            return;
        }
        
        if (node.equals(g.getEnd())) {
            isStart = false;
            isActive = true;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (!isActive) return;

        Node toNode = panel.getNode(e.getX(), e.getY());
        if (toNode == null) return;
        if (toNode.equals(g.getStart())) return;
        if (toNode.equals(g.getEnd())) return;
        if (!toNode.isWalkable()) return;

        if (isStart)
            g.setStart(toNode);
        else
            g.setEnd(toNode);

        panel.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        isActive = false;
    }

}
