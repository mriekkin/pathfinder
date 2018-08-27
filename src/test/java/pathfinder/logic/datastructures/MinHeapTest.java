package pathfinder.logic.datastructures;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class MinHeapTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    MinHeap<Integer> heap;

    @Before
    public void setUp() {
        heap = new MinHeap<>();
        heap.add(10);
        heap.add(11);
        heap.add(12);
        heap.add(13);
        heap.add(14);
        heap.add(15);
    }

    @Test
    public void constructorThrowsExceptionIfInitialCapacityNonPositive() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Capacity non-positive: 0");
        heap = new MinHeap<>(0);
    }

    @Test
    public void addCanInsertMaximalElement() {
        heap.add(100);
        assertEquals("[10, 11, 12, 13, 14, 15, 100]", heap.toString());
    }

    @Test
    public void addCanInsertMinimalElement() {
        heap.add(0);
        assertEquals("[0, 11, 10, 13, 14, 15, 12]", heap.toString());
    }

    @Test
    public void addCanInsertIntermediateElement() {
        heap.add(0);
        heap.add(1);
        assertEquals("[0, 1, 10, 11, 14, 15, 12, 13]", heap.toString());
    }

    @Test
    public void addCanResizeWhenFull() {
        heap.add(16);
        heap.add(17);
        heap.add(18);
        heap.add(19);
        heap.add(20);
        assertEquals(11, heap.size());
    }

    @Test
    public void clearRemovesAllOfTheElements() {
        heap.clear();
        assertEquals(0, heap.size());
    }

    @Test
    public void peekReturnsMinimalElement() {
        assertEquals(Integer.valueOf(10), heap.peek());
    }

    @Test
    public void peekDoesNotRemoveMinimalElement() {
        heap.peek();
        assertEquals(6, heap.size());
    }

    @Test
    public void peekReturnsNullIfQueueIsEmpty() {
        heap = new MinHeap<>();
        assertNull(heap.peek());
    }

    @Test
    public void pollReturnsMinimalElement() {
        assertEquals(Integer.valueOf(10), heap.poll());
    }

    @Test
    public void pollRemovesMinimalElement() {
        heap.poll();
        assertEquals(5, heap.size());
    }

    @Test
    public void canPollRepeatedly() {
        heap.poll();
        heap.poll();
        assertEquals("[12, 13, 14, 15]", heap.toString());
    }

    @Test
    public void canPollUntilEmpty() {
        heap.poll();
        heap.poll();
        heap.poll();
        heap.poll();
        heap.poll();
        heap.poll();
        assertEquals(0, heap.size());
    }

    @Test
    public void canDoACombinationOfAddAndPoll() {
        heap.poll();
        heap.add(0);
        heap.add(1);
        heap.add(-1);
        heap.poll();
        heap.poll();
        assertEquals(6, heap.size());
        assertEquals("[1, 13, 11, 15, 14, 12]", heap.toString());
    }

    @Test
    public void pollReturnsNullIfQueueIsEmpty() {
        heap = new MinHeap<>();
        assertNull(heap.poll());
    }

    @Test
    public void sizeReturnsNumberOfElements() {
        assertEquals(6, heap.size());
    }

    @Test
    public void heapWithEqualValues() {
        heap = new MinHeap<>();
        heap.add(1);
        heap.add(1);
        heap.add(1);
        assertEquals("[1, 1, 1]", heap.toString());
        heap.poll();
        heap.poll();
        heap.poll();
        assertEquals(0, heap.size());
    }

}
