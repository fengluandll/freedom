package com.movemini.simpleajaxservlet.versiones;

import java.util.ArrayList;

import javax.servlet.ServletRequest;

import com.movemini.data.DataArray;
import com.movemini.data.OneValueFactory;
import com.movemini.data.Record;
import com.movemini.simpleflexgrid.HTMLTable;
import com.movemini.simpleflexgrid.HTMLTableColumn;
import com.movemini.util.StringUtils;



/**
 * DOCUMENTACION
 *
 * @movemini html_table_select_evento_interprete
 */
public class VersionTable {


	private ServletRequest request;

	private String modo = "DEFAULT";


	public VersionTable(ServletRequest request) {

		this.request = request;
	}

	public void setModo(String modo) {
		this.modo = modo;
	}

	//
	//
	// CUSTOMIZED
	//
	//
	public String getHtml() {

		StringBuilder sb = new StringBuilder();

		String id_evento_version = request.getParameter("ID_EVENTO");

		//sb.append(" <a style=\"float:right\" href=\"#\" onclick=\"nuevaFactura( '").append(id_evento).append( "')\" type=\"button\" class=\"btn btn-info\">Nueva Factura</a>");

		ArrayList<Record> records = DataArray.getArrayList("select * from evento_version where id_evento = (select id_evento from evento_version where id_evento_version = "+id_evento_version+")");

		ArrayList<HTMLTableColumn> columns = new ArrayList<HTMLTableColumn>();

		columns.add(new HTMLTableColumn("N&uacute;mero de Versi&oacute;n", 				"num_version", 		"left", 	"label", 	" "));
		columns.add(new HTMLTableColumn("Ver", 					"id_evento_version", 		"center", 	"link", 	"onclick='window.location.href=\"/SAO/home/adm_evento_sedes.jsp?ID_EVENTO=id_evento_version\"'", "<i class=\"fa fa-eye fa-2x\"></i>"));		//fa-check-square-o
		//columns.add(new HTMLTableColumn("Quitar", 					"id_evento_version", 		"center", 	"link", 	"onclick='eliminarVersion(id_evento_version)'", "<i class=\"fa fa-trash fa-2x\"></i>"));





		String tableWidth = "100%";
		String additionalAttributes = "class=\"table table-bordered\"";


		sb.append(HTMLTable.getHtml(records, columns, tableWidth, additionalAttributes));




		return sb.toString();





//
//		sb.append("<table class=\"table table-bordered\" width='100%'> ");
//
//
//
//		sb.append("</table>");



		//return sb.toString();
	}



}
