package pathfinder.benchmark;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import pathfinder.datastructures.ArrayList;
import pathfinder.datastructures.List;
import pathfinder.io.GraphReader;
import pathfinder.logic.Graph;
import pathfinder.logic.neighbours.*;
import pathfinder.logic.pathfinders.*;

/**
 * Runs individual scenarios, which consist of a list of experiments.
 * <p>
 * Typically this list of experiments is defined in a scenario file.
 *
 * @see RunExperiment
 */
public class RunScenario {

    private final int replicates;
    private final Path mapDirectory;
    private final Timer timer;
    private final boolean cornerCutting;
    private final PrintStream out;
    private final DecimalFormat timeFormat;
    private final DecimalFormat distFormat;

    /**
     * Constructs a <code>RunScenario</code> object with the specified
     * properties.
     *
     * @param replicates the number of times each experiment is to be repeated
     * @param mapDirectory path of the directory which contains the map files
     * @param timer a Timer object which is to be used for timing the operations
     * @param cornerCutting whether corner-cutting is allowed
     * @param out an output stream where the results are to be printed
     */
    public RunScenario(int replicates, Path mapDirectory, Timer timer, boolean cornerCutting, OutputStream out) {
        this.replicates = replicates;
        this.mapDirectory = mapDirectory;
        this.timer = timer;
        this.cornerCutting = cornerCutting;
        this.out = new PrintStream(out);
        this.timeFormat = getTimeFormat();
        this.distFormat = getDistFormat();
    }

    /**
     * Runs the specified list of experiments (one scenario) and prints out the
     * results.
     * <p>
     * This method assumes that the specified experiments use the same map.
     *
     * @param experiments a list of experiments to be run
     * @throws IOException if the map file referenced by the experiments cannot
     * be read
     */
    public void run(List<Experiment> experiments) throws IOException {
        if (experiments.isEmpty()) {
            return;
        }

        // Since each scenario file refers to only one map
        // we assume here that the specified experiments use the same map
        Graph g = loadGraph(experiments.get(0));
        List<Pathfinder> algorithms = getAlgorithms(g);
        RunExperiment runner = new RunExperiment(g, algorithms, replicates, timer);

        // Run each experiment and print the results
        for (Experiment e : experiments) {
            Result[] results = runner.run(e);
            printRow(e, results);
        }
    }

    private List<Pathfinder> getAlgorithms(Graph g) {
        List<Pathfinder> algorithms = new ArrayList<>();
        algorithms.add(new Dijkstra(g, getNeighbours(g)));
        algorithms.add(new AStar(g, getNeighbours(g)));
        algorithms.add(new JumpPointSearch(g, getPrune(g)));
        return algorithms;
    }

    private Neighbours getNeighbours(Graph g) {
        return new Neighbours(g, cornerCutting);
    }

    private NeighbourPruningRules getPrune(Graph g) {
        return NeighbourPruningRulesFactory.get(g, cornerCutting);
    }

    private Graph loadGraph(Experiment e) throws IOException {
        String filename = e.getMap();
        Path mapFile = mapDirectory.resolve(filename);
        return GraphReader.readFile(mapFile);
    }

    private void printRow(Experiment e, Result[] results) {
        StringBuilder row = new StringBuilder();
        row.append(e.getBucket());
        for (Result result : results) {
            row.append("\t");
            row.append(timeFormat.format(result.getTime()));
        }

        for (Result result : results) {
            row.append("\t");
            row.append(distFormat.format(result.getDist()));
        }

        out.println(row);
    }

    private DecimalFormat getTimeFormat() {
        return new DecimalFormat("0.000", getSymbols());
    }

    private DecimalFormat getDistFormat() {
        return new DecimalFormat("0.00000000", getSymbols());
    }

    private DecimalFormatSymbols getSymbols() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        return symbols;
    }

}
