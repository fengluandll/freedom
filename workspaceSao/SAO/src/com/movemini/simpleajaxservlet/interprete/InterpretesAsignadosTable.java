package com.movemini.simpleajaxservlet.interprete;

import java.util.ArrayList;

import javax.servlet.ServletRequest;

import com.movemini.data.DataArray;
import com.movemini.data.OneValueFactory;
import com.movemini.data.Record;
import com.movemini.simpleflexgrid.HTMLTable;
import com.movemini.simpleflexgrid.HTMLTableColumn;
import com.movemini.util.StringUtils;
import com.movemini.debug.DebugTools;


/**
 * DOCUMENTACION
 *
 * @movemini html_table_select_evento_interprete
 */
public class InterpretesAsignadosTable {


	private ServletRequest request;

	private String modo = "DEFAULT";


	public InterpretesAsignadosTable(ServletRequest request) {

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
		DebugTools.println("Version " + id_evento_version,InterpretesAsignadosTable.class);

		ArrayList<Record> salas = DataArray.getArrayList("evento_sala_select_pr", id_evento_version);

//		ArrayList<Record> eventoInterpreterecords = DataArray.getArrayList("evento_interprete_select_pr", id_evento);



		String id_tipo_evento = OneValueFactory.get("SELECT id_tipo_eveto FROM evento WHERE id_evento = ( select id_evento from evento_version where id_evento_version = " + id_evento_version+ ")");




		sb.append("<table class=\"table table-bordered\" width='100%'>");

		for (Record sala : salas) {
			
			if( !"MEMO".equals(modo)){
				if("CITAS".equals(modo)) {
					System.out.println("modo citas");
					sb.append("<tr>");
					//sb.append("<td><br>Encabezado: " + sala.get("nombre_sala"));
					sb.append("</td>");
					sb.append("</tr>");


					sb.append("<tr>");
					sb.append("<td align='center'>");


					//sb.append("<div class=\"col-md-2 col-sm-2 col-xs-12\">");
					//sb.append("</div>");

						sb.append("<div class=\"col\" >");
														//for (Record record : eventoInterpreterecords) {


											//
											//				String cellTemplate = "<td> Sala: VAR_SALA<br>"
											//									+ "Fecha: <input type='date' value='VAR_FECHA' onChange='updateInterpreteFecha(id_evento_interprete, this)'><br> "
											//									+ "Horario: <input type='time' value='VAR_HORA_INI' onChange='updateAsignacionInterpreteCampo(id_evento_interprete, this, 'hora_inicio')'> - "
											//									+ "<input type='time' value='VAR_HORA_FIN' onChange='updateAsignacionInterpreteCampo(id_evento_interprete, this, 'hora_fin')'><br>"
											//									+ "Interprete: VAR_INTERPRETE <a href='#' onclick='selectEventoInterprete(id_evento_interprete)'>(Asignar)</a></td>";
											//
											//				String cellValue = cellTemplate .replace("VAR_SALA", record.get("nombre_sala"))
											//												.replace("VAR_FECHA", record.get("fecha"))
											//												.replace("VAR_HORA_INI", record.get("hora_inicio"))
											//												.replace("VAR_HORA_FIN", record.get("hora_fin"))
											//												.replace("VAR_INTERPRETE", record.get("interprete"))
											//												.replace("id_evento_interprete", record.get("id_evento_interprete"))
											//												.replace("(Asignar)", (record.get("interprete").equals("")? "(Asignar)":"(Reasignar)"));
											//				sb.append(cellValue);
											//




						ArrayList<Record> records = DataArray.getArrayList("evento_interprete_select_by_sala_pr", sala.get("id_evento_sala"));

						ArrayList<HTMLTableColumn> columns = new ArrayList<HTMLTableColumn>();

						String interpreteTitle = id_tipo_evento.equals("1")?"Int&eacute;rprete":"Traductor";
						
						columns.add(new HTMLTableColumn(interpreteTitle, 				"interprete", 		"left", 	"label", 	""));  //"onkeyup='updateSede(KEY, this)'"
						
						columns.add(new HTMLTableColumn("Fecha", 					"fecha", 			"center", 	"label", 	""));


						if(id_tipo_evento.equals("1")) {
							columns.add(new HTMLTableColumn("Hora Cita", 				"hora_cita", 		"center", 	"label", ""));
						} else {
							columns.add(new HTMLTableColumn("Tarifa (Costo)", 				"tarifa_completa", 		"center", 	"label", 	""));
						}

						
						//NEW
						columns.add(new HTMLTableColumn("Hora Inicio", 				"hora_inicio", 		"center", 	"label", 	""));
						columns.add(new HTMLTableColumn("Hora Fin", 				"hora_fin", 		"center", 	"label", 	""));

						


						String tableWidth = "100%";
						String additionalAttributes = "class=\" horarioInterpretacion\"";


						sb.append(HTMLTable.getHtml(records, columns, tableWidth, additionalAttributes));



						//}



						sb.append("</div>");

						//sb.append("<div class=\"col-md-2 col-sm-2 col-xs-12\">");
						//sb.append("</div>");


					sb.append("<br></td>");
					sb.append("</tr>");

				}else {
					sb.append("<tr>");
					sb.append("<td><b>" + sala.get("nombre_sala") + "</b>");
					sb.append("</td>");
					sb.append("</tr>");


					sb.append("<tr>");
					sb.append("<td align='center'>");


					//sb.append("<div class=\"col-md-2 col-sm-2 col-xs-12\">");
					//sb.append("</div>");

						sb.append("<div class=\"col\">");
														//for (Record record : eventoInterpreterecords) {


											//
											//				String cellTemplate = "<td> Sala: VAR_SALA<br>"
											//									+ "Fecha: <input type='date' value='VAR_FECHA' onChange='updateInterpreteFecha(id_evento_interprete, this)'><br> "
											//									+ "Horario: <input type='time' value='VAR_HORA_INI' onChange='updateAsignacionInterpreteCampo(id_evento_interprete, this, 'hora_inicio')'> - "
											//									+ "<input type='time' value='VAR_HORA_FIN' onChange='updateAsignacionInterpreteCampo(id_evento_interprete, this, 'hora_fin')'><br>"
											//									+ "Interprete: VAR_INTERPRETE <a href='#' onclick='selectEventoInterprete(id_evento_interprete)'>(Asignar)</a></td>";
											//
											//				String cellValue = cellTemplate .replace("VAR_SALA", record.get("nombre_sala"))
											//												.replace("VAR_FECHA", record.get("fecha"))
											//												.replace("VAR_HORA_INI", record.get("hora_inicio"))
											//												.replace("VAR_HORA_FIN", record.get("hora_fin"))
											//												.replace("VAR_INTERPRETE", record.get("interprete"))
											//												.replace("id_evento_interprete", record.get("id_evento_interprete"))
											//												.replace("(Asignar)", (record.get("interprete").equals("")? "(Asignar)":"(Reasignar)"));
											//				sb.append(cellValue);
											//


						sb.append("<div align='right'>");

						sb.append("<a href=\"#\" onclick=\"nuevoEspacioAsignacionInterprete(" + sala.get("id_evento_sala") + ")\"><i class=\"fa fa-plus-circle fa-2x\"></i>Nueva Asignaci&oacute;n</a>");


						sb.append("</div>");


						ArrayList<Record> records = DataArray.getArrayList("evento_interprete_select_by_sala_pr", sala.get("id_evento_sala"));

						ArrayList<HTMLTableColumn> columns = new ArrayList<HTMLTableColumn>();

						columns.add(new HTMLTableColumn("Fecha", 					"fecha", 			"left", 	"text", 	"class='date' placeholder='dd/mm/aaaa' pattern='\\d{1,2}/\\d{1,2}/\\d{4}'   onChange='updateInterpreteFecha(KEY, this)'"));


						String interpreteTitle = id_tipo_evento.equals("1")?"Int&eacute;rprete":"Traductor";
						
						String localForaneo = OneValueFactory.get("select ifnull(id_tipo_x,0) from evento inner join evento_version  using (id_evento) where id_evento_version =" + id_evento_version);

						if(id_tipo_evento.equals("1")) {
							columns.add(new HTMLTableColumn("Hora Cita", 				"hora_cita", 		"left", 	"text", 	"class='time' placeholder='HH:mm' onkeyup=\"updateAsignacionInterpreteCampo(KEY, this, 'hora_cita')\""));
							columns.add(new HTMLTableColumn("Hora Inicio", 				"hora_inicio", 		"left", 	"text", 	"class='time' placeholder='HH:mm' onkeyup=\"updateAsignacionInterpreteCampo(KEY, this, 'hora_inicio')\""));
							columns.add(new HTMLTableColumn("Hora Fin", 				"hora_fin", 		"left", 	"text", 	"class='time' placeholder='HH:mm' onkeyup=\"updateAsignacionInterpreteCampo(KEY, this, 'hora_fin')\""));

							columns.add(new HTMLTableColumn("Tarifa Completa", 				"tarifa_completa", 		"left", 	"text", 	"onkeyup=\"updateAsignacionInterpreteCampo(KEY, this, 'tarifa_completa')\"", "style='width: 80px;'"));
							columns.add(new HTMLTableColumn("Media Tarifa", 				"media_tarifa", 		"left", 	"text", 	"onkeyup=\"updateAsignacionInterpreteCampo(KEY, this, 'media_tarifa')\"", "style='width: 80px;'"));
							columns.add(new HTMLTableColumn("Tarifa Viaje", 				"tarifa_viaje", 		"left", 	"text", 	"onkeyup=\"updateAsignacionInterpreteCampo(KEY, this, 'tarifa_viaje')\"", "style='width: 80px;'"));
							
							if("2".equals(localForaneo)) {
								System.out.println("foraneo, interprete");
								columns.add(new HTMLTableColumn("Traslado", 				"traslado", 		"left", 	"text", 	"onkeyup=\"updateAsignacionInterpreteCampo(KEY, this, 'traslado')\"", "style='width: 120px;'"));
								columns.add(new HTMLTableColumn("Hotel", 				"hotel", 		"left", 	"text", 	"onkeyup=\"updateAsignacionInterpreteCampo(KEY, this, 'hotel')\"", "style='width: 120px;'"));
								//columns.add(new HTMLTableColumn("Alimentos", 				"comida", 		"left", 	"text", 	"onkeyup=\"updateAsignacionInterpreteCampo(KEY, this, 'comida')\"", "style='width: 80px;'"));
									
							}
							
							//columns.add(new HTMLTableColumn("Hora Extra", 				"horas_extras", 		"left", 	"text", 	"onkeyup=\"updateAsignacionInterpreteCampo(KEY, this, 'horas_extras')\"", "style='width: 80px;'"));
							columns.add(new HTMLTableColumn("Penalizaci&oacute;n", 				"penalizacion", 		"left", 	"text", 	"onkeyup=\"updateAsignacionInterpreteCampo(KEY, this, 'penalizacion')\"", "style='width: 80px;'"));

						} else {
							columns.add(new HTMLTableColumn("Tarifa (Costo)", 				"tarifa_completa", 		"left", 	"text", 	"onkeyup=\"updateAsignacionInterpreteCampo(KEY, this, 'tarifa_completa')\"", "style='width: 80px;'"));
						}



						columns.add(new HTMLTableColumn(interpreteTitle, 				"interprete", 		"left", 	"label", 	"<span class=\"fa fa-times\" style='cursor:pointer;' onclick='desAsignarInterprete(\"KEY\")'></span>"));  //"onkeyup='updateSede(KEY, this)'"

						//fa-check-square-o
						columns.add(new HTMLTableColumn("Asignar", 					"id_evento_interprete", 		"center", 	"link", 	"onclick='selectEventoInterprete(id_evento_interprete)'", "<i class=\"fa fa-user fa-2x\"></i>"));
						columns.add(new HTMLTableColumn("Quitar", 					"id_evento_interprete", 		"center", 	"link", 	"onclick='eliminarEventoInterprete(id_evento_interprete)'", "<i class=\"fa fa-trash fa-2x\"></i>"));





						String tableWidth = "100%";
						String additionalAttributes = "class=\"table table-bordered\"";


						sb.append(HTMLTable.getHtml(records, columns, tableWidth, additionalAttributes));



						//}



						sb.append("</div>");

						//sb.append("<div class=\"col-md-2 col-sm-2 col-xs-12\">");
						//sb.append("</div>");


					sb.append("</td>");
					sb.append("</tr>");

				}
				

			}else{
				sb.append("<tr>");
			//	sb.append("<td><br>Encabezado: " + sala.get("nombre_sala"));
				sb.append("</td>");
				sb.append("</tr>");


				sb.append("<tr>");
				sb.append("<td align='center'>");


				//sb.append("<div class=\"col-md-2 col-sm-2 col-xs-12\">");
				//sb.append("</div>");

					sb.append("<div class=\"col\" >");
													//for (Record record : eventoInterpreterecords) {


										//
										//				String cellTemplate = "<td> Sala: VAR_SALA<br>"
										//									+ "Fecha: <input type='date' value='VAR_FECHA' onChange='updateInterpreteFecha(id_evento_interprete, this)'><br> "
										//									+ "Horario: <input type='time' value='VAR_HORA_INI' onChange='updateAsignacionInterpreteCampo(id_evento_interprete, this, 'hora_inicio')'> - "
										//									+ "<input type='time' value='VAR_HORA_FIN' onChange='updateAsignacionInterpreteCampo(id_evento_interprete, this, 'hora_fin')'><br>"
										//									+ "Interprete: VAR_INTERPRETE <a href='#' onclick='selectEventoInterprete(id_evento_interprete)'>(Asignar)</a></td>";
										//
										//				String cellValue = cellTemplate .replace("VAR_SALA", record.get("nombre_sala"))
										//												.replace("VAR_FECHA", record.get("fecha"))
										//												.replace("VAR_HORA_INI", record.get("hora_inicio"))
										//												.replace("VAR_HORA_FIN", record.get("hora_fin"))
										//												.replace("VAR_INTERPRETE", record.get("interprete"))
										//												.replace("id_evento_interprete", record.get("id_evento_interprete"))
										//												.replace("(Asignar)", (record.get("interprete").equals("")? "(Asignar)":"(Reasignar)"));
										//				sb.append(cellValue);
										//




					ArrayList<Record> records = DataArray.getArrayList("evento_interprete_select_by_sala_pr", sala.get("id_evento_sala"));

					ArrayList<HTMLTableColumn> columns = new ArrayList<HTMLTableColumn>();

					String interpreteTitle = id_tipo_evento.equals("1")?"Int&eacute;rprete":"Traductor";
					columns.add(new HTMLTableColumn(interpreteTitle, 				"interprete", 		"left", 	"label", 	""));  //"onkeyup='updateSede(KEY, this)'"
					columns.add(new HTMLTableColumn("Fecha", 					"fecha", 			"center", 	"label", 	""));



					
					if(id_tipo_evento.equals("1")) {
						columns.add(new HTMLTableColumn("Hora Inicio", 				"hora_inicio", 		"center", 	"label", 	""));
						columns.add(new HTMLTableColumn("Hora Fin", 				"hora_fin", 		"center", 	"label", 	""));


					} else {


						columns.add(new HTMLTableColumn("Tarifa (Costo)", 				"tarifa_completa", 		"left", 	"label", 	""));


					}

					


					String tableWidth = "100%";
					String additionalAttributes = "class=\" horarioInterpretacion\"";


					sb.append(HTMLTable.getHtml(records, columns, tableWidth, additionalAttributes));



					//}



					sb.append("</div>");

					//sb.append("<div class=\"col-md-2 col-sm-2 col-xs-12\">");
					//sb.append("</div>");


				sb.append("<br></td>");
				sb.append("</tr>");

			}
		}

		sb.append("</tr>");

		sb.append("</table>");



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
