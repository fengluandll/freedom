/**
 * @autor startOnline
 */
package mx.javaonline.extras;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class FileWritter {
	
	private static String txt;
	
	/**
	 * Metodo constructor
	 */
	public FileWritter(){
		StringBuilder stb = leeArchivo();
		//escribeArchivo(stb);
	}
	
	private StringBuilder leeArchivo(){
		BufferedReader in;
		StringBuilder stb = new StringBuilder();
		  try {
//			  String url = System.getProperty("user.dir");
//			  System.out.println(url);
			  final FileInputStream fstream = new FileInputStream( new File("archivos/a una rosa.txt"));
			   InputStreamReader fichero = new InputStreamReader(fstream, "UTF-8");
			   
			  	in = new BufferedReader(fichero);
				while ((txt=in.readLine()) != null) {
					stb.append(txt);
					stb.append("\n");
				  }
				
			
				in.close();
			System.out.println(stb.toString());
		  } catch (IOException e) {
			e.printStackTrace();
		}
		  
		  return stb;
		  
		  
	}
	
	private static void escribeArchivo(StringBuilder stb){
		try {
			final FileOutputStream fstream = new FileOutputStream("C:\\poemas\\a una rosa copy.txt");
			OutputStreamWriter fichero = new OutputStreamWriter(fstream, "UTF-8");
			BufferedWriter out = new BufferedWriter(fichero);
			System.out.println(stb.toString());
			out.write(stb.toString());
			out.close();
			System.out.println("Archivo creado.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new FileWritter();
		
	}
	

}
