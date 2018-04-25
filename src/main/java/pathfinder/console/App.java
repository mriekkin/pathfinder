package pathfinder.console;

import pathfinder.logic.Dijkstra;
import pathfinder.logic.Graph;
import pathfinder.logic.Node;

public class App {

    public static void main(String[] args) {
        Graph g = new Graph(10, 10);
        Node start = g.getNode(0, 0);
        Node end = g.getNode(9, 0);
        Dijkstra pathfinder = new Dijkstra(g, start, end);
        
        for (int y = 0; y < 9; y++) {
            g.getNode(5, y).setWalkable(false);
        }
        
        System.out.println(GraphWriter.plotDistances(pathfinder));
        System.out.println();
        
        pathfinder.find();
        
        System.out.println(GraphWriter.plotDistances(pathfinder));
    }
}
