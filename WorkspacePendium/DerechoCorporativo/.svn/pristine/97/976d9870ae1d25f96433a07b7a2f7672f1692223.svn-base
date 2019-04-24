package mx.com.televisa.derechocorporativo.modules.dynhtml;

import mx.com.televisa.derechocorporativo.modules.flextabs.FlexColumn;

public class TextArea {

	
	/**
	 * 
	 */
	public static String getTextArea(FlexColumn flexColumn, String value, int tiIdFlexTable) {
		if(tiIdFlexTable==23){
			return "<pre class='wrapForPre' style='font-size: 11px; font-family:Verdana, Arial, Helvetica, sans-serif'><textarea name='" + flexColumn.COD_FLEX_COLUM +"' id='" + tiIdFlexTable + "__" + flexColumn.COD_FLEX_COLUM + "' cols='" + flexColumn.CAN_TAMANN_COLUM + "'" + flexColumn.DES_FORMULA + " rows='1' >" + value + "</textarea></pre>";
		}else{
			return "<textarea name='" + flexColumn.COD_FLEX_COLUM +"' id='" + tiIdFlexTable + "__" + flexColumn.COD_FLEX_COLUM + "' cols='" + flexColumn.CAN_TAMANN_COLUM + "'" + flexColumn.DES_FORMULA + " rows='1' >" + value + "</textarea>";
		}
		//return "<textarea name='" + this.COD_CAMPO + "' cols='" + this.CAN_TAMANNO_CAMPO + "' rows='5' >";
	}

	
	
}
