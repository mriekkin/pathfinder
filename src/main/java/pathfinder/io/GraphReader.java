package pathfinder.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import pathfinder.logic.Graph;
import pathfinder.logic.Node;
import pathfinder.logic.Pair;

public class GraphReader {

    public static Graph readFile(Path path) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            return read(reader);
        }
    }

    public static Graph read(Reader r) throws IOException {
        try (BufferedReader reader = new BufferedReader(r)) {
            Pair dimensions = readHeader(reader);
            Pair tmp = new Pair(0, 0);
            Graph g = new Graph(dimensions, tmp, tmp);

            String nodes;
            int row = 0;
            while ((nodes = reader.readLine()) != null) {
                processRow(g, row, nodes);
                row++;
            }

            return g;
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

    private static Pair readHeader(BufferedReader reader) throws IOException {
        String line1 = reader.readLine();
        String line2 = reader.readLine();
        String line3 = reader.readLine();
        String line4 = reader.readLine();

        int height = Integer.parseInt(line2.split(" ")[1]);
        int width = Integer.parseInt(line3.split(" ")[1]);

        return new Pair(width, height);
    }

}
