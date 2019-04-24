package com.movemini.simpleajaxservlet.reports;

import java.util.ArrayList;

import javax.servlet.ServletRequest;

import com.movemini.data.DataArray;
import com.movemini.data.Record;
import com.movemini.simpleflexgrid.HTMLTable;
import com.movemini.simpleflexgrid.HTMLTableColumn;

public class Report1Table {


	private ServletRequest request;
	
	public Report1Table(ServletRequest request) {

		this.request = request;
	}
	
	
	public String getHtml() {
		
		String Cotizacion = request.getParameter("Cotizacion");
		String Cliente = request.getParameter("Cliente");
		String Evento = request.getParameter("Evento");
		String Estatus = request.getParameter("Estatus");
		
		
		
		ArrayList<Record> records = DataArray.getArrayList("report_1_get_table", Cotizacion, Cliente, Evento, Estatus);
	
		StringBuilder sb = new StringBuilder();
			
		ArrayList<HTMLTableColumn> columns = new ArrayList<HTMLTableColumn>();
		
		
		
		
		columns.add(new HTMLTableColumn("Cotizacion", 			"Cotizacion", 		"left", 	"label", 	"")); //"onkeyup='updateSede(KEY, this)'"
		columns.add(new HTMLTableColumn("Cliente", 				"Cliente", 			"left", 	"label", 	"")); //"onkeyup='updateSede(KEY, this)'"
		columns.add(new HTMLTableColumn("Evento", 				"Evento", 			"left", 	"label", 	"")); //"onkeyup='updateSede(KEY, this)'"
		columns.add(new HTMLTableColumn("Estatus", 				"Estatus", 			"left", 	"label", 	"")); //"onkeyup='updateSede(KEY, this)'"
		columns.add(new HTMLTableColumn("Total Facturado", 		"Total_Facturado", 	"right", 	"label", 	"")); //"onkeyup='updateSede(KEY, this)'"
		
		
		String tableWidth = "100%";
		String additionalAttributes = "class=\"table table-bordered\"";
		
		
		sb.append(HTMLTable.getHtml(records, columns, tableWidth, additionalAttributes));
		
		
		/*
		sb.append("<br><br><br><table width='80%'>");
		sb.append("<tr>");
		sb.append("<td>");
		//sb.append("Observaciones Generales:<br><br><textarea rows='3' cols='50' onChange='updatePOComment(this)'>" + head.getString("RECEIPT_COMMENT") + "</textarea>");
		
		sb.append("</td>");
		sb.append("</tr>");
		sb.append("</table>");
		*/
		
		
		
		return sb.toString();
	}
	
	
	
	
}
