package com.movemini.autoconfig;

import java.util.ArrayList;

import com.movemini.data.ConnectionCounter;
import com.movemini.data.ConnectionWrapper;
import com.movemini.data.DataArray;
import com.movemini.data.OneValueFactory;
import com.movemini.data.Record;
import com.movemini.util.StringUtils;

public class AutoConfigFlexEntities {


	/**
	 * 
	 * 
	 */
	public static void main(String ...a) {
		
		
		
//		proceessAllTables();
//		
//		
		
		
		
		
		
		ConnectionWrapper conn = null;
		
		try {
			
			conn = new ConnectionWrapper();
			
				
				
			//processTable(conn, "flex_entity");
			//processTable(conn, "flex_field");
//			processTable(conn, "issue");
//			processTable(conn, "issue_categoria");
//			processTable(conn, "issue_line");
//			processTable(conn, "issue_status");

//											

			
			
			//processTable(conn, "crm_razon_social");
			//processTable(conn, "cat_region");
			
			processTable(conn, "ss_menu");
			processTable(conn, "ss_rol");
			
			
			
			
			
			
			
			
			//processTable(conn, "TABLE_NAME2");
				
			
			
			System.out.print("FIN DEL PROCESO");
			
			
		} catch(Exception ex) {
			
			ex.printStackTrace();
			
		} finally {
			
			//ConnectionCounter.printConnetionCount();
			
			ConnectionWrapper.closeAll(conn);
			
		}		
		
		
		
		
	}
	
	
	private static void proceessAllTables(){
		
		ConnectionWrapper conn = null;
		
		try {
			
			conn = new ConnectionWrapper();
			
			ArrayList<Record> tables = DataArray.getArrayList("show tables");
			
			
			for (Record table : tables) {
				
				
				processTable(conn, table.getId());
				
				
			}
			
			System.out.print("FIN DEL PROCESO");
			
			
		} catch(Exception ex) {
			
			ex.printStackTrace();
			
		} finally {
			
			//ConnectionCounter.printConnetionCount();
			
			ConnectionWrapper.closeAll(conn);
			
		}		
		
	}
	

	
	
	private static void processTable(ConnectionWrapper conn, String tableName) throws Exception {
		//if(table.)
		
				
		ArrayList<Record> cols = DataArray.getArrayList("desc " + tableName);
		
		
		String idColumn = null;
		
		String columnHeaders = "";
		String columnCodes = "";
		
		
		for (Record col : cols) {
		
			columnHeaders += StringUtils.tipoTitulo(col.get("Field")) + "|";
			columnCodes += col.get("Field") + "|";
			
			if(idColumn == null) {
				idColumn = col.get("Field");
			}
		}
		
		
		columnHeaders = StringUtils.removeLastChar(columnHeaders);
		columnCodes = StringUtils.removeLastChar(columnCodes);
		
		
		conn.executeUpdate("INSERT INTO flex_entity " +
				"( " +
				"	title, " +
				"	title2, " +
				"	new_btn_text, " +
				"	list_table_view, " +
				"	`table`, " +
				"	column_headers, " +
				"	column_codes, " +
				"	edit_btn_text, " +
				"	delete_btn_text, " +
				"	column_id " +
				") " +
				"VALUES " +
				"( " +
				"	'" + StringUtils.tipoTitulo(tableName) + "', " +
				"	'" + StringUtils.tipoTitulo(tableName) + "', " +
				"	'Nuevo', " +
				"	'" + tableName + "' , " +
				"	'" + tableName + "' , " +
				"	'" + columnHeaders + "', " +
				"	'" + columnCodes + "', " +
				"	'Editar', " +
				"	'Eliminar', " +
				"	'" + idColumn + "' " +
				")");
		
		
		
		
		
		

		
		String id = OneValueFactory.get("SELECT max(id) FROM flex_entity");
		
		
		
		
		
		for (Record col : cols) {
			
		
			String field = col.get("Field");
			String type = col.get("Type");
			
			
			
			String sql = "INSERT INTO flex_field " +
						"( " +
						"	id_flex_entity, " +
						"	label, " +
						"	code, " +
						"	align, " +
						"	type, " +
						"	js_code, " +
						"	attributes, " +
						"	catalog, " +
						"	order_id " +
						") " +
						"VALUES " +
						"( " +
						"		'" + id + "', " +
						"		'" + StringUtils.tipoTitulo(field.replaceAll("_", " "))  + "', " +
						"		'" + field + "', " +
						"		NULL, " +
						"		'text', " +
						"		'onKeyUp=''updateField(this)''', " +
						"		'style=\"width: 90%\"', " +
						"		NULL, " +
						"		'10'	 " +						
						")";
			
			
			conn.executeUpdate(sql);
			
		}
	}
	
	
}
