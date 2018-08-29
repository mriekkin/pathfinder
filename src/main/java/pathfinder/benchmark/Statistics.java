package pathfinder.benchmark;

/**
 * Computation of selected statistical measures.
 */
public class Statistics {

    /**
     * Returns the median of the specified array of doubles.
     *
     * @param x array whose median is to be computed
     * @return the median of the specified array
     */
    public static final double median(double[] x) {
        final int n = x.length;
        if (n % 2 == 0) {
            return (x[n/2] + x[n/2 - 1]) / 2.0;
        } else {
            return x[n/2];
        }
    }

    /**
     * Sorts the specified array into ascending order, according to the natural
     * ordering of its elements.
     * <p>
     * This method implements insertion sort. Insertion sort was chosen because
     * it's simple and sufficiently fast for very short arrays. The arrays
     * to be sorted by this method are expected to be of length 10 or less.
     * 
     * @param a the array to be sorted
     */
    protected static void sort(double[] a) {
        for (int i = 1; i < a.length; i++) {
            double x = a[i];
            int j = i-1;
            while (j >= 0 && a[j] > x) {
                a[j+1] = a[j];
                j--;
            }

            a[j+1] = x;
        }
    }

}
