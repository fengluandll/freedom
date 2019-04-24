var PGModule = null;
var MetaSession = null;
var htmlPoderes = new Array();

$(document).ready(function() {
	
	try{
		PGModule = new ClassPoderGeneral();		
	}catch(e){
		alert(e);
	}
	
	$("#body").show();
	//PARA IMPRIMIR PODERES	
	if($(parent.document).find("#iFrame_PG").contents().find('body').html() != undefined &&
	   $(parent.document).find("#iFrame_PE").contents().find('body').html() != undefined &&
	   $(parent.document).find("#iFrame_CP").contents().find('body').html() != undefined &&
	   $(parent.document).find("#iFrame_ER").contents().find('body').html() != undefined){
		 var action = 'cargarHtmlConsultaPoderes';		 

		 //Obtengo el html de la pestaña de Poderes
		 htmlPoderes = getHtmlToPrint();    
		 
		 //Enviar html de Poderes via ajax al Servlet para guardar html en sesion
		 $.ajax({
		        dataType: 'json',
		        success: function(){        	
		        },
		        complete: function(){		        	
		        },
		        error: function(){
		        	
		        },
		        url: '/DerechoCorporativo/Abc_PoderesGenerales',
		        data: {action:action, htmlPG:htmlPoderes[0], htmlPE:htmlPoderes[1], htmlCP:htmlPoderes[2], htmlER:htmlPoderes[3]},
		        type: 'POST'
		    });	   		
	}		 	 
});

var ClassPoderGeneral = function() {
	var _this = this;

	var txtPGQuery = $("#txtPGQuery");	
				
	var getSemaforoRP = function(bean) {
		if (!bean.ind_requiere_proto)
			return 'Img_Semaforo_gray';

		if (bean.des_escritura == null || bean.num_documentum_instr == null
				|| bean.fec_otorgamiento_instr == null || bean.num_licenciado == "-1")
			return 'Img_Semaforo_red';
		else
			return 'Img_Semaforo_green';
	}

	var getSemaforoRPPC = function(bean) {				
		if (!bean.ind_requiere_inscr_rppc || bean.ind_requiere_inscr_rppc == '0' || bean.ind_requiere_inscr_rppc == '' )
    		return 'Img_Semaforo_gray';
    	
    	if (bean.fec_registro == "" || bean.fec_registro == null || bean.num_folio_merc == null || bean.num_folio_merc.trim() == "") 
    		return 'Img_Semaforo_red';
    	else
    		return 'Img_Semaforo_green';					
	}
	
	var getSemaforoStatus = function(bean){		
		if(!bean.ind_aplica_status){
			$("#img_semaforo_status").attr("src", "/DerechoCorporativo/img/semaforo_gray.png");
			return;
		}
		if(bean.ind_aplica_status && bean.ind_status_ac == 6 && bean.id_ent_resp != "-1" && bean.fec_ent != null){						
			$("#img_semaforo_status").attr("src", "/DerechoCorporativo/img/semaforo_green.png");			
		}else
			$("#img_semaforo_status").attr("src", "/DerechoCorporativo/img/semaforo_red.png");
		
	} 

	var consultarEscrituras = function() {
		var palabraABuscar = $(txtPGQuery).val().trim();					
		Request.EscrituraPoderes.consultarEscrituras(
				MetaSession.SessionBean.idCurrentEmpresa,MetaSession.TipoEsc ,
				palabraABuscar, {
					response : function(rsp) {
						var Grid_escrituras = Util.getBeanList(rsp[0]);										
						$("#gridPGmain").jGridContentMVC_UpDate(Grid_escrituras);
					},
					error : function(xhttp, e) {
                    	  sweetAlert('No se pudo realizar la busqueda', 'Error:' + e,'error');
					},
					complete : function() {

					}
				});
	}
	
	var initComponents = function() {

		var msAux = null;
		try {
			var mstr = $("#MetaSession").html();
			msAux = $.parseJSON(mstr);
		} catch (e) {
			var mstr = $("#MetaSessionHdn").val();
			msAux = $.parseJSON(mstr);
		}
		MetaSession = new Object();
		MetaSession.SessionBean = msAux[0];
		MetaSession.Context = msAux[1];
		MetaSession.TipoEsc = msAux[2];

		// Obtener contexto de la Sesion y asignarlo al RequestModel
		Request.EscrituraPoderes.handler = MetaSession.Context+ Request.EscrituraPoderes.handler;
			
		$( txtPGQuery ).keypress(function( event ) {
			  if ( event.which == 13 ) {
				  consultarEscrituras();
			  }
		});
		
		$("#btnPGQuery").click(consultarEscrituras);
		
		$("#btnReset").click(function(){			
			$("#txtPGQuery").val("");
			consultarEscrituras();
		});
						
		switch(MetaSession.TipoEsc){
			case 'PG': 
				initPG();
				break;
			case 'PE': 
				initPE();
				break;
			case 'CP':
				initCP();
				break;
			case 'ER':
				initER();
				break;
		}
			
	}
	
	var showDetail = function(iRow, bean, pty, gridId, td, event) {
		var left = screen.width - ((screen.width - 300) / 1);
	    var top = (screen.height - 700) / 1;  
		var newwindow=open('/DerechoCorporativo/faces/jsp/consulta/poderesGEC/consultaPoderesGenerales/Detalle.jsp?ID_Escritura='+bean.id_ep_pk, 'name', 'height=600,width=650,toolbar=0,menubar=0,location=0,status=0,scrollbars=1,resizable=1,left=' + left + ',top=' + top);

		if (focus) {newfocus();}								
	}
	
	var initPG = function(){
		$(parent.document).find("#msgPoderes").val("ok");
		
		$("#gridPGmain").jGridContentMVC({
					gridId : "gridPGmain",
					noDataMsg : 'no hay registros para mostrar',
					rowTooltip: 'click para ver el detalle',
					storage : null,					
					rowClick : showDetail,
					rowMaxHeight:'400px',
					cellClick :null,
					rowCssA : 'evenCeld',
					rowCssB : 'oddCeld',
					rowFinal : null,
					rowAlign : 'center',
					Encabezados : true,
					HeadCss : "tableHeads",
					Heads : true,
					columnPagination:{pages:[[0,1,2,3,4,5,6,7],[0,1,2,8,9,10,11]],initPage:0,allColumns:true},
					columns : [
							{//0
								HeadText : 'Escritura No.',
								'width' : '5%',
								id : 'des_escritura',
								'align' : 'center',
								setMaxHeight:false,
								cellValue : 'eval',
								eval : function(bean,id,column,iCell,iRow,config,RowCSS) {
									var fondo = 'fondoA';									
									
									if(iRow==0){
										bean.metaFondo = 'fondoA';
										bean.RowCSS = RowCSS;
										return '<div class="metaFondo" metaFondoRow="'+bean.RowCSS+'" metaFondo="fondoAInit">'+bean.des_escritura+'</div>';									
									}if(config.storage[iRow-1].id_ep_pk == bean.id_ep_pk){
										fondo = config.storage[iRow-1].metaFondo;
										fondo = fondo == 'fondoA' ? 'fondoB' : 'fondoA';
										bean.metaFondo = fondo;		
										bean.RowCSS = config.storage[iRow-1].RowCSS;
										return '<div class="metaFondo" metaFondoRow="'+bean.RowCSS+'" metaFondo="'+fondo+'"></div>';
									}									
									else{
										fondo = config.storage[iRow-1].metaFondo;
										fondo = fondo == 'fondoA' ? 'fondoB' : 'fondoA';
										bean.metaFondo = fondo;
										rowCSS = config.storage[iRow-1].RowCSS;
										bean.RowCSS = rowCSS == config.rowCssA ? config.rowCssB : config.rowCssA;
										return '<div class="metaFondo" metaFondoRow="'+bean.RowCSS+'" metaFondo="'+fondo+'Init">'+bean.des_escritura+'</div>';
									}
								}
							},
							
							{//1
								HeadText : 'Fecha',
								'width' : '5%',
								id : 'fec_fecha',
								'align' : 'center'								
							},
							{//2
								HeadText : '&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;Apoderados&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;',
								'width' : '31%',
								id : 'desc_apoderados',
								type : 'action',
								'align' : 'left',
								cellValue : 'eval',
								eval : function(bean,id,column,iCell,iRow,config,RowCSS) {										
									return '<b>'+bean.des_podertipo+'</b><br/>'+bean.desc_apoderados;
									}
							},
							{//3
								HeadText : 'Actos de<br/> Dominio',
								'width' : '10%',
								id : 'desc_actosdominio',
								type : 'action',
								'align' : 'left'
							},
							{//4
								HeadText : 'Actos de<br/> Administraci\u00F3n',
								'width' : '10%',
								id : 'desc_actosadmon',
								type : 'action',
								'align' : 'left'
							},
							{//5
								HeadText : 'T\u00EDtulos de<br/> Cr\u00E9dito',
								'width' : '10%',
								id : 'desc_tituloscredito',
								type : 'action',
								'align' : 'left'
							},
							{//6
								HeadText : 'Pleitos y<br/> Cobranzas',
								'width' : '10%',
								id : 'desc_pleitoscobranza',
								type : 'action',
								'align' : 'left'
							},
//							desc_poder_especial
							{//7
								HeadText : 'Poder <br/>Especial',
								'width' : '10%',
								id : 'desc_poder_especial',
								type : 'action',
								'align' : 'left'
							},
							{//8
								HeadText : 'Vigencia',
								'width' : '5%',
								id : 'fec_vigenciafin',
								type : 'action',
								'align' : 'left',
								cellValue : 'eval',
								eval : function(bean,id,column,iCell,iRow,config) {		
									var now = new Date();
									var date = Util.toDate(bean.fec_vigenciafin,'SHORT');
									if(date == null)
										return "";
									if(date.getTime() < now.getTime())
										return '<label style="color:red">'+bean.fec_vigenciafin+'</label>';
									else
										return bean.fec_vigenciafin;															
									
								}},
							{//9
								HeadText : 'Esc.',
								'width' : '3%',
								id : 'C8',
								type : 'action',
								'align' : 'center',
								cellValue : 'eval',
								eval : function(bean,id,column,iCell,iRow,config) {																								
									if(iRow > 0 && config.storage[iRow-1].id_ep_pk == bean.id_ep_pk){										
										return '';																				
									}
									else{										
										var sem = getSemaforoRP(bean);
										return '<div class="' + sem	+ '"></div>';
									}
								}},
							{//10
								HeadText : 'RPPC',
								'width' : '3%',
								id : 'C4',
								type : 'action',
								'align' : 'center',
								cellValue : 'eval',
								eval : function(bean,id,column,iCell,iRow,config) {																									
									if(iRow > 0 && config.storage[iRow-1].id_ep_pk == bean.id_ep_pk){										
										return '';					
									}
									else{
										var sem = getSemaforoRPPC(bean);
										return '<div class="' + sem	+ '"></div>';										
									}
								}
							},
							
							{//11
								HeadText : 'Detalle',
								'width' : '3%',
								id : 'detalle',
								type : 'action',
								'align' : 'center',
								cellValue : 'eval',
								eval : function(bean,id,column,iCell,iRow,config) {																																
									if(iRow > 0 && config.storage[iRow-1].id_ep_pk == bean.id_ep_pk){										
										return '';				
									}
									else{
										return '<div class="Img_Icon_details"></div>';											
									}
								}
							} ],
					tblCssClass : "",
					tblStyle : "width:100%",
					cellpadding : '3',
					cellspacing : '0',
					border : '0',
					afterDrawing : function(config) {
						$(".metaFondo").each(function(i,e){
							var metaFondo = $(e).attr('metaFondo');
							var parent = $(e).parent();
							$(parent).addClass(metaFondo);
							var metaFondoRow = $(e).attr('metaFondoRow');
							var row = $(parent).parent();
							$(row).removeClass(config.rowCssA);
							$(row).removeClass(config.rowCssB);
							$(row).addClass( metaFondoRow );
						});
						
					}
				});	
		
		
	}
	
	var initPE = function(){
		$("#gridPGmain").jGridContentMVC({
					gridId : "gridPGmain",
					noDataMsg : 'no hay registros para mostrar',
					rowTooltip: 'click para ver el detalle',
					storage : null,					
					rowClick : showDetail,
					rowMaxHeight:'400px',
					cellClick :null,
					rowCssA : 'evenCeld',
					rowCssB : 'oddCeld',
					rowFinal : null,
					rowAlign : 'center',
					Encabezados : true,
					HeadCss : "tableHeads",
					Heads : true,
					columns : [
							{
								HeadText : 'Escritura No.',
								'width' : '5%',
								id : 'des_escritura',
								'align' : 'center',
								setMaxHeight:false,
								cellValue : 'eval',
								eval : function(bean,id,column,iCell,iRow,config,RowCSS) {
									var fondo = 'fondoA';									
									
									if(iRow==0){
										bean.metaFondo = 'fondoA';
										bean.RowCSS = RowCSS;
										return '<div class="metaFondo" metaFondoRow="'+bean.RowCSS+'" metaFondo="fondoAInit">'+bean.des_escritura+'</div>';									
									}if(config.storage[iRow-1].id_ep_pk == bean.id_ep_pk){
										fondo = config.storage[iRow-1].metaFondo;
										bean.metaFondo = fondo;
										bean.RowCSS = config.storage[iRow-1].RowCSS;
										return '<div class="metaFondo" metaFondoRow="'+bean.RowCSS+'" metaFondo="'+fondo+'"></div>';
									}else{
										fondo = config.storage[iRow-1].metaFondo;
										fondo = fondo == 'fondoA' ? 'fondoB' : 'fondoA';
										bean.metaFondo = fondo;
										rowCSS = config.storage[iRow-1].RowCSS;
										bean.RowCSS = rowCSS == config.rowCssA ? config.rowCssB : config.rowCssA;
										return '<div class="metaFondo" metaFondoRow="'+bean.RowCSS+'" metaFondo="'+fondo+'Init">'+bean.des_escritura+'</div>';
									}
								}
							},
							{
								HeadText : 'Fecha',
								'width' : '5%',
								id : 'fec_fecha',
								'align' : 'center'
							},
							{
								HeadText : 'Tipo Poder',
								'width' : '10%',
								id : 'des_podertipo',
								'align' : 'center'
							},
							{
								HeadText : 'Apoderados',
								'width' : '30%',
								id : 'desc_apoderados',
								type : 'action',
								'align' : 'left'
							},
							{
								HeadText : 'Descripci\u00F3n',
								'width' : '30%',
								id : 'des_poder',
								type : 'action',
								'align' : 'left',
								cellValue : 'eval',
								eval : function(bean,id,column,iCell,iRow,config) {				
									return '<b>'+bean.des_podertipo+'</b><br/>'+bean.des_poder;
										
									}},
									{
										HeadText : 'Vigencia',
										'width' : '5%',
										id : 'fec_vigenciafin',
										type : 'action',
										'align' : 'left',
										cellValue : 'eval',
										eval : function(bean,id,column,iCell,iRow,config) {		
											var now = new Date();
											var date = Util.toDate(bean.fec_vigenciafin,'SHORT');
											if(date == null)
												return "";
											if(date.getTime() < now.getTime())
												return '<label style="color:red">'+bean.fec_vigenciafin+'</label>';
											else
												return bean.fec_vigenciafin;															
											
										}},
							{
								HeadText : 'Esc.',
								'width' : '5%',
								id : 'C8',
								type : 'action',
								'align' : 'center',
								cellValue : 'eval',
								eval : function(bean,id,column,iCell,iRow,config) {						
									
									if(iRow > 0 && config.storage[iRow-1].id_ep_pk == bean.id_ep_pk)										
										return '';
									else{
										var sem = getSemaforoRP(bean);
										return '<div class="' + sem	+ '"></div>';		
									}								
									
								}},
							{
								HeadText : 'RPPC',
								'width' : '5%',
								id : 'C4',
								type : 'action',
								'align' : 'center',
								cellValue : 'eval',
								eval : function(bean,id,column,iCell,iRow,config) {							
									
									if(iRow > 0 && config.storage[iRow-1].id_ep_pk == bean.id_ep_pk)										
										return '';
									else{
										var sem = getSemaforoRPPC(bean);
										return '<div class="' + sem	+ '"></div>';		
									}	
									
								}
							},
							
							{
								HeadText : 'Detalle',
								'width' : '5%',
								id : 'detalle',
								type : 'action',
								'align' : 'center',
								cellValue : 'eval',
								eval : function(bean,id,column,iCell,iRow,config) {							
									
									if(iRow > 0 && config.storage[iRow-1].id_ep_pk == bean.id_ep_pk)										
										return '';
									else										
										return '<div class="Img_Icon_details"></div>';								
									
								}
							} ],
					tblCssClass : "",
					tblStyle : "width:100%",
					cellpadding : '3',
					cellspacing : '0',
					border : '0',
					afterDrawing : function(config) {
						$(".metaFondo").each(function(i,e){
							var metaFondo = $(e).attr('metaFondo');
							var parent = $(e).parent();
							$(parent).addClass(metaFondo);
							var metaFondoRow = $(e).attr('metaFondoRow');
							var row = $(parent).parent();
							$(row).removeClass(config.rowCssA);
							$(row).removeClass(config.rowCssB);
							$(row).addClass( metaFondoRow );
						});
					}
				});		
	}
	
	var initCP = function(){
		$("#gridPGmain").jGridContentMVC({
					gridId : "gridPGmain",
					noDataMsg : 'no hay registros para mostrar',
					rowTooltip: 'click para ver el detalle',
					storage : null,					
					rowClick : showDetail,
					rowMaxHeight:'400px',
					cellClick :null,
					rowCssA : 'evenCeld',
					rowCssB : 'oddCeld',
					rowFinal : null,
					rowAlign : 'center',
					Encabezados : true,
					HeadCss : "tableHeads",
					Heads : true,
					columns : [
							
							{
								HeadText : 'Fecha',
								'width' : '5%',
								id : 'fec_fecha',
								'align' : 'center'
							},
							{
								HeadText : 'Tipo Poder',
								'width' : '10%',
								id : 'des_podertipo',
								'align' : 'center'
							},
							{
								HeadText : 'Apoderados',
								'width' : '40%',
								id : 'desc_apoderados',
								type : 'action',
								'align' : 'left'
							},
							{
								HeadText : 'Descripci\u00F3n',
								'width' : '40%',
								id : 'des_poder',
								type : 'action',
								'align' : 'left',
								cellValue : 'eval',
								eval : function(bean,id,column,iCell,iRow,config) {				
									return '<b>'+bean.des_podertipo+'</b><br/>'+bean.des_poder;
										
									}},
									{//8
										HeadText : 'Vigencia',
										'width' : '5%',
										id : 'fec_vigenciafin',
										type : 'action',
										'align' : 'left',
										cellValue : 'eval',
										eval : function(bean,id,column,iCell,iRow,config) {		
											var now = new Date();
											var date = Util.toDate(bean.fec_vigenciafin,'SHORT');
											if(date == null)
												return "";
											if(date.getTime() < now.getTime())
												return '<label style="color:red">'+bean.fec_vigenciafin+'</label>';
											else
												return bean.fec_vigenciafin;															
											
										}},
							
							{
								HeadText : 'Detalle',
								'width' : '5%',
								id : 'detalle',
								type : 'action',
								'align' : 'center',
								cellValue : 'eval',
								eval : function(bean,id,column,iCell,iRow,config) {							
									
									if(iRow > 0 && config.storage[iRow-1].id_ep_pk == bean.id_ep_pk)										
										return '';
									else										
										return '<div class="Img_Icon_details"></div>';								
									
								}
							} ],
					tblCssClass : "",
					tblStyle : "width:100%",
					cellpadding : '3',
					cellspacing : '0',
					border : '0',
					afterDrawing : function() {

					}
				});		
	}
	
	var initER = function(){
		$("#gridPGmain").jGridContentMVC({
					gridId : "gridPGmain",
					noDataMsg : 'no hay registros para mostrar',
					rowTooltip: 'click para ver el detalle',
					storage : null,					
					rowClick : showDetail,
					rowMaxHeight:'400px',
					cellClick :null,
					rowCssA : 'evenCeld',
					rowCssB : 'oddCeld',
					rowFinal : null,
					rowAlign : 'center',
					Encabezados : true,
					HeadCss : "tableHeads",
					Heads : true,
					columns : [
							{
								HeadText : 'Escritura No.',
								'width' : '20%',
								id : 'des_escritura',
								'align' : 'center',
								setMaxHeight:false,
								cellValue : 'eval',
								eval : function(bean,id,column,iCell,iRow,config,RowCSS) {
									var fondo = 'fondoA';									
									
									if(iRow==0){
										bean.metaFondo = 'fondoA';
										bean.RowCSS = RowCSS;
										return '<div class="metaFondo" metaFondoRow="'+bean.RowCSS+'" metaFondo="fondoAInit">'+bean.des_escritura+'</div>';									
									}if(config.storage[iRow-1].id_ep_pk == bean.id_ep_pk){
										fondo = config.storage[iRow-1].metaFondo;
										bean.metaFondo = fondo;
										bean.RowCSS = config.storage[iRow-1].RowCSS;
										return '<div class="metaFondo" metaFondoRow="'+bean.RowCSS+'" metaFondo="'+fondo+'"></div>';
									}else{
										fondo = config.storage[iRow-1].metaFondo;
										fondo = fondo == 'fondoA' ? 'fondoB' : 'fondoA';
										bean.metaFondo = fondo;
										rowCSS = config.storage[iRow-1].RowCSS;
										bean.RowCSS = rowCSS == config.rowCssA ? config.rowCssB : config.rowCssA;
										return '<div class="metaFondo" metaFondoRow="'+bean.RowCSS+'" metaFondo="'+fondo+'Init">'+bean.des_escritura+'</div>';
									}
								}
							},   	
							
							{
								HeadText : 'Fecha',
								'width' : '20%',
								id : 'fec_fecha',
								'align' : 'center'
							},
							{
								HeadText : 'Tipo de reuni&oacute;n',
								'width' : '20%',
								id : 'ind_delegado_por',
								type : 'action',
								'align' : 'center',
								cellValue : 'eval',
								eval : function(bean,id,column,iCell,iRow,config) {										
									return bean.delegado_por;																													
								}
							},			
							{
								HeadText : 'Esc.',
								'width' : '15%',
								id : 'C8',
								type : 'action',
								'align' : 'center',
								cellValue : 'eval',
								eval : function(bean,id,column,iCell,iRow,config) {						
									
									if(iRow > 0 && config.storage[iRow-1].id_ep_pk == bean.id_ep_pk)										
										return '';
									else{
										var sem = getSemaforoRP(bean);
										return '<div class="' + sem	+ '"></div>';		
									}								
									
								}},
							{
								HeadText : 'RPPC',
								'width' : '15%',
								id : 'C4',
								type : 'action',
								'align' : 'center',
								cellValue : 'eval',
								eval : function(bean,id,column,iCell,iRow,config) {							
									
									if(iRow > 0 && config.storage[iRow-1].id_ep_pk == bean.id_ep_pk)										
										return '';
									else{
										var sem = getSemaforoRPPC(bean);
										return '<div class="' + sem	+ '"></div>';		
									}	
									
								}
							},
							{
								HeadText : 'Detalle',
								'width' : '10%',
								id : 'detalle',
								type : 'action',
								'align' : 'center',
								cellValue : 'eval',
								eval : function(bean,id,column,iCell,iRow,config) {							
									
									if(iRow > 0 && config.storage[iRow-1].id_ep_pk == bean.id_ep_pk)										
										return '';
									else										
										return '<div class="Img_Icon_details"></div>';								
									
								}
							} ],
					tblCssClass : "",
					tblStyle : "width:100%",
					cellpadding : '3',
					cellspacing : '0',
					border : '0',
					afterDrawing : function() {

					}
				});		
	}
		
	initComponents();
}

function getHtmlToPrint(){
	
	var htmlPoderes = new Array();
	//Se ajusta el tamaño de la tabla de Poderes para que quepa en la impresion 
	 $(parent.document).find("#iFrame_PG").contents().find('#TblgridPGmain').css('width','100%');
	 $(parent.document).find("#iFrame_PE").contents().find('#TblgridPGmain').css('width','100%');
	 $(parent.document).find("#iFrame_CP").contents().find('#TblgridPGmain').css('width','100%');
	 $(parent.document).find("#iFrame_ER").contents().find('#TblgridPGmain').css('width','100%');
	
	 //Se oculta la seccion de busqueda de escrituras para la impresion 
	 $(parent.document).find("#iFrame_PG").contents().find('#filaBuscarImprimir').hide();
	 $(parent.document).find("#iFrame_PE").contents().find('#filaBuscarImprimir').hide();
	 $(parent.document).find("#iFrame_CP").contents().find('#filaBuscarImprimir').hide();
	 $(parent.document).find("#iFrame_ER").contents().find('#filaBuscarImprimir').hide();
	 
	 //Obtener html de los iFrames de poderes
	 
	var htmlPG = "";     	    
	if($(parent.document).find("#iFrame_PG").contents().find('body').html() != undefined){
		htmlPG = $(parent.document).find("#iFrame_PG").contents().find('body').html();
	    htmlPG = htmlPG.replace(/%/g,"percent");
	    htmlPG = htmlPG.replace(/\+/g,"plusssign");	    
	    htmlPG = encodeURIComponent(htmlPG);
	    htmlPG = htmlPG.replace(/%E2%97%8F%20%09/g,"bullet");	    		    	    
	    htmlPG = decodeURIComponent(htmlPG);
	}
	 
	var htmlPE = "";
	 if($(parent.document).find("#iFrame_PE").contents().find('body').html() != undefined){
	 	htmlPE = $(parent.document).find("#iFrame_PE").contents().find('body').html();
	 	htmlPE = htmlPE.replace(/%/g,"percent");
	 	htmlPE = htmlPE.replace(/\+/g,"plusssign");
	 	htmlPE = encodeURIComponent(htmlPE);
	 	htmlPE = htmlPE.replace(/%E2%97%8F%20%09/g,"bullet");
		htmlPE = decodeURIComponent(htmlPE);
	 }
	 
	 var htmlCP = "";
	 if($(parent.document).find("#iFrame_CP").contents().find('body').html() != undefined){
	 	htmlCP = $(parent.document).find("#iFrame_CP").contents().find('body').html();
	 	htmlCP = htmlCP.replace(/%/g,"percent");
	 	htmlCP = htmlCP.replace(/\+/g,"plusssign"); 	 		    
	 	htmlCP = encodeURIComponent(htmlCP);
	 	htmlCP = htmlCP.replace(/%E2%97%8F%20%09/g,"bullet");
		htmlCP = decodeURIComponent(htmlCP);
		}
	 
	 var htmlER = "";
	 if($(parent.document).find("#iFrame_ER").contents().find('body').html() != undefined){
	 	htmlER = $(parent.document).find("#iFrame_ER").contents().find('body').html();
	 	htmlER = htmlER.replace(/%/g,"percent");
	 	htmlER = htmlER.replace(/\+/g,"plusssign"); 
	 	htmlER = encodeURIComponent(htmlER);
	 	htmlER = htmlER.replace(/%E2%97%8F%20%09/g,"bullet");
	 	htmlER = decodeURIComponent(htmlER);
		}
	 
	 htmlPoderes.push(htmlPG, htmlPE, htmlCP, htmlER);
	 	    	    
	 //Se regresa la tabla de Poderes a su tamaño original 
	 $(parent.document).find("#iFrame_PG").contents().find('#TblgridPGmain').css('width','100%');
	 $(parent.document).find("#iFrame_PE").contents().find('#TblgridPGmain').css('width','100%');
	 $(parent.document).find("#iFrame_CP").contents().find('#TblgridPGmain').css('width','100%');
	 $(parent.document).find("#iFrame_ER").contents().find('#TblgridPGmain').css('width','100%');
	
	 //Se devuelve la seccion de busqueda de escrituras para la impresion
	 $(parent.document).find("#iFrame_PG").contents().find('#filaBuscarImprimir').show();
	 $(parent.document).find("#iFrame_PE").contents().find('#filaBuscarImprimir').show();
	 $(parent.document).find("#iFrame_CP").contents().find('#filaBuscarImprimir').show();
	 $(parent.document).find("#iFrame_ER").contents().find('#filaBuscarImprimir').show();
	 
	 return htmlPoderes;
		
}