package com.movemini.simpleajaxservlet.agenda;

import java.util.ArrayList;

import javax.servlet.ServletRequest;

import com.movemini.data.DataArray;
import com.movemini.data.OneValueFactory;
import com.movemini.data.Record;
import com.movemini.simpleflexgrid.HTMLTable;
import com.movemini.simpleflexgrid.HTMLTableColumn;

public class FacturasList {



	private ServletRequest request;

	private String modo = "DEFAULT";


	public FacturasList(ServletRequest request) {

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


		ArrayList<Record> records = DataArray.getArrayList("select nfactura,(select round(sum(cantidad),2) from factura_monto fm where fm.id_factura = ef.id_factura) as subtotal,(select round(sum(cantidad)*0.16,2) from factura_monto fm where fm.id_factura = ef.id_factura)as iva ,(select round(sum(cantidad)*1.16,2) from factura_monto fm where fm.id_factura = ef.id_factura)as total from evento_factura ef where id_evento_version =  " + id_evento_version);

		ArrayList<HTMLTableColumn> columns = new ArrayList<HTMLTableColumn>();


		columns.add(new HTMLTableColumn("No. Factura", 				"nfactura", 		"left", 	"label", 	""));

		columns.add(new HTMLTableColumn("Subtotal", 					"subtotal", 			"left", 	"label", 	""));
		columns.add(new HTMLTableColumn("Iva", 					"iva", 			"left", 	"label", 	""));
		columns.add(new HTMLTableColumn("Total", 					"total", 			"left", 	"label", 	""));



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
