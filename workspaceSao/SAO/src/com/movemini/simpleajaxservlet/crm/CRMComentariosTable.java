package com.movemini.simpleajaxservlet.crm;

import java.util.ArrayList;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import com.movemini.data.DataArray;
import com.movemini.data.Record;
import com.movemini.simpleflexgrid.HTMLTable;
import com.movemini.simpleflexgrid.HTMLTableColumn;

public class CRMComentariosTable {


	private HttpServletRequest request;
	private String idCliente;
	private String idEvento;

	public CRMComentariosTable(HttpServletRequest request) {

		this.request = request;
	}

	public CRMComentariosTable(String idCliente) {

		this.idCliente  = idCliente ;
	}

	public void setIdCliente(String idCliente){
		this.idCliente = idCliente;
	}

	public void setIdEvento(String idEvento){
		this.idEvento = idEvento;
	}

	public String getHtml() {
 	if(idCliente == null) {
 		 idCliente = (String) request.getSession().getAttribute("cliente_id");
 	}
		String extra = "";
		if(idEvento != null){
			extra = " and evento.id_evento = " + idEvento;

		}

		//ArrayList<Record> records = DataArray.getArrayList("SELECT evento.id_evento, crm_cliente_comentarios.id, crm_cliente_comentarios.id_usuario, crm_cliente_comentarios.fecha, cat_semaforo.nombre as semaforo, crm_cliente_comentarios.comentario,evento.nombre as nevento FROM crm_cliente_comentarios   INNER JOIN cat_semaforo ON cat_semaforo.id_semaforo = crm_cliente_comentarios.id_semaforo LEFT JOIN evento on crm_cliente_comentarios.id_evento = evento.id_evento WHERE crm_cliente_comentarios.id_cliente = " + idCliente + extra +" ORDER BY id desc");
		ArrayList<Record> records = DataArray.getArrayList("SELECT crm_cliente_comentarios.id, evento.id_evento, crm_cliente_comentarios.id_usuario, crm_cliente_comentarios.fecha, cat_semaforo.nombre as semaforo, crm_cliente_comentarios.comentario,evento.nombre as nevento FROM crm_cliente_comentarios   INNER JOIN cat_semaforo ON cat_semaforo.id_semaforo = crm_cliente_comentarios.id_semaforo LEFT JOIN evento on crm_cliente_comentarios.id_evento = evento.id_evento WHERE crm_cliente_comentarios.id_cliente = " + idCliente + extra +" ORDER BY id desc");

		StringBuilder sb = new StringBuilder();

		ArrayList<HTMLTableColumn> columns = new ArrayList<HTMLTableColumn>();



		columns.add(new HTMLTableColumn("Usuario", 						"id_usuario", 		"left", 	"USER_AVATAR", 	""));

		columns.add(new HTMLTableColumn("Fecha", 						"fecha", 			"left", 	"label", 	""));

		columns.add(new HTMLTableColumn("Sem&aacute;foro", 					"semaforo", 		"left", 	"label", 	""));
		columns.add(new HTMLTableColumn("Cotizaci&oacute;n", 					"id_evento", 		"left", 	"label", 	""));
		columns.add(new HTMLTableColumn("Evento", 					"nevento", 		"left", 	"label", 	""));

		columns.add(new HTMLTableColumn("Comentario", 					"comentario", 		"left", 	"label", 	""));

		columns.add(new HTMLTableColumn("Quitar", 					"id", 		"center", 	"link", 	"onclick='deleteComentario(id)'", "<i class=\"fa fa-trash fa-2x\"></i>"));



		String tableWidth = "100%";
		String additionalAttributes = "class=\"table table-bordered\"";


		sb.append(HTMLTable.getHtml(records, columns, tableWidth, additionalAttributes));





		return sb.toString();
	}




}
