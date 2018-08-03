package pathfinder.logic.pathfinders;

import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pathfinder.logic.Graph;
import pathfinder.logic.Node;
import pathfinder.logic.Pair;

public class NeighbourPruningRulesTest {

    Graph g;

    @Before
    public void setUp() {
        Pair dimensions = new Pair(10, 10);
        Pair source = new Pair(0, 0);
        Pair dest = new Pair(9, 9);
        g = new Graph(dimensions, source, dest);
    }

    @Test
    public void returnsNaturalNeighboursForHorizontalMove() {
        NeighbourPruningRules prune = new NeighbourPruningRules(g);
        Node p = g.getNode(4, 5);
        Node x = g.getNode(5, 5);
        List<Node> neighbours = prune.getPrunedNeighbours(p, x);
        assertEquals(1, neighbours.size());
        assertEquals("(6, 5)", neighbours.get(0).toString());
    }

    @Test
    public void returnsForcedNeighboursForHorizontalMove() {
        NeighbourPruningRules prune = new NeighbourPruningRules(g);
        Node p = g.getNode(4, 5);
        Node x = g.getNode(5, 5);
        g.getNode(5, 4).setWalkable(false); // Add obstacles,
        g.getNode(5, 6).setWalkable(false); // which should create forced neighbours
        List<Node> neighbours = prune.getPrunedNeighbours(p, x);
        assertEquals(3, neighbours.size());
        assertEquals("(6, 5)", neighbours.get(0).toString()); // 1 natural neighbour
        assertEquals("(6, 4)", neighbours.get(1).toString()); // 2 forced neighbours
        assertEquals("(6, 6)", neighbours.get(2).toString());
    }

    @Test
    public void returnsNaturalNeighboursForVerticalMove() {
        NeighbourPruningRules prune = new NeighbourPruningRules(g);
        Node p = g.getNode(5, 4);
        Node x = g.getNode(5, 5);
        List<Node> neighbours = prune.getPrunedNeighbours(p, x);
        assertEquals(1, neighbours.size());
        assertEquals("(5, 6)", neighbours.get(0).toString());
    }

    @Test
    public void returnsForcedNeighboursForVerticalMove() {
        NeighbourPruningRules prune = new NeighbourPruningRules(g);
        Node p = g.getNode(5, 4);
        Node x = g.getNode(5, 5);
        g.getNode(4, 5).setWalkable(false); // Add obstacles,
        g.getNode(6, 5).setWalkable(false); // which should create forced neighbours
        List<Node> neighbours = prune.getPrunedNeighbours(p, x);
        assertEquals(3, neighbours.size());
        assertEquals("(5, 6)", neighbours.get(0).toString()); // 1 natural neighbour
        assertEquals("(4, 6)", neighbours.get(1).toString()); // 2 forced neighbours
        assertEquals("(6, 6)", neighbours.get(2).toString());
    }

    @Test
    public void returnsNaturalNeighboursForDiagonalMove() {
        NeighbourPruningRules prune = new NeighbourPruningRules(g);
        Node p = g.getNode(4, 6);
        Node x = g.getNode(5, 5);
        List<Node> neighbours = prune.getPrunedNeighbours(p, x);
        assertEquals(3, neighbours.size());
        assertEquals("(5, 4)", neighbours.get(0).toString());
        assertEquals("(6, 4)", neighbours.get(1).toString());
        assertEquals("(6, 5)", neighbours.get(2).toString());
    }

    @Test
    public void returnsForcedNeighboursForDiagonalMove() {
        NeighbourPruningRules prune = new NeighbourPruningRules(g);
        Node p = g.getNode(4, 6);
        Node x = g.getNode(5, 5);
        g.getNode(4, 5).setWalkable(false); // Add obstacles,
        g.getNode(5, 6).setWalkable(false); // which should create forced neighbours
        List<Node> neighbours = prune.getPrunedNeighbours(p, x);
        assertEquals(5, neighbours.size());
        assertEquals("(5, 4)", neighbours.get(0).toString()); // 3 natural neighbours
        assertEquals("(6, 4)", neighbours.get(1).toString());
        assertEquals("(6, 5)", neighbours.get(2).toString());
        assertEquals("(4, 4)", neighbours.get(3).toString()); // 2 forced neighbours
        assertEquals("(6, 6)", neighbours.get(4).toString());
    }

    @Test
    public void doesNotReturnUnwalkableNaturalNeighbours() {
        NeighbourPruningRules prune = new NeighbourPruningRules(g);
        Node p = g.getNode(4, 5); // Horizontal move
        Node x = g.getNode(5, 5);
        g.getNode(6, 5).setWalkable(false); // Block the 1 natural neighbour
        List<Node> neighbours = prune.getPrunedNeighbours(p, x);
        assertTrue(neighbours.isEmpty());
    }

    @Test
    public void doesNotReturnUnwalkableForcedNeighbours() {
        NeighbourPruningRules prune = new NeighbourPruningRules(g);
        Node p = g.getNode(4, 5); // Horizontal move
        Node x = g.getNode(5, 5);
        g.getNode(5, 4).setWalkable(false); // Add obstacles to get 2 forced neighbours
        g.getNode(5, 6).setWalkable(false);
        g.getNode(6, 4).setWalkable(false); // But make them unwalkable
        g.getNode(6, 6).setWalkable(false);
        List<Node> neighbours = prune.getPrunedNeighbours(p, x);
        assertEquals(1, neighbours.size());
        assertEquals("(6, 5)", neighbours.get(0).toString()); // Only the 1 natural neighbour remains
    }

    @Test
    public void irrelevantObstaclesDoNotCreateForcedNeighbours() {
        NeighbourPruningRules prune = new NeighbourPruningRules(g);
        Node p = g.getNode(4, 5); // Horizontal move
        Node x = g.getNode(5, 5);
        g.getNode(4, 4).setWalkable(false); // Block all "irrelevant" neighbours,
        g.getNode(4, 6).setWalkable(false); // which should not create a forced neighbour
        List<Node> neighbours = prune.getPrunedNeighbours(p, x);
        assertEquals(1, neighbours.size()); // No forced neighbours (only the 1 natural neighbour)
        assertEquals("(6, 5)", neighbours.get(0).toString());
    }

}
