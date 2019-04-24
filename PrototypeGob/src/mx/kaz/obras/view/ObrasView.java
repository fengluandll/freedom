package mx.kaz.obras.view;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;

import mx.kaz.beans.PersonalBean;
import mx.kaz.daos.ProyectDAO;
import mx.kaz.obras.beans.EmpresasObrasBean;
import mx.kaz.obras.beans.ObrasBean;
import mx.kaz.obras.daos.ObrasDAO;
import mx.kaz.util.FuncionesGenerales;
import mx.kaz.util.ManejoFechas;
import mx.kaz.util.Mensajes;

/**
 * @author Argumel
 *
 */

@ManagedBean
@ViewScoped

public class ObrasView {

	List<ObrasBean> listObras;
	ObrasDAO obrasDAO;
	ProyectDAO proyectDAO;
	FacesContext facesContext;
	HttpSession session;
	private List<ObrasDAO> filteredObra;
	private int idBuilds;
	private int idPort;
	private String buildDescription;
	private int idTypeProcedure;
	private String tipoProce;
	private Date fecContractIni;
	private Date fecContractFin;
	private BigDecimal amountHire;
	private BigDecimal contractedAmount;
	private String providerName;
	private String resourceType;
	private Date fecCompranet;
	private Date fecClarification_meeting;
	private Date fecPresentationsTender;
	private Date fecFail;
	private Date fecFirmacontrato;
	private int tipoArt;
	private int tipoFrac;
	private String numAcuerdo;
	private String apareceFecFirmaCont	= "false";
	private String apareceAcuerdo 		= "false";
	private String apareceEmpresas 		= "false";
	private String requeridoCompranet 	= "true";
	private String requeridoPrePro 		= "true";
	private String requeridoJAclaraciones	= "true";
	private String requeridoFallo 			= "true";
	private boolean disabledCompranet 		= false;
	private boolean disabledPrePro 			= false;
	private boolean disabledJAclaraciones	= false;
	private boolean disabledFallo 			= false;
	// Nuevo para empresas
	private String nombreRazonSocial;
	private String repLegal;
	private BigDecimal montoOfertado;
	private List<EmpresasObrasBean> listEmpresas;
	private int idEmpresas;

	private Map<String, Integer> articulos = new HashMap<String, Integer>();
	Map<String, Integer> fraccionesMap = new HashMap<String, Integer>();
	private org.apache.log4j.Logger logger = Logger.getLogger(ObrasView.class);
	ObrasBean obrasBean;
	boolean abrirArt = true;
	boolean abrirFrac = true;
	boolean desMonto;
	private String razonSocial;

	public ObrasView() {
		listEmpresas = new ArrayList<>();
		obrasDAO = new ObrasDAO();
		facesContext = FacesContext.getCurrentInstance();
		session = (HttpSession) facesContext.getExternalContext().getSession(true);
		listaObras();
		obtenArtFrac();
		this.tipoArt = 0;
		this.tipoFrac = 0;
		if (this.fecFail == null) {
			this.desMonto = true;
		}
	}

	public String regresar() {
		return "regresar";
	}

	public void guardaEmpresas() {

		EmpresasObrasBean emp = new EmpresasObrasBean();
		emp.setNomEmpresa(this.nombreRazonSocial);
		emp.setRepLegal(this.repLegal);
		emp.setMontoOfertado(this.montoOfertado);
		listEmpresas.add(emp);
		this.nombreRazonSocial = "";
		this.repLegal = "";
		this.montoOfertado = null;
		RequestContext.getCurrentInstance().update("tblEmp");
		//return "refrescaEmpresas";
	}

	public void getEmpresas() {

	}

	public void abreNvaObra() {
	/*	obrasBean = new ObrasBean();
		this.buildDescription = obrasBean.getBuildDescription();
		this.idTypeProcedure = obrasBean.getIdTypeProcedure();
		this.fecContractIni = obrasBean.getFecContractIni();
		this.fecContractFin = obrasBean.getFecContractFin();
		this.resourceType = obrasBean.getResourceType();
		this.fecCompranet = obrasBean.getFecCompranet();
		this.fecFail = obrasBean.getFecFail();
		this.tipoArt = obrasBean.getIdArticulo();
		this.tipoFrac = obrasBean.getIdFraccion();
		this.amountHire = obrasBean.getAmountHire();
		this.contractedAmount = obrasBean.getContractedAmount();
		this.providerName = obrasBean.getProviderName();
		this.fecClarification_meeting = obrasBean.getFecClarification_meeting();
		this.fecPresentationsTender = obrasBean.getFecPresentationsTender();
		this.razonSocial = obrasBean.getRazonSocial();
		this.numAcuerdo = obrasBean.getSegment1();*/

		 abrirFrac = true;
		 abrirArt = true;
		 RequestContext.getCurrentInstance().update("id_obra");
		 RequestContext.getCurrentInstance().update("cmbArt");
		 RequestContext.getCurrentInstance().update("msgAlert");
		 FuncionesGenerales.manageDialog("dlgObra", 2);
		//return "nuevaContratacion";
	}

	public void activaMontoProvedor() {
		this.desMonto = false;
		RequestContext.getCurrentInstance().update("id_contractedAmount");
		RequestContext.getCurrentInstance().update("id_nomProve");
	}

	public void mostrarLeyenda() {
		if (this.tipoFrac == 3 || this.tipoFrac == 5 || this.tipoFrac == 10 || this.tipoFrac == 11
				|| this.tipoFrac == 12 || this.tipoFrac == 16 || this.tipoFrac == 17 || this.tipoFrac == 18
				|| this.tipoFrac == 19 || this.tipoFrac == 20 || this.tipoFrac == 21) {
			String frase = "REQUIERE AUTORIZACIÓN DEL COMITÉ DE ADQUISICIONES, ARRENDAMIENTOS Y SERVICIOS.";
			FuncionesGenerales.mostrarMensaje("Atención", frase, Mensajes.WARN);
			RequestContext.getCurrentInstance().update("msgAlert");
			apareceAcuerdo = "true";
			RequestContext.getCurrentInstance().update("pnlAcuerdo1");
			RequestContext.getCurrentInstance().update("pnlAcuerdo2");
			RequestContext.getCurrentInstance().update("pnlAcuerdo3");
			RequestContext.getCurrentInstance().update("pnlAcuerdo4");
		} else {
			apareceAcuerdo = "false";
			RequestContext.getCurrentInstance().update("pnlAcuerdo1");
			RequestContext.getCurrentInstance().update("pnlAcuerdo2");
			RequestContext.getCurrentInstance().update("pnlAcuerdo3");
			RequestContext.getCurrentInstance().update("pnlAcuerdo4");
			RequestContext.getCurrentInstance().update("msgAlert");
		}
	}

	public void mostrar() {

		if (this.idTypeProcedure == 2 || this.idTypeProcedure == 3) {
			abrirArt = false;
			apareceFecFirmaCont = "false";
			this.fecFirmacontrato = null;
			RequestContext.getCurrentInstance().update("cmbArt");
			RequestContext.getCurrentInstance().update("cmbArt_act");
			RequestContext.getCurrentInstance().update("pnlFimraContraNuevo");
			RequestContext.getCurrentInstance().update("pnlFimraContraNuevo2");
			RequestContext.getCurrentInstance().update("pnlFimraContraNuevo3");
			RequestContext.getCurrentInstance().update("pnlFimraContraNuevo4");

			if (this.tipoArt == 1) {
				abrirFrac = false;
				requeridoCompranet 		 = "true";
				requeridoPrePro		 	 = "true";
				requeridoJAclaraciones	 = "true";
				requeridoFallo			 = "true";
				
				disabledCompranet		 = false;
				disabledPrePro			 = false;
				disabledJAclaraciones	 = false;
				disabledFallo			 = false;
				RequestContext.getCurrentInstance().update("idFecCompranet");
				RequestContext.getCurrentInstance().update("idJunAclaraciones");
				RequestContext.getCurrentInstance().update("idPresPropuestas");
				RequestContext.getCurrentInstance().update("id_fecFallo");
				//Actualizacion
				RequestContext.getCurrentInstance().update("idFecCompranet_2");
				RequestContext.getCurrentInstance().update("idJunAclaraciones_2");
				RequestContext.getCurrentInstance().update("idPresPropuestas_2");
				RequestContext.getCurrentInstance().update("id_fecFallo_2");
				if (this.tipoFrac == 3 || this.tipoFrac == 5 || this.tipoFrac == 10 || this.tipoFrac == 11
						|| this.tipoFrac == 12 || this.tipoFrac == 16 || this.tipoFrac == 17 || this.tipoFrac == 18
						|| this.tipoFrac == 19 || this.tipoFrac == 20 || this.tipoFrac == 21) {
					apareceAcuerdo = "true";
				} else {
					apareceAcuerdo = "false";
				}
				RequestContext.getCurrentInstance().update("cmbFra");
				RequestContext.getCurrentInstance().update("cmbFra_act");

			} else if (this.tipoArt == 2) {
				abrirFrac = true;
				apareceFecFirmaCont = "false";
				this.fecFirmacontrato = null;
				apareceAcuerdo = "false";
				requeridoCompranet 		 = "true";
				requeridoPrePro		 	 = "true";
				requeridoJAclaraciones	 = "true";
				requeridoFallo			 = "true";
				disabledCompranet		 = false;
				disabledPrePro			 = false;
				disabledJAclaraciones	 = false;
				disabledFallo			 = false;
				RequestContext.getCurrentInstance().update("pnlFimraContraNuevo");
				RequestContext.getCurrentInstance().update("pnlFimraContraNuevo2");
				RequestContext.getCurrentInstance().update("pnlFimraContraNuevo3");
				RequestContext.getCurrentInstance().update("pnlFimraContraNuevo4");
				RequestContext.getCurrentInstance().update("idFecCompranet");
				RequestContext.getCurrentInstance().update("idJunAclaraciones");
				RequestContext.getCurrentInstance().update("idPresPropuestas");
				RequestContext.getCurrentInstance().update("id_fecFallo");
				//Actualizacion
				RequestContext.getCurrentInstance().update("idFecCompranet_2");
				RequestContext.getCurrentInstance().update("idJunAclaraciones_2");
				RequestContext.getCurrentInstance().update("idPresPropuestas_2");
				RequestContext.getCurrentInstance().update("id_fecFallo_2");
				actualizaGral1();
			} else if (this.tipoArt == 23) {
				apareceFecFirmaCont = "true";
				abrirFrac = true;
				apareceAcuerdo = "false";
				requeridoCompranet = "false";
				requeridoPrePro		 	 = "false";
				requeridoJAclaraciones	 = "false";
				requeridoFallo			 = "false";
				disabledCompranet		 = true;
				disabledPrePro			 = true;
				disabledJAclaraciones	 = true;
				disabledFallo			 = true;
				this.fecCompranet        = null;
				this.fecClarification_meeting = null;
				this.fecPresentationsTender   = null;
				this.fecFail  				  = null;
				RequestContext.getCurrentInstance().update("pnlFimraContraNuevo");
				RequestContext.getCurrentInstance().update("pnlFimraContraNuevo2");
				RequestContext.getCurrentInstance().update("pnlFimraContraNuevo3");
				RequestContext.getCurrentInstance().update("pnlFimraContraNuevo4");
				RequestContext.getCurrentInstance().update("idFecCompranet");
				RequestContext.getCurrentInstance().update("idJunAclaraciones");
				RequestContext.getCurrentInstance().update("idPresPropuestas");
				RequestContext.getCurrentInstance().update("id_fecFallo");
				//Actualizacion
				RequestContext.getCurrentInstance().update("idFecCompranet_2");
				RequestContext.getCurrentInstance().update("idJunAclaraciones_2");
				RequestContext.getCurrentInstance().update("idPresPropuestas_2");
				RequestContext.getCurrentInstance().update("id_fecFallo_2");
				actualizaGral1();
			}
		} else if (this.idTypeProcedure == 1) {
			apareceAcuerdo = "false";
			apareceFecFirmaCont = "false";
			this.fecFirmacontrato = null;
			requeridoCompranet 		 = "true";
			requeridoPrePro		 	 = "true";
			requeridoJAclaraciones	 = "true";
			requeridoFallo			 = "true";
			disabledCompranet		 = false;
			disabledPrePro			 = false;
			disabledJAclaraciones	 = false;
			disabledFallo			 = false;
			abrirArt = true;
			abrirFrac = true;
			RequestContext.getCurrentInstance().update("cmbArt");
			RequestContext.getCurrentInstance().update("cmbArt_act");
			RequestContext.getCurrentInstance().update("cmbFra");
			RequestContext.getCurrentInstance().update("cmbFra_act");
			RequestContext.getCurrentInstance().update("idFecCompranet");
			RequestContext.getCurrentInstance().update("idJunAclaraciones");
			RequestContext.getCurrentInstance().update("idPresPropuestas");
			RequestContext.getCurrentInstance().update("id_fecFallo");
			//Actualizacion
			RequestContext.getCurrentInstance().update("idFecCompranet_2");
			RequestContext.getCurrentInstance().update("idJunAclaraciones_2");
			RequestContext.getCurrentInstance().update("idPresPropuestas_2");
			RequestContext.getCurrentInstance().update("id_fecFallo_2");
		}

	}

	private void actualizaGral1() {

		abrirFrac = true;
		apareceAcuerdo = "false";
		RequestContext.getCurrentInstance().update("pnlAcuerdo1");
		RequestContext.getCurrentInstance().update("pnlAcuerdo2");
		RequestContext.getCurrentInstance().update("pnlAcuerdo3");
		RequestContext.getCurrentInstance().update("pnlAcuerdo4");
		RequestContext.getCurrentInstance().update("cmbFra");
		RequestContext.getCurrentInstance().update("cmbFra_act");

		RequestContext.getCurrentInstance().update("msgAlert");
	}

	private void listaObras() {

		String nomPto = (String) session.getAttribute("nomPto");
		proyectDAO = new ProyectDAO();
		int idPort = proyectDAO.obtenIdPto(nomPto);
		listObras = obrasDAO.obtenObras(idPort);
	}

	
	public String abreEmpresas(ObrasBean obrasBean){
		session.setAttribute("obrasBeanSession", obrasBean);
		return "nuevasEmpresas";
	}
	public void editarProject(ObrasBean obrasBean) {
		//ObrasDAO obrasDAO = new ObrasDAO();
		this.idBuilds = obrasBean.getIdBuilds();
		this.buildDescription = obrasBean.getBuildDescription();
		this.idTypeProcedure = obrasBean.getIdTypeProcedure();
		this.fecContractIni = obrasBean.getFecContractIni();
		this.fecContractFin = obrasBean.getFecContractFin();
		// this.resourceType = obrasBean.getResourceType();
		this.resourceType = obrasBean.getResourceTypeNum();
		this.fecCompranet = obrasBean.getFecCompranet();
		this.fecFail = obrasBean.getFecFail();
		this.tipoArt = obrasBean.getIdArticulo();
		this.tipoFrac = obrasBean.getIdFraccion();
		this.amountHire = obrasBean.getAmountHire();
		this.contractedAmount = obrasBean.getContractedAmount();
		this.providerName = obrasBean.getProviderName();
		this.fecClarification_meeting = obrasBean.getFecClarification_meeting();
		this.fecPresentationsTender = obrasBean.getFecPresentationsTender();
		this.razonSocial = obrasBean.getRazonSocial();
		this.numAcuerdo = obrasBean.getSegment1();
		mostrar();
		//this.listEmpresas = obrasDAO.getEmpresasPropuestas(idBuilds);
		//return "editaContratacion";
	}

	public void actualizaObra() {
		obrasBean = new ObrasBean();
		obrasBean.setIdBuilds(this.idBuilds);
		obrasBean.setBuildDescription(this.buildDescription);
		obrasBean.setIdTypeProcedure(this.idTypeProcedure);
		obrasBean.setFecContractIni(ManejoFechas.utilDateToslDate(this.fecContractIni));
		obrasBean.setFecContractFin(ManejoFechas.utilDateToslDate(this.fecContractFin));
		obrasBean.setAmountHire(this.amountHire);
		obrasBean.setContractedAmount(this.contractedAmount);
		obrasBean.setProviderName(this.providerName);
		obrasBean.setResourceType(this.resourceType);
		obrasBean.setFecCompranet(this.fecCompranet == null ? null : ManejoFechas.utilDateToslDate(this.fecCompranet));
		obrasBean.setFecClarification_meeting(this.fecClarification_meeting!=null?ManejoFechas.utilDateToslDate(this.fecClarification_meeting):null);
		obrasBean.setFecPresentationsTender(this.fecPresentationsTender!=null?ManejoFechas.utilDateToslDate(this.fecPresentationsTender):null);
		obrasBean.setFecFail(this.fecFail != null ? ManejoFechas.utilDateToslDate(this.fecFail) : null);
		obrasBean.setIdArticulo(this.tipoArt);
		obrasBean.setIdFraccion(this.tipoFrac);
		obrasBean.setRazonSocial(this.razonSocial);
		obrasBean.setFecFirmaContratacion(this.fecFirmacontrato!=null?ManejoFechas.utilDateToslDate(this.fecFirmacontrato):null);
		mostrar();
		
		if (((this.contractedAmount.intValue() > 0) || (!this.providerName.equals(""))) && (this.fecFail == null) && this.tipoArt != 23) {
			FuncionesGenerales.mostrarMensaje(
					"Si no existe el FALLO NO podras Capturar " + "el NOMBRE DEL PROVEEEDOR o MONTO CONTRATADO",
					"Atención", Mensajes.WARN);
		} else if ((this.fecFail != null) && (this.providerName.equals(""))) {
			FuncionesGenerales.mostrarMensaje(
					"Es requerido capturar el nombre del proveedor cuando ya se tiene la fecha de fallo", "Atención",
					Mensajes.WARN);
		} else if (this.numAcuerdo != null && (this.numAcuerdo.equals("0") || this.numAcuerdo.startsWith("-"))) {
			FuncionesGenerales.mostrarMensaje("El No. Acuerdo debe ser mayor a cero ", "Atención", Mensajes.WARN);
		} else {

			if (this.tipoArt == 2) {
				obrasBean.setSegment1(null);
			} else {
				if (this.tipoFrac == 4 || this.tipoFrac == 6 || this.tipoFrac == 7 || this.tipoFrac == 8
						|| this.tipoFrac == 9 || this.tipoFrac == 9 || this.tipoFrac == 13 || this.tipoFrac == 14
						|| this.tipoFrac == 15 || this.tipoFrac == 22) {
					obrasBean.setSegment1(null);
				} else {
					obrasBean.setSegment1(this.numAcuerdo);
				}
			}

			boolean bandera = obrasDAO.actualizaObra(obrasBean);
			if (bandera) {
				FuncionesGenerales.mostrarMensaje("Contratación actualizada con Exito", "Exito", Mensajes.INFO);
				FuncionesGenerales.manageDialog("dlgObraActua", 1);
				listaObras();
				RequestContext.getCurrentInstance().update("tblObra");
			}
		}

	}

/*  Metodo donde guardaba y pasaba a la siguiente pagina intento
	public String guardaObra() throws IOException {

		if (((this.contractedAmount.intValue() > 0) || (!this.providerName.equals(""))) && (this.fecFail == null)) {
			FuncionesGenerales.mostrarMensaje(
					"Si no existe el FALLO NO podras Capturar " + "el NOMBRE DEL PROVEEEDOR o MONTO CONTRATADO",
					"Atención", Mensajes.WARN);
		} else if ((this.fecFail != null) && (this.providerName.equals(""))) {
			FuncionesGenerales.mostrarMensaje(
					"Es requerido capturar el nombre del proveedor cuando ya se tiene la fecha de fallo", "Atención",
					Mensajes.WARN);
		} else if (this.numAcuerdo != null && (this.numAcuerdo.equals("0") || this.numAcuerdo.startsWith("-"))) {
			FuncionesGenerales.mostrarMensaje("El No. Acuerdo debe ser mayor a cero ", "Atención", Mensajes.WARN);
		} else {

			obrasBean = new ObrasBean();
			String nomPto = (String) session.getAttribute("nomPto");
			if (nomPto != null) {
				obrasBean.setIdPort(proyectDAO.obtenIdPto(nomPto));
			} else {
				PersonalBean personalBean = (PersonalBean) session.getAttribute("personalBean");
				obrasBean.setIdPort(personalBean.getSegment6());
			}

			obrasBean.setBuildDescription(this.buildDescription);
			obrasBean.setIdTypeProcedure(this.idTypeProcedure);
			obrasBean.setFecContractIni(ManejoFechas.utilDateToslDate(this.fecContractIni));
			obrasBean.setFecContractFin(ManejoFechas.utilDateToslDate(this.fecContractFin));
			obrasBean.setAmountHire(this.amountHire);
			obrasBean.setContractedAmount(this.contractedAmount);
			obrasBean.setProviderName(this.providerName);
			obrasBean.setResourceType(this.resourceType);
			obrasBean.setFecCompranet(
					this.fecCompranet == null ? null : ManejoFechas.utilDateToslDate(this.fecCompranet));
			obrasBean.setFecClarification_meeting(ManejoFechas.utilDateToslDate(this.fecClarification_meeting));
			obrasBean.setFecPresentationsTender(ManejoFechas.utilDateToslDate(this.fecPresentationsTender));
			obrasBean.setFecFail(this.fecFail != null ? ManejoFechas.utilDateToslDate(this.fecFail) : null);
			obrasBean.setIdArticulo(this.tipoArt);
			obrasBean.setIdFraccion(this.tipoFrac);
			obrasBean.setRazonSocial(this.razonSocial);
			obrasBean.setSegment1(this.numAcuerdo);

			boolean bandera = obrasDAO.insertObra(obrasBean);
			if (bandera) {
				int idBuild = obrasDAO.selectUtimaObra(obrasBean.getIdPort(), obrasBean.getBuildDescription());
				if(!this.getListEmpresas().isEmpty()){
					boolean okInsert = obrasDAO.insertEmpresa(this.getListEmpresas(), idBuild);
				}
				
				//if (okInsert) {
					FuncionesGenerales.mostrarMensaje("Contratación agregada con Exito", "Exito", Mensajes.INFO);
					FuncionesGenerales.manageDialog("dlgObra", 1);
					//FacesContext.getCurrentInstance().getExternalContext().redirect("registroObra.xhtml");
					apareceEmpresas = "true";
					RequestContext.getCurrentInstance().update("pnlEmpresas");
					return "refrescaEmpresas";
				//}

				// listaObras();
				/*
				 * RequestContext.getCurrentInstance().update("tblObra");
				 * this.idBuilds = 0; this.buildDescription = "";
				 * this.idTypeProcedure = 0; this.fecContractIni = null;
				 * this.fecContractFin = null; this.amountHire = null;
				 * this.contractedAmount = null; this.providerName = null;
				 * this.resourceType = null; this.fecCompranet = null;
				 * this.fecClarification_meeting = null;
				 * this.fecPresentationsTender = null; this.fecFail = null;
				 * this.tipoArt = 0; this.tipoFrac = 0; this.numAcuerdo = "";
				 */

			//}
	//	}
	//	return "";
	//}
	public void guardaObra() throws IOException {

		if (((this.contractedAmount.intValue() > 0) || (!this.providerName.equals(""))) && (this.fecFail == null) && this.tipoArt != 23) {
			FuncionesGenerales.mostrarMensaje(
					"Si no existe el FALLO NO podras Capturar " + "el NOMBRE DEL PROVEEEDOR o MONTO CONTRATADO",
					"Atención", Mensajes.WARN);
		} else if ((this.fecFail != null) && (this.providerName.equals(""))) {
			FuncionesGenerales.mostrarMensaje(
					"Es requerido capturar el nombre del proveedor cuando ya se tiene la fecha de fallo", "Atención",
					Mensajes.WARN);
		} else if (this.numAcuerdo != null && (this.numAcuerdo.equals("0") || this.numAcuerdo.startsWith("-"))) {
			FuncionesGenerales.mostrarMensaje("El No. Acuerdo debe ser mayor a cero ", "Atención", Mensajes.WARN);
		} else {

			obrasBean = new ObrasBean();
			String nomPto = (String) session.getAttribute("nomPto");
			if (nomPto != null) {
				obrasBean.setIdPort(proyectDAO.obtenIdPto(nomPto));
			} else {
				PersonalBean personalBean = (PersonalBean) session.getAttribute("personalBean");
				obrasBean.setIdPort(personalBean.getSegment6());
			}

			obrasBean.setBuildDescription(this.buildDescription);
			obrasBean.setIdTypeProcedure(this.idTypeProcedure);
			obrasBean.setFecContractIni(ManejoFechas.utilDateToslDate(this.fecContractIni));
			obrasBean.setFecContractFin(ManejoFechas.utilDateToslDate(this.fecContractFin));
			obrasBean.setAmountHire(this.amountHire);
			obrasBean.setContractedAmount(this.contractedAmount);
			obrasBean.setProviderName(this.providerName);
			obrasBean.setResourceType(this.resourceType);
			obrasBean.setFecCompranet(
					this.fecCompranet == null ? null : ManejoFechas.utilDateToslDate(this.fecCompranet));
			obrasBean.setFecClarification_meeting(this.fecClarification_meeting==null?null:ManejoFechas.utilDateToslDate(this.fecClarification_meeting));
			obrasBean.setFecPresentationsTender(this.fecPresentationsTender==null?null:ManejoFechas.utilDateToslDate(this.fecPresentationsTender));
			obrasBean.setFecFail(this.fecFail != null ? ManejoFechas.utilDateToslDate(this.fecFail) : null);
			obrasBean.setIdArticulo(this.tipoArt);
			obrasBean.setIdFraccion(this.tipoFrac);
			obrasBean.setRazonSocial(this.razonSocial);
			obrasBean.setSegment1(this.numAcuerdo);
			obrasBean.setFecFirmaContratacion(this.fecFirmacontrato!=null?ManejoFechas.utilDateToslDate(this.fecFirmacontrato):null);

			boolean bandera = obrasDAO.insertObra(obrasBean);
			if (bandera) {
				FuncionesGenerales.mostrarMensaje("Obra agregada con Exito", "Exito", Mensajes.INFO);
				FuncionesGenerales.manageDialog("dlgObra", 1);
				listaObras();
				RequestContext.getCurrentInstance().update("tblObra");
				this.idBuilds       	= 0;
				this.buildDescription   = "";
				this.idTypeProcedure	= 0;
				this.fecContractIni		= null;
				this.fecContractFin		= null;
				this.amountHire			= null;
				this.contractedAmount	= null;
				this.providerName		= null;
				this.resourceType		= null;
				this.fecCompranet		= null;
				this.fecClarification_meeting = null;
				this.fecPresentationsTender = null;
				this.fecFail			= null;
				this.tipoArt			= 0;
				this.tipoFrac			= 0;
				this.fecFirmacontrato   = null;

			}
		}
		
	}


	public void obtenArtFrac() {

		articulos = obrasDAO.obtenArticulosCombo();
		fraccionesMap = obrasDAO.obtenFraccionesCombo();
	}

	public List<ObrasBean> getListObras() {
		return listObras;
	}

	public void setListObras(List<ObrasBean> listObras) {
		this.listObras = listObras;
	}

	public List<ObrasDAO> getFilteredObra() {
		return filteredObra;
	}

	public void setFilteredObra(List<ObrasDAO> filteredObra) {
		this.filteredObra = filteredObra;
	}

	public ObrasDAO getObrasDAO() {
		return obrasDAO;
	}

	public void setObrasDAO(ObrasDAO obrasDAO) {
		this.obrasDAO = obrasDAO;
	}

	public ProyectDAO getProyectDAO() {
		return proyectDAO;
	}

	public void setProyectDAO(ProyectDAO proyectDAO) {
		this.proyectDAO = proyectDAO;
	}

	public int getIdBuilds() {
		return idBuilds;
	}

	public void setIdBuilds(int idBuilds) {
		this.idBuilds = idBuilds;
	}

	public int getIdPort() {
		return idPort;
	}

	public void setIdPort(int idPort) {
		this.idPort = idPort;
	}

	public String getBuildDescription() {
		return buildDescription;
	}

	public void setBuildDescription(String buildDescription) {
		this.buildDescription = buildDescription;
	}

	public int getIdTypeProcedure() {
		return idTypeProcedure;
	}

	public void setIdTypeProcedure(int idTypeProcedure) {
		this.idTypeProcedure = idTypeProcedure;
	}

	public String getTipoProce() {
		return tipoProce;
	}

	public void setTipoProce(String tipoProce) {
		this.tipoProce = tipoProce;
	}

	public Date getFecContractIni() {
		return fecContractIni;
	}

	public void setFecContractIni(Date fecContractIni) {
		this.fecContractIni = fecContractIni;
	}

	public Date getFecContractFin() {
		return fecContractFin;
	}

	public void setFecContractFin(Date fecContractFin) {
		this.fecContractFin = fecContractFin;
	}

	public BigDecimal getAmountHire() {
		return amountHire;
	}

	public void setAmountHire(BigDecimal amountHire) {
		this.amountHire = amountHire;
	}

	public BigDecimal getContractedAmount() {
		return contractedAmount;
	}

	public void setContractedAmount(BigDecimal contractedAmount) {
		this.contractedAmount = contractedAmount;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public Date getFecCompranet() {
		return fecCompranet;
	}

	public void setFecCompranet(Date fecCompranet) {
		this.fecCompranet = fecCompranet;
	}

	public Date getFecClarification_meeting() {
		return fecClarification_meeting;
	}

	public void setFecClarification_meeting(Date fecClarification_meeting) {
		this.fecClarification_meeting = fecClarification_meeting;
	}

	public Date getFecPresentationsTender() {
		return fecPresentationsTender;
	}

	public void setFecPresentationsTender(Date fecPresentationsTender) {
		this.fecPresentationsTender = fecPresentationsTender;
	}

	public Date getFecFail() {
		return fecFail;
	}

	public void setFecFail(Date fecFail) {
		this.fecFail = fecFail;
	}

	public int getTipoArt() {
		return tipoArt;
	}

	public void setTipoArt(int tipoArt) {
		this.tipoArt = tipoArt;
	}

	public Map<String, Integer> getArticulos() {
		return articulos;
	}

	public void setArticulos(Map<String, Integer> articulos) {
		this.articulos = articulos;
	}

	public boolean isAbrirArt() {
		return abrirArt;
	}

	public void setAbrirArt(boolean abrirArt) {
		this.abrirArt = abrirArt;
	}

	public Map<String, Integer> getFraccionesMap() {
		return fraccionesMap;
	}

	public void setFraccionesMap(Map<String, Integer> fraccionesMap) {
		this.fraccionesMap = fraccionesMap;
	}

	public int getTipoFrac() {
		return tipoFrac;
	}

	public void setTipoFrac(int tipoFrac) {
		this.tipoFrac = tipoFrac;
	}

	public boolean isAbrirFrac() {
		return abrirFrac;
	}

	public void setAbrirFrac(boolean abrirFrac) {
		this.abrirFrac = abrirFrac;
	}

	public boolean isDesMonto() {
		return desMonto;
	}

	public void setDesMonto(boolean desMonto) {
		this.desMonto = desMonto;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getNumAcuerdo() {
		return numAcuerdo;
	}

	public void setNumAcuerdo(String numAcuerdo) {
		this.numAcuerdo = numAcuerdo;
	}

	public String getApareceAcuerdo() {
		return apareceAcuerdo;
	}

	public void setApareceAcuerdo(String apareceAcuerdo) {
		this.apareceAcuerdo = apareceAcuerdo;
	}

	public String getRequeridoCompranet() {
		return requeridoCompranet;
	}

	public void setRequeridoCompranet(String requeridoCompranet) {
		this.requeridoCompranet = requeridoCompranet;
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

	public List<EmpresasObrasBean> getListEmpresas() {
		return listEmpresas;
	}

	public void setListEmpresas(List<EmpresasObrasBean> listEmpresas) {
		this.listEmpresas = listEmpresas;
	}

	public int getIdEmpresas() {
		return idEmpresas;
	}

	public void setIdEmpresas(int idEmpresas) {
		this.idEmpresas = idEmpresas;
	}

	public String getApareceEmpresas() {
		return apareceEmpresas;
	}

	public void setApareceEmpresas(String apareceEmpresas) {
		this.apareceEmpresas = apareceEmpresas;
	}

	public String getRequeridoPrePro() {
		return requeridoPrePro;
	}

	public void setRequeridoPrePro(String requeridoPrePro) {
		this.requeridoPrePro = requeridoPrePro;
	}

	public String getRequeridoJAclaraciones() {
		return requeridoJAclaraciones;
	}

	public void setRequeridoJAclaraciones(String requeridoJAclaraciones) {
		this.requeridoJAclaraciones = requeridoJAclaraciones;
	}

	public String getRequeridoFallo() {
		return requeridoFallo;
	}

	public void setRequeridoFallo(String requeridoFallo) {
		this.requeridoFallo = requeridoFallo;
	}

	public boolean isDisabledCompranet() {
		return disabledCompranet;
	}

	public void setDisabledCompranet(boolean disabledCompranet) {
		this.disabledCompranet = disabledCompranet;
	}

	public boolean isDisabledPrePro() {
		return disabledPrePro;
	}

	public void setDisabledPrePro(boolean disabledPrePro) {
		this.disabledPrePro = disabledPrePro;
	}

	public boolean isDisabledJAclaraciones() {
		return disabledJAclaraciones;
	}

	public void setDisabledJAclaraciones(boolean disabledJAclaraciones) {
		this.disabledJAclaraciones = disabledJAclaraciones;
	}

	public boolean isDisabledFallo() {
		return disabledFallo;
	}

	public void setDisabledFallo(boolean disabledFallo) {
		this.disabledFallo = disabledFallo;
	}

	public Date getFecFirmacontrato() {
		return fecFirmacontrato;
	}

	public void setFecFirmacontrato(Date fecFirmacontrato) {
		this.fecFirmacontrato = fecFirmacontrato;
	}

	public String getApareceFecFirmaCont() {
		return apareceFecFirmaCont;
	}

	public void setApareceFecFirmaCont(String apareceFecFirmaCont) {
		this.apareceFecFirmaCont = apareceFecFirmaCont;
	}
	
	
}
