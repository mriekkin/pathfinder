package pathfinder.logic.neighbours;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pathfinder.datastructures.List;
import pathfinder.logic.Graph;
import pathfinder.logic.Node;
import pathfinder.logic.Pair;

public class NeighbourPruningRulesCcDisallowedTest {

    Graph g;

    @Before
    public void setUp() {
        Pair dimensions = new Pair(10, 10);
        Pair source = new Pair(1, 1);
        Pair dest = new Pair(9, 9);
        g = new Graph(dimensions, source, dest);
    }

    @Test
    public void returnsNaturalNeighboursForHorizontalMove() {
        NeighbourPruningRules prune = new NeighbourPruningRulesCcDisallowed(g);
        Node p = g.getNode(4, 5);
        Node x = g.getNode(5, 5);
        List<Node> neighbours = prune.getNeighbours(p, x);
        assertEquals(1, neighbours.size());
        assertEquals("(6, 5)", neighbours.get(0).toString());
    }

    @Test
    public void returnsForcedNeighboursForHorizontalMove() {
        NeighbourPruningRules prune = new NeighbourPruningRulesCcDisallowed(g);
        Node p = g.getNode(4, 5);
        Node x = g.getNode(5, 5);
        g.getNode(4, 4).setWalkable(false); // Add obstacles,
        g.getNode(4, 6).setWalkable(false); // which should create forced neighbours
        List<Node> neighbours = prune.getNeighbours(p, x);
        assertEquals(5, neighbours.size());
        assertEquals("(6, 5)", neighbours.get(0).toString()); // 1 natural neighbour
        assertEquals("(5, 4)", neighbours.get(1).toString()); // 4 forced neighbours
        assertEquals("(6, 4)", neighbours.get(2).toString());
        assertEquals("(5, 6)", neighbours.get(3).toString());
        assertEquals("(6, 6)", neighbours.get(4).toString());
    }

    @Test
    public void preventsCornerCuttingForHorizontalMove1() {
        NeighbourPruningRules prune = new NeighbourPruningRulesCcDisallowed(g);
        Node p = g.getNode(4, 5);
        Node x = g.getNode(5, 5);
        g.getNode(4, 4).setWalkable(false); // Add obstacles,
        g.getNode(4, 6).setWalkable(false); // which should create forced neighbours
        g.getNode(6, 5).setWalkable(false); // but prevent diagonal movement (1/2)
        List<Node> neighbours = prune.getNeighbours(p, x);
        assertEquals(2, neighbours.size());
        assertEquals("(5, 4)", neighbours.get(0).toString()); // only 2 forced neighbours (as opposed to 4)
        assertEquals("(5, 6)", neighbours.get(1).toString()); // the natural neighbour is blocked
    }

    @Test
    public void preventsCornerCuttingForHorizontalMove2() {
        NeighbourPruningRules prune = new NeighbourPruningRulesCcDisallowed(g);
        Node p = g.getNode(4, 5);
        Node x = g.getNode(5, 5);
        g.getNode(4, 4).setWalkable(false); // Add obstacles,
        g.getNode(4, 6).setWalkable(false); // which should create forced neighbours
        g.getNode(5, 4).setWalkable(false); // but prevent diagonal movement (2/2)
        g.getNode(5, 6).setWalkable(false);
        List<Node> neighbours = prune.getNeighbours(p, x);
        assertEquals(1, neighbours.size());
        assertEquals("(6, 5)", neighbours.get(0).toString()); // 1 natural neighbour, no forced neighbours
    }

    @Test
    public void returnsNaturalNeighboursForVerticalMove() {
        NeighbourPruningRules prune = new NeighbourPruningRulesCcDisallowed(g);
        Node p = g.getNode(5, 4);
        Node x = g.getNode(5, 5);
        List<Node> neighbours = prune.getNeighbours(p, x);
        assertEquals(1, neighbours.size());
        assertEquals("(5, 6)", neighbours.get(0).toString());
    }

    @Test
    public void returnsForcedNeighboursForVerticalMove() {
        NeighbourPruningRules prune = new NeighbourPruningRulesCcDisallowed(g);
        Node p = g.getNode(5, 4);
        Node x = g.getNode(5, 5);
        g.getNode(4, 4).setWalkable(false); // Add obstacles,
        g.getNode(6, 4).setWalkable(false); // which should create forced neighbours
        List<Node> neighbours = prune.getNeighbours(p, x);
        assertEquals(5, neighbours.size());
        assertEquals("(5, 6)", neighbours.get(0).toString()); // 1 natural neighbour
        assertEquals("(4, 5)", neighbours.get(1).toString()); // 4 forced neighbours
        assertEquals("(4, 6)", neighbours.get(2).toString());
        assertEquals("(6, 5)", neighbours.get(3).toString());
        assertEquals("(6, 6)", neighbours.get(4).toString());
    }

    @Test
    public void preventsCornerCuttingForVerticalMove1() {
        NeighbourPruningRules prune = new NeighbourPruningRulesCcDisallowed(g);
        Node p = g.getNode(5, 4);
        Node x = g.getNode(5, 5);
        g.getNode(4, 4).setWalkable(false); // Add obstacles,
        g.getNode(6, 4).setWalkable(false); // which should create forced neighbours
        g.getNode(5, 6).setWalkable(false); // but prevent diagonal movement (1/2)
        List<Node> neighbours = prune.getNeighbours(p, x);
        assertEquals(2, neighbours.size());
        assertEquals("(4, 5)", neighbours.get(0).toString()); // only 2 forced neighbours (as opposed to 4)
        assertEquals("(6, 5)", neighbours.get(1).toString()); // the natural neighbour is blocked
    }

    @Test
    public void preventsCornerCuttingForVerticalMove2() {
        NeighbourPruningRules prune = new NeighbourPruningRulesCcDisallowed(g);
        Node p = g.getNode(5, 4);
        Node x = g.getNode(5, 5);
        g.getNode(4, 4).setWalkable(false); // Add obstacles,
        g.getNode(6, 4).setWalkable(false); // which should create forced neighbours
        g.getNode(4, 5).setWalkable(false); // but prevent diagonal movement (2/2)
        g.getNode(6, 5).setWalkable(false);
        List<Node> neighbours = prune.getNeighbours(p, x);
        assertEquals(1, neighbours.size());
        assertEquals("(5, 6)", neighbours.get(0).toString()); // 1 natural neighbour, no forced neighbours
    }

    @Test
    public void returnsNaturalNeighboursForDiagonalMove() {
        NeighbourPruningRules prune = new NeighbourPruningRulesCcDisallowed(g);
        Node p = g.getNode(4, 6);
        Node x = g.getNode(5, 5);
        List<Node> neighbours = prune.getNeighbours(p, x);
        assertEquals(3, neighbours.size());
        assertEquals("(5, 4)", neighbours.get(0).toString());
        assertEquals("(6, 4)", neighbours.get(1).toString());
        assertEquals("(6, 5)", neighbours.get(2).toString());
    }

    @Test
    public void returnsNaturalNeighboursForDiagonalMoveButChecksForCornerCutting1() {
        NeighbourPruningRules prune = new NeighbourPruningRulesCcDisallowed(g);
        Node p = g.getNode(4, 6);
        Node x = g.getNode(5, 5);
        g.getNode(5, 4).setWalkable(false); // Prevent diagonal movement (1/3)
        g.getNode(6, 5).setWalkable(false); // (block both cardinal directions)
        List<Node> neighbours = prune.getNeighbours(p, x);
        assertEquals(0, neighbours.size()); // No natural neighbours
    }

    @Test
    public void returnsNaturalNeighboursForDiagonalMoveButChecksForCornerCutting2() {
        NeighbourPruningRules prune = new NeighbourPruningRulesCcDisallowed(g);
        Node p = g.getNode(4, 6);
        Node x = g.getNode(5, 5);
        g.getNode(5, 4).setWalkable(false); // Prevent diagonal movement (2/3)
        List<Node> neighbours = prune.getNeighbours(p, x);
        assertEquals(1, neighbours.size());
        assertEquals("(6, 5)", neighbours.get(0).toString()); // 1 natural neighbour remains
    }

    @Test
    public void returnsNaturalNeighboursForDiagonalMoveButChecksForCornerCutting3() {
        NeighbourPruningRules prune = new NeighbourPruningRulesCcDisallowed(g);
        Node p = g.getNode(4, 6);
        Node x = g.getNode(5, 5);
        g.getNode(6, 5).setWalkable(false); // Prevent diagonal movement (3/3)
        List<Node> neighbours = prune.getNeighbours(p, x);
        assertEquals(1, neighbours.size());
        assertEquals("(5, 4)", neighbours.get(0).toString()); // 1 natural neighbour remains
    }

    @Test
    public void diagonalMoveCannotGenerateForcedNeighbours() {
        // When corner-cutting is not allowed, diagonal moves cannot generate forced neighbours
        // Let's test the scenario which would generate forced neighbours IF corner-cutting were allowed
        NeighbourPruningRules prune = new NeighbourPruningRulesCcDisallowed(g);
        Node p = g.getNode(4, 6);
        Node x = g.getNode(5, 5);
        g.getNode(4, 5).setWalkable(false); // Add obstacles,
        g.getNode(5, 6).setWalkable(false); // which would create forced neighbours IF c-c were allowed
        List<Node> neighbours = prune.getNeighbours(p, x);
        assertEquals(3, neighbours.size());
        assertEquals("(5, 4)", neighbours.get(0).toString()); // 3 natural neighbours
        assertEquals("(6, 4)", neighbours.get(1).toString()); // No forced neighbours
        assertEquals("(6, 5)", neighbours.get(2).toString());
    }

    @Test
    public void doesNotReturnUnwalkableNaturalNeighbours() {
        NeighbourPruningRules prune = new NeighbourPruningRulesCcDisallowed(g);
        Node p = g.getNode(4, 5); // Horizontal move
        Node x = g.getNode(5, 5);
        g.getNode(6, 5).setWalkable(false); // Block the 1 natural neighbour
        List<Node> neighbours = prune.getNeighbours(p, x);
        assertTrue(neighbours.isEmpty());
    }

    @Test
    public void doesNotReturnUnwalkableForcedNeighbours() {
        NeighbourPruningRules prune = new NeighbourPruningRulesCcDisallowed(g);
        Node p = g.getNode(4, 5); // Horizontal move
        Node x = g.getNode(5, 5);
        g.getNode(4, 4).setWalkable(false); // Add obstacles to get 4 forced neighbours
        g.getNode(4, 6).setWalkable(false);
        g.getNode(5, 4).setWalkable(false); // But make them unwalkable
        g.getNode(6, 4).setWalkable(false);
        g.getNode(5, 6).setWalkable(false);
        g.getNode(6, 6).setWalkable(false);
        List<Node> neighbours = prune.getNeighbours(p, x);
        assertEquals(1, neighbours.size());
        assertEquals("(6, 5)", neighbours.get(0).toString()); // Only the 1 natural neighbour remains
    }

    @Test
    public void irrelevantObstaclesDoNotCreateForcedNeighbours() {
        NeighbourPruningRules prune = new NeighbourPruningRulesCcDisallowed(g);
        Node p = g.getNode(4, 5); // Horizontal move
        Node x = g.getNode(5, 5);
        g.getNode(6, 4).setWalkable(false); // Block all "irrelevant" neighbours,
        g.getNode(6, 6).setWalkable(false); // which should not create a forced neighbour
        List<Node> neighbours = prune.getNeighbours(p, x);
        assertEquals(1, neighbours.size()); // No forced neighbours (only the 1 natural neighbour)
        assertEquals("(6, 5)", neighbours.get(0).toString());
    }

    @Test
    public void returnsUnprunedNeighboursForSourceNode() {
        NeighbourPruningRules prune = new NeighbourPruningRulesCcDisallowed(g);
        Node p = null; // The source node has no predecessor
        Node x = g.getNode(1, 1);
        List<Node> neighbours = prune.getNeighbours(p, x);
        assertEquals(8, neighbours.size()); // Returns all 8 possible neighbours
    }

}
