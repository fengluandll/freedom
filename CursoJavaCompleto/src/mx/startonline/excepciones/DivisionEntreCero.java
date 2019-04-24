/**
 * @autor startOnline
 */
package mx.startonline.excepciones;

/**
 * 
 * 
 */
public class DivisionEntreCero {

	public DivisionEntreCero() {
			String numero1 = "10";
	        String numero2 = "0";
	        int resultado;
//	    try{
	        resultado = Integer.parseInt(numero1) / Integer.parseInt(numero2);
	        System.out.println("Resultado "+resultado);
//	    }catch(ArithmeticException e){
	        System.out.println("Tremendoooooo Error ");
//	        e.printStackTrace();
//	    }catch(NumberFormatException num){
//	        num.printStackTrace();
//	        System.out.println("No te pases el numero debe de ser entero ");
	        
//	    }finally{
	            System.out.println("Paso por el try");
//	    }
	    System.out.println("CONTINUA CON EL PROGRAMA");
	}
	
    public static void main(String args[]){
        new DivisionEntreCero();
       }

}
