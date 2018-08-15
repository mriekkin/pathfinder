/**
 * The identification of neighbour nodes. Concepts relevant to this package are
 * corner-cutting and pruning.
 * <p>
 * Corner-cutting refers to taking a diagonal shortcut around a corner. In
 * principle, a corner-cutting move should not be possible if the agent has
 * physical width. Still, corner-cutting is sometimes allowed in simulations.
 * Therefore, this package allows the user to choose whether corner-cutting
 * moves are allowed or disallowed. By default they are disallowed.
 * <p>
 * Pruning is a method used by the Jump point search algorithm. Pruning reduces
 * the number of neighbour nodes. To be more precise, pruning eliminates nodes
 * which do not have to be evaluated in order to reach the destination node
 * optimally. It is based on the notion of path symmetry.
 */
package pathfinder.logic.neighbours;
