package com.movemini.simpleajaxservlet.subtotales;

import java.util.ArrayList;

import javax.servlet.ServletRequest;

import com.movemini.data.DataArray;
import com.movemini.data.Record;
import com.movemini.simpleflexgrid.HTMLTable;
import com.movemini.simpleflexgrid.HTMLTableColumn;

public class SubtotalCotizadorTable {

	private ServletRequest request;

	public SubtotalCotizadorTable(ServletRequest request){
		this.request = request;
	}

	public String getHtml() {

		String idEvento = request.getParameter("id_evento");

		ArrayList<Record> records = DataArray.getArrayList("select * from evento_cotizacion_subtotales where id_evento_version ="+  idEvento );

		StringBuilder sb = new StringBuilder();

		ArrayList<HTMLTableColumn> columns = new ArrayList<HTMLTableColumn>();
		columns.add(new HTMLTableColumn("Nombre", 			"descripcion", 	"left", 	"text", 	"onkeyup='updateSubtotalRecord(KEY, this)'"));
		columns.add(new HTMLTableColumn("Texto", 			"texto", 		"left", 	"text", 	"onkeyup='updateSubtotalRecord(KEY, this)'"));
		//columns.add(new HTMLTableColumn("Eliminar", 		"id_cotizacion_subgrupo", 		"center", 	"link", 	"onclick='eliminarSubgrupo(id_cotizacion_subgrupo)'", "<i class=\"fa fa-plus fa-2x\"></i>"));

		String tableWidth = "100%";
		String additionalAttributes = "class=\"table table-bordered\"";


		sb.append(HTMLTable.getHtml(records, columns, tableWidth, additionalAttributes));

		return sb.toString();
	}
}
