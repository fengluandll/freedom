import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import mx.javaonline.util.ManejoFechas;


public class Tester {
	
	public static void main(String[] args) {
		Date fec = ManejoFechas.deStringToDate("9999-12-31");
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
		try {
			System.out.println(formatoDelTexto.format(formatoDelTexto.parse("9999-12-31")));
			System.out.println(fec);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//System.out.println(formatoDelTexto.format(new Date()));
	}

}
