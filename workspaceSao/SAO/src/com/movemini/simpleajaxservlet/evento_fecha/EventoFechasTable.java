package com.movemini.simpleajaxservlet.evento_fecha;

import java.util.ArrayList;

import javax.servlet.ServletRequest;

import com.movemini.data.DataArray;
import com.movemini.data.Record;
import com.movemini.simpleflexgrid.HTMLTable;
import com.movemini.simpleflexgrid.HTMLTableColumn;

public class EventoFechasTable {



	private ServletRequest request;

	public EventoFechasTable(ServletRequest request) {

		this.request = request;
	}


	public String getHtml() {

		String id_evento = request.getParameter("id_evento");

		ArrayList<Record> records = DataArray.getArrayList("SELECT id_evento_fecha,  id_evento_version,DATE_FORMAT(fecha,'%d/%m/%Y') fecha FROM evento_fecha WHERE id_evento_version =" +id_evento);

		StringBuilder sb = new StringBuilder();

		ArrayList<HTMLTableColumn> columns = new ArrayList<HTMLTableColumn>();



		columns.add(new HTMLTableColumn("Fecha", 						"fecha", 		"left", 	"text", 	"class='date' placeholder='dd/mm/aaaa' pattern='\\d{1,2}/\\d{1,2}/\\d{4}' onChange='updateSalaFechaEvento(KEY, this)'"));
		//columns.add(new HTMLTableColumn("Hora Inicio", 					"hora_inicio", 		"left", 	"time", 	"onkeyup=\"updateSalaCampo(KEY, this, 'hora_inicio')\""));
		//columns.add(new HTMLTableColumn("Hora Fin", 					"hora_fin", 		"left", 	"time", 	"onkeyup=\"updateSalaCampo(KEY, this, 'hora_fin')\""));

		//////////////columns.add(new HTMLTableColumn("Ver Detalle", 					"id_evento_sala_dia", 		"center", 	"link", 	"onclick='selectFecha(id_evento_sala_dia)'", "<i class=\"fa fa-pencil fa-2x\"></i>"));

		//columns.add(new HTMLTableColumn("Hora Comida", 					"hora_comida", 		"left", 	"time", 	"onkeyup=\"updateSalaCampo(KEY, this, 'hora_comida')\""));

		//columns.add(new HTMLTableColumn("Capacidad Registro", 			"registros_hora", 	"right", 	"text4", 	"onkeyup=\"updateSalaCampo(KEY, this, 'registros_hora')\""));
		//columns.add(new HTMLTableColumn("Empleados Atendidos", 			"empleados_atendidos", 	"right", 	"text4", ""));
		//onclick='if(confirm('Seguro?')){deleteSedeFecha(id);}'
		columns.add(new HTMLTableColumn("Borrar Fecha", 					"id_evento_fecha", 				"center", 	"link", 	"onclick='if(confirm(\"¿Seguro que desea eliminar el Día?\")){deleteSalaFechaEvento(id_evento_fecha);}'", "<i class=\"fa fa-close fa-2x\"></i>"));


		String tableWidth = "100%";
		String additionalAttributes = "class=\"table table-bordered\"";


		sb.append(HTMLTable.getHtml(records, columns, tableWidth, additionalAttributes));





		return sb.toString();
	}


}
