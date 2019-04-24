package mx.javaonline.arreglos;

public class ArregloUnidimensional {

	public ArregloUnidimensional() {
		int[] calificaciones = new int[5];
		//Integer[] calificaciones= new Integer[5];
	   calificaciones[0]=9;
       calificaciones[1]=7;
       calificaciones[2]=5;
       calificaciones[3]=6;
       calificaciones[4]=8;
       
       String[] paises = new String[5];
       paises[0] = "Mexico";
       paises[1] = "Brasil";
       paises[2] = "EU";
       paises[3] = "Canada";
       paises[4] = "Argentina";
       
       String[][] paises2 = {{"Mexico","Puebla"},{"Brasil","Bahia"},{"EU","Texas"},{"Canada","Toronto"},{"Argentina","La pampa"}};
       

       for(int i = 0; i< paises2.length; i++){
    	  
    	   for(int j = 0; j < paises2[i].length; j++){
    		   System.out.println(paises2[i][j]);
    	   }
       }
		
		//System.out.println(calificaciones[2]);
		
//		for(int i=0;i<calificaciones.length;i++){
//			System.out.println(calificaciones[i]);
//		}

//Hacer que ellos hagn el for version 5		
//		for(Integer calf:calificaciones){
//			System.out.println(calf);
//		}
	}
	
	public static void main(String[] args) {
		new ArregloUnidimensional();
	}

}
