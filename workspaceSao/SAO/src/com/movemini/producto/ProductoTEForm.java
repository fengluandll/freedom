package com.movemini.producto;

import java.util.ArrayList;

import com.movemini.catalogos.ManejadorCatalogos;
import com.movemini.data.Record;
import com.movemini.simpleflexgrid.HTMLForm;
import com.movemini.simpleflexgrid.HTMLTableColumn;

public class ProductoTEForm {
	private ManejadorCatalogos mnjCatalogos = new ManejadorCatalogos();
	private String idProductoTE;

	public String getIdProductoTE() {
		return idProductoTE;
	}
	
	public ProductoTEForm(String idProductoTE){
		if(idProductoTE.equals("0")){
			Record record = mnjCatalogos.obtenerProductoTraduccionEscrita(idProductoTE);
			this.idProductoTE = record.get("id_producto");
		}else{
			this.idProductoTE = idProductoTE;
		}
	}

	public String getFormHtml(String idProductoTE) {
		
		StringBuilder sb = new StringBuilder();

		ArrayList<HTMLTableColumn> columns = new ArrayList<HTMLTableColumn>();
		
		
		Record record = mnjCatalogos.obtenerProductoTraduccionEscrita(idProductoTE);
		
		//columns.add(new HTMLTableColumn("Cobro:", 		"id_cobro", 				"left", 	"select", 	"onkeyup='updateProductoTE(this)'", "", "select id_cobro, nombre from cat_cobro_te where id_status = 1"));
		columns.add(new HTMLTableColumn("Idioma:", 		"idioma", 				"left", 	"text", 	"onkeyup='updateProductoTE(this)'",""));
		
		columns.add(new HTMLTableColumn("Idioma en Ingl&eacute;s:", 		"idioma", 				"left", 	"text", 	"onkeyup='updateProductoTE(this)'","")); 
		columns.add(new HTMLTableColumn("Servicio:", 		"servicio", 		"left", 	"text", 	"onkeyup='updateProductoTE(this)'",""));
		columns.add(new HTMLTableColumn("Servicio en Ingl&eacute;s:", 		"servicio_ingles", 		"left", 	"text", 	"onkeyup='updateProductoTE(this)'",""));
		
		columns.add(new HTMLTableColumn("Precio Cliente Cuartilla Normal:", 		"cuartilla_normal", 			"left", 	"text", 	"onkeyup='updateProductoTE(this)'",""));
		columns.add(new HTMLTableColumn("Precio Cliente Cuartilla Urgente:", 		"cuartilla_urgente", 		"left", 	"text", 	"onkeyup='updateProductoTE(this)'",""));
		columns.add(new HTMLTableColumn("Precio Especial Cuartilla Normal:", 		"cuartilla_normal_especial", 			"left", 	"text", 	"onkeyup='updateProductoTE(this)'",""));
		columns.add(new HTMLTableColumn("Precio Especial Cuartilla Urgente:", 		"cuartilla_urgente_especial", 		"left", 	"text", 	"onkeyup='updateProductoTE(this)'",""));
		
		columns.add(new HTMLTableColumn("Precio Cliente Palabra Normal:", 		"palabra_normal", 		"left", 	"text", 	"onkeyup='updateProductoTE(this)'",""));
		columns.add(new HTMLTableColumn("Precio Cliente Palabra Urgente:", 		"palabra_urgente", 		"left", 	"text", 	"onkeyup='updateProductoTE(this)'",""));
		columns.add(new HTMLTableColumn("Precio Especial Palabra Normal:", 		"palabra_normal_especial", 		"left", 	"text", 	"onkeyup='updateProductoTE(this)'",""));
		columns.add(new HTMLTableColumn("Precio Espcial Palabra Urgente:", 		"palabra_urgente_especial", 		"left", 	"text", 	"onkeyup='updateProductoTE(this)'",""));
		
		

		columns.add(new HTMLTableColumn("Precio Cliente Cuartilla Normal USD:", 		"cuartilla_normal_usd", 			"left", 	"text", 	"onkeyup='updateProductoTE(this)'",""));
		columns.add(new HTMLTableColumn("Precio Cliente Cuartilla Urgente USD:", 		"cuartilla_urgente_usd", 		"left", 	"text", 	"onkeyup='updateProductoTE(this)'",""));
		columns.add(new HTMLTableColumn("Precio Especial Cuartilla Normal USD:", 		"cuartilla_normal_especial_usd", 			"left", 	"text", 	"onkeyup='updateProductoTE(this)'",""));
		columns.add(new HTMLTableColumn("Precio Especial Cuartilla Urgente USD:", 		"cuartilla_urgente_especial_usd", 		"left", 	"text", 	"onkeyup='updateProductoTE(this)'",""));
		
		columns.add(new HTMLTableColumn("Precio Cliente Palabra Normal USD:", 		"palabra_normal_usd", 		"left", 	"text", 	"onkeyup='updateProductoTE(this)'",""));
		columns.add(new HTMLTableColumn("Precio Cliente Palabra Urgente USD:", 		"palabra_urgente_usd", 		"left", 	"text", 	"onkeyup='updateProductoTE(this)'",""));
		columns.add(new HTMLTableColumn("Precio Especial Palabra Normal USD:", 		"palabra_normal_especial_usd", 		"left", 	"text", 	"onkeyup='updateProductoTE(this)'",""));
		columns.add(new HTMLTableColumn("Precio Espcial Palabra Urgente USD:", 		"palabra_urgente_especial_usd", 		"left", 	"text", 	"onkeyup='updateProductoTE(this)'",""));
		
		//columns.add(new HTMLTableColumn("Entrega:", 		"normal_urgente", 		"left", 	"select", 	"onchange='updateProductoTE(this)'", "", "select id_entrega, nombre from cat_entrega_te where id_status = 1"));
		//normal_urgente
		String additionalAttributes = "";
		
		sb.append(HTMLForm.getFormHtml(record, columns, additionalAttributes));

		return sb.toString();
	}
}