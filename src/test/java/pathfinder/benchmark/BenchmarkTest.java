package pathfinder.benchmark;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * There's considerable overlap between these tests and the collection of tests
 * formed by ExperimentParserTest, ScenarioReaderTest and RunScenarioTest.
 * That's acceptable, however, as these tests can be considered to be
 * integration tests.
 */
public class BenchmarkTest {

    ByteArrayOutputStream outContent;
    PrintStream out;

    @Before
    public void setUp() {
        outContent = new ByteArrayOutputStream();
        out = new PrintStream(outContent);
    }

    @Test
    public void exampleScenarioPrintsExpectedResults() {
        Benchmark b = new Benchmark("grids/tests/example.map.scen", out);
        b.run();
        // Column headers: bucket, time_Dijkstra, time_A*, dist_Dijkstra, dist_A*
        // Times are 1 because we use the stub timer which always returns 1
        // Distances should match the reference values (and be the same for all algorithms)
        assertTrue(outContent.toString().matches(""
                + "Results\n"
                + "0	[0-9]+	[0-9]+	2.82842712	2.82842712\n"
                + "0	[0-9]+	[0-9]+	2.41421356	2.41421356\n"
                + "0	[0-9]+	[0-9]+	3.82842712	3.82842712\n"
                + "0	[0-9]+	[0-9]+	3.41421356	3.41421356\n"
                + "0	[0-9]+	[0-9]+	2.41421356	2.41421356\n"
                + "0	[0-9]+	[0-9]+	1.00000000	1.00000000\n"
                + "0	[0-9]+	[0-9]+	1.41421356	1.41421356\n"
                + "0	[0-9]+	[0-9]+	3.41421356	3.41421356\n"
                + "0	[0-9]+	[0-9]+	3.41421356	3.41421356\n"
                + "0	[0-9]+	[0-9]+	2.00000000	2.00000000\n"
                + "1	[0-9]+	[0-9]+	4.41421356	4.41421356\n"
                + "1	[0-9]+	[0-9]+	5.24264069	5.24264069\n"
                + "1	[0-9]+	[0-9]+	6.41421356	6.41421356\n"
                + "1	[0-9]+	[0-9]+	5.65685425	5.65685425\n"
                + "1	[0-9]+	[0-9]+	5.00000000	5.00000000\n"
                + "1	[0-9]+	[0-9]+	7.24264069	7.24264069\n"
                + "1	[0-9]+	[0-9]+	5.24264069	5.24264069\n"
                + "1	[0-9]+	[0-9]+	6.41421356	6.41421356\n"
                + "1	[0-9]+	[0-9]+	7.24264069	7.24264069\n"
                + "1	[0-9]+	[0-9]+	6.65685425	6.65685425\n"));
    }

    @Test
    public void emptyScenarioPrintsNothing() {
        Benchmark b = new Benchmark("grids/tests/empty_file.map.scen", out);
        b.run();
        assertEquals("", outContent.toString());
    }

    @Test
    public void printsErrorMessageIfScenarioFileDoesNotExists() {
        Benchmark b = new Benchmark("grids/tests/non-existent_file.map.scen", out);
        assertEquals(""
                + "Cannot load scenario\n"
                + "   java.nio.file.NoSuchFileException: grids/tests/non-existent_file.map.scen\n",
                outContent.toString());
    }

    @Test
    public void printsErrorMessageIfScenarioFileHasInvalidVersionNumber() {
        // The version number should be 1 but in this case it isn't
        Benchmark b = new Benchmark("grids/tests/invalid_version.map.scen", out);
        assertEquals(""
                + "Cannot load scenario\n"
                + "   pathfinder.io.ScenarioFileException: Invalid version number\n"
                + "   version 2\n",
                outContent.toString());
    }

    @Test
    public void printsErrorMessageIfScenarioFileHasMissingColumn() {
        // One line in the scenario file is missing the first column
        Benchmark b = new Benchmark("grids/tests/missing_column.map.scen", out);
        assertEquals(""
                + "Cannot load scenario\n"
                + "   pathfinder.io.ScenarioFileException: Invalid experiment definition: Missing column(s)\n"
                + "   lak100d.map	538	792	381	541	384	543	3.82842712\n",
                outContent.toString());
    }

    @Test
    public void printsErrorMessageIfScenarioFileHasInvalidValue() {
        // One line in the scenario file has an invalid value
        Benchmark b = new Benchmark("grids/tests/invalid_value.map.scen", out);
        assertEquals(""
                + "Cannot load scenario\n"
                + "   pathfinder.io.ScenarioFileException: Invalid experiment definition: Invalid value\n"
                + "   java.lang.NumberFormatException: For input string: \"not-int\"\n"
                + "   0	lak100d.map	not-int	792	381	541	384	543	3.82842712\n",
                outContent.toString());
    }

    @Test
    public void printsErrorMessageIfScenarioFileContainsInvalidMapName() {
        // The scenario file refers to a map which doesn't exist
        Benchmark b = new Benchmark("grids/tests/invalid_map.map.scen", out);
        b.run();
        assertEquals(""
                + "Cannot run scenario\n"
                + "   java.nio.file.NoSuchFileException: grids/tests/invalid.map\n",
                outContent.toString());
    }

}
