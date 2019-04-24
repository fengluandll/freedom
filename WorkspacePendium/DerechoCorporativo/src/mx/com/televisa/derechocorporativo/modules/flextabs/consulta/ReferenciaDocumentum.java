package mx.com.televisa.derechocorporativo.modules.flextabs.consulta;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import mx.com.televisa.derechocorporativo.bean.ReferenciaDocumentumBean;
import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;

public class ReferenciaDocumentum {

	private ConnectionWrapper 	puConnectionWrapper;
	private CallableStatement   puCallableStatement;
	private Connection 		    puConnection;
	private ResultSet           puResultSet;
	private PreparedStatement   puPreparedStatement;

	public ReferenciaDocumentum(ConnectionWrapper 	tuConnectionWrapper){
		puConnectionWrapper = tuConnectionWrapper;
		puCallableStatement = null;
		puConnection        = null;
		puResultSet         = null;
		puPreparedStatement = null;

	}

	public String doQueryPodGenEspRefDoc(int... tFiltros){

		StringBuilder lsRemovePodGenEspRefDoc = new StringBuilder();
		ReferenciaDocumentumBean refDocBean = new ReferenciaDocumentumBean();

		StringBuilder lsSql = new StringBuilder(  
				        "SELECT   VAL_C23 AS MEMO "             +
		                        ",VAL_C43 AS DOCUMENTO_ENTREGA "+
                                ",VAL_C29 AS OTROS "            +
						"FROM     DERCORP_METATBL_TAB " +
						"WHERE    1=1 "                 +
						"AND      ID_META_ROW = ? "     +
						"AND      ID_FLEX_TBL = ? "     +
						"AND      ID_EMPRESA  = ? "
						)
        ;

		try {
			puPreparedStatement = puConnectionWrapper.prepareStatement(lsSql.toString());
			puPreparedStatement.setInt(1, tFiltros[0] );
			puPreparedStatement.setInt(2, tFiltros[1] );
			puPreparedStatement.setInt(3, tFiltros[2] );
			puResultSet = puPreparedStatement.executeQuery();

			while(puResultSet.next()){
				refDocBean.setMemo(puResultSet.getString("MEMO"));
				refDocBean.setDocEnt(puResultSet.getString("DOCUMENTO_ENTREGA"));
				refDocBean.setOtros(puResultSet.getString("OTROS"));

			}

		}catch(SQLException sqle){
			sqle.printStackTrace();
			sqle.getSQLState();

		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();

		}

		if (refDocBean.getMemo() != null && refDocBean.getMemo().equals("No"))
			lsRemovePodGenEspRefDoc.append(removeMemoPodGenEsp());

		if(refDocBean.getDocEnt() != null && refDocBean.getDocEnt().equals("No"))
			lsRemovePodGenEspRefDoc.append(removeDocEntPodGenEsp());

		if(refDocBean.getOtros() != null && refDocBean.getOtros().equals("No"))
			lsRemovePodGenEspRefDoc.append(removeOtrosPodGenEsp());
		
		if( (refDocBean.getMemo() != null && refDocBean.getMemo().equals("No")) &&
			(refDocBean.getDocEnt() != null && refDocBean.getDocEnt().equals("No")) &&
			(refDocBean.getOtros() != null && refDocBean.getOtros().equals("No"))
		)
			lsRemovePodGenEspRefDoc.append("AND COD_FLEX_COLUM <> 'VAL_C22' ");

		return lsRemovePodGenEspRefDoc.toString();
	}
	
	private String removeMemoPodGenEsp(){
		StringBuilder lsRemoveMemo = new StringBuilder(
									"AND COD_FLEX_COLUM <> 	'VAL_C23' " +                    
									"AND COD_FLEX_COLUM <> 	'VAL_C24' " +
									"AND COD_FLEX_COLUM <> 	'VAL_C25' " +
									"AND COD_FLEX_COLUM <> 	'VAL_C26' " +
									"AND COD_FLEX_COLUM <> 	'VAL_C27' " +
									"AND COD_FLEX_COLUM <> 	'VAL_C28' "
		                            );
		return lsRemoveMemo.toString();
	}
	
	private String removeDocEntPodGenEsp(){
		StringBuilder lsRemoveDocEnt = new StringBuilder(
									"AND COD_FLEX_COLUM <> 	'VAL_C43' " +
									"AND COD_FLEX_COLUM <> 	'VAL_C44' " +
							        "AND COD_FLEX_COLUM <> 	'VAL_C45' " +
							        "AND COD_FLEX_COLUM <> 	'VAL_C46' "
		);
		return lsRemoveDocEnt.toString();
	}
	
	private String removeOtrosPodGenEsp(){
		StringBuilder lsRemoveOtros = new StringBuilder(
				                    "AND COD_FLEX_COLUM <> 	'VAL_C29' " +
									"AND COD_FLEX_COLUM <> 	'VAL_C30' " +
									"AND COD_FLEX_COLUM <> 	'VAL_C31' " +
									"AND COD_FLEX_COLUM <> 	'VAL_C32' "
		);
		return lsRemoveOtros.toString();
	}
	
	public String doQueryRefTotRefDoc(int... tiFiltros){
		
		StringBuilder lsRemoveRefTotRefDoc = new StringBuilder(); 
		ReferenciaDocumentumBean refDocBean = new ReferenciaDocumentumBean();
		StringBuilder lsSQL = new StringBuilder(
									"SELECT     VAL_C19 AS MEMO "     +
									          ",VAL_C24 AS ACT_ASAM " +
									          ",VAL_C27 AS DOC_ENT "  +
									          ",VAL_C32 AS CONV "     +
									"FROM     DERCORP_METATBL_TAB "   +
									"WHERE    1=1 "                   +
									"AND      ID_META_ROW = ? "       +
									"AND      ID_FLEX_TBL = ? "       +
									"AND      ID_EMPRESA  = ? "
		);

		try {
			puPreparedStatement = puConnectionWrapper.prepareStatement( lsSQL.toString() );
			puPreparedStatement.setInt(1, tiFiltros[0]);
			puPreparedStatement.setInt(2, tiFiltros[1]);
			puPreparedStatement.setInt(3, tiFiltros[2]);
			puResultSet = puPreparedStatement.executeQuery();

			while(puResultSet.next()){
				refDocBean.setMemo(puResultSet.getString("MEMO"));
				refDocBean.setActaAsamblea(puResultSet.getString("ACT_ASAM"));
				refDocBean.setDocEnt(puResultSet.getString("DOC_ENT"));
				refDocBean.setConvocatoria(puResultSet.getString("CONV"));

			}

		}catch(SQLException sqle){
			sqle.printStackTrace();
			sqle.getStackTrace();

		}catch (Exception e) {
			e.printStackTrace();
			e.getStackTrace();

		}

		if (refDocBean.getMemo() != null && refDocBean.getMemo().equals("No"))
			lsRemoveRefTotRefDoc.append( removeMemoRefTot() );

        if (refDocBean.getActaAsamblea() != null && refDocBean.getActaAsamblea().equals("No"))
        	lsRemoveRefTotRefDoc.append( removeActaAsambleaRefTot() );

        if(refDocBean.getDocEnt() != null && refDocBean.getDocEnt().equals("No"))
        	lsRemoveRefTotRefDoc.append( removeDocEntRefTot() );

        if(refDocBean.getConvocatoria() != null && refDocBean.getConvocatoria().equals("No"))
        	lsRemoveRefTotRefDoc.append(removeConvocatoriaRefTot());

        if( (refDocBean.getMemo() != null && refDocBean.getMemo().equals("No")) &&
        	(refDocBean.getActaAsamblea() != null && refDocBean.getActaAsamblea().equals("No")) &&
        	(refDocBean.getDocEnt() != null && refDocBean.getDocEnt().equals("No")) &&
        	(refDocBean.getConvocatoria() != null && refDocBean.getConvocatoria().equals("No"))
          )
        	lsRemoveRefTotRefDoc.append("AND COD_FLEX_COLUM <> 'VAL_C18' ");

		return lsRemoveRefTotRefDoc.toString();
	}

    private String removeMemoRefTot(){
    	StringBuilder lsRemoveMemo = new StringBuilder(
    			                            "AND COD_FLEX_COLUM <> 	'VAL_C19' " +
											"AND COD_FLEX_COLUM <> 	'VAL_C56' " +
											"AND COD_FLEX_COLUM <> 	'VAL_C20' " +
											"AND COD_FLEX_COLUM <> 	'VAL_C21' " +
											"AND COD_FLEX_COLUM <> 	'VAL_C22' " +
											"AND COD_FLEX_COLUM <> 	'VAL_C23' "
        );
    	return lsRemoveMemo.toString();
    }
    private String removeActaAsambleaRefTot(){
    	StringBuilder lsRemoveActaAsam = new StringBuilder(
			                            "AND COD_FLEX_COLUM <> 	'VAL_C24' " +
                                        "AND COD_FLEX_COLUM <> 	'VAL_C26' "
        );
    	return lsRemoveActaAsam.toString();
    }
    private String removeDocEntRefTot(){
    	StringBuilder lsRemoveDocEnt = new StringBuilder(
    			                            "AND COD_FLEX_COLUM <> 	'VAL_C27' " +
											"AND COD_FLEX_COLUM <> 	'VAL_C28' " +
											"AND COD_FLEX_COLUM <> 	'VAL_C29' " +
											"AND COD_FLEX_COLUM <> 	'VAL_C31' "
        );
    	return lsRemoveDocEnt.toString();
    }
    private String removeConvocatoriaRefTot(){
    	StringBuilder lsRemoveConvocatoria = new StringBuilder(
    	    "AND COD_FLEX_COLUM <> 	'VAL_C32' " +
            "AND COD_FLEX_COLUM <> 	'VAL_C33' " +
            "AND COD_FLEX_COLUM <> 	'VAL_C35' "
        );
    	return lsRemoveConvocatoria.toString();
    }
    
    public String doQueryRefParRefDoc(int... tiFiltros){
    	ReferenciaDocumentumBean refDocBean = new ReferenciaDocumentumBean();
    	StringBuilder lsRemoveRefPar = new StringBuilder();

    	StringBuilder lsSQL = new StringBuilder(
								"SELECT   VAL_C10 AS MEMO "         +
								        ",VAL_C15 AS ACTA "         +
								        ",VAL_C18 AS DOC_ENT "      +
								        ",VAL_C23 AS CONVOCATORIA " +
								"FROM     DERCORP_METATBL_TAB "     +
								"WHERE    1=1 "                     +
								"AND      ID_META_ROW = ? "         +
								"AND      ID_FLEX_TBL = ? "         +
								"AND      ID_EMPRESA  = ? "
        );

    	try {
			puPreparedStatement = puConnectionWrapper.prepareStatement( lsSQL.toString() );
			puPreparedStatement.setInt(1, tiFiltros[0]);
			puPreparedStatement.setInt(2, tiFiltros[1]);
			puPreparedStatement.setInt(3, tiFiltros[2]);
			puResultSet = puPreparedStatement.executeQuery();

			while(puResultSet.next()){
				refDocBean.setMemo(puResultSet.getString("MEMO"));
				refDocBean.setActa(puResultSet.getString("ACTA"));
				refDocBean.setDocEnt(puResultSet.getString("DOC_ENT"));
				refDocBean.setConvocatoria(puResultSet.getString("CONVOCATORIA"));
			}

		}catch(SQLException sqle){
			
		}catch (Exception e) {

		}

    	if(refDocBean.getMemo() != null && refDocBean.getMemo().equals("No"))
    		lsRemoveRefPar.append(removeMemoRefPar());

        if(refDocBean.getActa() != null && refDocBean.getActa().equals("No"))
        	lsRemoveRefPar.append(removeActaRefPar());

        if(refDocBean.getDocEnt() != null && refDocBean.getDocEnt().equals("No"))
        	lsRemoveRefPar.append(removeDocEntRefPar());

        if(refDocBean.getConvocatoria() != null && refDocBean.getConvocatoria().equals("No"))
        	lsRemoveRefPar.append(removeConvocatoriaRefPar());

        if( (refDocBean.getMemo() != null && refDocBean.getMemo().equals("No")) &&
            (refDocBean.getActa() != null && refDocBean.getActa().equals("No")) &&
            (refDocBean.getDocEnt() != null && refDocBean.getDocEnt().equals("No")) &&
            (refDocBean.getConvocatoria() != null && refDocBean.getConvocatoria().equals("No"))
          )
        	lsRemoveRefPar.append("AND COD_FLEX_COLUM <> 'VAL_C9' ");

        return lsRemoveRefPar.toString();
    }
    private String removeMemoRefPar(){
    	StringBuilder lsRemoveMemo = new StringBuilder(
    			                "AND COD_FLEX_COLUM <> 	'VAL_C10' " +
					            "AND COD_FLEX_COLUM <> 	'VAL_C47' " +
								"AND COD_FLEX_COLUM <> 	'VAL_C11' " +
								"AND COD_FLEX_COLUM <> 	'VAL_C12' " +
								"AND COD_FLEX_COLUM <> 	'VAL_C13' " +
								"AND COD_FLEX_COLUM <> 	'VAL_C14' "
        );
    	return lsRemoveMemo.toString();
    }
    private String removeActaRefPar(){
    	StringBuilder lsRemoveActa = new StringBuilder(
    			                "AND COD_FLEX_COLUM <> 	'VAL_C15' " +
                                "AND COD_FLEX_COLUM <> 	'VAL_C17' "
        );
    	return lsRemoveActa.toString();
    }
    private String removeDocEntRefPar(){
    	StringBuilder lsRemoveDocEnt = new StringBuilder(
    			                "AND COD_FLEX_COLUM <> 	'VAL_C18' " +
								"AND COD_FLEX_COLUM <> 	'VAL_C19' " +
								"AND COD_FLEX_COLUM <> 	'VAL_C20' " +
								"AND COD_FLEX_COLUM <> 	'VAL_C22' "
        );
    	return lsRemoveDocEnt.toString();
    }
    private String removeConvocatoriaRefPar(){
    	StringBuilder lsRemoveConvocatoria = new StringBuilder(
    			                "AND COD_FLEX_COLUM <> 	'VAL_C23' " +
					            "AND COD_FLEX_COLUM <> 	'VAL_C24' " +
					            "AND COD_FLEX_COLUM <> 	'VAL_C26' "
        );
    	return lsRemoveConvocatoria.toString();
    }
    
    public String doQueryAprobEjerSocRefDoc(int... tiFiltros){
    	ReferenciaDocumentumBean refDocBean = new ReferenciaDocumentumBean();
    	StringBuilder lsRemoveAprobEjerSoc = new StringBuilder();
    	StringBuilder lsSQL = new StringBuilder(
									"SELECT   VAL_C34 AS MEMO "         +
									        ",VAL_C39 AS INFO_COMI "    +
									        ",VAL_C44 AS DIC_EDOS_FIN " +
									        ",VAL_C49 AS DIC_FIS "      +
									        ",VAL_C54 AS ACT_ASAM "     +
									        ",VAL_C57 AS DOC_ENT "      +
									        ",VAL_C62 AS CONV "         +
									        ",VAL_C87 AS CONS "         +
									"FROM     DERCORP_METATBL_TAB "     +
									"WHERE    1=1 " +
									"AND      ID_META_ROW = ? " +
									"AND      ID_FLEX_TBL = ? " +
									"AND      ID_EMPRESA  = ? "
        );

    	try {
			puPreparedStatement = puConnectionWrapper.prepareStatement(lsSQL.toString());
			puPreparedStatement.setInt(1, tiFiltros[0]);
			puPreparedStatement.setInt(2, tiFiltros[1]);
			puPreparedStatement.setInt(3, tiFiltros[2]);
			puResultSet = puPreparedStatement.executeQuery();

			while(puResultSet.next()){
				refDocBean.setMemo(puResultSet.getString("MEMO"));
				refDocBean.setInformeComisario(puResultSet.getString("INFO_COMI"));
				refDocBean.setDictamenEdosFin(puResultSet.getString("DIC_EDOS_FIN"));
				refDocBean.setDictamenFiscal(puResultSet.getString("DIC_FIS"));
				refDocBean.setActaAsamblea(puResultSet.getString("ACT_ASAM"));
				refDocBean.setDocEnt(puResultSet.getString("DOC_ENT"));
				refDocBean.setConvocatoria(puResultSet.getString("CONV"));
				refDocBean.setConstancia(puResultSet.getString("CONS"));
			}
			

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
            e.printStackTrace();
		}

    	if(refDocBean.getMemo() != null && refDocBean.getMemo().equals("No"))
    		lsRemoveAprobEjerSoc.append(removeMemoAES());

    	if(refDocBean.getInformeComisario() != null && refDocBean.getInformeComisario().equals("No"))
    		lsRemoveAprobEjerSoc.append(removeInfoComiAES());

    	if(refDocBean.getDictamenEdosFin() != null && refDocBean.getDictamenEdosFin().equals("No"))
    		lsRemoveAprobEjerSoc.append(removeDicEdosFinAES());

    	if(refDocBean.getDictamenFiscal() != null && refDocBean.getDictamenFiscal().equals("No"))
    		lsRemoveAprobEjerSoc.append(removeDicFiscalAES());

    	if(refDocBean.getActaAsamblea() != null && refDocBean.getActaAsamblea().equals("No"))
    		lsRemoveAprobEjerSoc.append(removeActaAsamAES());

    	if(refDocBean.getDocEnt() != null && refDocBean.getDocEnt().equals("No"))
    		lsRemoveAprobEjerSoc.append(removeDocEntAES());

    	if(refDocBean.getConvocatoria() != null && refDocBean.getConvocatoria().equals("No"))
    		lsRemoveAprobEjerSoc.append(removeConvocatoriaAES());

    	if(refDocBean.getConstancia() != null && refDocBean.getConstancia().equals("No"))
    		lsRemoveAprobEjerSoc.append(removeConstanciaAES());

    	if( (refDocBean.getMemo() != null && refDocBean.getMemo().equals("No")) &&
    		(refDocBean.getInformeComisario() != null && refDocBean.getInformeComisario().equals("No")) &&
    		(refDocBean.getDictamenEdosFin() != null && refDocBean.getDictamenEdosFin().equals("No")) &&
    		(refDocBean.getDictamenFiscal() != null && refDocBean.getDictamenFiscal().equals("No")) &&
    		(refDocBean.getActaAsamblea() != null && refDocBean.getActaAsamblea().equals("No")) &&
    		(refDocBean.getDocEnt() != null && refDocBean.getDocEnt().equals("No")) &&
    		(refDocBean.getConvocatoria() != null && refDocBean.getConvocatoria().equals("No")) &&
    		(refDocBean.getConstancia() != null && refDocBean.getConstancia().equals("No"))
          )
    		lsRemoveAprobEjerSoc.append("AND COD_FLEX_COLUM <> 'VAL_C33' ");


    	return lsRemoveAprobEjerSoc.toString();
    }
    private String removeMemoAES(){
    	StringBuilder lsRemoveMemo = new StringBuilder(
						    			"AND COD_FLEX_COLUM <> 	'VAL_C34' " +
						    			"AND COD_FLEX_COLUM <> 	'VAL_C91' " +
										"AND COD_FLEX_COLUM <> 	'VAL_C35' " +
										"AND COD_FLEX_COLUM <> 	'VAL_C36' " +
										"AND COD_FLEX_COLUM <> 	'VAL_C37' " +
										"AND COD_FLEX_COLUM <> 	'VAL_C38' "
        );
        return lsRemoveMemo.toString();
    }
    private String removeInfoComiAES(){
    	StringBuilder lsRemoveInfoComi = new StringBuilder(
						    			"AND COD_FLEX_COLUM <> 	'VAL_C39' " +
						    			"AND COD_FLEX_COLUM <> 	'VAL_C40' " +
										"AND COD_FLEX_COLUM <> 	'VAL_C41' " +
										"AND COD_FLEX_COLUM <> 	'VAL_C43' "
        );
    	return lsRemoveInfoComi.toString();
    }
    private String removeDicEdosFinAES(){
    	StringBuilder lsRemoveDicEdosFin = new StringBuilder(
						    			"AND COD_FLEX_COLUM <> 	'VAL_C44' " +
						    			"AND COD_FLEX_COLUM <> 	'VAL_C45' " +
										"AND COD_FLEX_COLUM <> 	'VAL_C46' " +
										"AND COD_FLEX_COLUM <> 	'VAL_C48' "
        );
    	return lsRemoveDicEdosFin.toString();
    }
    private String removeDicFiscalAES(){
    	StringBuilder lsRemoveDicFiscal = new StringBuilder(
    			                        "AND COD_FLEX_COLUM <> 	'VAL_C49' " +
							            "AND COD_FLEX_COLUM <> 	'VAL_C50' " +
										"AND COD_FLEX_COLUM <> 	'VAL_C51' " +
										"AND COD_FLEX_COLUM <> 	'VAL_C53' "
        );
    	return lsRemoveDicFiscal.toString();
    }
    private String removeActaAsamAES(){
    	StringBuilder lsRemoveActaAsam = new StringBuilder(
    			                        "AND COD_FLEX_COLUM <> 	'VAL_C54' " +
                                        "AND COD_FLEX_COLUM <> 	'VAL_C56' "
        );
    	return lsRemoveActaAsam.toString();
    }
    private String removeDocEntAES(){
    	StringBuilder lsRemoveDocEnt = new StringBuilder(
    			                        "AND COD_FLEX_COLUM <> 	'VAL_C57' " +
										"AND COD_FLEX_COLUM <> 	'VAL_C58' " +
										"AND COD_FLEX_COLUM <> 	'VAL_C59' " +
										"AND COD_FLEX_COLUM <> 	'VAL_C61' "
        );
    	return lsRemoveDocEnt.toString();
    }
    private String removeConvocatoriaAES(){
    	StringBuilder lsRemoveConvocatoria = new StringBuilder(
    			                        "AND COD_FLEX_COLUM <> 	'VAL_C62' " +
										"AND COD_FLEX_COLUM <> 	'VAL_C63' " +
										"AND COD_FLEX_COLUM <> 	'VAL_C65' "
        );
    	return lsRemoveConvocatoria.toString();
    }
    private String removeConstanciaAES(){
    	StringBuilder lsRemoveConstancia = new StringBuilder(
    			                        "AND COD_FLEX_COLUM <> 	'VAL_C87' " +
							            "AND COD_FLEX_COLUM <> 	'VAL_C88' " +
										"AND COD_FLEX_COLUM <> 	'VAL_C89' " +
										"AND COD_FLEX_COLUM <> 	'VAL_C90' "
        );
    	return lsRemoveConstancia.toString();
    }

    public String doQueryTransfRefDoc(int... tiFiltros){
    	ReferenciaDocumentumBean refDocBean = new ReferenciaDocumentumBean();

    	StringBuilder lsRemoveTransformacion = new StringBuilder();
    	StringBuilder lsSQL = new StringBuilder(
							            "SELECT   VAL_C17 AS MEMO "     +
										        ",VAL_C22 AS ACTA "     +
										        ",VAL_C25 AS DOC_ENT "  +
										        ",VAL_C30 AS CONV "     +
										"FROM     DERCORP_METATBL_TAB " +
										"WHERE    1=1 "                 +
										"AND      ID_META_ROW = ? "     +
										"AND      ID_FLEX_TBL = ? "     +
										"AND      ID_EMPRESA  = ? "
        );

    	try {
			puPreparedStatement = puConnectionWrapper.prepareStatement(lsSQL.toString());
			puPreparedStatement.setInt(1, tiFiltros[0]);
			puPreparedStatement.setInt(2, tiFiltros[1]);
			puPreparedStatement.setInt(3, tiFiltros[2]);
			puResultSet = puPreparedStatement.executeQuery();
			
			while(puResultSet.next()){
				refDocBean.setMemo(puResultSet.getString("MEMO"));
				refDocBean.setActa(puResultSet.getString("ACTA"));
				refDocBean.setDocEnt(puResultSet.getString("DOC_ENT"));
				refDocBean.setConvocatoria(puResultSet.getString("CONV"));
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();

		}

		if(refDocBean.getMemo() != null && refDocBean.getMemo().equals("No"))
			lsRemoveTransformacion.append(removeMemoTrans());

		if(refDocBean.getActa() != null && refDocBean.getActa().equals("No"))
			lsRemoveTransformacion.append(removeActaTrans());

		if(refDocBean.getDocEnt() != null && refDocBean.getDocEnt().equals("No"))
			lsRemoveTransformacion.append(removeDocEntTrans());

		if(refDocBean.getConvocatoria() != null && refDocBean.getConvocatoria().equals("No"))
			lsRemoveTransformacion.append(removeConvocatoriaTrans());

		if( (refDocBean.getMemo() != null && refDocBean.getMemo().equals("No")) &&
			(refDocBean.getActa() != null && refDocBean.getActa().equals("No")) &&
			(refDocBean.getDocEnt() != null && refDocBean.getDocEnt().equals("No")) &&
			(refDocBean.getConvocatoria() != null && refDocBean.getConvocatoria().equals("No"))
        )
			lsRemoveTransformacion.append("AND COD_FLEX_COLUM <> 'VAL_C16' ");

    	return lsRemoveTransformacion.toString();
    }
    private String removeMemoTrans(){
    	StringBuilder lsRemoveTrans = new StringBuilder(
    			                        "AND COD_FLEX_COLUM <> 	'VAL_C17' " +
							            "AND COD_FLEX_COLUM <> 	'VAL_C54' " +
										"AND COD_FLEX_COLUM <> 	'VAL_C18' " +
										"AND COD_FLEX_COLUM <> 	'VAL_C19' " +
										"AND COD_FLEX_COLUM <> 	'VAL_C20' " +
										"AND COD_FLEX_COLUM <> 	'VAL_C21' "
        );
    	return lsRemoveTrans.toString();
    }
    private String removeActaTrans(){
    	StringBuilder lsRemoveActa = new StringBuilder(
    	    "AND COD_FLEX_COLUM <> 	'VAL_C22' " +
            "AND COD_FLEX_COLUM <> 	'VAL_C24' "
        );
    	return lsRemoveActa.toString();
    }
    private String removeDocEntTrans(){
    	StringBuilder lsRemoveDocEnt = new StringBuilder(
    			                        "AND COD_FLEX_COLUM <> 	'VAL_C25' " +
							            "AND COD_FLEX_COLUM <> 	'VAL_C26' " +
										"AND COD_FLEX_COLUM <> 	'VAL_C27' " +
										"AND COD_FLEX_COLUM <> 	'VAL_C29' "
        );
    	return lsRemoveDocEnt.toString();
    }
    private String removeConvocatoriaTrans(){
        StringBuilder lsRemoveConvocatoria = new StringBuilder(
        	"AND COD_FLEX_COLUM <> 	'VAL_C30' " +
        	"AND COD_FLEX_COLUM <> 	'VAL_C31' " +
			"AND COD_FLEX_COLUM <> 	'VAL_C33' "
        );
        return lsRemoveConvocatoria.toString();
    }

    public String doQueryActaOtrosRefDoc(int... tFiltros){

    	ReferenciaDocumentumBean refDocBean = new ReferenciaDocumentumBean();
    	StringBuilder lsRemoveActaOtros = new StringBuilder();

    	StringBuilder lsSQL = new StringBuilder(
            "SELECT   VAL_C13 AS SOLICITUD "  +
			        ",VAL_C18 AS ACTA_RESOL " +
			        ",VAL_C20 AS CONV "       +
			        ",VAL_C22 AS PUBLICA "    +
			        ",VAL_C24 AS DOC_ENT "    +
			"FROM     DERCORP_METATBL_TAB "   +
			"WHERE    1=1 " +
			"AND      ID_META_ROW = ? " +
			"AND      ID_FLEX_TBL = ? " +
			"AND      ID_EMPRESA  = ? "
        );

    	try {
			puPreparedStatement = puConnectionWrapper.prepareStatement(lsSQL.toString());
			puPreparedStatement.setInt(1, tFiltros[0]);
			puPreparedStatement.setInt(2, tFiltros[1]);
			puPreparedStatement.setInt(3, tFiltros[2]);
			puResultSet = puPreparedStatement.executeQuery();
			
			while(puResultSet.next()){
				refDocBean.setSolicitud(puResultSet.getString("SOLICITUD"));
				refDocBean.setActaResoluciones(puResultSet.getString("ACTA_RESOL"));
				refDocBean.setConvocatoria(puResultSet.getString("CONV"));
				refDocBean.setPublicaciones(puResultSet.getString("PUBLICA"));
				refDocBean.setDocEnt(puResultSet.getString("DOC_ENT"));
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
            e.printStackTrace();
		}

    	if(refDocBean.getSolicitud() == null )
    		lsRemoveActaOtros.append(removeSolicitudActaOtros());

    	if(refDocBean.getActaResoluciones() == null)
    		lsRemoveActaOtros.append(removeActaResolActaOtros());

    	if(refDocBean.getConvocatoria() == null)
    		lsRemoveActaOtros.append(removeConvocatoriaActaOtros());

    	if(refDocBean.getPublicaciones() == null)
    		lsRemoveActaOtros.append(removePublicacionesActaOtros());

    	if(refDocBean.getDocEnt() == null)
    		lsRemoveActaOtros.append(removeDocEntActaOtros());

    	if( (refDocBean.getSolicitud() == null ) &&
    		(refDocBean.getActaResoluciones() == null) &&
    		(refDocBean.getConvocatoria() == null) &&
    		(refDocBean.getPublicaciones() == null) &&
    		(refDocBean.getDocEnt() == null)
        )
    		lsRemoveActaOtros.append("AND COD_FLEX_COLUM <> 'VAL_C12' ");

    	return lsRemoveActaOtros.toString();
    }
    private String removeSolicitudActaOtros(){
    	StringBuilder lsRemoveSolicitud = new StringBuilder(
            "AND COD_FLEX_COLUM <> 	'VAL_C13' " +
			"AND COD_FLEX_COLUM <> 	'VAL_C14' " +
			"AND COD_FLEX_COLUM <> 	'VAL_C15' " +
			"AND COD_FLEX_COLUM <> 	'VAL_C16' " +
			"AND COD_FLEX_COLUM <> 	'VAL_C17' "
        );
    	return lsRemoveSolicitud.toString();
    }
    private String removeActaResolActaOtros(){
    	StringBuilder lsRemoveActaOtros = new StringBuilder(
						"AND COD_FLEX_COLUM <> 	'VAL_C18' " +
						"AND COD_FLEX_COLUM <> 	'VAL_C19' "
        );
    	return lsRemoveActaOtros.toString();
    }
    private String removeConvocatoriaActaOtros(){
    	StringBuilder lsRemoveConv = new StringBuilder(
            "AND COD_FLEX_COLUM <> 	'VAL_C20' " +
			"AND COD_FLEX_COLUM <> 	'VAL_C21' "
        );
    	return lsRemoveConv.toString();
    }
    private String removePublicacionesActaOtros(){
    	StringBuilder lsRemovePublica = new StringBuilder(
	        "AND COD_FLEX_COLUM <> 	'VAL_C22' " +
	        "AND COD_FLEX_COLUM <> 	'VAL_C23' "
        );
    	return lsRemovePublica.toString();
    }
    private String removeDocEntActaOtros(){
    	StringBuilder lsRemoveDocEnt = new StringBuilder(
            "AND COD_FLEX_COLUM <> 	'VAL_C24' " +
            "AND COD_FLEX_COLUM <> 	'VAL_C25' "
        );
    	return lsRemoveDocEnt.toString();
    }

    public String doQueryAumentoCapitalRefDoc(int... tiFiltros){
    	ReferenciaDocumentumBean refDocBean = new ReferenciaDocumentumBean();
    	StringBuilder lsRemoveAumentoCapital = new StringBuilder();

    	StringBuilder lsSQL = new StringBuilder(
				            "SELECT   VAL_C25 AS SOLICITUD "  +
							        ",VAL_C30 AS ACTA_RESOL " +
							        ",VAL_C32 AS CONV "       +
							        ",VAL_C34 AS PUBLI "      +
							        ",VAL_C36 AS DOC_ENT "    +
							"FROM     DERCORP_METATBL_TAB "   +
							"WHERE    1=1 "                   +
							"AND      ID_META_ROW = ? "       +
							"AND      ID_FLEX_TBL = ? "       +
							"AND      ID_EMPRESA  = ? "
        );

    	try {
    		puPreparedStatement = puConnectionWrapper.prepareStatement(lsSQL.toString());
    		puPreparedStatement.setInt(1, tiFiltros[0]);
    		puPreparedStatement.setInt(2, tiFiltros[1]);
    		puPreparedStatement.setInt(3, tiFiltros[2]);
    		puResultSet = puPreparedStatement.executeQuery();

    		while(puResultSet.next()){
    			refDocBean.setSolicitud(puResultSet.getString("SOLICITUD"));
    			refDocBean.setActaResoluciones(puResultSet.getString("ACTA_RESOL"));
    			refDocBean.setConvocatoria(puResultSet.getString("CONV"));
    			refDocBean.setPublicaciones(puResultSet.getString("PUBLI"));
    			refDocBean.setDocEnt(puResultSet.getString("DOC_ENT"));
    		}

		} catch (SQLException sqle) {
			sqle.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();

		}

    	if(refDocBean.getSolicitud() == null)
    		lsRemoveAumentoCapital.append(removeSolicitudAumenCapit());

    	if(refDocBean.getActaResoluciones() == null)
    		lsRemoveAumentoCapital.append(removeActaResolAumenCapit());

    	if(refDocBean.getConvocatoria() == null)
    		lsRemoveAumentoCapital.append(removeConvocatoriaAumenCapit());

    	if(refDocBean.getPublicaciones() == null)
    		lsRemoveAumentoCapital.append(removePublicacionesAumenCapit());

    	if(refDocBean.getDocEnt() == null)
    		lsRemoveAumentoCapital.append(removeDocEntAumenCapit());
    	
    	if( (refDocBean.getSolicitud() == null) &&
    		(refDocBean.getActaResoluciones() == null) &&
    		(refDocBean.getConvocatoria() == null) &&
    		(refDocBean.getPublicaciones() == null) &&
    		(refDocBean.getDocEnt() == null)
        )
    		lsRemoveAumentoCapital.append("AND COD_FLEX_COLUM <> 'VAL_C24' ");

    	return lsRemoveAumentoCapital.toString();
    }
    private String removeSolicitudAumenCapit(){
    	StringBuilder lsRemoveSolicitud = new StringBuilder(
			            "AND COD_FLEX_COLUM <> 'VAL_C25' " +
						"AND COD_FLEX_COLUM <> 'VAL_C26' " +
						"AND COD_FLEX_COLUM <> 'VAL_C27' " +
						"AND COD_FLEX_COLUM <> 'VAL_C28' " +
						"AND COD_FLEX_COLUM <> 'VAL_C29' "
        );
    	return lsRemoveSolicitud.toString();
    }
    private String removeActaResolAumenCapit(){
    	StringBuilder lsRemoveActaResol = new StringBuilder(
						"AND COD_FLEX_COLUM <> 'VAL_C30' " +
						"AND COD_FLEX_COLUM <> 'VAL_C31' "
        );
    	return lsRemoveActaResol.toString();
    }
    private String removeConvocatoriaAumenCapit(){
    	StringBuilder lsRemoveConv = new StringBuilder(
			            "AND COD_FLEX_COLUM <> 'VAL_C32' " +
			    		"AND COD_FLEX_COLUM <> 'VAL_C33' "
        );
    	return lsRemoveConv.toString();
    }
    private String removePublicacionesAumenCapit(){
    	StringBuilder lsRemovePubli = new StringBuilder(
				        "AND COD_FLEX_COLUM <> 'VAL_C34' " +
				        "AND COD_FLEX_COLUM <> 'VAL_C35' "
        );
    	return lsRemovePubli.toString();
    }
    private String removeDocEntAumenCapit(){
    	StringBuilder lsRemoveDocEnt = new StringBuilder(
            "AND COD_FLEX_COLUM <> 'VAL_C36' " +
            "AND COD_FLEX_COLUM <> 'VAL_C37' "
        );
    	return lsRemoveDocEnt.toString();
    }

    public String doQueryDecreDiviRefDoc(int... tiFiltros){
    	ReferenciaDocumentumBean refDocBean = new ReferenciaDocumentumBean();
    	StringBuilder lsRemoveDecreDivi = new StringBuilder();

    	StringBuilder lsSQL = new StringBuilder(
            "SELECT    VAL_C15 AS SOLICITUD " +
			         ",VAL_C20 AS ACTA_RESOL " +
			         ",VAL_C22 AS DOC_ENT " +
			"FROM     DERCORP_METATBL_TAB " +
			"WHERE    1=1 " +
			"AND      ID_META_ROW = ? " +
			"AND      ID_FLEX_TBL = ? " +
			"AND      ID_EMPRESA  = ? "
        );

    	try {
			puPreparedStatement = puConnectionWrapper.prepareStatement(lsSQL.toString());
			puPreparedStatement.setInt(1, tiFiltros[0]);
			puPreparedStatement.setInt(2, tiFiltros[1]);
			puPreparedStatement.setInt(3, tiFiltros[2]);
			puResultSet = puPreparedStatement.executeQuery();

			while(puResultSet.next()){
				refDocBean.setSolicitud(puResultSet.getString("SOLICITUD"));
				refDocBean.setActaResoluciones(puResultSet.getString("ACTA_RESOL"));
				refDocBean.setDocEnt(puResultSet.getString("DOC_ENT"));

			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();

		}

    	if(refDocBean.getSolicitud() == null)
    		lsRemoveDecreDivi.append(removeSolicitudDecreDivi());

    	if(refDocBean.getActaResoluciones() == null)
    		lsRemoveDecreDivi.append(removeActaResolDecreDivi());

    	if(refDocBean.getDocEnt() == null)
    		lsRemoveDecreDivi.append(removeDocEntDecreDivi());

    	if( (refDocBean.getSolicitud() == null) &&
    		(refDocBean.getActaResoluciones() == null) &&
    		(refDocBean.getDocEnt() == null)
          )
    		lsRemoveDecreDivi.append("AND COD_FLEX_COLUM <> 'VAL_C14' ");

    	return lsRemoveDecreDivi.toString();

    }
    private String removeSolicitudDecreDivi(){
    	StringBuilder lsRemoveSolicitud = new StringBuilder(
			            "AND COD_FLEX_COLUM <> 'VAL_C15' " +
						"AND COD_FLEX_COLUM <> 'VAL_C16' " +
						"AND COD_FLEX_COLUM <> 'VAL_C17' " +
						"AND COD_FLEX_COLUM <> 'VAL_C18' " +
						"AND COD_FLEX_COLUM <> 'VAL_C19' "
        );
    	return lsRemoveSolicitud.toString();
    }
    private String removeActaResolDecreDivi(){
    	StringBuilder lsRemoveActaResol = new StringBuilder(
            "AND COD_FLEX_COLUM <> 	'VAL_C20' " +
            "AND COD_FLEX_COLUM <> 	'VAL_C21' "
        );
    	return lsRemoveActaResol.toString();
    }
    private String removeDocEntDecreDivi(){
    	StringBuilder lsRemoveDocEnt = new StringBuilder(
            "AND COD_FLEX_COLUM <> 	'VAL_C22' " +
            "AND COD_FLEX_COLUM <> 	'VAL_C23' "
        );
    	return lsRemoveDocEnt.toString();
    }

    public String doQueryDismiCapiRefDoc(int... tiFiltros){
    	StringBuilder lsRemoveDismiCapi = new StringBuilder();
    	ReferenciaDocumentumBean refDocBean = new ReferenciaDocumentumBean();

    	StringBuilder lsSQL = new StringBuilder(
				            "SELECT   VAL_C25 AS SOLICITUD "  +
							        ",VAL_C30 AS ACTA_RESOL " +
							        ",VAL_C32 AS CONV "       +
							        ",VAL_C34 AS PUBLI "      +
							        ",VAL_C36 AS DOC_ENT "    +
							"FROM     DERCORP_METATBL_TAB "   +
							"WHERE    1=1 "                   +
							"AND      ID_META_ROW = ? "       +
							"AND      ID_FLEX_TBL = ? "       +
							"AND      ID_EMPRESA  = ? "
        );

    	try {
    		puPreparedStatement = puConnectionWrapper.prepareStatement(lsSQL.toString());
    		puPreparedStatement.setInt(1, tiFiltros[0]);
    		puPreparedStatement.setInt(2, tiFiltros[1]);
    		puPreparedStatement.setInt(3, tiFiltros[2]);
    		puResultSet = puPreparedStatement.executeQuery();

    		while(puResultSet.next()){
    			refDocBean.setSolicitud(puResultSet.getString("SOLICITUD"));
    			refDocBean.setActaResoluciones(puResultSet.getString("ACTA_RESOL"));
    			refDocBean.setConvocatoria(puResultSet.getString("CONV"));
    			refDocBean.setPublicaciones(puResultSet.getString("PUBLI"));
    			refDocBean.setDocEnt(puResultSet.getString("DOC_ENT"));
    		}

		} catch (SQLException sqle) {
			sqle.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();

		}

    	if(refDocBean.getSolicitud() == null)
    		lsRemoveDismiCapi.append(removeSolucionesDismiCapi());

    	if(refDocBean.getActaResoluciones() == null)
    		lsRemoveDismiCapi.append(removeActaResolDismiCapi());

    	if(refDocBean.getConvocatoria() == null)
    		lsRemoveDismiCapi.append(removeConvocatoriaDismiCapi());

    	if(refDocBean.getPublicaciones() == null)
    		lsRemoveDismiCapi.append(removePublicacionesDismiCapi());

    	if(refDocBean.getDocEnt() == null)
    		lsRemoveDismiCapi.append(removeDocEntDismiCapi());

    	if( (refDocBean.getSolicitud() == null) &&
    		(refDocBean.getActaResoluciones() == null) &&
    		(refDocBean.getConvocatoria() == null) &&
    		(refDocBean.getPublicaciones() == null) &&
    		(refDocBean.getDocEnt() == null)
        )
    		lsRemoveDismiCapi.append("AND COD_FLEX_COLUM <> 'VAL_C24' ");

    	return lsRemoveDismiCapi.toString();

    }
    private String removeSolucionesDismiCapi(){
    	StringBuilder lsRemoveSoluciones = new StringBuilder(
    			"AND COD_FLEX_COLUM <> 'VAL_C25' " +
				"AND COD_FLEX_COLUM <> 'VAL_C26' " +
				"AND COD_FLEX_COLUM <> 'VAL_C27' " +
				"AND COD_FLEX_COLUM <> 'VAL_C28' " +
				"AND COD_FLEX_COLUM <> 'VAL_C29' "
        );
    	return lsRemoveSoluciones.toString();
    }
    private String removeActaResolDismiCapi(){
    	StringBuilder lsRemoveActaResol = new StringBuilder(
            "AND COD_FLEX_COLUM <> 'VAL_C30' " +
            "AND COD_FLEX_COLUM <> 'VAL_C31' "
        );
    	return lsRemoveActaResol.toString();
    }
    private String removeConvocatoriaDismiCapi(){
    	StringBuilder lsRemoveConvocatoria = new StringBuilder(
            "AND COD_FLEX_COLUM <> 'VAL_C32' " +
            "AND COD_FLEX_COLUM <> 'VAL_C33' "
        );
    	return lsRemoveConvocatoria.toString();
    }
    private String removePublicacionesDismiCapi(){
    	StringBuilder lsRemovePublicaciones = new StringBuilder(
            "AND COD_FLEX_COLUM <> 'VAL_C34' " +
            "AND COD_FLEX_COLUM <> 'VAL_C35' "
        );
    	return lsRemovePublicaciones.toString();
    }
    private String removeDocEntDismiCapi(){
    	StringBuilder lsRemoveDocEnt = new StringBuilder(
	        "AND COD_FLEX_COLUM <> 'VAL_C36' " +
	        "AND COD_FLEX_COLUM <> 'VAL_C37' "
        );
    	return lsRemoveDocEnt.toString();
    }

    public String doQueryEscisionRefDoc(int... tiFiltros){
    	StringBuilder lsRemoveEscision = new StringBuilder();
    	ReferenciaDocumentumBean refDocBean = new ReferenciaDocumentumBean();

    	StringBuilder lsSQL = new StringBuilder(
		            "SELECT   VAL_C27 AS SOLICITUD "     +
					        ",VAL_C32 AS ACTA_RESOL "    +
					        ",VAL_C34 AS CONV "          +
					        ",VAL_C36 AS PUBLICACIONES " +
					        ",VAL_C38 AS DOC_ENT "       +
					"FROM     DERCORP_METATBL_TAB "      +
					"WHERE    1=1 "                      +
					"AND      ID_META_ROW = ? "          +
					"AND      ID_FLEX_TBL = ? "          +
					"AND      ID_EMPRESA  = ? "
        );

    	try {
			puPreparedStatement = puConnectionWrapper.prepareStatement(lsSQL.toString());
			puPreparedStatement.setInt(1, tiFiltros[0]);
			puPreparedStatement.setInt(2, tiFiltros[1]);
			puPreparedStatement.setInt(3, tiFiltros[2]);
			puResultSet = puPreparedStatement.executeQuery();

			while(puResultSet.next()){
				refDocBean.setSolicitud(puResultSet.getString("SOLICITUD"));
				refDocBean.setActaResoluciones(puResultSet.getString("ACTA_RESOL"));
				refDocBean.setConvocatoria(puResultSet.getString("CONV"));
				refDocBean.setPublicaciones(puResultSet.getString("PUBLICACIONES"));
				refDocBean.setDocEnt(puResultSet.getString("DOC_ENT"));

			}

		} catch (SQLException sqle) {
            sqle.printStackTrace();

		} catch (Exception e) {
            e.printStackTrace();

		}

    	if(refDocBean.getSolicitud() == null)
    		lsRemoveEscision.append(removeSolicitudEsci());

    	if(refDocBean.getActaResoluciones() == null)
    		lsRemoveEscision.append(removeActaResolEsci());

    	if(refDocBean.getConvocatoria() == null)
    		lsRemoveEscision.append(removeConvocatoriaEsci());

    	if(refDocBean.getPublicaciones() == null)
    		lsRemoveEscision.append(removePublicacionesEsci());

    	if(refDocBean.getDocEnt() == null)
    		lsRemoveEscision.append(removeDocEntEsci());

    	if( (refDocBean.getSolicitud() == null) &&
    		(refDocBean.getActaResoluciones() == null) &&
    		(refDocBean.getConvocatoria() == null) &&
    		(refDocBean.getPublicaciones() == null) &&
    		(refDocBean.getDocEnt() == null)
        )
    		lsRemoveEscision.append("AND COD_FLEX_COLUM <> 'VAL_C26' ");

    	return lsRemoveEscision.toString();

    }
    private String removeSolicitudEsci(){
        StringBuilder lsRemoveSolicitud = new StringBuilder(
				        "AND COD_FLEX_COLUM <> 'VAL_C27' " +
						"AND COD_FLEX_COLUM <> 'VAL_C28' " +
						"AND COD_FLEX_COLUM <> 'VAL_C29' " +
						"AND COD_FLEX_COLUM <> 'VAL_C30' " +
						"AND COD_FLEX_COLUM <> 'VAL_C31' "
        );
        return lsRemoveSolicitud.toString();
    }
    private String removeActaResolEsci(){
    	StringBuilder lsRemoveActaResol = new StringBuilder(
            "AND COD_FLEX_COLUM <> 'VAL_C32' " +
            "AND COD_FLEX_COLUM <> 'VAL_C33' "
        );
    	return lsRemoveActaResol.toString();
    }
    private String removeConvocatoriaEsci(){
    	StringBuilder lsRemoveConvocatoria = new StringBuilder(
            "AND COD_FLEX_COLUM <> 'VAL_C34' " +
            "AND COD_FLEX_COLUM <> 'VAL_C35' "
        );
    	return lsRemoveConvocatoria.toString();
    }
    private String removePublicacionesEsci(){
    	StringBuilder lsRemovePublicaciones = new StringBuilder(
            "AND COD_FLEX_COLUM <> 'VAL_C36' " +
            "AND COD_FLEX_COLUM <> 'VAL_C37' "
        );
    	return lsRemovePublicaciones.toString();
    }
    private String removeDocEntEsci(){
        StringBuilder lsRemoveDocEnt = new StringBuilder(
            "AND COD_FLEX_COLUM <> 'VAL_C38' " +
            "AND COD_FLEX_COLUM <> 'VAL_C39' "
        );
        return lsRemoveDocEnt.toString();
    }

    public String doQueryFusionRefDoc(int... tiFiltros){
        StringBuilder lsRemoveFusion = new StringBuilder();
        ReferenciaDocumentumBean refDocBean = new ReferenciaDocumentumBean();

        StringBuilder lsSQL = new StringBuilder(
            "SELECT   VAL_C29 AS SOLICITUD "     +
			        ",VAL_C34 AS ACTA_RESOL "    +
			        ",VAL_C36 AS CONV "          +
			        ",VAL_C38 AS PUBLICACIONES " +
			        ",VAL_C40 AS DOC_ENT "       +
			"FROM     DERCORP_METATBL_TAB "      +
			"WHERE    1=1 "                      +
			"AND      ID_META_ROW = ? "          +
			"AND      ID_FLEX_TBL = ? "          +
			"AND      ID_EMPRESA  = ? "
        );
        try {
			puPreparedStatement = puConnectionWrapper.prepareStatement(lsSQL.toString());
            puPreparedStatement.setInt(1, tiFiltros[0]);
            puPreparedStatement.setInt(2, tiFiltros[1]);
            puPreparedStatement.setInt(3, tiFiltros[2]);
            puResultSet = puPreparedStatement.executeQuery();

            while(puResultSet.next()){
            	refDocBean.setSolicitud(puResultSet.getString("SOLICITUD"));
            	refDocBean.setActaResoluciones(puResultSet.getString("ACTA_RESOL"));
            	refDocBean.setConvocatoria(puResultSet.getString("CONV"));
            	refDocBean.setPublicaciones(puResultSet.getString("PUBLICACIONES"));
            	refDocBean.setDocEnt(puResultSet.getString("DOC_ENT"));
            }

		} catch (SQLException sqle) {
            sqle.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();

		}

        if(refDocBean.getSolicitud() == null)
        	lsRemoveFusion.append(removeSolicitudFusion());

        if(refDocBean.getActaResoluciones() == null)
        	lsRemoveFusion.append(removeActaResolFusion());

        if(refDocBean.getConvocatoria() == null)
        	lsRemoveFusion.append(removeConvocatoriaFusion());

        if(refDocBean.getPublicaciones() == null)
        	lsRemoveFusion.append(removePublicacionesFusion());

        if(refDocBean.getDocEnt() == null)
        	lsRemoveFusion.append(removeDocEntFusion());

        if( (refDocBean.getSolicitud() == null) &&
        	(refDocBean.getActaResoluciones() == null) &&
        	(refDocBean.getConvocatoria() == null) &&
        	(refDocBean.getPublicaciones() == null) &&
        	(refDocBean.getDocEnt() == null)
        )
        	lsRemoveFusion.append("AND COD_FLEX_COLUM <> 'VAL_C28' ");

        return lsRemoveFusion.toString();

    }
    private String removeSolicitudFusion(){
    	StringBuilder lsRemoveSolicitud = new StringBuilder(
            "AND COD_FLEX_COLUM <> 'VAL_C29' " +
			"AND COD_FLEX_COLUM <> 'VAL_C30' " +
			"AND COD_FLEX_COLUM <> 'VAL_C31' " +
			"AND COD_FLEX_COLUM <> 'VAL_C32' " +
			"AND COD_FLEX_COLUM <> 'VAL_C33' "
        );
    	return lsRemoveSolicitud.toString();
    }
    private String removeActaResolFusion(){
        StringBuilder lsRemoveActaReso = new StringBuilder(
            "AND COD_FLEX_COLUM <> 'VAL_C34' " +
            "AND COD_FLEX_COLUM <> 'VAL_C35' "
        );
        return lsRemoveActaReso.toString();
    }
    private String removeConvocatoriaFusion(){
    	StringBuilder lsRemoveConvocatoria = new StringBuilder(
			"AND COD_FLEX_COLUM <> 'VAL_C36' " +
			"AND COD_FLEX_COLUM <> 'VAL_C37' "
        );
    	return lsRemoveConvocatoria.toString();
    }
    private String removePublicacionesFusion(){
    	StringBuilder lsRemovePublicaciones = new StringBuilder(
            "AND COD_FLEX_COLUM <> 'VAL_C38' " +
            "AND COD_FLEX_COLUM <> 'VAL_C39' "
        );
    	return lsRemovePublicaciones.toString();
    }
    private String removeDocEntFusion(){
    	StringBuilder lsRemoveDocEnt = new StringBuilder(
			"AND COD_FLEX_COLUM <> 'VAL_C40' " +
			"AND COD_FLEX_COLUM <> 'VAL_C41' "
        );
    	return lsRemoveDocEnt.toString();
    }

    public String doQuerySesionRefDoc(int... tiFiltros){
        StringBuilder lsRemoveSesion = new StringBuilder();
        ReferenciaDocumentumBean refDocBean = new ReferenciaDocumentumBean();

        StringBuilder lsSQL = new StringBuilder(
					"SELECT   VAL_C14 AS SOLICITUD "  +
					        ",VAL_C19 AS ACTA_RESOL " +
					        ",VAL_C21 AS CONV "       +
					        ",VAL_C23 AS DOC_ENT "    +
					"FROM     DERCORP_METATBL_TAB "   +
					"WHERE    1=1 "                   +
					"AND      ID_META_ROW = ? "       +
					"AND      ID_FLEX_TBL = ? "       +
					"AND      ID_EMPRESA  = ? "
        );
        try {
        	puPreparedStatement = puConnectionWrapper.prepareStatement(lsSQL.toString());
        	puPreparedStatement.setInt(1, tiFiltros[0]);
        	puPreparedStatement.setInt(2, tiFiltros[1]);
        	puPreparedStatement.setInt(3, tiFiltros[2]);
        	puResultSet = puPreparedStatement.executeQuery();

        	while(puResultSet.next()){
        		refDocBean.setSolicitud(puResultSet.getString("SOLICITUD"));
        		refDocBean.setActaResoluciones(puResultSet.getString("ACTA_RESOL"));
        		refDocBean.setConvocatoria(puResultSet.getString("CONV"));
        		refDocBean.setDocEnt(puResultSet.getString("DOC_ENT"));
        	}

		} catch (SQLException sqle) {
			sqle.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();

		}

        if(refDocBean.getSolicitud() == null)
        	lsRemoveSesion.append(removeSolicitudSesion());

        if(refDocBean.getActaResoluciones() == null)
        	lsRemoveSesion.append(removeActaResolSesion());

        if(refDocBean.getConvocatoria() == null)
        	lsRemoveSesion.append(removeConvocatoriaSesion());

        if(refDocBean.getDocEnt() == null)
        	lsRemoveSesion.append(removeDocEntSesion());

        if( (refDocBean.getSolicitud() == null) &&
        	(refDocBean.getActaResoluciones() == null) &&
        	(refDocBean.getConvocatoria() == null) &&
        	(refDocBean.getDocEnt() == null)
        )
        	lsRemoveSesion.append("AND COD_FLEX_COLUM <> 'VAL_C13' ");

        return lsRemoveSesion.toString();

    }
    private String removeSolicitudSesion(){
    	StringBuilder lsRemoveSolicitud = new StringBuilder(
    	        "AND COD_FLEX_COLUM <> 'VAL_C14' " +
    			"AND COD_FLEX_COLUM <> 'VAL_C15' " +
    			"AND COD_FLEX_COLUM <> 'VAL_C16' " +
    			"AND COD_FLEX_COLUM <> 'VAL_C17' " +
    			"AND COD_FLEX_COLUM <> 'VAL_C18' "
        );
    	return lsRemoveSolicitud.toString();
    }
    private String removeActaResolSesion(){
    	StringBuilder lsRemoveActaResol = new StringBuilder(
	            "AND COD_FLEX_COLUM <>	'VAL_C19' " +
	            "AND COD_FLEX_COLUM <>	'VAL_C20' "
        );
    	return lsRemoveActaResol.toString();
    }
    private String removeConvocatoriaSesion(){
        StringBuilder lsRemoveConvocatoria = new StringBuilder(
            "AND COD_FLEX_COLUM <> 'VAL_C21' " +
            "AND COD_FLEX_COLUM <> 'VAL_C22' "
        );
        return lsRemoveConvocatoria.toString();
    }
    private String removeDocEntSesion(){
        StringBuilder lsRemoveDocEnt = new StringBuilder(
            "AND COD_FLEX_COLUM <> 'VAL_C23' " +
            "AND COD_FLEX_COLUM <> 'VAL_C24' "
        );
        return lsRemoveDocEnt.toString();
    }

    public String doQueryEscriturasOtrosRefDoc(int... tiFiltros){
    	StringBuilder lsRemoveEscriturasOtros = new StringBuilder();
    	ReferenciaDocumentumBean refDocBean = new ReferenciaDocumentumBean();

    	StringBuilder lsSQL = new StringBuilder(
            "SELECT   VAL_C10 AS SOLICITUD " +
			        ",VAL_C15 AS DOC_ENT "   +
			"FROM     DERCORP_METATBL_TAB "  +
			"WHERE    1=1 "                  +
			"AND      ID_META_ROW = ? "      +
			"AND      ID_FLEX_TBL = ? "      +
			"AND      ID_EMPRESA  = ? "
        );

    	try {
			puPreparedStatement = puConnectionWrapper.prepareStatement(lsSQL.toString());
			puPreparedStatement.setInt(1, tiFiltros[0]);
			puPreparedStatement.setInt(2, tiFiltros[1]);
			puPreparedStatement.setInt(3, tiFiltros[2]);
			puResultSet = puPreparedStatement.executeQuery();

			while(puResultSet.next()){
				refDocBean.setSolicitud(puResultSet.getString("SOLICITUD"));
				refDocBean.setDocEnt(puResultSet.getString("DOC_ENT"));
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();

		}

    	if(refDocBean.getSolicitud() == null)
    		lsRemoveEscriturasOtros.append(removeSolicitudEscriturasOtros());

    	if(refDocBean.getDocEnt() == null)
    		lsRemoveEscriturasOtros.append(removeDocEntEscriturasOtros());

    	if( (refDocBean.getSolicitud() == null) &&
    		(refDocBean.getDocEnt() == null)
        )
    		lsRemoveEscriturasOtros.append("AND COD_FLEX_COLUM <> 'VAL_C9' ");

    	return lsRemoveEscriturasOtros.toString();
    }
    private String removeSolicitudEscriturasOtros(){
    	StringBuilder lsRemoveSolicitud = new StringBuilder(
            "AND COD_FLEX_COLUM <> 'VAL_C10' " +
			"AND COD_FLEX_COLUM <> 'VAL_C11' " +
			"AND COD_FLEX_COLUM <> 'VAL_C12' " +
			"AND COD_FLEX_COLUM <> 'VAL_C13' " +
			"AND COD_FLEX_COLUM <> 'VAL_C14' "
        );
    	return lsRemoveSolicitud.toString();
    }
    private String removeDocEntEscriturasOtros(){
    	StringBuilder lsRemoveDocEnt = new StringBuilder(
            "AND COD_FLEX_COLUM <> 'VAL_C15' " +
            "AND COD_FLEX_COLUM <> 'VAL_C16' "
        );
    	return lsRemoveDocEnt.toString();
    }

    public String doQueryContratosRefDoc(int... tiFiltros){
    	StringBuilder lsRemoveContratos = new StringBuilder();
    	ReferenciaDocumentumBean refDocBean = new ReferenciaDocumentumBean();

    	StringBuilder lsSQL = new StringBuilder(
            "SELECT   VAL_C20 AS SOLICITUD " +
			        ",VAL_C25 AS CONT_ENT "  +
			"FROM     DERCORP_METATBL_TAB "  +
			"WHERE    1=1 "                  +
			"AND      ID_META_ROW = ? "      +
			"AND      ID_FLEX_TBL = ? "      +
			"AND      ID_EMPRESA  = ? "
        );

    	try {
			puPreparedStatement = puConnectionWrapper.prepareStatement(lsSQL.toString());
			puPreparedStatement.setInt(1, tiFiltros[0]);
			puPreparedStatement.setInt(2, tiFiltros[1]);
			puPreparedStatement.setInt(3, tiFiltros[2]);
			puResultSet = puPreparedStatement.executeQuery();

			while(puResultSet.next()){
				refDocBean.setSolicitud(puResultSet.getString("SOLICITUD"));
				refDocBean.setContEnt(puResultSet.getString("CONT_ENT"));
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();

		}

    	if(refDocBean.getSolicitud() == null)
    		lsRemoveContratos.append(removeSolicitudContratos());

    	if(refDocBean.getContEnt() == null)
    		lsRemoveContratos.append(removeContEntContratos());

    	if( (refDocBean.getSolicitud() == null) &&
    		(refDocBean.getContEnt() == null)
          )
    		lsRemoveContratos.append("AND COD_FLEX_COLUM <> 'VAL_C19' ");

    	return lsRemoveContratos.toString();

    }
    private String removeSolicitudContratos(){
    	StringBuilder lsRemoveSolicitud = new StringBuilder(
		    	"AND COD_FLEX_COLUM <> 'VAL_C20' " +
				"AND COD_FLEX_COLUM <> 'VAL_C21' " +
				"AND COD_FLEX_COLUM <> 'VAL_C22' " +
				"AND COD_FLEX_COLUM <> 'VAL_C23' " +
				"AND COD_FLEX_COLUM <> 'VAL_C24' "
    	);
    	return lsRemoveSolicitud.toString();
    }
    private String removeContEntContratos(){
    	StringBuilder lsRemoveContEnt = new StringBuilder(
				"AND COD_FLEX_COLUM <> 'VAL_C25' " +
				"AND COD_FLEX_COLUM <> 'VAL_C26' "
    	);
    	return lsRemoveContEnt.toString();
    }

}