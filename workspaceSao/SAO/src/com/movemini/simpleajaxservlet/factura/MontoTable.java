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
 * @movemini MontoTable
 */
public class MontoTable {


	private ServletRequest request;

	private String modo = "DEFAULT";


	public MontoTable(ServletRequest request) {

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

		String id_factura = request.getParameter("ID_FACTURA");

		sb.append("<p><b>*NOTA: Ingresar en el precio unicamete el valor num&eacute;rico.</b> <a style=\"float:right\" href=\"#\" onclick=\"nuevoMonto( '").append(id_factura).append( "')\" type=\"button\" class=\"btn btn-info\">Nuevo Monto</a></p>");
		ArrayList<Record> records = DataArray.getArrayList("select * from factura_monto  where id_factura = " + id_factura);

		ArrayList<HTMLTableColumn> columns = new ArrayList<HTMLTableColumn>();





        columns.add(new HTMLTableColumn("Descripci&oacute;n", 				"descripcion", 		"left", 	"textarea", 	" onkeyup=\"updateMonto(KEY, this, 'descripcion')\""));

		columns.add(new HTMLTableColumn("Precio", 				"cantidad", 		"left", 	"number", 	" step=\"any\" onkeyup=\"updateMonto(KEY, this, 'cantidad')\""));


		//fa-check-square-o

		columns.add(new HTMLTableColumn("Quitar", 					"id", 		"center", 	"link", 	"onclick='deleteMonto(id,"+id_factura+")'", "<i class=\"fa fa-trash fa-2x\"></i>"));





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


		String id_factura = request.getParameter("id_factura");


		ArrayList<Record> records = DataArray.getArrayList("select * from factura_monto where id_factura = " + id_factura);


		ArrayList<HTMLTableColumn> columns = new ArrayList<HTMLTableColumn>();


		if(modo.equals("DEFAULT")) {
			columns.add(new HTMLTableColumn("Seleccionar", 					"id_factura_interprete", 		"center", 	"link", 	"onclick='selectEventoInterprete(id_factura_interprete)'", "<i class=\"fa fa-check-square-o fa-2x\"></i>"));
		}

		columns.add(new HTMLTableColumn("Sala", 					"nombre_sala", 		"left", 	"label", 	""));  //"onkeyup='updateSede(KEY, this)'"

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

		String id_factura = request.getParameter("ID_FACTURA");

		ArrayList<Record> records = DataArray.getArrayList("select * from factura_monto where id_factura= "+id_factura);



		sb.append("<table class=\"table table-bordered\" width='100%'> ");

		for (Record record : records) {

			sb.append("<tr>");

			sb.append("<td>");
			sb.append("</td>");

			String cellTemplate = "<td> Sala: VAR_SALA<br>"
								+ "Fecha: <input type='date' value='VAR_FECHA' onChange='updateInterpreteFecha(id_factura_interprete, this)'><br> "
								+ "Horario: <input type='time' value='VAR_HORA_INI' onChange='updateAsignacionInterpreteCampo(id_factura_interprete, this, 'hora_inicio')'> - "
								+ "<input type='time' value='VAR_HORA_FIN' onChange='updateAsignacionInterpreteCampo(id_factura_interprete, this, 'hora_fin')'><br>"
								+ "Interprete: VAR_INTERPRETE <a href='#' onclick='selectEventoInterprete(id_factura_interprete)'>(Asignar)</a></td>";

			String cellValue = cellTemplate .replace("VAR_SALA", record.get("nombre_sala"))
											.replace("VAR_FECHA", record.get("fecha"))
											.replace("VAR_HORA_INI", record.get("hora_inicio"))
											.replace("VAR_HORA_FIN", record.get("hora_fin"))
											.replace("VAR_INTERPRETE", record.get("interprete"))
											.replace("id_factura_interprete", record.get("id_factura_interprete"))
											.replace("(Asignar)", (record.get("interprete").equals("")? "(Asignar)":"(Reasignar)"));
			sb.append(cellValue);

			sb.append("</tr>");
		}

		sb.append("</table>");



		return sb.toString();
	}







}
