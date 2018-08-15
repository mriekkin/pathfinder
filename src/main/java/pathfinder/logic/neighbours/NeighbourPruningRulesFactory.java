package pathfinder.logic.neighbours;

import pathfinder.logic.Graph;

/**
 * Instantiates objects which implement the interface
 * <code>NeighbourPruningRules</code>.
 */
public class NeighbourPruningRulesFactory {

    /**
     * Returns an object which implements the interface
     * <code>NeighbourPruningRules</code>:
     *
     * @param g the graph to be used by this object
     * @param cornerCutting whether corner-cutting is allowed
     * @return a newly created object
     */
    public static NeighbourPruningRules get(Graph g, boolean cornerCutting) {
        if (cornerCutting) {
            return new NeighbourPruningRulesCcAllowed(g);
        }

        return new NeighbourPruningRulesCcDisallowed(g);
    }

}
