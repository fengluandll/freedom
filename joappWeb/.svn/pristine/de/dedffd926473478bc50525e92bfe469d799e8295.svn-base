package mx.javaonline.util;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


import mx.javaonline.beans.CoursesBean;

@FacesConverter("controlerPicklist")
public class ControlerPicklist implements Converter {
	CoursesBean coursesBean;
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent component, String value) {
		coursesBean = new CoursesBean();
		return coursesBean;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		// TODO Auto-generated method stub
		return null;
	}
	

    
}
