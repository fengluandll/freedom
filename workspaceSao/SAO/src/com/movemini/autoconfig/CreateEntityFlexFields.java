package com.movemini.autoconfig;


import java.util.ArrayList;

import com.movemini.data.ConnectionWrapper;
import com.movemini.data.DataArray;
import com.movemini.data.OneValueFactory;
import com.movemini.data.Record;
import com.movemini.util.StringUtils;



public class CreateEntityFlexFields {

	/**
	 * 
	 * 
	 */
	public static void main(String ...a) {
		
		String table = "cat_observacion";
		
		ConnectionWrapper.staticExecuteUpdate(
				"INSERT INTO flex_entity " +
				"( " +
				"	title, " +
				"	title2, " +
				"	new_btn_text, " +
				"	list_table_view, " +
				"	`table`, " +
				"	column_headers, " +
				"	column_codes, " +
				"	edit_btn_text, " +
				"	delete_btn_text, " +
				"	column_id " +
				") " +
				"VALUES " +
				"( " +
				"	'SAO - Observaciones', " +
				"	'Observaciones', " +
				"	'', " +
				"	'cat_observacion' , " +
				"	'cat_observacion' , " +
				"	'Observación', " +
				"	'nombre', " +
				"	'Editar', " +
				"	'Eliminar', " +
				"	'id_observacion' " +
				")");

		
		
		
		String id = OneValueFactory.get("SELECT max(id) FROM flex_entity");
		
		
		
		ArrayList<Record> cols = DataArray.getArrayList("desc " + table);
		
		for (Record col : cols) {
			
		
			String field = col.get("Field");
			String type = col.get("Type");
			
			
			
			ConnectionWrapper.staticExecuteUpdate(
					"INSERT INTO flex_field " +
					"( " +
					"	id_flex_entity, " +
					"	label, " +
					"	code, " +
					"	align, " +
					"	type, " +
					"	js_code, " +
					"	attributes, " +
					"	catalog, " +
					"	order_id " +
					") " +
					"VALUES " +
					"( " +
					"		'" + id + "', " +
					"		'" + StringUtils.tipoTitulo(field.replaceAll("_", " "))  + "', " +
					"		'" + field + "', " +
					"		NULL, " +
					"		'text', " +
					"		'onKeyUp=''updateField(this)''', " +
					"		'style=\"width: 90%\"', " +
					"		NULL, " +
					"		'10'	 " +						
					")");			
		}
		
		
		
		
		System.out.print("ok");
		
		
	}
	
	
	
	
	
	
	
	
	
	/*
	 
	 	String table = "cat_producto_servicio";
		
		ConnectionWrapper.staticExecuteUpdate(
				"INSERT INTO flex_entity " +
				"( " +
				"	title, " +
				"	title2, " +
				"	new_btn_text, " +
				"	list_table_view, " +
				"	`table`, " +
				"	column_headers, " +
				"	column_codes, " +
				"	edit_btn_text, " +
				"	delete_btn_text, " +
				"	column_id " +
				") " +
				"VALUES " +
				"( " +
				"	'Mis Productos', " +
				"	'Mi listado de productos en NIP', " +
				"	'Nuevo Producto', " +
				"	'cat_producto_servicio', " +
				"	'cat_producto_servicio', " +
				"	'Nombre|Porcentaje Utilidad|Porcentaje Comisión', " +
				"	'nombre|porcentaje_utilidad|porcentaje_comision', " +
				"	'Actualizar Datos', " +
				"	'Suspender Temporalmente', " +
				"	'id_producto_servicio' " +
				")");
				
				
				
				
				
				
				String table = "nip_miembro";
		
		ConnectionWrapper.staticExecuteUpdate(
				"INSERT INTO flex_entity " +
				"( " +
				"	title, " +
				"	title2, " +
				"	new_btn_text, " +
				"	list_table_view, " +
				"	`table`, " +
				"	column_headers, " +
				"	column_codes, " +
				"	edit_btn_text, " +
				"	delete_btn_text, " +
				"	column_id " +
				") " +
				"VALUES " +
				"( " +
				"	'Miembros NIP', " +
				"	'Todos los Miembros de Nip', " +
				"	'Nuevo Miembro', " +
				"	'miembro_all_vw', " +
				"	'nip_miembro', " +
				"	'Nombre|Compañía / Sección|Rol', " +
				"	'NOMBRE_COMPLETO|REGION|TIPO_GRUPO', " +
				"	'Actualizar Datos', " +
				"	'Suspender Temporalmente', " +
				"	'id_miembro' " +
				")");
				
				
				
				
						String table = "nip_visitante";
		
		ConnectionWrapper.staticExecuteUpdate(
				"INSERT INTO flex_entity " +
				"( " +
				"	title, " +
				"	title2, " +
				"	new_btn_text, " +
				"	list_table_view, " +
				"	`table`, " +
				"	column_headers, " +
				"	column_codes, " +
				"	edit_btn_text, " +
				"	delete_btn_text, " +
				"	column_id " +
				") " +
				"VALUES " +
				"( " +
				"	'Registro de Visitantes', " +
				"	'Visitantes Invitados por Mi', " +
				"	'Registrar Nuevo Visitante', " +
				"	'visitante_mine_vw WHERE invitado_por = invitado_por', " +
				"	'nip_visitante' , " +
				"	'Nombre|Empresa|Telefono', " +
				"	'NOMBRE_COMPLETO|empresa|telefono', " +
				"	'Actualizar Datos', " +
				"	'', " +
				"	'id_visitante' " +
				")");
		
				
				
				
		String table = "nip_reunion";
		
		ConnectionWrapper.staticExecuteUpdate(
				"INSERT INTO flex_entity " +
				"( " +
				"	title, " +
				"	title2, " +
				"	new_btn_text, " +
				"	list_table_view, " +
				"	`table`, " +
				"	column_headers, " +
				"	column_codes, " +
				"	edit_btn_text, " +
				"	delete_btn_text, " +
				"	column_id " +
				") " +
				"VALUES " +
				"( " +
				"	'Reuniones de Negocios', " +
				"	'Mis Reuniones de Negocio', " +
				"	'Registrar Nueva Reunión', " +
				"	'reunion_mine_vw WHERE id_miembro_1 = id_miembro_1 AND id_status = 1', " +
				"	'nip_reunion' , " +
				"	'Miembro|Fecha|Telefono', " +
				"	'CON|fecha|lugar', " +
				"	'Actualizar Datos', " +
				"	'', " +
				"	'id_reunion' " +
				")");
		
		
				
				
				
				
				
				
				
					String table = "nip_prospecto";
		
		ConnectionWrapper.staticExecuteUpdate(
				"INSERT INTO flex_entity " +
				"( " +
				"	title, " +
				"	title2, " +
				"	new_btn_text, " +
				"	list_table_view, " +
				"	`table`, " +
				"	column_headers, " +
				"	column_codes, " +
				"	edit_btn_text, " +
				"	delete_btn_text, " +
				"	column_id " +
				") " +
				"VALUES " +
				"( " +
				"	'Prospectos Efectivos', " +
				"	'Mis Prospectos Efectivos', " +
				"	'Registrar Nuevo Prospecto', " +
				"	'prospecto_mine_vw WHERE id_miembro = id_miembro AND id_status = 1', " +
				"	'nip_prospecto' , " +
				"	'Prospecto|Referido Por|Fecha', " +
				"	'nombre_prospecto|REFERIDO_POR|fecha', " +
				"	'Actualizar Datos', " +
				"	'', " +
				"	'id_prospecto' " +
				")");
				
				
				
				
				
				
				
				
				
				
	 
	 
				
				
				
				
				
				
				
	 
	 
				
				
				
				
				
				
				
	 
	 
				
				
				
				
				
				
				
	 
	 
				
				
				
				
				
				
				
	 
	 
	 
	 *
	 */
}
