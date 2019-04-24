package mx.com.televisa.derechocorporativo.modules.home;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mx.com.televisa.derechocorporativo.bean.ClasificacionBean;
import mx.com.televisa.derechocorporativo.bean.CriterioBusquedaBean;
import mx.com.televisa.derechocorporativo.bean.PaisBean;
import mx.com.televisa.derechocorporativo.daos.ClasificacionDAO;
import mx.com.televisa.derechocorporativo.daos.CriterioBusquedaDAO;
import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;
import mx.com.televisa.derechocorporativo.model.Empresa;
import mx.com.televisa.derechocorporativo.modules.captura.Pagina;
//import mx.com.televisa.derechocorporativo.modules.captura.Seccion;
//import mx.com.televisa.derechocorporativo.data.HQLBeanList;
//import mx.com.televisa.derechocorporativo.model.VAllowedSection;
//import mx.com.televisa.derechocorporativo.util.FacesUtils;
import mx.com.televisa.derechocorporativo.util.FacesUtils;

public class MenuBean {

 private List<Empresa> empresas;
 ClasificacionDAO clasificacionDAO;
 
 public String getClasificacionChecks(){
	 int cont = 0;
	 clasificacionDAO = new ClasificacionDAO();
	 List<ClasificacionBean> listClasi = clasificacionDAO.dameClasificacionChecks();
	 StringBuilder sb = new StringBuilder();
	
	 sb.append("<table width=\"100%\" style=\"text-align:left;\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">");
	 
	 for(ClasificacionBean cla : listClasi){
		 cont ++;
		 if(cont % 3 != 0){
			 /*
			 if(cont % 2 != 0){
				 sb.append("<td width='10%'>");
				 sb.append("<span ></span></td>");
			 }*/
			 
			 sb.append("<td width=\"45%\"><label><input type='checkbox' "+
			           " name='chk' id='"+cla.getIdCatalogoValor() + "' value='"+cla.getIdCatalogoValor()+"'/>"  + cla.getValCatVal() + "</label>");
			 sb.append("</td>");
		 }else if(cont % 3 == 0){
			 if(sb.toString().indexOf("<tr>") > -1){
				 sb.append("</tr>");
			 }
			 sb.append("<tr>\n");
			 /*
			 if(cont % 2 != 0){
				 
				 sb.append("<td>");
				 sb.append("<span ></span></td>");
			 }
			 */
			 
			 sb.append("<td width=\"45%\"><label><input type='checkbox' "+
			           " name='chk' id='"+cla.getIdCatalogoValor() + "' value='"+cla.getIdCatalogoValor()+"'/>" + cla.getValCatVal() + "</label>");
			 sb.append("</td>");
			 cont ++;
		 }
	 }
	 sb.append("</table>");
	 return sb.toString();
 }
 
 public String getCatalogoChecks(int idCatalog){
	 int cont = 0;
	 clasificacionDAO = new ClasificacionDAO();
	 List<ClasificacionBean> listClasi = clasificacionDAO.dameCatalogoChecks(idCatalog);
	 StringBuilder sb = new StringBuilder();
	
	 sb.append("<table width=\"100%\" style=\"text-align:left;\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">");
	 
	 for(ClasificacionBean cla : listClasi){
		 cont ++;
		 if(cont % 4 != 0){
			 /*
			 if(cont % 2 != 0){
				 sb.append("<td width='10%'>");
				 sb.append("<span ></span></td>");
			 }*/
			 
			 sb.append("<td width=\"45%\"><label><input type='checkbox' "+
			           " name='chk_"+idCatalog+"' id='"+cla.getIdCatalogoValor() + "' value='"+cla.getIdCatalogoValor()+"'/>"  + cla.getValCatVal() + "</label>");
			 sb.append("</td>");
		 }else if(cont % 4 == 0){
			 if(sb.toString().indexOf("<tr>") > -1){
				 sb.append("</tr>");
			 }
			 sb.append("<tr>\n");
			 /*
			 if(cont % 2 != 0){
				 
				 sb.append("<td>");
				 sb.append("<span ></span></td>");
			 }
			 */
			 
			 sb.append("<td width=\"45%\"><label><input type='checkbox' "+
			           " name='chk_"+idCatalog+"' id='"+cla.getIdCatalogoValor() + "' value='"+cla.getIdCatalogoValor()+"'/>" + cla.getValCatVal() + "</label>");
			 sb.append("</td>");
			 cont ++;
		 }
	 }
	 sb.append("</table>");
	 return sb.toString();
 }
 
 public String getPaises(){
	 clasificacionDAO = new ClasificacionDAO();
	 List<PaisBean> listPais = clasificacionDAO.damePaises();
	 StringBuilder sb = new StringBuilder();
	 sb.append("<select name='pai' style='width: 180px;'>");
	 sb.append("<option value='0'>Todos</option>");
	 for(PaisBean pai : listPais){
		sb.append("<option value='"+pai.getIdCatalogoValor()+"'>"+pai.getValCatVal()+"</option>");
	 }
	 sb.append("</select>");
	 return sb.toString();
 }
 
 public String getMenuEmpresas() throws Exception {
  
  StringBuilder sb = new StringBuilder();
 
  try {
 
   for (Empresa empresa : empresas) {

    sb.append("<a href='" + FacesUtils.getContextPath()  + 
      "/faces/jsp/captura/capMain.jsp?idEmp="  +empresa.getIdEmpresa() +
      								  "&NomEmp=" +empresa.getNomEmpresa()+
      "' target='contentFrame' onClick=\"minimizeMenu()\">");
    
    sb.append(empresa.getNomEmpresa());
    
    sb.append("</a><br><br>");
    
   }
   
   return sb.toString();

  } catch (Exception ex) {

   // TODO - Pendiente - Gestion de Excepciones

   
   ex.printStackTrace();
   
   
   return ex.toString();
  } 
 }

 public String getMenuEmpresasV2(String contextPath) throws Exception {
	  
	  StringBuilder sb = new StringBuilder();
	 
	  try {
	 
	   for (Empresa empresa : empresas) {

	   
	    sb.append("<a href='" + contextPath + 
	    	      "/faces/jsp/captura/capMain.jsp?idEmp="  +empresa.getIdEmpresa() +
					  "&NomEmp=" +empresa.getNomEmpresa()+
	    		  "' target='contentFrame' onClick=\"minimizeMenu()\">");
	    
	    sb.append(empresa.getNomEmpresa());
	    
	    sb.append("</a><br><br>");
	    
	   }
	   
	   return sb.toString();

	  } catch (Exception ex) {

	   // TODO - Pendiente - Gestion de Excepciones

	   
	   ex.printStackTrace();
	   
	   
	   return ex.toString();
	  } 
	 }
 
 /**
  * Construye arbol de empresas
  * @throws Exception
  */
 public MenuBean() throws Exception {
  /*
  empresas = new ArrayList<Empresa>();
  ConnectionWrapper conn = null;
  HttpSession session = FacesUtils.getSession();
  String emp = (String) session.getAttribute("empBusca");
  ResultSet set = null;
  PreparedStatement psmt = null;
  try {
   conn = new ConnectionWrapper();
   String sqlEmpresas     = null;
   if(emp == null){
    sqlEmpresas  = "SELECT * FROM DERCORP_EMPRESA_TAB WHERE 1 = 1 ";
    psmt = conn.prepareStatement(sqlEmpresas);
   }else{
    sqlEmpresas = "SELECT NOM_EMPRESA, ID_EMPRESA from DERCORP_EMPRESA_TAB WHERE UPPER(NOM_EMPRESA) like UPPER(?)";
    psmt = conn.prepareStatement(sqlEmpresas);
    psmt.setString(1, "%" + emp + "%");
   }
   
   set = psmt.executeQuery();
   while (set.next()) {
    empresas.add(new Empresa(set, set.getMetaData()));
   }

  } catch (Exception ex) {
   ex.printStackTrace();   
  } finally {
	  set.close();
	  psmt.close();
	  ConnectionWrapper.closeAll(conn);
  }
  session.removeAttribute("empBusca");
  */
 }
 
 
 public String getMainMenu(){

	  ConnectionWrapper conn = null;

	  String rolId = FacesUtils.getSessionBean().getIdRol();
	  
	  try {
		    conn = new ConnectionWrapper();
		    String sql = "SELECT SS_MENU_PKG.GET_MENU(" + rolId + ") FROM DUAL";


		    ResultSet set = conn.createStatement().executeQuery(sql);
		    
		    while (set.next()) {
		    	
		    	return set.getString(1);
		    }

		    return null;
		    
	  } catch (Exception ex) {
		  ex.printStackTrace();   
		  return null;
	  } finally {
		  ConnectionWrapper.closeAll(conn);
	  }
 }
 
 
 
 
 public String cargaInicial(){
	 CriterioBusquedaDAO busquedaDAO = new CriterioBusquedaDAO();
	 List<CriterioBusquedaBean> lsitBusq = busquedaDAO.cargaInicial();
	 StringBuilder sb = new StringBuilder();
	 for(CriterioBusquedaBean cb : lsitBusq){
		 sb.append("<a href='" + FacesUtils.getContextPath() + 
	    	      "/faces/jsp/captura/capMain.jsp?idEmp="  +cb.getIdEmpresa() +
					  "&NomEmp=" +cb.getDenomActual()+
	    		  "' target='contentFrame' onClick=\"minimizeMenu()\">");
	    sb.append(cb.getDenomActual());
	    sb.append("</a><br><br>");
	 }
	 return sb.toString();
 }
 
 
 //ECM 25 AGOSTO 2015
 public String cargaInicialCon(){
	 CriterioBusquedaDAO busquedaDAO = new CriterioBusquedaDAO();
	 List<CriterioBusquedaBean> lsitBusq = busquedaDAO.cargaInicial();
	 StringBuilder sb = new StringBuilder();
	 for(CriterioBusquedaBean cb : lsitBusq){
		 sb.append("<a href='" + FacesUtils.getContextPath() + 
	    	      "/faces/jsp/captura/capMain.jsp?idEmp="  +cb.getIdEmpresa() +"&Edit=disabled"+
				  "&NomEmp=" +cb.getDenomActual()+
	    		  "' target='contentFrame' onClick=\"minimizeMenu()\">");
	    sb.append(cb.getDenomActual());
	    sb.append("</a><br><br>");
	 }
	 return sb.toString();
 }
 
 
    public String cargaMenu(CriterioBusquedaBean criterioBusquedaBean,String contextPath) throws Exception {
	 StringBuilder sb = new StringBuilder();
	 	CriterioBusquedaDAO busquedaDAO = new CriterioBusquedaDAO();
	 	List<CriterioBusquedaBean> listBusqueda = busquedaDAO.dameBusqueda(criterioBusquedaBean);
	   for(CriterioBusquedaBean cb : listBusqueda){
		   sb.append("<a href='" + contextPath + 
		    	      "/faces/jsp/captura/capMain.jsp?idEmp="  +cb.getIdEmpresa() +
						  "&NomEmp=" +cb.getDenomActual()+
		    		  "' target='contentFrame' onClick=\"minimizeMenu()\">");
		    
		    sb.append(cb.getDenomActual());
		    
		    sb.append("</a><br><br>");
	   }
	   return sb.toString();
    }

    //ECM 15 Octubre 2015
    public String cargaMenuCon(CriterioBusquedaBean criterioBusquedaBean,String contextPath) throws Exception {
   	 StringBuilder sb = new StringBuilder();
   	 	CriterioBusquedaDAO busquedaDAO = new CriterioBusquedaDAO();
   	 	List<CriterioBusquedaBean> listBusqueda = busquedaDAO.dameBusqueda(criterioBusquedaBean);
   	   for(CriterioBusquedaBean cb : listBusqueda){
   		   sb.append("<a href='" + contextPath + 
   		    	      "/faces/jsp/captura/capMain.jsp?idEmp="  +cb.getIdEmpresa() +
   						  "&NomEmp=" +cb.getDenomActual()+"&Edit=disabled"+
   		    		  "' target='contentFrame' onClick=\"minimizeMenu()\">");
   		    
   		    sb.append(cb.getDenomActual());
   		    
   		    sb.append("</a><br><br>");
   	   }
   	   return sb.toString();
   	 }

 public static void setStatusPestañaCero() {
	 	Connection tuConnect 				= null;
		java.sql.PreparedStatement luPreparedStatement = null;
		String lsUpdate						  = "";
		
		try{
			ConnectionWrapper puConnectionWrapper = new ConnectionWrapper();
			tuConnect = puConnectionWrapper.getConnection();
			lsUpdate = "UPDATE DERCORP_CONTROL_SECCION SET 	   STATUS = ?\n"+
	                   "                                       WHERE"+
	                   "									   ID_USER = ?";
			luPreparedStatement = tuConnect.prepareStatement(lsUpdate);
			luPreparedStatement.setInt(1, 0);
			luPreparedStatement.setInt(2, Integer.parseInt(FacesUtils.getSessionBean().getIdUser()));
			luPreparedStatement.executeUpdate();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try{
				tuConnect.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
 }
 public MenuBean(String emp) throws Exception {
	  
	  empresas = new ArrayList<Empresa>();
	  ConnectionWrapper conn = null;
	  

	  try {
		    conn = new ConnectionWrapper();
		    String sqlEmpresas     = null;
		    PreparedStatement psmt = null;
		    sqlEmpresas = "SELECT * from DERCORP_EMPRESA_TAB WHERE UPPER(NOM_EMPRESA) like UPPER(?)";
		    psmt = conn.prepareStatement(sqlEmpresas);
		    psmt.setString(1, "%" + emp + "%");
		    ResultSet set = psmt.executeQuery();
		    while (set.next()) {
		    	empresas.add(new Empresa(set, set.getMetaData()));
		    }

	  } catch (Exception ex) {
		  ex.printStackTrace();   
	  } finally {
		  ConnectionWrapper.closeAll(conn);
	  }
	  
	 }
 
 public List<Empresa> getEmpresas() {
  return empresas;
 }

 public void setEmpresas(List<Empresa> empresas) {
  this.empresas = empresas;
 }
 
 public void unblock(){
	 	
		Pagina.unlockUserCap();
		
		FacesUtils.getSession().setAttribute("alertUnblock", 1);
 }
}