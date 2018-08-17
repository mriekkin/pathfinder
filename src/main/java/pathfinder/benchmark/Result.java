package pathfinder.benchmark;

/**
 * Represents the results of a single experiment. Each experiment represents one
 * shortest path problem. Hence the results include the median time taken to
 * solve the problem (time) and the median length of the computed shortest path
 * (distance).
 * <p>
 * The reported results are medians because each experiment is replicated a
 * specified number of times. This means running the same shortest path problem
 * for multiple times and taking the median of the running times.
 */
public class Result {

    private final Experiment experiment;
    private final double time;
    private final double dist;

    /**
     * Constructs a <code>Result</code> object with the specified experiment,
     * time and distance.
     *
     * @param experiment the experiment whose results are represented by this
     * class
     * @param time time taken to solve the problem (in milliseconds)
     * @param dist length of the computed shortest path
     */
    public Result(Experiment experiment, double time, double dist) {
        this.experiment = experiment;
        this.time = time;
        this.dist = dist;
    }

    /**
     * Returns the experiment whose results are represented by this class.
     *
     * @return the experiment whose results are represented by this class
     */
    public Experiment getExperiment() {
        return experiment;
    }

    /**
     * Returns the time taken to solve the problem (in milliseconds).
     *
     * @return the time taken to solve the problem (in milliseconds)
     */
    public double getTime() {
        return time;
    }

    /**
     * Returns the length of the computed shortest path.
     *
     * @return the length of the computed shortest path
     */
    public double getDist() {
        return dist;
    }

}
