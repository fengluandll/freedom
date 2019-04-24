/**
* Project: Derecho Corporativo
*
* File: ConstanciaActasDAO.java
*
* Created on: Julio 31, 2016 at 12:00
*
* Copyright (c) - Televisa - 2015
*/
package mx.com.televisa.derechocorporativo.daos;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;
import mx.com.televisa.derechocorporativo.data.packages.DERCORP_CONSULTA_PKG;
import mx.com.televisa.derechocorporativo.bean.ConstanciaActasBean;

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
public class ConstanciaActasDAO {
	
	 private PreparedStatement puPreparedStatement;
	 private ResultSet         puResultSet;
	 private ConnectionWrapper puConnectionWrapper;

	 /**
	  * Metodo Constructor
	  */
	public ConstanciaActasDAO(){
		 puPreparedStatement = null;
		 puResultSet = null;
		 puConnectionWrapper = null;

	}

	/**
	 * Obtiene el tipo de sociedad
	 * @param idEmpresa
	 * @return String
	 */
	public String tipoSociedad(int idEmpresa){
		String lstSql = "SELECT cv.val_valor \n"+
						" FROM DERCORP_ADD_CAMPO_VALOR_TAB cv \n"+
						" WHERE id_empresa = ? \n"+
						" AND ID_ADD_CAMPO = 517";
		String lsTipoSociedad = null;
		try {
			puConnectionWrapper = new ConnectionWrapper();
			puPreparedStatement = puConnectionWrapper.prepareStatement(lstSql);
			puPreparedStatement.setInt(1, idEmpresa);
			puResultSet = puPreparedStatement.executeQuery();
			while(puResultSet.next()){
				lsTipoSociedad = puResultSet.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				puConnectionWrapper.close();
				puResultSet.close();
				puPreparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return lsTipoSociedad;
	}
	
	/**
	 * Obtiene Auditores Externos
	 * @param tIdEmpresa
	 * @return String
	 */
	public String getAuditoresExternos(String tIdEmpresa){

		String lsSql;
		StringBuilder luReturn = new StringBuilder(); 
		luReturn.append("");

		lsSql = "SELECT  VAL_CAT_VAL "+
				"FROM    DERCORP_ADD_CAMPO_CAT_VAL_TAB "+
				"WHERE   1=1 "+
				"AND     ID_CATALOGO_VALOR = ( "+
				                              "SELECT  VAL_VALOR "+
				                              "FROM    DERCORP_ADD_CAMPO_VALOR_TAB "+
				                              "WHERE   1=1 "+
				                              "AND     ID_ADD_CAMPO = 1052 "+
				                              "AND     ID_EMPRESA = ?)";

		try{
			puConnectionWrapper = new ConnectionWrapper();
			puPreparedStatement = puConnectionWrapper.prepareStatement(lsSql);
			puPreparedStatement.setInt(1, Integer.parseInt(tIdEmpresa));
			puResultSet = puPreparedStatement.executeQuery();

			while(puResultSet.next()){
				luReturn.append(puResultSet.getString("VAL_CAT_VAL"));
			}

		}catch(SQLException sqle){
			sqle.printStackTrace();
			sqle.getSQLState();

		}catch(Exception e){
			e.printStackTrace();
			e.getMessage();
		}finally{
			try {
				puPreparedStatement.close();
				puResultSet.close();
				puConnectionWrapper.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return luReturn.toString();

	}
	
	/**
	 * Obtiene Asambleas
	 * @param tIdEmpresa
	 * @param anio
	 * @return Arreglo de ConstanciaActasBean
	 */
	public ArrayList<ConstanciaActasBean> getAsambleas(String tIdEmpresa,String anio){

		String lsSql;
		ConstanciaActasBean luConstanciaActasBean;
		ArrayList<ConstanciaActasBean> alConstanciaActasBean = null;
		CallableStatement puCallableStatement = null;
		
		try{
			puConnectionWrapper = new ConnectionWrapper();
			
			lsSql = "SELECT  CVAL.VAL_CAT_VAL, MTBL.VAL_C3 "                                    +
					"FROM    DERCORP_METATBL_TAB           MTBL, "								+
					        "DERCORP_ADD_CAMPO_CAT_VAL_TAB CVAL "								+
					"WHERE   1=1 "																+
					"AND     MTBL.ID_FLEX_TBL IN (20, 21, 22, 23, 28,29, 31, 32, 33, 34, 35, 41) "	+
					//AND     MTBL.ID_FLEX_TBL IN (27)
					"AND     MTBL.ID_EMPRESA = ? "												+
					"AND     MTBL.VAL_C2 = CVAL.ID_CATALOGO_VALOR "								+
					"AND  SUBSTR(MTBL.VAL_C3,INSTR(MTBL.VAL_C3,'/',1,2)+1,10) = ?"              +
					"ORDER BY APP_COMMON_PKG.GET_AS_DATE_FOR_ORDER(MTBL.VAL_C3)  DESC"
	                ;
			puPreparedStatement = puConnectionWrapper.prepareStatement(lsSql);
			puPreparedStatement.setInt(1, Integer.parseInt(tIdEmpresa));
			puPreparedStatement.setString(2, anio);
			puResultSet = puPreparedStatement.executeQuery();

			alConstanciaActasBean = new ArrayList<ConstanciaActasBean>();

			while(puResultSet.next()){
				luConstanciaActasBean = new ConstanciaActasBean();
				luConstanciaActasBean.setPsAsamblea(puResultSet.getString("VAL_CAT_VAL"));
				luConstanciaActasBean.setPsFecha(puResultSet.getString("VAL_C3"));

				alConstanciaActasBean.add(luConstanciaActasBean);
			}
			 
			//ACTAS DE PODERES			
			puCallableStatement = puConnectionWrapper.prepareCall("PENDIUM_REPORTES_PODERES_PKG.QUERY_ESC_POR_EMP_ANIO_PR",3);
			puCallableStatement.setInt(1, Integer.parseInt(tIdEmpresa));
			puCallableStatement.setInt(2, Integer.parseInt(anio));
			puCallableStatement.registerOutParameter(3, OracleTypes.CURSOR);
			puCallableStatement.execute();
			puResultSet =((OracleCallableStatement) puCallableStatement).getCursor(3);			
						
			while(puResultSet.next()){
				luConstanciaActasBean = new ConstanciaActasBean();
				luConstanciaActasBean.setPsTipoEscritura(puResultSet.getString("IND_TIPO_ESCRITURA").equals("PG")?"Poder General":puResultSet.getString("IND_TIPO_ESCRITURA").equals("PE")?"Poder Especial":"Revocaci�n");
				luConstanciaActasBean.setPsFecha(puResultSet.getString("FEC_FECHA"));
				luConstanciaActasBean.setPsAsamblea(puResultSet.getString("VAL_CAT_VAL"));				
				alConstanciaActasBean.add(luConstanciaActasBean);
			}
			
			ordernarListaConstancias(alConstanciaActasBean);

		}catch(SQLException sqle){
			sqle.printStackTrace();
			sqle.getSQLState();

		}catch(Exception e){
			e.printStackTrace();
			e.getMessage();
		}finally{
			try {
				puPreparedStatement.close();
				puCallableStatement.close();
				puResultSet.close();
				puConnectionWrapper.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		return alConstanciaActasBean;
	}
	
	/**
	 * Obtiene Nombre de la Empresa
	 * @param tsNameEmp
	 * @return String
	 */
	public String getNameEmp(String tsNameEmp){

		String lsSql = /*"SELECT   NOM_EMPRESA "                                          +
						"FROM    DERCORP_EMPRESA_TAB EMP "                              +
						"WHERE   1=1 "                                                  +
						"AND     ID_EMPRESA IN (?) "                                    +
						"AND     NOM_EMPRESA = ( SELECT  VAL_CAT_VAL "                  +
						                        "FROM    DERCORP_ADD_CAMPO_CAT_VAL_TAB "+
						                        "WHERE   1=1 "                          +
						                        "AND     ID_CATALOGO = 1 "              +
						                        "AND     VAL_CAT_VAL = EMP.NOM_EMPRESA "+
						")"*/
						                        
						"SELECT val_cat_val AS NOM_EMPRESA\n"+
				        "  FROM dercorp_add_campo_cat_val_tab\n"+ 
				        "  WHERE id_catalogo = 1\n"+
				        "  AND   id_catalogo_valor = (SELECT val_valor\n"+ 
				        "                            FROM dercorp_add_campo_valor_tab\n"+ 
				        "                             WHERE id_empresa = ?\n"+
				        "                             AND id_add_campo = 500)";

		try{
			puConnectionWrapper = new ConnectionWrapper();
			puPreparedStatement = puConnectionWrapper.prepareStatement(lsSql);
			puPreparedStatement.setInt(1, Integer.parseInt(tsNameEmp));
			puResultSet = puPreparedStatement.executeQuery();

			while(puResultSet.next()){
				return puResultSet.getString("NOM_EMPRESA");
			}

        }catch(Exception sqle){
			sqle.printStackTrace();

		}finally{
			try {
				puPreparedStatement.close();
				puResultSet.close();
				puConnectionWrapper.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		return "";

	}

	/**
	 * Obtiene Direccion de los auditores Externos
	 * @param tsIdEmp
	 * @return StringBuilder
	 */
	public StringBuilder getDireccAudExt(String tsIdEmp){
		StringBuilder lugetDireccAudExt = new StringBuilder();
		StringBuilder luSql =  new StringBuilder(
				                "SELECT  ATRIBUTO1 "                                                   +
								"FROM    DERCORP_ADD_CAMPO_CAT_VAL_TAB "                               +
								"WHERE   1=1 "                                                         +
								"AND     ID_CATALOGO = 58 "                                            +
								"AND     ID_CATALOGO_VALOR = ( "                                       +
								                              "SELECT VAL_VALOR "                      +
								                              "FROM   DERCORP_ADD_CAMPO_VALOR_TAB "    +
								                              "WHERE  1=1 "                            +
								                              "AND    ID_ADD_CAMPO = 1052 "            +
								                              "AND    ID_EMPRESA = ? "              +
                                ")");
		try{
			puConnectionWrapper = new ConnectionWrapper();
			puPreparedStatement = puConnectionWrapper.prepareStatement(luSql.toString());
			puPreparedStatement.setInt(1, Integer.parseInt(tsIdEmp));
			puResultSet = puPreparedStatement.executeQuery();
			
			while(puResultSet.next()){
				return lugetDireccAudExt.append(puResultSet.getString("ATRIBUTO1"));
			}

		}catch(SQLException sqle){
			sqle.printStackTrace();
			sqle.getSQLState();

		}catch(Exception e){
			e.printStackTrace();
			e.getMessage();
		}finally{
			try {
				puPreparedStatement.close();
				puResultSet.close();
				puConnectionWrapper.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return null;

	}
	
	/**
	 * Obtiene remitente y direcci�n del reporte
	 * @return Map con key y value de remitente y direcci�n
	 */
	public Map<String,String> getDatosParaReporte(){
		String lsSql;
		Map<String,String> result = new HashMap<String,String>();
		lsSql = "SELECT  NOM_CAT_VAL,VAL_CAT_VAL " +
				"FROM    DERCORP_ADD_CAMPO_CAT_VAL_TAB VAL INNER JOIN DERCORP_ADD_CAMPO_CAT_TAB CAT " +	
				"ON      VAL.ID_CATALOGO = CAT.ID_CATALOGO " +
				"WHERE   1=1 " +
				"AND     COD_CATALOGO = 'ReporteConstanciasActas'"
                ;

		try{
			puConnectionWrapper = new ConnectionWrapper();						
			puResultSet = puConnectionWrapper.executeQuery(lsSql);

			while(puResultSet.next()){		
				result.put(puResultSet.getString("NOM_CAT_VAL"), puResultSet.getString("VAL_CAT_VAL"));
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
				puConnectionWrapper.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
		return result;
	}
	
	/**
	 * Guarda remitente y direcci�n del reporte
	 */
	public boolean saveDatosParaReporte(String remitente, String direccion){
		String lsSql;
		boolean result = false;		

		try{
			puConnectionWrapper = new ConnectionWrapper();						
			lsSql = "UPDATE DERCORP_ADD_CAMPO_CAT_VAL_TAB " +
					"SET VAL_CAT_VAL=? " +				        
					"WHERE   1=1 " +
					"AND    NOM_CAT_VAL='Remitente'"
	                ;
			puPreparedStatement = puConnectionWrapper.prepareStatement(lsSql);
			puPreparedStatement.setString(1, remitente);
			result = puPreparedStatement.execute();
			
			lsSql = "UPDATE DERCORP_ADD_CAMPO_CAT_VAL_TAB " +
					"SET VAL_CAT_VAL=? " +				        
					"WHERE   1=1 " +
					"AND    NOM_CAT_VAL='Direcci�n'"
	                ;
			puPreparedStatement = puConnectionWrapper.prepareStatement(lsSql);
			puPreparedStatement.setString(1, direccion);
			
			result = puPreparedStatement.execute();

		}catch(SQLException sqle){
			sqle.printStackTrace();
			sqle.getSQLState();

		}catch(Exception e){
			e.printStackTrace();
			e.getMessage();
		}finally{
			try {				
				puResultSet.close();
				puConnectionWrapper.close();
				puPreparedStatement.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
		return result;
	}
	
	private List<ConstanciaActasBean> ordernarListaConstancias(List<ConstanciaActasBean> lista){		
		Collections.sort(lista, Collections.reverseOrder(new Comparator<ConstanciaActasBean>() {
		    public int compare(ConstanciaActasBean c1, ConstanciaActasBean c2) {
		    	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");		    	
		    	try { 
					return formatter.parse(c1.getPsFecha()).compareTo(formatter.parse(c2.getPsFecha()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
		    	return 0;
		    }
		}));
		return lista;
	}
}