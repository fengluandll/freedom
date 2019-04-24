package com.movemini.simpleajaxservlet.condiciones;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletRequest;

import com.movemini.data.ConnectionWrapper;
import com.movemini.data.DataArray;
import com.movemini.data.OneRecordFactory;
import com.movemini.data.OneValueFactory;
import com.movemini.data.Record;
import com.movemini.simpleflexgrid.HTMLTable;
import com.movemini.simpleflexgrid.HTMLTableColumn;



public class CondicionesAplicablesTable {

	private ServletRequest request;

	public CondicionesAplicablesTable(ServletRequest request) {

		this.request = request;
	}


	public String getHtml() {

		String id_evento_version = request.getParameter("ID_EVENTO");
		String id_evento = OneValueFactory.get("select id_evento from evento_version where id_evento_version = " + id_evento_version);

		//String ID_TIPO_EVENTO = request.getParameter("ID_TIPO_EVENTO");


		Record eventoRecord = OneRecordFactory.getFirstRecord("SELECT * FROM evento WHERE id_evento = " + id_evento);
		String ID_TIPO_EVENTO = eventoRecord.get("id_tipo_eveto");


		/*if(poId == null || poId.equals("") || poId.equals("0")) {

			return "";
		}*/


		//Record head = POHeader.getHeader(poId);
		ArrayList<Record> records = DataArray.getArrayList("evento_condiciones_applic_select_pr", id_evento, ID_TIPO_EVENTO);

		StringBuilder sb = new StringBuilder();

		ArrayList<HTMLTableColumn> columns = new ArrayList<HTMLTableColumn>();




		columns.add(new HTMLTableColumn("Agregar", 					"id_condicion", 		"center", 	"link", 	"onclick='agregarCondicion(id_condicion)'", "<i class=\"fa fa-plus fa-2x\"></i>"));



		columns.add(new HTMLTableColumn("Condici&oacute;n", 				"nombre", 		"left", 	"label", 	""));  //"onkeyup='updateSede(KEY, this)'"

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
