package pathfinder.logic.pathfinders;

import java.util.ArrayList;
import java.util.List;
import pathfinder.logic.Graph;
import pathfinder.logic.Node;
import pathfinder.logic.Neighbours;

/**
 * Implements neighbour pruning rules for Jump point search.
 * <p>
 * Pruning eliminates nodes which do not have to be evaluated in order to reach
 * the destination node optimally. This class implements neighbour pruning rules
 * as described in the original JPS paper by Harabor and Grastien.
 *
 * @see
 * <a href="https://www.aaai.org/ocs/index.php/AAAI/AAAI11/paper/download/3761/4007">Harabor,
 * D. and Grastien, A. (2011). "Online Graph Pruning for Pathfinding on Grid
 * Maps", 25th National Conference on Artificial Intelligence, AAAI.</a>
 */
public class NeighbourPruningRules {

    private final Graph g;
    private final Neighbours n;

    public NeighbourPruningRules(Graph g) {
        this.g = g;

        // This returns the unpruned neighbours of a node
        // For this we allow corner cutting
        this.n = new Neighbours(g, true);
    }

    /**
     * Returns the pruned set of neighbours for the specified node u.
     *
     * @param p the predecessor of u
     * @param u the current node
     * @return pruned set of neighbours for the specified node
     */
    public List<Node> getPrunedNeighbours(Node p, Node u) {
        ArrayList<Node> neighbours = new ArrayList<>();
        if (p == null) {
            // No pruning for the source node
            return n.getNeighbours(u);
        }

        int x = u.x();
        int y = u.y();
        int dx = clamp(u.x() - p.x(), -1, 1);
        int dy = clamp(u.y() - p.y(), -1, 1);

        if (dy == 0) {
            // A horizontal move

            // Natural neighbours
            // Add if the node itself is walkable
            addIfWalkable(x + dx, y, neighbours);

            // Forced neighbours
            // Add if there's an obstacle on the path and the node itself is walkable
            if (!isWalkable(x, y - 1)) {
                addIfWalkable(x + dx, y - 1, neighbours);
            }

            if (!isWalkable(x, y + 1)) {
                addIfWalkable(x + dx, y + 1, neighbours);
            }
        } else if (dx == 0) {
            // A vertical move

            // Natural neighbours
            // Add if the node itself is walkable
            addIfWalkable(x, y + dy, neighbours);

            // Forced neighbours
            // Add if there's an obstacle on the path and the node itself is walkable
            if (!isWalkable(x - 1, y)) {
                addIfWalkable(x - 1, y + dy, neighbours);
            }

            if (!isWalkable(x + 1, y)) {
                addIfWalkable(x + 1, y + dy, neighbours);
            }
        } else {
            // A diagonal move

            // Natural neighbours
            // Add if the node itself is walkable
            addIfWalkable(x, y + dy, neighbours);
            addIfWalkable(x + dx, y + dy, neighbours);
            addIfWalkable(x + dx, y, neighbours);

            // Forced neighbours
            // Add if there's an obstacle on the path and the node itself is walkable
            if (!isWalkable(x - dx, y)) {
                addIfWalkable(x - dx, y + dy, neighbours);
            }

            if (!isWalkable(x, y - dy)) {
                addIfWalkable(x + dx, y - dy, neighbours);
            }
        }

        return neighbours;
    }

    /**
     * Returns true if the specified node u has a forced neighbour.
     *
     * @param p the predecessor of u
     * @param u the current node
     * @return true if the specified node has a forced neighbour
     */
    public boolean hasForcedNeighbour(Node p, Node u) {
        int x = u.x();
        int y = u.y();
        int dx = clamp(u.x() - p.x(), -1, 1);
        int dy = clamp(u.y() - p.y(), -1, 1);

        if (dy == 0) {
            // A horizontal move

            if (!isWalkable(x, y - 1) && isWalkable(x + dx, y - 1)) {
                return true;
            }

            if (!isWalkable(x, y + 1) && isWalkable(x + dx, y + 1)) {
                return true;
            }
        } else if (dx == 0) {
            // A vertical move

            if (!isWalkable(x - 1, y) && isWalkable(x - 1, y + dy)) {
                return true;
            }

            if (!isWalkable(x + 1, y) && isWalkable(x + 1, y + dy)) {
                return true;
            }
        } else {
            // A diagonal move

            if (!isWalkable(x - dx, y) && isWalkable(x - dx, y + dy)) {
                return true;
            }

            if (!isWalkable(x, y - dy) && isWalkable(x + dx, y - dy)) {
                return true;
            }
        }

        return false;
    }

    private int clamp(int val, int min, int max) {
        return Math.max(min, Math.min(max, val));
    }

    private boolean isWalkable(int x, int y) {
        Node u = g.getNode(x, y);
        if (u == null) {
            return false; // Out of range
        }

        return u.isWalkable();
    }

    private void addIfWalkable(int x, int y, List<Node> list) {
        if (!isWalkable(x, y)) {
            return;
        }

        list.add(g.getNode(x, y));
    }

}
