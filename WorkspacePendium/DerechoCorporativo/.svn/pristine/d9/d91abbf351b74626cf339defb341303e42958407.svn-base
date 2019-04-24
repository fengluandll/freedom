/**
* Project: Derecho Corporativo
*
* File: Abc_AppConfigTabDAO.java
*
* Created on: Julio 31, 2016 at 12:00
*
* Copyright (c) - Televisa - 2015
*/
package mx.com.televisa.derechocorporativo.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mx.com.televisa.derechocorporativo.bean.Abc_AppConfigTabBean;
import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;
import mx.com.televisa.derechocorporativo.data.packages.APP_CONFIG_PKG;



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
public class Abc_AppConfigTabDAO {

	private ConnectionWrapper 	puConnectionWrapper;
	private Connection 		    puConnection;
	private ResultSet           puResultSet;
	private PreparedStatement   puPreparedStatement;
	private CallableStatement   puCallableStatement;
	
	/**
	 * Metodo Cnstructor
	 */
	public Abc_AppConfigTabDAO(){
		puConnectionWrapper = null;
		puConnection        = null;
		puResultSet         = null;
		puPreparedStatement = null;
		puCallableStatement = null;
	}
	
	public boolean isUserOper(int idUser){
		String sql = "SELECT TRIM(nom_name) \n"+
					 "	FROM SS_ROL_TAB\n"+
					 "	WHERE id_rol = (SELECT id_rol \n"+
					 "	                FROM SS_USER_ROL_TAB ss \n"+
					 "	                WHERE ss.id_user = ?)";
		boolean paso = false;
		try {
			puConnectionWrapper = new ConnectionWrapper();
			PreparedStatement psmt = puConnectionWrapper.prepareStatement(sql);
			psmt.setInt(1, idUser);
			puResultSet = psmt.executeQuery();
			String nomRol = null;
			while(puResultSet.next()){
				nomRol = puResultSet.getString(1);
			}
			
			if(nomRol != null && nomRol.equals("USEROPER")){
				paso = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			puConnectionWrapper.close();
		}
		return paso;
	}

	/**
	 * Consulta toda la tabla
	 * @return lista de los resultados
	 */
	public ArrayList<Abc_AppConfigTabBean> ConsultaAppConfigTab(){

		Abc_AppConfigTabBean lAbc_AppConfigTabBean;
		String               lsSQL;

		ArrayList<Abc_AppConfigTabBean> alConsultaAppConfigTab = new ArrayList<Abc_AppConfigTabBean>();

		try{
			puConnectionWrapper = new ConnectionWrapper();

			lsSQL =    "SELECT    ID_CONFIG,"+
						          "COD_CONFIG,"+
						          "NOM_CONFIG,"+
						          "DES_CONFIG,"+
						          "VAL_CONFIG "+
						"FROM      APP_CONFIG_TAB "+
						"WHERE     1=1 "+
						"ORDER BY  ID_CONFIG"
			;

			puConnection = puConnectionWrapper.getConnection();
			puPreparedStatement  =  puConnection.prepareStatement(lsSQL);
			puResultSet = puPreparedStatement.executeQuery();

			while(puResultSet.next()){
				lAbc_AppConfigTabBean = new Abc_AppConfigTabBean();

				lAbc_AppConfigTabBean.setIdConfig(puResultSet.getInt(1));
				lAbc_AppConfigTabBean.setCodConfig(puResultSet.getString(2));
				lAbc_AppConfigTabBean.setNomConfig(puResultSet.getString(3));
				lAbc_AppConfigTabBean.setDesConfig(puResultSet.getString(4));
				lAbc_AppConfigTabBean.setValConfig(puResultSet.getString(5));

				alConsultaAppConfigTab.add(lAbc_AppConfigTabBean);
			}

		}catch(SQLException sqlerr){
			sqlerr.getErrorCode();
			sqlerr.getMessage();
			sqlerr.getStackTrace();
			sqlerr.printStackTrace();
		}catch(Exception err){
			err.printStackTrace();
		}
		finally{
			try {
				//puResultSet.close();
				puPreparedStatement.close();
				puConnection.close();
				puConnectionWrapper.close();
			} catch (SQLException sqlerr) {
				sqlerr.printStackTrace();
			}
		}

		return alConsultaAppConfigTab;

	}
	
	/**
	 * Inserta registro en la tabla de configuracion
	 * @param psCodCon
	 * @param psNomCon
	 * @param psDesCon
	 * @param psValCon
	 * @return
	 */
	public String insertarAppConfigTab(String psCodCon, String psNomCon, String psDesCon, String psValCon){
		try {
			puConnectionWrapper = new ConnectionWrapper();
			puCallableStatement = puConnectionWrapper.prepareCall(APP_CONFIG_PKG.ADD_APP_CONFIG_PR);
			puCallableStatement.setString(1, psCodCon);
			puCallableStatement.setString(2, psNomCon);
			puCallableStatement.setString(3, psDesCon);
			puCallableStatement.setString(4, psValCon);
			puCallableStatement.registerOutParameter(5, java.sql.Types.VARCHAR);
			puCallableStatement.execute();

			return puCallableStatement.getString(5);

        } catch (Exception e) {
        	e.printStackTrace();
        	return e.toString();
        }finally {
            try {
            	//puPreparedStatement.close();
                //puResultSet.close();
                puCallableStatement.close();
                puConnectionWrapper.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}
	
	/**
	 * Actualiza Registro en la tabla de configuracion
	 * @param psIdCon
	 * @param psCodCon
	 * @param psNomCon
	 * @param psDesCon
	 * @param psValCon
	 * @return
	 */
	public String actualizarAppConfigTab(String psIdCon, String psCodCon, String psNomCon, String psDesCon, String psValCon){
		try {
			puConnectionWrapper = new ConnectionWrapper();
			puCallableStatement = puConnectionWrapper.prepareCall(APP_CONFIG_PKG.UPDATE_APP_CONFIG_PR);
			puCallableStatement.setString(1, psIdCon);
			puCallableStatement.setString(2, psCodCon);
			puCallableStatement.setString(3, psNomCon);
			puCallableStatement.setString(4, psDesCon);
			puCallableStatement.setString(5, psValCon);
			puCallableStatement.registerOutParameter(6, java.sql.Types.VARCHAR);
			puCallableStatement.execute();

			return puCallableStatement.getString(6);

        } catch (Exception e) {
        	e.printStackTrace();
        	return e.toString();
        }finally {
            try {
                puCallableStatement.close();
                puConnectionWrapper.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}
	
	/**
	 * Borra registro en la tabla de configuracion
	 * @param psIdCon
	 * @return valor Estatus
	 */
	public String borrarAppConfigTab(String psIdCon){
		try {
			puConnectionWrapper = new ConnectionWrapper();
			puCallableStatement = puConnectionWrapper.prepareCall(APP_CONFIG_PKG.DELETE_APP_CONFIG_PR);
			puCallableStatement.setString(1, psIdCon);
			puCallableStatement.registerOutParameter(2, java.sql.Types.VARCHAR);
			puCallableStatement.execute();

			return puCallableStatement.getString(2);

        } catch (Exception e) {
        	e.printStackTrace();
        	return e.toString();
        }finally {
            try {
                puCallableStatement.close();
                puConnectionWrapper.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}
	
	/**
	 * Obtiene el ambiente, Desarrollo o produccion
	 * @return
	 */
	public String getAmbiente(){
    	String lsSql = "";
    	String lsAmbiente = "";
    	int liContador = 0;
    	String ambiente = "";
		puConnection        = null;
		puResultSet         = null;
		puPreparedStatement = null;

    	try{

    	//	lsSql = "SELECT COUNT(1) AS COUNT FROM APP_CONFIG_TAB WHERE 1=1 AND VAL_CONFIG = 'Desarrollo'";
    		//lsSql = "SELECT COUNT(1) AS COUNT FROM APP_CONFIG_TAB WHERE 1=1 AND VAL_CONFIG = 'Ambiente de Calidad (QA)'";
    		
    		lsSql = "SELECT VAL_CONFIG AS AMBIENTE FROM APP_CONFIG_TAB WHERE 1=1 AND COD_CONFIG = 'AMB_ACTUAL'";

			puConnectionWrapper = new ConnectionWrapper();
			puConnection = puConnectionWrapper.getConnection();
			puPreparedStatement  =  puConnection.prepareStatement(lsSql);
			puResultSet = puPreparedStatement.executeQuery();

			while(puResultSet.next()){
				lsAmbiente = puResultSet.getString("AMBIENTE");
			}
			
			if(lsAmbiente.equalsIgnoreCase("Producción"))
				lsAmbiente = "";
			
/*
			if(liContador == 1)
				lsAmbiente = "Ambiente de Calidad (QA)";
			else
				lsAmbiente = "";*/

    	} catch (Exception e) {
            e.printStackTrace();

        }finally {
			try {
				puResultSet.close();
				puPreparedStatement.close();
				puConnection.close();
				puConnectionWrapper.close();
			} catch (SQLException sqlerr) {
				sqlerr.printStackTrace();
			}          
        }

    	return lsAmbiente;

    }

}