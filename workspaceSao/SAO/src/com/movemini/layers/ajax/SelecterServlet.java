package com.movemini.layers.ajax;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movemini.config.HardCodeConstants;
import com.movemini.data.OneValueFactory;
import com.movemini.layers.session.SessionBean;
import com.movemini.util.NumberFormatter;
import com.movemini.util.NumbersUtil;

/**
 * Servlet implementation class SelecterServlet
 */
@WebServlet("/SelecterServlet")
public class SelecterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelecterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());


		String result = "";
		
		String div_field_code = request.getParameter("div_field_code");
		
		
		StringTokenizer field_code_tok = new StringTokenizer(div_field_code, "-");
		
		
		String trash1 = field_code_tok.nextToken();
		String column_code = field_code_tok.nextToken();
		String recordId = field_code_tok.nextToken();
		

		SessionBean sessionBean = SessionBean.getInstance(request);
		String id_evento = sessionBean.getIdEvento();
		
		
		
		//String idMoneda = OneValueFactory.get("SELECT id_moneda FROM evento WHERE id_evento = " + id_evento);
		String idTipoCotizacion = OneValueFactory.get("SELECT id_tipo_cotizacion FROM evento WHERE id_evento = " + id_evento);

		String precio_sufix = "";

		/*
		if (idMoneda.equals("1")) {

			precio_sufix = "_mxn";

		} else {

			precio_sufix = "_usd";
		}
		*/
		if (HardCodeConstants.ID_OMNILINGUA_USA.equals(idTipoCotizacion)) {

			precio_sufix = "_usd";
			

		} else {

			precio_sufix = "_mxn";
		}
		

		if(column_code.contains("precio_cliente_descuento")) {
			
			result = OneValueFactory.get("SELECT precio_cliente_descuento" + precio_sufix + " FROM evento_producto WHERE id_evento_producto = '" + recordId  + "'");
		
			result = NumberFormatter.conComas(result);
		}


		if(column_code.contains("precio_especial_descuento")) {
	
			result = OneValueFactory.get("SELECT precio_especial_descuento" + precio_sufix + " FROM evento_producto WHERE id_evento_producto = '" + recordId  + "'");
		
			//result = NumberFormatter.conComas(result);
			
			result = "<input type=\"text\" size=\"7\" id=\"precio_especial_descuento" + precio_sufix + "\" value=\"" + result + "\" onkeyup=\"updatePrecioEspecial(" + recordId + ", this)\">";
			
			
			
		}

		
		
		
		
		
		
		response.getWriter().append(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
