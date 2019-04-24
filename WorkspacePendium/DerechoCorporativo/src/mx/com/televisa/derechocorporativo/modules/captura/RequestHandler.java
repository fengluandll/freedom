/*
* Project: Derecho Corporativo
*
* File: RequestHandler.java
*
* Created on: Agosto 31, 2015 at 12:00
*
* Copyright (c) - Televisa - 2015
*/

package mx.com.televisa.derechocorporativo.modules.captura;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Enumeration;

import javax.servlet.ServletRequest;

import oracle.jdbc.OracleTypes;
import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;
import mx.com.televisa.derechocorporativo.data.packages.DERCORP_CAPTURA_PKG;
import mx.com.televisa.derechocorporativo.data.packages.XXTV_CAPITAL_SOC_PKG;
import mx.com.televisa.derechocorporativo.util.FacesUtils;
import mx.com.televisa.derechocorporativo.model.Catalog;


public class RequestHandler {
	
	/**
	 * 
	 * 
	 */
	private static String pistDenomActualNew;
	private static String pistNombreCortoNew;
	private static String pistCtaOracleNew;
	private static String pistActividadNew;
	private static String pistGiroNew;
	private static String pistDivisionNew;
	private static String pistSegResponsableNew;
	private static String pistClasificacionNew;
	private static String pistFecClasificacionNew ;
	private static String pistPaisNew;
	private static String pistAdmiteExtNew;
	private static String pistDomicilioSocNew;
	private static String pistTieneInmuebleNew;
	private static String pistDuracionNew;
	private static String pistFecInicialNew;
	private static String pistFecFinalNew;
	private static String pistConsContableNew;
	private static String pistTipoSociedadNew;
	private static String pistAuditoresExtNew;
	
	public static String handleRequest(ServletRequest tuServletRequest) {
		int liIdEmpresa = Integer.parseInt(FacesUtils.getSessionBean().getIdCurrentEmpresa());
		String lsEscritura  		= null;
		String lstTipoPoder 		= null;
		String lstGrupoApoderado	= null;
		String lstNumOrden 		 	= null;
		String lsIdSeccion = tuServletRequest.getParameter("idSeccion");

		if(tuServletRequest.getParameter("submit") != null 
				&& tuServletRequest.getParameter("submit").equals("Guardar Cambios")) {

			ConnectionWrapper   luConnectionWrapper = null;
			ResultSet           luResultSet         = null;
			CallableStatement   luCallableStatement = null;
			int                 linUserId           = Integer.parseInt(FacesUtils.getSessionBean().getIdUser());
			Enumeration<String> lnParamNames        = tuServletRequest.getParameterNames();

			try {
				luConnectionWrapper = new ConnectionWrapper();
				if(lsIdSeccion.equals("19") || lsIdSeccion.equals("7")){//7 seccion Resumen General para parte social
					luCallableStatement = luConnectionWrapper.prepareCall(DERCORP_CAPTURA_PKG.DELETE_CHECKBOX_INFO_PR);
					luCallableStatement.setInt(1,liIdEmpresa);
					luCallableStatement.setObject(2, lsIdSeccion);
					luCallableStatement.execute();
					luCallableStatement.close();

				}else if(lsIdSeccion.equals("20")){
					luCallableStatement = luConnectionWrapper.prepareCall(DERCORP_CAPTURA_PKG.DELETE_CHECKBOX_ADM_VIG_PR);
					luCallableStatement.setInt(1,liIdEmpresa);
					luCallableStatement.execute();
					luCallableStatement.close();

				}else if(lsIdSeccion.equals("21")){
					luCallableStatement = luConnectionWrapper.prepareCall(DERCORP_CAPTURA_PKG.DELETE_CHECKBOX_ESCRITURA_PR );
					luCallableStatement.setInt(1,liIdEmpresa);
					luCallableStatement.execute();
					luCallableStatement.close();
				}
				
				//}else if(lsIdSeccion.equals("19")){JJAQ se quito porque no funciona bien
									
				//}

				luCallableStatement = luConnectionWrapper.prepareCall(DERCORP_CAPTURA_PKG.SAVE_INFO_PR);

				while (lnParamNames.hasMoreElements()) {
					String lsParamName = lnParamNames.nextElement();
					String lsValue     = tuServletRequest.getParameter(lsParamName);

					if(lsParamName.contains("selectEscritura")){
						lsEscritura = lsValue.trim();
					}
					if(lsParamName.contains("selectTipoPoder")){
						lstTipoPoder = lsValue.trim();
					}
					if(lsParamName.contains("selectGrupoApo")){
						lstGrupoApoderado = lsValue.trim();
					}
					if(lsParamName.contains("txtNumOrden")){
						lstNumOrden = lsValue.trim();
					}
					if(lsParamName.contains("C62")){
						//System.out.println("C62: "+lsValue);
						lsValue = lsValue.replaceAll(",", "");
						lsValue = lsValue.replace("$", "");
						//System.out.println("C62: "+lsValue);
					}
					if(lsParamName.contains("VAL_C21")){//Objeto Social
						//System.out.println("VAL_C21: "+lsValue);
						lsValue = "";
						//System.out.println("VAL_C21: "+lsValue);
					}
					
					if(lsParamName.equals("C1")){
						
						CallableStatement luCs = luConnectionWrapper.prepareCall(DERCORP_CAPTURA_PKG.DERCORP_TEST_FLASH);
						luCs.setInt(1,liIdEmpresa);
						luCs.setInt(2,linUserId);
						luCs.setString(3,lsValue.trim());
						luCs.execute();
						luCs.close();
					}
					//Para monitoreo de Informacion General
					if(lsParamName.equals("C1")){
						pistDenomActualNew = lsValue.trim();
					}
					if(lsParamName.equals("C2")){
						pistNombreCortoNew = lsValue.trim();
					}
					if(lsParamName.equals("C3")){
						pistCtaOracleNew = lsValue.trim();
					}
					if(lsParamName.equals("C4")){
						pistActividadNew = lsValue.trim();
					}
					if(lsParamName.equals("C5")){
						pistGiroNew = lsValue.trim();
					}
					if(lsParamName.equals("C6")){
						pistDivisionNew = lsValue.trim();
					}
					if(lsParamName.equals("C7")){
						pistSegResponsableNew = lsValue.trim();
					}
					if(lsParamName.equals("C8")){
						pistClasificacionNew = lsValue.trim();
					}
					if(lsParamName.equals("C9")){
						pistFecClasificacionNew = lsValue.trim();
					}
					if(lsParamName.equals("C10")){
						pistPaisNew = lsValue.trim();
					}
					if(lsParamName.equals("C11")){
						pistAdmiteExtNew = lsValue.trim();
					}
					if(lsParamName.equals("C12")){
						pistDomicilioSocNew = lsValue.trim();
					}
					if(lsParamName.equals("C13")){
						pistTieneInmuebleNew = lsValue.trim();
					}
					if(lsParamName.equals("C14")){
						pistDuracionNew = lsValue.trim();
					}
					if(lsParamName.equals("C15")){
						pistFecInicialNew = lsValue.trim();
					}
					if(lsParamName.equals("C16")){
						pistFecFinalNew = lsValue.trim();
					}
					if(lsParamName.equals("C17")){
						pistConsContableNew = lsValue.trim();
					}
					if(lsParamName.equals("C18")){
						pistTipoSociedadNew = lsValue.trim();
					}
					if(lsParamName.equals("C1052")){
						pistAuditoresExtNew = lsValue.trim()	;
					}
					//System.out.println("Val: "+lsParamName  + ": " + lsValue);

					luCallableStatement.setInt(1,liIdEmpresa);
					luCallableStatement.setString(2, lsParamName);
					luCallableStatement.setString(3, lsValue);
					luCallableStatement.setInt(4,linUserId);
					luCallableStatement.addBatch();

					//System.out.println(liIdEmpresa+" "+lsParamName+" "+lsValue+" "+linUserId);
				}
				/*
				System.out.println("EXECUTE MONITOREO INFO GENERAL");
				CallableStatement luCs1 = luConnectionWrapper.prepareCall(DERCORP_CAPTURA_PKG.MONITOR_RESUMEN_GENERAL_PR);
				luCs1.setInt(1,liIdEmpresa);
				luCs1.setInt(2,linUserId);
				luCs1.setString(3,pistDenomActualNew);
				luCs1.setString(4,pistNombreCortoNew);
				luCs1.setString(5,pistCtaOracleNew);
				luCs1.setString(6,pistActividadNew);
				luCs1.setString(7,pistGiroNew);
				luCs1.setString(8,pistDivisionNew);
				luCs1.setString(9,pistSegResponsableNew);
				luCs1.setString(10,pistClasificacionNew);
				luCs1.setString(11,pistFecClasificacionNew);
				luCs1.setString(12,pistPaisNew);
				luCs1.setString(13,pistAdmiteExtNew);
				luCs1.setString(14,pistDomicilioSocNew);
				luCs1.setString(15,pistTieneInmuebleNew);
				luCs1.setString(16,pistDuracionNew);
				luCs1.setString(17,pistFecInicialNew);
				luCs1.setString(18,pistFecFinalNew);
				luCs1.setString(19,pistConsContableNew);
				luCs1.setString(20,pistTipoSociedadNew);
				luCs1.setString(21,pistAuditoresExtNew);
				luCs1.execute();
				luCs1.close();
				System.out.println("EXECUTE BATCH");*/

	            int[] laUpdateCounts = luCallableStatement.executeBatch();
	            // para recacular estructura cuando se cambia el valor nominal en resumen general
	            if(lsIdSeccion.equals("7")){
	            	luCallableStatement = luConnectionWrapper.prepareCall(XXTV_CAPITAL_SOC_PKG.REFRESH_ACCIONES_ESC_PR);
	            	luCallableStatement.setInt(1,liIdEmpresa);
	            	luCallableStatement.execute();
	            	luCallableStatement.close();
	            }
	            
	            
	            //ECM 15 Julio 2015
	            luCallableStatement = luConnectionWrapper.prepareCall(DERCORP_CAPTURA_PKG.UNFORMAT_VAL_CAMPO_NUMERIC_PR);
				luCallableStatement.setInt(1,liIdEmpresa);
				luCallableStatement.execute();
				//luCallableStatement.close();

				//ECM 18 Noviembre 2015
				luCallableStatement = luConnectionWrapper.prepareCall(DERCORP_CAPTURA_PKG.SAVE_MONEDA_PR);
				luCallableStatement.setInt(1, liIdEmpresa);
				luCallableStatement.execute();
				//luCallableStatement.close();

				//ECM 26 Febrero 2016
				luCallableStatement = luConnectionWrapper.prepareCall(DERCORP_CAPTURA_PKG.SAVE_SEMAFORO_PR);
				luCallableStatement.setInt(1, liIdEmpresa);
				luCallableStatement.execute();
				//luCallableStatement.close();

				//ICL 03/03/2016 Apoderados // JJAQ 12/05/2016 Orden Apoderados
				Catalog.copyDataToApoderadosFinal(Integer.valueOf(liIdEmpresa), 
												  lsEscritura,
												  lstTipoPoder,
												  lstGrupoApoderado,
												  lstNumOrden);
				
				//ECM 05/MAYO/2016 - Captura - Resumen  General - Nombre Corto
				luCallableStatement = luConnectionWrapper.prepareCall(DERCORP_CAPTURA_PKG.CAMBIAR_NOMBRE_CORTO_PR);
				luCallableStatement.setInt(1, liIdEmpresa);
				luCallableStatement.execute();
				//luCallableStatement.close();

				//ECM 12/Mayo/2016 - Captura - Administracion - Borrar registros de flex que no aparecen.
				luCallableStatement = luConnectionWrapper.prepareCall(DERCORP_CAPTURA_PKG.BORRAR_REG_FLEX_ADM_PR);
				luCallableStatement.setInt(1, liIdEmpresa);
				luCallableStatement.execute();
				//luCallableStatement.close();
				
			    /*
			    ECM 13 Mayo 2016 
			    Captura - Resumen General - Capital Social 
			    Borrar porcentaje de participacion cuando Socio Externo es 'No'
			    */
				luCallableStatement = luConnectionWrapper.prepareCall(DERCORP_CAPTURA_PKG.BORRAR_PORCENTAJE_PAR_PR);
				luCallableStatement.setInt(1, liIdEmpresa);
				luCallableStatement.execute();
				//luCallableStatement.close();
				
				if(lsIdSeccion.equals("19")){
				/*
				 * ECM 16 Agosto 2016
				 * Estructura de Capital Social
				 * Permitir capturar los campos de capital fijo y variable cuando no haya monto.
				 */
				luCallableStatement = luConnectionWrapper.prepareCall(DERCORP_CAPTURA_PKG.RECALCULAR_CAM_CAP_ECS_PR);
				luCallableStatement.setInt(1, liIdEmpresa);
				luCallableStatement.registerOutParameter(2, OracleTypes.VARCHAR);
				luCallableStatement.execute();	
				}

			} catch(Exception ex) {
	
				return ex.toString();
				
			} finally {
				
				ConnectionWrapper.closeAll(luResultSet, luCallableStatement, luConnectionWrapper);
			}

			return "<div align='center' class='infoMessageText'>Se guardaron los cambios correctamente!<div>";
		
		} else {			
			return "";
		}
	}
}