package com.movemini.simpleajaxservlet.productos;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import com.movemini.config.HardCodeConstants;
import com.movemini.data.ConnectionWrapper;
import com.movemini.data.DataArray;
import com.movemini.data.OneValueFactory;
import com.movemini.data.Record;
import com.movemini.layers.session.SessionBean;
import com.movemini.simpleflexgrid.HTMLTable;
import com.movemini.simpleflexgrid.HTMLTableColumn;



public class ProductosEventoTable {

	private HttpServletRequest request;

	public ProductosEventoTable(HttpServletRequest request) {

		this.request = request;
	}


	public String getHtml() {

		String idTipoEvento = request.getParameter("id_tipo_evento");
		String id_dia = request.getParameter("id_dia");

		/*if(poId == null || poId.equals("") || poId.equals("0")) {

			return "";
		}*/


		SessionBean sessionBean = SessionBean.getInstance(request);
		String id_evento = sessionBean.getIdEvento();


	/*
		String idMoneda = OneValueFactory.get("SELECT id_moneda FROM evento WHERE id_evento = " + id_evento);


		String precio_sufix = "";

		if(idMoneda.equals("1")) {

			precio_sufix = "_mxn";

		} else {

			precio_sufix = "_usd";
		}
	*/

		String idTipoCotizacion = OneValueFactory.get("SELECT id_tipo_cotizacion FROM evento WHERE id_evento = " + id_evento);
		String idIdiomaCotizacion = OneValueFactory.get("SELECT id_idioma_cotizacion FROM evento WHERE id_evento = " + id_evento);

		String precio_sufix = "";

//		if(idMoneda.equals("2")) {
//
//			precio_sufix = "_usd";
//
//		} else {
//
//			precio_sufix = "_mxn";
//		}


		if (HardCodeConstants.ID_OMNILINGUA_USA.equals(idTipoCotizacion)) {

			precio_sufix = "_usd";

		} else {

			precio_sufix = "_mxn";
		}




		//Record head = POHeader.getHeader(poId);


		StringBuilder sb = new StringBuilder();

		ArrayList<HTMLTableColumn> columns = new ArrayList<HTMLTableColumn>();
		ArrayList<Record> records = DataArray.getArrayList("evento_producto_select_pr", id_dia, idTipoEvento);

		if(idTipoEvento.equals("1")){


			columns.add(new HTMLTableColumn("Cantidad", 				"cantidad", 		"left", 	"text4", 	"onBlur='updateCantidad(KEY, this)' onchange='updateCantidad(KEY, this)' onkeypress='return soloNumero(this)'")); //

//			if(idMoneda.equals("2")){
//				columns.add(new HTMLTableColumn("Producto", 				"nombre_ingles", 		"left", 	"label", 	"")); //"onkeyup='updateSede(KEY, this)'"
//
//			}else
//			{
//				columns.add(new HTMLTableColumn("Producto", 				"nombre", 		"left", 	"label", 	"")); //"onkeyup='updateSede(KEY, this)'"
//			}


			if (HardCodeConstants.ID_COTIZACION_INGLES.equals(idIdiomaCotizacion)) {
				//CAMBIADO PORQUE SE NECESITA EN ESPAÑOL AUNQUE LA COTIZACIÓN VAYA A SER EN INGLÉS

				columns.add(new HTMLTableColumn("Producto", 				"nombre", 		"left", 	"label", 	"")); //"onkeyup='updateSede(KEY, this)'"

			} else {


				columns.add(new HTMLTableColumn("Producto", 				"nombre", 		"left", 	"label", 	"")); //"onkeyup='updateSede(KEY, this)'"
			}




			//columns.add(new HTMLTableColumn("Descuento %", 				"descuento", 		"left", 	"number4", 	"onkeyup='updateDescuento(KEY, this)' onchange='updateDescuento(KEY, this)'")); //


			//columns.add(new HTMLTableColumn("Precio Cliente", 				"precio_cliente", 		"left", 	"label", 	""));
			columns.add(new HTMLTableColumn("Precio Cliente", 				"precio_cliente_descuento" + precio_sufix, 		"left", 	"label", 	""));



			columns.add(new HTMLTableColumn("Precio Especial", 				"precio_especial_descuento" + precio_sufix, 		"left", 	"text7", 	" onkeyup='updatePrecioEspecial(KEY, this)'"));


			columns.add(new HTMLTableColumn("Cortes&iacute;a", "cortesia", 		"left", 	"select", 	"onChange='doUpdateCortesia(KEY,this)'", "", "select i as id, n as nombre from (select 2 i, 'Cliente' n union select 3 i,'Especial' n union select 1 i, 'Ambos' n ) as t"));
			columns.add(new HTMLTableColumn("Quitar", 					"id_evento_producto", 		"center", 	"link", 	"onclick='quitarProducto(id_evento_producto)'", "<i class=\"fa fa-close fa-2x\"></i>"));

		}else
		{
			/*
			 * CC.entrega_normal,
		        CC.entrega_urgente,
		        CC.palabra_normal,
		        CC.palabra_urgente,
		        cc.id_cobro,
		        cc.normal_urgente
			 */




			columns.add(new HTMLTableColumn("Idioma", 	"idioma", 		"left", 	"label", 	"")); //
			columns.add(new HTMLTableColumn("Servicio", "servicio", 		"left", 	"label", 	""));

			columns.add(new HTMLTableColumn("Cobro por", "id_cobro", 		"left", 	"select", 	"onChange='updateProductoTEEvento(KEY,this)'", "", "SELECT id_cobro, nombre FROM cat_cobro_te"));
			columns.add(new HTMLTableColumn("Entrega", "id_entrega", 		"left", 	"select", 	"onChange='updateProductoTEEvento(KEY,this)'", "", "SELECT id_entrega, nombre FROM cat_entrega_te"));

			columns.add(new HTMLTableColumn("Cantidad", 				"cantidad", 		"left", 	"text4", 	"onkeyup='updateProductoTEEvento(KEY, this)' onkeypress='return espera(this,event)'")); //

			//columns.add(new HTMLTableColumn("Normal", 	"entrega_normal", 		"left", 	"text4", 	"onkeyup='updateCantidadProductoTE(KEY, this)' onchange='updateCantidadProductoTE(KEY, this)' onkeypress='return soloNumero(this)'"));
			//columns.add(new HTMLTableColumn("Urgente", 	"entrega_urgente", 		"left", 	"text4", 	"onkeyup='updateCantidadProductoTE(KEY, this)' onchange='updateCantidadProductoTE(KEY, this)' onkeypress='return soloNumero(this)'"));
			//columns.add(new HTMLTableColumn("Palabra Normal", 	"palabra_normal", 		"left", 	"text4", 	"onkeyup='updateCantidadProductoTE(KEY, this)' onchange='updateCantidadProductoTE(KEY, this)' onkeypress='return soloNumero(this)'"));
			//columns.add(new HTMLTableColumn("Palabra Urgente", 	"normal_urgente", 		"left", 	"text4", 	"onkeyup='updateCantidadProductoTE(KEY, this)' onchange='updateCantidadProductoTE(KEY, this)' onkeypress='return soloNumero(this)'"));

//			columns.add(new HTMLTableColumn("Cortes&iacute;a",                              "cortesia",             "center",       "checkbox",     " onchange='doUpdateCortesiaTE(KEY, this)'"));
			
			columns.add(new HTMLTableColumn("Cortes&iacute;a", "cortesia", 		"left", 	"select", 	"onChange='doUpdateCortesiaTE(KEY,this)'", "", "select i as id, n as nombre from (select 2 i, 'Cliente' n union select 3 i,'Especial' n union select 1 i, 'Ambos' n ) as t"));

			columns.add(new HTMLTableColumn("Monto", 				"monto", 		"left", 	"text4", 	"onkeyup='updateProductoTEEvento(KEY, this)' onkeypress='return soloNumero(this)'")); //
			columns.add(new HTMLTableColumn("Monto Especial", 				"monto_especial", 		"left", 	"text4", 	"onkeyup='updateProductoTEEvento(KEY, this)' onkeypress='return soloNumero(this)'")); //

			columns.add(new HTMLTableColumn("Quitar", 					"id_evento_producto", 		"center", 	"link", 	"onclick='quitarProductoTE(id_evento_producto)'", "<i class=\"fa fa-close fa-2x\"></i>"));


		}



//		columns.add(new HTMLTableColumn("Ver Fechas", 					"id", 		"center", 	"link", 	"onclick='selectSede(id)'", "<i class=\"fa fa-calendar fa-2x\"></i>"));

		//columns.add(new HTMLTableColumn("Borrar Sede", 					"id", 		"center", 	"link", 	"onclick='if(confirm('Seguro?')){deleteSede(id);}'", "<i class=\"fa fa-close fa-2x\"></i>"));





		String tableWidth = "100%";
		String additionalAttributes = "class=\"table table-bordered\"";


		sb.append(HTMLTable.getHtml(records, columns, tableWidth, additionalAttributes));


		/*
		sb.append("<br><br><br><table width='80%'>");
		sb.append("<tr>");
		sb.append("<td>");
		//sb.append("Observaciones Generales:<br><br><textarea rows='3' cols='50' onChange='updatePOComment(this)'>" + head.getString("RECEIPT_COMMENT") + "</textarea>");

		sb.append("</td>");
		sb.append("</tr>");
		sb.append("</table>");
		*/



		return sb.toString();
	}








}
