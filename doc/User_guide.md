# User guide

## Obtaining a copy of the repository

You can clone the repository with the following command, which should be entered on the command-line.

```bash
git clone https://github.com/mriekkin/pathfinder.git
```

This might take a while because the repository is rather large, around 180 MB. Most of this size is due to a large number of data files. Then change the current directory to the projects root directory.

```bash
cd pathfinder
```

All subsequent commands should be entered in the project's root directory.

## Visualization mode

The visualization mode can be run by executing the command

```bash
gradle run
```

Alternatively, you may create a JAR file and execute it with the commands

```bash
gradle jar
java -jar build/libs/pathfinder.jar
```

When the program opens it displays a pre-loaded map.

**Map editing.** Obstacles can be placed and removed with the mouse.

**Creating a new map.** To create a new grid select the menu File >> New. This creates an empty grid, and places the source and destination nodes at the corners.

**Opening a map file.** To open a previously created grid file select the menu File >> Open.

**User preferences.** To edit user preferences select the menu Preferences >> Preferences.

*Modifying the cell size*.

*Enabling/disabling corner-cutting.*

## Benchmark mode

### Running the problem sets

The benchmark mode only supports UNIX-like systems. The benchmark mode employs a shell script which invokes the Java runtime. Since shell scripts only work on UNIX-like systems, the benchmark mode cannot be run on Windows machines. Windows users can still run individual scenarios, as explained below, but they cannot use the benchmark script, which would run the full range of benchmarks.

The benchmark mode can be run with the command

```bash
./benchmark
```

This runs a shell script, which is in the project's root directory. By default, this runs the full range of problem sets. To add or remove problem sets you can modify the script's last few lines, which are

```bash
run_problem_set "da2"
run_problem_set "dao"
run_problem_set "bg512"
run_problem_set "sc1"
```

Running the problem sets is a time consuming process. You should expect each problem set to take several hours to complete. On our test machine the computations for each problem set would take from 2 hours (DA2) to up to 8 hours (DAO and BG512) and up to a day (SC1). The benchmarks are run on a single thread so on a multicore machine the CPU utilization remains below 100&nbsp;%. Regardless, to get reliable results one should not work on other tasks while running the benchmarks. Hence, it's convenient to run the benchmarks overnight.

### Analyzing the results

Benchmark mode creates a set of files under the subdirectory ```results/data```. These files are organized according to a timestamp and the originating problem set. The data are a set of CSV files where each file corresponds to a single scenario. For instance, the DAO problem set has 156 maps (and the same number of scenarios), so the results are a set of 156 CSV files. The [results file format](Results_file_format.md) is described in another document.

We have provided Matlab/Octave scripts for analyzing the results. One may use either Matlab or Octave, but here we use Octave, which is [freely available](https://www.gnu.org/software/octave/). To run the scripts, start Octave and navigate to the subdirectory ```results/```. Then you can issue, in Octave, the following command

```octave
results2
```

which runs the analysis. To change the settings you can modify the top portion of the file ```results2.m```, which looks like this

```octave
TIMESTAMP = "180831";
PROBLEM_SET = "dao";
```

This analyzes the data in the subdirectory ```results/data/180831/dao```. To analyze multiple problem sets you have to run the script multiple times with the appropriate settings.

### Running individual scenarios

If you can run the benchmark mode, then running individual scenarios should not be necessary. However, we describe the process here for completeness.

Individual scenarios can be run with the command

```
java -jar build/libs/pathfinder.jar -b grids/dao/lak100d.map.scen
```

Here ```-b``` refers to the benchmark mode. Without it the application will start in the visualization mode. The last parameter specifies the scenario file to be used. This command will print out a large table of [data](Results_file_format.md). To save the results the output can be piped to a file. In fact, this is the approach used by the benchmark script.
