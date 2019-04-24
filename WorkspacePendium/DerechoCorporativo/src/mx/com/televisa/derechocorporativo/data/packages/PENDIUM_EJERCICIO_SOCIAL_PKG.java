package mx.com.televisa.derechocorporativo.data.packages;

import mx.com.televisa.derechocorporativo.data.Procedure;

public interface PENDIUM_EJERCICIO_SOCIAL_PKG {
	Procedure CREATE_EJERCICIOS_PR 			= new Procedure("PENDIUM_EJERCICIO_SOCIAL_PKG.CREATE_EJERCICIOS_PR", 7);
	Procedure CREATE_EJERCICIOS_METAROW_PR 	= new Procedure("PENDIUM_EJERCICIO_SOCIAL_PKG.CREATE_EJERCICIOS_METAROW_PR", 8);
	Procedure FIND_EJERCIOS_TMP_PR 			= new Procedure("PENDIUM_EJERCICIO_SOCIAL_PKG.FIND_EJERCICIOS_TMP_PR",2);
	Procedure DELETE_EJERCICIO_PR 			= new Procedure("PENDIUM_EJERCICIO_SOCIAL_PKG.DELETE_EJERCICIO_PR",1);
	Procedure FIND_ONE_EJERCICIO 			= new Procedure("PENDIUM_EJERCICIO_SOCIAL_PKG.FIND_ONE_EJERCICIO",7);
	Procedure UPDATE_EJERCICIO 				= new Procedure("PENDIUM_EJERCICIO_SOCIAL_PKG.UPDATE_EJERCICIO",6);
	Procedure DELETE_ALL_TEMP 				= new Procedure("PENDIUM_EJERCICIO_SOCIAL_PKG.DELETE_ALL_TEMP",1);
	Procedure UPDATE_EJERCICIO_METAROW		= new Procedure("PENDIUM_EJERCICIO_SOCIAL_PKG.UPDATE_EJERCICIO_METAROW",2);
	Procedure FIND_EJERCICIOS_METAROW_PR	= new Procedure("PENDIUM_EJERCICIO_SOCIAL_PKG.FIND_EJERCICIOS_METAROW_PR",2);
}