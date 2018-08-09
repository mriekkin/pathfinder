package pathfinder.benchmark;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import pathfinder.io.ScenarioFileException;
import pathfinder.io.ScenarioReader;

public class Benchmark {

    public static final int REPLICATES = 10;

    private List<Experiment> experiments;
    private Path mapDirectory;

    public Benchmark(String scenarioFile) {
        loadScenario(scenarioFile);
    }

    private void loadScenario(String file) {
        try {
            Path scenarioFile = Paths.get(file);
            experiments = new ScenarioReader().read(scenarioFile);
            mapDirectory = scenarioFile.getParent();
        } catch (ScenarioFileException e) {
            System.out.println("Cannot load scenario");
            System.out.println("   " + e);
            if (e.getCause() != null) {
                System.out.println("   " + e.getCause());
            }
            System.out.println("   " + e.getLine());
        } catch (IOException e) {
            System.out.println("Cannot load scenario");
            System.out.println("   " + e);
        }
    }

    public void run() {
        if (experiments == null || experiments.isEmpty()) {
            return;
        }

        try {
            RunExperiments runner = new RunExperiments(experiments, REPLICATES, mapDirectory);
            runner.run();
        } catch (IOException e) {
            System.out.println("Cannot run scenario");
            System.out.println("   " + e);
        }
    }

}
