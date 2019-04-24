package mx.com.televisa.derechocorporativo.modules.fusion;

import java.sql.CallableStatement;

import mx.com.televisa.derechocorporativo.config.NavigationRules;
import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;
import mx.com.televisa.derechocorporativo.util.FacesUtils;


/**
 * 
 */
public class FusionCopy {


	/**
	 * 
	 */
	public static void doCopy(String key, String IdEmpresa) {
			
		
		ConnectionWrapper conn = null;

		
		try {
			
			conn = new ConnectionWrapper();
			
			CallableStatement callStmt = conn.prepareCall(
						"DERCORP_FUSION_CPY_PKG.CPY_PR", 2);
			
			callStmt.setString(1,key);
			callStmt.setString(2,IdEmpresa);
			callStmt.execute();			
			
		} catch(Exception ex) {
			
			ex.printStackTrace();
			
			
		} finally {
			
			ConnectionWrapper.closeAll(conn);
		}
		
		//return NavigationRules.AUTHORIZED_ACCESS;
	}
	
}
