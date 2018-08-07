package pathfinder.benchmark;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import pathfinder.io.ScenarioLoader;

public class Benchmark {

    public static final int REPLICATES = 10;

    public Benchmark(String[] args) {
        if (args.length < 2) {
            System.out.println("No scenario file specified");
            return;
        }

        String scenario = args[1];

        try {
            List<Experiment> experiments = ScenarioLoader.load(Paths.get(scenario));

            RunExperiments runner = new RunExperiments(experiments, REPLICATES);

            runner.run();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void run() {

    }

}
