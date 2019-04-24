package mx.com.televisa.derechocorporativo.modules.dynhtml;

import java.sql.CallableStatement;
import java.sql.SQLException;

import oracle.jdbc.OracleTypes;
import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;
import mx.com.televisa.derechocorporativo.data.packages.DERCORP_CAPTURA_PKG;
import mx.com.televisa.derechocorporativo.modules.flextabs.FlexColumn;

public class TextNumero {
	
	public static String getTextNumero(FlexColumn flexColumn, String value, int tiIdFlexTable, String tsEmpresa, ConnectionWrapper tuConnectionWrapper){
		//String lstValue = getMoneda(tsEmpresa, tuConnectionWrapper);
		
		return "<input type='text' title ='"+ value +"' name='" + flexColumn.COD_FLEX_COLUM +"' id='" +tiIdFlexTable + "__" + flexColumn.COD_FLEX_COLUM + "' size='" + flexColumn.CAN_TAMANN_COLUM + "' style='"+flexColumn.DES_RULE+"' value='" + value + "' "+flexColumn.DES_FORMULA+">"
               +"&nbsp<b>"
			   +getMoneda(tsEmpresa, tuConnectionWrapper)+"</b>"
               //+SelectList.getSelectMoneda(tuConnectionWrapper, flexColumn, "", tiIdFlexTable, tsEmpresa)
				;

	}

	public static String getTextNumeroDisabled(String value, String tsEmpresa, ConnectionWrapper tuConnectionWrapper){
		return value
               +"  <b>"+getMoneda(tsEmpresa, tuConnectionWrapper)+"</b>"
        ;

	}
	
	public static String getMoneda(String tsEmpresa, ConnectionWrapper tuConnectionWrapper){
        CallableStatement luCallableStatement = null;
        String            lsMoneda            = "";

        try{
        	luCallableStatement = tuConnectionWrapper.prepareCall(DERCORP_CAPTURA_PKG.GET_MONEDA_PR);
        	luCallableStatement.setInt(1, Integer.parseInt(tsEmpresa));
        	luCallableStatement.registerOutParameter(2, OracleTypes.VARCHAR);
            luCallableStatement.execute();

            lsMoneda = luCallableStatement.getString(2);

        } catch (Exception e) {
            e.printStackTrace();

        }finally {
            try {
            	luCallableStatement.close();

            } catch (SQLException e) {
            e.printStackTrace();
	        }
        }
		return lsMoneda;
	}

}