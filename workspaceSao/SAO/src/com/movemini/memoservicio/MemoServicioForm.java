package com.movemini.memoservicio;

import java.util.ArrayList;

import com.movemini.data.OneRecordFactory;
import com.movemini.data.OneValueFactory;
import com.movemini.data.Record;
import com.movemini.simpleflexgrid.HTMLForm;
import com.movemini.simpleflexgrid.HTMLTableColumn;

public class MemoServicioForm {

	
	StringBuilder sb = new StringBuilder();
	ArrayList<HTMLTableColumn> columns = new ArrayList<HTMLTableColumn>();
	String idEventoVersion = "";
	Record record = null;
	
	
	public String getFormHtml(String id_evento_version) {

		idEventoVersion = id_evento_version;		
		
		String localForaneo = OneValueFactory.get("select ifnull(id_tipo_x,0) from evento inner join evento_version  using (id_evento) where id_evento_version =" + id_evento_version);
				
		if( "0".equals(localForaneo) ) {
			//falta definir el tipo de memo
			generarLocal();
			
		} else if("1".equals(localForaneo)) {
			// es local
			
			generarLocal();	
			
		} else if("2".equals(localForaneo)) {
			//es foraneo
			generarForaneo();
			
		} else {
			// no se encontraron registros ERROR
			sb.append("Error");			
		}	
		 
		return sb.toString();
	}
	
	
	public void generarLocal() {
		record = OneRecordFactory.getPr("evento_memo_servicio_pr", idEventoVersion);
		columns.add(new HTMLTableColumn("Nombre Evento:", 		"nombre_evento", 			"left", 	"text", 	"onChange='actualizarCampoMemoServicio(this, KEY)'"));
		columns.add(new HTMLTableColumn("Cliente:", 	"cliente", 			"left", 	"text", 	"onChange='actualizarCampoMemoServicio(this, KEY)'"));
		columns.add(new HTMLTableColumn("Contacto:", 		"contacto", 				"left", 	"textBig", 	"onChange='actualizarCampoMemoServicio(this, KEY)'"));
		columns.add(new HTMLTableColumn("Lugar:", 		"lugar", 				"left", 	"textBig", 	"onChange='actualizarCampoMemoServicio(this, KEY)'"));
		columns.add(new HTMLTableColumn("Sede:", 		"sede", 				"left", 	"text", 	"onChange='actualizarCampoMemoServicio(this, KEY)'"));
		columns.add(new HTMLTableColumn("Salon:", 		"salon", 				"left", 	"text", 	"onChange='actualizarCampoMemoServicio(this, KEY)'"));
		columns.add(new HTMLTableColumn("Fechas:", 		"fechas", 				"left", 	"text", 	"onChange='actualizarCampoMemoServicio(this, KEY)'"));
		columns.add(new HTMLTableColumn("Horario General:", 		"horario_general", 				"left", 	"text", 	"onChange='actualizarCampoMemoServicio(this, KEY)'"));
		columns.add(new HTMLTableColumn("Equipo de Interpretacion:", 		"equipo_interpretacion", 				"left", 	"textBig", 	"onChange='actualizarCampoMemoServicio(this, KEY)'"));
		columns.add(new HTMLTableColumn("Horario de Instalacion:", 		"horario_instalacion", 				"left", 	"text", 	"onChange='actualizarCampoMemoServicio(this, KEY)'"));
		columns.add(new HTMLTableColumn("Nombre Tecnicos:", 		"nombre_tecnicos", 				"left", 	"textBig", 	"onChange='actualizarCampoMemoServicio(this, KEY)'"));
		columns.add(new HTMLTableColumn("Nombre Int&eacute;rpretes:", 		"nombre_interpretes", 				"left", 	"textBig", 	"onChange='actualizarCampoMemoServicio(this, KEY)'"));
		columns.add(new HTMLTableColumn("Idiomas:", 		"idiomas", 				"left", 	"textBig", 	"onChange='actualizarCampoMemoServicio(this, KEY)'"));

		columns.add(new HTMLTableColumn("Tipo Interpretacion:", 		"tipo_interpretacion", 				"left", 	"text", 	"onChange='actualizarCampoMemoServicio(this, KEY)'"));

		columns.add(new HTMLTableColumn("Observaciones:", 		"observaciones", 				"left", 	"textarea", 	"onChange='actualizarCampoMemoServicio(this, KEY)'"));

		String additionalAttributes = "";

		sb.append(HTMLForm.getFormHtml(record, columns, additionalAttributes));

		
	}
	
	public void generarForaneo() {
		System.out.println(idEventoVersion);
		record = OneRecordFactory.getPr("evento_memo_servicio_foraneo_pr", idEventoVersion);

		columns.add(new HTMLTableColumn("Nombre Evento:", 		"nombre_evento", 			"left", 	"text", 	"onChange='actualizarCampoMemoServicio(this, KEY)'"));
		columns.add(new HTMLTableColumn("Cliente:", 	"cliente", 			"left", 	"text", 	"onChange='actualizarCampoMemoServicio(this, KEY)'"));
		columns.add(new HTMLTableColumn("Contacto:", 		"contacto", 				"left", 	"textBig", 	"onChange='actualizarCampoMemoServicio(this, KEY)'"));
		columns.add(new HTMLTableColumn("Lugar:", 		"lugar", 				"left", 	"textBig", 	"onChange='actualizarCampoMemoServicio(this, KEY)'"));
		columns.add(new HTMLTableColumn("Sede:", 		"sede", 				"left", 	"text", 	"onChange='actualizarCampoMemoServicio(this, KEY)'"));
		columns.add(new HTMLTableColumn("Salon:", 		"salon", 				"left", 	"text", 	"onChange='actualizarCampoMemoServicio(this, KEY)'"));
		columns.add(new HTMLTableColumn("Fechas:", 		"fechas", 				"left", 	"text", 	"onChange='actualizarCampoMemoServicio(this, KEY)'"));
		columns.add(new HTMLTableColumn("Horario General:", 		"horario_general", 				"left", 	"text", 	"onChange='actualizarCampoMemoServicio(this, KEY)'"));
		columns.add(new HTMLTableColumn("Equipo de Interpretacion:", 		"equipo_interpretacion", 				"left", 	"textBig", 	"onChange='actualizarCampoMemoServicio(this, KEY)'"));
		//columns.add(new HTMLTableColumn("Comida", 		"comida", 				"left", 	"checkbox", 	"onChange='actualizarCampoMemoServicio(this, KEY)'"));
		columns.add(new HTMLTableColumn("Horario de Instalacion:", 		"horario_instalacion", 				"left", 	"text", 	"onChange='actualizarCampoMemoServicio(this, KEY)'"));
		columns.add(new HTMLTableColumn("Alimentos:", 		"comida", 				"left", 	"text", 	"onChange='actualizarCampoMemoServicio(this, KEY)'"));
		//columns.add(new HTMLTableColumn("Nombre T&eacute;cnicos:", 		"nombre_tecnicos", 				"left", 	"textBig", 	"onChange='actualizarCampoMemoServicio(this, KEY)'"));
		//columns.add(new HTMLTableColumn("Nombre Int&eacute;rpretes:", 		"nombre_interpretes", 				"left", 	"textBig", 	"onChange='actualizarCampoMemoServicio(this, KEY)'"));
		
		columns.add(new HTMLTableColumn("Idiomas:", 		"idiomas", 				"left", 	"textBig", 	"onChange='actualizarCampoMemoServicio(this, KEY)'"));

		columns.add(new HTMLTableColumn("Tipo Interpretacion:", 		"tipo_interpretacion", 				"left", 	"text", 	"onChange='actualizarCampoMemoServicio(this, KEY)'"));

		columns.add(new HTMLTableColumn("Observaciones:", 		"observaciones", 				"left", 	"textarea", 	"onChange='actualizarCampoMemoServicio(this, KEY)'"));

		String additionalAttributes = "";

		sb.append(HTMLForm.getFormHtml(record, columns, additionalAttributes));

		
	}
}
