package com.movemini.simpleajaxservlet.usuario;

import java.util.ArrayList;

import javax.servlet.ServletRequest;

import com.movemini.data.OneRecordFactory;
import com.movemini.data.Record;
import com.movemini.simpleflexgrid.HTMLForm;
import com.movemini.simpleflexgrid.HTMLTableColumn;

public class UsuarioForm {


	private ServletRequest request;
	
	public UsuarioForm(ServletRequest request) {

		this.request = request;
	}
	
	
	public String getFormHtml() {
		
		return "NOT_USED";
		
//	
//		StringBuilder sb = new StringBuilder();
//		
//		ArrayList<HTMLTableColumn> columns = new ArrayList<HTMLTableColumn>();
//		
//		String id_usuario = request.getParameter("id_usuario");
//		
//		Record record = OneRecordFactory.getFirstRecord("SELECT * FROM ss_usuario WHERE id_usuario =" + id_usuario);
//		
//		
//		
//		columns.add(new HTMLTableColumn("Nombre:", 				"nombre", 			"left", 	"text", 	"onkeyup='updateEvento()'"));
//		columns.add(new HTMLTableColumn("Apellido:", 			"apellido", 		"left", 	"text", 	"onkeyup='updateEvento()'", "SELECT id_cliente, nombre FROM crm_cliente"));
//		columns.add(new HTMLTableColumn("Correo:", 				"correo", 			"left", 	"text", 	"onkeyup='updateEvento()'", "SELECT id_contacto, nombre_contacto FROM crm_contacto"));
//		columns.add(new HTMLTableColumn("Rol:", 				"id_rol", 			"left", 	"select", 	"onkeyup='updateEvento()'", "SELECT id_rol, nombre FROM ss_rol"));
//		columns.add(new HTMLTableColumn("Nombre de Usuario:", 	"usuario", 			"left", 	"text", 	"onkeyup='updateEvento()'"));
//		columns.add(new HTMLTableColumn("Contraseña:", 			"contrasenia", 		"left", 	"text", 	"onkeyup='updateEvento()'"));
//		
//		//String tableWidth = "100%";
//		String additionalAttributes = "";
//		
//		
//		sb.append(HTMLForm.getFormHtml(record, columns, additionalAttributes));
//		
//
//		return sb.toString();
	}
}
