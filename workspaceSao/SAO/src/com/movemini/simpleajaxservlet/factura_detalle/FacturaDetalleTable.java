package com.movemini.simpleajaxservlet.factura_detalle;

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
public class FacturaDetalleTable {


	private ServletRequest request;

	private String modo = "DEFAULT";


	public FacturaDetalleTable(ServletRequest request) {

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

		String id_evento_factura = request.getParameter("ID_FACTURA");

		sb.append(" <a style=\"float:right\" href=\"#\" onclick=\"nuevoDetalleFactura( '").append(id_evento_factura).append( "')\" type=\"button\" class=\"btn btn-info\">Nuevo Campo</a>");
		ArrayList<Record> records = DataArray.getArrayList("factura_detalle_select_pr", id_evento_factura);

		ArrayList<HTMLTableColumn> columns = new ArrayList<HTMLTableColumn>();



		columns.add(new HTMLTableColumn("Campo", 				"campo", 		"left", 	"text", 	" onkeyup=\"updateDetalleFactura(KEY, this, 'campo','"+id_evento_factura+"')\""));

  	columns.add(new HTMLTableColumn("Valor", 					"valor", 		"center", 	"text", 	"onkeyup=\"updateDetalleFactura(KEY, this, 'valor','"+id_evento_factura+"')\""));
		columns.add(new HTMLTableColumn("Quitar", 					"id", 		"center", 	"link", 	"onclick='deleteDetalleFactura(id,"+ id_evento_factura+")'", "<i class=\"fa fa-trash fa-2x\"></i>"));

		String tableWidth = "100%";
		String additionalAttributes = "class=\"table table-bordered\"";


		sb.append(HTMLTable.getHtml(records, columns, tableWidth, additionalAttributes));

		return sb.toString();
	}


}
