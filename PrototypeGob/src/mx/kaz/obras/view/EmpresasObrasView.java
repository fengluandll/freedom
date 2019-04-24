package mx.kaz.obras.view;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import mx.kaz.obras.beans.EmpresasObrasBean;
import mx.kaz.obras.beans.ObrasBean;
import mx.kaz.obras.daos.ObrasDAO;
import mx.kaz.util.FuncionesGenerales;
import mx.kaz.util.Mensajes;

@ManagedBean
@ViewScoped
public class EmpresasObrasView implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<EmpresasObrasBean> listEmpresas;
	private String nombreRazonSocial;
	private String repLegal;
	private BigDecimal montoOfertado;
	private int idEmpresas;
	HttpSession session;
	FacesContext facesContext;
	ObrasBean obrasBean;
	ObrasDAO 		  obrasDAO;
	int idBuilds;
	
	
	@PostConstruct
    public void init() {
		this.listEmpresas = obrasDAO.getEmpresasPropuestas(idBuilds);
    }
	
	public EmpresasObrasView(){
		listEmpresas = new ArrayList<>();
		obrasDAO  = new ObrasDAO();
		facesContext = FacesContext.getCurrentInstance();
		session  = (HttpSession) facesContext.getExternalContext().getSession(true);
		obrasBean = (ObrasBean)session.getAttribute("obrasBeanSession");
		//this.listEmpresas = obrasDAO.getEmpresasPropuestas(idBuilds);
		idBuilds = obrasBean.getIdBuilds();
		
	}
	public List<EmpresasObrasBean> dameEmpresas(){
		this.listEmpresas = obrasDAO.getEmpresasPropuestas(idBuilds);
		return this.listEmpresas;
	}
	
	
	public void guardaEmpresas(){
		
		EmpresasObrasBean emp 		= new EmpresasObrasBean();
		if(!this.nombreRazonSocial.equals("") && !this.repLegal.equals("") && this.montoOfertado.intValue() >= 0){
			emp.setNomEmpresa(this.nombreRazonSocial);
			emp.setRepLegal(this.repLegal);
			emp.setMontoOfertado(this.montoOfertado);
			boolean okInsert = obrasDAO.insertEmpresa(emp, obrasBean.getIdBuilds());
			if(okInsert){
				this.nombreRazonSocial = "";
				this.repLegal = "";
				this.montoOfertado = null;
				//RequestContext.getCurrentInstance().update("pnlEmpresas");
				dameEmpresas();
				FuncionesGenerales.mostrarMensaje(
						"Empresa agregada con éxito",
						"Éxito", Mensajes.INFO);
			}
		}else{
			this.montoOfertado = null;
			FuncionesGenerales.mostrarMensaje(
					"Es obligatorio capturar todos los campos",
					"Atención", Mensajes.WARN);
		}
		
		
		
	}
	
	public void onRowEdit(RowEditEvent event) {
		EmpresasObrasBean obj = (EmpresasObrasBean) event.getObject();
		System.out.println(((EmpresasObrasBean) event.getObject()).getNomEmpresa());
		boolean ok = obrasDAO.actualizaEmpresas(obj);
		if(ok){
			FuncionesGenerales.mostrarMensaje(
					"Edición Correcta",
					"OK", Mensajes.INFO);
		}else{
			FuncionesGenerales.mostrarMensaje(
					"Ocurrio un error al actualizar la empresa",
					"Error", Mensajes.ERROR);
		}
        
    }
	
	public void onRowCancel(RowEditEvent event) {
		FuncionesGenerales.mostrarMensaje(
				"Edición cancelada",
				"Atención", Mensajes.WARN);
    }
	
	public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
         
        if(newValue != null && !newValue.equals(oldValue)) {
            //FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            //FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
	
	public void eliminaEmpresa(EmpresasObrasBean be){
		boolean ok = obrasDAO.eliminaEmpresa(be);
		if(ok){
			//RequestContext.getCurrentInstance().update(":tblEmpRow:tblEmp");
			dameEmpresas();
			FuncionesGenerales.mostrarMensaje(
					"Empresa eliminada correctamente",
					"OK", Mensajes.INFO);
		}else{
			FuncionesGenerales.mostrarMensaje(
					"Ocurrio un error al Eliminar la empresa",
					"Error", Mensajes.ERROR);
		}
	}
	
	public String regresar() {
		return "regresar";
	}



	public List<EmpresasObrasBean> getListEmpresas() {
		return listEmpresas;
	}



	public void setListEmpresas(List<EmpresasObrasBean> listEmpresas) {
		this.listEmpresas = listEmpresas;
	}



	public String getNombreRazonSocial() {
		return nombreRazonSocial;
	}



	public void setNombreRazonSocial(String nombreRazonSocial) {
		this.nombreRazonSocial = nombreRazonSocial;
	}



	public String getRepLegal() {
		return repLegal;
	}



	public void setRepLegal(String repLegal) {
		this.repLegal = repLegal;
	}



	public BigDecimal getMontoOfertado() {
		return montoOfertado;
	}



	public void setMontoOfertado(BigDecimal montoOfertado) {
		this.montoOfertado = montoOfertado;
	}





	public int getIdEmpresas() {
		return idEmpresas;
	}



	public void setIdEmpresas(int idEmpresas) {
		this.idEmpresas = idEmpresas;
	}

	public int getIdBuilds() {
		return idBuilds;
	}

	public void setIdBuilds(int idBuilds) {
		this.idBuilds = idBuilds;
	}
	
	
}
