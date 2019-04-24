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
import com.movemini.util.FileReader;

import com.movemini.data.OneValueFactory;
import org.apache.http.message.BasicNameValuePair;
import com.movemini.data.OneRecordFactory;
import com.movemini.notificacion.EnviarCorreo;
import com.movemini.notificacion.MailFormat;
import com.movemini.pdfreport.ConvertidorPDF;
import com.movemini.util.FileReader;
import com.movemini.data.Record;


/**
 * Servlet implementation class NotificacionCierreClienteServlet
 */
@WebServlet("/NotificacionCierreClienteServlet")
public class NotificacionCierreClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NotificacionCierreClienteServlet() {
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
		String email = request.getParameter("email");
		String asunto = request.getParameter("asunto");
		String mensaje = request.getParameter("mensaje");
		String nombreUsuario = request.getParameter("nombre_usuario");
		String apellidoUsuario = request.getParameter("apellido_usuario");

		List<File> adjuntos = new ArrayList<File>();

		MailFormat mailFormat = new MailFormat();
		String correo = OneValueFactory.get("select mail from mail_template where id_mail = 1");

		String path = FileReader.getServerContextPath(request);


		Record nuevo = OneRecordFactory.getPr("insert_cotizacion_pdf", id_evento_version,nombreUsuario, apellidoUsuario);
		String id = nuevo.get("id");
		String fullPath = path + "/home/reporte_cotizacion_pdf.jsp?ID_PDF=" + id;

		mailFormat.setSubject(asunto);
		mensaje = mensaje.replaceAll("(\r\n|\n)", "<br />");
		correo = correo.replace("%MENSAJE%", mensaje);
		correo = correo.replace("%FULL_PATH%", fullPath);

		mailFormat.setContent(correo);
		mailFormat.setHtmlContent(true);

		EnviarCorreo.EnviarTo(email, "omnilingua@omnilingua.com.mx ", mailFormat);



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
