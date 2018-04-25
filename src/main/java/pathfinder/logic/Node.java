package pathfinder.logic;

public class Node {

    private int x;
    private int y;
    private boolean walkable;

    public Node(int x, int y, boolean walkable) {
        this.x = x;
        this.y = y;
        this.walkable = walkable;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public boolean isWalkable() {
        return walkable;
    }

    public void setWalkable(boolean walkable) {
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
        return this.x == other.x && this.y == other.y;
    }

}
