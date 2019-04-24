package com.movemini.fileuploading;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import com.movemini.data.ConnectionWrapper;
import com.movemini.layers.session.SessionBean;

/**
 * Servlet implementation class ProfileImageServlet
 */
@WebServlet("/ProfilePhotoServlet")
public class ProfilePhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProfilePhotoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());

		int BUFFER_SIZE = 4096;

		String idUsuario = request.getParameter("idUser");
		
		if(idUsuario == null || idUsuario.equals("")) {
			
			idUsuario = SessionBean.getInstance(request).getUser().getId();
		}
		
			
		
		

		try {

			ConnectionWrapper con = new ConnectionWrapper();

			String sql = "SELECT * FROM ss_usuario WHERE id_usuario = ?";
			PreparedStatement statement = con.prepareStatement(sql);

			statement.setObject(1, idUsuario);

			ResultSet result = statement.executeQuery();

			if (result.next()) {

				//String fileName = result.getString("file_name");
				//Blob blob = result.getBlob("file_data");
				
				
				String fileName = result.getString("usuario") + ".jpg";
				Blob blob = result.getBlob("imagen");
				
				
				InputStream inputStream = blob.getBinaryStream();
				int fileLength = inputStream.available();

				System.out.println("fileLength = " + fileLength);

				ServletContext context = getServletContext();

				String mimeType = context.getMimeType(fileName);
				if (mimeType == null) {
					mimeType = "application/octet-stream";
				}

				response.setContentType(mimeType);
				response.setContentLength(fileLength);
				String headerKey = "Content-Disposition";
				String headerValue = String.format("attachment; filename=\"%s\"", fileName);
				response.setHeader(headerKey, headerValue);

				OutputStream outStream = response.getOutputStream();

				byte[] buffer = new byte[BUFFER_SIZE];
				int bytesRead = -1;

				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outStream.write(buffer, 0, bytesRead);
				}

				inputStream.close();
				outStream.close();

			} else {

				response.getWriter().print("File not found ");
			}

		} catch (Exception ex) {

			ex.printStackTrace();
			response.getWriter().print("IO Error: " + ex.getMessage());

		} finally {

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
