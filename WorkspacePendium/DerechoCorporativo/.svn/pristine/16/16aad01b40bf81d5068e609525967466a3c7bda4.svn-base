<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style type="text/css">

.jqueryslidemenu{
font: 12px Verdana;
/*
font: bold 12px Verdana;
*/

/*background: #414141;*/
background-image:url(<c:out value='${applicationBean.contextPath}'/>/img/menu-back.png);
width: 100%;
}

.jqueryslidemenu ul{
margin: 0;
padding: 0;
list-style-type: none;
}

/*Top level list items*/
.jqueryslidemenu ul li{
position: relative;
display: inline;
float: left;
}

/*Top level menu link items style*/
.jqueryslidemenu ul li a{
display: block;
/*background: #414141; *//*background of tabs (default state)*/
background-image:url(<c:out value='${applicationBean.contextPath}'/>/img/menu-back.png);
color: white;
padding: 8px 10px;
border-right: 1px solid #778;

/*color: #2d2b2b;
*/
color: #000058;
text-decoration: none;
}

* html .jqueryslidemenu ul li a{ /*IE6 hack to get sub menu links to behave correctly*/
display: inline-block;
}

.jqueryslidemenu ul li a:link, .jqueryslidemenu ul li a:visited{
color: #000058;
}

.jqueryslidemenu ul li a:hover{
	
	background-image:url(<c:out value='${applicationBean.contextPath}'/>/img/menu-back-2.png);
	/*
background: black;
*/
 /*tab link background during hover state*/


/*
color: #5087FF;
*/

color: #ED7B18;
}
	
/*1st sub level menu*/
.jqueryslidemenu ul li ul{
position: absolute;
left: 0;
display: block;
visibility: hidden;
}

/*Sub level menu list items (undo style from Top level List Items)*/
.jqueryslidemenu ul li ul li{
display: list-item;
float: none;
}

/*All subsequent sub menu levels vertical offset after 1st level sub menu */
.jqueryslidemenu ul li ul li ul{
top: 0;
}

/* Sub level menu links style */
.jqueryslidemenu ul li ul li a{
font: normal 13px Verdana;
width: 160px; /*width of sub menus*/
padding: 5px;
margin: 0;
border-top-width: 0;
border-bottom: 1px solid gray;
}

.jqueryslidemenu ul li ul li a:hover{ /*sub menus hover style*/

background-image:url(<c:out value='${applicationBean.contextPath}'/>/img/menu-back-2.png);
/*
background: #eff9ff;
*/

/*
color: #5087FF;
*/
color: #ED7B18;



}

/* ######### CSS classes applied to down and right arrow images  ######### */

.downarrowclass{
position: absolute;
top: 12px;
right: 7px;
}

.rightarrowclass{
position: absolute;
top: 6px;
right: 5px;
}
</style>
