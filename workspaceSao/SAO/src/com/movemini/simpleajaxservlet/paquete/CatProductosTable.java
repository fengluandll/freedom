package com.movemini.simpleajaxservlet.paquete;

import java.util.ArrayList;

import javax.servlet.ServletRequest;

import com.movemini.data.DataArray;
import com.movemini.data.Record;
import com.movemini.simpleflexgrid.HTMLTable;
import com.movemini.simpleflexgrid.HTMLTableColumn;

public class CatProductosTable {

	

	private ServletRequest request;

	
	public CatProductosTable(ServletRequest request) {

		this.request = request;
	}
	
	
	
	public String getHtml() {
		
		StringBuilder sb = new StringBuilder();
		
		
		String filter = request.getParameter("filter");
		
		
		filter = (filter == null) ? "" : filter;
			
		ArrayList<Record> records = DataArray.getArrayList("SELECT *, concat('$ ', FORMAT(precio_cliente_mxn , 2) ) as precio_cliente_mxn_ , concat('$ ',FORMAT(precio_especial_mxn , 2)) as precio_especial_mxn_, concat('$ ',FORMAT(costo_mxn , 2)) as costo_mxn_ from cat_producto WHERE nombre like '%" + filter + "%' AND id_status = '1' ORDER BY nombre");

		
		ArrayList<HTMLTableColumn> columns = new ArrayList<HTMLTableColumn>();
		
		columns.add(new HTMLTableColumn("Clave Producto", 			"id_producto", 		"left", 	"label", 	"")); 
		columns.add(new HTMLTableColumn("Nombre", 					"nombre", 		"left", 	"label", 	""));  
		columns.add(new HTMLTableColumn("Nombre Técnico:", 					"nombre_tecnico", 		"left", 	"label", 	""));  //"onkeyup='updateSede(KEY, this)'"
		
		columns.add(new HTMLTableColumn("Precio Cliente (MXN)", 	"precio_cliente_mxn_", 		"left", 	"label", 	""));  
		columns.add(new HTMLTableColumn("Precio Especial (MXN)", 	"precio_especial_mxn_", 		"left", 	"label", 	""));  
		columns.add(new HTMLTableColumn("Costo (MXN)", 				"costo_mxn_", 		"left", 	"label", 	""));  
		columns.add(new HTMLTableColumn("Num. Parte", 				"numero_parte", 		"left", 	"label", 	""));  
		
		columns.add(new HTMLTableColumn("Agregar", 					"id_producto", 		"center", 	"link", 	"onclick='doAgregarProducto(id_producto)'", "<i class=\"fa fa-plus fa-2x\"></i>"));
		
		
		String tableWidth = "100%";
		String additionalAttributes = "class=\"table table-bordered\" id=\"datatable\" ";
		
		
		sb.append(HTMLTable.getHtml(records, columns, tableWidth, additionalAttributes));
		
		
		
		return sb.toString();	
		
	}
	
	
	

	
	
}
