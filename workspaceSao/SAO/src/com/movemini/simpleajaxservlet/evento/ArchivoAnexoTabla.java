package com.movemini.simpleajaxservlet.evento;

import java.util.ArrayList;

import javax.servlet.ServletRequest;

import com.movemini.config.HardCodeConstants;
import com.movemini.data.DataArray;
import com.movemini.data.Record;

public class ArchivoAnexoTabla {

	private ServletRequest request;

	public ArchivoAnexoTabla(ServletRequest request) {

		this.request = request;
	}


	public String getHtml() {

		String id_evento_version = request.getParameter("ID_EVENTO");



		ArrayList<Record> records = DataArray.getArrayList("select * from evento_anexo where id_evento_version =" +id_evento_version );

		StringBuilder sb = new StringBuilder();




		sb.append("<div class=\"col-md-12 col-sm-12 col-xs-12\">");


		for (Record record : records) {

			sb.append("<div class=\"col-md-3 col-sm-3 col-xs-12\" align='center' border='1'>");

			String nombre = record.getString("nombre_archivo");

			String img = "";

			if(nombre.contains(".pdf")) {

				img = "PDF.png";

			} else if(nombre.contains(".doc")) {

				img = "DOC.png";

			} else if(nombre.contains(".xls")) {

				img = "XLS.png";

			} else if(nombre.contains(".ppt")) {

				img = "PPT.png";

			} else if(nombre.contains(".jpg") || nombre.contains(".jpeg")) {

				img = "JPG.png";

			} else if(nombre.contains(".png")) {

				img = "PNG.png";

			} else if(nombre.contains(".zip")) {

				img = "ZIP.png";
			} else {

				img = "BLANK.png";
			}

			sb.append("<a href='/" + HardCodeConstants.CONTEXT_PATH + "/ArchivoAnexoDownloadServlet?id=" + record.getId() + "' target='_blank'>");
			sb.append("<img src='img/icons/" + img  + "'>");
			sb.append("<br>");
			sb.append(nombre);
			sb.append("</a><br>");

			sb.append("<a class='btn btn-danger' onclick='deleteAnexo(" + record.getId() + ")'>Quitar</a>");
			sb.append("</div>");

		}
		sb.append("</div>");




		return sb.toString();
	}







}
