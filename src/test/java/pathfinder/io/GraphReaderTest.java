package pathfinder.io;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;
import pathfinder.logic.Graph;

public class GraphReaderTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void readFileCreatesGraphFromFile() throws IOException {
        Graph g = GraphReader.readFile(Paths.get("grids/tests/small.map"));
        assertEquals(30, g.getCols());
        assertEquals(15, g.getRows());
        assertEquals(""
                + ".....................##.......\n"
                + ".....................##.......\n"
                + ".................E...##.......\n"
                + "...##................##.......\n"
                + "...##........##......##.......\n"
                + "...##........##......#####....\n"
                + "...##........##......#####....\n"
                + "...##...S....##...............\n"
                + "...##........##...............\n"
                + "...##........##...............\n"
                + "...##........##...............\n"
                + "...##........##...............\n"
                + ".............##...............\n"
                + ".............##...............\n"
                + ".............##...............",
                GraphWriter.plotGrid(g));
    }

    @Test
    public void readFileAcceptsAtSignForObstacles() throws IOException {
        Graph g = GraphReader.readFile(Paths.get("grids/tests/small_at.map"));
        assertEquals(30, g.getCols());
        assertEquals(15, g.getRows());
        assertEquals(""
                + ".....................##.......\n"
                + ".....................##.......\n"
                + ".................E...##.......\n"
                + "...##................##.......\n"
                + "...##........##......##.......\n"
                + "...##........##......#####....\n"
                + "...##........##......#####....\n"
                + "...##...S....##...............\n"
                + "...##........##...............\n"
                + "...##........##...............\n"
                + "...##........##...............\n"
                + "...##........##...............\n"
                + ".............##...............\n"
                + ".............##...............\n"
                + ".............##...............",
                GraphWriter.plotGrid(g));
    }

    @Test
    public void readFileThrowsExceptionWhenNoSuchFileExists() throws IOException {
        thrown.expect(NoSuchFileException.class);
        thrown.expectMessage("grids/tests/non-existent-file.map");
        GraphReader.readFile(Paths.get("grids/tests/non-existent-file.map"));
    }

}
