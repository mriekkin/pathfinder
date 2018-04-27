package pathfinder.logic;

import java.util.PriorityQueue;

public class Dijkstra {

    private final Graph g;
    private final Node start;
    private final Node end;
    private PriorityQueue<PriorityNode> q;
    private boolean[][] visited;
    private int[][] dist;
    private Node[][] pred;

    public Dijkstra(Graph g, Node start, Node end) {
        this.g = g;
        this.start = start;
        this.end = end;
        this.q = new PriorityQueue<>();
        this.visited = new boolean[g.getHeight()][g.getWidth()];
        this.dist = new int[g.getHeight()][g.getWidth()];
        this.pred = new Node[g.getHeight()][g.getWidth()];

        for (int y = 0; y < g.getHeight(); y++) {
            for (int x = 0; x < g.getWidth(); x++) {
                dist[y][x] = Integer.MAX_VALUE;
            }
        }

        q.add(new PriorityNode(start, 0));
        setDist(start, 0);
    }

    public int find() {
        while (!q.isEmpty()) {
            Node u = q.poll().node;
            if (getVisited(u)) continue;
            setVisited(u, true);

            if (u.equals(end)) break;

            for (Node v : g.neighbours(u)) {
                if (getDist(v) > getDist(u) + 1) {
                    setDist(v, getDist(u) + 1);
                    setPred(v, u);
                    q.add(new PriorityNode(v, getDist(v)));
                }
            }
        }

        if (getDist(end) == Integer.MAX_VALUE) {
            return -1;
        }

        return getDist(end);
    }

    public Graph getGraph() {
        return g;
    }

    public Node getStart() {
        return start;
    }

    public Node getEnd() {
        return end;
    }

    public boolean getVisited(Node u) {
        return visited[u.y()][u.x()];
    }

    public int getDist(Node u) {
        return dist[u.y()][u.x()];
    }

    public Node getPred(Node u) {
        return pred[u.y()][u.x()];
    }

    private void setVisited(Node u, boolean visited) {
        this.visited[u.y()][u.x()] = visited;
    }

    private void setDist(Node u, int dist) {
        this.dist[u.y()][u.x()] = dist;
    }

    private void setPred(Node u, Node pred) {
        this.pred[u.y()][u.x()] = pred;
    }

}
