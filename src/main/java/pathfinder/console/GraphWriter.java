package pathfinder.console;

import pathfinder.logic.Dijkstra;
import pathfinder.logic.Graph;
import pathfinder.logic.Node;

/**
 * Plots ASCII representations of graphs
 * 
 */
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

    public static String plotDistances(Dijkstra pathfinder) {
        StringBuffer s = new StringBuffer();
        Graph g = pathfinder.getGraph();
        for (int y = g.getRows()-1; y >= 0; y--) {
            for (int x = 0; x < g.getCols(); x++) {
                s.append(getDistance(g, pathfinder, g.getNode(x, y)));
                s.append(' ');
            }

            if (y > 0) s.append("\n");
        }

        return s.toString();
    }

    public static String plotPredecessors(Dijkstra pathfinder) {
        StringBuffer s = new StringBuffer();
        Graph g = pathfinder.getGraph();
        for (int y = g.getRows()-1; y >= 0; y--) {
            for (int x = 0; x < g.getCols(); x++) {
                s.append(getPredecessor(g, pathfinder, g.getNode(x, y)));
            }

            if (y > 0) s.append("\n");
        }

        return s.toString();
    }

    private static String getNode(Graph g, Node node) {
        String s = getSpecialCases(g, node);
        if (s != null) return s;

        return ". ";
    }

    private static String getDistance(Graph g, Dijkstra pathfinder, Node node) {
        String s = getSpecialCases(g, node);
        if (s != null) return s;

        int dist = pathfinder.getDist(node);
        if (dist == Integer.MAX_VALUE) return ". ";
        if (dist < 10) return Integer.toString(dist) + " ";

        return Integer.toString(dist);
    }

    private static String getPredecessor(Graph g, Dijkstra pathfinder, Node node) {
        String s = getSpecialCases(g, node);
        if (s != null) return s;

        Node pred = pathfinder.getPred(node);
        if (pred == null) return ". ";
        if (pred.x() < node.x()) return "< ";
        if (pred.x() > node.x()) return "> ";
        if (pred.y() < node.y()) return "v ";
        if (pred.y() > node.y()) return "^ ";

        return "  ";
    }

    private static String getSpecialCases(Graph g, Node node) {
        if (!node.isWalkable()) return "##";
        if (node.equals(g.getStart())) return "S ";
        if (node.equals(g.getEnd())) return "E ";

        return null;
    }

}
