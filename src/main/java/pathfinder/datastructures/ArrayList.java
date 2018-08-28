package pathfinder.datastructures;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A resizable array which implements the <code>List</code> interface.
 *
 * @param <E> the type of elements in this list
 */
public class ArrayList<E> extends AbstractCollection<E> implements List<E> {

    private E[] table;
    private int n;

    /**
     * Constructs an empty list with an initial capacity of ten
     */
    public ArrayList() {
        this(10);
    }

    /**
     * Constructs an empty list with the specified initial capacity
     *
     * @param capacity initial capacity of this list
     */
    @SuppressWarnings("unchecked")
    public ArrayList(int capacity) {
        if (capacity <= 0)
            throw new IllegalArgumentException("Capacity non-positive: " + capacity);

        this.table = (E[]) new Object[capacity];
        this.n = 0;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= n) throw new ArrayIndexOutOfBoundsException(index);

        return table[index];
    }

    @Override
    public void add(E e) {
        add(n, e);
    }

    @Override
    public void add(int index, E element) {
        // In here we allow the index n (one-over the last position)
        if (index < 0 || index > n) throw new ArrayIndexOutOfBoundsException(index);
        if (isFull()) {
            resize(2 * table.length);
        }

        for (int i = n; i-1 >= index; i--) {
            table[i] = table[i-1];
        }

        table[index] = element;
        n++;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= n) throw new ArrayIndexOutOfBoundsException(index);

        E element = get(index);
        for (int i = index; i+1 < n; i++) {
            table[i] = table[i+1];
        }
        n--;

        return element;
    }

    @Override
    public void clear() {
        n = 0;
    }

    @SuppressWarnings("unchecked")
    private void resize(int capacity) {
        E[] copy = (E[]) new Object[capacity];
        System.arraycopy(table, 0, copy, 0, n);

        table = copy;
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    private boolean isFull() {
        return n == table.length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;

        final ArrayList<?> other = (ArrayList<?>) o;
        if (this.n != other.n)
            return false;

        for (int i = 0; i < n; i++) {
            if (!get(i).equals(other.get(i)))
                return false;
        }

        return true;
    }

    /**
     * Returns an iterator over the elements in this list.
     *
     * @return an iterator over the elements in this list
     */
    @Override
    public Iterator<E> iterator() {
        return new Iter();
    }

    /**
     * An iterator over the elements in this list. This iterator returns the
     * elements in sequential order.
     */
    private class Iter implements Iterator<E> {

        private int index;

        public Iter() {
            this.index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < n;
        }

        @Override
        public E next() {
            if (index >= n)
                throw new NoSuchElementException("No such element: index = " + index);

            return get(index++);
        }

    }

}
