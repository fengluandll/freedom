import java.util.Scanner;

/**
 * @autor startOnline
 */

/**
 * 
 * 
 */
public class CasaCambio extends Test{
	
	Scanner scaner;
	final float DOLLAR = 13.4f;

	public CasaCambio() {
		scaner = new Scanner (System.in);
        pesosDolares();
        dolaresPesos();
        scaner.close();
	}
	
	 public void pesosDolares(){
		    
	        System.out.println("Ingresa los pesos a convertir");
	        float pesos = scaner.nextFloat();//lee toda la linea       
	        float dolares = pesos / DOLLAR;
	        System.out.println(pesos+" pesos a dolares son : "+dolares+" dolares");

	    }
	    
	    public void dolaresPesos(){
	        System.out.println("Ingrese los dolares a convertir");
	        float dolares = scaner.nextFloat();
	        float total = dolares * DOLLAR;
	        System.out.println(dolares+" dolares a pesos son "+total+" pesos");
	        System.out.println("-----------------------");
	        
	    }

    public static void main(String[] args){
        new CasaCambio();
    }
}
