package pathfinder.datastructures;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class ArrayListTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    ArrayList<String> list;

    @Before
    public void setUp() {
        list = new ArrayList<>();
        list.add("Zero");
        list.add("One");
        list.add("Two");
        list.add("Three");
        list.add("Four");
        list.add("Five");
    }

    @Test
    public void constructorThrowsExceptionForZeroCapacity() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Capacity non-positive: 0");
        list = new ArrayList<>(0);
    }

    @Test
    public void getReturnsItemAtSpecifiedPosition() {
        assertEquals("Zero", list.get(0));
        assertEquals("One", list.get(1));
        assertEquals("Two", list.get(2));
        assertEquals("Three", list.get(3));
        assertEquals("Four", list.get(4));
        assertEquals("Five", list.get(5));
    }

    @Test
    public void getThrowsExceptionForNegativeIndex() {
        thrown.expect(ArrayIndexOutOfBoundsException.class);
        thrown.expectMessage("Array index out of range: -1");
        list.get(-1);
    }

    @Test
    public void getThrowsExceptionForTooLargeIndex() {
        thrown.expect(ArrayIndexOutOfBoundsException.class);
        thrown.expectMessage("Array index out of range: 6");
        list.get(6);
    }

    @Test
    public void addAppendsItemToTheEndOfList() {
        list.add("Six");
        assertEquals("Six", list.get(6));
    }

    @Test
    public void addIncreasesSizeByOne() {
        assertEquals(6, list.size());
        list.add("Six");
        assertEquals(7, list.size());
    }

    @Test
    public void canAddToTheBeginningOfList() {
        list.add(0, "First");
        assertEquals("First", list.get(0));
        assertEquals("Zero", list.get(1));
        assertEquals("One", list.get(2));
        assertEquals("Two", list.get(3));
        assertEquals("Three", list.get(4));
        assertEquals("Four", list.get(5));
        assertEquals("Five", list.get(6));
        assertEquals(7, list.size());
    }

    @Test
    public void canAddToTheMiddleOfList() {
        list.add(4, "Middle");
        assertEquals("Zero", list.get(0));
        assertEquals("One", list.get(1));
        assertEquals("Two", list.get(2));
        assertEquals("Three", list.get(3));
        assertEquals("Middle", list.get(4));
        assertEquals("Four", list.get(5));
        assertEquals("Five", list.get(6));
        assertEquals(7, list.size());
    }

    @Test
    public void canAddToTheEndOfList() {
        list.add(6, "Last"); // one-over the last position
        assertEquals("Zero", list.get(0));
        assertEquals("One", list.get(1));
        assertEquals("Two", list.get(2));
        assertEquals("Three", list.get(3));
        assertEquals("Four", list.get(4));
        assertEquals("Five", list.get(5));
        assertEquals("Last", list.get(6));
        assertEquals(7, list.size());
    }

    @Test
    public void addThrowsExceptionForNegativeIndex() {
        thrown.expect(ArrayIndexOutOfBoundsException.class);
        thrown.expectMessage("Array index out of range: -1");
        list.add(-1, "Negative index");
    }

    @Test
    public void addThrowsExceptionForTooLargeIndex() {
        thrown.expect(ArrayIndexOutOfBoundsException.class);
        thrown.expectMessage("Array index out of range: 7");
        list.add(7, "Index out-of-bounds"); // two-over the last
    }

    @Test
    public void addResizesWhenFull() {
        list = new ArrayList<>(1); // initial capacity 1
        list.add("Zero"); list.add("One"); list.add("Two"); list.add("Three");
        list.add("Four"); list.add("Five");
        assertEquals("Zero", list.get(0));
        assertEquals("One", list.get(1));
        assertEquals("Two", list.get(2));
        assertEquals("Three", list.get(3));
        assertEquals("Four", list.get(4));
        assertEquals("Five", list.get(5));
        assertEquals(6, list.size());
    }

    @Test
    public void canAddALargeNumberOfElements() {
        list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add("" + i);
        }
        assertEquals(1000, list.size());
    }

    @Test
    public void canRemoveFirstElement() {
        list.remove(0);
        assertEquals("One", list.get(0));
        assertEquals("Two", list.get(1));
        assertEquals("Three", list.get(2));
        assertEquals("Four", list.get(3));
        assertEquals("Five", list.get(4));
        assertEquals(5, list.size());
    }

    @Test
    public void canRemoveAnElementInTheMiddle() {
        list.remove(2);
        assertEquals("Zero", list.get(0));
        assertEquals("One", list.get(1));
        assertEquals("Three", list.get(2));
        assertEquals("Four", list.get(3));
        assertEquals("Five", list.get(4));
        assertEquals(5, list.size());
    }

    @Test
    public void canRemoveLastElement() {
        list.remove(5);
        assertEquals("Zero", list.get(0));
        assertEquals("One", list.get(1));
        assertEquals("Two", list.get(2));
        assertEquals("Three", list.get(3));
        assertEquals("Four", list.get(4));
        assertEquals(5, list.size());
    }

    @Test
    public void canRemoveTheOnlyElement() {
        list = new ArrayList<>();
        list.add("List with one element");
        list.remove(0);
        assertTrue(list.isEmpty());
    }

    @Test
    public void removeReturnsTheRemovedElement() {
        assertEquals("Five", list.remove(5));
        assertEquals("Two", list.remove(2));
        assertEquals("Zero", list.remove(0));
    }

    @Test
    public void removeDecreasesSizeByOne() {
        assertEquals(6, list.size());
        list.remove(4);
        assertEquals(5, list.size());
    }

    @Test
    public void removeThrowsExceptionForNegativeIndex() {
        thrown.expect(ArrayIndexOutOfBoundsException.class);
        thrown.expectMessage("Array index out of range: -1");
        list.remove(-1);
    }

    @Test
    public void removeThrowsExceptionForTooLargeIndex() {
        thrown.expect(ArrayIndexOutOfBoundsException.class);
        thrown.expectMessage("Array index out of range: 6");
        list.remove(6);
    }

    @Test
    public void sizeReturnsTheNumberOfItems() {
        assertEquals(6, list.size());
    }

    @Test
    public void isEmptyReturnsTrueForEmptyList() {
        list = new ArrayList<>();
        assertTrue(list.isEmpty());
    }

    @Test
    public void isEmptyReturnsFalseForNonEmptyList() {
        assertFalse(list.isEmpty());
    }

    @Test
    public void iteratorReturnsNextElement() {
        Iterator<String> iter = list.iterator();
        assertEquals(true, iter.hasNext());
        assertEquals("Zero", iter.next());
        assertEquals("One", iter.next());
        assertEquals("Two", iter.next());
        assertEquals("Three", iter.next());
        assertEquals("Four", iter.next());
        assertEquals("Five", iter.next());
        assertEquals(false, iter.hasNext());
    }

    @Test
    public void iteratorThrowsNoSuchElementExceptionAfterLastElement() {
        thrown.expect(NoSuchElementException.class);
        thrown.expectMessage("No such element: index = 6");
        Iterator<String> iter = list.iterator();
        iter.next();
        iter.next();
        iter.next();
        iter.next();
        iter.next();
        iter.next();
        iter.next();
    }

}
