package mx.startonline.operaciones;

/**
@autor Jesus Argumedo
 **/
public class DemoMetodos {
	
	public DemoMetodos(){
		//sumaNumeros();
		//sumaNumeros(10.0f,30.0f);
		//float result = divisionNumerosReturn();
		//System.out.println(result);
		//String leyenda = divisionNumerosReturn(34.0f,40.0f,"El resultado de la division es: ");
		//System.out.println(leyenda);
	}
	
	public void sumaNumeros(){
		float resultado;
		float a = 20.0f;
		float b = 10.0f;
		resultado = a + b;
		System.out.println(resultado);
	}
	
	public void sumaNumeros(float a,float b){
		float resultado;
		resultado = a + b;
		System.out.println(resultado);
	}
	
	public float divisionNumerosReturn(){
		float a = 100.5f;
		float b = 20.1f;
		float resultado;
		resultado = a / b;
		return resultado;
	}
	
	public String divisionNumerosReturn(float a,float b,String leyenda){
		float resultado;
		resultado = a / b;
		return leyenda + resultado;
	}
	


}
