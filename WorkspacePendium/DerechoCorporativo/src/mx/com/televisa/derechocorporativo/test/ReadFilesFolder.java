package mx.com.televisa.derechocorporativo.test;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import mx.com.televisa.derechocorporativo.modules.reports.tenecascada.TenenciaCascada;
import mx.com.televisa.derechocorporativo.util.ErrorsUtils;

import org.apache.log4j.Logger;

public class ReadFilesFolder {
	final static Logger log = Logger.getLogger(ReadFilesFolder.class);
	public static void main(String args[]) {
		
		String uu = null;
		
		try{
			uu.length();
		}catch(Exception e){
			e.printStackTrace();
			log.error(ErrorsUtils.getStackTraceAsString(e));
		}
		
		
		String path = "C:" + File.separator + "Users" + File.separator
				+ "Antonio" + File.separator + "Documents" + File.separator
			+ "WorkSpace" + File.separator + "DerechoCorporativo"
				+ File.separator + "src" + File.separator;
		String pathJSP = "C:" + File.separator + "Users" + File.separator
				+ "Antonio" + File.separator + "Documents" + File.separator
				+ "WorkSpace" + File.separator + "DerechoCorporativo"
				+ File.separator + "WebContent" + File.separator + "jsp"
				+ File.separator;
		
		String pathJS = "C:" + File.separator + "Users" + File.separator
				+ "Antonio" + File.separator + "Documents" + File.separator
				+ "WorkSpace" + File.separator + "DerechoCorporativo"
				+ File.separator + "WebContent" + File.separator + "js"
				+ File.separator;

		File directory = new File(path);

		List<File> list = new ArrayList<File>();
		getFiles(directory, list,".java");
		System.out.println("----JAVA----");
		System.out.println("Número de archivos: " + list.size());
		for (File file : list) {
			System.out.println(file.getName());
		}

		File directoryJSP = new File(pathJSP);

		List<File> listJSP = new ArrayList<File>();
		getFiles(directoryJSP, listJSP,".jsp");
		System.out.println("----JSP----");
		System.out.println("Número de archivos: " + listJSP.size());
		for (File file : listJSP) {
			System.out.println(file.getName());
		}
		
		File directoryJS = new File(pathJS);

		List<File> listJS = new ArrayList<File>();
		getFiles(directoryJS, listJS,".js");
		System.out.println("----JS----");
		System.out.println("Número de archivos: " + listJS.size());
		for (File file : listJS) {
			System.out.println(file.getName());
		}

	}


	public static void getFiles(File rootfile, List<File> list,String tipo) {
		File[] files;
		if (rootfile.isDirectory()) {
			files = rootfile.listFiles();
		} else
			return;
		for (File file : files) {
			if (file.isDirectory()) {
				getFiles(file, list,tipo);
			} else {
				if (file.getName().endsWith(tipo)) {
					list.add(file);
				}
			}
		}

	}

	
}
