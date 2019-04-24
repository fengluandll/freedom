package mx.com.televisa.derechocorporativo.data.packages;

import mx.com.televisa.derechocorporativo.data.Procedure;

public interface DERCORP_PANEL_CONTROL_PKG {

	Procedure INSERT_EMPRESA_PR = new Procedure("DERCORP_PANEL_CONTROL_PKG.INSERT_EMPRESA_PR", 6);
	
	Procedure UPDATE_EMPRESA_PR = new Procedure("DERCORP_PANEL_CONTROL_PKG.INSERT_EMPRESA_PR", 7);
	
	Procedure INSERT_ROL_PR = new Procedure("DERCORP_PANEL_CONTROL_PKG.INSERT_ROL_PR", 4);
	
	Procedure UPDATE_NEW_MENU_PR = new Procedure("DERCORP_PANEL_CONTROL_PKG.UPDATE_NEW_MENU_PR", 2);
	
	Procedure UPDATE_MENU_PR = new Procedure("DERCORP_PANEL_CONTROL_PKG.UPDATE_MENU_PR", 1);
	
	Procedure UPDATE_NEW_ROL_SECCION_PR = new Procedure("DERCORP_PANEL_CONTROL_PKG.UPDATE_NEW_ROL_SECCION_PR", 1);
	
	Procedure UPDATE_ROL_SECCION_PR = new Procedure("DERCORP_PANEL_CONTROL_PKG.UPDATE_ROL_SECCION_PR", 1);
}
