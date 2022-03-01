My solution is as follows:

I will create N number of threads, and then do something....

I first create a "Minotaur" thread to control which threads enter the maze.
It will throw random numbers in to a variable that the other threads are watching,
  the range of these random numbers being equal to the number of threads.

All threads will busy wait while they wait for their number to be called,
  or until a thread throws the success flag, signaling that everyone has visited.

The way the threads throw the success flag is by counting the number of times that they visit the maze.
Since there is already a cupcake in the maze

Once a thread's number is called, it will snap out of busy wait and enter the maze.
If it sees the cupcake there, it will eat it

