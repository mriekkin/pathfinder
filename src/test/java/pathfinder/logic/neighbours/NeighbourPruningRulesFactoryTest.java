package pathfinder.logic.neighbours;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pathfinder.logic.Graph;
import pathfinder.logic.Pair;

public class NeighbourPruningRulesFactoryTest {

    Graph g;

    @Before
    public void setUp() {
        Pair source = new Pair(0, 0);
        Pair dest = new Pair(9, 9);
        Pair dim = new Pair(10, 10);
        g = new Graph(dim, source, dest);
    }

    @Test
    public void returnsCorrectTypeWhenCornerCuttingAllowed() {
        NeighbourPruningRules n = NeighbourPruningRulesFactory.get(g, true);
        assertTrue(n instanceof NeighbourPruningRulesCcAllowed);
    }

    @Test
    public void returnsCorrectTypeWhenCornerCuttingDisallowed() {
        NeighbourPruningRules n = NeighbourPruningRulesFactory.get(g, false);
        assertTrue(n instanceof NeighbourPruningRulesCcDisallowed);
    }

    @Test
    public void getCoverageForEmptyDefaultConstructorOfStaticClass() {
        NeighbourPruningRulesFactory f = new NeighbourPruningRulesFactory();
    }

}
