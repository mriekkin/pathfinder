package pathfinder.io;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import pathfinder.benchmark.Experiment;

public class ScenarioReaderTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    ScenarioReader reader;

    @Before
    public void setUp() {
        reader = new ScenarioReader();
    }

    @Test
    public void readsValidFileCorrectly() throws ScenarioFileException, IOException {
        List<Experiment> experiments = reader.read(Paths.get("grids/tests/example.map.scen"));
        assertEquals(20, experiments.size());
    }

    @Test
    public void canReadEmptyFileWithNoErrors() throws ScenarioFileException, IOException {
        List<Experiment> experiments = reader.read(Paths.get("grids/tests/empty_file.map.scen"));
        assertEquals(0, experiments.size());
    }

    @Test
    public void throwsExceptionIfNoSuchFileExists() throws ScenarioFileException, IOException {
        thrown.expect(NoSuchFileException.class);
        reader.read(Paths.get("grids/tests/non-existent_file.map.scen"));
    }

    @Test
    public void throwsExceptionIfVersionNumberOtherThan1() throws ScenarioFileException, IOException {
        thrown.expect(ScenarioFileException.class);
        thrown.expectMessage("Invalid version number");
        reader.read(Paths.get("grids/tests/invalid_version.map.scen"));
    }

}
