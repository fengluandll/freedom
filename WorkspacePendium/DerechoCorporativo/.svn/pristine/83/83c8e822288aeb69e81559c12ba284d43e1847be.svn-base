package mx.com.televisa.derechocorporativo.modules.flextabs.consulta;

import org.apache.log4j.Logger;

import mx.com.televisa.derechocorporativo.bean.reformas.FusionBean;
import mx.com.televisa.derechocorporativo.daos.FusionDAO;
import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;

public class Fusion {
	private final static Logger log = Logger.getLogger(Fusion.class);

	private FusionDAO fusionDAO;

	public Fusion(){
		fusionDAO = new FusionDAO();
	}

	public String getCapFijVarSocAumDisPr(int tIdMetaRow, ConnectionWrapper connectionWrapper){
		StringBuilder lsCapFijVarSocAumDisPr = new StringBuilder();
		FusionBean fusionBean = new FusionBean();
		fusionBean = fusionDAO.getCapFijVarSocAumDisPr(tIdMetaRow, connectionWrapper);
		//JJAQ 26/04/2017 para que no muestre el titulo del aumento de capital
		if((fusionBean.getCapitalFijoAum() == null || fusionBean.getCapitalFijoAum().equals("No") ) &&
		   (fusionBean.getCapitalVariableAum() == null ||fusionBean.getCapitalVariableAum().equals("No"))	){
			lsCapFijVarSocAumDisPr.append("AND COD_FLEX_COLUM <> 'VAL_C8' ");
			lsCapFijVarSocAumDisPr.append("AND COD_FLEX_COLUM <> 'VAL_C17' ");
		}
		if(fusionBean.getCapitalFijoAum() == null ||
           fusionBean.getCapitalFijoAum().equals("No")
        ){
			lsCapFijVarSocAumDisPr.append("AND COD_FLEX_COLUM <> 'VAL_C9' ");
			lsCapFijVarSocAumDisPr.append("AND COD_FLEX_COLUM <> 'VAL_C10' ");
			lsCapFijVarSocAumDisPr.append("AND COD_FLEX_COLUM <> 'VAL_C11' ");
			lsCapFijVarSocAumDisPr.append("AND COD_FLEX_COLUM <> 'VAL_C12' ");
		}

		if(fusionBean.getCapitalVariableAum() == null ||
           fusionBean.getCapitalVariableAum().equals("No")){
            lsCapFijVarSocAumDisPr.append("AND COD_FLEX_COLUM <> 'VAL_C13' ");
            lsCapFijVarSocAumDisPr.append("AND COD_FLEX_COLUM <> 'VAL_C14' ");
            lsCapFijVarSocAumDisPr.append("AND COD_FLEX_COLUM <> 'VAL_C15' ");
            lsCapFijVarSocAumDisPr.append("AND COD_FLEX_COLUM <> 'VAL_C16' ");
		}
		
		//JJAQ 26/04/2017 para que no muestre el titulo de disminucion de capital
		if( (fusionBean.getCapitalFijoDis() == null || fusionBean.getCapitalFijoDis().equals("No") ) &&
			(fusionBean.getCapitalVariableDis() == null || fusionBean.getCapitalVariableDis().equals("No")) ){
			lsCapFijVarSocAumDisPr.append("AND COD_FLEX_COLUM <> 'VAL_C42' ");
            lsCapFijVarSocAumDisPr.append("AND COD_FLEX_COLUM <> 'VAL_C51' ");
		}
		
		
        if(fusionBean.getCapitalFijoDis() == null ||
           fusionBean.getCapitalFijoDis().equals("No")
        ){
            lsCapFijVarSocAumDisPr.append("AND COD_FLEX_COLUM <> 'VAL_C43' ");
            lsCapFijVarSocAumDisPr.append("AND COD_FLEX_COLUM <> 'VAL_C44' ");
            lsCapFijVarSocAumDisPr.append("AND COD_FLEX_COLUM <> 'VAL_C45' ");
            lsCapFijVarSocAumDisPr.append("AND COD_FLEX_COLUM <> 'VAL_C46' ");

        }

        if(fusionBean.getCapitalVariableDis() == null ||
           fusionBean.getCapitalVariableDis().equals("No")
        ){
            lsCapFijVarSocAumDisPr.append("AND COD_FLEX_COLUM <> 'VAL_C47' ");
            lsCapFijVarSocAumDisPr.append("AND COD_FLEX_COLUM <> 'VAL_C48' ");
            lsCapFijVarSocAumDisPr.append("AND COD_FLEX_COLUM <> 'VAL_C49' ");
            lsCapFijVarSocAumDisPr.append("AND COD_FLEX_COLUM <> 'VAL_C50' ");

        }

        if(fusionBean.getDeCapFijAum() == null)
            lsCapFijVarSocAumDisPr.append("AND COD_FLEX_COLUM <> 'VAL_C10' ");

        if(fusionBean.getConCapFijAum() == null)
            lsCapFijVarSocAumDisPr.append("AND COD_FLEX_COLUM <> 'VAL_C11' ");

        if(fusionBean.getQuedarCapFijAum() == null)
            lsCapFijVarSocAumDisPr.append("AND COD_FLEX_COLUM <> 'VAL_C12' ");

        if(fusionBean.getDeCapVarAum() == null)
            lsCapFijVarSocAumDisPr.append("AND COD_FLEX_COLUM <> 'VAL_C14' ");

        if(fusionBean.getConCapVarAum() == null)
            lsCapFijVarSocAumDisPr.append("AND COD_FLEX_COLUM <> 'VAL_C15' ");

        if(fusionBean.getQuedarCapVarAum() == null)
            lsCapFijVarSocAumDisPr.append("AND COD_FLEX_COLUM <> 'VAL_C16' ");

        if(fusionBean.getDeCapFijDis() == null)
            lsCapFijVarSocAumDisPr.append("AND COD_FLEX_COLUM <> 'VAL_C44' ");

        if(fusionBean.getConCapFijDis() == null)
            lsCapFijVarSocAumDisPr.append("AND COD_FLEX_COLUM <> 'VAL_C45' ");

        if(fusionBean.getQuedarCapFijDis() == null)
            lsCapFijVarSocAumDisPr.append("AND COD_FLEX_COLUM <> 'VAL_C46' ");

        if(fusionBean.getDeCapVarDis() == null)
            lsCapFijVarSocAumDisPr.append("AND COD_FLEX_COLUM <> 'VAL_C48' ");

        if(fusionBean.getConCapVarDis() == null)
        	lsCapFijVarSocAumDisPr.append("AND COD_FLEX_COLUM <> 'VAL_C49' ");

        if(fusionBean.getQuedarCapVarDis() == null)
        	lsCapFijVarSocAumDisPr.append("AND COD_FLEX_COLUM <> 'VAL_C50' ");

        if(fusionBean.getDeCapFijAum() == null &&
           fusionBean.getConCapFijAum() == null &&
           fusionBean.getQuedarCapFijAum() == null &&
           fusionBean.getDeCapVarAum() == null &&
           fusionBean.getConCapVarAum() == null &&
           fusionBean.getQuedarCapVarAum() == null
        ){
        	lsCapFijVarSocAumDisPr.append("AND COD_FLEX_COLUM <> 'VAL_C8' ");
        	lsCapFijVarSocAumDisPr.append("AND COD_FLEX_COLUM <> 'VAL_C17' ");

        }

        if(fusionBean.getDeCapFijDis() == null &&
           fusionBean.getConCapFijDis() == null &&
           fusionBean.getQuedarCapFijDis() == null &&
           fusionBean.getDeCapVarDis() == null &&
           fusionBean.getConCapVarDis() == null &&
           fusionBean.getQuedarCapVarDis() == null
        ){
        	lsCapFijVarSocAumDisPr.append("AND COD_FLEX_COLUM <> 'VAL_C42' ");
        	lsCapFijVarSocAumDisPr.append("AND COD_FLEX_COLUM <> 'VAL_C51' ");

        }

        if(fusionBean.getCapitalTotal() == null){
        	System.out.println(fusionBean.getCapitalTotal());
        	lsCapFijVarSocAumDisPr.append("AND COD_FLEX_COLUM <> 'VAL_C52' ");
        }

		return lsCapFijVarSocAumDisPr.toString();

	}

	public String getCamposValoresFusion(int tIdMetaRow, ConnectionWrapper connectionWrapper){
		StringBuilder lsValoresFusion = new StringBuilder();
		FusionBean fusionBean = new FusionBean();
		fusionBean = fusionDAO.getCamposValoresFusion(tIdMetaRow, connectionWrapper);

		if(fusionBean.getAsunto() == null)
			lsValoresFusion.append("AND COD_FLEX_COLUM <> 'VAL_C149' ");

		if(fusionBean.getTipoReunion() == null || fusionBean.getTipoReunion().equals("0"))
			lsValoresFusion.append("AND COD_FLEX_COLUM <> 'VAL_C2' ");

		if(fusionBean.getSociedadFusionante() == null || fusionBean.getSociedadFusionante().equals("0"))
			lsValoresFusion.append("AND COD_FLEX_COLUM <> 'VAL_C98' ");

		if(fusionBean.getFecha() == null)
			lsValoresFusion.append("AND COD_FLEX_COLUM <> 'VAL_C3' ");

		if(fusionBean.getHora() == null)
			lsValoresFusion.append("AND COD_FLEX_COLUM <> 'VAL_C4' ");

		if(fusionBean.getAsambleaSociedadesFusionantes() == null)
			lsValoresFusion.append("AND COD_FLEX_COLUM <> 'VAL_C99' ");

		if(fusionBean.getFechaEfectosPartes() == null)
			lsValoresFusion.append("AND COD_FLEX_COLUM <> 'VAL_C18' ");

		if(fusionBean.getFechaEfectosTerceros() == null || fusionBean.getFechaEfectosTerceros().equals("0"))
			lsValoresFusion.append("AND COD_FLEX_COLUM <> 'VAL_C19' ");

		if(fusionBean.getArtClaEstatusReformadas() == null)
			lsValoresFusion.append("AND COD_FLEX_COLUM <> 'VAL_C20' ");

		if(fusionBean.getOtrosAcuerdosObservaciones() == null)
			lsValoresFusion.append("AND COD_FLEX_COLUM <> 'VAL_C21' ");

		if(fusionBean.getOtrosRegistros() == null)
			lsValoresFusion.append("AND COD_FLEX_COLUM <> 'VAL_C100' ");

		return lsValoresFusion.toString();

	}

}