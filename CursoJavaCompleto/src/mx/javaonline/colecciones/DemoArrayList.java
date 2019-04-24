package mx.javaonline.colecciones;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class DemoArrayList {
	ArrayList<String> listDatos;

	public DemoArrayList() {
		listDatos = new ArrayList<>();
	
		String nombre = JOptionPane.showInputDialog(null,"¿Cual es tu nombre?","Demo ArrayList",JOptionPane.QUESTION_MESSAGE);
		listDatos.add(nombre);
		String apellidos = JOptionPane.showInputDialog(null,"¿Cuales son tus apellidos","Demo ArrayList",JOptionPane.QUESTION_MESSAGE);
		listDatos.add(apellidos);
		String edad = JOptionPane.showInputDialog(null,"¿Cual es tu edad?","Demo ArrayList",JOptionPane.QUESTION_MESSAGE);
		listDatos.add(edad);
		String direccion = JOptionPane.showInputDialog(null,"¿Cual es tu direccion?","Demo ArrayList",JOptionPane.QUESTION_MESSAGE);
		listDatos.add(direccion);
		String edoCivil = JOptionPane.showInputDialog(null,"¿Cual es tu estado civil?","Demo ArrayList",JOptionPane.QUESTION_MESSAGE);
		listDatos.add(edoCivil);
		System.out.println("Para saber el tamaño del arrayList "+ listDatos.size());
		System.out.println("Vamos a iterar el ArrayList con un for");
		for(int i = 0;i < listDatos.size();i++){
			System.out.println(listDatos.get(i));
		}
	}
	
	public static void main(String[] args) {
		new DemoArrayList();

	}

}
