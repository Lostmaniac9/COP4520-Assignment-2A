import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicBoolean;

// Minotaur randomly selects guests to enter one at a time,
// using a flag to know when to wait before sending in another guest
class Minotaur implements Runnable
{
  private int threadCount;
  private AtomicInteger guestInMaze;
  private AtomicBoolean success;
  
  public Minotaur(int threadCount, AtomicInteger guestInMaze, AtomicBoolean success)
  {
    this.threadCount = threadCount;
    this.guestInMaze = guestInMaze;
    this.success = success;
  }

  public void run()
  {
    try
    {
      while (success.toString() == "false")
      {
        // send in a guest
        guestInMaze.set(ThreadLocalRandom.current().nextInt(1, threadCount + 1));
        // busywait while waiting for a guest to exit the maze
        while (guestInMaze.intValue() != 0) {}
      }
    }
    catch (Exception e)
    {
      System.out.println("Exception has occured" + e);
    }
    
    return;
  }
}

// a guest will take a cupcake if they haven't had one,
// if one isn't there and they haven't had one, summon one and eat it
// each guest will count how many times they enter the maze and don't see a cupcake
// once a single guest counts n-1 times, with n being the thread count, declare victory 
class Guest implements Runnable
{
  private int threadCount;
  private int id;
  private AtomicInteger guestInMaze;
  private AtomicBoolean success;
  private AtomicBoolean cupcake;
  
  public Guest(int threadCount, int id, AtomicInteger guestInMaze, AtomicBoolean success, AtomicBoolean cupcake)
  {
    this.threadCount = threadCount;
    this.id = id;
    this.guestInMaze = guestInMaze;
    this.success = success;
    this.cupcake = cupcake;
  }

  public void run()
  {
    try
    {
      boolean eaten = false;
      int guestCounter = 1;
      while (success.toString() == "false")
      {
        // busywait while guest's id is not called
        // checking for success flag is so guest threads don't get lost waiting for a number after minotaur has quit
        while (guestInMaze.intValue() != id && success.toString() == "false") {}
        
        // exit immediately if success flag is thrown
        if (success.toString() == "true")
        {
          return;
        }
        
        // once their id is called, check cupcake status
        // if the cupcake is there and the guest hasn't eaten, eat it and leave
        if (cupcake.toString() == "true" && eaten == false)
        {
          cupcake.set(false);
          eaten = true;
        }
        // is the cupcake is there and the guest has eaten, count up and leave
        else if (cupcake.toString() == "true" && eaten == true)
        {
          guestCounter++;
        }
        // if the cupcake isn't there and the guest hasn't eaten, summon a cupcake, eat it, and leave
        else if (cupcake.toString() == "false" && eaten == false)
        {
          eaten = true;
        }
        // if the cupcake isn't there and the guest has eaten, leave a cupcake, reset guestCounter and leave
        else if (cupcake.toString() == "false" && eaten == true)
        {
          cupcake.set(true);
          guestCounter = 1;
        }
        
        // declare success if the number of guests to be counted is met
        if (guestCounter == threadCount)
        {
          success.set(true);
          System.out.println("Everyone has visited!");
        }
        
        // otherwise, reset value to allow another guest in
        guestInMaze.set(0);
      }
    }
    catch (Exception e)
    {
      System.out.println("Exception has occured" + e);
    }
    
    return;
  }
}

// the leader will do nothing lmao
class Leader implements Runnable
{

  public void run()
  {
    try
    {
      
    }
    catch (Exception e)
    {
      System.out.println("Exception has occured" + e);
    }
    
    return;
  }
}

class Party 
{
  public static void main(String args[])
  {
    int threadCount = 20;
    AtomicInteger guestInMaze = new AtomicInteger(0);
    AtomicBoolean success = new AtomicBoolean(false);
    AtomicBoolean cupcake = new AtomicBoolean(true);
    
    try
    {
      // start up all of the threads
      // I tried doing this is a neat loop but it wasn't working so here we are
      Thread minotaur = new Thread(new Minotaur(threadCount, guestInMaze, success));
      Thread guest1 = new Thread(new Guest(threadCount, 1, guestInMaze, success, cupcake));
      Thread guest2 = new Thread(new Guest(threadCount, 2, guestInMaze, success, cupcake));
      Thread guest3 = new Thread(new Guest(threadCount, 3, guestInMaze, success, cupcake));
      Thread guest4 = new Thread(new Guest(threadCount, 4, guestInMaze, success, cupcake));
      Thread guest5 = new Thread(new Guest(threadCount, 5, guestInMaze, success, cupcake));
      Thread guest6 = new Thread(new Guest(threadCount, 6, guestInMaze, success, cupcake));
      Thread guest7 = new Thread(new Guest(threadCount, 7, guestInMaze, success, cupcake));
      Thread guest8 = new Thread(new Guest(threadCount, 8, guestInMaze, success, cupcake));
      Thread guest9 = new Thread(new Guest(threadCount, 9, guestInMaze, success, cupcake));
      Thread guest10 = new Thread(new Guest(threadCount, 10, guestInMaze, success, cupcake));
      Thread guest11 = new Thread(new Guest(threadCount, 11, guestInMaze, success, cupcake));
      Thread guest12 = new Thread(new Guest(threadCount, 12, guestInMaze, success, cupcake));
      Thread guest13 = new Thread(new Guest(threadCount, 13, guestInMaze, success, cupcake));
      Thread guest14 = new Thread(new Guest(threadCount, 14, guestInMaze, success, cupcake));
      Thread guest15 = new Thread(new Guest(threadCount, 15, guestInMaze, success, cupcake));
      Thread guest16 = new Thread(new Guest(threadCount, 16, guestInMaze, success, cupcake));
      Thread guest17 = new Thread(new Guest(threadCount, 17, guestInMaze, success, cupcake));
      Thread guest18 = new Thread(new Guest(threadCount, 18, guestInMaze, success, cupcake));
      Thread guest19 = new Thread(new Guest(threadCount, 19, guestInMaze, success, cupcake));
      Thread guest20 = new Thread(new Guest(threadCount, 20, guestInMaze, success, cupcake));
      
      
      guest1.start();
      guest2.start();
      guest3.start();
      guest4.start();
      guest5.start();
      guest6.start();
      guest7.start();
      guest8.start();
      guest9.start();
      guest10.start();
      guest11.start();
      guest12.start();
      guest13.start();
      guest14.start();
      guest15.start();
      guest16.start();
      guest17.start();
      guest18.start();
      guest19.start();
      guest20.start();
      
      minotaur.start();
      minotaur.join();
      guest1.join();
      guest2.join();
      guest3.join();
      guest4.join();
      guest5.join();
      guest6.join();
      guest7.join();
      guest8.join();
      guest9.join();
      guest10.join();
      guest11.join();
      guest12.join();
      guest13.join();
      guest14.join();
      guest15.join();
      guest16.join();
      guest17.join();
      guest18.join();
      guest19.join();
      guest20.join();
    }
    catch (Exception e)
    {
      System.out.println("Exception has occured" + e);
    }
    
  }
}
