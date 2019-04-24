package mx.start;

import java.rmi.RemoteException;

public class TestMethod {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		HolaWSProxy proxy = new HolaWSProxy();
		HolaWS hola = proxy.getHolaWS();
		try {
			System.out.println(hola.saluda("JOSE"));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
