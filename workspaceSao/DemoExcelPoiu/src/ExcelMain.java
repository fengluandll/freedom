import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelMain {
	
	private final String FILE_NAME = "C:\\Users\\argumedo\\Documents\\ProyectoNava\\AGENDA2018.xlsx";
	Workbook    workbook;
	Sheet 	 	sheet;
	CellStyle 	style;
	Font 	font;
	int firstColumn	=	0;
	int lastColumn	=	0;

	public ExcelMain() {
		
//		workbook 	= new XSSFWorkbook();
//        sheet 		= workbook.createSheet("Abril");
//        Calendar now = Calendar.getInstance();
//        String[] strDays = new String[]{
//				"Domingo",
//				"Lunes",
//				"Martes",
//				"Miercoles",
//				"Jueves",
//				"Viernes",
//				"Sabado"};
//        System.out.println(now.get(Calendar.DAY_OF_WEEK));
//        System.out.println(now.get(Calendar.MONDAY));
//        System.out.println("Hoy es : " + strDays[7 - 1]);
//        
//        firstColumn	=	1;
//    	lastColumn	=	5;
//        sheet.addMergedRegion(new CellRangeAddress(0,0,firstColumn,lastColumn));//first row,last row(hacia abajo),first column,last column
//        Row row = sheet.createRow(0);
//        Cell cell = row.createCell(1);
//        cell.setCellValue("Lunes 2 de abril");
//        cell.setCellStyle(StyleCustom.diaSemanaBlack(workbook));
//        
//        //Seccion 2da fila color azul memo
//        Row rowMemo = sheet.createRow(1);
//        sheet.setColumnWidth(1, 1000);
//        cell = rowMemo.createCell(1);
//        cell.setCellStyle(StyleCustom.colorCelda(workbook, "AZUL"));
//        Cell cellMemo = rowMemo.createCell(2);
//        sheet.setColumnWidth(2, 2820);
//        rowMemo.setHeight((short) 360);
//        cellMemo.setCellValue("Memo");
//        cellMemo.setCellStyle(StyleCustom.letritasNegras(workbook,"JUSTIFICADO",(short)8));
//        
//      //Seccion 2da fila color blanco s/memo
//        Row rowsMemo = sheet.createRow(2);
//        sheet.setColumnWidth(1, 1000);
//        cell = rowsMemo.createCell(1);
//        //cell.setCellStyle(StyleCustom.colorCelda(workbook, "BLANCO"));
//        Cell cellsMemo = rowsMemo.createCell(2);
//        sheet.setColumnWidth(2, 2820);
//        rowsMemo.setHeight((short) 360);
//        cellsMemo.setCellValue("S/Memo");
//        cellsMemo.setCellStyle(StyleCustom.letritasNegras(workbook,"JUSTIFICADO",(short)8));
//        
//        //Seccion 3da fila color rojo cancelado
//        Row rowsCancelado = sheet.createRow(3);
//        sheet.setColumnWidth(1, 1000);
//        cell = rowsCancelado.createCell(1);
//        cell.setCellStyle(StyleCustom.colorCelda(workbook, "ROJO"));
//        Cell cellCancelado = rowsCancelado.createCell(2);
//        sheet.setColumnWidth(2, 2820);
//        rowsCancelado.setHeight((short) 360);
//        cellCancelado.setCellValue("Cancelado");
//        cellCancelado.setCellStyle(StyleCustom.letritasNegras(workbook,"JUSTIFICADO",(short)8));
//        
//        //Seccion 4da fila color rojo cancelado
//        Row rowsPenalizacion = sheet.createRow(4);
//        sheet.setColumnWidth(1, 1000);
//        cell = rowsPenalizacion.createCell(1);
//        cell.setCellStyle(StyleCustom.colorCelda(workbook, "AMARILLO"));
//        Cell cellPenalizacion = rowsPenalizacion.createCell(2);
//        sheet.setColumnWidth(2, 2820);
//        rowsPenalizacion.setHeight((short) 360);
//        cellPenalizacion.setCellValue("Penalización");
//        cellPenalizacion.setCellStyle(StyleCustom.letritasNegras(workbook,"JUSTIFICADO",(short)8));
//        
//        //Seccion 5a fila color negro toda la fila
//        Row rowsBarraNegra1 = sheet.createRow(5);
//        cell = rowsBarraNegra1.createCell(1);
//        sheet.addMergedRegion(new CellRangeAddress(5,5,firstColumn,lastColumn));
//        cell.setCellStyle(StyleCustom.colorCelda(workbook, "NEGRO"));
//        rowsBarraNegra1.setHeight((short) 240);
//        
//        //Seccion 6a fila G EN ROJO
//        Row rowsLetraGde = sheet.createRow(6);
//        cell = rowsLetraGde.createCell(1);
//        rowsLetraGde.setHeight((short) 460);
//        cell.setCellValue("G");
//        cell.setCellStyle(StyleCustom.letraGde(workbook,"ROJO"));
//        Cell cellCotiz = rowsLetraGde.createCell(2);
//        cellCotiz.setCellValue("Cotización:");
//        cellCotiz.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8));
//        Cell cellCotizVal = rowsLetraGde.createCell(3);
//        cellCotizVal.setCellValue("0486-18");
//        cellCotizVal.setCellStyle(StyleCustom.letritasNegras(workbook,"IZQUIERDA",(short)10));
//        Cell cellFac = rowsLetraGde.createCell(4);
//        sheet.setColumnWidth(3, 5050);
//        cellFac.setCellValue("Factura(s):");
//        cellFac.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8));
//        Cell cellFacVal = rowsLetraGde.createCell(5);
//        cellFacVal.setCellValue("5794");
//        cellFacVal.setCellStyle(StyleCustom.letritasNegras(workbook,"IZQUIERDA",(short)10));
//        
//        
//        //Seccion 7a fila color negro toda la fila
//        Row rowsCliente = sheet.createRow(7);
//        Cell cellCliente = rowsCliente.createCell(1);
//        cellCliente.setCellValue("Cliente:");
//        cellCliente.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8));
//        sheet.addMergedRegion(new CellRangeAddress(7,7,firstColumn,lastColumn - 3));
//        rowsCliente.setHeight((short) 510);//25.5
//        Cell cellClienteVal = rowsCliente.createCell(3);
//        cellClienteVal.setCellValue("GRUPO DESTINOS");
//        cellClienteVal.setCellStyle(StyleCustom.letritasNegras(workbook,"IZQUIERDA",(short)10));
//        Cell cellSede = rowsCliente.createCell(4);
//        cellSede.setCellValue("Sede:");
//        cellSede.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8));
//        Cell cellSedeVal = rowsCliente.createCell(5);
//        cellSedeVal.setCellValue("CDMX");
//        cellSedeVal.setCellStyle(StyleCustom.letritasNegras(workbook,"IZQUIERDA",(short)10));
//        
//      //Seccion 8a fila color negro toda la fila
//        Row rowsCantEquipo = sheet.createRow(8);
//        Cell cellCantEquipo = rowsCantEquipo.createCell(1);
//        cellCantEquipo.setCellValue("Cantidad Equipo:");
//        sheet.addMergedRegion(new CellRangeAddress(8,8,firstColumn,lastColumn - 3));
//        cellCantEquipo.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8));
//        
//        Cell cellCantEquipoVal = rowsCantEquipo.createCell(3);
//        sheet.addMergedRegion(new CellRangeAddress(8,8,firstColumn + 2,lastColumn));
//        cellCantEquipoVal.setCellValue("40W, 1CAB, 1TEC");
//        cellCantEquipoVal.setCellStyle(StyleCustom.letritasNegras(workbook,"IZQUIERDA",(short)10));
//        
//      //Seccion 9a fila color negro toda la fila
//        Row rowsInterprete = sheet.createRow(9);
//        Cell cellInterprete = rowsInterprete.createCell(1);
//        cellInterprete.setCellValue("Intérprete(s):");
//        sheet.addMergedRegion(new CellRangeAddress(9,9,firstColumn,lastColumn - 3));
//        cellInterprete.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8));
//        
//        Cell cellInterpreteVal = rowsInterprete.createCell(3);
//        sheet.addMergedRegion(new CellRangeAddress(9,9,firstColumn + 2,lastColumn));
//        cellInterpreteVal.setCellValue("GABRIELA DURAZO");
//        cellInterpreteVal.setCellStyle(StyleCustom.letritasNegras(workbook,"IZQUIERDA",(short)10));
//        
//      //Seccion 10a fila 
//        Row rowsTecnicos = sheet.createRow(10);
//        Cell celltecnico = rowsTecnicos.createCell(1);
//        celltecnico.setCellValue("Técnico(s):");
//        sheet.addMergedRegion(new CellRangeAddress(10,10,firstColumn,lastColumn - 3));
//        celltecnico.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8));
//        
//        Cell cellTecnicoVal = rowsTecnicos.createCell(3);
//        sheet.addMergedRegion(new CellRangeAddress(10,10,firstColumn + 2,lastColumn));
//        cellTecnicoVal.setCellValue("");
//        cellTecnicoVal.setCellStyle(StyleCustom.letritasNegras(workbook,"IZQUIERDA",(short)10));
//        
//      //Seccion 11a fila 
//        Row rowsHorarios = sheet.createRow(11);
//        Cell cellHorario = rowsHorarios.createCell(1);
//        cellHorario.setCellValue("Horario:");
//        sheet.addMergedRegion(new CellRangeAddress(11,11,firstColumn,lastColumn - 3));
//        cellHorario.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8));
//        
//        Cell cellHorarioVal = rowsHorarios.createCell(3);
//        //sheet.addMergedRegion(new CellRangeAddress(11,11,firstColumn + 2,lastColumn - 1));
//        cellHorarioVal.setCellValue("7:30 - 9:30 HRS");
//        cellHorarioVal.setCellStyle(StyleCustom.letritasNegras(workbook,"IZQUIERDA",(short)8));
//        
//        Cell cellTema = rowsHorarios.createCell(4);
//        //sheet.addMergedRegion(new CellRangeAddress(11,11,firstColumn + 2,lastColumn - 1));
//        cellTema.setCellValue("Tema:");
//        cellTema.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8));
//        rowsHorarios.setHeight((short) 1000);
//        
//        Cell cellTemaVal = rowsHorarios.createCell(5);
//        //sheet.addMergedRegion(new CellRangeAddress(11,11,firstColumn + 2,lastColumn - 1));
//        cellTemaVal.setCellValue("");
//        cellTemaVal.setCellStyle(StyleCustom.letritasNegras(workbook,"IZQUIERDA",(short)10));
//        
//        
//        
//        
//        
//        
//        
//        
//        //Segundo bloque. siguiente dia
//        firstColumn	=	lastColumn + firstColumn;
//    	lastColumn	=	lastColumn + 5;
//        sheet.addMergedRegion(new CellRangeAddress(0,0,firstColumn,lastColumn));
//        //Row row2 = sheet.createRow(0);
//        Cell cell2 = row.createCell(firstColumn);
//        cell2.setCellValue("Martes 3 de abril");
//        cell2.setCellStyle(StyleCustom.diaSemanaBlack(workbook));
//        
//        
//        
//        try {
//            FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
//            workbook.write(outputStream);
//            outputStream.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        System.out.println("Done");
        List<String>  rangoMeses = getMonthForInt(1,12);
        for(String m : rangoMeses){
        	System.out.println(m);
        }
		
	}

	 public List<String> getMonthForInt(int m1,int m2) {
	        
	        DateFormatSymbols dfs = new DateFormatSymbols();
	        
	        List<String> listRangoMeses = new ArrayList<String>();
	        for(int i = m1-1; i < m2;i ++){
	            //months  = dfs.getMonths(); 
	            listRangoMeses.add(dfs.getMonths()[i]);
	            //System.out.println("mes "+months[i]);
	        }
	        return listRangoMeses;
	        }
	 
	public static void main(String[] args) {
		
			new ExcelMain();
	}

}
