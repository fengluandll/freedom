/**
 * @(#)DemoDoWhile.java
 *
 *
 * @author 
 * @version 1.00 2010/4/24
 */


public class DemoDoWhile {

    public DemoDoWhile() {
    }
    
    public static void main(String[] args) {
    	int i = 5;
        do{
            i --;
            System.out.println(i);
            if (i == 3) 
            	break;
        }
        while(i > 0);
            // En este ejemplo cuando i tenga el valor 3
// se abandonará el bucle.


    }
    
}