package com.movemini.simpleajaxservlet.interprete;

import java.util.ArrayList;

import javax.servlet.ServletRequest;

import com.movemini.data.DataArray;
import com.movemini.data.OneValueFactory;
import com.movemini.data.Record;
import com.movemini.simpleflexgrid.HTMLTable;
import com.movemini.simpleflexgrid.HTMLTableColumn;

public class InterpretesDisponiblesTable {



	private ServletRequest request;

	public InterpretesDisponiblesTable(ServletRequest request) {

		this.request = request;
	}


	public String getHtml() {

		String id_evento_version = request.getParameter("ID_EVENTO");
		System.out.print(id_evento_version);

		String selected_especialidades = request.getParameter("selected_especialidades");
		String selected_idiomas = request.getParameter("selected_idiomas");


		String id_tipo_evento = OneValueFactory.get("SELECT id_tipo_eveto FROM evento WHERE id_evento = (select id_evento from evento_version where id_evento_version = "+id_evento_version+")" );




		ArrayList<Record> records = null;

//		if(selected_especialidades == null || selected_especialidades.equals("")) {
//
//
//			records = DataArray.getArrayList("evento_interpretes_disponibles_select_pr", id_evento);
//
//		} else {
//



			String specsIds = selected_especialidades.replaceAll("especialidad_", "");
			String idiomasIds = selected_idiomas.replaceAll("idioma_", "");


			System.out.println(specsIds);
			System.out.println(idiomasIds);


			String cond1 = "1 = 1";
			String cond2 = "1 = 1";
			String cond3 = "";


			if(specsIds != null && !specsIds.equals("|")) {

				cond1 = "	id_interprete IN (SELECT id_interprete FROM cat_interprete_especialidad WHERE '|" + specsIds + "|' LIKE CONCAT('%|',id_especialidad,'|%')) ";
			}

			if(idiomasIds != null && !idiomasIds.equals("|")) {

				cond2 = "	id_interprete IN (SELECT id_interprete FROM cat_interprete_idioma WHERE '|" + idiomasIds + "|' LIKE CONCAT('%|',id_idioma,'|%')) ";
			}


			if(id_tipo_evento.equals("1")) {

				cond3 = " AND id_tipo_empleado IN (2,3)";
			}


			if(id_tipo_evento.equals("2")) {

				cond3 = " AND id_tipo_empleado IN (1,3)";
			}



			String sql = "SELECT  " +
							"	id_interprete, " +
							"	cat_jerarquia.jerarquia, " +
							"	CONCAT( " +
							"		IFNULL(nombre,''), ' ', " +
							"		IFNULL(apellido,'') " +
							"	) nombre_completo " +
							"FROM cat_interprete left join cat_jerarquia ON cat_jerarquia.id_jerarquia = cat_interprete.id_jerarquia " +
			                "WHERE " +
							cond1 +
			                "    AND " +
			                cond2 +
			                cond3 +
			                " AND id_status = 1 "+
			                " ORDER BY nombre"
			                ;


			//System.out.println(sql);

			records = DataArray.getArrayList(sql);





//		}


		StringBuilder sb = new StringBuilder();

		ArrayList<HTMLTableColumn> columns = new ArrayList<HTMLTableColumn>();


		columns.add(new HTMLTableColumn("Seleccionar", 					"id_interprete", 		"center", 	"link", 	"onclick='asignarInterprete(id_interprete)'", "<i class=\"fa fa-plus fa-2x\"></i>"));

		columns.add(new HTMLTableColumn("Jerarquia", 				"jerarquia", 		"left", 	"label", 	""));  //"onkeyup='updateSede(KEY, this)'"
		columns.add(new HTMLTableColumn("Interprete", 				"nombre_completo", 		"left", 	"label", 	""));  //"onkeyup='updateSede(KEY, this)'"





		String tableWidth = "100%";
		String additionalAttributes = "class=\"table table-bordered\" id=\"tablaInterpretesDisponibles\"";


		sb.append(HTMLTable.getHtml(records, columns, tableWidth, additionalAttributes));



		return sb.toString();
	}




}
