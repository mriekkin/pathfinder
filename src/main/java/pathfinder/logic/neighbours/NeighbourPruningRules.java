package pathfinder.logic.neighbours;

import java.util.List;
import pathfinder.logic.Node;

/**
 * A common interface for neighbour pruning rules used by Jump point search.
 */
public interface NeighbourPruningRules {

    /**
     * Returns the pruned set of neighbours for the specified node u.
     *
     * @param p the predecessor of u
     * @param u the current node
     * @return pruned set of neighbours for the specified node
     */
    List<Node> getPrunedNeighbours(Node p, Node u);

    /**
     * Returns true if the specified node u has a forced neighbour.
     *
     * @param p the predecessor of u
     * @param u the current node
     * @return true if the specified node has a forced neighbour
     */
    boolean hasForcedNeighbour(Node p, Node u);

    /**
     * Returns true if it's possible to take one step: starting from the
     * specified node u and moving in the specified direction (dx, dy).
     * <p>
     * The main purpose of this method is to provide an easy way to check the
     * validity of diagonal moves.
     *
     * @param u the current node
     * @param dx the change in x-coordinate (should be 0 or 1)
     * @param dy the change in y-coordinate (should be 0 or 1)
     * @return true if it's possible to move from the specified node in the
     * specified direction
     */
    boolean isValidMove(Node u, int dx, int dy);

}
