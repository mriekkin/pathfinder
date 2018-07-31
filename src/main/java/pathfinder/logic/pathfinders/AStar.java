package pathfinder.logic.pathfinders;

import java.util.PriorityQueue;
import pathfinder.logic.Graph;
import pathfinder.logic.Node;

/**
 * Implements the A* algorithm for the shortest path between two nodes.
 */
public class AStar extends AbstractPathfinder {

    private PriorityQueue<PriorityNode> q;

    /**
     * Constructs an <code>AStar</code> object with the specified graph.
     *
     * @param g the graph to be used by this pathfinder
     */
    public AStar(Graph g) {
        super(g);
    }

    @Override
    protected void init() {
        super.init();
        Node start = g.getStart();
        q = new PriorityQueue<>();
        q.add(new PriorityNode(start, 0));
        setDist(start, 0);
    }

    @Override
    public double run() {
        init();
        Node end = g.getEnd();
        while (!q.isEmpty()) {
            Node u = q.poll().node;
            if (getVisited(u)) continue;
            setVisited(u, true);
            
            if (u.equals(end)) break;
            
            for (Node v : g.neighbours(u)) {
                double alt = getDist(u) + getDistAdj(u, v);

                if (getDist(v) > alt) {
                    setDist(v, alt);
                    setPred(v, u);
                    double priority = getDist(v) + heuristic(v, end);
                    q.add(new PriorityNode(v, priority));
                }
            }
        }
        
        if (getDist(end) == INFINITY) {
            return -1;
        }

        updatePath();
        return getDist(end);
    }

    private double heuristic(Node a, Node b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }

}
