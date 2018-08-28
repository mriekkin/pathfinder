package pathfinder.datastructures;

import java.util.Iterator;

/**
 * Implements a few methods which are common to all collections.
 *
 * @param <E> the type of elements in this collection
 */
public abstract class AbstractCollection<E> implements Collection<E> {

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

}
