package com.movemini.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Record {

	private String id; // Or First Column

	private Map<String, String> map = new HashMap<String, String>();

	public Record(ResultSet set) throws SQLException {

		//TODO - Porque no se esta usando reflexion util
		
		
		//ReflexionUtil.fillObject(set, set.getMetaData(), this, Record.class);

		for (int colIndex = 1; colIndex <= set.getMetaData().getColumnCount(); colIndex++) {

			// String colName = set.getMetaData().getColumnName(colIndex);
			String colName = set.getMetaData().getColumnLabel(colIndex); // TODO
																			// VER
																			// SI
																			// ESTO
																			// MISMO
																			// APLICA
																			// EN
																			// ReflexionUtil.fillObject
																			// Si aplico 2017
			String b = set.getString(colIndex);
			map.put(colName, b == null ? "" : b);

			if (colIndex == 1) {

				id = set.getString(colIndex);
			}
		}
	}

	public String getId() {
		return id;
	}

	public String get(String key) {
		return map.get(key);
	}

	public String getString(String key) {

		try {

			String value = map.get(key);

			if (value != null) {
				return map.get(key);
			} else {

				return "";
			}

		} catch (Exception e) {
			// TODO: handle exception
			return "";
		}
	}

}
