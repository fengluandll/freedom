package com.movemini.util;

import java.sql.CallableStatement;
import java.sql.ResultSet;

import com.movemini.data.ConnectionWrapper;



public class CSVUtil {

	
	public static String getLinesForCSV(
				//String procedure, String oneParamValue, boolean addHeader, String separador
			String procedure, boolean addHeader, String separador, String ...params
				) {
		
		String output = "";
		
		ConnectionWrapper conn = null;
		CallableStatement stmt = null;
		
		ResultSet set = null;
		
		try {
			
			conn = new ConnectionWrapper();
			
			stmt = conn.prepareCallProcedure(procedure, params);

			set = stmt.executeQuery();
			
			if(addHeader) {
				
				String columNamesLine = "";
				for (int colIndex = 1; colIndex <= set.getMetaData().getColumnCount(); colIndex++) {
					
					String colName = set.getMetaData().getColumnLabel(colIndex);
					
					//columNamesLine += "\"" + StringUtils.tipoTitulo(colName.trim().replace("_", " ")) + "\"" + separador;
					columNamesLine += "" + StringUtils.tipoTitulo(colName.trim().replace("_", " ")) + "" + separador;
					
				}
				output += columNamesLine + StringUtils.NEW_LINE;
			}
			
			
			while(set.next()) {
				
				String recordTextLine = "";
				for (int colIndex = 1; colIndex <= set.getMetaData().getColumnCount(); colIndex++) {
					
					
					String value = set.getString(colIndex);
					
					if(value == null) {
						
						value = "";
					}
					
					recordTextLine += value.trim().replace(",", ";") + separador;
				}

				output += recordTextLine + StringUtils.NEW_LINE;
			}
						
			return output;
			
		}catch(Exception ex){
			
			//LOG EXCEPTION
			ex.printStackTrace();
			
			return null;
			
		} finally {
			
			ConnectionWrapper.closeAll(set, stmt, conn);
			
		}
	}
}
