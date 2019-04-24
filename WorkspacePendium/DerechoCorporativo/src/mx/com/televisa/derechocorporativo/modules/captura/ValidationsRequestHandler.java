/*
* Project: Derecho Corporativo
*
* File: ValidationsRequestHandler.java
*
* Created on: Agosto 31, 2015 at 12:00
*
* Copyright (c) - Televisa - 2015
*/

package mx.com.televisa.derechocorporativo.modules.captura;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletRequest;
import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;

public class ValidationsRequestHandler {

	public static String handleRequestNotario(ServletRequest tuServletRequest) {
        String            lsLic               = tuServletRequest.getParameter("lic");
        String            lsNot               = tuServletRequest.getParameter("not");
        String            lsDe                = "";
        String            lsSqlQuery          = "";
        ConnectionWrapper luConnectionWrapper = null;
        ResultSet         luResultSet         = null;
        PreparedStatement luPreparedStatement = null;

		try {
			luConnectionWrapper = new ConnectionWrapper();
			lsSqlQuery = "SELECT CV.ID_CATALOGO_VALOR AS ID_LICENCIADO, " +
				       				"CV.VAL_CAT_VAL AS NOM_LICENCIADO, " +
				       				"CV.ATRIBUTO1 AS NOTARIO, " +
				       				"(SELECT ID_CATALOGO_VALOR " + 
				       				"FROM DERCORP_ADD_CAMPO_CAT_VAL_TAB " +
				       				"WHERE 1=1" +
				       				"AND VAL_CAT_VAL = CV.ATRIBUTO2 " +
				       				"AND ID_CATALOGO = 13 " +
				       				") AS ID_ENTIDAD " +
				       		 "FROM DERCORP_ADD_CAMPO_CAT_VAL_TAB CV " +
				       		 "WHERE ID_CATALOGO        = 12 ";
		   if (!lsLic.equals("null")) {
			   lsSqlQuery = lsSqlQuery + "AND CV.ID_CATALOGO_VALOR = NVL(#ID_LIC#,CV.ID_CATALOGO_VALOR) ";
			   lsSqlQuery = lsSqlQuery.replaceAll("#ID_LIC#", 
					   String.valueOf(lsLic));
		   }
		   else if (!lsNot.equals("null")){
			   lsSqlQuery = lsSqlQuery + "AND CV.ATRIBUTO1         = NVL(#ID_NOT#,CV.ATRIBUTO1) ";
			   lsSqlQuery = lsSqlQuery.replaceAll("#ID_NOT#", 
					   String.valueOf(lsNot));
		   }

		   //System.out.println(lsSqlQuery);
		   luPreparedStatement= luConnectionWrapper.prepareStatement(lsSqlQuery);
		   luResultSet = luPreparedStatement.executeQuery();
				
		   while (luResultSet.next()) {
				lsLic = luResultSet.getString(1);
				lsNot = luResultSet.getString(3);
				lsDe  = luResultSet.getString(4);
		   }

		   return lsLic+"|"+lsNot+"|"+lsDe;

		} catch(Exception ex) {			
			ex.printStackTrace();
			
			return ex.toString();
			
		} finally {
			ConnectionWrapper.closeAll(luConnectionWrapper);
		} 
	}

	public static String handleRequestContacto(ServletRequest tuServletRequest){
        String            lsValC1             = "";
        String            lsValC2             = "";
        String            lsValC3             = "";
        String            lsValC4             = "";
        String            lsValC5             = "";
        String            lsNom               = tuServletRequest.getParameter("Nom");
        ConnectionWrapper luConnectionWrapper = null;
        ResultSet         luResultSet         = null;
        PreparedStatement tuPreparedStatement = null;
        String            lsSqlQuery          = "";

		try{
            luConnectionWrapper = new ConnectionWrapper();
            lsSqlQuery =
            		"SELECT   CV1.ID_CATALOGO_VALOR AS VAL_C1 "+
                              ",CV1.DES_CAT_VAL AS VAL_C2 "+
            	              ",CV1.ATRIBUTO1 AS VAL_C3 "+
            		          ",CV1.ATRIBUTO2 AS VAL_C4 "+
            		          ",CV1.ATRIBUTO3 AS VAL_C5 "+
                    "FROM     DERCORP_ADD_CAMPO_CAT_VAL_TAB CV1 "+
                    "WHERE   1=1 "+
                    "AND     CV1.ID_CATALOGO = 56 "+
                    "AND     CV1.ID_CATALOGO_VALOR = "+lsNom
                    ;

			   //System.out.println(sqlQuery);
               System.out.println("nom:  "+lsNom);
			   tuPreparedStatement= luConnectionWrapper.prepareStatement(lsSqlQuery);
			   luResultSet = tuPreparedStatement.executeQuery();

			   while (luResultSet.next()) {
                   lsValC1 = luResultSet.getString(1);
                   lsValC2 = luResultSet.getString(2);
                   lsValC3 = luResultSet.getString(3);
                   lsValC4 = luResultSet.getString(4);
                   lsValC5 = luResultSet.getString(5);
			   }
			   System.out.println(lsValC1+"|"+lsValC2+"|"+lsValC3+"|"+lsValC4+"|"+lsValC5);
			   return lsValC1+"|"+lsValC2+"|"+lsValC3+"|"+lsValC4+"|"+lsValC5;	
		} catch(Exception ex) {
			ex.printStackTrace();
			
			return ex.toString();
			
		} finally {
			ConnectionWrapper.closeAll(luConnectionWrapper);
		}
	}
	
	public static String handleRequestLic(ServletRequest tuServletRequest){
        String            lsIdCatVal          = tuServletRequest.getParameter("IdCatVal");
        String            lsValC5             = "";
        String            lsValC6             = "";
        String            lsSqlQuery          = "";
        ConnectionWrapper luConnectionWrapper = null;
        ResultSet         luResultSet         = null;
        PreparedStatement luPreparedStatement = null;

		try{
			luConnectionWrapper = new ConnectionWrapper();
			lsSqlQuery = 	"SELECT  C_V.ATRIBUTO1 "+
								"        ,(SELECT  ID_CATALOGO_VALOR "+
											"FROM    DERCORP_ADD_CAMPO_CAT_VAL_TAB "+
											"WHERE   1=1 "+
											"AND     ID_CATALOGO = 13 "+
											"AND     NOM_CAT_VAL = C_V.ATRIBUTO2 "+
										") AS COD_CAT_VAL "+
								"FROM    DERCORP_ADD_CAMPO_CAT_VAL_TAB C_V "+
								"WHERE   1=1 "+
								"AND     ID_CATALOGO = 12 "+
								"AND     ID_CATALOGO_VALOR = "+lsIdCatVal
			;

			//System.out.println(lsSqlQuery);
			luPreparedStatement= luConnectionWrapper.prepareStatement(lsSqlQuery);
			luResultSet = luPreparedStatement.executeQuery();
			
		   while (luResultSet.next()) {
			   lsValC5 = luResultSet.getString(1);
			   lsValC6 = luResultSet.getString(2);
		   }
		   //System.out.println(lsValC5+"|"+lsValC6);
		   return lsValC5+"|"+lsValC6;	

		} catch(Exception ex) {
			ex.printStackTrace();

			return ex.toString();

		} finally {
			ConnectionWrapper.closeAll(luConnectionWrapper);
		}

	}

	public static String handleRequestInfoNotario(ServletRequest tuServletRequest){
        String            lsNotPub            = tuServletRequest.getParameter("NotPub");
        String            lsValC4             = "";
        String            lsValC6             = "";
        String            sqlQuery            = "";
        ConnectionWrapper luConnectionWrapper = null;
        ResultSet         luResultSet         = null;
        PreparedStatement luPreparedStatement = null;

		try{
			luConnectionWrapper = new ConnectionWrapper();
			sqlQuery = "SELECT  C_V.ID_CATALOGO_VALOR "+
										",(SELECT  ID_CATALOGO_VALOR "+
										"FROM    DERCORP_ADD_CAMPO_CAT_VAL_TAB "+
										"WHERE   1=1 "+
										"AND     ID_CATALOGO = 13 "+
										"AND     NOM_CAT_VAL = C_V.ATRIBUTO2 "+
										") AS COD_CAT_VAL "+
										"FROM    DERCORP_ADD_CAMPO_CAT_VAL_TAB C_V "+
										"WHERE   1=1 "+
										"AND     ID_CATALOGO = 12 "+
										"AND     C_V.ATRIBUTO1 = "+lsNotPub
			;

			//System.out.println(sqlQuery);
			luPreparedStatement= luConnectionWrapper.prepareStatement(sqlQuery);
			luResultSet = luPreparedStatement.executeQuery();

		   while (luResultSet.next()) {
			   lsValC4 = luResultSet.getString(1);
			   lsValC6 = luResultSet.getString(2);
		   }
		   //System.out.println(lsValC4+"|"+lsValC6);
		   return lsValC4+"|"+lsValC6;

		} catch(Exception ex) {
			ex.printStackTrace();

			return ex.toString();

		} finally {
			ConnectionWrapper.closeAll(luConnectionWrapper);
		}
	}
}