package pathfinder.logic.pathfinders;

import java.util.ArrayDeque;
import java.util.Queue;
import pathfinder.logic.Graph;
import pathfinder.logic.Node;

public class BFS extends AbstractPathfinder {

    private Queue<Node> q;

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
    public int find() {
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
                    setDist(v, getDist(u) + 1);
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