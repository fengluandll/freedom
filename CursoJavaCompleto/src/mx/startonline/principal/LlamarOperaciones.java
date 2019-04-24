package mx.startonline.principal;

import mx.startonline.operaciones.DemoMetodos;

/**
@autor Jesus Argumedo
 **/
public class LlamarOperaciones {
	
	DemoMetodos demoMetodos;//Declaración

	public LlamarOperaciones() {
		
		demoMetodos = new DemoMetodos();//Inicializacion o instancia
		//demoMetodos.sumaNumeros();
		//demoMetodos.sumaNumeros(30.5f, 10.3f);
		float numero = demoMetodos.divisionNumerosReturn();
		System.out.println(numero);
		String leyenda = demoMetodos.divisionNumerosReturn(5.5f, 10.5f, "El resultado es: ");
		System.out.println(leyenda);
	
	}


	public static void main(String[] args) {
		new LlamarOperaciones();

	}

}
