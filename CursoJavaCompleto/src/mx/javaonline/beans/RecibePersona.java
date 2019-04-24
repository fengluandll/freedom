/**
 * @autor startOnline
 */
package mx.javaonline.beans;

public class RecibePersona {

	RegistraPersona registraPersona;
	
	public RecibePersona() {
		registraPersona = new RegistraPersona();
		PersonaBean personaBean = registraPersona.creaPersona();
		System.out.println("Nombre: "   + personaBean.getNombre());
		System.out.println("Apellido: " +personaBean.getApellidos());
		System.out.println("Edad: "     +personaBean.getEdad());
		System.out.println("Edo Civil: " + personaBean.getEdoCivil());
	}

	public static void main(String[] args) {
		new RecibePersona();

	}

}
