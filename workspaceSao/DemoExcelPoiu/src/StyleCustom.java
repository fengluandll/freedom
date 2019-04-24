import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;


public class StyleCustom {
	
	
	public static CellStyle diaSemanaBlack(Workbook workbook){
		CellStyle style = workbook.createCellStyle();
        style.setFillBackgroundColor(IndexedColors.BLACK.getIndex());
        style.setFillPattern(XSSFCellStyle.BIG_SPOTS);
        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 14);
        font.setFontName("Arial");
        font.setColor(HSSFColor.WHITE.index);
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        style.setFont(font);
        style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        return style;
	}
	
	/**
	 * 
	 * @param workbook
	 * @param colorcelda en mayusculas el color a desear
	 * @return
	 */
	public static CellStyle colorCelda(Workbook workbook,String colorcelda){
		CellStyle style = workbook.createCellStyle();
		if(colorcelda.equals("AZUL")){
			style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
			style.setFillForegroundColor(IndexedColors.BLUE.getIndex());
		}else if(colorcelda.equals("BLANCO")){
			style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
			style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
		}else if(colorcelda.equals("ROJO")){
			style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
			style.setFillForegroundColor(IndexedColors.RED.getIndex());
		}else if(colorcelda.equals("AMARILLO")){
			style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
			style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		}else if(colorcelda.equals("NEGRO")){
			style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
			style.setFillForegroundColor(IndexedColors.BLACK.getIndex());
		}
        
        
        return style;
	}
	
	public static CellStyle letritasNegras(Workbook workbook,String alineacion,short tamanio){
		CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        if(tamanio == 8){
        	font.setFontHeightInPoints((short) 8);
        }else if(tamanio == 10){
        	font.setFontHeightInPoints((short) 10);
        }else if(tamanio == 12){
        	font.setFontHeightInPoints((short) 12);
        }
        
        font.setFontName("Arial");
        font.setColor(HSSFColor.BLACK.index);
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        style.setFont(font);
        
        if(alineacion.equals("DERECHA")){
        	style.setAlignment(XSSFCellStyle.ALIGN_RIGHT);
        }else if(alineacion.equals("JUSTIFICADO")){
        	style.setAlignment(XSSFCellStyle.ALIGN_JUSTIFY);
        }else if(alineacion.equals("IZQUIERDA")){
        	style.setAlignment(XSSFCellStyle.ALIGN_LEFT);
        }
        style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        return style;
	}
	
	public static CellStyle letraGde(Workbook workbook,String colorcelda){
		CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 14);
        font.setFontName("Arial");
        font.setColor(HSSFColor.WHITE.index);
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        style.setFont(font);
        style.setAlignment(XSSFCellStyle.ALIGN_JUSTIFY);
        style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        if(colorcelda.equals("ROJO")){
        	style.setFillForegroundColor(IndexedColors.RED.getIndex());
        }else if(colorcelda.equals("AZUL")){
        	style.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        }		
        style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        return style;
	}

}
