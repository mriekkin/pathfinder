# Week 5

[Hour reporting](Hour_reporting.md)

## What happened during week 5

In this week I worked on performance testing.

I wrote scripts to run the benchmarks and process the results. The benchmarks can be run by executing the command ```./benchmark``` in the project's root directory. Once the computations are complete the results can be analyzed with Matlab/Octave scripts, which are located in the directory ```results/```.

I ran the benchmarks overnight, 1 problem set per day. The DA2 problem set was relatively fast, but the DAO and BG512 sets took around 8 hours each to compute. On the other hand, the SC1 problem set is as of yet unfinished because one night was not enough to finish the computations. In other words, I have preliminary results for 3 problem sets. I guess I'll have to repeat the computations once I'll have my own data structures in place.

I studied the results and wrote about performance testing to the implementation document. At this point I have plenty of graphs and some text explaining the results. I'll continue the writing process next week.

Finally, there was a peer review. I reviewed another student's project on the same topic.

## Problems

See the questions below.

## Questions

Do my [results](https://github.com/mriekkin/pathfinder/blob/master/doc/Implementation_document.md#aggregated-results) seem sensible?

- One undesirable feature in the results are "jumps", although I think their presence can be explained. I think they can be explained as follows:
  - Some individual scenarios are much faster/slower than the average. The difference in running times is large enough to skew the average downwards/upwards. When such a scenario ends (the scenarios have different lengths), the average shifts abruptly in the opposite direction. This can be seen by plotting the running times for individual scenarios.
  - The original JPS paper shows no "jumps". This seems troublesome. However, I think they might be using only a subset of all the maps in the problem set.
- The speedup for JPS is modest compared to the up to 20x improvement in the original JPS paper. I can come up with a couple of possible reasons for this:
  - In that original paper corner-cutting is allowed. This probably increases performance a little bit because corner-cutting allows for stronger diagonal pruning rules.
  - Furthermore, I think my implementation could use some optimization in regard to memory access. Storing the frequently accessed "walkability" values into an array of node objects was probably a bad idea. Instead they should be stored as a boolean array (or even a BitSet). I think an array of primitives would be much better in regard to CPU cache utilization. The current version accesses a large number of individual node objects, which could be scattered around the heap.

## Next week

The most important job for next week is to implement the remaining data structures. I'll need to implement a binary min heap, a stack and one simple sorting algorithm. Once that's complete I'll work on documentation and, if time permits, work on some pending tasks on the programming side. Finally, there's another peer review.
