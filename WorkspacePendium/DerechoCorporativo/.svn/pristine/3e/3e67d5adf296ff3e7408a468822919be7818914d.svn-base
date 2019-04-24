package mx.com.televisa.derechocorporativo.servlets;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;

import mx.com.televisa.derechocorporativo.bean.SessionBean;
import mx.com.televisa.derechocorporativo.daos.GlosarioDAO;


/**
 * Servlet implementation class SubeGlosarioServlet
 */
@WebServlet("/SubeGlosarioServlet")
@MultipartConfig
public class SubeGlosarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	GlosarioDAO glosarioDAO;
	
	public void processRequest(HttpServletRequest tRequest, HttpServletResponse tResponse) throws IOException{
		try {
			HttpSession session = tRequest.getSession();
			SessionBean sessionBean = (SessionBean)session.getAttribute("sessionBean");
			InputStream fileContent = null;
			/*String nomArchivo2 = tRequest.getParameter("file");
			Part filePart = tRequest.getPart("file");
			String nomArchivo = filePart.getName();
		    InputStream fileContent = filePart.getInputStream();*/
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> formItems = upload.parseRequest(tRequest);
			 
            if (formItems != null && formItems.size() > 0) {
                String nombreArchivo = "";
                String despPunto     = "";
                boolean paso		 = false;
                for (FileItem item : formItems) {
                    // processes only fields that are not form fields
                    if (!item.isFormField()) {
                        fileContent = item.getInputStream();
                        nombreArchivo = item.getName();
                        despPunto = StringUtils.substringAfter(nombreArchivo, ".");
                        if(despPunto.equals("pdf") || despPunto.equals("PDF")){
                        	glosarioDAO = new GlosarioDAO();
                		    paso = glosarioDAO.insertGlosarioPdf(item.getName(), fileContent, sessionBean);
                		    if(paso){
                		    	session.setAttribute("messageFileUpload","<span style='font-size: 11px;color: green;'>¡¡Tu archivo fue cargado correctamente!!</span>");
                		    }                        
                        }else{
                        		session.setAttribute("messageFileUpload","<span style='font-size: 11px;color: red;'>Error: Solo es permitido subir archivos PDF</span>");
                        }
                        
                    }
                }
            }else{
            	session.setAttribute("messageFileUpload","<span style='font-size: 11px;color: red;'>Es requerido subir un archivo PDF</span>");
            }
            	tResponse.sendRedirect(tRequest.getContextPath()+"/jsp/ayuda/actualizaGlosario.jsp");
			
		    
		} catch (FileUploadException e) {
			e.printStackTrace();
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
