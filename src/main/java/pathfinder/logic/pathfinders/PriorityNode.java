package pathfinder.logic.pathfinders;

import pathfinder.logic.Node;

class PriorityNode implements Comparable<PriorityNode> {

    public Node node;
    public int dist;

    public PriorityNode(Node node, int dist) {
        this.node = node;
        this.dist = dist;
    }

    @Override
    public int compareTo(PriorityNode p) {
        return dist - p.dist;
    }

}
