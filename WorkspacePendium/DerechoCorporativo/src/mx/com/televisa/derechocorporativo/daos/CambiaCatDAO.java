/**
* Project: Derecho Corporativo
*
* File: CambiaCatDAO.java
*
* Created on: Julio 31, 2016 at 12:00
*
* Copyright (c) - Televisa - 2015
*/
package mx.com.televisa.derechocorporativo.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mx.com.televisa.derechocorporativo.bean.DetalleCatagoBean;
import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;

/**
 * Acceso y manipulación de los catalogos
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
public class CambiaCatDAO {

	private ConnectionWrapper 	puConnectionWrapper;
	private Connection 		    puConnection;
	private ResultSet           puResultSet;
	private PreparedStatement   puPreparedStatement;
	
	/**
	 * Metodo Cosntruccion
	 */
	public CambiaCatDAO(){
		puConnectionWrapper = null;
		puConnection        = null;
		puResultSet         = null;
		puPreparedStatement = null;
	}

	/**
	 * Consulta un catalogo
	 * @param psNumPais
	 * @return Arreglo de DetalleCatagoBean
	 */
	public ArrayList<DetalleCatagoBean> consultaNuevoCat(String psNumPais){
		ArrayList<DetalleCatagoBean> laDetalleCatago = new ArrayList<DetalleCatagoBean>();
		DetalleCatagoBean luDetCatEntFed;
		String lsSql;

		try{
			lsSql =    "SELECT   EF.ID_CATALOGO_VALOR,"+
						         "EF.COD_CAT_VAL,"+
						         "EF.NOM_CAT_VAL,"+
						         "EF.VAL_CAT_VAL,"+
						         "EF.DES_CAT_VAL"+
						" FROM     DERCORP_ADD_CAMPO_CAT_VAL_TAB EF"+
						" WHERE    1=1"+
						" AND      EF.ID_CATALOGO = 13"+
						" AND      EF.DES_CAT_VAL = ("+
						                              "SELECT   VAL_CAT_VAL"+
						                              " FROM     DERCORP_ADD_CAMPO_CAT_VAL_TAB"+
						                              " WHERE    1=1"+
						                              " AND      ID_CATALOGO = 7"+
						                              " AND      ID_CATALOGO_VALOR = ?"+
                          ") ORDER BY TO_NUMBER(EF.COD_CAT_VAL)";

			puConnectionWrapper = new ConnectionWrapper();
			puPreparedStatement = puConnectionWrapper.prepareStatement(lsSql);
			puPreparedStatement.setString(1, psNumPais);
            puResultSet = puPreparedStatement.executeQuery();

            while(puResultSet.next()){
            	luDetCatEntFed = new DetalleCatagoBean();

            	luDetCatEntFed.setIdCatalogoVal(puResultSet.getInt(1));
            	luDetCatEntFed.setIdentificador(puResultSet.getString(2));
            	luDetCatEntFed.setNombre(puResultSet.getString(3));
            	luDetCatEntFed.setValor(puResultSet.getString(4));
            	luDetCatEntFed.setDescripcion(puResultSet.getString(5));
            	
            	laDetalleCatago.add(luDetCatEntFed);

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
				puResultSet.close();
				puPreparedStatement.close();
				//puConnection.close();
				puConnectionWrapper.close();
			} catch (SQLException sqlerr) {
				sqlerr.printStackTrace();
			}
		}

		return laDetalleCatago;

	}
	
	/**
	 * consulta un campo valor 
	 * @param psNumEmpresa
	 * @param psIdCampo
	 * @return String
	 */
	public String consultaCampoValor(String psNumEmpresa, String psIdCampo){
		String lsSql;
		String lsCampoValor = null;
		try{
			lsSql =    "SELECT  VAL_VALOR "+
						"FROM    DERCORP_ADD_CAMPO_VALOR_TAB "+
						"WHERE   1=1 "+
						"AND     ID_EMPRESA = ? "+
						"AND     ID_ADD_CAMPO = ?";

			puConnectionWrapper = new ConnectionWrapper();
			puPreparedStatement = puConnectionWrapper.prepareStatement(lsSql);
			puPreparedStatement.setString(1, psNumEmpresa);
			puPreparedStatement.setString(2, psIdCampo);
			puResultSet = puPreparedStatement.executeQuery();
			
			while(puResultSet.next()){
				lsCampoValor = puResultSet.getString(1);
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
				puResultSet.close();
				puPreparedStatement.close();
				//puConnection.close();
				puConnectionWrapper.close();
			} catch (SQLException sqlerr) {
				sqlerr.printStackTrace();
			}
		}
		return lsCampoValor;

	}

}