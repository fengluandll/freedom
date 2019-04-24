package mx.gob.tsjdf.view;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import mx.gob.tsjdf.bean.LoginBean;
import mx.gob.tsjdf.bean.SessionMenuBean;
import mx.gob.tsjdf.model.MenuDAO;
import mx.gob.tsjdf.util.Funciones;

@ManagedBean
@RequestScoped
public class MenuView {
	
	private static org.apache.log4j.Logger logger = Logger.getLogger(MenuView.class);
	private MenuDAO menuDAO;
	List<SessionMenuBean> listMenu;
	List<SessionMenuBean> listSubmenu;
	
	private String pageLink;
	
	public void onNodeSelect(String age) {
		
		 this.setPageLink(age);
	    }

	public String getPageLink() {
		if(pageLink == null){
			this.pageLink = "/TSJDF_HE_Sistema/principal/default.xhtml";
		}
		return pageLink;
	}

	public void setPageLink(String pageLink) {
		this.pageLink = pageLink;
	}
	
	private MenuModel model;
	
	@PostConstruct
	public void init() {
		
		LoginBean loginBean = (LoginBean) Funciones.getSession().getAttribute("loginBean");
		listMenu = new ArrayList<SessionMenuBean>();
		listSubmenu = new ArrayList<SessionMenuBean>();
		
		menuDAO = new MenuDAO();
        model = new DefaultMenuModel();
        
        listMenu = menuDAO.getMenus(loginBean.getIdRol());
        DefaultMenuItem item = null;
        DefaultSubMenu firstSubmenu = null;
        /**/
        for(SessionMenuBean m : listMenu){
        	
        	//logger.info(" * "+m.getMenu());
    		firstSubmenu = new DefaultSubMenu(m.getMenu().toString());
            firstSubmenu.setExpanded(true);
            listSubmenu = menuDAO.getSubmenus(m.getId_rol().toString(),m.getId_menu().toString());
            
            for (SessionMenuBean sm : listSubmenu){
            	//logger.info("     > "+sm.getSubmenu());
            	if(m.getId_menu() == sm.getId_menu()){
            		item = new DefaultMenuItem(sm.getSubmenu());
                    //item.setUrl(sm.getSubmenu_url());
                    item.setIcon("ui-icon-extlink");
                    //this.setPageLink(sm.getSubmenu_url());
                    item.setCommand("#{menuView.setPageLink('"+sm.getSubmenu_url()+"')}");
                    item.setUpdate(":frameContent");
                    firstSubmenu.addElement(item);
            	}
            }
            model.addElement(firstSubmenu);
        }
        
    }
	public MenuModel getModel() {
        return model;
    }   
}
