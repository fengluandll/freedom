package mx.com.televisa.derechocorporativo.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;
import mx.com.televisa.derechocorporativo.data.packages.DERCORP_CONSULTA_PKG;

public class AdmVigDAO {
	private CallableStatement   puCallableStatement;
	private ResultSet           puResultSet;

	public AdmVigDAO(){
		puCallableStatement = null;
		puResultSet = null;
	}

	public int getSuperindice(int tiIdMetaRow,int tidEmpresa,int tidFlex){
		int liSuperIndice = 0;

		ConnectionWrapper puConnectionWrapper = null;
		
		try {
			puConnectionWrapper = new ConnectionWrapper();
			puCallableStatement = puConnectionWrapper.prepareCall(DERCORP_CONSULTA_PKG.GET_ADM_VIG_NOMBRE_SUPLENTE_PR);
			puCallableStatement.setInt(1, tiIdMetaRow);
			puCallableStatement.setInt(2, tidEmpresa);
			puCallableStatement.setInt(3, tidFlex);
			puCallableStatement.registerOutParameter(4, OracleTypes.NUMBER);
			puCallableStatement.execute();

			liSuperIndice = ((OracleCallableStatement) puCallableStatement).getInt(4);

		} catch (Exception e) {
            e.printStackTrace();
		}finally{
			try {
				puCallableStatement.close();
				puConnectionWrapper.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return liSuperIndice;

	}

	public String getReferenciaNotaPie(int tiIdFlex
                                      ,int tiIdEmp
    ){

		StringBuilder lsReferenciaNotaPie = new StringBuilder();

		ConnectionWrapper puConnectionWrapper = null;
		
		try {
			puConnectionWrapper = new ConnectionWrapper();
			puCallableStatement = puConnectionWrapper.prepareCall(DERCORP_CONSULTA_PKG.GET_ADM_VIG_REF_NOTA_PIE_PR);
			puCallableStatement.setInt(1, tiIdFlex);
			puCallableStatement.setInt(2, tiIdEmp);
			puCallableStatement.registerOutParameter(3, OracleTypes.VARCHAR);
			puCallableStatement.execute();

			lsReferenciaNotaPie.append(((OracleCallableStatement) puCallableStatement).getString(3));

		} catch (Exception e) {
            e.printStackTrace();
        }finally{
        	try {
				puCallableStatement.close();
				puConnectionWrapper.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        	
        }

		return lsReferenciaNotaPie.toString();

	}
	
	public String insertNotaPie(int tiIdFlex,int tiIdEmp)
	{
			
			StringBuilder lsReferenciaNotaPie = new StringBuilder();
			ConnectionWrapper puConnectionWrapper = null;
			
			try {
				puConnectionWrapper = new ConnectionWrapper();
				puCallableStatement = puConnectionWrapper.prepareCall(DERCORP_CONSULTA_PKG.INSERT_SUBINDICE_ADMIN_VIG_PR);
				puCallableStatement.setInt(1, tiIdFlex);
				puCallableStatement.setInt(2, tiIdEmp);
				puCallableStatement.registerOutParameter(3, OracleTypes.VARCHAR);
				puCallableStatement.execute();
				
				lsReferenciaNotaPie.append(((OracleCallableStatement) puCallableStatement).getString(3));
			
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				try {
					puCallableStatement.close();
					puConnectionWrapper.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
			
			return lsReferenciaNotaPie.toString();
			
			}
	
	

}