package mx.kaz.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import mx.kaz.beans.UserBean;
import mx.kaz.daos.UsersDAO;
import mx.kaz.util.FuncionesGenerales;
import mx.kaz.util.Mensajes;

public class AdminUserView{
	
	
	List<UserBean>         listUser;
	private List<UserBean> filteredUser;
	private UserBean       selectedUserBean;
	UsersDAO usersDAO;
	private FacesContext facesContext = FacesContext.getCurrentInstance();
	HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
	Map<String,Integer> mapRoles = new HashMap<String,Integer>();
	Map<String,Integer> mapStatus = new HashMap<String,Integer>();
	private String 	givenNames;
	private int 	tipoRol;
	private int 	tipoStatus;
	private String 	userName;
	private boolean checkPwd;
	private int		loginId;
	private int		personalId;
	private String password;
	
	String contexto 				= facesContext.getExternalContext().getRequestContextPath();
	HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
	
	public AdminUserView() {
		
//        response.addHeader("Pragma", "no-cache");
//        response.addHeader("Cache-Control", "no-cache");
//        // Stronger according to blog comment below that references HTTP spec
//        response.addHeader("Cache-Control", "no-store");
//        response.addHeader("Cache-Control", "must-revalidate");
//        // some date in the past
//        response.addHeader("Expires", "Mon, 8 Aug 2006 10:00:00 GMT");
        
		usersDAO = new UsersDAO();
		obtenUsuarios();
		mapRoles  = FuncionesGenerales.creaCombo("select rol_id,rol_name from roles order by rol_name");
		mapStatus = FuncionesGenerales.creaCombo("select id_status_login,nom_status from status_login order by nom_status");
		
	}
	
	private void obtenUsuarios() {
		this.listUser = usersDAO.getUser();
	}
	
	public void refresVariablesUsuario(UserBean userBean) {
		this.tipoRol 			= userBean.getRolId();
		this.tipoStatus 		= userBean.getIdStatus();
		this.userName			= userBean.getUserName();
		this.loginId 			= userBean.getLoginId();
		this.personalId			= userBean.getPersonalId();
		this.givenNames			= userBean.getGivenNames();
		this.password			= userBean.getPassword();
		
	}
	
	public void cleanVariablesUsuario() {
		this.tipoRol 			= 0;
		this.tipoStatus 		= 0;
		this.userName			= "";
		this.loginId 			= 0;
		this.personalId			= 0;
		this.givenNames			= "";
		this.password			= "";
		
	}
	
	public void actualizaUsuario() {
		//PersonalBean personalBean = (PersonalBean)session.getAttribute("personalBean");
		UserBean userBean = new UserBean();
		userBean.setLoginId(this.loginId);
		userBean.setPersonalId(this.personalId);
		userBean.setRolId(this.tipoRol);
		userBean.setIdStatus(this.tipoStatus);
		userBean.setUserName(this.userName);
		userBean.setGivenNames(this.givenNames);
		userBean.setPassword(this.password);
		//userBean.setSegment1(this.checkPwd==true?"true":"false");
		if(this.tipoStatus == 4 && (this.password == null || this.password.equals(""))) {
			FuncionesGenerales.mostrarMensaje("Es necesario capturar la contraseña", "Error", Mensajes.INFO);
		}else {
		
				boolean paso = usersDAO.updateUser(userBean, this.personalId);
				if(paso) {
					FuncionesGenerales.mostrarMensaje("Usuario actualizado correctamente", "", Mensajes.INFO);
				}else {
					FuncionesGenerales.mostrarMensaje("Sucedio algo en la actualización, No se actualizo el registro", "", Mensajes.ERROR);
				}
				FuncionesGenerales.manageDialog("dlgUserActua", 1);
				usersDAO = new UsersDAO();
				obtenUsuarios();
		}
	}
	
	public void creaUsuario() throws IOException {
		UserBean userBean = new UserBean();
		userBean.setLoginId(this.loginId);
		userBean.setPersonalId(this.personalId);
		userBean.setRolId(this.tipoRol);
		userBean.setIdStatus(this.tipoStatus);
		userBean.setUserName(this.userName);
		userBean.setGivenNames(this.givenNames);
		userBean.setPassword(this.password);
		
		String paso = usersDAO.creaUsuarioDAO(userBean);
		if(paso.equals("TRUE")) {
			FuncionesGenerales.mostrarMensaje("Usuario se agrego correctamente", "", Mensajes.INFO);
		}else if(paso.equals("FALSE")){
			FuncionesGenerales.mostrarMensaje("El usuario que intentas agregar ya existe, intenta con uno diferente", "", Mensajes.WARN);
		}else if(paso.equals("NO_ADMIN")) {
			FuncionesGenerales.mostrarMensaje("Solo debe existir un usuario Administrativo", "", Mensajes.WARN);
		}
		FuncionesGenerales.manageDialog("dlgNvoUsr", 1);
		usersDAO = new UsersDAO();
		obtenUsuarios();
		//RequestContext.getCurrentInstance().update(":pnlUser");
		//response.sendRedirect(contexto+"admin/adminUser.xhtml");
		
	}
	
	
	public void deleteUser(UserBean userBean) {
		
		String paso = usersDAO.deleteUser(userBean);
		if(paso.equals("TRUE")){
			FuncionesGenerales.mostrarMensaje("Usuario se eliminó correctamente", "", Mensajes.INFO);
		}else if(paso.equals("ADMIN")){ 
			FuncionesGenerales.mostrarMensaje("El usuario Administrador no se puede eliminar", "", Mensajes.ERROR);
		}else {
			FuncionesGenerales.mostrarMensaje("ERROR: NO se elimino el usuario. Consulte conel administrador de sistema", "", Mensajes.ERROR);
		}
		usersDAO = new UsersDAO();
		obtenUsuarios();
		//RequestContext.getCurrentInstance().update("tblUser");
		
	}

	public List<UserBean> getListUser() {
		return listUser;
	}

	public void setListUser(List<UserBean> listUser) {
		this.listUser = listUser;
	}

	public List<UserBean> getFilteredUser() {
		return filteredUser;
	}

	public void setFilteredUser(List<UserBean> filteredUser) {
		this.filteredUser = filteredUser;
	}

	public UserBean getSelectedUserBean() {
		return selectedUserBean;
	}

	public void setSelectedUserBean(UserBean selectedUserBean) {
		this.selectedUserBean = selectedUserBean;
	}

	public String getGivenNames() {
		return givenNames;
	}

	public void setGivenNames(String givenNames) {
		this.givenNames = givenNames;
	}

	public int getTipoRol() {
		return tipoRol;
	}

	public void setTipoRol(int tipoRol) {
		this.tipoRol = tipoRol;
	}

	public Map<String, Integer> getMapRoles() {
		return mapRoles;
	}

	public void setMapRoles(Map<String, Integer> mapRoles) {
		this.mapRoles = mapRoles;
	}

	public int getTipoStatus() {
		return tipoStatus;
	}

	public void setTipoStatus(int tipoStatus) {
		this.tipoStatus = tipoStatus;
	}

	public Map<String, Integer> getMapStatus() {
		return mapStatus;
	}

	public void setMapStatus(Map<String, Integer> mapStatus) {
		this.mapStatus = mapStatus;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean isCheckPwd() {
		return checkPwd;
	}

	public void setCheckPwd(boolean checkPwd) {
		this.checkPwd = checkPwd;
	}

	public int getLoginId() {
		return loginId;
	}

	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}

	public int getPersonalId() {
		return personalId;
	}

	public void setPersonalId(int personalId) {
		this.personalId = personalId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
	
	

}
