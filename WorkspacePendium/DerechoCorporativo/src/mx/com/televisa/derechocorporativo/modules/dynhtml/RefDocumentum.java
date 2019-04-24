package mx.com.televisa.derechocorporativo.modules.dynhtml;

import mx.com.televisa.derechocorporativo.modules.flextabs.FlexColumn;

public class RefDocumentum {


	public static String getHref(FlexColumn flexColumn, String value, int tiIdFlexTable) {		
		StringBuilder sb = new StringBuilder();

		sb.append("<input type='text' id='" +tiIdFlexTable + "__" +flexColumn.COD_FLEX_COLUM + 
				  "' name='" + flexColumn.COD_FLEX_COLUM +"' value='"+value+"' size='"+flexColumn.CAN_TAMANN_COLUM
				  //+"' maxlength='"+flexColumn.CAN_TAMANN_COLUM
				  +"' "
				  +flexColumn.ATRIBUTO2+">");

		sb.append("<a href='" + flexColumn.DES_FORMULA + "'><abbr title='Obtener Documento'>"+
                  "<img alt='Obtener Documento' src='"+flexColumn.ATRIBUTO1+"'></abbr> </a> ");
		
		sb.append("<input type='hidden' name='idMetaRow_"+tiIdFlexTable+"' id='"+tiIdFlexTable+"__"+flexColumn.COD_FLEX_COLUM+"' value='"+flexColumn.COD_FLEX_COLUM+"'>");
		
		return sb.toString();
	}

	
	public static String getHrefConsulta(FlexColumn flexColumn, String value, int tiIdFlexTable) {		
		StringBuilder sb = new StringBuilder();

		sb.append(value);
		
		sb.append("<input type='hidden' id='" +tiIdFlexTable + "__" +flexColumn.COD_FLEX_COLUM + 
				  "' name='" + flexColumn.COD_FLEX_COLUM +"' value='"+value+"' size='"+flexColumn.CAN_TAMANN_COLUM+
				  "' maxlength='"+flexColumn.CAN_TAMANN_COLUM+"' "+flexColumn.ATRIBUTO2+">");

		sb.append("<a href='" + flexColumn.DES_FORMULA + "'><abbr title='Obtener Documento'>"+
                  "<img alt='Obtener Documento' src='"+flexColumn.ATRIBUTO1+"'></abbr> </a> ");
		
		sb.append("<input type='hidden' name='idMetaRow_"+tiIdFlexTable+"' id='"+tiIdFlexTable+"__"+flexColumn.COD_FLEX_COLUM+"' value='"+flexColumn.COD_FLEX_COLUM+"'>");
		
		return sb.toString();
	}

}
