
public class DemoMetodos {

	/**
	 * Metodo constructor
	 */
	public DemoMetodos() {
		sumaNumeros();
		sumaNumeros(15,23.5f,"El resultado de la suma es: ");
		float div = divisionNumerosReturn();
		String divRetro = divisionNumerosReturn(34.43f,1,"El resultado de la division es: ");
		System.out.println(div);
		System.out.println(divRetro);
	}
	
	
	/**
	 * Metodo normal sin retroalimentacion
	 */
	public void sumaNumeros(){
		float a = 10.0f;
		float b = 20.0f;
		float resultado;
		resultado = a + b;
		System.out.println(resultado);
	}
	
	/**
	 * Metodo que recibe 3 parametros
	 * @param a primer valor a sumar
	 * @param b segundo valor a sumar
	 * @param leyenda cualquier cadena de caracteres a mostrar
	 */
	public void sumaNumeros(float a,float b,String leyenda){
		float resultado;
		resultado = a + b;
		System.out.println(leyenda + resultado);
	}
	
	/**
	 * Metodo retroalimentativo que divide dos numeros y regresa su resultado
	 * @return regresa el resultado de la division
	 */
	public float divisionNumerosReturn(){
		float a = 100.5f;
		float b = 20.1f;
		float resultado;
		resultado = a / b;
		return resultado;
	}
	
	/**
	 * Metodo que recibe parametros y que divide dos numeros 
	 * @param a primer valor a dividir
	 * @param b segundo valor a dividir
	 * @param leyenda cualquier cadena de caracteres a mostrar
	 * @return regresa el la leyenda concatenada con el resultado de la division
	 */
	public String divisionNumerosReturn(float a,float b,String leyenda){
		float resultado;
		resultado = a / b;
		return leyenda + resultado;
	}
	
	public static void main(String[] args) {
		new DemoMetodos();
	}

}
