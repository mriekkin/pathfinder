package pathfinder.datastructures;

/**
 * A last-in-first-out (LIFO) stack of objects.
 *
 * @param <E> the type of elements in this stack
 */
public interface Stack<E> extends Collection<E> {

    /**
     * Pushes an element onto the top of this stack.
     *
     * @param e the element to be pushed onto this stack
     */
    @Override
    public void add(E e);

    /**
     * Pushes an element onto the top of this stack.
     *
     * @param e the element to be pushed onto this stack
     */
    public void push(E e);

    /**
     * Returns the element at the top of this stack.
     *
     * @return the element at the top of this stack
     */
    public E peek();

    /**
     * Removes and returns the element at the top of this stack.
     *
     * @return the element at the top of this stack
     */
    public E pop();

}
