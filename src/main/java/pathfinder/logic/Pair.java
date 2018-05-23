package pathfinder.logic;

/**
 * A pair consisting of two integers. A pair can represent, for instance
 * coordinates or dimensions. In this implementation <code>Pair</code> objects
 * are immutable. The elements are referred to as 'left' and 'right'.
 */
public class Pair {

    private final int left;
    private final int right;

    /**
     * Constructs a <code>Pair</code> with the specified left and right
     * elements.
     *
     * @param left the left element
     * @param right the right element
     */
    public Pair(int left, int right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Returns the left element from this pair.
     *
     * @return the left element
     */
    public final int getLeft() {
        return left;
    }

    /**
     * Returns the right element from this pair.
     *
     * @return the right element
     */
    public final int getRight() {
        return right;
    }

    @Override
    public String toString() {
        return "(" + left + ", " + right + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        final Pair other = (Pair) obj;
        return this.left == other.left && this.right == other.right;
    }

}
