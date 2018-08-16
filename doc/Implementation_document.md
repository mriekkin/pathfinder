# Implementation document

Pathfinder is a collection of shortest path algorithms for grid-based maps. It can be used in a visualization mode to illustrate the application of individual algorithms, or in a benchmark mode to compare the performance of different algorithms.

## Program structure

Central abstractions for this program include the class Graph and the interface Pathfinder.

Pathfinder is a common interface for shortest path algorithms. It defines the contract between different algorithms and the rest of the program. Pathfinder is implemented by AbstractPathfinder. It provides a skeletal implementation of the Pathfinder interface to minimize the effort required to implement this interface. AbstractPathfinder defines member variables and methods that are shared by all algorithms. Each subclass that extends AbstractPathfinder represents one algorithm for finding a shortest path between two nodes.

In visualization mode, CurrentGraph represents the graph which is currently being edited in this application. It holds a reference to the graph which is of current interest to the user. This is an example of a model suitable for applications which edit only one item at a time. The graphs represented in this program are 2-dimensional uniform-cost grids. Hence the class Graph is implemented as a 2-dimensional array of nodes. Nodes are simple coordinate pairs which store a boolean value indicating whether that node is walkable. Edges are not stored but inferred at computation time.

The diagram below represents a simplified class diagram for the visualization mode. It represents only those classes which are relevant to program logic. Additional GUI specific classes are shown.

![Class diagram for the visualization mode](img/gui_structure.png)

The diagram below represents a simplified class diagram for the benchmark mode.

![Class diagram for the benchmark mode](img/benchmark_structure.png)

![Class diagram for RunExperiment](img/runexperiment_structure.png)
