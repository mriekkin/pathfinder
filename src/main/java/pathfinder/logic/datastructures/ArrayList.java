package pathfinder.logic.datastructures;

/**
 * A resizable array which implements the <code>List</code> interface.
 *
 * @param <E> the type of elements in this list
 */
public class ArrayList<E> implements List<E> {

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

}
