package mx.gob.tsjdf.view;

import java.io.Serializable;
import java.util.HashMap;
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
public class RolesLovView implements Serializable {
	private static final long serialVersionUID = -8954696263458713891L;
	
	private Map<String,Map<String,String>> data = new HashMap<String, Map<String,String>>();
    private String code, descripcion; 
    private Map<String,String> lovData;
    LovDAO lovDAO; 
    List<LovBean> listLov;
    
    @PostConstruct
    public void init() {
    	lovDAO = new LovDAO();
    	listLov =  lovDAO.getRoles();
    	//lovData  = new HashMap<String, String>();
    	lovData  = new TreeMap<String, String>();
    	
    	for(LovBean l : listLov){
    		lovData.put(l.getCode()+" - "+l.getDescripcion(),l.getCode());
    	}
    }
 
    public Map<String, Map<String, String>> getData() {
        return data;
    }
 
    public String getCode() {
        return code;
    }
 
    public void setCode(String code) {
        this.code = code;
    }

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Map<String, String> getLovData() {
		return lovData;
	}

	public void setLovData(Map<String, String> lovData) {
		this.lovData = lovData;
	}
	public void onQuincenaChange() {
    }
}
