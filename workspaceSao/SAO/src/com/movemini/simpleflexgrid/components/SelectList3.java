package com.movemini.simpleflexgrid.components;

import java.sql.ResultSet;
import java.sql.Statement;

import com.movemini.data.ConnectionWrapper;
import com.movemini.simpleflexgrid.HTMLTableColumn;

/**
 * IGUAL A SelectList pero sin la opcion (Seleccione) 
 */
public class SelectList3 {

	

	
	/**
	 * 
	 */
	public static String getSelectList(HTMLTableColumn col, String currVal) {
		
		return getSelectList(col, currVal, "");
	}
	
	/**
	 * 
	 */
	public static String getSelectList(HTMLTableColumn col, String currVal, String additionalAttributes) {
		
		
		//String[] attrs = col.getAttributes();
		
		//String sqlCatalog = attrs[0];
		String sqlCatalog = col.getCatalog();
		
		
		StringBuilder sb = new StringBuilder();
		
		ConnectionWrapper conn = null;
		Statement stmt = null;
		
		ResultSet set = null;
		
		try {
			
			conn = new ConnectionWrapper();
			
			stmt = conn.createStatement();

			//String idCatalog = flexField.get("ID_CATALOG");
			//String sqlCatalog = flexField.get("SQL_CATALOG");
			
			String sql = sqlCatalog;
			
			
			set = stmt.executeQuery(sql);
			
			//String aditionalAttributes = (col.getAttributes() != null) ? col.getAttributes()[1] : "";
			
			//width='" + flexField.get("SIZE") + "' 
			
			
			
			
			/*
			sb.append("<select name='" + col.getCode() +"' id='" + col.getCode() + "' " + aditionalAttributes + "  " + col.getJsCode());
			
			
			
			sb.append("<option value='0'>");
			sb.append("(Seleccione)");
			sb.append("</option>");
			
			while(set.next()) {

				
				String elementDB_id = set.getString(1);
				String elementDB_text = set.getString(2);
				
				String chk = "";
				
				if(elementDB_id.equals(value)) {
					
					chk = "selected='selected'";
				}
				
				
				sb.append("<option value='" + elementDB_id + "' " + chk + ">");
				sb.append(elementDB_text);
				sb.append("</option>");				
			}
			
			
			sb.append("</select>");
			*/
			
			
			
			
			
			
			
			
			
			//String formHTML= getListByQuery(col.getCode(), col.getCatalog(), "(Seleccionar)", col.getJsCode() + additionalAttributes, currVal);
			String formHTML= getListByQuery(col.getCode(), col.getCatalog(), null, col.getJsCode() + additionalAttributes, currVal);
			
			
			sb.append(formHTML);
			
			
			
			
			
			return sb.toString();
			
		}catch(Exception ex){
			
			//LOG EXCEPTION
			ex.printStackTrace();
			
			return null;
			
		} finally {
			
			ConnectionWrapper.closeAll(set, stmt, conn);
			
		}
	}


	public static String getListByQuery(String htmlElementId, String query, String selectText, String moreAttributes, String currVal) {
		

		StringBuilder sb = new StringBuilder();
		
		ConnectionWrapper conn = null;
		Statement stmt = null;
		
		ResultSet set = null;
		
		try {
			
			conn = new ConnectionWrapper();
			
			stmt = conn.createStatement();

			set = stmt.executeQuery(query);
			
			sb.append("<select id='" + htmlElementId + "' name='" + htmlElementId + "' " + moreAttributes + " "
					//"style=\"width: 200px;\" class=\"form-control\" style=\"padding-left: 0px;\" required>");
					+ "class=\"form-control\" style=\"padding-left: 0px;\" required>");
			
			
			if(selectText != null) {
				sb.append("<option value='0'>");
				sb.append(selectText);
				sb.append("</option>");
			}
			
			while(set.next()) {

				String chk = "";
				
				if(set.getString(1).equals(currVal)) {
					
					chk = "selected='selected'";
				}
				
				sb.append("<option value='" + set.getString(1) + "' " + chk + ">");
				sb.append(set.getString(2));
				sb.append("</option>");				
			}
			
			
			sb.append("</select>");
			
			return sb.toString();
			
		}catch(Exception ex){
			
			//LOG EXCEPTION
			ex.printStackTrace();
			
			return null;
			
		} finally {
			
			ConnectionWrapper.closeAll(set, stmt, conn);
			
		}
	}
	
	
	
	
	public static String getListByResultSet(String htmlElementId, ResultSet set, String selectText, String moreAttributes, String currVal) {
		

		StringBuilder sb = new StringBuilder();
		
		//ConnectionWrapper conn = null;
		//Statement stmt = null;
		
		//ResultSet set = null;
		
		try {
			
			//conn = new ConnectionWrapper();
			
			//stmt = conn.createStatement();

			//set = stmt.executeQuery(query);
			
			sb.append("<select id='" + htmlElementId + "' name='" + htmlElementId + "' " + moreAttributes + " "
					+ "style=\"width: 290px;\" class=\"form-control\" style=\"padding-left: 0px;\" required>");
			
			
			sb.append("<option value='0'>");
			sb.append(selectText);
			sb.append("</option>");
			
			while(set.next()) {

				String chk = "";
				
				if(set.getString(1).equals(currVal)) {
					
					chk = "selected='selected'";
				}
				
				sb.append("<option value='" + set.getString(1) + "' " + chk + ">");
				sb.append(set.getString(2));
				sb.append("</option>");				
			}
			
			
			sb.append("</select>");
			
			return sb.toString();
			
		}catch(Exception ex){
			
			//LOG EXCEPTION
			ex.printStackTrace();
			
			return null;
			
		} finally {
			
			///ConnectionWrapper.closeAll(set, stmt, conn);
			
		}
	}

	
	
	
	
}
