package pathfinder.io;

import java.io.IOException;
import java.io.StringReader;
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
    public void readFileCreatesGraphFromFile() throws GraphReaderException, IOException {
        Graph g = GraphReader.readFile(Paths.get("grids/tests/small.map"));
        assertEquals(30, g.getCols());
        assertEquals(15, g.getRows());
        assertEquals(""
                + ".....................##.......\n"
                + ".....................##.......\n"
                + ".................B...##.......\n"
                + "...##................##.......\n"
                + "...##........##......##.......\n"
                + "...##........##......#####....\n"
                + "...##........##......#####....\n"
                + "...##...A....##...............\n"
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
    public void readFileAcceptsAtSignForObstacles() throws GraphReaderException, IOException {
        Graph g = GraphReader.readFile(Paths.get("grids/tests/small_at.map"));
        assertEquals(30, g.getCols());
        assertEquals(15, g.getRows());
        assertEquals(""
                + ".....................##.......\n"
                + ".....................##.......\n"
                + ".................B...##.......\n"
                + "...##................##.......\n"
                + "...##........##......##.......\n"
                + "...##........##......#####....\n"
                + "...##........##......#####....\n"
                + "...##...A....##...............\n"
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
    public void readFileThrowsExceptionWhenNoSuchFileExists() throws GraphReaderException, IOException {
        thrown.expect(NoSuchFileException.class);
        thrown.expectMessage("grids/tests/non-existent-file.map");
        GraphReader.readFile(Paths.get("grids/tests/non-existent-file.map"));
    }

    @Test
    public void readThrowsExceptionForEmptyFile() throws Exception {
        thrown.expect(GraphReaderException.class);
        thrown.expectMessage("Invalid header: expected 4 lines");
        GraphReader.read(new StringReader(""));
    }

}
