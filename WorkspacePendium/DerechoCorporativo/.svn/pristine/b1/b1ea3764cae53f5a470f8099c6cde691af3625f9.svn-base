<%--

Incluir este archivo en las paginas necesarias en <head> con:
	%@include file="/js/util/clientInfo.jsp" %
En lugar de usar: 
	<script type="text/javascript" src=.....
	
Ver: /jsp/zTest/jsUtil/clientInfoTest.jsp
--%>


<SCRIPT Language="JavaScript">
	function Version_java(){return "1.0";}
</SCRIPT>
<SCRIPT Language="JavaScript1.1">
	function Version_java(){return "1.1";}
</SCRIPT>
<SCRIPT Language="JavaScript1.2">
	function Version_java(){return "1.2";}
</SCRIPT>
<SCRIPT Language="JavaScript1.3">
	function Version_java(){return "1.3";}
</SCRIPT>
<SCRIPT Language="JavaScript1.4">
	function Version_java(){return "1.4";}
</SCRIPT>
<SCRIPT Language="JavaScript1.5">
	function Version_java(){return "1.5";}
</SCRIPT>


<script type="text/javascript">

/**
 * Obtiene Informacion a cerca de el navegador y 
 * la regresa como un string dividido por pipes '|' con el formato: 
 *
 *	navegador|version|versionDeJavaScript|resolucion|sistemaOperativo|userAgent	
 */
function getClientInfo() {

	var browser = '';
	var version = '';
	var javaScriptVersion = Version_java();
	var screenResolution = screen.width + " x " + screen.height;
	var os = window.navigator.platform;  
	var userAgent = navigator.userAgent;
	
	if(userAgent.indexOf("MSIE") != -1) {
		var tempModUserAgent = userAgent.substr(userAgent.indexOf("MSIE"), 8);
		var arr = tempModUserAgent.split(" ");
		browser = arr[0];
		version = arr[1];
	} else if(userAgent.indexOf("Firefox") != -1) {
		var tempModUserAgent = userAgent.substr(userAgent.indexOf("Firefox"));
		var arr = tempModUserAgent.split("/");
		browser = arr[0];
		version = arr[1];
	} else if(userAgent.indexOf("Safari") != -1) {
		if(userAgent.indexOf("Version") != -1) {
			var tempModUserAgent = userAgent.substr(userAgent.indexOf("Version"));
			browser = tempModUserAgent.split(" ")[1].split("/")[0];
			version = tempModUserAgent.split(" ")[0].split("/")[1];
		} else {
			var tempModUserAgent = userAgent.substr(userAgent.indexOf("like Gecko")+12);
			browser = tempModUserAgent.split(" ")[0].split("/")[0];
			version = tempModUserAgent.split(" ")[0].split("/")[1];	
		}
	} else if(userAgent.indexOf("Opera") != -1) {
		var tempModUserAgent = userAgent.split("/");
		browser = tempModUserAgent[0];
		version = tempModUserAgent[tempModUserAgent.length - 1];
	} else {
		var browser = 'Unrecognized';
		var version = '0';
	}

	return 	browser + '|'+ 
			version + '|'+
			javaScriptVersion + '|'+ 
			screenResolution + '|'+
			os + '|'+
			userAgent;
}

</script>