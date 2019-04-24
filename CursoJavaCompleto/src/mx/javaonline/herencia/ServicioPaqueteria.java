/**
 * @autor startOnline
 */
package mx.javaonline.herencia;

import javax.swing.JOptionPane;

/**
 * 
 * 
 */
public class ServicioPaqueteria extends ControladorPaquetes{

	public ServicioPaqueteria() {
			super();
			String lugar = JOptionPane.showInputDialog(null,"¿A que lugar enviara su paquete?",
			                            "Lugar",JOptionPane.QUESTION_MESSAGE);
			String[] opciones = {BANCO_HSBC,BANCO_BANAMEX,BANCO_SANTANDER};
			String banco = (String)JOptionPane.showInputDialog(null,"¿A que banco deposito?",
										"Banco",JOptionPane.QUESTION_MESSAGE,null,opciones,opciones[0]);
		                                                                                                                                                                                                                                
			
			enviaPaquete(banco, lugar);
	}


	public static void main(String[] args) {
		new ServicioPaqueteria();

	}

}
