/**
 * @Jose de Jesus Argumedo Quiroz
 */
package mx.javaonline.admin.beans;

import java.io.Serializable;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import mx.javaonline.daos.ThemeSwitcherDAO;

@ManagedBean
@SessionScoped
public class GuestPreferences implements Serializable{

	//private String menuMode = "layout-menu-static";
	private String menuMode = "layout-menu-static-inactive";
	

    private String theme = "indigo";

    private String layout = "indigo";

    private boolean lightMenu = true;
                            
	public String getTheme() {		
		return theme;
	}
    
	public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getLayout() {		
		return layout;
	}
    
	public void setLayout(String layout) {
        this.layout = layout;
    }

    public String getMenuMode() {
        String mode = menuMode;
        if(this.isLightMenu()) {
            mode = mode + " layout-menu-light";
        }

		return "layout-wrapper layout-menu-static layout-menu-light layout-menu-static-inactive";//mode;
    }
    
    public void changeTheme(String value) {
        this.theme = value;

        if(value.indexOf("-") != -1) {
            this.layout = value.split("-")[0];
        }
        else {
            this.layout = value;
        }
    }

    public boolean isLightMenu() {
        return this.lightMenu;
    }

    public void setLightMenu(boolean lightMenu) {
        this.lightMenu = lightMenu;
    } 
    
	public void setMenuMode(String menuMode) {
		this.menuMode = menuMode;
	}
	
	
	

}
