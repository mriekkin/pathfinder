package pathfinder.benchmark;

import java.util.List;
import pathfinder.logic.Graph;
import pathfinder.logic.Node;
import pathfinder.logic.pathfinders.Pathfinder;

/**
 * Runs individual experiments and reports their results.
 * <p>
 * The constructor of this class accepts a list of algorithms. When an
 * experiment is run, it is repeated for each algorithm in the specified list of
 * algorithms. Repeating the same experiment for a number of algorithms allows a
 * comparison to be made between the algorithms.
 * <p>
 * The constructor of this class also accepts the number of replicates. When an
 * experiment is run, it is repeated a specified number of times (the number of
 * replicates) for each algorithm. In other words, the reported results are
 * averages because each experiment is replicated a specified number of times.
 * This means running the same shortest path problem for multiple times and
 * taking the average of the running times.
 *
 * @see Experiment
 * @see Result
 */
public class RunExperiment {

    private final Graph g;
    private final List<Pathfinder> algorithms;
    private final int replicates;
    private final Timer timer;

    /**
     * Constructs a <code>RunExperiment</code> object with the specified
     * properties.
     *
     * @param g the graph to be used in all the experiments
     * @param algorithms a list of algorithms to be tested
     * @param replicates the number of times each experiment is to be repeated
     * @param timer a Timer object which is to be used for timing the operations
     */
    public RunExperiment(Graph g, List<Pathfinder> algorithms, int replicates, Timer timer) {
        this.g = g;
        this.algorithms = algorithms;
        this.replicates = replicates;
        this.timer = timer;
    }

    /**
     * Runs the specified experiment and returns the results for each algorithm.
     *
     * @param e the experiment to be run
     * @return the results for each algorithm (specified with the constructor)
     */
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
