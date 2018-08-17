package pathfinder.logic.datastructures;

public interface List<E> {

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
     * Returns the number of elements in this list
     *
     * @return the number of elements in this list
     */
    int size();

    /**
     * Returns true if this list contains no elements
     *
     * @return true if this list contains no elements
     */
    boolean isEmpty();

}
