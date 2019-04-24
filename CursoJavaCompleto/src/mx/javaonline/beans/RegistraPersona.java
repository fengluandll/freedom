/**
 * @autor startOnline
 */
package mx.javaonline.beans;

public class RegistraPersona {


	protected PersonaBean creaPersona() {
		PersonaBean personaBean = new PersonaBean();
		personaBean.setNombre("Juan");
		personaBean.setApellidos("Castillejos");
		personaBean.setEdad(30);
		personaBean.setEdoCivil("casado");
		return personaBean;
		
	}

}
