package com.movemini.simpleajaxservlet.notificaciones;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.movemini.data.OneValueFactory;
import com.movemini.notificacion.EnviarCorreo;
import com.movemini.notificacion.MailFormat;
import com.movemini.pdfreport.ConvertidorPDF;
import com.movemini.util.FileReader;

/**
 * Servlet implementation class NotificacionMemoServicioServlet
 */
@WebServlet("/NotificacionMemoServicioServlet")
public class NotificacionMemoServicioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NotificacionMemoServicioServlet() {
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
		String emailCliente = request.getParameter("emailCliente");
		String emailOmnilingua = request.getParameter("emailOmnilingua");
		String emailIntepretes = request.getParameter("emailIntepretes");

		String asuntoCliente = request.getParameter("asuntoCliente");
		String asuntoOmnilingua = request.getParameter("asuntoOmnilingua");
		String mensajeCliente = request.getParameter("mensajeCliente");
		String mensajeOmnilingua = request.getParameter("mensajeOmnilingua");

		emailOmnilingua += ";"+emailIntepretes;
		//String nombreUsuario = request.getParameter("nombre_usuario");
		//String apellidoUsuario = request.getParameter("apellido_usuario");

		ConvertidorPDF convertidor = new ConvertidorPDF();

		String baseUrl = OneValueFactory.get("select valor from parametros where nombre = 'baseUrl'");
		String jspMemoServicio = OneValueFactory.get("select valor from parametros where nombre = 'jspMemoServicio'");

		List<NameValuePair> urlParametros = new ArrayList<NameValuePair>();

		urlParametros.add(new BasicNameValuePair("ID_EVENTO", id_evento_version));
		//urlParametros.add(new BasicNameValuePair("NOMBRE", nombreUsuario));
		//urlParametros.add(new BasicNameValuePair("APELLIDO", apellidoUsuario));

		// envio a cliente si archivo

		MailFormat mailFormat = new MailFormat();

		mailFormat.setSubject(asuntoCliente);
		mailFormat.setContent("<html><head></head><body>" + mensajeCliente + "</body></html>");
		mailFormat.setHtmlContent(true);

		List<File> adjuntos = new ArrayList<File>();
		EnviarCorreo.EnviarTo(emailCliente, "", "", "omnilingua@omnilingua.com.mx", mailFormat, adjuntos);









		// envio a cliente si archivo





//		if(convertidor.processFile("", "memo_servicio_" + idEvento, baseUrl, jspMemoServicio, urlParametros))
//		{
//			adjuntos.add(convertidor.getArchivoEnvio());
//		}

		String path = FileReader.getServerContextPath(request);

		String fullPath = path + "/home/reporte_memo_servicio_pdf.jsp?ID_EVENTO=" + id_evento_version	;

//		URL url = new URL(fullPath);
//
//		File f = null;
//
//		FileUtils.copyURLToFile(url, f);


		//List<File> adjuntos = new ArrayList<File>();

//		adjuntos.add(f);






			mailFormat.setSubject(asuntoOmnilingua);
			mailFormat.setContent("<html><head></head><body>" + mensajeOmnilingua + "" + "<br><br><a href='" + fullPath + "'>Ver Documento PDF</a>" + "</body></html>");
			mailFormat.setHtmlContent(true);



			if(!emailOmnilingua.equals("")){

				String argEmail [] = emailOmnilingua.split(";");
				for(String email : argEmail){
					EnviarCorreo.EnviarTo(email.trim(), "", "", "omnilingua@omnilingua.com.mx", mailFormat, adjuntos);
				}
			}








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
