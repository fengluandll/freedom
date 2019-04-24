package mx.com.televisa.derechocorporativo.servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.com.televisa.derechocorporativo.config.AppConfig;
import mx.com.televisa.derechocorporativo.util.ExecSSH;


/**
 * Servlet implementation class DownloadDocumentumFile
 */
@WebServlet("/DownloadDocumentumFile")
public class DownloadDocumentumFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException{
		// reads input file from an absolute path
		//String doc = request.getParameter("doc");
		HttpSession session = request.getSession();
		String doc = (String)session.getAttribute("numDocumentum"); //FacesUtils.getSessionBean().getLstNumDocumentum();
		
		System.out.println("Iniciando extracción de documeto..."+doc);
		ExecSSH exec = new ExecSSH();
		exec.setSsh(AppConfig.getConfigValue("SH_ES_RUTA"),AppConfig.getConfigValue("IP_SRV_DOC"),AppConfig.getConfigValue("USR_DOC"),
				AppConfig.getConfigValue("PWD_DOC"), doc,AppConfig.getConfigValue("REPO_DOC"),AppConfig.getConfigValue("FIN_RUTA"), doc.replace(",", "_"));
		exec.getFile(AppConfig.getConfigValue("IP_SRV_DOC"), AppConfig.getConfigValue("USR_DOC"), AppConfig.getConfigValue("PWD_DOC"),
				AppConfig.getConfigValue("FIN_RUTA")+"pdf/"+doc.replace(",", "_")+".pdf", System.getProperty("user.dir")+"/"+doc.replace(",", "_")+".pdf");
		exec.doEraseSsh(AppConfig.getConfigValue("IP_SRV_DOC"), AppConfig.getConfigValue("USR_DOC"), AppConfig.getConfigValue("PWD_DOC"),
				AppConfig.getConfigValue("FIN_RUTA"), doc.replace(",", "_"));
		
		System.out.println("PahtLocal: "+System.getProperty("user.dir"));
		
		String filePath = System.getProperty("user.dir")+"/"+doc.replace(",", "_")+".pdf";
        File downloadFile = new File(filePath);
        try{ 
        	FileInputStream inStream = new FileInputStream(downloadFile);
        	// if you want to use a relative path to context root:
            String relativePath = getServletContext().getRealPath("");
            System.out.println("relativePath = " + relativePath);
             
            // obtains ServletContext
            ServletContext context = getServletContext();
             
            // gets MIME type of the file
            String mimeType = context.getMimeType(filePath);
            if (mimeType == null) {        
                // set to binary type if MIME mapping not found
                mimeType = "application/octet-stream";
            }
            System.out.println("MIME type: " + mimeType);
             
            // modifies response
            response.setContentType(mimeType);
            response.setContentLength((int) downloadFile.length());
             
            // forces download
            String headerKey = "Content-Disposition";
            String headerValue = String.format("inline; filename=\"%s\"", downloadFile.getName());
            response.setHeader(headerKey, headerValue);
             
            // obtains response's output stream
            OutputStream outStream = response.getOutputStream();
             
            byte[] buffer = new byte[4096];
            int bytesRead = -1;
             
            while ((bytesRead = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }
             
            inStream.close();
            outStream.close();
            downloadFile.delete(); 
            
            /*PrintWriter out = response.getWriter();
            response.setContentType("text/html; charset=UTF-8");
            response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
            response.setHeader("Pragma","no-cache"); //HTTP 1.0
            response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
            out.println("<html><head>");
            out.println("<script type='text/javascript'>" +
                      "window.parent.document.getElementById('loader').style.display ='none';" +
                         "</script>");
            out.println("</head><body>Prueba...</body></html>"); */
        }catch(Exception e){
        	System.out.println("No se pudo encontrar el archivo");
        	response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Importar archivo de Documentum </title>");
            out.println("</head>");
            out.println("<body bgcolor=\"white\">");
            out.println("<body>");
            out.println("<P>No se pudo encontrar el documento solicitado</P>");
            out.println("</body>");
            out.println("</html>");
        }
        
         
         
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

}
