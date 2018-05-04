package pathfinder.logic.pathfinders;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import pathfinder.logic.Graph;
import pathfinder.logic.Node;

abstract class AbstractPathfinder implements Pathfinder {

    protected final Graph g;
    private boolean[][] visited;
    private int[][] dist;
    private Node[][] pred;
    private List<Node> path;

    public AbstractPathfinder(Graph g) {
        this.g = g;
        this.visited = new boolean[g.getRows()][g.getCols()];
        this.dist = new int[g.getRows()][g.getCols()];
        this.pred = new Node[g.getRows()][g.getCols()];

        init();
    }

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

    protected void setVisited(Node u, boolean visited) {
        this.visited[u.y()][u.x()] = visited;
    }

    protected void setDist(Node u, int dist) {
        this.dist[u.y()][u.x()] = dist;
    }

    protected void setPred(Node u, Node pred) {
        this.pred[u.y()][u.x()] = pred;
    }

    @Override
    public List<Node> getPath() {
        return path;
    }

    protected void updatePath() {
        this.path = constructPath();
    }

    private List<Node> constructPath() {
        Node u = g.getEnd();
        if (getPred(u) == null)
            return new ArrayList<>();

        Deque<Node> s = new ArrayDeque<>();
        while (u != null) {
            s.push(u);
            u = getPred(u);
        }

        return new ArrayList<>(s);
    }

}
