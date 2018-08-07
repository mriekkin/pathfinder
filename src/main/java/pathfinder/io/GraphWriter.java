package pathfinder.io;

import pathfinder.logic.Graph;
import pathfinder.logic.Node;

public class GraphWriter {

    public static String plotGrid(Graph g) {
        StringBuilder s = new StringBuilder();
        for (int y = 0; y < g.getRows(); y++) {
            for (int x = 0; x < g.getCols(); x++) {
                s.append(getNode(g, g.getNode(x, y)));
            }

            if (y+1 < g.getRows()) s.append("\n");
        }

        return s.toString();
    }

    private static String getNode(Graph g, Node node) {
        String s = getSpecialCases(g, node);
        if (s != null) return s;

        return ".";
    }

    private static String getSpecialCases(Graph g, Node node) {
        if (!node.isWalkable()) return "#";
        if (node.equals(g.getSource())) return "A";
        if (node.equals(g.getDest())) return "B";

        return null;
    }

}
