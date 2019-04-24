package mx.com.televisa.derechocorporativo.daos;

import java.sql.CallableStatement;
import java.sql.ResultSet;

import org.apache.log4j.Logger;

import mx.com.televisa.derechocorporativo.bean.reformas.FusionBean;
import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;
import mx.com.televisa.derechocorporativo.data.packages.DERCORP_CONSULTA_PKG;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

public class FusionDAO {
	
	private final static Logger log = Logger.getLogger(FusionDAO.class);

	private CallableStatement callState;
	private ResultSet resultSet;	

	public FusionDAO(){
		callState = null;
		resultSet = null;

	}

	public FusionBean getCapFijVarSocAumDisPr(int tIdMetaRow, ConnectionWrapper connectionWrapper){
		FusionBean fusionBean = null;
		try {
			callState = connectionWrapper.prepareCall(DERCORP_CONSULTA_PKG.GET_CAP_FIJ_VAR_SOC_AUM_DIS_PR);
			callState.registerOutParameter(1, OracleTypes.CURSOR);
			callState.setInt(2, tIdMetaRow);
			callState.execute();

			resultSet = ((OracleCallableStatement) callState).getCursor(1);

			while(resultSet.next()){
				fusionBean = new FusionBean(resultSet.getString("CAPITAL_FIJO_AUM"),
                                            resultSet.getString("DE_CAP_FIJ_AUM"),
                                            resultSet.getString("CON_CAP_FIJ_AUM"),
                                            resultSet.getString("QUEDAR_CAP_FIJ_AUM"),
                                            resultSet.getString("CAPITAL_VARIABLE_AUM"),
                                            resultSet.getString("DE_CAP_VAR_AUM"),
                                            resultSet.getString("CON_CAP_VAR_AUM"),
                                            resultSet.getString("QUEDAR_CAP_VAR_AUM"),
                                            resultSet.getString("CAPITAL_SOCIAL_AUM"),
                                            resultSet.getString("CAPITAL_FIJO_DIS"),
                                            resultSet.getString("DE_CAP_FIJ_DIS"),
                                            resultSet.getString("CON_CAP_FIJ_DIS"),
                                            resultSet.getString("QUEDAR_CAP_FIJ_DIS"),
                                            resultSet.getString("CAPITAL_VARIABLE_DIS"),
                                            resultSet.getString("DE_CAP_VAR_DIS"),
                                            resultSet.getString("CON_CAP_VAR_DIS"),
                                            resultSet.getString("QUEDAR_CAP_VAR_DIS"),
                                            resultSet.getString("CAPITAL_SOCIAL_DIS"),
                                            resultSet.getString("CAPITAL_TOTAL")
                );
             }
		} catch (Exception e) {
            e.printStackTrace();

		}

		return fusionBean;

	}

	public FusionBean getCamposValoresFusion(int tIdMetaRow, ConnectionWrapper connectionWrapper){
		FusionBean fusionBean = null;

		try {
			callState = connectionWrapper.prepareCall(DERCORP_CONSULTA_PKG.GET_CAMPOS_VALORES_FUSION_PR);
			callState.registerOutParameter(1, OracleTypes.CURSOR);
			callState.setInt(2, tIdMetaRow);
			callState.execute();

			resultSet = ((OracleCallableStatement) callState).getCursor(1);

			while(resultSet.next()){
				fusionBean = new FusionBean(resultSet.getString("Asunto")
						                   ,resultSet.getString("TipoReunion")
						                   ,resultSet.getString("SociedadFusionante")
						                   ,resultSet.getString("Fecha")
						                   ,resultSet.getString("Hora")
						                   ,resultSet.getString("AsambleasSociedadesFusionadas")
						                   ,resultSet.getString("FechaEfectosPartes")
						                   ,resultSet.getString("FechaEfectosTerceros")
						                   ,resultSet.getString("ArtClauEstatRefor")
						                   ,resultSet.getString("OtrosObservaciones")
						                   ,resultSet.getString("OtrosRegistros")
                           );
			}

		} catch (Exception e) {
          e.printStackTrace();
		}
		return fusionBean;
	}

}