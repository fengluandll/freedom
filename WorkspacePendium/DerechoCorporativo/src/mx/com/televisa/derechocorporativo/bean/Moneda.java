/**
* Project: Derecho Corporativo
*
* File: Moneda.java
*
* Created on: Junio 13, 2016 at 12:00
*
* Copyright (c) - Televisa - 2015
*/
package mx.com.televisa.derechocorporativo.bean;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import mx.com.televisa.derechocorporativo.config.NavigationRules;
import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;
import mx.com.televisa.derechocorporativo.util.FacesUtils;

/**
 * Mapeo de Datos
 *
 * @author KAZ - CONSULTING/
 *         Iván Castañeda Loeza
 *         Jesús Argumedo
 *         Eduardo Nava
 *         Ernesto Corona Mendoza
 *
 *
 *
 * @version 1.0.0
 *
 * @date Junio 13, 2016 at 12:00 pm
 *
 */
public class Moneda {
/*
	public static String getMonedaValorNominal(String idEmpresa) {

		String idCampoMonedaValorNominal = "564";
		
		ConnectionWrapper conn = null;
		Statement stmt = null;
		ResultSet set = null;
		
		try {
			
			conn = new ConnectionWrapper();
		
			
			stmt = conn.createStatement();
			
			set = stmt.executeQuery("SELECT DERCORP_REPORTFLEX_PKG.GET_FIELD_TEXT_VALUE(" + idCampoMonedaValorNominal + ", " + idEmpresa + ") FROM DUAL");
			
			set.next();
			
			return set.getString(1);
			
		
		} catch(Exception ex) {
			
			ex.printStackTrace();
			
			return ex.toString();
			
		} finally {
			
			ConnectionWrapper.closeAll(conn);
		}
	}*/
}
