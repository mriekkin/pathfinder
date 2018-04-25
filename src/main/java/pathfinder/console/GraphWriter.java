package pathfinder.console;

import pathfinder.logic.Dijkstra;
import pathfinder.logic.Graph;
import pathfinder.logic.Node;

public class GraphWriter {

    public static String plotDistances(Dijkstra pathfinder) {
        StringBuffer s = new StringBuffer();
        Graph g = pathfinder.getGraph();
        for (int y = g.getHeight()-1; y >= 0; y--) {
            for (int x = 0; x < g.getWidth(); x++) {
                s.append(getDistance(pathfinder, g.getNode(x, y)));
                s.append(' ');
            }

            s.append("\n");
        }

        return s.toString();
    }

    private static String getDistance(Dijkstra pathfinder, Node node) {
        if (!node.isWalkable()) {
            return "##";
        }

        if (node.equals(pathfinder.getStart())) {
            return "S ";
        }

        if (node.equals(pathfinder.getEnd())) {
            return "E ";
        }

        int dist = pathfinder.getDist(node);
        if (dist < 10) {
            return Integer.toString(dist) + " ";
        }
        if (dist == Integer.MAX_VALUE) {
            return ". ";
        }

        return Integer.toString(dist);
    }

    public static String plotPredecessors(Graph g) {
        return "";
    }

}
