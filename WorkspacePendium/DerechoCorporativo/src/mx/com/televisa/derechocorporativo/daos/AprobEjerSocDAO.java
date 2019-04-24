/**
* Project: Derecho Corporativo
*
* File: AprobEjerSocDAO.java
*
* Created on: Julio 31, 2016 at 12:00
*
* Copyright (c) - Televisa - 2015
*/
package mx.com.televisa.derechocorporativo.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import mx.com.televisa.derechocorporativo.bean.reformas.AprobEjerSocBean;
import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;
import mx.com.televisa.derechocorporativo.data.packages.DERCORP_CONSULTA_PKG;
import mx.com.televisa.derechocorporativo.util.ServiceLocator;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

/**
 * Acceso a datos para la tabla de configuracion 
 *
 * @author KAZ - CONSULTING/
 *         Iván Castañeda Loeza
 *         Jesús Argumedo
 *         Eduardo Nava
 *         Ernesto Corona Mendoza
 *
 *
 *
 * @version 1.0.0
 *
 * @date Julio 31, 2015 at 12:00 pm
 *
 */
public class AprobEjerSocDAO {

	private CallableStatement callState;
	private ResultSet resultSet;

	/**
	 * Metodo Constructor
	 */
	public AprobEjerSocDAO(){
		callState = null;
		resultSet = null;
	}

	/**
	 * Obtiene Resultado de aporobacion del ejercicio soscial
	 * @param tIdMetaRow
	 * @param connectionWrapper
	 * @return AprobEjerSocBean
	 */
	public AprobEjerSocBean getResultado(int tIdMetaRow, ConnectionWrapper connectionWrapper){
		AprobEjerSocBean aprobEjerSocBean = null;

		try {
			callState = connectionWrapper.prepareCall(DERCORP_CONSULTA_PKG.GET_AES_RESULTADO_PR);
			callState.registerOutParameter(1, OracleTypes.CURSOR);
			callState.setInt(2, tIdMetaRow);
			callState.execute();

			resultSet = ((OracleCallableStatement) callState).getCursor(1);

			while(resultSet.next()){
				aprobEjerSocBean = new AprobEjerSocBean(resultSet.getString("UTILIDAD")
                                                       ,resultSet.getString("PERDIDA")
                );

			}

		} catch (Exception e) {
            e.printStackTrace();

		}
		return aprobEjerSocBean;
	}

	/**
	 * Obtiene otros Acuerdos
	 * @param tiIdEmpresa
	 * @param tuConnectionWrapper
	 * @return AprobEjerSocBean
	 */
    public AprobEjerSocBean getOtrosAcuerdos(int tiIdEmpresa, ConnectionWrapper tuConnectionWrapper){
    	AprobEjerSocBean aprobEjerSocBean = null;
    	try {
    		callState = tuConnectionWrapper.prepareCall(DERCORP_CONSULTA_PKG.GET_AES_OTROS_ACUERDOS_PR);
    		callState.registerOutParameter(1, OracleTypes.CURSOR);
    		callState.setInt(2, tiIdEmpresa);
    		callState.execute();

    		resultSet = ((OracleCallableStatement) callState).getCursor(1);

    		while(resultSet.next()){
    			aprobEjerSocBean = new AprobEjerSocBean(resultSet.getString("APROB_DIC_FISCAL")
                                                       ,resultSet.getString("DECRE_DIVIDENDOS")
                                                       ,resultSet.getString("RATIFI_CONSEJEROS")
                                                       ,resultSet.getString("RATIFI_FUNCIONARIOS")
                                                       ,resultSet.getString("RATIFI_COMISARIOS")
                                                       ,resultSet.getString("DESIG_CONSEJEROS")
                                                       ,resultSet.getString("DESIG_FUNCIONARIOS")
                                                       ,resultSet.getString("DESIG_COMISARIOS")
                                                       ,resultSet.getString("OTORGA_PODERES")
                                                       ,resultSet.getString("REVOCA_PODERES")
                );
    		}

		} catch (Exception e) {
            e.printStackTrace();
		}

    	return aprobEjerSocBean;
    }

}