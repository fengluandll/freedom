package com.movemini.layers.session;

import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpSession;

import com.movemini.data.Record;

public class SessionBean {

	/**
	 * 
	 */
	public static SessionBean getInstance(HttpSession session) {
		
		
		SessionBean sessionBean = (SessionBean) session.getAttribute("SessionBean");
		
		if(sessionBean == null) {
			
			sessionBean = new SessionBean();
			
			session.setAttribute("SessionBean", sessionBean);
		}
		
		return sessionBean;
	}
	

	public static SessionBean getInstance(HttpServletRequest request) {
		HttpSession  sesion = request.getSession();
		sesion.setMaxInactiveInterval(60*60);
		return getInstance(sesion);		
	}
	
	/**
	 * Just Controlled Instance 
	 */
	private SessionBean() {	
	}
	
	
	

	private Record user;
	
	
	
	public Record getUser() {
		return user;
	}
	
	public void setUser(Record user) {
		this.user = user;
	}
	
	
	private String idEvento;
	
	
	public String getIdEvento() {
		return idEvento;
	}
	
	public void setIdEvento(String idEvento) {
		this.idEvento = idEvento;
	}
	
	

	
	private String idEventoVersion;
	
	
	public String getIdEventoVersion() {
		return idEventoVersion;
	}
	
	public void setIdEventoVersion(String idEventoVersion) {
		this.idEventoVersion = idEventoVersion;
	}
	
	
//	private String sede;
//	
//	public String getSede() {
//		return sede;
//	}
//	
//	public void setSede(String sede) {
//		this.sede = sede;
//	}	
//	
//	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
