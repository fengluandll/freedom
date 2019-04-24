package com.movemini.simpleajaxservlet.evento;

import java.util.ArrayList;

import javax.servlet.ServletRequest;

import com.movemini.data.OneRecordFactory;
import com.movemini.data.Record;
import com.movemini.simpleflexgrid.HTMLForm;
import com.movemini.simpleflexgrid.HTMLTable;
import com.movemini.simpleflexgrid.HTMLTableColumn;
import com.movemini.simpleflexgrid.components.SelectList;

public class EventoForm {


	private ServletRequest request;

	public EventoForm(ServletRequest request) {

		this.request = request;
	}


	public String getComboClientesHtml()
	{
		StringBuilder sb = new StringBuilder();

		HTMLTableColumn column = new HTMLTableColumn("Cliente:", 		"id_cliente", 			"left", 	"select", 	"", "", "select id_cliente, nombre from crm_cliente where id_status = 1 and nombre is not null");

		String selectHTML = SelectList.getSelectList(column, "", column.getAttributes());

		sb.append(selectHTML);

		return sb.toString();
	}

	public String getComboEventoEstatusHtml()
	{
		StringBuilder sb = new StringBuilder();

		HTMLTableColumn column = new HTMLTableColumn("estatus:", 		"id_evento_status", 			"left", 	"select", 	"", "", "select id_evento_status, nombre from cat_evento_status");

		String selectHTML = SelectList.getSelectList(column, "", column.getAttributes());

		sb.append(selectHTML);


		return sb.toString();
	}

	public String getComboEventoNombreHtml(){
		StringBuilder sb = new StringBuilder();

		String qry = "SELECT"
		+ " E.id_evento,"
		+ " E.nombre nombre_evento"
        + " FROM "
        + " evento E"
		+ " LEFT JOIN crm_cliente C ON C.id_cliente = E.id_cliente"
		+ " LEFT JOIN crm_contacto O ON O.id_contacto = E.id_contacto"
        + " LEFT JOIN ss_usuario SS ON SS.id_usuario = E.id_usuario";

		HTMLTableColumn column = new HTMLTableColumn("estatus:", 		"id_evento", 			"left", 	"select", 	"", "", qry);

		String selectHTML = SelectList.getSelectList(column, "", column.getAttributes());

		sb.append(selectHTML);


		return sb.toString();
	}

//	public String getComboEventoNombreHtml(){
//		StringBuilder sb = new StringBuilder();
//
//		String qry = "SELECT"
//		+ " E.id_evento,"
//		+ " E.nombre nombre_evento"
//        + " FROM "
//        + " evento E"
//		+ " LEFT JOIN crm_cliente C ON C.id_cliente = E.id_cliente"
//		+ " LEFT JOIN crm_contacto O ON O.id_contacto = E.id_contacto"
//        + " LEFT JOIN ss_usuario SS ON SS.id_usuario = E.id_usuario";
//
//		HTMLTableColumn column = new HTMLTableColumn("estatus:", 		"id_evento", 			"left", 	"select", 	"", "", qry);
//
//		String selectHTML = SelectList.getSelectList(column, "", column.getAttributes());
//
//		sb.append(selectHTML);
//
//
//		return sb.toString();
//	}

	public String getFormHtml() {

		StringBuilder sb = new StringBuilder();

		ArrayList<HTMLTableColumn> columns = new ArrayList<HTMLTableColumn>();

		String id_evento_version = request.getParameter("ID_EVENTO");

		Record record = OneRecordFactory.getFirstRecord("SELECT * FROM evento WHERE id_evento = (select id_evento from evento_version where id_evento_version = "+ id_evento_version+")");

		String id_tipo_evento = record.get("id_tipo_eveto");



//		columns.add(new HTMLTableColumn("Cliente:", 		"id_cliente", 			"left", 	"select", 	"onChange='updateEvento(this)'", " disabled='true'", "SELECT id_cliente, nombre FROM crm_cliente  WHERE id_status = 1"));
//		columns.add(new HTMLTableColumn("Moneda para Reporte:", 	"id_moneda", 			"left", 	"select", 	"onChange='updateEvento(this)'", " disabled='true'", "SELECT id_moneda, nombre FROM cat_moneda"));
		//columns.add(new HTMLTableColumn("Folio: ", 				"clave_cotizacion", 		"left", 	"text", "",	"disabled")); //



		columns.add(new HTMLTableColumn("Cliente:", 		"id_cliente", 			"left", 	"select", 	"onChange='updateEvento(this)'", " disabled='true'", "SELECT id_cliente, nombre FROM crm_cliente  WHERE id_status = 1 and nombre is not null"));
		columns.add(new HTMLTableColumn("Contacto:", 	"id_contacto", 			"left", 	"select", 	"onChange='updateEvento(this)'", "", "SELECT id_contacto, nombre_contacto FROM crm_contacto WHERE id_status = 1 and (estado <> 2 or estado is null) AND nombre_contacto is not null and  id_cliente = " + record.get("id_cliente") + " ORDER BY nombre_contacto"));

		
		columns.add(new HTMLTableColumn("Regi&oacute;n:", 		"id_region", 			"left", 	"select", 	"onChange='updateEvento(this)'", " ", "SELECT * FROM cat_region"));
		
		

		if(id_tipo_evento.equals("1")) {
			columns.add(new HTMLTableColumn("Evento:", 		"nombre", 				"left", 	"textBig", 	"onkeyup='updateEvento(this)'"));
		} else {

			columns.add(new HTMLTableColumn("Descripci&oacute;n de la Cotizaci&oacute;n:", 		"nombre", 				"left", 	"textBig", 	"onkeyup='updateEvento(this)'"));
		}

		if(id_tipo_evento.equals("1")) {
			columns.add(new HTMLTableColumn("Lugar:", 		"lugar", 				"left", 	"text", 	"onkeyup='updateEvento(this)'"));
			columns.add(new HTMLTableColumn("Sede:", 		"sede", 				"left", 	"text", 	"onkeyup='updateEvento(this)'"));
		}

		if(id_tipo_evento.equals("1")) {
			columns.add(new HTMLTableColumn("Fechas:", 		"fechas", 				"left", 	"text", 	"onkeyup='updateEvento(this)'"));
		} else {

			columns.add(new HTMLTableColumn("Fecha de Entrega:", 		"fechas", 				"left", 	"text", 	"onkeyup='updateEvento(this)'"));
		}


		if(id_tipo_evento.equals("1")) {
			columns.add(new HTMLTableColumn("Horario:", 		"horario", 				"left", 	"textarea", 	"onkeyup='updateEvento(this)' style='width:500px'"));

			//columns.add(new HTMLTableColumn("Moneda para Reporte:", 	"id_moneda", 			"left", 	"select", 	"onChange='updateEvento(this)'", "", "SELECT id_moneda, nombre FROM cat_moneda"));

			columns.add(new HTMLTableColumn("Tipo de Cotizaci&oacute;n:", 	"id_tipo_cotizacion", 			"left", 	"select", 	"onChange='updateEvento(this)'", "", "SELECT id_tipo_cotizacion, nombre FROM cat_tipo_cotizacion"));

			columns.add(new HTMLTableColumn("Tipo de Interpretaci&oacute;n:", 	"id_tipo_interpretacion", 			"left", 	"select", 	"onChange='updateEvento(this)'", "", "SELECT id_tipo_interpretacion, tipo FROM cat_tipo_interpretacion"));


			columns.add(new HTMLTableColumn("Mostrar Precios:", 	"id_mostrar_precios", 			"left", 	"select", 	"onChange='updateEvento(this)'", "", "SELECT id_mostrar_precios, nombre FROM cat_mostrar_precios"));
			
			columns.add(new HTMLTableColumn("Local o For&aacute;neo:", 	"id_tipo_x", 			"left", 	"select", 	"onChange='updateEvento(this)'", "", "select   1 as id ,'Local' as descripcion union select 2 as id ,'For&aacute;neo' as descripcion"));
			
			


		}



		if(id_tipo_evento.equals("2")) {
			columns.add(new HTMLTableColumn("Mostrar Precios:", 	"id_mostrar_precios", 			"left", 	"select", 	"onChange='updateEvento(this)'", "", "SELECT id_mostrar_precios, nombre FROM cat_mostrar_precios"));
			columns.add(new HTMLTableColumn("Tipo de Cotizaci&oacute;n:", 	"id_tipo_cotizacion", 			"left", 	"select", 	"onChange='updateEvento(this)'", "", "SELECT id_tipo_cotizacion, nombre FROM cat_tipo_cotizacion where id_tipo_cotizacion <> 5"));	
			//columns.add(new HTMLTableColumn("Tiempo de Entrega:", 	"id_tiempo_entrega", 			"left", 	"select", 	"onChange='updateEvento(this)'", "", "SELECT id_tiempo_entrega, nombre FROM cat_tiempo_entrega"));

		}





		columns.add(new HTMLTableColumn("Idioma Cotizaci&oacute;n:", 	"id_idioma_cotizacion", 			"left", 	"select2", 	"onChange='updateEvento(this)'", "", "SELECT id_idioma, idioma FROM cat_idioma_cotizacion"));

		
		
		/*
		 * 
		 **/
		columns.add(new HTMLTableColumn("Folio Anterior:", 	"folio_anterior", 			"left", 	"text", 	"onChange='updateEvento(this)'"));

		//columns.add(new HTMLTableColumn("Fecha:", 		"clave_cotizacion", 	"left", 	"text", 	"onkeyup='updateEvento()'"));
		//columns.add(new HTMLTableColumn("Lugar:", 		"lugar", 				"left", 	"text", 	"onkeyup='updateEvento()'"));
		//columns.add(new HTMLTableColumn("Sede:", 		"sede", 				"left", 	"text", 	"onkeyup='updateEvento()'"));

		//String tableWidth = "100%";
		String additionalAttributes = "";


		sb.append(HTMLForm.getFormHtml(record, columns, additionalAttributes));


		return sb.toString();
	}
}
