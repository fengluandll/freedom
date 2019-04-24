/**
 * @autor startOnline
 */
package mx.javaonline.colecciones;

import java.util.Iterator;
import java.util.TreeMap;

/**
 * 
 * 
 */
public class DemoHasMap {

	public DemoHasMap() {
		TreeMap<String,Integer> mapa = new TreeMap<>();
        //Map mapa = new TreeMap();
        //Map<String,Integer> mapa = new Hashtable<String,Integer> ();
        mapa.put("Angel", 1); // adding value into HashMap
        mapa.put("Ra", 2);
        mapa.put("BreeMap", 3);

        System.out.println("HashMap contiene " + mapa.size());

        if (mapa.isEmpty()) {
            System.out.println("Esta vacio");
        } else {
            iteraMapa(mapa);
        }
	}
	
    private void iteraMapa(TreeMap<String, Integer> mapa){
        //Las llaves que contiene el mapa
        Iterator<String> claves = mapa.keySet().iterator();
//        while(claves.hasNext()){
//            String clave = claves.next();
//            Object valor = mapa.get(clave);
//            System.out.println(valor);
//        }
        for(String key: mapa.keySet()){
            System.out.println(key  +" :: "+ mapa.get(key));
        }
        
//        for (Entry<String, Integer> entry : mapa.entrySet()) {
//			System.out.println(entry.getKey().toString() + " - " + entry.getValue());
//		}
    }
    
    public static void main(String args[]) {
        new DemoHasMap();
    }

}
