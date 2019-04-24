/**
 * @autor startOnline
 */
package mx.javaonline.beans;

import javax.swing.JOptionPane;

public class RegistraComputadora {
	
	ComputadoraBean computadoraBean;
	ResumenComputadora resumenComputadora;

	public RegistraComputadora() {
		resumenComputadora = new ResumenComputadora();
		char opcion;
		 do {
	            String[] opciones = {"1 Armar computadora","2 Mostrar resumen","3 salir"};
	            String entrada = (String)JOptionPane.showInputDialog(null,
																	 "Elije una opción",
																	 "Practica JavaBeans",
																	 JOptionPane.INFORMATION_MESSAGE,
																	 null,
	            													 opciones,opciones[0]);
			 	opcion = entrada.charAt(0);
			 	if(opcion == '1'){
			 		computadoraBean = new ComputadoraBean();
			 		String marca = JOptionPane.showInputDialog(null,
			 									"Escribe la Marca",
			 									"Practica JavaBeans",
			 									JOptionPane.QUESTION_MESSAGE);
			 		computadoraBean.setMarca(marca);
			 		String procesador = JOptionPane.showInputDialog(null,
								"Escribe el procesador",
								"Practica JavaBeans",
								JOptionPane.QUESTION_MESSAGE);
			 		computadoraBean.setProcesador(procesador);
			 		
			 		String memoria = JOptionPane.showInputDialog(null,
								"Escribe la cantidad de memoria",
								"Practica JavaBeans",
								JOptionPane.QUESTION_MESSAGE);
			 		
			 		computadoraBean.setMemoria(Integer.parseInt(memoria));
			 		
			 		String discoDuro = JOptionPane.showInputDialog(null,
								"Escribe la capacidad del disco duro",
								"Practica JavaBeans",
								JOptionPane.QUESTION_MESSAGE);
			 		
			 		computadoraBean.setDiscoDuro(discoDuro);
			 		
			 		String sistemaOp = JOptionPane.showInputDialog(null,
								"Escribe el sistema operativo",
								"Practica JavaBeans",
								JOptionPane.QUESTION_MESSAGE);
			 		computadoraBean.setSo(sistemaOp);
			 		
			 	}else if(opcion == '2'){
			 		resumenComputadora.mostrarDetalle(computadoraBean);
			 		
			 	}else if(opcion == '3'){
			 		System.exit(1);
			 	}
		 }while(opcion != 3);  
	}

	public static void main(String[] args) {
		new RegistraComputadora();

	}

}
