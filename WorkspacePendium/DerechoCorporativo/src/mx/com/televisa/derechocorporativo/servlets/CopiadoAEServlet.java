package mx.com.televisa.derechocorporativo.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.com.televisa.derechocorporativo.daos.AESCopyDAO;

/**
 * Servlet implementation class CopiadoAEServlet
 */
@WebServlet("/CopiadoAEServlet")
public class CopiadoAEServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CopiadoAEServlet() {
        super();
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response)throws IOException{
    	System.out.println("Entro al servlet");
    	response.setContentType("text/html;charset=UTF-8");
    	String metaRowId = request.getParameter("metaRowId");
    	String idEmpresa = request.getParameter("idEmpresa");
    	
    	if(metaRowId != null && idEmpresa != null){
    		boolean exito = AESCopyDAO.doCopy(metaRowId,idEmpresa);
    		if(exito){
    			response.getWriter().write("ok");
    		}else{
    			response.getWriter().write("nOk");
    		}
    	}else{
    		response.getWriter().write("No se encontro el id Metarow");
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
