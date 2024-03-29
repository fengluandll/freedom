package mx.com.televisa.derechocorporativo.modules.reports.ecs;

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
import mx.com.televisa.derechocorporativo.modules.reportsFlex.ReportFlex;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Servlet implementation class FlexReportECSToExcel
 */
@WebServlet("/FlexReportECSToExcel")
public class FlexReportECSToExcel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
protected void performRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		/*String repId = request.getParameter("repId");

		int intRepId = Integer.parseInt(repId);

		ReportFlex rep = ReportFlex.getReport(repId);
		*/
		String idEmpresas  = request.getParameter("hiddenEmpresa");
		String accionistas = request.getParameter("hiddenAccionista");
		String fileName = "Estructura_de_capital_social.xlsx";
		
		SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
		int liIdRol =   Integer.valueOf(sessionBean.getIdRol());
		

		try {
			
			XSSFWorkbook wb = ReporteECSExcel.getReportAsExcel(request, idEmpresas,accionistas);

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
	
	
	
	
    public FlexReportECSToExcel() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
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
