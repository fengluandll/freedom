package com.movemini.memoservicio;

import com.movemini.data.ConnectionWrapper;
import com.movemini.data.OneRecordFactory;
import com.movemini.data.Record;

public class MemoServicio {
	public String obtenerMemoDeServicio(String id_evento_version)
	{
		MemoServicioForm memoForm = new  MemoServicioForm();

		return memoForm.getFormHtml(id_evento_version);
	}

	public void actualizarCampoMemoServicio(String campo, String valor, String id){
		String sql = "UPDATE evento_memo_servicio SET " + campo + " = '" + valor + "' WHERE id_evento_memo = " + id;
		//System.out.println(sql);
		ConnectionWrapper.staticExecuteUpdate(sql);
	}

	public String obtenerCorreoContacto(String idEvento){
		String correo = "";
		Record record = OneRecordFactory.getPr("evento_correo_contacto_pr", idEvento);

		if(record != null)
		{
			correo = record.get("correo");
		}

		return correo;
	}

	public String obternerCorreosOmnilingua(){
		String correo = "";
		Record record = OneRecordFactory.getPr("evento_correo_omnilingua_pr", correo);

		if(record != null)
		{
			correo = record.get("correo");
		}

		return correo;
	}


	public String obternerCorreosInterpretes(String idEvento){
		String correo = "";
		Record record = OneRecordFactory.getPr("evento_correo_interptetes_pr", idEvento);

		if(record != null)
		{
			correo = record.get("correo");
		}

		return correo;
	}

	public String obtenerNombreEvento(String idEvento){
		String nombreEvento = "";
		Record record = OneRecordFactory.getPr("evento_select_by_id_pr", idEvento);

		if(record != null)
		{
			nombreEvento = record.get("nombre_evento");
		}

		return nombreEvento;
	}
}
