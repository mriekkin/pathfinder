package pathfinder.io;

/**
 * Signals that an attempt to read a scenario file has failed.
 */
public class ScenarioFileException extends Exception {

    private final String line;

    /**
     * Constructs a <code>ScenarioFileException</code> with the specified detail
     * message and line.
     *
     * @param message the detail message
     * @param line the line which caused this exception to be thrown
     */
    public ScenarioFileException(String message, String line) {
        super(message);
        this.line = line;
    }

    /**
     * Constructs a <code>ScenarioFileException</code> with the specified detail
     * message, cause and line.
     *
     * @param message the detail message
     * @param cause the cause
     * @param line the line which caused this exception to be thrown
     */
    public ScenarioFileException(String message, Throwable cause, String line) {
        super(message, cause);
        this.line = line;
    }

    /**
     * Returns the line which caused this exception to be thrown.
     *
     * @return the line which caused this exception to be thrown
     */
    public String getLine() {
        return line;
    }

}
