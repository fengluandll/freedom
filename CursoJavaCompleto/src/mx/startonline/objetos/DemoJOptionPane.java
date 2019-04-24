package mx.startonline.objetos;

import javax.swing.JOptionPane;

public class DemoJOptionPane {

	public DemoJOptionPane() {
		
		//Mostrar tipos de mensajes
		/*
		JOptionPane.showMessageDialog(null,"Hola mundo con JOptionPane",
				                          "Cuadros de dialogo",
				                          JOptionPane.INFORMATION_MESSAGE);
		JOptionPane.showMessageDialog(null,"Ups error",
                "Cuadros de dialogo",
                JOptionPane.ERROR_MESSAGE);
		JOptionPane.showMessageDialog(null,"Advertencia",
                "Cuadros de dialogo",
                JOptionPane.WARNING_MESSAGE);
		JOptionPane.showMessageDialog(null,"Pregunta",
                "Cuadros de dialogo",
                JOptionPane.QUESTION_MESSAGE);
		JOptionPane.showMessageDialog(null,"Plano",
                "Cuadros de dialogo",
                JOptionPane.PLAIN_MESSAGE);
		*/
		//Mostrar opciones si o no
		int opcion = JOptionPane.showConfirmDialog(null,"¿Quieres aprender Java?","IMPORTANTE",
				                      JOptionPane.YES_NO_OPTION);
		System.out.println(opcion);
		if(opcion == 0){
			//Mostrar varias opciones customizadas
			String[] opciones = {"Sin duda Alguna","Puede ser","No lo se","No estoy loco"};
			int opcion2 = JOptionPane.showOptionDialog(null, "¿De verdad.. Quieres aprender Java?",
					                            "Cuadros de dialogo",
					                            JOptionPane.DEFAULT_OPTION,
					                            JOptionPane.INFORMATION_MESSAGE, 
					                            null,
					                            opciones,
					                            opciones);
			System.out.println(opcion2);
			if(opcion2 == 0){
				//Mostrar un combo
				String[] combo = {"Escribe tu nombre","Escribe tu apellido"};
				String opcion3 = (String)JOptionPane.showInputDialog(null,"Elije una opcion",
											"Cuadros de dialogo",JOptionPane.INFORMATION_MESSAGE,
											null,combo,combo[0]);
				
				if(opcion3.equals("Escribe tu nombre")){
					//Capturar texto libre parecido al Scanner
					String nombre = JOptionPane.showInputDialog(null,"Escribe tu nombre",
												"Cuadros de dialogo",JOptionPane.QUESTION_MESSAGE);
					System.out.println(nombre);
				}else if(opcion3.equals("Escribe tu apellido")){
					String apellido = JOptionPane.showInputDialog(null,"Escribe tu apellido",
							"Cuadros de dialogo",JOptionPane.QUESTION_MESSAGE);
					System.out.println(apellido);
				}
				
			}
			
		}else{
			JOptionPane.showMessageDialog(null,"Que lastima, de lo que te estas perdiendo",
                    "Cuadros de dialogo",
                    JOptionPane.INFORMATION_MESSAGE);
		}
		
	}

	public static void main(String[] args) {
			new DemoJOptionPane();
	}

}
