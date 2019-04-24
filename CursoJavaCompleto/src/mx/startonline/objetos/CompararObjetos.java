package mx.startonline.objetos;

import java.util.Scanner;

/**
@autor Jesus Argumedo
 **/
public class CompararObjetos {

	public CompararObjetos() {
		
		Scanner scaner = new Scanner(System.in);
		System.out.println("Ingresa un nombre");
		String nombre1 = scaner.nextLine();
		System.out.println("Ingresa el nombre 2");
		String nombre2 = scaner.nextLine();
		
		System.out.println("nombre1 " + nombre1);
		System.out.println("nombre2 "+ nombre2);
		if(nombre1.equals(nombre2)){
			System.out.println("Los nombres son iguales");
		}else{
			System.out.println("Los nombres NOOO son iguales");
		}
		/*
		String nombre1 = "karla";
		String nombre2 = "karla";
		if(nombre1 == nombre2){
			System.out.println("Son iguales");
		}else{
			System.out.println("NO Son iguales");
		}
		*/
	}

	
	public static void main(String[] args) {
		new CompararObjetos();

	}

}
