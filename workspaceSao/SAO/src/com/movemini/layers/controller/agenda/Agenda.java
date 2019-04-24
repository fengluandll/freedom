package com.movemini.layers.controller.agenda;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.movemini.data.DataArray;
import com.movemini.data.OneValueFactory;
import com.movemini.data.Record;
import com.movemini.util.ColorUtil;
import com.movemini.util.DateUtils;
import com.movemini.util.StringUtils;
import com.movemini.debug.DebugTools;


public class Agenda {

	private static Map<String, String> dicColoresEvento = new HashMap<String, String>();
	private static Map<String, String> dicColoresLetraEvento = new HashMap<String, String>();
	private static Map<String, String> dicColoresEventoInterpretes = new HashMap<String, String>();
	private static Map<String, String> dicColoresEventoTecnicos = new HashMap<String, String>();
	private static Map<String, Integer> contador = new HashMap<String, Integer>();

	private static StringBuilder crearListaEventos(ArrayList<Record> eventos)
	{
		StringBuilder sb = new StringBuilder();
		ColorUtil colorUtil = new ColorUtil();
		String color = "";



		if(eventos != null){


						for (Record record : eventos) {
							if(!contador.containsKey(record.getId())){
								contador.put(record.getId(),1);
							}else{
								contador.put(record.getId(),contador.get(record.getId()) + 1);
							}
						}

		for (Record record : eventos) {

			String d = "";
			try {
				d = DateUtils.getDateHumanReadable(record.get("FECHA_FMT"));
			} catch (Exception e) {
				System.out.println("getDateHumanReadable NO VALIDO: " + record.get("FECHA_FMT"));
			}


			String cveEvento = record.get("clave_cotizacion");
			
			
			
			
			
//			if(cveEvento.contains("082")) {
//				
//				continue;
//			}
			
			

			if(!dicColoresEvento.containsKey(record.getId())){
					if(!record.get("id_evento_status").equals("5") ) {
						dicColoresEvento.put(record.getId(), "#FFFFFF");
						dicColoresLetraEvento.put(record.getId(),"#BBBBBB");
					}else{

							if( contador.get(record.getId())  == 1  ){
								dicColoresEvento.put(record.getId(), "#FFFFFF");
								dicColoresLetraEvento.put(record.getId(),"#000000");
							}else{
								dicColoresEvento.put(record.getId(), colorUtil.anyColor());
								dicColoresLetraEvento.put(record.getId(),"#111111");
							}
					}


			}



			// Learning Log SAO-1
			//String id_evento = OneValueFactory.get("SELECT id_ultima_version as id_evento FROM evento WHERE nombre = '" + record.get("nombre") + "'");
			
			
			String id_evento = OneValueFactory.get("SELECT id_ultima_version as id_evento FROM evento WHERE clave_cotizacion = '" + cveEvento + "'");


			sb.append("{"	+ "id: '" + id_evento + "',"+ "title: '" + record.get("clave_cotizacion") + " - " + record.get("nombre") + "', "
					+ "start: new Date('" + record.get("FECHA_FMT_START") + "'),"
							+ "fecha: '" + d + "',"
							+ "evento: '" + record.get("nombre") + "',"
							+ "cliente: '" + record.get("Cliente") + "',"
							+ "sala: '" + record.get("nombre_sala") + "',"
							//+ "interprete: '" + record.get("nombre_interprete") + "',"
							//+ "id_evento: '" + record.getId() + "',"
							+ "id_evento: '" + id_evento + "',"
							+ "id_tipo_eveto: '" + record.get("id_tipo_eveto") + "',"

							+ "id_estatus: '"+ record.get("id_evento_status") + "',"

							+ " backgroundColor: '"+ dicColoresEvento.get(record.getId()) + "',"
							+ " textColor: '" + dicColoresLetraEvento.get(record.getId()) + "',"
							+ " tipo: '1'"
							+ "},");
		}
		}
		else
			sb.append("{},");
		return sb;
	}

	private static StringBuilder crearListaEventosInterpretes(ArrayList<Record> eventos){

		StringBuilder sb = new StringBuilder();
		ColorUtil colorUtil = new ColorUtil();
		String color = "";


					for (Record record : eventos) {
						if(!contador.containsKey(record.getId())){
							contador.put(record.get("id_evento"),1);
						}else{
							contador.put(record.get("id_evento"),contador.get(record.get("id_evento")) + 1);
						}
					}


		for (Record record : eventos) {

			String d = DateUtils.getDateHumanReadable(record.get("FECHA_FMT"));


			if(!dicColoresEventoInterpretes.containsKey(record.get("id_evento"))){


						if(!record.get("id_evento_status").equals("5")  ){
							dicColoresEventoInterpretes.put(record.get("id_evento"), "#FFFFFF");
							dicColoresLetraEvento.put(record.get("id_evento"),"#909090");
						}else{
							if( contador.get(record.get("id_evento"))== 1  ){
								dicColoresEventoInterpretes.put(record.get("id_evento"), "#FFFFFF");
								dicColoresLetraEvento.put(record.get("id_evento"),"#111111");
							}else{
							dicColoresEventoInterpretes.put(record.get("id_evento"), colorUtil.anyColor());
							dicColoresLetraEvento.put(record.get("id_evento"),"#111111");
						}
				}
			}


			sb.append("{title: '" + record.get("nombre_interprete") + "', "
					+ "start: new Date('" + record.get("FECHA_FMT_START") + "'),"
							+ "fecha: '" + d + "',"
							+ "cliente: '" + record.get("Cliente") + "',"
							//+ "sala: '" + record.get("nombre_sala") + "',"
							+ "evento: '" + record.get("nombre") + "',"
							+ "interprete: '" + record.get("nombre_interprete") + "',"
							+ "id_evento: '" + record.getId() + "',"
							+ "id_tipo_eveto: '" + record.get("id_tipo_eveto") + "',"
							+ "id_estatus: '"+ record.get("id_evento_status") + "',"
							+ " backgroundColor: '"+ dicColoresEventoInterpretes.get(record.get("id_evento")) + "',"
							+ " textColor: '" + dicColoresLetraEvento.get(record.get("id_evento")) + "',"
							+ " tipo: '2'"
							+ "},");
		}

		return sb;
	}

	private static StringBuilder crearListaEventosTecnicos(ArrayList<Record> eventos){

		StringBuilder sb = new StringBuilder();
		ColorUtil colorUtil = new ColorUtil();
		String color = "";
		// record.get("id_tecnico")

		for (Record record : eventos) {
			if(!contador.containsKey(record.getId())){
				contador.put(record.get("id_evento"),1);
			}else{
				contador.put(record.get("id_evento"),contador.get(record.get("id_evento")) + 1);
			}
		}
		for (Record record : eventos) {

			String d = DateUtils.getDateHumanReadable(record.get("FECHA_FMT"));


			if(!dicColoresEvento.containsKey(record.get("id_evento"))){

					if(!record.get("id_evento_status").equals("5") ) {
						dicColoresEventoTecnicos.put(record.get("id_evento"), "#FFFFFF");
						dicColoresLetraEvento.put(record.get("id_evento"),"#909090");

					}else{
						if( contador.get(record.get("id_evento")) == 1 ){
							dicColoresEventoTecnicos.put(record.get("id_evento"), "#FFFFFF");
							dicColoresLetraEvento.put(record.get("id_evento"),"#111111");
						}else{
						dicColoresEventoTecnicos.put(record.get("id_evento"), colorUtil.anyColor());
						dicColoresLetraEvento.put(record.get("id_evento"),"#111111");
						}
				}

			}

			sb.append("{title: '" + record.get("nombre_tecnico") + "', "
					+ "start: new Date('" + record.get("FECHA_FMT_START") + "'),"
							+ "fecha: '" + d + "',"
							+ "cliente: '" + record.get("Cliente") + "',"
							+ "sala: '" + record.get("nombre_sala") + "',"
							+ "evento: '" + record.get("nombre") + "',"
							+ "id_evento: '" + record.getId() + "',"
							+ "id_tipo_eveto: '" + record.get("id_tipo_eveto") + "',"
							+ "id_estatus: '"+ record.get("id_evento_status") + "',"
							+ "interprete: '" + record.get("nombre_interprete") + "',"
							+ " backgroundColor: '"+ dicColoresEventoTecnicos.get(record.get("id_evento")) + "',"
											+ " textColor: '" + dicColoresLetraEvento.get(record.get("id_evento")) + "',"
							+ " tipo: '3'"
							+ "},");
		}

		return sb;
	}

	public static String getEvents() {
		ArrayList<Record> eventos = DataArray.getArrayList("agenda_select_pr", 0, "");

		StringBuilder sb = crearListaEventos(eventos);

		return StringUtils.removeLastChar(sb.toString());
	}

	public static String getEventsSplit(String idEstatus, String idCliente, String idEvento, String fecha) {

		//ArrayList<Record> eventos = DataArray.getArrayList("agenda_select_pr", idEstatus, fecha);

		String sql = "SELECT "
				+ "	E.id_ultima_version as id_evento, "
				+ "	E.nombre, E.clave_cotizacion,"
				+ "	C.nombre Cliente, "
				+ "	ES.nombre_sala,"
				+ "	ESD.fecha FECHA_FMT,"
				+ "	CONCAT(date_format(ESD.fecha, '%Y-%m-%d'),'T',trim(IFNULL(ESD.hora_inicio,'12:00')),':00') FECHA_FMT_START, "
				+ "	CONCAT(EI.nombre,' ', EI.apellido) nombre_interprete ,E.id_evento_status ,E.id_tipo_eveto"
				+ " FROM "
				+ "	evento E "
				+ "	inner join evento_version EV ON EV.id_evento = E.id_evento and status_version = 1 "
				+ "	inner join evento_sala ES ON ES.id_evento_version = EV.id_evento_version "
				+ "	left join evento_interprete ESD ON ESD.id_evento_sala = ES.id_evento_sala "
				+ "	left join cat_interprete EI ON EI.id_interprete = ESD.id_interprete "
				+ "	left  join crm_cliente C ON C.id_cliente = E.id_cliente "
				+ " WHERE  id_tipo_eveto = 1 and ";

				if(!idEstatus.equals("0")) {
					sql +=  "	E.id_evento_status = " + idEstatus;
				}else{
					sql +=  " 1=1 ";
				}

				if(!idCliente.equals("0")){
					sql += "	and C.id_cliente = " + idCliente;
				}else{
					sql +=  " and  2=2 ";
				}

				if(!idEvento.equals("0")){
					sql += "	and E.id_evento = " + idEvento;
				}else{
					sql +=  " and  3=3 ";
				}

				if(!fecha.equals("")){
					sql += "	and year(esd.fecha) = " + fecha;
				}else{
					sql +=  " and  4=4 ";
				}


				sql +=  "	AND ESD.fecha IS NOT NULL "
				+ "	group by E.nombre, ESD.fecha";

		System.out.println(sql);
		ArrayList<Record> eventos = DataArray.getArrayList(sql);

		DebugTools.println(sql, Agenda.class);

		StringBuilder sb = crearListaEventos(eventos);

		return ("[" + StringUtils.removeLastChar(sb.toString()) + "]");
	}
	
	public static String getEventsSplit2(String idEstatus, String idCliente, String idEvento, String fecha) {

		//ArrayList<Record> eventos = DataArray.getArrayList("agenda_select_pr", idEstatus, fecha);
		String sql0 =" SELECT "
				+ " id_evento,"
				+ "nombre,"
				+ "clave_cotizacion,"
				+ "Cliente,"
				+ "nombre_sala,"
				+ "FECHA_FMT,"
				+ "FECHA_FMT_START,"
				+ "nombre_interprete,"
				+ "id_evento_status, "
				+ "id_tipo_eveto FROM ";
				
		
		String sql ="SELECT "
				+ "E.id_ultima_version as id_evento,"
				+ "E.nombre,"
				+ "E.clave_cotizacion,"
				+ "C.nombre Cliente,"
				+ "'' nombre_sala,"
				+ "EF.fecha FECHA_FMT,"
				+ "concat(Date_format(EF.fecha, '%Y-%m-%d'), 'T','00:00',':00') FECHA_FMT_START,"
				+ "'' nombre_interprete,"
				+ "E.id_evento_status, E.id_tipo_eveto FROM evento E"
				+ " inner join evento_version EV on EV.id_evento = E.id_evento"
				+ " inner join evento_fecha EF on EF.id_evento_version = EV.id_evento_version"
				+ " left join crm_cliente C on  C.id_cliente = E.id_cliente where"
				;

		String sql2 = "SELECT "
				+ "	E.id_ultima_version as id_evento, "
				+ "	E.nombre, E.clave_cotizacion,"
				+ "	C.nombre Cliente, "
				+ "	ES.nombre_sala,"
				+ "	ESD.fecha FECHA_FMT,"
				+ "	CONCAT(date_format(ESD.fecha, '%Y-%m-%d'),'T',trim(IFNULL(ESD.hora_inicio,'12:00')),':00') FECHA_FMT_START, "
				+ "	CONCAT(EI.nombre,' ', EI.apellido) nombre_interprete ,"
				+ "E.id_evento_status ,E.id_tipo_eveto"
				+ " FROM "
				+ "	evento E "
				+ "	inner join evento_version EV ON EV.id_evento = E.id_evento and status_version = 1 "
				+ "	inner join evento_sala ES ON ES.id_evento_version = EV.id_evento_version "
				+ "	left join evento_interprete ESD ON ESD.id_evento_sala = ES.id_evento_sala "
				+ "	left join cat_interprete EI ON EI.id_interprete = ESD.id_interprete "
				+ "	left  join crm_cliente C ON C.id_cliente = E.id_cliente "
				+ " WHERE  ";

				if(!idEstatus.equals("0")) {
					sql +=  "	E.id_evento_status = " + idEstatus;
					sql2 +=  "	E.id_evento_status = " + idEstatus;
				}else{
					sql +=  " 1=1 ";
					sql2 +=  " 1=1 ";
				}

				if(!idCliente.equals("0")){
					sql += "	and C.id_cliente = " + idCliente;
					sql2 += "	and C.id_cliente = " + idCliente;
				}else{
					sql +=  " and  2=2 ";
					sql2 +=  " and  2=2 ";
				}

				if(!idEvento.equals("0")){
					sql += "	and E.id_evento = " + idEvento;
					sql2 += "	and E.id_evento = " + idEvento;
				}else{
					sql +=  " and  3=3 ";
					sql2 +=  " and  3=3 ";
				}

				if(!fecha.equals("")){
					sql += "	and year(EF.fecha) = " + fecha;
					sql2 += "	and year(ESD.fecha) = " + fecha;
				}else{
					sql +=  " and  4=4 ";
					sql2 +=  " and  4=4 ";
				}


				sql +=  "	AND EF.fecha IS NOT NULL ";
				//+ "	group by E.nombre, EF.fecha";
				sql2 +=  "	AND ESD.fecha IS NOT NULL ";
						//+ "	group by E.nombre, ESD.fecha";

		sql0 += "(" + sql + " union " + sql2 + ") T group by nombre, FECHA_FMT";
				
		System.out.println(sql0);
		ArrayList<Record> eventos = DataArray.getArrayList(sql0);

		DebugTools.println(sql, Agenda.class);

		StringBuilder sb = crearListaEventos(eventos);

		return ("[" + StringUtils.removeLastChar(sb.toString()) + "]");
	}

	public static String getAgendaEventoPorInterprete()
	{
		ArrayList<Record> eventos = DataArray.getArrayList("agenda_select_interprete_pr", 0, "");




		//ArrayList<Record> eventos = DataArray.getArrayList(sql);

		StringBuilder sb = crearListaEventosInterpretes(eventos);

		return StringUtils.removeLastChar(sb.toString());
	}

	public static String getAgendaEventoPorInterpreteSplit(String idEstatus, String idCliente, String idEvento, String fecha)
	{
		//ArrayList<Record> eventos = DataArray.getArrayList("agenda_select_interprete_pr", idEstatus, fecha);
		String sql = " SELECT"
				+ " 	E.id_ultima_version as id_evento,"
				+ " 	E.nombre,"
				+ " 	C.nombre Cliente,"
				+ " 	ES.nombre_sala,"
				+ " 	ESD.fecha FECHA_FMT,"
				+ " 	CONCAT(date_format(ESD.fecha, '%Y-%m-%d'),'T',IFNULL(ESD.hora_inicio,'12:00'),':00') FECHA_FMT_START,"
				+ " 	CONCAT(EI.nombre,' ',"
				+ " 	EI.apellido) nombre_interprete, EI.id_interprete,E.id_evento_status,E.id_tipo_eveto"
				+ " FROM "
				+ " 	evento E"
				+ " 	inner join evento_version EV ON EV.id_evento = E.id_evento and status_version = 1 "
				+ " 	inner join evento_sala ES ON ES.id_evento_version = EV.id_evento_version "
				+ " 	inner join evento_interprete ESD ON ESD.id_evento_sala = ES.id_evento_sala "
				+ " 	inner join cat_interprete EI ON EI.id_interprete = ESD.id_interprete "
				+ " 	left  join crm_cliente C ON C.id_cliente = E.id_cliente "
				+ " WHERE id_tipo_eveto = 1 and";

				if(!idEstatus.equals("0")) {
					sql +=  "	E.id_evento_status = " + idEstatus;
				}else{
					sql +=  " 1=1 ";
				}

				if(!idCliente.equals("0")){
					sql += "	and C.id_cliente = " + idCliente;
				}else{
					sql +=  " and  2=2 ";
				}

				if(!idEvento.equals("0")){
					sql += "	and E.id_evento = " + idEvento;
				}else{
					sql +=  " and  3=3 ";
				}

				if(!fecha.equals("")){
					sql += "	and year(esd.fecha) = " + fecha;
				}else{
					sql +=  " and  4=4 ";
				}

				sql +=  "	AND ESD.fecha IS NOT NULL";

		ArrayList<Record> eventos = DataArray.getArrayList(sql);

		StringBuilder sb = crearListaEventosInterpretes(eventos);


		return ("[" + StringUtils.removeLastChar(sb.toString()) + "]");
	}

	public static String getAgendaEventoPorTecnico()
	{
		ArrayList<Record> eventos = DataArray.getArrayList("agenda_select_tecnico_pr", 0, "");


		StringBuilder sb = crearListaEventosTecnicos(eventos);

		return ("[" + StringUtils.removeLastChar(sb.toString()) + "]");
	}

	public static String getAgendaEventoPorTecnico(String idEstatus, String idCliente, String idEvento, String fecha)
	{
		//ArrayList<Record> eventos = DataArray.getArrayList("agenda_select_tecnico_pr", idEstatus, fecha);

		String sql =  " SELECT "
				+ " 	E.id_ultima_version as id_evento, "
				+ " 	E.nombre, "
				+ " 	C.nombre Cliente, "
				+ " 	ES.nombre_sala, "
				+ " 	ESD.fecha FECHA_FMT, "
				+ " 	CONCAT(date_format(ESD.fecha, '%Y-%m-%d'),'T',IFNULL(ESD.hora_inicio,'12:00'),':00') FECHA_FMT_START, "
				+ " 	CONCAT(EI.nombre,' ', "
				+ " 	EI.apellido) nombre_tecnico, "
				+ " 	EI.id_tecnico ,E.id_evento_status,E.id_tipo_eveto"
				+ " FROM  "
				+ " 	evento E "
				+ " 	inner join evento_version EV ON EV.id_evento = E.id_evento and status_version = 1 "
				+ " 	inner join evento_sala ES ON ES.id_evento_version = EV.id_evento_version "
				+ " 	inner join evento_tecnico ESD ON ESD.id_evento_sala = ES.id_evento_sala "
				+ " 	inner join cat_tecnicos EI ON EI.id_tecnico = ESD.id_tecnico "
				+ " 	left  join crm_cliente C ON C.id_cliente = E.id_cliente "
				+ " WHERE id_tipo_eveto = 1 and ";

		if(!idEstatus.equals("0")) {
			sql +=  "	E.id_evento_status = " + idEstatus;
		}else{
			sql +=  " 1=1 ";
		}

		if(!idCliente.equals("0")){
			sql += "	and C.id_cliente = " + idCliente;
		}else{
		}

		if(!idEvento.equals("0")){
			sql += "	and E.id_evento = " + idEvento;
		}else{
			sql +=  " and  3=3 ";
		}

		sql +=  " and  2=2 ";
		if(!fecha.equals("")){
			sql += "	and year(esd.fecha) = " + fecha;
		}else{
			sql +=  " and  4=4 ";
		}

		sql +=  "	AND ESD.fecha IS NOT NULL";

		ArrayList<Record> eventos = DataArray.getArrayList(sql);

		StringBuilder sb = crearListaEventosTecnicos(eventos);


		return ("[" + StringUtils.removeLastChar(sb.toString()) + "]");
}



}
