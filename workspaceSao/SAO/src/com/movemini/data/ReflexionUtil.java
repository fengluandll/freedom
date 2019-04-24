package com.movemini.data;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;


public class ReflexionUtil {

	public static void fillObject(ResultSet set, ResultSetMetaData metaData, Object obj, Class clazz) {
				
		try {
			
			for (int colIndex = 1; colIndex <= metaData.getColumnCount(); colIndex++) {
	
				//String colName = metaData.getColumnName(colIndex);
				String colName = metaData.getColumnLabel(colIndex);
				String colNameWithPattern = colName.replace("_", "");
				
				Object fieldValue;
				
				Field[] fields = clazz.getDeclaredFields();
	
				for(Field field : fields){
					
					String fieldName = field.getName().replace("_", "");
					if(fieldName.toLowerCase().equals(colNameWithPattern.toLowerCase())) {
						
						field.setAccessible(true);
						if(field.getType().getName().equals("java.lang.String")) {
	
							fieldValue = set.getString(colIndex);
							field.set(obj, fieldValue==null?"":fieldValue);
							
						} else if(field.getType().getName().equals("int")) {
							
							fieldValue = set.getInt(colIndex);
							field.set(obj, fieldValue==null?"":fieldValue);
						
						} else if(field.getType().getName().equals("double")) {
							
							fieldValue = set.getDouble(colIndex);
							field.set(obj, fieldValue==null?"":fieldValue);
						
						} else if(field.getType().getName().equals("java.util.Date")) {
							
							fieldValue = set.getDate(colIndex);
							field.set(obj, fieldValue==null?"":fieldValue);
						
						}
						
						
						if(field.getType().isAssignableFrom(String.class)) {
	
							fieldValue = set.getString(colIndex);
							field.set(obj, fieldValue==null?"":fieldValue);
							
						} else if(field.getType().isAssignableFrom(Integer.class)) {
							
							fieldValue = set.getInt(colIndex);
							field.set(obj, fieldValue==null?"":fieldValue);
						
						} else if(field.getType().isAssignableFrom(Double.class)) {
							
							fieldValue = set.getDouble(colIndex);
							field.set(obj, fieldValue==null?"":fieldValue);
						
						}
						
						
						
					}
						
				}
			}
			
		}catch(Exception ex) {
			
			ex.printStackTrace();
		}
	}
}
