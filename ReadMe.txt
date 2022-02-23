My solution is as follows:

I will create N number of threads, and then do something....

I first create a "Minotaur" thread to control which threads enter the maze.
It will throw random numbers in to a variable that the other threads are watching,
  the range of these random numbers being equal to the number of threads.
Once a thread sees its number pop up, it enters a queue to gain the lock to allow it into the "maze".
Once it gets through the queue, it will acess the lock itself and check if the cupcake is there. If it is not there,
  it may choose to put it there and then either eat it or not.
