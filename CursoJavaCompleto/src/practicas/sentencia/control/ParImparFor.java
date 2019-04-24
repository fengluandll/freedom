/**
 * @autor startOnline
 */
package practicas.sentencia.control;

/**
 * 
 * 
 */
public class ParImparFor {

	public ParImparFor() {
		
		for(int num = 1; num <= 20; num++){
			
				if(num % 2 == 0){
					System.out.println(num + " es numero Par");
				}else{
					System.out.println(num + " es numero ImPar");
				}
			
		}
	}
	
	public static void main(String[] args) {
		new ParImparFor();
	}

}
