package mx.bardahl.beans;

import java.io.Serializable;

public class CustomerBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int accountNumber;
	private String accountName;
	private String descripcion;
	private String direccion;
	
	public int getAccountNumber() {
		return accountNumber;
	}
	
	
	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	

}
