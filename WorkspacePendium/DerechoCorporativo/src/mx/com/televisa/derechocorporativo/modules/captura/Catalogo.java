/**
* Project: Derecho Corporativo
*
* File: Catalogo.java
*
* Created on: Agosto 31, 2015 at 12:00
*
* Copyright (c) - Televisa - 2015
*/

package mx.com.televisa.derechocorporativo.modules.captura;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;
import org.apache.log4j.Logger;
import org.antlr.stringtemplate.*;


/**
 * Obtener información para los catalogos en los combos.
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
 * @date Agosto 31, 2015 at 12:00 pm
 *
 */
public class Catalogo {

	int idCatalogo;
	
	final static Logger log = Logger.getLogger(Catalogo.class);

	/**
	 * Recibe un parámetro y lo asigna a un atributo de clase.
	 * 
	 * @param catalogo_id
	 */
	public Catalogo(int catalogo_id) {
		
		this.idCatalogo = catalogo_id;
	}

	/**
	 * Devuelve un arreglo de elementos.
	 * 
	 * @param tuConnect
	 * @return ArrayList<CatalogoValor>
	 */
	public ArrayList<CatalogoValor> getElementos(ConnectionWrapper tuConnect) {
		ArrayList<CatalogoValor> laCatalogoValor     = new ArrayList<CatalogoValor>();
		ResultSet                luResultSet         = null;
		PreparedStatement        luPreparedStatement = null;
		String                   lsSqlCatalogoValor  = "";

		try {
			switch(this.idCatalogo){
			case 9:
				lsSqlCatalogoValor = "SELECT * "
				           + "FROM DERCORP_ADD_CAMPO_CAT_VAL_TAB "
				           + "WHERE 1=1 "
				           + "AND ID_CATALOGO = #CATALOGO_ID# "
				           + "ORDER BY NOM_CAT_VAL ,UPPER(APP_COMMON_PKG.SIN_ACENTOS_FN(VAL_CAT_VAL))" 
				           ;
				break;
			
			default:
				lsSqlCatalogoValor = "SELECT * "
				           + "FROM DERCORP_ADD_CAMPO_CAT_VAL_TAB "
				           + "WHERE 1=1 "
				           + "AND ID_CATALOGO = #CATALOGO_ID# "
				           + "ORDER BY UPPER(APP_COMMON_PKG.SIN_ACENTOS_FN(VAL_CAT_VAL))" 
				           ;
				break;
			}
			
			/*lsSqlCatalogoValor = "SELECT * "
					           + "FROM DERCORP_ADD_CAMPO_CAT_VAL_TAB "
					           + "WHERE 1=1 "
					           + "AND ID_CATALOGO = #CATALOGO_ID# "
					           + "ORDER BY UPPER(APP_COMMON_PKG.SIN_ACENTOS_FN(VAL_CAT_VAL))" 
					           ;*/
			lsSqlCatalogoValor = lsSqlCatalogoValor.replaceAll("#CATALOGO_ID#", "" + this.idCatalogo);
			
			luPreparedStatement= tuConnect.prepareStatement(lsSqlCatalogoValor);
			luResultSet = luPreparedStatement.executeQuery();

			while (luResultSet.next()) {
				laCatalogoValor.add(new CatalogoValor(luResultSet, luResultSet.getMetaData()));
			}
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try{
				luPreparedStatement.close();
				luResultSet.close();
			} catch (Exception ex){ 
				ex.printStackTrace();
			}
		}

		return laCatalogoValor;
	}
	public ArrayList<CatalogoValor> getElementosByCatalogoValor(ConnectionWrapper tuConnect, int idCatalogoValor) {
		ArrayList<CatalogoValor> laCatalogoValor     = new ArrayList<CatalogoValor>();
		ResultSet                luResultSet         = null;
		PreparedStatement        luPreparedStatement = null;
		String                   lsSqlCatalogoValor  = "";
		
		try {
			
				lsSqlCatalogoValor = "SELECT * "
				           + "FROM DERCORP_ADD_CAMPO_CAT_VAL_TAB "
				           + "WHERE 1=1 "
				           + "AND ID_CATALOGO = $idCatalogo$ "
				           + "AND ID_CATALOGO_VALOR = $idCatalogoValor$ "
				           + "ORDER BY UPPER(APP_COMMON_PKG.SIN_ACENTOS_FN(VAL_CAT_VAL))" 
				           ;
				
				StringTemplate query = new StringTemplate(lsSqlCatalogoValor);
				query.setAttribute("idCatalogo",this.idCatalogo);
				query.setAttribute("idCatalogoValor",idCatalogoValor);
			
			//lsSqlCatalogoValor = lsSqlCatalogoValor.replaceAll("#CATALOGO_ID#", "" + this.idCatalogo);
			
			luPreparedStatement= tuConnect.prepareStatement(query.toString());
			luResultSet = luPreparedStatement.executeQuery();

			while (luResultSet.next()) {
				laCatalogoValor.add(new CatalogoValor(luResultSet, luResultSet.getMetaData()));
			}
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try{
				luPreparedStatement.close();
				luResultSet.close();
			} catch (Exception ex){ 
				ex.printStackTrace();
			}
		}

		return laCatalogoValor;
	}
	/**
	 * Devuelve un arreglo de elementos Sin Reflexion.
	 * 
	 * @param tuConnect
	 * @return ArrayList<CatalogoValor>
	 */
	public ArrayList<CatalogoValor> getElementosSinReflexion(ConnectionWrapper tuConnect) {
		ArrayList<CatalogoValor> laCatalogoValor     = new ArrayList<CatalogoValor>();
		ResultSet                luResultSet         = null;
		PreparedStatement        luPreparedStatement = null;
		String                   lsSqlCatalogoValor  = "";

		try {
			switch(this.idCatalogo){
			case 9:
				lsSqlCatalogoValor = "SELECT * "
				           + "FROM DERCORP_ADD_CAMPO_CAT_VAL_TAB "
				           + "WHERE 1=1 "
				           + "AND ID_CATALOGO = #CATALOGO_ID# "
				           + "ORDER BY NOM_CAT_VAL ,UPPER(APP_COMMON_PKG.SIN_ACENTOS_FN(VAL_CAT_VAL))" 
				           ;
				break;
			default:
				lsSqlCatalogoValor = "SELECT * "
				           + "FROM DERCORP_ADD_CAMPO_CAT_VAL_TAB "
				           + "WHERE 1=1 "
				           + "AND ID_CATALOGO = #CATALOGO_ID# "
				           + "ORDER BY UPPER(APP_COMMON_PKG.SIN_ACENTOS_FN(VAL_CAT_VAL))" 
				           ;
				break;
			}
			/*lsSqlCatalogoValor = "SELECT * "
					           + "FROM DERCORP_ADD_CAMPO_CAT_VAL_TAB "
					           + "WHERE 1=1 "
					           + "AND ID_CATALOGO = #CATALOGO_ID# "
					           + "ORDER BY UPPER(APP_COMMON_PKG.SIN_ACENTOS_FN(VAL_CAT_VAL))" 
					           ;*/
			lsSqlCatalogoValor = lsSqlCatalogoValor.replaceAll("#CATALOGO_ID#", "" + this.idCatalogo);
			
			luPreparedStatement= tuConnect.prepareStatement(lsSqlCatalogoValor);
			luResultSet = luPreparedStatement.executeQuery();

			while (luResultSet.next()) {
				//laCatalogoValor.add(new CatalogoValor(luResultSet, luResultSet.getMetaData()));
				laCatalogoValor.add(new CatalogoValor(luResultSet.getInt("ID_CATALOGO_VALOR"), luResultSet.getString("VAL_CAT_VAL")));
				
			}
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try{
				luPreparedStatement.close();
				luResultSet.close();
			} catch (Exception ex){ 
				ex.printStackTrace();
			}
		}

		return laCatalogoValor;
	}
	
	/**
	 * Devuelve un arreglo de elementos para Admin Vigilancia.
	 * 
	 * @param tuConnect
	 * @return ArrayList<CatalogoValor>
	 */
	public ArrayList<CatalogoValor> getElementosAdminVig(ConnectionWrapper tuConnect
                                                         ,int tIdCatVal) {
		ArrayList<CatalogoValor> laCatalogoValor     = new ArrayList<CatalogoValor>();
		ResultSet                luResultSet         = null;
		PreparedStatement        luPreparedStatement = null;
		String                   lsSqlCatalogoValor  = "";

		try {
			lsSqlCatalogoValor = "SELECT * "
					           + "FROM DERCORP_ADD_CAMPO_CAT_VAL_TAB "
					           + "WHERE 1=1 "
					           //+ "AND ID_CATALOGO = ?"
					           + "AND ID_CATALOGO_VALOR = ?"
					           + "ORDER BY UPPER(APP_COMMON_PKG.SIN_ACENTOS_FN(VAL_CAT_VAL))" 
					           ;
			//lsSqlCatalogoValor = lsSqlCatalogoValor.replaceAll("#CATALOGO_ID#", "" + this.idCatalogo);
			
			luPreparedStatement= tuConnect.prepareStatement(lsSqlCatalogoValor);
			//luPreparedStatement.setInt(1, this.idCatalogo);
			luPreparedStatement.setInt(1, tIdCatVal);
			luResultSet = luPreparedStatement.executeQuery();

			while (luResultSet.next()) {
				laCatalogoValor.add(new CatalogoValor(luResultSet, luResultSet.getMetaData()));
			}
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try{
				luPreparedStatement.close();
				luResultSet.close();
			} catch (Exception ex){ 
				ex.printStackTrace();
			}
		}

		return laCatalogoValor;
	}
	
	//ECM 27 ENERO 2016 Agregar catálogo manual para la pestaña Contratos, campo nombres en Captura.
	public ArrayList<CatalogoValor> getElementosPersonalizados(ConnectionWrapper tuConnect) {
		ArrayList<CatalogoValor> laCatalogoValor     = new ArrayList<CatalogoValor>();
		ResultSet                luResultSet         = null;
		PreparedStatement        luPreparedStatement = null;
		String                   lsSqlCatalogoValor  = "";

		try {
			lsSqlCatalogoValor =    "SELECT  PT.PERSON_ID AS idCatalogoValor"+
						                    ",PT.NOMBRE AS valCatVal "+
						            "FROM    DERCORP_CAT_PERSONAS_TOTAL_TAB PT "+
						            "WHERE   1=1 "+
						            "ORDER BY APP_COMMON_PKG.SIN_ACENTOS_FN(PT.PERSON_ID)"
									;

			luPreparedStatement= tuConnect.prepareStatement(lsSqlCatalogoValor);
			luResultSet = luPreparedStatement.executeQuery();

			while (luResultSet.next()) {
				laCatalogoValor.add(new CatalogoValor(luResultSet, luResultSet.getMetaData()));
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try{
				luPreparedStatement.close();
				luResultSet.close();
			} catch (Exception ex){ 
				ex.printStackTrace();
			}
		}

		return laCatalogoValor;
	}

	//ECM 29 ENERO 2016 Agregar catálogo manual para la pestaña Poderes Generales y todas las del semáforo.
	public ArrayList<CatalogoValor> getEdosMex(ConnectionWrapper tuConnect) {
		ArrayList<CatalogoValor> laCatalogoValor     = new ArrayList<CatalogoValor>();
		ResultSet                luResultSet         = null;
		PreparedStatement        luPreparedStatement = null;
		String                   lsSqlCatalogoValor  = "";

		try {
			lsSqlCatalogoValor =    "SELECT  ID_CATALOGO_VALOR,"
									        +"ID_CATALOGO,"
									        +"COD_CAT_VAL,"
									        +"NOM_CAT_VAL,"
									        +"VAL_CAT_VAL,"
									        +"DES_CAT_VAL "
									+"FROM    DERCORP_ADD_CAMPO_CAT_VAL_TAB "
									+"WHERE   1=1 "
									+"AND     ID_CATALOGO = 13 "
									+"AND     APP_COMMON_PKG.SIN_ACENTOS_NI_NN_FN(UPPER(DES_CAT_VAL)) = APP_COMMON_PKG.SIN_ACENTOS_NI_NN_FN(UPPER('mexico')) "
									+"ORDER BY TO_NUMBER(COD_CAT_VAL)"
									;

			luPreparedStatement= tuConnect.prepareStatement(lsSqlCatalogoValor);
			luResultSet = luPreparedStatement.executeQuery();

			while (luResultSet.next()) {
				laCatalogoValor.add(new CatalogoValor(luResultSet, luResultSet.getMetaData()));
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try{
				luPreparedStatement.close();
				luResultSet.close();
			} catch (Exception ex){ 
				ex.printStackTrace();
			}
		}

		return laCatalogoValor;
	}

}
