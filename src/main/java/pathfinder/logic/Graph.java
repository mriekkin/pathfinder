package pathfinder.logic;

import java.util.ArrayList;
import java.util.List;

/**
 * A specialised graph data structure, which represents a square 2D grid. A grid
 * is composed of nodes arranged to a rectangular matrix. Each node is indexed
 * by the familiar cartesian coordinates <code>(x, y)</code>, where coordinates
 * start from 0. Each graph has also one node labeled as <code>start</code> and
 * one node labeled as <code>end</code>. These are used by pathfinders as the
 * start and end nodes. Each node is either walkable or unwalkable. The
 * unwalkable cells are obstacles. A path, as created by a pathfinder, visits
 * only walkable nodes.
 * <p>
 * Adjacent nodes are called neighbours and are connected by vertices. This
 * implementation connects the usual horizontal and vertical neighbours (N, E,
 * S, W) but ignores diagonal neighbours. Hence a cell has at most 4 neighbours.
 * A node is connected only to those neighbours which are walkable. Obstacles
 * are not connected. At present, all edges have weight 1. This could be changed
 * to include more variability.
 */
public class Graph {

    private final Node[][] nodes;
    private final int cols;
    private final int rows;
    private Node start;
    private Node end;

    /**
     * Constructs a <code>Graph</code> with the specified dimensions, and the
     * specified start and end nodes.
     *
     * @param dimensions the pair <code>(cols, rows)</code>, which represents
     * the number of columns and rows
     * @param start the <code>(x, y)</code> coordinates of the the start node to
     * be used by pathfinders
     * @param end the <code>(x, y)</code> coordinates of the end node to be used
     * by pathfinders
     */
    public Graph(Pair dimensions, Pair start, Pair end) {
        this.cols = dimensions.getLeft();
        this.rows = dimensions.getRight();
        this.nodes = new Node[rows][cols];
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                nodes[y][x] = new Node(x, y, true);
            }
        }

        this.start = getNode(start.getLeft(), start.getRight());
        this.end = getNode(end.getLeft(), end.getRight());
    }

    /**
     * Returns the node at the specified <code>(x, y)</code> coordinates in this
     * grid. Each node is indexed by the familiar cartesian coordinates
     * <code>(x, y)</code>, where coordinates start from 0.
     *
     * @param x the x-coordinate of the node to be returned
     * @param y the y-coordinate of the node to be returned
     * @return the node at the specified <code>(x, y)</code> coordinates
     */
    public final Node getNode(int x, int y) {
        if (x < 0 || y < 0 || x >= cols || y >= rows) {
            throw new IllegalArgumentException("Coordinates out of range (" + x + ", " + y + ")");
        }

        return nodes[y][x];
    }

    /**
     * Returns the number of columns in this grid. Each grid has a specific
     * number of rows and columns.
     *
     * @return the number of columns in this grid
     */
    public final int getCols() {
        return cols;
    }

    /**
     * Returns the number of rows in this grid. Each grid has a specific number
     * of rows and columns.
     *
     * @return the number of rows in this grid
     */
    public final int getRows() {
        return rows;
    }

    /**
     * Returns the node labeled as a starting node.
     *
     * @return the node labeled as a starting node for this graph
     * @see #setStart(Node)
     */
    public Node getStart() {
        return start;
    }

    /**
     * Returns the node labeled as an end node.
     *
     * @return the node labeled as an end node for this graph
     * @see #setEnd(Node)
     */
    public Node getEnd() {
        return end;
    }

    /**
     * Labels the specified node as the new starting node for this graph. Each
     * graph has one node labeled as <code>start</code> and one node labeled as
     * <code>end</code>. These are used by pathfinders as the start and end
     * nodes.
     *
     * @param start the node to be labeled as the new starting node
     */
    public void setStart(Node start) {
        this.start = start;
    }

    /**
     * Labels the specified node as the new end node for this graph. Each graph
     * has one node labeled as <code>start</code> and one node labeled as
     * <code>end</code>. These are used by pathfinders as the start and end
     * nodes.
     *
     * @param end the node to be labeled as the new end node
     */
    public void setEnd(Node end) {
        this.end = end;
    }

    /**
     * Returns the list of neighbours for the specified node.
     * <p>
     * Adjacent nodes are called neighbours and are connected by vertices. This
     * implementation connects the usual horizontal and vertical neighbours (N,
     * E, S, W) but ignores diagonal neighbours. Hence a cell has at most 4
     * neighbours. A node is connected only to those neighbours which are
     * walkable. Obstacles are not connected.
     *
     * @param u the node whose neighbours are to be returned
     * @return the list of neighbours for the specified node
     */
    public List<Node> neighbours(Node u) {
        return neighbours(u.x(), u.y());
    }

    /**
     * Returns the list of neighbours for the node at the specified coordinates
     * in this grid.
     * <p>
     * Adjacent nodes are called neighbours and are connected by vertices. This
     * implementation connects the usual horizontal and vertical neighbours (N,
     * E, S, W) but ignores diagonal neighbours. Hence a cell has at most 4
     * neighbours. A node is connected only to those neighbours which are
     * walkable. Obstacles are not connected.
     *
     * @param x the x-coordinate of the node in this grid
     * @param y the y-coordinate of the node in this grid
     * @return the list of neighbours for the node at the specified coordinates
     */
    public List<Node> neighbours(int x, int y) {
        ArrayList<Node> adj = new ArrayList<>(4);

        if (y > 0 && getNode(x, y-1).isWalkable()) {
            adj.add(getNode(x, y-1));
        }

        if (x+1 < cols && getNode(x+1, y).isWalkable()) {
            adj.add(getNode(x+1, y));
        }

        if (y+1 < rows && getNode(x, y+1).isWalkable()) {
            adj.add(getNode(x, y+1));
        }

        if (x > 0 && getNode(x-1, y).isWalkable()) {
            adj.add(getNode(x-1, y));
        }

        return adj;
    }

}
