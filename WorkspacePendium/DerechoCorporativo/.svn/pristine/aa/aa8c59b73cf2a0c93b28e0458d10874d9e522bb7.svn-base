package mx.com.televisa.derechocorporativo.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import mx.com.televisa.derechocorporativo.bean.SessionBean;
import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;

public class SalirDAO {
	
	
	 
	 public void salirControlSeccion(SessionBean sessionbean){
		 ConnectionWrapper 	puConnectionWrapper = null;
		 Connection 		puConnection 		= null;
		 PreparedStatement  psmt 				= null;
		 PreparedStatement  psmt2 				= null;
		 
		 String sql     = "UPDATE DERCORP_CONTROL_SECCION "+
						  "	SET status = 0 \n"+
						  "	WHERE id_user = ?";
		String sql2 	= "delete from pendium_ss_log_stat_conect_tab where id_user = ?";
		 
		 try {
			puConnectionWrapper = new ConnectionWrapper();
			puConnection = puConnectionWrapper.getConnection();
			psmt = puConnection.prepareStatement(sql);
			psmt2 = puConnection.prepareStatement(sql2);
			psmt.setInt(1, Integer.parseInt(sessionbean.getIdUser()));
			psmt2.setInt(1, Integer.parseInt(sessionbean.getIdUser()));
			psmt.executeUpdate();
			psmt2.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				psmt.close();
				puConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	 }

}
