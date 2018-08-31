# Week 6

[Hour reporting](Hour_reporting.md)

## What happened during week 6

In this week I implemented my own data structures and wrote a testing document.

1. I implemented the remaining data structures. First, I implemented a binary min heap and a stack. Second, I implemented a simple sorting algorithm (insertion sort) for the purpose of computing the median of small arrays. Third, I also made my ArrayList and other collections iterable. All in all the process was relatively smooth.
2. I wrote a testing document. It took me two days and I'm quite happy with the result. I think it's pretty comprehensive (probably more so than would be necessary).
3. There was also a peer review. I reviewed another student's project on the same topic.

## Problems

I also fixed a couple of bugs with the GUI. I'll have to admit that Swing is still giving me a little bit of trouble. These problems have to do with recomputing the layout and painting.

1. One problem appears when the GUI resizes to a much bigger size. This happens, for instance, when you load one of the larger maps from the problem sets. What might happen is that the window resizes to a size which is much bigger than the size of the screen.
2. Another problem occurs when the GUI resizes to a much smaller size. In this case the JScrollPane might not repaint the previously occupied area of the background.

Curiously, but luckily, these problems disappeared when I tried the program on a Windows machine. So, some of these problems might be Mac-specific.

## Questions

I guess that a few problems with the GUI are, to a degree, somewhat acceptable.

## Next week

I'll recompute my results and update the implementation document accordingly. I'll also write a user's guide. If time permits, there are a few programming tasks to finish.
