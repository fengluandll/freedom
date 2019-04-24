/**
* Project: Derecho Corporativo
*
* File: BuscaEmpresaConServlet.java
*
* Created on: Agosto 31, 2015 at 12:00
*
* Copyright (c) - Televisa - 2015
*/

package mx.com.televisa.derechocorporativo.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import mx.com.televisa.derechocorporativo.bean.CriterioBusquedaBean;
import mx.com.televisa.derechocorporativo.modules.home.MenuBean;

/**
 * Servlet implementation class BuscaEmpresaConServlet 
 *
 * @author KAZ - CONSULTING/
 *         Iván Castañeda Loeza
 *         Jesús Argumedo
 *         Eduardo Nava
 *         Ernesto Corona Mendoza
 *
 *
 *
 * @version 1.0.0
 *
 * @date Agosto 31, 2015 at 12:00 pm
 *
 */
@WebServlet("/BuscaEmpresaConServlet")
public class BuscaEmpresaConServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	CriterioBusquedaBean busquedaBean;
    MenuBean menuBean;
	
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException{
        System.out.println("Entro al servlet CONSULTA");
   	    response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        StringBuilder stb = new StringBuilder();
        busquedaBean = new CriterioBusquedaBean();
        String lstDenominacion = request.getParameter("denominacion");
        busquedaBean.setDenominacion(lstDenominacion);
        busquedaBean.setIdRol(request.getParameter("IdRol"));
        String[] check         = request.getParameterValues("chk");
        if(check != null){
       	 for(String chk : check){
           	 stb.append(chk);
           	 stb.append(",");
            }
       	 String clasif = StringUtils.substring(stb.toString(), 0,stb.toString().length() -1);
       	 busquedaBean.setIdClasificacion(clasif);
       	 System.out.println("clasificacion "+clasif);
        }else{
       	 busquedaBean.setIdClasificacion(null);
        }
        String lstPais = request.getParameter("pai");
        busquedaBean.setIdPais(Integer.parseInt(lstPais));
        System.out.println("Pais "+lstPais);
        String lstTextoBuscar     = request.getParameter("textBuscar");
        busquedaBean.setEmpresa(lstTextoBuscar.equals("Empresa")?null:lstTextoBuscar);
        System.out.println("Testo a buscar "+lstTextoBuscar);
        
        HttpSession session = request.getSession();
        
        try{
       	 menuBean = new MenuBean();
       	 String codigo = menuBean.cargaMenuCon(busquedaBean, request.getContextPath());
       	 session.setAttribute("recargaMenu", codigo);
        } catch (Exception e) {
   			e.printStackTrace();
   		} finally {
            out.close();
        }
       }

	
	  /**
     * Recibe peticiones
     * @param tuHttpServletRequest
     * @param tuHttpServletResponse 
     */
    protected void doGet(HttpServletRequest tuHttpServletRequest
                        ,HttpServletResponse tuHttpServletResponse)
                                         throws ServletException, IOException {
        processRequest(tuHttpServletRequest,tuHttpServletResponse);

    }

    /**
     * Recibe peticiones
     * @param tuHttpServletRequest
     * @param tuHttpServletResponse 
     */
    protected void doPost(HttpServletRequest tuHttpServletRequest
                         ,HttpServletResponse tuHttpServletResponse)
                                         throws ServletException, IOException {
        processRequest(tuHttpServletRequest,tuHttpServletResponse);

    }
}
