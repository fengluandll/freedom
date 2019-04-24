package mx.com.televisa.derechocorporativo.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import mx.com.televisa.derechocorporativo.reflexion.ReflexionUtil;

public class Record {


	private Map<String, String> map = new HashMap<String, String>();
	
	
	
	public String get(String key) {
		return map.get(key);
	}
	
	
	
	public Record(ResultSet set) throws SQLException {
		
		ReflexionUtil.fillObject(set, set.getMetaData(), this, Record.class);
		
		for (int colIndex = 1; colIndex <= set.getMetaData().getColumnCount(); colIndex++) {
			
			String colName = set.getMetaData().getColumnName(colIndex);
			map.put(colName, set.getString(colIndex));
		}
	}
}
