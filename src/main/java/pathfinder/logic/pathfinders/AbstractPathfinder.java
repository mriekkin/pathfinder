package pathfinder.logic.pathfinders;

import java.util.ArrayDeque;
import java.util.Deque;
import pathfinder.datastructures.ArrayList;
import pathfinder.datastructures.List;
import pathfinder.logic.Graph;
import pathfinder.logic.Node;

/**
 * This class provides a skeletal implementation of the <code>Pathfinder</code>
 * interface to minimize the effort required to implement this interface. To
 * implement a pathfinder the programmer needs only to extend this class and
 * provide implementations for the <code>init</code> and <code>run</code>
 * methods.
 */
public abstract class AbstractPathfinder implements Pathfinder {

    private static final double SQRT2 = Math.sqrt(2);

    /**
     * The graph used by this pathfinder.
     */
    protected final Graph g;

    private boolean[][] visited;
    private double[][] dist;
    private Node[][] pred;
    private List<Node> path;

    /**
     * Constructs an <code>AbstractPathfinder</code> with the specified graph.
     *
     * @param g the graph to be used by this pathfinder
     */
    public AbstractPathfinder(Graph g) {
        this.g = g;
        this.visited = new boolean[g.getRows()][g.getCols()];
        this.dist = new double[g.getRows()][g.getCols()];
        this.pred = new Node[g.getRows()][g.getCols()];

        init();
    }

    /**
     * Initializes this pathfinder.
     * <p>
     * Each invocation of <code>run</code> begins with a call to
     * <code>init</code>. Hence the users of this class don't have to call
     * <code>init</code> themselves. However, if <code>init</code> is called, it
     * resets this pathfinder into its initial state.
     */
    protected void init() {
        for (int y = 0; y < g.getRows(); y++) {
            for (int x = 0; x < g.getCols(); x++) {
                visited[y][x] = false;
                dist[y][x] = INFINITY;
                pred[y][x] = null;
            }
        }

        setDist(g.getSource(), 0);
        path = new ArrayList<>();
    }

    @Override
    public Graph getGraph() {
        return g;
    }

    @Override
    public boolean getVisited(Node u) {
        return visited[u.y()][u.x()];
    }

    @Override
    public double getDist(Node u) {
        return dist[u.y()][u.x()];
    }

    @Override
    public Node getPred(Node u) {
        return pred[u.y()][u.x()];
    }

    /**
     * Updates whether the specified node has been visited.
     *
     * @param u the node which is to be updated
     * @param visited whether the specified node has been visited
     */
    protected void setVisited(Node u, boolean visited) {
        this.visited[u.y()][u.x()] = visited;
    }

    /**
     * Updates the distance to the specified node.
     *
     * @param u the node whose distance is to be updated
     * @param dist the new distance to the specified node
     */
    protected void setDist(Node u, double dist) {
        this.dist[u.y()][u.x()] = dist;
    }

    /**
     * Updates the predecessor node of the specified node.
     *
     * @param u the node whose predecessor node is to be updated
     * @param pred the new predecessor node
     */
    protected void setPred(Node u, Node pred) {
        this.pred[u.y()][u.x()] = pred;
    }

    /**
     * Returns the distance between two adjacent nodes. Returns 1 for horizontal
     * and vertical neighbours and sqrt(2) for diagonal neighbours.
     *
     * @param u the current node
     * @param neighbour the neighbour whose distance is to be returned
     * @return the distance between the two nodes
     */
    protected double getDistAdj(Node u, Node neighbour) {
        if (u.x() == neighbour.x() || u.y() == neighbour.y())
            return 1; // Horizontal or vertical neighbour

        return SQRT2; // Diagonal neighbour
    }

    @Override
    public List<Node> getPath() {
        return path;
    }

    /**
     * Updates the member variable <code>path</code> which is returned by the
     * method <code>getPath</code>. Hence <code>updatePath</code> should be
     * called before the user is expected to call <code>getPath</code>.
     * Naturally the user is expected to call <code>getPath</code> after calling
     * <code>run</code>. Therefore this method should be called near the end of
     * <code>run</code>, after the algorithm has finished updating.
     */
    protected void updatePath() {
        this.path = constructPath();
    }

    private List<Node> constructPath() {
        Node u = g.getDest();
        if (getPred(u) == null) {
            return new ArrayList<>();
        }

        // Traverse predecessor relationships
        Deque<Node> s = new ArrayDeque<>();
        while (u != null) {
            s.push(u);
            u = getPred(u);
        }

        // Empty the stack (returns the nodes in proper order)
        ArrayList<Node> p = new ArrayList<>();
        while (!s.isEmpty()) {
            p.add(s.pop());
        }

        return p;
    }

}
