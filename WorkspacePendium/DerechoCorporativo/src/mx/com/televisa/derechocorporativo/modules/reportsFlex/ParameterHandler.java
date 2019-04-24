package mx.com.televisa.derechocorporativo.modules.reportsFlex;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.*;
import java.util.Map.Entry;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import mx.com.televisa.derechocorporativo.bean.SessionBean;
import mx.com.televisa.derechocorporativo.components.JSCal;
import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;
import mx.com.televisa.derechocorporativo.data.packages.DERCORP_REPORTFLEX_PKG;
import mx.com.televisa.derechocorporativo.modules.captura.Catalogo;
import mx.com.televisa.derechocorporativo.modules.captura.CatalogoValor;
import mx.com.televisa.derechocorporativo.modules.flextabs.FlexColumn;
import mx.com.televisa.derechocorporativo.util.FacesUtils;

public class ParameterHandler {

	public static String addParam(String id) {
		
		Map<String,String> map = FacesUtils.getSessionBean().getDynamicParams();
		
		if(map.containsKey(id)) {
			
			
		} else {
			
			map.put(id, "");
			DBParamHandler.insertParam(FacesUtils.getSessionBean().getCurrentFlexReportId(), id);
		}
		
		return getParamsHTMLTable();
	}
	

	public static String removeParam(String id) {
		
		Map<String,String> map = FacesUtils.getSessionBean().getDynamicParams();
		
		map.remove(id);
		
		DBParamHandler.deleteParam(FacesUtils.getSessionBean().getCurrentFlexReportId(), id);
		
		return getParamsHTMLTable();
	}
	
	
	public static String addParamValue(String id, String value) {
		
		Map<String,String> map = FacesUtils.getSessionBean().getDynamicParams();
		
		map.put(id, value);
		
		DBParamHandler.updateParam(FacesUtils.getSessionBean().getCurrentFlexReportId(), id, value);
		
		return "OK";
	}
	

	
	
	public static String getParamsHTMLTable() {
		
		return getParamsHTMLTable(true);
	}
	
	public static String getParamsHTMLTable(boolean edit) {
		
		StringBuilder sb = new StringBuilder();
		
		Map<String,String> map = FacesUtils.getSessionBean().getDynamicParams();
		
		
		if(map.size() == 0) {
			
			return "<br><br>Seleccione los filtros que desea usar para la b&uacute;squeda..";
		}
		
		
		ConnectionWrapper conn = null;
		
		
		
		try {
			conn = new ConnectionWrapper();
			
			
			
			String params = "|";
			
			for(Entry<String, String> entry : map.entrySet()) {
			
				String key = entry.getKey();
			
			    params += key;
			}
			
			
			Map<String, Parameter> paramObjects = getParameters(params, conn);
			
			
			sb.append("<table width='100%'>");
			
			for(Entry<String, String> entry : map.entrySet()) {

				String key = entry.getKey();
			    String value = (entry.getValue() != null) ? entry.getValue() : "";
			
			    Parameter param = paramObjects.get(key);
			    
			    if(edit) {
			    
			    	sb.append("<tr><td>" + param.getNomCampo() + "</td><td>" + toHTMLComponent(param, value, conn) + "</td><td><a href='#' onclick=\"quitarParametro('" + key + "')\">Quitar</a></td></tr>");
			    	
			    } else {
			    	
			    	sb.append("<tr><td>" + param.getNomCampo() + "</td><td>" + toHTMLJustValue(param, value, conn) + "</td></tr>");
			    	
			    }
			    
			    
			    
			}

			sb.append("</table>");

			return  sb.toString();
			
		} catch(Exception ex) {
			
			ex.printStackTrace();
			
			return ex.toString();
		} finally {
			
			ConnectionWrapper.closeAll(conn);
			
		}
	}
	

	public static String toHTMLComponent(Parameter param, String value, ConnectionWrapper conn) {
		
		if(param.getIdCatalogo() == 0) {
			
			StringBuilder sb = new StringBuilder();
			
			sb.append("<input name='param_" + param.getIdAddCampo() + "' id='param_" + param.getIdAddCampo() + "' value='" + value + "' onKeyUp='updateParamValue(" + param.getIdAddCampo() + ",this.value)'>");
			
			/*
			if(param.getTipoCampo() != null && param.getTipoCampo().equals("DATE")) {
				
				
				sb.append(JSCal.getCalendar("param_" + param.getIdAddCampo() + "", 
						"updateParamValue(" + param.getIdAddCampo() + ",document.getElementById('param_" + param.getIdAddCampo() + "').value)"));
			}*/

			if(param.getTipoCampo() != null && param.getTipoCampo().equals("DATE")) {
					sb.append("<input type='button' value='...' "
								+ "onclick=\"openCalendarSelectPupUp('param_" + param.getIdAddCampo() + "','" +param.getIdAddCampo() + "')\">");
			}
			
			return sb.toString();
			
		} else {
			
			StringBuilder sb = new StringBuilder();
			
			//return getSelectList(conn, param, value);
			
			//String commaVals = getSelectValuesComma(conn, param, value);
			String vals = getSelectValues(conn, param, value);
			
			
			
			//sb.append("<input id='" +param.getIdAddCampo()+"_names' readonly='readonly' size='70px' "
			//		+ "value='" + commaVals + "' onChange='updateParamValue(" + param.getIdAddCampo() + ",this.value)'>");
			
			
			sb.append("<input type='hidden' name='param_" + param.getIdAddCampo() + "' "
								+ "id='param_" + param.getIdAddCampo() + "' value='" + value + "' "
								//+ "onChange='updateParamValue(" + param.getIdAddCampo() + ",this.value)'>");
								+ ">");
			
			
			
			sb.append("<input type='button' value='Seleccionar...' "
					+ "onclick=\"openMultiSelectPupUp('" +param.getIdCatalogo() + "',"
					+ "'param_" + param.getIdAddCampo() + "',"
					+ "'" +param.getIdAddCampo()+"_div', "
					+ "'innerHTML', "
					//+ "'" + value + "',"
					+ "document.getElementById('param_" + param.getIdAddCampo() + "').value,"
					+ "'" + param.getIdAddCampo() + "')\">");
			
			sb.append("<br>");
			
			sb.append("<div id='" + param.getIdAddCampo() +"_div'>" + vals + "</div>");
			
			
			
			return sb.toString();
		}
	}
	

	public static String toHTMLJustValue(Parameter param, String value, ConnectionWrapper conn) {
		
		if(param.getIdCatalogo() == 0) {
			
			return value;
			
		} else {
			
			return getSelectValues(conn, param, value);
		}
	}
	
	
	
	public static String getSelectList(ConnectionWrapper connect, Parameter param, String value) {

		StringBuilder sb = new StringBuilder();

		Catalogo cat = new Catalogo(param.getIdCatalogo());


		sb.append("<select name='param_" + param.getIdAddCampo() + "' id='param_" + param.getIdAddCampo() + "' style='width:180px' onchange='updateParamValue(" + param.getIdAddCampo() + ",this.value)'>");
		sb.append("<option value='0'>(Seleccione)</option>");
	
		for (CatalogoValor catElem : cat.getElementos(connect)) {

			 sb.append("<option value='" + catElem.idCatalogoValor + "'");
			 
			 if (Integer.toString(catElem.idCatalogoValor).equals(value)) {
				 sb.append("selected"); 
			 }
			 
			 sb.append(" >" + catElem.valCatVal + "</option>");
		}
		 
		sb.append("</select>");
		 
		
		return sb.toString();
	}
	
	public static String getSelectValue(ConnectionWrapper connect, Parameter param, String value) {

		StringBuilder sb = new StringBuilder();

		Catalogo cat = new Catalogo(param.getIdCatalogo());

		for (CatalogoValor catElem : cat.getElementos(connect)) {

			 if (Integer.toString(catElem.idCatalogoValor).equals(value)) {
				 
				 return catElem.nomCatVal;
			 }
		}
		
		return sb.toString();
	}
	
	
	public static String getSelectValues(ConnectionWrapper connect, Parameter param, String value) {

		StringBuilder sb = new StringBuilder();

		Catalogo cat = new Catalogo(param.getIdCatalogo());
		
		if(param.getIdCatalogo() != 31) {
			
			String commaValues = "," + value + ",";
	
			for (CatalogoValor catElem : cat.getElementos(connect)) {
	
				 //if (Integer.toString(catElem.idCatalogoValor).equals(value)) {
				
				if (commaValues.contains("," + Integer.toString(catElem.idCatalogoValor) + ",")) {
					
					sb.append(catElem.valCatVal + "<br>");
				 }
			}
		} else {
			
			// SEMAFORO
			String commaValues = "," + value + ",";
			
			for (CatalogoValor catElem : cat.getElementos(connect)) {
	
				 //if (Integer.toString(catElem.idCatalogoValor).equals(value)) {
				
				if (commaValues.contains("button_green.png") && Integer.toString(catElem.idCatalogoValor).contains("12070")) {
					
					sb.append("VERDE" + "<br>");
				 }
				
				if (commaValues.contains("button_yellow.png") && Integer.toString(catElem.idCatalogoValor).contains("12071")) {
					
					sb.append("AMARILLO" + "<br>");
				 }
				if (commaValues.contains("button_red.png") && Integer.toString(catElem.idCatalogoValor).contains("12072")) {
					
					sb.append("ROJO" + "<br>");
				 }
			}
			
			
		}
		
		
		
		//StringU
		
		return sb.toString();
	}
	
	
	public static String getSelectValuesComma(ConnectionWrapper connect, Parameter param, String value) {

		StringBuilder sb = new StringBuilder();

		Catalogo cat = new Catalogo(param.getIdCatalogo());
		
		String commaValues = "," + value + ",";

		for (CatalogoValor catElem : cat.getElementos(connect)) {

			 //if (Integer.toString(catElem.idCatalogoValor).equals(value)) {
			
			if (commaValues.contains(Integer.toString(catElem.idCatalogoValor))) {
				
				sb.append(catElem.valCatVal + ",");
			 }
		}
		
		//StringU
		
		return sb.toString();
	}
	
	
	
	
	
	public static Map<String, Parameter> getParameters(String paramIds, ConnectionWrapper connect) {

		Map<String, Parameter> params = new HashMap<String, Parameter>();
		
		CallableStatement stmt = null;
		ResultSet set = null;

		try {

			stmt = connect.prepareCall(DERCORP_REPORTFLEX_PKG.GET_PARAM_INFO_PR);

			stmt.registerOutParameter(1, OracleTypes.CURSOR);
			
			stmt.setString(2, paramIds);

			stmt.execute();

			set = ((OracleCallableStatement) stmt).getCursor(1);

			while (set.next()) {
				
				Parameter parameterObj = new Parameter(set, set.getMetaData());
						
				params.put(Integer.toString(parameterObj.getIdAddCampo()), parameterObj);
			}

			return params;
			
		} catch (Exception ex) {

			// TODO - Pendiente - Gestion de Excepciones
			ex.printStackTrace();

		} finally {
			
			ConnectionWrapper.closeAll(stmt, set);
		}
		return null;
	}


	
}
