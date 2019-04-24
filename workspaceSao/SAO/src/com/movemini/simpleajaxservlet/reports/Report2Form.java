package com.movemini.simpleajaxservlet.reports;

import java.util.ArrayList;

import javax.servlet.ServletRequest;

import com.movemini.data.ConnectionWrapper;
import com.movemini.data.OneRecordFactory;
import com.movemini.data.OneValueFactory;
import com.movemini.data.Record;
import com.movemini.simpleflexgrid.HTMLForm;
import com.movemini.simpleflexgrid.HTMLTableColumn;

public class Report2Form {



	private ServletRequest request;
	
//	private String id_interprete;
	
	
	public Report2Form(ServletRequest request) {

		this.request = request;

		init();
	}
	
	
	private void init() {

//		this.id_interprete = request.getParameter("id_interprete");
//		
//		
//		if(id_interprete.equals("0")) {
//			
//			ConnectionWrapper.staticExecuteUpdate("INSERT INTO cat_interprete (id_interprete) VALUES (0)");
//
//			this.id_interprete = OneValueFactory.get("select max(id_interprete) FROM cat_interprete");			
//		}

	}
	
	
//	public String getId_interprete() {
//		return id_interprete;
//	}
	
	public String getFormHtml() {
	
		StringBuilder sb = new StringBuilder();

		ArrayList<HTMLTableColumn> columns = new ArrayList<HTMLTableColumn>();
		
		
//		Record record = OneRecordFactory.getFirstRecord("SELECT * FROM cat_interprete WHERE id_interprete =" + id_interprete);
		
		
		
		columns.add(new HTMLTableColumn("Cliente:", 		"Cliente", 				"left", 	"select", 	"onChange='updateTable()'","", "SELECT id_cliente, nombre FROM CRM_Cliente where nombre is not null ORDER by nombre"));
		columns.add(new HTMLTableColumn("Fecha Inicio:", 		"Fecha_Ini", 				"left", 	"date", 	"onkeyup='updateTable()' onChange='updateTable()'"));
		columns.add(new HTMLTableColumn("Fecha Fin:", 		"Fecha_Fin", 				"left", 	"date", 	"onkeyup='updateTable()' onChange='updateTable()'"));
		
		
		//columns.add(new HTMLTableColumn("Total Facturado:", 		"id_jerarquia", 			"left", 	"select", 	"onChange='updateInterprete(this)'", "", "SELECT id_jerarquia, jerarquia FROM cat_jerarquia"));
		
		//String tableWidth = "100%";
		String additionalAttributes = "";
		
		//Record record = new Record();
		sb.append(HTMLForm.getFormHtml(columns, additionalAttributes));
		

		return sb.toString();
	}
	
	
}
