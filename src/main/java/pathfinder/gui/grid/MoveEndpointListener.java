package pathfinder.gui.grid;

import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;
import pathfinder.logic.CurrentGraph;
import pathfinder.logic.Node;

/**
 * A mouse listener for handling dragging of the source and destination nodes to
 * a different location. This mouse listener is activated by pressing on the
 * source or destination node. The source or destination node can then be
 * dragged to the desired location.
 */
public class MoveEndpointListener extends MouseInputAdapter {

    private final GridPanel panel;
    private final CurrentGraph g;
    private boolean isActive;
    private boolean isSource;

    /**
     * Constructs a <code>MoveEndpointListener</code> with the specified grid
     * panel and current graph.
     *
     * @param panel the grid panel used by this application
     * @param g the current graph used by this application
     */
    public MoveEndpointListener(GridPanel panel, CurrentGraph g) {
        this.panel = panel;
        this.g = g;
        this.isActive = false;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Node node = panel.getNode(e.getX(), e.getY());
        if (node == null) return;

        if (node.equals(g.getSource())) {
            isSource = true;
            isActive = true;
            return;
        }

        if (node.equals(g.getDest())) {
            isSource = false;
            isActive = true;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (!isActive) return;

        Node toNode = panel.getNode(e.getX(), e.getY());
        if (toNode == null) return;
        if (toNode.equals(g.getSource())) return;
        if (toNode.equals(g.getDest())) return;
        if (!toNode.isWalkable()) return;

        if (isSource)
            g.setSource(toNode);
        else
            g.setDest(toNode);

        panel.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        isActive = false;
    }

}
