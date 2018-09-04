package pathfinder.io;

import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;

public class GraphReaderExceptionTest {

    @Test
    public void getMessageReturnsTheDetailMessage() {
        GraphReaderException e = new GraphReaderException("reader exception");
        assertEquals("reader exception", e.getMessage());
    }

    @Test
    public void getCauseReturnsTheOriginalException() {
        IOException cause = new IOException("I/O exception");
        GraphReaderException e = new GraphReaderException("reader exception", cause);
        assertEquals(cause, e.getCause());
    }

}
