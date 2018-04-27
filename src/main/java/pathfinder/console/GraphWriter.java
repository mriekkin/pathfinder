package pathfinder.console;

import pathfinder.logic.Dijkstra;
import pathfinder.logic.Graph;
import pathfinder.logic.Node;

/**
 * Plots ASCII representations of graphs
 * 
 */
public class GraphWriter {

    public static String plotGrid(Graph g, Node start, Node end) {
        StringBuffer s = new StringBuffer();
        for (int y = g.getHeight()-1; y >= 0; y--) {
            for (int x = 0; x < g.getWidth(); x++) {
                s.append(getNode(g.getNode(x, y), start, end));
            }

            if (y > 0) s.append("\n");
        }

        return s.toString();
    }

    public static String plotDistances(Dijkstra pathfinder) {
        StringBuffer s = new StringBuffer();
        Graph g = pathfinder.getGraph();
        for (int y = g.getHeight()-1; y >= 0; y--) {
            for (int x = 0; x < g.getWidth(); x++) {
                s.append(getDistance(pathfinder, g.getNode(x, y)));
                s.append(' ');
            }

            if (y > 0) s.append("\n");
        }

        return s.toString();
    }

    public static String plotPredecessors(Dijkstra pathfinder) {
        StringBuffer s = new StringBuffer();
        Graph g = pathfinder.getGraph();
        for (int y = g.getHeight()-1; y >= 0; y--) {
            for (int x = 0; x < g.getWidth(); x++) {
                s.append(getPredecessor(pathfinder, g.getNode(x, y)));
            }

            if (y > 0) s.append("\n");
        }

        return s.toString();
    }

    private static String getNode(Node node, Node start, Node end) {
        String s = getSpecialCases(node, start, end);
        if (s != null) return s;

        return ". ";
    }

    private static String getDistance(Dijkstra pathfinder, Node node) {
        String s = getSpecialCases(node, pathfinder.getStart(), pathfinder.getEnd());
        if (s != null) return s;

        int dist = pathfinder.getDist(node);
        if (dist == Integer.MAX_VALUE) return ". ";
        if (dist < 10) return Integer.toString(dist) + " ";

        return Integer.toString(dist);
    }

    private static String getPredecessor(Dijkstra pathfinder, Node node) {
        String s = getSpecialCases(node, pathfinder.getStart(), pathfinder.getEnd());
        if (s != null) return s;

        Node pred = pathfinder.getPred(node);
        if (pred == null) return ". ";
        if (pred.x() < node.x()) return "< ";
        if (pred.x() > node.x()) return "> ";
        if (pred.y() < node.y()) return "v ";
        if (pred.y() > node.y()) return "^ ";

        return "  ";
    }

    private static String getSpecialCases(Node node, Node start, Node end) {
        if (!node.isWalkable()) return "##";
        if (node.equals(start)) return "S ";
        if (node.equals(end)) return "E ";

        return null;
    }

}
