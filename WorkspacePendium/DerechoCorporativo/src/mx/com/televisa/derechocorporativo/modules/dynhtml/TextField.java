package mx.com.televisa.derechocorporativo.modules.dynhtml;

import mx.com.televisa.derechocorporativo.modules.flextabs.FlexColumn;

public class TextField {

	/**
	 * 
	 */
	public static String getTextField(FlexColumn flexColumn, String value, int tiIdFlexTable) {
		
		//ECM 12 AGOSTO 2015		
		return "<input type='text' title ='"+ value +"' name='" + flexColumn.COD_FLEX_COLUM +"' id='" +tiIdFlexTable + "__" + flexColumn.COD_FLEX_COLUM + "' size='" + flexColumn.CAN_TAMANN_COLUM + "' value='" + value + "' "+flexColumn.DES_FORMULA+">";
		
		//return "<input type='text' name='" + flexColumn.COD_FLEX_COLUM +"' id='" + flexColumn.COD_FLEX_COLUM + "' size='" + flexColumn.CAN_TAMANN_COLUM + "' value='" + value + "'>";
		//return "<input type='text' name='" + this.COD_CAMPO + "' size='" + this.CAN_TAMANNO_CAMPO + "' value='" ;
	}
	

	public static String getTextFieldDisabled(FlexColumn flexColumn, String value, int tiIdFlexTable) {
		
		return "<input type='text' disabled='disabled' title ='"+ value +"' name='" + flexColumn.COD_FLEX_COLUM +"' id='" + tiIdFlexTable + "__" +flexColumn.COD_FLEX_COLUM + "' size='" + flexColumn.CAN_TAMANN_COLUM + "' value='" + value + "'>";
		//return "<input type='text' name='" + this.COD_CAMPO + "' size='" + this.CAN_TAMANNO_CAMPO + "' value='" ;
	}
	

	
}
