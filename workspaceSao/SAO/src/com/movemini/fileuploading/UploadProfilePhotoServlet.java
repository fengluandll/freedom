package com.movemini.fileuploading;

import java.io.IOException;
import java.sql.*;

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
import com.movemini.layers.session.SessionBean;


/**
 * Servlet implementation class UploadProfilePhoto
 */
@WebServlet("/UploadProfilePhotoServlet")
@MultipartConfig(location="/tmp",
                 fileSizeThreshold=0,    
                 maxFileSize=5242880,       // 5 MB
                 maxRequestSize=20971520)   // 20 MB
public class UploadProfilePhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadProfilePhotoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
		
		
		
		System.out.println("Hello!!!");
		
		
		Blob blob = null;
		try {
			
				ConnectionWrapper con = new ConnectionWrapper();
			
			
			 for (Part part : request.getParts()) {
				 
				PreparedStatement stm = con.prepareStatement("UPDATE ss_usuario SET imagen = ? WHERE id_usuario = " + SessionBean.getInstance(request).getUser().getId());
				 

			    blob = new SerialBlob(IOUtils.toByteArray(part.getInputStream()));//debugger says myinputstream has contents here.
			    
			    
			    stm.setBlob(1, blob);
			    
			    stm.executeUpdate();
			    
			    
			    //someObject.setSomeBlobProperty(blob);//debugger says blob is null here	 
			 }
			 
			
		    
		    
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		
		
		
		
//		
//		
//		// gets absolute path of the web application
//        String appPath = request.getServletContext().getRealPath("");
//
//        // constructs path of the directory to save uploaded file
//        String savePath = appPath + File.separator + SAVE_DIR;
//         
//        
//        // creates the save directory if it does not exists
//        File fileSaveDir = new File(savePath);
//        if (!fileSaveDir.exists()) {
//            fileSaveDir.mkdir();
//        }
//         
//        for (Part part : request.getParts()) {
//
//        	part.getInputStream();
//        	
//        	
//        	String fileName = extractFileName(part);
//            // refines the fileName in case it is an absolute path
//            fileName = new File(fileName).getName();
//            part.write(savePath + File.separator + fileName);
//        }
//        request.setAttribute("message", "Upload has been done successfully!");
//        getServletContext().getRequestDispatcher("/message.jsp").forward(
//                request, response);
//		
        
        
        
        
        
        
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
