/**
 * 
 * @author Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 */
public class OperadoresAritmeticos {

	public OperadoresAritmeticos() {
		// resultado es 3
        int resultado = 1 + 2;
        System.out.println(resultado);
 
        // resultado es 2
        resultado = resultado - 1;
        System.out.println(resultado);
 
        // resultado es 4
        resultado = resultado * 2;
        System.out.println(resultado);
 
        // resultado is now 2
        resultado = resultado / 2;
        System.out.println(resultado);
 
        // resultado is now 10
        resultado = resultado + 8;
        // resultado is now 3
        resultado = resultado % 7;
        System.out.println(resultado);
	}
	
	public static void main(String[] args) {
		new OperadoresAritmeticos();
	}

}
