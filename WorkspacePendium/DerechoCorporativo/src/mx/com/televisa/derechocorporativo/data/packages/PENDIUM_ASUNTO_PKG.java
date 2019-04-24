package mx.com.televisa.derechocorporativo.data.packages;

import mx.com.televisa.derechocorporativo.data.Procedure;

public interface PENDIUM_ASUNTO_PKG {
	Procedure CREATE_ASUNTO_PR = new Procedure("PENDIUM_ASUNTO_PKG.CREATE_ASUNTO_PR", 4);
	Procedure CREATE_ASUNTO_METAROW_PR = new Procedure("PENDIUM_ASUNTO_PKG.CREATE_ASUNTO_METAROW_PR", 5);
	Procedure FIND_ASUNTO_TMP_PR = new Procedure("PENDIUM_ASUNTO_PKG.FIND_ASUNTO_TMP_PR", 2);
	Procedure FIND_ASUNTO_METAROW_PR = new Procedure("PENDIUM_ASUNTO_PKG.FIND_ASUNTO_METAROW_PR", 2);
	Procedure DELETE_ASUNTO_PR = new Procedure("PENDIUM_ASUNTO_PKG.DELETE_ASUNTO_PR", 1);
	Procedure FIND_ONE_ASUNTO = new Procedure("PENDIUM_ASUNTO_PKG.FIND_ONE_ASUNTO", 4);
	Procedure UPDATE_ASUNTO = new Procedure("PENDIUM_ASUNTO_PKG.UPDATE_ASUNTO", 4);
	Procedure UPDATE_ASUNTO_METAROW = new Procedure("PENDIUM_ASUNTO_PKG.UPDATE_ASUNTO_METAROW", 2);
	Procedure DELETE_ALL_TEMP = new Procedure("PENDIUM_ASUNTO_PKG.DELETE_ALL_TEMP", 1);
}
