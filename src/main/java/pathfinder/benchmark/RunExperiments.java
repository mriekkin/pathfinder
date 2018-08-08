package pathfinder.benchmark;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import pathfinder.io.GraphReader;
import pathfinder.logic.Graph;
import pathfinder.logic.Node;
import pathfinder.logic.pathfinders.*;

public class RunExperiments {

    private final List<Experiment> experiments;
    private final int replicates;
    private final Path mapDirectory;

    public RunExperiments(List<Experiment> experiments, int replicates, Path mapDirectory) {
        this.experiments = experiments;
        this.replicates = replicates;
        this.mapDirectory = mapDirectory;
    }

    public void run() throws IOException {
        if (experiments.isEmpty()) {
            return;
        }

        // We assume that in one scenario file all experiments use the same map
        Graph g = loadFirstGraph();
        List<Pathfinder> algorithms = getAlgorithms(g);

        System.out.println("Results");

        for (Experiment e : experiments) {
            runExperiment(g, e, algorithms);
        }
    }

    private List<Pathfinder> getAlgorithms(Graph g) {
        List<Pathfinder> algorithms = new ArrayList<>();
        algorithms.add(new Dijkstra(g));
        algorithms.add(new AStar(g));
        //algorithms.add(new JumpPointSearch(g));
        return algorithms;
    }

    private void runExperiment(Graph g, Experiment e, List<Pathfinder> algorithms) {
        updateSourceAndDest(g, e);

        Result[] results = new Result[algorithms.size()];
        for (int i = 0; i < algorithms.size(); i++) {
            results[i] = runAlgorithm(e, algorithms.get(i));
        }

        printRow(e, results);
    }

    private Result runAlgorithm(Experiment e, Pathfinder algorithm) {
        long time = 0;
        double dist = 0;
        for (int i = 0; i < replicates; i++) {
            // Time one pathfinding operation (one replicate)
            long start = System.currentTimeMillis();
            dist += algorithm.run();
            long end = System.currentTimeMillis();

            time += end - start;
        }

        time = time / replicates; // Average over replicates
        dist = dist / replicates;
        return new Result(e, time, dist);
    }

    private Graph loadFirstGraph() throws IOException {
        String mapName = experiments.get(0).getMap();
        Path mapFile = mapDirectory.resolve(mapName);
        return GraphReader.readFile(mapFile);
    }

    private void updateSourceAndDest(Graph g, Experiment e) {
        Node source = g.getNode(e.getSourceX(), e.getSourceY());
        Node dest = g.getNode(e.getDestX(), e.getDestY());
        g.setSource(source);
        g.setDest(dest);
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
            row.append(result.getDist());
        }

        System.out.println(row);
    }

}
