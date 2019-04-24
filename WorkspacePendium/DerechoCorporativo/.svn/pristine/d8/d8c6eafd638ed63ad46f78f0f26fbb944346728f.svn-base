package mx.com.televisa.derechocorporativo.util;

import java.util.List;

import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import mx.com.televisa.derechocorporativo.bean.ApplicationBean;
import mx.com.televisa.derechocorporativo.bean.SessionBean;

public class FacesUtils {

	@SuppressWarnings("deprecation")
	public static Object getBean(String beanName) {

		FacesContext ctx = FacesContext.getCurrentInstance();
		Application app = ctx.getApplication();
		return app.createValueBinding("#{" + beanName + "}").getValue(ctx);
	}

	public static SessionBean getSessionBean() {

		return (SessionBean) getBean("sessionBean");
	}

	public static ApplicationBean getApplicationBean() {

		return (ApplicationBean) getBean("applicationBean");
	}

	public static String getContextPath() {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		return request.getContextPath();
	}

	public static ServletRequest getRequest() {

		FacesContext context = FacesContext.getCurrentInstance();
		return (ServletRequest) context.getExternalContext().getRequest();
	}

	public static HttpSession getSession() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext()
				.getSession(true);
		return session;
	}

	public static String getContextUrl() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context
				.getExternalContext().getRequest();
		System.out.println("URL FACESUTILS "
				+ req.getRequestURL()
						.toString()
						.substring(
								0,
								StringUtils.ordinalIndexOf(req.getRequestURL()
										.toString(), "/", 4)));
		return req
				.getRequestURL()
				.toString()
				.substring(
						0,
						StringUtils.ordinalIndexOf(req.getRequestURL()
								.toString(), "/", 4));
	}

	public static String getClientId(String componentServerId) {

		FacesContext context = FacesContext.getCurrentInstance();
		String clientId = "";

		UIViewRoot root = context.getViewRoot();

		List<UIComponent> components = root.getChildren().get(0).getChildren();

		for (UIComponent comp : components) {

			if (componentServerId.equals(comp.getId())) {

				clientId = comp.getClientId();
			}
		}

		return clientId;
	}
}