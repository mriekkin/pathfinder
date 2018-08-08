package pathfinder.logic.pathfinders;

import pathfinder.logic.Node;

public class Heuristics {

    private static final double SQRT2 = Math.sqrt(2);

    public static double manhattanDist(Node a, Node b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }

    public static double octileDist(Node a, Node b) {
        int dx = Math.abs(b.x() - a.x());
        int dy = Math.abs(b.y() - a.y());
        return 1 * (dx + dy) + (SQRT2 - 2 * 1) * Math.min(dx, dy);
    }

}
