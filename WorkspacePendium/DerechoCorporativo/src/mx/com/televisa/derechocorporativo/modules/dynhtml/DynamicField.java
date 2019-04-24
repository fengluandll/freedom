package mx.com.televisa.derechocorporativo.modules.dynhtml;

import mx.com.televisa.derechocorporativo.modules.captura.Campo;
import mx.com.televisa.derechocorporativo.modules.flextabs.FlexColumn;

public class DynamicField {

	
	public static final String TYPE_TEXTAREA = "TEXTAREA";
	public static final String TYPE_RADIO = "RADIO";
	public static final String TYPE_RADIO_V = "RADIO_V";

	public static final String TYPE_SELECT = "SELECT";
	public static final String TYPE_MULTISELECT = "MULTISELECT";	// FlexTables
	public static final String TYPE_FLEXTABLE = "FLEXTABLE";		//AddCampo
	public static final String TYPE_DATE = "DATE";
	
	public static final String DISABLED_TEXT = "DISABLED_TEXT";	// FlexTables
	
	public static final String TYPE_CHECKBOX = "CHECKBOX";
	public static final String TYPE_LABEL = "LABEL";
	
	public static final String TYPE_NUMERIC = "NUMERIC";
	public static final String TYPE_SELECT_IMG = "SELECT_IMG";
	
	public static final String TYPE_CHECKBOX_D = "CHECKBOX_D";
	
	public static final String TYPE_AJAX_PAGE = "AJAX_PAGE";
	public static final String TYPE_HREF  = "HREF";
	
	public static final String TYPE_SELECT_ACCIONISTAS = "SELECT_ACCIONISTAS";

	public static final String TYPE_TITLE = "TITLE";
	
	public static final String TYPE_IMG = "IMG";
	
	
	
	
	Campo campo;
	
	FlexColumn flexColum;
	
	
	public DynamicField(Campo campo) {
		
		this.campo = campo;
	}
	
	public DynamicField(FlexColumn flexColum) {
		
		this.flexColum = flexColum;
	}
	
	
	
	
	
	
	
	
	
	
}
