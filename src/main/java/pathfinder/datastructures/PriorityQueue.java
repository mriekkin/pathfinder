package pathfinder.datastructures;

/**
 * A priority queue.
 * <p>
 * The elements are sorted according to their natural ordering.
 *
 * @param <E> the type of elements in this priority queue
 */
public interface PriorityQueue<E extends Comparable<E>> {

    /**
     * Inserts the specified element into this priority queue.
     *
     * @param e the element to add
     */
    void add(E e);

    /**
     * Removes all of the elements from this priority queue.
     */
    void clear();

    /**
     * Returns the minimal element in this priority queue, or returns null if
     * this queue is empty.
     *
     * @return the minimal element, or null if this queue is empty
     */
    E peek();

    /**
     * Returns and removes the minimal element from this priority queue, or
     * returns null if this queue is empty.
     *
     * @return the minimal element, or null if this queue is empty
     */
    E poll();

    /**
     * Returns the number of elements in this priority queue.
     *
     * @return the number of elements
     */
    int size();

    /**
     * Returns true if this priority queue has no elements.
     *
     * @return true if this queue has no elements, false otherwise
     */
    boolean isEmpty();

}
