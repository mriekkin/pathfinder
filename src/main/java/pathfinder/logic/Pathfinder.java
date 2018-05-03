package pathfinder.logic;

import java.util.List;

public interface Pathfinder {

    /**
     * Represents infinity. Equals Integer.MAX_VALUE
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
