package com.movemini.tecnico;

import java.util.ArrayList;

import com.movemini.catalogos.ManejadorCatalogos;
import com.movemini.data.DataArray;
import com.movemini.data.OneValueFactory;
import com.movemini.data.Record;
import com.movemini.simpleflexgrid.HTMLTable;
import com.movemini.simpleflexgrid.HTMLTableColumn;

public class TecnicosAsignadosTable {

	public String getHtml(String id_evento_version){
		StringBuilder sb = new StringBuilder();

		//String id_evento = request.getParameter("ID_EVENTO");

		ManejadorCatalogos mnjCatalogos = new ManejadorCatalogos();


		ArrayList<Record> salas = mnjCatalogos.obtenerSalas(id_evento_version);

		String localForaneo = OneValueFactory.get("select ifnull(id_tipo_x,0) from evento inner join evento_version  using (id_evento) where id_evento_version =" + id_evento_version);
		sb.append("<table class=\"table table-bordered\" width='100%'>");

		for (Record sala : salas) {
			sb.append("<tr>");
			sb.append("<td><b>" + sala.get("nombre_sala") + "</b>");
			sb.append("</td>");
			sb.append("</tr>");


			sb.append("<tr>");
			sb.append("<td align='center'>");
				sb.append("<div class=\"col\">");

				sb.append("<div align='right'>");

				sb.append("<a href=\"#\" onclick=\"nuevoEspacioAsignacionTecnico(" + sala.get("id_evento_sala") + ")\"><i class=\"fa fa-plus-circle fa-2x\"></i>Nueva Asignaci&oacute;n</a>");


				sb.append("</div>");

				ArrayList<Record> lstTecnicosAsignados = mnjCatalogos.obtenerTecnicosAsignados(sala.get("id_evento_sala"));

				ArrayList<HTMLTableColumn> columns = new ArrayList<HTMLTableColumn>();

				columns.add(new HTMLTableColumn("Fecha de Instalaci&oacute;n", 					"fecha", 			"left", 	"text", 	"class='date' placeholder='dd/mm/aaaa' pattern='\\d{1,2}/\\d{1,2}/\\d{4}' onChange=\"updateAsignacionTecnicoCampo(KEY, this, 'fecha')\""));
				columns.add(new HTMLTableColumn("Hora Montaje", 				"hora_inicio", 		"left", 	"text", 	"class='time' placeholder='HH:mm' onkeyup=\"updateAsignacionTecnicoCampo(KEY, this, 'hora_inicio')\""));
				//columns.add(new HTMLTableColumn("Hora Fin", 				"hora_fin", 		"left", 	"text", 	"class='time' placeholder='HH:mm' onkeyup=\"updateAsignacionTecnicoCampo(KEY, this, 'hora_fin')\""));

				columns.add(new HTMLTableColumn("Tarifa Completa", 				"tarifa_completa", 		"left", 	"text", 	"onkeyup=\"updateAsignacionTecnicoCampo(KEY, this, 'tarifa_completa')\"", "style='width: 80px;'"));
				//columns.add(new HTMLTableColumn("Media Tarifa", 				"media_tarifa", 		"left", 	"text", 	"onkeyup=\"updateAsignacionTecnicoCampo(KEY, this, 'media_tarifa')\"", "style='width: 80px;'"));
				columns.add(new HTMLTableColumn("Tarifa Viaje", 				"tarifa_viaje", 		"left", 	"text", 	"onkeyup=\"updateAsignacionTecnicoCampo(KEY, this, 'tarifa_viaje')\"", "style='width: 80px;'"));
				//columns.add(new HTMLTableColumn("Hora Extra", 				"horas_extras", 		"left", 	"text", 	"onkeyup=\"updateAsignacionTecnicoCampo(KEY, this, 'horas_extras')\"", "style='width: 80px;'"));
				columns.add(new HTMLTableColumn("Penalizaci&oacute;n", 				"penalizacion", 		"left", 	"text", 	"onkeyup=\"updateAsignacionTecnicoCampo(KEY, this, 'penalizacion')\"", "style='width: 80px;'"));
				
				
				if("2".equals(localForaneo)) {
					System.out.println("foraneo, tecnico");
					columns.add(new HTMLTableColumn("Traslado", 				"traslado", 		"left", 	"text", 	"onkeyup=\"updateAsignacionTecnicoCampo(KEY, this, 'traslado')\"", "style='width: 120px;'"));
					columns.add(new HTMLTableColumn("Hotel", 				"hotel", 		"left", 	"text", 	"onkeyup=\"updateAsignacionTecnicoCampo(KEY, this, 'hotel')\"", "style='width: 120px;'"));
				//	columns.add(new HTMLTableColumn("Alimentos", 				"comida", 		"left", 	"text", 	"onkeyup=\"updateAsignacionTecnicoCampo(KEY, this, 'comida')\"", "style='width: 80px;'"));
						
				}
				
				columns.add(new HTMLTableColumn("T&eacute;cnico", 				"tecnico", 		"left", 	"label", "<span class=\"fa fa-times\" style='cursor:pointer;' onclick='desAsignarTecnico(\"KEY\")'></span>"));  //"onkeyup='updateSede(KEY, this)'"

				//fa-check-square-o
				columns.add(new HTMLTableColumn("Asignar", 					"id_evento_tecnico", 		"center", 	"link", 	"onclick='selectEventoTecnico(id_evento_tecnico)'", "<i class=\"fa fa-user fa-2x\"></i>"));
				columns.add(new HTMLTableColumn("Quitar", 					"id_evento_tecnico", 		"center", 	"link", 	"onclick='eliminarEventoTecnico(id_evento_tecnico)'", "<i class=\"fa fa-trash fa-2x\"></i>"));




				String tableWidth = "100%";
				String additionalAttributes = "class=\"table table-bordered\"";


				sb.append(HTMLTable.getHtml(lstTecnicosAsignados, columns, tableWidth, additionalAttributes));
				sb.append("</div>");
			sb.append("</td>");
			sb.append("</tr>");



		}

		sb.append("</tr>");

		sb.append("</table>");



		return sb.toString();
	}
}
