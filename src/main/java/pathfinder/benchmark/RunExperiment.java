package pathfinder.benchmark;

import java.util.List;
import pathfinder.logic.Graph;
import pathfinder.logic.Node;
import pathfinder.logic.pathfinders.Pathfinder;

public class RunExperiment {

    private final Graph g;
    private final List<Pathfinder> algorithms;
    private final int replicates;
    private final Timer timer;

    public RunExperiment(Graph g, List<Pathfinder> algorithms, int replicates, Timer timer) {
        this.g = g;
        this.algorithms = algorithms;
        this.replicates = replicates;
        this.timer = timer;
    }

    public Result[] run(Experiment e) {
        updateSourceAndDest(e);

        Result[] results = new Result[algorithms.size()];
        for (int i = 0; i < algorithms.size(); i++) {
            results[i] = runAlgorithm(e, algorithms.get(i));
        }

        return results;
    }

    private Result runAlgorithm(Experiment e, Pathfinder algorithm) {
        long time = 0;
        double dist = 0;
        for (int i = 0; i < replicates; i++) {
            // Time one pathfinding operation (one replicate)
            timer.reset();
            dist += algorithm.run();
            time += timer.getElapsedTime();
        }

        time = time / replicates; // Average over replicates
        dist = dist / replicates;
        return new Result(e, time, dist);
    }

    private void updateSourceAndDest(Experiment e) {
        Node source = g.getNode(e.getSourceX(), e.getSourceY());
        Node dest = g.getNode(e.getDestX(), e.getDestY());
        g.setSource(source);
        g.setDest(dest);
    }

}
