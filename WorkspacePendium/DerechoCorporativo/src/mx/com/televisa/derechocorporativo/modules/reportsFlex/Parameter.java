package mx.com.televisa.derechocorporativo.modules.reportsFlex;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import mx.com.televisa.derechocorporativo.reflexion.ReflexionUtil;

public class Parameter {

	 private int idAddCampo; 
	 private String nomCampo; 
	 private int idCatalogo;
	 private String tipoCampo;
     
      	

 	public Parameter(ResultSet set, ResultSetMetaData metaData) throws Exception {

 		ReflexionUtil.fillObject(set, metaData, this, Parameter.class);
 	}



	public int getIdAddCampo() {
		return idAddCampo;
	}



	public void setIdAddCampo(int idAddCampo) {
		this.idAddCampo = idAddCampo;
	}



	public String getNomCampo() {
		return nomCampo;
	}



	public void setNomCampo(String nomCampo) {
		this.nomCampo = nomCampo;
	}



	public int getIdCatalogo() {
		return idCatalogo;
	}



	public void setIdCatalogo(int idCatalogo) {
		this.idCatalogo = idCatalogo;
	}



	public String getTipoCampo() {
		return tipoCampo;
	}



	public void setTipoCampo(String tipoCampo) {
		this.tipoCampo = tipoCampo;
	}

     
     
     
}
