package mx.bardahl.beans;

import java.io.Serializable;

public class CustomerAddressBean implements Serializable{
	

	private static final long serialVersionUID = 1L;
	private String direccion;

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	

}
