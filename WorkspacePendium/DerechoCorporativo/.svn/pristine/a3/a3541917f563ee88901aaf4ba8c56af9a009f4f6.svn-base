package mx.com.televisa.derechocorporativo.bean;

import java.util.*;

import mx.com.televisa.derechocorporativo.modules.reportsFlex.DBParamHandler;

//import mx.com.televisa.derechocorporativo.model.VUserLogin;

public class SessionBean {

	private String idCurrentEmpresa;
	private String nomEmpresa;
	private String idRol;
	private String idUser;
	private String nomUser;
	private int    idUserAcces;
	private String paginaModo;
	private String currentFlexReportId;
	/*private String idCurrentEmpresaMoneda;*/
	
	
	
	
	//private Map<String, String> dynamicParams = new LinkedHashMap<String, String>();
	private Map<String, LinkedHashMap<String, String>> dynamicParams = new HashMap<String,LinkedHashMap<String, String>>();
	
	private int idFlexReport;
	
	//ECM 25 AGOSTO 2015
	private String editCon;
	
	//ECM 26 AGOSTO 2015
	private String escrituraApo;
	
	private String lstNumDocumentum;

	public String getLstNumDocumentum() {
		return lstNumDocumentum;
	}

	public void setLstNumDocumentum(String lstNumDocumentum) {
		this.lstNumDocumentum = lstNumDocumentum;
	}

	public String getEscrituraApo() {
		return escrituraApo;
	}

	public void setEscrituraApo(String escrituraApo) {
		this.escrituraApo = escrituraApo;
	}

	public String getEditCon() {
		return editCon;
	}

	public void setEditCon(String editCon) {
		this.editCon = editCon;
	}



	private ApoderadosSessionVars apoderadosSessionVars = new ApoderadosSessionVars();
	

	public int getIdUserAcces() {
		return idUserAcces;
	}

	public void setIdUserAcces(int idUserAcces) {
		this.idUserAcces = idUserAcces;
	}

	public String getNomUser() {
		return nomUser;
	}

	public void setNomUser(String nomUser) {
		this.nomUser = nomUser;
	}

	public String getIdCurrentEmpresa() {
		return idCurrentEmpresa;
	}

	public void setIdCurrentEmpresa(String idCurrentEmpresa) {
		
		
		
		this.idCurrentEmpresa = idCurrentEmpresa;
		
		/*
		setIdCurrentEmpresaMoneda(Moneda.getMonedaValorNominal(this.idCurrentEmpresa));
		*/
		
	}

	public String getNomEmpresa() {
		return nomEmpresa;
	}

	public void setNomEmpresa(String nomEmpresa) {
		this.nomEmpresa = nomEmpresa;
	}
	
	public String getIdRol() {
		return idRol;
	}

	public void setIdRol(String idRol) {
		this.idRol = idRol;
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public ApoderadosSessionVars getApoderadosSessionVars() {
		return apoderadosSessionVars;
	}

	
	
	public void setApoderadosSessionVars(ApoderadosSessionVars apoderadosSessionVars) {
		this.apoderadosSessionVars = apoderadosSessionVars;
	}

	public int getIdFlexReport() {
		return idFlexReport;
	}

	public void setIdFlexReport(int idFlexReport) {
		this.idFlexReport = idFlexReport;
	}

	public Map<String, String> getDynamicParams() {
		
		
		//return dynamicParams;
		
		
		String key = getCurrentFlexReportId();
		
		LinkedHashMap<String, String> dynamicParamsByFlexReport = dynamicParams.get(key);
		
		if(dynamicParamsByFlexReport == null) {
			
			//dynamicParamsByFlexReport = new LinkedHashMap<String, String>();
			dynamicParamsByFlexReport = DBParamHandler.getParams(key);
			dynamicParams.put(key, dynamicParamsByFlexReport);
		}
		
		return dynamicParamsByFlexReport;
	}

	/*public void setDynamicParams(Map<String, String> dynamicParams) {
		this.dynamicParams = dynamicParams;
	}*/

	public String getPaginaModo() {
		return paginaModo;
	}

	public void setPaginaModo(String paginaModo) {
		this.paginaModo = paginaModo;
	}
	
	
	
	/*
	private VUserLogin currentUser;
	

	public void setCurrentUser(VUserLogin currentUser) {
		this.currentUser = currentUser;
	}

	public VUserLogin getCurrentUser() {
		return currentUser;
	}
	*/
	
	public String getCurrentFlexReportId() {
		return currentFlexReportId;
	}
	
	public void setCurrentFlexReportId(String currentFlexReportId) {
		this.currentFlexReportId = currentFlexReportId;
	}
	/*
	public String getIdCurrentEmpresaMoneda() {
		return idCurrentEmpresaMoneda;
	}
	
	public void setIdCurrentEmpresaMoneda(String idCurrentEmpresaMoneda) {
		this.idCurrentEmpresaMoneda = idCurrentEmpresaMoneda;
	}*/
	
	
}
