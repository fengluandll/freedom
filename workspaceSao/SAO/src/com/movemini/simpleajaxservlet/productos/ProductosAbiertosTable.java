package com.movemini.simpleajaxservlet.productos;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.movemini.config.HardCodeConstants;
import com.movemini.data.DataArray;
import com.movemini.data.OneValueFactory;
import com.movemini.data.Record;
import com.movemini.layers.session.SessionBean;
import com.movemini.simpleflexgrid.HTMLTable;
import com.movemini.simpleflexgrid.HTMLTableColumn;

public class ProductosAbiertosTable {


	private HttpServletRequest request;

	public ProductosAbiertosTable(HttpServletRequest request) {

		this.request = request;
	}


	public String getHtml() {

		//String idTipoEvento = request.getParameter("id_tipo_evento");
		String id_dia = request.getParameter("id_dia");



		//SessionBean sessionBean = SessionBean.getInstance(request);
		//String id_evento = sessionBean.getIdEvento();




//		String idTipoCotizacion = OneValueFactory.get("SELECT id_tipo_cotizacion FROM evento WHERE id_evento = " + id_evento);
//		String idIdiomaCotizacion = OneValueFactory.get("SELECT id_idioma_cotizacion FROM evento WHERE id_evento = " + id_evento);
//
//		String precio_sufix = "";
//
//
//
//		if (HardCodeConstants.ID_OMNILINGUA_USA.equals(idTipoCotizacion)) {
//
//			precio_sufix = "_usd";
//
//		} else {
//
//			precio_sufix = "_mxn";
//		}
//




		StringBuilder sb = new StringBuilder();

		ArrayList<HTMLTableColumn> columns = new ArrayList<HTMLTableColumn>();


		ArrayList<Record> records = DataArray.getArrayList("select * from evento_producto_abierto where id_evento_sala = " + id_dia);
		String idTipoEvento =   OneValueFactory.get("select id_tipo_eveto from evento  where id_evento = (select id_evento from evento_version where id_evento_version = (select id_evento_version from evento_sala where id_evento_sala = "+id_dia+"))");
		if( idTipoEvento != null){
			if(idTipoEvento.equals("1")){
				columns.add(new HTMLTableColumn("Cantidad", 				"cantidad", 		"left", 	"text4", 	"onKeyUp='updateProductoAbiertoRecord(KEY, this)' onkeypress='return soloNumero(this)'")); //
				columns.add(new HTMLTableColumn("Descripci&oacute;n", 				"descripcion", 		"left", 	"textarea", 	"onKeyUp='updateProductoAbiertoRecord(KEY, this)' ")); //
				columns.add(new HTMLTableColumn("Precio Cliente", 				"precio_cliente", 		"left", 	"text4", 	"onKeyUp='updateProductoAbiertoRecord(KEY, this)' onkeypress='return soloNumero(this)'")); //
				columns.add(new HTMLTableColumn("Precio Especial", 				"precio_especial", 		"left", 	"text4", 	"onKeyUp='updateProductoAbiertoRecord(KEY, this)' onkeypress='return soloNumero(this)'")); //
				columns.add(new HTMLTableColumn("Cortes&iacute;a", "cortesia", 		"left", 	"select", 	"onChange='updateProductoAbiertoRecord(KEY,this)'", "", "select i as id, n as nombre from (select 2 i, 'Cliente' n union select 3 i,'Especial' n union select 1 i, 'Ambos' n ) as t"));

			}else{
				columns.add(new HTMLTableColumn("Cantidad", 				"cantidad", 		"left", 	"text4", 	"onKeyUp='updateProductoAbiertoRecord(KEY, this)' onkeypress='return soloNumero(this)'")); //
				columns.add(new HTMLTableColumn("Cobro por", "id_cobro", 		"left", 	"select", 	"onChange='updateProductoAbiertoRecord(KEY,this)'", "", "SELECT id_cobro, nombre FROM cat_cobro_te"));
				columns.add(new HTMLTableColumn("Servicio", 				"descripcion", 		"left", 	"text7", 	"onKeyUp='updateProductoAbiertoRecord(KEY, this)' ")); //
				columns.add(new HTMLTableColumn("Entrega", "id_entrega", 		"left", 	"select", 	"onChange='updateProductoAbiertoRecord(KEY,this)'", "", "SELECT id_entrega, nombre FROM cat_entrega_te"));
				columns.add(new HTMLTableColumn("Monto", 				"precio_cliente", 		"left", 	"text4", 	"onKeyUp='updateProductoAbiertoRecord(KEY, this)' onkeypress='return soloNumero(this)'")); //
				columns.add(new HTMLTableColumn("Costo Unitario", 				"precio_especial", 		"left", 	"text4", 	"onKeyUp='updateProductoAbiertoRecord(KEY, this)' onkeypress='return soloNumero(this)'")); //
			}
		}

		columns.add(new HTMLTableColumn("Quitar", 					"id_producto_abierto", 		"center", 	"link", 	"onclick='quitarProductoAbierto(id_producto_abierto)'", "<i class=\"fa fa-close fa-2x\"></i>"));



		String tableWidth = "100%";
		String additionalAttributes = "class=\"table table-bordered\"";


		sb.append(HTMLTable.getHtml(records, columns, tableWidth, additionalAttributes));

		return sb.toString();
	}









}
