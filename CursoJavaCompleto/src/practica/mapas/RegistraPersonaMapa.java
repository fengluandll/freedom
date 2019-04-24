package practica.mapas;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

public class RegistraPersonaMapa {
	
	PersonaBean personaBean;
	Map<String,PersonaBean> mapPer;

	public RegistraPersonaMapa() {
		
		
		mapPer = new HashMap<>();
		String[] articulos = {"1 Agrega persona","2 Mostrar personas","3 Salir"};
        char opcion;
        do {
	        String seleccion = (String)JOptionPane.showInputDialog(null, "Elije una opcion", 
	        														"Practica colecciones con Mapas",
	        														JOptionPane.QUESTION_MESSAGE,null, articulos, articulos[0]);
	      
	        opcion = seleccion.charAt(0);
	        switch(opcion){
        
		        case '1':
		        	String curp = JOptionPane.showInputDialog(null,"Ingresa el CURP","Practica Mapas",JOptionPane.QUESTION_MESSAGE);
		        	String nombre = JOptionPane.showInputDialog(null,"Ingresa el nombre","Practica Mapas",JOptionPane.QUESTION_MESSAGE);
		        	String apellidos = JOptionPane.showInputDialog(null,"Ingresa los apellidos","Practica Mapas",JOptionPane.QUESTION_MESSAGE);
		        	String edad = JOptionPane.showInputDialog(null,"Ingresa la edad","Practica Mapas",JOptionPane.QUESTION_MESSAGE);
		        	personaBean = new PersonaBean();
		        	personaBean.setCurp(curp);
		        	personaBean.setNombre(nombre);
		        	personaBean.setApellidos(apellidos);
		        	personaBean.setEdad(edad);
		        	mapPer.put(personaBean.getCurp(), personaBean);
		          
                break;
		        case '2':
		        	if(mapPer.isEmpty()){
		        		JOptionPane.showMessageDialog(null, "Aun no has agregado personas");
		        	}else{
		        		String mensaje = "";
			        	for(String key: mapPer.keySet()){
			        		personaBean = mapPer.get(key);
			        		mensaje += 
			        				  "CURP: " + personaBean.getCurp() + "\n"+
			        				  "Nombre: " + personaBean.getNombre() + "\n"+
			        				  "Apellido: " + personaBean.getApellidos() + "\n"+
			        				  "Edad: " + personaBean.getEdad() + "\n";
			        	}
			        	JOptionPane.showMessageDialog(null,mensaje);
		        	}
                break;
		        case '3':
		        	System.exit(1);
                break;
		        
	          }
           }while(opcion != '3');
	}

	public static void main(String[] args) {
		new RegistraPersonaMapa();

	}

}
