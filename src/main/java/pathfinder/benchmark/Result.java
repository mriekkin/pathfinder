package pathfinder.benchmark;

public class Result {

    private final Experiment experiment;
    private final long time;
    private final double dist;

    public Result(Experiment experiment, long time, double dist) {
        this.experiment = experiment;
        this.time = time;
        this.dist = dist;
    }

    public Experiment getExperiment() {
        return experiment;
    }

    public long getTime() {
        return time;
    }

    public double getDist() {
        return dist;
    }

}
