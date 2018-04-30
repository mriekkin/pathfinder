package pathfinder.logic;

import java.util.PriorityQueue;

public class Dijkstra extends AbstractPathfinder {

    private PriorityQueue<PriorityNode> q;

    public Dijkstra(Graph g, Node start, Node end) {
        super(g, start, end);
        q = new PriorityQueue<>();
        q.add(new PriorityNode(start, 0));
        setDist(start, 0);
    }

    @Override
    public int find() {
        while (!q.isEmpty()) {
            Node u = q.poll().node;
            if (getVisited(u)) continue;
            setVisited(u, true);

            if (u.equals(getEnd())) break;

            for (Node v : getGraph().neighbours(u)) {
                if (getDist(v) > getDist(u) + 1) {
                    setDist(v, getDist(u) + 1);
                    setPred(v, u);
                    q.add(new PriorityNode(v, getDist(v)));
                }
            }
        }

        if (getDist(getEnd()) == INFINITY) {
            return -1;
        }

        return getDist(getEnd());
    }

}
