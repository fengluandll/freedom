/**
* Project: Derecho Corporativo
*
* File: ConsultaDAO.java
*
* Created on: Julio 31, 2016 at 12:00
*
* Copyright (c) - Televisa - 2015
*/
package mx.com.televisa.derechocorporativo.daos;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;
import mx.com.televisa.derechocorporativo.data.packages.DERCORP_CONSULTA_PKG;

/**
 * Acceso y manipulaci�n de los catalogos
 *
 * @author KAZ - CONSULTING/
 *         Iv�n Casta�eda Loeza
 *         Jes�s Argumedo
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
public class ConsultaDAO {
	private ConnectionWrapper 	puConnectionWrapper;
	private CallableStatement   puCallableStatement;
	private Connection 		    puConnection;
	private ResultSet           puResultSet;
	private PreparedStatement   puPreparedStatement;
	private Statement			statement;

	/**
	 * Metodo Constructor
	 */
	public ConsultaDAO(){
		puConnectionWrapper = null;
		puCallableStatement = null;
		puConnection        = null;
		puResultSet         = null;
		puPreparedStatement = null;

	}
	
	/**

	 * Revisar si hay suplentes en Flex de Adm y Vig

	 * @param tiIdEmpresa

	 * @param tiIdFlex

	 * @return int

	 */

	public int getNumSuplentes(int tiIdFlex, int tiIdEmpresa){

		int numSuplentes = 0;

		String sql;

		if(tiIdFlex!=15){

			sql = "select count(1) from ("

				+ "SELECT   META.*"

				+ "    FROM     DERCORP_CON_ADM_VIG_TMP META "

				+ "    WHERE    META.ID_EMPRESA  =  ? " 

				+ "    AND      META.ID_FLEX_TBL =  ? "
				
				+ "    AND      META.VAL_C15 IS NOT NULL"

				+ "    AND      ( (META.VAL_C4 IS NULL ) OR (META.VAL_C7 IS NULL ) )"
				
				+ "    AND      META.VAL_C5 != 0"

				+ "    ORDER BY TO_NUMBER(META.VAL_C15), DERCORP_CATALOGS_PKG.GET_FUNCIONARIOS_FN(META.VAL_C1))";

		}else{

			sql = "select count(1) from ("

					+ "SELECT   META.*"

					+ "    FROM     DERCORP_CON_ADM_VIG_TMP META "

					+ "    WHERE    META.ID_EMPRESA  =  ? " 

					+ "    AND      META.ID_FLEX_TBL =  ? "

					+ "    AND      META.VAL_C5 != 0"
					
					+ "    ORDER BY TO_NUMBER(META.VAL_C15), DERCORP_CATALOGS_PKG.GET_FUNCIONARIOS_FN(META.VAL_C1))";

		}

		

		try{

			puConnectionWrapper = new ConnectionWrapper();

			puConnection = puConnectionWrapper.getConnection();

			puPreparedStatement =  puConnection.prepareStatement(sql);

			puPreparedStatement.setInt(1, tiIdEmpresa);

			puPreparedStatement.setInt(2, tiIdFlex);

			puResultSet = puPreparedStatement.executeQuery();

			

			while(puResultSet.next()){

				numSuplentes = puResultSet.getInt(1);

			}

			

		} catch(Exception e){

			e.printStackTrace();

		}finally{
			try {
				puResultSet.close();
				puPreparedStatement.close();
				puConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}

		return numSuplentes;

	}
	
	/**
	 * Ejecuta procedimeinto de Admin Vigilancia
	 * @param tiIdEmpresa
	 * @param tsIdFlex
	 */
	public void ejecutar_Pr_Adm_Vig(String tiIdEmpresa, int tsIdFlex){
		try{
			puConnectionWrapper = new ConnectionWrapper();
			puCallableStatement = puConnectionWrapper.prepareCall(DERCORP_CONSULTA_PKG.CON_ADM_VIG_PR);
			puCallableStatement.setInt(1, Integer.parseInt(tiIdEmpresa));
			puCallableStatement.setInt(2, tsIdFlex);
			puCallableStatement.execute();

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
	            puCallableStatement.close();
	            puConnectionWrapper.close();
				
			}catch(Exception e){
				e.printStackTrace();	
			}
		}

	}
	
	/**
	 * JJAQ 27/01/2019 PARA EL REPORTE PREDEFINIDO DE ADMIN Y VIGILANCIA SE AGREGA IN EN EL QUERY PARA QUE SE TRAIGA
     * SOLO A LAS PERSONAS QUE SOLICITEN EN EL REPORTE
	 * @param tiIdEmpresa
	 * @param tsIdFlex
	 * @param nombres
	 */
	public void ejecutar_Pr_Adm_Vig(String tiIdEmpresa, int tsIdFlex,String nombres){
		try{
			puConnectionWrapper = new ConnectionWrapper();
			puCallableStatement = puConnectionWrapper.prepareCall(DERCORP_CONSULTA_PKG.CONSULTAR_ADM_VIG_BY_NOMBRE_PR);
			puCallableStatement.setInt(1, Integer.parseInt(tiIdEmpresa));
			puCallableStatement.setInt(2, tsIdFlex);
			puCallableStatement.setString(3, nombres);
			puCallableStatement.execute();

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
	            puCallableStatement.close();
	            puConnectionWrapper.close();
				
			}catch(Exception e){
				e.printStackTrace();	
			}
		}

	}
	
	/**
	 * ECM 09 Mayo 2016 Consulta - Adm y Vig - Ocultar Flex sin registros.
	 * JJAQ 18-10-2018 Se corrije query ya que no mostraba flex cuando si lo deberia de mostrar
	 * @param tiIdEmpresa
	 * @param tsIdFlex
	 * @return int
	 */
    public int consultar_Adm_Vig(String tiIdEmpresa, int tsIdFlex){

    	String lsSql ="SELECT   COUNT(1) "
    		    +"FROM     DERCORP_METATBL_TAB META "
    		    +"WHERE    META.ID_EMPRESA  =   ? "
    		    +"AND      META.ID_FLEX_TBL =  ? "
    		    +"AND      META.VAL_C15 IS NOT NULL "
    		    //+"AND      ( (META.VAL_C4 IS NULL ) OR (META.VAL_C7 IS NULL ) ) " //JJAQ 09/01/2019 Se comenta para el punto prioritario numero 16
    		    +"ORDER BY TO_NUMBER(META.VAL_C15), DERCORP_CATALOGS_PKG.GET_FUNCIONARIOS_FN(META.VAL_C1)";
    	int liCountRegistros = 0;

    	try{
			puConnectionWrapper = new ConnectionWrapper();
			puConnection = puConnectionWrapper.getConnection();
			puPreparedStatement  =  puConnection.prepareStatement(lsSql);
			puPreparedStatement.setInt(1, Integer.parseInt(tiIdEmpresa));
			puPreparedStatement.setInt(2, tsIdFlex);

			puResultSet = puPreparedStatement.executeQuery();

			while(puResultSet.next()){
				liCountRegistros = puResultSet.getInt(1);
			}

    	}catch(SQLException sqlerr){
			sqlerr.getErrorCode();
			sqlerr.getMessage();
			sqlerr.getStackTrace();
			sqlerr.printStackTrace();

		}catch(Exception err){
			err.printStackTrace();

		}finally{
			try {
				puResultSet.close();
				puPreparedStatement.close();
				puConnection.close();
				puConnectionWrapper.close();
			} catch (SQLException sqlerr) {
				sqlerr.printStackTrace();
			}
		}

    	return liCountRegistros;
    }
    
    /**
     * JJAQ 27/01/2019 PARA EL REPORTE PREDEFINIDO DE ADMIN Y VIGILANCIA SE AGREGA IN EN EL QUERY PARA QUE SE TRAIGA
	 * SOLO A LAS PERSONAS QUE SOLICITEN EN EL REPORTE
     * @param tiIdEmpresa
     * @param tsIdFlex
     * @param nombreFunc
     * @return
     */
    public int consultar_Adm_Vig(String tiIdEmpresa, int tsIdFlex,String nombreFunc){

    	String lsSql ="SELECT   COUNT(1) "
    		    +"FROM     DERCORP_METATBL_TAB META "
    		    +"WHERE    META.ID_EMPRESA  = "+tiIdEmpresa+" "
    		    +"AND      META.ID_FLEX_TBL in ("+tsIdFlex+") "
    		    +"AND      META.VAL_C15 IS NOT NULL "
    		    //+"AND      ( (META.VAL_C4 IS NULL ) OR (META.VAL_C7 IS NULL ) ) " //JJAQ 09/01/2019 Se comenta para el punto prioritario numero 16
    		    +"AND VAL_C1 IN("+nombreFunc+") "
    		    +"ORDER BY TO_NUMBER(META.VAL_C15), DERCORP_CATALOGS_PKG.GET_FUNCIONARIOS_FN(META.VAL_C1)";
    	int liCountRegistros = 0;

    	try{
			puConnectionWrapper = new ConnectionWrapper();
			puConnection = puConnectionWrapper.getConnection();
			statement  =  puConnection.createStatement();
			puResultSet = statement.executeQuery(lsSql);

			while(puResultSet.next()){
				liCountRegistros = puResultSet.getInt(1);
			}

    	}catch(SQLException sqlerr){
			sqlerr.getErrorCode();
			sqlerr.getMessage();
			sqlerr.getStackTrace();
			sqlerr.printStackTrace();

		}catch(Exception err){
			err.printStackTrace();

		}finally{
			try {
				statement.close();
				puResultSet.close();
				puConnection.close();
				puConnectionWrapper.close();
			} catch (SQLException sqlerr) {
				sqlerr.printStackTrace();
			}
		}

    	return liCountRegistros;
    }
    
    /**
     * Obtiene los registros de Admin Vigilancia
     * de acuerdo a la empresa y el id de la flex
     * @param tiIdEmpresa
     * @param tsIdFlex
     * @return
     */
    public String getRows_Adm_Vig(String tiIdEmpresa, int tsIdFlex){
    	/*
    	 * Redefinici�n de la regla para mostrar los funcionarios en Consulta/Reportes para incluir una validaci�n de Fusionada y/o liquidada.
    	 */
/*
    	return          "SELECT   META.* "
					    +"FROM     DERCORP_METATBL_TAB META "
					    +"WHERE    META.ID_EMPRESA  =   "+tiIdEmpresa+" "
					    +"AND      META.ID_FLEX_TBL =   "+tsIdFlex+" "
					    +"AND      META.VAL_C15 IS NOT NULL "
					    +"AND       META.VAL_C4 IS  NULL  "
					    +"AND META.VAL_C7 IS  NULL "
					    +"ORDER BY TO_NUMBER(META.VAL_C15), DERCORP_CATALOGS_PKG.GET_FUNCIONARIOS_FN(META.VAL_C1)";*/
    	
    	return "SELECT   META.*  "
    			+"FROM     DERCORP_METATBL_TAB META  "
    			+"WHERE    META.ID_EMPRESA  =   "+tiIdEmpresa+" "
    			+"AND      META.ID_FLEX_TBL =   "+tsIdFlex+" "
    			+"AND      META.VAL_C15 IS NOT NULL  "
    			//+"--AND       META.VAL_C4 IS  NULL  --baja propietario "
    			//+"--AND META.VAL_C7 IS  NULL --baja suplente "
    			/*+"and EXISTS (SELECT   *  "
    			+"FROM     DERCORP_METATBL_TAB META2  "
    			+"WHERE    META.ID_EMPRESA  =   "+tiIdEmpresa+" "
    			+"AND      META.ID_FLEX_TBL =   "+tsIdFlex+" "
    			+"AND       META.VAL_C4 IS  NULL "
    			+"OR META.VAL_C7 IS NULL  "
    			+"AND META2.ID_META_ROW = META.ID_META_ROW) "*/ //JJAQ 10-01-2019 Se comenta para el punto prioritario numero 16
    			+"ORDER BY TO_NUMBER(META.VAL_C15), DERCORP_CATALOGS_PKG.GET_FUNCIONARIOS_FN(META.VAL_C1)";
    }
    
    /**
     * Obtiene los registros de Admin Vigilancia
     * de acuerdo a la empresa y el id de la flex
     * @param tiIdEmpresa
     * @param tsIdFlex
     * @param nombresFuncionarios
     * @return
     */
    public String getRows_Adm_Vig(String tiIdEmpresa, int tsIdFlex,String nombresFuncionarios){
    	/*
    	 * Redefinici�n de la regla para mostrar los funcionarios en Consulta/Reportes para incluir una validaci�n de Fusionada y/o liquidada.
    	 */
/*
    	return          "SELECT   META.* "
					    +"FROM     DERCORP_METATBL_TAB META "
					    +"WHERE    META.ID_EMPRESA  =   "+tiIdEmpresa+" "
					    +"AND      META.ID_FLEX_TBL =   "+tsIdFlex+" "
					    +"AND      META.VAL_C15 IS NOT NULL "
					    +"AND       META.VAL_C4 IS  NULL  "
					    +"AND META.VAL_C7 IS  NULL "
					    +"ORDER BY TO_NUMBER(META.VAL_C15), DERCORP_CATALOGS_PKG.GET_FUNCIONARIOS_FN(META.VAL_C1)";*/
    	
    	return "SELECT   META.*  "
    			+"FROM     DERCORP_METATBL_TAB META  "
    			+"WHERE    META.ID_EMPRESA  =   "+tiIdEmpresa+" "
    			+"AND      META.ID_FLEX_TBL =   "+tsIdFlex+" "
    			+"AND      META.VAL_C15 IS NOT NULL  "
    			+"AND      META.VAL_C1 IN("+nombresFuncionarios+") "
    			//+"--AND       META.VAL_C4 IS  NULL  --baja propietario "
    			//+"--AND META.VAL_C7 IS  NULL --baja suplente "
    			/*+"and EXISTS (SELECT   *  "
    			+"FROM     DERCORP_METATBL_TAB META2  "
    			+"WHERE    META.ID_EMPRESA  =   "+tiIdEmpresa+" "
    			+"AND      META.ID_FLEX_TBL =   "+tsIdFlex+" "
    			+"AND       META.VAL_C4 IS  NULL "
    			+"OR META.VAL_C7 IS NULL  "
    			+"AND META2.ID_META_ROW = META.ID_META_ROW) "*/ //JJAQ 10-01-2019 Se comenta para el punto prioritario numero 16
    			+"ORDER BY TO_NUMBER(META.VAL_C15), DERCORP_CATALOGS_PKG.GET_FUNCIONARIOS_FN(META.VAL_C1)";
    }

	public String consultarECSSem(String tsIdEmpresa){

		String lsSQL;
		String lsValValor = null;

		try{
			puConnectionWrapper = new ConnectionWrapper();
			lsSQL = "SELECT VAL_VALOR "                    +
					"FROM   DERCORP_ADD_CAMPO_VALOR_TAB " +
					"WHERE  1=1 "                         +
					"AND    ID_ADD_CAMPO = 546 "          +
					"AND    ID_EMPRESA = "+tsIdEmpresa
            ;

			puConnection = puConnectionWrapper.getConnection();
			puPreparedStatement  =  puConnection.prepareStatement(lsSQL);
			puResultSet = puPreparedStatement.executeQuery();
			
			while(puResultSet.next()){
				lsValValor = puResultSet.getString(1);
			}


		}catch(SQLException sqlerr){
			sqlerr.getErrorCode();
			sqlerr.getMessage();
			sqlerr.getStackTrace();
			sqlerr.printStackTrace();

		}catch(Exception err){
			err.printStackTrace();

		}finally{
			try {
				puResultSet.close();
				puPreparedStatement.close();
				puConnection.close();
				puConnectionWrapper.close();
			} catch (SQLException sqlerr) {
				sqlerr.printStackTrace();
			}
		}

		return lsValValor;
		
	}
	/**
	 * @deprecated YA NO SE USA
	 * @param tsIdEmpresa
	 * @param tiIdFlex
	 * @param tsIdMetaRow
	 * @return
	 */
	public String conPodGenEsp(String tsIdEmpresa, int tiIdFlex, String tsIdMetaRow){

		String lsValValor = null;
		String lsSQL      = null;
		ResultSet puResultSet = null;
		Connection puConnection = null;
		PreparedStatement puPreparedStatement  =  null;

		try{
			lsSQL = "SELECT  VAL_C4 \n"				+
					"FROM    DERCORP_METATBL_TAB \n"	+
					"WHERE   1=1 \n"					+
					"AND     ID_META_ROW = ?\n"+
					"AND     ID_FLEX_TBL = ?\n"+
					"AND     ID_EMPRESA  = ?\n"
					;
			System.out.println(lsSQL);
			System.out.println(tsIdEmpresa);
			System.out.println(tiIdFlex);
			System.out.println(tsIdMetaRow);
			puConnectionWrapper = new ConnectionWrapper();
			puConnection = puConnectionWrapper.getConnection();
			puPreparedStatement  =  puConnection.prepareStatement(lsSQL);
			puPreparedStatement.setString(1, tsIdMetaRow);
			puPreparedStatement.setInt(2, tiIdFlex);
			puPreparedStatement.setString(3, tsIdEmpresa);
			
			puResultSet = puPreparedStatement.executeQuery();

			while(puResultSet.next()){
				lsValValor = puResultSet.getString(1);
			}

		}catch(SQLException sqlerr){
			sqlerr.getErrorCode();
			sqlerr.getMessage();
			sqlerr.getStackTrace();
			sqlerr.printStackTrace();

		}catch(Exception err){
			err.printStackTrace();

		}finally{
			try {
				puResultSet.close();
				puPreparedStatement.close();
				puConnection.close();
				puConnectionWrapper.close();
			} catch (SQLException sqlerr) {
				sqlerr.printStackTrace();
			}
		}

		return lsValValor;

	}

	/**
	 * Realiza Queries de Poder General
	 * @param tsIdEmpresa
	 * @param tiIdFlex
	 * @param tsIdMetaRow
	 * @return
	 */
	public String doQueriesPG(String tsIdEmpresa, int tiIdFlex, String tsIdMetaRow){

        String lsSqlCampos = null;

		lsSqlCampos = 			 "AND ID_FLEX_TBL = " + tiIdFlex+" "+
								 "AND DES_FLEX_COLUM IS NOT NULL "									 +
								 "AND COD_FLEX_COLUM <> 'VAL_C6' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C7' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C8' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C9' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C10' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C11' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C12' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C13' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C15' "

								 //"ORDER BY DECODE(DES_GROUP,'GROUP',1,2), ID_ORDEN, TO_NUMBER(REPLACE(COD_FLEX_COLUM,'VAL_C',''))"
								 ;
		
		return lsSqlCampos;
	}
	
	/**
	 * 
	 * @param tsIdEmpresa
	 * @param tiIdFlex
	 * @param tsIdMetaRow
	 * @return
	 */
	public String doQueriesPE(String tsIdEmpresa, int tiIdFlex, String tsIdMetaRow){

        String lsSqlCampos = null;

		lsSqlCampos = 			 "AND ID_FLEX_TBL = " + tiIdFlex+" "+
								 "AND DES_FLEX_COLUM IS NOT NULL "									 +
								 "AND COD_FLEX_COLUM <> 'VAL_C18' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C19' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C20' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C21' "
								 //"ORDER BY DECODE(DES_GROUP,'GROUP',1,2), ID_ORDEN, TO_NUMBER(REPLACE(COD_FLEX_COLUM,'VAL_C',''))"
								 ;
		
		return lsSqlCampos;
	}
	
	public String doQueriesPGPE(String tsIdEmpresa, int tiIdFlex, String tsIdMetaRow){

        String lsSqlCampos = null;

		lsSqlCampos =            "AND ID_FLEX_TBL = " + tiIdFlex+" "+
								 "AND DES_FLEX_COLUM IS NOT NULL "									 +
								 "AND COD_FLEX_COLUM <> 'VAL_C6' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C7' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C8' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C9' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C10' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C11' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C12' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C13' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C15' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C18' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C19' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C20' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C21' "

								 //"ORDER BY DECODE(DES_GROUP,'GROUP',1,2), ID_ORDEN, TO_NUMBER(REPLACE(COD_FLEX_COLUM,'VAL_C',''))"
								 ;
		
		return lsSqlCampos;
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String doQueriesRefPGPE(String tsIdEmpresa, int tiIdFlex, String tsIdMetaRow){

        String lsSqlCampos = null;

		lsSqlCampos = 			 "AND ID_FLEX_TBL = " + tiIdFlex+" "+
								 "AND DES_FLEX_COLUM IS NOT NULL "									 +
								 "AND COD_FLEX_COLUM <> 'VAL_C84' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C85' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C86' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C87' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C88' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C89' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C90' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C91' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C93' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C94' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C95' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C96' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C97' "

								 //"ORDER BY DECODE(DES_GROUP,'GROUP',1,2), ID_ORDEN, TO_NUMBER(REPLACE(COD_FLEX_COLUM,'VAL_C',''))"
								 ;
		
		return lsSqlCampos;
	}

	public String doQueriesRefProto(String tsIdEmpresa, int tiIdFlex, String tsIdMetaRow){

        String lsSqlCampos = null;

		lsSqlCampos = 			 "AND ID_FLEX_TBL = " + tiIdFlex+" "+
								 "AND DES_FLEX_COLUM IS NOT NULL "									 +
								 "AND COD_FLEX_COLUM <> 'VAL_C84' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C85' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C86' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C87' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C88' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C89' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C90' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C91' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C93' "

								 //"ORDER BY DECODE(DES_GROUP,'GROUP',1,2), ID_ORDEN, TO_NUMBER(REPLACE(COD_FLEX_COLUM,'VAL_C',''))"
								 ;
		
		return lsSqlCampos;
	}
	
	public String doQueriesRefIns(String tsIdEmpresa, int tiIdFlex, String tsIdMetaRow){

        String lsSqlCampos = null;

		lsSqlCampos = 			 "AND ID_FLEX_TBL = " + tiIdFlex+" "+
								 "AND DES_FLEX_COLUM IS NOT NULL "									 +
								 "AND COD_FLEX_COLUM <> 'VAL_C94' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C95' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C96' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C97' "
								 //"ORDER BY DECODE(DES_GROUP,'GROUP',1,2), ID_ORDEN, TO_NUMBER(REPLACE(COD_FLEX_COLUM,'VAL_C',''))"
								 ;
		
		return lsSqlCampos;
	}

////////////////////////////////////////////////////////////////////////////////////////////
	public String doQueriesAproProIns(String tsIdEmpresa, int tiIdFlex, String tsIdMetaRow){

        String lsSqlCampos = null;

		lsSqlCampos =			 "AND ID_FLEX_TBL = " + tiIdFlex+" "                                     +
								 "AND DES_FLEX_COLUM IS NOT NULL "									     +
								 "AND COD_FLEX_COLUM <> 'VAL_C104' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C105' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C106' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C107' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C108' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C109' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C110' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C111' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C113' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C114' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C115' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C116' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C117' "

								 //"ORDER BY DECODE(DES_GROUP,'GROUP',1,2), ID_ORDEN, TO_NUMBER(REPLACE(COD_FLEX_COLUM,'VAL_C',''))"
								 ;
		
		return lsSqlCampos;
	}

	public String doQueriesAproPro(String tsIdEmpresa, int tiIdFlex, String tsIdMetaRow){

        String lsSqlCampos = null;

		lsSqlCampos =            "AND ID_FLEX_TBL = " + tiIdFlex+" "                                     +
								 "AND DES_FLEX_COLUM IS NOT NULL "									     +
								 "AND COD_FLEX_COLUM <> 'VAL_C104' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C105' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C106' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C107' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C108' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C109' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C110' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C111' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C113' "

								 //"ORDER BY DECODE(DES_GROUP,'GROUP',1,2), ID_ORDEN, TO_NUMBER(REPLACE(COD_FLEX_COLUM,'VAL_C',''))"
								 ;

		return lsSqlCampos;
	}
	
	public String doQueriesAproIns(String tsIdEmpresa, int tiIdFlex, String tsIdMetaRow){

        String lsSqlCampos = null;

		lsSqlCampos =            "AND ID_FLEX_TBL = " + tiIdFlex+" "                                     +
								 "AND DES_FLEX_COLUM IS NOT NULL "									     +
								 "AND COD_FLEX_COLUM <> 'VAL_C114' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C115' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C116' "	                                 +
								 "AND COD_FLEX_COLUM <> 'VAL_C117' "
								 //"ORDER BY DECODE(DES_GROUP,'GROUP',1,2), ID_ORDEN, TO_NUMBER(REPLACE(COD_FLEX_COLUM,'VAL_C',''))"
								 ;
		
		return lsSqlCampos;
	}

	//ECM 12 Abril 2016 Consulta Status Poderes
	public String doQueriesStatPode(int tiIdFlex){

        String lsSqlCampos = null;

		lsSqlCampos = addQueryStatusPoderes();
		
		return lsSqlCampos;
	}

	public String doQueriesStatRTot(int tiIdFlex){

        String lsSqlCampos = null;

		lsSqlCampos = addQueryStatusRef_RTot();
		
		return lsSqlCampos;
	}

	public String doQueriesStatRPar(int tiIdFlex){

        String lsSqlCampos = null;

		lsSqlCampos = addQueryStatusRef_RPar();
		
		return lsSqlCampos;
	}

	public String doQueriesStatRTrans(int tiIdFlex){

        String lsSqlCampos = null;

		lsSqlCampos = addQueryStatusRef_RTrans();
		
		return lsSqlCampos;
	}

	public String doQueriesStatRAES(int tiIdFlex){

        String lsSqlCampos = null;

		lsSqlCampos = addQueryStatusRef_R_AES();

		return lsSqlCampos;
	}

	public String doQueriesStatEscOtros(int tiIdFlex){

        String lsSqlCampos = null;

		lsSqlCampos = addQueryStatusRef_R_EscOtros();

		return lsSqlCampos;
	}

	public String doQueriesStatActaOtros(int tiIdFlex){

        String lsSqlCampos = null;

		lsSqlCampos = addQueryStatusRef_R_ActaOtros();
		
		return lsSqlCampos;
	}

	public String doQueriesStatAumCap(int tiIdFlex){

        String lsSqlCampos = null;

		lsSqlCampos = addQueryStatusRef_R_AumCap();
		
		return lsSqlCampos;
	}

	public String doQueriesStatCont(int tiIdFlex){

        String lsSqlCampos = null;

		lsSqlCampos = addQueryStatusRef_R_Cont();
		
		return lsSqlCampos;
	}

	public String doQueriesStatDecDiv(int tiIdFlex){

        String lsSqlCampos = null;

		lsSqlCampos = addQueryStatusRef_R_DecDiv();
		
		return lsSqlCampos;
	}

	public String doQueriesStatDisCap(int tiIdFlex){

        String lsSqlCampos = null;

		lsSqlCampos = addQueryStatusRef_R_DisCap();
		
		return lsSqlCampos;
	}

	public String doQueriesStatEscision(int tiIdFlex){

        String lsSqlCampos = null;

		lsSqlCampos = addQueryStatusRef_R_Escision();

		return lsSqlCampos;
	}

	public String doQueriesStatFusion(int tiIdFlex){

        String lsSqlCampos = null;

		lsSqlCampos = addQueryStatusRef_R_Fusion();

		return lsSqlCampos;
	}

	public String doQueriesStatSesionCons(int tiIdFlex){

        String lsSqlCampos = null;

		lsSqlCampos = addQueryStatusRef_R_SesionCons();
		
		return lsSqlCampos;
	}
	
	public String addQueryStatusPoderes(){
		String lsAddQueryStatusPoderes;
		lsAddQueryStatusPoderes = "AND COD_FLEX_COLUM <> 'VAL_C56' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C57' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C58' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C59' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C60' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C61' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C62' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C63' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C64' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C65' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C66' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C67' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C68' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C69' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C70' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C71' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C72' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C73' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C74' "
								;

		return lsAddQueryStatusPoderes;

	}

	public String addQueryStatusRef_RTot(){
		 String lsAddQueryStatusRef_RTot;

		lsAddQueryStatusRef_RTot = "AND COD_FLEX_COLUM <> 'VAL_C37' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C38' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C39' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C40' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C47' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C48' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C49' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C41' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C42' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C43' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C50' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C51' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C52' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C44' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C45' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C46' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C53' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C54' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C55' "
                                  ;
		return lsAddQueryStatusRef_RTot;

	}
	
	public String addQueryStatusRef_RPar(){
		String lsAddQueryStatusRef_RPar;
		lsAddQueryStatusRef_RPar = "AND COD_FLEX_COLUM <> 'VAL_C28' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C29' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C30' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C31' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C32' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C33' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C34' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C35' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C36' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C37' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C38' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C39' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C40' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C41' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C42' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C43' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C44' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C45' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C46' "
                                  ;


		return lsAddQueryStatusRef_RPar;

	}
	
	public String addQueryStatusRef_RTrans(){
		String lsAddQueryStatusRTrans;
		lsAddQueryStatusRTrans = "AND COD_FLEX_COLUM <> 'VAL_C35' "        +
				                  "AND COD_FLEX_COLUM <> 'VAL_C36' "        +
				                  "AND COD_FLEX_COLUM <> 'VAL_C37' "        +
				                  "AND COD_FLEX_COLUM <> 'VAL_C38' "        +
				                  "AND COD_FLEX_COLUM <> 'VAL_C39' "        +
				                  "AND COD_FLEX_COLUM <> 'VAL_C40' "        +
				                  "AND COD_FLEX_COLUM <> 'VAL_C41' "        +
				                  "AND COD_FLEX_COLUM <> 'VAL_C42' "        +
				                  "AND COD_FLEX_COLUM <> 'VAL_C43' "        +
				                  "AND COD_FLEX_COLUM <> 'VAL_C44' "        +
				                  "AND COD_FLEX_COLUM <> 'VAL_C45' "        +
				                  "AND COD_FLEX_COLUM <> 'VAL_C46' "        +
				                  "AND COD_FLEX_COLUM <> 'VAL_C47' "        +
				                  "AND COD_FLEX_COLUM <> 'VAL_C48' "        +
				                  "AND COD_FLEX_COLUM <> 'VAL_C49' "        +
				                  "AND COD_FLEX_COLUM <> 'VAL_C50' "        +
				                  "AND COD_FLEX_COLUM <> 'VAL_C51' "        +
				                  "AND COD_FLEX_COLUM <> 'VAL_C52' "        +
				                  "AND COD_FLEX_COLUM <> 'VAL_C53' "
                                  ;


		return lsAddQueryStatusRTrans;

	}

	public String addQueryStatusRef_R_AES(){
		String lsAddQueryStatusAES;
		lsAddQueryStatusAES = "AND COD_FLEX_COLUM <> 'VAL_C67' "        +
				                  "AND COD_FLEX_COLUM <> 'VAL_C68' "        +
				                  "AND COD_FLEX_COLUM <> 'VAL_C69' "        +
				                  "AND COD_FLEX_COLUM <> 'VAL_C70' "        +
				                  "AND COD_FLEX_COLUM <> 'VAL_C71' "        +
				                  "AND COD_FLEX_COLUM <> 'VAL_C72' "        +
				                  "AND COD_FLEX_COLUM <> 'VAL_C73' "        +
				                  "AND COD_FLEX_COLUM <> 'VAL_C74' "        +
				                  "AND COD_FLEX_COLUM <> 'VAL_C75' "        +
				                  "AND COD_FLEX_COLUM <> 'VAL_C76' "        +
				                  "AND COD_FLEX_COLUM <> 'VAL_C77' "        +
				                  "AND COD_FLEX_COLUM <> 'VAL_C78' "        +
				                  "AND COD_FLEX_COLUM <> 'VAL_C79' "        +
				                  "AND COD_FLEX_COLUM <> 'VAL_C80' "        +
				                  "AND COD_FLEX_COLUM <> 'VAL_C81' "        +
				                  "AND COD_FLEX_COLUM <> 'VAL_C82' "        +
				                  "AND COD_FLEX_COLUM <> 'VAL_C83' "        +
				                  "AND COD_FLEX_COLUM <> 'VAL_C84' "        +
				                  "AND COD_FLEX_COLUM <> 'VAL_C85' "		+
				                  "AND COD_FLEX_COLUM <> 'VAL_C104' "        +
				                  "AND COD_FLEX_COLUM <> 'VAL_C105' "		
                                  ;


		return lsAddQueryStatusAES;

	}

	public String addQueryStatusRef_R_EscOtros(){
		String lsAddQueryStatusEscOtros;

		lsAddQueryStatusEscOtros = "AND COD_FLEX_COLUM <> 'VAL_C6' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C7' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C8' "

                                  ;


		return lsAddQueryStatusEscOtros;

	}

	public String addQueryStatusRef_R_ActaOtros(){
		String lsAddQueryStatusActaOtros;

		lsAddQueryStatusActaOtros = "AND COD_FLEX_COLUM <> 'VAL_C7' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C8' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C9' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C10' "       +
                                  "AND COD_FLEX_COLUM <> 'VAL_C11' "

                                  ;


		return lsAddQueryStatusActaOtros;

	}

	public String addQueryStatusRef_R_AumCap(){
		String lsAddQueryStatusAumCap;

		lsAddQueryStatusAumCap = "AND COD_FLEX_COLUM <> 'VAL_C19' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C20' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C21' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C22' "       +
                                  "AND COD_FLEX_COLUM <> 'VAL_C23' "

                                  ;

		return lsAddQueryStatusAumCap;

	}

	public String addQueryStatusRef_R_Cont(){
		String lsAddQueryStatusCont;

		lsAddQueryStatusCont =    "AND COD_FLEX_COLUM <> 'VAL_C15' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C16' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C17' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C18' "

                                  ;

		return lsAddQueryStatusCont;

	}

	public String addQueryStatusRef_R_DecDiv(){
		String lsAddQueryStatusDecDiv;

		lsAddQueryStatusDecDiv = "AND COD_FLEX_COLUM <> 'VAL_C9'  "         +
                                  "AND COD_FLEX_COLUM <> 'VAL_C10' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C11' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C12' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C13' "

                                  ;

		return lsAddQueryStatusDecDiv;

	}

	public String addQueryStatusRef_R_DisCap(){
		String lsAddQueryStatusDisCap;

		lsAddQueryStatusDisCap = "AND COD_FLEX_COLUM <> 'VAL_C19'  "         +
                                  "AND COD_FLEX_COLUM <> 'VAL_C20' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C21' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C22' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C23' "

                                  ;

		return lsAddQueryStatusDisCap;

	}

	public String addQueryStatusRef_R_Escision(){
		String lsAddQueryStatusEscision;

		lsAddQueryStatusEscision = "AND COD_FLEX_COLUM <> 'VAL_C21'  "       +
                                  "AND COD_FLEX_COLUM <> 'VAL_C22' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C23' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C24' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C25' "

                                  ;

		return lsAddQueryStatusEscision;

	}

	public String addQueryStatusRef_R_Fusion(){
		String lsAddQueryStatusFusion;

		lsAddQueryStatusFusion = "AND COD_FLEX_COLUM <> 'VAL_C23'  "       +
                                  "AND COD_FLEX_COLUM <> 'VAL_C24' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C25' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C26' "        +
                                  "AND COD_FLEX_COLUM <> 'VAL_C27' "
                                  ;

		return lsAddQueryStatusFusion;

	}

	//ECM 14 Junio 2016 Consulta - Fusi�n - Ocultar Aumento
	public String removeCamposFusionAumento(){
		String lsRemoveCamFusAum =  "AND COD_FLEX_COLUM <>  'VAL_C9'   " +
									"AND COD_FLEX_COLUM <> 	'VAL_C10'  " +
									"AND COD_FLEX_COLUM <> 	'VAL_C53'  " +
									"AND COD_FLEX_COLUM <> 	'VAL_C11'  " +
									"AND COD_FLEX_COLUM <> 	'VAL_C54'  " +
									"AND COD_FLEX_COLUM <> 	'VAL_C12'  " +
									"AND COD_FLEX_COLUM <> 	'VAL_C55'  " +
									"AND COD_FLEX_COLUM <> 	'VAL_C13'  " +
									"AND COD_FLEX_COLUM <> 	'VAL_C14'  " +
									"AND COD_FLEX_COLUM <> 	'VAL_C56'  " +
									"AND COD_FLEX_COLUM <> 	'VAL_C15'  " +
									"AND COD_FLEX_COLUM <> 	'VAL_C57'  " +
									"AND COD_FLEX_COLUM <> 	'VAL_C16'  " +
									"AND COD_FLEX_COLUM <> 	'VAL_C58'  " +
									"AND COD_FLEX_COLUM <> 	'VAL_C17'  " +
									"AND COD_FLEX_COLUM <> 	'VAL_C59'  "
									//"AND COD_FLEX_COLUM <> 	'VAL_C18'  " +
									//"AND COD_FLEX_COLUM <> 	'VAL_C19'  " +
									//"AND COD_FLEX_COLUM <> 	'VAL_C20'  " +
									//"AND COD_FLEX_COLUM <> 	'VAL_C21'  " +
									//"AND COD_FLEX_COLUM <> 	'VAL_C100' "
;

		return lsRemoveCamFusAum;
	}

	//ECM 14 Junio 2016 Consulta - Fusi�n - Ocultar Disminucion	
	public String removeCamposFusionDisminucion(){
		String lsRemoveCamFusDis =  "AND COD_FLEX_COLUM <> 	'VAL_C43' " +
									"AND COD_FLEX_COLUM <> 	'VAL_C44' " +
									"AND COD_FLEX_COLUM <> 	'VAL_C60' " +
									"AND COD_FLEX_COLUM <> 	'VAL_C45' " +
									"AND COD_FLEX_COLUM <> 	'VAL_C61' " +
									"AND COD_FLEX_COLUM <> 	'VAL_C46' " +
									"AND COD_FLEX_COLUM <> 	'VAL_C62' " +
									"AND COD_FLEX_COLUM <> 	'VAL_C47' " +
									"AND COD_FLEX_COLUM <> 	'VAL_C48' " +
									"AND COD_FLEX_COLUM <> 	'VAL_C63' " +
									"AND COD_FLEX_COLUM <> 	'VAL_C49' " +
									"AND COD_FLEX_COLUM <> 	'VAL_C64' " +
									"AND COD_FLEX_COLUM <> 	'VAL_C50' " +
									"AND COD_FLEX_COLUM <> 	'VAL_C65' " +
									"AND COD_FLEX_COLUM <> 	'VAL_C51' " +
									"AND COD_FLEX_COLUM <> 	'VAL_C66' " +
									"AND COD_FLEX_COLUM <> 	'VAL_C52' " +
									"AND COD_FLEX_COLUM <> 	'VAL_C67' "
									;
		return lsRemoveCamFusDis;
	}
/*	
	public String doQueryFusionAumento(int tiIdFlex, String tsIdEmp, String tsIdMetaRow){
		String lsValor = "";
		String lsSql = 	"SELECT  VAL_C69 "             +
						"FROM    DERCORP_METATBL_TAB " +
						"WHERE   1=1 "                 +
						"AND     ID_META_ROW = ?"+" "  +
						"AND     ID_FLEX_TBL = ?"+" "  +
						"AND     ID_EMPRESA =  ?"+" "
						;
		try{
			puConnectionWrapper = new ConnectionWrapper();
			puPreparedStatement = puConnectionWrapper.prepareStatement(lsSql);
			puPreparedStatement.setInt(1, Integer.parseInt(tsIdMetaRow) );
			puPreparedStatement.setInt(2, tiIdFlex );
			puPreparedStatement.setInt(3, Integer.parseInt(tsIdEmp) );
			puResultSet = puPreparedStatement.executeQuery();
			
			while(puResultSet.next()){
				lsValor = puResultSet.getString(1);
			}

		}catch(SQLException sqle){
			sqle.printStackTrace();
			sqle.getSQLState();
			lsValor = "No";

		}catch(Exception e){
			e.printStackTrace();
			e.getMessage();
			lsValor = "No";

		}
		if (lsValor !=  null && lsValor.equals("No"))
			return removeCamposFusionAumento();
		else
			return "";

	}

	public String doQueryFusionDisminucion(int tiIdFlex, String tsIdEmp, String tsIdMetaRow){
		String lsValor = "";
		String lsSql = 	"SELECT  VAL_C70 "             +
						"FROM    DERCORP_METATBL_TAB " +
						"WHERE   1=1 "                 +
						"AND     ID_META_ROW = ?"+" "  +
						"AND     ID_FLEX_TBL = ?"+" "  +
						"AND     ID_EMPRESA =  ?"+" "
						;
		try{
			puConnectionWrapper = new ConnectionWrapper();
			puPreparedStatement = puConnectionWrapper.prepareStatement(lsSql);
			puPreparedStatement.setInt(1, Integer.parseInt(tsIdMetaRow) );
			puPreparedStatement.setInt(2, tiIdFlex );
			puPreparedStatement.setInt(3, Integer.parseInt(tsIdEmp) );
			puResultSet = puPreparedStatement.executeQuery();

			while(puResultSet.next()){
				lsValor = puResultSet.getString(1);
			}

		}catch(SQLException sqle){
			sqle.printStackTrace();
			sqle.getSQLState();
			lsValor = "No";

		}catch(Exception e){
			e.printStackTrace();
			e.getMessage();
			lsValor = "No";

		}

		if (lsValor != null && lsValor.equals("No"))
			return removeCamposFusionDisminucion();
		else
			return "";

	}
*/

	public String addQueryStatusRef_R_SesionCons(){
		String lsAddQueryStatusSesionCons;

		lsAddQueryStatusSesionCons =  "AND COD_FLEX_COLUM <> 'VAL_C8'  "       +
	                                  "AND COD_FLEX_COLUM <> 'VAL_C9'  "       +
	                                  "AND COD_FLEX_COLUM <> 'VAL_C10' "       +
	                                  "AND COD_FLEX_COLUM <> 'VAL_C11' "       +
	                                  "AND COD_FLEX_COLUM <> 'VAL_C12' "
                                  ;

		return lsAddQueryStatusSesionCons;

	}
	
	public int getActCheck(String tIdEmpresa, int tiIdAddCampo){

		int liisCheck = 0;

		String lsSql = "SELECT  COUNT(1) "                    +
						"FROM    DERCORP_ADD_CAMPO_VALOR_TAB "+
                        "WHERE   1=1 "                        +
                        "AND     ID_EMPRESA = ? "             +
                        "AND     ID_ADD_CAMPO = ? ";

		try{
			puConnectionWrapper = new ConnectionWrapper();
			puPreparedStatement = puConnectionWrapper.prepareStatement(lsSql);
			puPreparedStatement.setInt(1, Integer.parseInt(tIdEmpresa) );
			puPreparedStatement.setInt(2, tiIdAddCampo );
			puResultSet = puPreparedStatement.executeQuery();
			
			while(puResultSet.next()){
				liisCheck = puResultSet.getInt(1);
			}

		}catch(SQLException sqle){
			sqle.printStackTrace();
			sqle.getSQLState();

		}catch(Exception e){
			e.printStackTrace();
			e.getMessage();
		}finally{
			try {
				puResultSet.close();
				puPreparedStatement.close();
				puConnectionWrapper.close();
			} catch (SQLException sqlerr) {
				sqlerr.printStackTrace();
			}
		}

		return liisCheck;

	}
	
	public String addQueryApoderados(int tiIdFlexTbl, String tIdEmpresa, String tIdMetaRow){

		int liisApo = 0;
		String lsSqlCampos = "";

		String lsAddQApo = 	"SELECT  COUNT(1) "				+
                           	"FROM    DERCORP_METATBL_TAB "	+
                           	"WHERE   1=1 "					+
							"AND     ID_FLEX_TBL = ? "		+
							"AND     ID_EMPRESA = ? "		+
							"AND     ID_META_ROW = ? "		+
							"AND     VAL_C1 = 12342"
							;

		try{
			puConnectionWrapper = new ConnectionWrapper();
			puPreparedStatement = puConnectionWrapper.prepareStatement(lsAddQApo);
			puPreparedStatement.setInt(1, tiIdFlexTbl );
			puPreparedStatement.setInt(2, Integer.parseInt(tIdEmpresa) );
			puPreparedStatement.setInt(3, Integer.parseInt(tIdMetaRow) );
			puResultSet = puPreparedStatement.executeQuery();
			
			while(puResultSet.next()){
				liisApo = puResultSet.getInt(1);
			}
			System.out.println("tIdMetaRow: "+tIdMetaRow);
			System.out.println("isApo: "+liisApo);
			if(liisApo == 0)
				lsSqlCampos = "AND COD_FLEX_COLUM <> 'VAL_C2' ";

		}catch(SQLException sqle){
			sqle.printStackTrace();
			sqle.getSQLState();

		}catch(Exception e){
			e.printStackTrace();
			e.getMessage();
		}
		return lsSqlCampos;

	}
	
	public int getStatusEscCons(String tsIdEmp, String tsIdCampo){
        int liStatus = 0;

        String lsSql =  "SELECT COUNT(1) "                    +
						"FROM   DERCORP_ADD_CAMPO_VALOR_TAB " +
						"WHERE  1=1 "                         +
						"AND    ID_ADD_CAMPO = ? "            +
						"AND    ID_EMPRESA = ? "
						;

		try{

			puConnectionWrapper = new ConnectionWrapper();
			puPreparedStatement = puConnectionWrapper.prepareStatement(lsSql);
			puPreparedStatement.setInt(1, Integer.parseInt(tsIdCampo) );
			puPreparedStatement.setInt(2, Integer.parseInt(tsIdEmp) );
			puResultSet = puPreparedStatement.executeQuery();
			
			while(puResultSet.next())
				liStatus = puResultSet.getInt(1);

		}catch(SQLException sqle){
			sqle.printStackTrace();
			sqle.getSQLState();

		}catch(Exception e){
			e.printStackTrace();
			e.getMessage();
		}finally{
			if(puConnectionWrapper != null)
				puConnectionWrapper.close();
		}
		return liStatus;

	}

	public int valNominalValTeorico(int tiIdEmp, int tiIdCampo){
		int liValor = 0;

		try {
			puConnectionWrapper = new ConnectionWrapper();
			puCallableStatement = puConnectionWrapper.prepareCall(DERCORP_CONSULTA_PKG.GET_VAL_NOM_TEO_PR);
			puCallableStatement.registerOutParameter(1, OracleTypes.NUMBER);
			puCallableStatement.setInt(2, tiIdCampo);
			puCallableStatement.setInt(3, tiIdEmp);
			puCallableStatement.execute();

			liValor = ((OracleCallableStatement) puCallableStatement).getInt(1);

		} catch (Exception e) {
			e.printStackTrace();

		} finally{
			if(puConnectionWrapper != null)
                puConnectionWrapper.close();
		}

		return liValor;

	}

	public String getSocioExterno(int tiIdEmpresa){

		String lsValor = "";

		try {
			puConnectionWrapper = new ConnectionWrapper();
			puCallableStatement = puConnectionWrapper.prepareCall(DERCORP_CONSULTA_PKG.GET_SOCIO_EXTERNO_PR);
			puCallableStatement.registerOutParameter(1, OracleTypes.VARCHAR);
			puCallableStatement.setInt(2, tiIdEmpresa);
			puCallableStatement.execute();

			lsValor = ((OracleCallableStatement) puCallableStatement).getString(1);

		} catch (Exception e) {
			e.printStackTrace();

		}finally{
			if(puConnectionWrapper != null)
				puConnectionWrapper.close();
		}

		return lsValor;
	}

	public String getAdmiteExtranjeros(int tiIdEmpresa){
		String lsValor = "";

		try {
			puConnectionWrapper = new ConnectionWrapper();
			puCallableStatement = puConnectionWrapper.prepareCall(DERCORP_CONSULTA_PKG.GET_ADMITE_EXTRANJEROS_PR);
			puCallableStatement.registerOutParameter(1, OracleTypes.VARCHAR);
			puCallableStatement.setInt(2, tiIdEmpresa);
			puCallableStatement.execute();

			lsValor = ((OracleCallableStatement) puCallableStatement).getString(1);

		} catch (Exception e) {
			e.printStackTrace();

		}finally{
			if(puConnectionWrapper != null)
				puConnectionWrapper.close();
		}

		return lsValor;

	}

}