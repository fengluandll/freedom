package mx.com.televisa.derechocorporativo.modules.flextabs.consulta;

import mx.com.televisa.derechocorporativo.bean.reformas.AprobEjerSocBean;
import mx.com.televisa.derechocorporativo.daos.AprobEjerSocDAO;
import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;

public class AprobEjerSocCon {
	private AprobEjerSocDAO aprobEjerSocDAO;
	private AprobEjerSocBean aprobEjerSocBean;

	public AprobEjerSocCon(){
		aprobEjerSocDAO = new AprobEjerSocDAO();
	}

	public String getResultado(int tIdMetaRow, ConnectionWrapper connectionWrapper){
		StringBuilder lsResultado = new StringBuilder();
		aprobEjerSocBean = aprobEjerSocDAO.getResultado(tIdMetaRow, connectionWrapper);

		if(aprobEjerSocBean.getUtilidad().equals("No"))
			lsResultado.append("AND COD_FLEX_COLUM <> 'VAL_C7' ");

		if(aprobEjerSocBean.getPerdida().equals("No"))
			lsResultado.append("AND COD_FLEX_COLUM <> 'VAL_C9' ");

		System.out.println("AprobEjerSocCon: "+aprobEjerSocBean.getUtilidad()+" "+aprobEjerSocBean.getPerdida());

		return lsResultado.toString();
	}

	public String getOtrosAcuerdos(int tiIdEmpresa, ConnectionWrapper tuConnectionWrapper){
		StringBuilder lsOtrosAcuerdos = new StringBuilder();
		aprobEjerSocBean = aprobEjerSocDAO.getOtrosAcuerdos(tiIdEmpresa, tuConnectionWrapper);

		if(aprobEjerSocBean.getAprobDicFiscal().equals("No"))
			lsOtrosAcuerdos.append("AND COD_FLEX_COLUM <> 'VAL_C8' ");

		if(aprobEjerSocBean.getDecreDividendos().equals("No"))
			lsOtrosAcuerdos.append("AND COD_FLEX_COLUM <> 'VAL_C92' ");

        if(aprobEjerSocBean.getRatifiConsejeros().equals("No"))
        	lsOtrosAcuerdos.append("AND COD_FLEX_COLUM <> 'VAL_C22' ");

        if(aprobEjerSocBean.getRatifiFuncionarios().equals("No"))
        	lsOtrosAcuerdos.append("AND COD_FLEX_COLUM <> 'VAL_C23' ");

        if(aprobEjerSocBean.getRatifiComisarios().equals("No"))
        	lsOtrosAcuerdos.append("AND COD_FLEX_COLUM <> 'VAL_C24' ");

        if(aprobEjerSocBean.getDesigConsejeros().equals("No"))
        	lsOtrosAcuerdos.append("AND COD_FLEX_COLUM <> 'VAL_C25' ");

        if(aprobEjerSocBean.getDesigFuncionarios().equals("No"))
        	lsOtrosAcuerdos.append("AND COD_FLEX_COLUM <> 'VAL_C26' ");

        if(aprobEjerSocBean.getDesigComisarios().equals("No"))
        	lsOtrosAcuerdos.append("AND COD_FLEX_COLUM <> 'VAL_C27' ");

        if(aprobEjerSocBean.getOtorgaPoderes().equals("No"))
        	lsOtrosAcuerdos.append("AND COD_FLEX_COLUM <> 'VAL_C29' ");

        if(aprobEjerSocBean.getRevocaPoderes().equals("No"))
        	lsOtrosAcuerdos.append("AND COD_FLEX_COLUM <> 'VAL_C30' ");

		return lsOtrosAcuerdos.toString();
	}

}