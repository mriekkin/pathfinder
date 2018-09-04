package pathfinder.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import pathfinder.logic.Graph;
import pathfinder.logic.Node;
import pathfinder.logic.Pair;

/**
 * Reads the contents of a map file, and returns the corresponding graph.
 */
public class GraphReader {

    /**
     * Reads the contents of a map file, and returns the corresponding graph.
     *
     * @param path the path of the map file to be read
     * @return a graph created from the specified map file
     * @throws GraphReaderException if the specified map file is invalid
     * @throws IOException if the specified map file cannot be read
     */
    public static Graph readFile(Path path) throws GraphReaderException, IOException {
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            return read(reader);
        }
    }

    /**
     * Reads the contents of a map, using the specified reader, and returns the
     * corresponding graph.
     *
     * @param r the reader to be used
     * @return a graph created from the read map specification
     * @throws GraphReaderException if the map specification is invalid
     * @throws IOException if reading from the specified reader produces an I/O
     * exception
     */
    public static Graph read(Reader r) throws GraphReaderException, IOException {
        try (BufferedReader reader = new BufferedReader(r)) {
            Pair dimensions = readHeader(reader);
            Pair tmp = new Pair(0, 0);
            Graph g = new Graph(dimensions, tmp, tmp);

            String nodes;
            int row = 0;
            while ((nodes = reader.readLine()) != null) {
                checkRow(g, row, nodes);
                processRow(g, row, nodes);
                row++;
            }

            if (row < g.getRows()) {
                throw new GraphReaderException("Fewer rows than specified in the header.");
            }

            return g;
        }
    }

    private static void checkRow(Graph g, int row, String nodes) throws GraphReaderException {
        if (row >= g.getRows()) {
            throw new GraphReaderException("More rows than specified in the header.");
        }

        if (nodes.length() != g.getCols()) {
            throw new GraphReaderException("Length of row y=" + row + " doesn't match the header.");
        }
    }

    private static void processRow(Graph g, int row, String nodes) {
        for (int x = 0; x < nodes.length(); x++) {
            Node node = g.getNode(x, row);
            processNode(g, node, nodes.charAt(x));
        }
    }

    private static void processNode(Graph g, Node node, char c) {
        switch (c) {
            // Passable terrain
            case '.':
            case 'G':
            case 'S': node.setWalkable(true); break;

            // Impassable terrain
            case '#':
            case '@':
            case 'O':
            case 'T':
            case 'W': node.setWalkable(false); break;

            // The source (A) and destination (B) nodes
            case 'A': g.setSource(node); break;
            case 'B': g.setDest(node); break;
        }
    }

    private static Pair readHeader(BufferedReader reader) throws GraphReaderException, IOException {
        String line1 = reader.readLine();
        String line2 = reader.readLine();
        String line3 = reader.readLine();
        String line4 = reader.readLine();

        checkHeader(line1, line2, line3, line4);

        return getDimensions(line2, line3);
    }

    private static Pair getDimensions(String line2, String line3) throws GraphReaderException {
        int height = Integer.parseInt(line2.split(" ")[1]);
        int width = Integer.parseInt(line3.split(" ")[1]);

        if (width <= 0 || height <= 0) {
            throw new GraphReaderException("Invalid header: invalid dimensions");
        }

        return new Pair(width, height);
    }

    private static void checkHeader(String line1, String line2, String line3, String line4) throws GraphReaderException {
        if (line1 == null || line2 == null || line3 == null || line4 == null) {
            throw new GraphReaderException("Invalid header: expected 4 lines");
        }

        if (!line1.equals("type octile")) {
            throw new GraphReaderException("Invalid header: expected \"type octile\"");
        }

        if (!line2.matches("height [0-9]+")) {
            throw new GraphReaderException("Invalid header: expected \"height y\"");
        }

        if (!line3.matches("width [0-9]+")) {
            throw new GraphReaderException("Invalid header: expected \"width x\"");
        }

        if (!line4.equals("map")) {
            throw new GraphReaderException("Invalid header: expected \"map\"");
        }
    }

}
