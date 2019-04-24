<%


String protocol = request.getScheme(); 			// http
String serverName = request.getServerName(); 	// hostname.com

int serverPort = request.getServerPort(); 		// 8081
String contextPath = request.getContextPath(); 	// mywebapp

String fullContextPath = protocol + "://" + serverName + ":" + serverPort + contextPath;



%>


<table border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td><img
			src="border01_4Print.png">
		</td>
		<td width="100%" class="pageTitle"
			background="border02_4Print.png">