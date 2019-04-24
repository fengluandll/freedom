package mx.com.televisa.derechocorporativo.modules.dynhtml;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import mx.com.televisa.derechocorporativo.bean.AgregarOtrosBean;
import mx.com.televisa.derechocorporativo.bean.AsuntoBean;
import mx.com.televisa.derechocorporativo.bean.EjercicioBean;
import mx.com.televisa.derechocorporativo.daos.AgregarOtrosDAO;
import mx.com.televisa.derechocorporativo.daos.AsuntoDao;
import mx.com.televisa.derechocorporativo.daos.EjercicioSocialDao;
import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;
import mx.com.televisa.derechocorporativo.model.Catalog;
import mx.com.televisa.derechocorporativo.model.CatalogElement;
import mx.com.televisa.derechocorporativo.modules.captura.Catalogo;
import mx.com.televisa.derechocorporativo.modules.captura.CatalogoValor;
import mx.com.televisa.derechocorporativo.modules.flextabs.FlexColumn;

public class CustomTable {
	
	final static Logger log = Logger.getLogger(CustomTable.class);

	static class CustomTableColum {

		String columName = "";
		String columType = "";
		String calalogId = "";
	}

	public static String getCustomTable(ConnectionWrapper connect,
			FlexColumn flexColumn, String value, int tiIdFlexTable,
			boolean editable) {

		int catalogId = 0;
		Catalogo cat = null;
		List<CatalogElement> catalogElements = null;

		StringBuilder sb = new StringBuilder();

		ArrayList<CustomTableColum> listColumns = new ArrayList<CustomTableColum>();

		String columsDef = flexColumn.ATRIBUTO4;

		StringTokenizer columsDefToken = new StringTokenizer(columsDef, "|");

		while (columsDefToken.hasMoreTokens()) {

			String columDefinition = columsDefToken.nextToken();

			StringTokenizer columDefToken = new StringTokenizer(
					columDefinition, ";");

			CustomTableColum currCol = new CustomTableColum();

			currCol.columName = columDefToken.nextToken();
			currCol.columType = columDefToken.nextToken();

			if (currCol.columType.startsWith("CAT")) {

				StringTokenizer columTypeToken = new StringTokenizer(
						currCol.columType, "-");

				currCol.columType = columTypeToken.nextToken();
				currCol.calalogId = columTypeToken.nextToken();

				catalogId = Integer.parseInt(currCol.calalogId);
				cat = new Catalogo(catalogId);

				if (catalogId == 1) {
					try {
					//	catalogElements = new Catalog("DERCORP_EMPRESA_TAB")
						catalogElements = new Catalog("DERCORP_BUSQUEDA_VIEW")
								.getList("ID_EMPRESA, DENOM_ACTUAL", "");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					// Implementar
					// catalogElements = cat.getElementos(connect)
				}
			}

			listColumns.add(currCol);
		}

		sb.append("<div id='" + tiIdFlexTable + "_customTable_"
				+ flexColumn.COD_FLEX_COLUM + "'>");

		sb.append("<table width='100%'>");
		sb.append("<tr>");
		for (CustomTableColum customTableColum : listColumns) {

			sb.append("<th class='tableHeaderAlfa2'>");
			sb.append(customTableColum.columName);
			sb.append("</th>");
		}
		sb.append("</tr>");

		StringTokenizer valueTokenRows = new StringTokenizer(value, ",");

		while (valueTokenRows.hasMoreTokens()) {

			String valueTokenRow = valueTokenRows.nextToken();
			
			log.info("valueTokenRow: "+valueTokenRow);
			
			String idEmpresa = valueTokenRow.split(";")[0];
			
			//Se agrega para obtener el id de la empresa 26/06/2018 JAMS

			StringTokenizer valueTokenfields = new StringTokenizer(
					valueTokenRow, ";");
			
			log.info("valueTokenfields: "+valueTokenfields);

			sb.append("<tr>");
			for (CustomTableColum customTableColum : listColumns) {

				String cellValue = (valueTokenfields.hasMoreTokens()) ? valueTokenfields
						.nextToken() : "";
						
						log.info("VALOR:  "+cellValue);

				if (customTableColum.columType.equals("CAT")) {

					cellValue = getCatalogValue(catalogElements, cellValue,
							connect);
					log.info("cellValue:  "+cellValue);
				}

				sb.append("<td>");
				sb.append(cellValue);
				sb.append("</td>");
			}
			sb.append("</tr>");

		}

		sb.append("</table>");
		sb.append("</div>");

		if (editable) {

			/*
			 * 
			 * Catalogo cat = new Catalogo(flexColumn.ID_CATALOGO);
			 * 
			 * String commaNames = "";
			 * 
			 * //ECM 27 ENERO 2016 Agregar catálogo manual para la pestaña
			 * Contratos, campo nombres en Captura. if(flexColumn.ID_CATALOGO ==
			 * 6969){ for (CatalogoValor catElem :
			 * cat.getElementosPersonalizados(connect)) { String commaValues =
			 * "," + value + ","; String searchedValue = "," +
			 * Integer.toString(catElem.idCatalogoValor) + ",";
			 * 
			 * if (commaValues.contains(searchedValue)) {
			 * 
			 * 
			 * //commaNames += catElem.nomCatVal; //commaNames +=
			 * catElem.valCatVal; commaNames += "<li>" + catElem.valCatVal +
			 * "</li>"; } } }else{ for (CatalogoValor catElem :
			 * cat.getElementos(connect)) {
			 * 
			 * String commaValues = "," + value + ",";
			 * 
			 * String searchedValue = "," +
			 * Integer.toString(catElem.idCatalogoValor) + ",";
			 * 
			 * if (commaValues.contains(searchedValue)) {
			 * 
			 * 
			 * 
			 * //commaNames += catElem.valCatVal; commaNames += "<li>" +
			 * catElem.valCatVal + "</li>"; } } }
			 * 
			 * 
			 * int catalogId = flexColumn.ID_CATALOGO; String targetIds =
			 * tiIdFlexTable + "__" + flexColumn.COD_FLEX_COLUM; String
			 * targetNames = tiIdFlexTable + "_multilist_" +
			 * flexColumn.COD_FLEX_COLUM; String namesProperty = "innerHTML";
			 * String currentValue = "document.getElementById('" + targetIds +
			 * "').value"; //String currentValue = "'" + value + "'"; String
			 * namesFormat = "UL";
			 */
			// sb.append("<input id='"+tiIdFlexTable+"_multilist_" +
			// flexColumn.COD_FLEX_COLUM+"' style='width:"+flexColumn.CAN_TAMANN_COLUM+"px' readonly='readonly' value='"
			// + commaNames + "'>");
			// sb.append("<div id='" + targetNames + "'><ul>" + commaNames +
			// "</ul></div>");

			// sb.append(targetIds);

			String targetIds = tiIdFlexTable + "__" + flexColumn.COD_FLEX_COLUM;
			String currentValue = "document.getElementById('" + targetIds
					+ "').value";
			String targetNames = tiIdFlexTable + "_customTable_"
					+ flexColumn.COD_FLEX_COLUM;

			sb.append("<input type='hidden' name='" + flexColumn.COD_FLEX_COLUM
					+ "' id='" + targetIds + "' value='" + value + "'>");

			// sb.append("<input type='button' value='...' onclick=\"openMultiSelectPupUp('"
			// +flexColumn.ID_CATALOGO + "','" + tiIdFlexTable+"__"+
			// flexColumn.COD_FLEX_COLUM + "','"+tiIdFlexTable+"_multilist_" +
			// flexColumn.COD_FLEX_COLUM+"', '" + value + "')\">");
			// sb.append("<br>");

			sb.append("<div align='right'>");
			// sb.append("<input type='button' value='Seleccionar' onclick=\"openCustomTablePupUp('"
			// + catalogId + "','" + targetIds + "','" + targetNames + "', '" +
			// namesProperty + "', " + currentValue + ",'" + namesFormat +
			// "')\">");
			sb.append("<input type='button' value='Seleccionar' onclick=\"openCustomTablePupUp('"
					+ catalogId
					+ "','"
					+ targetIds
					+ "', "
					+ currentValue
					+ ",'" + targetNames + "')\">");
			sb.append("</div>");
		}

		return sb.toString();
	}

	private static String getCatalogValue(List<CatalogElement> catalogElements,
			String valueId, ConnectionWrapper connect) {

		try {
			for (CatalogElement catElem : catalogElements) {

				String idCatalogoValor = Integer.toString(catElem.getId());
				
				log.info("idCatalogoValor "+idCatalogoValor );

				if (idCatalogoValor.equals(valueId)) {
					//Se cambia contains por equals 26/06/2018 JAMS

					return catElem.getName();
				}
			}

			return "";
		} catch (Exception ex) {

			return "";
		}
	}

	public static String getCustomTableEjercicio(ConnectionWrapper connect,
			FlexColumn flexColumn, int value, int tiIdFlexTable,
			boolean editable) {
		System.out.println("Entra");
		StringBuilder sb = new StringBuilder();
		EjercicioSocialDao ejerDao = new EjercicioSocialDao();

		ArrayList<CustomTableColum> listColumns = new ArrayList<CustomTableColum>();

		String columsDef = flexColumn.ATRIBUTO4;

		StringTokenizer columsDefToken = new StringTokenizer(columsDef, "|");

		while (columsDefToken.hasMoreTokens()) {

			String columDefinition = columsDefToken.nextToken();

			StringTokenizer columDefToken = new StringTokenizer(
					columDefinition, ";");

			CustomTableColum currCol = new CustomTableColum();

			currCol.columName = columDefToken.nextToken();
			currCol.columType = columDefToken.nextToken();

			listColumns.add(currCol);
		}

		sb.append("<div id='" + tiIdFlexTable + "_customTable_"
				+ flexColumn.COD_FLEX_COLUM + "'>");

		// añadir contador de registros para mostrar u ocultar tabla
		int totalEjer = ejerDao.countEjercicioMetaRow(value);
		if (totalEjer != 0) {
			sb.append("<table id='tblEjercicios' width='100%'>");
		} else {
			sb.append("<table id='tblEjercicios' width='100%' class='hidden'>");
		}
		if (value != 0 && totalEjer !=0) {
		sb.append("<thead>");
		sb.append("<tr>");
		for (CustomTableColum customTableColum : listColumns) {

			sb.append("<th class='tableHeaderAlfa2'>");
			sb.append(customTableColum.columName);
			sb.append("</th>");
		}
		sb.append("</tr>");
		sb.append("</thead>");
		
			sb.append("<tbody id='ejerciciosTemp'>");
			

			ArrayList<EjercicioBean> listEjercicio = (ArrayList<EjercicioBean>) ejerDao
					.findEjerciciosMetaRow(value);
			int i = 1;
			for (EjercicioBean ejercicio : listEjercicio) {
				sb.append("<tr>");//29/09/2017 JAMS arrreglo en armado de tabla
				sb.append("<td>" + ejercicio.getTipoDoc() + "</td>");
				sb.append("<td>"
						+ (ejercicio.getFechaDocumentum()!=null?ejercicio.getFechaDocumentum():"")
						+ "</td>");
				sb.append("<td>"
						+ (ejercicio.getFechaEntrega()!=null?ejercicio.getFechaEntrega():"")
						+ "</td>");
				sb.append("<td>"+(ejercicio.getNoDocumentum()!=null?ejercicio.getNoDocumentum():"")+"<a href=\"javascript:getDocumentumDeTabla('"+ejercicio.getNoDocumentum()+"')\"><img src='/DerechoCorporativo/img/List_16.png'></a></td>");
				sb.append("</tr>");
				i++;
			}
			
			sb.append("</tbody>");
			sb.append("</table></div>");
		} else {
			sb.append("<tbody id='ejerciciosTemp'><tr><td colspan='4'></td></tr></tbody></table>");
		}

		if (editable) {
			String targetIds = tiIdFlexTable + "__" + flexColumn.COD_FLEX_COLUM;
			

			sb.append("<input type='hidden' name='" + flexColumn.COD_FLEX_COLUM
					+ "' id='" + targetIds + "' value='" + value + "'>");

			sb.append("<div align='left'>");
			
			sb.append("<img src='/DerechoCorporativo/img/icons/new.png' onclick=\"openCustomTableEjercicioPupUp('consulta',"
					+ value + ")\"><br>");
			sb.append("</div>");
		}
		System.out.println(sb.toString());
		return sb.toString();
	}
	
	public static String getCustomTableAsunto(ConnectionWrapper connect,
			FlexColumn flexColumn, int value, int tiIdFlexTable,
			boolean editable) {

		StringBuilder sb = new StringBuilder();
		AsuntoDao asuntDao = new AsuntoDao();

		ArrayList<CustomTableColum> listColumns = new ArrayList<CustomTableColum>();

		String columsDef = flexColumn.ATRIBUTO4;

		StringTokenizer columsDefToken = new StringTokenizer(columsDef, "|");

		while (columsDefToken.hasMoreTokens()) {

			String columDefinition = columsDefToken.nextToken();

			StringTokenizer columDefToken = new StringTokenizer(
					columDefinition, ";");

			CustomTableColum currCol = new CustomTableColum();

			currCol.columName = columDefToken.nextToken();
			currCol.columType = columDefToken.nextToken();

			listColumns.add(currCol);
		}

		sb.append("<div id='" + tiIdFlexTable + "_customTable_"
				+ flexColumn.COD_FLEX_COLUM + "'>");

		// añadir contador de registros para mostrar u ocultar tabla
		int totalAsunt = asuntDao.countAsuntoMetaRow(value);
		if (totalAsunt != 0) {
			sb.append("<table id='tblAsuntos' width='100%'>");
		} else {
			sb.append("<table id='tblAsuntos' width='100%' class='hidden'>");
		}
		sb.append("<thead>");
		sb.append("<tr>");
		for (CustomTableColum customTableColum : listColumns) {

			sb.append("<th class='tableHeaderAlfa2'>");
			sb.append(customTableColum.columName);
			sb.append("</th>");
		}
		sb.append("</tr>");
		sb.append("</thead>");
		if (value != 0) {
			sb.append("<tbody id='asuntosTemp'>");
			//sb.append("<tr>");

			ArrayList<AsuntoBean> listAsunto = (ArrayList<AsuntoBean>) asuntDao
					.findAsuntosMetaRow(value);
			int i = 1;
			for (AsuntoBean asunto : listAsunto) {
				sb.append("<tr>");
				sb.append("<td>" + asunto.getIdAsunto() + "</td>");
				sb.append("<td>"+asunto.getAsunto()+"</td>");
				sb.append("</tr>");
				i++;
			}

			//sb.append("</tr>");
			sb.append("</tbody>");
			sb.append("</table>");
		} else {
			sb.append("<tbody id='asuntosTemp'><tr><td colspan='4'></td></tr></tbody></table>");
		}

		if (editable) {
			String targetIds = tiIdFlexTable + "__" + flexColumn.COD_FLEX_COLUM;
			

			sb.append("<input type='hidden' name='" + flexColumn.COD_FLEX_COLUM
					+ "' id='" + targetIds + "' value='" + value + "'>");

			sb.append("<div align='left'>");
			
			sb.append("<img src='/DerechoCorporativo/img/icons/new.png' onclick=\"openCustomTableAsuntoPupUp('consulta',"
					+ value + ")\"><br>");
			sb.append("</div>");
		}

		return sb.toString();
	}
	
	
	
	public static String getCustomTableAgregarOtros(ConnectionWrapper connect,
			FlexColumn flexColumn, int value, int tiIdFlexTable,
			boolean editable) {

		StringBuilder sb = new StringBuilder();
		AgregarOtrosDAO asuntDao = new AgregarOtrosDAO();

		ArrayList<CustomTableColum> listColumns = new ArrayList<CustomTableColum>();

		String columsDef = flexColumn.ATRIBUTO4;

		StringTokenizer columsDefToken = new StringTokenizer(columsDef, "|");

		while (columsDefToken.hasMoreTokens()) {

			String columDefinition = columsDefToken.nextToken();

			StringTokenizer columDefToken = new StringTokenizer(
					columDefinition, ";");

			CustomTableColum currCol = new CustomTableColum();

			currCol.columName = columDefToken.nextToken();
			currCol.columType = columDefToken.nextToken();

			listColumns.add(currCol);
		}

		sb.append("<div id='" + tiIdFlexTable + "_customTable_"
				+ flexColumn.COD_FLEX_COLUM + "'>");

		// añadir contador de registros para mostrar u ocultar tabla
		int totalAsunt = asuntDao.countAsuntoMetaRow(value);
		if (totalAsunt != 0) {
			sb.append("<table id='tblAgregar' width='100%'>");
		} else {
			sb.append("<table id='tblAgregar' width='100%' class='hidden'>");
		}
		sb.append("<thead>");
		sb.append("<tr>");
		for (CustomTableColum customTableColum : listColumns) {

			sb.append("<th class='tableHeaderAlfa2'>");
			sb.append(customTableColum.columName);
			sb.append("</th>");
		}
		sb.append("</tr>");
		sb.append("</thead>");
		if (value != 0) {
			sb.append("<tbody id='agregarTemp'>");
			//sb.append("<tr>");

			ArrayList<AgregarOtrosBean> listAsunto = (ArrayList<AgregarOtrosBean>) asuntDao
					.findAsuntosMetaRow(value);
			int i = 1;
			for (AgregarOtrosBean asunto : listAsunto) {
				sb.append("<tr>");
				sb.append("<td>" + asunto.getIdAgregar() + "</td>");
				sb.append("<td>"+asunto.getAgregar()+"</td>");
				sb.append("</tr>");
				i++;
			}

			//sb.append("</tr>");
			sb.append("</tbody>");
			sb.append("</table>");
		} else {
			sb.append("<tbody id='agregarTemp'><tr><td colspan='4'></td></tr></tbody></table>");
		}

		if (editable) {
			String targetIds = tiIdFlexTable + "__" + flexColumn.COD_FLEX_COLUM;
			

			sb.append("<input type='hidden' name='" + flexColumn.COD_FLEX_COLUM
					+ "' id='" + targetIds + "' value='" + value + "'>");

			sb.append("<div align='left'>");
			
			sb.append("<img src='/DerechoCorporativo/img/icons/new.png' onclick=\"openCustomTableAgregarPupUp('consulta',"
					+ value + ")\"><br>");
			sb.append("</div>");
		}

		return sb.toString();
	}
	
	
	public static String getCustomTableEjercicioReportePerzonalizado(
			FlexColumn flexColumn, int value, int tiIdFlexTable,
			boolean editable) {
		System.out.println("Entra");
		log.info("value "+value);
		log.info("tiIdFlexTable "+tiIdFlexTable);
		log.info("editable "+editable);
		
		StringBuilder sb = new StringBuilder();
		EjercicioSocialDao ejerDao = new EjercicioSocialDao();

		ArrayList<CustomTableColum> listColumns = new ArrayList<CustomTableColum>();

		String columsDef = flexColumn.ATRIBUTO4;

		StringTokenizer columsDefToken = new StringTokenizer(columsDef, "|");

		while (columsDefToken.hasMoreTokens()) {

			String columDefinition = columsDefToken.nextToken();

			StringTokenizer columDefToken = new StringTokenizer(
					columDefinition, ";");

			CustomTableColum currCol = new CustomTableColum();

			currCol.columName = columDefToken.nextToken();
			currCol.columType = columDefToken.nextToken();

			listColumns.add(currCol);
		}

		sb.append("<div id='" + tiIdFlexTable + "_customTable_"
				+ flexColumn.COD_FLEX_COLUM + "'>");

		// añadir contador de registros para mostrar u ocultar tabla
		int totalEjer = ejerDao.countEjercicioMetaRow(value);
		if (totalEjer != 0) {
			sb.append("<table id='tblEjercicios' width='100%'>");
		} else {
			sb.append("<table id='tblEjercicios' width='100%' class='hidden'>");
		}
		if (value != 0 && totalEjer !=0) {
		sb.append("<thead>");
		sb.append("<tr>");
		for (CustomTableColum customTableColum : listColumns) {

			sb.append("<th class='tableHeaderAlfa2'>");
			sb.append(customTableColum.columName);
			sb.append("</th>");
		}
		sb.append("</tr>");
		sb.append("</thead>");
		
			sb.append("<tbody id='ejerciciosTemp'>");
			

			ArrayList<EjercicioBean> listEjercicio = (ArrayList<EjercicioBean>) ejerDao
					.findEjerciciosMetaRow(value);
			int i = 1;
			for (EjercicioBean ejercicio : listEjercicio) {
				sb.append("<tr>");//29/09/2017 JAMS arrreglo en armado de tabla
				sb.append("<td>" + ejercicio.getTipoDoc() + "</td>");
				sb.append("<td>"
						+ (ejercicio.getFechaDocumentum()!=null?ejercicio.getFechaDocumentum():"")
						+ "</td>");
				sb.append("<td>"
						+ (ejercicio.getFechaEntrega()!=null?ejercicio.getFechaEntrega():"")
						+ "</td>");
				sb.append("<td>"
						+ (ejercicio.getNoDocumentum()!=null?ejercicio.getNoDocumentum():"")
						+ "</td>");
				//sb.append("<td>"+(ejercicio.getNoDocumentum()!=null?ejercicio.getNoDocumentum():"")+"<a href=\"javascript:getDocumentumDeTabla('"+ejercicio.getNoDocumentum()+"')\"><img src='/DerechoCorporativo/img/List_16.png'></a></td>");
				sb.append("</tr>");
				i++;
			}
			
			sb.append("</tbody>");
			sb.append("</table></div>");
		} else {
			sb.append("<tbody id='ejerciciosTemp'><tr><td colspan='4'></td></tr></tbody></table>");
		}

		if (editable) {
			String targetIds = tiIdFlexTable + "__" + flexColumn.COD_FLEX_COLUM;
			

			sb.append("<input type='hidden' name='" + flexColumn.COD_FLEX_COLUM
					+ "' id='" + targetIds + "' value='" + value + "'>");

			sb.append("<div align='left'>");
			
			sb.append("<img src='/DerechoCorporativo/img/icons/new.png' onclick=\"openCustomTableEjercicioPupUp('consulta',"
					+ value + ")\"><br>");
			sb.append("</div>");
		}
		System.out.println(sb.toString());
		return sb.toString();
	}
}
