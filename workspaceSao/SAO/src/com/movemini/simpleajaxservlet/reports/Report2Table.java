package com.movemini.simpleajaxservlet.reports;

import java.util.ArrayList;

import javax.servlet.ServletRequest;

import com.movemini.data.DataArray;
import com.movemini.data.Record;
import com.movemini.simpleflexgrid.HTMLTable;
import com.movemini.simpleflexgrid.HTMLTableColumn;

public class Report2Table {


	private ServletRequest request;
	
	public Report2Table(ServletRequest request) {

		this.request = request;
	}
	
	
	public String getHtml() {
		
//		String Cliente = request.getParameter("Cliente");
//		String Fecha_Ini = request.getParameter("Fecha_Ini");
//		String Fecha_Fin = request.getParameter("Fecha_Fin");
//		
////		
//		
//		ArrayList<Record> records = DataArray.getArrayList("report_1_get_table", Cotizacion, Cliente, Evento, Estatus);
//	
//		StringBuilder sb = new StringBuilder();
//			
//		ArrayList<HTMLTableColumn> columns = new ArrayList<HTMLTableColumn>();
//		
//		
//		
//		
//		columns.add(new HTMLTableColumn("Cotizacion", 			"Cotizacion", 		"left", 	"label", 	"")); //"onkeyup='updateSede(KEY, this)'"
//		columns.add(new HTMLTableColumn("Cliente", 				"Cliente", 			"left", 	"label", 	"")); //"onkeyup='updateSede(KEY, this)'"
//		columns.add(new HTMLTableColumn("Evento", 				"Evento", 			"left", 	"label", 	"")); //"onkeyup='updateSede(KEY, this)'"
//		columns.add(new HTMLTableColumn("Estatus", 				"Estatus", 			"left", 	"label", 	"")); //"onkeyup='updateSede(KEY, this)'"
//		columns.add(new HTMLTableColumn("Total Facturado", 		"Total_Facturado", 	"right", 	"label", 	"")); //"onkeyup='updateSede(KEY, this)'"
//		
//		
//		String tableWidth = "100%";
//		String additionalAttributes = "class=\"table table-bordered\"";
//		
//		
//		sb.append(HTMLTable.getHtml(records, columns, tableWidth, additionalAttributes));
//		
//		
//		
//		return sb.toString();
//		
//		
//		
		
		

		StringBuilder sb = new StringBuilder();
		
		String Cliente = request.getParameter("Cliente");
		String Fecha_Ini = request.getParameter("Fecha_Ini");
		String Fecha_Fin = request.getParameter("Fecha_Fin");
		
	
		
		ArrayList<Record> interpretes = DataArray.getArrayList("interpretes_by_fechas_pr", Cliente, Fecha_Ini, Fecha_Fin);
		
//		ArrayList<Record> eventoInterpreterecords = DataArray.getArrayList("evento_interprete_select_pr", id_evento);
		
		
		
		sb.append("<table class=\"table table-bordered\" width='100%'>");
		
		
		
		
		for (Record interprete : interpretes) {
			
			
			sb.append("<tr>");
			sb.append("<td><b>" + interprete.get("nombre_interprete") + "</b>");
			sb.append("</td>");
			sb.append("</tr>");
			
			
			sb.append("<tr>");
			sb.append("<td align='center'>");
												
			
			sb.append("<div class=\"col-md-2 col-sm-2 col-xs-12\">");
			sb.append("</div>");
			
				sb.append("<div class=\"col-md-8 col-sm-8 col-xs-12\">");
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
														
				
				
				
				
				ArrayList<Record> recordsDates = DataArray.getArrayList("interprete_fechas_pr", interprete.get("id_interprete"), Fecha_Ini, Fecha_Fin);
				
				ArrayList<HTMLTableColumn> columns = new ArrayList<HTMLTableColumn>();

				columns.add(new HTMLTableColumn("Evento", 				"nombre", 		"left", 	"label", 	"")); 
				columns.add(new HTMLTableColumn("Fecha", 					"fecha", 			"left", 	"label", 	""));  
				columns.add(new HTMLTableColumn("Hora Inicio", 				"hora_inicio", 		"left", 	"label", 	""));  
				columns.add(new HTMLTableColumn("Hora Fin", 				"hora_fin", 		"left", 	"label", 	""));  
	
				
				columns.add(new HTMLTableColumn("Tarifa Completa", 				"tarifa_completa", 		"left", 	"label", 	""));
				columns.add(new HTMLTableColumn("Media Tarifa", 				"media_tarifa", 		"left", 	"label", 	""));
				columns.add(new HTMLTableColumn("Tarifa Viaje", 				"tarifa_viaje", 		"left", 	"label", 	""));
				columns.add(new HTMLTableColumn("Horas Extras", 				"horas_extras", 		"left", 	"label", 	""));
				columns.add(new HTMLTableColumn("Penalizaci&oacute;n", 				"penalizacion", 		"left", 	"label", 	""));
				
				
				
				String tableWidth = "100%";
				String additionalAttributes = "class=\"table table-bordered\"";
				
				
				sb.append(HTMLTable.getHtml(recordsDates, columns, tableWidth, additionalAttributes));
				
				
				
				//}
		
				
				
				sb.append("</div>");
				
				sb.append("<div class=\"col-md-2 col-sm-2 col-xs-12\">");
				sb.append("</div>");
				
				
			sb.append("</td>");
			sb.append("</tr>");
			
			
			
		}
		
		sb.append("</tr>");
		
		sb.append("</table>");
		
		
		
		return sb.toString();
		
		
		
		
		
	}
	
	
	
	
	
}
