package com.movemini.simpleajaxservlet.paquete;

import java.util.ArrayList;

import javax.servlet.ServletRequest;

import com.movemini.data.DataArray;
import com.movemini.data.Record;
import com.movemini.simpleflexgrid.HTMLTable;
import com.movemini.simpleflexgrid.HTMLTableColumn;

public class ProductosPaqueteTable {



	private ServletRequest request;

	
	public ProductosPaqueteTable(ServletRequest request) {

		this.request = request;
	}
	
	
	
	public String getHtml() {
		
		StringBuilder sb = new StringBuilder();
		
		
		String id_paquete = request.getParameter("id");
		
			
		ArrayList<Record> records = DataArray.getArrayList("paquete_productos_select_pr", id_paquete);

		
		ArrayList<HTMLTableColumn> columns = new ArrayList<HTMLTableColumn>();
		
		
		columns.add(new HTMLTableColumn("Producto:", 					"PRODUCTO", 		"left", 	"label", 	""));  //"onkeyup='updateSede(KEY, this)'"
		
				
			  
		columns.add(new HTMLTableColumn("Cant. Min.", 				"cantidad_min", 		"left", 	"text6", 	"onkeyup=\"updatePaqueteProductoCampo(KEY, this, 'cantidad_min')\""));  
		  
		
		columns.add(new HTMLTableColumn("Cant. Max.", 				"cantidad_max", 						"left", 	"text6", 	"onkeyup=\"updatePaqueteProductoCampo(KEY, this, 'cantidad_max')\""));
		columns.add(new HTMLTableColumn("Cant. Inicial.", 				"cantidad_default", 				"left", 	"text6", 	"onkeyup=\"updatePaqueteProductoCampo(KEY, this, 'cantidad_default')\""));
		
		columns.add(new HTMLTableColumn("Precio Fijo", 				"precio_fijo", 				"left", 	"select_small", 	"onChange=\"updatePaqueteProductoCampo(KEY, this, 'precio_fijo')\"","","SELECT id, nombre FROM cat_opciones_precio_fijo"));
		
		columns.add(new HTMLTableColumn("Precio Unitario Cliente (MXN)", 				"precio_cliente_mxn", 		"left", 	"text6", 	"onkeyup=\"updatePaqueteProductoCampo(KEY, this, 'precio_cliente_mxn')\""));
		columns.add(new HTMLTableColumn("Precio Unitario Especial (MXN)", 				"precio_especial_mxn", 		"left", 	"text6", 	"onkeyup=\"updatePaqueteProductoCampo(KEY, this, 'precio_especial_mxn')\""));
		columns.add(new HTMLTableColumn("Costo (MXN)", 				"costo_mxn", 				"left", 	"text6", 	"onkeyup=\"updatePaqueteProductoCampo(KEY, this, 'costo_mxn')\""));
		columns.add(new HTMLTableColumn("Precio Unitario Cliente (USD)", 				"precio_cliente_usd", 		"left", 	"text6", 	"onkeyup=\"updatePaqueteProductoCampo(KEY, this, 'precio_cliente_usd')\""));
		columns.add(new HTMLTableColumn("Precio Unitario Especial (USD)", 				"precio_especial_usd", 		"left", 	"text6", 	"onkeyup=\"updatePaqueteProductoCampo(KEY, this, 'precio_especial_usd')\""));
		columns.add(new HTMLTableColumn("Costo (USD)", 				"costo_usd", 				"left", 	"text6", 	"onkeyup=\"updatePaqueteProductoCampo(KEY, this, 'costo_usd')\""));
		
		
		
		columns.add(new HTMLTableColumn("Quitar", 					"id_paquete_producto", 		"center", 	"link", 	"onclick='quitarProducto(id_paquete_producto)'", "<i class=\"fa fa-close fa-2x\"></i>"));
		
		
		String tableWidth = "100%";
		String additionalAttributes = "class=\"table table-bordered\"";
		
		
		sb.append(HTMLTable.getHtml(records, columns, tableWidth, additionalAttributes));
		
		   
		
		return sb.toString();

		
		
		
		
		
		
	}
	
	
	



}
