/**
 * @autor startOnline
 */
package mx.startonline.juegos;

import java.util.Random;
import java.util.Scanner;


public class PicasFijas {
	
	public final static int TAM = 4;
	private int numeroOculto[];
	private int numeroIngresado[];
	int numAleatroio[];
	String snumAleatorio;
	Scanner scaner;

	public PicasFijas() {
		numeroOculto 	= new int[TAM];
		numeroIngresado = new int[TAM];
		numAleatroio = getNumberAleat(); 
		scaner = new Scanner(System.in);
		
		System.out.println("----Tienes 10 intentos para adivinar un número de 4 digitos----");
		int numPicas = 0;
		int numFijas = 0;
		int numIntentos = 0;
		
			do{
				try{
					System.out.println("Intento # "+ (numIntentos + 1));
					System.out.println("Ingresa un numero de 4 digitos");
					String numIngresado = scaner.nextLine();
					
					int num1 = Integer.parseInt(numIngresado.substring(0,1));
					int num2 = Integer.parseInt(numIngresado.substring(1,2));
					int num3 = Integer.parseInt(numIngresado.substring(2,3));
					int num4 = Integer.parseInt(numIngresado.substring(3,4));
					
					numeroIngresado[0] = num1;
					numeroIngresado[1] = num2;
					numeroIngresado[2] = num3;
					numeroIngresado[3] = num4;
					numPicas = picas(numeroIngresado);
					numFijas = fijas(numeroIngresado);
//					System.out.println("numPicas "+numPicas);
//					System.out.println("numFijas "+numFijas);
					
					System.out.println("Fijas\tTu número\tPicas");
					System.out.println(numFijas+"\t"+numIngresado+"\t\t"+Math.abs(numPicas-numFijas));
					numIntentos++; //Incremento el numero de intentos
					if(numFijas == 4){
						System.out.println("!!GANASTE!!");
						System.out.println("El numero aleatorio es: " + snumAleatorio);
						break;
					}
					if(numIntentos == 10){
						System.out.println("!!PERDISTE!!");
						System.out.println("El numero aleatorio era: " + snumAleatorio);
						break;
						
					}
			}catch (StringIndexOutOfBoundsException e) {
				System.out.println("Requiere ser un numero de 4 digitos");
			}
			}while(numIntentos < 10);
			scaner.close();
			
		
	}


	/**
	 * Metodo que obtiene el numero picas, es decir si alguno de tus numeros coincide
	 * con alguno del numero aleatorio
	 * @param numIngresado
	 * @return
	 */
	private int picas(int[] numIngresado){
		int count = 0;
	
		
		for(int i = 0; i < numIngresado.length;i++){
			
			for(int j = 0; j <numAleatroio.length; j++){
				if(numIngresado[i] == numAleatroio[j]){
					count++;
				}
			}
			
		}
		
		return count;
		
	}
	
	/**
	 * Metodo dode devuelve cuantos numeros fueron los que se atinaron en el mismo orden
	 * @param numIngresado
	 * @return
	 */
	private int fijas(int[] numIngresado){
		int count = 0;
		if(numIngresado[0] == numAleatroio[0]){
			count++;
		}
		if(numIngresado[1] == numAleatroio[1]){
			count++;
		}
		if(numIngresado[2] == numAleatroio[2]){
			count++;
		}
		if(numIngresado[3] == numAleatroio[3]){
			count++;
		}
		return count;
	}
	
	/**
	 * Metodo que obtiene un numero aleatorio de 4 digitos
	 * Si en este numero existe uno repetido, entra el do y
	 * vuelve a crear otro numero aleatorio hasta que ya no se
	 * repitan entre ellos mismos
	 * @return
	 */
	private int[] getNumberAleat(){
		int count = 0;
			do{
				count = 0;
				Random rnd = new Random();
				snumAleatorio = String.valueOf(Math.abs(rnd.nextInt())).substring(0,4);
//				System.out.println("Num aleatorio "+snumAleatorio); //Este te sirve para saber el numero aleatorio e ir debugeando si el proceso esta bien hecho
				int alea1 = Integer.parseInt(snumAleatorio.substring(0,1));
				int alea2 = Integer.parseInt(snumAleatorio.substring(1,2));
				int alea3 = Integer.parseInt(snumAleatorio.substring(2,3));
				int alea4 = Integer.parseInt(snumAleatorio.substring(3,4));
				numeroOculto[0] = alea1;
				numeroOculto[1] = alea2;
				numeroOculto[2] = alea3;
				numeroOculto[3] = alea4;
				for(int i = 0; i < numeroOculto.length; i++ ){
					for(int j=0;j< numeroOculto.length; j++){
						//System.out.println(numeroOculto[i]+" = "+numeroOculto[j]);
						if( numeroOculto[i] == numeroOculto[j] ){
							count ++;
							
						}
					}
				}
				//System.out.println(count);
				
				
		   }while(count > 4);
		return numeroOculto;
	}
	
	
	public static void main(String[] args) {

		new PicasFijas();
	}

}
