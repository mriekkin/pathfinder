package pathfinder.datastructures;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A stack backed by an <code>ArrayList</code> and which implements the
 * <code>Stack</code> interface.
 *
 * @param <E> the type of elements in this stack
 */
public class ArrayStack<E> extends AbstractCollection<E> implements Stack<E> {

    private ArrayList<E> list;

    /**
     * Constructs an empty stack with an initial capacity of ten.
     */
    public ArrayStack() {
        this(10);
    }

    /**
     * Constructs an empty stack with the specified initial capacity.
     *
     * @param capacity initial capacity of this stack
     */
    @SuppressWarnings("unchecked")
    public ArrayStack(int capacity) {
        this.list = new ArrayList<>(capacity);
    }

    @Override
    public void add(E e) {
        push(e);
    }

    @Override
    public void push(E e) {
        list.add(e);
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }

        return list.get(top());
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            return null;
        }

        return list.remove(top());
    }

    private int top() {
        return list.size() - 1;
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public int size() {
        return list.size();
    }

    /**
     * Returns an iterator over the elements in this stack.
     *
     * @return an iterator over the elements in this stack
     */
    @Override
    public Iterator<E> iterator() {
        return new Iter();
    }

    /**
     * An iterator over the elements in this stack. This iterator returns in
     * reverse order the elements in the backing list.
     */
    private class Iter implements Iterator<E> {

        private int index;

        public Iter() {
            this.index = top();
        }

        @Override
        public boolean hasNext() {
            return index >= 0;
        }

        @Override
        public E next() {
            if (index < 0) {
                throw new NoSuchElementException("No such element: index = " + index);
            }

            return list.get(index--);
        }

    }

}
