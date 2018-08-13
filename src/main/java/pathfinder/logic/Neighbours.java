package pathfinder.logic;

import java.util.ArrayList;
import java.util.List;

public class Neighbours {

    /**
     * The (dx, dy) offset for each possible neighbour
     */
    private static final int[][] NEIGHBOURS = new int[][]{
        {-1, -1}, {0, -1}, {1, -1}, {-1, 0}, {1, 0}, {-1, 1}, {0, 1}, {1, 1}
    };

    private final Graph g;
    private final boolean cornerCutting;

    /**
     * Constructs a <code>Neighbours</code> object with the specified graph and
     * setting for corner-cutting.
     *
     * @param g the graph to be used by this object
     * @param cornerCutting whether corner-cutting is allowed
     */
    public Neighbours(Graph g, boolean cornerCutting) {
        this.g = g;
        this.cornerCutting = cornerCutting;
    }

    /**
     * Returns the list of neighbours for the specified node.
     * <p>
     * Adjacent nodes are called neighbours and are connected by edges. This
     * implementation connects the usual horizontal and vertical neighbours (N,
     * E, S, W) and diagonal neighbours (NE, NW, SE, SW). Hence a cell has at
     * most 8 neighbours. A node is connected only to those neighbours which are
     * walkable. Obstacles are not connected.
     *
     * @param u the node whose neighbours are to be returned
     * @return the list of neighbours for the specified node
     */
    public List<Node> getNeighbours(Node u) {
        return getNeighbours(u.x(), u.y());
    }

    /**
     * Returns the list of neighbours for the node at the specified coordinates
     * in this grid.
     * <p>
     * Adjacent nodes are called neighbours and are connected by edges. This
     * implementation connects the usual horizontal and vertical neighbours (N,
     * E, S, W) and diagonal neighbours (NE, NW, SE, SW). Hence a cell has at
     * most 8 neighbours. A node is connected only to those neighbours which are
     * walkable. Obstacles are not connected.
     *
     * @param x the x-coordinate of the node in this grid
     * @param y the y-coordinate of the node in this grid
     * @return the list of neighbours for the node at the specified coordinates
     */
    public List<Node> getNeighbours(int x, int y) {
        ArrayList<Node> neighbours = new ArrayList<>(8);

        for (int i = 0; i < NEIGHBOURS.length; i++) {
            int dx = NEIGHBOURS[i][0];
            int dy = NEIGHBOURS[i][1];
            addNeighbour(x, y, dx, dy, neighbours);
        }

        return neighbours;
    }

    private void addNeighbour(int x, int y, int dx, int dy, ArrayList<Node> neighbours) {
        if (checkNeighbour(x, y, dx, dy)) {
            neighbours.add(g.getNode(x + dx, y + dy));
        }
    }

    private boolean checkNeighbour(int x, int y, int dx, int dy) {
        if (!isWalkable(x + dx, y + dy)) {
            return false;
        }

        if (dx == 0 || dy == 0) {
            return true;
        }

        if (cornerCutting) {
            return true;
        }

        return isWalkable(x + dx, y) && isWalkable(x, y + dy);
    }

    private boolean isWalkable(int x, int y) {
        Node u = g.getNode(x, y);
        if (u == null) {
            return false; // Out of range
        }

        return u.isWalkable();
    }

}
