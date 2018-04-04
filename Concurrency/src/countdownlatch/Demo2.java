package countdownlatch;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Demo2 {
	public void testSomeProcessing() throws Exception {
		   // should be called twice
		   final CountDownLatch testLatch = new CountDownLatch(2);
		   ExecutorService executor = Executors.newFixedThreadPool(1);
		   AsyncProcessor processor = new AsyncProcessor(new Observer() {
		      // this observer would be the analogue for a listener in your async process
		      public void update(Observable o, Object arg) {
		         System.out.println("Counting down...");
		         testLatch.countDown();
		      }
		   });

		   //submit two tasks to be process
		   // (in my real world example, these were JMS messages)
		   executor.submit(processor);
		   executor.submit(processor);

		   System.out.println("Submitted tasks. Time to wait...");
		   long time = System.currentTimeMillis();
		   testLatch.await(5000, TimeUnit.MILLISECONDS); // bail after a reasonable time
		   long totalTime = System.currentTimeMillis() - time;

		   System.out.println("I awaited for " + totalTime +
		                      "ms. Did latch count down? " + (testLatch.getCount() == 0));

		   executor.shutdown();
		}

		// just a process that takes a random amount of time
		// (up to 2 seconds) and calls its listener
		public class AsyncProcessor implements Callable<Object> {
		      private Observer listener;
		      private AsyncProcessor(Observer listener) {
		      this.listener = listener;
		   }

		   public Object call() throws Exception {
		      // some processing here which can take all kinds of time...
		      int sleepTime = new Random().nextInt(2000);
		      System.out.println("Sleeping for " + sleepTime + "ms");
		      Thread.sleep(sleepTime);
		      listener.update(null, null); // not standard usage, but good for a demo
		      return null;
		   }
		}

	public void testWaitNotify() throws Exception {
		   final CountDownLatch latch = new CountDownLatch(1); // just one time
		   Thread t = new Thread() {
		      public void run() {
		         // no lock to acquire!
		         System.out.println("Going to count down...");
		         latch.countDown();
		      }
		   };

		   t.start(); // start her up and let her wait()
		   System.out.println("Going to await...");
		   latch.await();
		   System.out.println("Done waiting!");
		}


	public static void main(String[] args) throws Exception{
		
		new Demo2().testWaitNotify();
	}

}
