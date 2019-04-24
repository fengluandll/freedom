package mx.com.televisa.derechocorporativo.model.empresa;

import java.sql.ResultSet;
import java.sql.Statement;

import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;

public class EmpresaValores {


	public static double getValorTeoricoNominalMultiplicador(String idEmpresa) {
		
		ConnectionWrapper conn = null;
		Statement stmt = null;
		ResultSet set = null;
		
		try {
			
			conn = new ConnectionWrapper();
		
			
			stmt = conn.createStatement();
			
			set = stmt.executeQuery("SELECT DERCORP_CAPTURA_PKG.GET_VALOR_TEORICO_NOMINAL_FN(" + idEmpresa + ") FROM DUAL");
			
			set.next();
			
			
			double val = set.getDouble(1);
			
			
			if(val == 0){
				
				return 1;
			}
			
 			return val;
			
		
		} catch(Exception ex) {
			
			/**
			 *   PUEDE OCURRIR SI NO SE TIENE EL DATO EN DB. EN CUYO CASO SE REGRESA 1 
			 *   YA QUE AL MULTIPLICAR NO AFECTA EL RESULTADO
			 */
			return 1;
			
		} finally {
			
			ConnectionWrapper.closeAll(set, stmt, conn);
		}
	}
	
	
	public static String getMonedaECS(String idEmpresa) {
		
		ConnectionWrapper conn = null;
		Statement stmt = null;
		ResultSet set = null;
		
		try {
			
			conn = new ConnectionWrapper();
		
			
			stmt = conn.createStatement();
			
			set = stmt.executeQuery("SELECT DERCORP_CAPTURA_PKG.GET_MONEDA_ECS_FN(" + idEmpresa + ") FROM DUAL");
			
			set.next();
			
			
			String val = set.getString(1);
			
			
			if(val == null){
				
				return "";
			}
			
 			return val;
			
		
		} catch(Exception ex) {
			
			ex.printStackTrace();
			
			/**
			 * 
			 */
			return "";
			
		} finally {
			
			ConnectionWrapper.closeAll(set, stmt, conn);
		}
	}



}
