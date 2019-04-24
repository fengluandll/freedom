package mx.gob.tsjdf.view;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import mx.gob.tsjdf.bean.LovBean;
import mx.gob.tsjdf.model.LovDAO;

@ManagedBean
@ViewScoped
public class AprobarSolicitudesView {
	
	List<LovBean> status;
    private Map<String,String> lovStatusData;
    List<LovBean> listStatusLov;
    LovDAO lovStatusDAO; 

	public Map<String, String> getLovStatusData() {
		return lovStatusData;
	}

	public void setLovStatusData(Map<String, String> lovStatusData) {
		this.lovStatusData = lovStatusData;
	}

	@PostConstruct
    public void init() {
    	
    	lovStatusDAO = new LovDAO();
    	listStatusLov =  lovStatusDAO.getStatusSolicitud();
    	lovStatusData  = new TreeMap<String, String>();
    	
    	for(LovBean l : listStatusLov){
    		lovStatusData.put(l.getDescripcion(),l.getNmbre());
    	}
    }
    
}
