package com.movemini.simpleajaxservlet.evento_area;

import java.util.ArrayList;

import javax.servlet.ServletRequest;

import com.movemini.data.DataArray;
import com.movemini.data.Record;
import com.movemini.simpleflexgrid.HTMLTable;
import com.movemini.simpleflexgrid.HTMLTableColumn;
import com.movemini.data.OneValueFactory;



public class EventoAreaTable {

	private ServletRequest request;

	public EventoAreaTable(ServletRequest request) {

		this.request = request;
	}


	public String getHtml() {

		String id_evento_version = request.getParameter("ID_EVENTO");

		String id_evento = OneValueFactory.get("select id_evento from evento_version where id_evento_version = "+ id_evento_version);


		ArrayList<Record> records = DataArray.getArrayList("evento_area_select_by_evento_id_pr", id_evento);

		StringBuilder sb = new StringBuilder();

		ArrayList<HTMLTableColumn> columns = new ArrayList<HTMLTableColumn>();


		columns.add(new HTMLTableColumn("&Aacute;reas Funcionales", 					"nombre_area", 		"left", 	"text", 	"onkeyup='updateArea(KEY, this)'"));

		//columns.add(new HTMLTableColumn("Borrar&Aacute;rea", 					"id", 		"center", 	"link", 	"onclick='if(confirm('Seguro?')){deleteArea(id);}'", "<i class=\"fa fa-close fa-2x\"></i>"));
		columns.add(new HTMLTableColumn("Borrar &Aacute;rea", 					"id", 		"center", 	"link", 	"onclick='deleteArea(id)'", "<i class=\"fa fa-close fa-2x\"></i>"));





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
