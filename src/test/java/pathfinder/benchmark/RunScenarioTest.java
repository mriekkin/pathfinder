package pathfinder.benchmark;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import pathfinder.io.ScenarioReader;

public class RunScenarioTest {

    private static final int REPLICATES = 1;
    private static final Path SCENARIO_FILE = Paths.get("grids/tests/example.map.scen");
    private static final Path MAP_DIRECTORY = SCENARIO_FILE.getParent();
    private static final boolean CC = false;

    ByteArrayOutputStream outContent;
    PrintStream out;
    Timer timer;

    @Before
    public void setUp() {
        outContent = new ByteArrayOutputStream();
        out = new PrintStream(outContent);

        timer = new MockTimer();
    }

    @Test
    public void exampleScenarioPrintsExpectedResults() throws Exception {
        List<Experiment> experiments = new ScenarioReader().read(SCENARIO_FILE);
        RunScenario runner = new RunScenario(REPLICATES, MAP_DIRECTORY, timer, CC, out);

        runner.run(experiments);

        // Column headers: bucket, time_Dijkstra, time_A*, dist_Dijkstra, dist_A*
        // Times are 1 because we use the stub timer which always returns 1
        // Distances should match the reference values (and be the same for all algorithms)
        assertEquals(""
                + "0	1.000	1.000	1.000	2.82842712	2.82842712	2.82842712\n"
                + "0	1.000	1.000	1.000	2.41421356	2.41421356	2.41421356\n"
                + "0	1.000	1.000	1.000	3.82842712	3.82842712	3.82842712\n"
                + "0	1.000	1.000	1.000	3.41421356	3.41421356	3.41421356\n"
                + "0	1.000	1.000	1.000	2.41421356	2.41421356	2.41421356\n"
                + "0	1.000	1.000	1.000	1.00000000	1.00000000	1.00000000\n"
                + "0	1.000	1.000	1.000	1.41421356	1.41421356	1.41421356\n"
                + "0	1.000	1.000	1.000	3.41421356	3.41421356	3.41421356\n"
                + "0	1.000	1.000	1.000	3.41421356	3.41421356	3.41421356\n"
                + "0	1.000	1.000	1.000	2.00000000	2.00000000	2.00000000\n"
                + "1	1.000	1.000	1.000	4.41421356	4.41421356	4.41421356\n"
                + "1	1.000	1.000	1.000	5.24264069	5.24264069	5.24264069\n"
                + "1	1.000	1.000	1.000	6.41421356	6.41421356	6.41421356\n"
                + "1	1.000	1.000	1.000	5.65685425	5.65685425	5.65685425\n"
                + "1	1.000	1.000	1.000	5.00000000	5.00000000	5.00000000\n"
                + "1	1.000	1.000	1.000	7.24264069	7.24264069	7.24264069\n"
                + "1	1.000	1.000	1.000	5.24264069	5.24264069	5.24264069\n"
                + "1	1.000	1.000	1.000	6.41421356	6.41421356	6.41421356\n"
                + "1	1.000	1.000	1.000	7.24264069	7.24264069	7.24264069\n"
                + "1	1.000	1.000	1.000	6.65685425	6.65685425	6.65685425\n",
                outContent.toString());
    }

    @Test
    public void emptyScenarioPrintsNothing() throws Exception {
        List<Experiment> experiments = new ScenarioReader().read(Paths.get("grids/tests/empty_file.map.scen"));
        RunScenario runner = new RunScenario(REPLICATES, MAP_DIRECTORY, timer, CC, out);
        runner.run(experiments);
        assertEquals("", outContent.toString());
    }

}
