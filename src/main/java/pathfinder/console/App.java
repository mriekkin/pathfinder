package pathfinder.console;

import pathfinder.logic.Dijkstra;
import pathfinder.logic.Graph;
import pathfinder.logic.Node;

public class App {

    public static void main(String[] args) {
//      smallGrid();
        bigGrid();
    }
    
    public static void smallGrid() {
        Graph g = new Graph(10, 10);
        Node start = g.getNode(0, 0);
        Node end = g.getNode(9, 0);
        Dijkstra pathfinder = new Dijkstra(g, start, end);
        
        for (int y = 0; y < 9; y++) {
            g.getNode(5, y).setWalkable(false);
        }
        
        System.out.println(GraphWriter.plotGrid(g, start, end) + "\n");
        
        pathfinder.find();
        
        System.out.println(GraphWriter.plotDistances(pathfinder) + "\n");
        System.out.println(GraphWriter.plotPredecessors(pathfinder) + "\n");
    }
    
    public static void bigGrid() {
        Graph g = new Graph(30, 15);
        Node start = g.getNode(8, 7);
        Node end = g.getNode(17, 12);
        Dijkstra pathfinder = new Dijkstra(g, start, end);
        
        for (int y = 3; y <= 11; y++) {
            g.getNode(3, y).setWalkable(false);
            g.getNode(4, y).setWalkable(false);
        }
        
        for (int y = 0; y <= 10; y++) {
            g.getNode(13, y).setWalkable(false);
            g.getNode(14, y).setWalkable(false);
        }
        
        for (int y = 8; y < 15; y++) {
            g.getNode(21, y).setWalkable(false);
            g.getNode(22, y).setWalkable(false);
        }
        
        for (int y = 8; y <= 9; y++) {
            g.getNode(23, y).setWalkable(false);
            g.getNode(24, y).setWalkable(false);
            g.getNode(25, y).setWalkable(false);
        }
        
        System.out.println(GraphWriter.plotGrid(g, start, end) + "\n");
        
        pathfinder.find();
        
        System.out.println(GraphWriter.plotDistances(pathfinder) + "\n");
        System.out.println(GraphWriter.plotPredecessors(pathfinder) + "\n");
    }
    
}
