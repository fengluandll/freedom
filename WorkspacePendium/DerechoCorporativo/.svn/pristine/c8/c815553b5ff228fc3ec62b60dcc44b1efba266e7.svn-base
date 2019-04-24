package mx.com.televisa.derechocorporativo.daos;

import java.sql.CallableStatement;
import javax.servlet.ServletRequest;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;
import mx.com.televisa.derechocorporativo.data.packages.DERCORP_CAPTURA_PKG;

public class ResumenGeneralDAO {

	public static String getTelefono(ServletRequest tuServletRequest){
        StringBuilder lsTelefono = new StringBuilder();
    	CallableStatement callState;
    	ConnectionWrapper connWra = null;
    	int tiIdDomCom = Integer.parseInt(tuServletRequest.getParameter("idDomCom"));

		try {
			connWra = new ConnectionWrapper();
			callState = connWra.prepareCall(DERCORP_CAPTURA_PKG.GET_TEL_COMERCIAL_PR);
			callState.setInt(1, tiIdDomCom);
			callState.registerOutParameter(2, OracleTypes.VARCHAR);
			callState.execute();

			lsTelefono.append(((OracleCallableStatement) callState).getString(2));

		} catch (Exception e) {
			lsTelefono.append("");

		}finally{
			if (connWra != null)
				connWra.close();
		}

        return lsTelefono.toString();

	} 

}