package mx.com.televisa.derechocorporativo.model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import mx.com.televisa.derechocorporativo.reflexion.ReflexionUtil;

public class Empresa {

	int idEmpresa;
	String nomEmpresa;
	
	
	public Empresa(ResultSet set, ResultSetMetaData metaData) throws Exception {

		ReflexionUtil.fillObject(set, metaData, this, Empresa.class);
	}


	public int getIdEmpresa() {
		return idEmpresa;
	}


	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}


	public String getNomEmpresa() {
		return nomEmpresa;
	}


	public void setNomEmpresa(String nomEmpresa) {
		this.nomEmpresa = nomEmpresa;
	}
	
	

	
}
