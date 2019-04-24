package mx.com.televisa.derechocorporativo.daos;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import mx.com.televisa.derechocorporativo.bean.EjercicioBean;
import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;
import mx.com.televisa.derechocorporativo.data.packages.PENDIUM_EJERCICIO_SOCIAL_PKG;

public class EjercicioSocialDao {
	private final static Logger log = Logger.getLogger(EjercicioSocialDao.class);
	private CallableStatement callState;
	private ResultSet resultSet;
	ConnectionWrapper 	puConnectionWrapper;
	SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
	
	public void createEjercicioSocial(EjercicioBean ejercicio){
		boolean           status                = false;
		ConnectionWrapper tConnectionWrapper = null;
		callState = null;
		try {
			tConnectionWrapper = new ConnectionWrapper();
			callState = tConnectionWrapper.prepareCall(PENDIUM_EJERCICIO_SOCIAL_PKG.CREATE_EJERCICIOS_PR);
			callState.setInt(	1, ejercicio.getIdEmpresa());
			callState.setInt(	2, ejercicio.getEjercicioSocial());
			callState.setString(3, ejercicio.getNoDocumentum());
			callState.setString(	4, ejercicio.getFechaDocumentum());
			callState.setString(	5, ejercicio.getFechaEntrega());
			callState.setInt(	6, ejercicio.getIdUser());
			callState.setString(7, ejercicio.getTipoDoc());
			callState.execute();
			status=true;
		} catch (Exception e) {
            e.printStackTrace();
		}finally{
			tConnectionWrapper.close();
		}
	}
	
	public void createEjercicioSocialMetaRow(EjercicioBean ejercicio){
		boolean           status                = false;
		ConnectionWrapper tConnectionWrapper = null;
		callState = null;
		try {
			tConnectionWrapper = new ConnectionWrapper();
			callState = tConnectionWrapper.prepareCall(PENDIUM_EJERCICIO_SOCIAL_PKG.CREATE_EJERCICIOS_METAROW_PR);
			callState.setInt(	1, ejercicio.getIdMetaRow());
			callState.setInt(	2, ejercicio.getIdEmpresa());
			callState.setInt(	3, ejercicio.getEjercicioSocial());
			callState.setString(4, ejercicio.getNoDocumentum());
			callState.setString(	5, ejercicio.getFechaDocumentum());
			callState.setString(	6, ejercicio.getFechaEntrega());
			callState.setInt(	7, ejercicio.getIdUser());
			callState.setString(8, ejercicio.getTipoDoc());
			callState.execute();
			status=true;
		} catch (Exception e) {
            e.printStackTrace();
		}finally{
			tConnectionWrapper.close();
		}
	}
	
	public List<EjercicioBean> findEjerciciosTemp(int idEmpresa){
		List<EjercicioBean> listEjercicios = new ArrayList<EjercicioBean>();
		ConnectionWrapper tConnectionWrapper = null;
		EjercicioBean ejercicio;
		callState=null;
		try{
			tConnectionWrapper = new ConnectionWrapper();
			callState = tConnectionWrapper.prepareCall(PENDIUM_EJERCICIO_SOCIAL_PKG.FIND_EJERCIOS_TMP_PR);
			callState.setInt(1,idEmpresa);
			callState.registerOutParameter(2,OracleTypes.CURSOR);
			callState.execute();
			
			resultSet = ((OracleCallableStatement) callState).getCursor(2);
			
			while(resultSet.next()){
				ejercicio=new EjercicioBean();
				ejercicio.setIdEjercicioRow(resultSet.getInt("ID_EJERCICIO_ROW"));
				ejercicio.setEjercicioSocial(resultSet.getInt("EJERCICIO_SOCIAL"));
				ejercicio.setNoDocumentum(resultSet.getString("NO_DOCUMENTUM"));
				ejercicio.setFechaDocumentum(resultSet.getString("FECHA_DOCUMENTUM"));
				ejercicio.setFechaEntrega(resultSet.getString("FECHA_ENTREGA"));
				ejercicio.setTipoDoc(resultSet.getString("TIPO_DOCUMENT"));
				listEjercicios.add(ejercicio); 
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			tConnectionWrapper.close();
		}
		return listEjercicios;
	}
	
	public List<EjercicioBean> findEjerciciosMetaRow(int idMetaRow){
		List<EjercicioBean> listEjercicios = new ArrayList<EjercicioBean>();
		ConnectionWrapper tConnectionWrapper = null;
		EjercicioBean ejercicio;
		callState=null;
		try{
			tConnectionWrapper = new ConnectionWrapper();
			callState = tConnectionWrapper.prepareCall(PENDIUM_EJERCICIO_SOCIAL_PKG.FIND_EJERCICIOS_METAROW_PR);
			callState.setInt(1,idMetaRow);
			callState.registerOutParameter(2,OracleTypes.CURSOR);
			callState.execute();
			
			resultSet = ((OracleCallableStatement) callState).getCursor(2);
			while(resultSet.next()){
				ejercicio=new EjercicioBean();
				ejercicio.setIdEjercicioRow(resultSet.getInt("ID_EJERCICIO_ROW"));
				ejercicio.setEjercicioSocial(resultSet.getInt("EJERCICIO_SOCIAL"));
				ejercicio.setNoDocumentum(resultSet.getString("NO_DOCUMENTUM"));
				ejercicio.setFechaDocumentum(resultSet.getString("FECHA_DOCUMENTUM"));
				ejercicio.setFechaEntrega(resultSet.getString("FECHA_ENTREGA"));
				
				ejercicio.setTipoDoc(resultSet.getString("TIPO_DOCUMENT"));
				listEjercicios.add(ejercicio); 
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			tConnectionWrapper.close();
		}
		return listEjercicios;
	}
	
	public boolean deleteEjercicio(int idEjercicioRow){
		boolean           status                = false;
		ConnectionWrapper tConnectionWrapper = null;
		callState = null;
		try {
			tConnectionWrapper = new ConnectionWrapper();
			callState = tConnectionWrapper.prepareCall(PENDIUM_EJERCICIO_SOCIAL_PKG.DELETE_EJERCICIO_PR);
			callState.setInt(1, idEjercicioRow);
			callState.execute();
			status=true;
		} catch (Exception e) {
            e.printStackTrace();
		}finally{
			tConnectionWrapper.close();
		}
		return status;
	}
	
	public EjercicioBean findOne(int idElement){
		ConnectionWrapper tConnectionWrapper = null;
		EjercicioBean ejercicio = new EjercicioBean();
		callState=null;
		try{
			tConnectionWrapper = new ConnectionWrapper();
			callState = tConnectionWrapper.prepareCall(PENDIUM_EJERCICIO_SOCIAL_PKG.FIND_ONE_EJERCICIO);
			callState.setInt(1,idElement);
			callState.registerOutParameter(2,OracleTypes.NUMBER);
			callState.registerOutParameter(3,OracleTypes.NUMBER);
			callState.registerOutParameter(4,OracleTypes.VARCHAR);
			callState.registerOutParameter(5,OracleTypes.VARCHAR);
			callState.registerOutParameter(6,OracleTypes.VARCHAR);
			callState.registerOutParameter(7,OracleTypes.VARCHAR);
			callState.execute();
			
			ejercicio.setIdEjercicioRow(((OracleCallableStatement) callState).getInt(2));
			ejercicio.setEjercicioSocial(((OracleCallableStatement) callState).getInt(3));
			ejercicio.setNoDocumentum(((OracleCallableStatement) callState).getString(4));
			ejercicio.setFechaDocumentum(((OracleCallableStatement) callState).getString(5));
			ejercicio.setFechaEntrega(((OracleCallableStatement) callState).getString(6));
			ejercicio.setTipoDoc(((OracleCallableStatement) callState).getString(7));
			
		}catch (Exception e) {
            e.printStackTrace();
		}finally{
			tConnectionWrapper.close();
		}
		return ejercicio;
	}
	
	public boolean updateEjercicio(EjercicioBean ejercicio){
		boolean           status                = false;
		ConnectionWrapper tConnectionWrapper = null;
		callState = null;
		try{
			tConnectionWrapper = new ConnectionWrapper();
			callState = tConnectionWrapper.prepareCall(PENDIUM_EJERCICIO_SOCIAL_PKG.UPDATE_EJERCICIO);
			callState.setInt(	1, ejercicio.getIdEjercicioRow());
			callState.setString(2, ejercicio.getTipoDoc());
			callState.setString(3, ejercicio.getNoDocumentum());
			callState.setString(	4, ejercicio.getFechaDocumentum());
			callState.setString(	5, ejercicio.getFechaEntrega());
			callState.setInt(	6, ejercicio.getIdUser());
			callState.execute();
			status=true;
		}catch (Exception e) {
            e.printStackTrace();
		}finally{
			tConnectionWrapper.close();
		}
		return status;
	}
	
	public boolean updateEjercicioMetaRow(int idEmpresa, int idMetaRow){
		boolean           status                = false;
		ConnectionWrapper tConnectionWrapper = null;
		callState = null;
		try{
			tConnectionWrapper = new ConnectionWrapper();
			callState = tConnectionWrapper.prepareCall(PENDIUM_EJERCICIO_SOCIAL_PKG.UPDATE_EJERCICIO_METAROW);
			callState.setInt(	1, idMetaRow);
			callState.setInt(	2, idEmpresa);
			callState.execute();
			status=true;
		}catch (Exception e) {
            e.printStackTrace();
		}finally{
			tConnectionWrapper.close();
		}
		return status;
	}
	
	public boolean deleteAllTemp(int idEmpresa){
		boolean           status                = false;
		ConnectionWrapper tConnectionWrapper = null;
		callState = null;
		try {
			tConnectionWrapper = new ConnectionWrapper();
			callState = tConnectionWrapper.prepareCall(PENDIUM_EJERCICIO_SOCIAL_PKG.DELETE_ALL_TEMP);
			callState.setInt(1, idEmpresa);
			callState.execute();
			status=true;
		} catch (Exception e) {
            e.printStackTrace();
		}finally{
			tConnectionWrapper.close();
		}
		return status;
	}
	
	public int countEjercicioMetaRow(int idMetaRow){
		PreparedStatement luPreparedStatement = null;
		ResultSet         luResultSet         = null;
		int totalEjercicios=0;
		try{
			 String lsSql="SELECT COUNT(*) AS TOTAL_EJERCICIOS FROM PENDIUM_EJERCICIO_SOCIAL_TAB WHERE ID_META_ROW=?";
			 puConnectionWrapper = new ConnectionWrapper();
			 luPreparedStatement = puConnectionWrapper.prepareStatement(lsSql);
			 luPreparedStatement.setInt(1, idMetaRow);
			 luResultSet = luPreparedStatement.executeQuery();
			 while(luResultSet.next()){
				 totalEjercicios=luResultSet.getInt("TOTAL_EJERCICIOS");				 
			 }
		}catch (Exception e) {
			e.printStackTrace();

		}finally {
			try {
				luResultSet.close();
				luPreparedStatement.close();
				puConnectionWrapper.close();

			} catch (SQLException e) {
				e.printStackTrace();

			}
		}
		return totalEjercicios;
	}
}
