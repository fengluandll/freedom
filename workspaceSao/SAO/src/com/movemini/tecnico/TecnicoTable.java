package com.movemini.tecnico;

import java.util.ArrayList;

import com.movemini.data.OneRecordFactory;
import com.movemini.data.Record;
import com.movemini.simpleflexgrid.HTMLForm;
import com.movemini.simpleflexgrid.HTMLTableColumn;

public class TecnicoTable {

	private String idTecnico;
	
	public String getIdTecnico() {
		return idTecnico;
	}

	public TecnicoTable(String idTecnico){
	
	}

	
	public String getFormHtml(Record record) {
		
		StringBuilder sb = new StringBuilder();

		ArrayList<HTMLTableColumn> columns = new ArrayList<HTMLTableColumn>();
		
		
		
		
		columns.add(new HTMLTableColumn("Nombre:", 		"nombre", 				"left", 	"text", 	"onkeyup='updateTecnico(this)'"));
		columns.add(new HTMLTableColumn("Apellido:", 		"apellido", 		"left", 	"text", 	"onkeyup='updateTecnico(this)'"));
		columns.add(new HTMLTableColumn("Fecha de nacimiento:", 		"fecha_nacimiento", 		"left", 	"date", 	"onkeyup='updateTecnico(this)' onChange='updateTecnico(this)'"));
		columns.add(new HTMLTableColumn("Domicilio:", 		"domicilio", 		"left", 	"text", 	"onkeyup='updateTecnico(this)'"));
		
		//columns.add(new HTMLTableColumn("Idioma Materno:", 		"id_idioma_base", 			"left", 	"select", 	"onChange='updateInterprete(this)'", "", "SELECT id_idioma, idioma FROM cat_idioma"));
		
		//columns.add(new HTMLTableColumn("Jerarqu&iacute;a:", 		"id_jerarquia", 			"left", 	"select", 	"onChange='updateInterprete(this)'", "", "SELECT id_jerarquia, jerarquia FROM cat_jerarquia"));
		
		
		
		
		columns.add(new HTMLTableColumn("Tel&eacute;fono 1:", 		"telefono1", 		"left", 	"text", 	"onkeyup='updateTecnico(this)'"));
		columns.add(new HTMLTableColumn("Tel&eacute;fono 2:", 		"telefono2", 		"left", 	"text", 	"onkeyup='updateTecnico(this)'"));
		columns.add(new HTMLTableColumn("Tel&eacute;fono 3:", 		"telefono3", 		"left", 	"text", 	"onkeyup='updateTecnico(this)'"));
		columns.add(new HTMLTableColumn("Celular 1:", 		"celular_1", 		"left", 	"text", 	"onkeyup='updateTecnico(this)'"));
		columns.add(new HTMLTableColumn("Correo 1:", 		"correo1", 		"left", 	"text", 	"onkeyup='updateTecnico(this)'"));
		columns.add(new HTMLTableColumn("Correo 2:", 		"correo2", 		"left", 	"text", 	"onkeyup='updateTecnico(this)'"));
		columns.add(new HTMLTableColumn("Correo 3:", 		"correo3", 		"left", 	"text", 	"onkeyup='updateTecnico(this)'"));
		columns.add(new HTMLTableColumn("Documentos para viajar:", 		"documentos_viajar", 		"left", 	"text", 	"onkeyup='updateTecnico(this)'"));
		
		columns.add(new HTMLTableColumn("Observaciones:", 		"observaciones", 		"left", 	"textarea", 	"onkeyup='updateTecnico(this)'"));
		
		
		
		//String tableWidth = "100%";
		String additionalAttributes = "";
		
		
		sb.append(HTMLForm.getFormHtml(record, columns, additionalAttributes));
		

		return sb.toString();
	}
	
	public String getFormHtmlCuentaNomina(Record record) {
		
		StringBuilder sb = new StringBuilder();

		ArrayList<HTMLTableColumn> columns = new ArrayList<HTMLTableColumn>();
		
		
//		record = OneRecordFactory.getFirstRecord("SELECT * FROM cat_tecnicos WHERE id_tenico =" + idTecnico);
		
		columns.add(new HTMLTableColumn("Banco:", 		"cuenta_nomina_banco", 				"left", 	"text", 	"onkeyup='updateTecnico(this)'"));
		columns.add(new HTMLTableColumn("Cuenta:", 		"cuenta_nomina_cuenta", 			"left", 	"text", 	"onkeyup='updateTecnico(this)'"));
		columns.add(new HTMLTableColumn("CLABE:", 		"cuenta_nomina_clabe", 				"left", 	"text", 	"onkeyup='updateTecnico(this)'"));
		columns.add(new HTMLTableColumn("IBAN:", 		"cuenta_nomina_iban", 				"left", 	"text", 	"onkeyup='updateTecnico(this)'"));
		columns.add(new HTMLTableColumn("ABA:", 		"cuenta_nomina_aba", 				"left", 	"text", 	"onkeyup='updateTecnico(this)'"));
		columns.add(new HTMLTableColumn("SWIFT:", 		"cuenta_nomina_swift", 				"left", 	"text", 	"onkeyup='updateTecnico(this)'"));
		
		//String tableWidth = "100%";
		String additionalAttributes = "";
		
		sb.append(HTMLForm.getFormHtml(record, columns, additionalAttributes));
		

		return sb.toString();
	}
	
	public String getFormHtmlCuentaViaticos(Record record) {
		
		StringBuilder sb = new StringBuilder();

		ArrayList<HTMLTableColumn> columns = new ArrayList<HTMLTableColumn>();
		
		
//		record = OneRecordFactory.getFirstRecord("SELECT * FROM cat_tecnicos WHERE id_tenico =" + idTecnico);
		
		columns.add(new HTMLTableColumn("Banco:", 		"cuenta_viaticos_banco", 				"left", 	"text", 	"onkeyup='updateTecnico(this)'"));
		columns.add(new HTMLTableColumn("Cuenta:", 		"cuenta_viaticos_cuenta", 			"left", 	"text", 	"onkeyup='updateTecnico(this)'"));
		columns.add(new HTMLTableColumn("CLABE:", 		"cuenta_viaticos_clabe", 				"left", 	"text", 	"onkeyup='updateTecnico(this)'"));
		columns.add(new HTMLTableColumn("IBAN:", 		"cuenta_viaticos_iban", 				"left", 	"text", 	"onkeyup='updateTecnico(this)'"));
		columns.add(new HTMLTableColumn("ABA:", 		"cuenta_viaticos_aba", 				"left", 	"text", 	"onkeyup='updateTecnico(this)'"));
		columns.add(new HTMLTableColumn("SWIFT:", 		"cuenta_viaticos_swift", 				"left", 	"text", 	"onkeyup='updateTecnico(this)'"));
		
		//String tableWidth = "100%";
		String additionalAttributes = "";
		
		sb.append(HTMLForm.getFormHtml(record, columns, additionalAttributes));
		

		return sb.toString();
	}
}
