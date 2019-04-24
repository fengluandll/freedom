/**
 * @autor startOnline
 */
package mx.javaonline.interfaces;


public interface IEnsambladoraAutomotriz {

	/*Definiendo constantes siempre deben empezan con public static final*/
	public static final int MOTOR = 1;
	int MOTORw = 1;
	
	/*Definiendo metodos*/
	public abstract void ensamblaMotor();
	abstract public void ensamblaChasis();
	public void ensamblaAsientos();
	
}
