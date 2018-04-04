package countdownlatch;

public class Demo1 {
	public void testWaitNotify() throws Exception {
		   final Object mutex = new Object();
		   Thread t = new Thread() {
		      public void run() {
		      // we must acquire the lock before waiting to be notified
		      synchronized(mutex) {
		         System.out.println("Going to wait " +
		                            "(lock held by " + Thread.currentThread().getName() + ")");
		            try {
		               mutex.wait(); // this will release the lock to be notified (optional timeout can be supplied)
		            } catch (InterruptedException e) {
		               e.printStackTrace();
		            } 

		            System.out.println("Done waiting " +
		                               "(lock held by " + Thread.currentThread().getName() + ")");
		         }
		      }
		   };

		   t.start(); // start her up and let her wait()

		   // not normally how we do things, but good enough for demonstration purposes
		   Thread.sleep(1000);

		   // we acquire the lock released by wait(), and notify()
		   synchronized (mutex) {
		      System.out.println("Going to notify " +
		                         "(lock held by " + Thread.currentThread().getName() + ")");
		      mutex.notify();
		      System.out.println("Done notify " +
		                         "(lock held by " + Thread.currentThread().getName() + ")");
		   }

		}

	public static void main(String[] args) throws Exception {
		
		new Demo1().testWaitNotify();
	}

}
