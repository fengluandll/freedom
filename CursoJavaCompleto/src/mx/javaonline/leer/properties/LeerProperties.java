/**
 * @autor startOnline
 */
package mx.javaonline.leer.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 
 * 
 */
public class LeerProperties {

	public LeerProperties() {
		Properties prop = new Properties();
		InputStream input = null;
	 
		try {
			//File file = new File("C:\\programas java\\DatosGenerales.properties");
			File file = new File("\\10.7.2.59\\erp-egr\\Conex\\erp\\DataConnection.properties");
			//File file = new File("\\\\10.7.2.59\\erp-egr\\Conex\\erp\\DataConnection.properties");
			input = new FileInputStream(file);
	 
			// load a properties file
			prop.load(input);
	 
			// get the property value and print it out
			System.out.println(prop.getProperty("usuario.contrasena.incorrecto"));
	 
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new LeerProperties();
	}

}
