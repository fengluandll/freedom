package com.movemini.subtotales;

import com.movemini.data.ConnectionWrapper;

public class CotizacionSubtotales {

	public void crearSubtotal(String idEvento, String nombre,String texto)
	{
		String qry = "insert into evento_cotizacion_subtotales (id_evento_version, descripcion,texto) values ( " + idEvento + ", '" + nombre + "','"+texto+"' )";

		ConnectionWrapper.staticExecuteUpdate(qry);
	}

	public void actualizarSubtotalSala(String campo, String valor, String idSala){
		String sql = "UPDATE evento_sala SET " + campo + " = '" + valor + "' WHERE id_evento_sala = " + idSala;

		ConnectionWrapper.staticExecuteUpdate(sql);
	}
}
