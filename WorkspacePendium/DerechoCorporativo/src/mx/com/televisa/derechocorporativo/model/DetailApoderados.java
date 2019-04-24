package mx.com.televisa.derechocorporativo.model;

import java.sql.*;
import java.util.*;

import org.apache.commons.lang3.StringUtils;

import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;
import mx.com.televisa.derechocorporativo.util.FacesUtils;


public class DetailApoderados {
	
	
	
	
	public String getRevocados(String pstIdEmpresa, String pstNumTipoPoder, String pstEscritura){
		String            lsSql               = "{CALL DERCORP_APODERADOS_PKG.GET_REVOCADOS_PR(?,?,?,?)}";
		 
		ConnectionWrapper puConnectionWrapper = null;
		Connection luConnection 			  = null;
		CallableStatement luCallableStatement = null;	
		String stLeyenda 					  = "";
		try {
				puConnectionWrapper = new ConnectionWrapper();
				luConnection        = puConnectionWrapper.getConnection();
				luCallableStatement = luConnection.prepareCall(lsSql);

				luCallableStatement.setInt(1,Integer.parseInt(pstIdEmpresa));
				luCallableStatement.setInt(2,Integer.parseInt(pstNumTipoPoder));
				luCallableStatement.setString(3, pstEscritura);
				luCallableStatement.registerOutParameter(4, java.sql.Types.VARCHAR);
				luCallableStatement.execute();
				stLeyenda = luCallableStatement.getString(4);

			} catch (Exception e) {
				e.printStackTrace();

			}finally {
				try {
					luCallableStatement.close();
					puConnectionWrapper.close();
					luConnection.close();

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		return stLeyenda;
	}
	
	public String getRevocadosFinal(String pstIdEmpresa, String pstNumTipoPoder, String pstEscritura){
		String            lsSql               = "{CALL DERCORP_APODERADOS_PKG.GET_REVOCADOS_FINAL_PR(?,?,?,?)}";
		 
		ConnectionWrapper puConnectionWrapper = null;
		Connection luConnection 			  = null;
		CallableStatement luCallableStatement = null;	
		String stLeyenda 					  = "";
		try {
				puConnectionWrapper = new ConnectionWrapper();
				luConnection        = puConnectionWrapper.getConnection();
				luCallableStatement = luConnection.prepareCall(lsSql);

				luCallableStatement.setInt(1,Integer.parseInt(pstIdEmpresa));
				luCallableStatement.setInt(2,Integer.parseInt(pstNumTipoPoder));
				luCallableStatement.setString(3, pstEscritura);
				luCallableStatement.registerOutParameter(4, java.sql.Types.VARCHAR);
				luCallableStatement.execute();
				stLeyenda = luCallableStatement.getString(4);

			} catch (Exception e) {
				e.printStackTrace();

			}finally {
				try {
					luCallableStatement.close();
					puConnectionWrapper.close();
					luConnection.close();

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		return stLeyenda;
	}
	
	/**
	 * MEtodo que trae revocados
	 * @param pstIdEmpresa
	 * @param pstNumTipoPoder
	 * @param pstEscritura
	 * @return
	 */
	public List<DetailApoderadosDTO> getApoderadosConRevocados(String pstIdEmpresa, String pstNumTipoPoder, String pstEscritura){
    	Connection con = null;
    	DetailApoderadosDTO detailApoderadosDTO;
    	List<DetailApoderadosDTO> records = new ArrayList<>();
    	
    	PreparedStatement psmt = null;
    	ResultSet rs = null;
    	
    	
    	try{
    		ConnectionWrapper connectionWrapper = new ConnectionWrapper();
    		con = connectionWrapper.getConnection();
    		/*String lstQuerie = 
    				"SELECT DISTINCT APO.ID_EMPRESA,"+
                    "APO.DES_ESCRITURA,"+
                    "APO.NUM_TIPO_PODER,"+ 
                    "APO.DES_GRUPO,"+
                    "DERCORP_APODERADOS_PKG.GET_APODERADOS(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) AS APODERADOS,"+
                    "DERCORP_APODERADOS_PKG.GET_DOMINIO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) AS ACTOS_DOMINIO,"+
                    "DERCORP_APODERADOS_PKG.GET_ADMINISTRACION(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) AS ACTOS_ADMINISTRACION,"+
                    "DERCORP_APODERADOS_PKG.GET_CREDITO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) AS TITULOS_CREDITO,"+
                    "DERCORP_APODERADOS_PKG.GET_PLEITO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) AS PLEITOS_COBRANZAS,"+
                    "DERCORP_APODERADOS_PKG.GET_ESPECIAL(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) AS PODERES_ESPECIALES "+
                    "FROM            DERCORP_APODERADOS_TAB APO "+
                    "WHERE	1=1 "+
                    "AND	APO.ID_EMPRESA      = ? "+
                    "AND    APO.NUM_TIPO_PODER  = ? "+
                    "AND    APO.DES_ESCRITURA = ? ORDER BY APO.DES_GRUPO"
    		;*/
    		
    		//ICL 25022016 Agrupar "grupos"
    		/*
    		String lstQuerie = 
    				"SELECT  " +
    				"DERCORP_APODERADOS_PKG.GET_GRUPO_UNION_REVOCADO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) AS GRUPO_UNION, "+
    				"DERCORP_APODERADOS_PKG.GET_DOMINIO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) AS ACTOS_DOMINIO, "+
    				"DERCORP_APODERADOS_PKG.GET_ADMINISTRACION(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) AS ACTOS_ADMINISTRACION, "+
    				"DERCORP_APODERADOS_PKG.GET_CREDITO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) AS TITULOS_CREDITO, "+
    				"DERCORP_APODERADOS_PKG.GET_PLEITO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) AS PLEITOS_COBRANZAS, "+
    				"DERCORP_APODERADOS_PKG.GET_ESPECIAL(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) AS PODERES_ESPECIALES, "+
    				"MAX(APO.DES_GRUPO) "+
    				"FROM    DERCORP_APODERADOS_WK_TAB APO "+
    				"WHERE	1=1 "+
    				"AND	APO.ID_EMPRESA      = ? "+
    				"AND    APO.NUM_TIPO_PODER  = ? "+
    				"AND    APO.DES_ESCRITURA = ? "+
    				"GROUP BY "+
    				"DERCORP_APODERADOS_PKG.GET_GRUPO_UNION_REVOCADO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO), "+
    				"DERCORP_APODERADOS_PKG.GET_DOMINIO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO),  "+
    				"DERCORP_APODERADOS_PKG.GET_ADMINISTRACION(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO), "+
    				"DERCORP_APODERADOS_PKG.GET_CREDITO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO), "+
    				"DERCORP_APODERADOS_PKG.GET_PLEITO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO), "+
    				"DERCORP_APODERADOS_PKG.GET_ESPECIAL(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) "
    				;
    		*/
    		//ECM 10 Mayo 2016 Apoderados
    		String lstQuerie = 
    				"SELECT  " +
    				"DERCORP_WK_APODERADOS_PKG.GET_GRUPO_UNION_REVOCADO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) AS GRUPO_UNION, "+
    				"DERCORP_WK_APODERADOS_PKG.GET_DOMINIO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) AS ACTOS_DOMINIO, "+
    				"DERCORP_WK_APODERADOS_PKG.GET_ADMINISTRACION(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) AS ACTOS_ADMINISTRACION, "+
    				"DERCORP_WK_APODERADOS_PKG.GET_CREDITO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) AS TITULOS_CREDITO, "+
    				"DERCORP_WK_APODERADOS_PKG.GET_PLEITO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) AS PLEITOS_COBRANZAS, "+
    				"DERCORP_WK_APODERADOS_PKG.GET_ESPECIAL(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) AS PODERES_ESPECIALES, "+
    				"MAX(APO.DES_GRUPO) "+
    				"FROM    DERCORP_APODERADOS_WK_TAB APO "+
    				"WHERE	1=1 "+
    				"AND	APO.ID_EMPRESA      = ? "+
    				"AND    APO.NUM_TIPO_PODER  = ? "+
    				"AND    APO.DES_ESCRITURA = ? "+
    				"GROUP BY "+
    				"DERCORP_WK_APODERADOS_PKG.GET_GRUPO_UNION_REVOCADO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO), "+
    				"DERCORP_WK_APODERADOS_PKG.GET_DOMINIO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO),  "+
    				"DERCORP_WK_APODERADOS_PKG.GET_ADMINISTRACION(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO), "+
    				"DERCORP_WK_APODERADOS_PKG.GET_CREDITO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO), "+
    				"DERCORP_WK_APODERADOS_PKG.GET_PLEITO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO), "+
    				"DERCORP_WK_APODERADOS_PKG.GET_ESPECIAL(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) \n"+
    				"order by 1 asc"
    				;

    		
			psmt = con.prepareStatement(lstQuerie);
			psmt.setString(1, pstIdEmpresa);
			psmt.setString(2, pstNumTipoPoder);
			psmt.setString(3, pstEscritura);
			rs = psmt.executeQuery();
			
			while(rs.next()){
				detailApoderadosDTO = new DetailApoderadosDTO();
				detailApoderadosDTO.setApoderados(rs.getString(1));
				detailApoderadosDTO.setActosDominio(rs.getString(2));
				detailApoderadosDTO.setActosAdministracion(rs.getString(3));
				detailApoderadosDTO.setTitulosCredito(rs.getString(4));
				detailApoderadosDTO.setPleitosCobranzas(rs.getString(5));
				detailApoderadosDTO.setPoderesEspeciales(rs.getString(6));
				records.add(detailApoderadosDTO);
			}
    	} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			ConnectionWrapper.closeAll(rs,psmt, con);
			
			/*try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}*/
		}
    	return records; 
	}

	public List<DetailApoderadosDTO> getApoderados(String pstIdEmpresa, String pstNumTipoPoder, String pstEscritura){
    	Connection con = null;
    	DetailApoderadosDTO detailApoderadosDTO;
    	List<DetailApoderadosDTO> records = new ArrayList<>();
    	
    	PreparedStatement psmt = null;
    	ResultSet rs = null;
    	
    	
    	try{
    		ConnectionWrapper connectionWrapper = new ConnectionWrapper();
    		con = connectionWrapper.getConnection();
    		/*String lstQuerie = 
    				"SELECT DISTINCT APO.ID_EMPRESA,"+
                    "APO.DES_ESCRITURA,"+
                    "APO.NUM_TIPO_PODER,"+ 
                    "APO.DES_GRUPO,"+
                    "DERCORP_APODERADOS_PKG.GET_APODERADOS(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) AS APODERADOS,"+
                    "DERCORP_APODERADOS_PKG.GET_DOMINIO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) AS ACTOS_DOMINIO,"+
                    "DERCORP_APODERADOS_PKG.GET_ADMINISTRACION(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) AS ACTOS_ADMINISTRACION,"+
                    "DERCORP_APODERADOS_PKG.GET_CREDITO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) AS TITULOS_CREDITO,"+
                    "DERCORP_APODERADOS_PKG.GET_PLEITO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) AS PLEITOS_COBRANZAS,"+
                    "DERCORP_APODERADOS_PKG.GET_ESPECIAL(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) AS PODERES_ESPECIALES "+
                    "FROM            DERCORP_APODERADOS_TAB APO "+
                    "WHERE	1=1 "+
                    "AND	APO.ID_EMPRESA      = ? "+
                    "AND    APO.NUM_TIPO_PODER  = ? "+
                    "AND    APO.DES_ESCRITURA = ? ORDER BY APO.DES_GRUPO"
    		;*/
    		
    		//ICL 25022016 Agrupar "grupos"
    		String lstQuerie = 
    				"SELECT  " +
    				"DERCORP_APODERADOS_PKG.GET_GRUPO_UNION(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) AS GRUPO_UNION, "+
    				"DERCORP_APODERADOS_PKG.GET_DOMINIO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) AS ACTOS_DOMINIO, "+
    				"DERCORP_APODERADOS_PKG.GET_ADMINISTRACION(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) AS ACTOS_ADMINISTRACION, "+
    				"DERCORP_APODERADOS_PKG.GET_CREDITO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) AS TITULOS_CREDITO, "+
    				"DERCORP_APODERADOS_PKG.GET_PLEITO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) AS PLEITOS_COBRANZAS, "+
    				"DERCORP_APODERADOS_PKG.GET_ESPECIAL(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) AS PODERES_ESPECIALES, "+
    				"MAX(APO.DES_GRUPO) "+
    				"FROM            DERCORP_APODERADOS_TAB APO "+
    				"WHERE	1=1 "+
    				"AND	APO.ID_EMPRESA      = ? "+
    				"AND    APO.NUM_TIPO_PODER  = ? "+
    				"AND    APO.DES_ESCRITURA = ? "+
    				"GROUP BY "+
    				"DERCORP_APODERADOS_PKG.GET_GRUPO_UNION(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO), "+
    				"DERCORP_APODERADOS_PKG.GET_DOMINIO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO),  "+
    				"DERCORP_APODERADOS_PKG.GET_ADMINISTRACION(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO), "+
    				"DERCORP_APODERADOS_PKG.GET_CREDITO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO), "+
    				"DERCORP_APODERADOS_PKG.GET_PLEITO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO), "+
    				"DERCORP_APODERADOS_PKG.GET_ESPECIAL(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO)\n "+
    				"order by 1 asc"
    				;
    		
			psmt = con.prepareStatement(lstQuerie);
			psmt.setString(1, pstIdEmpresa);
			psmt.setString(2, pstNumTipoPoder);
			psmt.setString(3, pstEscritura);
			rs = psmt.executeQuery();
			
			while(rs.next()){
				detailApoderadosDTO = new DetailApoderadosDTO();
				detailApoderadosDTO.setApoderados(rs.getString(1));
				detailApoderadosDTO.setActosDominio(rs.getString(2));
				detailApoderadosDTO.setActosAdministracion(rs.getString(3));
				detailApoderadosDTO.setTitulosCredito(rs.getString(4));
				detailApoderadosDTO.setPleitosCobranzas(rs.getString(5));
				detailApoderadosDTO.setPoderesEspeciales(rs.getString(6));
				records.add(detailApoderadosDTO);
			}
    	} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			ConnectionWrapper.closeAll(rs,psmt, con);
			
			/*try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}*/
		}
    	return records; 
	}
	
	public List<DetailApoderadosDTO> getApoderadosReporte(String pstIdEmpresa, String pstNumTipoPoder, String pstEscritura,String pstPoder){
    	Connection con = null;
    	DetailApoderadosDTO detailApoderadosDTO;
    	List<DetailApoderadosDTO> records = new ArrayList<>();
    	
    	PreparedStatement psmt = null;
    	ResultSet rs = null;
    	String[] arrayPoderes = pstPoder.split(",");
    	String lstDominio 	= "";
    	String lstAdmin 	= "";
    	String lstCredito 	= "";
    	String lstPleito 	= "";
    	String lstEspecial 	= "";
    	String lstGeneral 	= "";
    	for(String str : arrayPoderes){
    		if(str.equals("33")){
    			lstDominio = str;
    		}else if(str.equals("34")){
    			lstAdmin = str;
    		}else if(str.equals("35")){
    			lstCredito = str;
    		}else if(str.equals("36")){
    			lstPleito = str;
    		}else if(str.equals("37")){
    			lstEspecial = str;
    		}
    	}
    	
    	try{
    		ConnectionWrapper connectionWrapper = new ConnectionWrapper();
    		con = connectionWrapper.getConnection();
    		StringBuilder stb = new StringBuilder();
    		stb.append("SELECT ");
    		//if(lstGeneral.equals("0")){
    			stb.append("DERCORP_APODERADOS_PKG.GET_GRUPO_UNION(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) AS GRUPO_UNION,\n ");
    		//}
			if(lstDominio.equals("33")){
				stb.append("DERCORP_APODERADOS_PKG.GET_DOMINIO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) AS ACTOS_DOMINIO,\n ");	
			}
			if(lstAdmin.equals("34")){
				stb.append("DERCORP_APODERADOS_PKG.GET_ADMINISTRACION(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) AS ACTOS_ADMINISTRACION,\n ");	
			}
			if(lstCredito.equals("35")){
				stb.append("DERCORP_APODERADOS_PKG.GET_CREDITO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) AS TITULOS_CREDITO,\n ");	
			}
			if(lstPleito.equals("36")){
				stb.append("DERCORP_APODERADOS_PKG.GET_PLEITO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) AS PLEITOS_COBRANZAS,\n ");	
			}
			if(lstEspecial.equals("37")){
				stb.append("DERCORP_APODERADOS_PKG.GET_ESPECIAL(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) AS PODERES_ESPECIALES,\n ");	
			}
			stb.append("MAX(APO.DES_GRUPO) ");
			stb.append("FROM            DERCORP_APODERADOS_TAB APO ");
			stb.append("WHERE	1=1 ");
			stb.append("AND	APO.ID_EMPRESA      = ? ");
			stb.append("AND    APO.NUM_TIPO_PODER  = ? ");
			stb.append("AND    APO.DES_ESCRITURA = ? ");
			
			//if(lstGeneral.equals("0")){
				stb.append("AND DERCORP_APODERADOS_PKG.GET_GRUPO_UNION(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) <> '<br><br>'\n");
			//}
			if(lstDominio.equals("33")){
				stb.append("AND DERCORP_APODERADOS_PKG.GET_DOMINIO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) <> '<br><br>'\n");	
			}
			if(lstAdmin.equals("34")){
				stb.append("AND DERCORP_APODERADOS_PKG.GET_ADMINISTRACION(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) <> '<br><br>'\n");	
			}
			if(lstCredito.equals("35")){
				stb.append("AND DERCORP_APODERADOS_PKG.GET_CREDITO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) <> '<br><br>'\n");	
			}
			if(lstPleito.equals("36")){
				stb.append("AND DERCORP_APODERADOS_PKG.GET_PLEITO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) <> '<br><br>'\n");	
			}
			if(lstEspecial.equals("37")){
				stb.append("AND DERCORP_APODERADOS_PKG.GET_ESPECIAL(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) <> '<br><br>'\n");	
			}
			stb.append("GROUP BY \n");
			//if(lstGeneral.equals("0")){
				stb.append("DERCORP_APODERADOS_PKG.GET_GRUPO_UNION(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO), \n");
			//}
			if(lstDominio.equals("33")){
				stb.append("DERCORP_APODERADOS_PKG.GET_DOMINIO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO), \n ");	
			}
			if(lstAdmin.equals("34")){
				stb.append("DERCORP_APODERADOS_PKG.GET_ADMINISTRACION(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO),\n ");	
			}
			if(lstCredito.equals("35")){
				stb.append("DERCORP_APODERADOS_PKG.GET_CREDITO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO),\n ");	
			}
			if(lstPleito.equals("36")){
				stb.append("DERCORP_APODERADOS_PKG.GET_PLEITO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO), \n");	
			}
			if(lstEspecial.equals("37")){
				stb.append("DERCORP_APODERADOS_PKG.GET_ESPECIAL(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO),\n ");
			}
			stb.deleteCharAt(stb.lastIndexOf(","));
			
			stb.append(" order by 1 asc");
			

    		//ICL 25022016 Agrupar "grupos"
    		String lstQuerie = stb.toString();
    		
			psmt = con.prepareStatement(lstQuerie);
			psmt.setString(1, pstIdEmpresa);
			psmt.setString(2, pstNumTipoPoder);
			psmt.setString(3, pstEscritura);
			rs = psmt.executeQuery();
			
			while(rs.next()){
				detailApoderadosDTO = new DetailApoderadosDTO();
		
					detailApoderadosDTO.setApoderados(rs.getString("GRUPO_UNION"));
				
				if(lstDominio.equals("33")){
					detailApoderadosDTO.setActosDominio(rs.getString("ACTOS_DOMINIO"));
				}
				if(lstAdmin.equals("34")){
					detailApoderadosDTO.setActosAdministracion(rs.getString("ACTOS_ADMINISTRACION"));
				}
				if(lstCredito.equals("35")){
					detailApoderadosDTO.setTitulosCredito(rs.getString("TITULOS_CREDITO"));
				}
				if(lstPleito.equals("36")){
					detailApoderadosDTO.setPleitosCobranzas(rs.getString("PLEITOS_COBRANZAS"));
				}
				if(lstEspecial.equals("37")){
					detailApoderadosDTO.setPoderesEspeciales(rs.getString("PODERES_ESPECIALES"));
				}
				records.add(detailApoderadosDTO);
			}
    	} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			ConnectionWrapper.closeAll(rs,psmt, con);
		}
    	return records; 
	}
	
	
	public List<DetailApoderadosDTO> getApoderadosReporteRev(String pstIdEmpresa, String pstNumTipoPoder, String pstEscritura,String pstPoder){
    	Connection con = null;
    	DetailApoderadosDTO detailApoderadosDTO;
    	List<DetailApoderadosDTO> records = new ArrayList<>();
    	
    	PreparedStatement psmt = null;
    	ResultSet rs = null;
    	String[] arrayPoderes = pstPoder.split(",");
    	String lstDominio 	= "";
    	String lstAdmin 	= "";
    	String lstCredito 	= "";
    	String lstPleito 	= "";
    	String lstEspecial 	= "";
    	String lstGeneral 	= "";
    	for(String str : arrayPoderes){
    		if(str.equals("33")){
    			lstDominio = str;
    		}else if(str.equals("34")){
    			lstAdmin = str;
    		}else if(str.equals("35")){
    			lstCredito = str;
    		}else if(str.equals("36")){
    			lstPleito = str;
    		}else if(str.equals("37")){
    			lstEspecial = str;
    		}
    	}
    	
    	try{
    		ConnectionWrapper connectionWrapper = new ConnectionWrapper();
    		con = connectionWrapper.getConnection();
    		StringBuilder stb = new StringBuilder();
    		stb.append("SELECT ");
    		//if(lstGeneral.equals("0")){
    			stb.append("DERCORP_APODERADOS_PKG.GET_GRUPO_UNION_REVOCADO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) AS GRUPO_UNION,\n ");
    		//}
			if(lstDominio.equals("33")){
				stb.append("DERCORP_APODERADOS_PKG.GET_DOMINIO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) AS ACTOS_DOMINIO,\n ");	
			}
			if(lstAdmin.equals("34")){
				stb.append("DERCORP_APODERADOS_PKG.GET_ADMINISTRACION(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) AS ACTOS_ADMINISTRACION,\n ");	
			}
			if(lstCredito.equals("35")){
				stb.append("DERCORP_APODERADOS_PKG.GET_CREDITO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) AS TITULOS_CREDITO,\n ");	
			}
			if(lstPleito.equals("36")){
				stb.append("DERCORP_APODERADOS_PKG.GET_PLEITO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) AS PLEITOS_COBRANZAS,\n ");	
			}
			if(lstEspecial.equals("37")){
				stb.append("DERCORP_APODERADOS_PKG.GET_ESPECIAL(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) AS PODERES_ESPECIALES,\n ");	
			}
			stb.append("MAX(APO.DES_GRUPO) ");
			stb.append("FROM            DERCORP_APODERADOS_TAB APO ");
			stb.append("WHERE	1=1 ");
			stb.append("AND	APO.ID_EMPRESA      = ? ");
			stb.append("AND    APO.NUM_TIPO_PODER  = ? ");
			stb.append("AND    APO.DES_ESCRITURA = ? ");
			
			//if(lstGeneral.equals("0")){
				stb.append("AND DERCORP_APODERADOS_PKG.GET_GRUPO_UNION_REVOCADO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) <> '<br><br>'\n");
			//}
			if(lstDominio.equals("33")){
				stb.append("AND DERCORP_APODERADOS_PKG.GET_DOMINIO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) <> '<br><br>'\n");	
			}
			if(lstAdmin.equals("34")){
				stb.append("AND DERCORP_APODERADOS_PKG.GET_ADMINISTRACION(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) <> '<br><br>'\n");	
			}
			if(lstCredito.equals("35")){
				stb.append("AND DERCORP_APODERADOS_PKG.GET_CREDITO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) <> '<br><br>'\n");	
			}
			if(lstPleito.equals("36")){
				stb.append("AND DERCORP_APODERADOS_PKG.GET_PLEITO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) <> '<br><br>'\n");	
			}
			if(lstEspecial.equals("37")){
				stb.append("AND DERCORP_APODERADOS_PKG.GET_ESPECIAL(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) <> '<br><br>'\n");	
			}
			stb.append("GROUP BY \n");
			//if(lstGeneral.equals("0")){
				stb.append("DERCORP_APODERADOS_PKG.GET_GRUPO_UNION_REVOCADO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO), \n");
			//}
			if(lstDominio.equals("33")){
				stb.append("DERCORP_APODERADOS_PKG.GET_DOMINIO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO), \n ");	
			}
			if(lstAdmin.equals("34")){
				stb.append("DERCORP_APODERADOS_PKG.GET_ADMINISTRACION(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO),\n ");	
			}
			if(lstCredito.equals("35")){
				stb.append("DERCORP_APODERADOS_PKG.GET_CREDITO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO),\n ");	
			}
			if(lstPleito.equals("36")){
				stb.append("DERCORP_APODERADOS_PKG.GET_PLEITO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO), \n");	
			}
			if(lstEspecial.equals("37")){
				stb.append("DERCORP_APODERADOS_PKG.GET_ESPECIAL(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO),\n ");
			}
			stb.deleteCharAt(stb.lastIndexOf(","));
			
			stb.append(" order by 1 asc");
			

    		//ICL 25022016 Agrupar "grupos"
    		String lstQuerie = stb.toString();
    		
			psmt = con.prepareStatement(lstQuerie);
			psmt.setString(1, pstIdEmpresa);
			psmt.setString(2, pstNumTipoPoder);
			psmt.setString(3, pstEscritura);
			rs = psmt.executeQuery();
			
			while(rs.next()){
				detailApoderadosDTO = new DetailApoderadosDTO();
		
					detailApoderadosDTO.setApoderados(rs.getString("GRUPO_UNION"));
				
				if(lstDominio.equals("33")){
					detailApoderadosDTO.setActosDominio(rs.getString("ACTOS_DOMINIO"));
				}
				if(lstAdmin.equals("34")){
					detailApoderadosDTO.setActosAdministracion(rs.getString("ACTOS_ADMINISTRACION"));
				}
				if(lstCredito.equals("35")){
					detailApoderadosDTO.setTitulosCredito(rs.getString("TITULOS_CREDITO"));
				}
				if(lstPleito.equals("36")){
					detailApoderadosDTO.setPleitosCobranzas(rs.getString("PLEITOS_COBRANZAS"));
				}
				if(lstEspecial.equals("37")){
					detailApoderadosDTO.setPoderesEspeciales(rs.getString("PODERES_ESPECIALES"));
				}
				records.add(detailApoderadosDTO);
			}
    	} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			ConnectionWrapper.closeAll(rs,psmt, con);
		}
    	return records; 
	}
	
	
	public List<DetailApoderadosDTO> getApoderadosReporteDefault(String pstIdEmpresa, String pstNumTipoPoder, String pstEscritura,String pstPoder){
    	Connection con = null;
    	DetailApoderadosDTO detailApoderadosDTO;
    	List<DetailApoderadosDTO> records = new ArrayList<>();
    	
    	PreparedStatement psmt = null;
    	ResultSet rs = null;
    	/*String[] arrayPoderes = pstPoder.split(",");
    	String lstDominio 	= "";
    	String lstAdmin 	= "";
    	String lstCredito 	= "";
    	String lstPleito 	= "";
    	String lstEspecial 	= "";
    	String lstGeneral 	= "";*/
    	
    	/*for(String str : arrayPoderes){
    		if(str.equals("33")){
    			lstDominio = str;
    		}else if(str.equals("34")){
    			lstAdmin = str;
    		}else if(str.equals("35")){
    			lstCredito = str;
    		}else if(str.equals("36")){
    			lstPleito = str;
    		}else if(str.equals("37")){
    			lstEspecial = str;
    		}
    	}*/
    	
    	try{
    		ConnectionWrapper connectionWrapper = new ConnectionWrapper();
    		con = connectionWrapper.getConnection();
    		StringBuilder stb = new StringBuilder();
    		stb.append("SELECT ");
    		//if(lstGeneral.equals("0")){
    			stb.append("DERCORP_APODERADOS_PKG.GET_GRUPO_UNION(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) AS GRUPO_UNION,\n ");
    		//}
			//if(lstDominio.equals("33")){
				stb.append("DERCORP_APODERADOS_PKG.GET_DOMINIO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) AS ACTOS_DOMINIO,\n ");	
			//}
			//if(lstAdmin.equals("34")){
				stb.append("DERCORP_APODERADOS_PKG.GET_ADMINISTRACION(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) AS ACTOS_ADMINISTRACION,\n ");	
			//}
			//if(lstCredito.equals("35")){
				stb.append("DERCORP_APODERADOS_PKG.GET_CREDITO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) AS TITULOS_CREDITO,\n ");	
			//}
			//if(lstPleito.equals("36")){
				stb.append("DERCORP_APODERADOS_PKG.GET_PLEITO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) AS PLEITOS_COBRANZAS,\n ");	
			//}
			//if(lstEspecial.equals("37")){
				stb.append("DERCORP_APODERADOS_PKG.GET_ESPECIAL(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) AS PODERES_ESPECIALES,\n ");	
			//}
			stb.append("MAX(APO.DES_GRUPO) ");
			stb.append("FROM            DERCORP_APODERADOS_TAB APO ");
			stb.append("WHERE	1=1 ");
			stb.append("AND	APO.ID_EMPRESA      = ? ");
			stb.append("AND    APO.NUM_TIPO_PODER  = ? ");
			stb.append("AND    APO.DES_ESCRITURA = ? ");
			
			//if(lstGeneral.equals("0")){
				stb.append("AND DERCORP_APODERADOS_PKG.GET_GRUPO_UNION(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) <> '<br><br>'\n");
			//}
			/*if(lstDominio.equals("33")){
				stb.append("AND DERCORP_APODERADOS_PKG.GET_DOMINIO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) <> '<br><br>'\n");	
			}
			if(lstAdmin.equals("34")){
				stb.append("AND DERCORP_APODERADOS_PKG.GET_ADMINISTRACION(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) <> '<br><br>'\n");	
			}
			if(lstCredito.equals("35")){
				stb.append("AND DERCORP_APODERADOS_PKG.GET_CREDITO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) <> '<br><br>'\n");	
			}
			if(lstPleito.equals("36")){
				stb.append("AND DERCORP_APODERADOS_PKG.GET_PLEITO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) <> '<br><br>'\n");	
			}
			if(lstEspecial.equals("37")){
				stb.append("AND DERCORP_APODERADOS_PKG.GET_ESPECIAL(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) <> '<br><br>'\n");	
			}*/
			stb.append("GROUP BY \n");
			//if(lstGeneral.equals("0")){
				stb.append("DERCORP_APODERADOS_PKG.GET_GRUPO_UNION(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO), \n");
			//}
			//if(lstDominio.equals("33")){
				stb.append("DERCORP_APODERADOS_PKG.GET_DOMINIO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO), \n ");	
			//}
			//if(lstAdmin.equals("34")){
				stb.append("DERCORP_APODERADOS_PKG.GET_ADMINISTRACION(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO),\n ");	
			//}
			//if(lstCredito.equals("35")){
				stb.append("DERCORP_APODERADOS_PKG.GET_CREDITO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO),\n ");	
			//}
			//if(lstPleito.equals("36")){
				stb.append("DERCORP_APODERADOS_PKG.GET_PLEITO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO), \n");	
			//}
			//if(lstEspecial.equals("37")){
				stb.append("DERCORP_APODERADOS_PKG.GET_ESPECIAL(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO)\n ");
			//}
			//stb.deleteCharAt(stb.lastIndexOf(","));
			
			stb.append(" order by 1 asc");
			

    		//ICL 25022016 Agrupar "grupos"
    		String lstQuerie = stb.toString();
    		
			psmt = con.prepareStatement(lstQuerie);
			psmt.setString(1, pstIdEmpresa);
			psmt.setString(2, pstNumTipoPoder);
			psmt.setString(3, pstEscritura);
			rs = psmt.executeQuery();
			
			while(rs.next()){
				detailApoderadosDTO = new DetailApoderadosDTO();
		
					detailApoderadosDTO.setApoderados(rs.getString("GRUPO_UNION"));
				
				//if(lstDominio.equals("33")){
					detailApoderadosDTO.setActosDominio(rs.getString("ACTOS_DOMINIO"));
				//}
				//if(lstAdmin.equals("34")){
					detailApoderadosDTO.setActosAdministracion(rs.getString("ACTOS_ADMINISTRACION"));
				//}
				//if(lstCredito.equals("35")){
					detailApoderadosDTO.setTitulosCredito(rs.getString("TITULOS_CREDITO"));
				//}
				//if(lstPleito.equals("36")){
					detailApoderadosDTO.setPleitosCobranzas(rs.getString("PLEITOS_COBRANZAS"));
				//}
				//if(lstEspecial.equals("37")){
					detailApoderadosDTO.setPoderesEspeciales(rs.getString("PODERES_ESPECIALES"));
				//}
				records.add(detailApoderadosDTO);
			}
    	} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			ConnectionWrapper.closeAll(rs,psmt, con);
		}
    	return records; 
	}
	
	public List<DetailApoderadosDTO> getApoderadosReporteDefaultRev(String pstIdEmpresa, String pstNumTipoPoder, String pstEscritura,String pstPoder){
    	Connection con = null;
    	DetailApoderadosDTO detailApoderadosDTO;
    	List<DetailApoderadosDTO> records = new ArrayList<>();
    	
    	PreparedStatement psmt = null;
    	ResultSet rs = null;
    	/*String[] arrayPoderes = pstPoder.split(",");
    	String lstDominio 	= "";
    	String lstAdmin 	= "";
    	String lstCredito 	= "";
    	String lstPleito 	= "";
    	String lstEspecial 	= "";
    	String lstGeneral 	= "";*/
    	
    	/*for(String str : arrayPoderes){
    		if(str.equals("33")){
    			lstDominio = str;
    		}else if(str.equals("34")){
    			lstAdmin = str;
    		}else if(str.equals("35")){
    			lstCredito = str;
    		}else if(str.equals("36")){
    			lstPleito = str;
    		}else if(str.equals("37")){
    			lstEspecial = str;
    		}
    	}*/
    	
    	try{
    		ConnectionWrapper connectionWrapper = new ConnectionWrapper();
    		con = connectionWrapper.getConnection();
    		StringBuilder stb = new StringBuilder();
    		stb.append("SELECT ");
    		//if(lstGeneral.equals("0")){
    			stb.append("DERCORP_APODERADOS_PKG.GET_GRUPO_UNION_REVOCADO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) AS GRUPO_UNION,\n ");
    		//}
			//if(lstDominio.equals("33")){
				stb.append("DERCORP_APODERADOS_PKG.GET_DOMINIO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) AS ACTOS_DOMINIO,\n ");	
			//}
			//if(lstAdmin.equals("34")){
				stb.append("DERCORP_APODERADOS_PKG.GET_ADMINISTRACION(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) AS ACTOS_ADMINISTRACION,\n ");	
			//}
			//if(lstCredito.equals("35")){
				stb.append("DERCORP_APODERADOS_PKG.GET_CREDITO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) AS TITULOS_CREDITO,\n ");	
			//}
			//if(lstPleito.equals("36")){
				stb.append("DERCORP_APODERADOS_PKG.GET_PLEITO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) AS PLEITOS_COBRANZAS,\n ");	
			//}
			//if(lstEspecial.equals("37")){
				stb.append("DERCORP_APODERADOS_PKG.GET_ESPECIAL(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) AS PODERES_ESPECIALES,\n ");	
			//}
			stb.append("MAX(APO.DES_GRUPO) ");
			stb.append("FROM            DERCORP_APODERADOS_TAB APO ");
			stb.append("WHERE	1=1 ");
			stb.append("AND	APO.ID_EMPRESA      = ? ");
			stb.append("AND    APO.NUM_TIPO_PODER  = ? ");
			stb.append("AND    APO.DES_ESCRITURA = ? ");
			
			//if(lstGeneral.equals("0")){
				stb.append("AND DERCORP_APODERADOS_PKG.GET_GRUPO_UNION_REVOCADO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) <> '<br><br>'\n");
			//}
			/*if(lstDominio.equals("33")){
				stb.append("AND DERCORP_APODERADOS_PKG.GET_DOMINIO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) <> '<br><br>'\n");	
			}
			if(lstAdmin.equals("34")){
				stb.append("AND DERCORP_APODERADOS_PKG.GET_ADMINISTRACION(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) <> '<br><br>'\n");	
			}
			if(lstCredito.equals("35")){
				stb.append("AND DERCORP_APODERADOS_PKG.GET_CREDITO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) <> '<br><br>'\n");	
			}
			if(lstPleito.equals("36")){
				stb.append("AND DERCORP_APODERADOS_PKG.GET_PLEITO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) <> '<br><br>'\n");	
			}
			if(lstEspecial.equals("37")){
				stb.append("AND DERCORP_APODERADOS_PKG.GET_ESPECIAL(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) <> '<br><br>'\n");	
			}*/
			stb.append("GROUP BY \n");
			//if(lstGeneral.equals("0")){
				stb.append("DERCORP_APODERADOS_PKG.GET_GRUPO_UNION_REVOCADO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO), \n");
			//}
			//if(lstDominio.equals("33")){
				stb.append("DERCORP_APODERADOS_PKG.GET_DOMINIO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO), \n ");	
			//}
			//if(lstAdmin.equals("34")){
				stb.append("DERCORP_APODERADOS_PKG.GET_ADMINISTRACION(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO),\n ");	
			//}
			//if(lstCredito.equals("35")){
				stb.append("DERCORP_APODERADOS_PKG.GET_CREDITO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO),\n ");	
			//}
			//if(lstPleito.equals("36")){
				stb.append("DERCORP_APODERADOS_PKG.GET_PLEITO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO), \n");	
			//}
			//if(lstEspecial.equals("37")){
				stb.append("DERCORP_APODERADOS_PKG.GET_ESPECIAL(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO)\n ");
			//}
			//stb.deleteCharAt(stb.lastIndexOf(","));
			
			stb.append(" order by 1 asc");
			

    		//ICL 25022016 Agrupar "grupos"
    		String lstQuerie = stb.toString();
    		
			psmt = con.prepareStatement(lstQuerie);
			psmt.setString(1, pstIdEmpresa);
			psmt.setString(2, pstNumTipoPoder);
			psmt.setString(3, pstEscritura);
			rs = psmt.executeQuery();
			
			while(rs.next()){
				detailApoderadosDTO = new DetailApoderadosDTO();
		
					detailApoderadosDTO.setApoderados(rs.getString("GRUPO_UNION"));
				
				//if(lstDominio.equals("33")){
					detailApoderadosDTO.setActosDominio(rs.getString("ACTOS_DOMINIO"));
				//}
				//if(lstAdmin.equals("34")){
					detailApoderadosDTO.setActosAdministracion(rs.getString("ACTOS_ADMINISTRACION"));
				//}
				//if(lstCredito.equals("35")){
					detailApoderadosDTO.setTitulosCredito(rs.getString("TITULOS_CREDITO"));
				//}
				//if(lstPleito.equals("36")){
					detailApoderadosDTO.setPleitosCobranzas(rs.getString("PLEITOS_COBRANZAS"));
				//}
				//if(lstEspecial.equals("37")){
					detailApoderadosDTO.setPoderesEspeciales(rs.getString("PODERES_ESPECIALES"));
				//}
				records.add(detailApoderadosDTO);
			}
    	} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			ConnectionWrapper.closeAll(rs,psmt, con);
		}
    	return records; 
	}
	
	
	public List<DetailApoderadosDTO> getApoderadosEsp(String pstIdEmpresa, String pstNumTipoPoder, String pstEscritura){
    	Connection con = null;
    	DetailApoderadosDTO detailApoderadosDTO;
    	List<DetailApoderadosDTO> records = new ArrayList<>();
    	PreparedStatement psmt = null;
    	ResultSet rs = null;
    	try{
    		ConnectionWrapper connectionWrapper = new ConnectionWrapper();
    		con = connectionWrapper.getConnection();
    		/*String lstQuerie = 
    				"SELECT DISTINCT APO.ID_EMPRESA,"+
                    "APO.DES_ESCRITURA,"+
                    "APO.NUM_TIPO_PODER,"+ 
                    "APO.DES_GRUPO,"+
                    "DERCORP_APODERADOS_PKG.GET_APODERADOS(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) AS APODERADOS,"+
                    "DERCORP_APODERADOS_PKG.GET_DOMINIO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) AS ACTOS_DOMINIO,"+
                    "DERCORP_APODERADOS_PKG.GET_ADMINISTRACION(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) AS ACTOS_ADMINISTRACION,"+
                    "DERCORP_APODERADOS_PKG.GET_CREDITO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) AS TITULOS_CREDITO,"+
                    "DERCORP_APODERADOS_PKG.GET_PLEITO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) AS PLEITOS_COBRANZAS,"+
                    "DERCORP_APODERADOS_PKG.GET_ESPECIAL(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) AS PODERES_ESPECIALES "+
                    "FROM            DERCORP_APODERADOS_TAB APO "+
                    "WHERE	1=1 "+
                    "AND	APO.ID_EMPRESA      = ? "+
                    "AND    APO.NUM_TIPO_PODER  = ? "+
                    "AND    APO.DES_ESCRITURA = ?"
    		;*/
    		
    		//ICL 25022016 Agrupar "grupos"
    		String lstQuerie = 
    				"SELECT  " +
    				"DERCORP_APODERADOS_PKG.GET_GRUPO_UNION(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) AS GRUPO_UNION, "+
    				"DERCORP_APODERADOS_PKG.GET_DOMINIO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) AS ACTOS_DOMINIO, "+
    				"DERCORP_APODERADOS_PKG.GET_ADMINISTRACION(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) AS ACTOS_ADMINISTRACION, "+
    				"DERCORP_APODERADOS_PKG.GET_CREDITO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) AS TITULOS_CREDITO, "+
    				"DERCORP_APODERADOS_PKG.GET_PLEITO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) AS PLEITOS_COBRANZAS, "+
    				"DERCORP_APODERADOS_PKG.GET_ESPECIAL(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) AS PODERES_ESPECIALES, "+
    				"MAX(APO.DES_GRUPO) "+
    				"FROM            DERCORP_APODERADOS_TAB APO "+
    				"WHERE	1=1 "+
    				"AND	APO.ID_EMPRESA      = ? "+
    				"AND    APO.NUM_TIPO_PODER  = ? "+
    				"AND    APO.DES_ESCRITURA = ? "+
    				"GROUP BY "+
    				"DERCORP_APODERADOS_PKG.GET_GRUPO_UNION(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO), "+
    				"DERCORP_APODERADOS_PKG.GET_DOMINIO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO),  "+
    				"DERCORP_APODERADOS_PKG.GET_ADMINISTRACION(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO), "+
    				"DERCORP_APODERADOS_PKG.GET_CREDITO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO), "+
    				"DERCORP_APODERADOS_PKG.GET_PLEITO(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO), "+
    				"DERCORP_APODERADOS_PKG.GET_ESPECIAL(APO.ID_EMPRESA,APO.NUM_TIPO_PODER,APO.DES_ESCRITURA,APO.DES_GRUPO) "
    				;
    		
			psmt = con.prepareStatement(lstQuerie);
			psmt.setString(1, pstIdEmpresa);
			psmt.setString(2, pstNumTipoPoder);
			psmt.setString(3, pstEscritura);
			rs = psmt.executeQuery();
			
			while(rs.next()){
				detailApoderadosDTO = new DetailApoderadosDTO();
				detailApoderadosDTO.setApoderados(rs.getString(1));
				detailApoderadosDTO.setActosDominio(rs.getString(2));
				detailApoderadosDTO.setActosAdministracion(rs.getString(3));
				detailApoderadosDTO.setTitulosCredito(rs.getString(4));
				detailApoderadosDTO.setPleitosCobranzas(rs.getString(5));
				detailApoderadosDTO.setPoderesEspeciales(rs.getString(6));
				records.add(detailApoderadosDTO);
			}
    	} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//try {
				ConnectionWrapper.closeAll(rs, psmt, con);
				//con.close();
			//} catch (SQLException e) {
			//	e.printStackTrace();
			//}
		}
    	return records; 
	}

	
	
	public static int getCountApoderados(String pstIdEmpresa, String pstNumTipoPoder, String pstEscritura){
    	
		Connection con = null;
    	ResultSet rs = null;
    	PreparedStatement psmt = null;
    	
    	try{
    		ConnectionWrapper connectionWrapper = new ConnectionWrapper();
    		con = connectionWrapper.getConnection();
    		
    		String lstQuerie = 
    				"SELECT count(*) NUM_RECORDS "+
                    "FROM   DERCORP_APODERADOS_TAB APO "+
                    "WHERE	1=1 "+
                    "AND	APO.ID_EMPRESA      = ? "+
                    "AND    APO.NUM_TIPO_PODER  = ? "+
                    "AND    APO.DES_ESCRITURA = ?";
    		
			psmt = con.prepareStatement(lstQuerie);
			psmt.setString(1, pstIdEmpresa);
			psmt.setString(2, pstNumTipoPoder);
			psmt.setString(3, pstEscritura);
			
			rs = psmt.executeQuery();
			rs.next();
			
			return rs.getInt("NUM_RECORDS");
			
    	} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			ConnectionWrapper.closeAll(rs, psmt, con);
		}
    	return 0; 
	}
	
	
}
