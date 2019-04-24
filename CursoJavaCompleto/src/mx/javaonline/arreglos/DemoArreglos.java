/**
 * @autor startOnline
 */
package mx.javaonline.arreglos;

/**
 * 
 * 
 */
public class DemoArreglos {

	public DemoArreglos() {
		
//		String[] paises = new String[5];
//		paises[0] = "Mexico";
//		paises[1] = "Brasil";
//		paises[2] = "EU";
//		paises[3] = "Canada";
//		paises[4] = "Argentina";
		
//		String[] paises = {"Mexico","Brasil","EU","Canada","Argentina"};
//		
//		
//		for(int i = 0;i < paises.length; i ++){
//			System.out.println(paises[i] );
//		}
		
		String[][] paises = new String[5][2];
		paises[0][0] = "Mexico";
		paises[0][1] = "Puebla";
		paises[1][0] = "Brasil";
		paises[1][1] = "Bahia";
		paises[2][0] = "EU";
		paises[2][1] = "Texas";
		paises[3][0] = "Canada";
		paises[3][1] = "Toronto";
		paises[4][0] = "Argentina";
		paises[4][1] = "La pampa";
		
		for(int i = 0; i < paises.length; i++){
			
			for(int j = 0;j < paises[i].length; j++){
				System.out.println(paises[i][j]);
			}
		}
		
	}
	public static void main(String[] args) {
		new DemoArreglos();
	}

}
