package pathfinder.io;

/**
 * Signals that an attempt to read a map file has failed.
 */
public class GraphReaderException extends Exception {

    /**
     * Constructs a <code>GraphReaderException</code> with the specified detail
     * message.
     *
     * @param message the detail message
     */
    public GraphReaderException(String message) {
        super(message);
    }

    /**
     * Constructs a <code>GraphReaderException</code> with the specified detail
     * message and cause.
     *
     * @param message the detail message
     * @param cause the cause
     */
    public GraphReaderException(String message, Throwable cause) {
        super(message, cause);
    }

}
