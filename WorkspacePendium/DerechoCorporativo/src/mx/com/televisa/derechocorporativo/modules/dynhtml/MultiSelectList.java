package mx.com.televisa.derechocorporativo.modules.dynhtml;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import mx.com.televisa.derechocorporativo.bean.EmpresasBean;
import mx.com.televisa.derechocorporativo.daos.EmpresasDAO;
import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;
import mx.com.televisa.derechocorporativo.modules.captura.Catalogo;
import mx.com.televisa.derechocorporativo.modules.captura.CatalogoValor;
import mx.com.televisa.derechocorporativo.modules.flextabs.FlexColumn;
import mx.com.televisa.derechocorporativo.modules.reports.Report;
import mx.com.televisa.derechocorporativo.modules.reportsFlex.ReportFlex;

public class MultiSelectList {
	private final static Logger log = Logger.getLogger(MultiSelectList.class);
	public static String getMultiSelectUnorderedList(ConnectionWrapper connect, FlexColumn flexColumn, String value, int tiIdFlexTable) {

		StringBuilder sb = new StringBuilder();
		Catalogo cat = new Catalogo(flexColumn.ID_CATALOGO);

		String commaNames = "";

		//ECM 27 ENERO 2016 Agregar catálogo manual para la pestaña Contratos, campo nombres en Captura.
		if(flexColumn.ID_CATALOGO == 6969){
			for (CatalogoValor catElem : cat.getElementosPersonalizados(connect)) {
				 String commaValues = "," + value + ",";
				 String searchedValue = "," + Integer.toString(catElem.idCatalogoValor) + ",";

				 if (commaValues.contains(searchedValue)) {
					 /*if(!commaNames.equals("")) {
						 commaNames += ", ";
					 }*/

					 //commaNames += catElem.nomCatVal;
					 //commaNames += catElem.valCatVal;
					 commaNames += "<li>" + catElem.valCatVal + "</li>";
				 }
			}
		}else{
			for (CatalogoValor catElem : cat.getElementos(connect)) {
	
				 String commaValues = "," + value + ",";
				 
				 String searchedValue = "," + Integer.toString(catElem.idCatalogoValor) + ",";
				 
				 if (commaValues.contains(searchedValue)) {
					 
					 /*if(!commaNames.equals("")) {
						 
						 commaNames += ", ";
					 }*/
					 
					 //commaNames += catElem.valCatVal;
					 commaNames += "<li>" + catElem.valCatVal + "</li>";
				 }
			}
		}		
		

		int catalogId = 		flexColumn.ID_CATALOGO;
		String targetIds = 		tiIdFlexTable + "__" + flexColumn.COD_FLEX_COLUM;
		String targetNames = 	tiIdFlexTable + "_multilist_" + flexColumn.COD_FLEX_COLUM;
		String namesProperty = 	"innerHTML";
		String currentValue = 	"document.getElementById('" + targetIds + "').value";		
		//String currentValue = "'" + value + "'";
		String namesFormat = 	"UL";
		
		
		//sb.append("<input id='"+tiIdFlexTable+"_multilist_" + flexColumn.COD_FLEX_COLUM+"' style='width:"+flexColumn.CAN_TAMANN_COLUM+"px' readonly='readonly' value='" + commaNames + "'>");
		sb.append("<div id='" + targetNames + "'><ul>" + commaNames + "</ul></div>");
		
		//sb.append(targetIds);
		sb.append("<input type='hidden' name='" + flexColumn.COD_FLEX_COLUM + "' id='" + targetIds + "' value='" + value + "'>");
		
		//sb.append("<input type='button' value='...' onclick=\"openMultiSelectPupUp('" +flexColumn.ID_CATALOGO + "','" + tiIdFlexTable+"__"+ flexColumn.COD_FLEX_COLUM + "','"+tiIdFlexTable+"_multilist_" + flexColumn.COD_FLEX_COLUM+"', '" + value + "')\">");
		//sb.append("<br>");
		
		
		sb.append("<div align='right'>");
		sb.append("<input type='button' value='Seleccionar' onclick=\"openMultiSelectPupUp2('" + catalogId + "','" + targetIds + "','" + targetNames + "', '" + namesProperty + "', " + currentValue + ",'" + namesFormat + "')\">");
		sb.append("</div>");
		
		
		return sb.toString();
	}
	
	public static String getMultiSelectList(ConnectionWrapper connect, FlexColumn flexColumn, String value, int tiIdFlexTable) {

		StringBuilder sb = new StringBuilder();
		Catalogo cat = new Catalogo(flexColumn.ID_CATALOGO);

		String commaNames = "";

		//ECM 27 ENERO 2016 Agregar catálogo manual para la pestaña Contratos, campo nombres en Captura.
		if(flexColumn.ID_CATALOGO == 6969){
			for (CatalogoValor catElem : cat.getElementosPersonalizados(connect)) {
				 String commaValues = "," + value + ",";
				 String searchedValue = "," + Integer.toString(catElem.idCatalogoValor) + ",";

				 if (commaValues.contains(searchedValue)) {
					 if(!commaNames.equals("")) {
						 commaNames += ", ";
					 }

					 commaNames += catElem.nomCatVal;
				 }
			}
		}else{
			for (CatalogoValor catElem : cat.getElementos(connect)) {
	
				 String commaValues = "," + value + ",";
				 
				 String searchedValue = "," + Integer.toString(catElem.idCatalogoValor) + ",";
				 
				 if (commaValues.contains(searchedValue)) {
					 
					 if(!commaNames.equals("")) {
						 
						 commaNames += ", ";
					 }
					 
					 commaNames += catElem.nomCatVal;
				 }
			}
		}		
		
		sb.append("<input id='"+tiIdFlexTable+"_multilist_" + flexColumn.COD_FLEX_COLUM+"' style='width:"+flexColumn.CAN_TAMANN_COLUM+"px' readonly='readonly' value='" + commaNames + "'>");
	
		sb.append("<input type='hidden' name='" + flexColumn.COD_FLEX_COLUM + "' id='" + tiIdFlexTable + "__" + flexColumn.COD_FLEX_COLUM+"' value='" + value + "'>");
		
		sb.append("<input type='button' value='...' onclick=\"openMultiSelectPupUp('" +flexColumn.ID_CATALOGO + "','" + tiIdFlexTable+"__"+ flexColumn.COD_FLEX_COLUM + "','"+tiIdFlexTable+"_multilist_" + flexColumn.COD_FLEX_COLUM+"', '" + value + "')\">");
		
		
		return sb.toString();
	}
	
	public static String getMultiSelectListValuesUnorderedList(ConnectionWrapper connect, FlexColumn flexColumn, String value, int tiIdFlexTable) {

		Catalogo cat = new Catalogo(flexColumn.ID_CATALOGO);
		String commaNames = "";

		//ECM 27 ENERO 2016 Agregar catálogo manual para la pestaña Contratos, campo nombres en Consulta.
		if(flexColumn.ID_CATALOGO == 6969){
			for (CatalogoValor catElem : cat.getElementosPersonalizados(connect)) {
				 String commaValues = "," + value + ",";
				 String searchedValue = "," + Integer.toString(catElem.idCatalogoValor) + ",";

				 if (commaValues.contains(searchedValue)) {
					 /*if(!commaNames.equals("")) {
						 commaNames += ", ";
					 }*/

					 //commaNames += catElem.nomCatVal;
					 commaNames += "<li>" + catElem.valCatVal + "</li>";
				 }
			}
		}else{
			for (CatalogoValor catElem : cat.getElementos(connect)) {
	
				 String commaValues = "," + value + ",";
				 
				 String searchedValue = "," + Integer.toString(catElem.idCatalogoValor) + ",";
				 
				 if (commaValues.contains(searchedValue)) {
					 
					 /*if(!commaNames.equals("")) {
						 
						 commaNames += ", ";
					 }*/
					 
					 //commaNames += catElem.nomCatVal;
					 commaNames += "<li>" + catElem.valCatVal + "</li>";
				 }
			}
		}

		return "<ul>" + commaNames + "</ul>";
	}
	
	public static String getMultiSelectListValues(ConnectionWrapper connect, FlexColumn flexColumn, String value, int tiIdFlexTable) {

		Catalogo cat = new Catalogo(flexColumn.ID_CATALOGO);
		String commaNames = "";

		//ECM 27 ENERO 2016 Agregar catálogo manual para la pestaña Contratos, campo nombres en Consulta.
		if(flexColumn.ID_CATALOGO == 6969){
			for (CatalogoValor catElem : cat.getElementosPersonalizados(connect)) {
				 String commaValues = "," + value + ",";
				 String searchedValue = "," + Integer.toString(catElem.idCatalogoValor) + ",";

				 if (commaValues.contains(searchedValue)) {
					 if(!commaNames.equals("")) {
						 commaNames += ", ";
					 }

					 commaNames += catElem.nomCatVal;
				 }
			}
		}else{
			for (CatalogoValor catElem : cat.getElementos(connect)) {
	
				 String commaValues = "," + value + ",";
				 
				 String searchedValue = "," + Integer.toString(catElem.idCatalogoValor) + ",";
				 
				 if (commaValues.contains(searchedValue)) {
					 
					 if(!commaNames.equals("")) {
						 
						 commaNames += ", ";
					 }
					 
					 commaNames += catElem.nomCatVal;
				 }
			}
		}

		return commaNames;
	}

	public static String getMultiSelectListEmp(String value, String target) {
		StringBuilder sb = new StringBuilder();

		EmpresasDAO luEmpresasDAO = new EmpresasDAO();
		List<EmpresasBean> listEmpresas = luEmpresasDAO.dameEmpresas();
		
		String commaNames = "";
		
		
		for (EmpresasBean emps : listEmpresas) {

			 String commaValues = "," + value + ",";
			 
			 String searchedValue = "," + Integer.toString(emps.getIdEmpresa()) + ",";
			 
			 if (commaValues.contains(searchedValue)) {
				 
				 /*if(!commaNames.equals("")) {
					 
					 commaNames += ", ";
				 }*/
				 
				 //commaNames += emps.getNomEmpresa();
				 commaNames += "<li>" + emps.getNomEmpresa() + "</li>";
			 }
		}
		
		
		//sb.append("<input id='emp_multilist' style='width:400px' readonly='readonly' value='" + commaNames + "'>");
		//sb.append("<input type='hidden' name='emp_h'  id='emp_h' value='" + value + "'>");
		//sb.append("<input type='button' value='...' onclick=\"openMultiSelectPupUp('0','emp_h','emp_multilist', '" + value + "')\">");
		

		sb.append("<div id='emp_multilist'><ul>" + commaNames + "</ul></div>");
		sb.append("<input type='hidden' name='emp_h'  id='emp_h' value='" + value + "'>");
		
		sb.append("<div align='right'>");
		sb.append("<input type='button' value='Seleccionar' onclick=\"openMultiSelectPupUp2"
							+ "('0','emp_h','emp_multilist', 'innerHTML', document.getElementById('emp_h').value,'UL')\">");
		sb.append("</div>");
		
		
		return sb.toString();
	}
	
	public static String getMultiSelectListReportPre(String value, String target) {
		StringBuilder sb = new StringBuilder();

		ArrayList<Report> listReports = Report.getReports(0);
		
		String commaNames = "";
		
		
		for (Report report : listReports) {

			 String commaValues = "," + value + ",";
			 
			 String searchedValue = "," + Integer.toString(report.getIdReporte()) + ",";
			 
			 if (commaValues.contains(searchedValue)) {
				 
				 /*if(!commaNames.equals("")) {
					 
					 commaNames += ", ";
				 }*/
				 
				 //commaNames += report.getNomReporte();
				 commaNames += "<li>" + report.getNomReporte() + "</li>";
			 }
		}
		
		
		//sb.append("<input id='reppre_multilist' style='width:400px' readonly='readonly' value='" + commaNames + "'>");
		//sb.append("<input type='hidden' name='reppre_h'  id='reppre_h' value='" + value + "'>");
		//sb.append("<input type='button' value='...' onclick=\"openMultiSelectPupUp('1000','reppre_h','reppre_multilist', '" + value + "')\">");

		sb.append("<div id='reppre_multilist'><ul>" + commaNames + "</ul></div>");
		sb.append("<input type='hidden' name='reppre_h'  id='reppre_h' value='" + value + "'>");
		
		sb.append("<div align='right'>");
		sb.append("<input type='button' value='Seleccionar' onclick=\"openMultiSelectPupUp2"
							+ "('1000','reppre_h','reppre_multilist', 'innerHTML', document.getElementById('reppre_h').value,'UL')\">");
		sb.append("</div>");
				
		return sb.toString();
	}
	
	
	public static String getMultiSelectListReportPer(String value, String target) {
		StringBuilder sb = new StringBuilder();

		ArrayList<ReportFlex> listReports = ReportFlex.getReports(0);
		
		String commaNames = "";
		
		
		for (ReportFlex report : listReports) {

			 String commaValues = "," + value + ",";
			 
			 String searchedValue = "," + Integer.toString(report.getIdReportFlex()) + ",";
			 
			 if (commaValues.contains(searchedValue)) {
				 
				 /*if(!commaNames.equals("")) {
					 
					 commaNames += ", ";
				 }*/
				 
				 //commaNames += report.getNomReporte();
				 commaNames += "<li>" + report.getNomReporte() + "</li>";
			 }
		}
		
		
		//sb.append("<input id='repper_multilist' style='width:400px' readonly='readonly' value='" + commaNames + "'>");
		//sb.append("<input type='hidden' name='repper_h'  id='repper_h' value='" + value + "'>");
		//sb.append("<input type='button' value='...' onclick=\"openMultiSelectPupUp('2000','repper_h','repper_multilist', '" + value + "')\">");
		
		
		sb.append("<div id='repper_multilist'><ul>" + commaNames + "</ul></div>");
		sb.append("<input type='hidden' name='repper_h'  id='repper_h' value='" + value + "'>");
		
		sb.append("<div align='right'>");
		sb.append("<input type='button' value='Seleccionar' onclick=\"openMultiSelectPupUp2"
							+ "('2000','repper_h','repper_multilist', 'innerHTML', document.getElementById('repper_h').value,'UL')\">");
		sb.append("</div>");
		
		
		return sb.toString();
	}	
//	
//
//	public static String getMultiSelectList(ConnectionWrapper connect, FlexColumn flexColumn, String value) {
//
//		StringBuilder sb = new StringBuilder();
//		Catalogo cat = new Catalogo(flexColumn.ID_CATALOGO);
//		
//		//"+flexColumn.DES_FORMULA+"
//		
//		// onClick=\"storeMultipleValues('multilist_" + flexColumn.COD_FLEX_COLUM+"','" + flexColumn.COD_FLEX_COLUM+"')\"
//		
//		sb.append("<select multiple name='multilist_" + flexColumn.COD_FLEX_COLUM + "' id='multilist_" + flexColumn.COD_FLEX_COLUM+"' style='width:"+flexColumn.CAN_TAMANN_COLUM+"px'>");
//		//sb.append("<option value='0'>(Seleccione)</option>");
//	
//		for (CatalogoValor catElem : cat.getElementos(connect)) {
//
//			 sb.append("<option value='" + catElem.idCatalogoValor + "' ");
//			 
//			 String commaValues = "," + value + ",";
//			 
//			 String searchedValue = "," + Integer.toString(catElem.idCatalogoValor) + ",";
//			 
//			 if (commaValues.contains(searchedValue)) {
//			 //if (Integer.toString(catElem.idCatalogoValor).equals(value)) {
//				 sb.append("selected"); 
//			 }
//			 
//			 sb.append(" >" + catElem.nomCatVal + "</option>");
//		}
//		 
//		sb.append("</select>");
//		
//		sb.append("<input type='hidden' name='" + flexColumn.COD_FLEX_COLUM + "' id='" + flexColumn.COD_FLEX_COLUM+"' value='" + value + "'>");
//		
//		
//		
//		
//		String inputId = "multilist_" + flexColumn.COD_FLEX_COLUM;
//	
//	/*
//		sb.append("<script type=\"text/javascript\">");
//		sb.append("$(function() { " +
//			   "     $('#" + inputId + "').change(function() { " +
//			   "         console.log($(this).val()); " +
//			   "  		storeMultipleValues('multilist_" + flexColumn.COD_FLEX_COLUM+"','" + flexColumn.COD_FLEX_COLUM+"');" + 	
//			   "     }).multipleSelect({ " +
//			   "         width: '80%' " +
//			   "     }); " +
//			   " });");
//		sb.append("</script>");
//		*/
//		
//		
//		
//		
//		return sb.toString();
//	}
//	

//	public static String getMultiSelectListScriptOnly(FlexColumn flexColumn){
//		
//		
//		String inputId = "multilist_" + flexColumn.COD_FLEX_COLUM;
//		/*
//		return "$(function () { " +
//			   "     $('[id*=" + inputId + "]').multiselect({ " +
//			   "         includeSelectAllOption: true " +
//			   "     }); " +
//			   " }); ";		
//		*/
//		
//		/*return "$('" + inputId + "').multipleSelect();";
//		*/
//		
//		/*return "$(function () { " +
//			   //"   $('select#" + inputId + "').listbox(); " +
//			   "   $('select#" + inputId + "').listbox({'searchbar': true}); " +
//			   " });";
//		*/
//	
//		
//		return "$(function() { " +
//			   //"	alert('sample 24');" + 
//			   "     $('#" + inputId + "').change(function() { " +
//			   "         console.log($(this).val()); " +
//			   "  		storeMultipleValues('multilist_" + flexColumn.COD_FLEX_COLUM+"','" + flexColumn.COD_FLEX_COLUM+"');" + 	
//			   "     }).multipleSelect({ " +
//			   "         width: '80%' " +
//			   "     }); " +
//			   " });";
//		
//		
//	}
//	
}
