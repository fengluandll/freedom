package mx.com.televisa.derechocorporativo.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.com.televisa.derechocorporativo.bean.EjercicioBean;
import mx.com.televisa.derechocorporativo.bean.SessionBean;
import mx.com.televisa.derechocorporativo.daos.EjercicioSocialDao;
import mx.com.televisa.derechocorporativo.util.TextFormatter;

/**
 * Servlet implementation class AprobEjerSocServlet
 */
@WebServlet("/AprobEjerSocServlet")
public class AprobEjerSocServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AprobEjerSocServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response)throws IOException{
    	
    	String tipoAccion			=	request.getParameter("tipoAccion");
    	SessionBean sessionBean= (SessionBean) request.getSession().getAttribute("sessionBean");
    	int idEmpresa  				= 	Integer.parseInt(sessionBean.getIdCurrentEmpresa());
    	EjercicioSocialDao ejercicioDao=new EjercicioSocialDao();
    	switch(tipoAccion){
    	case "insert":
    		//insert
    		//SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
	    	int idUser  				= 	Integer.parseInt(sessionBean.getIdUser());
	    	//int ejercicioSocial=Integer.parseInt(request.getParameter("ejercicio"));
	    	int idMetaRow=Integer.parseInt(request.getParameter("idMetaRow"));
	    	
	    	//solicitud
	    	String noDocumentumSol  	=	request.getParameter("documentoSol");
	    	String fechaDocSol			=	request.getParameter("fecha_docSol");
	    	String fechaEntSol			=	request.getParameter("fecha_entregaSol");
	    	String tipoDocS				=	request.getParameter("typeDocS");
	    	/*//Informe del comisario
	    	String noDocumentumInfC  	=	request.getParameter("documentoInfC");
	    	String fechaDocInfC			=	request.getParameter("fecha_docInfC");
	    	String fechaEntInfC			=	request.getParameter("fecha_entregaInfC");
	    	String tipoDocInfC				=	request.getParameter("typeDocInfC");
	    	//Dictamen de los estados financieros
	    	String noDocumentumDicE  	=	request.getParameter("documentoDicE");
	    	String fechaDocDicE			=	request.getParameter("fecha_docDicE");
	    	String fechaEntDicE			=	request.getParameter("fecha_entregaDicE");
	    	String tipoDocDicE				=	request.getParameter("typeDocDicE");
	    	//Dictamen Fiscal
	    	String noDocumentumDicF  	=	request.getParameter("documentoDicF");
	    	String fechaDocDicF			=	request.getParameter("fecha_docDicF");
	    	String fechaEntDicF			=	request.getParameter("fecha_entregaDicF");
	    	String tipoDocDicF				=	request.getParameter("typeDocDicF");
	    	
	    	ArrayList<EjercicioBean> listEjercicios = new ArrayList<EjercicioBean>();*/
    		
    		//Solicitud
        	if(tipoDocS!=null && !tipoDocS.equals("")){
        		EjercicioBean solicitud  	=	new EjercicioBean();
        		//solicitud.setEjercicioSocial(ejercicioSocial);
        		solicitud.setIdEmpresa(idEmpresa);
        		solicitud.setNoDocumentum(noDocumentumSol);
        		solicitud.setTipoDoc(tipoDocS);
        		solicitud.setIdUser(idUser);
        		solicitud.setIdMetaRow(idMetaRow);
        		solicitud.setFechaDocumentum(fechaDocSol);
        		solicitud.setFechaEntrega(fechaEntSol);
        		//se añade el objeto a la lista
        		//listEjercicios.add(solicitud);
        		if(solicitud.getIdMetaRow()!=0){
        			ejercicioDao.createEjercicioSocialMetaRow(solicitud);
        			response.setContentType("text/html;charset=UTF-8");
                    response.getWriter().write("ok");
        		}else{
        			ejercicioDao.createEjercicioSocial(solicitud);
        			response.setContentType("text/html;charset=UTF-8");
                    response.getWriter().write("ok");
        		}
        		
        	}
        	/*//Informe Comisario
        	if(noDocumentumInfC!=null && !noDocumentumInfC.equals("")){
        		EjercicioBean infoCom  	=	new EjercicioBean();
        		infoCom.setEjercicioSocial(ejercicioSocial);
        		infoCom.setIdEmpresa(idEmpresa);
        		infoCom.setNoDocumentum(noDocumentumInfC);
        		infoCom.setTipoDoc(tipoDocInfC);
        		infoCom.setIdUser(idUser);
        		infoCom.setIdMetaRow(idMetaRow);
        		try {
        			java.util.Date fec = null;
    				fec = formatoDelTexto.parse(fechaDocInfC);
    				infoCom.setFechaDocumentum(new java.sql.Date(fec.getTime()));
    				
    				java.util.Date fecE = null;
    				fecE = formatoDelTexto.parse(fechaEntInfC);
    				infoCom.setFechaEntrega(new java.sql.Date(fecE.getTime()));
    			} catch (ParseException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
        		listEjercicios.add(infoCom);
        	}
        	//Dictamen de los estados financieros
        	if(noDocumentumDicE!=null && !noDocumentumDicE.equals("")){
        		EjercicioBean dicEF  	=	new EjercicioBean();
        		dicEF.setEjercicioSocial(ejercicioSocial);
        		dicEF.setIdEmpresa(idEmpresa);
        		dicEF.setNoDocumentum(noDocumentumDicE);
        		dicEF.setTipoDoc(tipoDocDicE);
        		dicEF.setIdUser(idUser);
        		dicEF.setIdMetaRow(idMetaRow);
        		try {
        			java.util.Date fec = null;
    				fec = formatoDelTexto.parse(fechaDocDicE);
    				dicEF.setFechaDocumentum(new java.sql.Date(fec.getTime()));
    				
    				java.util.Date fecE = null;
    				fecE = formatoDelTexto.parse(fechaEntDicE);
    				dicEF.setFechaEntrega(new java.sql.Date(fecE.getTime()));
    			} catch (ParseException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
        		listEjercicios.add(dicEF);
        	}
        	//Dictamen Fiscal
        	if(noDocumentumDicF!=null && !noDocumentumDicF.equals("")){
        		EjercicioBean dicF  	=	new EjercicioBean();
        		dicF.setEjercicioSocial(ejercicioSocial);
        		dicF.setIdEmpresa(idEmpresa);
        		dicF.setNoDocumentum(noDocumentumDicF);
        		dicF.setTipoDoc(tipoDocDicF);
        		dicF.setIdUser(idUser);
        		dicF.setIdMetaRow(idMetaRow);
        		try {
        			java.util.Date fec = null;
    				fec = formatoDelTexto.parse(fechaDocDicF);
    				dicF.setFechaDocumentum(new java.sql.Date(fec.getTime()));
    				
    				java.util.Date fecE = null;
    				fecE = formatoDelTexto.parse(fechaEntDicF);
    				dicF.setFechaEntrega(new java.sql.Date(fecE.getTime()));
    			} catch (ParseException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
        		listEjercicios.add(dicF);
        	}
        	
        	for(int i=0;i < listEjercicios.size();i++){
        		EjercicioBean ejercicio = listEjercicios.get(i);
        		if(ejercicio.getIdMetaRow()!=0){
        			ejercicioDao.createEjercicioSocialMetaRow(ejercicio);
        		}else{
        			ejercicioDao.createEjercicioSocial(ejercicio);        			
        		}
        		
        	}*/
    		//insert
    		break;
    	//Para generar la tabla de datos temporales
    	case "findTemp":
    		int idMetaRowFT=Integer.parseInt(request.getParameter("idMetaRow"));
    		ArrayList<EjercicioBean> listEjerciciosTemp=new ArrayList<EjercicioBean>();
    		if(idMetaRowFT!=0){
    			listEjerciciosTemp=(ArrayList<EjercicioBean>) ejercicioDao.findEjerciciosMetaRow(idMetaRowFT);
    		}else{
    			listEjerciciosTemp=(ArrayList<EjercicioBean>) ejercicioDao.findEjerciciosTemp(idEmpresa);    			    			
    		}
    		StringBuilder rows =new StringBuilder();
    		int i=1;
    		//SimpleDateFormat formatoDelTexto3 =new SimpleDateFormat("dd/MM/yyyy");
    		for(EjercicioBean ejercicio: listEjerciciosTemp){
    			/*String tipoDoc="";
    			switch(ejercicio.getTipoDoc()){
				case "solicitud":
					tipoDoc="Solicitud";
					break;
				case "infCom":
					tipoDoc="Informe del Comisario";
					break;
				case "def":
					tipoDoc="Dictamen de los Estados Financieros";
					break;
				case "df":
					tipoDoc="Dictamen Fiscal";
					break;
				}*/
    			if(i % 2 == 0){
        			rows.append("<tr class='tableRow2'>");
    			}else{
    				rows.append("<tr>");
    			}
    				
    				rows.append("<td>"+ejercicio.getTipoDoc()+"</td>");
    				rows.append("<td>"+(ejercicio.getFechaDocumentum()!=null?ejercicio.getFechaDocumentum():"")+"</td>");
    				rows.append("<td>"+(ejercicio.getFechaEntrega()!=null?ejercicio.getFechaEntrega():"")+"</td>");
    				rows.append("<td>"+(ejercicio.getNoDocumentum()!=null?ejercicio.getNoDocumentum():"")+"<a href=\"javascript:getDocumentumDeTabla('"+ejercicio.getNoDocumentum()+"')\"><img src='"+request.getContextPath()+"/img/List_16.png'></a></td>");
    				rows.append("<td><center><a class='editElement' data-ejercicio=\""+ejercicio.getIdEjercicioRow()+"\"><img src='"+request.getContextPath()+"/img/icons/edit.png'></a></center></td>");
    				rows.append("<td><center><a class='deleteElement' data-ejercicio=\""+ejercicio.getIdEjercicioRow()+"\"><img src='"+request.getContextPath()+"/img/icons/delete.png'></a></center></td>");
    			rows.append("</tr>");
    			i++;
    		}
    		response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write(rows.toString());
    		break;
    		//Para generar la tabla de datos temporales
    	case "findTemp2":
    		int idMetaRowFT2=Integer.parseInt(request.getParameter("idMetaRow"));
    		ArrayList<EjercicioBean> listEjerciciosTemp2=new ArrayList<EjercicioBean>();
    		if(idMetaRowFT2!=0){
    			listEjerciciosTemp2=(ArrayList<EjercicioBean>) ejercicioDao.findEjerciciosMetaRow(idMetaRowFT2);
    		}else{
    			listEjerciciosTemp2=(ArrayList<EjercicioBean>) ejercicioDao.findEjerciciosTemp(idEmpresa);
    		}
    		StringBuilder rows2 =new StringBuilder();
    		int j=1;
    		//SimpleDateFormat formatoDelTexto4 =new SimpleDateFormat("dd/MM/yyyy");
    		for(EjercicioBean ejercicio: listEjerciciosTemp2){
    			/*String tipoDoc="";
    			switch(ejercicio.getTipoDoc()){
				case "solicitud":
					tipoDoc="Solicitud";
					break;
				case "infCom":
					tipoDoc="Informe del Comisario";
					break;
				case "def":
					tipoDoc="Dictamen de los Estados Financieros";
					break;
				case "df":
					tipoDoc="Dictamen Fiscal";
					break;
				}*/
    			if(j % 2 == 0){
        			rows2.append("<tr class='tableRow2'>");
    			}else{
    				rows2.append("<tr>");
    			}
    				rows2.append("<td>"+ejercicio.getTipoDoc()+"</td>");
    				rows2.append("<td>"+(ejercicio.getFechaDocumentum()!=null?ejercicio.getFechaDocumentum():"")+"</td>");
    				rows2.append("<td>"+(ejercicio.getFechaEntrega()!=null?ejercicio.getFechaEntrega():"")+"</td>");
    				rows2.append("<td>"+(ejercicio.getNoDocumentum()!=null?ejercicio.getNoDocumentum():"")+"<a href=\"javascript:getDocumentumDeTabla('"+ejercicio.getNoDocumentum()+"')\"><img src=\""+request.getContextPath()+"/img/List_16.png\"></a></td>");
    			rows2.append("</tr>");
    			j++;
    		}
    		response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write(rows2.toString());
    		break;
    	case "findAll":
    		//Aqui se mandaran llamar los ejercicios filtrados por metarow
    		break;	
    	case "findEdit":
    		//SimpleDateFormat formatoDelTexto5 =new SimpleDateFormat("dd/MM/yyyy");
    		int idEjercicioEdit = Integer.parseInt(request.getParameter("idRow"));
    		EjercicioBean ejercicio=ejercicioDao.findOne(idEjercicioEdit);
    		if(ejercicio.getIdEjercicioRow()!=0){
    			JsonObject json = Json.createObjectBuilder()
    					.add("id_ejercicio", ejercicio.getIdEjercicioRow())
    					//.add("ejercicio_social",ejercicio.getEjercicioSocial())
    					.add("no_documentum", ejercicio.getNoDocumentum()!=null?ejercicio.getNoDocumentum():"")
    					.add("fecha_documentum", (ejercicio.getFechaDocumentum()!=null)?ejercicio.getFechaDocumentum().toString():"")
    					.add("fecha_entrega", (ejercicio.getFechaEntrega()!=null)?ejercicio.getFechaEntrega().toString():"")
    					.add("tipo_documento", ejercicio.getTipoDoc()!=null?ejercicio.getTipoDoc():"")
    					.add("status", "ok").build();
    			
    			response.setContentType("application/json");
    			response.setCharacterEncoding("utf-8");
    			PrintWriter out = response.getWriter();
    			out.print(json.toString());
    		}else{
    			JsonObject json = Json.createObjectBuilder()
    					.add("status","error").build();
    			
    			response.setContentType("application/json");
    			response.setCharacterEncoding("utf-8");
    			PrintWriter out = response.getWriter();
    			out.print(json.toString());
    		}
    		break;
    	case "update":
    		SimpleDateFormat formatoDelTexto2 = new SimpleDateFormat("dd/MM/yyyy");
    		int idEjercicioU=Integer.parseInt(request.getParameter("idEjercicioRowU"));
    		//int ejercicioSocialU=Integer.parseInt(request.getParameter("ejercicioU"));
	    	String noDocumentumU  	=	request.getParameter("documentoU");
	    	String fechaDocU			=	request.getParameter("fecha_docU");
	    	String fechaEntU			=	request.getParameter("fecha_entregaU");
	    	String tipoDoc			=	request.getParameter("typeDocU");
	    	EjercicioBean ejercicioUpdate=new EjercicioBean();
	    	ejercicioUpdate.setIdEjercicioRow(idEjercicioU);
	    	//ejercicioUpdate.setEjercicioSocial(ejercicioSocialU);
	    	ejercicioUpdate.setNoDocumentum(noDocumentumU);
	    	ejercicioUpdate.setTipoDoc(tipoDoc);
	    	ejercicioUpdate.setFechaDocumentum(fechaDocU);
	    	ejercicioUpdate.setFechaEntrega(fechaEntU);
	    	
	    	boolean statusUp=ejercicioDao.updateEjercicio(ejercicioUpdate);
	    	if(statusUp){
	    		response.setContentType("text/html;charset=UTF-8");
	            response.getWriter().write("ok");
	    	}else{
	    		response.setContentType("text/html;charset=UTF-8");
	            response.getWriter().write("error");
	    	}
	    	
	    	
    		break;
    	case "updateMetaRow":
    		
    		break;
    	case "delete":
    		int idEjercicioRow = Integer.parseInt(request.getParameter("idRow"));
    		boolean status=ejercicioDao.deleteEjercicio(idEjercicioRow);
    		if(status){
    			response.setContentType("text/html;charset=UTF-8");
                response.getWriter().write("ok");
    		}else{
    			response.setContentType("text/html;charset=UTF-8");
                response.getWriter().write("error");
    		}
    		break;
    	case "deleteAllTemp":
    		boolean statusT=ejercicioDao.deleteAllTemp(idEmpresa);
    		if(statusT){
    			response.setContentType("text/html;charset=UTF-8");
                response.getWriter().write("ok");
    		}else{
    			response.setContentType("text/html;charset=UTF-8");
                response.getWriter().write("error");
    		}
    		break;
    	}
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request,response);
	}

}
