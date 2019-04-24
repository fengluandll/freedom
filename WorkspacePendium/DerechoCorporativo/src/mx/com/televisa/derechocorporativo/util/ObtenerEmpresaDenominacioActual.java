package mx.com.televisa.derechocorporativo.util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import org.apache.taglibs.standard.tag.common.core.SetSupport;

import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;

public class ObtenerEmpresaDenominacioActual {
	
	//public static ConnectionWrapper connection;	

	public static String getEmpresaDenominacionActual(String pIdEmpresa){
		ResultSet set = null;
		ResultSetMetaData metaData = null;
		PreparedStatement psmt     = null;
		String lstEmpresaDenominacionActual = "";
		ConnectionWrapper connection = null;
		try{
			connection = new ConnectionWrapper();
			String sqlSecciones = "SELECT  DISTINCT  ID_EMPRESA"+
													" ,DENOM_ACTUAL"+
													" ,ID_CLASIFICACION"+
													" ,ID_PAIS"+
													" FROM    DERCORP_BUSQUEDA_VIEW"+
													" WHERE   1=1"+
													" AND     DENOM_ACTUAL IS NOT NULL"+
													" AND     ID_EMPRESA = "+pIdEmpresa;
					
					
			psmt = connection.prepareStatement(sqlSecciones);
			set = psmt.executeQuery();
			
			metaData = null;
			while (set.next()) {
				metaData = set.getMetaData();
				lstEmpresaDenominacionActual = set.getObject("DENOM_ACTUAL").toString();
			}
		} catch (Exception ex) {

			ex.printStackTrace();
		} finally {
			try{
				set.close();
				psmt.close();
				//ConnectionWrapper.closeAll(connection);
				connection.close();
			} catch (Exception ex){
				ex.printStackTrace();
			}
		}
		return lstEmpresaDenominacionActual;
	}
}