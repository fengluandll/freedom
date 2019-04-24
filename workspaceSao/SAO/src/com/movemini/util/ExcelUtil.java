package com.movemini.util;

import java.io.FileInputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

	
	
	public static void autoSizeColumns(Workbook workbook) {
	    int numberOfSheets = workbook.getNumberOfSheets();
	    for (int i = 0; i < numberOfSheets; i++) {
	        Sheet sheet = workbook.getSheetAt(i);
	        if (sheet.getPhysicalNumberOfRows() > 0) {
	            Row row = sheet.getRow(0);
	            Iterator<Cell> cellIterator = row.cellIterator();
	            while (cellIterator.hasNext()) {
	                Cell cell = cellIterator.next();
	                int columnIndex = cell.getColumnIndex();
	                sheet.autoSizeColumn(columnIndex);
	            }
	        }
	    }
	}
	
	
	
	
	/**
	 * Establece el valor en String de la celda ubicandola por su posici�n 
	 */
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
	
	/**
	 * Establece una celda como tipo titulo (Negritas) 
	 */
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
	
	
	/**
	 * Establece una celda como tipo titulo (Negritas) - detallar
	 */
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
	
	
	
	
	/**
	 * Obtiene el valor de una celda por su clave de Excel Ej. A2
	 */
	public static String getCellValue(Sheet sheet, String cellCode) {
		
		try {
			   
	        Cell cell = getCell(sheet, cellCode);
	        
	        return cell.getStringCellValue();
	        
		}catch(Exception ex) {
			
			//ex.printStackTrace();
			return "";
		}
	}

	
	/**
	 * Establece el valor de una celda por su clave de Excel Ej. A2
	 */
	public static void setCellValue(XSSFSheet sheet, String cellCode, String value) {
		
		try {
			   
	        Cell cell = getCell(sheet, cellCode);
	        
	        cell.setCellValue(value);
	        
		}catch(Exception ex) {
			
			//ex.printStackTrace();
			//return "";
		}
	}

	

	public static double getNumericCellValue(Sheet sheet, String cellCode) {
		
		try {
			   
	        Cell cell = getCell(sheet, cellCode);
	        
	        return cell.getNumericCellValue();
	        
		}catch(Exception ex) {
			
			//ex.printStackTrace();
			return 0;
		}
	}
	
	
	public static Date getDateCellValue(Sheet sheet, String cellCode) {
		
		try {
			   
	        Cell cell = getCell(sheet, cellCode);
	        
	        return cell.getDateCellValue();
	        
		}catch(Exception ex) {
			
			//ex.printStackTrace();
			return null;
		}
	}
	
	

	private static Cell getCell(Sheet sheet, String cellCode) {
		
		try {
			//int index = getCellColCodeIndex(cellCode);
			
			/*
			String colCode = cellCode.substring(0, index);
			String rowCode = cellCode.substring(index);
			int intRowCode = Integer.parseInt(rowCode);
			
			String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			Row row = sheet.getRow(abc.indexOf(colCode));   
	        Cell cell = row.getCell(intRowCode);
	        
			*/
			
			String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			int intColIndex = abc.indexOf(cellCode.charAt(0));;
			
			String strColIndex = cellCode.substring(1);
			int intRowIndex = Integer.parseInt(strColIndex);
			
			Row row = sheet.getRow(intRowIndex - 1);   
	        Cell cell = row.getCell(intColIndex);
	        
			return cell;
			
		}catch(Exception ex) {
			
			//ex.printStackTrace();
			return null;
		}
	}
	/*
	private static int getCellColCodeIndex(String cellCode) {
		
		String colPart = ""; 
		int index = 0;
		do {
			colPart = cellCode.charAt(0) + "";
			
			if(!NumbersUtil.isNumeric(colPart)) {
			
				index++;
			} else {
				
				return index;
			}
			
		} while(index < cellCode.length());
		
		return 0;
	}*/
	
	
	/*
	public List readDrawing(Workbook workbook) throws Exception {
		
	    List<> list = workbook.getAllPictures();

	    for (int i=0; i<list.size(); i++) {
	        PictureData picture = (PictureData) list.get(i);

	        String ext = picture.suggestFileExtension();
	        byte[] data = picture.getData();

	        FileOutputStream out = new FileOutputStream("imageName" + "." + ext);
	        out.write(data);
	        out.close();
	    }

	    return list;
	}
	*/

	/**
	 * 
	 */
	/*public static void deleteColumn( Sheet sheet, int columnToDelete ){
			
		Iterator rowIter = sheet.iterator();
		
		while (rowIter.hasNext()) {
			
		   Row row = (Row) rowIter.next();
		   
		   Cell cell = row.getCell(columnToDelete);
		   
		   row.removeCell(cell);
		}

	}*/
	
    /**
     * Given a sheet, this method deletes a column from a sheet and moves
     * all the columns to the right of it to the left one cell.
     * 
     * Note, this method will not update any formula references.
     * 
     * @param sheet
     * @param column
     */
    /*public static void deleteColumn( Sheet sheet, int columnToDelete ){
        int maxColumn = 0;
        for ( int r=0; r < sheet.getLastRowNum()+1; r++ ){
            Row row = sheet.getRow( r );

            // if no row exists here; then nothing to do; next!
            if ( row == null )
                continue;

            // if the row doesn't have this many columns then we are good; next!
            int lastColumn = row.getLastCellNum();
            if ( lastColumn > maxColumn )
                maxColumn = lastColumn;

            if ( lastColumn < columnToDelete )
                continue;

            for ( int x=columnToDelete+1; x < lastColumn + 1; x++ ){
                Cell oldCell    = row.getCell(x-1);
                if ( oldCell != null )
                    row.removeCell( oldCell );

                Cell nextCell   = row.getCell( x );
                if ( nextCell != null ){
                    Cell newCell    = row.createCell( x-1, nextCell.getCellType() );
                    cloneCell(newCell, nextCell);
                }
            }
        }


        // Adjust the column widths
        for ( int c=0; c < maxColumn; c++ ){
            sheet.setColumnWidth( c, sheet.getColumnWidth(c+1) );
        }
    }
*/

    /*
     * Takes an existing Cell and merges all the styles and forumla
     * into the new one
     */
    /*private static void cloneCell( Cell cNew, Cell cOld ){
        cNew.setCellComment( cOld.getCellComment() );
        cNew.setCellStyle( cOld.getCellStyle() );

        switch ( cNew.getCellType() ){
            case Cell.CELL_TYPE_BOOLEAN:{
                cNew.setCellValue( cOld.getBooleanCellValue() );
                break;
            }
            case Cell.CELL_TYPE_NUMERIC:{
                cNew.setCellValue( cOld.getNumericCellValue() );
                break;
            }
            case Cell.CELL_TYPE_STRING:{
                cNew.setCellValue( cOld.getStringCellValue() );
                break;
            }
            case Cell.CELL_TYPE_ERROR:{
                cNew.setCellValue( cOld.getErrorCellValue() );
                break;
            }
            case Cell.CELL_TYPE_FORMULA:{
                cNew.setCellFormula( cOld.getCellFormula() );
                break;
            }
        }

    }
    */
    
    
    
	 /**
	 * Remove a row by its index
	 * @param sheet a Excel sheet
	 * @param rowIndex a 0 based index of removing row
	 */
	/*public static void removeRow(Sheet sheet, int rowIndex) {
	    int lastRowNum=sheet.getLastRowNum();
	    if(rowIndex>=0&&rowIndex<lastRowNum){
	        sheet.shiftRows(rowIndex+1,lastRowNum, -1);
	    }
	    if(rowIndex==lastRowNum){
	        Row removingRow=sheet.getRow(rowIndex);
	        if(removingRow!=null){
	            sheet.removeRow(removingRow);
	        }
	    }
	}*/
	

	 
	 
	
}
