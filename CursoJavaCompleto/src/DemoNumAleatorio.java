import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;


public class DemoNumAleatorio {
	
	private void dameEdad(String fecNaci){
		
		int diagonal = fecNaci.lastIndexOf("/");
		String anioNac = fecNaci.substring(diagonal+1,fecNaci.length());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy"); 
		String anioActual = sdf.format(new Date());
		System.out.println(Integer.parseInt(anioActual) - Integer.parseInt(anioNac) );
		System.out.println(anioNac);
		
	}
	
	private void numAleatorio(){
		Random rnd = new Random();
		DecimalFormat df = new DecimalFormat("00000000000");
		//int valor =  Math.abs(rnd.nextInt());
		//String valor = String.format("%02d",1);
		String valor2 = df.format( Math.abs(rnd.nextInt()));
		String valor = String.valueOf(Math.abs(rnd.nextInt()));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
		String anio = sdf.format(new Date());
		
		String fecNaci = "20/08/1984";
		try {
			Date dfecNaci = sdf.parse(fecNaci);
			Date fecActual = new Date();
			System.out.println("dfecNaci "+sdf.format(dfecNaci));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(valor.substring(0,4));
		System.out.println(valor2);
		System.out.println(anio);
	}
	
	public static void main(String[] args) {
		new DemoNumAleatorio().dameEdad("20/08/1984");
	}

}
