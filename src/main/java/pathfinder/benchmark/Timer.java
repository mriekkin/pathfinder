package pathfinder.benchmark;

/**
 * A stopwatch-like timer utility. One can start the clock by calling
 * <code>reset</code>, and then call <code>getElapsedTime</code> when the
 * operation finishes.
 */
public class Timer {

    private long start;

    /**
     * Constructs a <code>Timer</code> object.
     */
    public Timer() {
        reset();
    }

    /**
     * Resets the internal clock of this timer.
     */
    public void reset() {
        start = System.nanoTime();
    }

    /**
     * Returns the elapsed time in milliseconds since the last call to
     * <code>reset</code>.
     *
     * @return the elapsed time in milliseconds
     * @see #reset()
     */
    public double getElapsedTime() {
        return (System.nanoTime()- start) / 1000000.0;
    }

}
