package pathfinder.logic.neighbours;

import java.util.ArrayList;
import java.util.List;
import pathfinder.logic.Graph;
import pathfinder.logic.Node;

/**
 * Implements neighbour pruning rules for Jump point search (without
 * corner-cutting).
 * <p>
 * Pruning eliminates nodes which do not have to be evaluated in order to reach
 * the destination node optimally. This class implements neighbour pruning rules
 * as described in the <b>second</b> JPS paper by Harabor and Grastien.
 *
 * @see
 * <a href="https://www.aaai.org/ocs/index.php/SOCS/SOCS12/paper/download/5396/5212">Harabor,
 * D. and Grastien, A. (2012). "The JPS Pathfinding System", 26th National
 * Conference on Artificial Intelligence, AAAI.</a>
 */
public class NeighbourPruningRulesCcDisallowed implements NeighbourPruningRules {

    private final Graph g;
    private final Neighbours n;

    /**
     * Constructs this object with the specified graph.
     *
     * @param g the graph to be used by this object
     */
    public NeighbourPruningRulesCcDisallowed(Graph g) {
        this.g = g;

        // This returns the unpruned neighbours of a node
        // For this we disallow corner cutting
        this.n = new Neighbours(g, false);
    }

    @Override
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
            if (!isWalkable(x - dx, y - 1)) {
                addIfWalkable(x, y - 1, neighbours);

                if (isWalkable(x + dx, y) && isWalkable(x, y - 1)) {
                    addIfWalkable(x + dx, y - 1, neighbours);
                }
            }

            if (!isWalkable(x - dx, y + 1)) {
                addIfWalkable(x, y + 1, neighbours);

                if (isWalkable(x + dx, y) && isWalkable(x, y + 1)) {
                    addIfWalkable(x + dx, y + 1, neighbours);
                }
            }
        } else if (dx == 0) {
            // A vertical move

            // Natural neighbours
            // Add if the node itself is walkable
            addIfWalkable(x, y + dy, neighbours);

            // Forced neighbours
            if (!isWalkable(x - 1, y - dy)) {
                addIfWalkable(x - 1, y, neighbours);

                if (isWalkable(x - 1, y) && isWalkable(x, y + dy)) {
                    addIfWalkable(x - 1, y + dy, neighbours);
                }
            }

            if (!isWalkable(x + 1, y - dy)) {
                addIfWalkable(x + 1, y, neighbours);

                if (isWalkable(x + 1, y) && isWalkable(x, y + dy)) {
                    addIfWalkable(x + 1, y + dy, neighbours);
                }
            }
        } else {
            // A diagonal move

            // Natural neighbours
            // Add if the node itself is walkable
            // For diagonal movement we need to check also for corner-cutting
            addIfWalkable(x, y + dy, neighbours);

            if (isWalkable(x + dx, y) && isWalkable(x, y + dy)) {
                addIfWalkable(x + dx, y + dy, neighbours);
            }

            addIfWalkable(x + dx, y, neighbours);
        }

        return neighbours;
    }

    @Override
    public boolean hasForcedNeighbour(Node p, Node u) {
        int x = u.x();
        int y = u.y();
        int dx = clamp(u.x() - p.x(), -1, 1);
        int dy = clamp(u.y() - p.y(), -1, 1);

        if (dy == 0) {
            // A horizontal move

            if (!isWalkable(x - dx, y - 1) && isWalkable(x, y - 1)) {
                return true;
            }

            if (!isWalkable(x - dx, y + 1) && isWalkable(x, y + 1)) {
                return true;
            }
        } else if (dx == 0) {
            // A vertical move

            if (!isWalkable(x - 1, y - dy) && isWalkable(x - 1, y)) {
                return true;
            }

            if (!isWalkable(x + 1, y - dy) && isWalkable(x + 1, y)) {
                return true;
            }
        } else {
            // A diagonal move

            // A diagonal move cannot create forced neighbours
            // when corner-cutting is not allowed
        }

        return false;
    }

    @Override
    public boolean isValidMove(Node u, int dx, int dy) {
        int x = u.x();
        int y = u.y();
        dx = clamp(dx, -1, 1);
        dy = clamp(dy, -1, 1);
        return isWalkable(x + dx, y) && isWalkable(x, y + dy);
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
