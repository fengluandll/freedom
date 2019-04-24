package com.movemini.simpleajaxservlet.interprete;

import java.util.ArrayList;

import javax.servlet.ServletRequest;

import com.movemini.data.ConnectionWrapper;
import com.movemini.data.OneRecordFactory;
import com.movemini.data.OneValueFactory;
import com.movemini.data.Record;
import com.movemini.simpleflexgrid.HTMLForm;
import com.movemini.simpleflexgrid.HTMLTableColumn;

public class InterpreteForm {



	private ServletRequest request;
	
	private String id_interprete;
	
	
	public InterpreteForm(ServletRequest request) {

		this.request = request;

		init();
	}
	
	
	private void init() {

		this.id_interprete = request.getParameter("id_interprete");
		
		
		if(id_interprete.equals("0")) {
			
			ConnectionWrapper.staticExecuteUpdate("INSERT INTO cat_interprete (id_interprete) VALUES (0)");

			this.id_interprete = OneValueFactory.get("select max(id_interprete) FROM cat_interprete");			
		}

	}
	
	
	public String getId_interprete() {
		return id_interprete;
	}
	
	public String getFormHtml() {
	
		StringBuilder sb = new StringBuilder();

		ArrayList<HTMLTableColumn> columns = new ArrayList<HTMLTableColumn>();
		
		
		Record record = OneRecordFactory.getFirstRecord("SELECT * FROM cat_interprete WHERE id_interprete =" + id_interprete);
		
		
		columns.add(new HTMLTableColumn("Nombre:", 		"nombre", 				"left", 	"text", 	"onkeyup='updateInterprete(this)'"));
		columns.add(new HTMLTableColumn("Apellido:", 		"apellido", 		"left", 	"text", 	"onkeyup='updateInterprete(this)'"));
		
		
		columns.add(new HTMLTableColumn("Idioma Materno:", 		"id_idioma_base", 			"left", 	"select", 	"onChange='updateInterprete(this)'", "", "SELECT id_idioma, idioma FROM cat_idioma"));
		
		columns.add(new HTMLTableColumn("Fecha Nacimiento:", 		"fecha_nacimiento", 		"left", 	"date", 	"onkeyup='updateInterprete(this)'"));
		
				
		
		columns.add(new HTMLTableColumn("Jerarqu&iacute;a:", 		"id_jerarquia", 			"left", 	"select", 	"onChange='updateInterprete(this)'", "", "SELECT id_jerarquia, jerarquia FROM cat_jerarquia"));
		
		
		columns.add(new HTMLTableColumn("Tipo:", 		"id_tipo_empleado", 			"left", 	"select", 	"onChange='updateInterprete(this)'", "", "SELECT id_tipo_empleado, nombre FROM cat_tipo_empleado"));
		
		
		columns.add(new HTMLTableColumn("Tel&eacute;fono 1:", 		"telefono1", 		"left", 	"text", 	"onkeyup='updateInterprete(this)'"));
		columns.add(new HTMLTableColumn("Tel&eacute;fono 2:", 		"telefono2", 		"left", 	"text", 	"onkeyup='updateInterprete(this)'"));
		columns.add(new HTMLTableColumn("Tel&eacute;fono 3:", 		"telefono3", 		"left", 	"text", 	"onkeyup='updateInterprete(this)'"));
		
		columns.add(new HTMLTableColumn("Correo 1:", 		"correo1", 		"left", 	"text", 	"onkeyup='updateInterprete(this)'"));
		columns.add(new HTMLTableColumn("Correo 2:", 		"correo2", 		"left", 	"text", 	"onkeyup='updateInterprete(this)'"));
		columns.add(new HTMLTableColumn("Correo 3:", 		"correo3", 		"left", 	"text", 	"onkeyup='updateInterprete(this)'"));
		
		columns.add(new HTMLTableColumn("Celular 1:", 		"celular1", 		"left", 	"text", 	"onkeyup='updateInterprete(this)'"));
		columns.add(new HTMLTableColumn("Celular 2:", 		"celular2", 		"left", 	"text", 	"onkeyup='updateInterprete(this)'"));
		
		columns.add(new HTMLTableColumn("Domicilio:", 		"domicilio", 	"left", 	"text", 	"onkeyup='updateInterprete(this)'"));
		columns.add(new HTMLTableColumn("Documentos para viajar:", 		"doc_viajar", 		"left", 	"text", 	"onkeyup='updateInterprete(this)'"));
		columns.add(new HTMLTableColumn("Aeropuerto de Origen:", 		"aeropuerto_origen", 		"left", 	"text", 	"onkeyup='updateInterprete(this)'"));
		columns.add(new HTMLTableColumn("Local en:", 		"es_local_en", 	"left", 	"text", 	"onkeyup='updateInterprete(this)'"));
		
		columns.add(new HTMLTableColumn("Servicios:", 		"servicios", 		"left", 	"text", 	"onkeyup='updateInterprete(this)'"));
		columns.add(new HTMLTableColumn("Tarifa Simult&aacute;nea:", 		"tarifa_simultanea", 		"left", 	"text", 	"onkeyup='updateInterprete(this)'"));
		columns.add(new HTMLTableColumn("Tarifa Escrita:", 		"tarifa_escrita", 		"left", 	"text", 	"onkeyup='updateInterprete(this)'"));
		columns.add(new HTMLTableColumn("Tarifa Perito:", 		"tarifa_perito", 		"left", 	"text", 	"onkeyup='updateInterprete(this)'"));
		
		columns.add(new HTMLTableColumn("Observaciones:", 		"observaciones", 		"left", 	"textarea", 	"onkeyup='updateInterprete(this)'"));
			
		//String tableWidth = "100%";
		String additionalAttributes = "";
		
		
		sb.append(HTMLForm.getFormHtml(record, columns, additionalAttributes));
		

		return sb.toString();
	}
	
	
	
	
	public String getFormHtmlCuentaFiscal() {
		
		StringBuilder sb = new StringBuilder();

		ArrayList<HTMLTableColumn> columns = new ArrayList<HTMLTableColumn>();
		
		
		Record record = OneRecordFactory.getFirstRecord("SELECT * FROM cat_interprete WHERE id_interprete =" + id_interprete);
		
		columns.add(new HTMLTableColumn("Banco:", 		"cuenta_fiscal_banco", 				"left", 	"text", 	"onkeyup='updateInterprete(this)'"));
		columns.add(new HTMLTableColumn("Cuenta:", 		"cuenta_fiscal_cuenta", 			"left", 	"text", 	"onkeyup='updateInterprete(this)'"));
		columns.add(new HTMLTableColumn("CLABE:", 		"cuenta_fiscal_clabe", 				"left", 	"text", 	"onkeyup='updateInterprete(this)'"));
		columns.add(new HTMLTableColumn("IBAN:", 		"cuenta_fiscal_iban", 				"left", 	"text", 	"onkeyup='updateInterprete(this)'"));
		columns.add(new HTMLTableColumn("ABA:", 		"cuenta_fiscal_aba", 				"left", 	"text", 	"onkeyup='updateInterprete(this)'"));
		columns.add(new HTMLTableColumn("SWIFT:", 		"cuenta_fiscal_swift", 				"left", 	"text", 	"onkeyup='updateInterprete(this)'"));
		
		//String tableWidth = "100%";
		String additionalAttributes = "";
		
		sb.append(HTMLForm.getFormHtml(record, columns, additionalAttributes));
		

		return sb.toString();
	}
	
	

	public String getFormHtmlCuentaNoFiscal() {
		
		StringBuilder sb = new StringBuilder();

		ArrayList<HTMLTableColumn> columns = new ArrayList<HTMLTableColumn>();
		
		
		Record record = OneRecordFactory.getFirstRecord("SELECT * FROM cat_interprete WHERE id_interprete =" + id_interprete);
		
		columns.add(new HTMLTableColumn("Banco:", 		"cuenta_no_fiscal_banco", 				"left", 	"text", 	"onkeyup='updateInterprete(this)'"));
		columns.add(new HTMLTableColumn("Cuenta:", 		"cuenta_no_fiscal_cuenta", 			"left", 	"text", 	"onkeyup='updateInterprete(this)'"));
		columns.add(new HTMLTableColumn("CLABE:", 		"cuenta_no_fiscal_clabe", 				"left", 	"text", 	"onkeyup='updateInterprete(this)'"));
		columns.add(new HTMLTableColumn("IBAN:", 		"cuenta_no_fiscal_iban", 				"left", 	"text", 	"onkeyup='updateInterprete(this)'"));
		columns.add(new HTMLTableColumn("ABA:", 		"cuenta_no_fiscal_aba", 				"left", 	"text", 	"onkeyup='updateInterprete(this)'"));
		columns.add(new HTMLTableColumn("SWIFT:", 		"cuenta_no_fiscal_swift", 				"left", 	"text", 	"onkeyup='updateInterprete(this)'"));
		
		//String tableWidth = "100%";
		String additionalAttributes = "";
		
		sb.append(HTMLForm.getFormHtml(record, columns, additionalAttributes));
		

		return sb.toString();
	}
	
	
	public String getFormHtmlCuentaPersonal() {
		
		StringBuilder sb = new StringBuilder();

		ArrayList<HTMLTableColumn> columns = new ArrayList<HTMLTableColumn>();
		
		
		Record record = OneRecordFactory.getFirstRecord("SELECT * FROM cat_interprete WHERE id_interprete =" + id_interprete);
		
		columns.add(new HTMLTableColumn("Banco:", 		"cuenta_personal_banco", 				"left", 	"text", 	"onkeyup='updateInterprete(this)'"));
		columns.add(new HTMLTableColumn("Cuenta:", 		"cuenta_personal_cuenta", 			"left", 	"text", 	"onkeyup='updateInterprete(this)'"));
		columns.add(new HTMLTableColumn("CLABE:", 		"cuenta_personal_clabe", 				"left", 	"text", 	"onkeyup='updateInterprete(this)'"));
		columns.add(new HTMLTableColumn("IBAN:", 		"cuenta_personal_iban", 				"left", 	"text", 	"onkeyup='updateInterprete(this)'"));
		columns.add(new HTMLTableColumn("ABA:", 		"cuenta_personal_aba", 				"left", 	"text", 	"onkeyup='updateInterprete(this)'"));
		columns.add(new HTMLTableColumn("SWIFT:", 		"cuenta_personal_swift", 				"left", 	"text", 	"onkeyup='updateInterprete(this)'"));
		
		//String tableWidth = "100%";
		String additionalAttributes = "";
		
		sb.append(HTMLForm.getFormHtml(record, columns, additionalAttributes));
		

		return sb.toString();
	}
	
	

	
	
	
	
	
}
