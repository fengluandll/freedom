package com.movemini.traductores;

import java.util.ArrayList;

import com.movemini.catalogos.ManejadorCatalogos;
import com.movemini.data.OneRecordFactory;
import com.movemini.data.Record;
import com.movemini.simpleflexgrid.HTMLForm;
import com.movemini.simpleflexgrid.HTMLTableColumn;

public class TraductoresForm {
	private ManejadorCatalogos mnjCatalogos = new ManejadorCatalogos();
	private String idTraductor;

	public String getIdTraductor() {
		return idTraductor;
	}
	
	public TraductoresForm(String idTraductor){
		if(idTraductor.equals("0")){
			Record record = mnjCatalogos.obtenerTraductor(idTraductor);
			this.idTraductor = record.get("id_traductor");
		}else{
			this.idTraductor = idTraductor;
		}
	}

	public String getFormHtml(String idTraductor) {
		
		StringBuilder sb = new StringBuilder();

		ArrayList<HTMLTableColumn> columns = new ArrayList<HTMLTableColumn>();
		
		
		Record record = mnjCatalogos.obtenerTraductor(idTraductor);
		
		columns.add(new HTMLTableColumn("Nombre:", 		"nombre", 				"left", 	"text", 	"onkeyup='updateTraductor(this)'"));
		columns.add(new HTMLTableColumn("Apellido:", 		"apellido", 		"left", 	"text", 	"onkeyup='updateTraductor(this)'"));
		columns.add(new HTMLTableColumn("Idioma Materno:", 		"id_idioma_base", 			"left", 	"select", 	"onChange='updateTraductor(this)'", "", "SELECT id_idioma, idioma FROM cat_idioma"));
		columns.add(new HTMLTableColumn("Jerarqu&iacute;a:", 		"id_jerarquia", 			"left", 	"select", 	"onChange='updateTraductor(this)'", "", "SELECT id_jerarquia, jerarquia FROM cat_jerarquia"));
		columns.add(new HTMLTableColumn("Tel&eacute;fono 1:", 		"telefono1", 		"left", 	"text", 	"onkeyup='updateTraductor(this)'"));
		columns.add(new HTMLTableColumn("Tel&eacute;fono 2:", 		"telefono2", 		"left", 	"text", 	"onkeyup='updateTraductor(this)'"));
		columns.add(new HTMLTableColumn("Tel&eacute;fono 3:", 		"telefono3", 		"left", 	"text", 	"onkeyup='updateTraductor(this)'"));
		columns.add(new HTMLTableColumn("Correo 1:", 		"correo1", 		"left", 	"text", 	"onkeyup='updateTraductor(this)'"));
		columns.add(new HTMLTableColumn("Correo 2:", 		"correo2", 		"left", 	"text", 	"onkeyup='updateTraductor(this)'"));
		columns.add(new HTMLTableColumn("Correo 3:", 		"correo3", 		"left", 	"text", 	"onkeyup='updateTraductor(this)'"));
		
		
		
		//String tableWidth = "100%";
		String additionalAttributes = "";
		
		
		sb.append(HTMLForm.getFormHtml(record, columns, additionalAttributes));
		

		return sb.toString();
	}
}
