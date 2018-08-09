package pathfinder.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import pathfinder.benchmark.Experiment;

/**
 * Reads the contents of a scenario file, and returns the corresponding list of
 * experiments. Scenario files are CSV files defining a list of experiments.
 * 
 * @see ExperimentParser
 */
public class ScenarioReader {

    private ExperimentParser parser;

    /**
     * Constructs a <code>ScenarioReader</code> object.
     */
    public ScenarioReader() {
        this.parser = new ExperimentParser("\t");
    }

    /**
     * Reads the specified scenario file, parses the contents, and returns the
     * corresponding list of experiments.
     *
     * @param path path of the scenario file to be read
     * @return the corresponding list of experiments
     * @throws ScenarioFileException if the specified file is not a valid
     * scenario file
     * @throws IOException if reading the specified file causes an I/O error
     */
    public List<Experiment> read(Path path) throws ScenarioFileException, IOException {
        List<Experiment> experiments = new ArrayList<>();
        Charset charset = Charset.forName("US-ASCII");
        try (BufferedReader reader = Files.newBufferedReader(path, charset)) {
            checkVersionNumber(reader);
            readLines(reader, experiments);
        }

        return experiments;
    }

    private void checkVersionNumber(final BufferedReader reader) throws ScenarioFileException, IOException {
        String firstLine = reader.readLine().toLowerCase();
        if (!firstLine.equals("version 1")) {
            throw new ScenarioFileException("Invalid version number", firstLine);
        }
    }

    private void readLines(final BufferedReader reader, List<Experiment> experiments) throws ScenarioFileException, IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            Experiment e = parser.parseLine(line);
            experiments.add(e);
        }
    }

}
