package pathfinder.gui.grid;

import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;
import pathfinder.logic.CurrentGraph;
import pathfinder.logic.Node;

/**
 * A mouse listener for handling toggling a node between a walkable node and an
 * obstacle. This mouse listener is activated by pressing on any node other than
 * the source or destination. When the mouse is pressed, the node switches from
 * a walkable node to an obstacle node, or vice versa. Dragging the mouse,
 * similarly, toggles multiple nodes in a single motion.
 * <p>
 * When the mouse is dragged, the node which was pressed first determines
 * whether nodes are converted into walkable nodes or obstacles. In other words,
 * either walkable nodes are converted into obstacles, or obstacles are
 * converted into walkable nodes, but never both during a single motion.
 */
public class ToggleObstacleListener extends MouseInputAdapter {

    private final GridPanel panel;
    private final CurrentGraph g;
    private boolean isActive;
    private boolean walkable;

    /**
     * Constructs a <code>ToggleObstacleListener</code> with the specified
     * grid panel and current graph.
     * 
     * @param panel the grid panel used by this application
     * @param g the current graph used by this application
     */
    public ToggleObstacleListener(GridPanel panel, CurrentGraph g) {
        this.panel = panel;
        this.g = g;
        this.isActive = false;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Node node = panel.getNode(e.getX(), e.getY());

        if (node == null) return;
        if (node.equals(g.getSource())) return;
        if (node.equals(g.getDest())) return;

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
        if (node.equals(g.getSource())) return;
        if (node.equals(g.getDest())) return;
        if (node.isWalkable() == walkable) return;

        node.setWalkable(walkable);
        panel.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        isActive = false;
    }

}
