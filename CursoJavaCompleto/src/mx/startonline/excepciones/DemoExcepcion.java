package mx.startonline.excepciones;

public class DemoExcepcion {

	public DemoExcepcion() {
		int uno;
		int dos;
        try{
             uno = Integer.parseInt("e");
             dos = Integer.parseInt("1");
             System.out.println("La suma es " + (uno + dos));
            }
       catch(Exception error){
              System.out.println("Alguno de los par�metros es inv�lido"+error.getMessage());
            }
	}

	public static void main(String[] args) {
		new DemoExcepcion();

	}

}
