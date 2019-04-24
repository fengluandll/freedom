package mx.com.televisa.derechocorporativo.daos;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import mx.com.televisa.derechocorporativo.bean.AgregarOtrosBean;
import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;
import mx.com.televisa.derechocorporativo.data.packages.PENDIUM_AGREGAR_OTROS_PKG;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

public class AgregarOtrosDAO {

	
	private CallableStatement 	callState;
	private ResultSet 			resultSet;
	ConnectionWrapper 			puConnectionWrapper;
	private final static Logger log = Logger.getLogger(AgregarOtrosDAO.class);
	
	public void createAgregar(AgregarOtrosBean asunto){
		ConnectionWrapper tConnectionWrapper = null;
		callState = null;
		try {
			tConnectionWrapper = new ConnectionWrapper();
			callState = tConnectionWrapper.prepareCall(PENDIUM_AGREGAR_OTROS_PKG.CREATE_AGREGAR_PR);
			callState.setInt(	1, asunto.getIdEmpresa());
			callState.setString(	2, asunto.getIdAgregar());
			callState.setString(3, asunto.getAgregar());
			callState.setInt(	4, asunto.getIdUser());
			callState.execute();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			tConnectionWrapper.close();
		}
	}
	
	public void createAgregarMetaRow(AgregarOtrosBean asunto){
		ConnectionWrapper tConnectionWrapper = null;
		callState = null;
		try {
			tConnectionWrapper = new ConnectionWrapper();
			callState = tConnectionWrapper.prepareCall(PENDIUM_AGREGAR_OTROS_PKG.CREATE_AGREGAR_METAROW_PR);
			callState.setInt(	1, asunto.getIdMetaRow());
			callState.setInt(	2, asunto.getIdEmpresa());
			callState.setString(	3, asunto.getIdAgregar());
			callState.setString(4, asunto.getAgregar());
			callState.setInt(	5, asunto.getIdUser());
			callState.execute();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			tConnectionWrapper.close();
		}
	}
	
	public List<AgregarOtrosBean> findAsuntosTemp(int idEmpresa){
		List<AgregarOtrosBean> listAsuntos = new ArrayList<AgregarOtrosBean>();
		ConnectionWrapper tConnectionWrapper = null;
		AgregarOtrosBean asunto;
		callState=null;
		try{
			tConnectionWrapper = new ConnectionWrapper();
			callState = tConnectionWrapper.prepareCall(PENDIUM_AGREGAR_OTROS_PKG.FIND_AGREGAR_TMP_PR);
			callState.setInt(1,idEmpresa);
			callState.registerOutParameter(2,OracleTypes.CURSOR);
			callState.execute();
			
			resultSet = ((OracleCallableStatement) callState).getCursor(2);
			
			while(resultSet.next()){
				asunto=new AgregarOtrosBean();
				asunto.setIdAgregarRow(resultSet.getInt("ID_AGREGAR_ROW"));
				asunto.setIdAgregar(resultSet.getString("ID_AGREGAR"));
				asunto.setAgregar(resultSet.getString("AGREGAR"));
				listAsuntos.add(asunto); 
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			tConnectionWrapper.close();
		}
		return listAsuntos;
	}
	
	public List<AgregarOtrosBean> findAsuntosMetaRow(int idMetaRow){
		List<AgregarOtrosBean> listAsuntos = new ArrayList<AgregarOtrosBean>();
		ConnectionWrapper tConnectionWrapper = null;
		AgregarOtrosBean asunto;
		callState=null;
		try{
			tConnectionWrapper = new ConnectionWrapper();
			callState = tConnectionWrapper.prepareCall(PENDIUM_AGREGAR_OTROS_PKG.FIND_AGREGAR_METAROW_PR);
			callState.setInt(1,idMetaRow);
			callState.registerOutParameter(2,OracleTypes.CURSOR);
			callState.execute();
			
			resultSet = ((OracleCallableStatement) callState).getCursor(2);
			while(resultSet.next()){
				asunto=new AgregarOtrosBean();
				asunto.setIdAgregarRow(resultSet.getInt("ID_AGREGAR_ROW"));
				asunto.setIdAgregar(resultSet.getString("ID_AGREGAR"));
				asunto.setAgregar(resultSet.getString("AGREGAR"));
				listAsuntos.add(asunto); 
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			tConnectionWrapper.close();
		}
		return listAsuntos;
	}
	
	public boolean deleteAgregar(int idAsuntoRow){
		boolean           status                = false;
		ConnectionWrapper tConnectionWrapper = null;
		callState = null;
		try {
			tConnectionWrapper = new ConnectionWrapper();
			callState = tConnectionWrapper.prepareCall(PENDIUM_AGREGAR_OTROS_PKG.DELETE_AGREGAR_PR);
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
	
	public AgregarOtrosBean findOne(int idElement){
		ConnectionWrapper tConnectionWrapper = null;
		AgregarOtrosBean asunto = new AgregarOtrosBean();
		callState=null;
		try{
			tConnectionWrapper = new ConnectionWrapper();
			callState = tConnectionWrapper.prepareCall(PENDIUM_AGREGAR_OTROS_PKG.FIND_ONE_AGREGAR);
			callState.setInt(1,idElement);
			callState.registerOutParameter(2,OracleTypes.NUMBER);
			callState.registerOutParameter(3,OracleTypes.VARCHAR);
			callState.registerOutParameter(4,OracleTypes.VARCHAR);
			callState.execute();
			
			asunto.setIdAgregarRow(((OracleCallableStatement) callState).getInt(2));
			asunto.setIdAgregar(((OracleCallableStatement) callState).getString(3));
			asunto.setAgregar(((OracleCallableStatement) callState).getString(4));
			
		}catch (Exception e) {
            e.printStackTrace();
		}finally{
			tConnectionWrapper.close();
		}
		return asunto;
	}
	
	public boolean updateAgregar(AgregarOtrosBean asunto){
		boolean           status                = false;
		ConnectionWrapper tConnectionWrapper = null;
		callState = null;
		try{
			tConnectionWrapper = new ConnectionWrapper();
			callState = tConnectionWrapper.prepareCall(PENDIUM_AGREGAR_OTROS_PKG.UPDATE_AGREGAR);
			callState.setInt(	1, asunto.getIdAgregarRow());
			callState.setString(2, asunto.getAgregar());
			callState.setString(3, asunto.getIdAgregar());
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
			callState = tConnectionWrapper.prepareCall(PENDIUM_AGREGAR_OTROS_PKG.DELETE_ALL_TEMP);
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
			 String lsSql="SELECT COUNT(*) AS TOTAL_AGREGAR FROM PENDIUM_AGREGAR_TAB WHERE ID_META_ROW=?";
			 puConnectionWrapper = new ConnectionWrapper();
			 luPreparedStatement = puConnectionWrapper.prepareStatement(lsSql);
			 luPreparedStatement.setInt(1, idMetaRow);
			 luResultSet = luPreparedStatement.executeQuery();
			 while(luResultSet.next()){
				 totalEjercicios=luResultSet.getInt("TOTAL_AGREGAR");				 
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
