package mx.javaonline.arreglos;

import java.util.Scanner;

public class TablasMultiplicar {

	public TablasMultiplicar() {
		int[] tablas = {1,2,3,4,5,6,7,8,9,10};
		
		Scanner scaner = new Scanner(System.in);
		System.out.println("¿Que tabla quieres multiplicar?");
		int num = scaner.nextInt();
		for(int i = 0; i<tablas.length;i++){
			System.out.println(num + " X "+ tablas[i] + " = " + num * tablas[i]);
			
		}
		scaner.close();
	}

	public static void main(String[] args) {
		new TablasMultiplicar();
	}

}


