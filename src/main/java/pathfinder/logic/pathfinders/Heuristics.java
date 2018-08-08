package pathfinder.logic.pathfinders;

import pathfinder.logic.Node;

/**
 * Implements a few common heuristics used by pathfinding algorithms.
 */
public class Heuristics {

    private static final double SQRT2 = Math.sqrt(2);

    /**
     * Returns the Manhattan distance between the specified nodes a and b.
     *
     * @param a first node
     * @param b second node
     * @return Manhattan distance between the specified nodes
     */
    public static double manhattanDist(Node a, Node b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }

    /**
     * Returns the octile distance between the specified nodes a and b.
     *
     * @param a first node
     * @param b second node
     * @return octile distance between the specified nodes
     */
    public static double octileDist(Node a, Node b) {
        int dx = Math.abs(b.x() - a.x());
        int dy = Math.abs(b.y() - a.y());
        return 1 * (dx + dy) + (SQRT2 - 2 * 1) * Math.min(dx, dy);
    }

}
