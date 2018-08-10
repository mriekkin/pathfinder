package pathfinder.benchmark;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import pathfinder.io.ScenarioFileException;
import pathfinder.io.ScenarioReader;

public class Benchmark {

    public static final int REPLICATES = 10;

    private List<Experiment> experiments;
    private Path mapDirectory;
    private Timer timer;
    private PrintStream out;

    public Benchmark(String scenarioFile, OutputStream out) {
        this.out = new PrintStream(out);
        this.timer = new Timer();
        loadScenario(scenarioFile);
    }

    private void loadScenario(String file) {
        experiments = new ArrayList<>(); // Avoid a possible null pointer

        try {
            Path scenarioFile = Paths.get(file);
            experiments = new ScenarioReader().read(scenarioFile);
            mapDirectory = scenarioFile.getParent();
        } catch (ScenarioFileException e) {
            out.println("Cannot load scenario");
            out.println("   " + e);
            if (e.getCause() != null) {
                out.println("   " + e.getCause());
            }
            out.println("   " + e.getLine());
        } catch (IOException e) {
            out.println("Cannot load scenario");
            out.println("   " + e);
        }
    }

    public void run() {
        if (experiments.isEmpty()) {
            return;
        }

        try {
            RunScenario runner = new RunScenario(experiments, REPLICATES,
                    mapDirectory, timer, out);

            runner.run();
        } catch (IOException e) {
            out.println("Cannot run scenario");
            out.println("   " + e);
        }
    }

}
