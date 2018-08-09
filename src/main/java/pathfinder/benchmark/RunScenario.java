package pathfinder.benchmark;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import pathfinder.io.GraphReader;
import pathfinder.logic.Graph;
import pathfinder.logic.pathfinders.*;

public class RunScenario {

    private final List<Experiment> experiments;
    private final int replicates;
    private final Path mapDirectory;
    private final Timer timer;
    private final PrintStream out;
    private final DecimalFormat format;

    public RunScenario(List<Experiment> experiments, int replicates,
            Path mapDirectory, Timer timer, OutputStream out) {
        this.experiments = experiments;
        this.replicates = replicates;
        this.mapDirectory = mapDirectory;
        this.timer = timer;
        this.out = new PrintStream(out);
        this.format = getDistFormat();
    }

    public void run() throws IOException {
        if (experiments.isEmpty()) {
            return;
        }

        // We assume that in one scenario file all experiments use the same map
        Graph g = loadFirstGraph();
        List<Pathfinder> algorithms = getAlgorithms(g);
        RunExperiment runner = new RunExperiment(g, algorithms, replicates, timer);

        out.println("Results");

        for (Experiment e : experiments) {
            Result[] results = runner.run(e);
            printRow(e, results);
        }
    }

    private List<Pathfinder> getAlgorithms(Graph g) {
        List<Pathfinder> algorithms = new ArrayList<>();
        algorithms.add(new Dijkstra(g));
        algorithms.add(new AStar(g));
        //algorithms.add(new JumpPointSearch(g));
        return algorithms;
    }

    private Graph loadFirstGraph() throws IOException {
        String mapName = experiments.get(0).getMap();
        Path mapFile = mapDirectory.resolve(mapName);
        return GraphReader.readFile(mapFile);
    }

    private void printRow(Experiment e, Result[] results) {
        StringBuilder row = new StringBuilder();
        row.append(e.getBucket());
        for (Result result : results) {
            row.append("\t");
            row.append(result.getTime());
        }

        for (Result result : results) {
            row.append("\t");
            row.append(format.format(result.getDist()));
        }

        out.println(row);
    }

    private DecimalFormat getDistFormat() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        return new DecimalFormat("0.00000000", symbols);
    }

}
