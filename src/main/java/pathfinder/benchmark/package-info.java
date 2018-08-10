/**
 * The benchmark mode.
 * <p>
 * Algorithms can be compared by running the same set of problems for a number
 * of algorithms. Hence, the basic idea of the benchmark mode is to run a large
 * number of problems for the same set of algorithms. This means running
 * multiple problems on any single map with different source and destination
 * nodes. This set of source and destination nodes is called a scenario. It also
 * means running the same set of algorithms on a variety of different maps. This
 * means running multiple scenarios and averaging the results.
 * <p>
 * Each problem represents a small experiment. Each experiment should also be
 * replicated a specified number of times to obtain reliable results. This means
 * running the same shortest path problem for multiple times and taking the
 * average of the running times.
 */
package pathfinder.benchmark;
