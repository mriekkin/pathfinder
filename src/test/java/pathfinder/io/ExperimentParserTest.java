package pathfinder.io;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import pathfinder.benchmark.Experiment;

public class ExperimentParserTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    ExperimentParser parser;

    @Before
    public void setUp() {
        parser = new ExperimentParser("\t");
    }

    @Test
    public void parsesValidLineCorrectly() throws Exception {
        String line = "100	lak100d.map	538	792	385	535	280	325	401.09040375";
        Experiment e = parser.parseLine(line);
        assertEquals(100, e.getBucket());
        assertEquals("lak100d.map", e.getMap());
        assertEquals(538, e.getSizeX());
        assertEquals(792, e.getSizeY());
        assertEquals(385, e.getSourceX());
        assertEquals(535, e.getSourceY());
        assertEquals(280, e.getDestX());
        assertEquals(325, e.getDestY());
        assertEquals(401.09040375, e.getDist(), 0.000000005);
    }

    @Test
    public void throwsExceptionWhenLineContainsTooFewColumns() throws ScenarioFileException {
        thrown.expect(ScenarioFileException.class);
        thrown.expectMessage("Missing column(s)");
        String line = "100	lak100d.map	538	792	385	535	280	325";
        Experiment e = parser.parseLine(line);
    }

    @Test
    public void throwsExceptionWhenLineContainsInvalidInteger() throws ScenarioFileException {
        thrown.expect(ScenarioFileException.class);
        thrown.expectMessage("Invalid value");
        String line = "not-int	lak100d.map	538	792	385	535	280	325	401.09040375";
        Experiment e = parser.parseLine(line);
    }

    @Test
    public void throwsExceptionWhenLineContainsInvalidDouble() throws ScenarioFileException {
        thrown.expect(ScenarioFileException.class);
        thrown.expectMessage("Invalid value");
        String line = "100	lak100d.map	538	792	385	535	280	325	not-double";
        Experiment e = parser.parseLine(line);
    }

}
