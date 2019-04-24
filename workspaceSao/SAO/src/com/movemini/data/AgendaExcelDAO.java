package com.movemini.data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.movemini.layers.model.DatosCalendarioBean;
import com.movemini.layers.model.DiaMesAgendaBean;
import com.movemini.layers.model.MesAgendaBean;

public class AgendaExcelDAO {
	
	List<DiaMesAgendaBean> 			listDiaMes;
	List<MesAgendaBean> 			listMes;
	List<DatosCalendarioBean> 		listCal;
	DiaMesAgendaBean 				diaMesAgendaBean;
	MesAgendaBean					mesAgendaBean;
	DatosCalendarioBean				datosCalendarioBean;
	ResultSet 						rs;
	
	public List<DiaMesAgendaBean> getDayOfWeek(String anio,String fecInicio,String fecFin,int idCliente) {

		ConnectionWrapper conn = null;
		PreparedStatement stmt = null;
		listDiaMes = new ArrayList<DiaMesAgendaBean>();
		StringBuilder sb = new StringBuilder();
		
		sb.append("SELECT CASE 	WHEN dia_evento  = '01' THEN '1'  \n"+
					 "				WHEN dia_evento  = '02' THEN '2'  \n"+
					 "               WHEN dia_evento = '03' THEN '3' \n"+
					 "               WHEN dia_evento = '04' THEN '4' \n"+
					 "               WHEN dia_evento = '05' THEN '5' \n"+
					 "               WHEN dia_evento = '06' THEN '6' \n"+
					 "               WHEN dia_evento = '07' THEN '7' \n"+
					 "               WHEN dia_evento = '08' THEN '8' \n"+
					 "               WHEN dia_evento = '09' THEN '9' \n"+
					 "				ELSE							 \n"+
					 "						dia_evento				 \n"+
					 "				END AS dia_evento,				 \n"+
					 "		 CASE 	 WHEN mes_evento = '01' THEN '1' \n"+
					 "				 WHEN mes_evento = '02' THEN '2' \n"+
					 "               WHEN mes_evento = '03' THEN '3' \n"+
					 "               WHEN mes_evento = '04' THEN '4' \n"+
					 "               WHEN mes_evento = '05' THEN '5' \n"+
					 "               WHEN mes_evento = '06' THEN '6' \n"+
					 "               WHEN mes_evento = '07' THEN '7' \n"+
					 "               WHEN mes_evento = '08' THEN '8' \n"+
					 "               WHEN mes_evento = '09' THEN '9' \n"+
					 "		ELSE									 \n"+
					 "		mes_evento								 \n"+
					 "      END AS  mes_evento,  	 				 \n"+
					 "      id_evento_fecha,   					     \n"+
					 "	    id_evento_version,						 \n"+
					 "	    fecha,						 			 \n"+
					 "		id_evento_status,						 \n"+
					 "(SELECT nombre								 \n"+
					 "		FROM crm_cliente cc						  \n"+
					 "		WHERE cc.id_cliente = t.id_cliente)	AS CLIENTE, \n"+
					 "	prioridad									 \n"+
					 "	FROM(\n"+
					 "	select 	date_format(ef.fecha, '%d') AS dia_evento,	\n"+
					 "			date_format(ef.fecha, '%m') AS mes_evento,	\n"+
					 "			ef.fecha			  ,						\n"+
					 "		    ef.id_evento_fecha    ,						\n"+
					 "			ef.id_evento_version  ,					    \n"+
					 "			e.id_evento_status    ,                      \n"+
					 "			e.id_cliente,                                \n"+
					 "			(SELECT orden_prioridad                      \n"+
					 "			FROM cat_evento_status						 \n"+
					 "			WHERE id_evento_status = e.id_evento_status)AS prioridad \n"+
					 "	from evento_fecha	 ef								\n"+
					 "       ,evento_version ev								\n"+
					 "		 ,evento e										\n"+
					 "	WHERE 1=1 											\n"+
					 "  AND ef.id_evento_version = ev.id_evento_version 	\n"+
					 "	AND ev.id_evento = e.id_evento    					\n"+
					 "  AND   date_format(ef.fecha, '%Y') = ?              	\n");
		
					if(idCliente != 0){
						sb.append("AND e.id_cliente = "+idCliente+"\n");
					}
					sb.append("	)t\n");
					sb.append("	WHERE 1=1");
			
			if(!fecInicio.equals("") && !fecFin.equals("")){
				sb.append("	AND t.fecha between CAST(? AS DATE) AND CAST(? AS DATE) \n");
			}
			
			sb.append("	order by t.mes_evento,t.dia_evento,prioridad desc");
		
		try {
			
			conn = new ConnectionWrapper();
			
			stmt = conn.prepareStatement(sb.toString());
			stmt.setString(1, anio);
			if(!fecInicio.equals("") && !fecFin.equals("")){
				stmt.setString(2, fecInicio);
				stmt.setString(3, fecFin);
			}

			rs = stmt.executeQuery();
			
			while(rs.next()){
				diaMesAgendaBean = new DiaMesAgendaBean();
				diaMesAgendaBean.setDia(rs.getString(1));
				diaMesAgendaBean.setMes(rs.getString(2));
				diaMesAgendaBean.setIdEventoFecha(rs.getInt(3)); 
				diaMesAgendaBean.setIdEventoVersion(rs.getInt(4));
				diaMesAgendaBean.setFecha(rs.getString(5));
				diaMesAgendaBean.setIdEventoStatus(rs.getInt(6));
				diaMesAgendaBean.setClienteNom(rs.getString(7));
				listDiaMes.add(diaMesAgendaBean);
			} 
						
		}catch(Exception ex){
			ex.printStackTrace();
			
		} finally {
			
			ConnectionWrapper.closeAll(rs, stmt, conn);
		}
		
		return listDiaMes;
		
	}
	
	/**
	 * Obtiene los meses que existen de acuerdo al año intriducido
	 * @param anio
	 * @return
	 */
	public List<MesAgendaBean> getMonths(String anio){
		
		ConnectionWrapper conn = null;
		PreparedStatement stmt = null;
		listMes = new ArrayList<MesAgendaBean>();
		
		String sql = "SELECT CASE WHEN T.mes_evento = '01' THEN 'Enero' \n"+
			"WHEN T.mes_evento = '02' THEN 'Febrero'\n"+
            "WHEN T.mes_evento = '03' THEN 'Marzo'\n"+
            "WHEN T.mes_evento = '04' THEN 'Abril'\n"+
            "WHEN T.mes_evento = '05' THEN 'Mayo'\n"+
            "WHEN T.mes_evento = '06' THEN 'Junio'\n"+
            "WHEN T.mes_evento = '07' THEN 'Julio'\n"+
            "WHEN T.mes_evento = '08' THEN 'Agosto'\n"+
            "WHEN T.mes_evento = '09' THEN 'Septiembre'\n"+
            "WHEN T.mes_evento = '10' THEN 'Octubre'\n"+
            "WHEN T.mes_evento = '11' THEN 'Noviembre'\n"+
            "WHEN T.mes_evento = '12' THEN 'Diciembre'\n"+
            "ELSE\n"+
			"	'No coincide Mes'\n"+
			"END  AS mes,\n"+
			"T.mes_evento AS mes \n"+
			"FROM(\n"+
			"		select 	distinct date_format(fecha, '%m')AS mes_evento\n"+
			"		from evento_fecha\n"+
			"		WHERE date_format(fecha, '%Y') = ?\n"+
			"		) T order by t.mes_evento";
		
			try {
				conn = new ConnectionWrapper();
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, anio);
				rs = stmt.executeQuery();
				while(rs.next()){
					mesAgendaBean = new MesAgendaBean();
					mesAgendaBean.setMesLetra(rs.getString(1));
					mesAgendaBean.setMesNumero(rs.getString(2));
					listMes.add(mesAgendaBean);
					
				}
			}catch (Exception e) {
					e.printStackTrace();
			}finally {
					
					ConnectionWrapper.closeAll(rs, stmt, conn);
				}
			return listMes;
	}
	
	/**
	 * 
	 * @param anio
	 * @param fechaEvento fecha en formato yyyy-MM-dd
	 */
	public DatosCalendarioBean getCotCliente(String anio,String fechaEvento,int idEventoVersion){
		
		StringBuilder sb = new StringBuilder();
		String sql = "SELECT 	CONCAT(su.nombre,su.apellido) 	AS quien_lleva_evento,	\n"+
					 "			cc.id_cliente,										  	\n"+
					 "			cc.nombre 						AS nom_cliente, 		\n"+
					 "			e.id_evento,											\n"+
					 "			e.nombre 						AS nom_evento,			\n"+
					 "			e.clave_cotizacion,										\n"+
					 "			e.sede,													\n"+
					 "			ef.nfactura,											\n"+
					 "			ev.id_evento_version,									\n"+
					 "			efec.fecha,												\n"+
					 "			CONCAT(SUBSTRING(su.nombre,1,1),SUBSTRING(su.apellido,1,1))AS letraResponsable \n"+
					 "	from crm_cliente 		cc,										\n"+
					 "		  evento 			e,										\n"+
					 "	      evento_version 	ev										\n"+
					 "	      left join evento_factura ef on ev.id_evento_version = ef.id_evento_version,\n"+
					 "	      ss_usuario		su,  									\n"+
					 "	      evento_fecha		efec									\n"+
					 "	WHERE cc.id_cliente 		 		  = e.id_cliente			\n"+
					 "		AND su.id_usuario		 		  = e.id_usuario			\n"+
					 "		AND ev.id_evento 		 		  = e.id_evento				\n"+
					 "	    AND efec.id_evento_version		  = ev.id_evento_version	\n"+
					 "      AND ev.id_evento_version		  = ?		                \n"+
					 "	    AND date_format(efec.fecha, '%Y') = ?					\n"+
					 "		AND efec.fecha = CAST(? AS DATE)";
		
		sb.append(sql);
		
		ConnectionWrapper conn = null;
		PreparedStatement stmt = null;
		listCal = new ArrayList<DatosCalendarioBean>();
		
		try {
			conn = new ConnectionWrapper();
			stmt = conn.prepareStatement(sb.toString());
			stmt.setInt(1, idEventoVersion);
			stmt.setString(2, anio);
			stmt.setString(3, fechaEvento);
			rs = stmt.executeQuery();
			while(rs.next()){
				datosCalendarioBean = new DatosCalendarioBean();
				datosCalendarioBean.setLlevaEvento(rs.getString(1));
				datosCalendarioBean.setIdCliente(rs.getInt(2));
				datosCalendarioBean.setNomCliente(rs.getString(3));
				datosCalendarioBean.setIdEvento(rs.getInt(4));
				datosCalendarioBean.setNomEvento(rs.getString(5));
				datosCalendarioBean.setClaveCotizacion(rs.getString(6));
				datosCalendarioBean.setSede(rs.getString(7));
				datosCalendarioBean.setNumFactura(rs.getString(8));
				datosCalendarioBean.setIdEventoVersion(rs.getInt(9));
				datosCalendarioBean.setFecha(rs.getString(10));
				datosCalendarioBean.setAbreviaResponsable(rs.getString(11));
				//listCal.add(datosCalendarioBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionWrapper.closeAll(rs, stmt, conn);
		}
		return datosCalendarioBean;
	}
	
	/**
	 * 
	 * @param idEventoVersion
	 * @return
	 */
	public String getCantidadEquipos(int idEventoVersion){
		
		String sql = "SELECT distinct cp.nombre_tecnico						\n"+
					 "	FROM 	evento_sala 	es,							\n"+
					 "			evento_producto ep,							\n"+
					 "	        cat_producto	cp							\n"+
					 "	WHERE	es.id_evento_sala    = ep.id_evento_sala	\n"+
					 "		AND ep.id_producto	  	 = cp.id_producto		\n"+
					 "	    AND es.id_evento_version = ?					";
		
		ConnectionWrapper conn = null;
		PreparedStatement stmt = null;
		StringBuilder 	  sb   = null;
		try {
			conn = new ConnectionWrapper();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, idEventoVersion);
			rs = stmt.executeQuery();
			String sEquipos = "";
			sb = new StringBuilder();
			
			while(rs.next()){
				sb.append(rs.getString(1));
				sb.append(",");
			}
			if(sb.length() > 0){
				sb.delete(sb.length()-1, sb.length());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionWrapper.closeAll(rs, stmt, conn);
		}
		return sb.toString();
	}
	
	public String getInterprete(int idEventoVersion){
		
		String sql = "SELECT CONCAT(ci.nombre,' ',ci.apellido) AS nom_interprete \n"+
					 "	FROM 	evento_sala 		es,							\n"+
					 "			evento_interprete 	ei,							\n"+
					 "	        cat_interprete	    ci							\n"+
					 "	WHERE	es.id_evento_sala    = ei.id_evento_sala		\n"+	
					 "		AND ei.id_interprete  	 = ci.id_interprete			\n"+
					 "	    AND es.id_evento_version = ?";
		
		ConnectionWrapper conn = null;
		PreparedStatement stmt = null;
		StringBuilder 	  sb   = null;
		try {
			conn = new ConnectionWrapper();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, idEventoVersion);
			rs = stmt.executeQuery();
			
			sb = new StringBuilder();
			
			while(rs.next()){
				sb.append(rs.getString(1));
				sb.append(",");
			}
			if(sb.length() > 0){
				sb.delete(sb.length()-1, sb.length());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionWrapper.closeAll(rs, stmt, conn);
		}
		return sb.toString();
	}
	
public String getTecnico(int idEventoVersion){
		
		String sql = "SELECT CONCAT(ct.nombre,' ',ct.apellido) AS nom_interprete \n"+
					 "	FROM 	evento_sala 		es,								\n"+
					 "			evento_tecnico   	et,								\n"+
					 "	        cat_tecnicos	    ct								\n"+
					 "	WHERE	es.id_evento_sala    = et.id_evento_sala			\n"+
					 "		AND et.id_tecnico   	 = ct.id_tecnico				\n"+
					 "	    AND es.id_evento_version = ? ";
		
		ConnectionWrapper conn = null;
		PreparedStatement stmt = null;
		StringBuilder 	  sb   = null;
		try {
			conn = new ConnectionWrapper();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, idEventoVersion);
			rs = stmt.executeQuery();
			sb = new StringBuilder();
			
			while(rs.next()){
				sb.append(rs.getString(1));
				sb.append(",");
			}
			if(sb.length() > 0){
				sb.delete(sb.length()-1, sb.length());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionWrapper.closeAll(rs, stmt, conn);
		}
		return sb.toString();
	}

public String getHorario(int idEventoVersion){
	
	String sql = "SELECT 	GROUP_CONCAT(distinct										\n"+ 
				 "			CONCAT(TIME_FORMAT(hora_inicio, '%H:%i'),					\n"+
				 "									' ',								\n"+
				 "									DATE_FORMAT(fecha, '%d-%m-%Y'))) AS horario_instalacion \n"+
				 "	FROM evento_tecnico													\n"+
				 "	WHERE id_evento_sala IN (SELECT id_evento_sala						\n"+
				 "								FROM									\n"+
				 "									evento_sala							\n"+
				 "								WHERE									\n"+
				 "									id_evento_version = ?)";
		ConnectionWrapper conn = null;
		PreparedStatement stmt = null;
		String horario = "";
		
		try {
			conn = new ConnectionWrapper();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, idEventoVersion);
			rs = stmt.executeQuery();
			
			
			while(rs.next()){
				horario = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionWrapper.closeAll(rs, stmt, conn);
		}
		return horario;
	}

}
