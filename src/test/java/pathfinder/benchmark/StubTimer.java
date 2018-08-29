package pathfinder.benchmark;

/**
 * A simple stub object for the class Timer. Using this timer the method
 * <code>getElapsedTime</code> always returns 1.
 */
public class StubTimer extends Timer {

    @Override
    public double getElapsedTime() {
        return 1;
    }

}
