package pathfinder.logic.pathfinders;

import pathfinder.logic.neighbours.NeighbourPruningRules;
import java.util.ArrayList;
import java.util.List;
import pathfinder.datastructures.MinHeap;
import pathfinder.datastructures.PriorityQueue;
import pathfinder.logic.Graph;
import pathfinder.logic.Node;

/**
 * Implements the Jump point search (JPS) algorithm for finding the shortest
 * path between two nodes.
 *
 * @see
 * <a href="https://www.aaai.org/ocs/index.php/AAAI/AAAI11/paper/download/3761/4007">Harabor,
 * D. and Grastien, A. (2011). "Online Graph Pruning for Pathfinding on Grid
 * Maps", 25th National Conference on Artificial Intelligence, AAAI.</a>
 * @see
 * <a href="https://www.aaai.org/ocs/index.php/SOCS/SOCS12/paper/download/5396/5212">Harabor,
 * D. and Grastien, A. (2012). "The JPS Pathfinding System", 26th National
 * Conference on Artificial Intelligence, AAAI.</a>
 */
public class JumpPointSearch extends AbstractPathfinder {

    private PriorityQueue<PriorityNode> q;
    private final NeighbourPruningRules prune;
    private final Jump jump;

    /**
     * Constructs a <code>JumpPointSearch</code> object with the specified
     * graph and neighbour pruning rules.
     *
     * @param g the graph to be used by this object
     * @param prune neigbour pruning rules to be used by this object
     */
    public JumpPointSearch(Graph g, NeighbourPruningRules prune) {
        super(g);
        this.prune = prune;
        this.jump = new Jump(g, prune);
    }

    @Override
    protected void init() {
        super.init();
        Node source = g.getSource();
        q = new MinHeap<>();
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
                double alt = getDist(u) + dist(u, v);

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

    /**
     * Identifies the jump point successors of the specified node x.
     *
     * @param x the current node
     * @return jump point successors of the specified node
     */
    private List<Node> identifySuccessors(Node x) {
        List<Node> successors = new ArrayList<>();
        List<Node> neighbours = prune.getPrunedNeighbours(getPred(x), x);

        for (Node n : neighbours) {
            int dx = n.x() - x.x();
            int dy = n.y() - x.y();

            n = jump.jump(x, dx, dy);

            if (n != null) {
                successors.add(n);
            }
        }

        return successors;
    }

    private double heuristic(Node a, Node b) {
        return Heuristics.octileDist(a, b);
    }

    private double dist(Node x, Node n) {
        return Heuristics.octileDist(x, n);
    }

}
