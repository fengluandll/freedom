
package mx.kaz.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;

import mx.kaz.beans.ProyectBean;
import mx.kaz.beans.RegProyectoBean;
import mx.kaz.beans.reportes.AgendaAperturasBean;
import mx.kaz.beans.reportes.AgendaFallosBean;
import mx.kaz.beans.reportes.ReporteLicitaBean;
import mx.kaz.beans.reportes.ReporteObraBean;
import mx.kaz.model.ConectionWrapper;
import mx.kaz.obras.beans.ObrasBean;

import org.apache.log4j.Logger;

/**
 * @author Jesus Argumedo
 *
 */
public class ReportesDAO {

	
	private org.apache.log4j.Logger logger = Logger.getLogger(ReportesDAO.class);
	ConectionWrapper conectionWrapper;
	Connection con;
	ReporteLicitaBean reporteLicitaBean;
	List<ReporteLicitaBean> listRep;
	List<ReporteLicitaBean> listLiciProceso;
	List<AgendaAperturasBean> listApertura;
	List<AgendaFallosBean> listFallos;
	List<ProyectBean> listProyectos;
	List<ObrasBean> listlaassp;
	AgendaAperturasBean agendaAperturasBean;
	AgendaFallosBean agendaFallosBean;
	ProyectBean proyectBean;
	ObrasBean obrasBean;
	private Map<String,Integer> mapApis;
	
	public ReportesDAO(){
		conectionWrapper = new ConectionWrapper();
		
	}
	
	public Map<String,Integer> damePuertos(){
		String sql = "SELECT id_port,\n"+
					 "      acronym\n"+
					 "FROM ports_cat";
		ResultSet rs 		   = null;
		PreparedStatement psmt = null;
		mapApis = new HashMap<String,Integer>();
		
		try {
			con = conectionWrapper.getConexion();
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			int idPuerto  = 0;
			String nomPto = "";
			while(rs.next()){
				idPuerto = rs.getInt(1);
				nomPto   = rs.getString(2);
				mapApis.put(nomPto, idPuerto);
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				psmt.close();
				rs.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return mapApis;
	}
	
	public boolean obrasReporte(ReporteObraBean reporteObraBean){
		String sql = "{CALL reporte_obras_pr(?,?,?,?,?,?,?,?,?,?,?)}";
		CallableStatement cstm = null;
		boolean ok = false;
		
		
		try {
			con = conectionWrapper.getConexion();
			cstm = con.prepareCall(sql);
			cstm.setBigDecimal(1, reporteObraBean.getMontoAsignadoIni());
			cstm.setBigDecimal(2, reporteObraBean.getMontoAsignadoFin());
			cstm.setString(3, reporteObraBean.getTipoRecurso());
			cstm.setInt(4, reporteObraBean.getIdPuerto());
			cstm.setString(5, reporteObraBean.getNom_contratista());
			cstm.setDate(6,reporteObraBean.getPeriodEjecIni());
			cstm.setDate(7,reporteObraBean.getPeriodEjecFin());
			cstm.setDate(8,reporteObraBean.getPeriodEjecIni2());
			cstm.setDate(9,reporteObraBean.getPeriodEjecFin2());
			cstm.setDate(10,reporteObraBean.getFechaFalloIni());
			cstm.setDate(11,reporteObraBean.getFechaFalloFin());
			cstm.execute();
			ok = true;
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				cstm.close();
				con.close();
		    } catch (SQLException e) {
			    logger.error(e);
			    FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:",e.getMessage()));
		      }
		}
		return ok; 
	}
	
	public List<ObrasBean> laasspReport(int idPuerto){
		listlaassp = new ArrayList<>();
		String sql = 	"SELECT id_port,\n" +
						"    puerto,\n" +
						"    descripcion,\n" +
						"    tipo_procedimiento,\n" +
						"    articulo,\n" +
						"    fraccion,\n" +
						"    cont_inicio,\n" +
						"    cont_fin,\n" +
						"    monto_a_contratar,\n" +
						"    monto_contratado,\n" +
						"    nom_proveedor,\n" +
						"(SELECT nombre_recurso \n" +
	                    " FROM tipo_recurso_cat_tbl trc\n" +
	                    " WHERE trc.id_tipo_recurso = tipo_recurso)AS tipo_recurso,\n" +
						"    fec_compranet,\n" +
						"    fec_junt_aclar,\n" +
						"    fec_prent_prop,\n" +
						"    fec_fallo,\n" +
						"    tp,\n" +
						"    numAcuerdo\n" +
						"FROM laassp_view";
		String sql2 = 	"SELECT id_port,\n" +
				"    puerto,\n" +
				"    descripcion,\n" +
				"    tipo_procedimiento,\n" +
				"    articulo,\n" +
				"    fraccion,\n" +
				"    cont_inicio,\n" +
				"    cont_fin,\n" +
				"    monto_a_contratar,\n" +
				"    monto_contratado,\n" +
				"    nom_proveedor,\n" +
				"    tipo_recurso,\n" +
				"    fec_compranet,\n" +
				"    fec_junt_aclar,\n" +
				"    fec_prent_prop,\n" +
				"    fec_fallo,\n" +
				"    tp,\n" +
				"    numAcuerdo\n" +
				"FROM laassp_view WHERE id_port = ?";
		try {
			con = conectionWrapper.getConexion();
			PreparedStatement psmt = null;
			if(idPuerto != 0){
				psmt = con.prepareStatement(sql2);
				psmt.setInt(1, idPuerto);
			}else{
				psmt = con.prepareStatement(sql);
			}
			ResultSet rs = psmt.executeQuery();
			while(rs.next()){
				obrasBean = new ObrasBean();
				obrasBean.setIdPort(rs.getInt(1));
				obrasBean.setNomPuerto(rs.getString(2));
				obrasBean.setBuildDescription(rs.getString(3));
				obrasBean.setTipoProce(rs.getString(4));
				obrasBean.setArticulo(rs.getString(5));
				obrasBean.setFraccion(rs.getString(6));
				obrasBean.setFecContractIni(rs.getDate(7));
				obrasBean.setFecContractFin(rs.getDate(8));
				obrasBean.setAmountHire(rs.getBigDecimal(9));
				obrasBean.setContractedAmount(rs.getBigDecimal(10));
				obrasBean.setProviderName(rs.getString(11));
				obrasBean.setResourceType(rs.getString(12));
				obrasBean.setFecCompranet(rs.getDate(13));
				obrasBean.setFecClarification_meeting(rs.getDate(14));
				obrasBean.setFecPresentationsTender(rs.getDate(15));
				obrasBean.setFecFail(rs.getDate(16));
				obrasBean.setTp(rs.getString(17));
				obrasBean.setSegment1(rs.getString(18));
				listlaassp.add(obrasBean);
			}
		} catch (NamingException | SQLException e) {
			logger.error(e);
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				logger.error(e);
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:",e.getMessage()));
			}
		}
		
		return listlaassp;
	}
	
	public List<ProyectBean> proyectosAgregados(){
		listProyectos = new ArrayList<>();
		
		String sql = "SELECT proyectos_view.nom_puerto,\n" +
					"    proyectos_view.name,\n" +
					"    proyectos_view.description,\n" +
					"    proyectos_view.code,\n" +
					"    proyectos_view.period,\n" +
					"    proyectos_view.total_investment,\n" +
					"    proyectos_view.exercised_amounrt,\n" +
					"    proyectos_view.asigned_amount,\n" +
					"    proyectos_view.resuorce_type\n" +
					"FROM startonl_sct_app.proyectos_view";
		try {
			con = conectionWrapper.getConexion();
			PreparedStatement psmt = con.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()){
				proyectBean = new ProyectBean();
				proyectBean.setNomPto(rs.getString(1));
				proyectBean.setName(rs.getString(2));
				proyectBean.setDescription(rs.getString(3));
				proyectBean.setCode(rs.getString(4));
				proyectBean.setPeriod(rs.getString(5));
				proyectBean.setTotalInvestment(rs.getBigDecimal(6));
				proyectBean.setExercisedAmount(rs.getBigDecimal(7));
				proyectBean.setAsignedAmount(rs.getBigDecimal(8));
				proyectBean.setResourceType(rs.getString(9));
				listProyectos.add(proyectBean);
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				logger.error(e);
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:",e.getMessage()));
			}
		}
		
		return listProyectos;
	}
	
	public List<ReporteLicitaBean> liciRealizadas(int idPuerto){
		listRep = new ArrayList<>();
	
		String sql = "SELECT encabezado,\n" +
				"    id_port,\n" +
				"    proyecto,\n" +
				"    obra,\n" +
				"    monto_contratado,\n" +
				"    publicacion_prebases,\n" +
				"    publicacion_convocatoria,\n" +
				"    apertura_proposiciones,\n" +
				"    fallo,\n" +
				"    inicio_fin_obra,\n" +
				"    participantes,\n" +
				"    contratista,\n" +
				"    observaciones,\n" +
				"    tipo_procedimiento,\n" +
				"    puerto\n" +
				"FROM licitaciones_realizadas\n";
		String sql2 = "SELECT encabezado,\n" +
				"    id_port,\n" +
				"    proyecto,\n" +
				"    obra,\n" +
				"    monto_contratado,\n" +
				"    publicacion_prebases,\n" +
				"    publicacion_convocatoria,\n" +
				"    apertura_proposiciones,\n" +
				"    fallo,\n" +
				"    inicio_fin_obra,\n" +
				"    participantes,\n" +
				"    contratista,\n" +
				"    observaciones,\n" +
				"    tipo_procedimiento,\n" +
				"    puerto\n" +
				"FROM licitaciones_realizadas\n"+
				"WHERE id_port = ?";
		
		try {
			con = conectionWrapper.getConexion();
			PreparedStatement psmt;
			if(idPuerto != 0){
				psmt = con.prepareStatement(sql2);
				psmt.setInt(1, idPuerto);
			}else{
				psmt = con.prepareStatement(sql);
			}
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()){
				reporteLicitaBean = new ReporteLicitaBean();
				reporteLicitaBean.setProyecto(rs.getString(3));
				reporteLicitaBean.setObras(rs.getString(4));
				reporteLicitaBean.setMontoContratado(rs.getBigDecimal(5));
				reporteLicitaBean.setPubliPrebases(rs.getDate(6));
				reporteLicitaBean.setPubliConvocatoria(rs.getDate(7));
				reporteLicitaBean.setAperturaProposiciones(rs.getDate(8));
				reporteLicitaBean.setFallo(rs.getDate(9));
				reporteLicitaBean.setIncioFinObra(rs.getString(10));
				reporteLicitaBean.setParticipantes(rs.getString(11));
				reporteLicitaBean.setContratista(rs.getString(12));
				reporteLicitaBean.setObservaciones(rs.getString(13));
				reporteLicitaBean.setTipoProcedimiento(rs.getString(14));
				reporteLicitaBean.setNomPto(rs.getString(15));
				listRep.add(reporteLicitaBean);
			}
		} catch (NamingException | SQLException e) {
			logger.error(e);
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				logger.error(e);
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:",e.getMessage()));
			}
		}
		return listRep;
	}
	
	public List<ReporteLicitaBean> liciProcesadas(int idPuerto){
		listLiciProceso = new ArrayList<>();
		String sql = "SELECT encabezado,\n" +
				"    id_port,\n" +
				"    proyecto,\n" +
				"    obra,\n" +
				"    presupuesto_base,\n" +
				"    publicacion_prebases,\n" +
				"    publicacion_convocatoria,\n" +
				"    apertura_proposiciones,\n" +
				"    fallo,\n" +
				"    inicio_fin_obra,\n" +
				"    participantes,\n" +
				"    contratista,\n" +
				"    observaciones,\n" +
				"    tipo_procedimiento,\n" +
				"    puerto\n" +
				"FROM licitaciones_proceso\n" +
				"WHERE id_port = ?";
		String sql2 = "SELECT encabezado,\n" +
				"    id_port,\n" +
				"    proyecto,\n" +
				"    obra,\n" +
				"    presupuesto_base,\n" +
				"    publicacion_prebases,\n" +
				"    publicacion_convocatoria,\n" +
				"    apertura_proposiciones,\n" +
				"    fallo,\n" +
				"    inicio_fin_obra,\n" +
				"    participantes,\n" +
				"    contratista,\n" +
				"    observaciones,\n" +
				"    tipo_procedimiento,\n" +
				"    puerto\n" +
				"FROM licitaciones_proceso";
		try {
			con = conectionWrapper.getConexion();
			PreparedStatement psmt;
			if(idPuerto != 0){
				psmt = con.prepareStatement(sql);
				psmt.setInt(1, idPuerto);
			}else{
				psmt = con.prepareStatement(sql2);
			}
			
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()){
				reporteLicitaBean = new ReporteLicitaBean();
				reporteLicitaBean.setEncabezado(rs.getString(1));
				reporteLicitaBean.setProyecto(rs.getString(3));
				reporteLicitaBean.setObras(rs.getString(4));
				reporteLicitaBean.setPresuBase(rs.getBigDecimal(5));
				reporteLicitaBean.setPubliPrebases(rs.getDate(6));
				reporteLicitaBean.setPubliConvocatoria(rs.getDate(7));
				reporteLicitaBean.setAperturaProposiciones(rs.getDate(8));
				reporteLicitaBean.setFallo(rs.getDate(9));
				reporteLicitaBean.setIncioFinObra(rs.getString(10));
				reporteLicitaBean.setParticipantes(rs.getString(11));
				reporteLicitaBean.setContratista(rs.getString(12));
				reporteLicitaBean.setObservaciones(rs.getString(13));
				reporteLicitaBean.setTipoProcedimiento(rs.getString(14));
				reporteLicitaBean.setNomPto(rs.getString(15));
				listLiciProceso.add(reporteLicitaBean);
			}
		} catch (NamingException | SQLException e) {
			logger.error(e);
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				logger.error(e);
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:",e.getMessage()));
			}
		}
		return listLiciProceso;
	}
	
	public List<AgendaAperturasBean> getAgendaApertura(){
		listApertura = new ArrayList<>();
		String sql = "SELECT dia,\n" +
					"    api,\n" +
					"    obra,\n" +
					"    observaciones\n" +
					"FROM agenda_aperturas order by dia";
		
		try {
			con = conectionWrapper.getConexion();
			PreparedStatement psmt = con.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()){
				agendaAperturasBean = new AgendaAperturasBean();
				agendaAperturasBean.setDia(rs.getString(1));
				agendaAperturasBean.setApi(rs.getString(2));
				agendaAperturasBean.setObra(rs.getString(3));
				agendaAperturasBean.setObservaciones(rs.getString(4));
				listApertura.add(agendaAperturasBean);
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
		return listApertura;
	}
	
	public List<AgendaFallosBean> getAgendaFallos(){
		listFallos = new ArrayList<>();
		String sql = "SELECT dia,\n" +
					"    api,\n" +
					"    obra,\n" +
					"    observaciones\n" +
					"FROM agenda_fallos order by dia";
		
		try {
			con = conectionWrapper.getConexion();
			PreparedStatement psmt = con.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()){
				agendaFallosBean = new AgendaFallosBean();
				agendaFallosBean.setDia(rs.getString(1));
				agendaFallosBean.setApi(rs.getString(2));
				agendaFallosBean.setObra(rs.getString(3));
				agendaFallosBean.setObservaciones(rs.getString(4));
				listFallos.add(agendaFallosBean);
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
		return listFallos;
	}
	
}
