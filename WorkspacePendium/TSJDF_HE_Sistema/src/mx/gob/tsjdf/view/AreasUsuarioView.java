package mx.gob.tsjdf.view;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;

import mx.gob.tsjdf.bean.AreasUsuarioBean;
import mx.gob.tsjdf.bean.LoginBean;
import mx.gob.tsjdf.bean.LovBean;
import mx.gob.tsjdf.model.AreasUsuarioDAO;
import mx.gob.tsjdf.model.LovDAO;
import mx.gob.tsjdf.model.UsersDAO;
import mx.gob.tsjdf.util.Funciones;

@ManagedBean
@ViewScoped
public class AreasUsuarioView {

	private static org.apache.log4j.Logger logger = Logger.getLogger(UsersDAO.class);
	
    LovDAO lovDAO; 
    List<LovBean> listLov;
    private Map<String,String> lovAreas;
    String idUsuario;

    List<AreasUsuarioBean> listAreas;
    List<AreasUsuarioBean> listAreasFiltered;
    
    AreasUsuarioDAO areasDao;
    
    private String username;
    private String area;
    
    public List<AreasUsuarioBean> getListAreasFiltered() {
		return listAreasFiltered;
	}

	public void setListAreasFiltered(List<AreasUsuarioBean> listAreasFiltered) {
		this.listAreasFiltered = listAreasFiltered;
	}

	public List<AreasUsuarioBean> getListAreas() {
		return listAreas;
	}

	public void setListAreas(List<AreasUsuarioBean> listAreas) {
		this.listAreas = listAreas;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@PostConstruct
    public void init() {
		consultaAreas();
    }

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Map<String, String> getLovAreas() {
		return lovAreas;
	}

	public void setLovAreas(Map<String, String> lovAreas) {
		this.lovAreas = lovAreas;
	} 
	
	public void consultaAreas(){
    	lovDAO = new LovDAO();
    	listLov =  lovDAO.getAreasUsuario();
    	lovAreas  = new TreeMap<String, String>();
    	
    	for(LovBean l : listLov){
    		lovAreas.put(l.getNmbre()+" - "+l.getDescripcion(),l.getNmbre());
    	}
	}
	
	public void consultaAreasUsuario(){
		areasDao = new AreasUsuarioDAO();
		logger.info("");
		logger.info("usuario: "+this.username);
		int idUsuario = areasDao.obtenIdUsuario(this.username);
		listAreas = areasDao.obtenAreasUsuario(idUsuario);
		Funciones.updateComponent("frmAreas:dtAreas");
	}
	public void muestraArea(){
		logger.info("");
		logger.info("usuario: "+this.area);
	}
	public void insertarDatos(){
		areasDao = new AreasUsuarioDAO();
		LoginBean loginBean = (LoginBean) Funciones.getSession().getAttribute("loginBean");
		logger.info("");
		logger.info("usuario: "+this.username);
		logger.info("area: "+this.area);
		int idUsuario = areasDao.obtenIdUsuario(this.username);
		areasDao.nuevaAreaUsusario(idUsuario, this.area, loginBean.getIdUsuario());
		listAreas = areasDao.obtenAreasUsuario(idUsuario);
		Funciones.updateComponent("frmAreas:dtAreas");
	}
}
