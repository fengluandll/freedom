<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="mx.com.televisa.derechocorporativo.daos.MngDataPoderes"%>
<%@page import="mx.com.televisa.derechocorporativo.bean.GenericDataBean"%>
<%@page import="mx.com.televisa.derechocorporativo.bean.SessionBean"%>
<%@page import="mx.com.televisa.derechocorporativo.util.FacesUtils"%>

<%@page import="com.google.gson.Gson"%>
<%@page import="com.google.gson.GsonBuilder"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="mx.com.televisa.derechocorporativo.daos.Abc_AppConfigTabDAO"%>
<%	
	
	String nonCache = false ? "" : "?lastversion=" + new java.util.Random().nextInt(); 
	List<Object> initArgs = new ArrayList();	
	List<Object> metaSession = new ArrayList();
	Gson gson = new Gson();
	
	String idSeccion = request.getParameter("idSeccion");	
	SessionBean sessionBean = (SessionBean)session.getAttribute("sessionBean");	 	
	metaSession.add(sessionBean);	
	metaSession.add(request.getContextPath());
	String idEscritura = request.getParameter("idEscritura")!=null?request.getParameter("idEscritura").toString() : "";
	String tipoEscritura = request.getParameter("tipoEscritura")!=null?request.getParameter("tipoEscritura").toString() : "";
	metaSession.add(idEscritura);
	session.setAttribute("idEscritura", idEscritura);
	metaSession.add(tipoEscritura);
	session.setAttribute("tipoEscritura", tipoEscritura);
    
	String jsonMS=gson.toJson(metaSession);
	
	//******** Carga del catálogo "Delegado Por" con id:49 :0
		initArgs.add( MngDataPoderes.queryCATALOGOS(49));
	
	//******** Carga del catálogo "Notario" con id:12 :1
		initArgs.add( MngDataPoderes.queryCATALOGOS(12));
	
	//****** Carga del catálogo "Notario" con id:13 :2
		initArgs.add( MngDataPoderes.queryCATALOGOS(13));
	
	
	//******** Carga del catálogo "Texto Poderes" :3
		initArgs.add( MngDataPoderes.queryCatalogosDePoderes("PG"));
	
	//******** Carga registros de Escritura Poder Tipo Poder General "PG" :4
		GenericDataBean listEP2 = MngDataPoderes.queryESCRITURA_PODER(Integer.parseInt(sessionBean.getIdCurrentEmpresa()), "PG");
		initArgs.add(listEP2);
	
	//******** Carga registros de Revocaciones "ER" :5
		GenericDataBean listER = MngDataPoderes.queryESCRITURA_PODER(Integer.parseInt(sessionBean.getIdCurrentEmpresa()), "ER" );
		initArgs.add(listER);
		
		GenericDataBean listEP = MngDataPoderes.queryESCRITURA_PODER(Integer.parseInt(sessionBean.getIdCurrentEmpresa()), "PG");
		
		listEP.beans = new ArrayList<Object[]>();
		for(int i =0; i<listEP2.beans.size();i++){
			Object idEsc =  listEP2.get(i).getProperty("des_escritura");
			if(idEsc != null)
				listEP.beans.add(listEP2.beans.get(i));
		}
		for(int i =0; i<listER.beans.size();i++){
			Object idEsc =  listER.get(i).getProperty("des_escritura");
			if(idEsc != null)
				listEP.beans.add(listER.beans.get(i));
		}
		
	
	//******** Carga del catálogo "Grupo de Apoderados" con id:45 :6
		initArgs.add( MngDataPoderes.queryCATALOGOS(45));
	
		//****** Carga del catálogo "Notario" con id:13 :7
				initArgs.add( MngDataPoderes.queryCATALOGOS(56));
				//****** Carga del catálogo "Notario" con id:13 :7
				initArgs.add( MngDataPoderes.queryCATALOGOS(59));
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta content="text/html" http-equiv="content-type" charset="utf-8"></meta>
    <title></title>
    <link href="../../../css/webCore.css<%=nonCache %>" rel="stylesheet" type="text/css" />
    <link href="../../../css/jquery-ui-css/pendium/jquery-ui.css" rel="stylesheet" type="text/css" />
    <script src="../../../js/jquery/jquery-2.1.4.min.js" type="text/javascript"></script>
    
    <script src="../../../css/jquery-ui-css/pendium/jquery-ui.js" type="text/javascript"></script>
    
        
    <link href="../../../css/jquery-ui-css/blue-moon/GridSort.css" rel="stylesheet" type="text/css" />
    <script src="../../../js/jquery/globalize.js" type="text/javascript"></script>
    <script src="../../../js/jquery/plugins/jGridContentMVC-1.0.2.js<%=nonCache %>" type="text/javascript"></script>
    <script src="../../../js/jquery/plugins/jquery.tablesorter.js<%=nonCache %>" type="text/javascript"></script>
    <script src="../../../js/jquery/plugins/jquery.json-2.2.min.js<%=nonCache %>" type="text/javascript"></script>
    <script src="../../../js/jquery/plugins/urlEncode.js<%=nonCache %>" type="text/javascript"></script>
    
    <script src="../../../js/jsCal/jquery.mask.js<%=nonCache %>" type="text/javascript"></script>
    
    <script src="../../../js/jquery/WebKernel/Util.js<%=nonCache %>" type="text/javascript"></script>
    <script src="../../../js/jquery/WebKernel/webKernel.js<%=nonCache %>" type="text/javascript"></script>
    <script src="../../../js/jquery/WebKernel/RequestModel.js<%=nonCache %>" type="text/javascript"></script>
    
    <link href="indexPoderes/indexPoderes.css<%=nonCache %>" rel="stylesheet" type="text/css" />
    <script src="indexPoderes/RequestModel.js<%=nonCache %>" type="text/javascript"></script>
    <script src="indexPoderes/indexPoderes.js<%=nonCache %>" type="text/javascript"></script>
    
    <link href="indexPoderes/AsistenteDeCopiado/estilos.css<%=nonCache %>" rel="stylesheet" type="text/css" />
    <script src="indexPoderes/AsistenteDeCopiado/controlador.js<%=nonCache %>" type="text/javascript"></script>
    
    <script src="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.min.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.css">	
	<%@include file="../../components/session_timeout/session_timeout.jsp"%>
	
	<script src="../../../js/CKEditor/ckeditor.js" type="text/javascript"></script>
    
</head>
<body id="body" style="display:none">
<input id= "btnSalir"  type="button" name="cerrar" value="Salir" onClick="go('<%=FacesUtils.getContextPath()%>/faces/jsp/captura/capWait.jsp?idEmp=<%=request.getParameter("idEmp")%>&salida=true&idSeccion=<%=request.getParameter("idSeccion") %>');" style="margin-bottom:10px;margin-left:80%" >
    <div id="tabsPoderes">    
	<ul>
		<li><a href="#tabsPoderes-1" workDiv="divPGMain">Generales</a></li>
		<li><a href="#tabsPoderes-2" workDiv="divPEMain">Especiales</a></li>
		<li><a href="#tabsPoderes-3" workDiv="divCPMain">Carta Poder</a></li>
		<li><a href="#tabsPoderes-4" workDiv="divERMain">Revocaciones</a></li>			
	</ul>
	<div id="tabsPoderes-1">	
		<div id="divPGMain">
        <table width="100%">        
            <tr>        
                <td>        
                    <input type="text" id="txtPGQuery"></input>
                    <button type="button" openFormType="PG" id="btnPGQuery" class="ui-button ui-corner-all ui-widget">Buscar</button>
                    
                </td>
                <td>
                    <div openFormMode="new" openFormType="PG" divHide="#divPGMain" title="Crear Poder General" class="btnPGAdd Img_Icon_new"></div>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <div id="gridPGmain">
                    	<%=gson.toJson(initArgs.get(4)) %>
                    </div>
                </td>
            </tr>
        </table>  
       </div>   
       
    </div>
	<div id="tabsPoderes-2">
        
    </div>
	<div id="tabsPoderes-3">
	
	</div>
	<div id="tabsPoderes-4">
		<div id="divERMain">
		<table width="100%">        
            <tr>        
                <td>        
                	<input type="text" id="txtERQuery"></input>
                    <button type="text" openFormType="ER" id="btnERQuery" class="ui-button ui-corner-all ui-widget">Buscar</button>                    
                </td>
                <td>
                    <div openFormMode="new" openFormType="ER" divHide="#divERMain" title="Crear Escritura de Revocaci&oacute;n" class="btnPGAdd Img_Icon_new"></div>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <div id="gridERmain"> 
                    	<%=gson.toJson(initArgs.get(5)) %>
                    </div>
                </td>
            </tr>
        </table>  
        </div>
	</div>
	
	
	<div id="divPGForm" style="display:none">
		<input type="hidden" field="divPGForm_TypeReg" dataField="ind_tipo_escritura" controller="hdn" class="divPGForm" value="" />
       	<div id="divSeccH1" class="divSeccH showSecc showSeccER showSeccPG showSeccPE showSeccCP" toggleContent="false" style="height:30px; padding-top:5px">
       		<label id="divPGForm_title"></label>        		
       		<button type="button" id="divPGFormBtnClose"  style="float:right">Cerrar</button>
       		       		
       	</div>
       	<div id="divSeccH1Content" class="divSeccHContent showSecc showSeccER showSeccPG showSeccPE">
       		
       		<table width="100%">
       			<tr>
       				<td style="width:30%">Delegado por</td>
       				<td>
       				<select keyValue="ID_CATALOGO_VALOR" viewValue="NOM_CAT_VAL" dataField="ind_delegado_por" controller="cmb" embeddedData="true" defaultValue="0" defaultText="Seleccione..." class="divPGForm" eChange="eChange_ind_delegado_por">
       					<%=gson.toJson(initArgs.get(0)) %>
       				</select>
       				</td>
       			</tr>
       			<tr>
       				<td>Fecha</td>
       				<td><input type="text" dataField="fec_fecha" controller="calendar" class="divPGForm"></input></td>
       			</tr>
       			<tr>
       				<td>Hora</td>
       				<td><input type="text" dataField="fec_hora" controller="text" class="divPGForm" size='8'></input></td>
       			</tr>
       		</table>
       	</div>
       	
			<!--Para la seccion de Carta Poder -->
       		<div id="divSeccH1CartaPoderContent" class="divSeccHContent showSecc showSeccCP">
       		
       		<table width="100%">
       			<tr>
       				<td style="width:30%">Mediante</td>
       				<td>
       				<select keyValue="ID_CATALOGO_VALOR" viewValue="NOM_CAT_VAL" dataField="ind_mediante" controller="cmb" embeddedData="true" defaultValue="0" defaultText="Seleccione..." class="divPGForm">
       					<%=gson.toJson(initArgs.get(0)) %>
       				</select>
       				</td>
       			</tr>
       			<tr>
       				<td>Fecha</td>
       				<td><input type="text" dataField="fec_fecha_cp" controller="calendar" class="divPGForm"></input></td>
       			</tr>       			
       		</table>
       	</div>
       	
       	<form id="divPGForm" action="/Abc_PoderesGenerales" method="post">
       	<div id="divSeccA" class="divSeccHead showSecc showSeccER showSeccPG showSeccPE" toggleContent="false">
       		Instrumento 
       	</div>
       	<div id="divSeccAContent" class="divSeccContent showSecc showSeccER showSeccPG showSeccPE">
       		
       		<table width="100%">       			
       			<tr>
       				<td style="width:50%">Requiere Protocolizaci&oacute;n:
       				</td>
       				<td>
       					<input type="checkbox" name="PGForm_ind_requiere_proto" dataField="ind_requiere_proto" toggleDiv="#divPGForm_RP" defaultShow="false" controller="check" class="divPGForm"></input>
       				</td>
       			</tr>
       			<tr>
       				<td>Requiere Inscripci&oacute;n RPPC:</td>
       				<td><input type="checkbox" name="PGForm_ind_requiere_inscr_rppc" dataField="ind_requiere_inscr_rppc" toggleDiv="#divPGForm_RPPC" defaultShow="false" controller="check" class="divPGForm"></input></td>
       			</tr>
       			
       		</table>
       			
       			<table id="divPGForm_RP" width="100%" style="display:none">
       			<tr>
       				<td>Escritura No.</td>
       				<td><input type="text" field="divPGForm_des_escritura" dataField="des_escritura" controller="number" class="divPGForm" maxLength="6"></input></td>
       			</tr>
       			<tr>
       				<td>Escritura Digitalizada:</td>
       				<td><div field="divPGForm_ocumentum_instr" dataField="num_documentum_instr" controller="doc" class="divPGForm"></div></td>
       			</tr>
       			<tr>
       				<td>Fecha Otorgamiento:</td>
       				<td><input type="text" dataField="fec_otorgamiento_instr" controller="calendar" class="divPGForm"></input></td>
       			</tr>
       			<tr>
       				<td style="width:50%">Licenciado:</td>
       				<td><select field="PGForm_num_licenciado" dataField="num_licenciado" controller="cmb" keyValue="ID_CATALOGO_VALOR" viewValue="NOM_CAT_VAL" embeddedData="true" defaultValue="-1" defaultText="Seleccione..." class="divPGForm" eChange="eChange_num_licenciado">
       					<%=gson.toJson(initArgs.get(1)) %>
       				</select></td>
       			</tr>
       			<tr>
       				<td>Notario P&uacute;blico:</td>
       				<td><input type="text" field="PGForm_nom_notario_publico" dataField="nom_notario_publico" controller="text" eEnterKeyPress="eEnterKeyPress_notario_publico" class="divPGForm"></input></td>
       			</tr>
       			<tr>
       				<td>De:</td>
       				<td><select field="PGForm_num_de" dataField="num_de" controller="cmb" keyValue="val_cat_val" viewValue="nom_cat_val" embeddedData="true" defaultValue="-1" defaultText="N/D" class="divPGForm" >       					
       				<%=gson.toJson(initArgs.get(2)) %>
       				</select></td>
       			</tr>
       			<tr>
       				<td>Suplencia / Asociado
       				</td>
       				<td>
       				<textarea dataField="des_suplencia_asociado" controller="text" class="divPGForm" rows="1" style="width:70%"></textarea>
       				</td>
       			</tr>
       			</table>
       			
       			
       			<table id="divPGForm_RPPC" width="100%" style="display:none">
       			<tr>
       				<td style="width:50%">Inscrita en el Registro P&uacute;blico de Comercio de:
       				</td>
       				<td><select dataField="num_insc_regpub" dataFieldDesc="des_insc_regpub" controller="cmb" class="divPGForm" keyValue="val_cat_val" viewValue="nom_cat_val" embeddedData="true" defaultValue="-1" defaultText="N/D" class="divPGForm">
       				<%=gson.toJson(initArgs.get(2)) %>
       				</select></td>
       			</tr>
       			<tr>
       				<td>Fecha de Registro:
       				</td>
       				<td><input type="text" dataField="fec_registro" controller="calendar" class="divPGForm"></input></td>
       			</tr>
       			<tr>
       				<td> Folio Mercantil / Folio Mercantil Electr&oacute;nico</td>
       				<td><input type="text" dataField="num_folio_merc" controller="text" class="divPGForm"></input></td>
       			</tr>
       			<tr>
       				<td>Otros Datos de Registro</td>
       				<td>
       				<textarea dataField="des_otros_datos_registro" controller="text" class="divPGForm" rows="1" style="width:70%"></textarea>
       				</td>
       			</tr>
       		</table>
       		
       	</div>
       		
       	<div id="divSeccADeCartaPoder" class="divSeccHead showSecc showSeccCP">
       		Instrumento 
       	</div>
       		<div id="divSeccADeCartaPoderContent" class="divSeccContent showSecc showSeccCP">
       	
       			<table width="100%">
       			<tr>
       				<td style="width:30%">Carta Poder Digitalizada:</td>
       				<td><div dataField="num_documentum_instr_cp" controller="doc" class="divPGForm"></div></td>
       			</tr>
       			</table>

       	</div>
       	
       	    	
       	
       	<div id="divSeccC" class="divSeccHead showSecc showSeccER showSeccPG showSeccPE showSeccCP" toggleContent="true">
       		Status
       	</div>
       	<div id="divSeccCContent" class="divSeccContent showSecc showSeccER showSeccPG showSeccPE showSeccCP" style="display:none">   
       		
       			<table width="100%">       		
	       			<tr>
	       				<td valign="" height="0" style="width:50%"><div id="DIVC_30_VAL_C28_1">Aplica: </div></td>
	       				<td>
	       					<input type="checkbox" name="PGForm_ind_requiere_status" dataField="ind_aplica_status" toggleDiv="#divStatusDetail" defaultShow="false" controller="check" class="divPGForm"></input><br></div>
	       				</td>
	       			</tr>
	       			<tr class="tableRow2">
       					<td valign="" height="0" style="width:50%"><div id="DIVC_30_VAL_C150_1">Asunto: </div></td>
       					<td><img id="img_semaforo_status" src="/DerechoCorporativo/img/semaforo_gray.png"></td>
       				</tr>
       			</table>
       				<table width="100%" id="divStatusDetail">       		       					
       				<tr>
       					<td>Fecha Programada de Entrega:</td>
       					<td><input type="text" dataField="fec_pe" controller="calendar" class="divPGForm"></input>
       					</td>
       				</tr>
       			<tr><td style="font-weight:bold">Redactada:</td><td><input type="checkbox" field="ind_status_ac_1" dataField="ind_status_ac" controller="checkradio" class="divPGForm ind_status_ac"></input></td></tr>
<tr><td>Responsable:</td><td><select field="PGFormdes_rep_resp" dataField="id_red_resp"  dataFieldDesc="des_rep_resp" controller="cmb" keyValue="ID_CATALOGO_VALOR" viewValue="NOM_CAT_VAL" embeddedData="true" defaultValue="-1" defaultText="Seleccione..." class="divPGForm"><%=gson.toJson(initArgs.get(8)) %></select></td></tr>
<tr><td>Cumplimiento:</td><td><input type="text" dataField="fec_rep" controller="calendar" class="divPGForm"></input></td></tr>
<tr><td style="font-weight:bold">Revis&oacute;n Gerente:</td><td><input type="checkbox" field="ind_status_ac_2" dataField="ind_status_ac" controller="checkradio" class="divPGForm ind_status_ac"></input></td></tr>
<tr><td>Responsable:</td><td><select field="PGFormdes_reg_resp" dataField="id_reg_resp"  dataFieldDesc="des_reg_resp" controller="cmb" keyValue="ID_CATALOGO_VALOR" viewValue="NOM_CAT_VAL" embeddedData="true" defaultValue="-1" defaultText="Seleccione..." class="divPGForm"><%=gson.toJson(initArgs.get(8)) %></select></td></tr>
<tr><td>Cumplimiento:</td><td><input type="text" dataField="fec_reg" controller="calendar" class="divPGForm"></input></td></tr>
<tr><td style="font-weight:bold">Correcciones:</td><td><input type="checkbox" field="ind_status_ac_3" dataField="ind_status_ac" controller="checkradio" class="divPGForm ind_status_ac"></input></td></tr>
<tr><td>Responsable:</td><td><select field="PGFormdes_cor_resp" dataField="id_cor_resp"  dataFieldDesc="des_cor_resp" controller="cmb" keyValue="ID_CATALOGO_VALOR" viewValue="NOM_CAT_VAL" embeddedData="true" defaultValue="-1" defaultText="Seleccione..." class="divPGForm"><%=gson.toJson(initArgs.get(8)) %></select></td></tr>
<tr><td>Cumplimiento:</td><td><input type="text" dataField="fec_cor" controller="calendar" class="divPGForm"></input></td></tr>
<tr><td style="font-weight:bold">Aut. Direcci&oacute;n:</td><td><input type="checkbox" field="ind_status_ac_4" dataField="ind_status_ac" controller="checkradio" class="divPGForm ind_status_ac"></input></td></tr>
<tr><td>Responsable:</td><td><select field="PGFormdes_aut_resp" dataField="id_aut_resp"  dataFieldDesc="des_aut_resp" controller="cmb" keyValue="ID_CATALOGO_VALOR" viewValue="NOM_CAT_VAL" embeddedData="true" defaultValue="-1" defaultText="Seleccione..." class="divPGForm"><%=gson.toJson(initArgs.get(8)) %></select></td></tr>
<tr><td>Cumplimiento:</td><td><input type="text" dataField="fec_aut" controller="calendar" class="divPGForm"></input></td></tr>
<tr><td style="font-weight:bold">En Firmas:</td><td><input type="checkbox" field="ind_status_ac_5" dataField="ind_status_ac" controller="checkradio" class="divPGForm ind_status_ac"></input></td></tr>
<tr><td>Responsable:</td><td><select field="PGFormdes_fir_resp" dataField="id_fir_resp"  dataFieldDesc="des_fir_resp" controller="cmb" keyValue="ID_CATALOGO_VALOR" viewValue="NOM_CAT_VAL" embeddedData="true" defaultValue="-1" defaultText="Seleccione..." class="divPGForm"><%=gson.toJson(initArgs.get(8)) %></select></td></tr>
<tr><td>Cumplimiento:</td><td><input type="text" dataField="fec_fir" controller="calendar" class="divPGForm"></input></td></tr>
<tr><td style="font-weight:bold">Entregada:</td><td><input type="checkbox" field="ind_status_ac_6" dataField="ind_status_ac" controller="checkradio" class="divPGForm ind_status_ac"></input></td></tr>
<tr><td>Responsable:</td><td><select field="PGFormdes_ent_resp" dataField="id_ent_resp"  dataFieldDesc="des_ent_resp" controller="cmb" keyValue="ID_CATALOGO_VALOR" viewValue="NOM_CAT_VAL" embeddedData="true" defaultValue="-1" defaultText="Seleccione..." class="divPGForm"><%=gson.toJson(initArgs.get(8)) %></select></td></tr>
<tr><td>Cumplimiento:</td><td><input type="text" dataField="fec_ent" controller="calendar" class="divPGForm"></input></td></tr>
       			</table>
       			
       	</div>
       	
       	<div id="divSeccB" class="divSeccHead showSecc showSeccER showSeccPG showSeccPE showSeccCP" toggleContent="true">
       		Referencia Documentum 
       	</div>
       	<div id="divSeccBContent" class="divSeccContent showSecc showSeccER showSeccPG showSeccPE showSeccCP" style="display:none">   
       		<table width="100%">
       			<tr><td style="width:50%">Solicitud:</td><td><div field="divPGForm_id_sol_doc" dataField="id_sol_doc" controller="doc" class="divPGForm"></div></td></tr>

<tr><td>Solicitado por:</td><td><select field="PGFormdes_sol_resp" dataField="id_sol_resp"  dataFieldDesc="des_sol_resp" controller="cmb" keyValue="ID_CATALOGO_VALOR" viewValue="VAL_CAT_VAL" embeddedData="true" defaultValue="-1" defaultText="Seleccione..." class="divPGForm"><%=gson.toJson(initArgs.get(7)) %></select></td></tr>
<tr><td>Fecha de Solicitud:</td><td><input type="text" dataField="fec_sol" controller="calendar" class="divPGForm"></input></td></tr>
<tr><td>Fecha de Recibido:</td><td><input type="text" dataField="fec_sol_rec" controller="calendar" class="divPGForm"></input></td></tr>
<tr><td>Folio:</td><td><input type="text" dataField="des_sol_folio" controller="text" class="divPGForm"></input></td></tr>
<tr><td>Documento de Entrega:</td><td><div field="divPGForm_id_ent_doc" dataField="id_ent_doc" controller="doc" class="divPGForm"></div></td></tr>
<tr><td>Fecha de Documento:</td><td><input type="text" dataField="fec_ent_doc" controller="calendar" class="divPGForm"></input></td></tr>
<tr><td>Fecha de Recibido:</td><td><input type="text" dataField="fec_ent_rec" controller="calendar" class="divPGForm"></input></td></tr>


       			
       		</table>
       		
       		<button type="button" id="PGForm_Btn_AddDocuments">Agregar Documento</button> 
       		<br/>   		
       		<div id="PGForm_Grid_Documents" field="PGForm_Grid_Documents" dataField="documents" controller="grid" class="divPGForm"></div>
       		
       	</div>   
       	
       	<div id="divSeccD" class="divSeccHead showSecc showSeccPG" toggleContent="true">
       		Poderes
       	</div>
       	<div id="divSeccDContent" class="divSeccContent showSecc showSeccPG">
       		<table width="100%">
       			<tr>
       				<td style="width:10%">Poder:</td>
       				<td style="width:80%"><div id="PGForm_Grid_poderSelecc"></div></td>
       				<td style="width:10%"><div id="PGForm_Btn_poderSelecc" class="Img_Icon_new" style="float:right"></div></td>       				
       			</tr>
       			<tr>
       				<td></td>
       				<td colspan="2"><button type="button" id="PGForm_Btn_poderOrder" style="float:left;">Ordenar</button></td>       				
       			</tr>
       			<tr>
       				<td colspan="2">
       					<div id="PGForm_Btn_poderAddCarta" class="Img_Icon_new" style="display:none"></div>
       				</td>
       				<td></td>       				
       			</tr>
       			<tr>
       				<td colspan="3">
       					<div id="PGForm_Grid_apoderados" field="PGForm_Grid_apoderados" dataField="Apoderados" controller="grid" class="divPGForm"></div>
       				</td>
       			</tr>
       			<tr>
       				<td colspan="2">
       					<div id="PGForm_lbl_apoderados_revocados" dataField="RevocadosLeyenda" controller="label" class="divPGForm ApendiceRevocados"></div>
       				</td>
       				<td>
       				
       				</td>	
       			</tr>
       		</table>
       	</div>
       	<div class="divSeccContent showSecc showSeccER showSeccPG" style="display:none">
       		<button type="button" id="PGForm_Btn_poderSave" style="float:right">Guardar</button>
       	</div>
       	<div id="divSeccE" class="divSeccHead showSecc showSeccPE" toggleContent="false">
       		Poderes
       	</div>
       	<div id="divSeccEContent" class="divSeccContent showSecc showSeccPE">
       		
       	</div>
       	
       	<div id="divSeccF" class="divSeccHead showSecc showSeccCP" toggleContent="false">
       		Poderes
       	</div>
       	<div id="divSeccFContent" class="divSeccContent showSecc showSeccCP">
       		
       	</div>
       	
       	</form>
       </div>   

</div>

<div id="PGForm_DlgApoderadosOtros" title="Captura de poderes">
	<div id="PGForm_DlgPoderes" title="Seleccionar Poderes">
	<table width="100%">		
		<tr>			
			<td><label for="PGForm_DlgPoderes_Txt_poder">Poder:</label>  <input type="text" id="PGForm_DlgPoderes_Txt_poder" size="10"></input><button type="button" id="PGForm_DlgPoderes_Btn_buscarPoder">Buscar</button></td>
		</tr>
		<tr>
			<td></td>
		</tr>
		<tr>
			<td>
				<div id="PGForm_DlgPoderes_Grid_poderes" field="PGForm_DlgPoderes_Grid_poderes"  radioName="PGForm_Grid_apoderados_radio" dataField="num_podertipo" dataFieldDesc="des_podertipo" keyValue="id_poder_pk" viewValue="des_podertipo" embeddedData="true" controller="radioGrid" class="PGForm_DlgApoderados" defaultValue="-1">
				<%=gson.toJson(initArgs.get(3)) %>
				</div>
			</td>
		</tr>
	</table>
</div>
	<div id="PGForm_DlgApoderados" title="Captura de poderes">
	
	<fieldset>
    <legend style="font-weight:bold">Apoderados</legend>
    
	<div>
	<input type="checkbox" field="PGForm_DlgApoderados_chk_GroupPoderes" toggleDivXOr=".PGForm_DlgApoderados_div_NotGroupPoderes" toggleDiv=".PGForm_DlgApoderados_div_GroupPoderes" defaultShow="false" controller="check" class="PGForm_DlgApoderados" eChange="eChange_chk_GroupPoderes"/>
	  <label for="PGForm_DlgApoderadosOtros_chk_aD">Agrupar Apoderados</label>
	  <button id="PGForm_DlgApoderados_btn_orderAllapoder" type="button">Ordenar</button>
	  <div class="PGForm_DlgApoderados_div_GroupPoderes">
		<label for="PGForm_DlgApoderados_gapoder">Grupos de apoderados</label>
		<select field="PGForm_DlgApoderados_gapoder" keyValue="ID_CATALOGO_VALOR" viewValue="NOM_CAT_VAL" dataField="id_grupo_fk" controller="cmb" embeddedData="true" defaultValue="0" defaultText="" class="PGForm_DlgApoderados">
			<%=gson.toJson(initArgs.get(6)) %>
		</select>
	</div>
	<button id="PGForm_DlgApoderados_btn_addAllapoder" type="button">Seleccionar...</button>
	</div>
	
	<div style="font-weight:bold">Seleccionados:</div>
	<div id="PGForm_DlgApoderados_Div_gapoderSelecc" class="PGForm_DlgApoderados_div_GroupPoderes"></div>
	<div id="PGForm_DlgApoderados_Grid_gapoderSelecc" dataField="Apoderados" controller="grid" class="PGForm_DlgApoderados PGForm_DlgApoderados_div_NotGroupPoderes"></div>
    </fieldset>
	
	<fieldset>
    <legend style="font-weight:bold">Vigencia</legend>
	<table width="100%">
		<tr>
			<td style="width:20%">Vigencia</td>
			<td style="width:30%">
				<select field="PGForm_DlgApoderados_cmb_vigencia" dataField="num_vigenciatipo" dataFieldDesc="des_vigenciatipo" controller="cmb" class="PGForm_DlgApoderados" defaultValue="-1" defaultText="Seleccione..." eChange="eChange_cmb_vigencia">
					<option value="0">Sin Vigencia</option>
					<option value="1">A&ntilde;os</option>
					<option value="2">Meses</option>
					<option value="3">Rango</option>
				</select>
				
			</td>
						
		</tr>
		<tr>
			<td>Tiempo</td>
			<td>
			<input maxlength="3" field="PGForm_DlgApoderados_spi_vigencia" dataField="num_vigenciatiempo" controller="spinner" class="PGForm_DlgApoderados" eChange="eChange_spn_vigenciatiempo"/>
			</td>
			<td></td>
		</tr>
		<tr>
			<td>Fecha Inicio</td>
			<td>
			<input type="text" field="PGForm_DlgApoderados_calendar_vigenciaInicio" dataField="fec_vigenciainicio" toField="PGForm_DlgApoderados_calendar_vigenciaFin" controller="calendarRange" class="PGForm_DlgApoderados" style="z-index:99"/></td>
			<td></td>
		</tr>
		<tr>
			<td>Vencimiento</td>
			<td>
			
			<input type="text" field="PGForm_DlgApoderados_calendar_vigenciaFin" dataField="fec_vigenciafin" fromField="PGForm_DlgApoderados_calendar_vigenciaInicio" controller="calendarRange" class="PGForm_DlgApoderados"/></td>
			<td></td>
		</tr>
	</table>
	</fieldset>
</div>
	<div id="PGForm_DlgApoderadosOtros_Div">
		
		<fieldset class="DetallePoder DetallePoderCat DetallePoderGeneral">
    		<legend style="font-weight:bold">Apoderados</legend>
			<div id="PGForm_DlgApoderadosOtros_Grid_ApSelecc"></div>
			<!-- <div id="PGForm_DlgApoderadosOtros_Div_ApSelecc" class="PGForm_DlgApoderados_div_GroupPoderes"></div> -->
		</fieldset>
		<fieldset class="DetallePoder DetallePoderCat">
    		<legend id="DetallePoderCat_title" style="font-weight:bold">Apoderados</legend>
			<div id="DetallePoderCat_detail"></div>
		</fieldset>
		
		<!-- <fieldset class="DetallePoder DetallePoderCat DetallePoderGeneral"> -->
    		<!-- <legend style="font-weight:bold;">Caracter&iacute;sticas/Limitaciones</legend> -->
    		<textarea style="display:none;width:100%;" rows="3" cols="20" field="PGForm_DlgApoderados_txt_caract" dataField="desc_caracteristicas" type="text" class="PGForm_DlgApoderados"></textarea>
		<!-- </fieldset> -->
		
		<div id="PGForm_DlgApoderadosOtros_tabs" class="DetallePoder DetallePoderGeneral">
	  <ul>
	    <li><a href="#PGForm_DlgApoderadosOtros_1">Actos de Administraci&oacute;n</a></li>
	    <li><a href="#PGForm_DlgApoderadosOtros_2">Actos de Dominio</a></li>
	    <li><a href="#PGForm_DlgApoderadosOtros_3">T&iacute;tulos de Cr&eacute;dito</a></li>
	    <li><a href="#PGForm_DlgApoderadosOtros_4">Pleitos y Cobranzas</a></li>    
	    
	  </ul>
	  
	  <div id="PGForm_DlgApoderadosOtros_1">
	  	<input type="checkbox" field="PGForm_DlgApoderadosOtros_chk_aA" toggleDiv="#PGForm_DlgApoderadosOtros_chk_aA_Table" defaultShow="false" controller="check" class="PGForm_DlgApoderados"/>
	  	<label for="PGForm_DlgApoderadosOtros_chk_aA">Actos de Administraci&oacute;n</label>
	    <table width="100%" id="PGForm_DlgApoderadosOtros_chk_aA_Table">
	    	<tr>
	    		<td style="width:33%" valign="top">
	    			<div>
	    				<input type="radio" name="PGForm_DlgApoderadosOtros_Deleg1" id="PGForm_DlgApoderadosOtros_DelegA1" value="Delegable" checked="checked" />
	    				<label for="PGForm_DlgApoderadosOtros_Deleg1">Delegable</label>
	    			</div>
	    			<div>
	    				<input type="radio" name="PGForm_DlgApoderadosOtros_Deleg1" id="PGForm_DlgApoderadosOtros_DelegB1" value="No Delegable" />
	    				<label for="PGForm_DlgApoderadosOtros_Deleg1">No Delegable</label>
	    			</div>
	    		</td>
	    		<td colspan="2" valign="top">
	    			<div>
	    				<input type="radio" name="PGForm_DlgApoderadosOtros_Deleg2" id="PGForm_DlgApoderadosOtros_DelegA2" value="Individual" checked="checked"/>
	    				<label for="PGForm_DlgApoderadosOtros_DelegA2">Individual</label>
	    			</div>
	    			<div>
	    				<input type="radio" name="PGForm_DlgApoderadosOtros_Deleg2" id="PGForm_DlgApoderadosOtros_DelegB2" value="Conjuntamente" />
	    				<label for="PGForm_DlgApoderadosOtros_DelegB2">Conjuntamente</label>
	    			</div>
	    			<div>
	    				<input type="radio" name="PGForm_DlgApoderadosOtros_Deleg2" id="PGForm_DlgApoderadosOtros_DelegC2" value="Conjuntamente o Separadamente" />
	    				<label for="PGForm_DlgApoderadosOtros_DelegB2">Conjuntamente o Separadamente</label>
	    			</div>
	    			<div>
	    				<input type="radio" name="PGForm_DlgApoderadosOtros_Deleg2" id="PGForm_DlgApoderadosOtros_DelegD2" value="Otro" />
	    				<label for="PGForm_DlgApoderadosOtros_DelegD2">Otro</label>
	    			</div>  
	    		</td>
	    		
	    	</tr>
	    	<tr>
	    		<td  valign="top">
	    			<div><label for="PGForm_DlgApoderadosOtros_txt_caractLAL">Caracter&iacute;sticas/Limitaciones</label></div>
	    			<textarea style="width:98%;" rows="5" cols="15" id="PGForm_DlgApoderadosOtros_txt_caractLAL" class="PGForm_DlgApoderadosOtros"></textarea>
	    			     			
	    		</td>
	    		<td  valign="top" style="width:33%" >
	    			<div><label for="PGForm_DlgApoderadosOtros_txt_caract">Forma de Ejercerlo</label></div>
	    			<textarea style="width:98%;" rows="5" cols="15" id="PGForm_DlgApoderadosOtros_txt_caractA" class="PGForm_DlgApoderadosOtros"></textarea>
	    			     			
	    		</td>
	    		<td style="width:30%; padding-left:15px;" valign="top">
	    			<div>
	    				<input type="checkbox" field="PGForm_DlgApoderadosOtros_ApodA" toggleDiv="#PGForm_DlgApoderadosOtros_Apod_DivA" defaultShow="false" controller="check" class="PGForm_DlgApoderados"></input>
	    				
	    				<label for="PGForm_DlgApoderadosOtros_DelegC2">Mancomunado</label>
	    			</div>
	    			<div id="PGForm_DlgApoderadosOtros_Apod_DivA">
	    			<button type="button" metadata="A" class="PGForm_DlgApoderadosOtros_BtnCon">Seleccione...</button>
	    			<div id="PGForm_DlgApoderadosOtros_grid_A"></div>
	    			</div>
	    		</td>
	    	
	    	
	    		
	    		
	    	</tr>
	    </table>
	  </div>  
	  
	  <div id="PGForm_DlgApoderadosOtros_2">
	  <input type="checkbox" field="PGForm_DlgApoderadosOtros_chk_aD" toggleDiv="#PGForm_DlgApoderadosOtros_chk_aD_Table" defaultShow="false" controller="check" class="PGForm_DlgApoderados"/>
	  <label for="PGForm_DlgApoderadosOtros_chk_aD">Actos de Dominio</label>
	    <table width="100%" id="PGForm_DlgApoderadosOtros_chk_aD_Table">
	    	<tr>
	    		<td style="width:33%" valign="top">
	    			<div>
	    				<input type="radio" name="PGForm_DlgApoderadosOtros_Deleg1B" id="PGForm_DlgApoderadosOtros_DelegA2" value="Delegable" checked="checked" />
	    				<label for="PGForm_DlgApoderadosOtros_Deleg1">Delegable</label>
	    			</div>
	    			<div>
	    				<input type="radio" name="PGForm_DlgApoderadosOtros_Deleg1B" id="PGForm_DlgApoderadosOtros_DelegB2" value="No Delegable" />
	    				<label for="PGForm_DlgApoderadosOtros_Deleg1">No Delegable</label>
	    			</div>
	    		</td>
	    		<td colspan="2"  valign="top">
	    			<div>
	    				<input type="radio" name="PGForm_DlgApoderadosOtros_Deleg2B" id="PGForm_DlgApoderadosOtros_DelegA2B" value="Individual" checked="checked"/>
	    				<label for="PGForm_DlgApoderadosOtros_DelegA2">Individual</label>
	    			</div>
	    			<div>
	    				<input type="radio" name="PGForm_DlgApoderadosOtros_Deleg2B" id="PGForm_DlgApoderadosOtros_DelegB2B" value="Conjuntamente" />
	    				<label for="PGForm_DlgApoderadosOtros_DelegB2">Conjuntamente</label>
	    			</div> 
	    			<div>
	    				<input type="radio" name="PGForm_DlgApoderadosOtros_Deleg2B" id="PGForm_DlgApoderadosOtros_DelegC2B" value="Conjuntamente o Separadamente" />
	    				<label for="PGForm_DlgApoderadosOtros_DelegB2">Conjuntamente o Separadamente</label>
	    			</div> 			
	    			<div>
	    				<input type="radio" name="PGForm_DlgApoderadosOtros_Deleg2B" id="PGForm_DlgApoderadosOtros_DelegD2B" value="Otro" />
	    				<label for="PGForm_DlgApoderadosOtros_DelegD2">Otro</label>
	    			</div>
	    			
	    		</td>
	    		
	    		</tr>
	    		<tr>
	    		
	    		<td  valign="top">
	    			<div><label for="PGForm_DlgApoderadosOtros_txt_caractLB">Caracter&iacute;sticas/Limitaciones</label></div>
	    			<textarea style="width:98%;" rows="5" cols="15" id="PGForm_DlgApoderadosOtros_txt_caractLB" class="PGForm_DlgApoderadosOtros"></textarea>
	    			     			
	    		</td>
	    		<td valign="top" style="width:33%">
	    			<div><label for="PGForm_DlgApoderadosOtros_txt_caractB">Forma de Ejercerlo</label></div>
	    			<textarea style="width:98%;" rows="5" cols="15" id="PGForm_DlgApoderadosOtros_txt_caractB" class="PGForm_DlgApoderadosOtros"></textarea>
	    		</td>
	    		<td style="padding-left:15px;" valign="top">		    			
	    			<div>
	    				<input type="checkbox" field="PGForm_DlgApoderadosOtros_ApodB" toggleDiv="#PGForm_DlgApoderadosOtros_Apod_DivB" defaultShow="false" controller="check" class="PGForm_DlgApoderados"></input>
	    				
	    				<label for="PGForm_DlgApoderadosOtros_ApodB">Mancomunado</label>
	    			</div>
	    			<div id="PGForm_DlgApoderadosOtros_Apod_DivB">
	    			<button type="button" metadata="B" class="PGForm_DlgApoderadosOtros_BtnCon">Seleccione...</button>
	    			<div id="PGForm_DlgApoderadosOtros_grid_B"></div>
	    			</div>
	    		</td>
	    	</tr>
	    </table>
	  </div>  
	  <div id="PGForm_DlgApoderadosOtros_3">
	  <input type="checkbox" field="PGForm_DlgApoderadosOtros_chk_tC" toggleDiv="#PGForm_DlgApoderadosOtros_chk_tC_Table" defaultShow="false" controller="check" class="PGForm_DlgApoderados"/>
	  <label for="PGForm_DlgApoderadosOtros_chk_tC">T&iacute;tulos de Cr&eacute;dito</label>
	    <table width="100%" id="PGForm_DlgApoderadosOtros_chk_tC_Table">
	    	<tr>
	    		<td style="width:33%" valign="top">
	    			<div>
	    				<input type="radio" name="PGForm_DlgApoderadosOtros_Deleg1C" id="PGForm_DlgApoderadosOtros_DelegA3" value="Delegable" checked="checked" />
	    				<label for="PGForm_DlgApoderadosOtros_Deleg1">Delegable</label>
	    			</div>
	    			<div>
	    				<input type="radio" name="PGForm_DlgApoderadosOtros_Deleg1C" id="PGForm_DlgApoderadosOtros_DelegB3" value="No Delegable" />
	    				<label for="PGForm_DlgApoderadosOtros_Deleg1">No Delegable</label>
	    			</div>
	    		</td>
	    		<td  colspan="2"  valign="top"> 
	    			<div>
	    				<input type="radio" name="PGForm_DlgApoderadosOtros_Deleg2C" id="PGForm_DlgApoderadosOtros_DelegA2C" value="Individual" checked="checked"/>
	    				<label for="PGForm_DlgApoderadosOtros_DelegA2">Individual</label>
	    			</div>
	    			<div>
	    				<input type="radio" name="PGForm_DlgApoderadosOtros_Deleg2C" id="PGForm_DlgApoderadosOtros_DelegB2C" value="Conjuntamente" />
	    				<label for="PGForm_DlgApoderadosOtros_DelegB2">Conjuntamente</label>
	    			</div>    
	    				
	    			<div>
	    				<input type="radio" name="PGForm_DlgApoderadosOtros_Deleg2C" id="PGForm_DlgApoderadosOtros_DelegC2C" value="Conjuntamente o Separadamente" />
	    				<label for="PGForm_DlgApoderadosOtros_DelegB2">Conjuntamente o Separadamente</label>
	    			</div>		
	    			<div>
	    				<input type="radio" name="PGForm_DlgApoderadosOtros_Deleg2C" id="PGForm_DlgApoderadosOtros_DelegD2C" value="Otro" />
	    				<label for="PGForm_DlgApoderadosOtros_DelegD2">Otro</label>
	    			</div>    			
	    		</td>
	    		
	    		
	    		</tr>
	    		<tr>
	    		<td  valign="top">
	    			<div><label for="PGForm_DlgApoderadosOtros_txt_caractLC">Caracter&iacute;sticas/Limitaciones</label></div>
	    			<textarea style="width:98%;" rows="5" cols="15" id="PGForm_DlgApoderadosOtros_txt_caractLC" class="PGForm_DlgApoderadosOtros"></textarea>
	    			     			
	    		</td>
	    		<td valign="top" style="width:33%">
	    			<div><label for="PGForm_DlgApoderadosOtros_txt_caractC">Forma de Ejercerlo</label></div>
	    			<textarea style="width:98%;" rows="5" cols="15" id="PGForm_DlgApoderadosOtros_txt_caractC" class="PGForm_DlgApoderadosOtros"></textarea>
	    		</td>
	    		<td rowspan="2" style="width:30%; padding-left:15px;" valign="top">   		    			
	    			<div>
	    				<input type="checkbox" field="PGForm_DlgApoderadosOtros_ApodC" toggleDiv="#PGForm_DlgApoderadosOtros_Apod_DivC" defaultShow="false" controller="check" class="PGForm_DlgApoderados"></input>
	    				
	    				<label for="PGForm_DlgApoderadosOtros_ApodC">Mancomunado</label>
	    			</div>
	    			<div id="PGForm_DlgApoderadosOtros_Apod_DivC">
	    			<button type="button" metadata="C" class="PGForm_DlgApoderadosOtros_BtnCon">Seleccione...</button>
	    			<div id="PGForm_DlgApoderadosOtros_grid_C"></div>
	    			</div>
	    		</td>
	    		
	    	</tr>
	    </table>
	  </div>  
	  
	  <div id="PGForm_DlgApoderadosOtros_4" title="Apoderados a la carta">
	  <input type="checkbox" field="PGForm_DlgApoderadosOtros_chk_pC" toggleDiv="#PGForm_DlgApoderadosOtros_chk_pC_Table" defaultShow="false" controller="check" class="PGForm_DlgApoderados"/>
	  <label for="PGForm_DlgApoderadosOtros_chk_pC">Pleitos y Cobranzas</label>
	    <table width="100%" id="PGForm_DlgApoderadosOtros_chk_pC_Table">
	    	<tr>
	    		<td style="width:33%" valign="top">
	    			<div>
	    				<input type="radio" name="PGForm_DlgApoderadosOtros_Deleg1D" id="PGForm_DlgApoderadosOtros_DelegA4" value="Delegable" checked="checked" />
	    				<label for="PGForm_DlgApoderadosOtros_Deleg1">Delegable</label>
	    			</div>
	    			<div>
	    				<input type="radio" name="PGForm_DlgApoderadosOtros_Deleg1D" id="PGForm_DlgApoderadosOtros_DelegB4" value="No Delegable" />
	    				<label for="PGForm_DlgApoderadosOtros_Deleg1">No Delegable</label>
	    			</div>
	    		</td>
	    		<td  colspan="2"  valign="top">
	    			<div>
	    				<input type="radio" name="PGForm_DlgApoderadosOtros_Deleg2D" id="PGForm_DlgApoderadosOtros_DelegA2D" value="Individual" checked="checked"/>
	    				<label for="PGForm_DlgApoderadosOtros_DelegA2">Individual</label>
	    			</div>
	    			<div>
	    				<input type="radio" name="PGForm_DlgApoderadosOtros_Deleg2D" id="PGForm_DlgApoderadosOtros_DelegB2D" value="Conjuntamente" />
	    				<label for="PGForm_DlgApoderadosOtros_DelegB2">Conjuntamente</label>
	    			</div>    	
	    				
	    			<div>
	    				<input type="radio" name="PGForm_DlgApoderadosOtros_Deleg2D" id="PGForm_DlgApoderadosOtros_DelegC2D" value="Conjuntamente o Separadamente" />
	    				<label for="PGForm_DlgApoderadosOtros_DelegB2">Conjuntamente o Separadamente</label>
	    			</div> 	
	    			<div>
	    				<input type="radio" name="PGForm_DlgApoderadosOtros_Deleg2D" id="PGForm_DlgApoderadosOtros_DelegD2D" value="Otro" />
	    				<label for="PGForm_DlgApoderadosOtros_DelegD2">Otro</label>
	    			</div>
	    			
	    		</td>
	    		
	    	</tr>
	    	<tr>
	    		
	    		<td  valign="top">
	    			<div><label for="PGForm_DlgApoderadosOtros_txt_caractLD">Caracter&iacute;sticas/Limitaciones</label></div>
	    			<textarea style="width:98%;" rows="5" cols="15" id="PGForm_DlgApoderadosOtros_txt_caractLD" class="PGForm_DlgApoderadosOtros"></textarea>
	    			     			
	    		</td>
	    		<td  valign="top" style="width:33%">
	    			<div><label for="PGForm_DlgApoderadosOtros_txt_caractD">Forma de Ejercerlo</label></div>
	    			<textarea style="width:98%;" rows="5" cols="15" id="PGForm_DlgApoderadosOtros_txt_caractD" class="PGForm_DlgApoderadosOtros"></textarea>
	    		</td>
	    		<td style="padding-left:15px;" valign="top">    		    			
	    			<div>
	    				<input type="checkbox" field="PGForm_DlgApoderadosOtros_ApodD" toggleDiv="#PGForm_DlgApoderadosOtros_Apod_DivD" defaultShow="false" controller="check" class="PGForm_DlgApoderados"></input>
	    				
	    				<label for="PGForm_DlgApoderadosOtros_ApodD">Mancomunado</label>
	    			</div>
	    			<div id="PGForm_DlgApoderadosOtros_Apod_DivD">
	    			<button type="button" metadata="D" class="PGForm_DlgApoderadosOtros_BtnCon">Seleccione...</button>
	    			<div id="PGForm_DlgApoderadosOtros_grid_D"></div>
	    			</div>
	    		</td>
	    	</tr>
	    </table>
	  </div> 
	</div>
	
	
	</div>
</div>

<div id="PGForm_DlgRevocacion" title="Revocaci&oacute;n">
	
	<div id="PGForm_DlgRevocacion_divGrid">
	<fieldset>
    <legend style="font-weight:bold">Revocar</legend>    
    <div id="PGForm_DlgRevocacion_grid_Revocados" class="DlgRevocacion_grid_Revocados"></div>
    </fieldset>
    </div>
    <div  id="PGForm_DlgRevocacion_divRevocar" style="display:none">     
    
    <label>Revocar a:</label><label id="GForm_DlgRevocacion_LblName" style="font-weight:bold"></label>
    <br />
    <fieldset>
    	<legend style="font-weight:bold">Campos para Revocar</legend>
    	<table width="100%">
    		<tr>
    			<td>El poder termin&oacute; por:</td>
    			<td><select field="PGForm_DlgRevocacion_cmb_Escritura_cmb_razon" controller="cmb" class="PGForm_DlgRevocacion" eChange="eChange_cmb_razonRevocacion">
						<option value="" selected="selected">---Seleccione---</option>
						<option value="1">Revocaci&oacute;n</option>
						<option value="2">Muerte</option>
						
						<option value="4">Se agot&oacute; el objeto</option>
						<option value="5">Renuncia</option>
					</select></td>
    			<td>De Fecha:</td>
    			<td><input type="text" field="PGForm_DlgRevocacion_cmb_Escritura_date_fecha" controller="calendar" class="PGForm_DlgRevocacion"></input></td>
    		</tr>
    		<tr>
    			<td width="15%">Escritura</td>
    			<td width="35%">
    				<select field="PGForm_DlgRevocacion_cmb_Escritura"  embeddedData="true"  keyValue="des_escritura" viewValue="des_escritura" defaultValue="N/D" defaultText="---Seleccione---" controller="cmb" class="PGForm_DlgRevocacion"  eChange="eChange_cmb_Escritura">						
						<%=gson.toJson(listEP) %>
					</select>					
					</td>
				<td width="10%">
				
				</td>
				<td width="35%"></td>
    		</tr>    		
    		<tr>
    			<td>Documento Digitalizado: </td>
    			<td><div type="text" field="PGForm_DlgRevocacion_doc_Documento_digital" controller="doc" class="PGForm_DlgRevocacion"></div></td>
    			<td></td>
    			<td></td>
    		</tr>
    	</table>
    </fieldset>
    </div>
    
</div>

<div id="PGForm_DlgCopy" title="COPIAR ESCRITURA">
	<fieldset>
    	<legend style="font-weight:bold"></legend>
    	<table width="100%">
    		<tr>
    		
    			<td><input id="PGForm_DlgCopy_CopyType" type="checkbox" />Integrar Escritura Poder General a Especial</td>
    		</tr>
    		<tr class="divCopyType">
    			<td width="25%"><input name="PGForm_DlgCopy_modeType" id="PGForm_DlgCopy_modeType" value="PG" type="radio" />General</td>
    			<td width="25%"><input name="PGForm_DlgCopy_modeType" id="PGForm_DlgCopy_modeType" value="PE" type="radio" />Especial</td>
    			<td width="25%"><input name="PGForm_DlgCopy_modeType" id="PGForm_DlgCopy_modeType" value="CP" type="radio" />Carta Poder</td>
    			<td width="25%"><input name="PGForm_DlgCopy_modeType" id="PGForm_DlgCopy_modeType" value="ER" type="radio" />Revocaci&oacute;n</td>
    		</tr>
    		<tr class="divCopyType">
    			<td colspan="3">
    				<button type="button" id="PGForm_DlgCopy_btnSel">Seleccione la empresa o empresas a copiar</button>
    			</td>    			
    		</tr>
    		<tr class="divCopyType">
    			<td colspan="3">
    				<div id="PGForm_DlgCopy_gridSel"></div>
    			</td>
    		</tr>
    	</table>
    </fieldset>
    
</div>
<div id="MetaSession" style="display:none">	
	<%=jsonMS %>
</div>
<input type="hidden" id="MetaSessionHdn" value="<%=jsonMS %>"/>
<div id="AppEngaged" title="Por favor espere..." >
        <table style="width: 100%; height: 100%;">            
            <tr><td align="center"><div id="AppEngaged_Icon"></div></td></tr>
            <tr><td align="center"><div id="AppEngaged_Msg"></div></td></tr>                    
        </table>
</div>
    
    <%@include file="indexPoderes/AsistenteDeCopiado.html" %>
</body>
</html>
