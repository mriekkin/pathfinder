package pathfinder.io;

import org.junit.Test;
import static org.junit.Assert.*;

public class ScenarioFileExceptionTest {

    @Test
    public void getLineReturnsTheLineWhichCausedException() {
        ScenarioFileException e = new ScenarioFileException("Some problem", "100 1 2 3");
        assertEquals("Some problem", e.getMessage());
        assertEquals("100 1 2 3", e.getLine());
    }

}