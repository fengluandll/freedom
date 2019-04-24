package mx.com.televisa.derechocorporativo.modules.reports.ecs;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

import mx.com.televisa.derechocorporativo.daos.ConsultaDAO;
import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;
import mx.com.televisa.derechocorporativo.data.packages.DERCORP_CONSULTA_PKG;
import mx.com.televisa.derechocorporativo.modules.captura.Agrupaciones;
import mx.com.televisa.derechocorporativo.modules.captura.Pagina;
import mx.com.televisa.derechocorporativo.reflexion.ReflexionUtil;
import mx.com.televisa.derechocorporativo.util.FacesUtils;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

import org.apache.log4j.Logger;

public class ReporteECSubseccion {

	final static Logger log = Logger.getLogger(ReporteECSubseccion.class);
	protected int    idSubSeccion;
	protected int    idSeccion;
	protected String codSubSeccion;
	protected String nomSubSeccion;
	protected String desSubSeccion;
	
	public ReporteECSubseccion(){
		
	}

	public ReporteECSubseccion(ResultSet tuResultSet, ResultSetMetaData tuResultSetMetaData)
			throws Exception {

		ReflexionUtil.fillObject(tuResultSet, tuResultSetMetaData, this, ReporteECSubseccion.class);
	}

	public boolean equals(Object o){
		
	    if(o instanceof ReporteECSubseccion){
	    	ReporteECSubseccion toCompare = (ReporteECSubseccion) o;
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


	public ArrayList<ReporteECSCampo> getCampos(ConnectionWrapper tuConnectionWrapper,String idEmpresa) {
        int               			liIdEmpresa         = Integer.parseInt(idEmpresa);
        ArrayList<ReporteECSCampo>  laCampos            = new ArrayList<ReporteECSCampo>();
        ResultSet         			luResultSet         = null;
        CallableStatement 			luCallableStatement = null;

		try {
			luCallableStatement = tuConnectionWrapper.prepareCall(DERCORP_CONSULTA_PKG.GET_CAMPOS_PR);
			luCallableStatement.setInt(1, liIdEmpresa);
			luCallableStatement.setInt(2, this.idSubSeccion);
			//log.info("idSubSeccion: "+idSubSeccion);
			luCallableStatement.registerOutParameter(3, OracleTypes.CURSOR);
			luCallableStatement.execute();
			luResultSet = ((OracleCallableStatement)luCallableStatement).getCursor(3);

			while (luResultSet.next()) {
		
				
				laCampos.add(new ReporteECSCampo(luResultSet.getInt("id_add_campo"), 
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
	public ArrayList<ReporteECSCampo> getCamposConFlex(ConnectionWrapper tuConnectionWrapper) {
        int               liIdEmpresa         = Integer.parseInt(FacesUtils.getSessionBean().getIdCurrentEmpresa());
        ArrayList<ReporteECSCampo>  laCampos            = new ArrayList<ReporteECSCampo>();
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
				laCampos.add(new ReporteECSCampo(luResultSet, luResultSet.getMetaData()));
			}

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {			

		}

		return laCampos;
	}


 	public String toHTML(ConnectionWrapper tuConnectionWrapper, Pagina pagina,String idEmpresa) {
        StringBuilder           luStringBuilder = new StringBuilder();
        ArrayList<Agrupaciones> laAgrupaciones  = getAgrupaciones(tuConnectionWrapper);
        ArrayList<ReporteECSCampo>        laCampos        = getCampos(tuConnectionWrapper,idEmpresa);
        String                  lsBgcolor       = "";

		int countEmpty=0;
		int liCampoIndex = 0;
		for(Agrupaciones luAgrupacion:laAgrupaciones){
			luStringBuilder.append("<tr bgcolor='" + lsBgcolor +"'>");
			liCampoIndex = 1;
			for (ReporteECSCampo campo : laCampos) {		
				
				if(campo.idAgrupacion == luAgrupacion.idAgrupacion){
					String  lsColspan1 = "";
					String  lsColspan2 = "";
					boolean bPrePair   = false;
					boolean bPair      = false;

					if(luAgrupacion.countCampos == 1 && luAgrupacion.idAgrupacion==17 && this.idSeccion==19){
						lsColspan1 = "1";
                        lsColspan2 = "1";
                        bPrePair = false;
                        bPair = false;
					} else if(luAgrupacion.countCampos == 1) {
                        lsColspan1 = "1";
                        lsColspan2 = "2";
                        bPrePair = false;
                        bPair = false;

					} else if(luAgrupacion.countCampos == 2) {						
                        lsColspan1 = "1";
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
					
					String campoSTR = campo.toHTML(luAgrupacion, lsBgcolor,tuConnectionWrapper, lsColspan1, lsColspan2, bPrePair, bPair, pagina,idEmpresa); 
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
