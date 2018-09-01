# Results data file format

Benchmark mode creates a set of files under the subdirectory ```results/data```. These files are organized according to a timestamp and the originating problem set. The data are a set of CSV files where each file corresponds to a single scenario. For instance, the DAO problem set has 156 maps (and the same number of scenarios), so the results are a set of 156 CSV files.

Each file looks something like this

```
0	0.654	0.178	0.436	3.00000000	3.00000000	3.00000000
0	0.121	0.065	0.283	2.41421356	2.41421356	2.41421356
0	0.059	0.033	0.131	2.00000000	2.00000000	2.00000000
0	0.082	0.031	0.198	1.41421356	1.41421356	1.41421356
0	0.191	0.073	0.268	3.82842712	3.82842712	3.82842712
```

The columns are, from left to right,

1. bucket
2. time_{Dijkstra}
3. time_{A*}
4. time_{JPS}
5. dist_{Dijkstra}
6. dist_{A*}
7. dist_{JPS}

The times are in milliseconds (ms). The distances are such that, between neighbouring cells, cardinal movement costs 1, and diagonal movement costs sqrt(2).
