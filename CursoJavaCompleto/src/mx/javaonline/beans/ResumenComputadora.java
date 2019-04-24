/**
 * @autor startOnline
 */
package mx.javaonline.beans;

import javax.swing.JOptionPane;

public class ResumenComputadora {

	public void mostrarDetalle(ComputadoraBean computadoraBean){
	
		JOptionPane.showMessageDialog(null, "-- Resumen -- \n "+"Marca: " +computadoraBean.getMarca() + "\n"+
									  "Pocesador: " + computadoraBean.getProcesador() +"\n"+
									  "Memoria: "   + computadoraBean.getMemoria()    + "\n"+
									  "Disco Duro: "+ computadoraBean.getDiscoDuro()  + "\n"+
									  "Sistema Operativo: " + computadoraBean.getSo(),
									 "Practica JavaBeans",JOptionPane.INFORMATION_MESSAGE);
	}

}
