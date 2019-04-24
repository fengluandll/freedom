package com.movemini.simpleajaxservlet.notificaciones;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.movemini.data.OneValueFactory;
import com.movemini.notificacion.EnviarCorreo;
import com.movemini.notificacion.MailFormat;
import com.movemini.pdfreport.ConvertidorPDF;
import com.movemini.util.FileReader;
import com.movemini.data.ConnectionWrapper;
import com.movemini.data.OneRecordFactory;
import com.movemini.data.Record;

/**
 * Servlet implementation class NotificacionCotizacionClienteServlet
 */
@WebServlet("/NotificacionCotizacionClienteServlet")
public class NotificacionCotizacionClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NotificacionCotizacionClienteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String result = "OK";

		String id_evento_version = request.getParameter("id_evento");

		String idEvento = OneValueFactory.get("select id_evento from evento_version where id_evento_version = "+ id_evento_version);

		String email = request.getParameter("email");
		
		String cc = request.getParameter("cc");
		String cco = request.getParameter("cco");
		
		String asunto = request.getParameter("asunto");
		String mensaje = request.getParameter("mensaje");
		String nombreUsuario = request.getParameter("nombre_usuario");
		String apellidoUsuario = request.getParameter("apellido_usuario");

		ConvertidorPDF convertidor = new ConvertidorPDF();

		String baseUrl = OneValueFactory.get("select valor from parametros where nombre = 'baseUrl'");
		String jspCotizacion = OneValueFactory.get("select valor from parametros where nombre = 'jspCotizacion'");

		List<NameValuePair> urlParametros = new ArrayList<NameValuePair>();

		urlParametros.add(new BasicNameValuePair("ID_EVENTO", idEvento));
		urlParametros.add(new BasicNameValuePair("NOMBRE", nombreUsuario));
		urlParametros.add(new BasicNameValuePair("APELLIDO", apellidoUsuario));


		String path = FileReader.getServerContextPath(request);


		Record nuevo = OneRecordFactory.getPr("insert_cotizacion_pdf", id_evento_version,nombreUsuario, apellidoUsuario);

		String id = nuevo.get("id");
		String fullPath = path + "/home/reporte_cotizacion_pdf.jsp?ID_PDF=" + id;

//		URL url = new URL(fullPath);
//
//		File f = null;
//
//		FileUtils.copyURLToFile(url, f);


		List<File> adjuntos = new ArrayList<File>();

//		adjuntos.add(f);




			MailFormat mailFormat = new MailFormat();
			String correo = OneValueFactory.get("select mail from mail_template where id_mail = 1");
			mailFormat.setSubject(asunto);
			mensaje = mensaje.replaceAll("(\r\n|\n)", "<br />");
			correo = correo.replace("%MENSAJE%", mensaje);
			correo = correo.replace("%FULL_PATH%", fullPath);

			//"<html><head></head><body><label>" + mensaje + "</label>" + "<br><br><a href='" + fullPath + "'>Ver Documento PDF</a>" + "</body></html>"
			mailFormat.setContent(correo);
			mailFormat.setHtmlContent(true);



//			if(convertidor.processFile("", "cotizacion_" + idEvento, baseUrl, jspCotizacion, urlParametros)) {
//
//				adjuntos.add(convertidor.getArchivoEnvio());
//			}

			EnviarCorreo.EnviarTo(email, cc, cco, "omnilingua@omnilingua.com.mx ", mailFormat, adjuntos);



		response.getWriter().append(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
