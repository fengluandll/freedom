package mx.javaonline.colecciones;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class PracticaArrayList {

	ArrayList<String> listProductos;
	String productos;
	public PracticaArrayList() {
		listProductos = new ArrayList<>();
		muestraMenu();
	}
	
	public void muestraMenu(){
		String[] articulos = {"1 Refresco","2 Mantequilla","3 Aceite","4 Agua","5 Mayonesa","6 Mostrar carrito de compras","7 Salir"};
        char opcion;
        do {
	        String seleccion = (String)JOptionPane.showInputDialog(null, "Elije un producto para agregar a tu lista", 
	        														"Super mercado",
	        														JOptionPane.QUESTION_MESSAGE,null, articulos, articulos[0]);
	        String selecSinNumero = seleccion.substring(2,seleccion.length());
	        opcion = seleccion.charAt(0);
	        switch(opcion){
        
		        case '1':
		        	listProductos.add(selecSinNumero);
		                  JOptionPane.showMessageDialog(null,"Agregaste 1 " + selecSinNumero);
		          
                break;
		        case '2':
		        	listProductos.add(selecSinNumero);
		                  JOptionPane.showMessageDialog(null,"Agregaste 1 " + selecSinNumero);
		          
                break;
		        case '3':
		        	listProductos.add(selecSinNumero);
	                  JOptionPane.showMessageDialog(null,"Agregaste 1 " + selecSinNumero);
		          
                break;
		        case '4':
		        	listProductos.add(selecSinNumero);
	                  JOptionPane.showMessageDialog(null,"Agregaste 1 " + selecSinNumero);
		          
                break;
		        case '5':
		        	listProductos.add(selecSinNumero);
	                  JOptionPane.showMessageDialog(null,"Agregaste 1 " + selecSinNumero);
		          
                break;
		        case '6':
		        	productos = "";
		        	for(String pro : listProductos){
		        		productos += pro + "\n";
		        	}
		        	JOptionPane.showMessageDialog(null,"Tu carrito tiene: " + productos);
		                  
		          
                break;
	          }
           }while(opcion != '7');
	}
        public static void main(String[] args) {
    		new PracticaArrayList();

    	}
}
