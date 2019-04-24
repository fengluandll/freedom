
public class DemoSwitch {

	public DemoSwitch(int numero){
		
		switch(numero) {
		 case 1: 
		     salida("Uno");
		     break;
		 case 2: 
		     salida("Dos");
		     break;
		 case 3: 
		     salida("Tres");
		     break;
		 case 4: 
		     salida("Cuatro");
		     break;
		 case 5: 
		 case 6: 
		     salida("Cinco o seis");
		     break;
		 default: 
		     salida("Otro número");
		     break;
		 }

	}
	
	public void salida(String cadena){
		System.out.println(cadena);
	}

	
	public static void main(String[] args) {		
		new DemoSwitch(1);
	}
}
