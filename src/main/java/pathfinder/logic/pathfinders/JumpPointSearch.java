package pathfinder.logic.pathfinders;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import pathfinder.logic.Graph;
import pathfinder.logic.Node;

public class JumpPointSearch extends AbstractPathfinder {

    private PriorityQueue<PriorityNode> q;

    public JumpPointSearch(Graph g) {
        super(g);
    }

    @Override
    protected void init() {
        super.init();
        Node source = g.getSource();
        q = new PriorityQueue<>();
        q.add(new PriorityNode(source, 0));
        setDist(source, 0);
    }

    @Override
    public double run() {
        init();
        Node dest = g.getDest();
        while (!q.isEmpty()) {
            Node u = q.poll().node;
            if (getVisited(u)) continue;
            setVisited(u, true);

            if (u.equals(dest)) break;

            for (Node v : identifySuccessors(u)) {
                double alt = getDist(u) + getDistAdj(u, v);

                if (getDist(v) > alt) {
                    setDist(v, alt);
                    setPred(v, u);
                    double priority = getDist(v) + heuristic(v, dest);
                    q.add(new PriorityNode(v, priority));
                }
            }
        }

        if (getDist(dest) == INFINITY) {
            return -1;
        }

        updatePath();
        return getDist(dest);
    }

    private List<Node> identifySuccessors(Node x) {
        List<Node> successors = new ArrayList<>();
        List<Node> neighbours = getPrunedNeighbours(getPred(x), x);

        return neighbours;
    }

    protected List<Node> getPrunedNeighbours(Node p, Node u) {
        ArrayList<Node> neighbours = new ArrayList<>();
        if (p == null) {
            // No pruning for the source node
            return g.neighbours(u);
        }

        int x = u.x();
        int y = u.y();
        int dx = clamp(u.x() - p.x(), -1, 1);
        int dy = clamp(u.y() - p.y(), -1, 1);

        if (dy == 0) {
            // A horizontal move

            // Natural neighbours
            // Add if the node itself is walkable
            addIfWalkable(x + dx, y, neighbours);

            // Forced neighbours
            // Add if there's an obstacle on the path and the node itself is walkable
            if (!isWalkable(x, y - 1)) {
                addIfWalkable(x + dx, y - 1, neighbours);
            }

            if (!isWalkable(x, y + 1)) {
                addIfWalkable(x + dx, y + 1, neighbours);
            }
        } else if (dx == 0) {
            // A vertical move

            // Natural neighbours
            // Add if the node itself is walkable
            addIfWalkable(x, y + dy, neighbours);

            // Forced neighbours
            // Add if there's an obstacle on the path and the node itself is walkable
            if (!isWalkable(x - 1, y)) {
                addIfWalkable(x - 1, y + dy, neighbours);
            }

            if (!isWalkable(x + 1, y)) {
                addIfWalkable(x + 1, y + dy, neighbours);
            }
        } else {
            // A diagonal move

            // Natural neighbours
            // Add if the node itself is walkable
            addIfWalkable(x, y + dy, neighbours);
            addIfWalkable(x + dx, y + dy, neighbours);
            addIfWalkable(x + dx, y, neighbours);

            // Forced neighbours
            // Add if there's an obstacle on the path and the node itself is walkable
            if (!isWalkable(x - dx, y)) {
                addIfWalkable(x - dx, y + dy, neighbours);
            }

            if (!isWalkable(x, y - dy)) {
                addIfWalkable(x + dx, y - dy, neighbours);
            }
        }

        return neighbours;
    }

    private double heuristic(Node a, Node b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }

    private int clamp(int val, int min, int max) {
        return Math.max(min, Math.min(max, val));
    }

    private boolean isWalkable(int x, int y) {
        Node u = g.getNode(x, y);
        if (u == null) {
            return false; // Out of range
        }

        return u.isWalkable();
    }

    private void addIfWalkable(int x, int y, List<Node> list) {
        if (!isWalkable(x, y)) {
            return;
        }

        list.add(g.getNode(x, y));
    }

}
