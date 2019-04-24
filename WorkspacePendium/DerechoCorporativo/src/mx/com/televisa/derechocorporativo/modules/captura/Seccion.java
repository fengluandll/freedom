/*
* Project: Derecho Corporativo
*
* File: Seccion.java
*
* Created on: Agosto 31, 2015 at 12:00
*
* Copyright (c) - Televisa - 2015
*/

package mx.com.televisa.derechocorporativo.modules.captura;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;
import mx.com.televisa.derechocorporativo.data.packages.DERCORP_CONSULTA_PKG;
import mx.com.televisa.derechocorporativo.reflexion.ReflexionUtil;
import mx.com.televisa.derechocorporativo.util.FacesUtils;

/**
 * 
 * @author ernesto1
 *
 */
public class Seccion {

	/*
	int seccion_id;
	String codigo;
	String nombre;
	String descripcion;
	 */

	protected int		idSeccion;
	protected String	nomSeccion;
	protected String	codSeccion;
	protected String	desSeccion;
	
	public Seccion(ResultSet luResultSet, ResultSetMetaData luResultSetMetaData) throws Exception {

		ReflexionUtil.fillObject(luResultSet, luResultSetMetaData, this, Seccion.class);
	}
	
	
	public boolean equals(Object o){
		
	    if(o instanceof Seccion){
	    	Seccion toCompare = (Seccion) o;
	    	//return this.idSeccion.equals(toCompare.idSeccion);
	    	return (this.idSeccion == toCompare.idSeccion);
	    }
	    return false;
	}
	

	public String toHTML(ConnectionWrapper tuConnectionWrapper, EmpresaValues empVal, Pagina pagina) {
        
		StringBuilder         luStringBuilder = new StringBuilder();
		
        String lstIdEmpresa = FacesUtils.getSessionBean().getIdCurrentEmpresa();
        String psEditable = FacesUtils.getSessionBean().getEditCon();

        
        ArrayList<SubSeccion> laSubSecciones = null;
        
        if(psEditable != null && psEditable.equals("disabled")){

        	laSubSecciones  = getSubSeccionesConInfo(tuConnectionWrapper, lstIdEmpresa);
        } else {
        	
        	laSubSecciones  = getSubSecciones(tuConnectionWrapper);
        }

        ArrayList<SubSeccion> visibleSubSeccionList = empVal.getVisibleSubSeccionList(laSubSecciones);

        luStringBuilder.append("<table width='100%' align='center' border='0' id='subSectionTableContainer'>");
        
        if(psEditable != null && psEditable.equals("disabled")){
        	if(!pagina.getModo().equals("PRINT") && !pagina.getModo().equals("PRINT_ALL")) {
                luStringBuilder.append("<tr><td>&nbsp;</td></tr>	");
                luStringBuilder.append("<tr>");
                luStringBuilder.append("<td>");

                luStringBuilder.append("<div align='right'><a href='#' onclick=\"openPrintTab('" + this.idSeccion + "')\">Imprimir esta Pesta�a</a></div>");

                luStringBuilder.append("</td>");                                             
                luStringBuilder.append("</tr>");                                                	
                
        	}
        }

        int liIdTable = 0;

        for (SubSeccion luSubSeccion : laSubSecciones) {
        	String hideText = "";
			if(!visibleSubSeccionList.contains(luSubSeccion)) {				
				hideText = " style='display:none'";
				//luStringBuilder.append("<!-- Ocultando la Seccion de ECS -->");
			}
			//ULR 26/01/2017 sea grego condicion para chequear si es asociacion civil y no mostrar subseccion Acciones/part sociales en consulta y
			//version imprimible asi como estatus en escritura constitutiva
			if(luSubSeccion.idSeccion==7 && luSubSeccion.idSubSeccion==18 && psEditable != null && psEditable.equals("disabled") && empVal.isAsociacionCivil==true){
				luStringBuilder.append("");
			}else if(luSubSeccion.idSeccion==21 && luSubSeccion.idSubSeccion==51 && psEditable != null && psEditable.equals("disabled")){
				luStringBuilder.append("");
			}else if(luSubSeccion.idSeccion==21 && luSubSeccion.idSubSeccion==52){
				luStringBuilder.append("");
			}else{
				luStringBuilder.append("<tr>");
	            luStringBuilder.append("<td>");
	            if(luSubSeccion.idSubSeccion==19 && psEditable != null && psEditable.equals("disabled")){
	            	luStringBuilder.append("<div id='div_tbl_stat_5' style='display:none'><legend>"+luSubSeccion.nomSubSeccion+"&nbsp;<label style='cursor:pointer;' onclick=\"hideShowContactos('fieldset_tbl_stat_5','btn_max_tbl_stat_5', 'btn_min_tbl_stat_5')\"><img id='btn_max_tbl_stat_5' src='/DerechoCorporativo/img/fleca_abajo_32.png'></label></legend></div>");
	            }
	            if(luSubSeccion.idSubSeccion==19 && psEditable != null && psEditable.equals("disabled")){
	                luStringBuilder.append("<fieldset id='fieldset_tbl_stat_5' " + hideText + " style=''>");
	            }else{
	            	luStringBuilder.append("<fieldset" + hideText + ">");
	            }
	            luStringBuilder.append("<legend>");
	            if(luSubSeccion.idSubSeccion==19 && psEditable != null && psEditable.equals("disabled")){
	            	luStringBuilder.append(luSubSeccion.nomSubSeccion+"&nbsp;<label style='cursor:pointer;' onclick=\"hideShowContactos('div_tbl_stat_5','btn_max_tbl_stat_5', 'btn_min_tbl_stat_5')\"><img id='btn_min_tbl_stat_5' src='/DerechoCorporativo/img/fleca_arriba_32.png'></label>");
	            }else{
	            	luStringBuilder.append(luSubSeccion.nomSubSeccion);
	            }
	            luStringBuilder.append("</legend>");
	            luStringBuilder.append("<table width='100%' border='0' id='tbl_stat_"+liIdTable+"' name='tbl_stat_"+liIdTable+"'>");
	            
	
	            //ECM 24 Octubre 2016 
	            if(psEditable !=null && psEditable.equals("disabled") && luSubSeccion.idSeccion == 20 && luSubSeccion.idSubSeccion == 30){
	
	            }else{
					//Fila que determina las posiciones de los campos
		            luStringBuilder.append("<tr>");
		            luStringBuilder.append("<td width='17%'></td>");
		            luStringBuilder.append("<td width='8%'></td>");
		            luStringBuilder.append("<td width='8%'></td>");
		            luStringBuilder.append("<td width='17%'></td>");
		            luStringBuilder.append("<td width='17%'></td>");
		            luStringBuilder.append("<td width='8%'></td>");
		            luStringBuilder.append("<td width='8%'></td>");
		            luStringBuilder.append("<td width='17%'></td>");
		            luStringBuilder.append("</tr>");
	            }
	
	            //ECM 24 Octubre 2016 
	            if(psEditable !=null && psEditable.equals("disabled") && luSubSeccion.idSeccion == 20 && luSubSeccion.idSubSeccion == 30){
	
	            }else{
		            luStringBuilder.append("<tr>");
		            luStringBuilder.append("<td colspan='8'>");
		            luStringBuilder.append((luSubSeccion.desSubSeccion != null)? luSubSeccion.desSubSeccion: "");
		            luStringBuilder.append("</td>");
		            luStringBuilder.append("</tr>");
	            }
	
	            //ECM 11 Abril 2016 Caramelo Status y referencia documentum.
	            if(luSubSeccion.idSeccion == 21 && luSubSeccion.idSubSeccion == 52 && luSubSeccion.nomSubSeccion.equals("Status") ||
	            	luSubSeccion.idSeccion == 21 && luSubSeccion.idSubSeccion == 51 && luSubSeccion.nomSubSeccion.equals("Referencia Documentum") ){
	            	luStringBuilder.append(luSubSeccion.toHTML(tuConnectionWrapper, pagina, "class='tableRow2'"));
	
	            //ECM 24 Octubre 2016 
	            }else if(psEditable !=null && psEditable.equals("disabled") && luSubSeccion.idSeccion == 20 && luSubSeccion.idSubSeccion == 30){
	                    luStringBuilder.append(luSubSeccion.toHTML(psEditable, tuConnectionWrapper, pagina));
	
	            }else{
	            	luStringBuilder.append(luSubSeccion.toHTML(tuConnectionWrapper, pagina));
	            }
	
	            luStringBuilder.append("<tr><td>&nbsp;</td><td>&nbsp;</td></tr>	");
	
	            luStringBuilder.append("</table>");
	            luStringBuilder.append("</fieldset>");
	            luStringBuilder.append("</td>");
	            luStringBuilder.append("</tr>");
	            
	            liIdTable++;
				 if(laSubSecciones.size() > liIdTable){
					 luStringBuilder.append("<tr><td>&nbsp;</td></tr>");
				            }
			}//cerrar if de validacion asoc civil
		}
       
        luStringBuilder.append("</table>");

		return luStringBuilder.toString();

	}

	public ArrayList<SubSeccion> getSubSecciones(ConnectionWrapper tuConnectionWrapper) {
		
        ArrayList<SubSeccion> laSubSecciones      = new ArrayList<SubSeccion>();
		ResultSet             luResultSet         = null;
		CallableStatement     luCallableStatement = null;

		try {
			luCallableStatement = tuConnectionWrapper.prepareCall(DERCORP_CONSULTA_PKG.GET_SUB_SECCIONES_PR);
			luCallableStatement.setInt(1, this.idSeccion);
			luCallableStatement.registerOutParameter(2, OracleTypes.CURSOR);
			luCallableStatement.execute();
			luResultSet = ((OracleCallableStatement)luCallableStatement).getCursor(2);

			while (luResultSet.next()) {
				laSubSecciones.add(new SubSeccion(luResultSet, luResultSet.getMetaData()));
			}

		} catch (Exception ex) {
			// TODO - Pendiente - Gestion de Excepciones
			ex.printStackTrace();

		} finally {
			ConnectionWrapper.closeAll(luResultSet, luCallableStatement);

			/*try{
				set.close();
				psmt.close();
			} catch (Exception ex){ 
				ex.printStackTrace();
			}*/

		}

		return laSubSecciones;
	}
	
	public ArrayList<SubSeccion> getSubSeccionesConInfo(ConnectionWrapper tuConnectionWrapper, String idEmpresa) {
		
        ArrayList<SubSeccion> laSubSecciones      = new ArrayList<SubSeccion>();
		ResultSet             luResultSet         = null;
		CallableStatement     luCallableStatement = null;

		try {
			luCallableStatement = tuConnectionWrapper.prepareCall(DERCORP_CONSULTA_PKG.GET_SUB_SECCIONES_CON_INFO_PR);
			
			luCallableStatement.setInt(1, this.idSeccion);
			luCallableStatement.setString(2, idEmpresa);
			
			luCallableStatement.registerOutParameter(3, OracleTypes.CURSOR);
			luCallableStatement.execute();
			luResultSet = ((OracleCallableStatement)luCallableStatement).getCursor(3);

			while (luResultSet.next()) {
				laSubSecciones.add(new SubSeccion(luResultSet, luResultSet.getMetaData()));
			}

		} catch (Exception ex) {
			// TODO - Pendiente - Gestion de Excepciones
			ex.printStackTrace();

		} finally {
			ConnectionWrapper.closeAll(luResultSet, luCallableStatement);

			/*try{
				set.close();
				psmt.close();
			} catch (Exception ex){ 
				ex.printStackTrace();
			}*/

		}

		return laSubSecciones;
	}
	
	

	public static ArrayList<SubSeccion> getSubSecciones(int idSeccion, ConnectionWrapper tuConnectionWrapper) {
		ArrayList<SubSeccion> 	subSecciones = new ArrayList<SubSeccion>();
		ResultSet 				set = null;
		CallableStatement 		stmt 		= null;

		try {
			stmt = tuConnectionWrapper.prepareCall(DERCORP_CONSULTA_PKG.GET_SUB_SECCIONES_PR);
			stmt.setInt(1, idSeccion);
			stmt.registerOutParameter(2, OracleTypes.CURSOR);
			stmt.execute();
			set = ((OracleCallableStatement)stmt).getCursor(2);
			
			while (set.next()) {
				subSecciones.add(new SubSeccion(set, set.getMetaData()));
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			ConnectionWrapper.closeAll(set, stmt);
		}

		return subSecciones;

	}
	
	

	public int getIdSeccion() {
		return idSeccion;
	}

	public void setIdSeccion(int idSeccion) {
		this.idSeccion = idSeccion;
	}

	public String getNomSeccion() {
		return nomSeccion;
	}

	public void setNomSeccion(String nomSeccion) {
		this.nomSeccion = nomSeccion;
	}

	public String getCodSeccion() {
		return codSeccion;
	}

	public void setCodSeccion(String codSeccion) {
		this.codSeccion = codSeccion;
	}

	public String getDesSeccion() {
		return desSeccion;
	}

	public void setDesSeccion(String desSeccion) {
		this.desSeccion = desSeccion;
	}
}
