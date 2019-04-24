package com.movemini.layers.view.menu;

import com.movemini.data.OneValueFactory;

public class RecordCounter {

	
	public static String getCount(String idUser, String url) {

		String count = "";
		
		
		if(!url.contains("adm_eventos_list_m")
				&& 
				!url.contains("adm_eventos_list_a")
				) {
			
			return "";
		}
		
		
		
		
		String cond = "";
		if(url.contains("adm_eventos_list_m")) {
			
			cond = " AND id_usuario = " + idUser;
		}
		
		
		String idStatus = url.replace("adm_eventos_list_m", "")
								.replace("adm_eventos_list_a", "")
								.replace(".jsp", "")
								;
		
		
		if("ALL".equals(idStatus)) {
			
			count = OneValueFactory.get("SELECT count(*) FROM evento WHERE 1 = 1 " + cond);

		} else {
			
			count = OneValueFactory.get("SELECT count(*) FROM evento WHERE id_evento_status = " + idStatus + cond);	
		}
			
		
		
		
		
		if(count != "" 
					&& !count.equals("0")
					) {
			
			return " (" + count + ")";
		} else {
			
			return "";
		}
	}
	
}

