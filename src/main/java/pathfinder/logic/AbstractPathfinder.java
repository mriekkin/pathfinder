package pathfinder.logic;

public abstract class AbstractPathfinder implements Pathfinder {

    private final Graph g;
    private final Node start;
    private final Node end;
    private boolean[][] visited;
    private int[][] dist;
    private Node[][] pred;

    public AbstractPathfinder(Graph g, Node start, Node end) {
        this.g = g;
        this.start = start;
        this.end = end;
        this.visited = new boolean[g.getHeight()][g.getWidth()];
        this.dist = new int[g.getHeight()][g.getWidth()];
        this.pred = new Node[g.getHeight()][g.getWidth()];

        for (int y = 0; y < g.getHeight(); y++) {
            for (int x = 0; x < g.getWidth(); x++) {
                dist[y][x] = INFINITY;
            }
        }

        dist[start.y()][start.x()] = 0;
    }


    @Override
    public Graph getGraph() {
        return g;
    }

    @Override
    public Node getStart() {
        return start;
    }

    @Override
    public Node getEnd() {
        return end;
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

}
