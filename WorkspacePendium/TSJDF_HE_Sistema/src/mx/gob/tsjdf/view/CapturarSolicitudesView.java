package mx.gob.tsjdf.view;



import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;

@ManagedBean(name="dtCapturaSolicitud")
@RequestScoped
public class CapturarSolicitudesView {
	private static org.apache.log4j.Logger logger = Logger.getLogger(CapturarSolicitudesView.class);
	
	public CapturarSolicitudesView(){
		
	}
	
	public String nuevaSolicitud(){
		return "nuevaSolicitud";
	}
}
