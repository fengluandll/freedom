package com.movemini.layers.controller.usuario;

import java.sql.PreparedStatement;

import javax.servlet.http.HttpServletRequest;

import com.movemini.data.ConnectionWrapper;
import com.movemini.data.OneRecordFactory;
import com.movemini.data.Record;

public class UsuarioEditBean {

	private HttpServletRequest request;

	public UsuarioEditBean(HttpServletRequest request) {

		this.request = request;
	}

	public String updateUser() {

		ConnectionWrapper conn = null;

		PreparedStatement preparedStmtUser = null;
		
		try {
			
			conn = new ConnectionWrapper();
			

			String upDateUser = "UPDATE ss_usuario SET nombre = ?, apellido= ?, usuario =?, contrasenia =?, imagen=? WHERE id_usuario = ?";

			preparedStmtUser = conn.prepareStatement(upDateUser);
			preparedStmtUser.setString(1, request.getParameter("nombre").toString());
			preparedStmtUser.setString(2, request.getParameter("apellidos").toString());
			preparedStmtUser.setString(3, request.getParameter("username").toString());
			preparedStmtUser.setString(4, request.getParameter("userpass").toString());
			preparedStmtUser.setString(5, request.getParameter("perfil").toString());
			preparedStmtUser.setString(6, request.getParameter("id_usuario"));

			preparedStmtUser.executeUpdate();
			
			return "OK";

		} catch (Exception e) {
			// TODO: handle exception

			
			e.printStackTrace();
			
			return e.toString();
		} finally {
			 
			ConnectionWrapper.closeAll(preparedStmtUser, conn);
		}

	}

}
