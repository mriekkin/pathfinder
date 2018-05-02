package pathfinder.logic;

import java.util.ArrayList;
import java.util.List;

public class Graph {

    private final Node[][] nodes;
    private final int cols;
    private final int rows;

    public Graph(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;
        this.nodes = new Node[rows][cols];

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                nodes[y][x] = new Node(x, y, true);
            }
        }
    }

    public Node getNode(int x, int y) {
        if (x < 0 || y < 0 || x >= cols || y >= rows) {
            throw new IllegalArgumentException("Coordinates out of range (" + x + "," + y + ")");
        }

        return nodes[y][x];
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
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
