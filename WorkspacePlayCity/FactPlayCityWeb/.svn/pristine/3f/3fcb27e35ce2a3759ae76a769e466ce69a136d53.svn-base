/*
* Project: AISA
*
* File: MenuView.java
*
* Created on: Abril 5, 2019 at 11:00
*
* Copyright (c) - Kaz Consulting - 2019
*/

package mx.com.televisa.playcity.view;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import mx.com.televisa.playcity.beans.LoginBean;
import mx.com.televisa.playcity.beans.SessionMenuBean;
import mx.com.televisa.playcity.model.MenuDAO;
import mx.com.televisa.playcity.util.Functions;
import org.apache.log4j.Logger;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/*
* Controlador de la vista de menús y submenús  
*
* @author Kaz Consulting / Jesus Argumedo
*
* @version 1.0.0
*
* @date Abril 5, 2019 at 11:00 am
*/

@ManagedBean
@RequestScoped
public class MenuView {
	
	private static Logger poLogger = Logger.getLogger(MenuView.class);
	private MenuDAO       poMenuDAO;
	List<SessionMenuBean> paListMenu;
	List<SessionMenuBean> paListSubmenu;
	private String        psPageLink;
	private MenuModel     poModel;
	
	public void onNodeSelect(String tsPage) {		
		this.setPageLink(tsPage);
	}

	public String getPageLink() {
		if(psPageLink == null){
			this.psPageLink = "/FactPlayCityWeb/principal/default.xhtml";
		}
		return psPageLink;
	}

	public void setPageLink(String tsPageLink) {
		this.psPageLink = tsPageLink;
	}	
	
	@PostConstruct
	public void init() {
		
		LoginBean loginBean = (LoginBean) Functions.getSession().getAttribute("loginBean");
		paListMenu = new ArrayList<SessionMenuBean>();
		paListSubmenu = new ArrayList<SessionMenuBean>();
		
		poMenuDAO = new MenuDAO();
		poModel = new DefaultMenuModel();
        
        paListMenu = poMenuDAO.getMenus();
        DefaultMenuItem loItem = null;
        DefaultSubMenu loFirstSubmenu = null;
        /**/
        for(SessionMenuBean toMenu : paListMenu){
        	
        	//logger.info(" * "+m.getMenu());
        	loFirstSubmenu = new DefaultSubMenu(toMenu.getPsMenu().toString());
        	loFirstSubmenu.setExpanded(true);
            paListSubmenu = poMenuDAO.getSubmenus(toMenu.getPiIdMenu().toString());
            
            for (SessionMenuBean toSessionMenu : paListSubmenu){
            	//logger.info("     > "+sm.getSubmenu());
            	if(toMenu.getPiIdMenu() == toSessionMenu.getPiIdMenu()){
            		loItem = new DefaultMenuItem(toSessionMenu.getPsSubmenu());
                    //item.setUrl(sm.getSubmenu_url());
            		loItem.setIcon("ui-icon-extlink");
                    //this.setPageLink(sm.getSubmenu_url());
            		loItem.setCommand("#{menuView.setPageLink('"+toSessionMenu.getPsSubmenuUrl()+"')}");
            		loItem.setUpdate(":frameContent");
            		loFirstSubmenu.addElement(loItem);
            	}
            }
            poModel.addElement(loFirstSubmenu);
        }       
    }
	
	public MenuModel getModel() {
        return poModel;
    }   
}
