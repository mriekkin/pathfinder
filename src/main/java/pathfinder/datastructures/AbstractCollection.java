package pathfinder.datastructures;

import java.util.Iterator;

/**
 * Implements a few methods which are common to all collections.
 *
 * @param <E> the type of elements in this collection
 */
public abstract class AbstractCollection<E> implements Collection<E> {

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        Iterator<E> iter = iterator();
        s.append('[');
        while (iter.hasNext()) {
            s.append(iter.next());

            if (iter.hasNext()) {
                s.append(", ");
            }
        }

        s.append(']');
        return s.toString();
    }

    /**
     * Throws an IllegalArgumentException if the value specified as an argument
     * is non-positive.
     *
     * @param name name of the value to be tested. Used to create a detail
     * message in case an exception is thrown.
     * @param value the value to be tested
     * @throws IllegalArgumentException if the value specified as an argument is
     * non-positive
     */
    protected final void requirePositive(String name, int value) throws IllegalArgumentException {
        if (value <= 0) {
            throw new IllegalArgumentException("Non-positive " + name + ": " + value);
        }
    }

}
