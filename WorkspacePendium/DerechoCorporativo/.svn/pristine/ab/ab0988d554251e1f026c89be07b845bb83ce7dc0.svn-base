package mx.com.televisa.derechocorporativo.modules.reports.adminvig;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.com.televisa.derechocorporativo.bean.SessionBean;
import mx.com.televisa.derechocorporativo.modules.reports.ecs.ReporteECSExcel;
import mx.com.televisa.derechocorporativo.util.StringUtils;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Servlet implementation class FlexReportVigToExcel
 */
@WebServlet("/FlexReportVigToExcel")
public class FlexReportVigToExcel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public FlexReportVigToExcel() {
        super();
    }

protected void performRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		/*String repId = request.getParameter("repId");

		int intRepId = Integer.parseInt(repId);

		ReportFlex rep = ReportFlex.getReport(repId);
		*/
		String idEmpresas = request.getParameter("hiddenEmpresa");
		String idPersona = request.getParameter("idPersona").length() > 0?request.getParameter("idPersona"):"";
		String totAdminVig = request.getParameter("totAdminVig");
		
		
		String fileName = "Administracion_vigilancia.xlsx";
		
		SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
		int liIdRol =   Integer.valueOf(sessionBean.getIdRol());
		

		try {

			String[] arrayIdFlex = StringUtils.split(totAdminVig,",");	
			XSSFWorkbook wb = ReporteAdmVigExcel.getReportAsExcel(request, idEmpresas,arrayIdFlex,idPersona );

			FileOutputStream fileOut = new FileOutputStream("tmp.xlsx");
			wb.write(fileOut);
			fileOut.close();

			FileInputStream fin = new FileInputStream(new File("tmp.xlsx"));

			response.setHeader("Pragma", "public");
			response.setHeader("Expires", "0");
			response.setHeader("Cache-Control","must-revalidate, post-check=0, pre-check=0");
			response.setHeader("Cache-Control", "false");
			response.setHeader("Content-Disposition", "attachment;filename=\""+ fileName + "\"");
			response.setHeader("Content-Transfer-Encoding", "binary");
			response.setContentType("application/vnd.ms-excel");
			// response.setContentLength(fileInBytes.length);

			BufferedInputStream bis = new BufferedInputStream(fin);

			ServletOutputStream stream = response.getOutputStream();
			int readBytes = 0;

			while ((readBytes = bis.read()) != -1) {		
				stream.write(readBytes);
			}
			
		
		} catch (Exception ex) {
			ex.printStackTrace();
			response.setContentType("text/html;charset=UTF-8");
	        PrintWriter out = null;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        out.println(ex.toString());
		} finally {
			
			//new File(tempFile).delete();
			
		}
		
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		performRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		performRequest(request, response);
	}

}
