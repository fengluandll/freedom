package mx.gob.tsjdf.cfdi.recibos.reporte;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;


import mx.gob.tsjdf.cfdi.dto.EmpleadoTokenDto;
import mx.gob.tsjdf.cfdi.dto.ErrorDatoEmpleadoDto;
import mx.gob.tsjdf.cfdi.dto.ErroresDatosEmpleadosDto;
import mx.gob.tsjdf.cfdi.dto.ReciboStatusDto;

public class ReportesExcel
{
	private static org.apache.log4j.Logger logger = Logger.getLogger(ReportesExcel.class);
	
  public String creaRelacionEmpleadoToken(String pathReporte, ArrayList<EmpleadoTokenDto> listaETA, String fechaPaga)
  {
    String nombreExcel = "";
    try
    {
      HSSFWorkbook workbook = new HSSFWorkbook();
      

      HSSFSheet sheet = workbook.createSheet("empleado-token-archivo");
      

      HSSFFont fontTitulo = workbook.createFont();
      fontTitulo.setBoldweight((short)700);
      HSSFCellStyle styleTitulo = workbook.createCellStyle();
      styleTitulo.setFont(fontTitulo);
      styleTitulo.setAlignment((short)2);
      

      HSSFFont fontEncabezado = workbook.createFont();
      fontEncabezado.setBoldweight((short)700);
      HSSFCellStyle styleEncabezado = workbook.createCellStyle();
      styleEncabezado.setFont(fontEncabezado);
      

      HSSFRow row0 = sheet.createRow(0);
      

      HSSFCell cell00 = row0.createCell(0);
      cell00.setCellValue("Relacion empleado-token-archivo para fecha de paga: " + fechaPaga);
      cell00.setCellStyle(styleTitulo);
      
      sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));
      

      HSSFRow row1 = sheet.createRow(1);
      

      HSSFCell cell10 = row1.createCell(0);
      cell10.setCellValue("Numero de Empleado");
      cell10.setCellStyle(styleEncabezado);
      HSSFCell cell11 = row1.createCell(1);
      cell11.setCellValue("Token");
      cell11.setCellStyle(styleEncabezado);
      HSSFCell cell12 = row1.createCell(2);
      cell12.setCellValue("Archivo");
      cell12.setCellStyle(styleEncabezado);
      

      int contRow = 2;
      for (EmpleadoTokenDto dto : listaETA)
      {
        HSSFRow row = sheet.createRow(contRow++);
        

        HSSFCell cell1 = row.createCell(0);
        HSSFCell cell2 = row.createCell(1);
        HSSFCell cell3 = row.createCell(2);
        

        cell1.setCellValue(dto.getNumeroEmpleado());
        cell2.setCellValue(dto.getToken());
        cell3.setCellValue(dto.getArchivo());
      }
      for (int columnPosition = 0; columnPosition < 3; columnPosition++) {
        sheet.autoSizeColumn((short)columnPosition);
      }
      FileOutputStream file = new FileOutputStream(pathReporte);
      workbook.write(file);
      

      file.close();
      if (pathReporte.contains("/")) {
        nombreExcel = pathReporte.substring(pathReporte.lastIndexOf("/") + 1, pathReporte.length());
      }
    }
    catch (Exception e)
    {
      System.out.println("Ha ocurrido un error al escribir el archivo " + pathReporte);
      System.out.println("Informacion para el programador: ");
      e.printStackTrace();
    }
    return nombreExcel;
  }
  
  public String creaRelacionTxtNoValidos(String pathReporte, 
		  								 ArrayList<ErroresDatosEmpleadosDto> errores,
		  								 String fechaPaga,
		  								 ArrayList<ReciboStatusDto> listaStatus,
		  								 String descripcion)
  {
    String nombreExcel = "";
    logger.info("pathReporte Validacion "+pathReporte);
    
    try
    {
      HSSFWorkbook workbook = new HSSFWorkbook();
      

      HSSFSheet sheet = workbook.createSheet("errores_validacion");
      

      HSSFFont fontTitulo = workbook.createFont();
      fontTitulo.setBoldweight((short)700);
      HSSFCellStyle styleTitulo = workbook.createCellStyle();
      styleTitulo.setFont(fontTitulo);
      styleTitulo.setAlignment((short)2);
      

      HSSFFont fontEncabezado = workbook.createFont();
      fontEncabezado.setBoldweight((short)700);
      HSSFCellStyle styleEncabezado = workbook.createCellStyle();
      styleEncabezado.setFont(fontEncabezado);
      

      HSSFRow row0 = sheet.createRow(0);
      

      HSSFCell cell00 = row0.createCell(0);
      cell00.setCellValue("Empleados con datos no validos para la fecha de paga : " + fechaPaga);
      cell00.setCellStyle(styleTitulo);
      
      sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));
      

      HSSFRow row1 = sheet.createRow(1);
      

      HSSFCell cell10 = row1.createCell(0);
      cell10.setCellValue("Fecha de paga");
      cell10.setCellStyle(styleEncabezado);
      HSSFCell cell11 = row1.createCell(1);
      cell11.setCellValue("Numero de Empleado");
      cell11.setCellStyle(styleEncabezado);
      HSSFCell cell12 = row1.createCell(2);
      cell12.setCellValue("Periodo");
      cell12.setCellStyle(styleEncabezado);
      HSSFCell cell13 = row1.createCell(3);
      cell13.setCellValue("Dato no valido");
      cell13.setCellStyle(styleEncabezado);
      HSSFCell cell14 = row1.createCell(4);
      cell14.setCellValue("Campo");
      cell14.setCellStyle(styleEncabezado);
      HSSFCell cell15 = row1.createCell(5);
      cell15.setCellValue("Mensaje de error");
      cell15.setCellStyle(styleEncabezado);
      

      int contRow = 2;
      Iterator localIterator2;
      HSSFCell cell4;
      for (Iterator localIterator1 = errores.iterator(); localIterator1.hasNext(); localIterator2.hasNext())
      {
        ErroresDatosEmpleadosDto dto = (ErroresDatosEmpleadosDto)localIterator1.next();
        

        localIterator2 = dto.getErrores().iterator(); 
        ErrorDatoEmpleadoDto error = (ErrorDatoEmpleadoDto)localIterator2.next();
        
        HSSFRow row = sheet.createRow(contRow++);
        

        HSSFCell cell1 = row.createCell(0);
        HSSFCell cell2 = row.createCell(1);
        HSSFCell cell3 = row.createCell(2);
        cell4 = row.createCell(3);
        HSSFCell cell5 = row.createCell(4);
        HSSFCell cell6 = row.createCell(5);
        

        cell1.setCellValue(fechaPaga);
        cell2.setCellValue(dto.getNumeroEmpleado());
        if (dto.getPeriodo() > 0) {
          cell3.setCellValue(dto.getPeriodo());
        }
        cell4.setCellValue(error.getDato());
        cell5.setCellValue(error.getCampo());
        cell6.setCellValue(error.getError());
      }
      if (listaStatus.size() > 0)
      {
        HSSFSheet sheetL = workbook.createSheet("lista_estatus");
        
        HSSFRow rowL0 = sheetL.createRow(0);
        
        HSSFCell cellL10 = rowL0.createCell(0);
        cellL10.setCellValue("Fecha de paga");
        cellL10.setCellStyle(styleEncabezado);
        HSSFCell cellL11 = rowL0.createCell(1);
        cellL11.setCellValue("Numero de empleado");
        cellL11.setCellStyle(styleEncabezado);
        HSSFCell cellL12 = rowL0.createCell(2);
        cellL12.setCellValue("Periodo");
        cellL12.setCellStyle(styleEncabezado);
        HSSFCell cellL13 = rowL0.createCell(3);
        cellL13.setCellValue("Estatus");
        cellL13.setCellStyle(styleEncabezado);
        
        int contRowL = 1;
        for (ReciboStatusDto recibo : listaStatus)
        {
          HSSFRow rowL = sheetL.createRow(contRowL++);
          
          HSSFCell cell1 = rowL.createCell(0);
          HSSFCell cell2 = rowL.createCell(1);
          HSSFCell cell3 = rowL.createCell(2);
           cell4 = rowL.createCell(3);
          
          cell1.setCellValue(fechaPaga);
          cell2.setCellValue(recibo.getNumeroEmpleado());
          cell3.setCellValue(recibo.getPeriodo());
          cell4.setCellValue(recibo.getStatus());
        }
        for (int columnPosition = 0; columnPosition < 3; columnPosition++) {
          sheetL.autoSizeColumn((short)columnPosition);
        }
      }
      for (int columnPosition = 0; columnPosition < 6; columnPosition++) {
        sheet.autoSizeColumn((short)columnPosition);
      }
      FileOutputStream file = new FileOutputStream(pathReporte);
      workbook.write(file);
      

      file.close();
      if (pathReporte.contains("/")) {
        nombreExcel = descripcion + "_" + pathReporte.substring(pathReporte.lastIndexOf("/") + 1, pathReporte.length());
      }
    }
    catch (Exception e)
    {
      System.out.println("Ha ocurrido un error al escribir el archivo " + pathReporte);
      System.out.println("Informacion para el programador: ");
      e.printStackTrace();
    }
    return nombreExcel;
  }
}
