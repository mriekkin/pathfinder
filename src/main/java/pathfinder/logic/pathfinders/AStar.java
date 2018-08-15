package pathfinder.logic.pathfinders;

import java.util.PriorityQueue;
import pathfinder.logic.Graph;
import pathfinder.logic.Node;
import pathfinder.logic.neighbours.Neighbours;

/**
 * Implements the A* algorithm for the shortest path between two nodes.
 */
public class AStar extends AbstractPathfinder {

    private PriorityQueue<PriorityNode> q;
    private final Neighbours n;

    /**
     * Constructs an <code>AStar</code> object with the specified graph and
     * neighbours object.
     *
     * @param g the graph to be used by this pathfinder
     * @param n the neighbours object to be used by this pathfinder
     */
    public AStar(Graph g, Neighbours n) {
        super(g);
        this.n = n;
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
            
            for (Node v : n.getNeighbours(u)) {
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

    private double heuristic(Node a, Node b) {
        return Heuristics.octileDist(a, b);
    }

}
