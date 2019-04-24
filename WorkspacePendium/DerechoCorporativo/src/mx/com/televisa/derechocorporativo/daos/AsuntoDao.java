package mx.com.televisa.derechocorporativo.daos;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import mx.com.televisa.derechocorporativo.bean.AsuntoBean;
import mx.com.televisa.derechocorporativo.bean.EjercicioBean;
import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;
import mx.com.televisa.derechocorporativo.data.packages.PENDIUM_AGREGAR_OTROS_PKG;
import mx.com.televisa.derechocorporativo.data.packages.PENDIUM_ASUNTO_PKG;
import mx.com.televisa.derechocorporativo.data.packages.PENDIUM_EJERCICIO_SOCIAL_PKG;

public class AsuntoDao {
	private CallableStatement callState;
	private ResultSet resultSet;
	ConnectionWrapper 	puConnectionWrapper;
	
	public void createAsunto(AsuntoBean asunto){
		ConnectionWrapper tConnectionWrapper = null;
		callState = null;
		try {
			tConnectionWrapper = new ConnectionWrapper();
			callState = tConnectionWrapper.prepareCall(PENDIUM_ASUNTO_PKG.CREATE_ASUNTO_PR);
			callState.setInt(	1, asunto.getIdEmpresa());
			callState.setString(	2, asunto.getIdAsunto());
			callState.setString(3, asunto.getAsunto());
			callState.setInt(	4, asunto.getIdUser());
			callState.execute();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			tConnectionWrapper.close();
		}
	}
	
	public void createAsuntoMetaRow(AsuntoBean asunto){
		ConnectionWrapper tConnectionWrapper = null;
		callState = null;
		try {
			tConnectionWrapper = new ConnectionWrapper();
			callState = tConnectionWrapper.prepareCall(PENDIUM_ASUNTO_PKG.CREATE_ASUNTO_METAROW_PR);
			callState.setInt(	1, asunto.getIdMetaRow());
			callState.setInt(	2, asunto.getIdEmpresa());
			callState.setString(	3, asunto.getIdAsunto());
			callState.setString(4, asunto.getAsunto());
			callState.setInt(	5, asunto.getIdUser());
			callState.execute();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			tConnectionWrapper.close();
		}
	}
	
	public List<AsuntoBean> findAsuntosTemp(int idEmpresa){
		List<AsuntoBean> listAsuntos = new ArrayList<AsuntoBean>();
		ConnectionWrapper tConnectionWrapper = null;
		AsuntoBean asunto;
		callState=null;
		try{
			tConnectionWrapper = new ConnectionWrapper();
			callState = tConnectionWrapper.prepareCall(PENDIUM_ASUNTO_PKG.FIND_ASUNTO_TMP_PR);
			callState.setInt(1,idEmpresa);
			callState.registerOutParameter(2,OracleTypes.CURSOR);
			callState.execute();
			
			resultSet = ((OracleCallableStatement) callState).getCursor(2);
			
			while(resultSet.next()){
				asunto=new AsuntoBean();
				asunto.setIdAsuntoRow(resultSet.getInt("ID_ASUNTO_ROW"));
				asunto.setIdAsunto(resultSet.getString("ID_ASUNTO"));
				asunto.setAsunto(resultSet.getString("ASUNTO"));
				listAsuntos.add(asunto); 
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			tConnectionWrapper.close();
		}
		return listAsuntos;
	}
	
	public List<AsuntoBean> findAsuntosMetaRow(int idMetaRow){
		List<AsuntoBean> listAsuntos = new ArrayList<AsuntoBean>();
		ConnectionWrapper tConnectionWrapper = null;
		AsuntoBean asunto;
		callState=null;
		try{
			tConnectionWrapper = new ConnectionWrapper();
			callState = tConnectionWrapper.prepareCall(PENDIUM_ASUNTO_PKG.FIND_ASUNTO_METAROW_PR);
			callState.setInt(1,idMetaRow);
			callState.registerOutParameter(2,OracleTypes.CURSOR);
			callState.execute();
			
			resultSet = ((OracleCallableStatement) callState).getCursor(2);
			while(resultSet.next()){
				asunto=new AsuntoBean();
				asunto.setIdAsuntoRow(resultSet.getInt("ID_ASUNTO_ROW"));
				asunto.setIdAsunto(resultSet.getString("ID_ASUNTO"));
				asunto.setAsunto(resultSet.getString("ASUNTO"));
				listAsuntos.add(asunto); 
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			tConnectionWrapper.close();
		}
		return listAsuntos;
	}
	
	public boolean deleteAsunto(int idAsuntoRow){
		boolean           status                = false;
		ConnectionWrapper tConnectionWrapper = null;
		callState = null;
		try {
			tConnectionWrapper = new ConnectionWrapper();
			callState = tConnectionWrapper.prepareCall(PENDIUM_ASUNTO_PKG.DELETE_ASUNTO_PR);
			callState.setInt(1, idAsuntoRow);
			callState.execute();
			status=true;
		} catch (Exception e) {
            e.printStackTrace();
		}finally{
			tConnectionWrapper.close();
		}
		return status;
	}
	
	public AsuntoBean findOne(int idElement){
		ConnectionWrapper tConnectionWrapper = null;
		AsuntoBean asunto = new AsuntoBean();
		callState=null;
		try{
			tConnectionWrapper = new ConnectionWrapper();
			callState = tConnectionWrapper.prepareCall(PENDIUM_ASUNTO_PKG.FIND_ONE_ASUNTO);
			callState.setInt(1,idElement);
			callState.registerOutParameter(2,OracleTypes.NUMBER);
			callState.registerOutParameter(3,OracleTypes.VARCHAR);
			callState.registerOutParameter(4,OracleTypes.VARCHAR);
			callState.execute();
			
			asunto.setIdAsuntoRow(((OracleCallableStatement) callState).getInt(2));
			asunto.setIdAsunto(((OracleCallableStatement) callState).getString(3));
			asunto.setAsunto(((OracleCallableStatement) callState).getString(4));
			
		}catch (Exception e) {
            e.printStackTrace();
		}finally{
			tConnectionWrapper.close();
		}
		return asunto;
	}
	
	public boolean updateAsunto(AsuntoBean asunto){
		boolean           status                = false;
		ConnectionWrapper tConnectionWrapper = null;
		callState = null;
		try{
			tConnectionWrapper = new ConnectionWrapper();
			callState = tConnectionWrapper.prepareCall(PENDIUM_ASUNTO_PKG.UPDATE_ASUNTO);
			callState.setInt(	1, asunto.getIdAsuntoRow());
			callState.setString(2, asunto.getAsunto());
			callState.setString(3, asunto.getIdAsunto());
			callState.setInt(4, asunto.getIdUser());
			callState.execute();
			status=true;
		}catch (Exception e) {
            e.printStackTrace();
		}finally{
			tConnectionWrapper.close();
		}
		return status;
	}
	
	public boolean updateAsuntoMetaRow(int idEmpresa, int idMetaRow){
		boolean           status                = false;
		ConnectionWrapper tConnectionWrapper = null;
		callState = null;
		try{
			tConnectionWrapper = new ConnectionWrapper();
			callState = tConnectionWrapper.prepareCall(PENDIUM_ASUNTO_PKG.UPDATE_ASUNTO_METAROW);
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
	
	public boolean updateAgregarMetaRow(int idEmpresa, int idMetaRow){
		boolean           status                = false;
		ConnectionWrapper tConnectionWrapper = null;
		callState = null;
		try{
			tConnectionWrapper = new ConnectionWrapper();
			callState = tConnectionWrapper.prepareCall(PENDIUM_AGREGAR_OTROS_PKG.UPDATE_AGREGAR_METAROW);
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
			callState = tConnectionWrapper.prepareCall(PENDIUM_ASUNTO_PKG.DELETE_ALL_TEMP);
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
	
	public int countAsuntoMetaRow(int idMetaRow){
		PreparedStatement luPreparedStatement = null;
		ResultSet         luResultSet         = null;
		int totalEjercicios=0;
		try{
			 String lsSql="SELECT COUNT(*) AS TOTAL_ASUNTOS FROM PENDIUM_ASUNTO_TAB WHERE ID_META_ROW=?";
			 puConnectionWrapper = new ConnectionWrapper();
			 luPreparedStatement = puConnectionWrapper.prepareStatement(lsSql);
			 luPreparedStatement.setInt(1, idMetaRow);
			 luResultSet = luPreparedStatement.executeQuery();
			 while(luResultSet.next()){
				 totalEjercicios=luResultSet.getInt("TOTAL_ASUNTOS");				 
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
