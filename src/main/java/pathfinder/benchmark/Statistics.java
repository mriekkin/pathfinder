package pathfinder.benchmark;

import java.util.Arrays;

/**
 * Computation of selected statistical measures.
 */
public class Statistics {

    /**
     * Returns the median of the specified array of longs.
     *
     * @param x array whose median is to be computed
     * @return the median of the specified array
     */
    public static final double median(long[] x) {
        Arrays.sort(x);

        final int n = x.length;
        if (n % 2 == 0) {
            return (x[n/2] + x[n/2 - 1]) / 2.0;
        } else {
            return x[n/2];
        }
    }

    /**
     * Returns the median of the specified array of doubles.
     *
     * @param x array whose median is to be computed
     * @return the median of the specified array
     */
    public static final double median(double[] x) {
        Arrays.sort(x);

        final int n = x.length;
        if (n % 2 == 0) {
            return (x[n/2] + x[n/2 - 1]) / 2.0;
        } else {
            return x[n/2];
        }
    }

}
