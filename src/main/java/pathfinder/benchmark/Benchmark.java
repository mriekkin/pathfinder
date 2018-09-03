package pathfinder.benchmark;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import pathfinder.datastructures.ArrayList;
import pathfinder.datastructures.List;
import pathfinder.io.GraphReaderException;
import pathfinder.io.ScenarioFileException;
import pathfinder.io.ScenarioReader;

/**
 * Main class for the benchmark mode.
 * <p>
 * The main job of this class is to wire things together. It delegates the
 * majority of the work to {@link ScenarioReader} and {@link RunScenario}. This
 * class also catches any exceptions, should they arise, and prints their
 * contents.
 */
public class Benchmark {

    private List<Experiment> experiments;
    private Path mapDirectory;
    private final int replicates;
    private final boolean cornerCutting;
    private final Timer timer;
    private final PrintStream out;

    /**
     * Constructs a <code>Benchmark</code> object with the specified scenario
     * file and output stream. Loads the specified scenario file.
     *
     * @param scenarioFile a scenario file to be loaded
     * @param replicates the number of times each experiment is to be repeated
     * @param cornerCutting whether corner-cutting is allowed
     * @param out an output stream where the results are to be printed
     */
    public Benchmark(String scenarioFile, int replicates, boolean cornerCutting, OutputStream out) {
        this.out = new PrintStream(out);
        this.replicates = replicates;
        this.cornerCutting = cornerCutting;
        this.timer = new Timer();
        loadScenario(scenarioFile);
    }

    private void loadScenario(String file) {
        experiments = new ArrayList<>(); // Avoid a possible null pointer

        try {
            Path scenarioFile = Paths.get(file);
            experiments = new ScenarioReader().read(scenarioFile);
            mapDirectory = scenarioFile.getParent();
        } catch (ScenarioFileException e) {
            out.println("Cannot load scenario");
            out.println("   " + e);
            if (e.getCause() != null) {
                out.println("   " + e.getCause());
            }
            out.println("   " + e.getLine());
        } catch (IOException e) {
            out.println("Cannot load scenario");
            out.println("   " + e);
        }
    }

    /**
     * Runs the scenario which was specified upon construction.
     */
    public void run() {
        if (experiments.isEmpty()) {
            return;
        }

        try {
            RunScenario runner = new RunScenario(replicates, mapDirectory, timer, cornerCutting, out);
            runner.run(experiments);
        } catch (IOException | GraphReaderException e) {
            out.println("Cannot run scenario");
            out.println("   " + e);
        }
    }

}
