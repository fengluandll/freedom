package mx.com.televisa.derechocorporativo.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.com.televisa.derechocorporativo.bean.GlosarioBean;
import mx.com.televisa.derechocorporativo.bean.SessionBean;
import mx.com.televisa.derechocorporativo.daos.GlosarioDAO;


/**
 * Servlet implementation class SubeGlosarioServlet
 */
@WebServlet("/DescargaGlosarioServlet")
public class DescargaGlosarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	GlosarioDAO glosarioDAO;
	
	public void processRequest(HttpServletRequest tRequest, HttpServletResponse tResponse) throws IOException{
	
			HttpSession session = tRequest.getSession();
			SessionBean sessionBean = (SessionBean)session.getAttribute("sessionBean");
			GlosarioDAO glosarioDAO = new GlosarioDAO();
			GlosarioBean glosarioBean = glosarioDAO.dameArchivoGlosario();
			
			InputStream fileContent = null;
			tResponse.setContentType("application/pdf");
			tResponse.addHeader("Content-Disposition", "attachment; filename=" + glosarioBean.getNomArchivo());
			//tResponse.setContentLength((int) pdfFile.length());

			//FileInputStream fileInputStream = new FileInputStream(glosarioBean.getArchivo());
			OutputStream responseOutputStream = tResponse.getOutputStream();
			int bytes;
			while ((bytes = glosarioBean.getArchivo().read()) != -1) {
				responseOutputStream.write(bytes);
			}
		
	
	}
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

}
