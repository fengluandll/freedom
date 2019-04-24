/**
 * @autor startOnline
 */
package practicas.sentencia.control;

/**
 * 
 * 
 */
public class ParInpar {

	public ParInpar() {
		int num = 1;
		
		while(num <= 20){
			
			if(num % 2 == 0){
				System.out.println(num + " es numero Par");
			}else{
				System.out.println(num + " es numero ImPar");
			}
			num ++;
		}
		
	}
	
	public static void main(String[] args) {
		new ParInpar();
	}

}


