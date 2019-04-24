package mx.com.televisa.derechocorporativo.reports;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.com.televisa.derechocorporativo.bean.SessionBean;
import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;
import mx.com.televisa.derechocorporativo.modules.reportsFlex.ReportFlex;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Servlet implementation class FlexReportToExcel
 */
@WebServlet("/FlexReportToExcel")
public class FlexReportToExcel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	
	protected void performRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		String repId = request.getParameter("repId");

		int intRepId = Integer.parseInt(repId);

		ReportFlex rep = ReportFlex.getReport(repId);

		String fileName = rep.getNomReporte().replace(" ", "_")+".xlsx";
		
		SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
		int liIdRol =   Integer.valueOf(sessionBean.getIdRol());
		

		try {
			
			XSSFWorkbook wb = ReportFlex.getReportAsExcel(intRepId, request, liIdRol );

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
	
	
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FlexReportToExcel() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		performRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		performRequest(request, response);
	}

}
