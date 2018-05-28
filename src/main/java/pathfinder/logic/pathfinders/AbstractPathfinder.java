package pathfinder.logic.pathfinders;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
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

    /**
     * The graph used by this pathfinder.
     */
    protected final Graph g;

    private boolean[][] visited;
    private int[][] dist;
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
        this.dist = new int[g.getRows()][g.getCols()];
        this.pred = new Node[g.getRows()][g.getCols()];

        init();
    }

    /**
     * Initializes this pathfinder. One should recognize that <code>init</code>
     * must be called before calling <code>run</code>. The initial call,
     * however, is handled by the constructor of this class. Hence the users of
     * this class may just call <code>run</code>.
     * <p>
     * If <code>init</code> is called again it resets this pathfinder into its
     * initial state. One may then call <code>run</code> again. In this way it
     * is possible to repeat the same algorithm multiple times.
     */
    protected void init() {
        for (int y = 0; y < g.getRows(); y++) {
            for (int x = 0; x < g.getCols(); x++) {
                visited[y][x] = false;
                dist[y][x] = INFINITY;
                pred[y][x] = null;
            }
        }

        setDist(g.getStart(), 0);
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
    public int getDist(Node u) {
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
    protected void setDist(Node u, int dist) {
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
        Node u = g.getEnd();
        if (getPred(u) == null) {
            return new ArrayList<>();
        }

        Deque<Node> s = new ArrayDeque<>();
        while (u != null) {
            s.push(u);
            u = getPred(u);
        }

        return new ArrayList<>(s);
    }

}
