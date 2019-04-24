package mx.start;

import java.rmi.RemoteException;

import org.apache.axis.AxisFault;

public class ProbarWs {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			//HolaMundoSoap11BindingStub stub = new HolaMundoSoap11BindingStub();
			HolaMundoPortTypeProxy proxy = new HolaMundoPortTypeProxy();
			HolaMundoPortType port = proxy.getHolaMundoPortType();
			System.out.println("EYY "+port.saluda("jose de jesus")); 
		} catch (AxisFault e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
