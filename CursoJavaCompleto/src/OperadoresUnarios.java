

public class OperadoresUnarios {

	public OperadoresUnarios() {
		// resultado es 1
        int resultado = +1;
        System.out.println(resultado);
        // resultado es 0
        resultado--;
        System.out.println(resultado);
        // resultado es 1 
        resultado++;
        System.out.println(resultado);
        // resultado es -1
        resultado = -resultado;
        System.out.println(resultado);
        boolean exito = false;
        // false
        System.out.println(exito);
        // true
        System.out.println(!exito);
	}
	
	public static void main(String[] args) {
		new OperadoresUnarios();
	}

}
