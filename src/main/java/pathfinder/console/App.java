package pathfinder.console;

import pathfinder.logic.pathfinders.Dijkstra;
import pathfinder.logic.Graph;
import pathfinder.logic.Pair;

public class App {

    public static void main(String[] args) {
//      smallGrid();
        bigGrid();
    }
    
    public static void smallGrid() {
        Pair dimensions = new Pair(10, 10);
        Pair start = new Pair(0, 0);
        Pair end = new Pair(9, 0);
        Graph g = new Graph(dimensions, start, end);
        Dijkstra pathfinder = new Dijkstra(g);
        
        for (int y = 0; y < 9; y++) {
            g.getNode(5, y).setWalkable(false);
        }
        
        System.out.println(GraphWriter.plotGrid(g) + "\n");
        
        pathfinder.run();
        
        System.out.println(GraphWriter.plotDistances(pathfinder) + "\n");
        System.out.println(GraphWriter.plotPredecessors(pathfinder) + "\n");
    }
    
    public static void bigGrid() {
        Pair dimensions = new Pair(30, 15);
        Pair start = new Pair(8, 7);
        Pair end = new Pair(17, 12);
        Graph g = new Graph(dimensions, start, end);
        Dijkstra pathfinder = new Dijkstra(g);
        
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
        
        System.out.println(GraphWriter.plotGrid(g) + "\n");
        
        pathfinder.run();
        
        System.out.println(GraphWriter.plotDistances(pathfinder) + "\n");
        System.out.println(GraphWriter.plotPredecessors(pathfinder) + "\n");
    }
    
}
