<%
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
	response.setHeader("Pragma","no-cache"); //HTTP 1.0
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<%@page import="com.movemini.config.HardCodeConstants"%>
<%@page import="com.movemini.layers.controller.login.LoginControllerBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %> 
<%@ page import="java.io.*" %> 
<!DOCTYPE html>
	<html lang="en">
	<head>
		<meta charset="UTF-8">
		<title><%= HardCodeConstants.CONTEXT_PATH %></title>
		<meta name="viewport" content="width=device-width, initial-scale=1">

		<!-- google fonts -->

		<!-- Css link -->

<!-- 		<link rel="stylesheet" href="http://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css"> -->
<!-- 		<link rel="stylesheet" href="loginFiles/font-awesome.min.css"> -->
<!-- 		<link rel="stylesheet" href="loginFiles/owl.carousel.css"> -->
<!-- 		<link rel="stylesheet" href="loginFiles/owl.transitions.css"> -->
<!-- 		<link rel="stylesheet" href="loginFiles/animate.min.css"> -->
		<link rel="stylesheet" href="loginFiles/lightbox.css">
		<link rel="stylesheet" href="loginFiles/bootstrap.min.css">
<!-- 		<link rel="stylesheet" href="loginFiles/preloader.css"> -->
<!-- 		<link rel="stylesheet" href="loginFiles/image.css"> -->
<!-- 		<link rel="stylesheet" href="loginFiles/icon.css"> -->
		<link rel="stylesheet" href="loginFiles/style.css">
<!-- 		<link rel="stylesheet" href="loginFiles/responsive.css"> -->
		<link rel="stylesheet" href="vendors/sweet_alert/sweetalert.css">


	</head>
	<body id="top">


        <header id="navigation" class="navbar-fixed-top animated-header">
            <div class="container">
                <div class="navbar-header">
                    <!-- responsive nav button -->
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
                    </button>
					<!-- /responsive nav button -->

					<!-- logo -->
					<h1 class="navbar-brand">
						<a href="#body">
							<!--
							<img src="img/logo.png" height="40" width="164" alt="">
							-->

							<img src="../img/Logo-Omnilingua-Smaller.png" alt="">
							
							
		

						</a>
					</h1>
					<!-- /logo -->
                </div>

				<!-- main nav -->
                <nav class="collapse navbar-collapse navbar-right" role="navigation">
                    <ul id="nav" class="nav navbar-nav menu">
                                        
                        <li><a href="http://omnilingua.com.mx//">Omnilingua.com.mx</a></li>
                        
                        <li><a href="/<%= HardCodeConstants.CONTEXT_PATH %>/doc/Manual_de_Usuario_SAO.pdf" target="_blank">Manual de Usuario de SAO</a></li>
                    </ul>
                </nav>
				<!-- /main nav -->

            </div>
        </header>


	<div class="wrapper">





		<section id="banner">
			<div class="container">
				<div class="row">
<!-- 					 class="col-md-6" -->
					<div class="col-md-6">
						<div class="block">
							<br>
							<h1>
									<!--Clean and Flexible Business Template-->
											SAO

							</h1>
							<span class="smallerH1">Sistema Automatizado Omnilingua</span>
							<p>&nbsp;</p>
							<p>&nbsp;</p>
							
							<h2>
									<!--
									Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod <br> tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,-->

									Ingrese sus datos para acceder al sistema:

							</h2>
<!-- 							<BR> -->
<!-- 								<BR> -->
									
							<div class="buttons">

									<form method="post" action="login.jsp" name="login">

															<div>
																<input type="text" autofocus name="user" class="form-control" placeholder="Usuario" required="required" width="80%" tabindex="1" />
															</div>
															<BR>
															<div>

																	<input type="password" name="pass" class="form-control" placeholder="Contraseña" required="required" tabindex="2" />
															</div>
															<BR>
															<div align='right'>
<!-- 																<a href="#" class="btn btn-learn">Ingresar</a> -->
																
																<button type="submit" style="float: right;" class="btn btn-default submit" tabindex="3" name="login">Ingresar</button>
																
																
															</div>


									</form>
								<!--
								<a href="#" class="btn btn-learn">Purchase Now</a>

								<a href="#" class="btn btn-learn">View Featurese</a>
								-->
							</div>
						</div>
					</div>



<!-- 					</div> -->




				</div>
			</div>
			<div class="scrolldown">
				<!--
	            <a id="scroll" href="#features" class="scroll"></a>
	        -->
	        </div>
		</section>














		<footer>
			<div class="container">
				<div class="row">


					<div class="col-md-12">
						<div class="block">

							<!--<a href="#"><img src="img/logo.png" alt=""></a>-->

							<p>© Omnilingua. Todos los derechos reservados. 2017</p>
						</div>
					</div>
				</div>
			</div>
		</footer>


	</div>

		<!-- load Js -->
		<script src="loginFiles/jquery-1.11.3.min.js"></script>
		<script src="loginFiles/bootstrap.min.js"></script>
<!-- 		<script src="loginFiles/waypoints.min.js"></script> -->
<!-- 		<script src="loginFiles/lightbox.js"></script> -->
<!-- 		<script src="loginFiles/jquery.counterup.min.js"></script> -->
<!-- 		<script src="loginFiles/owl.carousel.min.js"></script> -->
<!-- 		<script src="loginFiles/html5lightbox.js"></script> -->
<!-- 		<script src="loginFiles/jquery.mixitup.js"></script> -->
<!-- 		<script src="loginFiles/wow.min.js"></script> -->
<!-- 		<script src="loginFiles/jquery.scrollUp.js"></script> -->
<!-- 		<script src="loginFiles/jquery.sticky.js"></script> -->
		<script src="loginFiles/jquery.nav.js"></script>
<!-- 		<script src="loginFiles/main.js"></script> -->
		<script src="vendors/sweet_alert/sweetalert.min.js"></script>
		



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
				    		
// 							setTimeout(function() {
// 				    		    $('#mydiv').fadeOut('fast');
// 				    		}, 4000);
			    		
				    		//alert("<%= iaex.getMessage() %>");
				    		swal({title:"",text: "<%= iaex.getMessage() %>",type: "info", html:true});
				    		
			    		</script>
			    		
			    		 
<!-- 			    		<center> -->
<!-- 			    		 style="padding-left: 300px; padding-right: 500px;" -->
<!-- 				    		<div> -->
<!-- 					    		<div class="alert alert-info" id="mydiv" style="padding-left: 40px; padding-right: 40px;">		    		 -->
							    	
<!-- 							    </div> -->
<!-- 				    		</div> -->
<!-- 			    		</center> -->
				<%
					//out.println();
				
				}catch(Exception ex){
					System.out.println(ex);
					ex.printStackTrace();
				}
				
			%>



	</body>
</html>