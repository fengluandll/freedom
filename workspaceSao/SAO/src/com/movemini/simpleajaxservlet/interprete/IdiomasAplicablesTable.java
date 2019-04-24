package com.movemini.simpleajaxservlet.interprete;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletRequest;

import com.movemini.data.ConnectionWrapper;
import com.movemini.data.DataArray;
import com.movemini.data.Record;
import com.movemini.simpleflexgrid.HTMLTable;
import com.movemini.simpleflexgrid.HTMLTableColumn;



public class IdiomasAplicablesTable {

	private ServletRequest request;
	
	public IdiomasAplicablesTable(ServletRequest request) {

		this.request = request;
	}
	
	
	public String getHtml() {
		
		String id_interprete = request.getParameter("id_interprete");
		
		/*if(poId == null || poId.equals("") || poId.equals("0")) {
			
			return "";
		}*/
		
		
		//Record head = POHeader.getHeader(poId);		
		ArrayList<Record> records = DataArray.getArrayList("interprete_idiomas_applic_select_pr", id_interprete);
	
		StringBuilder sb = new StringBuilder();
			
		ArrayList<HTMLTableColumn> columns = new ArrayList<HTMLTableColumn>();
		
		
		
		columns.add(new HTMLTableColumn("Agregar", 					"id_idioma", 		"center", 	"link", 	"onclick='agregarIdioma(id_idioma)'", "<i class=\"fa fa-plus fa-2x\"></i>"));
		
		
		columns.add(new HTMLTableColumn("Idioma", 				"idioma", 		"left", 	"label", 	""));  //"onkeyup='updateSede(KEY, this)'"
		
//		columns.add(new HTMLTableColumn("Ver Fechas", 					"id", 		"center", 	"link", 	"onclick='selectSede(id)'", "<i class=\"fa fa-calendar fa-2x\"></i>"));
		
		//columns.add(new HTMLTableColumn("Borrar Sede", 					"id", 		"center", 	"link", 	"onclick='if(confirm('Seguro?')){deleteSede(id);}'", "<i class=\"fa fa-close fa-2x\"></i>"));
		
		
		
		
		
		
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
