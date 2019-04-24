/*
* Project: Derecho Corporativo
*
* File: SubSeccion.java
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
import java.util.TreeMap;

import org.apache.log4j.Logger;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import mx.com.televisa.derechocorporativo.daos.ConsultaDAO;
import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;
import mx.com.televisa.derechocorporativo.data.packages.DERCORP_CONSULTA_PKG;
import mx.com.televisa.derechocorporativo.reflexion.ReflexionUtil;
import mx.com.televisa.derechocorporativo.util.FacesUtils;

/**
 * 
 * @author ernesto1
 *
 */
public class SubSeccion {
	final static Logger log = Logger.getLogger(SubSeccion.class);
	protected int    idSubSeccion;
	protected int    idSeccion;
	protected String codSubSeccion;
	protected String nomSubSeccion;
	protected String desSubSeccion;

	public SubSeccion(ResultSet tuResultSet, ResultSetMetaData tuResultSetMetaData)
			throws Exception {

		ReflexionUtil.fillObject(tuResultSet, tuResultSetMetaData, this, SubSeccion.class);
	}

	public boolean equals(Object o){
		
	    if(o instanceof SubSeccion){
	    	SubSeccion toCompare = (SubSeccion) o;
	    	//return this.idSubSeccion.equals(toCompare.idSubSeccion);
	    	return (this.idSubSeccion == toCompare.idSubSeccion);
	    }
	    return false;
	}

	public ArrayList<Agrupaciones> getAgrupaciones(ConnectionWrapper connect){
        ArrayList<Agrupaciones> laAgrupaciones      = new ArrayList<Agrupaciones>(); 
        ResultSet               luResultSet         = null;
        CallableStatement       luCallableStatement = null;

		try{
            luCallableStatement = connect.prepareCall(DERCORP_CONSULTA_PKG.GET_AGRUPACIONES_PR);
            luCallableStatement.setInt(1, this.idSeccion);
            luCallableStatement.setInt(2, this.idSubSeccion);
            luCallableStatement.registerOutParameter(3, OracleTypes.CURSOR);
            luCallableStatement.execute();
            luResultSet = ((OracleCallableStatement) luCallableStatement).getCursor(3);
          
            while (luResultSet.next()) {
        		laAgrupaciones.add(new Agrupaciones(luResultSet, luResultSet.getMetaData()));
			}
        } catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			//ConnectionWrapper.closeAll(luResultSet,       luCallableStatement);
			
			/*
			try{
				set.close();
				psmt.close();
			} catch (Exception ex){ 
				ex.printStackTrace();
			}*/

		}
		return laAgrupaciones;
	}

    //ECM 24 Octubre 2016
	public ArrayList<Agrupaciones> getAgrupacionesConFlex(ConnectionWrapper connect){
        ArrayList<Agrupaciones> laAgrupaciones      = new ArrayList<Agrupaciones>(); 
        ResultSet               luResultSet         = null;
        CallableStatement       luCallableStatement = null;

		try{
			int liIdEmpresa = Integer.parseInt( FacesUtils.getSessionBean().getIdCurrentEmpresa() );

            luCallableStatement = connect.prepareCall(DERCORP_CONSULTA_PKG.GET_AGRUPACIONES_CON_FLEX_PR);
            luCallableStatement.setInt(1, this.idSeccion);
            luCallableStatement.setInt(2, this.idSubSeccion);
            luCallableStatement.registerOutParameter(3, OracleTypes.CURSOR);
            luCallableStatement.setInt(4, liIdEmpresa);
            luCallableStatement.execute();
            luResultSet = ((OracleCallableStatement) luCallableStatement).getCursor(3);

			while (luResultSet.next()) {
				laAgrupaciones.add(new Agrupaciones(luResultSet, luResultSet.getMetaData()));
			}

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {

		}
		return laAgrupaciones;
	}


	public ArrayList<Campo> getCampos(ConnectionWrapper tuConnectionWrapper) {
        int               liIdEmpresa         = Integer.parseInt(FacesUtils.getSessionBean().getIdCurrentEmpresa());
        ArrayList<Campo>  laCampos            = new ArrayList<Campo>();
        ResultSet         luResultSet         = null;
        CallableStatement luCallableStatement = null;

		try {
			luCallableStatement = tuConnectionWrapper.prepareCall(DERCORP_CONSULTA_PKG.GET_CAMPOS_PR);
			luCallableStatement.setInt(1, liIdEmpresa);
			luCallableStatement.setInt(2, this.idSubSeccion);
			log.info("idSubSeccion: "+idSubSeccion);
			luCallableStatement.registerOutParameter(3, OracleTypes.CURSOR);
			luCallableStatement.execute();
			luResultSet = ((OracleCallableStatement)luCallableStatement).getCursor(3);

			while (luResultSet.next()) {
		
				
				laCampos.add(new Campo(luResultSet.getInt("id_add_campo"), 
						luResultSet.getString("cod_campo"), 
						luResultSet.getString("nom_campo"), 
						luResultSet.getString("des_campo"), 
						luResultSet.getString("des_tipo_campo"), 
						luResultSet.getString("can_tamanno_campo"), 
						luResultSet.getInt("id_catalogo"), 
						luResultSet.getInt("id_seccion"),   
						luResultSet.getInt("id_subseccion") ,
						luResultSet.getString("VAL_VALOR"), 
						luResultSet.getInt("id_agrupacion"), 
						luResultSet.getInt("id_flex_tbl"), 
						luResultSet.getString("atributo1"), 
						luResultSet.getString("des_formula"), 
						luResultSet.getString("atributo3"), 
						luResultSet.getString("atributo4"), 
						luResultSet.getString("atributo5")));
			}

		} catch (Exception ex) {
			// TODO - Pendiente - Gestion de Excepciones
			ex.printStackTrace();

		} finally {			
            //ConnectionWrapper.closeAll(luResultSet, luCallableStatement);
			/*
			try{
				set.close();
				psmt.close();
			} catch (Exception ex){ 
				ex.printStackTrace();
			}*/

		}

		return laCampos;
	}
	
	

	//24 Octubre 2016
	public ArrayList<Campo> getCamposConFlex(ConnectionWrapper tuConnectionWrapper) {
        int               liIdEmpresa         = Integer.parseInt(FacesUtils.getSessionBean().getIdCurrentEmpresa());
        ArrayList<Campo>  laCampos            = new ArrayList<Campo>();
        ResultSet         luResultSet         = null;
        CallableStatement luCallableStatement = null;

		try {
			luCallableStatement = tuConnectionWrapper.prepareCall(DERCORP_CONSULTA_PKG.GET_CAMPOS_CON_FLEX_PR);
			luCallableStatement.setInt(1, liIdEmpresa);
			luCallableStatement.setInt(2, this.idSubSeccion);
			luCallableStatement.registerOutParameter(3, OracleTypes.CURSOR);
			luCallableStatement.execute();
			luResultSet = ((OracleCallableStatement)luCallableStatement).getCursor(3);

			while (luResultSet.next()) {
				laCampos.add(new Campo(luResultSet, luResultSet.getMetaData()));
			}

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {			

		}

		return laCampos;
	}


 	public String toHTML(ConnectionWrapper tuConnectionWrapper, Pagina pagina) {
        StringBuilder           luStringBuilder = new StringBuilder();
        ArrayList<Agrupaciones> laAgrupaciones  = getAgrupaciones(tuConnectionWrapper);
        ArrayList<Campo>        laCampos        = getCampos(tuConnectionWrapper);
        String                  lsBgcolor       = "";

		int countEmpty=0;
		for(Agrupaciones luAgrupacion:laAgrupaciones){
			luStringBuilder.append("<tr bgcolor='" + lsBgcolor +"'>");
			int liCampoIndex = 1;
			for (Campo campo : laCampos) {		
				
				if(campo.idAgrupacion == luAgrupacion.idAgrupacion){
					String  lsColspan1 = "";
					String  lsColspan2 = "";
					boolean bPrePair   = false;
					boolean bPair      = false;

					if(luAgrupacion.countCampos == 1 && luAgrupacion.idAgrupacion==17 && this.idSeccion==19){
						lsColspan1 = "2";
                        lsColspan2 = "2";
                        bPrePair = false;
                        bPair = false;
					} else if(luAgrupacion.countCampos == 1) {
                        lsColspan1 = "2";
                        lsColspan2 = "6";
                        bPrePair = false;
                        bPair = false;

					} else if(luAgrupacion.countCampos == 2) {						
                        lsColspan1 = "2";
                        lsColspan2 = "2";
                        bPrePair = false;
                        bPair = false;

					}
					else if(luAgrupacion.countCampos == 3 && luAgrupacion.isPair.equals("NO")) {						
						if(liCampoIndex == 1) {
							lsColspan1 = "1";
							lsColspan2 = "2";
							bPrePair = false;
							bPair = false;
							
						}
						else if(liCampoIndex == 2) {
							lsColspan1 = "1";
							lsColspan2 = "1";
							bPrePair = false;
							bPair = false;
							
						}
						else if(liCampoIndex == 3) {
							lsColspan1 = "2";
							lsColspan2 = "1";
							bPrePair = false;
							bPair = false;
						}

					}
					else if(luAgrupacion.countCampos == 3 && luAgrupacion.isPair.equals("YES")) {
					    if(liCampoIndex == 1) {
						    lsColspan1 = "2";
                            lsColspan2 = "2";
                            bPrePair = false;
                            bPair = false;

						}
					    else if(liCampoIndex == 2) {
							lsColspan1 = "2";
							lsColspan2 = "2";
							bPrePair = true;
							bPair = false;

						}
					    else if(liCampoIndex == 3) {
							lsColspan1 = "0";
							lsColspan2 = "0";
							bPrePair = false;
							bPair = true;
						}
					}
					else {
						System.out.println("Cantidad no soportada de campos en una agrupacion1!!!!");
					}
					
					
					/*String cCampo =campo.toHTML(luAgrupacion, lsBgcolor,tuConnectionWrapper, lsColspan1, lsColspan2, bPrePair, bPair, pagina);
					*/
					//ULR contador de checks vacios
					
					String campoSTR = campo.toHTML(luAgrupacion, lsBgcolor,tuConnectionWrapper, lsColspan1, lsColspan2, bPrePair, bPair, pagina); 
					if(campoSTR.contains("<i></i>")){
						countEmpty++;
					}
					//ULR cuando los checks esten vacios mostrar la etiqueta N/A
					if(countEmpty==4){
						luStringBuilder.append("<td>N/A</td>");
						countEmpty=0;
					}else{
						luStringBuilder.append(campoSTR);
					}
					
					//luStringBuilder.append(campo.toHTML(luAgrupacion, lsBgcolor,tuConnectionWrapper, lsColspan1, lsColspan2, bPrePair, bPair, pagina));					
					liCampoIndex++;
					
				}
				
			}			
			luStringBuilder.append("</tr>");
		}
		return luStringBuilder.toString();
	}

 	//ECM 24 Octubre 2016
 	public String toHTML(String tsEditable, ConnectionWrapper tuConnectionWrapper, Pagina tPagina) {
        StringBuilder           luStringBuilder = new StringBuilder();
        
        ArrayList<Agrupaciones> laAgrupaciones  = getAgrupacionesConFlex(tuConnectionWrapper);
        
        ArrayList<Campo>        laCampos        = getCamposConFlex(tuConnectionWrapper);
       
		String                  lsBgcolor       = "";

		for(Agrupaciones luAgrupacion:laAgrupaciones){
					luStringBuilder.append("<tr bgcolor='" + lsBgcolor +"'>");
					int liCampoIndex = 1;

					for (Campo campo : laCampos) {
						if(campo.idAgrupacion == luAgrupacion.idAgrupacion){
							String  lsColspan1 = "";
							String  lsColspan2 = "";
							boolean bPrePair   = false;
							boolean bPair      = false;

							if(luAgrupacion.countCampos == 1) {
		                        lsColspan1 = "2";
		                        lsColspan2 = "6";
		                        bPrePair = false;
		                        bPair = false;

							}else if(luAgrupacion.countCampos == 2) {
		                        lsColspan1 = "2";
		                        lsColspan2 = "2";
		                        bPrePair = false;
		                        bPair = false;
		
							}else if(luAgrupacion.countCampos == 3 && luAgrupacion.isPair.equals("NO")) {						
								if(liCampoIndex == 1) {
									lsColspan1 = "1";
									lsColspan2 = "2";
									bPrePair = false;
									bPair = false;
									
								}else if(liCampoIndex == 2) {
									lsColspan1 = "1";
									lsColspan2 = "1";
									bPrePair = false;
									bPair = false;
									
								}else if(liCampoIndex == 3) {
									lsColspan1 = "2";
									lsColspan2 = "1";
									bPrePair = false;
									bPair = false;
								}
		
							}else if(luAgrupacion.countCampos == 3 && luAgrupacion.isPair.equals("YES")) {
							    if(liCampoIndex == 1) {
								    lsColspan1 = "2";
		                            lsColspan2 = "2";
		                            bPrePair = false;
		                            bPair = false;
		
								}
							    else if(liCampoIndex == 2) {
									lsColspan1 = "2";
									lsColspan2 = "2";
									bPrePair = true;
									bPair = false;
		
								}
							    else if(liCampoIndex == 3) {
									lsColspan1 = "0";
									lsColspan2 = "0";
									bPrePair = false;
									bPair = true;
								}
							}
							else {
								System.out.println("Cantidad no soportada de campos en una agrupacion2!!!!");
							}
							
							luStringBuilder.append(campo.toHTML(luAgrupacion, lsBgcolor,tuConnectionWrapper, lsColspan1, lsColspan2, bPrePair, bPair, tPagina));					
							liCampoIndex++;
							
						}
					}			
					luStringBuilder.append("</tr>");

		}
		return luStringBuilder.toString();
	}

    //ECM 11 Abril 2016 Caramelo Status y referencia documentum.
 	public String toHTML(ConnectionWrapper tuConnectionWrapper, Pagina pagina, String tTableRow2) {
        StringBuilder           luStringBuilder = new StringBuilder();
        ArrayList<Agrupaciones> laAgrupaciones  = getAgrupaciones(tuConnectionWrapper);
        ArrayList<Campo>        laCampos        = getCampos(tuConnectionWrapper);
		String                  lsBgcolor       = "";
		String                  lsTableRow2		= "";
		int                     liIdRow         = 0;
		boolean                 isConsulta      = false;
		ConsultaDAO             luConsultaDAO   = new ConsultaDAO();
		int                     liStatus        = 0;

		//ECM 29 Abril 2016 - Consulta - Escritura Constitutiva - Mostrar u Ocultar Status.
		String psEditable = FacesUtils.getSessionBean().getEditCon();
		String lstIdEmpresa = FacesUtils.getSessionBean().getIdCurrentEmpresa();
		
		liStatus = luConsultaDAO.getStatusEscCons(lstIdEmpresa, "1024");

		if ((psEditable == null) || (psEditable.equals("")))
			isConsulta      = false;
		else
			isConsulta      = true;

		for(Agrupaciones luAgrupacion:laAgrupaciones){
			
			luStringBuilder.append("<tr id='Stat_"+ liIdRow +"' " + lsTableRow2 +" name='Stat_"+ liIdRow +"'>");

			int liCampoIndex = 1;

			for (Campo campo : laCampos) {
				if(isConsulta && liStatus == 0 && this.idSubSeccion == 52 && liIdRow > 1){
					
				}else
						if(campo.idAgrupacion == luAgrupacion.idAgrupacion){
							String  lsColspan1 = "";
							String  lsColspan2 = "";
							boolean bPrePair   = false;
							boolean bPair      = false;

							if(luAgrupacion.countCampos == 1) {
		                        lsColspan1 = "2";
		                        lsColspan2 = "6";
		                        bPrePair = false;
		                        bPair = false;
		
							}
							else if(luAgrupacion.countCampos == 2) {
		                        lsColspan1 = "2";
		                        lsColspan2 = "2";
		                        bPrePair = false;
		                        bPair = false;
		
							}
							else if(luAgrupacion.countCampos == 3 && luAgrupacion.isPair.equals("NO")) {						
								if(liCampoIndex == 1) {
									lsColspan1 = "1";
									lsColspan2 = "2";
									bPrePair = false;
									bPair = false;
									
								}
								else if(liCampoIndex == 2) {
									lsColspan1 = "1";
									lsColspan2 = "1";
									bPrePair = false;
									bPair = false;
									
								}
								else if(liCampoIndex == 3) {
									lsColspan1 = "2";
									lsColspan2 = "1";
									bPrePair = false;
									bPair = false;
								}
		
							}
							else if(luAgrupacion.countCampos == 3 && luAgrupacion.isPair.equals("YES")) {
							    if(liCampoIndex == 1) {
								    lsColspan1 = "2";
		                            lsColspan2 = "2";
		                            bPrePair = false;
		                            bPair = false;
		
								}
							    else if(liCampoIndex == 2) {
									lsColspan1 = "2";
									lsColspan2 = "2";
									bPrePair = true;
									bPair = false;
		
								}
							    else if(liCampoIndex == 3) {
									lsColspan1 = "0";
									lsColspan2 = "0";
									bPrePair = false;
									bPair = true;
								}
							}
							else {
								System.out.println("Cantidad no soportada de campos en una agrupacion3!!!!");
							}
							luStringBuilder.append(campo.toHTML(luAgrupacion, lsBgcolor,tuConnectionWrapper, lsColspan1, lsColspan2, bPrePair, bPair, pagina));					
							liCampoIndex++; 
						}
			}		
			luStringBuilder.append("</tr>");

			if(lsTableRow2.equals("")){
				lsTableRow2 = tTableRow2;
			}else{
				lsTableRow2 = "";
			}
			liIdRow++;

		}
		return luStringBuilder.toString();
	}

 	
 
 	
	public int getIdSubSeccion() {
		return idSubSeccion;
	}

	public void setIdSubSeccion(int idSubSeccion) {
		this.idSubSeccion = idSubSeccion;
	}

	public int getIdSeccion() {
		return idSeccion;
	}

	public void setIdSeccion(int idSeccion) {
		this.idSeccion = idSeccion;
	}

	public String getCodSubSeccion() {
		return codSubSeccion;
	}

	public void setCodSubSeccion(String codSubSeccion) {
		this.codSubSeccion = codSubSeccion;
	}

	public String getNomSubSeccion() {
		return nomSubSeccion;
	}

	public void setNomSubSeccion(String nomSubSeccion) {
		this.nomSubSeccion = nomSubSeccion;
	}

	public String getDesSubSeccion() {
		return desSubSeccion;
	}

	public void setDesSubSeccion(String desSubSeccion) {
		this.desSubSeccion = desSubSeccion;
	}
}