package com.movemini.debug;
import com.movemini.config.HardCodeConstants;

public class DebugTools{

      public static void print(Object obj){
          if(HardCodeConstants.debug){
            System.out.print(obj);
          }
      }

      public static void println(Object obj, Class place){
          if(HardCodeConstants.debug){
            System.out.print("[debug-log] - ");
            System.out.println(place);
            System.out.println();
            System.out.println(obj);
            System.out.println();
          }
      }
}
