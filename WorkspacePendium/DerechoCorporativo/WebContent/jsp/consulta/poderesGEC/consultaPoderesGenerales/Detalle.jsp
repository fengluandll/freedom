<%@page import="mx.com.televisa.derechocorporativo.daos.MngDataPoderes"%>
<%@page import="mx.com.televisa.derechocorporativo.bean.GenericDataBean"%>
<%@page import="mx.com.televisa.derechocorporativo.bean.GenericBean"%>
<%@page import="mx.com.televisa.derechocorporativo.bean.SessionBean"%>
<%@page import="mx.com.televisa.derechocorporativo.util.FacesUtils"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="com.google.gson.GsonBuilder"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<%	
	String nonCache = true ? "" : "?lastversion=" + new java.util.Random().nextInt(); 
	List<Object> initArgs = new ArrayList();	
	List<Object> metaSession = new ArrayList();
	Gson gson = new Gson();
	String idSeccion = request.getParameter("idSeccion");
	int ID_Esc = Integer.parseInt(request.getParameter("ID_Escritura"));
	SessionBean sessionBean = (SessionBean)session.getAttribute("sessionBean");	 	
	metaSession.add(sessionBean);	
	metaSession.add(request.getContextPath());
    	
	//******** Carga del cat�logo "Delegado Por" con id:49 :0
		initArgs.add( MngDataPoderes.queryCATALOGOS(49));
	
	//******** Carga del cat�logo "Notario" con id:12 :1
		initArgs.add( MngDataPoderes.queryCATALOGOS(12));
	
	//****** Carga del cat�logo "Notario" con id:13 :2
		initArgs.add( MngDataPoderes.queryCATALOGOS(13));
		
	//******** Carga del cat�logo "Texto Poderes" :3
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
			
	//******** Carga del cat�logo "Grupo de Apoderados" con id:45 :6
		initArgs.add( MngDataPoderes.queryCATALOGOS(45));
	
		//****** Carga del cat�logo "Notario" con id:13 :7
				initArgs.add( MngDataPoderes.queryCATALOGOS(56));
				//****** Carga del cat�logo "Notario" con id:13 :7
				initArgs.add( MngDataPoderes.queryCATALOGOS(59));
				
				//****** Carga cabecera Escritura
				GenericDataBean head = MngDataPoderes.queryESCRITURA_PODER(ID_Esc);
 				metaSession.add( head );
								
				//****** Carga detalle Escritura				
				List<Object> details = new ArrayList<Object>();
		         
				GenericDataBean op = null;
		        details.add(MngDataPoderes.queryDOCUMENTUMS(ID_Esc));
		        if(head.get(0).getProperty("ind_tipo_escritura").equals("PG"))
		        	op = MngDataPoderes.queryOTORGA_PODER_PG(ID_Esc);
		        else
		        	op = MngDataPoderes.queryOTORGA_PODER(ID_Esc);
		        
		        //Revocar
		       try{
			        for(int i=0; i < op.beans.size(); i++){
			        	GenericBean<Object> bean = op.get(i);
			        	int idpoder = (int)Double.parseDouble( bean.getProperty("id_opoder_ep_pk").toString() );
			        	GenericDataBean list = MngDataPoderes.queryAPODERADO(idpoder);        	
			        	bean.setProperty("Apoderados", list);	        	  
			        	GenericDataBean listFac = MngDataPoderes.queryFACULTADES(idpoder);       	
			        	bean.setProperty("Facultades", listFac);
			        	for(int f=0;f<listFac.beans.size();f++){
			        		GenericBean<Object> fac = listFac.get(f);
			        		if((int)Double.parseDouble(fac.getProperty("MANCOMUNADO").toString())!=1)
			        			continue;
			        		int idFac = (int)Double.parseDouble(fac.getProperty("id_fac_ep_pk").toString());
			        		GenericDataBean listMancomunados = MngDataPoderes.queryAPODERADO(idFac, 1); 
			        		fac.setProperty("listMancomunados",listMancomunados);
			        	}
			        	
			        }
		        
		        details.add(op);
		        GenericDataBean rev = MngDataPoderes.queryREVOCA(ID_Esc);
		        details.add(rev);
		       }
		       catch(Exception ex){
					ex.printStackTrace();
		       }
		        
		       metaSession.add( details);
		         String jsonMS=gson.toJson(metaSession);
%>

<html>
<head>
	<meta content="text/html" http-equiv="content-type" charset="utf-8"></meta>
    <title>Detalle</title>
    <link href="/DerechoCorporativo/css/webCore.css<%=nonCache %>" rel="stylesheet" type="text/css" />
    <link href="/DerechoCorporativo/css/jquery-ui-css/pendium/jquery-ui.css" rel="stylesheet" type="text/css" />
    <script src="/DerechoCorporativo/js/jquery/jquery-2.1.4.min.js" type="text/javascript"></script>
    
    <script src="/DerechoCorporativo/css/jquery-ui-css/pendium/jquery-ui.js" type="text/javascript"></script>
            
    <link href="/DerechoCorporativo/css/jquery-ui-css/blue-moon/GridSort.css" rel="stylesheet" type="text/css" />
    <script src="/DerechoCorporativo/js/jquery/globalize.js" type="text/javascript"></script>
    <script src="/DerechoCorporativo/js/jquery/plugins/jGridContentMVC-1.0.2.js<%=nonCache %>" type="text/javascript"></script>
    <script src="/DerechoCorporativo/js/jquery/plugins/jquery.tablesorter.js<%=nonCache %>" type="text/javascript"></script>
    <script src="/DerechoCorporativo/js/jquery/plugins/jquery.json-2.2.min.js<%=nonCache %>" type="text/javascript"></script>
    <script src="/DerechoCorporativo/js/jquery/plugins/urlEncode.js<%=nonCache %>" type="text/javascript"></script>
    
    <script src="/DerechoCorporativo/js/jsCal/jquery.mask.js<%=nonCache %>" type="text/javascript"></script>
    
    <script src="/DerechoCorporativo/js/jquery/WebKernel/Util.js<%=nonCache %>" type="text/javascript"></script>
    <script src="/DerechoCorporativo/js/jquery/WebKernel/webKernel.js<%=nonCache %>" type="text/javascript"></script>
    <script src="/DerechoCorporativo/js/jquery/WebKernel/RequestModel.js<%=nonCache %>" type="text/javascript"></script>
    
    <link href="consultaPG.css<%=nonCache %>" rel="stylesheet" type="text/css" />
    <script src="RequestModel.js<%=nonCache %>" type="text/javascript"></script>
    <script src="Detalle.js<%=nonCache %>" type="text/javascript"></script>
        
    <script src="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.min.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.css">
    		
	 <script type="text/javascript">
	 var printDetalle = function(){
		 $("#print").hide();
		 var action = 'cargarHtmlDetallePoderes'; 
		//Obtener html de los iFrames de poderes
		 var htmlDetalle = $('body').html();
		 $("#print").show();
		//Enviar html de Poderes via ajax al Servlet para guardar html en sesion
			$.ajax({
		        dataType: 'json',
		        success: function(){
		        	
		        },
		        complete: function(){
		        	location.href = 'detallePrint.jsp';		        	
		        },
		        error: function(){
		        	
		        },
		        url: '/DerechoCorporativo/Abc_PoderesGenerales',
		        data: {action:action, htmlDetalle:$.URLEncode(htmlDetalle)},
		        type: 'POST'
		    });    
   	     }
	 
	</script>	
	   
</head>
<body>

<div id="divPGForm" style="width:100%;">
		<div style="width:95%;text-align:right">
			<a id="print" onclick="printDetalle()" style="text-decoration:none;color:#2B385D;cursor:pointer;">Versi�n Imprimible</a>
		</div>
		<h3 style="width:100%" align="center"><b><%=FacesUtils.getSessionBean().getNomEmpresa()%></b></h3>
						
		<input type="hidden" field="divPGForm_TypeReg" dataField="ind_tipo_escritura" controller="hdn" class="divPGForm" value="" showReview="false" />
       	<div id="divSeccH1" class="divSeccH showSecc showSeccER showSeccPG showSeccPE showSeccCP" toggleContent="false" style="height:30px; padding-top:5px">
       		<label id="divPGForm_title"></label>        		       		       		       		
       	</div>
       	<div id="divSeccH1Content" class="divSeccHContent showSecc showSeccER showSeccPG showSeccPE">
       		
       		<table class="tablaCaramel" width="100%">
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
       				<td><input type="text" dataField="fec_hora" controller="text" class="divPGForm" onkeyup="javascript:getMascaraHora(this);" size='8'></input></td>
       			</tr>
       		</table>
       	</div>
       	
			<!--Para la seccion de Carta Poder -->
       		<div id="divSeccH1CartaPoderContent" class="divSeccHContent showSecc showSeccCP">
       		
       		<table class="tablaCaramel" width="100%">
       			<tr>
       				<td style="width:30%">Mediante</td>
       				<td>
       				<select keyValue="ID_CATALOGO_VALOR" viewValue="NOM_CAT_VAL" dataField="ind_delegado_por" controller="cmb" embeddedData="true" defaultValue="0" defaultText="Seleccione..." class="divPGForm">
       					<%=gson.toJson(initArgs.get(0)) %>
       				</select>
       				</td>
       			</tr>
       			<tr>
       				<td>Fecha</td>
       				<td><input type="text" dataField="fec_fecha" controller="calendar" class="divPGForm"></input></td>
       			</tr>       			
       		</table>
       	</div>
       	
       	<form id="divPGForm" action="/Abc_PoderesGenerales" method="post">
       	<div id="divSeccA" class="divSeccHead showSecc showSeccER showSeccPG showSeccPE" toggleContent="false">
       		Instrumento 
       	</div>
       	<div id="divSeccAContent" class="divSeccContent showSecc showSeccER showSeccPG showSeccPE">
       		
       		<table class="tablaCaramel" width="100%">       			
       			<tr style="display:none">
       				<td style="width:30%">Requiere Protocolizaci&oacute;n:
       				</td>
       				<td>
       					<input type="checkbox" name="PGForm_ind_requiere_proto" dataField="ind_requiere_proto" toggleDiv="#divPGForm_RP" defaultShow="false" controller="check" class="divPGForm"></input>
       				</td>
       			</tr>
       			<tr style="display:none">
       				<td>Requiere Inscripci&oacute;n RPPC:</td>
       				<td><input type="checkbox" name="PGForm_ind_requiere_inscr_rppc" dataField="ind_requiere_inscr_rppc" toggleDiv="#divPGForm_RPPC" defaultShow="false" controller="check" class="divPGForm"></input></td>
       			</tr>
       			
       		</table>
       			
       			<table id="divPGForm_RP" width="100%" class="tablaCaramel" style="display:none">
       			<tr>
       				<td style="width:30%">Escritura No.</td>
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
       				<td style="width:30%">Licenciado:</td>
       				<td><select field="PGForm_num_licenciado" dataField="num_licenciado" controller="cmb" keyValue="ID_CATALOGO_VALOR" viewValue="NOM_CAT_VAL" embeddedData="true" defaultValue="-1" defaultText="Seleccione..." class="divPGForm" eChange="eChange_num_licenciado">
       					<%=gson.toJson(initArgs.get(1)) %>
       				</select></td>
       			</tr>
       			<tr>
       				<td>Notario P&uacute;blico:</td>
       				<td><input type="text" field="PGForm_nom_notario_publico" dataField="nom_notario_publico" controller="text" eEnterKeyPress="eEnterKeyPress_notario_publico" class="divPGForm" reviewDataEmbedded="true"></input></td>
       			</tr>
       			<tr>
       				<td>De:</td>
       				<td><select field="PGForm_num_de" dataField="num_de" controller="cmb" keyValue="val_cat_val" viewValue="nom_cat_val" embeddedData="true" defaultValue="-1" defaultText="N/D" class="divPGForm"  reviewDataEmbedded="true">       					
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
       			
       			
       			<table id="divPGForm_RPPC" width="100%" class="tablaCaramel" style="display:none">
       			<tr>
       				<td style="width:30%">Inscrita en el Registro P&uacute;blico de Comercio de:
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
       	
       			<table class="tablaCaramel" width="100%">
       			<tr>
       				<td style="width:30%">Carta Poder Digitalizada:</td>
       				<td><div dataField="num_documentum_instr" controller="doc" class="divPGForm"></div></td>
       			</tr>
       			</table>

       	</div>
       	       	    
       	
       	
       	<div id="divSeccB" class="divSeccHead showSecc showSeccER showSeccPG showSeccPE showSeccCP" toggleContent="true">
       		Referencia Documentum 
       	</div>
       	<div id="divSeccBContent" class="divSeccContent showSecc showSeccER showSeccPG showSeccPE showSeccCP" style="display:none">   
       		<table class="tablaCaramel" width="100%">
       			<tr><td style="width:30%">Solicitud:</td><td><div field="divPGForm_id_sol_doc" dataField="id_sol_doc" controller="doc" class="divPGForm"></div></td></tr>
				<tr><td>Solicitado por:</td><td><select field="PGFormdes_sol_resp" dataField="id_sol_resp"  dataFieldDesc="des_sol_resp" controller="cmb" keyValue="ID_CATALOGO_VALOR" viewValue="VAL_CAT_VAL" embeddedData="true" defaultValue="-1" defaultText="Seleccione..." class="divPGForm"><%=gson.toJson(initArgs.get(7)) %></select></td></tr>
				<tr><td>Fecha de Solicitud:</td><td><input type="text" dataField="fec_sol" controller="calendar" class="divPGForm"></input></td></tr>
				<tr><td>Fecha de Recibido:</td><td><input type="text" dataField="fec_sol_rec" controller="calendar" class="divPGForm"></input></td></tr>
				<tr><td>Folio:</td><td><input type="text" dataField="des_sol_folio" controller="text" class="divPGForm"></input></td></tr>
				<tr><td>Documento de Entrega:</td><td><div field="divPGForm_id_ent_doc" dataField="id_ent_doc" controller="doc" class="divPGForm"></div></td></tr>
				<tr><td>Fecha de Documento:</td><td><input type="text" dataField="fec_ent_doc" controller="calendar" class="divPGForm"></input></td></tr>
				<tr><td>Fecha de Recibido:</td><td><input type="text" dataField="fec_ent_rec" controller="calendar" class="divPGForm"></input></td></tr>       			
       		</table>       
       		
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
       				<td style="width:10%"></td>       				
       			</tr>
       			<tr>
       				<td></td>
       				<td colspan="2"></td>       				
       			</tr>
       			<tr>
       				<td colspan="2">
       					
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
       					<div id="PGForm_lbl_apoderados_revocados" dataField="RevocadosLeyenda" controller="label" class="divPGForm"></div>
       				</td>
       				<td>
       				
       				</td>	
       			</tr>
       		</table>
       	</div>
       	<div class="divSeccContent showSecc showSeccER showSeccPG" style="display:none">
       		
       	</div>
       	<div id="divSeccE" class="divSeccHead showSecc showSeccPE" toggleContent="false">
       		Poderes
       	</div>
       	<div id="divSeccEContent" class="divSeccContent showSecc showSeccPE">
       		<table width="100%">
       			<tr>
       				<td style="width:10%">Poder:</td>
       				<td style="width:80%"><div id="PEForm_Grid_poderSelecc"></div></td>
       				<td style="width:10%"></td>       				
       			</tr>
       			<tr>
       				<td></td>
       				<td colspan="2"></td>       				
       			</tr>
       			<tr>
       				<td colspan="2">
       					
       				</td>
       				<td></td>       				
       			</tr>
       			<tr>
       				<td colspan="3">
       					<div id="PEForm_Grid_apoderados" field="CPForm_Grid_apoderados" dataField="Apoderados" controller="grid" class="divPGForm"></div>
       				</td>
       			</tr>
       			<tr>
       				<td colspan="2">
       					<div id="PEForm_lbl_apoderados_revocados" dataField="RevocadosLeyenda" controller="label" class="divPGForm"></div>
       				</td>
       				<td>
       				
       				</td>	
       			</tr>
       		</table>
       	</div>
       	
       	<div id="divSeccF" class="divSeccHead showSecc showSeccCP" toggleContent="false">
       		Poderes
       	</div>
       	<div id="divSeccFContent" class="divSeccContent showSecc showSeccCP">
       		<table width="100%">
       			<tr>
       				<td style="width:10%">Poder:</td>
       				<td style="width:80%"><div id="CPForm_Grid_poderSelecc"></div></td>
       				<td style="width:10%"></td>       				
       			</tr>
       			<tr>
       				<td></td>
       				<td colspan="2"></td>       				
       			</tr>
       			<tr>
       				<td colspan="2">
       					
       				</td>
       				<td></td>       				
       			</tr>
       			<tr>
       				<td colspan="3">
       					<div id="CPForm_Grid_apoderados" field="CPForm_Grid_apoderados" dataField="Apoderados" controller="grid" class="divPGForm" style="width:99%;"></div>
       				</td>
       			</tr>
       			<tr>
       				<td colspan="2">
       					<div id="CPForm_lbl_apoderados_revocados" dataField="RevocadosLeyenda" controller="label" class="divPGForm"></div>
       				</td>
       				<td>
       				
       				</td>	
       			</tr>
       		</table>
       	</div>
       	<div id="divSeccC" class="divSeccHead showSecc showSeccER showSeccPG showSeccPE showSeccCP" toggleContent="true"  defaultShow="false">
       		Status
       	</div>       	
       	<div id="divSeccCContent" class="divSeccContent" style="display:none">   
       		  <table class="tablaCaramel" width="100%">       			       			
	       		<tr>
       				<td height="0" style="width:30%"><div id="DIVC_30_VAL_C150_1">Asunto: </div></td>
       				<td><img id="img_semaforo_status" src="/DerechoCorporativo/img/semaforo_gray.png"></td>
       			</tr>
       		</table>        			
       		<table class="tablaCaramel" width="100%" id="divStatusDetail"  style="display:none">       		       					
       			<tr>
       				<td width="30%">Fecha Programada de Entrega:</td>
       				<td><input type="text" dataField="fec_pe" controller="calendar" class="divPGForm"></input></td>
       			</tr>
       			<tr><td style="font-weight:bold">Redactada:</td><td><input type="checkbox" field="ind_status_ac_1" dataField="ind_status_ac" controller="checkradio" class="divPGForm ind_status_ac" showReview="false"></input></td></tr>
				<tr><td>Responsable:</td><td><select field="PGFormdes_rep_resp" dataField="id_red_resp"  dataFieldDesc="des_rep_resp" controller="cmb" keyValue="ID_CATALOGO_VALOR" viewValue="NOM_CAT_VAL" embeddedData="true" defaultValue="-1" defaultText="Seleccione..." class="divPGForm"><%=gson.toJson(initArgs.get(8)) %></select></td></tr>
				<tr><td>Cumplimiento:</td><td><input type="text" dataField="fec_rep" controller="calendar" class="divPGForm"></input></td></tr>
				<tr><td style="font-weight:bold">Revis&oacute;n Gerente:</td><td><input type="checkbox" field="ind_status_ac_2" dataField="ind_status_ac" controller="checkradio" class="divPGForm ind_status_ac" showReview="false"></input></td></tr>
				<tr><td>Responsable:</td><td><select field="PGFormdes_reg_resp" dataField="id_reg_resp"  dataFieldDesc="des_reg_resp" controller="cmb" keyValue="ID_CATALOGO_VALOR" viewValue="NOM_CAT_VAL" embeddedData="true" defaultValue="-1" defaultText="Seleccione..." class="divPGForm"><%=gson.toJson(initArgs.get(8)) %></select></td></tr>
				<tr><td>Cumplimiento:</td><td><input type="text" dataField="fec_reg" controller="calendar" class="divPGForm"></input></td></tr>
				<tr><td style="font-weight:bold">Correcciones:</td><td><input type="checkbox" field="ind_status_ac_3" dataField="ind_status_ac" controller="checkradio" class="divPGForm ind_status_ac" showReview="false"></input></td></tr>
				<tr><td>Responsable:</td><td><select field="PGFormdes_cor_resp" dataField="id_cor_resp"  dataFieldDesc="des_cor_resp" controller="cmb" keyValue="ID_CATALOGO_VALOR" viewValue="NOM_CAT_VAL" embeddedData="true" defaultValue="-1" defaultText="Seleccione..." class="divPGForm"><%=gson.toJson(initArgs.get(8)) %></select></td></tr>
				<tr><td>Cumplimiento:</td><td><input type="text" dataField="fec_cor" controller="calendar" class="divPGForm"></input></td></tr>
				<tr><td style="font-weight:bold">Aut. Direcci&oacute;n:</td><td><input type="checkbox" field="ind_status_ac_4" dataField="ind_status_ac" controller="checkradio" class="divPGForm ind_status_ac" showReview="false"></input></td></tr>
				<tr><td>Responsable:</td><td><select field="PGFormdes_aut_resp" dataField="id_aut_resp"  dataFieldDesc="des_aut_resp" controller="cmb" keyValue="ID_CATALOGO_VALOR" viewValue="NOM_CAT_VAL" embeddedData="true" defaultValue="-1" defaultText="Seleccione..." class="divPGForm"><%=gson.toJson(initArgs.get(8)) %></select></td></tr>
				<tr><td>Cumplimiento:</td><td><input type="text" dataField="fec_aut" controller="calendar" class="divPGForm"></input></td></tr>
				<tr><td style="font-weight:bold">En Firmas:</td><td><input type="checkbox" field="ind_status_ac_5" dataField="ind_status_ac" controller="checkradio" class="divPGForm ind_status_ac" showReview="false"></input></td></tr>
				<tr><td>Responsable:</td><td><select field="PGFormdes_fir_resp" dataField="id_fir_resp"  dataFieldDesc="des_fir_resp" controller="cmb" keyValue="ID_CATALOGO_VALOR" viewValue="NOM_CAT_VAL" embeddedData="true" defaultValue="-1" defaultText="Seleccione..." class="divPGForm"><%=gson.toJson(initArgs.get(8)) %></select></td></tr>
				<tr><td>Cumplimiento:</td><td><input type="text" dataField="fec_fir" controller="calendar" class="divPGForm"></input></td></tr>
				<tr><td style="font-weight:bold">Entregada:</td><td><input type="checkbox" field="ind_status_ac_6" dataField="ind_status_ac" controller="checkradio" class="divPGForm ind_status_ac" showReview="false"></input></td></tr>
				<tr><td>Responsable:</td><td><select field="PGFormdes_ent_resp" dataField="id_ent_resp"  dataFieldDesc="des_ent_resp" controller="cmb" keyValue="ID_CATALOGO_VALOR" viewValue="NOM_CAT_VAL" embeddedData="true" defaultValue="-1" defaultText="Seleccione..." class="divPGForm"><%=gson.toJson(initArgs.get(8)) %></select></td></tr>
				<tr><td>Cumplimiento:</td><td><input type="text" dataField="fec_ent" controller="calendar" class="divPGForm"></input></td></tr>
       		</table>  
       			     			
       	</div>
       	</form>
       </div> 
       
<div id="MetaSession" style="display:none">	
	<%=jsonMS %>
</div>

<div id="AppEngaged" title="Por favor espere...">
        <table style="width: 100%; height: 100%;">            
            <tr><td align="center"><div id="AppEngaged_Icon"></div></td></tr>
            <tr><td align="center"><div id="AppEngaged_Msg"></div></td></tr>                    
        </table>
    </div>
</body>
</html>