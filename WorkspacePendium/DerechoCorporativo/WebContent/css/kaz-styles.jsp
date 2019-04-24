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

<%--@import 'https://fonts.googleapis.com/css?family=Raleway:500'; --%>

<%-- @import url('https://fonts.googleapis.com/css?family=Roboto+Slab:300,400'); --%>

@import url('//fonts.googleapis.com/css?family=Open+Sans');

.wrapForPre {
             white-space: pre-wrap;
             word-wrap: break-word;
             width: 100%;
        }
td.titleMenu{
	color:#FFFFFF;
	font-size: 13px;
	font-family: 'Open Sans', sans-serif;
}        
        
/*ECM 13 Septiembre 2016 ADM Y VIG - Referencia Nota de Pie*/
.refNotaPie{
    font-family:Verdana;
	font-size: 10px;
	font-weight: bold;
	color: red;
	margin-left: 80px;
}
.superIndice{
	color: red;
}

.infoTab {
	text-align: center;
	font-size: 13px;
}
.headTab {
	font-size: 14px;
}
.nvoCta {
	text-align: right;
	font-weight: bold;
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
	font-weight: bold;
	font-size: 13px;
}

.lightText{ 
		color : #D9D5D5; 
		}
body {
	/*background-image:
		url("<c:out value='${applicationBean.contextPath}'/>/img/page_background.jpg");
	background-attachment:fixed;*/
	/*margin-top: 0;
		margin-left: 0;
		margin-right: 0;
		margin-bottom: 0;*/
}

body,td,th,p {
	/*font-family:Verdana, Arial, Helvetica, sans-serif;*/
	font-size: 13px;
	font-family: 'Open Sans', sans-serif;
	font-weight: lighter;
}

input, button, select, textarea, optgroup, option {
    font-family: inherit;
    font-size: inherit;
    font-style: inherit;
    font-weight: inherit;
}

btnGuardar{
	font-family:Verdana, Arial, Helvetica, sans-serif;
	font-size: 12px;
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
	/*color: #2E4E67;*/
	color: #FFFFFF;
	font-family: 'Open Sans', sans-serif;
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
	text-align: center;
	font-weight: lighter;
}

.tableHeaderAlfa {
	/*background-color: #23008C;*/
	background-image:
		url("<c:out value='${applicationBean.contextPath}'/>/img/tableHeaderBack-alfa.png");
	color: white;
	/*font-weight: bold;*/
	text-align: center;
	/*color: #23008C;*/
/* 	font-weight: lighter; */
}

.tableHeaderAlfa2 {
	/*background-color: #23008C;*/
	background-image:
		url("<c:out value='${applicationBean.contextPath}'/>/img/tableHeaderBack-alfa.png");
	color: white;
	text-align: center;
	/*color: #23008C;*/
	color: white;
/* 	font-weight: lighter; */
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
	background:#DCE8F6;
	/*background:#FFFFFF;*/
}

.tableRowAdv{
	
	background:#DCE8F6;
	
}

/*ECM 20 AGOSTO 2015*/
.tableRow3{
	font-size: 14px;
	font-weight: bold;
	font-style: italic;
	color: white;
}
.tableRow4{
	font-size: 14px;
	font-weight: bold;
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
	font-weight: bold;
	font-size: 14px;
	background-image:
		url("<c:out value='${applicationBean.contextPath}'/>/img/borders/page_border/border02_azul.png");
}

a {
	text-decoration: none;
}
#linkSalir{
	color: #FFFFFF;
}

a:link,a:visited {
	color: #2B385D;
	/*color: #29385F;*/
    /*color: #23008C; */
	
}

a:hover {
	/*color: #E97C00;*/
/* 	color: #23008C; */
	color: #2B385D;	
	/*background-color: #23008C;*/
	background-color: #FFA033;
	
}

.headLineFactura {
	color: #FFFFFF
}

.fondoLineFactura {
	background-color: #0066FF
}


legend {

/* 	color:#000099; */
	color:#2B385D;
	font-size: 15px;
	font-weight: bolder;
}

.legendThin {

	color:#000099;
	font-size: 13px;
	font-weight: bold;
}



.blueBoldText {

	color:#000099;
	color:#2B385D;
		/*font-weight: bold;*/
	font-size: 13px;
}

/* 09-03-2017 ULR Para funcionamiento de la custom table para a�adir ejercicios*/

.divContainer{
	float: left;
}

/*para evitar que se empalmen los divs*/
.divContent2{
	clear: left;
}

.divCheckCustomTable{
	float: left;
	width: 25%;
	padding-top: 30px;
	padding-bottom: 30px;
}

.divContent{
	width: 80%;
	text-align: left;
	padding-left: 10%;
}

.divGroup{
    display: table;
    horizontal-align: middle;
    width: 100%;
    padding-top: 10px;
	padding-bottom: 10px;
}

.divGroup2{
    display: table;
    horizontal-align: middle;
    width: 100%;
    padding-top: 10px;
    padding-bottom: 10px;
}

div.divGroup > div + div {
	width: 50%;
	display: table-cell;
}

div.divGroup > div > label{
	width: 100%;
}

div.divGroup2 > div > label{
	width: 100%;
}

div.divGroup > div > input{
	width: 98%;
}

div.divGroup > div > input.year{
	width: 35%;
}

div.divGroup2 > div > input.year{
	width: 39%;
}

div.divGroup2 > div > input{
	width: 100%;
}

.labelCustomTable{
  display: block;
  padding-left: 15px;
  text-indent: -15px;
}
.inputCheckCustomTable{
  width: 13px;
  height: 13px;
  padding: 0;
  margin:0;
  vertical-align: bottom;
  position: relative;
  top: -1px;
  *overflow: hidden;
}

.hidden{
	display: none;
}

.headerTitleSeccion {
	height: 20px;
	background-color: #2b385d;
	color: white;
	text-align: center;
}

.divLabelDocumentum{
	width: 50%;
	display: table-cell;
}

.divInputDocumentum{
	width: 45%;
	display: table-cell;
	padding-left: 10px;
}

.divInputDate{
	width: 35%;
	display: table-cell;
}

.divHrefIcon{
	padding-left: 6px;
	width: 3%;
	display: table-cell;
}

.floatRight{
	float: right;
	margin-left:auto; 
	margin-right:0;
	display: table;
	padding-right:10%;
	padding-bottom:3%;
}

.buttons{
	display: table-cell;
	padding-left: 10px;
}

.light-color, #contentForms{
	background-color: #DCE8F6;
}
/*termina codigo a�adido por ULR*/
</style>
