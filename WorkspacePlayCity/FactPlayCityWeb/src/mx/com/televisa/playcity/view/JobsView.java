package mx.com.televisa.playcity.view;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;







import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;

import mx.com.televisa.playcity.beans.config.JobsBean;
import mx.com.televisa.playcity.model.JobDAO;
import mx.com.televisa.playcity.util.Functions;
import mx.com.televisa.playcity.util.Mensajes;


@ManagedBean
@ViewScoped
public class JobsView {
	
	private static org.apache.log4j.Logger logger = Logger.getLogger(JobsView.class);
	
	List<JobsBean>         loListJobs;
	int                    linIdJob;
	int                    linIdCasino;
	private String         lsNomCasino;
	private Date           ltHoraMinutos;
	private String[]       laSemana; 
	private List<String>   selectSemanas;
	private JobDAO         loJobDAO;
	private String         lsTiempo;
	private String         lsCodSemana;
	Format  lsFormatter    = new SimpleDateFormat("HH:mm");
	private List<JobDAO>   filteredJobs;

	/**
	 * Metodo Constructor
	 */
	public JobsView(){
		getListJobs();
		selectSemanas = new ArrayList<String>(); 
		selectSemanas.add("Lunes");
		selectSemanas.add("Martes");
		selectSemanas.add("Miercoles");
		selectSemanas.add("Jueves");
		selectSemanas.add("Viernes");
		selectSemanas.add("S�bado");
		selectSemanas.add("Domingo");
	}
	
	
	public void getListJobs(){
		loJobDAO = new JobDAO();
		this.loListJobs = loJobDAO.getJobs();
	}
	
	public void cleanVariables(){
//		RequestContext.getCurrentInstance().update("id_obra");
//		 RequestContext.getCurrentInstance().update("cmbArt");
//		 RequestContext.getCurrentInstance().update("msgAlert");
		this.lsNomCasino =   "";
		this.laSemana    =   null;
		this.ltHoraMinutos = null;
	}
	
	public void refresVariablesJob(JobsBean loJobBean){
		this.lsNomCasino = loJobBean.getPsNomCasino();
		this.linIdJob    = loJobBean.getPiIdJob();
		this.linIdCasino = loJobBean.getPiIdCasino();
		this.lsTiempo    = loJobBean.getPsTiempo();
		this.laSemana    = loJobBean.getPsCodSemana().split(",");
	}
	
	public void saveJob(){
		loJobDAO             = new JobDAO();
		JobsBean loJobsBean  = new JobsBean();
		
				
		
	    String   lsTime      = lsFormatter.format(this.ltHoraMinutos);
	    String[] lsplitTime  = lsTime.split(":");
	    loJobsBean.setPsHora(lsplitTime[0]);
	    loJobsBean.setPsMinuto(lsplitTime[1]);
	    loJobsBean.setPsNomCasino(this.lsNomCasino);
	    logger.debug("Nombre casino: "+this.lsNomCasino);
		logger.debug("Tiempo ejecuci�n: "+lsTime);
	    
	   
		loJobsBean.setPsCodSemana(convertSemanaArrayToString(this.laSemana));
		boolean lbPaso = loJobDAO.insertJob(loJobsBean);
		if(lbPaso){
			getListJobs();
			Functions.showMessage(
					"Se agrego Correctamente",
					"Exito", Mensajes.INFO);
			Functions.manageDialog("dlgJob", 1);
		}else{
			Functions.showMessage(
					"Error al insertar el registro",
					"Ups", Mensajes.ERROR);
			
		}
		
	}
	
	
	public void editJob(){
		
		loJobDAO             = new JobDAO();
		JobsBean loJobsBean  = new JobsBean();
		String   lsTime      = lsFormatter.format(new Date(this.lsTiempo));
		String[] lsplitTime  = lsTime.split(":");
		
		loJobsBean.setPiIdCasino(this.linIdCasino);
		loJobsBean.setPiIdJob(this.linIdJob );
		loJobsBean.setPsNomCasino(this.lsNomCasino);
		loJobsBean.setPsHora(lsplitTime[0]);
		loJobsBean.setPsMinuto(lsplitTime[1]);
		loJobsBean.setPsCodSemana(convertSemanaArrayToString(this.laSemana));
		
		boolean lbPaso = loJobDAO.editJob(loJobsBean);
		if(lbPaso){
			getListJobs();
			Functions.showMessage(
					"Registro Actualizado Correctamente",
					"Exito", Mensajes.INFO);
			Functions.manageDialog("dlgEditJob", 1);
		}else{
			Functions.showMessage(
					"Error al actualizar el registro",
					"Ups", Mensajes.ERROR);
			
		}
	}
	
	public void deleteJob(JobsBean loJobsBean){
		loJobDAO             = new JobDAO();
		
//		loJobsBean.setPiIdCasino(this.linIdCasino);
//		loJobsBean.setPiIdJob   (this.linIdJob );
		boolean lbPaso = loJobDAO.deleteJobCasino(loJobsBean);
		if(lbPaso){
			getListJobs();
			Functions.showMessage(
					"Registro Eliminado Correctamente",
					"Exito", Mensajes.INFO);
			Functions.manageDialog("dlgEditJob", 1);
		}else{
			Functions.showMessage(
					"Error al eliminar el registro",
					"Ups", Mensajes.ERROR);
			
		}
	}
	
	/**
	 * Convierte la semana de array a una cadena String
	 * @param pssemana semana en arreglo
	 * @return cadena de texto de las semanas
	 */
	private String convertSemanaArrayToString(String[] pssemana){
		
		StringBuilder stb    = new StringBuilder();
		for(String sem : this.laSemana){
			logger.debug("Dia de la semana: "+sem);
			stb.append(sem);
			stb.append(",");
		}
		if(stb.length() > 0){
			stb.delete(stb.length()-1, stb.length());
		}
		return stb.toString();
	}
	


	public List<JobsBean> getLoListJobs() {
		return loListJobs;
	}


	public void setLoListJobs(List<JobsBean> loListJobs) {
		this.loListJobs = loListJobs;
	}


	public int getLinIdJob() {
		return linIdJob;
	}


	public void setLinIdJob(int linIdJob) {
		this.linIdJob = linIdJob;
	}


	public String getLsNomCasino() {
		return lsNomCasino;
	}


	public void setLsNomCasino(String lsNomCasino) {
		this.lsNomCasino = lsNomCasino;
	}


	public Date getLtHoraMinutos() {
		return ltHoraMinutos;
	}


	public void setLtHoraMinutos(Date ltHoraMinutos) {
		this.ltHoraMinutos = ltHoraMinutos;
	}


	public String[] getLaSemana() {
		return laSemana;
	}


	public void setLaSemana(String[] laSemana) {
		this.laSemana = laSemana;
	}


	public List<String> getSelectSemanas() {
		return selectSemanas;
	}


	public void setSelectSemanas(List<String> selectSemanas) {
		this.selectSemanas = selectSemanas;
	}


	public int getLinIdCasino() {
		return linIdCasino;
	}


	public void setLinIdCasino(int linIdCasino) {
		this.linIdCasino = linIdCasino;
	}


	public String getLsTiempo() {
		return lsTiempo;
	}


	public void setLsTiempo(String lsTiempo) {
		this.lsTiempo = lsTiempo;
	}


	public String getLsCodSemana() {
		return lsCodSemana;
	}


	public void setLsCodSemana(String lsCodSemana) {
		this.lsCodSemana = lsCodSemana;
	}


	public List<JobDAO> getFilteredJobs() {
		return filteredJobs;
	}


	public void setFilteredJobs(List<JobDAO> filteredJobs) {
		this.filteredJobs = filteredJobs;
	}

	
     	
	
	
	
}