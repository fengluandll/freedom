package mx.com.televisa.derechocorporativo.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import mx.com.televisa.derechocorporativo.bean.AgregarOtrosBean;
import mx.com.televisa.derechocorporativo.bean.SessionBean;
import mx.com.televisa.derechocorporativo.daos.AgregarOtrosDAO;


/**
 * Servlet implementation class AddAgregarServlet
 */
@WebServlet("/AddAgregarServlet")
public class AddAgregarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddAgregarServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response)throws IOException{
    	String tipoAccion			=	request.getParameter("tipoAccion");
    	SessionBean sessionBean= (SessionBean) request.getSession().getAttribute("sessionBean");
    	int idEmpresa  				= 	Integer.parseInt(sessionBean.getIdCurrentEmpresa());
    	int idUser  				= 	Integer.parseInt(sessionBean.getIdUser());
    	AgregarOtrosDAO asuntoDao = new AgregarOtrosDAO();
    	
    	switch(tipoAccion){
	    	case "insert":
	    		String idAgregar  	=	request.getParameter("idAgregar");
		    	String agregar			=	request.getParameter("agregar");
		    	int idMetaRow=Integer.parseInt(request.getParameter("idMetaRow"));
		    	System.out.println("Id meta row: "+idMetaRow);
		    	if((idAgregar!=null && !idAgregar.equals("")) && (agregar!=null) && !agregar.equals("")){
		    		AgregarOtrosBean asuntoB = new AgregarOtrosBean();
		    		asuntoB.setIdMetaRow(idMetaRow);
		    		asuntoB.setIdEmpresa(idEmpresa);
		    		asuntoB.setIdUser(idUser);
		    		asuntoB.setIdAgregar(idAgregar);
		    		asuntoB.setAgregar(agregar);
		    		if(asuntoB.getIdMetaRow()!=0){
		    			asuntoDao.createAgregarMetaRow(asuntoB);
		    			response.setContentType("text/html;charset=UTF-8");
	                    response.getWriter().write("ok");
		    		}else{
		    			asuntoDao.createAgregar(asuntoB);
		    			response.setContentType("text/html;charset=UTF-8");
	                    response.getWriter().write("ok");
		    		}
		    	}else{
		    		response.setContentType("text/html;charset=UTF-8");
	                response.getWriter().write("error");
		    	}
	    		break;
	    	case "findTemp":
	    		int idMetaRowFT=Integer.parseInt(request.getParameter("idMetaRow"));
	    		ArrayList<AgregarOtrosBean> listAsuntosTemp=new ArrayList<AgregarOtrosBean>();
	    		if(idMetaRowFT!=0){
	    			listAsuntosTemp=(ArrayList<AgregarOtrosBean>) asuntoDao.findAsuntosMetaRow(idMetaRowFT);
	    		}else{
	    			listAsuntosTemp=(ArrayList<AgregarOtrosBean>) asuntoDao.findAsuntosTemp(idEmpresa);    			    			
	    		}
	    		StringBuilder rows =new StringBuilder();
	    		int i=1;
	    		for(AgregarOtrosBean asunt: listAsuntosTemp){
	    			if(i % 2 == 0){
	        			rows.append("<tr class='tableRow2'>");
	    			}else{
	    				rows.append("<tr>");
	    			}
	    			
	    			rows.append("<td>"+asunt.getIdAgregar()+"</td>");
    				rows.append("<td>"+asunt.getAgregar()+"</td>");
    				rows.append("<td><center><a class='editElement' data-agregar=\""+asunt.getIdAgregarRow()+"\"><img src='"+request.getContextPath()+"/img/icons/edit.png'></a></center></td>");
    				rows.append("<td><center><a class='deleteElement' data-agregar=\""+asunt.getIdAgregarRow()+"\"><img src='"+request.getContextPath()+"/img/icons/delete.png'></a></center></td>");
    			rows.append("</tr>");
    			i++;
	    		}
	    		response.setContentType("text/html;charset=UTF-8");
	            response.getWriter().write(rows.toString());
	    		break;
	    	case "findTemp2":
	    		int idMetaRowFT2=Integer.parseInt(request.getParameter("idMetaRow"));
	    		ArrayList<AgregarOtrosBean> listAsuntosTemp2=new ArrayList<AgregarOtrosBean>();
	    		if(idMetaRowFT2!=0){
	    			listAsuntosTemp2=(ArrayList<AgregarOtrosBean>) asuntoDao.findAsuntosMetaRow(idMetaRowFT2);
	    		}else{
	    			listAsuntosTemp2=(ArrayList<AgregarOtrosBean>) asuntoDao.findAsuntosTemp(idEmpresa);
	    		}
	    		StringBuilder rows2 =new StringBuilder();
	    		int j=1;
	    		for(AgregarOtrosBean asunt: listAsuntosTemp2){
	    			if(j % 2 == 0){
	        			rows2.append("<tr class='tableRow2'>");
	    			}else{
	    				rows2.append("<tr>");
	    			}
	    				rows2.append("<td>"+asunt.getIdAgregar()+"</td>");
	    				rows2.append("<td>"+asunt.getAgregar()+"</td>");
	    			rows2.append("</tr>");
	    			j++;
	    		}
	    		response.setContentType("text/html;charset=UTF-8");
	            response.getWriter().write(rows2.toString());
	    		break;
	    	case "findEdit":
	    		//SimpleDateFormat formatoDelTexto5 =new SimpleDateFormat("dd/MM/yyyy");
	    		int idEjercicioEdit = Integer.parseInt(request.getParameter("idRow"));
	    		AgregarOtrosBean asunt=asuntoDao.findOne(idEjercicioEdit);
	    		if(asunt.getIdAgregarRow()!=0){
	    			JsonObject json = Json.createObjectBuilder()
	    					.add("id_agregar_row", asunt.getIdAgregarRow())
	    					.add("id_agregar", asunt.getIdAgregar())
	    					.add("agregar", asunt.getAgregar())
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
	    		int idAsuntoRowU=Integer.parseInt(request.getParameter("idAgregarRowU"));
	    		//int ejercicioSocialU=Integer.parseInt(request.getParameter("ejercicioU"));
		    	String idAsuntoU  	=	request.getParameter("idAgregarU");
		    	String asuntoU		=	request.getParameter("agregarU");
		    	AgregarOtrosBean asuntoUpdate = new AgregarOtrosBean();
		    	asuntoUpdate.setIdAgregarRow(idAsuntoRowU);
		    	asuntoUpdate.setIdAgregar(idAsuntoU);
		    	asuntoUpdate.setAgregar(asuntoU);
		    	
		    	boolean statusUp=asuntoDao.updateAgregar(asuntoUpdate);
		    	if(statusUp){
		    		response.setContentType("text/html;charset=UTF-8");
		            response.getWriter().write("ok");
		    	}else{
		    		response.setContentType("text/html;charset=UTF-8");
		            response.getWriter().write("error");
		    	}
		    	
		    	
	    		break;
	    	case "delete":
	    		int idEjercicioRow = Integer.parseInt(request.getParameter("idRow"));
	    		boolean status=asuntoDao.deleteAgregar(idEjercicioRow);
	    		if(status){
	    			response.setContentType("text/html;charset=UTF-8");
	                response.getWriter().write("ok");
	    		}else{
	    			response.setContentType("text/html;charset=UTF-8");
	                response.getWriter().write("error");
	    		}
	    		break;
	    	case "deleteAllTemp":
	    		boolean statusT=asuntoDao.deleteAllTemp(idEmpresa);
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
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
