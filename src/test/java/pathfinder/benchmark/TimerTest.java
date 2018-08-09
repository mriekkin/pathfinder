package pathfinder.benchmark;

import org.junit.Test;
import static org.junit.Assert.*;

public class TimerTest {

    @Test
    public void getElapsedTimeReturnsNonnegativeValue() {
        Timer timer = new Timer();
        timer.reset();
        assertTrue(timer.getElapsedTime() >= 0);
    }

}
