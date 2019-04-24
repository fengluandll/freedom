package mx.javaonline.carousel.beans;

import java.util.Iterator;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.primefaces.context.ApplicationContext;
import org.primefaces.context.RequestContext;

public class OpenVideoBean {
	FacesContext facesContext = FacesContext.getCurrentInstance();
	
	private static org.apache.log4j.Logger logger = Logger.getLogger(OpenVideoBean.class);
	String linkTopic;
	

	public OpenVideoBean() {
		
	}
	public void openVideo(String linkTopic){
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
//		logger.debug("LINK DEL VIDEO "+linkTopic);
		session.removeAttribute("linkTopic");
		session.setAttribute("linkTopic", linkTopic);
		this.setLinkTopic(linkTopic);
	}
	public String getLinkTopic() {
//		logger.debug("******************* "+linkTopic);
		return linkTopic;
	}
	public void setLinkTopic(String linkTopic) {
		this.linkTopic = linkTopic;
	}
	
	

}
