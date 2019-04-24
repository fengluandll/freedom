package com.movemini.simpleajaxservlet.evento;

import java.io.IOException;
import java.sql.Blob;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.rowset.serial.SerialBlob;

import org.apache.commons.io.IOUtils;

import com.movemini.data.ConnectionWrapper;

/**
 * Servlet implementation class ArchivoAnexoDeleteServlet
 */
@WebServlet("/ArchivoAnexoDeleteServlet")
public class ArchivoAnexoDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArchivoAnexoDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		

		String id_evento_anexo = request.getParameter("id_evento_anexo");

		
		//System.out.println("Hello!!!");
		
		

		try {
			
				ConnectionWrapper con = new ConnectionWrapper();
			
				 
				PreparedStatement stm = con.prepareStatement("DELETE FROM evento_anexo WHERE id_evento_anexo = " + id_evento_anexo);

			    stm.executeUpdate();
			    
			    
			    //someObject.setSomeBlobProperty(blob);//debugger says blob is null here	 
			 
			
		    
		    
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
