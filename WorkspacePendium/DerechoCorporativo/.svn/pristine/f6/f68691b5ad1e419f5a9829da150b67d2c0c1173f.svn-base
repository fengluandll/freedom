package mx.com.televisa.derechocorporativo.modules.dynhtml;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;
import mx.com.televisa.derechocorporativo.modules.captura.Catalogo;
import mx.com.televisa.derechocorporativo.modules.captura.CatalogoValor;
import mx.com.televisa.derechocorporativo.modules.flextabs.FlexColumn;
import mx.com.televisa.derechocorporativo.modules.reports.ecs.ReporteECSFlexColumn;

public class SelectList {

	final static Logger log = Logger.getLogger(SelectList.class);

	/**
	 * 
	 */
	public static String getSelectList(ConnectionWrapper connect, FlexColumn flexColumn, String value, int tiIdFlexTable) {

		StringBuilder sb = new StringBuilder();
		Catalogo cat = new Catalogo(flexColumn.ID_CATALOGO);

		sb.append("<select name='" + flexColumn.COD_FLEX_COLUM + "' id='" + tiIdFlexTable + "__" + flexColumn.COD_FLEX_COLUM+"' style='width:"+flexColumn.CAN_TAMANN_COLUM+"px'"+flexColumn.DES_FORMULA+">");
		sb.append("<option value='0'>(Seleccione)</option>");

		if(flexColumn.ID_CATALOGO == 4000){
			for (CatalogoValor catElem : cat.getEdosMex(connect)) {
				 sb.append("<option value='" + catElem.idCatalogoValor + "' " +"title='"+catElem.valCatVal+"'");

				 if (Integer.toString(catElem.idCatalogoValor).equals(value)) {
					 sb.append("selected");
				 }

				 sb.append(" >" + catElem.valCatVal + "</option>");
			}
		}else{
			for (CatalogoValor catElem : cat.getElementos(connect)) {
				 sb.append("<option value='" + catElem.idCatalogoValor + "' " +"title='"+catElem.valCatVal+"'");

				 if (Integer.toString(catElem.idCatalogoValor).equals(value)) {
					 sb.append("selected");
				 }

				 sb.append(" >" + catElem.valCatVal + "</option>");
			}
		}

		sb.append("</select>");

		return sb.toString();
	}
	
	
	
	

	//ECM 14 AGOSTO 2015
	public static String getSelectList(ConnectionWrapper connect, String pstCodCampo, String pstCanTamannoCampo, String pstFormula, String pstValValor, int piidCatalogo) {

		StringBuilder sb = new StringBuilder();

//		Catalogo cat = new Catalogo(this.idCatalogo);
//		Catalogo cat = new Catalogo(21);
		Catalogo cat = new Catalogo(piidCatalogo);
		

		sb.append("<select name='" + pstCodCampo + "' id='" + pstCodCampo+"' style='width:"+pstCanTamannoCampo+"px '" + pstFormula+" >");
		sb.append("<option value='0'>(Seleccione)</option>");
		
		
		for (CatalogoValor catElem : cat.getElementos(connect)) {


			 sb.append("<option value='" + catElem.idCatalogoValor + "' " +"title='"+catElem.valCatVal+"'");
			 
			 if (Integer.toString(catElem.idCatalogoValor).equals(pstValValor)) {
				 sb.append("selected"); 
			 }
			 
			 sb.append(" >" + catElem.valCatVal + "</option>");
		}
		 
		sb.append("</select>");
		 
		
		return sb.toString();
	}
	
	//ICL 05112015
	public static String getSelectListCatin(ConnectionWrapper connect, String pstCodCampo, String pstCanTamannoCampo, String pstFormula, String pstValValor, int piidCatalogo) {

		StringBuilder sb = new StringBuilder();

//		Catalogo cat = new Catalogo(this.idCatalogo);
//		Catalogo cat = new Catalogo(21);
		Catalogo cat = new Catalogo(piidCatalogo);
		

		sb.append("<select name='" + pstCodCampo + "' id='" + pstCodCampo+"' style='width:"+pstCanTamannoCampo+"px '" + pstFormula+" >");
		sb.append("<option value='0'>(Seleccione)</option>");
		
		
		for (CatalogoValor catElem : cat.getElementos(connect)) {


			 sb.append("<option value='" + catElem.valCatVal + "' " +"title='"+catElem.valCatVal+"'");
			 if(pstValValor != null && pstValValor != ""){
				 if (catElem.valCatVal.equals(pstValValor.trim())) {
					 sb.append("selected"); 
				 }
			 }
			 
			 
			 sb.append(" >" + catElem.valCatVal + "</option>");
		}
		 
		sb.append("</select>");
		 
		
		return sb.toString();
	}
	
	/**
	 * JJAQ 27/02/2017
	 * Para el catalogo de dnominaciones sociales 
	 * @param connect
	 * @param pstCodCampo
	 * @param pstCanTamannoCampo
	 * @param pstFormula
	 * @param pstValValor
	 * @param piidCatalogo
	 * @return
	 */
	public static String getSelectListCatinId(ConnectionWrapper connect, String pstCodCampo, String pstCanTamannoCampo, String pstFormula, String pstValValor, int piidCatalogo) {

		StringBuilder sb = new StringBuilder();

//		Catalogo cat = new Catalogo(this.idCatalogo);
//		Catalogo cat = new Catalogo(21);
		Catalogo cat = new Catalogo(piidCatalogo);
		

		sb.append("<select name='" + pstCodCampo + "' id='" + pstCodCampo+"' style='width:"+pstCanTamannoCampo+"px '" + pstFormula+" >");
		sb.append("<option value='0'>(Seleccione)</option>");
		
		
		for (CatalogoValor catElem : cat.getElementos(connect)) {


			 sb.append("<option value='" + catElem.idCatalogoValor + "' " +"title='"+catElem.valCatVal+"'");
			 if(pstValValor != null && pstValValor != ""){
				 if (String.valueOf(catElem.idCatalogoValor).equals(pstValValor.trim())) {
					 sb.append("selected"); 
				 }
			 }
			 
			 
			 sb.append(" >" + catElem.valCatVal + "</option>");
		}
		 
		sb.append("</select>");
		 
		
		return sb.toString();
	}

	//ECM 01 Septiembre 2015
	public static String getSelectListAccionista(ConnectionWrapper connect, FlexColumn flexColumn, String value, int tiIdFlexTable){
		StringBuilder sb = new StringBuilder();
		Catalogo cat = new Catalogo(flexColumn.ID_CATALOGO);

		//ECM 14 Agosto 2015
		//Agregar formula.
		sb.append("<select name='" + flexColumn.COD_FLEX_COLUM + "' id='" + tiIdFlexTable + "__" + flexColumn.COD_FLEX_COLUM+"' style='width:"+flexColumn.CAN_TAMANN_COLUM+"px'"+">");
		sb.append("<option value='0'>(Seleccione)</option>");

		for (CatalogoValor catElem : cat.getElementos(connect)) {
			 sb.append("<option value='" + catElem.idCatalogoValor + "' "  +"title='"+catElem.valCatVal+"'");

			 if (Integer.toString(catElem.idCatalogoValor).equals(value)) {
				 sb.append("selected"); 
			 }

			 if (catElem.atributo1 == null)
				 sb.append(" >" + catElem.valCatVal +"  "+catElem.atributo2+"</option>");
			 else
				 sb.append(" >" + catElem.valCatVal +"  "+catElem.atributo1+"</option>");
		}

		sb.append("</select>");

		return sb.toString();
	}

	public static String getCatalogValue(ConnectionWrapper connect, int idCatalogo, String value) {
		
		StringBuilder sb = new StringBuilder();
		Catalogo cat = new Catalogo(idCatalogo);

		for (CatalogoValor catElem : cat.getElementos(connect)) {
			 if (Integer.toString(catElem.idCatalogoValor).equals(value)) {
					 return catElem.nomCatVal;
			 }
		}
		return "";
	}
	
	
	//ECM 21 Septiembre 2015
	public static String getCatalogValueAccionistas(ConnectionWrapper connect, FlexColumn flexColumn, String value) {
		StringBuilder sb = new StringBuilder();
		Catalogo cat = new Catalogo(flexColumn.ID_CATALOGO);

		ArrayList<CatalogoValor> elementos =  new ArrayList<CatalogoValor>();
		 
		 if(flexColumn.ID_CATALOGO == 40){
			 elementos = cat.getElementosByCatalogoValor(connect, Integer.parseInt(value));
		 }else{
			 elementos = cat.getElementos(connect);
		 }
		for (CatalogoValor catElem : elementos) {		
		//for (CatalogoValor catElem : cat.getElementos(connect)) {
			 if (Integer.toString(catElem.idCatalogoValor).equals(value)) {
				 System.out.println("RFC: getCatalogValueAccionistas "+catElem.atributo2);
				 System.out.println("RFC: getCatalogValueAccionistas "+catElem.atributo1);
				 if(catElem.atributo1 == null)
					 return catElem.valCatVal+"\nRFC:N/A\n "+catElem.atributo2;
				 else if(catElem.atributo1 != null && catElem.atributo2 != null)
					 return catElem.valCatVal+"\nRFC:"+catElem.atributo1+"\n "+catElem.atributo2;
				 else if(catElem.atributo2 == null)
					 return catElem.valCatVal+"\nRFC:"+catElem.atributo1;
			 }
		}
		return "";
	}
	//JJAQ 16/01/2019
	public static String getCatalogValueAccionistas(ConnectionWrapper connect, ReporteECSFlexColumn flexColumn, String value) {
		StringBuilder sb = new StringBuilder();
		Catalogo cat = new Catalogo(flexColumn.ID_CATALOGO);

		ArrayList<CatalogoValor> elementos =  new ArrayList<CatalogoValor>();
		 
		 if(flexColumn.ID_CATALOGO == 40){
			 elementos = cat.getElementosByCatalogoValor(connect, Integer.parseInt(value));
		 }else{
			 elementos = cat.getElementos(connect);
		 }
		for (CatalogoValor catElem : elementos) {		
		//for (CatalogoValor catElem : cat.getElementos(connect)) {
			 if (Integer.toString(catElem.idCatalogoValor).equals(value)) {
				 System.out.println("RFC: getCatalogValueAccionistas "+catElem.atributo2);
				 System.out.println("RFC: getCatalogValueAccionistas "+catElem.atributo1);
				 if(catElem.atributo1 == null)
					 return catElem.valCatVal+"\nRFC:N/A\n "+catElem.atributo2;
				 else if(catElem.atributo1 != null && catElem.atributo2 != null)
					 return catElem.valCatVal+"\nRFC:"+catElem.atributo1+"\n "+catElem.atributo2;
				 else if(catElem.atributo2 == null)
					 return catElem.valCatVal+"\nRFC:"+catElem.atributo1;
			 }
		}
		return "";
	}

	public static String getCatalogValueAccionistasSinPais(ConnectionWrapper connect, FlexColumn flexColumn, String value) {
		StringBuilder sb = new StringBuilder();
		Catalogo cat = new Catalogo(flexColumn.ID_CATALOGO);
		ArrayList<CatalogoValor> elementos =  new ArrayList<CatalogoValor>();
		 
		 if(flexColumn.ID_CATALOGO == 40){
			 elementos = cat.getElementosByCatalogoValor(connect, Integer.parseInt(value));
		 }else{
			 elementos = cat.getElementos(connect);
		 }
		for (CatalogoValor catElem : elementos) {
		//for (CatalogoValor catElem : cat.getElementos(connect)) {
			 if (Integer.toString(catElem.idCatalogoValor).equals(value)) {
				 System.out.println("RFC: getCatalogValueAccionistasSinPais "+catElem.atributo2);
				 System.out.println("RFC: getCatalogValueAccionistasSinPais "+catElem.atributo1);
				 if(catElem.atributo1 == null)
					 return catElem.valCatVal+"\nRFC:N/A";
				 else
					 return catElem.valCatVal+"\nRFC:"+catElem.atributo1;
			 }
		}
		return "";
	}
	
	//JJAQ 16/01/2019
	public static String getCatalogValueAccionistasSinPais(ConnectionWrapper connect, ReporteECSFlexColumn flexColumn, String value) {
		StringBuilder sb = new StringBuilder();
		Catalogo cat = new Catalogo(flexColumn.ID_CATALOGO);
		ArrayList<CatalogoValor> elementos =  new ArrayList<CatalogoValor>();
		 
		 if(flexColumn.ID_CATALOGO == 40){
			 elementos = cat.getElementosByCatalogoValor(connect, Integer.parseInt(value));
		 }else{
			 elementos = cat.getElementos(connect);
		 }
		for (CatalogoValor catElem : elementos) {
		//for (CatalogoValor catElem : cat.getElementos(connect)) {
			 if (Integer.toString(catElem.idCatalogoValor).equals(value)) {
				 System.out.println("RFC: getCatalogValueAccionistasSinPais "+catElem.atributo2);
				 System.out.println("RFC: getCatalogValueAccionistasSinPais "+catElem.atributo1);
				 if(catElem.atributo1 == null)
					 return catElem.valCatVal+"\nRFC:N/A";
				 else
					 return catElem.valCatVal+"\nRFC:"+catElem.atributo1;
			 }
		}
		return "";
	}
	
	public static String getCatalogValueAccionistasSinRFCConPais(ConnectionWrapper connect, FlexColumn flexColumn, String value) {
		StringBuilder sb = new StringBuilder();
		Catalogo cat = new Catalogo(flexColumn.ID_CATALOGO);

		ArrayList<CatalogoValor> elementos =  new ArrayList<CatalogoValor>();
		 
		 if(flexColumn.ID_CATALOGO == 40){
			 elementos = cat.getElementosByCatalogoValor(connect, Integer.parseInt(value));
		 }else{
			 elementos = cat.getElementos(connect);
		 }
		for (CatalogoValor catElem : elementos) {
		//for (CatalogoValor catElem : cat.getElementos(connect)) {
			 if (Integer.toString(catElem.idCatalogoValor).equals(value)) {
				 return catElem.valCatVal+"\n"+catElem.atributo2;
			 }
		}
		return "";
	}
	
	//JJAQ 16/01/2019
	public static String getCatalogValueAccionistasSinRFCConPais(ConnectionWrapper connect, ReporteECSFlexColumn flexColumn, String value) {
		StringBuilder sb = new StringBuilder();
		Catalogo cat = new Catalogo(flexColumn.ID_CATALOGO);

		ArrayList<CatalogoValor> elementos =  new ArrayList<CatalogoValor>();
		 
		 if(flexColumn.ID_CATALOGO == 40){
			 elementos = cat.getElementosByCatalogoValor(connect, Integer.parseInt(value));
		 }else{
			 elementos = cat.getElementos(connect);
		 }
		for (CatalogoValor catElem : elementos) {
		//for (CatalogoValor catElem : cat.getElementos(connect)) {
			 if (Integer.toString(catElem.idCatalogoValor).equals(value)) {
				 return catElem.valCatVal+"\n"+catElem.atributo2;
			 }
		}
		return "";
	}
	
	public static String getCatalogValueAccionistasSinRFC(ConnectionWrapper connect, FlexColumn flexColumn, String value) {
		StringBuilder sb = new StringBuilder();
		Catalogo cat = new Catalogo(flexColumn.ID_CATALOGO);
		 ArrayList<CatalogoValor> elementos =  new ArrayList<CatalogoValor>();
		 
		 if(flexColumn.ID_CATALOGO == 40){
			 elementos = cat.getElementosByCatalogoValor(connect, Integer.parseInt(value));
		 }else{
			 elementos = cat.getElementos(connect);
		 }
		for (CatalogoValor catElem : elementos) {
			 if (Integer.toString(catElem.idCatalogoValor).equals(value)) {
					 return catElem.valCatVal;
			 }
		}
		return "";
	}
	
	//JJAQ 16/01/2019
	
	public static String getCatalogValueAccionistasSinRFC(ConnectionWrapper connect, ReporteECSFlexColumn flexColumn, String value) {
		StringBuilder sb = new StringBuilder();
		Catalogo cat = new Catalogo(flexColumn.ID_CATALOGO);
		 ArrayList<CatalogoValor> elementos =  new ArrayList<CatalogoValor>();
		 
		 if(flexColumn.ID_CATALOGO == 40){
			 elementos = cat.getElementosByCatalogoValor(connect, Integer.parseInt(value));
		 }else{
			 elementos = cat.getElementos(connect);
		 }
		for (CatalogoValor catElem : elementos) {
			 if (Integer.toString(catElem.idCatalogoValor).equals(value)) {
					 return catElem.valCatVal;
			 }
		}
		return "";
	}
	
	public static String getSelectMoneda(ConnectionWrapper connect, FlexColumn flexColumn, String value, int tiIdFlexTable, String tsEmpresa) {
		StringBuilder sb = new StringBuilder();
		//Catalogo cat = new Catalogo(flexColumn.ID_CATALOGO);
		Catalogo cat = new Catalogo(20);

		sb.append("<select name='" + flexColumn.COD_FLEX_COLUM + "' id='" + tiIdFlexTable + "__" + flexColumn.COD_FLEX_COLUM+"' style='width:60px'>");
		sb.append("<option value='0'>(Seleccione)</option>");

		for (CatalogoValor catElem : cat.getElementos(connect)) {

			 sb.append("<option value='" + catElem.idCatalogoValor + "' " +"title='"+catElem.valCatVal+"'");

			 /*if (Integer.toString(catElem.idCatalogoValor).equals(value)) {
				 sb.append("selected"); 
			 }*/

			 sb.append(" >" + catElem.valCatVal + "</option>");
		}

		sb.append("</select>");
        sb.append("&nbsp<b>"+TextNumero.getMoneda(tsEmpresa, connect)+"</b>");

		return sb.toString();
	}

	public static String getSelectListMoneda(ConnectionWrapper connect, FlexColumn flexColumn, String value, int tiIdFlexTable, String tsEmpresa) {

		StringBuilder sb = new StringBuilder();
		Catalogo cat = new Catalogo(flexColumn.ID_CATALOGO);

		sb.append("<select name='" + flexColumn.COD_FLEX_COLUM + "' id='" + tiIdFlexTable + "__" + flexColumn.COD_FLEX_COLUM+"' style='width:"+flexColumn.CAN_TAMANN_COLUM+"px'"+flexColumn.DES_FORMULA+">");
		sb.append("<option value='0'>(Seleccione)</option>");

			for (CatalogoValor catElem : cat.getElementos(connect)) {
				 sb.append("<option value='" + catElem.idCatalogoValor + "' " +"title='"+catElem.valCatVal+"'");

				 if (Integer.toString(catElem.idCatalogoValor).equals(value)) {
					 sb.append("selected");
				 }

				 sb.append(" >" + catElem.valCatVal + "</option>");
			}

		sb.append("</select>");
		sb.append("&nbsp");

		//String lstValue = TextNumero.getMoneda(tsEmpresa, connect);
		//sb.append(getSelectMoneda(connect, flexColumn, "", tiIdFlexTable, tsEmpresa));

		return sb.toString();
	}

	
}
