package com.movemini.simpleajaxservlet.agenda;

import java.util.ArrayList;

import javax.servlet.ServletRequest;

import com.movemini.data.OneRecordFactory;
import com.movemini.data.Record;
import com.movemini.simpleflexgrid.HTMLForm;
import com.movemini.simpleflexgrid.HTMLTable;
import com.movemini.simpleflexgrid.HTMLTableColumn;
import com.movemini.simpleflexgrid.components.SelectList;

public class AgendaEventoForm {


	private ServletRequest request;

	public AgendaEventoForm(ServletRequest request) {

		this.request = request;
	}


	public String getFormHtml() {

		StringBuilder sb = new StringBuilder();

		ArrayList<HTMLTableColumn> columns = new ArrayList<HTMLTableColumn>();

		String id_evento_version = request.getParameter("ID_EVENTO");

		Record record = OneRecordFactory.getFirstRecord("SELECT e.id_cliente,e.nombre,e.sede, select_cantidad_equipo("+id_evento_version+") as nequipo, cc.nombre as nombreCliente, e.id_tipo_eveto FROM evento e left join crm_cliente cc using(id_cliente) WHERE id_evento = (select id_evento from evento_version where id_evento_version = "+ id_evento_version+")");

		String id_tipo_evento = record.get("id_tipo_eveto");

//		columns.add(new HTMLTableColumn("Cliente:", 		"id_cliente", 			"left", 	"select", 	"onChange='updateEvento(this)'", " disabled='true'", "SELECT id_cliente, nombre FROM crm_cliente  WHERE id_status = 1"));
//		columns.add(new HTMLTableColumn("Moneda para Reporte:", 	"id_moneda", 			"left", 	"select", 	"onChange='updateEvento(this)'", " disabled='true'", "SELECT id_moneda, nombre FROM cat_moneda"));
		//columns.add(new HTMLTableColumn("Folio: ", 				"clave_cotizacion", 		"left", 	"text", "",	"disabled")); //



		columns.add(new HTMLTableColumn("Cliente:", 		"nombreCliente", 			"left", 	"text", 	" readonly "));

		if(id_tipo_evento.equals("1")) {
			columns.add(new HTMLTableColumn("Evento:", 		"nombre", 				"left", 	"text", 	" readonly "));
		} else {
			columns.add(new HTMLTableColumn("Descripci&oacute;n de la Cotizaci&oacute;n:", 		"nombre", 				"left", 	"textBig", 	 " readonly " ));

		}

		if(id_tipo_evento.equals("1")) {
			columns.add(new HTMLTableColumn("Sede:", 		"sede", 				"left", 	"text", 	" readonly "));
			columns.add(new HTMLTableColumn("Cantidad de Equipo:", 		"nequipo", 				"left", 	"textarea", 	" readonly style=\"width:100%;height:150px;\""));
		}


		String additionalAttributes = "";


		sb.append(HTMLForm.getFormHtml(record, columns, additionalAttributes));


		return sb.toString();
	}
}
