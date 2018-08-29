package pathfinder.benchmark;

import java.io.IOException;
import java.nio.file.Paths;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pathfinder.datastructures.ArrayList;
import pathfinder.datastructures.List;
import pathfinder.io.GraphReader;
import pathfinder.logic.Graph;
import pathfinder.logic.neighbours.Neighbours;
import pathfinder.logic.pathfinders.Dijkstra;
import pathfinder.logic.pathfinders.Pathfinder;

public class RunExperimentTest {

    RunExperiment runner;
    Experiment smallExperiment;

    @Before
    public void setUp() throws IOException {
        Graph g = GraphReader.readFile(Paths.get("grids/tests/small.map"));
        List<Pathfinder> algorithms = new ArrayList<>();
        algorithms.add(new Dijkstra(g, new Neighbours(g, false)));

        Timer timer = new StubTimer();
        runner = new RunExperiment(g, algorithms, 10, timer);

        smallExperiment = new Experiment(2, "small.map", 30, 15, 8, 7, 17, 2, 4 + 5 * Math.sqrt(2));
    }

    @Test
    public void smallExperimentProducesOneResult() {
        Result[] results = runner.run(smallExperiment);
        assertEquals(results.length, 1);
    }

    @Test
    public void smallExperimentProducesCorrectOptimalPathLength() {
        // We run an experiment on the map "small.map" and compare the results
        // to what we would expect. Here we compare the length of the computed
        // shortest path to a reference value computed by hand.
        Result[] results = runner.run(smallExperiment);
        Result result = results[0];
        assertEquals(smallExperiment.getDist(), result.getDist(), 0.000001);
    }

    @Test
    public void smallExperimentFinishesInExpectedTime() {
        // Testing the running time requires a little extra effort
        // For this we've created a simple mock timer which always returns 1.
        Result[] results = runner.run(smallExperiment);
        Result result = results[0];
        assertEquals(1, result.getTime(), 0.000001);
    }

}
