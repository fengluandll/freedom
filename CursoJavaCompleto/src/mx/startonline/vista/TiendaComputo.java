package mx.startonline.vista;

import javax.swing.JOptionPane;

import mx.startonlie.controlador.InfoVentas;



public class TiendaComputo {

	public TiendaComputo() {
		creaMenu();
	}
	
	private void creaMenu(){
		String combo[] = {"1 Lap Top IBM","2 Impresora HP","3 Monitor LCD","4 Salir"};
		
		char indice;
		do{
			String opcion = (String)JOptionPane.showInputDialog(null,"Que desea cotizar","Tienda de computo",
					         JOptionPane.WARNING_MESSAGE,null,combo,combo[0]);
			indice = opcion.charAt(0);
			if(indice == '1'){
				String cantidad = JOptionPane.showInputDialog("¿Cuántas quieres?");
				logicaLaptop(Integer.parseInt(cantidad));
			}else if(indice == '2'){
				String cantidad = JOptionPane.showInputDialog("¿Cuántas quieres?");
				logicaImpresora(Integer.parseInt(cantidad));
				
			}else if(indice == '3'){
				String cantidad = JOptionPane.showInputDialog("¿Cuántas quieres?");
				logicaMonitor(Integer.parseInt(cantidad));
				
			}
			
		}while(indice != '4');
		
	}

	private void logicaMonitor(int cantidad) {
		InfoVentas infoVentas = new InfoVentas();
		double total = infoVentas.monitorLcd(cantidad);
		JOptionPane.showMessageDialog(null,"El total de "+cantidad+" Monitores LG es de "+total);
		
	}

	private void logicaImpresora(int cantidad) {
		InfoVentas infoVentas = new InfoVentas();
		double total = infoVentas.impresoraHp(cantidad);
		JOptionPane.showMessageDialog(null,"El total de "+cantidad+" Impresoras HP es de "+total);
		
	}

	private void logicaLaptop(int cantidad) {
		InfoVentas infoVentas = new InfoVentas();
		double total = infoVentas.laptopIbm(cantidad);
		JOptionPane.showMessageDialog(null,"El total de "+cantidad+" Lap top IBM es de "+total);
		
	}

	public static void main(String[] args) {
		new TiendaComputo();

	}

}
