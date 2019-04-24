<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Libros de Actas Asambleas y del Consejo</title>
<%@include file="/css/kaz-styles.jsp"%>

<script src="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.min.js"></script> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.css">
<%@include file="/jsp/components/session_timeout/session_timeout.jsp"%>

<script type="text/javascript">
function ingresarPeriodo(pObjeto){
	var vSeparador = '';
	var vPatron =  new Array(2,2);
	var vNumerico = true;

	if(pObjeto.valant != pObjeto.value){
		val = pObjeto.value
		largo = val.length
		val = val.split(vSeparador)
		val2 = ''

		for(r=0;r<val.length;r++){
			val2 += val[r]	
		}

		if(vNumerico){
			for(z=0;z<val2.length;z++){
				if(isNaN(val2.charAt(z))){
					letra = new RegExp(val2.charAt(z),"g")
					val2 = val2.replace(letra,"")
				}
			}
		}

		val = ''
		val3 = new Array()

		for(s=0; s<vPatron.length; s++){
			val3[s] = val2.substring(0,vPatron[s])
			val2 = val2.substr(vPatron[s])
		}

		for(q=0;q<val3.length; q++){
			if(q ==0){
				val = val3[q]
			}
			else{
				if(val3[q] != ""){
					val += vSeparador + val3[q]
					}
			}
		}

		pObjeto.value = val
		pObjeto.valant = val

		}
}
</script>

</head>
<body>
			<jsp:include page="/jsp/components/pages_border/open-pre-title.jsp"></jsp:include>
			
				Resumen de Aprobacion del Ejercicio Fiscal
			
			<jsp:include page="/jsp/components/pages_border/open-post-title.jsp"></jsp:include>
			<jsp:include page="/jsp/components/backPageReports/regresaMenuReportes.jsp">
				<jsp:param name="action" value="rPredefinidos"/>
			</jsp:include>
			<form id="fmResAprobEjerFis" name="fmResAprobEjerFis" method="post" action="/DerechoCorporativo/ResAprobEjerFisServlet">
        <table>
            <tr>
                <td>
					<fieldset>
						<legend>Ingresar periodo (AAAA):</legend>
						<input type="text" title="" name="txtPeriodo" id="txtPeriodo" size="4" value="" onkeyup="javascript:ingresarPeriodo(this);">
			        </fieldset>
                </td>
            </tr>
            <tr>
                <td>
					<fieldset>
						<legend>Exportar a:</legend>
							<div align="center">
								<table width="40%">
									<tr>
										<td>
											<select name='sPdfExcel' style='width: 180px;'>
													 <option value="PDF">PDF</option>
													 <option value="EXCEL">Excel</option>
											</select>
										</td>
										<td>
											<input type="submit" value="Generar">
										</td>
									</tr>
								</table>
							</div>
			        </fieldset>
                </td>
            </tr>
        </table>
        </form>
</body>
</html>