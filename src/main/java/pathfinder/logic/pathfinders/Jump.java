package pathfinder.logic.pathfinders;

import pathfinder.logic.neighbours.NeighbourPruningRules;
import pathfinder.logic.Graph;
import pathfinder.logic.Node;

/**
 * Implements the identification of indidual jump points for Jump point search.
 * <p>
 * Jump points can be described as turning points, where the studied path
 * changes direction. Their identification is described in the original JPS
 * paper by Harabor and Grastien.
 *
 * @see
 * <a href="https://www.aaai.org/ocs/index.php/AAAI/AAAI11/paper/download/3761/4007">Harabor,
 * D. and Grastien, A. (2011). "Online Graph Pruning for Pathfinding on Grid
 * Maps", 25th National Conference on Artificial Intelligence, AAAI.</a>
 */
public class Jump {

    private final Graph g;
    private final NeighbourPruningRules prune;

    public Jump(Graph g, NeighbourPruningRules prune) {
        this.g = g;
        this.prune = prune;
    }

    /**
     * Returns the jump point successor of the specified node x in the specified
     * direction (dx, dy). Returns null, if x has no jump point successor in the
     * direction (dx, dy).
     *
     * @param x the initial node
     * @param dx the x-coordinate of the direction of travel (dx, dy)
     * @param dy the y-coordinate of the direction of travel (dx, dy)
     * @return the jump point successor of the specified node in the specified
     * direction, if one exists
     */
    public Node jump(Node x, int dx, int dy) {
        Node n = step(x, dx, dy);
        if (n == null || !n.isWalkable()) {
            return null;
        }

        if (n.equals(g.getDest())) {
            return n;
        }

        if (prune.hasForcedNeighbour(x, n)) {
            return n;
        }

        if (dx != 0 && dy != 0) {
            if (jump(n, dx, 0) != null) {
                return n;
            }

            if (jump(n, 0, dy) != null) {
                return n;
            }
        }

        return jump(n, dx, dy);
    }

    private Node step(Node x, int dx, int dy) {
        if (!prune.isValidMove(x, dx, dy)) {
            return null;
        }

        return g.getNode(x.x() + dx, x.y() + dy);
    }

}
