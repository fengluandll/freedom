package mx.com.televisa.derechocorporativo.modules.dynhtml;

import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;
import mx.com.televisa.derechocorporativo.modules.captura.Catalogo;
import mx.com.televisa.derechocorporativo.modules.captura.CatalogoValor;
import mx.com.televisa.derechocorporativo.modules.flextabs.FlexColumn;

public class Radio {

	

	/**
	 * 
	 */
	public static String getRadioButtonList(ConnectionWrapper connect, FlexColumn flexColumn, String value, int tiIdFlexTable) {

		StringBuilder sb = new StringBuilder();

		Catalogo cat = new Catalogo(flexColumn.ID_CATALOGO);

		for (CatalogoValor catElem : cat.getElementos(connect)) {

			sb.append("<label> ");
			sb.append("	<input type='radio'" +"' id='" + tiIdFlexTable + "__" + flexColumn.COD_FLEX_COLUM + "'");
			sb.append("		name='"+ tiIdFlexTable+"__"+ flexColumn.COD_FLEX_COLUM + "'");
			sb.append("         value='" + catElem.idCatalogoValor + "'" +" "+ "ondblclick='uncheckme(this)'" );
			
			 if (Integer.toString(catElem.idCatalogoValor).equals(value)){
				sb.append("checked");
			}
			
			sb.append(">");
			sb.append(catElem.nomCatVal);
			sb.append("</label>");

			if (flexColumn.DES_TIPO_COLUM.equals(DynamicField.TYPE_RADIO_V)) {
				sb.append("<br>");
			}

		}
		
		sb.append("<input type='radio' id ='" + tiIdFlexTable + "__" + flexColumn.COD_FLEX_COLUM +"' name='"+ tiIdFlexTable+"__"+ flexColumn.COD_FLEX_COLUM +"' value='' ondblclick='uncheckme(this)' style='display:none'/>");

		return sb.toString();
	}
	
	//ECM 29 ENERO 2016 Agregar catálogo manual para la pestaña Poderes Generales y todas las del semáforo, Consulta.
	public static String getRadioButtonListValue(ConnectionWrapper connect, FlexColumn flexColumn, String value, int tiIdFlexTable) {

		Catalogo cat = new Catalogo(flexColumn.ID_CATALOGO);

		if(flexColumn.ID_CATALOGO == 4000){
			for (CatalogoValor catElem : cat.getEdosMex(connect)) {
				 if (Integer.toString(catElem.idCatalogoValor).equals(value)){
					return catElem.nomCatVal;
				}
			}
		}else{
			for (CatalogoValor catElem : cat.getElementos(connect)) {
				 if (Integer.toString(catElem.idCatalogoValor).equals(value)){
					 if(tiIdFlexTable == 34){
						 return catElem.valCatVal; 
					 }else{
						 return catElem.nomCatVal;
					 }
				}
			}
		}
		return "";
	}

}
