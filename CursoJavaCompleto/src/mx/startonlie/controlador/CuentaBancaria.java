/**
 * @autor startOnline
 */
package mx.startonlie.controlador;


public class CuentaBancaria {
	
	Double saldo = 0.0;

	public CuentaBancaria() {

	}
	
	 public void depositarDinero(Double ingreso){
	        this.saldo += ingreso;
	        System.out.println("La cantidad "+ingreso+" Fue ingresada exitosamente");
	    }

	    public void retirarDinero(Double cantidad){
	        if(saldo - cantidad < 0){
	            System.out.println("No tiene saldo suficiente");
	        }
	        saldo -= cantidad;
	        System.out.println("Saldo despues del retiro "+ saldo);
	    }

	    public Double consultarSaldo(){
	        return saldo;
	    }

}



