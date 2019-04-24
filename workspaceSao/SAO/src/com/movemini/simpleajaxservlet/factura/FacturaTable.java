package com.movemini.simpleajaxservlet.factura;

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
public class FacturaTable {


	private ServletRequest request;

	private String modo = "DEFAULT";


	public FacturaTable(ServletRequest request) {

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

		sb.append(" <a style=\"float:right\" href=\"#\" onclick=\"nuevaFactura( '").append(id_evento_version).append( "')\" type=\"button\" class=\"btn btn-info\">Nueva Factura</a>");
		ArrayList<Record> records = DataArray.getArrayList("evento_factura_select_pr", id_evento_version);

		ArrayList<HTMLTableColumn> columns = new ArrayList<HTMLTableColumn>();



		columns.add(new HTMLTableColumn("No. Factura", 				"nfactura", 		"left", 	"text", 	" onkeyup=\"updateFactura(KEY, this, 'nfactura')\""));

  	columns.add(new HTMLTableColumn("Detalles", 					"id_factura", 		"center", 	"link", 	"onclick='mostrarModalDetalleFacturas(id_factura,"+id_evento_version+")'", "<i class=\"fa fa-money fa-2x\"></i>"));

		//columns.add(new HTMLTableColumn("Montos",                                      "id_factura",           "center",       "link",         "onclick='montosFactura(id_factura)'", "<i class=\"fa fa-money fa-2x\"></i>"));

		//columns.add(new HTMLTableColumn("Ver",                                  "id_factura",           "center",       "link",         "onclick='previewFactura(id_factura,"+id_evento_version+")'", "<i class=\"fa fa-eye fa-2x\"></i>"));

		columns.add(new HTMLTableColumn("Raz&oacute;n Social",                "id_razon",           "center",       "select",         "onchange=\"updateFactura(KEY, this, 'id_razon')\"" ,"","select id_razon_social, razon_social from crm_razon_social where id_status = 1 and id_cliente = (select id_cliente from evento where id_evento = (select id_evento from evento_version where id_evento_version = "+id_evento_version+"))"));

	 	columns.add(new HTMLTableColumn("PDF",                                  "id_factura",           "center",       "link",         " target='_blank' href=\"/SAO/home/factura_pdf.jsp?ID_EVENTO="+id_evento_version+"&idFactura=id_factura\"", "<i class=\"fa fa-file-pdf-o fa-2x\"></i>"));

		columns.add(new HTMLTableColumn("Observaciones", 				"observaciones", 		"left", 	"textarea", 	" onkeyup=\"updateFactura(KEY, this, 'observaciones')\""));

		columns.add(new HTMLTableColumn("Quitar", 					"id_factura", 		"center", 	"link", 	"onclick='deleteFactura(id_factura,"+id_evento_version+")'", "<i class=\"fa fa-trash fa-2x\"></i>"));





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




	public String getHtmlVersionAnterior1() {

		StringBuilder sb = new StringBuilder();


		String id_evento_version = request.getParameter("ID_EVENTO");


		ArrayList<Record> records = DataArray.getArrayList("evento_interprete_select_pr", id_evento_version);


		ArrayList<HTMLTableColumn> columns = new ArrayList<HTMLTableColumn>();


		if(modo.equals("DEFAULT")) {
			columns.add(new HTMLTableColumn("Seleccionar", 					"id_evento_interprete", 		"center", 	"link", 	"onclick='selectEventoInterprete(id_evento_interprete)'", "<i class=\"fa fa-check-square-o fa-2x\"></i>"));
		}

		columns.add(new HTMLTableColumn("Sala", 					"nombre_sala", 		"left", 	"label", 	""));  //"onkeyup='updateSede(KEY, this)'"

//		columns.add(new HTMLTableColumn("Fecha", 					"fecha", 		"left", 	"label", 	""));
//		columns.add(new HTMLTableColumn("Hora Inicio", 				"hora_inicio", 		"left", 	"label", 	""));
//		columns.add(new HTMLTableColumn("Hora Fin", 				"hora_fin", 		"left", 	"label", 	""));

		columns.add(new HTMLTableColumn("Fecha", 					"fecha", 			"left", 	"date", 	"onChange='updateInterpreteFecha(KEY, this)'"));
		columns.add(new HTMLTableColumn("Hora Inicio", 				"hora_inicio", 		"left", 	"time", 	"onkeyup=\"updateAsignacionInterpreteCampo(KEY, this, 'hora_inicio')\""));
		columns.add(new HTMLTableColumn("Hora Fin", 				"hora_fin", 		"left", 	"time", 	"onkeyup=\"updateAsignacionInterpreteCampo(KEY, this, 'hora_fin')\""));



		columns.add(new HTMLTableColumn("Interprete", 				"interprete", 		"left", 	"label", 	""));  //"onkeyup='updateSede(KEY, this)'"





		String tableWidth = "100%";
		String additionalAttributes = "class=\"table table-bordered\"";


		sb.append(HTMLTable.getHtml(records, columns, tableWidth, additionalAttributes));



		return sb.toString();
	}






	//
	//
	// CUSTOMIZED
	//
	//
	public String getHtmlVersionAnterior2() {

		StringBuilder sb = new StringBuilder();

		String id_evento_version = request.getParameter("ID_EVENTO");

		ArrayList<Record> records = DataArray.getArrayList("evento_interprete_select_pr", id_evento_version);



		sb.append("<table class=\"table table-bordered\" width='100%'> ");

		for (Record record : records) {

			sb.append("<tr>");

			sb.append("<td>");
			sb.append("</td>");

			String cellTemplate = "<td> Sala: VAR_SALA<br>"
								+ "Fecha: <input type='date' value='VAR_FECHA' onChange='updateInterpreteFecha(id_evento_interprete, this)'><br> "
								+ "Horario: <input type='time' value='VAR_HORA_INI' onChange='updateAsignacionInterpreteCampo(id_evento_interprete, this, 'hora_inicio')'> - "
								+ "<input type='time' value='VAR_HORA_FIN' onChange='updateAsignacionInterpreteCampo(id_evento_interprete, this, 'hora_fin')'><br>"
								+ "Interprete: VAR_INTERPRETE <a href='#' onclick='selectEventoInterprete(id_evento_interprete)'>(Asignar)</a></td>";

			String cellValue = cellTemplate .replace("VAR_SALA", record.get("nombre_sala"))
											.replace("VAR_FECHA", record.get("fecha"))
											.replace("VAR_HORA_INI", record.get("hora_inicio"))
											.replace("VAR_HORA_FIN", record.get("hora_fin"))
											.replace("VAR_INTERPRETE", record.get("interprete"))
											.replace("id_evento_interprete", record.get("id_evento_interprete"))
											.replace("(Asignar)", (record.get("interprete").equals("")? "(Asignar)":"(Reasignar)"));
			sb.append(cellValue);

			sb.append("</tr>");
		}

		sb.append("</table>");



		return sb.toString();
	}







}
