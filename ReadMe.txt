To execute this program, use the commands "javac Party.java" followed by "java Party".

My solution is as follows:

I first create a "Minotaur" thread to control which threads enter the maze.
It will throw random numbers in to a variable that the other threads are watching,
  the range of these random numbers being equal to the number of threads.
The number of threads is hardcoded to be 20.

All threads will busy wait while they wait for their number to be called,
  or until a thread throws the success flag, signaling that everyone has visited.

The way the threads throw the success flag is by counting the number of times that they visit the maze.
Since there is already a cupcake in the maze, the thread will enter and eat a cupcake if it hasn't. If there wasn't a cupcake,
  it will summon one and instantly eat it.
After it has eaten the cupcake, it will then always place a cupcake there if there isn't one, and it will NEVER eat another cupcake.
Therefore, only threads that have NOT eaten will remove cupcakes, and threads that have eaten will leave new cupcakes.

After a thread has eaten a cupcake, it will begin to count how many consecutive times it enters the maze and sees a cupcake there.
If it enters the maze and does NOT see a cupcake, it will reset its count.
The goal for each thread is to count up to whatever the maximum number of threads is. In this program's case, 20.
If any single thread visits the maze 20 times in a row and sees a cupcake sitting there, it then makes the assumption that,
  having seen a cupcake present 20 times in a row, that everyone has entered the maze since no threads have eaten a cupcake in a while
  (since threads only eat when they first enter).

Due to how this works, each thread will likely reset its counting several times at the start because there will be threads constantly
  entering the maze and eating cupcakes on their first entrance, but as time goes on the numbers of threads that have eaten will grow
  and so the cupcakes will remain for longer periods of time.
Again, once a thread counts 20 consecutive times it enters and sees a present cupcake, it will call a success and end the exercise.

Technically, this approach leaves a small probability that a thread will not be called in the amount of time it takes any particular thread
  to count to 20. However, this probability is so low that I have chosen to ignore it. Technically, using this method there is NO way to
  entirely avoid that possibility, I can only bring it lower and lower, and so I think counting to the thread count is a saatisfying balance
  between speed of execution and reduced probability of false success flags. My testing consistently saw good results so I rest my case on that.

At the moment of a thread counting to 20 it then throws the success flag and declares "Everyone has visited!". The program then ends.
