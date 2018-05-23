package pathfinder.logic.pathfinders;

import java.util.List;
import pathfinder.logic.Graph;
import pathfinder.logic.Node;

public interface Pathfinder {

    /**
     * Represents infinity. Equals <code>Integer.MAX_VALUE</code>.
     */
    int INFINITY = Integer.MAX_VALUE;

    /**
     * Finds a shortest path from start to end.
     *
     * @return length of a shortest path
     */
    int find();

    Graph getGraph();

    boolean getVisited(Node u);

    int getDist(Node u);

    Node getPred(Node u);

    List<Node> getPath();

}
