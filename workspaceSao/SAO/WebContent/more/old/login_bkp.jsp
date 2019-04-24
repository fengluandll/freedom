<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %> 
<%@ page import="java.io.*" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
try{
Class.forName("com.mysql.jdbc.Driver");
String url="jdbc:mysql://localhost:3306/prosante";
String username="root";
String password="120315";
Connection conn=DriverManager.getConnection(url,username,password);

%>

<html lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Acceso Prosant&eacute; </title>
	
    <!-- Bootstrap -->
    <link href="vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="vendors/nprogress/nprogress.css" rel="stylesheet">
    <!-- Animate.css -->
    <link href="vendors/animate.css/animate.min.css" rel="stylesheet">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <style type="text/css">
  body { 
    /*
    background-image: url("../img/login.jpg");
    background-repeat: no-repeat;
    background-attachment: fixed;
    background-position: center; 
    background-size: cover;
    */
}</style>
  </head>
  <body class="login">
  
<% 
//allow access only if session exists
if(session.getAttribute("ID_USER") == null){

%>

    <div>
      <a class="hiddenanchor" id="signup"></a>
      <a class="hiddenanchor" id="signin"></a>
      <div class="login_wrapper">
        <div class="animate form login_form" style="width: 600px; padding-top: 200px; padding-left: 300px;">
          <section class="login_content">
            <form method="post" action="login.jsp" name="login">
            <hr>
              <h1>Acceso Prosant&eacute;</h1>
              <div>
                <input type="text" name="user" class="form-control" placeholder="Usuario" required="" />
              </div>
              <div>
                <input type="password" name="pass" class="form-control" placeholder="Contraseña" required="" />
              </div>
              <div>
              <br>
                <button type="submit" style="float: right;" class="btn btn-default submit" name="login">Ingresar</a>
                <!-- <a class="reset_pass" href="">Lost your password? </a>-->
              </div>
              <br>
              <br>
			  <hr>
              <div class="clearfix"></div>

              <div class="separator">
                <p class="change_link"><!-- New to site?-->
                 <!-- <a href="#signup" class="to_register"> Create Account </a>-->
                </p>

                <div class="clearfix"></div>
                <br />
				<!--  
                <div>
                  <h1><i class="fa fa-paw"></i> Gentelella Alela!</h1>
                  <p>©2016 All Rights Reserved. Gentelella Alela! is a Bootstrap 3 template. Privacy and Terms</p>
                </div>
                -->
              </div>
            </form>
          </section>
        </div>
		<!-- Seccion Registro  -->
		<!--  
        <div id="register" class="animate form registration_form">
          <section class="login_content">
            <form>
              <h1>Create Account</h1>
              <div>
                <input type="text" class="form-control" placeholder="Username" required="" />
              </div>
              <div>
                <input type="email" class="form-control" placeholder="Email" required="" />
              </div>
              <div>
                <input type="password" class="form-control" placeholder="Password" required="" />
              </div>
              <div>
                <a class="btn btn-default submit" href="index.html">Submit</a>
              </div>

              <div class="clearfix"></div>

              <div class="separator">
                <p class="change_link">Already a member ?
                  <a href="#signin" class="to_register"> Log in </a>
                </p>

                <div class="clearfix"></div>
                <br />

                <div>
                  <h1><i class="fa fa-paw"></i> Gentelella Alela!</h1>
                  <p>©2016 All Rights Reserved. Gentelella Alela! is a Bootstrap 3 template. Privacy and Terms</p>
                </div>
              </div>
            </form>
          </section>
        </div>
        -->
        
      </div>
    </div>
  </body>
  <%} %>
</html>
<%  
    String user = request.getParameter("user");
    String pass = request.getParameter("pass");
    Boolean avanzar = false;
    
    if(request.getParameter("login") != null){
    	Statement stLogin = conn.createStatement();
    	ResultSet rsLogin = stLogin.executeQuery("SELECT * FROM user WHERE USERNAME = '"+user+"' AND PASSWORD = '"+pass+"'");
    	while(rsLogin.next()){
    		
   			session.setAttribute("ID_USER", 		 rsLogin.getString(1));
    		session.setAttribute("NOMBRE", 			 rsLogin.getString(2));
    		session.setAttribute("APELLIDO", 	     rsLogin.getString(3));
    		session.setAttribute("EDAD", 			 rsLogin.getString(4));
    		session.setAttribute("FECHA_NACIMIENTO", rsLogin.getString(5));
    		session.setAttribute("TITULO",           rsLogin.getString(6));
    		Statement stRole = conn.createStatement();
    		ResultSet rsRole = stRole.executeQuery("SELECT * FROM user_role WHERE ID_USER = "+rsLogin.getString(1)+"");
    		while(rsRole.next()){
    			session.setAttribute("ID_ROLE", rsRole.getInt(1));   
    			avanzar = true;
    		}    		
    	}
    	if(avanzar){
    		%><script>window.location.replace("index.jsp");</script><%
    	}
    	else {
    		%><script>    			
	    		setTimeout(function() {
	    		    $('#mydiv').fadeOut('fast');
	    		}, 4000);
    		
    		</script> 
    		<center>
	    		<div style="padding-left: 300px; padding-right: 500px;">
		    		<div class="alert alert-info" id="mydiv">		    		
				    	Tu <strong>USUARIO</strong> y/o <strong>CONTRASEÑA</strong> son incorrectos. <br>Por favor verifíca tus datos.
				    </div>
	    		</div>
    		</center>
    		<%
    	}
    }
}catch(Exception _e){
	System.out.println(_e);
	_e.printStackTrace();
}

%>
