package pathfinder.logic;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PairTest {

    Pair pair;

    @Before
    public void setUp() {
        pair = new Pair(1, 2);
    }

    @Test
    public void getLeftReturnsTheLeftElement() {
        assertEquals(1, pair.getLeft());
    }

    @Test
    public void getRightReturnsTheRightElement() {
        assertEquals(2, pair.getRight());
    }

    @Test
    public void toStringWorks() {
        assertEquals("(1, 2)", pair.toString());
    }

    @Test
    public void equalsWorks() {
        assertTrue(pair.equals(pair));
        assertTrue(pair.equals(new Pair(1, 2)));
        assertFalse(pair.equals(null));
        assertFalse(pair.equals(1));
        assertFalse(pair.equals(new Pair(1, 3)));
        assertFalse(pair.equals(new Pair(2, 3)));
    }

}
