package semaphores;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Semaphore;

public class ConnectionLimiter {
   private final Semaphore semaphore;
 
   private ConnectionLimiter(int maxConcurrentRequests) {

 semaphore = new Semaphore(maxConcurrentRequests);
   }
 
   public URLConnection acquire(URL url) throws InterruptedException,








IOException {

 semaphore.acquire();

 return url.openConnection();
   }
 
   public void release(URLConnection conn) {

 try {

     /*

     * ... clean up here

     */

 } finally {

     semaphore.release();

 }
   }
}
