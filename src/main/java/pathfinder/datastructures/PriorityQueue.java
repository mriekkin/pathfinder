package pathfinder.datastructures;

import java.util.Iterator;

/**
 * A priority queue.
 * <p>
 * The elements are sorted according to their natural ordering.
 *
 * @param <E> the type of elements in this priority queue
 */
public interface PriorityQueue<E extends Comparable<E>> extends Collection<E> {

    /**
     * Inserts the specified element into this priority queue.
     *
     * @param e the element to add
     */
    @Override
    void add(E e);

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
     * Returns an iterator over the elements in this queue. The iterator does
     * not return the elements in any particular order.
     *
     * @return an iterator over the elements in this queue
     */
    @Override
    Iterator<E> iterator();

}
