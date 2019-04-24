<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Pendium</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<style type="text/css">
body {
	/*background-image:
		url("<c:out value='${applicationBean.contextPath}'/>/img/page_background.jpg");
	background-attachment:fixed;*/
	margin-top: 0;
		margin-left: 0;
		margin-right: 0;
		margin-bottom: 0;
}
</style>
</head>
<frameset rows="76,*,25" border="0" name="home">
	<frame
		src="<c:out value="${applicationBean.contextPath}"/>/faces/jsp/home/header.jsp"
		name="header" scrolling="no" noresize>
	<frameset cols="340,*" border="0" scrolling="auto" id="framesetMenuAndWindow">
		<frame
			src="<c:out value="${applicationBean.contextPath}"/>/faces/jsp/home/mainMenu.jsp"
			name="menu" noresize>
		<frame
			src="<c:out value="${applicationBean.contextPath}"/>/faces/jsp/home/content.jsp"
			name="contentFrame" id="contentFrame" noresize>
		<frame src="" frameborder="1" name="contentFrameClaim" noresize>
	</frameset>
	<!--<frame
		 src="<c:out value="${applicationBean.contextPath}"/>/faces/jsp/home/footer.jsp" 
		name="footer" scrolling="no" noresize> -->
</frameset>
</html>
