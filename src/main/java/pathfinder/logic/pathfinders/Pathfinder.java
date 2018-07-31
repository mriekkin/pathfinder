package pathfinder.logic.pathfinders;

import java.util.List;
import pathfinder.logic.Graph;
import pathfinder.logic.Node;

/**
 * A common interface for shortest path algorithms. Defines those operations
 * which are common to all shortest path algorithms. Which particular algorithm
 * is used depends on the implementing class. Each implementing class represents
 * one algorithm for finding a shortest path between two nodes.
 * <p>
 * The proper way to use this interface is to call <code>run</code> first. The
 * details of the algorithm's execution can then be accessed using the member
 * methods.
 */
public interface Pathfinder {

    /**
     * Represents infinity. Equals <code>Integer.MAX_VALUE</code>.
     */
    int INFINITY = Integer.MAX_VALUE;

    /**
     * Executes the shortest path algorithm until its completion. After calling
     * <code>run</code> the details of the algorithm's execution can then be
     * accessed using the member methods.
     *
     * @return the length of a shortest path
     */
    double run();

    /**
     * Returns the graph used by this pathfinder
     *
     * @return the graph used by this pathfinder
     */
    Graph getGraph();

    /**
     * Returns <code>true</code> if the specified node has been visited.
     *
     * @param u the node whose visited status to return
     * @return <code>true</code> if the specified node has been visited
     */
    boolean getVisited(Node u);

    /**
     * Returns the current distance to the specified node. If the node hasn't
     * been visited, will return {@link #INFINITY}.
     *
     * @param u the node whose distance to return
     * @return the current distance to the specified node
     */
    double getDist(Node u);

    /**
     * Returns the current predecessor node for the specified node.
     *
     * @param u the node whose predecessor to return
     * @return the predecessor node for the specified node
     */
    Node getPred(Node u);

    /**
     * Returns a shortest path from start to end. The parth is represented as a
     * list of nodes. As there may be more than one shortest path, returns one
     * of the possible paths. Which of the possible shortest paths is returned
     * is unspecified.
     *
     * @return nodes which constitute one possible shortest path
     */
    List<Node> getPath();

}
