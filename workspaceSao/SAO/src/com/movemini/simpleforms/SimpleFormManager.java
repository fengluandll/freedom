package com.movemini.simpleforms;

import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.movemini.data.ConnectionWrapper;
import com.movemini.data.DataArray;
import com.movemini.data.OneRecordFactory;
import com.movemini.data.OneValueFactory;
import com.movemini.data.Record;
import com.movemini.simpleflexgrid.HTMLForm;
import com.movemini.simpleflexgrid.HTMLTableColumn;
import com.movemini.simpleforms.custom.SAOCustomActions;
import com.movemini.util.StringUtils;

public class SimpleFormManager {


	private HttpServletRequest request;
	private HttpSession session;
	
	
	private String id;
	
	private String entityCode;
	
	private Record screenConfig;
	
	
	
	

	private Record record;
	
	
	private int maxTabId;
	
	private ArrayList<String> tabHeaders = null;
		//		public String[] tabHeaders = null;
	
	
	
	
	
	public SimpleFormManager(HttpServletRequest request, HttpSession session) {

		this.request = request;
		this.session = session;		
		
		init();
	}
	
	
	private void init() {
		
		
		this.id = request.getParameter("id");
		
	 	this.entityCode = (String) session.getAttribute("entityCode");
	 	String cliente = (String)session.getAttribute("crmcliente");
		
	 	this.screenConfig =  OneRecordFactory.getFirstRecord("SELECT * FROM flex_entity WHERE id = " + this.entityCode);
	 	
	 	
	 	this.tabHeaders = StringUtils.split(this.screenConfig.get("tab_headers"), "|");  
	 	
	 	this.maxTabId = Integer.parseInt(OneValueFactory.get("SELECT max(tab_id) FROM flex_field WHERE id_flex_entity = " + this.entityCode));
	 	
	 	
		if(this.id.equals("0")
				//record == null
				) {
			
			
			if("7".equals(this.entityCode) || "21".equals(this.entityCode)) {
				ConnectionWrapper.staticExecuteUpdate("INSERT INTO " + screenConfig.get("table") + " (" + screenConfig.get("column_id") + ",id_cliente) VALUES (0,"+cliente+")");
			}else {
				ConnectionWrapper.staticExecuteUpdate("INSERT INTO " + screenConfig.get("table") + " (" + screenConfig.get("column_id") + ") VALUES (0)");
			}
			

			this.id = OneValueFactory.get("select max(" + screenConfig.get("column_id") + ") FROM " + screenConfig.get("table") + "");
			
			
			SAOCustomActions.postInsert(this);
			
			
		}
		
		
		session.setAttribute("row_id", this.id);
		
		
		
		this.record = OneRecordFactory.getFirstRecord(
				"SELECT * FROM " + screenConfig.get("table") + " WHERE " + screenConfig.get("column_id") + " =" + id);
		
		
		
		
	 	
	}
	
	
	
	public int getMaxTabId() {
		return maxTabId;
	}
	
	
	public String getId() {
		return id;
	}
	
	

	public ArrayList<String> getTabHeaders() {
		return tabHeaders;
	}
	
	public Record getScreenConfig() {
		return screenConfig;
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}
	
	
	public String getFormHtml() {
	
		
		StringBuilder sb = new StringBuilder();
		
		ArrayList<HTMLTableColumn> columns = new ArrayList<HTMLTableColumn>();

		
		ArrayList<Record> rows = DataArray.getArrayList("SELECT * FROM flex_field WHERE id_flex_entity = " + entityCode);

		
		
		for (Record row : rows) {
		
			columns.add(new HTMLTableColumn(row.get("label") + ":",
					row.get("code"),
					row.get("align"), 	
					row.get("type"),
					row.get("js_code"),
					row.get("attributes"),
					row.get("catalog")
					));
		}
		
		
		//String tableWidth = "100%";
		String additionalAttributes = "";
		
		
		sb.append(HTMLForm.getFormHtml(record, columns, additionalAttributes));
		

		return sb.toString();
	}
	
	

	public String getFormHtml(int tabId) {
	
		
		StringBuilder sb = new StringBuilder();
		
		ArrayList<HTMLTableColumn> columns = new ArrayList<HTMLTableColumn>();

		ArrayList<Record> rows = DataArray.getArrayList("SELECT * FROM flex_field WHERE id_flex_entity = " + entityCode + " AND tab_id = " + tabId + " ORDER BY order_id");
		
		for (Record row : rows) {
		
			columns.add(new HTMLTableColumn(row.get("label") + ":",
					row.get("code"),
					row.get("align"), 	
					row.get("type"),
					row.get("js_code"),
					row.get("attributes"),
					row.get("catalog")
					));
		}
				
		//String tableWidth = "100%";
		String additionalAttributes = "";
		
		sb.append(HTMLForm.getFormHtml(this.record, columns, additionalAttributes));

		return sb.toString();
	}




}
