/**
 * @autor startOnline
 */
package mx.startonline.objetos;


public class DemoString {

	public DemoString() {
		//String correo = new String("argumedo40@hotmail.com");
    	String correo = "argumedo_40@yahoo.com.mx";
    	//un valor mayor a cero significa que si lo encontro
    	//Un valor menor a cero significa que no lo encontro
    	int arroba = correo.indexOf("@");//metodo que encuentra la primer ocurrencia
    	System.out.println(arroba);
    	
    	int arroba2 = correo.lastIndexOf("@");//Toma la ultima ocurrencia
    	System.out.println(arroba2);
    	
    	char letra = correo.charAt(arroba2);
    	System.out.println(letra);
    	String palabra = correo.substring(arroba2+1);
    	String palabra2 = correo.substring(12,17);
    	
    	String[] splt = correo.split("@");//Quita el string y trae el resto
    	System.out.println(splt[0]);//Si es cero trae lo de la izquierda y si es 1 trae lo de la derecha
    	String mayusCorreo = correo.toUpperCase();
    	System.out.println(mayusCorreo.toLowerCase());
	}
	
	public static void main(String[] args) {
		new DemoString();
	}

}
