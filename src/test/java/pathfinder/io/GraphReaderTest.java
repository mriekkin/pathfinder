package pathfinder.io;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;
import org.junit.Before;
import pathfinder.logic.Graph;

public class GraphReaderTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
    }

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

    @Test
    public void readThrowsExceptionIfInvalidType() throws Exception {
        thrown.expect(GraphReaderException.class);
        thrown.expectMessage("Invalid header: expected \"type octile\"");
        GraphReader.read(new StringReader(""
                + "type invalid\n"
                + "height 3\n"
                + "width 10\n"
                + "map\n"
                + "..........\n"
                + "..........\n"
                + ".........."));
    }

    @Test
    public void readThrowsExceptionIfHeightNotInteger() throws Exception {
        thrown.expect(GraphReaderException.class);
        thrown.expectMessage("Invalid header: expected \"height y\"");
        GraphReader.read(new StringReader(""
                + "type octile\n"
                + "height not-int\n"
                + "width 10\n"
                + "map\n"
                + "..........\n"
                + "..........\n"
                + ".........."));
    }

    @Test
    public void readThrowsExceptionIfHeightZero() throws Exception {
        thrown.expect(GraphReaderException.class);
        thrown.expectMessage("Invalid header: invalid dimensions");
        GraphReader.read(new StringReader(""
                + "type octile\n"
                + "height 0\n"
                + "width 10\n"
                + "map\n"));
    }

    @Test
    public void readThrowsExceptionIfHeightValueMissing() throws Exception {
        thrown.expect(GraphReaderException.class);
        thrown.expectMessage("Invalid header: expected \"height y\"");
        GraphReader.read(new StringReader(""
                + "type octile\n"
                + "height\n"
                + "width 10\n"
                + "map\n"
                + "..........\n"
                + "..........\n"
                + ".........."));
    }

    @Test
    public void readThrowsExceptionIfWidthNotInteger() throws Exception {
        thrown.expect(GraphReaderException.class);
        thrown.expectMessage("Invalid header: expected \"width x\"");
        GraphReader.read(new StringReader(""
                + "type octile\n"
                + "height 3\n"
                + "width not-int\n"
                + "map\n"
                + "..........\n"
                + "..........\n"
                + ".........."));
    }

    @Test
    public void readThrowsExceptionIfWidthZero() throws Exception {
        thrown.expect(GraphReaderException.class);
        thrown.expectMessage("Invalid header: invalid dimensions");
        GraphReader.read(new StringReader(""
                + "type octile\n"
                + "height 3\n"
                + "width 0\n"
                + "map\n"
                + "\n"
                + "\n"
                + ""));
    }

    @Test
    public void readThrowsExceptionIfWidthValueMissing() throws Exception {
        thrown.expect(GraphReaderException.class);
        thrown.expectMessage("Invalid header: expected \"width x\"");
        GraphReader.read(new StringReader(""
                + "type octile\n"
                + "height 3\n"
                + "width\n"
                + "map\n"
                + "..........\n"
                + "..........\n"
                + ".........."));
    }

    @Test
    public void readThrowsExceptionIfInvalidHeaderLineMap() throws Exception {
        thrown.expect(GraphReaderException.class);
        thrown.expectMessage("Invalid header: expected \"map\"");
        GraphReader.read(new StringReader(""
                + "type octile\n"
                + "height 3\n"
                + "width 10\n"
                + "invalid\n"
                + "..........\n"
                + "..........\n"
                + ".........."));
    }

    @Test
    public void readThrowsExceptionIfMissingRows() throws Exception {
        thrown.expect(GraphReaderException.class);
        thrown.expectMessage("Fewer rows than specified in the header");
        GraphReader.read(new StringReader(""
                + "type octile\n"
                + "height 3\n"
                + "width 10\n"
                + "map\n"
                + "..........\n"
                + ".........."));
    }

    @Test
    public void readThrowsExceptionIfNoDataRows() throws Exception {
        thrown.expect(GraphReaderException.class);
        thrown.expectMessage("Fewer rows than specified in the header");
        GraphReader.read(new StringReader(""
                + "type octile\n"
                + "height 3\n"
                + "width 10\n"
                + "map"));
    }

    @Test
    public void readThrowsExceptionIfTooManyRows() throws Exception {
        thrown.expect(GraphReaderException.class);
        thrown.expectMessage("More rows than specified in the header");
        GraphReader.read(new StringReader(""
                + "type octile\n"
                + "height 3\n"
                + "width 10\n"
                + "map\n"
                + "..........\n"
                + "..........\n"
                + "..........\n"
                + ".........."));
    }

    @Test
    public void readThrowsExceptionIfMissingColumns() throws Exception {
        thrown.expect(GraphReaderException.class);
        thrown.expectMessage("Length of row y=0 doesn't match the header");
        GraphReader.read(new StringReader(""
                + "type octile\n"
                + "height 3\n"
                + "width 10\n"
                + "map\n"
                + ".........\n"
                + "..........\n"
                + ".........."));
    }

    @Test
    public void readThrowsExceptionIfEmptyRow() throws Exception {
        thrown.expect(GraphReaderException.class);
        thrown.expectMessage("Length of row y=0 doesn't match the header");
        GraphReader.read(new StringReader(""
                + "type octile\n"
                + "height 3\n"
                + "width 10\n"
                + "map\n"
                + "\n"
                + "..........\n"
                + ".........."));
    }

    @Test
    public void readThrowsExceptionIfTooManyColumns() throws Exception {
        thrown.expect(GraphReaderException.class);
        thrown.expectMessage("Length of row y=2 doesn't match the header");
        GraphReader.read(new StringReader(""
                + "type octile\n"
                + "height 3\n"
                + "width 10\n"
                + "map\n"
                + "..........\n"
                + "..........\n"
                + "..........."));
    }

    @Test
    public void getCoverageForEmptyDefaultConstructorOfStaticClass() {
        GraphReader reader = new GraphReader();
    }

}
