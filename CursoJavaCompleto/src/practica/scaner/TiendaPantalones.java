/**
 * @autor startOnline
 */
package practica.scaner;

import java.util.Scanner;

/**
 * 
 * 
 */
public class TiendaPantalones {
	
	final float PRECIO_PANTALON = 230.00f;
	float total = 0.0f;
	String leyenda;
	float descuento;

	public TiendaPantalones() {
		Scanner scaner =new Scanner(System.in);
		System.out.println("¿Cuántos pantalones desea comprar?");
		int numPanta = scaner.nextInt();
		
		if(numPanta < 5){
			total = numPanta * PRECIO_PANTALON;
			leyenda = "Total a pagar de " + numPanta + " Pantalon(es) es de:"+ total + " No se genero ningun descuento";
		}else if(numPanta >= 5 && numPanta < 12){
			total =  numPanta * PRECIO_PANTALON;
			descuento = (total * 15) / 100;
			leyenda = "Total a pagar de " + numPanta + " Pantalon(es) es de: "+ (total - descuento) + " Se genero un 15% de descuento";
		}else if(numPanta >= 12){
			total =  numPanta * PRECIO_PANTALON;
			descuento = (total * 30) / 100;
			leyenda = "Total a pagar de " + numPanta + " Pantalon(es) es de: "+ (total - descuento) + " Se genero un 30% de descuento";
		}
		
		System.out.println(leyenda);
		scaner.close();
	}
	
	public static void main(String[] args) {
		new TiendaPantalones();
	}

}
