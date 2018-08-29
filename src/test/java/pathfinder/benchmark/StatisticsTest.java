package pathfinder.benchmark;

import org.junit.Test;
import static org.junit.Assert.*;

public class StatisticsTest {

    @Test
    public void computesMedianForOddNumberOfDoubles() {
        double[] x = new double[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        assertEquals(5, Statistics.median(x), 0.000001);
    }

    @Test
    public void computesMedianForEvenNumberOfDoubles() {
        double[] x = new double[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        assertEquals(5.5, Statistics.median(x), 0.000001);
    }

    @Test
    public void sortsArrayInAscendingOrder() {
        double[] x = new double[]{3, 3, 2, 5, 7, 3, 0, 9, 8, 5, 1, 4, 6, 10};
        Statistics.sort(x);
        assertArrayEquals(new double[]{0, 1, 2, 3, 3, 3, 4, 5, 5, 6, 7, 8, 9, 10}, x, 0.000001);
    }

    @Test
    public void getCoverageForEmptyDefaultConstructorOfStaticClass() {
        Statistics s = new Statistics();
    }

}
