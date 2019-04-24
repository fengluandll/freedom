package mx.com.televisa.derechocorporativo.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.com.televisa.derechocorporativo.modules.home.MenuBean;

/**
 * Servlet implementation class RecargaMenuServlet
 */
@WebServlet("/RecargaMenuServlet")
public class RecargaMenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public RecargaMenuServlet() {
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //String empresa = request.getParameter("emp");
        HttpSession session = request.getSession();
        String codigoHtml = (String)session.getAttribute("recargaMenu");
        try {
            out.println(codigoHtml);
            
        } catch (Exception e) {
			e.printStackTrace();
		} finally {
            out.close();
        }
        
       }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

}
