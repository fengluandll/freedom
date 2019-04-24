package mx.com.televisa.derechocorporativo.modules.flextabs;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;
import mx.com.televisa.derechocorporativo.data.packages.XXTV_CAPITAL_SOC_PKG;
import mx.com.televisa.derechocorporativo.modules.dynhtml.SelectList;

public class EstructuraCapitalSocial {
	
	public static int ECS_FLEX_TABLE_ID = 7;
	

	
	boolean aplicaCapitalFijo;
	boolean aplicaCapitalVariable;
	String formatoAccionesCapitalFijoVariable;
	String textoAccionesCapitalFijo;
	String textoAccionesCapitalVariable;
	String textoTotal;
	boolean habilitarValorCaptura;
	String accionista;
	
	
	
	public EstructuraCapitalSocial(ConnectionWrapper conn, String idEmpresa) {

		try {
			
			CallableStatement callStmt = conn.prepareCall(XXTV_CAPITAL_SOC_PKG.GET_VARIABLES_PR);
			
			callStmt.setString(1, idEmpresa);
			callStmt.registerOutParameter(2, java.sql.Types.NUMERIC);
			callStmt.registerOutParameter(3, java.sql.Types.NUMERIC);
			callStmt.registerOutParameter(4, java.sql.Types.VARCHAR);
			callStmt.registerOutParameter(5, java.sql.Types.VARCHAR);
			callStmt.registerOutParameter(6, java.sql.Types.VARCHAR);
			callStmt.registerOutParameter(7, java.sql.Types.VARCHAR);
			callStmt.registerOutParameter(8, java.sql.Types.NUMERIC);
			callStmt.registerOutParameter(9, java.sql.Types.VARCHAR);
			
			callStmt.execute();
			
			aplicaCapitalFijo = (callStmt.getInt(2) == 1);
			aplicaCapitalVariable = (callStmt.getInt(3) == 1);
			formatoAccionesCapitalFijoVariable = callStmt.getString(4);
			textoAccionesCapitalFijo = callStmt.getString(5);
			textoAccionesCapitalVariable = callStmt.getString(6);
			textoTotal = callStmt.getString(7);
			habilitarValorCaptura = (callStmt.getInt(8) == 1);
			accionista = callStmt.getString(9);
			
		} catch(Exception ex) {
			
			ex.printStackTrace();
			
		} finally {
			
			//ConnectionWrapper.closeAll(conn);
		}
		
	}
	
	/**
	 * 
	 */
	public ArrayList<FlexColumn> customizeFlexColumns(ArrayList<FlexColumn> flexColumns) {
		
		return customizeFlexColumns(flexColumns, false);
	}
	
	
	/**
	 * 
	 */
	public ArrayList<FlexColumn> customizeFlexColumns(ArrayList<FlexColumn> flexColumns, boolean modoConfiguracioDeReportes) {
		
		ArrayList<FlexColumn> newFlexColumnsList = new ArrayList<FlexColumn>(); 
		
		for (FlexColumn flexColumn : flexColumns) {
			
			boolean addElement = true;

			//ECM 01 Septiembre 2015
			if(flexColumn.COD_FLEX_COLUM.equals("VAL_C1")) {
				flexColumn.NOM_FLEX_COLUM = accionista;
			}


			if(flexColumn.COD_FLEX_COLUM.equals("VAL_C3")) {
				
				if(!aplicaCapitalFijo) {
					
					//flexColumns.remove(flexColumn);
					
					addElement = false;
					
				} else {
					
					flexColumn.DES_FORMAT = formatoAccionesCapitalFijoVariable;
					flexColumn.NOM_FLEX_COLUM = textoAccionesCapitalFijo;
				}
			}
			
			if(flexColumn.COD_FLEX_COLUM.equals("VAL_C4")) {
				
				if(!aplicaCapitalVariable) {
					
					//flexColumns.remove(flexColumn);
					
					addElement = false;
					
				} else {
					
					flexColumn.DES_FORMAT = formatoAccionesCapitalFijoVariable;
					flexColumn.NOM_FLEX_COLUM = textoAccionesCapitalVariable;
				}
			}
			
			
			if(flexColumn.COD_FLEX_COLUM.equals("VAL_C6")) {
				
				
				
				if(!habilitarValorCaptura) {
					flexColumn.DES_TIPO_COLUM = "DISABLED_TEXT";
				}
				
				flexColumn.DES_FORMAT = formatoAccionesCapitalFijoVariable;
				flexColumn.NOM_FLEX_COLUM = textoTotal;
			}
			
			
			if(addElement) {
				
				newFlexColumnsList.add(flexColumn);
			}
			
			
		}
		
		
		return newFlexColumnsList;
		
	}
	
	
	
	public static StringBuilder getTableSubHeader(ArrayList<FlexColumn> flexColumns, ConnectionWrapper conn, String lstIdEmpresa, boolean showEditableCombosECS, String specificFlexColumnsList) {
		
		StringBuilder sb = new StringBuilder();
		
		String lstForFijo 		= "onchange='javascript:cambiaComboCapitalFijo(this);'";
		String lstForVariable 	= "onchange='javascript:cambiaComboCapitalVariable(this);'";
		sb.append("<tr>");

			if(specificFlexColumnsList == null || specificFlexColumnsList.equals("") || specificFlexColumnsList.contains("93")) {
				sb.append("<td>");
				sb.append("</td>");
			}
		
			if(specificFlexColumnsList == null || specificFlexColumnsList.equals("") || specificFlexColumnsList.contains("22")) {
				sb.append("<td>");
				sb.append("</td>");
			}
			
			if(flexColumns.contains(new FlexColumn("VAL_C3"))) { 
			
				sb.append("<td><div id='DIVC24'>");
					
				if(showEditableCombosECS) {
				
					sb.append(SelectList.getSelectList(conn,"C24","90",lstForFijo,FlexTable.getValValor(conn, lstIdEmpresa, "523"), 21));
					
				} else {
					
					sb.append(SelectList.getCatalogValue(conn, 21, FlexTable.getValValor(conn, lstIdEmpresa, "523")));
				}
				sb.append("</div></td>");
			}
			
			if(flexColumns.contains(new FlexColumn("VAL_C4"))) {
				
				sb.append("<td><div id='DIVC25'>");
				
				if(showEditableCombosECS) {
					
					sb.append(SelectList.getSelectList(conn,"C25","90",lstForVariable,FlexTable.getValValor(conn, lstIdEmpresa, "524"), 21));
					
				} else {
					
					sb.append(SelectList.getCatalogValue(conn, 21, FlexTable.getValValor(conn, lstIdEmpresa, "524")));
				}
				
				sb.append("</div></td>");
			}
			
			if(specificFlexColumnsList == null || specificFlexColumnsList.equals("") || specificFlexColumnsList.contains("26")) {
				sb.append("<td>");
				sb.append("</td>");
			}
			
		sb.append("</tr>");
		
		return sb;
	}
	
	public static int isAsociacionCivil(int idEmpresa){
		ConnectionWrapper connectionWrapper = null;
		Connection 		  con 				= null;
		PreparedStatement psmt				= null;
		ResultSet		  rs				= null;
		int 			  isAC				= 0;
		String sql = "SELECT USRDRC.XXTV_CAPITAL_SOC_PKG.GET_TIPO_SOCIEDAD_FN(?) FROM dual";
		try {
			connectionWrapper = new ConnectionWrapper();
			con = connectionWrapper.getConnection();
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, idEmpresa);
			rs = psmt.executeQuery();
			while(rs.next()){
				isAC = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				psmt.close();
				con.close();
				connectionWrapper.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return isAC;
	}
	
	public static String getTipoSoc(int idEmpresa){
		ConnectionWrapper connectionWrapper = null;
		Connection 		  con 				= null;
		PreparedStatement psmt				= null;
		ResultSet		  rs				= null;
		String 			  tipoSoc			= null;
		String sql = "SELECT USRDRC.XXTV_CAPITAL_SOC_PKG.SOCIEDAD_FN(?) FROM dual";
		try {
			connectionWrapper = new ConnectionWrapper();
			con = connectionWrapper.getConnection();
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, idEmpresa);
			rs = psmt.executeQuery();
			while(rs.next()){
				tipoSoc = rs.getString(1);
						//rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				psmt.close();
				con.close();
				connectionWrapper.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return tipoSoc;
	}
	
}
