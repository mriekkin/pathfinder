package pathfinder.io;

import pathfinder.benchmark.Experiment;

/**
 * Parses individual lines of a scenario file. Scenario files are CSV files
 * defining a list of experiments.
 */
public class ExperimentParser {

    private static final String PREFIX = "Invalid experiment definition: ";

    private final String separator;

    /**
     * Constructs an <code>ExperimentParser</code> with the specified column
     * separator.
     *
     * @param separator column separator
     */
    public ExperimentParser(String separator) {
        this.separator = separator;
    }

    /**
     * Parses the specified line, which should define one experiment, and
     * returns the corresponding <code>Experiment</code> object.
     *
     * @param line definition of one experiment
     * @return the corresponding <code>Experiment</code> object
     * @throws ScenarioFileException if the line doesn't have the correct number
     * of columns, or contains one or more values which cannot be parsed
     */
    public Experiment parseLine(String line) throws ScenarioFileException {
        String[] columns = line.split(separator);

        if (columns.length < 9) {
            throw new ScenarioFileException(PREFIX + "Missing column(s)", line);
        }

        return parse(line, columns);
    }

    private Experiment parse(String line, String[] columns) throws ScenarioFileException {
        try {
            int bucket = Integer.parseInt(columns[0]);
            String map = columns[1];
            int sizeX = Integer.parseInt(columns[2]);
            int sizeY = Integer.parseInt(columns[3]);
            int sourceX = Integer.parseInt(columns[4]);
            int sourceY = Integer.parseInt(columns[5]);
            int destX = Integer.parseInt(columns[6]);
            int destY = Integer.parseInt(columns[7]);
            double dist = Double.parseDouble(columns[8]);

            return new Experiment(bucket, map, sizeX, sizeY,
                    sourceX, sourceY, destX, destY, dist);
        } catch (NumberFormatException e) {
            throw new ScenarioFileException(PREFIX + "Invalid value", e, line);
        }
    }

}
