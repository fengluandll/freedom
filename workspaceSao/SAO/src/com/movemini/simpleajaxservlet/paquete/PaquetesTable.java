package com.movemini.simpleajaxservlet.paquete;

import java.util.ArrayList;

import javax.servlet.ServletRequest;

import com.movemini.data.DataArray;
import com.movemini.data.Record;
import com.movemini.simpleflexgrid.HTMLTable;
import com.movemini.simpleflexgrid.HTMLTableColumn;

public class PaquetesTable {



	private ServletRequest request;

	
	public PaquetesTable(ServletRequest request) {

		this.request = request;
	}
	
	
	
	public String getHtml() {
		
		StringBuilder sb = new StringBuilder();
		
		
		String filter = request.getParameter("filter");
		
			
		ArrayList<Record> records = DataArray.getArrayList("SELECT * FROM cat_paquete WHERE nombre like '%" + filter + "%' and id_status = 1");

		
		ArrayList<HTMLTableColumn> columns = new ArrayList<HTMLTableColumn>();
	
		columns.add(new HTMLTableColumn("Agregar", 					"id_paquete", 		"center", 	"link", 	"onclick='agregarPaquete(id_paquete)'", "<i class=\"fa fa-plus fa-2x\"></i>"));
		
		columns.add(new HTMLTableColumn("Nombre:", 					"nombre", 		"left", 	"label", 	""));  
		columns.add(new HTMLTableColumn("Descripci&oacute;n:", 			"descripcion", 	"left", 	"label", 	""));  
		
		
		
		String tableWidth = "100%";
		String additionalAttributes = "class=\"table table-bordered\"";
		
		
		sb.append(HTMLTable.getHtml(records, columns, tableWidth, additionalAttributes));
		
		
		
		return sb.toString();
		
		
		
		
	}
	
	
	


	
}
