package pathfinder.logic;

import java.util.ArrayList;
import java.util.List;

public class Graph {

    private final Node[][] nodes;
    private final int width;
    private final int height;

    public Graph(int width, int height) {
        this.width = width;
        this.height = height;
        this.nodes = new Node[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                nodes[y][x] = new Node(x, y, true);
            }
        }
    }

    public Node getNode(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) {
            throw new IllegalArgumentException("Coordinates out of range (" + x + "," + y + ")");
        }

        return nodes[y][x];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<Node> neighbours(Node u) {
        return neighbours(u.x(), u.y());
    }

    public List<Node> neighbours(int x, int y) {
        ArrayList<Node> adj = new ArrayList<>(4);

        if (y > 0 && getNode(x, y-1).isWalkable()) {
            adj.add(getNode(x, y-1));
        }

        if (x+1 < width && getNode(x+1, y).isWalkable()) {
            adj.add(getNode(x+1, y));
        }

        if (y+1 < height && getNode(x, y+1).isWalkable()) {
            adj.add(getNode(x, y+1));
        }

        if (x > 0 && getNode(x-1, y).isWalkable()) {
            adj.add(getNode(x-1, y));
        }

        return adj;
    }

}
