# Week 2

[Hour reporting](Hour_reporting.md)

## What happened during week 2

In this week I studied and implemented Jump point search (JPS).

Before delving into Jump point search there were a few tasks which needed attention. First, I revised and reviewed A*. I'm glad I did because it turned out that my implementation had a small bug. Second, I implemented diagonal movement on top of the existing codebase. This was necessary because Jump point search assumes that diagonal movement is possible.

At the same time I was reading about JPS. I used these two articles ([1](https://harablog.wordpress.com/2011/09/07/jump-point-search/) and [2](https://zerowidth.com/2013/05/05/jump-point-search-explained.html)) to get started, and then read the [paper](https://www.aaai.org/ocs/index.php/AAAI/AAAI11/paper/download/3761/4007) by Harabor and Grastien.

After reading up, I moved on to implementation. The original paper divides the algorithm into two parts: algorithms 1 (identify successors) and 2 (jumping). I implemented them in that order, and tested 1 before moving on to 2. To be more precise, I started with neighbour pruning rules, which are an important part of identifying the successors the current node. This also turned out to be, for me, the most difficult part of the implementation.

So, first, I implemented neighbour pruning rules. The pruning rules could be implemented independently (just return the pruned neighbours and otherwise do a normal A*). That simplified things as I could run the partial algorithm and see if it made any sense. At this point I wrote a reasonably thorough set of unit tests for the neighbour pruning rules.

Second, I implemented jumping. To begin with I implemented the first few lines and checked that it worked at that point. Then I just wrote the rest the jump method. The description of algorithm 2 is quite detailed which makes implementing it relatively easy (compared to algorithm 1) even though algorithm 2 is a lot longer. That said, the first version did have a bug which made the algorithm fail on some maps while succeeding on others. Curiously enough, visual inspection suggested that the algorithm seemed to have problem with turning left - while turning right was OK. This hint was enough to locate the problem which was a flawed definition of the vectors d_1 and d_2.

Third, I updated the for loop inside the run method. At this point I had a first version of Jump point search up and running. I tested it with a bunch of maps and it seems to work. I wrote a revised version the next day, along with a few simple test cases, but I'll continue with more thorough testing next week.

## Problems

There were some problems which I believe were solved. The JPS paper by Harabor and Grastien is reasonably understandable but omits some implementation details. In particular, at least at first, I found the neighbour pruning rules quite difficult to implement. For instance, I wasn't sure whether forced neighbours should be handled at this point. They should be, and that creates quite a few cases which need to checked. This [article](https://gamedevelopment.tutsplus.com/tutorials/how-to-speed-up-a-pathfinding-with-the-jump-point-search-algorithm--gamedev-5818), although a bit troubled, pointed me in the right direction. The solution is to just check each neighbour one-by-one using a large set of if-statements. I had hoped for a more elegant solution, but the current solution works.

## Questions

In the class pathfinder.logic.pathfinders.NeighbourPruningRules I have a few pretty long methods which consist of a set of if-statements. In this case I hope its OK.

## Next week

I'll begin work on the benchmarking mode. This involves studying the material at [movingai.com/benchmarks](https://www.movingai.com/benchmarks/). In addition, I'll continue with more thorough testing of the JPS implementation.
