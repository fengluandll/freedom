
package mx.kaz.daos;

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

import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import mx.kaz.beans.ProyectBean;
import mx.kaz.beans.RegLicitaciones;
import mx.kaz.beans.RegProyectoBean;
import mx.kaz.model.ConectionWrapper;

import org.apache.log4j.Logger;

/**
 * @author Jesús Argumedo
 *
 */
public class ProyectDAO {
	
	private org.apache.log4j.Logger logger = Logger.getLogger(ProyectDAO.class);
	private ConectionWrapper conectionWrapper;
	private Connection con;
	private FacesContext facesContext;
	//private HttpSession session;
	private RegProyectoBean regProyectoBean;
	private RegLicitaciones regLicitaciones;
	


	public ProyectDAO(){
		conectionWrapper = new ConectionWrapper();
		facesContext = FacesContext.getCurrentInstance();
		//session = (HttpSession) facesContext.getExternalContext().getSession(true);
	}
	
	public Map<String,Integer> loadTipoRecurso(){
		
		Map<String,Integer> mapTR = new TreeMap<String,Integer>();
		String sql = "SELECT id_tipo_recurso,nombre_recurso FROM tipo_recurso_cat_tbl ORDER BY id_tipo_recurso";
		try {
			con = conectionWrapper.getConexion();
			PreparedStatement psmt = con.prepareStatement(sql);
			ResultSet rs =  psmt.executeQuery();
			int idTipoRecurso;
			String nomRecurso;
			while(rs.next()){
				idTipoRecurso = rs.getInt(1);
				nomRecurso = rs.getString(2);
				mapTR.put(nomRecurso,idTipoRecurso);
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				logger.error(e);
			}
		}
		
		return mapTR;
	}
	
	public Map<String,Integer> loadProcedure(){
		Map<String,Integer> procedures = new HashMap<String,Integer>();
		String sql = "SELECT id_type_procedure,name_procedure FROM type_procedure";
		try {
			con = conectionWrapper.getConexion();
			PreparedStatement psmt = con.prepareStatement(sql);
			ResultSet rs =  psmt.executeQuery();
			int idProcedure;
			String nomProcedure;
			while(rs.next()){
				idProcedure = rs.getInt(1);
				nomProcedure = rs.getString(2);
				procedures.put(nomProcedure,idProcedure);
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				logger.error(e);
			}
		}
		
		return procedures;
	}
	
	public List<RegProyectoBean> obtenContenido(int puerto){
		List<RegProyectoBean> listPjects = new ArrayList<>();
		String sql = "SELECT id_proyect,\n" +
					"    id_port,\n" +
					"    name,\n" +
					"    description,\n" +
					"    code,\n" +
					"    period,\n" +
					"    total_investment,\n" +
					"    exercised_amounrt,\n" +
					"    asigned_amount,\n" +
					"    (SELECT nombre_recurso \n"+
                    " FROM tipo_recurso_cat_tbl trc \n"+
                    " WHERE trc.id_tipo_recurso = resuorce_type)AS resource_type,\n"+
                    " resuorce_type AS resuorce_type_num\n"+
					"FROM build_law_projects \n"+
					"WHERE id_port = ? order by id_proyect desc";
		try {
			con = conectionWrapper.getConexion();
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setInt(1, puerto);
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()){
				regProyectoBean = new RegProyectoBean();
				regProyectoBean.setIdProyect(rs.getInt(1));
				regProyectoBean.setIdPort(rs.getInt(2));
				regProyectoBean.setNomProyecto(rs.getString(3));
				regProyectoBean.setDesPro(rs.getString(4));
				regProyectoBean.setClaveCartera(rs.getString(5));
				regProyectoBean.setPeriodoEjecu(rs.getString(6));
				regProyectoBean.setInversionTotal(rs.getBigDecimal(7));
				regProyectoBean.setMontoEjercido(rs.getBigDecimal(8));
				regProyectoBean.setMontoAsignado(rs.getBigDecimal(9));
				regProyectoBean.setTipoRecurso(rs.getString(10));
				regProyectoBean.setTipoRecursoNum(rs.getString(11));
				listPjects.add(regProyectoBean);
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
		return listPjects;
	}
	
	
	public int obtenIdPto(String nomPto){
		conectionWrapper = new ConectionWrapper();
		String sql = "SELECT id_port FROM ports_cat WHERE acronym = ?";
		int idPort = 0;
		try {
			con = conectionWrapper.getConexion();
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, nomPto);
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()){
				idPort = rs.getInt(1);
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
		return idPort;
	}
	
	public String obtenNomPto(int idPto){
		conectionWrapper = new ConectionWrapper();
		String sql = "SELECT acronym FROM ports_cat WHERE id_port = ?";
		String nomPto = null;
		try {
			con = conectionWrapper.getConexion();
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setInt(1, idPto);
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()){
				nomPto = rs.getString(1);
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
		return nomPto;
	}
/*	
public boolean insertProyect(ProyectBean proyectBean){
		
		boolean bandera = false;
		
		try {
			con = conectionWrapper.getConexion();
			String sql = "INSERT INTO build_law_projects( id_port,\n" +
"									name,\n" +
"									description,\n" +
"                                    code,\n" +
"                                    period,\n" +
"                                    total_investment,\n" +
"                                    exercised_amounrt,\n" +
"                                    asigned_amount,\n" +
"                                    resuorce_type,\n" +
"                                    creation_date)\n" +
						"	VALUES ( ?,\n" +
						"			 ?,\n" +
						"            ?,\n" +
						"            ?,\n" +
						"            ?,\n" +
						"            ?,\n" +
						"            ?,\n" +
						"            ?,\n" +
						"            ?,\n" +
						"            SYSDATE())";
			PreparedStatement cs = con.prepareStatement(sql);
			cs.setInt(1, proyectBean.getIdPort());
			cs.setString(2, proyectBean.getName());
			cs.setString(3,proyectBean.getDescription());
			cs.setString(4, proyectBean.getCode());
			cs.setString(5, proyectBean.getPeriod());
			cs.setString(6, String.valueOf(proyectBean.getTotalInvestment()));
			cs.setString(7, String.valueOf(proyectBean.getExercisedAmount()));
			cs.setString(8, String.valueOf(proyectBean.getAsignedAmount()));
			cs.setString(9, proyectBean.getResourceType());
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
*/
	public boolean insertProyect(ProyectBean proyectBean){
		
		boolean bandera = false;
		
		try {
			con = conectionWrapper.getConexion();
			CallableStatement cs = con.prepareCall("{Call insert_project(?,?,?,?,?,?,?,?,?)}");
			cs.setInt(1, proyectBean.getIdPort());
			cs.setString(2, proyectBean.getName());
			cs.setString(3,proyectBean.getDescription());
			cs.setString(4, proyectBean.getCode());
			cs.setString(5, proyectBean.getPeriod());
			cs.setString(6, String.valueOf(proyectBean.getTotalInvestment()));
			cs.setString(7, String.valueOf(proyectBean.getExercisedAmount()));
			cs.setString(8, String.valueOf(proyectBean.getAsignedAmount()));
			cs.setString(9, proyectBean.getResourceType());
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
	
	public String dameTipoProcedimiento(int tipoProcedure){
		String nomProcedure = "";
		String sql = "SELECT name_procedure FROM startonl_sct_app.type_procedure WHERE id_type_procedure = ?";
		try {
			con = conectionWrapper.getConexion();
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setInt(1, tipoProcedure);
			ResultSet rs =  psmt.executeQuery();
			
			while(rs.next()){
				nomProcedure = rs.getString(1);
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return nomProcedure;
	}
/*	
public boolean insertLicitacion(RegLicitaciones regLicitaciones){
		
		boolean bandera = false;
		
		try {
			con = conectionWrapper.getConexion();
			String sql = "INSERT INTO build_law_tenders( id_project,\n" +
"								   buid_description,\n" +
"                                   base_budget,\n" +
"                                   call_publication_date,\n" +
"                                   propositions_date,\n" +
"                                   fail_date,\n" +
"                                   contractor_name,\n" +
"                                   total_amount,\n" +
"                                   begin_date,\n" +
"                                   end_date,\n" +
"                                   annotations,\n" +
"                                   creation_date,\n" +
"								   suppliers,\n" +
"								   segment1)\n" +
						"	VALUES (?,\n" +
						"			?,\n" +
						"           ?,\n" +
						"			?,\n" +
						"           ?,\n" +
						"           ?,\n" +
						"           ?,\n" +
						"           ?,\n" +
						"           ?,\n" +
						"           ?,\n" +
						"           ?,\n" +
						"            SYSDATE(),\n" +
						"	        ?,\n" +
						"		    ?)";
			PreparedStatement cs = con.prepareStatement(sql);
			cs.setInt(1, regLicitaciones.getIdProject());
			cs.setString(2, regLicitaciones.getDescObra());
			cs.setBigDecimal(3,regLicitaciones.getPresuBase());
			cs.setDate(4, regLicitaciones.getPublConvocatoria());
			cs.setDate(5, regLicitaciones.getAperProposiciones());
			cs.setDate(6, regLicitaciones.getFecFallo());
			cs.setString(7, regLicitaciones.getNomContratista());
			cs.setBigDecimal(8, regLicitaciones.getTotalMonto());
			cs.setDate(9, regLicitaciones.getIniObra());
			cs.setDate(10, regLicitaciones.getFinObra());
			cs.setString(11, regLicitaciones.getObservaciones());
			cs.setString(12, regLicitaciones.getParticipantes());
			cs.setInt(13,regLicitaciones.getTipoProcedimiento());
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
	
*/	
	
	public boolean insertLicitacion(RegLicitaciones regLicitaciones){
		
		boolean bandera = false;
		
		try {
			con = conectionWrapper.getConexion();
			CallableStatement cs = con.prepareCall("{Call insert_tender(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			cs.setInt(1, regLicitaciones.getIdProject());
			cs.setString(2, regLicitaciones.getDescObra());
			cs.setBigDecimal(3,regLicitaciones.getPresuBase());
			cs.setDate(4, regLicitaciones.getPublConvocatoria());
			cs.setDate(5, regLicitaciones.getAperProposiciones());
			cs.setDate(6, regLicitaciones.getFecFallo());
			cs.setString(7, regLicitaciones.getNomContratista());
			cs.setBigDecimal(8, regLicitaciones.getTotalMonto());
			cs.setDate(9, regLicitaciones.getIniObra());
			cs.setDate(10, regLicitaciones.getFinObra());
			cs.setString(11, regLicitaciones.getObservaciones());
			cs.setString(12, regLicitaciones.getParticipantes());
			cs.setInt(13,regLicitaciones.getTipoProcedimiento());
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
	
	public List<RegLicitaciones> obtenLicitacion(int idProyect){
		conectionWrapper = new ConectionWrapper();
		List<RegLicitaciones> listLici = new ArrayList<>();
		String sql = "SELECT id_tender,\n" +		
				"    buid_description,\n" +
				"    base_budget,\n" +
				"    call_publication_date,\n" +
				"    propositions_date,\n" +
				"    fail_date,\n" +
				"    contractor_name,\n" +
				"    total_amount,\n" +
				"    begin_date,\n" +
				"    end_date,\n" +
				"    annotations,\n" +
				"    creation_date,\n" +
				"    last_update_date,\n" +
				"	 id_project,\n"+
				"	 suppliers,\n"+
				"	 segment1\n"+
				"FROM build_law_tenders\n"+
				"WHERE id_project = ?";

		try {
			con = conectionWrapper.getConexion();
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setInt(1, idProyect);
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()){
				regLicitaciones = new RegLicitaciones();
				regLicitaciones.setIdLicitacion(rs.getInt(1));
				regLicitaciones.setDescObra(rs.getString(2));
				regLicitaciones.setPresuBase(rs.getBigDecimal(3));
				regLicitaciones.setPublConvocatoria(rs.getDate(4));
				regLicitaciones.setAperProposiciones(rs.getDate(5));
				regLicitaciones.setFecFallo(rs.getDate(6));
				regLicitaciones.setNomContratista(rs.getString(7));
				regLicitaciones.setTotalMonto(rs.getBigDecimal(8));
				regLicitaciones.setIniObra(rs.getDate(9));
				regLicitaciones.setFinObra(rs.getDate(10));
				regLicitaciones.setObservaciones(rs.getString(11));
				regLicitaciones.setIdProject(rs.getInt(14));
				regLicitaciones.setParticipantes(rs.getString(15));
				regLicitaciones.setTipoProcedimiento(rs.getInt(16));
				listLici.add(regLicitaciones);
				
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
		return listLici;
	}
/*	
	public boolean actualizaProject(RegProyectoBean regProyectoBean){
		boolean bandera = false;
		
		try {
			con = conectionWrapper.getConexion();
			String sql = "UPDATE build_law_projects\n" +
						"    SET	name    	    = ?,\n" +
						"           description		= ?,\n" +
						"           code			= ?,\n" +
						"		   period			= ?,\n" +
						"		   total_investment	= ?,\n" +
						"		   exercised_amounrt= ?,\n" +
						"		   asigned_amount 	= ?,\n" +
						"           resuorce_type	= ?,\n" +
						"           last_update_date	= SYSDATE()\n" +
						"	WHERE  id_proyect       = ?";
			PreparedStatement cs = con.prepareStatement(sql);
			cs.setInt(1, regProyectoBean.getIdProyect());
			cs.setString(2,regProyectoBean.getNomProyecto());
			cs.setString(3, regProyectoBean.getDesPro());
			cs.setString(4, regProyectoBean.getClaveCartera());
			cs.setString(5, regProyectoBean.getPeriodoEjecu());
			cs.setBigDecimal(6, regProyectoBean.getInversionTotal());
			cs.setBigDecimal(7, regProyectoBean.getMontoEjercido());
			cs.setBigDecimal(8, regProyectoBean.getMontoAsignado());
			cs.setString(9, regProyectoBean.getTipoRecurso());
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
	
*/	
	
	public boolean actualizaProject(RegProyectoBean regProyectoBean){
		boolean bandera = false;
		
		try {
			con = conectionWrapper.getConexion();
			CallableStatement cs = con.prepareCall("{Call update_project(?,?,?,?,?,?,?,?,?,?)}");
			cs.setInt(1, regProyectoBean.getIdProyect());
			cs.setInt(2, 0);
			cs.setString(3,regProyectoBean.getNomProyecto());
			cs.setString(4, regProyectoBean.getDesPro());
			cs.setString(5, regProyectoBean.getClaveCartera());
			cs.setString(6, regProyectoBean.getPeriodoEjecu());
			cs.setBigDecimal(7, regProyectoBean.getInversionTotal());
			cs.setBigDecimal(8, regProyectoBean.getMontoEjercido());
			cs.setBigDecimal(9, regProyectoBean.getMontoAsignado());
			cs.setString(10, regProyectoBean.getTipoRecurso());
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
	
/*
	public boolean actualizaLicitacion(RegLicitaciones regLicitaciones){
		boolean bandera = false;
		
		try {
			con = conectionWrapper.getConexion();
			String sql = "UPDATE build_law_tenders\n" +
						"    SET   suppliers            = ?,\n" +
						"		   buid_description	    = ?,\n" +
						"		   base_budget			= ?,\n" +
						"		   call_publication_date= ?,\n" +
						"		   propositions_date	= ?,\n" +
						"           fail_date			= ?,\n" +
						"		   contractor_name		= ?,\n" +
						"           total_amount		= ?,\n" +
						"		   begin_date			= ?,\n" +
						"		   end_date				= ?,\n" +
						"		   annotations			= ?,\n" +
						"		   last_update_date		= SYSDATE(),\n" +
						"		   segment1             = ?\n" +
						"	WHERE  id_tender			= ?";
			PreparedStatement cs = con.prepareStatement(sql);
			cs.setInt(1, regLicitaciones.getIdLicitacion());
			cs.setString(2, regLicitaciones.getDescObra());
			cs.setBigDecimal(3,regLicitaciones.getPresuBase());
			cs.setDate(4, regLicitaciones.getPublConvocatoria());
			cs.setDate(5, regLicitaciones.getAperProposiciones());
			cs.setDate(6, regLicitaciones.getFecFallo());
			cs.setString(7, regLicitaciones.getNomContratista());
			cs.setBigDecimal(8, regLicitaciones.getTotalMonto());
			cs.setDate(9, regLicitaciones.getIniObra());
			cs.setDate(10, regLicitaciones.getFinObra());
			cs.setString(11, regLicitaciones.getObservaciones());
			cs.setString(12, regLicitaciones.getParticipantes());
			cs.setInt(13, regLicitaciones.getTipoProcedimiento());
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
*/	
	
	public boolean actualizaLicitacion(RegLicitaciones regLicitaciones){
		boolean bandera = false;
		
		try {
			con = conectionWrapper.getConexion();
			CallableStatement cs = con.prepareCall("{Call update_tender(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			cs.setInt(1, regLicitaciones.getIdLicitacion());
			cs.setString(2, regLicitaciones.getDescObra());
			cs.setBigDecimal(3,regLicitaciones.getPresuBase());
			cs.setDate(4, regLicitaciones.getPublConvocatoria());
			cs.setDate(5, regLicitaciones.getAperProposiciones());
			cs.setDate(6, regLicitaciones.getFecFallo());
			cs.setString(7, regLicitaciones.getNomContratista());
			cs.setBigDecimal(8, regLicitaciones.getTotalMonto());
			cs.setDate(9, regLicitaciones.getIniObra());
			cs.setDate(10, regLicitaciones.getFinObra());
			cs.setString(11, regLicitaciones.getObservaciones());
			cs.setString(12, regLicitaciones.getParticipantes());
			cs.setInt(13, regLicitaciones.getTipoProcedimiento());
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
	
	

}