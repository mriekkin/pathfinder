package pathfinder.logic;

import java.util.ArrayList;
import java.util.List;

public class Graph {

    private final Node[][] nodes;
    private final int cols;
    private final int rows;
    private Node start;
    private Node end;

    public Graph(Pair dimensions, Pair start, Pair end) {
        this.cols = dimensions.getL();
        this.rows = dimensions.getR();
        this.nodes = new Node[rows][cols];
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                nodes[y][x] = new Node(x, y, true);
            }
        }

        this.start = getNode(start.getL(), start.getR());
        this.end = getNode(end.getL(), end.getR());
    }

    public Node getNode(int x, int y) {
        if (x < 0 || y < 0 || x >= cols || y >= rows) {
            throw new IllegalArgumentException("Coordinates out of range (" + x + ", " + y + ")");
        }

        return nodes[y][x];
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    public Node getStart() {
        return start;
    }

    public Node getEnd() {
        return end;
    }

    public void setStart(Node start) {
        this.start = start;
    }

    public void setEnd(Node end) {
        this.end = end;
    }

    public List<Node> neighbours(Node u) {
        return neighbours(u.x(), u.y());
    }

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
