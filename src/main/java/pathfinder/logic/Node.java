package pathfinder.logic;

/**
 * A single node in a <code>Graph</code>. A node is part of a graph, and since
 * in this application graphs represent 2D grids, each node is assigned a pair
 * of <code>(x, y)</code> coordinates. In addition to these coordinates each
 * node also has a flag which indicates whether it's walkable or unwalkable. A
 * walkable node can be visited by a pathfinder. An unwalkable node on the other
 * hand is an obstacle and cannot be visited. The users of this class will refer
 * to nodes but should have no need to construct any new nodes. The nodes should
 * be constructed by the containing graph.
 *
 * @see Graph
 */
public class Node {

    private final int x;
    private final int y;
    private boolean walkable;

    /**
     * Constructs a <code>Node</code> with the specified <code>(x, y)</code>
     * coordinates, and the specified walkability.
     *
     * @param x the x-coordinate of this node
     * @param y the y-coordinate of this node
     * @param walkable whether this node is walkable or unwalkable
     */
    public Node(int x, int y, boolean walkable) {
        this.x = x;
        this.y = y;
        this.walkable = walkable;
    }

    /**
     * Returns the x-coordinate of this node.
     *
     * @return the x-coordinate of this node
     */
    public final int x() {
        return x;
    }

    /**
     * Returns the y-coordinate of this node.
     *
     * @return the y-coordinate of this node
     */
    public final int y() {
        return y;
    }

    /**
     * Returns true if this node is walkable.
     *
     * @return <code>true</code> if this node is walkable
     */
    public final boolean isWalkable() {
        return walkable;
    }

    /**
     * Sets whether this node is walkable.
     *
     * @param walkable whether this node will be set as walkable
     */
    public final void setWalkable(boolean walkable) {
        this.walkable = walkable;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        final Node other = (Node) obj;

        // In this case we compare only the coordinates
        return this.x == other.x && this.y == other.y;
    }

}
