/**
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 */

/**
 * 
 * 
 */
public class OperadoresCondicionales {

	public OperadoresCondicionales() {
		int valor1 = 1;
        int valor2 = 2;
        if((valor1 == 1) && (valor2 == 2))
            System.out.println("valor1 es 1 AND valor2 es 2");
        if((valor1 == 1) || (valor2 == 1))
            System.out.println("valor1 es 1 OR valor2 es 1");
	}

	public static void main(String[] args) {
		new OperadoresCondicionales();
	}
}
