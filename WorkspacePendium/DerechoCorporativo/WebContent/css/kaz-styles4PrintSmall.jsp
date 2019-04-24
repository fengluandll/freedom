<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style type="text/css">

/*Argumedo*/
@import url('//fonts.googleapis.com/css?family=Open+Sans');
.wrapForPre {
             white-space: pre-wrap;
             word-wrap: break-word;
             width: 100%;
        }
td.titleMenu{
	color:#FFFFFF;
	font-size: 12px;
	font-family: 'Open Sans', sans-serif;
}        
        
/*ECM 13 Septiembre 2016 ADM Y VIG - Referencia Nota de Pie*/
.refNotaPie{
    font-family:Verdana;
	font-size: 10px;
	/*font-weight: bold;*/
	color: red;
	margin-left: 80px;
}
.superIndice{
	color: red;
}

.infoTab {
	text-align: center;
	font-size: 12px;
}
.headTab {
	font-size: 14px;
}
.nvoCta {
	text-align: right;
	/*font-weight: bold;*/
	font-style: italic;
	color: #06F;
}
.centerNew {
	text-align: center;
}
a:hover {
	background-color: #ACBCED;
}
a:hover.encima{
	background-color: #7D7FD2;
}

/****************************************/

.letraNegrita{
	/*font-weight: bold;*/
	font-size: 12px;
}

.lightText{ 
		color : #D9D5D5; 
		}
body {
/*
	background-image:
		url("<c:out value='${applicationBean.contextPath}'/>/img/page_background.jpg");
	background-attachment:fixed;*/
	/*margin-top: 0;
		margin-left: 0;
		margin-right: 0;
		margin-bottom: 0;*/
}

body,td,p {
	/*font-family:Verdana, Arial, Helvetica, sans-serif;*/
	font-size: 13px;
	font-family: 'Open Sans', sans-serif;
}

th {
	font-family:Verdana, Arial, Helvetica, sans-serif;
	font-size: 8px;
}

input, button, select, textarea, optgroup, option {
    font-family: inherit;
    font-size: inherit;
    font-style: inherit;
    font-weight: inherit;
}

btnGuardar{
	font-family:Verdana, Arial, Helvetica, sans-serif;
	font-size: 11px;
}


big {

	color: #444444;
}


.infoMessageText {
	color: #66AA66;
    font-weight: bold;
}

.errorMessageText {
	color: #AA6666;
    font-weight: bold;
}

.coloredText {
	color: #2E4E67;
}

.loginMessages{
	color: #FF0000;
}

.smallText {
	font-size: 10px;
}

.tableHeader {
	/*background-color: #23008C;*/
	background-image:
		url("<c:out value='${applicationBean.contextPath}'/>/img/tableHeaderBack.png");
	color: white;
}

.tableHeaderAlfa {
	/*background-color: #23008C;*/
	background-image:
		url("<c:out value='${applicationBean.contextPath}'/>/img/tableHeaderBack-alfa.png");
	color: white;
	/*font-weight: bold;*/
	text-align: center;
	/*color: #23008C;*/
}

.tableHeaderAlfa2 {
	/*background-color: #23008C;*/
	background-image:
		url("<c:out value='${applicationBean.contextPath}'/>/img/tableHeaderBack-alfa.png");
	color: white;
	/*font-weight: bold;*/
	text-align: center;
	/*color: #23008C;*/
	color: white;
}



.headerMenu {
	background-image:
		url("<c:out value='${applicationBean.contextPath}'/>/img/headerTab.png");
}

.flexTable {
	
	border-color: #F5F5F5;
	border-width: thin;
}

.tableRow2{
	/*background:#C2E3EA;*/
	/*background:#DCE8F6;
	background: #F6F9FD;*/
	background: #F6F9FD;
}

/*ECM 20 AGOSTO 2015*/
.tableRow3{
	font-size: 14px;
	/*font-weight: bold;*/
	font-style: italic;
	color: white;
}
.tableRow4{
	font-size: 10px;
	/*font-weight: bold;*/
}

.pageTitle {
	/*color: #2B4963;*/
	color: white;
	text-align: center;
	/*font-weight: bold;*/
	font-size: 14px;
}

.pageTitle2 {
	
	color: white;
	text-align: center;
	/*font-weight: bold;*/
	font-size: 14px;
	background-image:
		url("<c:out value='${applicationBean.contextPath}'/>/img/borders/page_border/border02_azul.png");
}

a {
	text-decoration: none;
}

a:link,a:visited {
	color: #23008C;
}

a:hover {
	color: #E97C00;
	background-color: #23008C;
}

.headLineFactura {
	color: #FFFFFF
}

.fondoLineFactura {
	background-color: #0066FF
}


legend {

	color:#000099;
	font-size: 15px;
	/*font-weight: bolder;*/
}

.legendThin {

	color:#000099;
	font-size: 12px;
	/*font-weight: bold;*/
}


.blueBoldText {

	color:#000099;
	/*font-weight: bold;*/
	font-size: 11px;
}






</style>