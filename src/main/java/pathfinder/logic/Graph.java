package pathfinder.logic;

import java.util.ArrayList;
import java.util.List;

/**
 * A specialised graph data structure, which represents a 2D grid. A grid
 * is composed of nodes arranged to a rectangular matrix. Each node is indexed
 * by the familiar cartesian coordinates <code>(x, y)</code>, where coordinates
 * start from 0. Each graph has also one node labeled as <code>source</code> and
 * one node labeled as <code>dest</code>. These are used by pathfinders as the
 * source and destination nodes. Each node is either walkable or unwalkable. The
 * unwalkable cells are obstacles. A path, as created by a pathfinder, visits
 * only walkable nodes.
 * <p>
 * Adjacent nodes are called neighbours and are connected by edges. This
 * implementation connects the usual horizontal and vertical neighbours (N,
 * E, S, W) and diagonal neighbours (NE, NW, SE, SW). Hence a cell has at
 * most 8 neighbours. A node is connected only to those neighbours which are
 * walkable. Obstacles are not connected.
 * <p>
 * At present, we model only uniform-cost grid maps. Each straight
 * (horizontal or vertical) move, from a node to one of its neighbours, costs 1;
 * diagonal moves cost sqrt(2).
 */
public class Graph {

    private final Node[][] nodes;
    private final int cols;
    private final int rows;
    private Node source;
    private Node dest;

    /**
     * Constructs a <code>Graph</code> with the specified dimensions, and the
     * specified source and destination nodes.
     *
     * @param dimensions the pair <code>(cols, rows)</code>, which represents
     * the number of columns and rows
     * @param source the <code>(x, y)</code> coordinates of the the source node
     * to be used by pathfinders
     * @param destination the <code>(x, y)</code> coordinates of the destination
     * node to be used by pathfinders
     */
    public Graph(Pair dimensions, Pair source, Pair destination) {
        this.cols = dimensions.getLeft();
        this.rows = dimensions.getRight();
        this.nodes = new Node[rows][cols];
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                nodes[y][x] = new Node(x, y, true);
            }
        }

        this.source = getNode(source.getLeft(), source.getRight());
        this.dest = getNode(destination.getLeft(), destination.getRight());
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
            return null;
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
     * Returns the node labeled as a source node.
     *
     * @return the node labeled as a source node for this graph
     * @see #setSource(Node)
     */
    public Node getSource() {
        return source;
    }

    /**
     * Returns the node labeled as a destination node.
     *
     * @return the node labeled as a destination node for this graph
     * @see #setDest(Node)
     */
    public Node getDest() {
        return dest;
    }

    /**
     * Labels the specified node as the new source node for this graph. Each
     * graph has one node labeled as <code>source</code> and one node labeled as
     * <code>dest</code>. These are used by pathfinders as the source and
     * destination nodes.
     *
     * @param source the node to be labeled as the new source node
     */
    public void setSource(Node source) {
        this.source = source;
    }

    /**
     * Labels the specified node as the new destination node for this graph.
     * Each graph has one node labeled as <code>source</code> and one node
     * labeled as <code>dest</code>. These are used by pathfinders as the source
     * and destination nodes.
     *
     * @param dest the node to be labeled as the new destination node
     */
    public void setDest(Node dest) {
        this.dest = dest;
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
    public List<Node> neighbours(Node u) {
        return neighbours(u.x(), u.y());
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
    public List<Node> neighbours(int x, int y) {
        ArrayList<Node> neighbours = new ArrayList<>(8);

        getNeighboursAbove(x, y, neighbours);
        getNeighboursMiddle(x, y, neighbours);
        getNeighboursBelow(x, y, neighbours);

        return neighbours;
    }

    private void getNeighboursAbove(int x, int y, ArrayList<Node> neighbours) {
        if (isOk(x-1, y-1) && getNode(x-1, y-1).isWalkable()) {
            neighbours.add(getNode(x-1, y-1));
        }

        if (isOk(x, y-1) && getNode(x, y-1).isWalkable()) {
            neighbours.add(getNode(x, y-1));
        }

        if (isOk(x+1, y-1) && y > 0 && getNode(x+1, y-1).isWalkable()) {
            neighbours.add(getNode(x+1, y-1));
        }
    }

    private void getNeighboursMiddle(int x, int y, ArrayList<Node> neighbours) {
        if (isOk(x-1, y) && getNode(x-1, y).isWalkable()) {
            neighbours.add(getNode(x-1, y));
        }

        if (isOk(x+1, y) && getNode(x+1, y).isWalkable()) {
            neighbours.add(getNode(x+1, y));
        }
    }

    private void getNeighboursBelow(int x, int y, ArrayList<Node> neighbours) {
        if (isOk(x-1, y+1) && getNode(x-1, y+1).isWalkable()) {
            neighbours.add(getNode(x-1, y+1));
        }

        if (isOk(x, y+1) && getNode(x, y+1).isWalkable()) {
            neighbours.add(getNode(x, y+1));
        }

        if (isOk(x+1, y+1) && getNode(x+1, y+1).isWalkable()) {
            neighbours.add(getNode(x+1, y+1));
        }
    }

    private boolean isOk(int x, int y) {
        return x >= 0 && y >= 0 && x < cols && y < rows;
    }

}
