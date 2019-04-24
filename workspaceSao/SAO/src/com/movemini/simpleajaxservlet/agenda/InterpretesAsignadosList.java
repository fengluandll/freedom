package com.movemini.simpleajaxservlet.agenda;

import java.util.ArrayList;

import javax.servlet.ServletRequest;

import com.movemini.data.DataArray;
import com.movemini.data.OneValueFactory;
import com.movemini.data.Record;
import com.movemini.simpleflexgrid.HTMLTable;
import com.movemini.simpleflexgrid.HTMLTableColumn;

public class InterpretesAsignadosList {



	private ServletRequest request;

	private String modo = "DEFAULT";


	public InterpretesAsignadosList(ServletRequest request) {

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

		String id_evento = OneValueFactory.get("select id_evento from  evento_version where id_evento_version = " + id_evento_version);

		//--ArrayList<Record> salas = DataArray.getArrayList("evento_sala_select_pr", id_evento);

//		ArrayList<Record> eventoInterpreterecords = DataArray.getArrayList("evento_interprete_select_pr", id_evento);



		String id_tipo_evento = OneValueFactory.get("SELECT id_tipo_eveto FROM evento WHERE id_evento = " + id_evento);




//		sb.append("<table class=\"table table-bordered\" width='100%'>");
//




		//---for (Record sala : salas) {


//			sb.append("<tr>");
//			sb.append("<td><b>" + sala.get("nombre_sala") + "</b>");
//			sb.append("</td>");
//			sb.append("</tr>");
//
//
//			sb.append("<tr>");
//			sb.append("<td align='center'>");


			//sb.append("<div class=\"col-md-2 col-sm-2 col-xs-12\">");
			//sb.append("</div>");


												//for (Record record : eventoInterpreterecords) {




				//--ArrayList<Record> records = DataArray.getArrayList("evento_interprete_select_by_sala_pr", sala.get("id_evento_sala"));
				ArrayList<Record> records = DataArray.getArrayList("evento_interprete_select_all_salas_pr",id_evento_version);
				ArrayList<HTMLTableColumn> columns = new ArrayList<HTMLTableColumn>();

				String interpreteTitle ="Int&eacute;rprete";
                String title2 = "Int&eacute;rpretes";
				if(id_tipo_evento.equals("1")) {

				} else {
					title2 = "Traductores";
					interpreteTitle = "Traductor";
				}
				sb.append("<h5 style='text-align:left'><b>").append(title2).append(":</b> </h5>");


				columns.add(new HTMLTableColumn(interpreteTitle, 				"interprete", 		"left", 	"label", 	""));  //"onkeyup='updateSede(KEY, this)'"

				columns.add(new HTMLTableColumn("Fecha", 					"fecha", 			"left", 	"label", 	"")); //"onChange='updateInterpreteFecha(KEY, this)'"

				
				columns.add(new HTMLTableColumn("Hora Cita", 					"hora_cita", 			"left", 	"label", 	"")); //"onChange='updateInterpreteFecha(KEY, this)'"

				columns.add(new HTMLTableColumn("Hora Inicio", 					"hora_inicio", 			"left", 	"label", 	"")); //"onChange='updateInterpreteFecha(KEY, this)'"
				columns.add(new HTMLTableColumn("Hora Fin", 					"hora_fin", 			"left", 	"label", 	"")); //"onChange='updateInterpreteFecha(KEY, this)'"

				String tableWidth = "100%";
				String additionalAttributes = "class=\"table table-bordered\"";


				sb.append(HTMLTable.getHtml(records, columns, tableWidth, additionalAttributes));



				//}





				//sb.append("<div class=\"col-md-2 col-sm-2 col-xs-12\">");
				//sb.append("</div>");


//			sb.append("</td>");
//			sb.append("</tr>");



		//--}

//		sb.append("</tr>");
//
//		sb.append("</table>");



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
