package com.movemini.catalogos;

import java.util.ArrayList;

import com.movemini.data.ConnectionWrapper;
import com.movemini.data.DataArray;
import com.movemini.data.OneRecordFactory;
import com.movemini.data.Record;

public class ManejadorCatalogos {


	public ArrayList<Record> obtenerSalas(String id_evento_version){

		ArrayList<Record> lstSalas = DataArray.getArrayList("evento_sala_select_pr", id_evento_version);

		return lstSalas;
	}

	public Record obtenerTecnico(int idTecnico){

		Record record = OneRecordFactory.getFirstRecord("SELECT * FROM cat_tecnicos WHERE id_tecnico =" + idTecnico);

		return record;
	}

	public ArrayList<Record> obtenerTecnicosAsignados(String idEventoSala){

		ArrayList<Record> records = DataArray.getArrayList("evento_tecnico_select_by_sala_pr", idEventoSala);

		return records;
	}

	public ArrayList<Record> obtenerTecnicosDisponibles(){

		String sql = "SELECT  " +
				"	id_tecnico, " +
				"	CONCAT( " +
				"		IFNULL(nombre,''), ' ', " +
				"		IFNULL(apellido,'') " +
				"	) nombre_completo " +
				"FROM cat_tecnicos " +
                "WHERE id_status = 1 ORDER BY nombre"
                ;

		ArrayList<Record> lstTecnicos = DataArray.getArrayList(sql);

		return lstTecnicos;
	}

	public ArrayList<Record> obtenerTraductores(){

		ArrayList<Record> traductores = DataArray.getArrayList("SELECT * FROM cat_traductor WHERE id_status = 1");

		return traductores;
	}

	public Record obtenerTraductor(String idTraductor){
		Record record = OneRecordFactory.getPr("cat_traductores_select_pr", idTraductor);

		return record;
	}

	public void actualizarCampoTraductor(String campo, String valor, String idTraductor){
		String sql = "UPDATE cat_traductor SET " + campo + " = '" + valor + "' WHERE id_traductor = '" + idTraductor + "'";
		ConnectionWrapper.staticExecuteUpdate(sql);
	}

	public ArrayList<Record> obtenerCatalogoTraduccionEscrita(){
		ArrayList<Record> traductores = DataArray.getArrayList("SELECT * FROM cat_producto_tescrita WHERE id_status = 1");

		return traductores;
	}

	public Record obtenerProductoTraduccionEscrita(String idProducto){
		Record record = OneRecordFactory.getPr("cat_producto_te_select_pr", idProducto);

		return record;
	}

	public void actualizarCampoProductoTraduccionEscrita(String campo, String valor, String idProducto){
		String sql = "UPDATE cat_producto_tescrita SET " + campo + " = '" + valor + "' WHERE id_producto = '" + idProducto + "'";
		ConnectionWrapper.staticExecuteUpdate(sql);
	}
}
