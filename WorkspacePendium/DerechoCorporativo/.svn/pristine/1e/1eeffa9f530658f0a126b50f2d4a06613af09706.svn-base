package mx.com.televisa.derechocorporativo.daos;

import java.sql.CallableStatement;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;
import mx.com.televisa.derechocorporativo.data.packages.DERCORP_REPORTS_PKG;

public class ReportesDAO {
	private ConnectionWrapper connectionWrapper;
	private CallableStatement callableStatement;

	public ReportesDAO(){
		connectionWrapper = null;
		callableStatement = null;
	}

	public boolean execProcess_Rep_RM_AES(int tiIdUser, String tsEjercicioSocial){
		boolean lbEndProcessSucc = false;
		try {
			connectionWrapper = new ConnectionWrapper();
			callableStatement = connectionWrapper.prepareCall(DERCORP_REPORTS_PKG.GET_REP_RM_AES_PR);
			callableStatement.setString(1, tsEjercicioSocial);
			callableStatement.setInt(2, tiIdUser);

			lbEndProcessSucc = callableStatement.execute();

		} catch (Exception e) {
            e.printStackTrace();
		}finally{
			connectionWrapper.close();
		}

		return lbEndProcessSucc;
	}

}