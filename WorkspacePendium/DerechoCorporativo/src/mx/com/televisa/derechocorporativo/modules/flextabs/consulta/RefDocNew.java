package mx.com.televisa.derechocorporativo.modules.flextabs.consulta;

import org.apache.log4j.Logger;

import mx.com.televisa.derechocorporativo.bean.reformas.ActaOtrosBean;
import mx.com.televisa.derechocorporativo.bean.reformas.AumentoCapitalBean;
import mx.com.televisa.derechocorporativo.bean.reformas.ContratosBean;
import mx.com.televisa.derechocorporativo.bean.reformas.DecretoDividendosBean;
import mx.com.televisa.derechocorporativo.bean.reformas.DisminucionCapitalBean;
import mx.com.televisa.derechocorporativo.bean.reformas.EscisionBean;
import mx.com.televisa.derechocorporativo.bean.reformas.EscriturasOtrosBean;
import mx.com.televisa.derechocorporativo.bean.reformas.FusionBean;
import mx.com.televisa.derechocorporativo.bean.reformas.SesionConsejoBean;
import mx.com.televisa.derechocorporativo.daos.ReformasDAO;
import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;

public class RefDocNew {
	private final static Logger log = Logger.getLogger(RefDocNew.class);
	private ReformasDAO reformasDAO;

	public RefDocNew(){
		reformasDAO = new ReformasDAO();
	}

	public String getCamposEscrituraOtros(int tIdMetaRow, ConnectionWrapper connectionWrapper){
		StringBuilder lsEscrituraOtros = new StringBuilder();
		EscriturasOtrosBean escriturasOtrosBean;

		escriturasOtrosBean = reformasDAO.getValuesEscOtros(tIdMetaRow, connectionWrapper);
		
		if(escriturasOtrosBean.getSolPor() == null || escriturasOtrosBean.getSolPor().equals("0"))
			lsEscrituraOtros.append("AND COD_FLEX_COLUM <> 'VAL_C119' ");
		
		if(escriturasOtrosBean.getFechaDocumento() == null)
			lsEscrituraOtros.append("AND COD_FLEX_COLUM <> 'VAL_C11' ");

		if(escriturasOtrosBean.getFechaRecibido() == null)
			lsEscrituraOtros.append("AND COD_FLEX_COLUM <> 'VAL_C12' ");

		if(escriturasOtrosBean.getFolioNum() == null)
			lsEscrituraOtros.append("AND COD_FLEX_COLUM <> 'VAL_C13' ");

		if(escriturasOtrosBean.getNumDocSol() == null)
			lsEscrituraOtros.append("AND COD_FLEX_COLUM <> 'VAL_C14' ");

		if(escriturasOtrosBean.getNumDocDocEnt() == null)
			lsEscrituraOtros.append("AND COD_FLEX_COLUM <> 'VAL_C16' ");

		if(escriturasOtrosBean.getFechaDocumentoE() == null)
			lsEscrituraOtros.append("AND COD_FLEX_COLUM <> 'VAL_C120' ");
		
		if(escriturasOtrosBean.getFechaRecibidoE() == null)
			lsEscrituraOtros.append("AND COD_FLEX_COLUM <> 'VAL_C121' ");
		
		if(escriturasOtrosBean.getAgregDoc() == 0)
			lsEscrituraOtros.append("AND COD_FLEX_COLUM <> 'VAL_C139' ");
		
		if(escriturasOtrosBean.getSolicitud() == null &&
           escriturasOtrosBean.getFechaDocumento() == null &&
           escriturasOtrosBean.getFechaRecibido() == null &&
           escriturasOtrosBean.getFolioNum() == null &&
           escriturasOtrosBean.getNumDocSol() == null 
        )
			lsEscrituraOtros.append("AND COD_FLEX_COLUM <> 	'VAL_C10' ");

        if(escriturasOtrosBean.getDocumentoEntregado() == null &&
           escriturasOtrosBean.getNumDocDocEnt() == null
        )
        	lsEscrituraOtros.append("AND COD_FLEX_COLUM <> 	'VAL_C15' ");
        //TODOS
		if(escriturasOtrosBean.getSolicitud() == null &&
		   (escriturasOtrosBean.getSolPor() == null || escriturasOtrosBean.getSolPor().equals("0")) &&
           escriturasOtrosBean.getFechaDocumento() == null &&
           escriturasOtrosBean.getFechaRecibido() == null &&
           escriturasOtrosBean.getFolioNum() == null &&
           escriturasOtrosBean.getNumDocSol() == null &&
           escriturasOtrosBean.getDocumentoEntregado() == null &&
           escriturasOtrosBean.getNumDocDocEnt() == null &&
           escriturasOtrosBean.getFechaDocumentoE() == null &&
           escriturasOtrosBean.getFechaRecibidoE() == null &&
           escriturasOtrosBean.getAgregDoc() == 0 
        ){
			lsEscrituraOtros.append("AND COD_FLEX_COLUM <> 'VAL_C9' ");
			lsEscrituraOtros.append("AND COD_FLEX_COLUM <> 'VAL_C10' ");
			lsEscrituraOtros.append("AND COD_FLEX_COLUM <> 'VAL_C15' ");
		}

		return lsEscrituraOtros.toString();
	}

	public String getCamposContratos(int tIdMetaRow, ConnectionWrapper connectionWrapper){
		StringBuilder lsContratos = new StringBuilder();
		ContratosBean contratosBean;
		
		contratosBean = reformasDAO.getValuesContratos(tIdMetaRow, connectionWrapper);

		if(contratosBean.getFechaDocumento() == null)
			lsContratos.append("AND COD_FLEX_COLUM <> 'VAL_C21' ");

		if(contratosBean.getSolPor() == null || contratosBean.getSolPor().equals("0"))
			lsContratos.append("AND COD_FLEX_COLUM <> 'VAL_C119' ");
		
		if(contratosBean.getFechaRecibido() == null)
			lsContratos.append("AND COD_FLEX_COLUM <> 'VAL_C22' ");

		if(contratosBean.getFolioNum() == null)
			lsContratos.append("AND COD_FLEX_COLUM <> 'VAL_C23' ");

		if(contratosBean.getNumContContratoEnt() == null)
			lsContratos.append("AND COD_FLEX_COLUM <> 'VAL_C138' ");
		//ULR 25/04/2017 se asigno el nuevo campo insertado para no rehacer el mapeo
		/*if(contratosBean.getNumContContratoEnt() == null)
			lsContratos.append("AND COD_FLEX_COLUM <> 'VAL_C138' ");*/
		
		if(contratosBean.getNumDocSol() == null)
			lsContratos.append("AND COD_FLEX_COLUM <> 'VAL_C24' ");
				
		if(contratosBean.getFechaDocumentoE() == null)
			lsContratos.append("AND COD_FLEX_COLUM <> 'VAL_C120' ");
		
		if(contratosBean.getFechaRecibidoE() == null)
			lsContratos.append("AND COD_FLEX_COLUM <> 'VAL_C121' ");
		
		if(contratosBean.getAgregDoc() == 0)
			lsContratos.append("AND COD_FLEX_COLUM <> 'VAL_C139' ");
		

        if(contratosBean.getSolicitud() == null &&
           contratosBean.getFechaDocumento() == null &&
           contratosBean.getFechaRecibido() == null &&
           contratosBean.getFolioNum() == null &&
           contratosBean.getNumDocSol() == null
        )
        	lsContratos.append("AND COD_FLEX_COLUM <> 'VAL_C20' ");

		//TODOS
		if(contratosBean.getSolicitud() == null &&
		   (contratosBean.getSolPor() == null || contratosBean.getSolPor().equals("0")) &&
           contratosBean.getFechaDocumento() == null &&
           contratosBean.getFechaRecibido() == null &&
           contratosBean.getFolioNum() == null &&
           contratosBean.getNumDocSol() == null &&
           contratosBean.getContratoEntregado() == null &&
           contratosBean.getNumContContratoEnt() == null &&
           contratosBean.getFechaDocumentoE() == null &&
           contratosBean.getFechaRecibidoE() == null &&
           contratosBean.getAgregDoc() == 0

        ){
            lsContratos.append("AND COD_FLEX_COLUM <> 'VAL_C19' ");
            lsContratos.append("AND COD_FLEX_COLUM <> 'VAL_C20' ");
            lsContratos.append("AND COD_FLEX_COLUM <> 'VAL_C25' ");
		}

		return lsContratos.toString();

	}

	public String getCamposActaOtros(int tIdMetaRow, ConnectionWrapper connectionWrapper){
		StringBuilder lsActaOtros = new StringBuilder();
		ActaOtrosBean actaOtrosBean = null;

		actaOtrosBean = reformasDAO.getValuesActaOtros(tIdMetaRow, connectionWrapper);

		if(actaOtrosBean.getFechaDocumento() == null)
			lsActaOtros.append("AND COD_FLEX_COLUM <> 'VAL_C14' ");

		if(actaOtrosBean.getSolPor() == null || actaOtrosBean.getSolPor().equals("0"))
			lsActaOtros.append("AND COD_FLEX_COLUM <> 'VAL_C119' ");
		
		if(actaOtrosBean.getFechaRecibido() == null)
			lsActaOtros.append("AND COD_FLEX_COLUM <> 'VAL_C15' ");

		if(actaOtrosBean.getFolioNum() == null)
			lsActaOtros.append("AND COD_FLEX_COLUM <> 'VAL_C16' ");

		if(actaOtrosBean.getNumDocSol() == null)
			lsActaOtros.append("AND COD_FLEX_COLUM <> 'VAL_C17' ");

		if(actaOtrosBean.getNumDocActaResol() == null)
			lsActaOtros.append("AND COD_FLEX_COLUM <> 'VAL_C19' ");

		if(actaOtrosBean.getNumDocConvocatoria() == null)
			lsActaOtros.append("AND COD_FLEX_COLUM <> 'VAL_C21' ");

		if(actaOtrosBean.getNumDocPublicaciones() == null)
			lsActaOtros.append("AND COD_FLEX_COLUM <> 'VAL_C23' ");

		if(actaOtrosBean.getNumDocDocEntregado() == null)
			lsActaOtros.append("AND COD_FLEX_COLUM <> 'VAL_C25' ");
		
		if(actaOtrosBean.getFechaDocumentoE() == null)
			lsActaOtros.append("AND COD_FLEX_COLUM <> 'VAL_C120' ");
		
		if(actaOtrosBean.getFechaRecibidoE() == null)
			lsActaOtros.append("AND COD_FLEX_COLUM <> 'VAL_C121' ");
		
		if(actaOtrosBean.getAgregDoc() == 0)
			lsActaOtros.append("AND COD_FLEX_COLUM <> 'VAL_C139' ");

		if(actaOtrosBean.getSolicitud() == null &&
            actaOtrosBean.getFechaDocumento() == null &&
            actaOtrosBean.getFechaRecibido() == null &&
            actaOtrosBean.getFolioNum() == null &&
            actaOtrosBean.getNumDocSol() == null
        )
			lsActaOtros.append("AND COD_FLEX_COLUM <> 'VAL_C13' ");

		if(actaOtrosBean.getActaResoluciones() == null &&
           actaOtrosBean.getNumDocActaResol() == null
        )
			lsActaOtros.append("AND COD_FLEX_COLUM <> 'VAL_C18' ");

		if(actaOtrosBean.getConvocatoria() == null &&
           actaOtrosBean.getNumDocConvocatoria() == null
        )
            lsActaOtros.append("AND COD_FLEX_COLUM <> 'VAL_C20' ");

		if(actaOtrosBean.getPublicaciones() == null &&
           actaOtrosBean.getNumDocPublicaciones() == null
        )
			lsActaOtros.append("AND COD_FLEX_COLUM <> 'VAL_C22' ");

		if(actaOtrosBean.getDocumentoEntregado() == null &&
           actaOtrosBean.getNumDocDocEntregado() == null
        )
			lsActaOtros.append("AND COD_FLEX_COLUM <> 'VAL_C24' ");

		if(actaOtrosBean.getSolicitud() == null &&
			(actaOtrosBean.getSolPor() == null || actaOtrosBean.getSolPor().equals("0")) &&
            actaOtrosBean.getFechaDocumento() == null &&
            actaOtrosBean.getFechaRecibido() == null &&
            actaOtrosBean.getFolioNum() == null &&
            actaOtrosBean.getNumDocSol() == null &&
            actaOtrosBean.getActaResoluciones() == null &&
            actaOtrosBean.getNumDocActaResol() == null &&
            actaOtrosBean.getConvocatoria() == null &&
            actaOtrosBean.getNumDocConvocatoria() == null &&
            actaOtrosBean.getPublicaciones() == null &&
            actaOtrosBean.getNumDocPublicaciones() == null &&
            actaOtrosBean.getDocumentoEntregado() == null &&
            actaOtrosBean.getNumDocDocEntregado() == null &&
            actaOtrosBean.getFechaDocumentoE() == null &&
            actaOtrosBean.getFechaRecibidoE() == null &&
            actaOtrosBean.getAgregDoc() == 0
        ){
			lsActaOtros.append("AND COD_FLEX_COLUM <> 'VAL_C12' ");
			lsActaOtros.append("AND COD_FLEX_COLUM <> 'VAL_C13' ");
			lsActaOtros.append("AND COD_FLEX_COLUM <> 'VAL_C18' ");
			lsActaOtros.append("AND COD_FLEX_COLUM <> 'VAL_C20' ");
			lsActaOtros.append("AND COD_FLEX_COLUM <> 'VAL_C22' ");
			lsActaOtros.append("AND COD_FLEX_COLUM <> 'VAL_C24' ");
		}

		return lsActaOtros.toString();

	}

	public String getCamposAumentoCapital(int tIdMetaRow, ConnectionWrapper connectionWrapper){
		StringBuilder lsAumentoCapital = new StringBuilder();
		AumentoCapitalBean aumentoCapitalBean = reformasDAO.getValuesAumentoCapital(tIdMetaRow, connectionWrapper);

		if(aumentoCapitalBean.getFechaDocumento() == null)
			lsAumentoCapital.append("AND COD_FLEX_COLUM <> 'VAL_C26' ");

		if(aumentoCapitalBean.getSolPor() == null || aumentoCapitalBean.getSolPor().equals("0"))
			lsAumentoCapital.append("AND COD_FLEX_COLUM <> 'VAL_C119' ");
		
		if(aumentoCapitalBean.getFechaRecibido() == null)
			lsAumentoCapital.append("AND COD_FLEX_COLUM <> 'VAL_C27' ");

		if(aumentoCapitalBean.getFolioNum() == null)
			lsAumentoCapital.append("AND COD_FLEX_COLUM <> 'VAL_C28' ");

		if(aumentoCapitalBean.getNumDocSol() == null)
			lsAumentoCapital.append("AND COD_FLEX_COLUM <> 'VAL_C29' ");

		if(aumentoCapitalBean.getNumDocActaResol() == null){
			lsAumentoCapital.append("AND COD_FLEX_COLUM <> 'VAL_C30' ");
			lsAumentoCapital.append("AND COD_FLEX_COLUM <> 'VAL_C31' ");

		}if(aumentoCapitalBean.getNumDocConvocatoria() == null){
			lsAumentoCapital.append("AND COD_FLEX_COLUM <> 'VAL_C32' ");
			lsAumentoCapital.append("AND COD_FLEX_COLUM <> 'VAL_C33' ");

		}if(aumentoCapitalBean.getNumDocPublicaciones() == null){
			lsAumentoCapital.append("AND COD_FLEX_COLUM <> 'VAL_C34' ");
			lsAumentoCapital.append("AND COD_FLEX_COLUM <> 'VAL_C35' ");

		}if(aumentoCapitalBean.getNumDocDocEntregado() == null){
			lsAumentoCapital.append("AND COD_FLEX_COLUM <> 'VAL_C36' ");
			lsAumentoCapital.append("AND COD_FLEX_COLUM <> 'VAL_C37' ");
			
		if(aumentoCapitalBean.getFechaDocumentoE() == null)
			lsAumentoCapital.append("AND COD_FLEX_COLUM <> 'VAL_C120' ");
		
		if(aumentoCapitalBean.getFechaRecibidoE() == null)
			lsAumentoCapital.append("AND COD_FLEX_COLUM <> 'VAL_C121' ");
		
		if(aumentoCapitalBean.getAgregDoc() == 0)
			lsAumentoCapital.append("AND COD_FLEX_COLUM <> 'VAL_C139' ");

		}if(aumentoCapitalBean.getSolicitud() == null &&
           aumentoCapitalBean.getFechaDocumento() == null &&
           aumentoCapitalBean.getFechaRecibido() == null &&
           aumentoCapitalBean.getFolioNum() == null &&
           aumentoCapitalBean.getNumDocSol() == null
          )
			lsAumentoCapital.append("AND COD_FLEX_COLUM <> 'VAL_C25' ");

		if(aumentoCapitalBean.getSolicitud() == null &&
		   (aumentoCapitalBean.getSolPor() == null || aumentoCapitalBean.getSolPor().equals("0")) &&
           aumentoCapitalBean.getFechaDocumento() == null &&
           aumentoCapitalBean.getFechaRecibido() == null &&
           aumentoCapitalBean.getFolioNum() == null &&
           aumentoCapitalBean.getNumDocSol() == null &&
           aumentoCapitalBean.getActaResoluciones() == null &&
           aumentoCapitalBean.getNumDocActaResol() == null &&
           aumentoCapitalBean.getConvocatoria() == null &&
           aumentoCapitalBean.getNumDocConvocatoria() == null &&
           aumentoCapitalBean.getPublicaciones() == null &&
           aumentoCapitalBean.getNumDocPublicaciones() == null &&
           aumentoCapitalBean.getDocumentoEntregado() == null &&
           aumentoCapitalBean.getNumDocDocEntregado() == null &&
           aumentoCapitalBean.getFechaDocumentoE() == null &&
           aumentoCapitalBean.getFechaRecibidoE() == null &&
           aumentoCapitalBean.getAgregDoc() == 0
        )
			lsAumentoCapital.append("AND COD_FLEX_COLUM <> 'VAL_C24' ");

		return lsAumentoCapital.toString();
	}

	public String getCamposDecretoDividendos(int tIdMetaRow, ConnectionWrapper connectionWrapper){
		StringBuilder lsDecretoDividendos = new StringBuilder();
		DecretoDividendosBean decretoDividendosBean = reformasDAO.getValuesDecretoDividendos(tIdMetaRow, connectionWrapper);

		if(decretoDividendosBean.getFechaDocumento() == null)
			lsDecretoDividendos.append("AND COD_FLEX_COLUM <> 'VAL_C16' ");
		
		if(decretoDividendosBean.getSolPor() == null || decretoDividendosBean.getSolPor().equals("0"))
			lsDecretoDividendos.append("AND COD_FLEX_COLUM <> 'VAL_C119' ");

		if(decretoDividendosBean.getFechaRecibido() == null)
			lsDecretoDividendos.append("AND COD_FLEX_COLUM <> 'VAL_C17' ");

		if(decretoDividendosBean.getFolioNum() == null)
			lsDecretoDividendos.append("AND COD_FLEX_COLUM <> 'VAL_C18' ");

		if(decretoDividendosBean.getNumDocSol() == null)
			lsDecretoDividendos.append("AND COD_FLEX_COLUM <> 'VAL_C19' ");

		if(decretoDividendosBean.getNumDocActaResol() == null)
			lsDecretoDividendos.append("AND COD_FLEX_COLUM <> 'VAL_C21' ");

		if(decretoDividendosBean.getNumDocDocEntregado() == null)
			lsDecretoDividendos.append("AND COD_FLEX_COLUM <> 'VAL_C23' ");
		
		if(decretoDividendosBean.getFechaDocumentoE() == null)
			lsDecretoDividendos.append("AND COD_FLEX_COLUM <> 'VAL_C120' ");
		
		if(decretoDividendosBean.getFechaRecibidoE() == null)
			lsDecretoDividendos.append("AND COD_FLEX_COLUM <> 'VAL_C121' ");
		
		if(decretoDividendosBean.getAgregDoc() == 0)
			lsDecretoDividendos.append("AND COD_FLEX_COLUM <> 'VAL_C139' ");

		if(decretoDividendosBean.getSolicitud() == null &&
            decretoDividendosBean.getFechaDocumento() == null &&
            decretoDividendosBean.getFechaRecibido() == null &&
            decretoDividendosBean.getFolioNum() == null &&
            decretoDividendosBean.getNumDocSol() == null
           )
			lsDecretoDividendos.append("AND COD_FLEX_COLUM <> 'VAL_C15' ");

        if(decretoDividendosBean.getActaResoluciones() == null &&
           decretoDividendosBean.getNumDocActaResol() == null
        )
			lsDecretoDividendos.append("AND COD_FLEX_COLUM <> 'VAL_C20' ");

        if(decretoDividendosBean.getDocumentoEntregado() == null &&
           decretoDividendosBean.getNumDocDocEntregado() == null
        )
			lsDecretoDividendos.append("AND COD_FLEX_COLUM <> 'VAL_C22' ");

		if(decretoDividendosBean.getSolicitud() == null &&
		   (decretoDividendosBean.getSolPor() == null || decretoDividendosBean.getSolPor().equals("0")) &&
           decretoDividendosBean.getFechaDocumento() == null &&
           decretoDividendosBean.getFechaRecibido() == null &&
           decretoDividendosBean.getFolioNum() == null &&
           decretoDividendosBean.getNumDocSol() == null &&
           decretoDividendosBean.getActaResoluciones() == null &&
           decretoDividendosBean.getNumDocActaResol() == null &&
           decretoDividendosBean.getDocumentoEntregado() == null &&
           decretoDividendosBean.getNumDocDocEntregado() == null &&
           decretoDividendosBean.getFechaDocumentoE() == null &&
           decretoDividendosBean.getFechaRecibidoE() == null &&
           decretoDividendosBean.getAgregDoc() == 0
        )
			lsDecretoDividendos.append("AND COD_FLEX_COLUM <> 'VAL_C14' ");

		return lsDecretoDividendos.toString();
	}

	public String getCamposDisminucionCapital(int tIdMetaRow, ConnectionWrapper connectionWrapper){
		StringBuilder lsDisminucionCapital = new StringBuilder();
		DisminucionCapitalBean disminucionCapitalBean = reformasDAO.getValuesDisminucionCapital(tIdMetaRow, connectionWrapper);

		if(disminucionCapitalBean.getFechaDocumento() == null)
			lsDisminucionCapital.append("AND COD_FLEX_COLUM <> 'VAL_C26' ");
		
		if(disminucionCapitalBean.getSolPor() == null || disminucionCapitalBean.getSolPor().equals("0"))
			lsDisminucionCapital.append("AND COD_FLEX_COLUM <> 'VAL_C119' ");

		if(disminucionCapitalBean.getFechaRecibido() == null)
            lsDisminucionCapital.append("AND COD_FLEX_COLUM <> 'VAL_C27' ");

		if(disminucionCapitalBean.getFolioNum() == null)
			lsDisminucionCapital.append("AND COD_FLEX_COLUM <> 'VAL_C28' ");

		if(disminucionCapitalBean.getNumDocSol() == null)
            lsDisminucionCapital.append("AND COD_FLEX_COLUM <> 'VAL_C29' ");

		if(disminucionCapitalBean.getNumDocActaResol() == null)
            lsDisminucionCapital.append("AND COD_FLEX_COLUM <> 'VAL_C31' ");

		if(disminucionCapitalBean.getNumDocConvocatoria() == null)
			lsDisminucionCapital.append("AND COD_FLEX_COLUM <> 'VAL_C33' ");

		if(disminucionCapitalBean.getNumDocPublicaciones() == null)
			lsDisminucionCapital.append("AND COD_FLEX_COLUM <> 'VAL_C35' ");

		if(disminucionCapitalBean.getNumDocDocEntregado() == null)
			lsDisminucionCapital.append("AND COD_FLEX_COLUM <> 'VAL_C37' ");
		
		if(disminucionCapitalBean.getFechaDocumentoE() == null)
			lsDisminucionCapital.append("AND COD_FLEX_COLUM <> 'VAL_C120' ");
		
		if(disminucionCapitalBean.getFechaRecibidoE() == null)
			lsDisminucionCapital.append("AND COD_FLEX_COLUM <> 'VAL_C121' ");
		
		if(disminucionCapitalBean.getAgregDoc() == 0)
			lsDisminucionCapital.append("AND COD_FLEX_COLUM <> 'VAL_C139' ");

		if(disminucionCapitalBean.getSolicitud() == null &&
           disminucionCapitalBean.getFechaDocumento() == null &&
           disminucionCapitalBean.getFechaRecibido() == null &&
           disminucionCapitalBean.getFolioNum() == null &&
           disminucionCapitalBean.getNumDocSol() == null
        )
			lsDisminucionCapital.append("AND COD_FLEX_COLUM <> 'VAL_C25' ");

		if(disminucionCapitalBean.getActaResoluciones() == null &&
           disminucionCapitalBean.getNumDocActaResol() == null
        )
			lsDisminucionCapital.append("AND COD_FLEX_COLUM <> 'VAL_C30' ");

		if(disminucionCapitalBean.getConvocatoria() == null &&
           disminucionCapitalBean.getNumDocConvocatoria() == null
        )
			lsDisminucionCapital.append("AND COD_FLEX_COLUM <> 'VAL_C32' ");

		if(disminucionCapitalBean.getPublicaciones() == null &&
           disminucionCapitalBean.getNumDocPublicaciones() == null
        )
			lsDisminucionCapital.append("AND COD_FLEX_COLUM <> 'VAL_C34' ");

		if(disminucionCapitalBean.getDocumentoEntregado() == null &&
           disminucionCapitalBean.getNumDocDocEntregado() == null
        )
			lsDisminucionCapital.append("AND COD_FLEX_COLUM <> 'VAL_C36' ");

		if(disminucionCapitalBean.getSolicitud() == null &&
		   (disminucionCapitalBean.getSolPor() == null || disminucionCapitalBean.getSolPor().equals("0")) &&
           disminucionCapitalBean.getFechaDocumento() == null &&
           disminucionCapitalBean.getFechaRecibido() == null &&
           disminucionCapitalBean.getFolioNum() == null &&
           disminucionCapitalBean.getNumDocSol() == null &&
           disminucionCapitalBean.getActaResoluciones() == null &&
           disminucionCapitalBean.getNumDocActaResol() == null &&
           disminucionCapitalBean.getConvocatoria() == null &&
           disminucionCapitalBean.getNumDocConvocatoria() == null &&
           disminucionCapitalBean.getPublicaciones() == null &&
           disminucionCapitalBean.getNumDocPublicaciones() == null &&
           disminucionCapitalBean.getDocumentoEntregado() == null &&
           disminucionCapitalBean.getNumDocDocEntregado() == null &&
           disminucionCapitalBean.getFechaDocumentoE() == null &&
           disminucionCapitalBean.getFechaRecibidoE() == null &&
           disminucionCapitalBean.getAgregDoc() == 0
        )
			lsDisminucionCapital.append("AND COD_FLEX_COLUM <> 'VAL_C24' ");

		return lsDisminucionCapital.toString();

	}

	public String getCamposEscision(int tIdMetaRow, ConnectionWrapper connectionWrapper){
        StringBuilder lsEscision = new StringBuilder();
        EscisionBean escisionBean = reformasDAO.getValuesEscision(tIdMetaRow, connectionWrapper);

        if(escisionBean.getFechaDocumento() == null)
        	lsEscision.append("AND COD_FLEX_COLUM <> 'VAL_C28' ");
        
        if(escisionBean.getSolPor() == null || escisionBean.getSolPor().equals("0"))
        	lsEscision.append("AND COD_FLEX_COLUM <> 'VAL_C119' ");

        if(escisionBean.getFechaRecibido() == null)
        	lsEscision.append("AND COD_FLEX_COLUM <> 'VAL_C29' ");

        if(escisionBean.getFolioNum() == null)
        	lsEscision.append("AND COD_FLEX_COLUM <> 'VAL_C30' ");

        if(escisionBean.getNumDocSol() == null)
        	lsEscision.append("AND COD_FLEX_COLUM <> 'VAL_C31' ");

        if(escisionBean.getNumDocActaResol() == null)
        	lsEscision.append("AND COD_FLEX_COLUM <> 'VAL_C33' ");

        if(escisionBean.getNumDocConvocatoria() == null)
        	lsEscision.append("AND COD_FLEX_COLUM <> 'VAL_C35' ");
        
        if(escisionBean.getNumDocPublicaciones() == null)
        	lsEscision.append("AND COD_FLEX_COLUM <> 'VAL_C37' ");

        if(escisionBean.getNumDocDocEntregado() == null)
        	lsEscision.append("AND COD_FLEX_COLUM <> 'VAL_C39' ");
        
        if(escisionBean.getFechaDocumentoE() == null)
        	lsEscision.append("AND COD_FLEX_COLUM <> 'VAL_C120' ");
		
		if(escisionBean.getFechaRecibidoE() == null)
			lsEscision.append("AND COD_FLEX_COLUM <> 'VAL_C121' ");
		
		if(escisionBean.getAgregDoc() == 0)
			lsEscision.append("AND COD_FLEX_COLUM <> 'VAL_C139' ");

        if(escisionBean.getSolicitud() == null &&
           escisionBean.getFechaDocumento() == null &&
           escisionBean.getFechaRecibido() == null &&
           escisionBean.getFolioNum() == null &&
           escisionBean.getNumDocSol() == null
        )
        	lsEscision.append("AND COD_FLEX_COLUM <> 'VAL_C27' ");

        if(escisionBean.getActaResoluciones() == null &&
           escisionBean.getNumDocActaResol() == null
        )
        	lsEscision.append("AND COD_FLEX_COLUM <> 'VAL_C32' ");

        if(escisionBean.getConvocatoria() == null &&
           escisionBean.getNumDocConvocatoria() == null
        )
        	lsEscision.append("AND COD_FLEX_COLUM <> 'VAL_C34' ");

        if(escisionBean.getPublicaciones() == null &&
           escisionBean.getNumDocPublicaciones() == null
        )
        	lsEscision.append("AND COD_FLEX_COLUM <> 'VAL_C36' ");

        if(escisionBean.getDocumentoEntregado() == null &&
           escisionBean.getNumDocDocEntregado() == null
        )
        	lsEscision.append("AND COD_FLEX_COLUM <> 'VAL_C38' ");

        if(escisionBean.getSolicitud() == null &&
           (escisionBean.getSolPor() == null || escisionBean.getSolPor().equals("0")) &&
	       escisionBean.getFechaDocumento() == null &&
	       escisionBean.getFechaRecibido() == null &&
	       escisionBean.getFolioNum() == null &&
	       escisionBean.getNumDocSol() == null &&
	       escisionBean.getActaResoluciones() == null &&
           escisionBean.getNumDocActaResol() == null &&
           escisionBean.getConvocatoria() == null &&
           escisionBean.getNumDocConvocatoria() == null &&
           escisionBean.getPublicaciones() == null &&
           escisionBean.getNumDocPublicaciones() == null &&
           escisionBean.getDocumentoEntregado() == null &&
           escisionBean.getNumDocDocEntregado() == null &&
           escisionBean.getFechaDocumentoE() == null &&
           escisionBean.getFechaRecibidoE() == null &&
           escisionBean.getAgregDoc() == 0
        )
        	lsEscision.append("AND COD_FLEX_COLUM <> 'VAL_C26' ");

        return lsEscision.toString();
	}

    public String getCamposFusion(int tIdMetaRow, ConnectionWrapper connectionWrapper){
    	StringBuilder lsFusion = new StringBuilder();
    	FusionBean fusionBean = reformasDAO.getValuesFusion(tIdMetaRow, connectionWrapper);

    	lsFusion.append(" AND COD_FLEX_COLUM <> 'VAL_C29' ");//JJAQ 21/11/2018 este cmapo ya no se ocupa
    	
    	if(fusionBean.getFechaDocumento() == null)
    		lsFusion.append(" AND COD_FLEX_COLUM <> 'VAL_C30' ");
    	
    	if(fusionBean.getSolPor() == null || fusionBean.getSolPor().equals("0"))
    		lsFusion.append(" AND COD_FLEX_COLUM <> 'VAL_C119' ");

    	if(fusionBean.getFechaRecibido() == null)
    		lsFusion.append(" AND COD_FLEX_COLUM <> 'VAL_C31' ");

    	if(fusionBean.getFolioNum() == null)
    		lsFusion.append(" AND COD_FLEX_COLUM <> 'VAL_C32' ");

    	if(fusionBean.getNumDocSol() == null)
    		lsFusion.append(" AND COD_FLEX_COLUM <> 'VAL_C33' ");

    	if(fusionBean.getNumDocActaResol() == null)
    		lsFusion.append(" AND COD_FLEX_COLUM <> 'VAL_C35' ");

    	if(fusionBean.getNumDocConvocatoria() == null)
    		lsFusion.append(" AND COD_FLEX_COLUM <> 'VAL_C37' ");

    	if(fusionBean.getNumDocPublicaciones() == null)
    		lsFusion.append(" AND COD_FLEX_COLUM <> 'VAL_C39' ");

    	if(fusionBean.getNumDocDocEntregado() == null)
    		lsFusion.append(" AND COD_FLEX_COLUM <> 'VAL_C41' ");

    	if(fusionBean.getNumDocConvenFusion() == null)
    		lsFusion.append(" AND COD_FLEX_COLUM <> 'VAL_C70' ");
    	
    	if(fusionBean.getFechaDocumentoE() == null)
    		lsFusion.append(" AND COD_FLEX_COLUM <> 'VAL_C120' ");
		
		if(fusionBean.getFechaRecibidoE() == null)
			lsFusion.append(" AND COD_FLEX_COLUM <> 'VAL_C121' ");
		
		if(fusionBean.getAgregDoc() == 0)
			lsFusion.append(" AND COD_FLEX_COLUM <> 'VAL_C139' ");

    	if(fusionBean.getSolicitud() == null &&
           fusionBean.getFechaDocumento() == null &&
           fusionBean.getFechaRecibido() == null &&
           fusionBean.getFolioNum() == null &&
           fusionBean.getNumDocSol() == null
        )
    		lsFusion.append("AND COD_FLEX_COLUM <> 'VAL_C29' ");

    	if(fusionBean.getActaResoluciones() == null &&
           fusionBean.getNumDocActaResol() == null
        )
    		lsFusion.append("AND COD_FLEX_COLUM <> 'VAL_C34' ");

    	if(fusionBean.getConvocatoria() == null &&
           fusionBean.getNumDocConvocatoria() == null
        )
    		lsFusion.append("AND COD_FLEX_COLUM <> 'VAL_C36' ");

    	if(fusionBean.getPublicaciones() == null &&
           fusionBean.getNumDocPublicaciones() == null
        )
    		lsFusion.append("AND COD_FLEX_COLUM <> 'VAL_C38' ");

    	if(fusionBean.getDocumentoEntregado() == null &&
           fusionBean.getNumDocDocEntregado() == null
        )
    		lsFusion.append("AND COD_FLEX_COLUM <> 'VAL_C40' ");

    	if(fusionBean.getConvenioFusion() == null &&
           fusionBean.getNumDocConvenFusion() == null
        )
    		lsFusion.append("AND COD_FLEX_COLUM <> 'VAL_C69' ");

    	if(fusionBean.getSolicitud() == null &&
    	   (fusionBean.getSolPor() == null || fusionBean.getSolPor().equals("0")) &&
           fusionBean.getFechaDocumento() == null &&
           fusionBean.getFechaRecibido() == null &&
           fusionBean.getFolioNum() == null &&
           fusionBean.getNumDocSol() == null &&
           fusionBean.getActaResoluciones() == null &&
           fusionBean.getNumDocActaResol() == null &&
           fusionBean.getConvocatoria() == null &&
           fusionBean.getNumDocConvocatoria() == null &&
           fusionBean.getPublicaciones() == null &&
           fusionBean.getNumDocPublicaciones() == null &&
           fusionBean.getDocumentoEntregado() == null &&
           fusionBean.getNumDocDocEntregado() == null &&
           fusionBean.getConvenioFusion() == null &&
           fusionBean.getNumDocConvenFusion() == null &&
           fusionBean.getFechaDocumentoE() == null &&
           fusionBean.getFechaRecibidoE() == null &&
           fusionBean.getAgregDoc() == 0
           )
    		lsFusion.append("AND COD_FLEX_COLUM <> 'VAL_C28' ");

    	return lsFusion.toString();
    }

    public String getCamposSesionConsejo(int tIdMetaRow, ConnectionWrapper connectionWrapper){
        StringBuilder lsSesionConsejo = new StringBuilder();
        SesionConsejoBean sesionConsejoBean = reformasDAO.getValuesSesionConsejo(tIdMetaRow, connectionWrapper);

        if(sesionConsejoBean.getFechaDocumento() == null)
        	lsSesionConsejo.append("AND COD_FLEX_COLUM <> 'VAL_C15' ");

        if(sesionConsejoBean.getSolPor() == null || sesionConsejoBean.getSolPor().equals("0"))
        	lsSesionConsejo.append("AND COD_FLEX_COLUM <> 'VAL_C119' ");
        
        if(sesionConsejoBean.getFechaRecibido() == null)
        	lsSesionConsejo.append("AND COD_FLEX_COLUM <> 'VAL_C16' ");

        if(sesionConsejoBean.getFolioNum() == null)
        	lsSesionConsejo.append("AND COD_FLEX_COLUM <> 'VAL_C17' ");

        if(sesionConsejoBean.getNumDocSol() == null)
            lsSesionConsejo.append("AND COD_FLEX_COLUM <> 'VAL_C18' ");

        if(sesionConsejoBean.getNumDocActaResol() == null)
            lsSesionConsejo.append("AND COD_FLEX_COLUM <> 'VAL_C20' ");

        if(sesionConsejoBean.getNumDocConvocatoria() == null)
            lsSesionConsejo.append("AND COD_FLEX_COLUM <> 'VAL_C22' ");

        if(sesionConsejoBean.getNumDocDocEntregado() == null)
            lsSesionConsejo.append("AND COD_FLEX_COLUM <> 'VAL_C24' ");
        
        if(sesionConsejoBean.getFechaDocumentoE() == null)
        	lsSesionConsejo.append("AND COD_FLEX_COLUM <> 'VAL_C120' ");
		
		if(sesionConsejoBean.getFechaRecibidoE() == null)
			lsSesionConsejo.append("AND COD_FLEX_COLUM <> 'VAL_C121' ");
		
		if(sesionConsejoBean.getAgregDoc() == 0)
			lsSesionConsejo.append("AND COD_FLEX_COLUM <> 'VAL_C139' ");

        if(sesionConsejoBean.getSolicitud() == null &&
           sesionConsejoBean.getFechaDocumento() == null &&
           sesionConsejoBean.getFechaRecibido() == null &&
           sesionConsejoBean.getFolioNum() == null &&
           sesionConsejoBean.getNumDocSol() == null
        )
        	lsSesionConsejo.append("AND COD_FLEX_COLUM <> 'VAL_C14' ");

        if(sesionConsejoBean.getActaResoluciones() == null &&
           sesionConsejoBean.getNumDocActaResol() == null
        )
        	lsSesionConsejo.append("AND COD_FLEX_COLUM <> 'VAL_C19' ");

        if(sesionConsejoBean.getConvocatoria() == null &&
           sesionConsejoBean.getNumDocConvocatoria() == null
        )
        	lsSesionConsejo.append("AND COD_FLEX_COLUM <> 'VAL_C21' ");

        if(sesionConsejoBean.getDocumentoEntregado() == null &&
           sesionConsejoBean.getNumDocDocEntregado() == null
        )
        	lsSesionConsejo.append("AND COD_FLEX_COLUM <> 'VAL_C23' ");

        if(sesionConsejoBean.getSolicitud() == null &&
           (sesionConsejoBean.getSolPor() == null || sesionConsejoBean.getSolPor().equals("0")) &&
	       sesionConsejoBean.getFechaDocumento() == null &&
	       sesionConsejoBean.getFechaRecibido() == null &&
	       sesionConsejoBean.getFolioNum() == null &&
	       sesionConsejoBean.getNumDocSol() == null &&
           sesionConsejoBean.getActaResoluciones() == null &&
	       sesionConsejoBean.getNumDocActaResol() == null &&
	       sesionConsejoBean.getConvocatoria() == null &&
           sesionConsejoBean.getNumDocConvocatoria() == null &&
           sesionConsejoBean.getDocumentoEntregado() == null &&
           sesionConsejoBean.getFechaDocumentoE() == null &&
           sesionConsejoBean.getFechaRecibidoE() == null &&
           sesionConsejoBean.getAgregDoc() == 0
        )
        	lsSesionConsejo.append("AND COD_FLEX_COLUM <> 'VAL_C13' ");

        return lsSesionConsejo.toString();
    }

}