
package mx.kaz.obras.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.jfree.util.Log;

import mx.kaz.model.ConectionWrapper;
import mx.kaz.obras.beans.EmpresasObrasBean;
import mx.kaz.obras.beans.ObrasBean;

/**
 * @author Argumel
 */
public class ObrasDAO {
	
	Connection con;
	ConectionWrapper conectionWrapper;
	private org.apache.log4j.Logger logger = Logger.getLogger(ObrasDAO.class);
	List<ObrasBean> listObras;
	
	public ObrasDAO() {
		conectionWrapper = new ConectionWrapper();
	}
	
	public List<ObrasBean> obtenObras(int puerto){
		ObrasBean obrasBean;
		listObras = new ArrayList<>();
		PreparedStatement psmt 	= null;
		ResultSet 		  rs	= null;
		String sql = "SELECT id_builds,\n" +
				"    id_port,\n" +
				"    build_description,\n" +
				"    id_type_procedure,\n" +
				"(SELECT name_procedure \n" + 
				" FROM startonl_sct_app.type_procedure \n" +
				" WHERE id_type_procedure = b.id_type_procedure)AS tipoProcedimiento,\n" +
				"    fec_contract_ini,\n" +
				"    fec_contract_fin,\n" +
				"    amount_hire,\n" +
				"    contracted_amount,\n" +
				"    provider_name,\n" +
				"    (SELECT nombre_recurso " +
                "     FROM tipo_recurso_cat_tbl trc " +
                "     WHERE trc.id_tipo_recurso = b.resource_type)AS resource_type,\n" +
				"    fec_compranet,\n" +
				"    fec_clarification_meeting,\n" +
				"    fec_presentations_tender,\n" +
				"    fec_fail,\n" +
				"    segment1,\n" +
				"    segment2,\n" +
				"    segment3,\n" +
				"    segment4,\n" +
				"    segment5,\n" +
				"    segment6,\n" +
				"    segment7,\n" +
				"    segment8,\n" +
				"    creation_date,\n" +
				"    last_update_date,\n" +
				"    id_articulo,\n" +
				"    id_fraccion,\n" +
				"    razon_social,\n" +
				"    b.resource_type\n"+
				"FROM startonl_sct_app.builds b\n" +
				"WHERE id_port = ?\n" +
				"order by id_builds desc";
		
		try {
			con = conectionWrapper.getConexion();
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, puerto);
			rs = psmt.executeQuery();
			while(rs.next()){
				obrasBean = new ObrasBean();
				obrasBean.setIdBuilds(rs.getInt(1));
				obrasBean.setIdPort(rs.getInt(2));
				obrasBean.setBuildDescription(rs.getString(3));
				obrasBean.setIdTypeProcedure(rs.getInt(4));
				obrasBean.setTipoProce(rs.getString(5));
				obrasBean.setFecContractIni(rs.getDate(6));
				obrasBean.setFecContractFin(rs.getDate(7));
				obrasBean.setAmountHire(rs.getBigDecimal(8));
				obrasBean.setContractedAmount(rs.getBigDecimal(9));
				obrasBean.setProviderName(rs.getString(10));
				obrasBean.setResourceType(rs.getString(11));
				obrasBean.setFecCompranet(rs.getDate(12));
				obrasBean.setFecClarification_meeting(rs.getDate(13));
				obrasBean.setFecPresentationsTender(rs.getDate(14));
				obrasBean.setFecFail(rs.getDate(15));
				obrasBean.setSegment1(rs.getString(16));
				obrasBean.setSegment2(rs.getString(17));
				obrasBean.setSegment3(rs.getString(18));
				obrasBean.setSegment4(rs.getString(19));
				obrasBean.setSegment5(rs.getString(20));
				obrasBean.setSegment6(rs.getInt(21));
				obrasBean.setSegment7(rs.getDate(22));
				obrasBean.setSegment8(rs.getBlob(23));
				obrasBean.setCreationDate(rs.getDate(24));
				obrasBean.setLastUpdateDate(rs.getDate(25));
				obrasBean.setIdArticulo(rs.getInt(26));
				obrasBean.setIdFraccion(rs.getInt(27));
				obrasBean.setRazonSocial(rs.getString(28));
				obrasBean.setResourceTypeNum(rs.getString(29));
				listObras.add(obrasBean);				
				
			}
			
		} catch (NamingException | SQLException e) {
			logger.error(e);
		}finally{
			try {
				rs.close();
				psmt.close();
				con.close();
			} catch (SQLException e) {
				logger.error(e);
			}
		}
		return listObras;
	}
	public boolean actualizaObra(ObrasBean obrasBean){
		boolean bandera = false;
		PreparedStatement cs = null;
				try {
					con = conectionWrapper.getConexion();
					String sql = "UPDATE startonl_sct_app.builds\n" +
								"	SET\n" +
								"		build_description 		  = ?,\n" +
								"		id_type_procedure 		  = ?,\n" +
								"		fec_contract_ini  		  = ?,\n" +
								"		fec_contract_fin  		  = ?,\n" +
								"		amount_hire       		  = ?,\n" +
								"		contracted_amount 		  = ?,\n" +
								"		provider_name	  		  = ?,\n" +
								"		resource_type 	  		  = ?,\n" +
								"		id_articulo		  		  = ?,\n" +
								"		id_fraccion 	  		  = ?,\n" +
								"		fec_compranet 	  		  = ?,\n" +
								"		fec_clarification_meeting = ?,\n" +
								"		fec_presentations_tender  = ?,\n" +
								"		fec_fail 				  = ?,\n" +
								"		last_update_date 		  = sysdate(),\n" +
								"       razon_social		      = ?,\n" +
								"       segment1                  = ?, \n" +
								"       fec_firma_contrato        = ? \n" +
								"	WHERE id_builds      		  = ?";
					cs = con.prepareStatement(sql);
					
					cs.setString(1, obrasBean.getBuildDescription());
					cs.setInt(2,obrasBean.getIdTypeProcedure());
					cs.setDate(3, obrasBean.getFecContractIni());
					cs.setDate(4, obrasBean.getFecContractFin());
					cs.setString(5, String.valueOf(obrasBean.getAmountHire()));
					cs.setString(6, String.valueOf(obrasBean.getContractedAmount()));
					cs.setString(7, obrasBean.getProviderName());
					cs.setString(8, obrasBean.getResourceType());
					cs.setInt(9, obrasBean.getIdArticulo());
					cs.setInt(10, obrasBean.getIdFraccion());
					cs.setDate(11, obrasBean.getFecCompranet());
					cs.setDate(12, obrasBean.getFecClarification_meeting());
					cs.setDate(13, obrasBean.getFecPresentationsTender());
					cs.setDate(14, obrasBean.getFecFail());				
					cs.setString(15, obrasBean.getRazonSocial());
					cs.setString(16, obrasBean.getSegment1());
					cs.setDate(17, obrasBean.getFecFirmaContratacion());
					cs.setInt(18, obrasBean.getIdBuilds());
					
					cs.executeUpdate();
					bandera = true;
				} catch (NamingException | SQLException e) {
					logger.error(e);
				}finally{
					try {
						cs.close();
						con.close();
					} catch (SQLException e) {
						logger.error(e);
					}
			    }
				
				return bandera;
			}
	
	/*	
	public boolean actualizaObra(ObrasBean obrasBean){
		boolean bandera = false;
		
		try {
			con = conectionWrapper.getConexion();
			CallableStatement cs = con.prepareCall("{Call update_Build_pr(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			cs.setInt(1, obrasBean.getIdBuilds());
			cs.setString(2, obrasBean.getBuildDescription());
			cs.setInt(3,obrasBean.getIdTypeProcedure());
			cs.setDate(4, obrasBean.getFecContractIni());
			cs.setDate(5, obrasBean.getFecContractFin());
			cs.setString(6, String.valueOf(obrasBean.getAmountHire()));
			cs.setString(7, String.valueOf(obrasBean.getContractedAmount()));
			cs.setString(8, obrasBean.getProviderName());
			cs.setString(9, obrasBean.getResourceType());
			cs.setDate(10, obrasBean.getFecCompranet());
			cs.setDate(11, obrasBean.getFecClarification_meeting());
			cs.setDate(12, obrasBean.getFecPresentationsTender());
			cs.setDate(13, obrasBean.getFecFail());
			cs.setInt(14, obrasBean.getIdArticulo());
			cs.setInt(15, obrasBean.getIdFraccion());
			cs.setString(16, obrasBean.getRazonSocial());
			cs.execute();
			bandera = true;
		} catch (NamingException | SQLException e) {
			logger.error(e);
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				logger.error(e);
			}
	    }
		
		return bandera;
	}
	
	*/
public int selectUtimaObra(int idPort,String descripcion){
	String sql  = "SELECT id_builds\n"+ 
				  "	FROM builds\n"+ 
				  "	WHERE id_port = ?\n"+ 
				  "	AND   build_description = ?";
	PreparedStatement psmt  = null;
	ResultSet 		  rs    = null;
	int idBuild = 0;
	try {
		con = conectionWrapper.getConexion();
		psmt = con.prepareStatement(sql);
		psmt.setInt(1, idPort);
		psmt.setString(2, descripcion);
		rs = psmt.executeQuery();
		while(rs.next()){
			idBuild = rs.getInt(1);
		}
	} catch (NamingException | SQLException e) {
		logger.error(e);
	}finally {
		try {
			rs.close();
			psmt.close();
			con.close();
		} catch (SQLException e) {
			logger.error(e);
		}
	}
	return idBuild;
}

public List<EmpresasObrasBean>  getEmpresasPropuestas(int pinIdBuild){
	String sql = "SELECT id_empresa_prop,\n"+
				 "		 id_emp_build,\n"+
				 "       nom_razon_social,\n"+
				 "       nom_rep_legal,\n"+
				 "       monto_ofertado\n"+
				 " FROM empresa_propuestas_tab where id_emp_build = ?\n"+
				 " order by creation_date desc";
	EmpresasObrasBean empresasObrasBean = new EmpresasObrasBean();
	List<EmpresasObrasBean> listEmpresas = new ArrayList<>();
	ResultSet rs 			= null;
	PreparedStatement psmt 	= null;
	try {
		con = conectionWrapper.getConexion();
		psmt = con.prepareStatement(sql);
		psmt.setInt(1, pinIdBuild);
		rs = psmt.executeQuery();
		while(rs.next()){
			empresasObrasBean = new EmpresasObrasBean();
			empresasObrasBean.setIdEmpPrpuesta(rs.getInt(1));
			empresasObrasBean.setIdEmpBuild(rs.getInt(2));
			empresasObrasBean.setNomEmpresa(rs.getString(3));
			empresasObrasBean.setRepLegal(rs.getString(4));
			empresasObrasBean.setMontoOfertado(rs.getBigDecimal(5));
			listEmpresas.add(empresasObrasBean);
		}
	} catch (NamingException | SQLException e) {
		e.printStackTrace();
	}finally {
		try {
			rs.close();
			psmt.close();
			con.close();
		} catch (SQLException e) {
			logger.error(e);
		}
	}
	
	return listEmpresas;
	
}

/**
 * @deprecated
 * Metodo que inserta las empresas que presentaron
 * propuestas
 * @param listEmp
 * @param idEmpBuild
 * @return
 */
public boolean insertEmpresa(List<EmpresasObrasBean> listEmp,int idEmpBuild){
	String insert = "INSERT INTO empresa_propuestas_tab(id_emp_build,nom_razon_social,nom_rep_legal,monto_ofertado,creation_date)\n"+
					"VALUES(?,?,?,?,sysdate())";
	PreparedStatement psmt = null;
	boolean paso = false;
	try {
		con = conectionWrapper.getConexion();
		psmt = con.prepareStatement(insert);
		for(EmpresasObrasBean em : listEmp){
			psmt.setInt(1, idEmpBuild);
			psmt.setString(2, em.getNomEmpresa());
			psmt.setString(3, em.getRepLegal());
			psmt.setBigDecimal(4, em.getMontoOfertado());
			psmt.executeUpdate();
		}
		paso = true;
		
	} catch (NamingException | SQLException e) {
		logger.error(e);
	}finally {
		try {
			psmt.close();
			con.close();
		} catch (SQLException e) {
			logger.error(e);
		}
	}
	return paso;
}

/**
 * 
 * @param emp
 * @return
 */
public boolean eliminaEmpresa(EmpresasObrasBean emp){
	String delete = "DELETE FROM empresa_propuestas_tab where id_empresa_prop = ?";
	PreparedStatement psmt = null;
	boolean ok = false;
	try {
		con = conectionWrapper.getConexion();
		psmt = con.prepareStatement(delete);
		psmt.setInt(1, emp.getIdEmpPrpuesta());
		psmt.executeUpdate();
		ok = true;
	} catch (NamingException | SQLException e) {
		logger.error(e);
	}finally {
		try {
			psmt.close();
			con.close();
		} catch (SQLException e) {
			logger.error(e);
		}
	}
	return ok;
}

/**
 * Actualiza las empresas que presentaron propuestas
 * @param emp
 */
public boolean actualizaEmpresas(EmpresasObrasBean emp){
	String sql = 	"UPDATE empresa_propuestas_tab\n"+
					"SET nom_razon_social 	= ?,\n"+
					"	nom_rep_legal    	= ?,\n"+
					"    monto_ofertado   	= ?\n"+
					"WHERE id_empresa_prop   =  ?";
	
	PreparedStatement psmt = null;
	boolean paso = false;
	try {
		con = conectionWrapper.getConexion();
		psmt = con.prepareStatement(sql);
		psmt.setString(1, emp.getNomEmpresa());
		psmt.setString(2, emp.getRepLegal());
		psmt.setBigDecimal(3, emp.getMontoOfertado());
		psmt.setInt(4, emp.getIdEmpPrpuesta());
		psmt.executeUpdate();
		paso = true;
	} catch (NamingException | SQLException e) {
		logger.error(e);
	}finally {
		try {
			psmt.close();
			con.close();
		} catch (SQLException e) {
			logger.error(e);
		}
	}
	return paso;
}

/**
 * Inserta empresas que presentaron propuestas
 * @param empresasObrasBean
 * @param idEmpBuild
 * @return
 */
public boolean insertEmpresa(EmpresasObrasBean empresasObrasBean,int idEmpBuild){
	String insert = "INSERT INTO empresa_propuestas_tab(id_emp_build,nom_razon_social,nom_rep_legal,monto_ofertado,creation_date)\n"+
					"VALUES(?,?,?,?,sysdate())";
	PreparedStatement psmt = null;
	boolean paso = false;
	try {
		con = conectionWrapper.getConexion();
		psmt = con.prepareStatement(insert);	
		psmt.setInt(1, idEmpBuild);
		psmt.setString(2, empresasObrasBean.getNomEmpresa());
		psmt.setString(3, empresasObrasBean.getRepLegal());
		psmt.setBigDecimal(4, empresasObrasBean.getMontoOfertado());
		psmt.executeUpdate();
		paso = true;
		
	} catch (NamingException | SQLException e) {
		logger.error(e);
	}finally {
		try {
			psmt.close();
			con.close();
		} catch (SQLException e) {
			logger.error(e);
		}
	}
	return paso;
}

public boolean insertObra(ObrasBean obrasBean){
		
		boolean bandera = false;
		
		try {
			con = conectionWrapper.getConexion();
			String sql = "INSERT INTO startonl_sct_app.builds\n" +
"										(   id_port,\n" +
"											build_description,\n" +
"											id_type_procedure,\n" +
"											fec_contract_ini,\n" +
"											fec_contract_fin,\n" +
"											amount_hire,\n" +
"											contracted_amount,\n" +
"											provider_name,\n" +
"											resource_type,\n" +
"											fec_compranet,\n" +
"											fec_clarification_meeting,\n" +
"											fec_presentations_tender,\n" +
"											fec_fail,\n" +
"											creation_date,\n" +
"                                            id_articulo,\n" +
"                                            id_fraccion,\n" +
"                                            razon_social,"+
"											 segment1,"+
"											 fec_firma_contrato		)\n" +
"										VALUES(\n" +
"											?,\n" +
"											?,\n" +
"											?,\n" +
"											?,\n" +
"											?,\n" +
"											?,\n" +
"											?,\n" +
"											?,\n" +
"											?,\n" +
"											?,\n" +
"											?,\n" +
"											?,\n" +
"											?,\n" +
"											SYSDATE(),\n" +
"                                            ?,\n" +
"                                            ?,\n" +
"                                            ?,\n" +
"                                            ?,\n" +
"											 ? )";
			
			PreparedStatement cs = con.prepareStatement(sql);
			cs.setInt(1, obrasBean.getIdPort());
			cs.setString(2, obrasBean.getBuildDescription());
			cs.setInt(3,obrasBean.getIdTypeProcedure());
			cs.setDate(4, obrasBean.getFecContractIni());
			cs.setDate(5, obrasBean.getFecContractFin());
			cs.setString(6, String.valueOf(obrasBean.getAmountHire()));
			cs.setString(7, String.valueOf(obrasBean.getContractedAmount()));
			cs.setString(8, obrasBean.getProviderName());
			cs.setString(9, obrasBean.getResourceType());
			cs.setDate(10, obrasBean.getFecCompranet());
			cs.setDate(11, obrasBean.getFecClarification_meeting());
			cs.setDate(12, obrasBean.getFecPresentationsTender());
			cs.setDate(13, obrasBean.getFecFail());
			cs.setInt(14, obrasBean.getIdArticulo());
			cs.setInt(15, obrasBean.getIdFraccion());
			cs.setString(16, obrasBean.getRazonSocial());
			cs.setString(17, obrasBean.getSegment1());
			cs.setDate(18, obrasBean.getFecFirmaContratacion());
			cs.executeUpdate();
			bandera = true;
		} catch (NamingException | SQLException e) {
			logger.error(e);
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				logger.error(e);
			}
	    }
		
		return bandera;
	}
	
	/*
	public boolean insertObra(ObrasBean obrasBean){
		
		boolean bandera = false;
		
		try {
			con = conectionWrapper.getConexion();
			CallableStatement cs = con.prepareCall("{Call insertBuild_pr(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			cs.setInt(1, obrasBean.getIdPort());
			cs.setString(2, obrasBean.getBuildDescription());
			cs.setInt(3,obrasBean.getIdTypeProcedure());
			cs.setDate(4, obrasBean.getFecContractIni());
			cs.setDate(5, obrasBean.getFecContractFin());
			cs.setString(6, String.valueOf(obrasBean.getAmountHire()));
			cs.setString(7, String.valueOf(obrasBean.getContractedAmount()));
			cs.setString(8, obrasBean.getProviderName());
			cs.setString(9, obrasBean.getResourceType());
			cs.setDate(10, obrasBean.getFecCompranet());
			cs.setDate(11, obrasBean.getFecClarification_meeting());
			cs.setDate(12, obrasBean.getFecPresentationsTender());
			cs.setDate(13, obrasBean.getFecFail());
			cs.setInt(14, obrasBean.getIdArticulo());
			cs.setInt(15, obrasBean.getIdFraccion());
			cs.setString(16, obrasBean.getRazonSocial());
			cs.execute();
			bandera = true;
		} catch (NamingException | SQLException e) {
			logger.error(e);
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				logger.error(e);
			}
	    }
		
		return bandera;
	}
	*/

	public  Map<String,Integer> obtenArticulosCombo(){
		Map<String,Integer> articulos = new HashMap<String, Integer>();
		String sql = null;
		try {
			
			con = conectionWrapper.getConexion();
				sql = "SELECT id_opc_tipo_proc,\n" +
						 "	   nom_opcion\n" +
						 "FROM startonl_sct_app.opc_tipo_procedimiento \n" +
						 "WHERE tipo_opcion = 'A'";
			
			PreparedStatement psmt = con.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			int id = 0;
			String nomOpc = "";
			while(rs.next()){
				id = rs.getInt(1);
				nomOpc = rs.getString(2);
				articulos.put(nomOpc, id);
			}
			
		} catch (NamingException | SQLException e) {
			logger.error(e);
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				logger.error(e);
			}
	    }
		return articulos;
	}

	public  Map<String,Integer> obtenFraccionesCombo(){
		Map<String,Integer> fraccionesMap = new TreeMap<String, Integer>();
		String sql = null;
		try {
			
			con = conectionWrapper.getConexion();
				sql = "SELECT id_opc_tipo_proc,\n" +
						 "	   nom_opcion\n" +
						 "FROM startonl_sct_app.opc_tipo_procedimiento \n" +
						 "WHERE tipo_opcion = 'F' order by segment6";
			
			PreparedStatement psmt = con.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			int id = 0;
			String nomOpc = "";
			while(rs.next()){
				id = rs.getInt(1);
				nomOpc = rs.getString(2);
				fraccionesMap.put(nomOpc, id);
			}
			
		} catch (NamingException | SQLException e) {
			logger.error(e);
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				logger.error(e);
			}
	    }
		return fraccionesMap;
	}
}
