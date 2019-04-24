package com.movemini.simpleajaxservlet.viaticos;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletRequest;

import com.movemini.data.ConnectionWrapper;
import com.movemini.data.DataArray;
import com.movemini.data.OneRecordFactory;
import com.movemini.data.Record;
import com.movemini.simpleflexgrid.HTMLTable;
import com.movemini.simpleflexgrid.HTMLTableColumn;



public class ViaticosAplicablesTable {

	private ServletRequest request;

	public ViaticosAplicablesTable(ServletRequest request) {

		this.request = request;
	}


	public String getHtml() {

		String id_evento_version = request.getParameter("ID_EVENTO");

		//String idTipoEvento = request.getParameter("ID_TIPO_EVENTO");


		Record eventoRecord = OneRecordFactory.getFirstRecord("SELECT * FROM evento WHERE id_evento =  (select id_evento from evento_version where id_evento_version = "+id_evento_version+")" );
		String idTipoEvento = eventoRecord.get("id_tipo_eveto");



		/*if(poId == null || poId.equals("") || poId.equals("0")) {

			return "";
		}*/


		//Record head = POHeader.getHeader(poId);
		ArrayList<Record> records = DataArray.getArrayList("evento_viaticos_applic_select_pr", id_evento_version, idTipoEvento);

		StringBuilder sb = new StringBuilder();

		ArrayList<HTMLTableColumn> columns = new ArrayList<HTMLTableColumn>();




		columns.add(new HTMLTableColumn("Agregar", 					"id_viatico", 		"center", 	"link", 	"onclick='agregarViatico(id_viatico)'", "<i class=\"fa fa-plus fa-2x\"></i>"));



		columns.add(new HTMLTableColumn("Vi&aacute;tico", 				"nombre", 		"left", 	"label", 	""));  //"onkeyup='updateSede(KEY, this)'"

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
