package mx.com.televisa.derechocorporativo.daos;

import java.sql.SQLException;
import java.sql.CallableStatement;

import mx.com.televisa.derechocorporativo.bean.LogStatConectBean;
import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;
import mx.com.televisa.derechocorporativo.data.packages.PENDIUM_SS_LOG_STAT_CONECT_PKG;

public class LogStatConectDAO {

	public LogStatConectDAO(){
    	
    }

	public void insertarLogStatConect(LogStatConectBean tuLogStaConBean){
		ConnectionWrapper luConnectionWrapper = null;
		CallableStatement callableStatement;

		try {
			luConnectionWrapper = new ConnectionWrapper();
			callableStatement = luConnectionWrapper.prepareCall(
                PENDIUM_SS_LOG_STAT_CONECT_PKG.INSERTAR_LOG_STAT_CONECT_PR
            );
			callableStatement.setInt(1, tuLogStaConBean.getIdUser());
			callableStatement.execute();

		} catch (SQLException sqle) {
            sqle.printStackTrace();

		} catch (Exception e) {
            e.printStackTrace();
		}finally{
			if(luConnectionWrapper != null)
				luConnectionWrapper.close();
		}
	}

}
