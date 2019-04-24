package mx.gob.tsjdf.view;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;

import mx.gob.tsjdf.bean.LovBean;
import mx.gob.tsjdf.bean.UsuariosBean;
import mx.gob.tsjdf.model.LovDAO;
import mx.gob.tsjdf.model.UsersDAO;

@ManagedBean(name="dtUsersView")
@ViewScoped
public class UsersView implements Serializable{
	private static final long serialVersionUID = -1490606897506756310L;
	private static org.apache.log4j.Logger logger = Logger.getLogger(UsersView.class);

	private List<UsuariosBean> users;
	private UsuariosBean selectedUser;
	
	private Map<String,Map<String,String>> data = new HashMap<String, Map<String,String>>();
    private String code; 
    private Map<String,String> lovData;
    LovDAO lovDAO; 
    List<LovBean> listLov;
    UsersDAO usersDao;
    AreasUsuarioView areaUsuario;
	
	private List<UsuariosBean> filteredUsers;
    
    @ManagedProperty("#{usersDAO}")
    private UsersDAO service;
 
    @PostConstruct
    public void init() {
    	users = service.getUsuarios();

    	lovDAO = new LovDAO();
    	listLov =  lovDAO.getUsuarios();
    	lovData  = new TreeMap<String, String>();
    	
    	for(LovBean l : listLov){
    		lovData.put(l.getNmbre()+" - "+l.getDescripcion(),l.getNmbre());
    	}
    }
    
    public void guardaIdUsuarioAreas(){
    	//logger.info("Prueba de boton");
    	logger.info(this.code);
    	usersDao = new UsersDAO();
    	//areaUsuario.setIdUsuario(usersDao.obtieneUsusarioId(this.code));
    }
     
    public List<UsuariosBean> getUsers() {
        return users;
    }
 
    public void setService(UsersDAO service) {
        this.service = service;
    }

	public List<UsuariosBean> getFilteredUsers() {
		return filteredUsers;
	}

	public void setFilteredUsers(List<UsuariosBean> filteredUsers) {
		this.filteredUsers = filteredUsers;
	}

	public Map<String, Map<String, String>> getData() {
		return data;
	}

	public void setData(Map<String, Map<String, String>> data) {
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Map<String, String> getLovData() {
		return lovData;
	}

	public void setLovData(Map<String, String> lovData) {
		this.lovData = lovData;
	}

	public UsuariosBean getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(UsuariosBean selectedUser) {
		this.selectedUser = selectedUser;
	}
}
