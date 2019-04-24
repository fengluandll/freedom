package mx.gob.tsjdf.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;

import mx.gob.tsjdf.bean.LoginBean;
import mx.gob.tsjdf.bean.LovBean;
import mx.gob.tsjdf.bean.UsuariosBean;
import mx.gob.tsjdf.model.LovDAO;
import mx.gob.tsjdf.model.UsersDAO;
import mx.gob.tsjdf.util.Funciones;
import mx.gob.tsjdf.utils.security.cryptography.CryptMD5;

@ManagedBean(name="dtUsuarioABC")
@ViewScoped
public class UsuarioABC {

	
	private static org.apache.log4j.Logger logger = Logger.getLogger(UsuarioABC.class);
	private String txtInsNoEmpleado;
	private String nombreUsuario;
	private String password;
	private String rePassword;
	private int idRol;
	private String status;
	private int idUsuario;
	
	UsersDAO usersDAO;
	String   habilita = "true";
	
	private List<UsuariosBean> users;
	private UsuariosBean selectedUser;
	
	private Map<String,Map<String,String>> data = new HashMap<String, Map<String,String>>();
    private String code; 
    private Map<String,String> lovData;
    LovDAO lovDAO; 
    List<LovBean> listLov;
	
	private List<UsuariosBean> filteredUsers;
	
	public UsuarioABC(){
		usersDAO = new UsersDAO();
		cargaUsuarios();
	}
	
	public void booraUsuario(UsuariosBean usuariosBean){
		boolean paso = usersDAO.borraUsuario(usuariosBean.getId_usuario());
		if(paso){
			cargaUsuarios();
			Funciones.mostrarMensaje("Usuario Borrado Correctamente","", "INFO");
		}else{
			Funciones.mostrarMensaje("Error: No se borro el usuario","", "ERROR");
		}
	}
	
	public void cargaUsuarios(){
		users = usersDAO.getUsuarios();

    	lovDAO = new LovDAO();
    	listLov =  lovDAO.getUsuarios();
    	lovData  = new TreeMap<String, String>();
    	
    	for(LovBean l : listLov){
    		lovData.put(l.getNmbre()+" - "+l.getDescripcion(),l.getNmbre());
    	}
	}
	
	public void actualizaUsuario(){
		LoginBean loginBean = (LoginBean)Funciones.getSession().getAttribute("loginBean");
		
		boolean paso = usersDAO.actualizaUsuario(this.idRol, this.status, loginBean.getIdUsuario(), this.idUsuario);
		if(paso){
			cargaUsuarios();
			Funciones.mostrarMensaje("Usuario Actualizado","", "INFO");
			
		}else{
			Funciones.mostrarMensaje("Error: No se actualizo el usuario","", "ERROR");
		}
	}
	
	 public void obtenUsuario(UsuariosBean usuariosBean){
	    	logger.info(usuariosBean.getRol_name());
	    	logger.info(usuariosBean.getStatus());
	    	logger.info(usuariosBean.getNum_empleado());
	    	logger.info(usuariosBean.getNombre_completo());
	    	logger.info(usuariosBean.getPassword());
	    	
	    	this.idRol  		  = usuariosBean.getId_rol();
	    	this.status 		  = usuariosBean.getStatus().equals("Activo")?"A":"I";
	    	this.nombreUsuario    = usuariosBean.getNombre_completo();
	    	this.txtInsNoEmpleado = usuariosBean.getNum_empleado();
	    	this.password		  = usuariosBean.getPassword();
	    	this.rePassword 	  = usuariosBean.getPassword();
	    	this.idUsuario        = usuariosBean.getId_usuario();
	    	//Funciones.manageDialog("dlgEditar", 2);
	    	//RequestContext.getCurrentInstance().update(":frmRegActua");
	 }
	
	public void consultaNumEmpleado(){
		nombreUsuario = usersDAO.dameNomUsuario(this.txtInsNoEmpleado);
		if(nombreUsuario == null){
			habilita = "true";
			Funciones.mostrarMensaje("El numero de empleado que capturaste no se encontro", "", "INFO");
		}else{
			habilita = "false";
		}
	}
	
	public void nuevoEmpleado(){
		consultaNumEmpleado();
		
		LoginBean loginBean = (LoginBean) Funciones.getSession().getAttribute("loginBean");
		if(nombreUsuario == null){
			Funciones.mostrarMensaje("El numero de empleado que capturaste no se encontro", "", "WARN");
		}else{
			boolean existe = usersDAO.existeUsusario(this.txtInsNoEmpleado);
			if(existe){
				Funciones.mostrarMensaje("El numero de empleado ya existe registrado", "", "ERROR");
			}else{
				if(this.password.equals(this.rePassword)){
					usersDAO.nuevoUsusario(this.txtInsNoEmpleado, this.idRol, CryptMD5.getMD5(this.password,"MD5"), status,loginBean.getIdUsuario());
					cargaUsuarios();
					Funciones.mostrarMensaje("Empleado agregado exitosamente", "", "INFO");
				
				}else{
					Funciones.mostrarMensaje("Las contraseñas no coinciden", "", "ERROR");
				}
				
			}
		}
		
		
	}

	
	
	

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRePassword() {
		return rePassword;
	}

	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}

	public int getIdRol() {
		return idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	public String getHabilita() {
		return habilita;
	}

	public void setHabilita(String habilita) {
		this.habilita = habilita;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getTxtInsNoEmpleado() {
		return txtInsNoEmpleado;
	}

	public void setTxtInsNoEmpleado(String txtInsNoEmpleado) {
		this.txtInsNoEmpleado = txtInsNoEmpleado;
	}
	
	
	public void guardaIdUsuarioAreas(){
    	logger.info("Prueba de boton");
    	logger.info(this.code);
    }
     
    public List<UsuariosBean> getUsers() {
        return users;
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
