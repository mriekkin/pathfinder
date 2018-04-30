package pathfinder.logic;

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

    Node getStart();

    Node getEnd();

    boolean getVisited(Node u);

    int getDist(Node u);

    Node getPred(Node u);

}
