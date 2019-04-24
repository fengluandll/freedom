package com.movemini.simpleajaxservlet.productos;

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



public class ProductosAplicablesTable {

	private ServletRequest request;

	public ProductosAplicablesTable(ServletRequest request) {

		this.request = request;
	}


	public String getHtml() {

		String id_evento_version = request.getParameter("ID_EVENTO");

		//String idTipoEvento = request.getParameter("id_tipo_evento");
		Record eventoRecord = OneRecordFactory.getFirstRecord("SELECT * FROM evento WHERE id_evento =  (select id_evento from evento_version where id_evento_version = "+id_evento_version+")" );
		String idTipoEvento = eventoRecord.get("id_tipo_eveto");


		String id_dia = request.getParameter("id_dia");

		String filter = request.getParameter("filter");



		/*if(poId == null || poId.equals("") || poId.equals("0")) {

			return "";
		}*/


		//Record head = POHeader.getHeader(poId);
		ArrayList<Record> records = DataArray.getArrayList("evento_productos_applic_select_pr", id_dia, idTipoEvento, filter);

		StringBuilder sb = new StringBuilder();

		ArrayList<HTMLTableColumn> columns = new ArrayList<HTMLTableColumn>();


		String idMoneda = OneValueFactory.get("SELECT id_tipo_cotizacion FROM evento WHERE id_evento = " + eventoRecord.get("id_evento"));



		columns.add(new HTMLTableColumn("Agregar", 					"id_producto", 		"center", 	"link", 	"onclick='agregarProducto(id_producto)'", "<i class=\"fa fa-plus fa-2x\"></i>"));

		if(idTipoEvento.equals("1")){
			columns.add(new HTMLTableColumn("Nombre", 				"nombre_tecnico", 		"left", 	"label", 	""));  //"onkeyup='updateSede(KEY, this)'"
			if(idMoneda.equals("2")) {
				
				columns.add(new HTMLTableColumn("Producto", 				"nombre_ingles", 		"left", 	"label", 	""));  //"onkeyup='updateSede(KEY, this)'"
			}else
			{
				columns.add(new HTMLTableColumn("Producto", 				"nombre", 		"left", 	"label", 	""));  //"onkeyup='updateSede(KEY, this)'"
			}
		}else
		{
			columns.add(new HTMLTableColumn("Idioma", 	"idioma", 		"left", 	"label", 	"")); //
			columns.add(new HTMLTableColumn("Servicio", "servicio", 		"left", 	"label", ""));
			columns.add(new HTMLTableColumn("Cobro", 	"cobro", 		"left", 	"label", 	""));
		}





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
