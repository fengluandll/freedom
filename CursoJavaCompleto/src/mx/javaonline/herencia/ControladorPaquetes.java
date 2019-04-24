/**
 * @autor startOnline
 */
package mx.javaonline.herencia;

import javax.swing.JOptionPane;


public class ControladorPaquetes {

	public final String BANCO_HSBC = "HSBC";
	public final String BANCO_BANAMEX = "BANAMEX";
	public final String BANCO_SANTANDER = "SANTANDER SERFIN";

	public ControladorPaquetes() {
		JOptionPane.showMessageDialog(null, "Bienvenido al sistema de paquetria ","Sistema",JOptionPane.INFORMATION_MESSAGE);
	}
	


	public void enviaPaquete(String banco,String destino){
		if(banco.equals(BANCO_HSBC)){
			manejoMensaje(banco,destino);
		}
		if(banco.equals(BANCO_BANAMEX)){
			manejoMensaje(banco,destino);
		}
		if(banco.equals(BANCO_SANTANDER)){
			manejoMensaje(banco,destino);
		}
	}
	
	private void manejoMensaje(String banco,String destino){
		JOptionPane.showMessageDialog(null,"Pagado en la cuenta de "+banco+" Con destino a "+ destino);
	}

}
