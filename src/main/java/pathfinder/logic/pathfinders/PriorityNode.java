package pathfinder.logic.pathfinders;

import pathfinder.logic.Node;

/**
 * A pair consisting of a node and an associated priority. Instances of this
 * class can be used as elements in a priority queue.
 */
class PriorityNode implements Comparable<PriorityNode> {

    public Node node;
    public int priority;

    public PriorityNode(Node node, int priority) {
        this.node = node;
        this.priority = priority;
    }

    @Override
    public int compareTo(PriorityNode p) {
        return priority - p.priority;
    }

}
