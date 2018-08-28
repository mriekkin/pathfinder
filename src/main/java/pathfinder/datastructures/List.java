package pathfinder.datastructures;

/**
 * An ordered collection.
 *
 * @param <E> the type of elements in this list
 */
public interface List<E> extends Collection<E> {

    /**
     * Returns the element at the specified position in this list
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     */
    E get(int index);

    /**
     * Appends the specified element to the end of this list
     *
     * @param e element to be appended to this list
     */
    @Override
    void add(E e);

    /**
     * Inserts the specified element at the specified position in this list
     *
     * @param index index at which the specified element is to be inserted
     * @param element element to be inserted
     */
    void add(int index, E element);

    /**
     * Removes the element at the specified position in this list
     *
     * @param index index of the element to be removed
     * @return the element removed from this list
     */
    E remove(int index);

    /**
     * Returns true if thist list and the specified list contain the same
     * objects in the same order.
     *
     * @param o the object to be compared for equality with this list
     * @return true if the specified object is equal to this list
     */
    @Override
    public boolean equals(Object o);

}
