package com.movemini.simpleajaxservlet.crm;

import java.util.ArrayList;

import javax.servlet.ServletRequest;

import com.movemini.simpleflexgrid.HTMLForm;
import com.movemini.simpleflexgrid.HTMLTableColumn;
import com.movemini.data.OneValueFactory;

public class CRMComentariosForm {


	private ServletRequest request;
	private String idCliente;
	private String idEvento;

	public CRMComentariosForm(ServletRequest request) {

		this.request = request;
	}

	public CRMComentariosForm(String idCliente){
		this.idCliente = idCliente;
	}

	public void setIdEvento(String idEvento){
		this.idEvento = idEvento;
	}

	public String getFormHtml() {

		StringBuilder sb = new StringBuilder();

		ArrayList<HTMLTableColumn> columns = new ArrayList<HTMLTableColumn>();



		columns.add(new HTMLTableColumn("Sem&aacute;foro:", 		"id_semaforo", 			"left", 	"select", 	"", "", "SELECT id_semaforo, nombre FROM cat_semaforo"));
		if(idEvento == null){
			columns.add(new HTMLTableColumn("Evento:", 		"idEventoComentario", 			"left", 	"select", 	"", "", "select id_evento,nombre from evento where id_cliente = " + idCliente));
		}
		columns.add(new HTMLTableColumn("Comentario:", 	"comentario", 			"left", 	"textarea", ""));

		//String tableWidth = "100%";
		String additionalAttributes = "";

		sb.append(HTMLForm.getFormHtml(columns, additionalAttributes));

		return sb.toString();
	}


}
