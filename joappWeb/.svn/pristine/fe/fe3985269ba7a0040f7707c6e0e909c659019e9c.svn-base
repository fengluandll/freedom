package mx.javaonline.util;

import java.util.Iterator;
import java.util.Map;

import javax.faces.component.UIComponent;

import org.primefaces.context.RequestContext;

public class ComponentTreeBean {

	private Map<String, UIComponent> componentMap;
	
	public ComponentTreeBean() {
	}

	public Map<String, UIComponent> getComponentMap() {
		
		Iterator i = componentMap.entrySet().iterator();
		
		while(i.hasNext()){
			String clave = (String)i.next();
			System.out.println(componentMap.get(clave));
		}
		return componentMap;
	}

	public void findComponente(){
		System.out.println("Entro!!");
		RequestContext context = RequestContext.getCurrentInstance();
		Map<Object,Object> mapComp = context.getAttributes();
		Iterator i = mapComp.keySet().iterator();
		String clave;
		while(i.hasNext()){
			 clave = (String)i.next();
			 System.out.println("MAPA "+mapComp.get(clave));
		}
		
		  //context.update(":mainForm");
	}
	

}
