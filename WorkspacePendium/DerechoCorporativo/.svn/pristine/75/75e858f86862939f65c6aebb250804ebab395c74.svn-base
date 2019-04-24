package mx.com.televisa.derechocorporativo.modules.reports.apoderados;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import mx.com.televisa.derechocorporativo.bean.GenericBean;
import mx.com.televisa.derechocorporativo.bean.GenericDataBean;
import mx.com.televisa.derechocorporativo.daos.MngDataPoderes;
import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;
import mx.com.televisa.derechocorporativo.data.Record;
import mx.com.televisa.derechocorporativo.data.packages.XXTV_REP_APODERADOS_PKG;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

public class RepApoderados {

    public static String getIdValorCatalogo(String idCatalogo){
    	ConnectionWrapper connect = null;
    	ResultSet 		  rs 	  = null;
    	StringBuilder     sb      = new StringBuilder();
    	int linUltimaComa		  = 0;
    	Statement psmt    = null;
    	String sql = "SELECT ID_CATALOGO_VALOR \n"+
					 "	FROM DERCORP_ADD_CAMPO_CAT_VAL_TAB\n"+ 
					 "	WHERE id_catalogo IN ("+idCatalogo+")";
    	try {
			connect = new ConnectionWrapper();
			//Statement stmt = connect.createStatement();
			psmt = connect.createStatement();
			rs = psmt.executeQuery(sql);
			while(rs.next()){
				sb.append(rs.getString(1));
				sb.append(",");
			}
			linUltimaComa = sb.lastIndexOf(",");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			ConnectionWrapper.closeAll(rs, psmt, connect);
		}
    	return sb.substring(0, linUltimaComa).trim();
    }
	
	public static ArrayList<Record> getEmpresas(	String paramEmpresas, String hiddenApoderados, String hiddenPoder) {

		ConnectionWrapper connect = null;
		CallableStatement stmt = null;
		ResultSet set = null;
		
		ArrayList<Record> records = new ArrayList<Record>();

		try {

			connect = new ConnectionWrapper();

			stmt = connect.prepareCall(XXTV_REP_APODERADOS_PKG.GET_EMPRESAS_PR);

			stmt.registerOutParameter(1, OracleTypes.CURSOR);

			stmt.setString(2, paramEmpresas);
			stmt.setString(3, hiddenApoderados);
			stmt.setString(4, hiddenPoder);
			
			
			
			stmt.execute();

			set = ((OracleCallableStatement) stmt).getCursor(1);

			

			while (set.next()) {

				records.add(new Record(set));
			}

			return records;

		} catch (Exception ex) {

			ex.printStackTrace();

			return null;

		} finally {

			ConnectionWrapper.closeAll(set, stmt, connect);
		}
	}
	
	
	/**
	 * @deprecated 
	 */
	public static ArrayList<Record> getInfo(	String paramEmpresas, 
													String paramTipoPoder, String paramEscritura) {

		ConnectionWrapper connect = null;
		CallableStatement stmt = null;
		ResultSet set = null;
		
		ArrayList<Record> records = new ArrayList<Record>();

		try {

			connect = new ConnectionWrapper();

			stmt = connect.prepareCall(XXTV_REP_APODERADOS_PKG.GET_INFO_PR);

			stmt.registerOutParameter(1, OracleTypes.CURSOR);

			stmt.setString(2, paramEmpresas);
			stmt.setString(3, paramTipoPoder);
			stmt.setString(4, paramEscritura);
			
			stmt.execute();

			set = ((OracleCallableStatement) stmt).getCursor(1);

			

			while (set.next()) {

				records.add(new Record(set));
			}

			return records;

		} catch (Exception ex) {

			ex.printStackTrace();

			return null;

		} finally {

			ConnectionWrapper.closeAll(set, stmt, connect);
		}
	}
	
	
	
	
	

	public static String getInfoEscrituras(String paramEmpresa, String paramEscritura) {

		ConnectionWrapper connect = null;
		PreparedStatement stmt = null;
		ResultSet set = null;
		
		ArrayList<Record> records = new ArrayList<Record>();

		try {

			connect = new ConnectionWrapper();

			stmt = connect.prepareStatement("SELECT DERCORP_REPORTS_PKG.GET_INFO_ESCRITURA(?,?) FROM DUAL");

			stmt.setString(1, paramEmpresa);
			stmt.setString(2, paramEscritura);
			
			set = stmt.executeQuery();

			
			while (set.next()) {

				return set.getString(1);
			}

			return "";

		} catch (Exception ex) {

			ex.printStackTrace();

			return "";

		} finally {

			ConnectionWrapper.closeAll(set, stmt, connect);
		}
	}
	
	
	public static String getApendice(int idOtorgaPoder,String esc){
		String apendiceRevocados= "";
		GenericDataBean gdbRevocaciones = MngDataPoderes.query_REVOCACIONES(idOtorgaPoder);
		for(int j=0; j < gdbRevocaciones.beans.size(); j++){
			GenericBean beanRevocacion = gdbRevocaciones.get(j);	
			String apendiceRevocadoCurrent = "";								
			  apendiceRevocadoCurrent = beanRevocacion.getProperty("DESC_REVOCA") == null ? "" : beanRevocacion.getProperty("DESC_REVOCA").toString(); 
			  if(!apendiceRevocados.contains(apendiceRevocadoCurrent)){				
				  apendiceRevocados += apendiceRevocadoCurrent.trim().length()>0 ? "<i style='font-size:8pt'>" + apendiceRevocadoCurrent + "</i><br/>" : "";	
			  }									  
		}
		return apendiceRevocados;
	}
	
}
