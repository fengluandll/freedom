

<%



	//Revisar conexion con la BD
	
	String user = request.getParameter("user");
	String password = request.getParameter("password");
	
	if(user != null && user.equals(password)) {
	
		response.sendRedirect(request.getContextPath() + "/jsp/home/index.jsp");
	}

%>


<form action="" method="post">

	<table>
		<tr>
			<td>Nombre de Usuario</td>
			<td><input name="user"></td>
		</tr>
		<tr>
			<td>Contraseña</td>
			<td><input name="password"></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit"></td>
		</tr>
	</table>
</form>