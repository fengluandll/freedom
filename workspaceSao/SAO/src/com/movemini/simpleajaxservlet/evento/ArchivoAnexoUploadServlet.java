package com.movemini.simpleajaxservlet.evento;

import java.io.IOException;
import java.sql.Blob;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.rowset.serial.SerialBlob;

import org.apache.commons.io.IOUtils;

import com.movemini.data.ConnectionWrapper;

/**
 * Servlet implementation class ArchivoAnexoUploadServlet
 */
@WebServlet("/ArchivoAnexoUploadServlet")
@MultipartConfig(
		fileSizeThreshold=0,
		maxFileSize=5242880,       // 5 MB
		maxRequestSize=20971520)   // 20 MB
public class ArchivoAnexoUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArchivoAnexoUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());



		String id_evento_version = request.getParameter("id_evento");


		//System.out.println("Hello!!!");


		Blob blob = null;
		try {

				ConnectionWrapper con = new ConnectionWrapper();


			 for (Part part : request.getParts()) {

				PreparedStatement stm = con.prepareStatement("INSERT INTO evento_anexo (id_evento_version, nombre_archivo, archivo) VALUES (?,?,?)");

		        String nombreArchivo = part.getSubmittedFileName();

			    blob = new SerialBlob(IOUtils.toByteArray(part.getInputStream()));//debugger says myinputstream has contents here.

			    stm.setString(1, id_evento_version);
			    stm.setString(2, nombreArchivo);
			    stm.setBlob(3, blob);



			    stm.executeUpdate();


			    //someObject.setSomeBlobProperty(blob);//debugger says blob is null here
			 }




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
