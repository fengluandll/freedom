package mx.javaonline.util;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

public class FuncionesGrales {
	
//	public String timeOut = "7200000"; //Equvalente a 2 horas de inactividad
	public String timeOut = "200000";//dos minuto
	
	private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
 
    /**
     * Validate given email with regular expression.
     * 
     * @param email
     *            email for validation
     * @return true valid email, otherwise false
     */
    public static boolean validateEmail(String email) {
 
        // Compiles the given regular expression into a pattern.
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);
 
        // Match the given input against this pattern
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
 
    }
    
    /**
     * Metodo que regresa una contraseņa aleatoria de acuerdo al mail capturado
     * @param mail mail a procesar
     * @return regresa el nombre antes del arroba concatenado con un numero aleatorio
     */
    public static String generaContrasena(String mail){
    	Random rnd = new Random();
    	int arroba = mail.indexOf("@");
    	String pass = mail.substring(0,arroba)+"_"+String.valueOf(Math.abs(rnd.nextInt())).substring(0,4);
    	return pass;
    }
    
    /**
     * Metodo que convierte el mes con palabra a mes con numero
     * @param mes en letra con la primera en mayuscula por ejemplo Enero Febrero
     * @return regresa el numero de mes
     */
    public static String mesaNumero(String mes){
    		String mesNum = "";
			
			 if(mes.equals("Enero")){
				 mesNum = "01";
			 }else if(mes.equals("Febrero")){
				 mesNum = "02";
			 }else if(mes.equals("Marzo")){
				 mesNum = "03";
			 }else if(mes.equals("Abril")){
				 mesNum = "04";
			 }else if(mes.equals("Mayo")){
				 mesNum = "05";
			 }else if(mes.equals("Junio")){
				 mesNum = "06";
			 }else if(mes.equals("Julio")){
				 mesNum = "07";
			 }else if(mes.equals("Agosto")){
				 mesNum = "08";
			 }else if(mes.equals("Septiembre")){
				 mesNum = "09";
			 }else if(mes.equals("Octubre")){
				 mesNum = "10";
			 }else if(mes.equals("Noviembre")){
				 mesNum = "11";
			 }else if(mes.equals("Diciembre")){
				 mesNum = "12";
			 }
			 return mesNum;
    }
    
    /**
     * Metodo que convierte el mes con numero a mes en palabra
     * @param numero de mes por ejemplo 01 02 03 etc
     * @return regresa el mes en palabra
     */
    public static String numeroaMes(String numero){
		String mes = "";
		
		 if(numero.equals("01")){
			 mes  = "Enero";
		 }else if(numero.equals("02")){
			 mes  = "Febrero";
		 }else if(numero.equals("03")){
			 mes  = "Marzo";
		 }else if(numero.equals("04")){
			 mes  = "Abril";
		 }else if(numero.equals("05")){
			mes  = "Mayo";
		 }else if(numero.equals("06")){
			 mes  = "Junio";
		 }else if(numero.equals("07")){
			 mes  = "Julio";
		 }else if(numero.equals("08")){
			 mes  = "Agosto";
		 }else if(numero.equals("09")){
			 mes  = "Septiembre";
		 }else if(numero.equals("10")){
			 mes  = "Octubre";
		 }else if(numero.equals("11")){
			 mes  = "Noviembre";
		 }else if(numero.equals("12")){
			 mes  = "Diciembre";
		 }
		 return mes;
}

    /**
     * 
     * @param nomdialog nombre del dialogo
     * @param action 1 ocultar, 2 mostrar
     */
   public static void manageDialog(String nomdialog,int action){
		RequestContext context = RequestContext.getCurrentInstance();
		
		if(action == 1){
			context.execute("PF('"+nomdialog+"').hide();");
		}else if(action == 2){
			context.execute("PF('"+nomdialog+"').show();");
		}
		
   }
   
   /**
    * 
    * @param titulo
    * @param mensaje
    * @param tipoMensaje INFO,ERROR,FATAL,WARN
    */
   public static void mostrarMensaje(String titulo,String mensaje,String tipoMensaje){
	   
	   if(tipoMensaje.equals("INFO")){
		   FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,titulo,mensaje));
	   }else if(tipoMensaje.equals("ERROR")){
		   FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,titulo,mensaje));
	   }else if(tipoMensaje.equals("FATAL")){
		   FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_FATAL,titulo,mensaje));
	   }else if(tipoMensaje.equals("WARN")){
		   FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN,titulo,mensaje));
	   }
	   
	   
	   
	
   }
    
    
    
    /*
     * BEANS
     * */
	public String getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(String timeOut) {
		this.timeOut = timeOut;
	}
    
    
    
 

}
