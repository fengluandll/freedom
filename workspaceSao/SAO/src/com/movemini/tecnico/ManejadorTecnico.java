package com.movemini.tecnico;

import java.util.ArrayList;

import javax.servlet.ServletRequest;

import com.movemini.catalogos.ManejadorCatalogos;
import com.movemini.data.ConnectionWrapper;
import com.movemini.data.DataArray;
import com.movemini.data.OneRecordFactory;
import com.movemini.data.OneValueFactory;
import com.movemini.data.Record;

public class ManejadorTecnico {

	private ManejadorCatalogos mnjCatalogos = new ManejadorCatalogos();
	private int idTecnico;

	public int getIdTecnico() {
		return idTecnico;
	}

	public void crearTecnico(int idTecnico){

		if(idTecnico <= 0) {
			ConnectionWrapper.staticExecuteUpdate("INSERT INTO cat_tecnicos (id_tecnico) VALUES (0)");

			idTecnico = Integer.parseInt(OneValueFactory.get("select max(id_tecnico) FROM cat_tecnicos"));
		}

	    this.idTecnico = idTecnico;
	}

	public void crearEventoTecnico(String idEventoSala){
		ConnectionWrapper.staticExecuteUpdate("INSERT INTO evento_tecnico (id_evento_sala) VALUES ('" + idEventoSala + "')");
	}

	public String validarTecnicoAsignadoEvento(String idEventoTecnico, String idTecnico){
		String result = "";
		String matches = OneValueFactory.get("evento_tecnico_validacion_pr", idEventoTecnico, idTecnico);

		if(!matches.equals("0")) {

			result = "El T&eacute;cnico ya esta asignado a un evento en esa fecha. Desea continuar con la asignaci&oacute;n?";
		}

		return result;
	}


	public String crearTablaTecnico(int idTecnico){

		TecnicoTable tablaTecnico = new TecnicoTable(String.valueOf(idTecnico));

		return tablaTecnico.getFormHtml(mnjCatalogos.obtenerTecnico(idTecnico));
	}

	public String crearTablaNominaTecnico(int idTecnico){

		TecnicoTable tablaTecnico = new TecnicoTable(String.valueOf(idTecnico));

		return tablaTecnico.getFormHtmlCuentaNomina(mnjCatalogos.obtenerTecnico(idTecnico));
	}
	public String crearTablaViaticosTecnico(int idTecnico){

	TecnicoTable tablaTecnico = new TecnicoTable(String.valueOf(idTecnico));

	return tablaTecnico.getFormHtmlCuentaViaticos(mnjCatalogos.obtenerTecnico(idTecnico));
}

	public String crarTablaTecnicosAsignados(String id_evento_version){

		TecnicosAsignadosTable tecnicosAsignadosTable = new TecnicosAsignadosTable();
		return tecnicosAsignadosTable.getHtml(id_evento_version);
	}

	public String crearTablaTecnicosDisponibles(){

		TecnicosDisponiblesTable tecnicosAsignadosTable = new TecnicosDisponiblesTable();

		return tecnicosAsignadosTable.getHtml(mnjCatalogos.obtenerTecnicosDisponibles());
	}

	public void actualiarTecnico(String fieldId, String fieldValue, int idTecnico){

		String sql = "UPDATE cat_tecnicos SET " + fieldId + " = '" + fieldValue + "' WHERE id_tecnico = '" + idTecnico + "'";

		ConnectionWrapper.staticExecuteUpdate(sql);
	}

	public void actualizarCampoEventoTecnico(String id, String valor, String campo){

		String sql = "";

		if(campo.equals("fecha")){
			//DATE_FORMAT(date,'%d/%m/%Y')
			//sql = "UPDATE evento_tecnico SET " + campo +" =  DATE(str_to_date('" + valor + "', '%d/%m/%Y')) WHERE id_evento_tecnico = '" + id + "'";
			sql = "UPDATE evento_tecnico SET " + campo +" =  str_to_date('" + valor + "', '%d/%m/%Y') WHERE id_evento_tecnico = '" + id + "'";

		}else{

			sql = "UPDATE evento_tecnico SET " + campo +" = '" + valor + "' WHERE id_evento_tecnico = '" + id + "'";
		}
		ConnectionWrapper.staticExecuteUpdate(sql);

	}

	public void actualizarAsignacionTecnico(String idTecnico, String idEventoTecnico){
		String sql = "UPDATE evento_tecnico SET id_tecnico = '" + idTecnico + "' "
				+ "WHERE id_evento_tecnico = '" + idEventoTecnico + "'";

		ConnectionWrapper.staticExecuteUpdate(sql);
	}

	public void quitaAsignacionTecnico(String idEventoTecnico) {
		String sql = "UPDATE evento_tecnico SET id_tecnico = null "
				+ "WHERE id_evento_tecnico = '" + idEventoTecnico + "'";

		ConnectionWrapper.staticExecuteUpdate(sql);
	}

	public void quitaTecnico(String idEventoTecnico) {
		String sql = "DELETE FROM  evento_tecnico "
				+ "WHERE id_evento_tecnico = '" + idEventoTecnico + "'";

		ConnectionWrapper.staticExecuteUpdate(sql);
	}
}
