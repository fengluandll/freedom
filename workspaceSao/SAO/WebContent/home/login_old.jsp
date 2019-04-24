<%
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
	response.setHeader("Pragma","no-cache"); //HTTP 1.0
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<%@page import="com.movemini.layers.controller.login.LoginControllerBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %> 
<%@ page import="java.io.*" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>SAO </title>
	
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

  </head>
  <body class="login">



<table width="1024" align="center">
	<tr>
		<td colspan="3">&nbsp;
		</td>
	</tr>
	
	<tr>
		<td colspan="3">
			<img src="../img/Login_Header.jpg"> 
		</td>
	</tr>
	<tr>
		<td colspan="3">
		&nbsp;
		</td>
	</tr>
	<tr>
		<td colspan="3">
		&nbsp;
		</td>
	</tr>
	<tr>
		<td width="25%">
		</td>
		<td width="50%">
		
			<form method="post" action="login.jsp" name="login">
            	
            	<hr>
            
              <div>
                <input type="text" name="user" class="form-control" placeholder="Usuario" required="" />
              </div>
              <div>
                <input type="password" name="pass" class="form-control" placeholder="Contraseña" required="" />
              </div>
              <div>
              <br>
                <button type="submit" style="float: right;" class="btn btn-default submit" name="login">Ingresar</button>
                <!-- <a class="reset_pass" href="">Lost your password? </a>-->
              </div>
              
              <br>
              <br>
              
			  <hr>
            </form>
            
            
            
		
		</td>
		<td width="25%">

			
			<div style="margin-left: 20px">
			<%
				try{
					LoginControllerBean bean = new LoginControllerBean(request);
	
	
				    String user = request.getParameter("user");
				    String pass = request.getParameter("pass");
				    
				    
				    if(request.getParameter("login") != null){
				    	
				    	String result = bean.doLogin(user, pass);
				    	
				    	out.print(result);
				    }
				}catch(IllegalArgumentException iaex){
					
				%>
						<script>    			
				    		setTimeout(function() {
				    		    $('#mydiv').fadeOut('fast');
				    		}, 4000);
			    		
			    		</script> 
			    		<center>
			    		<!--  style="padding-left: 300px; padding-right: 500px;" -->
				    		<div>
					    		<div class="alert alert-info" id="mydiv" style="padding-left: 40px; padding-right: 40px;">		    		
							    	
							    </div>
				    		</div>
			    		</center>
				<%
					out.println(iaex.getMessage());
				
				}catch(Exception ex){
					System.out.println(ex);
					ex.printStackTrace();
				}
				
			%>
			</div>
		</td>
	</tr>
<!-- 	<tr> -->
<!-- 		<td colspan="3"> -->

<!-- 		</td> -->
<!-- 	</tr> -->
	<tr>
		<td colspan="3">
			<img src="../img/Login_Footer.jpg"> 
		</td>
	</tr>
</table>

  </body>
</html>
