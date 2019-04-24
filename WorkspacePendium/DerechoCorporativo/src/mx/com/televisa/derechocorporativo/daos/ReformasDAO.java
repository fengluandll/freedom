package mx.com.televisa.derechocorporativo.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import mx.com.televisa.derechocorporativo.bean.reformas.ActaOtrosBean;
import mx.com.televisa.derechocorporativo.bean.reformas.AumentoCapitalBean;
import mx.com.televisa.derechocorporativo.bean.reformas.ComitesBean;
import mx.com.televisa.derechocorporativo.bean.reformas.ContratosBean;
import mx.com.televisa.derechocorporativo.bean.reformas.DecretoDividendosBean;
import mx.com.televisa.derechocorporativo.bean.reformas.DisminucionCapitalBean;
import mx.com.televisa.derechocorporativo.bean.reformas.EscisionBean;
import mx.com.televisa.derechocorporativo.bean.reformas.EscriturasOtrosBean;
import mx.com.televisa.derechocorporativo.bean.reformas.FusionBean;
import mx.com.televisa.derechocorporativo.bean.reformas.SesionConsejoBean;
import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;
import mx.com.televisa.derechocorporativo.data.packages.DERCORP_CON_REF_DOC_PKG;
import mx.com.televisa.derechocorporativo.modules.flextabs.consulta.Fusion;
import mx.com.televisa.derechocorporativo.util.ServiceLocator;

public class ReformasDAO {
	private final static Logger log = Logger.getLogger(ReformasDAO.class);
	private CallableStatement callState;
	private ResultSet resultSet;

	public ReformasDAO(){
		callState = null;
		resultSet = null;
	}

	public EscriturasOtrosBean getValuesEscOtros(int tIdMetaRow, ConnectionWrapper connectionWrapper){
		EscriturasOtrosBean escriturasOtrosBean = null;

		try {
			
			callState = connectionWrapper.prepareCall(DERCORP_CON_REF_DOC_PKG.GET_VALUES_ESC_OTROS_PR);
			callState.registerOutParameter(1, OracleTypes.CURSOR);
			callState.setInt(2, tIdMetaRow);
			callState.execute();

			resultSet = ((OracleCallableStatement) callState).getCursor(1);
			while(resultSet.next()){
				escriturasOtrosBean = new EscriturasOtrosBean(resultSet.getString("SOLICITUD")
															 ,resultSet.getString("SOL_POR")
															 ,resultSet.getString("FEC_DOC")
                                                             ,resultSet.getString("FEC_REC")
                                                             ,resultSet.getString("FOL_NUM")
                                                             ,resultSet.getString("NUM_DOC_SOL")
                                                             ,resultSet.getString("DOC_ENT")
                                                             ,resultSet.getString("NUM_DOC_DOC_ENT")
                                                             ,resultSet.getString("FEC_DOC_E")
                                                             ,resultSet.getString("FEC_REC_E")
                                                             ,resultSet.getInt("AGREG_DOC")

                );
			}

		} catch (Exception e) {
            e.printStackTrace();

		}

		return escriturasOtrosBean;

	}

	public ContratosBean getValuesContratos(int tIdMetaRow, ConnectionWrapper connectionWrapper){
		ContratosBean contratosBean = null;

		try {
			
			callState = connectionWrapper.prepareCall(DERCORP_CON_REF_DOC_PKG.GET_VALUES_CONTRATOS_PR);
			callState.registerOutParameter(1, OracleTypes.CURSOR);
			callState.setInt(2, tIdMetaRow);
			callState.execute();

			resultSet = ((OracleCallableStatement) callState).getCursor(1);

			while(resultSet.next()){
				contratosBean = new ContratosBean(resultSet.getString("SOLICITUD")
												 ,resultSet.getString("SOL_POR")
                                                 ,resultSet.getString("FEC_DOC")
                                                 ,resultSet.getString("FEC_REC")
                                                 ,resultSet.getString("FOL_NUM")
                                                 ,resultSet.getString("NUM_DOC_SOL")
                                                 ,resultSet.getString("CONTRATO_ENT")
                                                 ,resultSet.getString("NUM_CONT_CONT_ENT")
                                                 ,resultSet.getString("FEC_DOC_E")
                                                 ,resultSet.getString("FEC_REC_E")
                                                 ,resultSet.getInt("AGREG_DOC")
                );
            }

		} catch (Exception e) {
            e.printStackTrace();

		}

		return contratosBean;

	}

	public ActaOtrosBean getValuesActaOtros(int tIdMetaRow, ConnectionWrapper connectionWrapper){
		ActaOtrosBean actaOtrosBean = null;

		try {
			
			callState = connectionWrapper.prepareCall(DERCORP_CON_REF_DOC_PKG.GET_VALUES_ACTA_OTROS_PR);
			callState.registerOutParameter(1, OracleTypes.CURSOR);
			callState.setInt(2, tIdMetaRow);
			callState.execute();

			resultSet = ((OracleCallableStatement) callState).getCursor(1);

			while(resultSet.next()){
				actaOtrosBean = new ActaOtrosBean(resultSet.getString("SOLICITUD")
												 ,resultSet.getString("SOL_POR")
                                                 ,resultSet.getString("FEC_DOC")
                                                 ,resultSet.getString("FEC_REC")
                                                 ,resultSet.getString("FOL_NUM")
                                                 ,resultSet.getString("NUM_DOC_SOL")
                                                 ,resultSet.getString("ACTA_RESOLUCIONES")
                                                 ,resultSet.getString("NUM_DOC_ACTA_RESOL")
                                                 ,resultSet.getString("CONVOCATORIA")
                                                 ,resultSet.getString("NUM_DOC_CONVOCATORIA")
                                                 ,resultSet.getString("PUBLICACIONES")
                                                 ,resultSet.getString("NUM_DOC_PUB")
                                                 ,resultSet.getString("DOC_ENTREGADO")
                                                 ,resultSet.getString("NUM_DOC_DOC_ENT")
                                                 ,resultSet.getString("FEC_DOC_E")
                                                 ,resultSet.getString("FEC_REC_E")
                                                 ,resultSet.getInt("AGREG_DOC")
                );
			}

		} catch (Exception e) {
            e.printStackTrace();

		}

        return actaOtrosBean;

	}

	public AumentoCapitalBean getValuesAumentoCapital(int tIdMetaRow, ConnectionWrapper connectionWrapper){
        AumentoCapitalBean aumentoCapitalBean = null;
        try {
			
			callState = connectionWrapper.prepareCall(DERCORP_CON_REF_DOC_PKG.GET_VALUES_AUMENTO_CAPITAL_PR);
			callState.registerOutParameter(1, OracleTypes.CURSOR);
			callState.setInt(2, tIdMetaRow);
			callState.execute();

			resultSet = ((OracleCallableStatement) callState).getCursor(1);

			while(resultSet.next()){
				aumentoCapitalBean = new AumentoCapitalBean(resultSet.getString("SOLICITUD")
														 ,resultSet.getString("SOL_POR")
		                                                 ,resultSet.getString("FEC_DOC")
		                                                 ,resultSet.getString("FEC_REC")
		                                                 ,resultSet.getString("FOL_NUM")
		                                                 ,resultSet.getString("NUM_DOC_SOL")
		                                                 ,resultSet.getString("ACTA_RESOL")
		                                                 ,resultSet.getString("NUM_DOC_ACTA_RESOL")
		                                                 ,resultSet.getString("CONVOCATORIA")
		                                                 ,resultSet.getString("NUM_DOC_CONV")
		                                                 ,resultSet.getString("PUBLICACIONES")
		                                                 ,resultSet.getString("NUM_DOC_PUB")
		                                                 ,resultSet.getString("DOC_ENT")
		                                                 ,resultSet.getString("NUM_DOC_DOC_ENT")
		                                                 ,resultSet.getString("FEC_DOC_E")
		                                                 ,resultSet.getString("FEC_REC_E")
		                                                 ,resultSet.getInt("AGREG_DOC")
                );
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

        return aumentoCapitalBean;

	}

    public ComitesBean getValuesComites(int tIdMetaRow, ConnectionWrapper connectionWrapper){
    	ComitesBean comitesBean = null;
        try {

			callState = connectionWrapper.prepareCall(DERCORP_CON_REF_DOC_PKG.GET_VALUES_COMITES_PR);
			callState.registerOutParameter(1, OracleTypes.CURSOR);
			callState.setInt(2, tIdMetaRow);
			callState.execute();

			resultSet = ((OracleCallableStatement) callState).getCursor(1);

			while(resultSet.next()){
				comitesBean = new ComitesBean(resultSet.getString("SOLICITUD")
                                             ,resultSet.getString("FEC_DOC")
                                             ,resultSet.getString("FEC_REC")
                                             ,resultSet.getString("FOLIO_NUM")
                                             ,resultSet.getString("NUM_DOC_SOL")
                                             ,resultSet.getString("ACTA_RESOL")
                                             ,resultSet.getString("NUM_DOC_ACTA_RESOL")
                                             ,resultSet.getString("CONVOCATORIA")
                                             ,resultSet.getString("NUM_DOC_CONVOCATORIA")
                                             ,resultSet.getString("DOCUMENTO_ENTREGADO")
                                             ,resultSet.getString("NUM_DOC_DOC_ENT")
                );
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

    	return comitesBean;
    }

    public DecretoDividendosBean getValuesDecretoDividendos(int tIdMetaRow, ConnectionWrapper connectionWrapper){
    	DecretoDividendosBean decretoDividendosBean = null;
        try {
			
			callState = connectionWrapper.prepareCall(DERCORP_CON_REF_DOC_PKG.GET_VALUES_DECRETO_DIVIDEN_PR);
			callState.registerOutParameter(1, OracleTypes.CURSOR);
			callState.setInt(2, tIdMetaRow);
			callState.execute();

			resultSet = ((OracleCallableStatement) callState).getCursor(1);

			while(resultSet.next()){
				decretoDividendosBean = new DecretoDividendosBean(
						                      resultSet.getString("SOLICITUD")
						                     ,resultSet.getString("SOL_POR")
                                             ,resultSet.getString("FEC_DOC")
                                             ,resultSet.getString("FEC_REC")
                                             ,resultSet.getString("FOL_NUM")
                                             ,resultSet.getString("NUM_DOC_SOL")
                                             ,resultSet.getString("ACTA_RESOL")
                                             ,resultSet.getString("NUM_DOC_ACTA_RESOL")
                                             ,resultSet.getString("DOCUMENTO_ENTREGADO")
                                             ,resultSet.getString("NUM_DOC_DOC_ENT")
                                             ,resultSet.getString("FEC_DOC_E")
		                                     ,resultSet.getString("FEC_REC_E")
		                                     ,resultSet.getInt("AGREG_DOC")
                                             
                );
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

    	return decretoDividendosBean;
    }

    public DisminucionCapitalBean getValuesDisminucionCapital(int tIdMetaRow, ConnectionWrapper connectionWrapper){
    	DisminucionCapitalBean disminucionCapitalBean = null;
        try {

			callState = connectionWrapper.prepareCall(DERCORP_CON_REF_DOC_PKG.GET_VALUES_DISMINUCION_CAP_PR);
			callState.registerOutParameter(1, OracleTypes.CURSOR);
			callState.setInt(2, tIdMetaRow);
			callState.execute();

			resultSet = ((OracleCallableStatement) callState).getCursor(1);

			while(resultSet.next()){
				disminucionCapitalBean = new DisminucionCapitalBean(
						                      resultSet.getString("SOLICITUD")
						                     ,resultSet.getString("SOL_POR")
                                             ,resultSet.getString("FEC_DOC")
                                             ,resultSet.getString("FEC_REC")
                                             ,resultSet.getString("FOL_NUM")
                                             ,resultSet.getString("NUM_DOC_SOL")
                                             ,resultSet.getString("ACTA_RESOL")
                                             ,resultSet.getString("NUM_DOC_ACTA_RESOL")
                                             ,resultSet.getString("CONVOCATORIA")
                                             ,resultSet.getString("NUM_DOC_CONV")
                                             ,resultSet.getString("PUBLICACIONES")
                                             ,resultSet.getString("NUM_DOC_PUBLI")
                                             ,resultSet.getString("DOCUMENTO_ENTREGADO")
                                             ,resultSet.getString("NUM_DOC_DOC_ENT")
                                             ,resultSet.getString("FEC_DOC_E")
		                                     ,resultSet.getString("FEC_REC_E")
		                                     ,resultSet.getInt("AGREG_DOC")
                );
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

    	return disminucionCapitalBean;

    }

    public EscisionBean getValuesEscision(int tIdMetaRow, ConnectionWrapper connectionWrapper){
    	EscisionBean escisionBean = null;
        try {
			
			callState = connectionWrapper.prepareCall(DERCORP_CON_REF_DOC_PKG.GET_VALUES_ESCISION_PR);
			callState.registerOutParameter(1, OracleTypes.CURSOR);
			callState.setInt(2, tIdMetaRow);
			callState.execute();

			resultSet = ((OracleCallableStatement) callState).getCursor(1);

			while(resultSet.next()){
				escisionBean = new EscisionBean(
						                      resultSet.getString("SOLICITUD")
						                     ,resultSet.getString("SOL_POR")
                                             ,resultSet.getString("FEC_DOC")
                                             ,resultSet.getString("FEC_REC")
                                             ,resultSet.getString("FOLIO_NUM")
                                             ,resultSet.getString("NUM_DOC_SOL")
                                             ,resultSet.getString("ACTA_RESOL")
                                             ,resultSet.getString("NUM_DOC_ACTA_RESOL")
                                             ,resultSet.getString("CONVOCATORIA")
                                             ,resultSet.getString("NUM_DOC_CONVOC")
                                             ,resultSet.getString("PUBLICACIONES")
                                             ,resultSet.getString("NUM_DOC_PUBLIC")
                                             ,resultSet.getString("DOCUMENTO_ENTREGADO")
                                             ,resultSet.getString("NUM_DOC_DOC_ENT")
                                             ,resultSet.getString("FEC_DOC_E")
		                                     ,resultSet.getString("FEC_REC_E")
		                                     ,resultSet.getInt("AGREG_DOC")
                );
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

    	return escisionBean;
    }

    public FusionBean getValuesFusion(int tIdMetaRow, ConnectionWrapper connectionWrapper){
    	FusionBean fusionBean = null;
        try {
			
			callState = connectionWrapper.prepareCall(DERCORP_CON_REF_DOC_PKG.GET_VALUES_FUSION_PR);
			callState.registerOutParameter(1, OracleTypes.CURSOR);
			callState.setInt(2, tIdMetaRow);
			callState.execute();

			resultSet = ((OracleCallableStatement) callState).getCursor(1);

			while(resultSet.next()){
				fusionBean = new FusionBean(
				                      resultSet.getString("SOLICITUD")
				                     ,resultSet.getString("SOL_POR")
                                     ,resultSet.getString("FEC_DOC")
                                     ,resultSet.getString("FEC_REC")
                                     ,resultSet.getString("FOLIO_NUM")
                                     ,resultSet.getString("NUM_DOC_SOL")
                                     ,resultSet.getString("ACTA_RESOL")
                                     ,resultSet.getString("NUM_DOC_ACTA_RESOL")
                                     ,resultSet.getString("CONVOCATORIA")
                                     ,resultSet.getString("NUM_DOC_CONVOC")
                                     ,resultSet.getString("PUBLICACIONES")
                                     ,resultSet.getString("NUM_DOC_PUBLIC")
                                     ,resultSet.getString("DOCUMENTO_ENTREGADO")
                                     ,resultSet.getString("NUM_DOC_DOC_ENT")
                                     ,resultSet.getString("CONVENIO_FUSION")
                                     ,resultSet.getString("NUM_DOC_CONVEN_FUSION")
                                     ,resultSet.getString("FEC_DOC_E")
		                             ,resultSet.getString("FEC_REC_E")
		                             ,resultSet.getInt("AGREG_DOC")
                );
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

        return fusionBean;
    }

    public SesionConsejoBean getValuesSesionConsejo(int tIdMetaRow, ConnectionWrapper connectionWrapper){
    	SesionConsejoBean sesionConsejoBean = null;
        try {
			
			callState = connectionWrapper.prepareCall(DERCORP_CON_REF_DOC_PKG.GET_VALUES_SESION_CONSEJO_PR);
			callState.registerOutParameter(1, OracleTypes.CURSOR);
			callState.setInt(2, tIdMetaRow);
			callState.execute();

			resultSet = ((OracleCallableStatement) callState).getCursor(1);

			while(resultSet.next()){
				sesionConsejoBean = new SesionConsejoBean(
				                      resultSet.getString("SOLICITUD")
				                     ,resultSet.getString("SOL_POR")
                                     ,resultSet.getString("FEC_DOC")
                                     ,resultSet.getString("FEC_REC")
                                     ,resultSet.getString("FOLIO_NUM")
                                     ,resultSet.getString("NUM_DOC_SOL")
                                     ,resultSet.getString("ACTA_RESOL")
                                     ,resultSet.getString("NUM_DOC_ACTA_RESOL")
                                     ,resultSet.getString("CONVOCATORIA")
                                     ,resultSet.getString("NUM_DOC_CONVOC")
                                     ,resultSet.getString("DOCUMENTO_ENTREGADO")
                                     ,resultSet.getString("NUM_DOC_DOC_ENT")
                                     ,resultSet.getString("FEC_DOC_E")
		                             ,resultSet.getString("FEC_REC_E")
		                             ,resultSet.getInt("AGREG_DOC")
                );
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

    	return sesionConsejoBean;

    }

}