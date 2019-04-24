package mx.com.televisa.derechocorporativo.daos;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import mx.com.televisa.derechocorporativo.bean.GlosarioBean;
import mx.com.televisa.derechocorporativo.bean.SessionBean;
import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;

public class GlosarioDAO {
	ConnectionWrapper puConnectionWrapper;
	GlosarioBean	  glosarioBean;
	
	public boolean insertGlosarioPdf(String nomArchivo,InputStream archivo,SessionBean sessionBean){
		
		boolean paso = false;
		String sql = "INSERT INTO DERCORP_AYUDA_GLOSARIO_TAB(ID_AYUDA_GLOSARIO,\n"+
                     "                   NOM_ARCHIVO,\n"+
                     "                   COD_ARCHIVO_BLOB,\n"+
                     "                   NUM_CREATED_BY,\n"+
                     "                   FEC_CREATION_DATE)\n"+
                     " VALUES (1,?,?,?,SYSDATE)";
		String delete = "DELETE FROM DERCORP_AYUDA_GLOSARIO_TAB WHERE ID_AYUDA_GLOSARIO = 1";
		PreparedStatement psmt = null;
		PreparedStatement psmt2 = null;
		Connection 		  con   = null;
		try {
			puConnectionWrapper = new ConnectionWrapper();
			con = puConnectionWrapper.getConnection();
			psmt = con.prepareStatement(sql);
			psmt2 = con.prepareStatement(delete);
			psmt2.executeUpdate();
			
			psmt.setString(1, nomArchivo);
			psmt.setBinaryStream(2,archivo);
			psmt.setString(3, sessionBean.getIdUser());
			psmt.executeUpdate();
			paso = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				psmt.close();
				psmt2.close();
				puConnectionWrapper.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return paso;
	}
	
	public GlosarioBean dameArchivoGlosario(){
		String sql = "SELECT NOM_ARCHIVO,\n"+
					 "      COD_ARCHIVO_BLOB\n"+
					 " FROM DERCORP_AYUDA_GLOSARIO_TAB\n"+
					 " WHERE ID_AYUDA_GLOSARIO = 1";
		
		PreparedStatement psmt = null;
		Connection 		  con   = null;
		ResultSet 		  rs	= null;
		try {
			puConnectionWrapper = new ConnectionWrapper();
			con = puConnectionWrapper.getConnection();
			psmt = con.prepareStatement(sql);
			rs =  psmt.executeQuery();
			while(rs.next()){
				glosarioBean = new GlosarioBean(rs.getString(1),rs.getBinaryStream(2));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				psmt.close();
				con.close();
				puConnectionWrapper.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return glosarioBean;
	}

}
