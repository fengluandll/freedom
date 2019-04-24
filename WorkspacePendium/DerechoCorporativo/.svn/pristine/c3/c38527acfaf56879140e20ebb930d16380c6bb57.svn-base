package mx.com.televisa.derechocorporativo.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import mx.com.televisa.derechocorporativo.daos.ApoderadosDAO;

/**
 * Servlet implementation class CopiaEscrituraServlet
 */
@WebServlet("/CopiaEscrituraServlet")
public class CopiaEscrituraServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String desde = request.getParameter("selectDesde");
		String hasta = request.getParameter("selectHasta");
		String idEmpresa = request.getParameter("idEmpresa");
		ApoderadosDAO apoderadosDAO = new ApoderadosDAO();
		HttpSession session = request.getSession();
		String escrituraDesde = StringUtils.substringBefore(desde, "|");
		String empresaDesde = StringUtils.substringAfter(desde, "|");
		boolean ok = apoderadosDAO.copiaEscritura(escrituraDesde, hasta, Integer.parseInt(idEmpresa),Integer.parseInt(empresaDesde));
		if(ok){
			String mensaje = "Copia de escritura Correcta";
			session.setAttribute("msgCopyEscritura", mensaje);
			response.sendRedirect(request.getContextPath()+"/faces/jsp/captura/apoderados/copiarEscritura.jsp");
		}
	}
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

}
