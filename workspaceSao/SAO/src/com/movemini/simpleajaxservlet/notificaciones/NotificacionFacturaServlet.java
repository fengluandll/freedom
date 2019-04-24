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
import com.movemini.data.Record;
import com.movemini.data.OneRecordFactory;
import com.movemini.notificacion.EnviarCorreo;
import com.movemini.layers.session.SessionBean;


import com.movemini.notificacion.MailFormat;
import com.movemini.pdfreport.ConvertidorPDF;
import com.movemini.util.FileReader;

/**
 * Servlet implementation class NotificacionFacturaServlet
 */
@WebServlet("/NotificacionFacturaServlet")
public class NotificacionFacturaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NotificacionFacturaServlet() {
        super();
        // TODO Auto-generated constructor stub
				/**/
    }
/*
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String result = "OK";
		String id_evento = request.getParameter("id_evento");
		String id_factura = request.getParameter("id_factura");
		String emailOmnilingua = request.getParameter("emailOmnilingua");
		String asuntoOmnilingua = request.getParameter("asuntoOmnilingua");
		String mensajeOmnilingua = request.getParameter("mensajeOmnilingua");
		//String nombreUsuario = request.getParameter("nombre_usuario");
		//String apellidoUsuario = request.getParameter("apellido_usuario");

		Record user = SessionBean.getInstance(request).getUser();
		String nombreUsuario = "";
		String apellidosUsuario ="";

			apellidosUsuario = user.getString("apellido");
			nombreUsuario = user.getString("nombre");


		ConvertidorPDF convertidor = new ConvertidorPDF();

		String baseUrl = OneValueFactory.get("select valor from parametros where nombre = 'baseUrl'");
		String jspMemoServicio = OneValueFactory.get("select valor from parametros where nombre = 'jspMemoServicio'");

		List<NameValuePair> urlParametros = new ArrayList<NameValuePair>();

		urlParametros.add(new BasicNameValuePair("ID_DETALLE", id_factura));
		//urlParametros.add(new BasicNameValuePair("NOMBRE", nombreUsuario));
		//urlParametros.add(new BasicNameValuePair("APELLIDO", apellidoUsuario));

		// envio a cliente si archivo

		Record nuevo = OneRecordFactory.getPr("insert_cotizacion_pdf", id_evento,nombreUsuario, apellidosUsuario);

		MailFormat mailFormat = new MailFormat();

// envio a cliente si archivo


	List<File> adjuntos = new ArrayList<File>();



//		if(convertidor.processFile("", "memo_servicio_" + idEvento, baseUrl, jspMemoServicio, urlParametros))
//		{
//			adjuntos.add(convertidor.getArchivoEnvio());
//		}

		String path = FileReader.getServerContextPath(request);
		String id = nuevo.get("id");


		String fullPath = path + "/home/factura_pdf.jsp?ID_EVENTO=" + id_evento +"&idFactura="+id_factura	;
		String fullPath2 = path + "/home/reporte_cotizacion_pdf.jsp?ID_PDF=" + id;

//		URL url = new URL(fullPath);
//
//		File f = null;
//
//		FileUtils.copyURLToFile(url, f);


		//List<File> adjuntos = new ArrayList<File>();

//		adjuntos.add(f);






			mailFormat.setSubject(asuntoOmnilingua);
			mailFormat.setContent("<html><head></head><body>" + mensajeOmnilingua + "" + "<br><br><a href='" + fullPath + "'>Ver Documento Factura</a><br><a href='" + fullPath2 + "'>Ver Documento Cotizac&oacute;n</a>" + "</body></html>");
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
