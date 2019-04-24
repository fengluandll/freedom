/**
 * @autor startOnline
 */
package mx.javaonline.colecciones;

import java.util.Map;
import java.util.TreeMap;

/**
 * 
 * 
 */
public class DemoTreeMap {

	public DemoTreeMap() {
		Map<String, Integer> students = new TreeMap<String, Integer>();
        //Add Key/Value pairs
        students.put("ed", 47);
        students.put("zlan", 34);
        students.put("sheila", 65);
        students.put("becca", 44);
 
        //Iterate over HashMap
        for(String key: students.keySet()){
            System.out.println(key  +" :: "+ students.get(key));
        }
	}
	
	public static void main(String[] args) {
		new DemoTreeMap();
	}

}
