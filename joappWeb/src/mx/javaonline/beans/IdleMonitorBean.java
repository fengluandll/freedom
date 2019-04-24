
package mx.javaonline.beans;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;


public class IdleMonitorBean {

	public IdleMonitorBean() {
		
	}
	
	 public void onIdle() {
		 LoginBean loginBean = new LoginBean();
		 loginBean.salir();
//		 RequestContext context = RequestContext.getCurrentInstance();
//		 context.execute("sessionTimeOutDialog.show();");
	    }
	 
	    public void onActive() {
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
	                                        "Welcome Back", "Well, that's a long coffee break!"));
	    }

}
