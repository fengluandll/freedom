/**
 * Project: Derecho Corporativo
 *
 * File: Campo.java
 *
 * Created on: Agosto 31, 2015 at 12:00
 *
 * Copyright (c) - Televisa - 2015
 */

package mx.com.televisa.derechocorporativo.modules.captura;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import mx.com.televisa.derechocorporativo.modules.flextabs.EstructuraCapitalSocial;
import mx.com.televisa.derechocorporativo.modules.flextabs.FlexTable;
import mx.com.televisa.derechocorporativo.reflexion.ReflexionUtil;
import mx.com.televisa.derechocorporativo.bean.CapitalFVBen;
import mx.com.televisa.derechocorporativo.components.JSCal;
import mx.com.televisa.derechocorporativo.daos.ConsultaDAO;
import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;
import mx.com.televisa.derechocorporativo.util.FacesUtils;
import mx.com.televisa.derechocorporativo.util.NumberFormatter;
import mx.com.televisa.derechocorporativo.util.StringUtils;
import mx.com.televisa.derechocorporativo.util.WebPageReader;
import mx.com.televisa.derechocorporativo.modules.flextabs.FlexTableSaveHandler;

/**
 * Crear todos los componentes web html.
 *
 * @author KAZ - CONSULTING/ Ivan Castaneda Loeza Jesus Argumedo Eduardo
 *         Nava Ernesto Corona Mendoza
 *
 * @version 1.0.0
 *
 * @date Agosto 31, 2015 at 12:00 pm
 *
 */
public class Campo {
	
	final static Logger log = Logger.getLogger(Campo.class);

	public Campo() {
		super();
	}

	private static final String TYPE_TEXTAREA = "TEXTAREA";
	private static final String TYPE_RADIO = "RADIO";
	private static final String TYPE_RADIO_V = "RADIO_V";
	private static final String TYPE_SELECT = "SELECT";
	private static final String TYPE_SELECT_PUPUP = "SELECT_P";
	private static final String TYPE_FLEXTABLE = "FLEXTABLE";
	private static final String TYPE_DATE = "DATE";
	private static final String TYPE_CHECKBOX = "CHECKBOX";
	private static final String TYPE_LABEL = "LABEL";
	private static final String TYPE_NUMERIC = "NUMERIC";
	private static final String TYPE_SELECT_IMG = "SELECT_IMG";
	private static final String TYPE_CHECKBOX_D = "CHECKBOX_D";
	private static final String TYPE_CHECKBOX_E = "CHECKBOX_E";
	private static final String TYPE_CHECKBOX_A = "CHECKBOX_A";
	private static final String TYPE_AJAX_PAGE = "AJAX_PAGE";
	private static final String TYPE_HREF = "HREF";
	//private static String psEditable = "";
	private String psEditable = "";
	private static final String TYPE_DATE_D = "DATE_D";
	private static final String TYPE_SEMAFORO = "SEMAFORO";

	//ECM 29 Agosto 2016 - Resumen General - Obtener Socio Externo
	private static final String TYPE_INPUT = "INPUT";

	int idAddCampo;
	String codCampo;
	String nomCampo;
	String desCampo;
	String desTipoCampo;
	String canTamannoCampo;
	int idCatalogo;
	int idSeccion;
	int idSubSeccion;
	String valValor;
	int idAgrupacion;
	int idflexTbl;
	String atributo1;
	String desFormula;
	String atributo3; // SRC para iFrame
	String atributo4; // Formula 2 para formato Documentum
	String atributo5; // Caramelo Reforma Documentum y Statutos
	
	private String lsconsultarECSSem;
	
	private FlexTableSaveHandler ftSavHand;
	int isAC;
	String tipoSoc;
	
	

	public Campo(int idAddCampo, String codCampo, String nomCampo,
			String desCampo, String desTipoCampo, String canTamannoCampo,
			int idCatalogo, int idSeccion, int idSubSeccion, String valValor,
			int idAgrupacion, int idflexTbl, String atributo1,
			String desFormula, String atributo3, String atributo4, String atributo5) {
		super();
		this.idAddCampo = idAddCampo;
		this.codCampo = codCampo;
		this.nomCampo = nomCampo;
		this.desCampo = desCampo;
		this.desTipoCampo = desTipoCampo;
		this.canTamannoCampo = canTamannoCampo;
		this.idCatalogo = idCatalogo;
		this.idSeccion = idSeccion;
		this.idSubSeccion = idSubSeccion;
		this.valValor = valValor;
		this.idAgrupacion = idAgrupacion;
		this.idflexTbl = idflexTbl;
		this.atributo1 = atributo1;
		this.desFormula = desFormula;
		this.atributo3 = atributo3;
		this.atributo4 = atributo4;
		this.atributo5 = atributo5;
	}

	/**
	 * Manda llamar al metodo fillObject que se encuentra almacenado en la
	 * clase ReflexionUtil para obtener los metadata.
	 * 
	 * @param tuSet
	 * @param tuMetaData
	 * @throws Exception
	 */
	public Campo(ResultSet tuSet, ResultSetMetaData tuMetaData)
			throws Exception {

		ReflexionUtil.fillObject(tuSet, tuMetaData, this, Campo.class);
	}

	private static boolean FLAG_SHOW_ECS_CHECKS = true;

	/**
	 * Crear componentes web html.
	 * 
	 * @param tuAgrupacion
	 * @param tsBgcolor
	 * @param tuConnect
	 * @param tsColspan1
	 * @param tsColspan2
	 * @param tbPrePair
	 * @param tbPair
	 * @return String
	 */
	public String toHTML(Agrupaciones tuAgrupacion, String tsBgcolor,
			ConnectionWrapper tuConnect, String tsColspan1, String tsColspan2,
			boolean tbPrePair, boolean tbPair, Pagina pagina) {
		 
		//ECM 29 Marzo 2016 Ocultar Fecha Pendiente en la consulta de ECS ja
		String lstIdEmpresa = FacesUtils.getSessionBean().getIdCurrentEmpresa();
		isAC = EstructuraCapitalSocial.isAsociacionCivil(Integer.parseInt(lstIdEmpresa));//JJAQ SABER SI ES UNA AC O NO
		tipoSoc = EstructuraCapitalSocial.getTipoSoc(Integer.parseInt(lstIdEmpresa));//ULR obtener tipo de sociedad
		// ECM 25 Agosto 2015
		psEditable = FacesUtils.getSessionBean().getEditCon();
		
		//ECM 19 Abril 2016 Consulta
		ConsultaDAO luConsultaDAO = new ConsultaDAO();

		if ((psEditable == null) || (psEditable.equals(""))) {

			FLAG_SHOW_ECS_CHECKS = true;

		} else {

			// Semaforo
			if (this.idAddCampo == 546) {
				if (this.valValor != null
						//&& this.valValor.equals("button_green.png_sin_fondo")) {
						&& (this.valValor.equals("button_green.png") || this.valValor.equals("0") ) ) { 
					FLAG_SHOW_ECS_CHECKS = false;

				} else {

					FLAG_SHOW_ECS_CHECKS = true;

				}
			}

			// Estructura de Capital Social
			if (this.idSeccion == 19 && this.desTipoCampo.equals("CHECKBOX_D")) {

				if (FLAG_SHOW_ECS_CHECKS == false) {

					return ""; // No imprime este campo
				}
			}

		}

		// AdministraciÃ³n y Vigilancia
		if (psEditable != null && psEditable.equals("disabled")
				&& this.idSeccion == 20 && this.desTipoCampo.equals("CHECKBOX")) {

			return "";
		}

		StringBuilder luStringBuilder = new StringBuilder();
		String lsFlexStyle = "";

		// ECM 01 JULIO 2015
		if (this.desTipoCampo == null) {
			this.desTipoCampo = "";
		}

		if (this.valValor == null) {
			this.valValor = "";
		}

		if (tuAgrupacion.isFlex.equals("NO")) {

		} else {
			tsColspan1 = "2";
			tsColspan2 = "6";
		}

		//ECM Caramelo
		String cssClass = ""; 
		if(this.atributo5 != null) {
			
			//cssClass = " class='" + this.atributo5 + "'";
		} 


		if (tuAgrupacion.isFlex.equals("NO") && !tbPair && this.nomCampo!=null) {
			//luStringBuilder.append("<td colspan='" + tsColspan1 + "' "+cssClass+">");
			if(this.idAddCampo == 520 || this.idAddCampo == 1077)
				//enava
				luStringBuilder.append("");
				//luStringBuilder.append("<td colspan='0'>");
			else
				/*ULR 21/12/2016 se añadio if para excluir los tds cuando el type_checkbox sea falso, se hizo de 
				 * esta manera ya que al añadir la condicion antes afectada los checkbox de captura*/
				if (this.psEditable.equals("disabled") && (this.desTipoCampo.equals(TYPE_CHECKBOX)||this.desTipoCampo.equals(TYPE_CHECKBOX_D)) && !this.valValor.equals(this.codCampo)) {
					luStringBuilder.append("");
				}else{
					luStringBuilder.append("<td colspan='" + tsColspan1 + "'>");
				}
				//luStringBuilder.append("<td colspan='" + tsColspan1 + "'>");
			if (!(  this.desTipoCampo.equals(TYPE_FLEXTABLE)
					|| this.desTipoCampo.equals(TYPE_CHECKBOX_D) 
					|| this.desTipoCampo.equals(TYPE_AJAX_PAGE))) {
				//poner condicion aqui para quitar el
				if (this.desTipoCampo.equals(TYPE_CHECKBOX) && this.valValor.equals(this.codCampo)) {
					luStringBuilder.append("<div id='nom_" + this.codCampo + "'>");
				}else if(this.desTipoCampo.equals(TYPE_DATE_D)&&this.idAddCampo==1022){
					luStringBuilder.append("<div id='nom_" + this.codCampo + "'>");
				}
				//luStringBuilder.append("<div id='nom_" + this.codCampo + "'>");

				if ((psEditable == null) || (psEditable.equals(""))) {
					/**
					 * JJAQ 31/01/2017 implementa que desaparesca aplica capital fijo y aplica capital variable
					 * Capital Fijo o Mínimo, Capital Variable y Capital Social: con las A.C.
					 */
					if(isAC > 0){ 
						if(this.idAddCampo != 1030 && this.idAddCampo != 1031 && this.idAddCampo != 1028 
								                   && this.idAddCampo != 1029 && this.idAddCampo != 541){
							luStringBuilder.append(this.nomCampo);
						}
					} else if((tipoSoc.equals("isSA")|| tipoSoc.equals("isSC") || tipoSoc.equals("isLLC") ||
							   tipoSoc.equals("isSLU") || tipoSoc.equals("isSAC") || tipoSoc.equals("isSAU") ||
							   tipoSoc.equals("isLTD") || tipoSoc.equals("isSL")) 
							&& (this.idAddCampo == 1028 || this.idAddCampo == 1029)){
						
					} else{
						luStringBuilder.append(this.nomCampo);
					}
					
			    //CONSULTA/////////////////////////////////////////////////////
				} else {
					//ECM 29 Marzo 2016 Ocultar Fecha Pendiente en la consulta de ECS
					//ECM 19 Abril 2016 Ocultar checks cuando no aplique ECS.

					if(this.codCampo.equals("C1022")){//Fecha de Trámite Pendiente
						lsconsultarECSSem = luConsultaDAO.consultarECSSem(lstIdEmpresa);

						if( lsconsultarECSSem != null && lsconsultarECSSem.equals("button_green.png"))
							luStringBuilder.append("<b>"+"</b>");
						else
							//luStringBuilder.append("<b>" + this.nomCampo + "</b>");
							luStringBuilder.append(this.nomCampo);
					}else if(this.desTipoCampo.equals("CHECKBOX_D")){
						int liisCheck = luConsultaDAO.getActCheck(lstIdEmpresa, this.idAddCampo);

						if(liisCheck == 1)
							//luStringBuilder.append("<b>" + this.nomCampo + "</b>");
							luStringBuilder.append(this.nomCampo);
						else
							//luStringBuilder.append("<b>"+"</b>");
							return ""; // No imprime este campo

					}else if(this.idAddCampo == 1030 || this.idAddCampo == 1031){
						//ECM 25 Abril 2016 Consulta ECS - Aplica CApital Fijo y Variable Que siempre ni en Si ni en No.
						//int liisCheck = luConsultaDAO.getActCheck(lstIdEmpresa, this.idAddCampo);
						
						//if(liisCheck == 1)
							//luStringBuilder.append("<b>" + this.nomCampo + "</b>");
						//else
							return ""; // No imprime este campo

					}else if(this.idAddCampo==1028 || this.idAddCampo==1029){
                		ftSavHand = new FlexTableSaveHandler();
    					CapitalFVBen capitalFVBen = ftSavHand.getCheckValuesFlex(Integer.parseInt(lstIdEmpresa));
    					if(capitalFVBen.getCapFijo() == 0 && capitalFVBen.getCapVariable() == 0 && this.psEditable.equals("disabled")){
    						luStringBuilder.append("");
    					}else{
    						if(tipoSoc.equals("isSdeRL") || tipoSoc.equals("isSA")|| tipoSoc.equals("isSAB") || 
    						   tipoSoc.equals("isLLC")   || tipoSoc.equals("isSLU") || tipoSoc.equals("isSAC") ||
    						   tipoSoc.equals("isSAU") || tipoSoc.equals("isLTD") || tipoSoc.equals("isSL")){
    							luStringBuilder.append("");
    						}else{
    							luStringBuilder.append(this.nomCampo);										    							
    						}
    					}
    					//ECM 29 Agosto 2016 Resumen General - Consulta - Ocultar Val Nominal o Val Teorico.
                	}else if(this.idAddCampo == 519 || this.idAddCampo == 1076){
						int liValor = luConsultaDAO.valNominalValTeorico(Integer.parseInt(lstIdEmpresa), this.idAddCampo);
						if(liValor > 0)
							//luStringBuilder.append("<b>" + this.nomCampo + "</b>");
							luStringBuilder.append(this.nomCampo);

                    //ECM 29 Agosto 2016 Resumen General - Consulta - Obtener Socio Externo
						}else if(this.idAddCampo == 522){
						String lsValor = luConsultaDAO.getSocioExterno(Integer.parseInt(lstIdEmpresa));
						if(lsValor != null && lsValor.equals("Si"))
							//luStringBuilder.append("<b>" + this.nomCampo + "</b>");
							luStringBuilder.append(this.nomCampo);

					}else if(this.desTipoCampo.equals(TYPE_CHECKBOX_A)){

                    }else if(this.desTipoCampo.equals(TYPE_LABEL)){
                    	if(this.idAddCampo == 1010){

                    	}else{
                    		//luStringBuilder.append("<b>" + this.nomCampo + "</b>");	
                    		luStringBuilder.append(this.nomCampo);
                    	}
                    	
                    }else{
                    	//luStringBuilder.append("<b>" + this.nomCampo + "</b>");
                    	//luStringBuilder.append(this.nomCampo);
                    	if (this.desTipoCampo.equals(TYPE_CHECKBOX) && !this.valValor.equals(this.codCampo)) {
                    		luStringBuilder.append("<i></i>");
                		}else{
                			
                			if(this.idAddCampo == 541 && isAC == 1){
                				//JJAQ para que no aparezca Capital Fijo o Mínimo, Capital Variable en consulta                			
                    		}else {
                    			luStringBuilder.append(this.nomCampo);
                    		}
                			
                		}
                    }

				}
				//insertar validacion aqui
				if (this.desTipoCampo.equals(TYPE_CHECKBOX) && this.valValor.equals(this.codCampo)) {
				luStringBuilder.append("</div>");
				}
				//luStringBuilder.append("</div>");
			}
			/*ULR 21/12/2016 se añadio if para excluir los tds cuando el type_checkbox sea falso, se hizo de 
			 * esta manera ya que al añadir la condicion antes afectada los checkbox de captura*/
			if (this.psEditable!=null && this.psEditable!="" && (this.desTipoCampo.equals(TYPE_CHECKBOX)||this.desTipoCampo.equals(TYPE_CHECKBOX_D)) && !this.valValor.equals(this.codCampo)) {
				luStringBuilder.append("");
			}else{
				luStringBuilder.append("</td>");
			}
			//luStringBuilder.append("</td>");
		}

		if (!tbPair) {
			if (tuAgrupacion.isFlex.equals("YES")) {
				luStringBuilder.append("<td colspan='8' "+cssClass+">");

			} else {
				//02 Septiembre 2016 - ECM Resumen General - Informacion General
				//TD COLSPAN para Valor Nominal - Valor Teorico Nominal
				//01-02-2017 ULR se agregaron los valores para valor nominal y teorico nominal en escritura constitutiva
				if(this.idAddCampo == 519 || this.idAddCampo == 520 
                || this.idAddCampo == 1077 || this.idAddCampo == 1076
                || this.idAddCampo == 563 || this.idAddCampo == 564
                || this.idAddCampo == 1088 || this.idAddCampo == 1089)
					if(this.desTipoCampo.equals(TYPE_SELECT) && 
							(this.codCampo.equals("C20") || this.codCampo.equals("C1076") 
							 || this.codCampo.equals("C64") || this.codCampo.equals("C1088"))){
						if(this.getSelectList(tuConnect).contains("$")){
							luStringBuilder.append("<td  colspan='1'"+cssClass+">");
						}else{
							luStringBuilder.append("<td colspan='2'"+cssClass+">");
						}
					}else{
						luStringBuilder.append("<td "+cssClass+">");
					}
						//luStringBuilder.append("<td colspan='0'  "+cssClass+">");
				else
					/*ULR 21/12/2016 se añadio if para excluir los tds cuando el type_checkbox sea falso, se hizo de 
					 * esta manera ya que al añadir la condicion antes afectada los checkbox de captura*/
					if (this.psEditable!=null && this.psEditable!="" && (this.desTipoCampo.equals(TYPE_CHECKBOX)||this.desTipoCampo.equals(TYPE_CHECKBOX_D)) && !this.valValor.equals(this.codCampo)) {
						luStringBuilder.append("");
					}else{
						luStringBuilder.append("<td colspan='" + tsColspan2 + "'  "+cssClass+">");
					}
					//luStringBuilder.append("<td colspan='" + tsColspan2 + "'  "+cssClass+">");

			}
		}

		if (this.idCatalogo == 0) {
			if (this.desTipoCampo.equals(TYPE_TEXTAREA)) {
				/*ULR se redimencionaron a una fila el textarea de resumen gral>inf. gral>Actiidad y escritura constitutiva>
				*(todos los de observaciones)*/
				if(this.idAddCampo == 503 ||this.idAddCampo == 557 || this.idAddCampo == 566 || this.idAddCampo == 549){
					luStringBuilder.append(this.getTextAreaUnRow(tuConnect));
				}else{
					luStringBuilder.append(this.getTextArea(tuConnect));
				}
				

			} else if (this.desTipoCampo.equals(TYPE_FLEXTABLE)) {
				if (this.getPrintFlextable(tuConnect) == 0
						&& this.isCheckBoxFlexTable(tuConnect) > 0) {
					lsFlexStyle = " style='display:none'";
				} else {
					lsFlexStyle = "";
				}

				if (pagina.useFlexTablesAsHtml) {

					if(lsFlexStyle.equals("")) {

						FlexTable ft = new FlexTable(this.idflexTbl);

						ft.atributo8 = "0"; // No mostrar busqueda

						//if(pagina.getPrintedTabName().equalsIgnoreCase("Administración y Vigilancia")) {
							ft.showTitle = true;
						//} else {
							//ft.showTitle = false;
						//}

						ft.showEditableCombosECS = false;

						luStringBuilder.append(ft.toHTML(pagina.request));

					}
				} else {

					luStringBuilder.append("<div id='flexTable_"
							+ this.idflexTbl + "' " + lsFlexStyle + "></div>");
					luStringBuilder.append("<div id='flexTableInnerForm_"
							+ this.idflexTbl + "'></div>");
					luStringBuilder
							.append("<script type='text/javascript'>loadFlexTabIni('"
									+ this.idflexTbl + "')</script>");

				}

			} else if (this.desTipoCampo.equals(TYPE_DATE)) {
				luStringBuilder.append("<div id='nom2_" + this.codCampo + "'>");
					luStringBuilder.append(getDate());
				luStringBuilder.append("</div>");

			} else if (this.desTipoCampo.equals(TYPE_CHECKBOX)) {
				
				if(isAC > 0){//JJAQ implementa que desaparesca aplica capital fijo y aplica capital variable con las A.C.
					if(this.idAddCampo != 1030 && this.idAddCampo != 1031){
						luStringBuilder.append("<div id='nom2_" + this.codCampo + "'>");
						luStringBuilder.append(this.getSingleCheckbox(tuConnect));
					    luStringBuilder.append("</div>");
					}
				}else{
					luStringBuilder.append("<div id='nom2_" + this.codCampo + "'>");
					luStringBuilder.append(this.getSingleCheckbox(tuConnect));
				    luStringBuilder.append("</div>");
				}
				

			} else if (this.desTipoCampo.equals(TYPE_LABEL)) {

			} else if (this.desTipoCampo.equals(TYPE_NUMERIC)) {
				//ULR 27/01/2017 no imprimir el capital fijo y capital variable en consulta si los checks no estan seleccionados
				if(this.idAddCampo==1028 || this.idAddCampo==1029){
					if(isAC == 1){
						//JJAQ para que no aparezca el valor de Capital Fijo o Mínimo, Capital Variable en consulta						
					}else if((tipoSoc.equals("isSdeRL") || tipoSoc.equals("isSA")|| tipoSoc.equals("isSAB") ||
							  tipoSoc.equals("isLLC") || tipoSoc.equals("isSLU") ||
							  tipoSoc.equals("isSAC") || tipoSoc.equals("isSAU") ||
							  tipoSoc.equals("isLTD") || tipoSoc.equals("isSL"))&& psEditable.equals("disabled")){
						//ULR para que no aparezca el valor de Capital Fijo o Mínimo, Capital Variable en consulta
					}else if((tipoSoc.equals("isSA")|| tipoSoc.equals("isSC") || tipoSoc.equals("isLLC") ||
							  tipoSoc.equals("isSLU") || tipoSoc.equals("isSAC") || tipoSoc.equals("isSAU") ||
							  tipoSoc.equals("isLTD") || tipoSoc.equals("isSL"))&& psEditable.equals("")){
						//ULR para inhabilitar el valor de Capital Fijo o Mínimo, Capital Variable en captura 29/03/2017 en los textField
						//luStringBuilder.append(this.getTextFieldNumericReadOnly(tuConnect));
					}else{
						ftSavHand = new FlexTableSaveHandler();
						CapitalFVBen capitalFVBen = ftSavHand.getCheckValuesFlex(Integer.parseInt(lstIdEmpresa));
						if(capitalFVBen.getCapFijo() == 0 && capitalFVBen.getCapVariable() == 0 && this.psEditable.equals("disabled")){
							luStringBuilder.append("");
						}else{
							luStringBuilder.append(this.getTextFieldNumeric(tuConnect));										
						}
					}
					
				}else if(this.idAddCampo == 541 && isAC == 1){
					//JJAQ para que no aparezca el valor de Capital Fijo o Mínimo, Capital Variable en consulta
				}else {
					luStringBuilder.append(this.getTextFieldNumeric(tuConnect));
				}
				
			} else if (this.desTipoCampo.equals(TYPE_CHECKBOX_D)) {
				if(this.idAddCampo != 1048){ //JJAQ No se va incluir En Trámite de Liquidación enECS
					luStringBuilder.append(getCheckBoxDiv(luConsultaDAO, lstIdEmpresa, this.idAddCampo));
				}
				

			} else if (this.desTipoCampo.equals(TYPE_CHECKBOX_E)) {
				luStringBuilder.append("<div id='nom2_" + this.codCampo + "'>");
				luStringBuilder.append(getSingleCheckboxEscritura(tuConnect));
				luStringBuilder.append("</div>");

			} else if (this.desTipoCampo.equals(TYPE_CHECKBOX_A)) {
				//luStringBuilder.append("<div id='nom2_" + this.codCampo + "'>");
				luStringBuilder.append(getSingleCheckboxAdmVig(tuConnect));
				//luStringBuilder.append("</div>");

			} else if (this.desTipoCampo.equals(TYPE_AJAX_PAGE)) {
				luStringBuilder.append(this.getAjaxPage(pagina));

			} else if (this.desTipoCampo.equals(TYPE_HREF)) {
				luStringBuilder.append(this.getHref());

			} else if (this.desTipoCampo.equals(TYPE_DATE_D)) {
				luStringBuilder.append(this.getDateDiv());

			} else if (this.desTipoCampo.equals(TYPE_SEMAFORO) ) {
				luStringBuilder.append(Image.getSemaforo(this.codCampo, this.valValor) );

			}else if (this.desTipoCampo.equals(TYPE_INPUT) ) {

	            //ECM 29 Agosto 2016
	            //Resumen General - Consulta - Info General - Admite Extranjeros
				if(this.idAddCampo == 526){
	                String lsAdmExt = luConsultaDAO.getAdmiteExtranjeros(Integer.parseInt(lstIdEmpresa));
	                if(lsAdmExt != null && lsAdmExt.equals("No")){
	                	luStringBuilder.append(this.getTextFieldValue(tuConnect));
	                }else
						luStringBuilder.append(this.getTextField(tuConnect));
				}else
					luStringBuilder.append(this.getTextField(tuConnect));

			}else {
				luStringBuilder.append(this.getTextField(tuConnect));
			}

		} else if (this.desTipoCampo.equals(TYPE_RADIO)
				|| this.desTipoCampo.equals(TYPE_RADIO_V)) {
			luStringBuilder.append(this.getRadioButtonList(tuConnect));

		} else if (this.desTipoCampo.equals(TYPE_SELECT)) {
			luStringBuilder.append("<div id="+this.codCampo+"_"+this.idCatalogo+">");
			String nomCatgo = this.getSelectList(tuConnect);
			luStringBuilder.append(nomCatgo);
			if (this.atributo1 != null) {
				String strSelected  = org.apache.commons.lang3.StringUtils.substringAfter(nomCatgo,"selected >");
				String strSeleccion = org.apache.commons.lang3.StringUtils.substringBefore(strSelected, "<");
				boolean isNumer = org.apache.commons.lang3.StringUtils.isNumeric(strSeleccion);
				if(this.psEditable.equals("disabled")){
					boolean isNumerCons = org.apache.commons.lang3.StringUtils.isNumeric(nomCatgo.trim());
					if(isNumerCons){
						luStringBuilder.append(" <label id='lblAtr1'>"+this.atributo1+"</label>");
					}
				}else{
					if(isNumer){
						luStringBuilder.append(" <label id='lblAtr1'>"+this.atributo1+"</label>");
					}else{
						luStringBuilder.append(" <label id='lblAtr1' style='display:none;'>"+this.atributo1+"</label>");
					}
					
				}
				
			}
			luStringBuilder.append("<div>");

		} else if (this.desTipoCampo.equals(TYPE_SELECT_PUPUP)) {
			luStringBuilder.append(this.getSelectPupUp(tuConnect));

		} else if (this.desTipoCampo.equals(TYPE_SELECT_IMG)) {
			luStringBuilder.append(this.getSelectListImg(tuConnect));

		}

		if (!tbPrePair) {
			/*ULR 21/12/2016 se añadio if para excluir los tds cuando el type_checkbox sea falso, se hizo de 
			 * esta manera ya que al añadir la condicion antes afectada los checkbox de captura*/ 
			if (this.psEditable!=null && this.psEditable!="" && (this.desTipoCampo.equals(TYPE_CHECKBOX)||this.desTipoCampo.equals(TYPE_CHECKBOX_D)) && !this.valValor.equals(this.codCampo)) {
				luStringBuilder.append("");
			}else{
				luStringBuilder.append("</td>");
			}
			//luStringBuilder.append("</td>");
		}
		
		return luStringBuilder.toString();

	}

	/**
	 * Crear componente web html Date.
	 * 
	 * @return String
	 */
	public String getDate() {
		String lsInputFecha = "<input type='text' name='" + this.codCampo
				+ "' id='" + this.codCampo + "' size='" + this.canTamannoCampo
				+ "' value='" + this.valValor + "'  " + psEditable
				//ULR 15/05/2017 se añadio onblur para evaluar fecha una vez que se termine de escribir
				+ " onblur='validarFecha(this)'"
				+ " onkeyup='javascript:getMascaraFecha(this);' >";

		if ((psEditable == null) || (psEditable.equals(""))) {
			return lsInputFecha
					+ JSCal.getCalendar(this.codCampo,
							(this.desFormula != null) ? this.desFormula
									.replace("onchange=", "").replace("\"", "")
									: "");
		} else {
			// return lsInputFecha;
			return this.valValor;
		}
	}

	/**
	 * Crear componente web html campo nï¿½merico.
	 * 
	 * @param tuConnect
	 * @return String
	 */
	public String getTextFieldNumeric(ConnectionWrapper tuConnect) {

		if (psEditable == null || psEditable.equals("")) {
			return "<input type='text' name='"
					+ this.codCampo
					+ "' id='"
					+ this.codCampo
					+ "' size='"
					+ this.canTamannoCampo
					+ "' value='"
					+ NumberFormatter.moneda(Double.parseDouble(this.valValor
							.equals("") || this.valValor == null ? "0"
							: this.valValor)) + "' " + this.desFormula + " "
					+ psEditable + " style='text-align: right;'>";
		} else {

			return "<div style='text-align: right; width:15%;'>"+NumberFormatter
					.moneda(Double.parseDouble(this.valValor.equals("")
							|| this.valValor == null ? "0" : this.valValor))+"<div>";
		}
	}
	
	public String getTextFieldNumericReadOnly(ConnectionWrapper tuConnect) {

		if (psEditable == null || psEditable.equals("")) {
			//psEditable ="readonly";
			return "<input type='text' name='"
					+ this.codCampo
					+ "' id='"
					+ this.codCampo
					+ "' size='"
					+ this.canTamannoCampo
					+ "' value='"
					+ NumberFormatter.moneda(Double.parseDouble(this.valValor
							.equals("") || this.valValor == null ? "0"
							: this.valValor)) + "' " + this.desFormula + " "
					+ psEditable + " style='text-align: right;' readonly='readonly'>";
		} else {

			return NumberFormatter
					.moneda(Double.parseDouble(this.valValor.equals("")
							|| this.valValor == null ? "0" : this.valValor));
		}
	}

	/**
	 * Crear componente web html campo de texto.
	 * 
	 * @param tuConnect
	 * @return String
	 */
	public String getTextField(ConnectionWrapper tuConnect) {
		if (psEditable.equals("disabled"))
			return this.valValor;
		else
			return "<input type='text' name='" + this.codCampo + "' id='"
					+ this.codCampo + "' size='" + this.canTamannoCampo
					+ "' value='" + this.valValor + "' " + "title='"
					+ this.valValor + "' " + "title='" + this.valValor + "' "
					+ this.desFormula + " " + psEditable + ">";
	}

	/**
	 * Crear componente web html campo de texto.
	 * 
	 * @param tuConnect
	 * @return String
	 */
	public String getTextFieldValue(ConnectionWrapper tuConnect) {
		if (psEditable.equals("disabled"))
			return "N/A";
		else
			return "<input type='text' name='" + this.codCampo + "' id='"
					+ this.codCampo + "' size='" + this.canTamannoCampo
					+ "' value='N/A' " + "title='"
					+ this.valValor + "' " + "title='" + this.valValor + "' "
					+ this.desFormula + " " + psEditable + ">";
	}

	/**
	 * Crear componente web html textarea.
	 * 
	 * @param tuConnect
	 * @return String
	 */
	public String getTextArea(ConnectionWrapper tuConnect) {

		if (psEditable == null || psEditable.equals("")) {

			return "<textarea name='" + this.codCampo + "' id='"
					+ this.codCampo + "' cols='" + this.canTamannoCampo
					+ "' rows='5' " + psEditable + ">" + this.valValor
					+ "</textarea>";
		} else {

			return this.valValor;
		}

	}
	
	/**
	 * Crear componente web html textarea con una fila.
	 * 
	 * @param tuConnect
	 * @return String
	 */
	public String getTextAreaUnRow(ConnectionWrapper tuConnect) {

		if (psEditable == null || psEditable.equals("")) {

			return "<textarea name='" + this.codCampo + "' id='"
					+ this.codCampo + "' cols='" + this.canTamannoCampo
					+ "' rows='1' " + psEditable + ">" + this.valValor
					+ "</textarea>";
		} else {

			return this.valValor;
		}

	}

	/**
	 * Crear componente web html checkbox.
	 * 
	 * @param tuConnect
	 * @return
	 */
	public String getSingleCheckbox(ConnectionWrapper tuConnect) {

		String lsChecked = "";

		if (this.valValor.equals(this.codCampo)) {
			lsChecked = " checked";
		}

	  if(psEditable == null || psEditable.equals("")){

		  return "<input type='checkbox' name='" + this.codCampo
				+ "' id='" + this.codCampo + "' size='" + this.canTamannoCampo
				+ "' value='" + this.codCampo + "' " + this.desFormula
				+ lsChecked + " " + psEditable + ">"+"<br>";

	  } else {
		  return "";//this.valValor.equals(this.codCampo)?"Si":"No";
	  }

	}

	public String getSingleCheckboxEscritura(ConnectionWrapper tuConnect) {

		String lsChecked = "";

		if (this.valValor.equals(this.codCampo)) {
			lsChecked = " checked";
		}

	  if(psEditable == null || psEditable.equals("")){

		  return "<input type='checkbox' name='" + this.codCampo
				+ "' id='" + this.codCampo + "' size='" + this.canTamannoCampo
				+ "' value='" + this.codCampo + "' " + this.desFormula
				+ lsChecked + " " + psEditable + ">"+"<br>";

	  } else {
		  return this.valValor.equals(this.codCampo)?"Si":"No";

	  }

	}

	public String getSingleCheckboxAdmVig(ConnectionWrapper tuConnect) {

		String lsChecked = "";

		if (this.valValor.equals(this.codCampo)) {
			lsChecked = " checked";
		}

	  if(psEditable == null || psEditable.equals("")){

		  return "<input type='checkbox' name='" + this.codCampo
				+ "' id='" + this.codCampo + "' size='" + this.canTamannoCampo
				+ "' value='" + this.codCampo + "' " + this.desFormula
				+ lsChecked + " " + psEditable + ">"+"<br>";

	  }else if(psEditable.equals("disabled")){
		  return "";
	  }else {
          //return this.valValor.equals(this.codCampo)?"Si":"No";
		  return "";
	  }

	}
	/**
	 * Crear componente web html RadioButton agrupado.
	 * 
	 * @param tuConnect
	 * @return
	 */
	public String getRadioButtonList(ConnectionWrapper tuConnect) {
		StringBuilder luStringBuilder = new StringBuilder();
		Catalogo luCatalogo = new Catalogo(this.idCatalogo);

		String txtValue = "";

		for (CatalogoValor luCatalogoValor : luCatalogo.getElementos(tuConnect)) {
			luStringBuilder.append("<label> ");
			luStringBuilder.append("	<input type='radio'" + "' id='"
					+ this.codCampo + "'");
			luStringBuilder.append("		name='" + this.codCampo + "'");
			luStringBuilder.append("         value='"
					+ luCatalogoValor.idCatalogoValor + "' " + this.desFormula
					+ " " + "ondblclick='uncheckme(this)'");

			if (Integer.toString(luCatalogoValor.idCatalogoValor).equals(
					this.valValor)) {
				luStringBuilder.append("checked");
				txtValue = luCatalogoValor.nomCatVal;
			}

			

			luStringBuilder.append(" " + psEditable);
			luStringBuilder.append(">");
			luStringBuilder.append(luCatalogoValor.nomCatVal);
			luStringBuilder.append("</label>");

			if (this.desTipoCampo.equals(TYPE_RADIO_V)) {
				luStringBuilder.append("<br>");
			}
		}
		
		luStringBuilder.append("<input type='radio' id ='"+ this.codCampo +"' name='"+ this.codCampo +"' value='' ondblclick='uncheckme(this)' style='display:none'/>");
		
		if ((psEditable == null) || (psEditable.equals(""))) {
			return luStringBuilder.toString();
		} else {

			return txtValue;
		}
	}

	/**
	 * Crear componente web html Combo para cambiar semï¿½foro.
	 * 
	 * @param tuConnect
	 * @return
	 */
	public String getSelectListImg(ConnectionWrapper tuConnect) {
		StringBuilder luStringBuilder = new StringBuilder();
		String lsSemaforoActual = "";
		Catalogo luCatalogo = new Catalogo(this.idCatalogo);
		String txtValue = "";

		luStringBuilder.append("<select name='" + this.codCampo + "' id='"
				+ this.codCampo + "' style='width:" + this.canTamannoCampo
				+ "px' " + this.desFormula + " " + psEditable + ">");
		luStringBuilder.append("<option value='0'>(Seleccione)</option>");

		for (CatalogoValor luCatalogoValor : luCatalogo.getElementos(tuConnect)) {
			luStringBuilder.append("<option value='"
					+ luCatalogoValor.valCatVal + "'");

			if (luCatalogoValor.valCatVal.equals(this.valValor)) {
				lsSemaforoActual = luCatalogoValor.valCatVal;
				luStringBuilder.append("selected");

				txtValue = luCatalogoValor.nomCatVal;
			}

			luStringBuilder.append(" >" + luCatalogoValor.nomCatVal
					+ "</option>");
		}

		luStringBuilder.append("</select>");

		if (psEditable != null && psEditable.equals("disabled")) {

			luStringBuilder = new StringBuilder();
			luStringBuilder.append(txtValue);
		}
		
		String lsTooltip = "";
		if(lsSemaforoActual != null && lsSemaforoActual.equals("button_green.png"))
			lsTooltip = "Verde";
		else if(lsSemaforoActual != null && lsSemaforoActual.equals("button_yellow.png"))
			lsTooltip = "Amarillo";
		else if(lsSemaforoActual != null && lsSemaforoActual.equals("button_red.png"))
			lsTooltip = "Rojo";

		//ECM 19 Abril 2016 - Que no aparezca la imagen nula de inicio en el semaforo ECS.
		//System.out.println("SemaforoActual: "+lsSemaforoActual);
		//if(lsSemaforoActual != null && !lsSemaforoActual.equals("0"))

		if(lsSemaforoActual == null || lsSemaforoActual.equals(""))
			luStringBuilder.append("<img id='im'>");
		else
			luStringBuilder.append("<img id='im' src='../../img/"+ lsSemaforoActual + "' title='"+lsTooltip+"'>");

		return luStringBuilder.toString();

	}

	/**
	 * Crear componente web html combo.
	 * 
	 * @param tuConnect
	 * @return
	 */
	public String getSelectList(ConnectionWrapper tuConnect) {

		StringBuilder luStringBuilder = new StringBuilder();
		Catalogo luCatalogo = new Catalogo(this.idCatalogo);

		luStringBuilder.append("<select name='" + this.codCampo + "' id='"
				+ this.codCampo + "' style='width:" + this.canTamannoCampo
				+ "px '" + this.desFormula + " " + psEditable + ">");
		if(this.idCatalogo == 1){}
		else
			luStringBuilder.append("<option value='0'>(Seleccione)</option>");

		boolean found = false;
		
		if (psEditable != null && psEditable.equals("")){
			for (CatalogoValor luCatalogoValor : luCatalogo.getElementosSinReflexion(tuConnect)) {
				luStringBuilder.append("<option value='"
						+ luCatalogoValor.idCatalogoValor + "' " + "title='"
						+ luCatalogoValor.valCatVal + "'");

				if (Integer.toString(luCatalogoValor.idCatalogoValor).equals(
						this.valValor)) {

					found = true;

					if (psEditable.equals("disabled")) {
						return luCatalogoValor.valCatVal.substring(0,
								luCatalogoValor.valCatVal.length());
					} else {
						luStringBuilder.append("selected");
					}
				}

				luStringBuilder.append(" >" + luCatalogoValor.valCatVal
						+ "</option>");
			}
			
		}else{
			for (CatalogoValor luCatalogoValor : luCatalogo.getElementos(tuConnect)) {
				luStringBuilder.append("<option value='"
						+ luCatalogoValor.idCatalogoValor + "' " + "title='"
						+ luCatalogoValor.valCatVal + "'");

				if (Integer.toString(luCatalogoValor.idCatalogoValor).equals(
						this.valValor)) {

					found = true;

					if (psEditable.equals("disabled")) {
						return luCatalogoValor.valCatVal.substring(0,
								luCatalogoValor.valCatVal.length());
					} else {
						luStringBuilder.append("selected");
					}
				}

				luStringBuilder.append(" >" + luCatalogoValor.valCatVal
						+ "</option>");
			}
		}

		

		luStringBuilder.append("</select>");

		if (psEditable.equals("disabled") && !found) {

			return "";
		}

		return luStringBuilder.toString();
	}

	public String getSelectPupUp(ConnectionWrapper tuConnect) {

		StringBuilder sb = new StringBuilder();

		Catalogo cat = new Catalogo(this.idCatalogo);

		String commaNames = "";

		String textValue = "";

		for (CatalogoValor catElem : cat.getElementos(tuConnect)) {

			if (Integer.toString(catElem.idCatalogoValor).equals(this.valValor)) {

				textValue = catElem.valCatVal;
			}
		}

		sb.append("<div id='" + this.codCampo + "_div'>" + textValue + "</div>");

		sb.append("<input type='hidden' name='" + this.codCampo + "' id='"
				+ this.codCampo + "' value='" + this.valValor + "'>");

		sb.append("<input type='button' value='Seleccionar' onclick=\"openSelectPupUp('"
				+ this.idCatalogo
				+ "','"
				+ this.codCampo
				+ "','"
				+ this.codCampo + "_div" + "', 'innerHTML', '')\">");

		return sb.toString();

	}

	/**
	 * Crear componente web html pï¿½ginas con ajax.
	 * 
	 * @return String
	 */
	public String getAjaxPage(Pagina pagina) {
		StringBuilder luStringBuilder = new StringBuilder();

		String url = "";

		url = this.atributo3;

		
		if (psEditable != null && !psEditable.equals("")) {

			if (this.atributo3.contains("consultaPoderes.jsp")) {

				if (pagina.getModo().equals("PRINT") || pagina.getModo().equals("PRINT_ALL")) {

					url = this.atributo3.replace(".jsp", "Print.jsp");
				} 
				/*else {

					url = this.atributo3.replace(".jsp", "consultaPoderes.jsp");
				}*/

			}

			if (this.atributo3.contains("reformas.jsp")) {

				if (pagina.getModo().equals("PRINT") || pagina.getModo().equals("PRINT_ALL")) {

					url = this.atributo3.replace(".jsp", "Print.jsp");
				} else {

					// url = this.atributo3.replace(".jsp", "Consulta.jsp");
				}

			}
		}

		if (pagina.useFlexTablesAsHtml) {

			try {

				String protocol = pagina.request.getScheme(); // http
				String serverName = pagina.request.getServerName(); // hostname.com

				int serverPort = pagina.request.getServerPort(); // 8081
				String contextPath = pagina.request.getContextPath(); // mywebapp

				String fullContextPath = protocol + "://" + serverName + ":"
						+ serverPort + contextPath;

				String content = WebPageReader.read(fullContextPath
						+ "/faces/jsp/captura/" + url + "?ID_EMPRESA="
						+ pagina.idEmpresa);

				//content = StringUtils.latin1ToUTF8(content);
				

				luStringBuilder.append(content);

			} catch (Exception ex) {

				luStringBuilder.append(ex.toString());
			}

		} else {

			
			if (psEditable != null && !psEditable.equals("")) {
				
				if(url.contains("apoderadosConsulta.jsp")) {
					
					luStringBuilder.append(
							"Escritura: "
							+ "<input id='searchEscritApodConsulta'> "
							+ "<input type='button' value='Buscar' onclick=\"setGlobalSearchEscritApodConsulta(document.getElementById('searchEscritApodConsulta').value);loadAjaxPage('"
										+ url + "','" + this.codCampo + "')\">"
							+ "<br><br>");
				}
			}
			
			luStringBuilder.append("<div id='" + this.codCampo + "' />");
			luStringBuilder
					.append("<script type='text/javascript'>loadAjaxPage('"
							+ url + "','" + this.codCampo + "')</script>");
		}

		return luStringBuilder.toString();

	}

	/**
	 * Crear componente web html lista de checkbox.
	 * 
	 * @param tuConnect
	 * @return
	 */
	public String getCheckBoxList(ConnectionWrapper tuConnect) {

		StringBuilder luStringBuilder = new StringBuilder();
		Catalogo luCatalogo = new Catalogo(this.idCatalogo);

		for (CatalogoValor catElem : luCatalogo.getElementos(tuConnect)) {
			luStringBuilder
					.append("<input type='checkbox' name='" + "CHCK"
							+ this.codCampo.substring(2) + "-"
							+ catElem.codCatVal + "' id='" + this.codCampo
							+ "-" + catElem.codCatVal + "' value='"
							+ catElem.idCatalogoValor + "' " + psEditable);

			luStringBuilder.append(" >" + catElem.nomCatVal + "<br>");
		}

		return luStringBuilder.toString();
	}

	/**
	 * Crear componente web html FlexTable.
	 * 
	 * @param tuConnect
	 * @return int
	 */
	public int getPrintFlextable(ConnectionWrapper tuConnect) {
		ResultSet luResultSet = null;
		PreparedStatement luPreparedStatement = null;
		int liCount = 0;
		String liIdEmpresa = FacesUtils.getSessionBean().getIdCurrentEmpresa();
		String lstSql = "";

		try {

			lstSql = "SELECT COUNT(*) FROM "
					+ "DERCORP_ADD_CAMPO_TAB CT "
					+ "LEFT JOIN DERCORP_ADD_CAMPO_VALOR_TAB CVT ON CVT.ID_ADD_CAMPO = CT.ID_ADD_CAMPO "
					+ "WHERE CT.DES_TIPO_CAMPO = 'CHECKBOX_A' "
					+ "AND   CT.DES_FORMULA LIKE '%flexTable_#ID_FLEXTBL#%' "
					+ "AND CVT.VAL_VALOR IS NOT NULL "
					+ "AND CVT.ID_EMPRESA = #ID_EMPRESA#";

			lstSql = lstSql.replaceAll("#ID_FLEXTBL#",
					String.valueOf(this.idflexTbl));

			lstSql = lstSql.replaceAll("#ID_EMPRESA#",
					String.valueOf(liIdEmpresa));

			luPreparedStatement = tuConnect.prepareStatement(lstSql);
			luResultSet = luPreparedStatement.executeQuery();

			while (luResultSet.next()) {
				liCount = luResultSet.getInt(1);
			}

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			try {
				luResultSet.close();
				luPreparedStatement.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}
		return liCount;
	}

	/**
	 * Validar si los checkbox de la flextable deben estar activados o
	 * desactivados.
	 * 
	 * @param tuConnect
	 * @return int
	 */
	public int isCheckBoxFlexTable(ConnectionWrapper tuConnect) {
		ResultSet luResultSet = null;
		PreparedStatement luPreparedStatement = null;
		int lintCount = 0;

		try {

			String lstSql = "SELECT COUNT(*) FROM "
					+ "DERCORP_FLEX_TBLS_TAB FT "
					+ "WHERE FT.ATRIBUTO1 = 'CHECK' "
					+ "AND   FT.ID_FLEX_TBL = #ID_FLEXTBL#";

			lstSql = lstSql.replaceAll("#ID_FLEXTBL#",
					String.valueOf(this.idflexTbl));

			luPreparedStatement = tuConnect.prepareStatement(lstSql);
			luResultSet = luPreparedStatement.executeQuery();

			while (luResultSet.next()) {
				lintCount = luResultSet.getInt(1);
			}

		} catch (Exception ex) {
			// TODO - Pendiente - Gestion de Excepciones
			ex.printStackTrace();

		} finally {
			try {
				luResultSet.close();
				luPreparedStatement.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}
		return lintCount;
	}

	/**
	 * Crear componente web html div y almacenar checkbox.
	 * 
	 * @return String
	 */
	public String getCheckBoxDiv(ConsultaDAO luConsultaDAO, String tIdEmpresa, int tiIdAddCampo) {
		String lsChecked = "";
		String lsCheck_Div = "";
		int liisCheck = 0;

		if (this.valValor.equals(this.codCampo)) {
			lsChecked = " checked";
		}
		if(psEditable == null || psEditable.equals("")){
			lsCheck_Div = "<div id=" + this.codCampo + ">"
					+ "<input type='checkbox' name='" + this.codCampo + "' id='"
					+ this.codCampo + "' size='" + this.canTamannoCampo
					+ "' value='" + this.codCampo + "'" + lsChecked
					+">" + this.nomCampo + "</div>";
	
			return lsCheck_Div;
		}else{
			liisCheck = luConsultaDAO.getActCheck(tIdEmpresa, tiIdAddCampo);

			if(liisCheck == 1)
				lsCheck_Div = "<div id=" + this.codCampo + ">"
						+ "<input type='checkbox' name='" + this.codCampo + "' id='"
						+ this.codCampo + "' size='" + this.canTamannoCampo
						+ "' value='" + this.codCampo + "'" + lsChecked + " "
						+ psEditable + ">" + this.nomCampo + "</div>";
			else				
				lsCheck_Div = "";//No ha sido seleccionado el checkbox de ECS.

			return lsCheck_Div;
		}
	}

	public String getCheckBoxAdmVig(ConsultaDAO luConsultaDAO, String tIdEmpresa, int tiIdAddCampo) {
		String lsChecked = "";
		String lsCheck_Div = "";
		int liisCheck = 0;

		if (this.valValor.equals(this.codCampo)) {
			lsChecked = " checked";
		}
		if(psEditable == null || psEditable.equals("")){
			lsCheck_Div = "<div id=" + this.codCampo + ">"
					+ "<input type='checkbox' name='" + this.codCampo + "' id='"
					+ this.codCampo + "' size='" + this.canTamannoCampo
					+ "' value='" + this.codCampo + "'" + lsChecked
					+">" + this.nomCampo + "</div>";
	
			return lsCheck_Div;
		}else{
			liisCheck = luConsultaDAO.getActCheck(tIdEmpresa, tiIdAddCampo);

			if(liisCheck == 1)
				lsCheck_Div = "<div id=" + this.codCampo + ">"
						+ "<input type='checkbox' name='" + this.codCampo + "' id='"
						+ this.codCampo + "' size='" + this.canTamannoCampo
						+ "' value='" + this.codCampo + "'" + lsChecked + " "
						+ psEditable + ">" + this.nomCampo + "</div>";
			else				
				lsCheck_Div = "";//No ha sido seleccionado el checkbox de ECS.

			return lsCheck_Div;
		}
	}
	

	/**
	 * Crear componente web html href.
	 * 
	 * @return
	 */
	public String getHref() {
		StringBuilder luStringBuilder = new StringBuilder();

		if (psEditable == null || psEditable.equals("")) {
			luStringBuilder.append("<input type='text' name='" + this.codCampo
					+ "' id='" + this.codCampo + "' size='"
					+ this.canTamannoCampo + "' value='" + this.valValor
					//+ "' maxlength='" + this.canTamannoCampo
					+ "' "
					+ this.atributo4 + ">");
		} else {

			luStringBuilder.append("<input type='hidden' name='"
					+ this.codCampo + "' id='" + this.codCampo + "' size='"
					+ this.canTamannoCampo + "' value='" + this.valValor
					+ "' maxlength='" + this.canTamannoCampo + "' "
					+ this.atributo4 + ">");

			luStringBuilder.append(this.valValor);
		}

		luStringBuilder
				.append("<a href='"
						+ this.desFormula
						+ "'><abbr title='Obtener Documento'><img alt='Obtener Documento' src='"
						+ this.atributo3 + "'></abbr> </a> ");

		return luStringBuilder.toString();

	}

	/**
	 * Crear campo Date con Div
	 * 
	 * @return String
	 */
	public String getDateDiv() {

		if (psEditable == null || psEditable.equals("")) {
			String lsInputFecha = "";
			lsInputFecha = "<div id='ele_"
					+ this.codCampo
					+ "'>"
					+ "<input type='text' name='"
					+ this.codCampo
					+ "' id='"
					+ this.codCampo
					+ "' size='"
					+ this.canTamannoCampo
					+ "' value='"
					+ this.valValor
					+ "'  "
					+ psEditable
					//ULR 15/05/2017 se añadio onblur para evaluar fecha una vez que se termine de escribir
					+ " onblur='validarFecha(this)'"
					+ " onkeyup='javascript:getMascaraFecha(this);'>"
					+ lsInputFecha
					+ JSCal.getCalendar(this.codCampo,
							(this.desFormula != null) ? this.desFormula
									.replace("onchange=", "").replace("\"", "")
									: "") + "</div>";

			return lsInputFecha;

		} else {
            if (lsconsultarECSSem != null && lsconsultarECSSem.equals("button_green"
            		+ ".png") )
            	return "";
            else
            	return this.valValor;
		}
	}
}
