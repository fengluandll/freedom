package mx.com.televisa.derechocorporativo.daos;

import java.sql.CallableStatement;

import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;

public class AESCopyDAO {
	
public static boolean doCopy(String key, String IdEmpresa) {
			
		
		ConnectionWrapper conn = null;
		boolean paso = false;
		
		try {
			
			conn = new ConnectionWrapper();
			
			CallableStatement callStmt = conn.prepareCall(
						"DERCORP_FUSION_CPY_PKG.CPY_AES_PR", 2);
			
			callStmt.setString(1,key);
			callStmt.setString(2,IdEmpresa);
			callStmt.execute();		
			paso = true;
			
		} catch(Exception ex) {
			
			ex.printStackTrace();
			
			
		} finally {
			
			ConnectionWrapper.closeAll(conn);
		}
		return paso;
	}

}
