package com.movemini.reports;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.CallableStatement;
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

import com.movemini.config.HardCodeConstants;
import com.movemini.data.ConnectionWrapper;
import com.movemini.util.ExcelUtil;
import com.movemini.util.StringUtils;



public class GenericReportHandler {

	public static void createReport(HttpServletRequest request, HttpServletResponse response, 
			String procedure,
			//String query,
			String layoutFileName, String fileName, int startRow) {

		String tempFile = HardCodeConstants.TEMP_DIR + "temp.xls";

		ConnectionWrapper conn = null;
		CallableStatement recordsStmt = null;
		ResultSet recordsSet = null;

		ResultSetMetaData metaData = null;

		try {

			// Layout
//			String layoutsPath = "http://" + request.getServerName() + ":" + request.getLocalPort()
//					+ request.getContextPath() + "/layouts/";
			
			
//			URL dir = new URL(layoutsPath + layoutFileName);
//			InputStream inp = dir.openStream();

//			XSSFWorkbook wb = new XSSFWorkbook(inp);
			XSSFWorkbook wb = new XSSFWorkbook();
			XSSFSheet sheet = wb.createSheet();
			//XSSFSheet sheet = wb.getSheetAt(0);

			conn = new ConnectionWrapper();

			//recordsStmt = conn.createStatement();
			//recordsSet = recordsStmt.executeQuery(query);
			
			String noParams = "";
			
			recordsStmt = conn.prepareCallProcedure(procedure, noParams); 
			recordsSet = recordsStmt.executeQuery();
			
			metaData = recordsSet.getMetaData();

			

			
			//String columNamesLine = "";
			for (int colIndex = 1; colIndex <= recordsSet.getMetaData().getColumnCount(); colIndex++) {
				
				String colName = recordsSet.getMetaData().getColumnLabel(colIndex);
				
				//columNamesLine += StringUtils.tipoTitulo(colName.trim().replace("_", " "));
				colName = StringUtils.tipoTitulo(colName.trim().replace("_", " "));
				
				colName = (colName.length() <= 3) ? colName.toUpperCase() : colName;
				
				ExcelUtil.setCellValue(sheet, 0, colIndex - 1, colName);
			}
			
			
			
			//int rowIndex = startRow;
			int rowIndex = 1;
			
			
			while (recordsSet.next()) {

//				XSSFRow row = sheet.getRow(rowIndex);

				//if (row == null)
				//	row = sheet.createRow(rowIndex);

				
				
				
				
				for (int colIndex = 0; colIndex < metaData.getColumnCount(); colIndex++) {

					String fieldValue = recordsSet.getString(colIndex + 1);

//					XSSFCell cell = row.getCell(colIndex);
//					if (cell == null)
//						cell = row.createCell(colIndex);

					// Triming
					//cell.setCellValue(fieldValue.trim());
					
					
					
					String value = (fieldValue != null)? fieldValue.trim() : "";
					
					ExcelUtil.setCellValue(sheet, rowIndex, colIndex, value);
					
					
					
				}

				rowIndex++;
			}
			
			
			ExcelUtil.autoSizeColumns(wb);
			

			FileOutputStream fileOut = new FileOutputStream(tempFile);
			wb.write(fileOut);
			fileOut.close();

			FileInputStream fin = new FileInputStream(new File(tempFile));

			response.setHeader("Pragma", "public");
			response.setHeader("Expires", "0");
			response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
			response.setHeader("Cache-Control", "false");
			response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
			response.setHeader("Content-Transfer-Encoding", "binary");
			response.setContentType("application/vnd.ms-excel");
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
			ConnectionWrapper.closeAll(recordsStmt, recordsSet, conn);
		}
	}

}
