/**
 * @autor startOnline
 */
package mx.startonline.mod.paq1;

/**
 * 
 * 
 */
public class C {

	public C() {
		A a = new A();
		volverSaludar();
	}
	public void saluda(){
		System.out.println("Saludo desde la clase C");
	}

	private void volverSaludar(){
		System.out.println("Saludo desde la clase C");
	}
	
	protected void holaMundo(){
		System.out.println("Hola mundo");
	}
}
