package pathfinder.datastructures;

/**
 * The root interface for all collections.
 *
 * @param <E> the type of elements in this collection
 */
public interface Collection<E> extends Iterable<E> {

    /**
     * Ensures that this collection contains the specified element.
     *
     * @param e element to add
     */
    void add(E e);

    /**
     * Removes all of the elements from this collection.
     */
    void clear();

    /**
     * Returns the number of elements in this collection
     *
     * @return the number of elements in this collection
     */
    int size();

    /**
     * Returns true if this collection contains no elements
     *
     * @return true if this collection contains no elements
     */
    boolean isEmpty();

    /**
     * Returns true if the specified object is equal to this collection.
     *
     * @param o object to be compared for equality with this collection
     * @return true if the specified object is equal to this collection
     */
    @Override
    boolean equals(Object o);

    /**
     * Returns a string representation of this collection.
     *
     * @return a string representation of this collection
     */
    @Override
    String toString();

}
