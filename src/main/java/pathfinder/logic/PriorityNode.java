package pathfinder.logic;

public class PriorityNode implements Comparable<PriorityNode> {

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
