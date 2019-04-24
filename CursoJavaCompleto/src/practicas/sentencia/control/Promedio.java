
package practicas.sentencia.control;


public class Promedio {

	public Promedio() {
		float eval1 = 7.0f;
		float eval2 = 6.0f;
		float promedio = (eval1 + eval2) / 2;
		System.out.println(promedio);
		if(promedio >= 7){
			System.out.println("APROBADO");
		}else{
			System.out.println("DESAPROBADO");
		}
		
		
		
	}
	
	public static void main(String[] args) {
		new Promedio();
	}

}
