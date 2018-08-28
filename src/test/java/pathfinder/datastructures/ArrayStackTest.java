package pathfinder.datastructures;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class ArrayStackTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    ArrayStack<String> stack;

    @Before
    public void setUp() {
        stack = new ArrayStack<>();
        stack.push("Zero");
        stack.push("One");
        stack.push("Two");
        stack.push("Three");
        stack.push("Four");
        stack.push("Five");
    }

    @Test
    public void constructorThrowsExceptionForZeroCapacity() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Non-positive capacity: 0");
        stack = new ArrayStack<>(0);
    }

    @Test
    public void addPushesItemOnTop() {
        stack.add("Six");
        assertEquals("Six", stack.peek());
    }

    @Test
    public void pushAddsItemOnTop() {
        stack.push("Six");
        assertEquals("Six", stack.peek());
    }

    @Test
    public void pushIncrementsSizeByOne() {
        assertEquals(6, stack.size());
        stack.push("Six");
        assertEquals(7, stack.size());
    }

    @Test
    public void canPushALargeNumberOfElements() {
        stack = new ArrayStack<>();
        for (int i = 0; i < 1000; i++) {
            stack.push("" + i);
        }
        assertEquals(1000, stack.size());
    }

    @Test
    public void peekReturnsElementOnTop() {
        assertEquals("Five", stack.peek());
    }

    @Test
    public void peekDoesNotRemoveElementOnTop() {
        stack.peek();
        assertEquals("Five", stack.peek());
        assertEquals(6, stack.size());
    }

    @Test
    public void peekReturnsNullIfStackIsEmpty() {
        stack = new ArrayStack<>();
        assertNull(stack.peek());
    }

    @Test
    public void popReturnsElementOnTop() {
        assertEquals("Five", stack.pop());
    }

    @Test
    public void popRemovesElementOnTop() {
        stack.pop();
        assertEquals("Four", stack.peek());
        assertEquals(5, stack.size());
    }

    @Test
    public void popReturnsNullIfStackIsEmpty() {
        stack = new ArrayStack<>();
        assertNull(stack.pop());
    }

    @Test
    public void clearRemovesAllOfTheElements() {
        stack.clear();
        assertEquals(0, stack.size());
    }

    @Test
    public void sizeReturnsTheNumberOfElements() {
        assertEquals(6, stack.size());
    }

    @Test
    public void iteratorReturnsNextElement() {
        Iterator<String> iter = stack.iterator();
        assertEquals(true, iter.hasNext());
        assertEquals("Five", iter.next());
        assertEquals("Four", iter.next());
        assertEquals("Three", iter.next());
        assertEquals("Two", iter.next());
        assertEquals("One", iter.next());
        assertEquals("Zero", iter.next());
        assertEquals(false, iter.hasNext());
    }

    @Test
    public void iteratorThrowsNoSuchElementExceptionAfterLastElement() {
        thrown.expect(NoSuchElementException.class);
        thrown.expectMessage("No such element: index = -1");
        Iterator<String> iter = stack.iterator();
        iter.next();
        iter.next();
        iter.next();
        iter.next();
        iter.next();
        iter.next();
        iter.next();
    }

}
