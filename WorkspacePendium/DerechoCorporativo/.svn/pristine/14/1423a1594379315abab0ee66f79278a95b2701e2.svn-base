<%@page import="mx.com.televisa.derechocorporativo.modules.reports.tenecascada.TenenciaCascada"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String empresaId = request.getParameter("empresaId");
	

	String paramConsolida = 	(request.getParameter("paramConsolida") != null) ? request.getParameter("paramConsolida") : "";
	String paramSegmento = 		(request.getParameter("paramSegmento") != null) ? request.getParameter("paramSegmento") : "";
	String paramClasificacion = (request.getParameter("paramClasificacion") != null) ? request.getParameter("paramClasificacion") : "";
	String paramPais = 			(request.getParameter("paramPais") != null) ? request.getParameter("paramPais") : "";
	String paramNoEmpOracle = 	(request.getParameter("paramNoEmpOracle") != null) ? request.getParameter("paramNoEmpOracle") : "";
	String paramGiro = 			(request.getParameter("paramGiro") != null) ? request.getParameter("paramGiro") : "";
	String paramPorcentaje = 	(request.getParameter("paramPorcentaje") != null) ? request.getParameter("paramPorcentaje") : "";
	String selectPorcentaje = 	(request.getParameter("selectPorcentaje") != null) ? request.getParameter("selectPorcentaje") : "";
	String selectPorcentajeCual = 	(request.getParameter("selectPorcentajeCual") != null) ? request.getParameter("selectPorcentajeCual") : "";

	//String fecha = request.getParameter("fecha");
	String fecha = "";
	
	String data = TenenciaCascada.getTreeDataGrafico(empresaId, fecha,
						paramConsolida, 
						paramSegmento, 
						paramClasificacion, 
						paramPais, 
						paramNoEmpOracle, 
						paramGiro,
						paramPorcentaje,
						selectPorcentaje,
						selectPorcentajeCual);
%>
<%= data %>

<%-- 
{
 "name": "Grupo Televisa",
 "children": [
  {
   "name": "RADIO",
   "children": [
    {
     "name": "XEZZ",
     	"children": [
		      {"name": "Servicios Xezz"}
		     ]
    },
    {
     "name": "La Z",
     "children": [
      {"name": "Concursos"}
     ]
    }
   ]
  },
  {
   "name": "AISA",
   "children": [
    {"name": "Sorteos del Trebol"},
    {"name": "Multijuegos"}
   ]
  }
 ]
}
--%>



<%--
{
   "name": "CADENA RADIODIFUSORA MEXICANA, S.A. DE C.V.",
   "children": [
     { "name":"XEZZ (99.000000% * 99.000000%)"
        , "children": [

          { "name":"     SERVICIOS XEZZ (99.998000% * 98.998020%)"
          }
          ]
     },
     { "name":"RADIO MELODIA (99.000000% * 99.000000%)"
     },
     { "name":"RADIOTAPATIA (99.000158% * 99.000158%)"
     }
     ]
}
 --%>
 
 
<%-- 
 {
   "name": "SISTEMA RADIÓPOLIS, S. A. DE  C. V.",
   "children": [
     { "name":"CADENA RADIODIFUSORA (99.999858%)"
        , "children": [
          { "name":"     XEZZ (98.999859%)"
             , "children": [
               { "name":"          SERVICIOS XEZZ (98.999878%)"
               }
               ]     
          },    
          { "name":"     RADIO MELODIA (98.999859%)"
          },
          { "name":"     RADIOTAPATIA (99.000017%)"
          }
          ]     
     },    
     { "name":"RADIO COMERCIALES (99.973596%)"
        , "children": [
          { "name":"     SERVICIOS RADIOPOLIS (99.999999%)"
          },
          { "name":"     SERVICIOS XEZZ (98.999878%)"
          }
          ]     
     },    
     { "name":"RADIO TELEVISORA DE MEXICALI (99.999751%)"
     },
     { "name":"SERVICIOS RADIOPOLIS (99.999999%)"
     }     ]
}
--%>