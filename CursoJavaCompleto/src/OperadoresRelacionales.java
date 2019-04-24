/**
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 */

/**
 * 
 * 
 */
public class OperadoresRelacionales {

	 int valor1 = 1;
     int valor2 = 2;
    
	public OperadoresRelacionales() {
		 if(valor1 == valor2)
	         System.out.println("valor1 == valor2");
	     if(valor1 != valor2)
	         System.out.println("valor1 != valor2");
	     if(valor1 > valor2)
	         System.out.println("valor1 > valor2");
	     if(valor1 < valor2)
	         System.out.println("valor1 < valor2");
	     if(valor1 <= valor2)
	         System.out.println("valor1 <= valor2");
	}
	
	public static void main(String[] args) {
		new OperadoresRelacionales();
	}

}
