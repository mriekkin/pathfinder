package pathfinder.benchmark;

/**
 * A simple mock object for the class Timer. Using this timer the method
 * <code>getElapsedTime</code> always returns 1.
 */
public class MockTimer extends Timer {

    @Override
    public long getElapsedTime() {
        return 1;
    }

}
