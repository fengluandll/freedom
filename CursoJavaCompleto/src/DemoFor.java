/**
 * @(#)DemoFor.java
 *
 *
 * @author 
 * @version 1.00 2009/12/19
 */


public class DemoFor {

    public DemoFor() {
    	
    	String[] paises = {"Mexico","Canada","EU"};
    	//for(int i = 0; i < paises.length ; i++){
    	//	System.out.println(paises[i]);
    	//	}
    	
    	for(String pai:paises){
    		System.out.println(pai);
    		}
    }
    
    public static void main(String args[]){
    		new DemoFor();
    	}
    
}