import java.time.LocalDate;
import java.time.Month;



public class FechaUtil {

	
	public FechaUtil(){
		
		LocalDate dateEne = LocalDate.of(2018, 1, 1);
		LocalDate dateFeb = LocalDate.of(2018, Month.FEBRUARY, 1);
		LocalDate dateMay = LocalDate.of(2018, Month.MAY, 1);
		int numEnero = dateEne.lengthOfMonth();
		int numFeb = dateFeb.lengthOfMonth();
		dateFeb.getDayOfWeek();
		
		System.out.println(numEnero);
		System.out.println(numFeb);
		System.out.println(dateFeb.getDayOfWeek().name());
		System.out.println(dateMay.getDayOfWeek());
		System.out.println(dateMay.lengthOfMonth());
		System.out.println(dateMay.getMonth());
		
//		String[] meses = {"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto",
//						  "Septiembre","Octubre","Noviembre","Diciembre"};
		int[] meses = {1,2,3,4,5,6,7,8,9,10,11,12};
		for(int i = 1; i<meses.length+1; i++){
			LocalDate dias = LocalDate.of(2018, i, 1);
			
			
			for(int j = 1; j < dias.lengthOfMonth() +1 ; j++){
				LocalDate dateGral = LocalDate.of(2018, i, j);
				System.out.println(dateGral.getDayOfWeek() + " " + dateGral.getDayOfMonth() + " de "+ dateGral.getMonth());
			}
			
		}
		
		for(int i = 0; i<this.meses.length ; i++){
			System.out.println(this.meses[i]);
		}
}
	
	public  String meses[] = {
		"Enero", 
		"Febrero", 
		"Marzo", 
		"Abril", 
		"Mayo", 
		"Junio", 
		"Julio", 
		"Agosto", 
		"Septiembre", 
		"Octubre", 
		"Noviembre", 
		"Diciembre"
	};
	
	public static void main(String[] args) {
		new FechaUtil();
	}
}
