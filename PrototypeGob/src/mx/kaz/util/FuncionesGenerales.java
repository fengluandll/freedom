package mx.kaz.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;

import mx.kaz.daos.UsersDAO;
import mx.kaz.model.ConectionWrapper;

/**
 * @author Argumel
 *
 */
public class FuncionesGenerales {
	
	private static ConectionWrapper conectionWrapper;
	private static Connection con;
	private static PreparedStatement psmt;
	private static ResultSet rs;
	private static org.apache.log4j.Logger logger = Logger.getLogger(UsersDAO.class);

	
	public FuncionesGenerales() {
		
	}
	
    /**
     * 
     * @param nomdialog nombre del dialogo
     * @param action 1 ocultar, 2 mostrar
     */
   public static void manageDialog(String nomdialog,int action){
		RequestContext context = RequestContext.getCurrentInstance();
		
		if(action == 1){
			context.execute("PF('"+nomdialog+"').hide();");
		}else if(action == 2){
			context.execute("PF('"+nomdialog+"').show();");
		}
		
   }
   
   /**
    * 
    * @param titulo
    * @param mensaje
    * @param tipoMensaje INFO,ERROR,FATAL,WARN
    */
   public static void mostrarMensaje(String titulo,String mensaje,Mensajes tipoMensaje){
	   
	   switch (tipoMensaje) {
		case INFO:
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,titulo,mensaje));	
			break;
		case FATAL:
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_FATAL,titulo,mensaje));
			break;
		case ERROR:
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,titulo,mensaje));
			break;
		case WARN:
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN,titulo,mensaje));
			break;
		case NULL:
			FacesContext.getCurrentInstance().addMessage(null,null);
			break;
		}
	   
   }
   
	/**
	 * Metodo que convierte una fecha de tipo java.util.Date a Strin
	 * @param fecha en java.util.Date
	 * @return regresa la fecha en String
	 */
	public static String dateToString(java.util.Date fecha){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(fecha);
	}  
	
	/**
	 * 
	 * @param sql Query empezando con el campo del id
	 * @return el hashmap con los resultados
	 */
	public static Map<String,Integer> creaCombo(String sql){
		Map<String,Integer> mapGral = new HashMap<String,Integer>();
		try {
			conectionWrapper = new ConectionWrapper();
			con = conectionWrapper.getConexion();
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			int id		= 0;
			String nom 	= "";
			while(rs.next()) {
				id =  rs.getInt(1);
				nom = rs.getString(2);
				mapGral.put(nom, id);
			}
		} catch (NamingException | SQLException e) {
			logger.error(e);
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				psmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				logger.error(e);
			}
		}
		return mapGral;
	}
	
}
