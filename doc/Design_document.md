# Design document

Pathfinder is a collection of shortest path algorithms for grid-based maps. It can be used in a visualization mode to illustrate the application of individual algorithms, or in a benchmarking mode to compare the performance of different algorithms.

## Algorithms and data structures

The intent is to implement a few shortest path algorithms. The basic algorithms to be implemented include Dijkstra and A*. These are well documented in the course notes and a number of other sources. In addition, one more complex algorithm needs to be implemented. The alternatives include D* and Jump point search (JPS). Of these two, Jump point search seems more promising. It provides a significant speedup compared to standard A*, and is geared towards game-like maps. Based on a brief reading, D* seems more optimized towards applications where the pathfinding algorithm is invoked repeatedly. Also, Jump point search seems to have more documentation available.

The running time of Dijkstra and A* is O((E + V) log V). This is true when the priority queue is implemented using a binary heap. Since Jump point search is an optimized version of A* it probably has the same worst-case running time. In practice, Dijkstra is slower than A*, and A* is slower than JPS. Jump point search can reduce the running time of A* by an order of magnitude.

The data structures required are binary heaps and dynamic arrays.

## Description of the program



## Inputs and outputs
