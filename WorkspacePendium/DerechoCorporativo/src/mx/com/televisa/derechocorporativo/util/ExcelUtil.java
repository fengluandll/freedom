package mx.com.televisa.derechocorporativo.util;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class ExcelUtil {

	public static void setCellValue(XSSFSheet sheet, int rowIndex, int colIndex, String value) {
		
		
		XSSFRow headRow = sheet.getRow(rowIndex);
		
		if (headRow == null)
			headRow = sheet.createRow(rowIndex);
		
		
		XSSFCell cell = headRow.getCell(colIndex);
		if (cell == null)
			cell = headRow.createCell(colIndex);

		//Triming
		cell.setCellValue(value);
	}
	
	public static void setCellStyleTitle(XSSFSheet sheet, int rowIndex, int colIndex) {
		
		XSSFCellStyle style = sheet.getWorkbook().createCellStyle();
        //style.setBorderTop((short) 6); // double lines border
        //style.setBorderBottom((short) 1); // single line border
        
        XSSFFont font = sheet.getWorkbook().createFont();
        font.setFontHeightInPoints((short) 15);
        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        
        style.setFont(font);                 

        XSSFRow row = sheet.getRow(rowIndex);   
        XSSFCell cell = row.getCell(colIndex);
        
        cell.setCellStyle(style);
        
        //for(int j = 0; j<=3; j++)
        //row.getCell(j).setCellStyle(style);
	}
	
	

	public static void setCellStyleBold(XSSFSheet sheet, int rowIndex, int colIndex) {
		
		XSSFCellStyle style = sheet.getWorkbook().createCellStyle();
        //style.setBorderTop((short) 6); // double lines border
        //style.setBorderBottom((short) 1); // single line border
        
        XSSFFont font = sheet.getWorkbook().createFont();
        //font.setFontHeightInPoints((short) 15);
        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        
        style.setFont(font);                 

        XSSFRow row = sheet.getRow(rowIndex);   
        XSSFCell cell = row.getCell(colIndex);
        
        cell.setCellStyle(style);
        
        //for(int j = 0; j<=3; j++)
        //row.getCell(j).setCellStyle(style);
	}
	
	
	
}
