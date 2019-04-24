/**
 * @(#)DemoScanner.java
 *
 *
 * @author 
 * @version 1.00 2010/4/25
 */
import java.util.Scanner;

public class DemoScanner {

    

public DemoScanner(){
	
	 Scanner scaner =new Scanner(System.in);
	 System.out.println("cual es tu nombre?:");
	 String nombre=scaner.nextLine();
	 
	 System.out.println("Dame el primer numero a sumar");
	 int a = scaner.nextInt();
	 System.out.println("Dame el segundo numero a sumar");
	 int b = scaner.nextInt();
	 int resultado = a + b;
	 System.out.println("El resultado de " + a + " + "+ b + " es " + resultado);
	 scaner.close();
 	
 	System.out.println("Mucho gusto " + nombre + " yo soy java");
}

public static void main(String[] args){

	new DemoScanner();

}
    
    
}