# Week 1

[Hour reporting](Hour_reporting.md)

## What has happened before week 1

I started this project in advance. At this point I've written 50 commits and around 1 000 lines. Most of this happened in May. I've implemented some algorithms (BFS, Dijkstra and A*) and most of the GUI. The code has Javadoc comments and a test coverage of 96%.

## What happened during week 1

In this week I wrote the design document and did some preliminary research on more complex algorithms (D* and JPS). This took a few days as I wrote the document in pieces. No code was written during this week.

## Problems

I hadn't really thought about a benchmarking mode before. I added it to the design document but at this point it's pretty loosely specified. The movingai-site has pretty good material though.

## Questions

Do I need to implement a benchmarking mode to obtain a grade in the range 4-5? It seems to me that it's needed in order to make a meaningful comparison of the algorithms.

Is it sufficient if all edges have uniform weights? This means that all horizontal and vertical edges have weight 1, and all diagonal edges have weight sqrt(2). Since JPS assumes uniform weights this seems to be OK.

Should corner-cutting be prohibited? Allowing it probably makes generating diagonal edges a lot easier, so I think it should be allowed.

## Next week

I'll study more about JPS, and possibly D*. I'll start to implement JPS but it might take a few weeks. In addition, there are some tasks relating to the existing codebase. For instance, at this point the program doesn't support diagonal edges.
