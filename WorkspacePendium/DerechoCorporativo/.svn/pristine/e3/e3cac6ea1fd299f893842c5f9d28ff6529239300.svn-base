package mx.com.televisa.derechocorporativo.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ErrorsUtils {
	
	public static String getStackTraceAsString(Throwable thr) {
		if (thr != null) {
			StringWriter swrt = new StringWriter();
			PrintWriter pwrt = new PrintWriter(swrt);
			thr.printStackTrace(pwrt);
			return swrt.toString();
		} else {
			return "No fue posible procesar el error!";
		}
	}
	
}
