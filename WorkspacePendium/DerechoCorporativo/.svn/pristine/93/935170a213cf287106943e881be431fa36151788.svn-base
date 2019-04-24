package mx.com.televisa.derechocorporativo.model;

import java.sql.*;
import java.util.*;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import mx.com.televisa.derechocorporativo.bean.ApoderadosAgregaBean;
import mx.com.televisa.derechocorporativo.bean.ApoderadosGruposApoBean;
import mx.com.televisa.derechocorporativo.bean.CatalogoValBean;
import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;
import mx.com.televisa.derechocorporativo.data.packages.DERCORP_CATALOGS_PKG;
import mx.com.televisa.derechocorporativo.data.packages.DERCORP_REPORTS_PKG;
import mx.com.televisa.derechocorporativo.data.packages.DERCORP_APODERADOS_PKG;
import mx.com.televisa.derechocorporativo.modules.reports.Report;
import mx.com.televisa.derechocorporativo.util.TextFormatter;

public class Catalog {
	
	ConnectionWrapper connectionWrapper;

	String tableName = "";

	public Catalog() {
		
	}
	
	public Catalog(String tableName) {
		
		this.tableName = tableName;
	}

    
    public CatalogElement getFirstRow(String where) throws Exception {

        return getList(where).get(0);
    }

    public List<CatalogElement> getList() throws Exception {

        return getList("");
    }

    
    public List<CatalogElement> getList(String where) throws Exception {
    
        return getList("*", where);
    }
    
    public String getNumeroOrdenApoderados(String idEmpresa,
    									 String tipoPoder,
    									 String escritura,
    									 String grupo){
    	ConnectionWrapper connect = null;
		Connection con 			  = null;
		ResultSet  rs    		  = null;
		String     numConse       = "";
		PreparedStatement psmt	  = null;
		
    	String sql = " SELECT atributo2 \n"+ 
			         "   FROM dercorp_apoderados_wk_tab apo\n"+
			         "   WHERE 1 = 1\n"+
			         "   AND	apo.id_empresa          = ?\n"+ 
			    	 "			AND apo.num_tipo_poder  = ?\n"+
			    	 "			AND apo.des_escritura   = ?\n"+
			         "   AND TRIM(apo.atributo3)        = ?\n"+
			         "   AND ROWNUM                     = 1";
    	try {
			connect = new ConnectionWrapper();
			con = connect.getConnection();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, idEmpresa);
			psmt.setString(2, tipoPoder);
			psmt.setString(3, escritura);
			psmt.setString(4, grupo==null?"":TextFormatter.replaceAccentsURLFromAjax(grupo).trim());
			rs = psmt.executeQuery();
			while(rs.next()){
				numConse = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				psmt.close();
				connect.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
    	
    	return numConse;
    }
    
    
    public static List<CatalogElement> getCatalogElements(int catalogId, String filter, String currentIds) {

		ArrayList<CatalogElement> rows = new ArrayList<CatalogElement>();

		ConnectionWrapper connect = null;
		CallableStatement stmt = null;
		ResultSet set = null;

		try {

			connect = new ConnectionWrapper();

			stmt = connect.prepareCall(DERCORP_CATALOGS_PKG.GET_CATALOG_ELEMENTS_PR);

			stmt.setInt(1, catalogId);
			stmt.setString(2, filter);
			stmt.setString(3, currentIds);
			stmt.setInt(4, 0);
			stmt.registerOutParameter(5, OracleTypes.CURSOR);

			stmt.execute();

			set = ((OracleCallableStatement) stmt).getCursor(5);

			while (set.next()) {

				rows.add(new CatalogElement(set));
			}

		} catch (Exception ex) {

			// TODO - Pendiente - Gestion de Excepciones
			ex.printStackTrace();

		} finally {

			ConnectionWrapper.closeAll(set, stmt, connect);
		}

		return rows;
	}
    
    public static List<CatalogElement> getCatalogElements(int catalogId, String filter, String currentIds, int rolId) {

		ArrayList<CatalogElement> rows = new ArrayList<CatalogElement>();

		ConnectionWrapper connect = null;
		CallableStatement stmt = null;
		ResultSet set = null;

		try {

			connect = new ConnectionWrapper();

			stmt = connect.prepareCall(DERCORP_CATALOGS_PKG.GET_CATALOG_ELEMENTS_PR);

			stmt.setInt(1, catalogId);
			stmt.setString(2, filter);
			stmt.setString(3, currentIds);
			stmt.setInt(4, rolId);
			stmt.registerOutParameter(5, OracleTypes.CURSOR);

			stmt.execute();

			set = ((OracleCallableStatement) stmt).getCursor(5);

			while (set.next()) {

				rows.add(new CatalogElement(set));
			}

		} catch (Exception ex) {

			// TODO - Pendiente - Gestion de Excepciones
			ex.printStackTrace();

		} finally {

			ConnectionWrapper.closeAll(set, stmt, connect);
		}

		return rows;
	}
    
    public static List<CatalogElement> getCatalogElements(int catalogId, String filter, String currentIds, int rolId,String clasif,String pais) {

		ArrayList<CatalogElement> rows = new ArrayList<CatalogElement>();

		ConnectionWrapper connect 	= null;
		CallableStatement stmt 		= null;
		ResultSet set  				= null;
		String strPais 				= "";
		if(pais != null && !pais.equals("")){
        	
        	int idPais = Integer.parseInt(pais);
        	if(idPais > 0){
        		strPais = "and VEMP.id_pais ="+idPais;
        	}
        	
        }

		try {

			connect = new ConnectionWrapper();

			stmt = connect.prepareCall(DERCORP_CATALOGS_PKG.GET_CATALOG_ELEMENTS_CLASIF_PR);

			stmt.setInt(1, catalogId);
			stmt.setString(2, filter);
			stmt.setString(3, currentIds);
			stmt.setInt(4, rolId);
			stmt.registerOutParameter(5, OracleTypes.CURSOR);
			stmt.setString(6, clasif);
			stmt.setString(7, strPais);

			stmt.execute();

			set = ((OracleCallableStatement) stmt).getCursor(5);

			while (set.next()) {

				rows.add(new CatalogElement(set));
			}

		} catch (Exception ex) {

			// TODO - Pendiente - Gestion de Excepciones
			ex.printStackTrace();

		} finally {

			ConnectionWrapper.closeAll(set, stmt, connect);
		}

		return rows;
	}
    
    
    
    

    public static String getCatalogElementDescription(String catalogElementId) {

		ConnectionWrapper connect = null;
		CallableStatement stmt = null;
		ResultSet set = null;

		try {

			connect = new ConnectionWrapper();

			stmt = connect.prepareCall(DERCORP_CATALOGS_PKG.GET_ELEMENT_DESCRIP_PR);

			stmt.setString(1, catalogElementId);
			stmt.registerOutParameter(2, OracleTypes.VARCHAR);

			stmt.execute();

			String str = (String )stmt.getObject(2);
			
			return str;
			
		} catch (Exception ex) {

			//ex.printStackTrace();

			return null;
			
		} finally {

			ConnectionWrapper.closeAll(set, stmt, connect);
		}		
	}
    

    
    public static List<CatalogElement> getCatalogPoderes(String filter, String currentIds) {

		ArrayList<CatalogElement> rows = new ArrayList<CatalogElement>();

		ConnectionWrapper connect = null;
		CallableStatement stmt = null;
		ResultSet set = null;

		try {

			connect = new ConnectionWrapper();

			stmt = connect.prepareCall(DERCORP_CATALOGS_PKG.GET_CATALOG_PODERES_PR);

			stmt.setString(1, filter);
			stmt.setString(2, currentIds);
			
			stmt.registerOutParameter(3, OracleTypes.CURSOR);

			stmt.execute();

			set = ((OracleCallableStatement) stmt).getCursor(3);

			while (set.next()) {

				rows.add(new CatalogElement(set));
			}

		} catch (Exception ex) {

			// TODO - Pendiente - Gestion de Excepciones
			ex.printStackTrace();

		} finally {

			ConnectionWrapper.closeAll(set, stmt, connect);
		}

		return rows;
	}
    
    
    public List<CatalogElement> getList(String fieldList, String where) throws Exception {

        String sql = "SELECT " + fieldList + " FROM " + this.tableName + " " + where;
        
        List<CatalogElement> records = new ArrayList<CatalogElement>();
        ConnectionWrapper connectionWrapper = null;
        
        try {
            connectionWrapper = new ConnectionWrapper();
            ResultSet rs = connectionWrapper.executeQuery(sql);
            ResultSetMetaData metaData = rs.getMetaData();
            while (rs.next()) {
                
       
                
            	CatalogElement elem = new CatalogElement();
                
                
                elem.fillObject(rs, metaData);
                        
                records.add(elem);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionWrapper.closeAll(connectionWrapper);
        }

        return records;
    }

    public List<CatalogElement> getList(String fieldList, String where,String clasif,String pais) throws Exception {

        String sql = "SELECT " + fieldList + " FROM " + this.tableName + " " + where;
        if(!clasif.equals("")){
        	sql += " AND  id_clasificacion IN ("+clasif+")";
        }
        if(pais != null && !pais.equals("")){
        	
        	int idPais = Integer.parseInt(pais);
        	if(idPais > 0){
        		sql += " AND  id_pais = "+idPais;
        	}
        	
        }
        sql += " ORDER BY UPPER(APP_COMMON_PKG.SIN_ACENTOS_FN(DENOM_ACTUAL))";
        List<CatalogElement> records = new ArrayList<CatalogElement>();
        ConnectionWrapper connectionWrapper = null;
        
        try {
            connectionWrapper = new ConnectionWrapper();
            ResultSet rs = connectionWrapper.executeQuery(sql);
            ResultSetMetaData metaData = rs.getMetaData();
            while (rs.next()) {
                
       
                
            	CatalogElement elem = new CatalogElement();
                
                
                elem.fillObject(rs, metaData);
                        
                records.add(elem);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionWrapper.closeAll(connectionWrapper);
        }

        return records;
    }
    
    public List<CatalogElementV2>	dameEscrituras(int idEmpresa){
    	Connection con = null;
    	CatalogElementV2 catalogElementV2;
    	List<CatalogElementV2> records = new ArrayList<>();
    	ResultSet rs = null;
    	try {
			connectionWrapper = new ConnectionWrapper();
			con = connectionWrapper.getConnection();
			/*String lstQuery = 	"SELECT      VAL_C8 AS ID, VAL_C8 "+
								"FROM      DERCORP_METATBL_TAB M " +
								"WHERE     1=1 "+
								"AND       ID_FLEX_TBL = 17 "+ 
								"AND       ID_EMPRESA = ? "+
								"UNION ALL "+
									"SELECT      VAL_C8 AS ID, VAL_C8 "+
								    "FROM      DERCORP_METATBL_TAB M "+
								    "WHERE     1=1 "+
								    "AND       ID_FLEX_TBL = 18 "+
								    "AND       ID_EMPRESA = ? "+
								"UNION ALL "+
								    "SELECT VAL_VALOR AS ID,VAL_VALOR AS VAL_C4 "+
								    "FROM( "+
								    "SELECT VAL_VALOR "+ 
							        "FROM DERCORP_ADD_CAMPO_VALOR_TAB "+ 
							        "WHERE ID_EMPRESA = ? "+
							        "AND ID_ADD_CAMPO = 551 "+
							        ")"; */
			
			//ICL 25/01/2016 Se cambia query para considerar escrituras no protocolizadas todavía
			//JJAQ Se agrega que no traiga escrituras marcadas como revocadas
			String lstQuery = 	
					"SELECT DISTINCT ID,ID2 FROM ( " +
					"SELECT  "+   
					"CASE "  +
				      "WHEN VAL_C8 IS NULL THEN (SELECT VAL_CAT_VAL FROM DERCORP_ADD_CAMPO_CAT_VAL_TAB WHERE ID_CATALOGO_VALOR = VAL_C1)||'-'||NVL(VAL_C3,'SF') " +
				      "WHEN VAL_C8 = 'N/A' THEN (SELECT VAL_CAT_VAL FROM DERCORP_ADD_CAMPO_CAT_VAL_TAB WHERE ID_CATALOGO_VALOR = VAL_C1)||'-'||NVL(VAL_C3,'SF') " +
				      "ELSE VAL_C8 " +
				    "END AS ID, "+
				    "CASE " + 
				      "WHEN VAL_C8 IS NULL THEN (SELECT VAL_CAT_VAL FROM DERCORP_ADD_CAMPO_CAT_VAL_TAB WHERE ID_CATALOGO_VALOR = VAL_C1)||'-'||NVL(VAL_C3,'SF') " +
				      "WHEN VAL_C8 = 'N/A' THEN (SELECT VAL_CAT_VAL FROM DERCORP_ADD_CAMPO_CAT_VAL_TAB WHERE ID_CATALOGO_VALOR = VAL_C1)||'-'||NVL(VAL_C3,'SF') " +
				      "ELSE VAL_C8 "+
				    "END AS ID2 " +
					"FROM      DERCORP_METATBL_TAB M " +
					"WHERE     1=1 "+
					"AND       ID_FLEX_TBL = 17 "+ 
					"AND       ID_EMPRESA = ? "+
					//"AND        val_c75 = 'No' "+
					"UNION ALL "+
						"SELECT " +
						"CASE "  +
					      "WHEN VAL_C8 IS NULL THEN (SELECT VAL_CAT_VAL FROM DERCORP_ADD_CAMPO_CAT_VAL_TAB WHERE ID_CATALOGO_VALOR = VAL_C1)||'-'||NVL(VAL_C3,'SF') " +
					      "WHEN VAL_C8 ='N/A' THEN (SELECT VAL_CAT_VAL FROM DERCORP_ADD_CAMPO_CAT_VAL_TAB WHERE ID_CATALOGO_VALOR = VAL_C1)||'-'||NVL(VAL_C3,'SF') " +
					      "ELSE VAL_C8 " +
					    "END AS ID, "+
					    "CASE " + 
					      "WHEN VAL_C8 IS NULL THEN (SELECT VAL_CAT_VAL FROM DERCORP_ADD_CAMPO_CAT_VAL_TAB WHERE ID_CATALOGO_VALOR = VAL_C1)||'-'||NVL(VAL_C3,'SF') " +
					      "WHEN VAL_C8='N/A' THEN (SELECT VAL_CAT_VAL FROM DERCORP_ADD_CAMPO_CAT_VAL_TAB WHERE ID_CATALOGO_VALOR = VAL_C1)||'-'||NVL(VAL_C3,'SF') " +
					      "ELSE VAL_C8 "+
					    "END AS ID2 " +
					 "FROM      DERCORP_METATBL_TAB M "+
					 "WHERE     1=1 "+
					 "AND       ID_FLEX_TBL = 18 "+
					 "AND       ID_EMPRESA = ? "+
					 //"AND        val_c75 = 'No' "+
					 "UNION ALL "+
					    "SELECT VAL_VALOR AS ID,VAL_VALOR AS VAL_C4 "+
					    "FROM( "+
					    "SELECT VAL_VALOR "+ 
				        "FROM DERCORP_ADD_CAMPO_VALOR_TAB "+ 
				        "WHERE ID_EMPRESA = ? "+
				        "AND ID_ADD_CAMPO = 551 "+
				        ") "+
				      "ORDER BY 1)";
			
			PreparedStatement psmt = con.prepareStatement(lstQuery);
			psmt.setInt(1, idEmpresa);
			psmt.setInt(2, idEmpresa);
			psmt.setInt(3, idEmpresa);
			 rs = psmt.executeQuery();

			while(rs.next()){
				catalogElementV2 = new CatalogElementV2();
				catalogElementV2.setValC4(TextFormatter.removeAccents(rs.getString(1)));
				catalogElementV2.setValValor(TextFormatter.removeAccents(rs.getString(2)));
				records.add(catalogElementV2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			ConnectionWrapper.closeAll(rs, con);
			/*try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}*/
		}
    	return records;
    }
    
    public List<CatalogElementV2>	dameEscrituras(int idEmpresa,int tipoPoder){
    	Connection con = null;
    	CatalogElementV2 catalogElementV2;
    	List<CatalogElementV2> records = new ArrayList<>();
    	ResultSet rs = null;
    	try {
			connectionWrapper = new ConnectionWrapper();
			con = connectionWrapper.getConnection();
			
			String lstQuery = "SELECT val_c8 as id1,val_c8 as id2 FROM DERCORP_METATBL_TAB\n"+ 
							  "	WHERE ID_EMPRESA  = ? \n"+
							  "	AND ID_FLEX_TBL   = ?\n"+
							  "	AND val_c75 = 'Si'";
			
			PreparedStatement psmt = con.prepareStatement(lstQuery);
			psmt.setInt(1, idEmpresa);
			if(tipoPoder==12290){
				psmt.setInt(2,17);
			}else if(tipoPoder==12291){
				psmt.setInt(2,18);
			}			
			 rs = psmt.executeQuery();

			while(rs.next()){
				catalogElementV2 = new CatalogElementV2();
				catalogElementV2.setValC4(rs.getString(1));
				catalogElementV2.setValValor(rs.getString(2));
				records.add(catalogElementV2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			ConnectionWrapper.closeAll(rs, con);
		}
    	return records;
    }
    

    
    /**
     * Obtiene la fecha de la escritura
     * @param tIdEmpresa
     * @param tTipoPoder
     * @param tsEscitura
     * @return
     */
    public String dameFechaEscritura(int tIdEmpresa,int tTipoPoder,String tsEscitura){
    	Connection con 				   = null;
    	ResultSet rs 		  		   = null;
    	String lsFecEscritura 		   = null;
    	try {
			connectionWrapper = new ConnectionWrapper();
			con = connectionWrapper.getConnection();
			
			String lstQuery = "SELECT val_c3 as fec FROM DERCORP_METATBL_TAB\n"+ 
							  "	WHERE ID_EMPRESA  = ? \n"+
							  "	AND ID_FLEX_TBL   = ?\n"+
							  "	AND val_c75 = 'Si'\n"+
							  " AND val_c8 = ?";
			
			PreparedStatement psmt = con.prepareStatement(lstQuery);
			psmt.setInt(1, tIdEmpresa);
			if(tTipoPoder==12290){
				psmt.setInt(2,17);
			}else if(tTipoPoder==12291){
				psmt.setInt(2,18);
			}
				psmt.setString(3, tsEscitura);
				rs = psmt.executeQuery();

			while(rs.next()){
				lsFecEscritura = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			ConnectionWrapper.closeAll(rs, con);
		}
    	return lsFecEscritura;
    }
    
    public List<CatalogoValBean> dameApoderados(int idCatalogo,
    										    int idEmpresa,
    										    int tipoPoder,
    										    String desGrupo,
    										    String desEscritura){
    	Connection con = null;
    	CatalogoValBean catalogoValBean;
    	List<CatalogoValBean> listCatal = new ArrayList<>();
    	ResultSet rs 		   = null;
    	PreparedStatement psmt = null;
    	
    	try {
			connectionWrapper = new ConnectionWrapper();
			//System.out.println("Abre con dameApoderados");
			con = connectionWrapper.getConnection();
			String lstQuery = "SELECT  cvt.id_catalogo_valor,\n"+
							  "	       cvt.id_catalogo,\n"+ 
							  "	       cvt.cod_cat_val,\n"+
							  "	       cvt.nom_cat_val,\n"+
							  "	       cvt.val_cat_val,\n"+
							  "	       cvt.des_cat_val,     \n"+
							  "	       cvt.atributo1     \n"+
							  "	FROM dercorp_add_campo_cat_val_tab cvt\n"+ 
							  "	WHERE cvt.id_catalogo = ?\n"+
							  "AND  NOT EXISTS(SELECT *\n"+ 
							  "                  FROM dercorp_apoderados_wk_tab\n"+
							  "                  WHERE id_catalogo       = ?\n"+
							  "                  AND   id_catalogo_valor = cvt.id_catalogo_valor\n"+
							  "                  AND   id_empresa        = ?\n" +
							  "                  AND   num_tipo_poder    = ?\n" +
							  "                  AND   trim(atributo3)         = trim(?)\n" +
							  "                  AND   trim(des_escritura)     = trim(?))\n" +
							  "ORDER BY APP_COMMON_PKG.SIN_ACENTOS_FN(cvt.val_cat_val)";

			psmt = con.prepareStatement(lstQuery);
			psmt.setInt(1, idCatalogo);
			psmt.setInt(2, idCatalogo);
			psmt.setInt(3, idEmpresa);
			psmt.setInt(4, tipoPoder);
			psmt.setString(5, desGrupo.trim());
			psmt.setString(6, desEscritura);
			
			//System.out.println("Query "+lstQuery);
			rs = psmt.executeQuery();

			while(rs.next()){
				catalogoValBean = new CatalogoValBean();
				catalogoValBean.setIdCatalogoValor(rs.getInt(1));
				catalogoValBean.setIdCatalogo(rs.getInt(2));
				catalogoValBean.setCodCatVal(rs.getString(3));
				catalogoValBean.setNomCatval(rs.getString(4));
				catalogoValBean.setValCatVal(rs.getString(5));
				catalogoValBean.setDesCatVal(rs.getString(6));
				catalogoValBean.setAtributo1(rs.getString(7));
				listCatal.add(catalogoValBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				//System.out.println("Cierra con dameApoderados");
				rs.close();
				psmt.close();
				connectionWrapper.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
    	return listCatal;
    }
    
    public List<ApoderadosAgregaBean> dameApoderadosAgregados(int idCatalogo,
    														  int idEmpresa,
    														  int tipoPoder,
    			    										  String desGrupo,
    			    										  String desEscritura){
    	Connection con = null;
    	ApoderadosAgregaBean apoderadosAgregaBean;
    	List<ApoderadosAgregaBean> listApode = new ArrayList<>();
    	PreparedStatement psmt = null;
    	ResultSet rs           = null;
    	
    	//System.out.println("idCatalogo: "+idCatalogo);
    	//System.out.println("idEmpresa: "+idEmpresa);
    	//System.out.println("tipoPoder "+tipoPoder);
    	//System.out.println("desGrupo: "+desGrupo);
    	//System.out.println("desEscritura "+desEscritura);
    	try {
			connectionWrapper = new ConnectionWrapper(); 
			con = connectionWrapper.getConnection();
			String lstQuery = "SELECT  da.id_empresa,\n"+
							  "	       da.id_catalogo,\n"+
							  "	       da.id_catalogo_valor,\n"+
							  "	       da.des_tipo_elemento,\n"+
							  "	       da.num_tipo_poder,\n"+
							  "	       da.des_grupo,\n"+
							  "	       da.des_escritura, \n"+
							  " (SELECT val_cat_val \n"+
							  "      FROM dercorp_add_campo_cat_val_tab \n"+
							  "      WHERE id_catalogo_valor = da.id_catalogo_valor) nombre,\n"+
							  " (SELECT atributo1 \n"+
							  "      FROM dercorp_add_campo_cat_val_tab \n"+
							  "      WHERE id_catalogo_valor = da.id_catalogo_valor) atributo1,\n"+
							  "			da.id_revocacion \n"+
							  "	FROM dercorp_apoderados_wk_tab da\n"+
							  "	WHERE id_catalogo = ?\n"+
							  "	AND   id_empresa  = ?\n"+
							  " AND   num_tipo_poder    = ?\n"+
							  "	AND   trim(atributo3)         = trim(?)\n"+
							  "	AND   trim(des_escritura)     = trim(?)\n"+
							  "ORDER BY  APP_COMMON_PKG.SIN_ACENTOS_FN(nombre)";

			psmt = con.prepareStatement(lstQuery);
			psmt.setInt(1, idCatalogo);
			psmt.setInt(2, idEmpresa);
			psmt.setInt(3, tipoPoder);
			psmt.setString(4, desGrupo);
			psmt.setString(5, desEscritura);
			rs = psmt.executeQuery();
			//System.out.println("Query: "+lstQuery);

			while(rs.next()){
				apoderadosAgregaBean = new ApoderadosAgregaBean();
				apoderadosAgregaBean.setIdEmpresa(rs.getInt(1));
				apoderadosAgregaBean.setIdCatalogo(rs.getInt(2));
				apoderadosAgregaBean.setIdCatalogoValor(rs.getInt(3));
				apoderadosAgregaBean.setDesTipoElemento(rs.getString(4));
				apoderadosAgregaBean.setNumTipoPoder(rs.getInt(5));
				apoderadosAgregaBean.setDesGrupo(rs.getString(6));
				apoderadosAgregaBean.setDesEscritura(rs.getString(7));
				apoderadosAgregaBean.setNombre(rs.getString(8));
				apoderadosAgregaBean.setAtributo1(rs.getString(9));
				apoderadosAgregaBean.setIdRevocacion(rs.getInt(10));
				listApode.add(apoderadosAgregaBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				psmt.close();
				rs.close();
				ConnectionWrapper.closeAll(connectionWrapper);
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
    	return listApode;
    }
    
    //ECM 25 Agosto 2015
    public List<ApoderadosGruposApoBean> dameGrupoApoderados(){
    	Connection con = null;
    	ApoderadosGruposApoBean grupoApo = null;
    	List<ApoderadosGruposApoBean> ltGrupoApo = new ArrayList<ApoderadosGruposApoBean>();
    	try{
			ConnectionWrapper connectionWrapper = new ConnectionWrapper();
			con = connectionWrapper.getConnection();
			String lstQuery = 	"SELECT  COD_CAT_VAL "						+
								       ",VAL_CAT_VAL "						+
								"FROM    DERCORP_ADD_CAMPO_CAT_VAL_TAB "	+
								"WHERE   1=1 "								+
								"AND     ID_CATALOGO = 45 "                 +
								"ORDER BY VAL_CAT_VAL"
			;
			PreparedStatement psmt = con.prepareStatement(lstQuery);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()){
				grupoApo = new ApoderadosGruposApoBean();
				grupoApo.setCodCatVal(rs.getString(1));
				grupoApo.setNomCatVal(rs.getString(2));
				ltGrupoApo.add(grupoApo);
			}

    	} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
    	return ltGrupoApo;
    }
    
    public List<CatalogElementV2>	dameTodasEscrituras(int idEmpresa){
    	Connection con = null;
    	CatalogElementV2 catalogElementV2;
    	List<CatalogElementV2> records = new ArrayList<>();
    	try {
			connectionWrapper = new ConnectionWrapper();
			con = connectionWrapper.getConnection();
			/*String lstQuery = 	"SELECT    (SELECT DISTINCT(v.denom_actual) \n" +
								"            FROM dercorp_busqueda_view v\n" +
								"            WHERE v.id_empresa = m.id_empresa)AS empresa,\n" +
								"           m.id_empresa,\n" +
								"           VAL_C8 AS ID,\n" +
								"           VAL_C8 \n" +
								"FROM      DERCORP_METATBL_TAB M \n" +
								"WHERE     1=1 \n" +
								"AND       ID_FLEX_TBL = 17\n" +
								"UNION ALL \n" +
								"    SELECT    (SELECT DISTINCT(v.denom_actual) \n" +
								"                FROM dercorp_busqueda_view v\n" +
								"                WHERE v.id_empresa = m.id_empresa)AS empresa,\n" +
								"              m.id_empresa,\n" +
								"              VAL_C8 AS ID,\n" +
								"              VAL_C8 \n" +
								"    FROM      DERCORP_METATBL_TAB M \n" +
								"    WHERE     1=1 \n" +
								"    AND       ID_FLEX_TBL = 18    \n" +
								"UNION ALL \n" +
								"    SELECT av.empresa,\n" +
								"           av.id_empresa,\n" +
								"           av.val_valor as id,\n" +
								"           av.val_valor as val_c4 \n" +
								"    FROM( \n" +
								"        SELECT m.val_valor,\n" +
								"               m.id_empresa,\n" +
								"               (SELECT DISTINCT(v.denom_actual) \n" +
								"                FROM dercorp_busqueda_view v\n" +
								"                WHERE v.id_empresa = m.id_empresa)AS empresa \n" +
								"        FROM dercorp_add_campo_valor_tab m\n" +
								"        WHERE 1=1\n" +
								"        AND id_add_campo = 551 \n" +
								"       )av ";  */
			String lstQuery = 	
					"SELECT DISTINCT EMPRESA,ID_EMPRESA,ID,ID2 FROM (  																												   " +		
							"            SELECT                                                                                                                                                " +
							"            (SELECT DISTINCT(V.DENOM_ACTUAL)                                                                                                                      " +
							"						 FROM DERCORP_BUSQUEDA_VIEW V                                                                                                              " +
							"						 WHERE V.ID_EMPRESA = M.ID_EMPRESA)AS EMPRESA,                                                                                             " +
							"						 M.ID_EMPRESA,                                                                                                                             " +
							"						CASE                                                                                                                                       " +
							"					      WHEN VAL_C8 IS NULL THEN (SELECT VAL_CAT_VAL FROM DERCORP_ADD_CAMPO_CAT_VAL_TAB WHERE ID_CATALOGO_VALOR = VAL_C1)||'-'||NVL(VAL_C3,'SF') " +
							"					      WHEN VAL_C8 = 'N/A' THEN (SELECT VAL_CAT_VAL FROM DERCORP_ADD_CAMPO_CAT_VAL_TAB WHERE ID_CATALOGO_VALOR = VAL_C1)||'-'||NVL(VAL_C3,'SF') " +							
							"					      ELSE VAL_C8                                                                                                                              " +
							"					    END AS ID,                                                                                                                                 " +
							"					    CASE                                                                                                                                       " +
							"					      WHEN VAL_C8 IS NULL THEN (SELECT VAL_CAT_VAL FROM DERCORP_ADD_CAMPO_CAT_VAL_TAB WHERE ID_CATALOGO_VALOR = VAL_C1)||'-'||NVL(VAL_C3,'SF') " +
							"					      WHEN VAL_C8 ='N/A' THEN (SELECT VAL_CAT_VAL FROM DERCORP_ADD_CAMPO_CAT_VAL_TAB WHERE ID_CATALOGO_VALOR = VAL_C1)||'-'||NVL(VAL_C3,'SF') " +
							"					      ELSE VAL_C8                                                                                                                              " +
							"					    END AS ID2                                                                                                                                 " +
							"						FROM      DERCORP_METATBL_TAB M                                                                                                            " +
							"						WHERE     1=1                                                                                                                              " +
							"						AND       ID_FLEX_TBL = 17                                                                                                                 " +
							"					UNION ALL                                                                                                                                      " +
							"            SELECT                                                                                                                                                " +
							"            (SELECT DISTINCT(V.DENOM_ACTUAL)                                                                                                                      " +
							"						 FROM DERCORP_BUSQUEDA_VIEW V                                                                                                              " +
							"						 WHERE V.ID_EMPRESA = M.ID_EMPRESA) AS EMPRESA,                                                                                            " +
							"						 M.ID_EMPRESA,                                                                                                                             " +
							"						CASE                                                                                                                                       " +
							"					      WHEN VAL_C8 IS NULL THEN (SELECT VAL_CAT_VAL FROM DERCORP_ADD_CAMPO_CAT_VAL_TAB WHERE ID_CATALOGO_VALOR = VAL_C1)||'-'||NVL(VAL_C3,'SF') " +
							"					      WHEN VAL_C8 = 'N/A' THEN (SELECT VAL_CAT_VAL FROM DERCORP_ADD_CAMPO_CAT_VAL_TAB WHERE ID_CATALOGO_VALOR = VAL_C1)||'-'||NVL(VAL_C3,'SF') " +
							"					      ELSE VAL_C8                                                                                                                              " +
							"					    END AS ID,                                                                                                                                 " +
							"					    CASE                                                                                                                                       " +
							"					      WHEN VAL_C8 IS NULL THEN (SELECT VAL_CAT_VAL FROM DERCORP_ADD_CAMPO_CAT_VAL_TAB WHERE ID_CATALOGO_VALOR = VAL_C1)||'-'||NVL(VAL_C3,'SF') " +
							"					      WHEN VAL_C8 ='N/A' THEN (SELECT VAL_CAT_VAL FROM DERCORP_ADD_CAMPO_CAT_VAL_TAB WHERE ID_CATALOGO_VALOR = VAL_C1)||'-'||NVL(VAL_C3,'SF') " +
							"					      ELSE VAL_C8                                                                                                                              " +
							"					    END AS ID2                                                                                                                                 " +
							"					 FROM      DERCORP_METATBL_TAB M                                                                                                               " +
							"					 WHERE     1=1                                                                                                                                 " +
							"					 AND       ID_FLEX_TBL = 18                                                                                                                    " +
							"					 UNION ALL                                                                                                                                     " +
							"           SELECT AV.EMPRESA,                                                                                                                                     " +
							"                  AV.ID_EMPRESA,                                                                                                                                  " +
							"								  AV.VAL_VALOR AS ID,                                                                                                              " +
							"								  AV.VAL_VALOR AS VAL_C4                                                                                                           " +
							"           FROM(                                                                                                                                                  " +
							"               SELECT M.VAL_VALOR,                                                                                                                                " +
							"							        M.ID_EMPRESA,                                                                                                                  " +
							"							       (SELECT DISTINCT(V.DENOM_ACTUAL)                                                                                                " +
							"							        FROM DERCORP_BUSQUEDA_VIEW V                                                                                                   " +
							"							        WHERE V.ID_EMPRESA = M.ID_EMPRESA)AS EMPRESA                                                                                   " +
							"							        FROM DERCORP_ADD_CAMPO_VALOR_TAB M                                                                                             " +
							"							        WHERE M.VAL_VALOR IS NOT NULL                                                                                                  " +
							"							        AND ID_ADD_CAMPO = 551 ) AV                                                                                                    " +
							"				      ) ORDER BY 1 ";
			PreparedStatement psmt = con.prepareStatement(lstQuery);
			ResultSet rs = psmt.executeQuery();

			while(rs.next()){
				catalogElementV2 = new CatalogElementV2();
				catalogElementV2.setEmpresa(rs.getString(1));
				catalogElementV2.setIdEmpresa(rs.getInt(2));
				catalogElementV2.setValC4(rs.getString(3));
				catalogElementV2.setValValor(rs.getString(4));
				records.add(catalogElementV2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
    	return records;
    }
    
    
    /**
     * 
     */
    public static void reloadCatPersonas() {

		ConnectionWrapper connect = null;
		CallableStatement stmt = null;
		ResultSet set = null;

		try {

			connect = new ConnectionWrapper();

			stmt = connect.prepareCall(DERCORP_CATALOGS_PKG.RELOAD_CAT_PERSONAS_PR);

			stmt.setString(1,"");
			
			stmt.execute();

		} catch (Exception ex) {

			// TODO - Pendiente - Gestion de Excepciones
			ex.printStackTrace();

		} finally {

			ConnectionWrapper.closeAll(set, stmt, connect);
		}

	}
    
    /**
     * 
     */
    public static void reloadCatPersonasTotal() {

		ConnectionWrapper connect = null;
		CallableStatement stmt = null;
		ResultSet set = null;

		try {

			connect = new ConnectionWrapper();

			stmt = connect.prepareCall(DERCORP_CATALOGS_PKG.RELOAD_CAT_PERSONAS_TOTAL_PR);

			stmt.setString(1,"");
			
			stmt.execute();

		} catch (Exception ex) {

			// TODO - Pendiente - Gestion de Excepciones
			ex.printStackTrace();

		} finally {

			ConnectionWrapper.closeAll(set, stmt, connect);
		}

	}
    
    public static void copyDataToApoderadosWK(int idEmpresa){
    	ConnectionWrapper connect = null;
		CallableStatement stmt = null;
		ResultSet set = null;

		try {

			connect = new ConnectionWrapper();

			stmt = connect.prepareCall(DERCORP_APODERADOS_PKG.COPY_ESTRUCTURA_WK_TBL_PR);

			stmt.setInt(1,idEmpresa);
			
			stmt.execute();

		} catch (Exception ex) {

			// TODO - Pendiente - Gestion de Excepciones
			ex.printStackTrace();

		} finally {

			ConnectionWrapper.closeAll(set, stmt, connect);
		}

	}
    
    public static void copyDataToApoderadosFinal(int idEmpresa, 
												 String tsEscritura,
												 String tstTipoPoder,
												 String tstGrupoApoderado,
												 String tstNumOrden){
    	ConnectionWrapper connect = null;
		CallableStatement stmt = null;
		ResultSet set = null;

		try {

			connect = new ConnectionWrapper();

			stmt = connect.prepareCall(DERCORP_APODERADOS_PKG.COPY_ESTRUCTURA_FINAL_PR);

			stmt.setInt(1,idEmpresa);
			stmt.setString(2, tsEscritura);
			stmt.setString(3, tstTipoPoder);
			stmt.setString(4, tstGrupoApoderado);
			stmt.setString(5, tstNumOrden);
			
			
			stmt.execute();

		} catch (Exception ex) {

			// TODO - Pendiente - Gestion de Excepciones
			ex.printStackTrace();

		} finally {

			ConnectionWrapper.closeAll(set, stmt, connect);
		}

	}
    
}
