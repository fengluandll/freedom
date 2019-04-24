package com.movemini.simpleajaxservlet.evento_sala;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletRequest;

import com.movemini.data.ConnectionWrapper;
import com.movemini.data.DataArray;
import com.movemini.data.OneValueFactory;
import com.movemini.data.Record;
import com.movemini.simpleflexgrid.HTMLTable;
import com.movemini.simpleflexgrid.HTMLTableColumn;



public class SalasTable {

	private ServletRequest request;

	public SalasTable(ServletRequest request) {

		this.request = request;
	}


	public String getHtml() {

		String id_evento_version = request.getParameter("ID_EVENTO");
		String id_version = request.getParameter("id_version");

		String id_evento = OneValueFactory.get("select id_evento from evento_version where id_evento_version = "+ id_evento_version);


		String id_tipo_evento = OneValueFactory.get("SELECT id_tipo_eveto FROM evento WHERE id_evento = " + id_evento);


		ArrayList<Record> records = DataArray.getArrayList("SELECT * FROM evento_sala WHERE id_evento_version = " + id_evento_version);

				//"evento_sede_select_by_evento_id_pr", id_evento);

		StringBuilder sb = new StringBuilder();

		ArrayList<HTMLTableColumn> columns = new ArrayList<HTMLTableColumn>();

		//columns.add(new HTMLTableColumn("Título", 				"nombre_sala", 		"left", 	"textarea350", 	"onkeyup='updateSalaNombre(KEY, this)'"));
		columns.add(new HTMLTableColumn("T&iacute;tulo", 				"nombre_sala", 		"left", 	"textarea250", 	"onkeyup='updateSalaNombre(KEY, this)'"));

		if(id_tipo_evento.equals("1")) {

			columns.add(new HTMLTableColumn("D&iacute;as", 				"cantidad_dias", 		"left", 	"text4", 	"onkeyup='updateSalaCantidad(KEY, this)' onkeypress='return soloNumero(this)'"));

			columns.add(new HTMLTableColumn("Salas", 				"cantidad_salas", 		"left", 	"text4", 	"onkeyup='updateEventoSede(KEY, this)' onkeypress='return soloNumero(this)'"));
			columns.add(new HTMLTableColumn("Multiplicar D&iacute;as", 	"multiplicar_dias", 	"left", 	"select3", 	"onChange='updateEventoSede(KEY,this)'", "", "SELECT id, nombre from cat_si_no"));
			columns.add(new HTMLTableColumn("Multiplicar Salas", 	"multiplicar_salas", 	"left", 	"select3", 	"onChange='updateEventoSede(KEY,this)'", "", "SELECT id, nombre from cat_si_no"));

			columns.add(new HTMLTableColumn("Texto Suma", 				"texto_subtotal_1", 		"left", 	"textarea150", 	"onkeyup='updateEventoSede(KEY, this)'"));
			columns.add(new HTMLTableColumn("Texto Multiplicaci&oacute;n", 				"texto_subtotal_2", 		"left", 	"textarea150", 	"onkeyup='updateEventoSede(KEY, this)'"));

			columns.add(new HTMLTableColumn("Subtotal", 					"id_subtotal", 		"center", 	"select", 	 	"onChange='updateEventoSede(KEY,this)'", "", "SELECT id, descripcion from evento_cotizacion_subtotales where id_evento_version  = " +id_evento_version));

		}

		columns.add(new HTMLTableColumn("Ver Detalle", 					"id_evento_sala", 		"center", 	"link", 	"onclick='selectSala(id_evento_sala)'", "<i class=\"fa fa-calendar fa-2x\"></i>"));

		//columns.add(new HTMLTableColumn("Borrar Sede", 					"id", 		"center", 	"link", 	"onclick='if(confirm('Seguro?')){deleteSede(id);}'", "<i class=\"fa fa-close fa-2x\"></i>"));
		//deleteSede(id)
		columns.add(new HTMLTableColumn("Borrar Encabezado", 					"id_evento_sala", 		"center", 	"link", 	"onclick='swal({title:\"Eliminar\",text:\"¿Seguro que desea eliminar la Sala?\",type:\"warning\",showCancelButton:!0,confirmButtonColor:\"#DD6B55\",confirmButtonText:\"Sí, eliminar\",cancelButtonText:\"No, cancelar\",closeOnConfirm:!1,closeOnCancel:!1},function(a){a?(deleteSala(id_evento_sala),swal(\"¡Eliminada!\",\"Sala eliminada correctamente\",\"success\")):swal(\"Cancelado\",\"No se ha eliminado nada\",\"info\")});'", "<i class=\"fa fa-close fa-2x\"></i>"));





		String tableWidth = "100%";
		String additionalAttributes = "class=\"table table-bordered\"";


		sb.append(HTMLTable.getHtml(records, columns, tableWidth, additionalAttributes));


		/*
		sb.append("<br><br><br><table width='80%'>");
		sb.append("<tr>");
		sb.append("<td>");
		//sb.append("Observaciones Generales:<br><br><textarea rows='3' cols='50' onChange='updatePOComment(this)'>" + head.getString("RECEIPT_COMMENT") + "</textarea>");

		sb.append("</td>");
		sb.append("</tr>");
		sb.append("</table>");
		*/



		return sb.toString();
	}








}
