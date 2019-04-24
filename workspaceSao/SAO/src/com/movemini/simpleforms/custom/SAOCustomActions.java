package com.movemini.simpleforms.custom;

import com.movemini.data.ConnectionWrapper;
import com.movemini.data.Record;
import com.movemini.layers.session.SessionBean;
import com.movemini.simpleforms.SimpleFormManager;

public class SAOCustomActions {

	public static void postInsert(SimpleFormManager simpleFormManager) {

		SessionBean sessionBean = SessionBean.getInstance(simpleFormManager.getRequest());
		Record usr = sessionBean.getUser();
		
		String currentTable = simpleFormManager.getScreenConfig().get("table");
		
		if(currentTable.equals("nip_visitante")) {
			
			ConnectionWrapper.staticExecuteUpdate("UPDATE nip_visitante SET invitado_por = " + usr.getId() + " WHERE id_visitante = " + simpleFormManager.getId());
		}
		
		
		if(currentTable.equals("nip_reunion")) {
			
			ConnectionWrapper.staticExecuteUpdate("UPDATE nip_reunion SET id_miembro_1 = " + usr.getId() + " WHERE id_reunion = " + simpleFormManager.getId());
		}
		
		

		if(currentTable.equals("nip_prospecto")) {
			
			ConnectionWrapper.staticExecuteUpdate("UPDATE nip_prospecto SET id_miembro = " + usr.getId() + " WHERE id_prospecto = " + simpleFormManager.getId());
		}
		
		
		if(currentTable.equals("nip_negocio_concretado")) {
			
			ConnectionWrapper.staticExecuteUpdate("UPDATE nip_negocio_concretado SET id_miembro = " + usr.getId() + " WHERE id_negocio_concretado = " + simpleFormManager.getId());
		}
	
	
	}

}
