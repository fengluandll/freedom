/**
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 */
package mx.kaz.maps;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import mx.kaz.beans.LoginBean;

import org.apache.log4j.Logger;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 * @author argumel Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 */
@ManagedBean
@ViewScoped
public class PortsMaps implements Serializable{
	
	private MapModel simpleModel;
    private Marker marker;
    private String action;
    FacesContext facesContext;
    HttpSession session;
    private static org.apache.log4j.Logger logger = Logger.getLogger(PortsMaps.class);
    
    @PostConstruct
    public void init() {
    	facesContext = FacesContext.getCurrentInstance();
    	session = (HttpSession) facesContext.getExternalContext().getSession(true);
        simpleModel = new DefaultMapModel();
          
        //Shared coordinates
        LatLng coatza = new LatLng(18.1302883,-94.4619181);
        LatLng altamira = new LatLng(22.387153,-97.998244);
        LatLng dosBocas = new LatLng(18.0850114,-94.40012);
        LatLng ensenada = new LatLng(18.1302883,-94.4619181);
        LatLng guaymas = new LatLng(27.8408952,-110.795867);
        LatLng lCardenas = new LatLng(17.9579171,-102.1907245);
        LatLng manza = new LatLng(19.0777207,-104.3377092);
        LatLng mazatlan = new LatLng(23.2467866,-106.4221208);
        LatLng progreso = new LatLng(21.2878546,-89.4859669);
        LatLng pMadero = new LatLng(15.6557135,-93.8461023);
        LatLng pVallarta = new LatLng(20.6405181,-105.2247223);
        LatLng sCruz = new LatLng(16.1964851,-95.196783);
        LatLng topo = new LatLng(25.6781707,-108.8378456);
        LatLng tuxpan = new LatLng(20.9232709,-96.790664);
        LatLng ver = new LatLng(19.1788059,-96.1624092);
          
        //Basic marker
        simpleModel.addOverlay(new Marker(coatza, "Coatzacoalcos"));
        simpleModel.addOverlay(new Marker(altamira, "Altamira"));
        simpleModel.addOverlay(new Marker(dosBocas, "Dos Bocas"));
        simpleModel.addOverlay(new Marker(ensenada, "Ensenada"));
        simpleModel.addOverlay(new Marker(guaymas, "Guaymas"));
        simpleModel.addOverlay(new Marker(lCardenas, "Lázaro Cárdenas"));
        simpleModel.addOverlay(new Marker(manza, "Manzanillo"));
        simpleModel.addOverlay(new Marker(mazatlan, "Mazatlán"));
        simpleModel.addOverlay(new Marker(progreso, "Progreso"));
        simpleModel.addOverlay(new Marker(pMadero, "Puerto Madero"));
        simpleModel.addOverlay(new Marker(pVallarta, "Puerto Vallarta"));
        simpleModel.addOverlay(new Marker(sCruz, "Salina Cruz"));
        simpleModel.addOverlay(new Marker(topo, "Topolobampo"));
        simpleModel.addOverlay(new Marker(tuxpan, "Tuxpan"));
        simpleModel.addOverlay(new Marker(ver, "Veracruz"));
    }
    
    public void onMarkerObras(OverlaySelectEvent event) {
        marker = (Marker) event.getOverlay();
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        try {
        	session.removeAttribute("nomPto");
        	session.setAttribute("nomPto", marker.getTitle());
			context.redirect(context.getRequestContextPath() + "/proyectos/proyectos.xhtml");
		} catch (IOException e) {
			logger.error(e);
		}
       
    }
    
    public void onMarkerAdqui(OverlaySelectEvent event) {
        marker = (Marker) event.getOverlay();
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        try {
        	
			context.redirect(context.getRequestContextPath() + "/proyectos/proyectos.xhtml?tipo=aqui");
		} catch (IOException e) {
			logger.error(e);
		}
       
    }    

	public MapModel getSimpleModel() {
		return simpleModel;
	}

	public void setSimpleModel(MapModel simpleModel) {
		this.simpleModel = simpleModel;
	}

	public Marker getMarker() {
		return marker;
	}

	public void setMarker(Marker marker) {
		this.marker = marker;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
    
    

}
