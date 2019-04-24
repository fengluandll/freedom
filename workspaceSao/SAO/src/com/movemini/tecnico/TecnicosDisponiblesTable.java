package com.movemini.tecnico;

import java.util.ArrayList;

import com.movemini.data.Record;
import com.movemini.simpleflexgrid.HTMLTable;
import com.movemini.simpleflexgrid.HTMLTableColumn;

public class TecnicosDisponiblesTable {

	
	public String getHtml(ArrayList<Record> records){
		
		StringBuilder sb = new StringBuilder();
		
		ArrayList<HTMLTableColumn> columns = new ArrayList<HTMLTableColumn>();

		columns.add(new HTMLTableColumn("Seleccionar", 					"id_tecnico", 		"center", 	"link", 	"onclick='asignarTecnico(id_tecnico)'", "<i class=\"fa fa-plus fa-2x\"></i>"));
		columns.add(new HTMLTableColumn("Interprete", 				"nombre_completo", 		"left", 	"label", 	""));  //"onkeyup='updateSede(KEY, this)'"
		
		String tableWidth = "100%";
		String additionalAttributes = "class=\"table table-bordered\"";
		
		sb.append(HTMLTable.getHtml(records, columns, tableWidth, additionalAttributes));
		
		
		
		return sb.toString();

	}
}
