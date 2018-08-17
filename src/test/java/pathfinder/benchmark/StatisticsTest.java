package pathfinder.benchmark;

import org.junit.Test;
import static org.junit.Assert.*;

public class StatisticsTest {

    @Test
    public void computesMedianForOddNumberOfLongs() {
        long[] x = new long[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        assertEquals(5, Statistics.median(x), 0.000001);
    }

    @Test
    public void computesMedianForOddNumberOfDoubles() {
        long[] x = new long[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        assertEquals(5, Statistics.median(x), 0.000001);
    }

    @Test
    public void computesMedianForEvenNumberOfLongs() {
        long[] x = new long[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        assertEquals(5.5, Statistics.median(x), 0.000001);
    }

    @Test
    public void computesMedianForEvenNumberOfDoubles() {
        long[] x = new long[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        assertEquals(5.5, Statistics.median(x), 0.000001);
    }

    @Test
    public void getCoverageForEmptyDefaultConstructorOfStaticClass() {
        Statistics s = new Statistics();
    }

}
