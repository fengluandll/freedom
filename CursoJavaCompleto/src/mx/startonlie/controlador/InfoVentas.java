/**
 * @autor startOnline
 */
package mx.startonlie.controlador;


public class InfoVentas {

	public final double PRECIO_LAP_TOP = 8950.00;
	private final double PRECIO_IMPRESORA_HP = 1250.00;
	private final double PRECIO_MONITOR_LCD = 2555.00;

	
	public double laptopIbm(int cantidad){
		double total = cantidad*PRECIO_LAP_TOP;
		return total;
	}
	
	public double impresoraHp(int cantidad){
		double total = cantidad*PRECIO_IMPRESORA_HP;
		return total;
	}

	public double monitorLcd(int cantidad){
		double total = cantidad*PRECIO_MONITOR_LCD;
		return total;
	}

}






