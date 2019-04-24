package mx.com.televisa.derechocorporativo.util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;

public class ControlSession {
	
	
	public ControlSession(){
		
	}
	
	public static String tiempoInactividad(){
		ResultSet rs 	  		 = null;
		PreparedStatement psmt 	 = null;
		String strtimeOut		 = "5"; //Default 5 min para el mensaje de session
		ConnectionWrapper conWr  = null;
		int   			  tiempo = 0;
		
		String sql = "SELECT val_config\n"+
					 "	FROM app_config_tab\n"+
					 "	WHERE cod_config = 'LogStatCon'";
		try {
			conWr = new ConnectionWrapper();
			psmt = conWr.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()){
				strtimeOut = rs.getString(1);
			}
			tiempo = Integer.parseInt(strtimeOut) * 60000 / 1;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				psmt.close();
				conWr.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return String.valueOf(tiempo);
	}
	
	public static String tiempoInactividadMensaje(){
		ResultSet 		  rs 	  		= null;
		PreparedStatement psmt 	 		= null;
		String            strtimeOut	= "30";//Defaul son 30 min
		ConnectionWrapper conWr  		= null;
		int				  tiempo 		= 0;
		String sql = "SELECT val_config\n"+
					 "	FROM app_config_tab\n"+
					 "	WHERE cod_config = 'TMP_SESS'";
		try {
			conWr = new ConnectionWrapper();
			psmt = conWr.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()){
				strtimeOut = rs.getString(1);
			}
			tiempo = Integer.parseInt(strtimeOut) * 60000;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				psmt.close();
				conWr.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return String.valueOf(tiempo);
	}

}
