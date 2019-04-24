package mx.com.televisa.derechocorporativo.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.com.televisa.derechocorporativo.bean.AsuntoBean;
import mx.com.televisa.derechocorporativo.bean.EjercicioBean;
import mx.com.televisa.derechocorporativo.bean.SessionBean;
import mx.com.televisa.derechocorporativo.daos.AsuntoDao;

/**
 * Servlet implementation class AddAsuntoServlet
 */
@WebServlet("/AddAsuntoServlet")
public class AddAsuntoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddAsuntoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void processRequest(HttpServletRequest request, HttpServletResponse response)throws IOException{
    	String tipoAccion			=	request.getParameter("tipoAccion");
    	SessionBean sessionBean= (SessionBean) request.getSession().getAttribute("sessionBean");
    	int idEmpresa  				= 	Integer.parseInt(sessionBean.getIdCurrentEmpresa());
    	int idUser  				= 	Integer.parseInt(sessionBean.getIdUser());
    	AsuntoDao asuntoDao = new AsuntoDao();
    	
    	switch(tipoAccion){
	    	case "insert":
	    		String idAsunto  	=	request.getParameter("idAsunto");
		    	String asunto			=	request.getParameter("asunto");
		    	int idMetaRow=Integer.parseInt(request.getParameter("idMetaRow"));
		    	System.out.println("Id meta row: "+idMetaRow);
		    	if((idAsunto!=null && !idAsunto.equals("")) && (asunto!=null) && !asunto.equals("")){
		    		AsuntoBean asuntoB = new AsuntoBean();
		    		asuntoB.setIdMetaRow(idMetaRow);
		    		asuntoB.setIdEmpresa(idEmpresa);
		    		asuntoB.setIdUser(idUser);
		    		asuntoB.setIdAsunto(idAsunto);
		    		asuntoB.setAsunto(asunto);
		    		if(asuntoB.getIdMetaRow()!=0){
		    			asuntoDao.createAsuntoMetaRow(asuntoB);
		    			response.setContentType("text/html;charset=UTF-8");
	                    response.getWriter().write("ok");
		    		}else{
		    			asuntoDao.createAsunto(asuntoB);
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
	    		ArrayList<AsuntoBean> listAsuntosTemp=new ArrayList<AsuntoBean>();
	    		if(idMetaRowFT!=0){
	    			listAsuntosTemp=(ArrayList<AsuntoBean>) asuntoDao.findAsuntosMetaRow(idMetaRowFT);
	    		}else{
	    			listAsuntosTemp=(ArrayList<AsuntoBean>) asuntoDao.findAsuntosTemp(idEmpresa);    			    			
	    		}
	    		StringBuilder rows =new StringBuilder();
	    		int i=1;
	    		for(AsuntoBean asunt: listAsuntosTemp){
	    			if(i % 2 == 0){
	        			rows.append("<tr class='tableRow2'>");
	    			}else{
	    				rows.append("<tr>");
	    			}
	    			
	    			rows.append("<td>"+asunt.getIdAsunto()+"</td>");
    				rows.append("<td>"+asunt.getAsunto()+"</td>");
    				rows.append("<td><center><a class='editElement' data-asunto=\""+asunt.getIdAsuntoRow()+"\"><img src='"+request.getContextPath()+"/img/icons/edit.png'></a></center></td>");
    				rows.append("<td><center><a class='deleteElement' data-asunto=\""+asunt.getIdAsuntoRow()+"\"><img src='"+request.getContextPath()+"/img/icons/delete.png'></a></center></td>");
    			rows.append("</tr>");
    			i++;
	    		}
	    		response.setContentType("text/html;charset=UTF-8");
	            response.getWriter().write(rows.toString());
	    		break;
	    	case "findTemp2":
	    		int idMetaRowFT2=Integer.parseInt(request.getParameter("idMetaRow"));
	    		ArrayList<AsuntoBean> listAsuntosTemp2=new ArrayList<AsuntoBean>();
	    		if(idMetaRowFT2!=0){
	    			listAsuntosTemp2=(ArrayList<AsuntoBean>) asuntoDao.findAsuntosMetaRow(idMetaRowFT2);
	    		}else{
	    			listAsuntosTemp2=(ArrayList<AsuntoBean>) asuntoDao.findAsuntosTemp(idEmpresa);
	    		}
	    		StringBuilder rows2 =new StringBuilder();
	    		int j=1;
	    		for(AsuntoBean asunt: listAsuntosTemp2){
	    			if(j % 2 == 0){
	        			rows2.append("<tr class='tableRow2'>");
	    			}else{
	    				rows2.append("<tr>");
	    			}
	    				rows2.append("<td>"+asunt.getIdAsunto()+"</td>");
	    				rows2.append("<td>"+asunt.getAsunto()+"</td>");
	    			rows2.append("</tr>");
	    			j++;
	    		}
	    		response.setContentType("text/html;charset=UTF-8");
	            response.getWriter().write(rows2.toString());
	    		break;
	    	case "findEdit":
	    		//SimpleDateFormat formatoDelTexto5 =new SimpleDateFormat("dd/MM/yyyy");
	    		int idEjercicioEdit = Integer.parseInt(request.getParameter("idRow"));
	    		AsuntoBean asunt=asuntoDao.findOne(idEjercicioEdit);
	    		if(asunt.getIdAsuntoRow()!=0){
	    			JsonObject json = Json.createObjectBuilder()
	    					.add("id_asunto_row", asunt.getIdAsuntoRow())
	    					.add("id_asunto", asunt.getIdAsunto())
	    					.add("asunto", asunt.getAsunto())
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
	    		int idAsuntoRowU=Integer.parseInt(request.getParameter("idAsuntoRowU"));
	    		//int ejercicioSocialU=Integer.parseInt(request.getParameter("ejercicioU"));
		    	String idAsuntoU  	=	request.getParameter("idAsuntoU");
		    	String asuntoU		=	request.getParameter("asuntoU");
		    	AsuntoBean asuntoUpdate=new AsuntoBean();
		    	asuntoUpdate.setIdAsuntoRow(idAsuntoRowU);
		    	asuntoUpdate.setIdAsunto(idAsuntoU);
		    	asuntoUpdate.setAsunto(asuntoU);
		    	
		    	boolean statusUp=asuntoDao.updateAsunto(asuntoUpdate);
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
	    		boolean status=asuntoDao.deleteAsunto(idEjercicioRow);
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
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request,response);
	}

}
