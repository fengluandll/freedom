package mx.gob.tsjdf.cfdi.recibos.reporte;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import mx.gob.tsjdf.cfdi.dto.EmpleadoTokenDto;
import mx.gob.tsjdf.cfdi.dto.ErrorDto;
import mx.gob.tsjdf.cfdi.service.ArchivoService;

public class ReportesPdf
{
  public static final Font BOLD = new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 1);
  
  public void creaReporteErroresPdf(String pathReporte, HashMap<String, ErrorDto> hmapErrores, Date tiempoEjecucion)
  {
    try
    {
      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss");
      
      Document document = new Document();
      Font fuente = new Font();
      fuente.setSize(24.0F);
      PdfWriter.getInstance(document, new FileOutputStream(new File(pathReporte)));
      
      document.open();
      Paragraph titulo = new Paragraph("Reporte de Errores - " + sdf.format(tiempoEjecucion), fuente);
      titulo.setAlignment(1);
      document.add(titulo);
      document.add(new Paragraph(" "));
      

      PdfPTable tabla = new PdfPTable(3);
      tabla.setWidths(new int[] { 15, 65, 20 });
      tabla.setWidthPercentage(100.0F);
      tabla.addCell(new Paragraph("Tipo Error", BOLD));
      tabla.addCell(new Paragraph("Descripcion del Error", BOLD));
      tabla.addCell(new Paragraph("Total de Archivos", BOLD));
      
      PdfPTable tablaArchivos = new PdfPTable(2);
      tablaArchivos.setWidths(new int[] { 20, 80 });
      tablaArchivos.setWidthPercentage(100.0F);
      

      int numeroError = 0;
      Iterator<String> it = hmapErrores.keySet().iterator();
      while (it.hasNext())
      {
        String key = (String)it.next();
        

        ((ErrorDto)hmapErrores.get(key)).setNumeroError(++numeroError);
        
        PdfPCell cell1 = new PdfPCell(new Paragraph(Integer.toString(((ErrorDto)hmapErrores.get(key)).getNumeroError())));
        cell1.setHorizontalAlignment(1);
        cell1.setVerticalAlignment(5);
        tabla.addCell(cell1);
        
        PdfPCell cell2 = new PdfPCell(new Paragraph(((ErrorDto)hmapErrores.get(key)).getErrorGral()));
        cell2.setHorizontalAlignment(0);
        cell2.setVerticalAlignment(5);
        tabla.addCell(cell2);
        
        PdfPCell cell3 = new PdfPCell(new Paragraph(Integer.toString(((ErrorDto)hmapErrores.get(key)).getListaArchivos().size())));
        cell3.setHorizontalAlignment(1);
        cell3.setVerticalAlignment(5);
        tabla.addCell(cell3);
        



        tablaArchivos.addCell(new Paragraph("Tipo Error", BOLD));
        tablaArchivos.addCell(new Paragraph("Descripcion", BOLD));
        
        PdfPCell cellA1 = new PdfPCell(new Paragraph(Integer.toString(((ErrorDto)hmapErrores.get(key)).getNumeroError())));
        cellA1.setHorizontalAlignment(1);
        cellA1.setVerticalAlignment(5);
        tablaArchivos.addCell(cellA1);
        
        PdfPCell cellDescError = new PdfPCell(new Paragraph(((ErrorDto)hmapErrores.get(key)).getErrorGral()));
        cellDescError.setVerticalAlignment(5);
        tablaArchivos.addCell(cellDescError);
        
        tablaArchivos.addCell(new Paragraph("Num. de Archivo"));
        tablaArchivos.addCell(new Paragraph("Nombre del Archivo"));
        


        int numArchivo = 0;
        for (String archivo : ((ErrorDto)hmapErrores.get(key)).getListaArchivos())
        {
          PdfPCell cellNumError = new PdfPCell(new Paragraph(Integer.toString(++numArchivo)));
          cellNumError.setHorizontalAlignment(1);
          cellNumError.setVerticalAlignment(5);
          tablaArchivos.addCell(cellNumError);
          tablaArchivos.addCell(archivo);
        }
        PdfPCell cellVacia = new PdfPCell(new Paragraph(" "));
        cellVacia.setVerticalAlignment(5);
        cellVacia.setColspan(2);
        cellVacia.setBorder(2);
        cellVacia.setBorder(8);
        cellVacia.setBackgroundColor(BaseColor.WHITE);
        tablaArchivos.addCell(cellVacia);
      }
      document.add(tabla);
      document.add(new Paragraph(" "));
      document.add(tablaArchivos);
      document.close();
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    }
    catch (DocumentException e)
    {
      e.printStackTrace();
    }
  }
  
  public String creaReportePdfRelacionETA(String pathReporte, ArrayList<EmpleadoTokenDto> listaETA, String fechaPago)
  {
    String nombrePdf = "";
    try
    {
      if (listaETA.size() > 0)
      {
        Document document = new Document();
        document.setPageSize(PageSize.LETTER.rotate());
        File file = new File(pathReporte);
        PdfWriter.getInstance(document, new FileOutputStream(new File(pathReporte)));
        document.open();
        

        Font fuente = new Font();
        fuente.setSize(16.0F);
        Paragraph titulo = new Paragraph("Relacion Empleado-Token-Archivo  " + fechaPago);
        titulo.setAlignment(1);
        document.add(titulo);
        document.add(new Paragraph(" "));
        

        PdfPTable tabla = new PdfPTable(3);
        tabla.setWidths(new int[] { 15, 35, 50 });
        tabla.setWidthPercentage(100.0F);
        tabla.addCell(new Paragraph("No. de Empleado", BOLD));
        tabla.addCell(new Paragraph("Token", BOLD));
        tabla.addCell(new Paragraph("Archivo", BOLD));
        for (EmpleadoTokenDto eta : listaETA)
        {
          tabla.addCell(eta.getNumeroEmpleado());
          tabla.addCell(eta.getToken());
          tabla.addCell(eta.getArchivo());
        }
        document.add(tabla);
        document.close();
        nombrePdf = file.getName();
      }
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    }
    catch (DocumentException e)
    {
      e.printStackTrace();
    }
    return nombrePdf;
  }
  
  public String creaLogPdf(String pathLog, String pathLogPdf)
  {
    String nombrePdf = "";
    try
    {
      ArchivoService as = new ArchivoService();
      Document document = new Document(PageSize.A3, 10.0F, 10.0F, 10.0F, 10.0F);
      document.setPageSize(PageSize.LETTER.rotate());
      
      File file = new File(pathLogPdf);
      PdfWriter.getInstance(document, new FileOutputStream(file));
      document.open();
      

      StringBuffer contenido = as.leerArchivoTxt(pathLog);
      String texto = contenido.toString().replace("\t", " ");
      

      document.add(new Paragraph(texto));
      
      document.close();
      nombrePdf = file.getName();
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    }
    catch (DocumentException e)
    {
      e.printStackTrace();
    }
    return nombrePdf;
  }
}
