/**
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 */
package mx.kaz.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import mx.kaz.beans.ProyectBean;
import mx.kaz.daos.ProyectDAO;

import org.apache.log4j.Logger;
import org.primefaces.model.chart.PieChartModel;

/**
 * @author argumel
 *
 */
@ManagedBean
public class ChartObrasView implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private org.apache.log4j.Logger logger = Logger.getLogger(ChartObrasView.class);
	private PieChartModel pieModel;
	ProyectDAO proyectDAO;
	FacesContext facesContext;
	HttpSession session;
	
	@PostConstruct
	public void init(){
		facesContext = FacesContext.getCurrentInstance();
		session = (HttpSession) facesContext.getExternalContext().getSession(true);
		createPieModel();
	}
	
	 private void createPieModel() {
		 proyectDAO = new ProyectDAO();
		 String nomPto = (String)session.getAttribute("nomPto");
		 int idPort = proyectDAO.obtenIdPto(nomPto);
//		 List<ProyectBean> listProj = proyectDAO.obtenContenido(idPort);
		 
		 pieModel = new PieChartModel();
	     
//		 for(ProyectBean p : listProj){
//			 pieModel.set(p.getCode(), p.getAsignedAmount());
//			 
//		 }
		 pieModel.setTitle("Obras");
		 pieModel.setLegendPosition("w");
		 pieModel.setFill(false);
		 pieModel.setShowDataLabels(true);
		 pieModel.setMouseoverHighlight(true);
		 pieModel.setShadow(true);
		 
	    
	 }
	 
	 
	public PieChartModel getPieModel() {
		return pieModel;
	}
	public void setPieModel(PieChartModel pieModel) {
		this.pieModel = pieModel;
	}
	
	
	
	
	

}
