/**
* Project: Derecho Corporativo
*
* File: ApplicationBean.java
*
* Created on: Agosto 31, 2015 at 12:00
*
* Copyright (c) - Televisa - 2015
*/

package mx.com.televisa.derechocorporativo.bean;

import javax.faces.model.SelectItem;

/*
import mx.com.televisa.derechocorporativo.data.HQLBeanList;
import mx.com.televisa.derechocorporativo.model.Client;
import mx.com.televisa.derechocorporativo.model.Enterprise;
import mx.com.televisa.derechocorporativo.model.SalesScenary;
*/
import mx.com.televisa.derechocorporativo.util.CatalogManager;
import mx.com.televisa.derechocorporativo.util.FacesUtils;

/**
 * Mapeo de Datos 
 *
 * @author KAZ - CONSULTING/
 *         Iván Castañeda Loeza
 *         Jesús Argumedo
 *         Eduardo Nava
 *         Ernesto Corona Mendoza
 *
 *
 *
 * @version 1.0.0
 *
 * @date Agosto 31, 2015 at 12:00 pm
 *
 */
public class ApplicationBean {

	private String contextPath;
	private String requesturl;

	private SelectItem[] monthsList = {	new SelectItem("1", "Enero"), new SelectItem("2", "Febrero"), new SelectItem("3", "Marzo"),
										new SelectItem("4", "Abril"), new SelectItem("5", "Mayo"), new SelectItem("6", "Junio"),
										new SelectItem("7", "Julio"), new SelectItem("8", "Agosto"), new SelectItem("9", "Septiembre"),
										new SelectItem("10", "Octubre"), new SelectItem("11", "Noviembre"), new SelectItem("12", "Diciembre")};
	
	private SelectItem[] enterprisesList;			
	private SelectItem[] clientsList;
	private SelectItem[] salesScenaryList;
	
	//Catalogs
	private SelectItem[] currencyList;
	private SelectItem[] genderList;
	private SelectItem[] specialityList;
	
	/**
	 * Puede requerir ser marcado como Syncronized para evitar ConcurrentModificationException 
	 */
	public ApplicationBean() throws Exception {
		
		this.contextPath = FacesUtils.getContextPath();
		this.requesturl  = FacesUtils.getContextUrl();
		
		loadEnterprises();
		loadClients();
		loadSalesScenaryList();
		loadCatalogs();
	}
	
	public final void loadEnterprises()  throws Exception {
		/*
		final HQLBeanList<Enterprise> hqlBeanList =  new HQLBeanList<Enterprise>();
		
		try {
			
			List<Enterprise> enterprisesArrayList = hqlBeanList.getList("from Enterprise");
			
			enterprisesList = new SelectItem[enterprisesArrayList.size()];
			int i = 0;
			for(Enterprise enterprise : enterprisesArrayList) {	
				enterprisesList[i] = new SelectItem(enterprise.getEnterpriseId()+"", enterprise.getName());
				i++;
			}	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			hqlBeanList.close();
		}*/
	}

	
	public final void loadClients()  throws Exception {
	}
	
	public final void loadSalesScenaryList()  throws Exception {
	}
	
	public final void loadCatalogs()  throws Exception {
		
		currencyList = CatalogManager.getCatalog("CURRENCY");
		genderList = CatalogManager.getCatalog("GENDER");
		specialityList = CatalogManager.getCatalog("SPECIALITY");
	}
	
	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	public void setMonthsList(SelectItem[] monthsList) {
		this.monthsList = monthsList;
	}

	public SelectItem[] getMonthsList() {
		return monthsList;
	}
	
	public SelectItem[] getEnterprisesList() {
		return enterprisesList;
	}

	public void setEnterprisesList(SelectItem[] enterprisesList) {
		this.enterprisesList = enterprisesList;
	}
	
	public void setClientsList(SelectItem[] clientsList) {
		this.clientsList = clientsList;
	}

	public SelectItem[] getClientsList() {
		return clientsList;
	}
	
	public void setCurrencyList(SelectItem[] currencyList) {
		this.currencyList = currencyList;
	}

	public SelectItem[] getCurrencyList() {
		return currencyList;
	}

	public void setGenderList(SelectItem[] genderList) {
		this.genderList = genderList;
	}

	public SelectItem[] getGenderList() {
		return genderList;
	}

	public void setSpecialityList(SelectItem[] specialityList) {
		this.specialityList = specialityList;
	}

	public SelectItem[] getSpecialityList() {
		return specialityList;
	}

	public void setSalesScenaryList(SelectItem[] salesScenaryList) {
		this.salesScenaryList = salesScenaryList;
	}

	public SelectItem[] getSalesScenaryList() {
		return salesScenaryList;
	}

	public String getRequesturl() {
		return requesturl;
	}

	public void setRequesturl(String requesturl) {
		this.requesturl = requesturl;
	}

	

	
	
}
