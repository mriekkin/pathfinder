package pathfinder.logic.pathfinders;

import java.util.ArrayDeque;
import java.util.Queue;
import pathfinder.logic.Graph;
import pathfinder.logic.Node;

/**
 * Implements breadth-first search (BFS) for finding the shortest path between
 * two nodes. This is mostly a standard implementation which is described in
 * CLRS. The only addition is the "early exit" condition.
 * <p>
 * The running time of BFS is O(V + E).
 */
public class BFS extends AbstractPathfinder {

    private Queue<Node> q;

    /**
     * Constructs a <code>BFS</code> object with the specified graph.
     *
     * @param g the graph to be used by this pathfinder
     */
    public BFS(Graph g) {
        super(g);
    }

    @Override
    protected void init() {
        super.init();
        Node start = g.getStart();
        q = new ArrayDeque<>();
        q.add(start);
        setVisited(start, true);
        setDist(start, 0);
    }

    @Override
    public double run() {
        init();
        Node end = g.getEnd();
        while (!q.isEmpty()) {
            Node u = q.poll();
            if (u.equals(end)) {
                break;
            }

            for (Node v : g.neighbours(u)) {
                if (!getVisited(v)) {
                    setVisited(v, true);
                    setDist(v, getDist(u) + getDistAdj(u, v));
                    setPred(v, u);
                    q.add(v);
                }
            }
        }

        if (getDist(end) == INFINITY) {
            return -1;
        }

        updatePath();
        return getDist(end);
    }

}
