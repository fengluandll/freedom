/**
 * @autor startOnline
 */
package mx.startonline.vista;

import javax.swing.JOptionPane;

import mx.startonlie.controlador.CuentaBancaria;


public class BancaElectronica {

	CuentaBancaria cuentaBancaria;
    char opcion;
    public BancaElectronica(){
        cuentaBancaria = new CuentaBancaria();
        do {
            String[] opciones = {"1 Consultar tu saldo","2 Depositar Dinero","3 Retirar dinero","4 salir"};
            String entrada = (String)JOptionPane.showInputDialog(null,
            													 "Elije una opción",
            													 "Cuenta Bancaria",
            													 JOptionPane.INFORMATION_MESSAGE,
            													 null,
            													 opciones,opciones[0]);
            opcion = entrada.charAt(0);
             switch(opcion){
                case '1':
                        JOptionPane.showMessageDialog(null,"Tu saldo es "+cuentaBancaria.consultarSaldo(),
                        							   "Saldo",
                        							   JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case '2':
                    	 String cantidad = JOptionPane.showInputDialog(null,"¿Cuánto quieres depositar?",
                    			 										"Banco",
                    			 										JOptionPane.QUESTION_MESSAGE);
                         cuentaBancaria.depositarDinero(Double.parseDouble(cantidad));
                         break;
                        case '3':
                        	String retiro = JOptionPane.showInputDialog(null,"¿Cuánto quieres retirar?",
                        												"Banco",
                        												JOptionPane.QUESTION_MESSAGE);
                            cuentaBancaria.retirarDinero(Double.parseDouble(retiro));
                            break;
                                case '4':
                                    System.exit(1);
                                    break;
            }
        }
        while(opcion!=4);  
    }
    
    public static void main(String[] args){
        
        new BancaElectronica();
    }

}
