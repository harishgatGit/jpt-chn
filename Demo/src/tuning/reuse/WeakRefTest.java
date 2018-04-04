package tuning.reuse;

import java.util.*;
import java.lang.ref.*;

public class WeakRefTest
{
  public static void main(String[] args)
  {
    try
    {
      Reference ref1 = new WeakReference(new Integer(5));
      Reference ref2 = new SoftReference(new Integer(6));
      System.out.println(ref1 + " : " + ref1.get());
      System.out.println(ref2 + " : " + ref2.get());

	  
      int REPEAT = args.length > 0 ? Integer.parseInt(args[0]) : 10000000;
     
      String ic;
      for (int i = 0; i < 10000000; i++)
        ic = new String(i+"");
     // System.gc();
      System.out.println(ref1 + " : " + ref1.get());
      System.out.println(ref2 + " : " + ref2.get());
    }
    catch(Exception e){e.printStackTrace();}
  }

}
