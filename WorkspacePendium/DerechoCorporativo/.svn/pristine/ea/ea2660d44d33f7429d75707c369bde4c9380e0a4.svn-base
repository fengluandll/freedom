package mx.com.televisa.derechocorporativo.servlets;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.com.televisa.derechocorporativo.bean.PoderesBean;
import mx.com.televisa.derechocorporativo.bean.SessionBean;
import mx.com.televisa.derechocorporativo.daos.Abc_AppConfigTabDAO;
import mx.com.televisa.derechocorporativo.daos.PoderesDAO;
import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;

/**
 * Servlet implementation class PoderesGeneralesServlet
 */
@WebServlet("/PoderesGeneralesServlet")
public class PoderesGeneralesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PoderesBean poderesBean;
	PoderesDAO  poderesDAO;	
	
	
	/**
	 * @throws IOException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
    public void processRequestPoderes(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	PoderesBean poderesBean = new PoderesBean();
		System.out.println("Entro en el servlet");		
	
	    poderesDAO   = new PoderesDAO();
	    
	    ConnectionWrapper   luConnectionWrapper = null;
		ResultSet           luResultSet         = null;
		CallableStatement   luCallableStatement = null;
		Enumeration<String> lnParamNames        = request.getParameterNames();
		
		HttpSession session = request.getSession();
		SessionBean sessionBean = (SessionBean)session.getAttribute("sessionBean");
		String action = request.getParameter("accion");
		String idEmpresa = sessionBean.getIdCurrentEmpresa();
		
		
		String strTipoPoder = request.getParameter("");
		String strIdEmpresa = request.getParameter("");
		String strFecha   	= request.getParameter("txtFec");
		String strHora 		= request.getParameter("txtHor");
		String strIndDelegadoPor   = request.getParameter("delegP");
		String strIndTipoDocumento = request.getParameter("tp");
		
		String strDescEscritura = request.getParameter("txtEscCp");
		String fecOtorgamientoInstr = request.getParameter("txtFec_2");
		String strNumDocumentumInstr = request.getParameter("docNumDoc");
		String strIndRequiereProto = request.getParameter("cbReqPro");
		String strIndRequiereInscrRppc = request.getParameter("cbReqIns");
		String strNomSemaforo = request.getParameter("semaF");
		String strNumLicenciado = request.getParameter("comLic");
		String strNomNotarioPublico = request.getParameter("txtNP");
		String strNumDe = request.getParameter("comDe");
		String strDesSuplenciaAsociado = request.getParameter("supAsoc");
		String strNumInscritaRegistroPublico = request.getParameter("comInsRpc");
		String strFecRegistro = request.getParameter("txtFec_3");
		String strNumFolioMec = request.getParameter("txtFolMe");
		String strDeOtrosDatosRegistro = request.getParameter("otrDat");
		
		String strIndMemo = request.getParameter("cbMemo");
		String strNumSolicitadoPor = request.getParameter("comSolPor");
		String strFecDocumentoMemo = request.getParameter("txtFec_16");
		String strFecRecibidoMemo = request.getParameter("txtFec_4");
		String strNumFolio = request.getParameter("txtNumFol");
		String strNumDocumentoMemo = request.getParameter("docNumDocM");
		String strIndDocEntrega = request.getParameter("cbDocEnt");
		String strFecDocumentoEntrega = request.getParameter("txtFec_5");
		String strFecRecibidaEntrega = request.getParameter("txtFec_6");
		String strNumDocumentumEntrega = request.getParameter("docNumDocE");
		String strIndOtros = request.getParameter("cbOtr");
		String strFecDocumentoOtros = request.getParameter("txtFec_7");
		String strFecRecibidoOtros = request.getParameter("txtFec_8");
		String strNumDocumentumOtros = request.getParameter("docNumDocO");
		
		String strIndAplicaEstatus = request.getParameter("cbEstatus");
		String strNomSemaforoEstatus = request.getParameter("semEst");
		String strFecProgEntregaEstatus = request.getParameter("fecProEnt");
		String strIndRedactada = request.getParameter("cbRedac");
		String strNumRespedactada = request.getParameter("comResp");
		String strFecCumplimientoRedactada = request.getParameter("cumResp");
		String strIndRevisionGerente = request.getParameter("cbRevGte");
		String strNumRespGerente = request.getParameter("comRespGte");
		String strFecCumplimientoGerente = request.getParameter("cumGte");
		String strIndCorrecciones = request.getParameter("cbCorr");
		String strNumRespCorrecciones = request.getParameter("cbRespCorr");
		String strFecCumplimientoCorrecciones = request.getParameter("cumCorr");
		String strIndAutDireccion = request.getParameter("cbAutDir");
		String strNumRespAut = request.getParameter("comRespDir");
		String strFecCumplimientoAut = request.getParameter("cumDir");
		String strIndFirmas = request.getParameter("cbEnFirm");
		String strNumRespFirmas = request.getParameter("comRespFirm");
		String strFecCumplimientoFirmas = request.getParameter("cumFirm");
		String strIndEntregada = request.getParameter("cbEntr");
		String strNumRespEntregada = request.getParameter("comEntr");
		String strFecCumplimientoEntregada = request.getParameter("cumEntr");
		String strNumEnviadaNotaria = request.getParameter("envNot");
		String strFecEnvioNotaria = request.getParameter("envMp");
		String strIndPoderAsunto = request.getParameter("strIndPoderAsunto");
		String strIndTipoArmado = request.getParameter("strIndTipoArmado");
		
		System.out.println("strTipoPoder "+strTipoPoder);
		System.out.println("strIdEmpresa "+strIdEmpresa);
		System.out.println("strFecha "+strFecha);
		System.out.println("strHora "+strHora);
		System.out.println("strIndDelegadoPor "+strIndDelegadoPor);
		System.out.println("strIndTipoDocumento "+strIndTipoDocumento);
		System.out.println("strDesEscritura "+strDescEscritura);
		System.out.println("strNumDocumentumInstr "+strNumDocumentumInstr);
		System.out.println("strFeOtorgamientoInstr "+fecOtorgamientoInstr);
		System.out.println("strIndRequiereProto "+strIndRequiereProto);
		System.out.println("strIndRequiereInscrRppc "+strIndRequiereInscrRppc);
		System.out.println("strNomSemaforo "+strNomSemaforo);
		System.out.println("strNumLicenciado "+strNumLicenciado);
		System.out.println("strNomNotarioPublico "+strNomNotarioPublico);
		System.out.println("strNumDe "+strNumDe);
		System.out.println("strDesSuplenciaAsociado "+strDesSuplenciaAsociado);
		System.out.println("strNumInscritaRegistroPublico "+strNumInscritaRegistroPublico);
		System.out.println("strFecRegistro "+strFecRegistro);
		System.out.println("strNumFolioMec "+strNumFolioMec);
		System.out.println("strDeOtrosDatosRegistro "+strDeOtrosDatosRegistro);
		System.out.println("strIndMemo "+strIndMemo);
		System.out.println("strNumSolicitadoPor "+strNumSolicitadoPor);
		System.out.println("strFecDocumentoMemo "+strFecDocumentoMemo);
		System.out.println("strFecRecibidoMemo "+strFecRecibidoMemo);
		System.out.println("strNumFolio "+strNumFolio);
		System.out.println("strNumDocumentoMemo "+strNumDocumentoMemo);
		System.out.println("strIndDocEntrega "+strIndDocEntrega);
		System.out.println("strFecDocumentoEntrega "+strFecDocumentoEntrega);
		System.out.println("strFecRecibidaEntrega "+strFecRecibidaEntrega);
		System.out.println("strNumDocumentumEntrega "+strNumDocumentumEntrega);
		System.out.println("strIndOtros "+strIndOtros);
		System.out.println("strFecDocumentoOtros "+strFecDocumentoOtros);
		System.out.println("strFecRecibidoOtros "+strFecRecibidoOtros);
		System.out.println("strNumDocumentumOtros "+strNumDocumentumOtros);
		System.out.println("strIndAplicaEstatus "+strIndAplicaEstatus);
		System.out.println("strNomSemaforoEstatus "+strNomSemaforoEstatus);
		System.out.println("strFecProgEntregaEstatus "+strFecProgEntregaEstatus);
		System.out.println("strIndRedactada "+strIndRedactada);
		System.out.println("strNumRespedactada "+strNumRespedactada);
		System.out.println("strFecCumplimientoRedactada "+strFecCumplimientoRedactada);
		System.out.println("strIndRevisionGerente "+strIndRevisionGerente);
		System.out.println("strNumRespGerente "+strNumRespGerente);
		System.out.println("strFecCumplimientoGerente "+strFecCumplimientoGerente);
		System.out.println("strIndCorrecciones "+strIndCorrecciones);
		System.out.println("strNumRespCorrecciones "+strNumRespCorrecciones);
		System.out.println("strFecCumplimientoCorrecciones "+strFecCumplimientoCorrecciones);
		System.out.println("strIndAutDireccion "+strIndAutDireccion);
		System.out.println("strNumRespAut "+strNumRespAut);
		System.out.println("strFecCumplimientoAut "+strFecCumplimientoAut);
		System.out.println("strIndFirmas "+strIndFirmas);
		System.out.println("strNumRespFirmas "+strNumRespFirmas);
		System.out.println("strFecCumplimientoFirmas "+strFecCumplimientoFirmas);
		System.out.println("strIndEntregada "+strIndEntregada);
		System.out.println("strNumRespEntregada "+strNumRespEntregada);
		System.out.println("strFecCumplimientoEntregada "+strFecCumplimientoEntregada);
		System.out.println("strNumEnviadaNotaria "+strNumEnviadaNotaria);
		System.out.println("strFecEnvioNotaria "+strFecEnvioNotaria);
		System.out.println("strIndPoderAsunto "+strIndPoderAsunto);
		System.out.println("strIndTipoArmado "+strIndTipoArmado);
		
		//poderesBean.setIntTipoPoder(Integer.parseInt(intTipoPoder));
		poderesBean.setIdEmpresa(Integer.parseInt(idEmpresa));		
		poderesBean.setFecFecha(strFecha);
		poderesBean.setFecHora(strHora);
		poderesBean.setIndDelegadoPor(Integer.parseInt(strIndDelegadoPor));
		poderesBean.setIndTipoDocumento(strIndTipoDocumento);
		poderesBean.setDescEscritura(strDescEscritura);
		poderesBean.setFecOtorgamientoInstr(fecOtorgamientoInstr);
		poderesBean.setNumDocumentumInstr(strNumDocumentumInstr);
		poderesBean.setIndRequiereProto(strIndRequiereProto);
		poderesBean.setIndRequiereInscrRppc(strIndRequiereInscrRppc);
		poderesBean.setNomSemaforo(strNomSemaforo);
		poderesBean.setNumLicenciado(Integer.parseInt(strNumLicenciado));
		poderesBean.setNomNotarioPublico(strNomNotarioPublico);
		poderesBean.setNumDe(Integer.parseInt(strNumDe));
		poderesBean.setDesSuplenciaAsociado(strDesSuplenciaAsociado);
		poderesBean.setNumInscritaRegistroPublico(strNumInscritaRegistroPublico);
		poderesBean.setFecRegistro(strFecRegistro);
		poderesBean.setNumFolioMec(strNumFolioMec);
		poderesBean.setDesOtrosDatosRegistro(strDeOtrosDatosRegistro);
		poderesBean.setIndMemo(strIndMemo);
		poderesBean.setNumSolicitadoPor(Integer.parseInt(strNumSolicitadoPor));
		poderesBean.setFecDocumentoMemo(strFecDocumentoMemo);
		poderesBean.setFecRecibidoMemo(strFecRecibidoMemo);
		poderesBean.setNumFolio(strNumFolio);
		poderesBean.setNumDocumentoMemo(strNumDocumentoMemo);
		poderesBean.setIndDocEntrega(strIndDocEntrega);
		poderesBean.setFecDocumentoEntrega(strFecDocumentoEntrega);
		poderesBean.setFecRecibidaEntrega(strFecRecibidaEntrega);
		poderesBean.setNumDocumentumEntrega(strNumDocumentumEntrega);
		poderesBean.setIndOtros(strIndOtros);
		poderesBean.setFecDocumentoOtros(strFecDocumentoOtros);
		poderesBean.setFecRecibidoOtros(strFecRecibidoOtros);
		poderesBean.setNumDocumentumOtros(strNumDocumentumOtros);
		poderesBean.setIndAplicaEstatus(strIndAplicaEstatus);
		poderesBean.setNomSemaforoEstatus(strNomSemaforoEstatus);
		poderesBean.setFecProgEntregaEstatus(strFecProgEntregaEstatus);
		poderesBean.setIndRedactada(strIndRedactada);
		poderesBean.setNumRespRedactada(Integer.parseInt(strNumRespedactada));
		poderesBean.setFecCumplimientoRedactada(strFecCumplimientoRedactada);
		poderesBean.setIndRevisionGerente(strIndRevisionGerente);
		poderesBean.setNumRespGerente(Integer.parseInt(strNumRespGerente));
		poderesBean.setFecCumplimientoGerente(strFecCumplimientoGerente);
		poderesBean.setIndCorrecciones(strIndCorrecciones);
		poderesBean.setNumRespCorrecciones(Integer.parseInt(strNumRespCorrecciones));
		poderesBean.setFecCumplimientoCorrecciones(strFecCumplimientoCorrecciones);
		poderesBean.setIndAutDireccion(strIndAutDireccion);
		poderesBean.setNumRespAut(Integer.parseInt(strNumRespAut));
		poderesBean.setFecCumplimientoAut(strFecCumplimientoAut);
		poderesBean.setIndFirmas(strIndFirmas);
		poderesBean.setNumRespFirmas(Integer.parseInt(strNumRespFirmas));
		poderesBean.setFecCumplimientoFirmas(strFecCumplimientoFirmas);
		poderesBean.setIndEntregada(strIndEntregada);
		poderesBean.setNumRespEntregada(Integer.parseInt(strNumRespEntregada));
		poderesBean.setFecCumplimientoEntregada(strFecCumplimientoEntregada);
		poderesBean.setNumEnviadaNotaria(strNumEnviadaNotaria);
		poderesBean.setFecEnvioNotaria(strFecEnvioNotaria);
		poderesBean.setIndPoderAsunto(strIndPoderAsunto);
		poderesBean.setIndTipoArmado(strIndTipoArmado);		
		
		boolean inserto = poderesDAO.insertaPoderes(poderesBean);
		if(inserto){
			System.out.println("INSERTO CORRECTAMENTE");
			response.sendRedirect(request.getContextPath()+"/faces/jsp/captura/poderes/poderesGenerales.jsp?idSeccion=22");
		}
		
	 }
		

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequestPoderes(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequestPoderes(request,response);
	}

}
