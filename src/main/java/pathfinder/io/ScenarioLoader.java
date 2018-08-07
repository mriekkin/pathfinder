package pathfinder.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import pathfinder.benchmark.Experiment;

public class ScenarioLoader {

    public static List<Experiment> load(Path path) throws IOException {
        List<Experiment> experiments = new ArrayList<>();
        Charset charset = Charset.forName("US-ASCII");
        try (BufferedReader reader = Files.newBufferedReader(path, charset)) {
            if (!readVersionNumber(reader, experiments)) {
                return experiments;
            }

            readLines(reader, experiments);
        }

        return experiments;
    }

    private static boolean readVersionNumber(final BufferedReader reader, List<Experiment> experiments) throws IOException {
        String firstLine = reader.readLine().toLowerCase();
        if (!firstLine.equals("version 1")) {
            System.out.println("Invalid version number: " + firstLine);
            return false;
        }

        return true;
    }

    private static void readLines(final BufferedReader reader, List<Experiment> experiments) throws IOException {
        String line;
        int count = 0;
        while ((line = reader.readLine()) != null) {
            if (!parseLine(count + 1, line, experiments)) {
                return;
            }

            count++;
        }
    }

    private static boolean parseLine(int lineNum, String line, List<Experiment> experiments) throws NumberFormatException {
        String[] columns = line.split("\t");

        if (columns.length < 9) {
            System.out.println("Missing column(s) on line " + lineNum + ": ");
            System.out.println("   " + line);
            return false;
        }

        parse(lineNum, line, columns, experiments);

        return true;
    }

    private static void parse(int lineNum, String line, String[] columns, List<Experiment> experiments) {
        try {
            int bucket = Integer.parseInt(columns[0]);
            String map = columns[1];
            int sizeX = Integer.parseInt(columns[2]);
            int sizeY = Integer.parseInt(columns[3]);
            int sourceX = Integer.parseInt(columns[4]);
            int sourceY = Integer.parseInt(columns[5]);
            int destX = Integer.parseInt(columns[6]);
            int destY = Integer.parseInt(columns[7]);
            double dist = Double.parseDouble(columns[8]);

            Experiment e = new Experiment(bucket, map, sizeX, sizeY,
                    sourceX, sourceY, destX, destY, dist);
            experiments.add(e);
        } catch (NumberFormatException e) {
            System.out.println("Error parsing line " + lineNum + ": ");
            System.out.println("   " + e);
            System.out.println("   " + line);
        }
    }

}
