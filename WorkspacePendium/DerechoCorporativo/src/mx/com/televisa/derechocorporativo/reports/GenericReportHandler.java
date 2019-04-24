package mx.com.televisa.derechocorporativo.reports;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/*
import com.iavsa.sofiareports.data.Conexion;
import com.iavsa.sofiareports.data.HardCodeConstants;
import com.iavsa.sofiareports.util.MIME_TYPES;
*/

public class GenericReportHandler {
/*
	
	public static void createReport(HttpServletRequest request, HttpServletResponse response, String query, String layoutFileName, String fileName, int startRow) {
		
		String tempFile = HardCodeConstants.TEMP_FILE;
		
		Conexion conn = null;
		Statement recordsStmt  = null;
		ResultSet recordsSet = null;
		
		ResultSetMetaData metaData = null;
		
		try {
		
			//Layout
			String layoutsPath = "http://" + request.getServerName() + ":" + request.getLocalPort() + request.getContextPath() + "/layouts/";
			URL dir = new URL(layoutsPath + layoutFileName);
			InputStream inp = dir.openStream();
			
			XSSFWorkbook wb = new XSSFWorkbook(inp);
			XSSFSheet sheet = wb.getSheetAt(0);

			conn = new Conexion();
			
			recordsStmt = conn.createStatement();
			recordsSet = recordsStmt.executeQuery(query);			
			metaData = recordsSet.getMetaData();

			int rowIndex = startRow;

			while (recordsSet.next()) {

				XSSFRow row = sheet.getRow(rowIndex);

				if (row == null)
					row = sheet.createRow(rowIndex);

				for (int colIndex = 0; colIndex < metaData.getColumnCount(); colIndex++) {

					String fieldValue = recordsSet.getString(colIndex + 1);

					XSSFCell cell = row.getCell(colIndex);
					if (cell == null)
						cell = row.createCell(colIndex);

					//Triming
					cell.setCellValue(fieldValue.trim());
				}

				rowIndex++;
			}

			FileOutputStream fileOut = new FileOutputStream(tempFile);
			wb.write(fileOut);
			fileOut.close();

			FileInputStream fin = new FileInputStream(new File(tempFile));

			response.setHeader("Pragma", "public");
			response.setHeader("Expires", "0");
			response.setHeader("Cache-Control","must-revalidate, post-check=0, pre-check=0");
			response.setHeader("Cache-Control", "false");
			response.setHeader("Content-Disposition", "attachment;filename=\""+ fileName + "\"");
			response.setHeader("Content-Transfer-Encoding", "binary");
			response.setContentType(MIME_TYPES.EXCEL);
			// response.setContentLength(fileInBytes.length);

			BufferedInputStream bis = new BufferedInputStream(fin);

			ServletOutputStream stream = response.getOutputStream();
			int readBytes = 0;

			while ((readBytes = bis.read()) != -1)
				stream.write(readBytes);
		
		
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
			
			new File(tempFile).delete();
			Conexion.closeAll(recordsStmt, recordsSet, conn);
		}
	}
*/
}
