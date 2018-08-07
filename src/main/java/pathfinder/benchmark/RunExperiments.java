package pathfinder.benchmark;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import pathfinder.io.GraphReader;
import pathfinder.logic.Graph;
import pathfinder.logic.Node;
import pathfinder.logic.pathfinders.AStar;
import pathfinder.logic.pathfinders.Dijkstra;
import pathfinder.logic.pathfinders.JumpPointSearch;
import pathfinder.logic.pathfinders.Pathfinder;

public class RunExperiments {

    private final List<Experiment> experiments;
    private final int replicates;

    public RunExperiments(List<Experiment> experiments, int replicates) {
        this.experiments = experiments;
        this.replicates = replicates;
    }

    public void run() throws IOException {
        Graph g = GraphReader.readFile(Paths.get("grids/" + experiments.get(0).getMap()));
        Pathfinder dijkstra = new Dijkstra(g);
        Pathfinder aStar = new AStar(g);
        Pathfinder jps = new JumpPointSearch(g);

        System.out.println("Results");

        for (Experiment e : experiments) {
            Node source = g.getNode(e.getSourceX(), e.getSourceY());
            Node dest = g.getNode(e.getDestX(), e.getDestY());
            g.setSource(source);
            g.setDest(dest);

            Result result1 = run(e, dijkstra);
            Result result2 = run(e, aStar);
            //Result result3 = run(e, jps);

            System.out.println(e.getBucket() + "\t"
                    + result1.getTime() + "\t" + result2.getTime() + "\t"
                    + result1.getDist() + "\t" + result2.getDist());
        }
    }

    private Result run(Experiment e, Pathfinder p) {
        long time = 0;
        double dist = 0;
        for (int i = 0; i < replicates; i++) {
            // One replicate (=one pathfinding operation)
            long start = System.currentTimeMillis();
            dist += p.run();
            long end = System.currentTimeMillis();

            time += end - start;
        }

        time = time / replicates; // Average over replicates
        dist = dist / replicates;
        return new Result(e, time, dist);
    }

}
