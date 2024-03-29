/**
 * Project: Facturación Automatica Aisa
 * 
 * File: JobDAO.java
 * 
 * Created on: 05/04/2019  13:58:19
 * 
 * Copyright (c) - Televisa- 2019
 */
package mx.com.televisa.playcity.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import org.apache.log4j.Logger;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import mx.com.televisa.playcity.beans.config.JobsBean;
import mx.com.televisa.playcity.model.db.ConnectionDB;

 /**
 * Facturación Automatica Aisa
 *
 * @author Kaz-Consulting / Argumedo
 *
 * DAO para jobs
 *
 * @version 1.0.0
 *
 * @date 05/04/2019  13:58:19
 */
public class JobDAO {
	
	
	private CallableStatement       poCst;
	private Connection              poCon;
	private ResultSet               poResultSet;
	JobsBean                        loJobsBean;
	private static org.apache.log4j.Logger logger = Logger.getLogger(JobDAO.class);
	
	
	/**
	 * Obtiene toda la lsita de Jobs guardada en la BD
	 * @return una lsita de jobs
	 */
	public List<JobsBean> getJobs(){
		ConnectionDB connectionDB = new ConnectionDB();
		List<JobsBean> loListJobs = new ArrayList<JobsBean>();
		try {
			poCon = connectionDB.getConexion();
			poCst = poCon.prepareCall("{CALL AISA_JOBS_PKG.GET_JOBS_PR(?)}");
			poCst.registerOutParameter(1, OracleTypes.CURSOR);
			poCst.execute();
			poResultSet = ((OracleCallableStatement) poCst).getCursor(1);
			while(poResultSet.next()){
				loJobsBean = new JobsBean();
				loJobsBean.setPiIdJob(poResultSet.getInt(1));
				loJobsBean.setPsNomJob(poResultSet.getString(2));
				loJobsBean.setPiIdCasino(poResultSet.getInt(3));
				loJobsBean.setPsNomCasino(poResultSet.getString(4));
				loJobsBean.setPsNomTrigger(poResultSet.getString(5));
				loJobsBean.setPsHora(poResultSet.getString(6));
				loJobsBean.setPsMinuto(poResultSet.getString(7));
				loJobsBean.setPsCodSemana(poResultSet.getString(8));
				loJobsBean.setPsTiempo(poResultSet.getString(9));
				loListJobs.add(loJobsBean);
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
			logger.error(e);
		}finally{
			try {
				poResultSet.close();
				poCst.close();
				poCon.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return loListJobs;
	}
	
	/**
	 * Metodo que inserta un nuevo job a la base de datos
	 * @param jobsBean bean del job
	 * @return si todo sale bien regresa tru de lo contrario false
	 */
	public boolean insertJob(JobsBean jobsBean){
		ConnectionDB connectionDB = new ConnectionDB();
		boolean      paso         = false; 
		try {
			poCon = connectionDB.getConexion();
			poCst = poCon.prepareCall("{CALL AISA_JOBS_PKG.INSERT_JOB_PR(?,?,?,?)}");
			poCst.setString(1, jobsBean.getPsNomCasino());
			poCst.setString(2, jobsBean.getPsHora());
			poCst.setString(3, jobsBean.getPsMinuto());
			poCst.setString(4, jobsBean.getPsCodSemana());
			poCst.execute();
			paso = true;
		}catch (NamingException | SQLException e) {
			e.printStackTrace();
			logger.error(e);
		}finally{
			try {
				poCst.close();
				poCon.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return paso;
	}
	
	/**
	 * Metodo apra editar el job
	 * @param poJobsBean recibe el objeto Bean
	 * @return regresa true si es correcto o de lo contrario false
	 */
	public boolean editJob(JobsBean poJobsBean){
	
		ConnectionDB connectionDB = new ConnectionDB();
		boolean      paso         = false; 
		try {
			poCon = connectionDB.getConexion();
			poCst = poCon.prepareCall("{CALL AISA_JOBS_PKG.UPDATE_JOB_PR(?,?,?,?,?,?)}");
			poCst.setInt(1, poJobsBean.getPiIdJob());
			poCst.setInt(2, poJobsBean.getPiIdCasino());
			poCst.setString(3, poJobsBean.getPsNomCasino());
			poCst.setString(4, poJobsBean.getPsHora());
			poCst.setString(5, poJobsBean.getPsMinuto());
			poCst.setString(6, poJobsBean.getPsCodSemana());
			poCst.execute();
			paso = true;
		}catch (NamingException | SQLException e) {
			e.printStackTrace();
			logger.error(e);
		}finally{
			try {
				poCst.close();
				poCon.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return paso;
	}
	
	/**
	 * Metodo que elimina un job y su casino correspondiente
	 * @param poJobsBean bean de mapeo
	 * @return regresa true si es correcto o de lo contrario false
	 */
	public boolean deleteJobCasino(JobsBean poJobsBean){
		
		ConnectionDB connectionDB = new ConnectionDB();
		boolean      paso         = false; 
		try {
			poCon = connectionDB.getConexion();
			poCst = poCon.prepareCall("{CALL AISA_JOBS_PKG.DELETE_JOB_PR(?,?)}");
			poCst.setInt(1, poJobsBean.getPiIdJob());
			poCst.setInt(2, poJobsBean.getPiIdCasino());
			poCst.execute();
			paso = true;
		}catch (NamingException | SQLException e) {
			e.printStackTrace();
			logger.error(e);
		}finally{
			try {
				poCst.close();
				poCon.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return paso;
	}

}
