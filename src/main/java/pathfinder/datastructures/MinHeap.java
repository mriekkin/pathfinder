package pathfinder.datastructures;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A binary min-heap which implements the <code>PriorityQueue</code> interface.
 * <p>
 * The elements are sorted according to their natural ordering.
 *
 * @param <E> the type of elements held in this min-heap
 */
public class MinHeap<E extends Comparable<E>> extends AbstractCollection<E> implements PriorityQueue<E> {

    private E[] A;
    private int n;

    /**
     * Constructs an empty min-heap with an initial capacity of ten.
     */
    public MinHeap() {
        this(10);
    }

    /**
     * Constructs an empty min-heap with the specified initial capacity.
     *
     * @param capacity initial capacity of this list
     */
    @SuppressWarnings("unchecked")
    public MinHeap(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity non-positive: " + capacity);
        }

        // Since the generic type is bounded,
        // the backing array should be of the bounding type
        this.A = (E[]) new Comparable[capacity];
        this.n = 0;
    }

    @Override
    public void add(E e) {
        if (isFull()) {
            resize(2 * A.length);
        }

        n++;
        A[n - 1] = e;
        heapifyUp(n - 1);
    }

    @Override
    public void clear() {
        n = 0;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }

        return A[0];
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }

        E min = A[0];
        A[0] = A[n - 1];
        n--;
        heapifyDown(0);
        return min;
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
        return n == A.length;
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private int left(int i) {
        return 2 * i + 1;
    }

    private int right(int i) {
        return 2 * i + 2;
    }

    private void heapifyUp(int i) {
        while (i >= 0 && A[parent(i)].compareTo(A[i]) > 0) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    private void heapifyDown(int i) {
        int s = getSmallerChild(i);
        while (s < n && A[i].compareTo(A[s]) > 0) {
            swap(i, s);
            i = s;
            s = getSmallerChild(i);
        }
    }

    private int getSmallerChild(int i) {
        int l = left(i);
        int r = right(i);
        if (r < n && A[r].compareTo(A[l]) < 0) {
            return r;
        }

        return l;
    }

    private void swap(int i, int j) {
        E tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }

    @SuppressWarnings("unchecked")
    private void resize(int capacity) {
        E[] copy = (E[]) new Comparable[capacity];
        System.arraycopy(A, 0, copy, 0, n);

        A = copy;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iter();
    }

    /**
     * An iterator over the elements in this queue. This iterator does not
     * return the elements in any particular order.
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

            return A[index++];
        }

    }

}
