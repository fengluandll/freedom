package mx.startonline.objetos;

import java.util.Scanner;

public class ValidaCorreoElectronico {

	public ValidaCorreoElectronico() {
		
		Scanner scaner = new Scanner(System.in);
		System.out.println("Ingresa tu correo electronico");
		String correo = scaner.nextLine();
		int arroba = correo.indexOf("@");
		if(arroba > -1){
			//Se le pone +1 para que no contemple el @ y solo se esta validand la cadena despues del arroba
			String despuesArroba = correo.substring(arroba+1,correo.length());
			int punto = despuesArroba.indexOf(".");
			if(punto > -1){
				String dominio = despuesArroba.substring(0,punto);//Se obtiene el puro dominio
				
				if(dominio.equals("yahoo") || dominio.equals("hotmail") || dominio.equals("gmail")){
					
					int com = despuesArroba.indexOf(".com");
					if(com > -1){
						System.out.println("¡Correo válido!");
					}else{
						System.out.println("Correo electronico no valido");
					}
					
				}else{
					System.out.println("Correo electronico no valido");
				}
			}else{
				System.out.println("Correo electronico no valido");
			}
			
		}else{
			System.out.println("Correo electronico no valido");
		}
		
	}

	public static void main(String[] args) {
		new ValidaCorreoElectronico();

	}

}
