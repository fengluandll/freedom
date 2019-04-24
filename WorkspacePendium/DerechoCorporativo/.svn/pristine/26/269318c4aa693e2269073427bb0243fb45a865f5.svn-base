package mx.com.televisa.derechocorporativo.modules.flextabs;

import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletRequest;

import mx.com.televisa.derechocorporativo.bean.CapitalFVBen;
import mx.com.televisa.derechocorporativo.daos.AgregarOtrosDAO;
import mx.com.televisa.derechocorporativo.daos.AsuntoDao;
import mx.com.televisa.derechocorporativo.daos.EjercicioSocialDao;
import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;
import mx.com.televisa.derechocorporativo.data.packages.DERCORP_CAPTURA_PKG;
import mx.com.televisa.derechocorporativo.util.FacesUtils;

import java.sql.PreparedStatement;


public class FlexTableSaveHandler {

	
	public static String handleRequest(ServletRequest request) throws UnsupportedEncodingException {

		int idEmpresa = Integer.parseInt(FacesUtils.getSessionBean().getIdCurrentEmpresa());
		String flexTabId = request.getParameter("flexTabId");
		
		
		//String idMetaRow = request.getParameter("idMetaRow_"+flexTabId);
		String idMetaRow = request.getParameter("idMetaRow");
		//System.out.println("****idMetaRow:"+idMetaRow);
		//idMetaRow="0";
		//String fieldIds = request.getParameter("fieldIds_"+flexTabId);
		String fieldIds = request.getParameter("fieldIds");
		String fieldNewValues = new String(request.getParameter("fieldNewValues").getBytes("ISO-8859-1"),"UTF-8");
		fieldIds = fieldIds.replace(flexTabId+"__", "");
		
		int linUserId =  Integer.parseInt(FacesUtils.getSessionBean().getIdUser());
		//Enumeration  params = request.getParameterNames();

		ConnectionWrapper conn 			   = null;
		Connection conexion                = null;
		CallableStatement callStmtSemaforo = null;
		PreparedStatement batchPstm		   = null;
		try {
			
			conn = new ConnectionWrapper();
			conexion = conn.getConnection();
			Statement stmt = conn.createStatement();
			
			
			StringTokenizer fieldIdsTokens = new StringTokenizer(fieldIds, "|");
			StringTokenizer fieldNewValuesTokens = new StringTokenizer(fieldNewValues, "|");
			
			
			if(idMetaRow.equals("0")) {
				
				CallableStatement callStmt = conn.prepareCall(
							"DERCORP_FLEXTAB_PKG.INSERT_PR", 4);
				
				callStmt.setString(1, Integer.toString(idEmpresa));
				callStmt.setString(2, flexTabId);
				callStmt.registerOutParameter(3, java.sql.Types.NUMERIC);
				callStmt.setInt(4, linUserId);
				
				callStmt.execute();
				
				int newId = callStmt.getInt(3);
				
				
				idMetaRow = Integer.toString(newId);
				
			}
			
			
			
			//while(params.hasMoreElements()) {
			while(fieldIdsTokens.hasMoreTokens()) {
				
				
				//String param = (String) params.nextElement();
				//String value = request.getParameter(param);
			
				String param = fieldIdsTokens.nextToken();
				
				
				if(param.indexOf("_FLEX") > 0){
					param = param.substring(0, param.indexOf("_FLEX")); 
				}
				
				String value = fieldNewValuesTokens.nextToken().replaceAll("#", "");
				
				
				/*
				String sqlUpdate = "UPDATE DERCORP_METATBL_TAB SET FIELD_CODE = 'NEW_FIELD_VALUE',  "+ 
									"NUM_LAST_UPDATED_BY = 'USER_ID', " +
									"FEC_LAST_UPDATE_DATE = SYSDATE " + 
									"WHERE ID_META_ROW = 'ID_META_ROW_VALUE'";
				
				sqlUpdate = sqlUpdate.replace("FIELD_CODE", param);
				sqlUpdate = sqlUpdate.replace("NEW_FIELD_VALUE",value);
				sqlUpdate = sqlUpdate.replace("ID_META_ROW_VALUE", idMetaRow);
				sqlUpdate = sqlUpdate.replace("USER_ID", String.valueOf(linUserId)); */
				
				String sqlUpdate = "UPDATE DERCORP_METATBL_TAB SET FIELD_CODE = ?,  "+ 
						"NUM_LAST_UPDATED_BY = ?, " +
						"FEC_LAST_UPDATE_DATE = SYSDATE " + 
						"WHERE ID_META_ROW = ?";
	
				sqlUpdate = sqlUpdate.replace("FIELD_CODE", param);

				batchPstm = conn.prepareStatement(sqlUpdate);
				batchPstm.setString(1, value);
				batchPstm.setString(2,String.valueOf(linUserId));
				batchPstm.setString(3,idMetaRow);
				
//				System.out.println(sqlUpdate);
				//stmt.addBatch(sqlUpdate);
				batchPstm.executeUpdate();
				
			}
			
//			stmt.executeBatch();	
			//Meter aqui el proceso para hacer el update a los ejercicios sociales 
			if(flexTabId.equals("20")||flexTabId.equals("21")||flexTabId.equals("22")
					||flexTabId.equals("23")||flexTabId.equals("28")||flexTabId.equals("27")||flexTabId.equals("29")
					||flexTabId.equals("30")||flexTabId.equals("31")||flexTabId.equals("32")||flexTabId.equals("33")
					||flexTabId.equals("34")||flexTabId.equals("35")||flexTabId.equals("41")){
				EjercicioSocialDao ejercicioDao=new EjercicioSocialDao();
				ejercicioDao.updateEjercicioMetaRow(idEmpresa, Integer.parseInt(idMetaRow));
			}
			//ULR 27-04-2017 enlazar asuntos temporales a un id_meta_row comites y sesion del consejo
			if(flexTabId.equals("35")||flexTabId.equals("41")){
				AsuntoDao asuntoDao = new AsuntoDao();
				asuntoDao.updateAsuntoMetaRow(idEmpresa,Integer.parseInt(idMetaRow));
			}else if(flexTabId.equals("27")){
				AgregarOtrosDAO asuntoDao = new AgregarOtrosDAO();
				asuntoDao.updateAgregarMetaRow(idEmpresa,Integer.parseInt(idMetaRow));
			}
			
			
			CallableStatement callStmtPost = conn.prepareCall("DERCORP_FLEXTAB_PKG.POST_SAVE_PR", 3);
			
			callStmtPost.setString(1, Integer.toString(idEmpresa));
			callStmtPost.setString(2, flexTabId);
			callStmtPost.setInt(3, Integer.parseInt(idMetaRow));
			
			callStmtPost.execute();
			
			//ECM 08 Septiembre 2015
			//Semaforo
			
			callStmtSemaforo = conexion.prepareCall("{CALL DERCORP_ESC_SEMA_PKG.GET_SEMAFORO_STAT_FN(?,?,?,?)}");
			callStmtSemaforo.setInt(1, idEmpresa);
			callStmtSemaforo.setInt(2, Integer.parseInt(flexTabId));
			callStmtSemaforo.setInt(3, Integer.parseInt(idMetaRow));
			callStmtSemaforo.registerOutParameter(4, java.sql.Types.VARCHAR);
			callStmtSemaforo.execute();
			String salidaR = callStmtSemaforo.getString(4);
			callStmtSemaforo.close();
			System.out.println(salidaR);
			//END SEMAFORO


			//ECM 12/Mayo/2016 - Captura - Administracion - Borrar registros de flex que no aparecen.
			callStmtSemaforo = conn.prepareCall(DERCORP_CAPTURA_PKG.BORRAR_REG_FLEX_ADM_PR);
			callStmtSemaforo.setInt(1, idEmpresa);
			callStmtSemaforo.execute();
			callStmtSemaforo.close();
			


			return "OK";
			
		} catch(Exception ex) {
			ex.printStackTrace();
			return ex.toString();
			
		} finally {
			
			ConnectionWrapper.closeAll(callStmtSemaforo, batchPstm, conexion, conn);
			/*
			try {
				callStmtSemaforo.close();
				batchPstm.close();
				conexion.close();
				ConnectionWrapper.closeAll(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}*/
			
		}
	}
	
	
	public static String handleRequestDelete(ServletRequest request) {

		String idMetaRow = request.getParameter("idMetaRow");
		int idEmpresa = Integer.parseInt(FacesUtils.getSessionBean().getIdCurrentEmpresa());
		String flexTabId = request.getParameter("flexTabId");

		ConnectionWrapper conn = null;

		Statement stmt = null;
		
		CallableStatement callStmtPost = null;
		
		CallableStatement callStmt = null;
		
		try {

			conn = new ConnectionWrapper();

			stmt = conn.createStatement();

			callStmt = conn.prepareCall(
						"DERCORP_FLEXTAB_PKG.DELETE_PR", 1);

			callStmt.setString(1, idMetaRow);
			callStmt.execute();

			System.out.println("handleRequestDelete - callStmtPost");
			System.out.println("Emp: "+Integer.toString(idEmpresa));
			System.out.println("flexTabId: " + flexTabId);

			callStmtPost = conn.prepareCall("DERCORP_FLEXTAB_PKG.POST_SAVE_PR", 3);
			callStmtPost.setString(1, Integer.toString(idEmpresa));
			callStmtPost.setString(2, flexTabId);
			callStmtPost.setInt(3, Integer.parseInt(idMetaRow));
			callStmtPost.execute();
			System.out.println("handleRequestDelete - callStmtPost - END");

			return "OK";

		} catch(Exception ex) {
			
			ex.printStackTrace();
			
			return ex.toString();
			
		} finally {
			
			ConnectionWrapper.closeAll(callStmt, callStmtPost, stmt, conn);
		}
	}
	

	//ECM 28 AGOSTO 2015
	public static String setCamposCapitalSocial(ServletRequest request){
		int 				idEmpresa = Integer.parseInt(FacesUtils.getSessionBean().getIdCurrentEmpresa());
		String 				flexTabId = request.getParameter("flexTabId");
		//System.out.println("setCamposCapitalSocial:  "+idEmpresa+" "+flexTabId);

		ConnectionWrapper 	conn = null;
		PreparedStatement	pstmt = null;
		ResultSet resultSet = null;
		CallableStatement callStmtPost = null;
		
		String lstCapSoc = "";
		String lstCapFij = "";
		String lstCapVar = "";
		String lstEstCap = "";
		
		
		try{
			conn = new ConnectionWrapper();
			callStmtPost = conn.prepareCall("DERCORP_FLEXTAB_PKG.SET_CAMPOS_CAPITAL_SOCIAL_PR", 1);

			callStmtPost.setString(1, Integer.toString(idEmpresa));
			
			callStmtPost.execute();
			
			String lstSQL = "SELECT  ("+
								        "SELECT  NVL(VAL_VALOR, '0') "+
								        "FROM    DERCORP_ADD_CAMPO_VALOR_TAB "+
								        "WHERE   1=1 "+
								        "AND     ID_EMPRESA = "+idEmpresa+" "+
								        "AND     ID_ADD_CAMPO = 541 "+
								        ")AS CAPSOC "+
								        ",( "+
								        "SELECT  NVL(VAL_VALOR, '0') "+
								        "FROM    DERCORP_ADD_CAMPO_VALOR_TAB "+
								        "WHERE   1=1 "+
								        "AND     ID_EMPRESA = "+idEmpresa+" "+
								        "AND     ID_ADD_CAMPO = 1028 "+
								        ")AS CAPFIJ "+
								        ",( "+
								        "SELECT  NVL(VAL_VALOR, '0') "+
								        "FROM    DERCORP_ADD_CAMPO_VALOR_TAB "+
								        "WHERE   1=1 "+
								        "AND     ID_EMPRESA = "+idEmpresa+" "+
								        "AND     ID_ADD_CAMPO = 1029 "+
								        ")AS CAPVAR "+
								        ",( "+
						                "SELECT  VAL_VALOR "+
						                "FROM    DERCORP_ADD_CAMPO_VALOR_TAB "+
						                "WHERE   1=1 "+
						                "AND     ID_ADD_CAMPO  = 1033 "+
						                "AND     ID_EMPRESA    = "+idEmpresa+" "+
						                ")AS ESTRUCTURA_CAPITAL "+
							"FROM    DUAL"
			;


			pstmt = conn.prepareStatement(lstSQL);
			resultSet = pstmt.executeQuery();
			
			while(resultSet.next()){
				lstCapSoc = resultSet.getString(1);
				lstCapFij = resultSet.getString(2);
				lstCapVar = resultSet.getString(3);
				lstEstCap = resultSet.getString(4);
			}
			//System.out.println("Campos Capital Social. \n"+lstCapSoc+"|"+lstCapFij+"|"+lstCapVar+"|"+lstEstCap);
			return lstCapSoc+"|"+lstCapFij+"|"+lstCapVar+"|"+lstEstCap;

		} catch(Exception ex) {
			
			ex.printStackTrace();
			return ex.toString();
			
		} finally {
			
			
			ConnectionWrapper.closeAll(resultSet, callStmtPost, pstmt, conn);
			
			/*
			try {
				callStmtPost.close();
				resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ConnectionWrapper.closeAll(conn);
			*/
		}
	}	

	
	//ECM 09 Marzo 2016
	public static String verificarEscritura(ServletRequest tRequest){

		String lsIdMetaRow = tRequest.getParameter("idMetaRow");
		String lsFlexTabId = tRequest.getParameter("flexTabId");
		int liIdEmpresa    = Integer.parseInt(FacesUtils.getSessionBean().getIdCurrentEmpresa());
		String lsOutMsg;

		ConnectionWrapper luConnectionWrapper = null;
		CallableStatement luCallableStatement = null;
		CallableStatement callStmtPost = null;

		try{
			luConnectionWrapper = new ConnectionWrapper();

			luCallableStatement = luConnectionWrapper.prepareCall("DERCORP_FLEXTAB_PKG.VERIFICAR_ESCRITURA_PR", 4);

			luCallableStatement.setInt(1, liIdEmpresa);
			luCallableStatement.setString(2, lsFlexTabId);
			luCallableStatement.setString(3, lsIdMetaRow);
			luCallableStatement.registerOutParameter(4,java.sql.Types.VARCHAR);
			luCallableStatement.execute();
			lsOutMsg = luCallableStatement.getString(4);
			luCallableStatement.close();

			System.out.println("verificarEscritura - luCallableStatement");
			System.out.println("Emp: "+Integer.toString(liIdEmpresa));
			System.out.println("flexTabId: " + lsFlexTabId);
			System.out.println("IdMetaRow: " + lsIdMetaRow);
			System.out.println("Out Msg: " + lsOutMsg);

			callStmtPost = luConnectionWrapper.prepareCall("DERCORP_FLEXTAB_PKG.POST_SAVE_PR", 3);
			callStmtPost.setString(1, Integer.toString(liIdEmpresa));
			callStmtPost.setString(2, lsFlexTabId);
			callStmtPost.setInt(3, Integer.parseInt(lsIdMetaRow));
			callStmtPost.execute();
			callStmtPost.close();

			System.out.println("verificarEscritura - callStmtPost - END");

			return lsOutMsg;

		} catch(Exception ex) {

			ex.printStackTrace();

			return ex.toString();

		} finally {

			ConnectionWrapper.closeAll(callStmtPost, luCallableStatement, luConnectionWrapper);
		}

	}
	
	//ULR 27/01/2017 para obtener el estatus de los checks de estr cap soc
	public CapitalFVBen getCheckValuesFlex(int idEmpresa){
		ConnectionWrapper luConnectionWrapper = null;
		CallableStatement luCallableStatement = null;
		CallableStatement callStmtPost = null;
		CapitalFVBen capitalFVBen = new CapitalFVBen();
		//List<Integer> checkStatusList =new ArrayList<Integer>();
		int licapVar = 0;
		int licapFij = 0;
		try{
			luConnectionWrapper = new ConnectionWrapper();

			luCallableStatement = luConnectionWrapper.prepareCall("DERCORP_FLEXTAB_PKG.GET_CHECK_CAP_FIJ_VAR_PR", 3);
			luCallableStatement.registerOutParameter(1,java.sql.Types.INTEGER);
			luCallableStatement.registerOutParameter(2,java.sql.Types.INTEGER);
			luCallableStatement.setInt(3, idEmpresa);
			luCallableStatement.execute();
			
			licapVar = luCallableStatement.getInt(1);
			licapFij = luCallableStatement.getInt(2);
			capitalFVBen.setCapFijo(licapFij);
			capitalFVBen.setCapVariable(licapVar);
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			ConnectionWrapper.closeAll(callStmtPost, luCallableStatement, luConnectionWrapper);
		}
		
		return capitalFVBen;
	}

}