package pathfinder.logic;

import java.util.PriorityQueue;

public class AStar extends AbstractPathfinder {

    private PriorityQueue<PriorityNode> q;

    public AStar(Graph g) {
        super(g);
    }

    @Override
    protected void init() {
        super.init();
        Node start = getGraph().getStart();
        q = new PriorityQueue<>();
        q.add(new PriorityNode(start, 0));
        setDist(start, 0);
    }

    @Override
    public int find() {
        init();
        Graph g = getGraph();
        Node end = g.getEnd();
        while (!q.isEmpty()) {
            Node u = q.poll().node;
            if (getVisited(u)) continue;
            setVisited(u, true);
            
            if (u.equals(end)) break;
            
            for (Node v : g.neighbours(u)) {
                if (getDist(v) > getDist(u) + 1) {
                    int priority = getDist(v) + heuristic(v, end);
                    setDist(v, getDist(u) + 1);
                    setPred(v, u);
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

    private int heuristic(Node a, Node b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }

}
