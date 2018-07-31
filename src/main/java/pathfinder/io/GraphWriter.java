package pathfinder.io;

import pathfinder.logic.Graph;
import pathfinder.logic.Node;

public class GraphWriter {

    public static String plotGrid(Graph g) {
        StringBuffer s = new StringBuffer();
        for (int y = g.getRows()-1; y >= 0; y--) {
            for (int x = 0; x < g.getCols(); x++) {
                s.append(getNode(g, g.getNode(x, y)));
            }

            if (y > 0) s.append("\n");
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
        if (node.equals(g.getSource())) return "S";
        if (node.equals(g.getDest())) return "E";

        return null;
    }

}
