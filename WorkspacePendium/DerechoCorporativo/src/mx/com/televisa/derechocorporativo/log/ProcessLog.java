package mx.com.televisa.derechocorporativo.log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.log4j.Logger;
import org.json.JSONObject;

@WebServlet("/ProcessLog")
public class ProcessLog extends HttpServlet {
	final static Logger log = Logger.getLogger(ProcessLog.class);
	

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		
		log.info("Parametros de descarga");
		log.info("Path: " + request.getParameter("path"));
		log.info("Hora de Inicio: " + request.getParameter("hInicio"));
		log.info("Hora de fin: " + request.getParameter("hFin"));
		log.info("Metodo: "+request.getParameter("method"));
		if (request.getParameter("method").equals("ver")) {
			// create Json Object
			JSONObject json = new JSONObject();
			PrintWriter out = response.getWriter();
			// put some value pairs into the JSON object .

			json.put(
					"jsonLog",
					getTxtLog(request.getParameter("path"),
							request.getParameter("hInicio"),
							request.getParameter("hFin")));

			// finally output the json string
			out.print(json.toString());
			log.info("acabo");
			out.close();

		} else {
			response.setContentType("text/txt");
			response.setHeader("Content-Disposition",
					"attachment; filename=\"log.txt\"");

			reponseFile(
					getTxtLog(request.getParameter("path"),
							request.getParameter("hInicio"),
							request.getParameter("hFin")), response);

		}

	}

	

	private String getTxtLog(String path, String horaInicio, String horaFin)
			throws IOException {
		String cadena = "";
		StringBuilder salida = new StringBuilder();
		boolean continua = false;
		FileReader f = new FileReader(path);
		BufferedReader b = new BufferedReader(f);
		while ((cadena = b.readLine()) != null) {
			if(horaInicio.isEmpty() && horaFin.isEmpty()){
				salida.append(cadena + "\n");
			}
			if (cadena.contains(horaInicio) && !(horaInicio.isEmpty() && horaFin.isEmpty())) {
				salida.append(cadena + "\n");
				continua = true;
			}
			if (continua && !(horaInicio.isEmpty() && horaFin.isEmpty())) {
				salida.append(cadena + "\n");
			}
			if (cadena.contains(horaFin) && continua && !(horaInicio.isEmpty() && horaFin.isEmpty())) {
				salida.append(cadena + "\n");
				break;
			}

		}
		b.close();
		return salida.toString();
	}

	private void reponseFile(String content, HttpServletResponse response)
			throws IOException {
		OutputStream outputStream = response.getOutputStream();
		outputStream.write(content.getBytes());
		outputStream.flush();
		outputStream.close();
	}
}
