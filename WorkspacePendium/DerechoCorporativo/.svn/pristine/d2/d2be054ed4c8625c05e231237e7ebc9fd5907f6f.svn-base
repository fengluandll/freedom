package mx.com.televisa.derechocorporativo.util;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.faces.model.SelectItem;

import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;
//import mx.com.televisa.derechocorporativo.data.HQLBeanList;
//import mx.com.televisa.derechocorporativo.model.VCatalog;

public class CatalogManager {

	public static SelectItem[] getCatalog(String catalogCode) throws Exception {
		
		/*
		SelectItem[] catalogItemArray = null;			
		final HQLBeanList<VCatalog> hqlBeanList =  new HQLBeanList<VCatalog>();
		
		try {
			List<VCatalog> catalogArrayList = hqlBeanList.getList("from VCatalog Where catalogCd = '" + catalogCode + "'");
			
			catalogItemArray  = new SelectItem[catalogArrayList .size()];
			int i = 0;
			for(VCatalog catalogElement : catalogArrayList ) {	
				catalogItemArray[i] = new SelectItem(catalogElement.getCatalogValueId()+"", catalogElement.getValue());
				i++;
			}	
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			hqlBeanList.close();
		}
		
		return catalogItemArray;
		
		*/
		
		return null;
		
	}
	
	
	// Implementado para homologar la obtencion de cat�logos aunque no esten en las tablas CATALOG y CATALOG_VALUE
	
	/*
	 * public static SelectItem[] getTableCatalog(String query) throws Exception {
		
		ArrayList<String> pipedRecords = new ArrayList<String>();
		
		
		ConnectionWrapper cw = new ConnectionWrapper();
		 
		try{

				ResultSet rs  = cw.executeQuery(query);
				
				while(rs.next()){
					pipedRecords.add(rs.getString(1) + "|" + rs.getString(2));
				}
				
				rs.close();
				cw.close();
				
				
				SelectItem[] catalogItemArray = new SelectItem[pipedRecords.size()];
				
				int i = 0;
				for(String pipedRecord : pipedRecords) {
					
					StringTokenizer tok = new StringTokenizer(pipedRecord, "|");
					
					String id = (String) tok.nextElement();
					String text = (String) tok.nextElement(); 
					
					
					catalogItemArray[i] = new SelectItem(id, text);
					i++;
					
				}
				
				
				return catalogItemArray;
				
		}finally {
			
			ConnectionWrapper.closeAll(cw);
			
		} 

	}*/
}
