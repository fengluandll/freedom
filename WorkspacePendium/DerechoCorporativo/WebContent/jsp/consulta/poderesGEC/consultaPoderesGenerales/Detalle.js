var PGModule = null;
var MetaSession = null;

$(document).ready(function() {
	PGModule = new ClassPoderGeneral();
	$("#body").fadeIn('fast');
	PGModule.openFormEscritura('review',
			MetaSession.HeadEsc.ind_tipo_escritura, '#div'
					+ MetaSession.HeadEsc.ind_tipo_escritura
					+ 'Main', MetaSession.HeadEsc);
	
	if(MetaSession.HeadEsc.id_sol_doc == null && MetaSession.HeadEsc.id_sol_resp == -1
			&& MetaSession.HeadEsc.fec_sol == null && MetaSession.HeadEsc.fec_sol_rec == null
			&& MetaSession.HeadEsc.des_sol_folio == null && MetaSession.HeadEsc.id_ent_doc == null
			&& MetaSession.HeadEsc.fec_ent_doc == null && MetaSession.HeadEsc.fec_ent_rec == null){
		$("#divSeccBContent").hide();
	}
	else{
		$("#divSeccBContent").show();
	}
	
});

var ClassPoderGeneral = function() {
	var _this = this;
	
	var Animation = false;

	this.engaged = false;
	var Grid_apoderados = new Array();
	var Grid_mancomunados = new Array();
	var Grid_ERmain = new Array();

	var dlgApoderadosOtros = "#PGForm_DlgApoderadosOtros";
	var dlgRevocacion = "#PGForm_DlgRevocacion";
	var dlgCopy = "#PGForm_DlgCopy";

	var loadDetails_Escritura = function(details,WorkBean) {

				var docs = Util.getBeanList(details[0]);
				Grid_apoderados = Util.getBeanList(details[1]);
				var Revocados = Util.getBeanList(details[2]);
				getSemaforoStatus(WorkBean);

				$.each(Grid_apoderados, function(i, bean) {
					if(bean.apoderados==null){
						bean.Apoderados = [];
						return;
					}
					bean.Apoderados = Util.getBeanList(bean.apoderados);
					$.each(bean.Apoderados,function(i,bean){
						if(bean.ind_status==2 && Revocados.length > 0){
								for(var r=0;r<Revocados.length;r++){
									var revo = Revocados[r];
									if(revo.id_apod_ep_fk == bean.id_apod_ep_pk){
										bean.Revocar = revo;
									}
								}							
						}
					});
					
					if (bean.facultades){
						bean.Facultades = Util.getBeanList(bean.facultades);
						for(var f in bean.Facultades){
							var fac = bean.Facultades[f];
							if(fac.mancomunado){
								fac.listMancomunados = Util.getBeanList(fac.listmancomunados);
							}								
						}
					}
					
				});

				$("#PGForm_Grid_Documents").jGridContentMVC_UpDate(docs);
				
				switch(WorkBean.ind_tipo_escritura){
					case 'PG':
						$("#PGForm_Grid_poderSelecc").jGridContentMVC_UpDate(Grid_apoderados);
						$("#PGForm_Grid_apoderados").jGridContentMVC_UpDate(Grid_apoderados);
						break;
					case 'PE':
						$("#PEForm_Grid_poderSelecc").jGridContentMVC_UpDate(Grid_apoderados);
						$("#PEForm_Grid_apoderados").jGridContentMVC_UpDate(Grid_apoderados);
						break;
					case 'CP':
						$("#CPForm_Grid_poderSelecc").jGridContentMVC_UpDate(Grid_apoderados);
						$("#CPForm_Grid_apoderados").jGridContentMVC_UpDate(Grid_apoderados);
						break;
				}		
			}
	
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

	this.openFormEscritura = function(mode, type, workdiv, WorkBean) {

		switch (type) {
		case 'ER': // Revocaciones
			$("#divPGForm_title").html('Escritura de Revocaci&oacute;n');
			
			break;
		case 'PG': // Generales
			$("#divPGForm_title").html('Poder General');
			
			break;
		case 'PE': // Especiales
			$("#divPGForm_title").html('Poder Especial');
			
			break;
		case 'CP': // Carta Poder
			$("#divPGForm_title").html('Carta Poder');			
			break;
		}

		WebForm.cleanBean('divPGForm');
		$("#PGForm_lbl_apoderados_revocados").html('');
		
		$(".showSecc").hide();
		$(".showSecc" + type).show();

		$(workdiv).hide();
		$("#divPGForm").data('MetaWork', {
			WorkDiv : workdiv,
			Type : type,
			Mode : mode,
			WorkBean : WorkBean
		});
		$("#divPGForm").show();
		
		try{
			WebForm.setBeanForReview('divPGForm', WorkBean);
			$("#PGForm_num_licenciado").change();
			loadDetails_Escritura(MetaSession.Detail,WorkBean);
			getSemaforoStatus(WorkBean);
		}catch(e){
			alert(e);
		}
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
		var HeadEsc = Util.getBeanList(msAux[2]);
		MetaSession.HeadEsc = HeadEsc[0];
		MetaSession.Detail = msAux[3];

		// Obtener contexto de la Sesion y asignarlo al RequestModel
		Request.EscrituraPoderes.handler = MetaSession.Context
				+ Request.EscrituraPoderes.handler;
				
		
		$("#divPGForm").data('MetaWork', {
			WorkDiv : '#divPGMain',
			Type : 'PG',
			Mode : 'new',
			WorkBean : MetaSession.HeadEsc
		});
		
		
		initForm();
	}

	var initForm = function() {

		$(".divSeccHead").click(function() {
			if ($(this).attr("toggleContent") == "false")
				return;
			$("#" + this.id + "Content").toggle();
		});

		$("#divPGFormBtnClose").button({
			icon : "ui-icon-close",
			showLabel : false
		}).click(function() {
			$("#divPGForm").hide();
			$($("#divPGForm").data('MetaWork').WorkDiv).show();
		});

		$(".divPGForm").each(function() {

		});		

		WebKernel.newWebForm("divPGForm", "PGModule", {
			ind_requiere_proto : {
				toggleDiv : '#divPGForm_RP'
			},
			ind_requiere_inscr_rppc : {
				toggleDiv : '#divPGForm_RP'
			},
			ind_aplica_status : {
				toggleDiv : '#divPGFormStatus'
			}
		});

		var PGForm_Grid_Documents_default = [ {
			desc_title : 'Solicitud',
			id_documentcve : '',
			fec_rec:'',
			fec_ent:''
		} ];
		
		$("#PGForm_Grid_Documents")
				.jGridContentMVC(
						{
							gridId : "PGForm_Grid_Documents",
							noDataMsg : '',
							storage : PGForm_Grid_Documents_default,
							rowClick : null,
							cellClick : null,
							rowMaxHeight:'400px',
							rowCssA : '',
							rowCssB : '',
							rowFinal : null,
							rowAlign : 'center',
							Encabezados : true,
							showHeadigns:false,
							HeadCss : "HHead",
							Heads : true,
							columns : [
									
									{
										HeadText : 'Titulo',
										'width' : '20%',
										id : 'desc_title',
										type : 'action',
										'align' : 'left',
										cellValue : 'eval',
										eval : function(bean, pty, column,
												iCell, iRow, config) {
											var md = ' gridId="'
													+ config.gridId
													+ '" gridRow="' + iRow
													+ '" ';
											var id = "documentTitle_"
													+ config.gridId + iRow
													+ iCell + "_text";
											var classIDText = "documentTitle_"
													+ config.gridId + "_text";										

											return '<input ' + md + ' id="'
													+ id + '" value="'
													+ bean.desc_title
													+ '" class="' + classIDText
													+ '" type="text" style="display:none"><label class="LblReview">'+bean.desc_title+'</label>';
										}
									},
									{
										HeadText : 'Fecha del documento',
										'width' : '20%',
										id : 'fec_rec',
										type : 'action',
										'align' : 'center',
										cellValue : 'eval',
										eval : function(bean, pty, column,
												iCell, iRow, config) {
											var md = ' gridId="'+ config.gridId+ '" gridRow="' + iRow + '" ';
											var id = "documentFec_"
													+ config.gridId + iRow
													+ iCell + "_text";
											var classIDText = "documentFec_"
													+ config.gridId + "_text";										

											return '<input ' + md + ' id="'
													+ id + '" value="'
													+ (bean.fec_rec == null ? '' : bean.fec_rec) 
													+ '" class="' + classIDText
													+ '" type="text" style="display:none"><label class="LblReview">'+bean.fec_rec+'</label>';
										}
									},
									{
										HeadText : 'Fecha de recibido',
										'width' : '20%',
										id : 'fec_ent',
										type : 'action',
										'align' : 'center',
										cellValue : 'eval',
										eval : function(bean, pty, column,
												iCell, iRow, config) {
											var md = ' gridId="'+ config.gridId+ '" gridRow="' + iRow + '" ';
											var id = "documentFecEnt_"
													+ config.gridId + iRow
													+ iCell + "_text";
											var classIDText = "documentFecEnt_"
													+ config.gridId + "_text";
										
											return '<input ' + md + ' id="'
													+ id + '" value="'
													+ (bean.fec_ent == null ? '' : bean.fec_ent) 
													+ '" class="' + classIDText
													+ '" type="text" style="display:none"><label class="LblReview">'+bean.fec_ent+'</label>';
										}
									},
									{
										HeadText : 'Documento',
										'width' : '40%',
										id : 'id_documentcve',
										type : 'action',
										'align' : 'center',
										cellValue : 'eval',
										eval : function(bean, pty, column,
												iCell, iRow, config) {
											var md = ' gridId="'
													+ config.gridId
													+ '" gridRow="' + iRow
													+ '" ';
											var id = "document_cve_"
													+ config.gridId + iRow
													+ iCell + "_text";
											var classIDText = "document_cve_"
													+ config.gridId + "_text";
											var classIDBtn = "document_cve_"
													+ config.gridId + "_btn";
											return '<div style="width: 385px;"><input '
													+ md
													+ ' id="'
													+ id
													+ '" value="'
													+ (bean.id_documentcve == null ? '' : bean.id_documentcve)
													+ '" class="'
													+ classIDText
													+ '" type="text" style="display:none"><label class="LblReview">'+bean.id_documentcve+'</label><div divfield="'
													+ id
													+ '" style="float: right;" for="'
													+ id
													+ '" class="Img_Icon_Documentum '
													+ classIDBtn
													+ '"></div></div>';
										}
									}

							],
							tblCssClass : "tablaCaramel",
							tblStyle : "width:100%",
							cellpadding : '0',
							cellspacing : '1',
							border : '0',
							afterDrawing : function(config) {
								var classIDText = ".document_cve_"
										+ config.gridId + "_text";
								var classIDBtn = ".document_cve_"
										+ config.gridId + "_btn";
								$(classIDBtn).click(function() {
									getDocumentum($(this).attr('divField'));
								});
								$(classIDText).attr('size', 35).keyup(
										function() {
											getFormatDocumentum(this);
										});

								$(classIDText).blur(
										function() {

											var val = $(this).val();
											var grid = $(this).attr('gridId');
											var iRow = $(this).attr('gridRow');

											var bean = $("#" + grid)
													.jGridContentMVC_Get(iRow);
											bean.id_documentcve = val;

										});

								var classIDTitle = ".documentTitle_"
										+ config.gridId + "_text";
								$(classIDTitle).blur(
										function() {
											var val = $(this).val();
											var grid = $(this).attr('gridId');
											var iRow = $(this).attr('gridRow');

											var bean = $("#" + grid)
													.jGridContentMVC_Get(iRow);
											bean.desc_title = val;
										});
								var classIDDate = ".documentFec_"
									+ config.gridId + "_text";
								$(classIDDate).datepicker({
							        showOn: "button",
							        buttonImage: '/DerechoCorporativo/img/calendar.gif',
							        buttonImageOnly: true,
							        buttonText: "Seleccione",        
							        changeMonth: true,
							        changeYear: true,
							        numberOfMonths: 1,							        
							        dateFormat: "dd/mm/yy",
							        getDate: function (element) {
							            var date;
							            var dateFormat = "dd/mm/yy";
							            try {							            	
							                date = $.datepicker.parseDate(dateFormat, element.value);							               
							            } catch (error) {
							                date = null;
							            }

							            return date;
							        }
							    });
			                    $(classIDDate).keyup(function(){
			                    	getMascaraFecha(this);
			                    });
								$(classIDDate).change(
									function() {										
										var val = $(this).val();
										var grid = $(this).attr('gridId');
										var iRow = $(this).attr('gridRow');

										var bean = $("#" + grid).jGridContentMVC_Get(iRow);										
										bean.fec_rec = val;										
										
									});
								
								var classIDDateEnt = ".documentFecEnt_"
									+ config.gridId + "_text";
								$(classIDDateEnt).datepicker({
							        showOn: "button",
							        buttonImage: '/DerechoCorporativo/img/calendar.gif',
							        buttonImageOnly: true,
							        buttonText: "Seleccione",        
							        changeMonth: true,
							        changeYear: true,
							        numberOfMonths: 1,							        
							        dateFormat: "dd/mm/yy",
							        getDate: function (element) {
							            var date;
							            var dateFormat = "dd/mm/yy";
							            try {							            	
							                date = $.datepicker.parseDate(dateFormat, element.value);							               
							            } catch (error) {
							                date = null;
							            }

							            return date;
							        }
							    });
			                    $(classIDDateEnt).keyup(function(){
			                    	getMascaraFecha(this);
			                    });
								$(classIDDateEnt).change(
									function() {										
										var val = $(this).val();
										var grid = $(this).attr('gridId');
										var iRow = $(this).attr('gridRow');

										var bean = $("#" + grid).jGridContentMVC_Get(iRow);										
										bean.fec_ent = val;										
										
									});
								$(".ui-datepicker-trigger").hide();
							}
						});
		
		initPG();
		initPE();
		initCP();
		
		initDlgPoderes();
		initDlgApoderados();
		initDlgApoderadosOtros();
		initDlgRevocacion();
		initDlgCopy();
	}
	
	var initPG = function(){
		$("#PGForm_Grid_poderSelecc").jGridContentMVC(
				{
					gridId : "PGForm_Grid_poderSelecc",
					noDataMsg : 'ningun poder seleccionado, por favor seleccione un poder',
					storage : Grid_apoderados,
					rowClick : null,
					cellClick : function(iRow, iCell, bean, pty,
							gridId, td, event) {
						switch (pty) {
						case 'del':
							if (_this.engaged)
								return;

							break;
						default:

							break;
						}
					},
					rowCssA : '',
					rowCssB : '',
					rowFinal : null,
					rowAlign : 'center',
					Encabezados : true,
					HeadCss : "",
					Heads : false,
					columns : [ {
						HeadText : 'Poderes',
						'width' : '5%',
						id : 'des_podertipo',
						'align' : 'left'
					}

					],
					tblCssClass : "",
					tblStyle : "width:100%",
					cellpadding : '0',
					cellspacing : '1',
					border : '0',
					afterDrawing : function() {

					}
				});

$("#PGForm_Grid_apoderados").jGridContentMVC(
				{
					gridId : "PGForm_Grid_apoderados",
					noDataMsg : 'ningun poder seleccionado, por favor seleccione un poder',
					storage : Grid_apoderados,					
					rowClick : null,
					cellClick : function(iRow, iCell, bean, pty,
							gridId, td, event) {
						
					},
					rowCssA : 'evenCeld',
					rowCssB : 'oddCeld',
					rowFinal : null,
					rowAlign : 'center',					
					Encabezados : false,
					HeadCss : "tableHeads",
					Heads : true,
					columns : [
							
							{
								HeadText : 'Poder',
								'width' : '5%',
								id : 'des_poder',
								'align' : 'center'
							},
							{
								HeadText : '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Apoderados&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;',
								'width' : '20%',								
								id : 'desc_apoderados',
								type : 'action',
								'align' : 'left',
								cellValue : 'eval',
								eval : function(bean,id,column,iCell,iRow,config) {		
									var apoderados = bean.desc_apoderados;									
									apoderados = apoderados.split("<br /> &#9679;").join("</li><li style='font-size:11px;margin-left:-6px;'>");
									apoderados = apoderados.split("&#9679;").join("<ul style='font-size:0;'><li style='font-size:11px;margin-left:-6px;'>");									
									apoderados = apoderados.split(" 	 ").join("");	
									apoderados = apoderados.split("<br />").join("</li></ul>");																	
									apoderados = apoderados.trim();															
									return apoderados;
								},	
							},
							{
								HeadText : 'Actos de <br/>Dominio',
								'width' : '14%',
								id : 'desc_actosdominio',
								type : 'action',
								'align' : 'left'
							},
							{
								HeadText : 'Actos de <br/>Administraci\u00F3n',
								'width' : '14%',
								id : 'desc_actosadmon',
								type : 'action',
								'align' : 'left'
							},
							{
								HeadText : 'T\u00EDtulos de <br/>Cr\u00E9dito',
								'width' : '14%',
								id : 'desc_tituloscredito',
								type : 'action',
								'align' : 'left'
							},
							{
								HeadText : 'Pleitos y <br/>Cobranzas',
								'width' : '14%',
								id : 'desc_pleitoscobranza',
								type : 'action',
								'align' : 'left'
							},
							{
								HeadText : 'Poder Especial',
								'width' : '14%',
								id : 'desc_poder_especial',
								type : 'action',
								'align' : 'left'
							},
							{
								HeadText : 'Vigencia',
								'width' : '5%',
								id : 'desc_vigencia',
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
									
								}}],
					tblCssClass : "",
					tblStyle : "width:100%",
					cellpadding : '0',
					cellspacing : '1',
					border : '0',
					afterDrawing : function(config) {
																
						
						if ($("#divPGForm").data('MetaWork').WorkBean == null){
							$("#PGForm_lbl_apoderados_revocados").html('');							
							return;
						}
						var apendices = new Array();
						var strA = "";
						var totalRev = 0;
						$.each(config.storage,function(i, bean) {
											if (bean.Apoderados) {
												$(bean.Apoderados).each(
																function(i,apod) {
																	if (parseInt(apod.ind_status) == 2 ) {
																		totalRev++;
																		var exists = false;
																		for ( var i in apendices) {
																			if (apendices[i] == apod.desc_revoca) {
																				exists = true;
																				break;
																			}
																		}
																		if (!exists) {
																			apendices.push(apod.desc_revoca);
																			//strA += "<br />" + apod.desc_revoca;
																		}

																	}
																});
											}
										});

						apendices.sort(function(a, b){
							a = a.replace(/<\/?[^>]+(>|$)/g, "");
							a = a.substring(0,a.indexOf(" "));
							b = b.replace(/<\/?[^>]+(>|$)/g, "");
							b = b.substring(0,b.indexOf(" "));								    
						    return a-b
						});

						for(var i in apendices)
							strA += "<br />" + apendices[i];
						
						var WorkBean = $("#divPGForm").data('MetaWork').WorkBean; // ,{WorkDiv:workdiv,Type:type,Mode:mode,:WorkBean});
						WorkBean.des_revoca = totalRev > 0 ? WorkBean.des_revoca : ''; 
						strA = strA == '' ? WorkBean.des_revoca == null ? ''
								: WorkBean.des_revoca
								: strA;
						$("#PGForm_lbl_apoderados_revocados").html(strA).show();

						WorkBean.des_revoca = strA;

						$(".ApodDetail").click(function() {
							var doc = $(this).attr('docId');
							if(doc=='' || doc == null){
								swal({ title: "Aviso",   
								      text: "El campo DOCUMENTUM de la Escritura se asign\u00F3 vac\u00EDo, Para corregir vuelva a realizar la Revocaci\u00F3n.",   
								      type: "warning",  
								      confirmButtonText: "Ok" });
								return;
							}
							window.open("/DerechoCorporativo/faces/jsp/documentum/waitingPage.jsp?doc="+doc
								     ,null
								     ,"height=600,width=1100,status=yes,toolbar=no,menubar=no,location=no");
						});
						
						

					}
				});
	}
	
	var initPE = function(){
		$("#PEForm_Grid_poderSelecc").jGridContentMVC({ 
    		gridId: "PEForm_Grid_poderSelecc",
            noDataMsg: 'ningun poder seleccionado, por favor seleccione un poder',
            storage: Grid_apoderados,
            rowClick: null,
            cellClick: function (iRow, iCell, bean, pty, gridId, td, event) {
                switch (pty) {
                    case 'del':
                        if (_this.engaged) return;                                          

                        break;
                    default:
                        
                        break;
                }
            },
            rowCssA: '',
            rowCssB: '',
            rowFinal: null,
            rowAlign: 'center',
            Encabezados: true,
            HeadCss: "",
            Heads: false,
            columns: [  		                            
                                       { HeadText: 'Poderes', 'width': '5%', id: 'des_podertipo', 'align': 'left' }         
                                      
                                      ],
            tblCssClass: "",
            tblStyle: "width:100%",
            cellpadding: '0',
            cellspacing: '1',
            border: '0',
            afterDrawing: function () {
                
            }
        });
    	
    	// Variable para almacenar el indice del apoderado seleccionado
    	var apeIndex = 1;
    	$("#PEForm_Grid_apoderados").jGridContentMVC({ 
    		gridId: "PEForm_Grid_apoderados",
            noDataMsg: 'ningun poder seleccionado, por favor seleccione un poder',
            storage: Grid_apoderados,
            rowClick: null,
            
            cellClick: function (iRow, iCell, bean, pty, gridId, td, event) {
                
            },
            rowCssA: 'evenCeld',
            rowCssB: 'oddCeld',
            rowFinal: null,
            rowAlign: 'center',
            Encabezados: true,
            HeadCss: "tableHeads",
            Heads: true,
            columns: [
									  		                              		                              		                            
                                     { HeadText: 'Poder', 'width': '8%', id: 'des_podertipo', type: 'action', 'align': 'left' }
                                     , {
         								HeadText : '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Apoderados&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;',
         								'width' : '20%',								
         								id : 'desc_apoderados',
         								type : 'action',
         								'align' : 'left',
         								cellValue : 'eval',
         								eval : function(bean,id,column,iCell,iRow,config) {		
         									var apoderados = bean.desc_apoderados;									
        									apoderados = apoderados.split("<br /> &#9679;").join("</li><li style='font-size:11px;margin-left:-6px;'>");
        									apoderados = apoderados.split("&#9679;").join("<ul style='font-size:0;'><li style='font-size:11px;margin-left:-6px;'>");									
        									apoderados = apoderados.split(" 	 ").join("");	
        									apoderados = apoderados.split("<br />").join("</li></ul>");
        									apoderados = apoderados.split("<sup style='font-size:8pt;'>").join("<sup style='font-size:8pt;'>");
        									apoderados = apoderados.split("<div style='font-weight: bold;'>null</div>").join("");
        									apoderados = apoderados.trim();
         									return apoderados;
         								}
         							}
  		                            , { HeadText: 'Descripcion', 'width': '15%', id: 'des_poder', type: 'action', 'align': 'left' }                                
                                    
                                    , { HeadText: 'Vigencia', 'width': '5%', id: 'fec_vigenciafin', type: 'action', 'align': 'left' }                                     
                                    
                                    ],
            tblCssClass: "",
            tblStyle: "width:100%",
            cellpadding: '0',
            cellspacing: '1',
            border: '0',
            afterDrawing: function (config) {      
            	            	
        		if ($("#divPGForm").data('MetaWork').WorkBean == null){
					$("#PEForm_lbl_apoderados_revocados").html('');
					return;
				}
				var apendices = new Array();
				var strA = "";
				var totalRev = 0;
				$.each(config.storage,function(i, bean) {
					if (bean.Apoderados) {
						$(bean.Apoderados).each(
										function(i,apod) {
											if (parseInt(apod.ind_status) == 2 ) {
												totalRev++;
												var exists = false;
												for ( var i in apendices) {
													if (apendices[i] == apod.desc_revoca) {
														exists = true;
														break;
													}
												}
												if (!exists) {
													apendices.push(apod.desc_revoca);
													//strA += "<br />" + apod.desc_revoca;
												}

											}
										});
					}
								});
				
				apendices.sort(function(a, b){
					a = a.replace(/<\/?[^>]+(>|$)/g, "");
					a = a.substring(0,a.indexOf(" "));
					b = b.replace(/<\/?[^>]+(>|$)/g, "");
					b = b.substring(0,b.indexOf(" "));								    
				    return a-b
				});
				
				for(var i in apendices)
					strA += "<br />" + apendices[i];
				var WorkBean = $("#divPGForm").data('MetaWork').WorkBean; // ,{WorkDiv:workdiv,Type:type,Mode:mode,:WorkBean});
				WorkBean.des_revoca = totalRev > 0 ? WorkBean.des_revoca : ''; 
				strA = strA == '' ? WorkBean.des_revoca == null ? ''
						: WorkBean.des_revoca
						: strA;
				$("#PEForm_lbl_apoderados_revocados").html(strA).show();

				WorkBean.des_revoca = strA;

				$(".ApodDetail").click(function() {
					var doc = $(this).attr('docId');
					if(doc=='' || doc == null){
						swal({ title: "Aviso",   
						      text: "El campo DOCUMENTUM se la Escritura se asign\u00F3 vac\u00EDo, Para corregir vuelva a realizar la Revocaci\u00F3n.",   
						      type: "warning",  
						      confirmButtonText: "Ok" });
						return;
					}
					window.open("/DerechoCorporativo/faces/jsp/documentum/waitingPage.jsp?doc="+doc
						     ,null
						     ,"height=600,width=1100,status=yes,toolbar=no,menubar=no,location=no");
				});
            	
            }
        });
		
	}
	
	var initCP = function(){
		$("#CPForm_Grid_poderSelecc").jGridContentMVC({
			gridId : "CPForm_Grid_poderSelecc",
			noDataMsg : 'ningun poder seleccionado, por favor seleccione un poder',
			storage : Grid_apoderados,
			rowClick : null,
			cellClick : function(iRow, iCell, bean, pty, gridId, td, event) {
				switch (pty) {
					case 'del':
						if (_this.engaged)
						return;
					break;
					default:

					break;
				}
			},
			rowCssA : '',
			rowCssB : '',
			rowFinal : null,
			rowAlign : 'center',
			Encabezados : true,
			HeadCss : "",
			Heads : false,
			columns : [ {HeadText: 'Poderes', 'width': '5%', id: 'des_podertipo', 'align': 'left' }],
			tblCssClass : "",
			tblStyle : "width:100%",
			cellpadding : '0',
			cellspacing : '1',
			border : '0',
			afterDrawing : function() {

			}
		});

							
		// Variable para almacenar el indice del apoderado seleccionado
		var apeIndex = 1;
		$("#CPForm_Grid_apoderados").jGridContentMVC({
			gridId : "CPForm_Grid_apoderados",
			noDataMsg : 'ningun poder seleccionado, por favor seleccione un poder',
			storage : Grid_apoderados,
			
			rowClick : null,
			cellClick : function(iRow, iCell, bean, pty,gridId, td, event) {
				
			},
			rowCssA : 'evenCeld',
			rowCssB : 'oddCeld',
			rowFinal : null,
			rowAlign : 'center',
			Encabezados : true,
			HeadCss : "tableHeads",
			Heads : true,
			columns : [
				     {
				    	HeadText : '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Apoderados&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;',
						'width' : '20%',								
						id : 'desc_apoderados',
						type : 'action',
						'align' : 'left',
						cellValue : 'eval',
						eval : function(bean,id,column,iCell,iRow,config) {		
							var apoderados = bean.desc_apoderados;									
							var apoderados = bean.desc_apoderados;									
							apoderados = apoderados.split("<br /> &#9679;").join("</li><li style='font-size:11px;margin-left:-6px;'>");
							apoderados = apoderados.split("&#9679;").join("<ul style='font-size:0;'><li style='font-size:11px;margin-left:-6px;'>");									
							apoderados = apoderados.split(" 	 ").join("");	
							apoderados = apoderados.split("<br />").join("</li></ul>");
							apoderados = apoderados.split("<sup style='font-size:8pt;'>").join("<sup style='font-size:8pt;'>");
							apoderados = apoderados.split("<div style='font-weight: bold;'>null</div>").join("");
							apoderados = apoderados.trim();
							return apoderados;
						}	
					 }
					,{ HeadText : 'Poder', 'width' : '8%', id : 'des_podertipo', type : 'action','align' : 'left'}
					,{ HeadText : 'Descripcion', 
					   'width' : '14%', 
					   id : 'desc_descripcion', 
					   type : 'action', 
					   'align' : 'left',
					   cellValue : 'eval',						
					   eval : function(bean,id,column,iCell,iRow,config) {				
							return '<b>'+bean.des_podertipo+'</b><br/>'+bean.des_poder;
								
					   }
					}					
					,{ HeadText : 'Vigencia', 'width' : '5%', id : 'fec_vigenciafin', type : 'action', 'align' : 'left'}					
					 ],
			tblCssClass : "",
			tblStyle : "width:100%",
			cellpadding : '0',
			cellspacing : '1',
			border : '0',
			afterDrawing : function(config) {	
								
				if ($("#divPGForm").data('MetaWork').WorkBean == null){
					$("#CPForm_lbl_apoderados_revocados").html('');
					return;
				}
				var apendices = new Array();
				var strA = "";				
				var totalRev = 0;
				$.each(config.storage,function(i, bean) {
					if (bean.Apoderados) {
						$(bean.Apoderados).each(
										function(i,apod) {
											if (parseInt(apod.ind_status) == 2 ) {
												totalRev++;
												var exists = false;
												for ( var i in apendices) {
													if (apendices[i] == apod.desc_revoca) {
														exists = true;
														break;
													}
												}
												if (!exists) {
													apendices.push(apod.desc_revoca);
													//strA += "<br />" + apod.desc_revoca;
												}

											}
										});
					}
								});
				
				apendices.sort(function(a, b){
					a = a.replace(/<\/?[^>]+(>|$)/g, "");
					a = a.substring(0,a.indexOf(" "));
					b = b.replace(/<\/?[^>]+(>|$)/g, "");
					b = b.substring(0,b.indexOf(" "));								    
				    return a-b
				});
				
				for(var i in apendices)
					strA += "<br />" + apendices[i];
				var WorkBean = $("#divPGForm").data('MetaWork').WorkBean; // ,{WorkDiv:workdiv,Type:type,Mode:mode,:WorkBean});
				WorkBean.des_revoca = totalRev > 0 ? WorkBean.des_revoca : ''; 
				strA = strA == '' ? WorkBean.des_revoca == null ? ''
						: WorkBean.des_revoca
						: strA;
				$("#CPForm_lbl_apoderados_revocados").html(strA).show();
				

				WorkBean.des_revoca = strA;

				$(".ApodDetail").click(function() {
					var doc = $(this).attr('docId');
					if(doc=='' || doc == null){
						swal({ title: "Aviso",   
						      text: "El campo DOCUMENTUM se la Escritura se asign\u00F3 vac\u00EDo, Para corregir vuelva a realizar la Revocaci\u00F3n.",   
						      type: "warning",  
						      confirmButtonText: "Ok" });
						return;
					}
					window.open("/DerechoCorporativo/faces/jsp/documentum/waitingPage.jsp?doc="+doc
						     ,null
						     ,"height=600,width=1100,status=yes,toolbar=no,menubar=no,location=no");
				});

			}
		});
	}
	
	var initDlgPoderes = function() {

		$("#PGForm_Grid_apoderadosOtro").change(function() {
			var check = $(this).is(":checked");
			if (check) {
				$("#PGForm_Btn_poderAddCarta").show();
				$("#PGForm_DlgPoderes_Chk_selecTodos").prop("checked", false);
				$(".PGForm_Grid_apoderados").each(function(i, input) {
					if ($(input).val() != "Otro")
						$(input).prop("checked", false);
				});
			} else
				$("#PGForm_Btn_poderAddCarta").hide();
		});		
	}

	var ClassPoder = function() {
		var _this = this;
		this.id_ep_fk = null;
		this.id_opoder_ep_pk = null;
		this.des_podertipo = arguments[0];
		this.des_poder = arguments[1] ? arguments[1] : arguments[0];
		this.num_order = $("#PGForm_Grid_apoderados").jGridContentMVC_GetList().length + 1;
		this.C3 = "";
		this.C4 = "No tienen";
		this.C5 = this.C1 == "VIP" ? "Para ejercerlo ante autoridades administrativas judiciales, penales, fiscales o laborales."
				: "Para ejercerlo ante autoridades administrativas judiciales, penales, fiscales o laborales. No delegable";
		this.C6 = "No tienen";
		this.C7 = "Excepto hacer cesi\u00F3n de bienes";
		this.C8 = "";
		this.C9 = "";

	}

	var ClassApoderado = function() {
		var _this = this;
		this.id_apod_ep_pk = null;
		this.desc_nom_empl = arguments[1];
		this.id_empl_fk = arguments[0];
		this.id = this.id_empl_fk;
		this.value = this.desc_nom_empl;
		this.id_opoder_ep_fk = arguments[3];
		this.id_ep_fk = arguments[4];
		this.ind_tipoapoderado = arguments[2];
		this.desc_tipoapoderado = arguments[2] == 1 ? 'APODERADO'
				: 'MANCOMUNADO';
		this.ind_status = 1;

		this.id_grupo_fk = arguments[5] ? arguments[5] : 0;
		this.des_grupo = arguments[6] ? arguments[6] : '';
		this.num_created_by = null;
	}

	var ClassFacultad = function() {
		var _this = this;
		_this.id_ep_fk = arguments[0];
		_this.ind_tipo = arguments[1];
		_this.des_tipo = arguments[2];
		_this.ind_delegable = arguments[3];
		_this.ind_individual = arguments[4];
		_this.caracteristicas = arguments[5];
		_this.des_formae = arguments[6];
		_this.mancomunado = arguments[7];
		_this.listMancomunados = arguments[8];
		_this.id_opoder_ep_fk = 0;
		_this.id_ep_fk = 0;
		_this.num_created_by = 0;
		_this.id_apod_ep_pk = 0;

	}
	var WorkGroups = [];

	var loadGroupApoderados = function(data) {
	
		var isgroup = WebForm.getKeyValue('PGForm_DlgApoderados_chk_GroupPoderes');
		var group = {
			key : 0,
			value : ''
		};
		if (isgroup.value)
			group = WebForm.getKeyValue('PGForm_DlgApoderados_gapoder');

		if(group.key==null){
			sweetAlert('No se pudo realizar la operacion', 'Debe seleccionar un grupo primero');
			return;
		}
		
		if (!WorkGroups[group.key]) {
			WorkGroups[group.key] = "#PGForm_DlgApoderados_Grid_gapoderSelecc_"
					+ group.key;
			$("#PGForm_DlgApoderados_Div_gapoderSelecc")
					.append(
							'<div style="font-weight:bold;">'
									+ (group.value==null?'':group.value)
									+ '</div><div id="PGForm_DlgApoderados_Grid_gapoderSelecc_'
									+ group.key + '"></div>');

			$("#PGForm_DlgApoderados_Grid_gapoderSelecc_" + group.key)
					.jGridContentMVC(
							{
								gridId : "PGForm_DlgApoderados_Grid_gapoderSelecc_"
										+ group.key,
								noDataMsg : 'no hay registros para mostrar',
								storage : [],
								rowClick : null,
								cellClick : function(iRow, iCell, bean, pty,
										gridId, td, event) {
									switch (pty) {
									case 'del':
										var iRowMain = $(
												"#PGForm_DlgApoderados_Grid_gapoderSelecc")
												.jGridContentMVC_Exists('id',
														bean.id, true);

										$("#" + gridId).jGridContentMVC_Delete(
												iRow);
										$(
												"#PGForm_DlgApoderados_Grid_gapoderSelecc")
												.jGridContentMVC_Delete(
														iRowMain);

										break;
									default:

										break;
									}
								},
								rowCssA : '',
								rowCssB : '',
								rowFinal : null,
								rowAlign : 'center',
								Encabezados : true,
								HeadCss : "HHead",
								Heads : false,
								columns : [
										{
											HeadText : 'Poderes',
											'width' : '95%',
											id : 'value',
											isUndefined : 'desc_nom_empl',
											type : 'action',
											'align' : 'left'
										},
										{
											HeadText : 'Poderes',
											'width' : '5%',
											id : 'del',
											type : 'action',
											'align' : 'left',
											cellValue : 'eval',
											eval : function(bean) {
												return '<div class="ui-state-default ui-corner-all" style="width:15px;"><span class="ui-icon ui-icon-circle-close"/></div>';
											}
										} ],
								tblCssClass : "",
								tblStyle : "width:100%",
								cellpadding : '0',
								cellspacing : '1',
								border : '0',
								afterDrawing : function() {

								}
							});

		}

		var adds = 0;
		$.each(data, function(i, bean) {
			bean.id_grupo_fk = group.key;
			bean.des_grupo = group.value;
			if ($("#PGForm_DlgApoderados_Grid_gapoderSelecc").jGridContentMVC_Exists('id', bean.id))
				return;
			adds++;
			$("#PGForm_DlgApoderados_Grid_gapoderSelecc").jGridContentMVC_Add(bean);
			$(WorkGroups[group.key]).jGridContentMVC_Add(bean);

		});
	
	}

	var initDlgApoderados = function() {
		$("#PGForm_DlgApoderados_Grid_gapoderSelecc")
				.jGridContentMVC(
						{
							gridId : "PGForm_DlgApoderados_Grid_gapoderSelecc",
							noDataMsg : 'no hay registros para mostrar',
							storage : [],
							rowClick : null,
							cellClick : function(iRow, iCell, bean, pty,
									gridId, td, event) {
								switch (pty) {
								case 'del':
									$(
											"#PGForm_DlgApoderados_Grid_gapoderSelecc")
											.jGridContentMVC_Delete(iRow);

									break;
								default:

									break;
								}
							},
							rowCssA : '',
							rowCssB : '',
							rowFinal : null,
							rowAlign : 'center',
							Encabezados : true,
							HeadCss : "HHead",
							Heads : false,
							columns : [
									{
										HeadText : 'Poderes',
										'width' : '95%',
										id : 'value',
										isUndefined : 'desc_nom_empl',
										type : 'action',
										'align' : 'left'
									},
									{
										HeadText : 'Poderes',
										'width' : '5%',
										id : 'del',
										type : 'action',
										'align' : 'left',
										cellValue : 'eval',
										eval : function(bean) {
											return '<div class="ui-state-default ui-corner-all" style="width:15px;"><span class="ui-icon ui-icon-circle-close"/></div>';
										}
									} ],
							tblCssClass : "",
							tblStyle : "width:100%",
							cellpadding : '0',
							cellspacing : '1',
							border : '0',
							afterDrawing : function() {

							}
						});
		
		$("#PGForm_DlgApoderados_btn_orderAllapoder").button({
			icon : "ui-icon-zoomin",
			showLabel : true
		}).click(function(){
			var isgroup = WebForm.getKeyValue('PGForm_DlgApoderados_chk_GroupPoderes');			
			if (isgroup.value){	
				$("#PGForm_DlgApoderados_Grid_gapoderSelecc").jGridContentMVC_UpDate([]);														
				for(var key in WorkGroups){
					var list = $("#PGForm_DlgApoderados_Grid_gapoderSelecc_" + key).jGridContentMVC_OrderBy('value','desc_nom_empl');					
					$("#PGForm_DlgApoderados_Grid_gapoderSelecc").jGridContentMVC_AddList(list);
				}											
			}else
				$("#PGForm_DlgApoderados_Grid_gapoderSelecc").jGridContentMVC_OrderBy('value','desc_nom_empl');//id : 'value',			isUndefined : 'desc_nom_empl'
			
		});
		
		$("#PGForm_DlgApoderados_btn_addAllapoder").button({
			icon : "ui-icon-zoomin",
			showLabel : true
		}).click(
				function() {
					WebKernel.multiselectDialog('Seleccione los apoderados',
							'32', '', {
								publish : function(data) {
									loadGroupApoderados(data);
								},
								metadata : null
							});
				});

		var dpConfigFrom = WebKernel.getDefaultDatepicker(true);
		var dpConfigTo = WebKernel.getDefaultDatepicker(true);
		dpConfigFrom.change = function() {
			var element = $(this).attr('toField');
			var currentDate = $(this).datepicker("getDate");
			var op = $("#PGForm_DlgApoderados_cmb_vigencia").val();

			switch (op) {
			case '1':
				var value = $("#PGForm_DlgApoderados_spi_vigencia").spinner(
						"value");
				var date = WebKernel.addMonths(currentDate, value * 12,
						'dd/mm/yyyy');
				$("#" + element).val(date);
				break;
			case '2':
				var value = $("#PGForm_DlgApoderados_spi_vigencia").spinner(
						"value");
				var date = WebKernel
						.addMonths(currentDate, value, 'dd/mm/yyyy');

				$("#" + element).val(date);
				break;
			default:
				$("#" + element).datepicker("option", "minDate",
						dpConfigTo.getDate(this));
			}

		}
		dpConfigTo.change = function() {
			var element = $(this).attr('fromField');

			$("#" + element).datepicker("option", "maxDate",
					dpConfigTo.getDate(this));
		}

		WebKernel.newWebForm("PGForm_DlgApoderados", "PGModule", {
			PGForm_DlgApoderados_calendar_vigenciaInicio : dpConfigFrom,
			PGForm_DlgApoderados_calendar_vigenciaFin : dpConfigTo

		});

		$("#PGForm_DlgApoderados_spi_vigencia").spinner("option", "disabled",
				false);
		$("#PGForm_DlgApoderados_calendar_vigenciaFin").datepicker("option", {
			disabled : true
		});
		$("#PGForm_DlgApoderados_calendar_vigenciaFin").val("");

	}

	var initDlgApoderadosOtros = function() {

		dlgApoderadosOtros = new Object();

		dlgApoderadosOtros.btnCancelar = {
			text : "cancelar",
			click : function() {
				dlgApoderadosOtros.dialog('close');
			}
		};

		dlgApoderadosOtros.btnAtrasPaso1 = {
			text : "Atras",
			click : function() {
				$("#PGForm_DlgPoderes").show();
				$("#PGForm_DlgApoderados").hide();
				$("#PGForm_DlgApoderadosOtros_Div").hide();
				dlgApoderadosOtros
						.dialog("option", "title",
								"Captura de Poder - Paso 1 Seleccione el tipo de poder");
				dlgApoderadosOtros.dialog('option', 'height', "auto");
				dlgApoderadosOtros.dialog('option', 'width', 750);
				dlgApoderadosOtros.dialog("option", "buttons", [
						dlgApoderadosOtros.btnCancelar,
						dlgApoderadosOtros.btnSigPaso1 ]);
				dlgApoderadosOtros.dialog('open');
			}
		};
		dlgApoderadosOtros.btnAtrasPaso2 = {
			text : "Atras",
			icon : "ui-icon-seek-back",
			iconPosition : "end",
			click : function() {
				dlgApoderadosOtros.btnSigPaso1.click();
			}
		};

		dlgApoderadosOtros.btnSigPaso1 = {
			text : "Siguiente",
			click : function() {
				var beanType = WebForm
						.getKeyValue('PGForm_DlgPoderes_Grid_poderes');
				var type = beanType.value; // $('input[name=PGForm_Grid_apoderados_radio]:checked').val();
				
				dlgApoderadosOtros.dialog("option", "title",
						'Captura de poder (' + type
								+ ') - Paso 2 Selecci\u00F3n a los apoderados');
				$("#PGForm_DlgPoderes").hide();
				$("#PGForm_DlgApoderados").show();
				$("#PGForm_DlgApoderadosOtros_Div").hide();
				dlgApoderadosOtros.dialog('option', 'height', "auto");
				dlgApoderadosOtros.dialog('option', 'width', 750);
				$('.DetallePoder').hide();
				switch (type) {
				case 'General':
					$('.DetallePoderGeneral').show();
					break;
				default:
					$('.DetallePoderCat').show();
					break;
				}
				dlgApoderadosOtros.dialog("option", "buttons", [
						dlgApoderadosOtros.btnCancelar,
						dlgApoderadosOtros.btnAtrasPaso1,
						dlgApoderadosOtros.btnSigPaso2 ]);
			}
		};

		dlgApoderadosOtros.btnSigPaso2 = {
			text : "Siguiente",
			click : function() {
				var beanType = WebForm.getKeyValue(
						'PGForm_DlgPoderes_Grid_poderes', true);
				var type = beanType.des_podertipo; // $('input[name=PGForm_Grid_apoderados_radio]:checked').val();
				var list = $("#PGForm_DlgApoderados_Grid_gapoderSelecc")
						.jGridContentMVC_GetList();
				$("#PGForm_DlgApoderadosOtros_Grid_ApSelecc")
						.jGridContentMVC_UpDate(list);

				$("#PGForm_DlgPoderes").hide();
				$("#PGForm_DlgApoderados").hide();
				$("#PGForm_DlgApoderadosOtros_Div").show();
				dlgApoderadosOtros.dialog('option', 'height', "auto");
				dlgApoderadosOtros.dialog('option', 'width', "auto");

				if (type != 'General') {
					var detailsStr = '<table width="100%"><tr><th style="width:25%">Actos de Dominio</th><th style="width:25%">Actos de Administraci\u00F3n</th><th style="width:25%">T\u00EDtulos de Cr\u00E9dito</th><th style="width:25%">Pleitos y Cobranzas</th></tr>';
					detailsStr += '<tr><td valign="top">AD_Replace</td><td valign="top">AA_Replace</td><td valign="top">TC_Replace</td><td valign="top">PC_Replace</td></tr></table>';

					var des_actosdominio = beanType.des_actosdominio == 'null' ? 'No Tiene'
							: beanType.des_actosdominio;
					var des_actosadmon = beanType.des_actosadmon == 'null' ? 'No Tiene'
							: beanType.des_actosadmon;
					var des_pleitoscobranzas = beanType.des_pleitoscobranzas == 'null' ? 'No Tiene'
							: beanType.des_pleitoscobranzas;
					var des_titulosdecreditos = beanType.des_titulosdecreditos == 'null' ? 'No Tiene'
							: beanType.des_titulosdecreditos;

					$("#DetallePoderCat_title").html(type);
					detailsStr = detailsStr.replace('AD_Replace',
							des_actosdominio);
					detailsStr = detailsStr.replace('AA_Replace',
							des_actosadmon);
					detailsStr = detailsStr.replace('PC_Replace',
							des_pleitoscobranzas);
					detailsStr = detailsStr.replace('TC_Replace',
							des_titulosdecreditos);
					dlgApoderadosOtros
							.dialog(
									"option",
									"title",
									'Captura de poder ('
											+ type
											+ ') - Paso 3 Caracter\u00EDsticas/Limitaciones');
					$("#DetallePoderCat_detail").html(detailsStr);
				} else
					dlgApoderadosOtros
							.dialog(
									"option",
									"title",
									'Captura de poder ('
											+ type
											+ ') - Paso 3 Asignaci\u00F3n de facultades');

				dlgApoderadosOtros.dialog("option", "buttons", [
						dlgApoderadosOtros.btnCancelar,
						dlgApoderadosOtros.btnAtrasPaso2,
						dlgApoderadosOtros.btnFinalizar ]);

			}
		};

		dlgApoderadosOtros.btnFinalizar = {
			text : "Finalizar",
			click : function() {
				/** ****************** Generar Poder * */
				// var type =
				// $('input[name=PGForm_Grid_apoderados_radio]:checked').val();
				var beanType = WebForm.getKeyValue(
						'PGForm_DlgPoderes_Grid_poderes', true);
				var type = beanType.des_podertipo; // $('input[name=PGForm_Grid_apoderados_radio]:checked').val();
				var WorkBean = dlgApoderadosOtros.Mode == 'new' ? new ClassPoder(type)
						: dlgApoderadosOtros.WorkBean;
				
				WorkBean.des_poder = type;
				/** ****************** Obtener Apoderador * */

				var list = $("#PGForm_DlgApoderados_Grid_gapoderSelecc")
						.jGridContentMVC_GetList();
				var selecc = new Array();
				
				WorkBean = WebForm.getBean('PGForm_DlgApoderados', WorkBean);

				var ga = list.length == 0 ? "" : list[0].des_grupo;
				var names = ga == '' ? '' : '<div style="font-weight: bold;">'
						+ ga + '</div>';
				$(list).each(function(i, bean) {
						var g = bean.des_grupo;
						if (g != ga) {
							names += ga == '' ? '': '<div style="font-weight: bold;">'+ g + '</div>';
							ga = g;
						}
						names += '&#9679; \t ' + bean.value+ '<br /> ';

						selecc.push(new ClassApoderado(bean.id,bean.value, 1, 0, 0,bean.id_grupo_fk, bean.des_grupo));

				});
				
				WorkBean.desc_apoderados = names;
				WorkBean.C8 = WorkBean.desc_caracteristicas;
				WorkBean.C9 = WorkBean.num_vigenciatipo == 0
						|| WorkBean.num_vigenciatipo == null ? ""
						: WorkBean.fec_vigenciafin;
				WorkBean.Apoderados = selecc;
				WorkBean.Facultades = new Array();

				switch (type) {
				case 'General':
					
					if ($("#PGForm_DlgApoderadosOtros_chk_pC").is(":checked")) {
						var pC = new Object();
						pC.delegable = $(
								"input[name='PGForm_DlgApoderadosOtros_Deleg1D']:checked")
								.val();
						pC.individual = $(
								"input[name='PGForm_DlgApoderadosOtros_Deleg2D']:checked")
								.val();
						pC.LstConjunto = $("#PGForm_DlgApoderadosOtros_grid_D")
								.jGridContentMVC_GetList();
						pC.otro = $("#PGForm_DlgApoderadosOtros_grid_D")
								.jGridContentMVC_ToString('value', '<BR/>',
										'&#9679;');
						pC.caract = $("#PGForm_DlgApoderadosOtros_txt_caractLD")
								.val();
						pC.mancomunado = $("#PGForm_DlgApoderadosOtros_ApodD")
								.is(":checked");
						pC.formae = $("#PGForm_DlgApoderadosOtros_txt_caractD")
								.val();
						WorkBean.C6 = "Caracter&iacute;sticas/Limitaciones:<br/>" + pC.caract 
								+ "<br /><br />*"
								+ pC.delegable								
								+ "<br /><br />Forma de ejercicio:<br />"
								+ pC.formae
								+ "<br /><br />"
								+ "*"
								+ pC.individual
								+ "<br /><br />"
								+ (pC.mancomunado ? "Mancomunado con:<br/>"
										+ pC.otro : "");
						WorkBean.des_poder += ', Pleitos y Cobranzas';
						//WorkBean.Facultades.push(new ClassFacultad(4,'Pleitos y Cobranzas', pC.delegable,pC.individual, pC.caract, pC.formae,pC.mancomunado, $("#PGForm_DlgApoderadosOtros_grid_D").jGridContentMVC_GetList())
						WorkBean.Facultades.push({id_ep_fk : 0
						,ind_tipo : 4
						,des_tipo : 'Pleitos y Cobranzas'
						,ind_delegable : pC.delegable
						,ind_individual : pC.individual
						,caracteristicas : pC.caract
						,des_formae : pC.formae
						,mancomunado : pC.mancomunado
						,listMancomunados : $("#PGForm_DlgApoderadosOtros_grid_D").jGridContentMVC_GetList()
						,id_opoder_ep_fk : 0
						,id_ep_fk : 0
						,pinnum_created_by : 0
						,id_apod_ep_pk : 0});
					} else
						WorkBean.C6 = "No Tiene";
					if ($("#PGForm_DlgApoderadosOtros_chk_aA").is(":checked")) {
						var aA = new Object();
						aA.delegable = $(
								"input[name='PGForm_DlgApoderadosOtros_Deleg1']:checked")
								.val();
						aA.individual = $(
								"input[name='PGForm_DlgApoderadosOtros_Deleg2']:checked")
								.val();
						aA.LstConjunto = $("#PGForm_DlgApoderadosOtros_grid_A")
								.jGridContentMVC_GetList();
						aA.otro = $("#PGForm_DlgApoderadosOtros_grid_A")
								.jGridContentMVC_ToString('value', '<BR/>',
										'&#9679;')
						aA.caract = $(
								"#PGForm_DlgApoderadosOtros_txt_caractLAL")
								.val();
						aA.mancomunado = $("#PGForm_DlgApoderadosOtros_ApodA")
								.is(":checked");
						aA.formae = $("#PGForm_DlgApoderadosOtros_txt_caractA")
								.val();
						WorkBean.C5 = "Caracter&iacute;sticas/Limitaciones:<br />"
								+ aA.caract 								
								+ "<br/><br/>*" + aA.delegable
								+ "<br />"
								+ "<br />Forma de ejercicio:<br />"
								+ aA.formae
								+ "<br /><br />"
								+ "*" + aA.individual
								+ "<br /><br />"
								+ ($("#PGForm_DlgApoderadosOtros_ApodA").is(
										":checked") ? "Mancomunado con:<br/>"
										+ aA.otro : "");
						WorkBean.des_poder += ', Actos de Administraci\u00F3n';
						//WorkBean.Facultades.push(new ClassFacultad(1,'Actos de Administraci\u00F3n', aA.delegable,aA.individual, aA.caract, aA.formae,aA.mancomunado, $("#PGForm_DlgApoderadosOtros_grid_A").jGridContentMVC_GetList()));
						WorkBean.Facultades.push({id_ep_fk : 0
							,ind_tipo : 1
							,des_tipo : 'Actos de Administraci\u00F3n'
							,ind_delegable : aA.delegable
							,ind_individual : aA.individual
							,caracteristicas : aA.caract
							,des_formae : aA.formae
							,mancomunado : aA.mancomunado
							,listMancomunados : $("#PGForm_DlgApoderadosOtros_grid_A").jGridContentMVC_GetList()
							,id_opoder_ep_fk : 0
							,id_ep_fk : 0
							,pinnum_created_by : 0
							,id_apod_ep_pk : 0});
						
					} else
						WorkBean.C5 = "No Tiene";
					if ($("#PGForm_DlgApoderadosOtros_chk_aD").is(":checked")) {
						var aD = new Object();
						aD.delegable = $(
								"input[name='PGForm_DlgApoderadosOtros_Deleg1B']:checked")
								.val();
						aD.individual = $(
								"input[name='PGForm_DlgApoderadosOtros_Deleg2B']:checked")
								.val();
						aD.LstConjunto = $("#PGForm_DlgApoderadosOtros_grid_B")
								.jGridContentMVC_GetList();
						aD.otro = $("#PGForm_DlgApoderadosOtros_grid_B")
								.jGridContentMVC_ToString('value', '<BR/>',
										'&#9679;');
						aD.caract = $("#PGForm_DlgApoderadosOtros_txt_caractLB")
								.val();
						aD.mancomunado = $(
								"#PGForm_DlgApoderadosOtros_ApodB").is(
								":checked");
						aD.formae = $("#PGForm_DlgApoderadosOtros_txt_caractB")
								.val();
						WorkBean.C4 = "Caracter&iacute;sticas/Limitaciones:<br />"
								+ aD.caract 
								+"<br/><br/>*"
								+ aD.delegable
								+ "<br /><br />Forma de ejercicio:<br />"
								+ aD.formae
								+ "<br /><br />"								
								+ "*"
								+ aD.individual															
								+ "<br /><br />"
								+ ($("#PGForm_DlgApoderadosOtros_ApodB").is(
										":checked") ? "Mancomunado con:<br/>"
										+ aD.otro : "");
						WorkBean.des_poder += ', Actos de Dominio';
						//WorkBean.Facultades.push(new ClassFacultad(2,'Actos de Dominio', aD.delegable,aD.individual, aD.caract, aD.formae,aD.mancomunado, $("#PGForm_DlgApoderadosOtros_grid_B").jGridContentMVC_GetList()));
						
						WorkBean.Facultades.push({id_ep_fk : 0
							,ind_tipo : 2
							,des_tipo : 'Actos de Dominio'
							,ind_delegable : aD.delegable
							,ind_individual : aD.individual
							,caracteristicas : aD.caract
							,des_formae : aD.formae
							,mancomunado : aD.mancomunado
							,listMancomunados : $("#PGForm_DlgApoderadosOtros_grid_B").jGridContentMVC_GetList()
							,id_opoder_ep_fk : 0
							,id_ep_fk : 0
							,pinnum_created_by : 0
							,id_apod_ep_pk : 0});
					} else
						WorkBean.C4 = "No Tiene";

					if ($("#PGForm_DlgApoderadosOtros_chk_tC").is(":checked")) {
						var tC = new Object();
						tC.delegable = $(
								"input[name='PGForm_DlgApoderadosOtros_Deleg1C']:checked")
								.val();
						tC.individual = $(
								"input[name='PGForm_DlgApoderadosOtros_Deleg2C']:checked")
								.val();
						tC.LstConjunto = $("#PGForm_DlgApoderadosOtros_grid_C")
								.jGridContentMVC_GetList();
						tC.otro = $("#PGForm_DlgApoderadosOtros_grid_C")
								.jGridContentMVC_ToString('value', '<BR/>',
										'&#9679;');
						tC.caract = $("#PGForm_DlgApoderadosOtros_txt_caractLC")
								.val();
						tC.mancomunado = $(
								"#PGForm_DlgApoderadosOtros_ApodC").is(
								":checked");
						tC.formae = $("#PGForm_DlgApoderadosOtros_txt_caractC")
								.val();
						WorkBean.C7 = "Caracter&iacute;sticas/Limitaciones:<br />"
								+ tC.caract 
								+"<br/><br />"+"*"
								+ tC.delegable
								+ "<br /><br />Forma de ejercicio:<br />"
								+ tC.formae
								+ "<br /><br />"								
								+ "*"
								+ tC.individual																
								+ "<br /><br />"
								+ ($("#PGForm_DlgApoderadosOtros_ApodC").is(
										":checked") ? "Mancomunado con:<br/>"
										+ tC.otro : "");
						;
						WorkBean.des_poder += ', T\u00EDtulos de Cr\u00E9dito';
						//WorkBean.Facultades.push(new ClassFacultad(3,'T\u00DEFtulos de Cr\U00E9dito', tC.delegable,tC.individual, tC.caract, tC.formae,tC.mancomunado, $("#PGForm_DlgApoderadosOtros_grid_C").jGridContentMVC_GetList()));
						WorkBean.Facultades.push({id_ep_fk : 0
							,ind_tipo : 3
							,des_tipo : 'T\u00EDtulos de C\u00E9rdito'
							,ind_delegable : tC.delegable
							,ind_individual : tC.individual
							,caracteristicas : tC.caract
							,des_formae : tC.formae
							,mancomunado : tC.mancomunado
							,listMancomunados : $("#PGForm_DlgApoderadosOtros_grid_C").jGridContentMVC_GetList()
							,id_opoder_ep_fk : 0
							,id_ep_fk : 0
							,pinnum_created_by : 0
							,id_apod_ep_pk : 0});
					} else
						WorkBean.C7 = "No Tiene";

					WorkBean.des_poder += ' ';
					break;
				default:
					WorkBean.C4 = beanType.des_actosdominio == 'null' ? 'No Tiene'
							: beanType.des_actosdominio;
					WorkBean.C5 = beanType.desc_actosadmon == 'null' ? 'No Tiene'
							: beanType.des_actosadmon;
					WorkBean.C6 = beanType.des_pleitoscobranzas == 'null' ? 'No Tiene'
							: beanType.des_pleitoscobranzas;
					WorkBean.C7 = beanType.des_titulosdecreditos == 'null' ? 'No Tiene'
							: beanType.des_titulosdecreditos;
					break;
				}

				WorkBean.desc_actosdominio = WorkBean.C4;
				WorkBean.desc_actosadmon = WorkBean.C5;
				WorkBean.desc_pleitoscobranza = WorkBean.C6;
				WorkBean.desc_tituloscredito = WorkBean.C7;
				WorkBean.desc_vigencia = WorkBean.C9;

				if (dlgApoderadosOtros.Mode == 'new')
					Grid_apoderados.push(WorkBean);
				$("#PGForm_Grid_poderSelecc").jGridContentMVC_UpDate(
						Grid_apoderados);
				$("#PGForm_Grid_apoderados").jGridContentMVC_UpDate(
						Grid_apoderados);
				dlgApoderadosOtros.dialog('close');
			}
		};

		dlgApoderadosOtros.open = function(mode, WorkBean) {
			dlgApoderadosOtros.WorkBean = WorkBean;
			dlgApoderadosOtros.Mode = mode;
			WorkGroups = [];
			$("#PGForm_DlgApoderados_Div_gapoderSelecc").html('');
			$("#PGForm_DlgApoderados_Grid_gapoderSelecc").jGridContentMVC_UpDate([]);

			for (var i = 0; i < 4; i++) {
				var cveA = [ 'aA', 'aB', 'aC', 'pC' ];
				var cveB = [ 'A', 'B', 'C', 'D' ];
				var cveC = [ '', 'B', 'C', 'D' ];
				var cveD = [ 'AL', 'B', 'C', 'D' ];
				WebForm.updController('PGForm_DlgApoderadosOtros_chk_'
						+ cveA[i], false);
				WebForm.updController('PGForm_DlgApoderadosOtros_Deleg1'
						+ cveC[i], false);
				WebForm.updController('PGForm_DlgApoderadosOtros_Deleg2'
						+ cveC[i], false);

				$("#PGForm_DlgApoderadosOtros_grid_" + cveB[i])
						.jGridContentMVC_UpDate([]);

				WebForm.updController('PGForm_DlgApoderadosOtros_txt_caract'
						+ cveB[i], '');
				WebForm.updController('PGForm_DlgApoderadosOtros_txt_caractL'
						+ cveD[i], '');
				WebForm.updController('PGForm_DlgApoderadosOtros_Apod'
						+ cveB[i], false);

			}
			
			$("#PGForm_Grid_poderSelecc").jGridContentMVC_UpDate();
			
			switch (mode) {
			case 'new':
				WebForm.cleanBean('PGForm_DlgApoderados');
				
				$("#PGForm_DlgApoderadosOtros_tabs").tabs( "option", "active", 0 );
				
				$.each($( "input[name=PGForm_DlgApoderadosOtros_Deleg1]" ), function(i, button) {								
					if('Delegable'==$(button).val()){
						$(button).prop("checked", true);
						return;
					}
				});
				$.each($( "input[name=PGForm_DlgApoderadosOtros_Deleg2]" ), function(i, button) {								
					if('Individual'==$(button).val()){
						$(button).prop("checked", true);
						return;
					}
				});
				
				$.each($( "input[name=PGForm_DlgApoderadosOtros_Deleg1B]" ), function(i, button) {								
					if('Delegable'==$(button).val()){
						$(button).prop("checked", true);
						return;
					}
				});
				$.each($( "input[name=PGForm_DlgApoderadosOtros_Deleg2B]" ), function(i, button) {								
					if('Individual'==$(button).val()){
						$(button).prop("checked", true);
						return;
					}
				});
				
				$.each($( "input[name=PGForm_DlgApoderadosOtros_Deleg1C]" ), function(i, button) {								
					if('Delegable'==$(button).val()){
						$(button).prop("checked", true);
						return;
					}
				});
				$.each($( "input[name=PGForm_DlgApoderadosOtros_Deleg2C]" ), function(i, button) {								
					if('Individual'==$(button).val()){
						$(button).prop("checked", true);
						return;
					}
				});
				
				$.each($( "input[name=PGForm_DlgApoderadosOtros_Deleg1D]" ), function(i, button) {								
					if('Delegable'==$(button).val()){
						$(button).prop("checked", true);
						return;
					}
				});
				$.each($( "input[name=PGForm_DlgApoderadosOtros_Deleg2D]" ), function(i, button) {								
					if('Individual'==$(button).val()){
						$(button).prop("checked", true);
						return;
					}
				});
				
				$('#PGForm_Grid_apoderados_radio').val('');
				dlgApoderadosOtros.btnAtrasPaso1.click();
				dlgApoderadosOtros.dialog('open');
				break;
			case 'edit':
				
				WebForm.setBean('PGForm_DlgApoderados', WorkBean);
				
				/** * Cargar Facultades */
				if (WorkBean.Facultades) {
					var facMenor = 0;
					for (var i = 0; i < WorkBean.Facultades.length; i++) {
						var facultad = WorkBean.Facultades[i];
						facMenor = facMenor == 0 ? facultad.ind_tipo : facMenor > facultad.ind_tipo ? facultad.ind_tipo : facMenor;
						switch (facultad.ind_tipo) {						
						case 1:
							WebForm.updController(
									'PGForm_DlgApoderadosOtros_chk_aA', true);
							
							$.each($( "input[name=PGForm_DlgApoderadosOtros_Deleg1]" ), function(i, button) {								
								if(facultad.ind_delegable==$(button).val()){
									$(button).prop("checked", true);
									return;
								}
							});
							$.each($( "input[name=PGForm_DlgApoderadosOtros_Deleg2]" ), function(i, button) {								
								if(facultad.ind_individual==$(button).val()){
									$(button).prop("checked", true);
									return;
								}
							});

							$("#PGForm_DlgApoderadosOtros_grid_A")
									.jGridContentMVC_UpDate(
											facultad.listMancomunados);

							WebForm.updController(
									'PGForm_DlgApoderadosOtros_txt_caractA',
									facultad.des_formae);
							WebForm.updController(
									'PGForm_DlgApoderadosOtros_txt_caractLAL',
									facultad.caracteristicas);
							WebForm.updController(
									'PGForm_DlgApoderadosOtros_ApodA',
									facultad.mancomunado);							
							break;
						case 2:
							WebForm.updController('PGForm_DlgApoderadosOtros_chk_aD', true);
							
							$.each($( "input[name=PGForm_DlgApoderadosOtros_Deleg1B]" ), function(i, button) {								
								if(facultad.ind_delegable==$(button).val()){
									$(button).prop("checked", true);
									return;
								}
							});
							$.each($( "input[name=PGForm_DlgApoderadosOtros_Deleg2B]" ), function(i, button) {								
								if(facultad.ind_individual==$(button).val()){
									$(button).prop("checked", true);
									return;
								}
							});
							
							$("#PGForm_DlgApoderadosOtros_grid_B")
									.jGridContentMVC_UpDate(
											facultad.listMancomunados);

							WebForm.updController(
									'PGForm_DlgApoderadosOtros_txt_caractB',
									facultad.des_formae);
							WebForm.updController(
									'PGForm_DlgApoderadosOtros_txt_caractLB',
									facultad.caracteristicas);
							WebForm.updController(
									'PGForm_DlgApoderadosOtros_ApodB',facultad.mancomunado);
							break;
						case 3:
							WebForm.updController(
									'PGForm_DlgApoderadosOtros_chk_tC', true);
							
							$.each($( "input[name=PGForm_DlgApoderadosOtros_Deleg1C]" ), function(i, button) {								
								if(facultad.ind_delegable==$(button).val()){
									$(button).prop("checked", true);
									return;
								}
							});
							$.each($( "input[name=PGForm_DlgApoderadosOtros_Deleg2C]" ), function(i, button) {								
								if(facultad.ind_individual==$(button).val()){
									$(button).prop("checked", true);
									return;
								}
							});
														
							$("#PGForm_DlgApoderadosOtros_grid_C")
									.jGridContentMVC_UpDate(
											facultad.listMancomunados);

							WebForm.updController(
									'PGForm_DlgApoderadosOtros_txt_caractC',
									facultad.des_formae);
							WebForm.updController(
									'PGForm_DlgApoderadosOtros_txt_caractLC',
									facultad.caracteristicas);
							WebForm.updController(
									'PGForm_DlgApoderadosOtros_ApodC',
									facultad.mancomunado);

							break;
						case 4:
							
							WebForm.updController('PGForm_DlgApoderadosOtros_chk_pC', true);
							
							$.each($( "input[name=PGForm_DlgApoderadosOtros_Deleg1D]" ), function(i, button) {								
								if(facultad.ind_delegable==$(button).val()){
									$(button).prop("checked", true);
									return;
								}
							});
							$.each($( "input[name=PGForm_DlgApoderadosOtros_Deleg2D]" ), function(i, button) {								
								if(facultad.ind_individual==$(button).val()){
									$(button).prop("checked", true);
									return;
								}
							});
							
							$("#PGForm_DlgApoderadosOtros_grid_D").jGridContentMVC_UpDate(facultad.listMancomunados);

							WebForm.updController('PGForm_DlgApoderadosOtros_txt_caractD',facultad.des_formae);
							WebForm.updController('PGForm_DlgApoderadosOtros_txt_caractLD',facultad.caracteristicas);
							WebForm.updController('PGForm_DlgApoderadosOtros_ApodD',facultad.mancomunado);

						}
					}
					$("#PGForm_DlgApoderadosOtros_tabs").tabs( "option", "active", facMenor-1 );
				}
				/** */

				/** * Cargar Apoderados */
				$('#PGForm_Grid_apoderados_radio').val(WorkBean.C1);

				var isgroup = false;
				for (var i = 0; i < WorkBean.Apoderados.length; i++) {
					var bean = WorkBean.Apoderados[i];
					bean.id = bean.id_empl_fk;
					bean.value = bean.desc_nom_empl;

					if (bean.des_grupo != null && bean.des_grupo != '') {
						isgroup = true;
					}
				}

				if (!isgroup){
					WebForm.updController('PGForm_DlgApoderados_chk_GroupPoderes', false);
					$("#PGForm_DlgApoderados_Grid_gapoderSelecc").jGridContentMVC_UpDate(WorkBean.Apoderados);					
				}else {
					WebForm.updController(
							'PGForm_DlgApoderados_chk_GroupPoderes', true);
					for (var i = 0; i < WorkBean.Apoderados.length; i++) {
						var bean = WorkBean.Apoderados[i];
						var group = {
							key : bean.id_grupo_fk,
							value : bean.des_grupo
						};

						if (!WorkGroups[group.key]) {
							WorkGroups[group.key] = "#PGForm_DlgApoderados_Grid_gapoderSelecc_"
									+ group.key;
							$("#PGForm_DlgApoderados_Div_gapoderSelecc")
									.append(
											'<div style="font-weight:bold;">'
													+ group.value
													+ '</div><div id="PGForm_DlgApoderados_Grid_gapoderSelecc_'
													+ group.key + '"></div>');

							$(
									"#PGForm_DlgApoderados_Grid_gapoderSelecc_"
											+ group.key)
									.jGridContentMVC(
											{
												gridId : "PGForm_DlgApoderados_Grid_gapoderSelecc_"
														+ group.key,
												noDataMsg : 'no hay registros para mostrar',
												storage : [],
												rowClick : null,
												cellClick : function(iRow,
														iCell, bean, pty,
														gridId, td, event) {
													switch (pty) {
													case 'del':
														var iRowMain = $(
																"#PGForm_DlgApoderados_Grid_gapoderSelecc")
																.jGridContentMVC_Exists(
																		'id',
																		bean.id,
																		true);

														$("#" + gridId)
																.jGridContentMVC_Delete(
																		iRow);
														$(
																"#PGForm_DlgApoderados_Grid_gapoderSelecc")
																.jGridContentMVC_Delete(
																		iRowMain);

														break;
													default:

														break;
													}
												},
												rowCssA : '',
												rowCssB : '',
												rowFinal : null,
												rowAlign : 'center',
												Encabezados : true,
												HeadCss : "HHead",
												Heads : false,
												columns : [
														{
															HeadText : 'Poderes',
															'width' : '95%',
															id : 'value',
															isUndefined : 'desc_nom_empl',
															type : 'action',
															'align' : 'left'
														},
														{
															HeadText : 'Poderes',
															'width' : '5%',
															id : 'del',
															type : 'action',
															'align' : 'left',
															cellValue : 'eval',
															eval : function(
																	bean) {
																return '<div class="ui-state-default ui-corner-all" style="width:15px;"><span class="ui-icon ui-icon-circle-close"/></div>';
															}
														} ],
												tblCssClass : "",
												tblStyle : "width:100%",
												cellpadding : '0',
												cellspacing : '1',
												border : '0',
												afterDrawing : function() {

												}
											});

						}
					}
					var adds = 0;
					$.each(WorkBean.Apoderados, function(i, bean) {
						bean.id_grupo_fk = bean.id_grupo_fk;
						bean.des_grupo = bean.des_grupo;
						adds++;
						if (!$("#PGForm_DlgApoderados_Grid_gapoderSelecc")
								.jGridContentMVC_Exists('id', bean.id))
							$("#PGForm_DlgApoderados_Grid_gapoderSelecc")
									.jGridContentMVC_Add(bean);
						$(WorkGroups[bean.id_grupo_fk]).jGridContentMVC_Add(
								bean);

					});
				}
				
				break;
			}
		}

		$("#PGForm_DlgApoderadosOtros_tabs").tabs();

		WebKernel.newWebForm("PGForm_DlgApoderadosOtros", "PGModule");

		$("#PGForm_DlgApoderadosOtros_Grid_ApSelecc").jGridContentMVC({
			gridId : "PGForm_DlgApoderadosOtros_Grid_ApSelecc",
			noDataMsg : 'no hay registros para mostrar',
			storage : [],
			rowClick : null,
			cellClick : function(iRow, iCell, bean, pty, gridId, td, event) {

			},
			rowCssA : '',
			rowCssB : '',
			rowFinal : null,
			rowAlign : 'center',
			Encabezados : true,
			HeadCss : "HHead",
			Heads : false,
			columns : [ {
				HeadText : 'Apoderados',
				'width' : '35%',
				id : 'value',
				isUndefined : 'desc_nom_empl',
				type : 'action',
				'align' : 'left'
			}, {
				HeadText : 'Apoderados',
				'width' : '65%',
				id : 'des_grupo',
				isNull : '',
				type : 'action',
				'align' : 'left'
			}

			],
			tblCssClass : "",
			tblStyle : "width:100%",
			cellpadding : '0',
			cellspacing : '1',
			border : '0',
			afterDrawing : function() {

			}
		});

		var listMd = [ 'A', 'B', 'C', 'D' ];
		for ( var i in listMd) {
			var id = "PGForm_DlgApoderadosOtros_grid_" + listMd[i]
			$("#" + id).jGridContentMVC(
					{
						gridId : id,
						noDataMsg : 'no hay registros para mostrar',
						storage : [],
						rowClick : null,
						cellClick : function(iRow, iCell, bean, pty, gridId,
								td, event) {

						},
						rowCssA : '',
						rowCssB : '',
						rowFinal : null,
						rowAlign : 'center',
						Encabezados : true,
						HeadCss : "HHead",
						Heads : false,
						columns : [ {
							HeadText : 'Nombre',
							'width' : '100%',
							id : 'value',
							type : 'action',
							'align' : 'left'
						}

						],
						tblCssClass : "",
						tblStyle : "width:100%",
						cellpadding : '0',
						cellspacing : '1',
						border : '0',
						afterDrawing : function() {

						}
					});
		}

		$(".PGForm_DlgApoderadosOtros_BtnCon").button().click(
				function() {
					var id = "#PGForm_DlgApoderadosOtros_grid_" + $(this).attr('metadata');						
					var mancomunadosIds = new Array();
					var mancomunados = $(id).jGridContentMVC_GetList();
					$.each(mancomunados, function(i,mancomunado){
						mancomunadosIds.push(mancomunado.id);				
					});
					WebKernel.multiselectDialog('Seleccione los mancomunados...', '32', mancomunadosIds, {
						publish : function(data) {							
							$(id).jGridContentMVC_UpDate([]);
							$.each(data, function(i, bean) {
								$(id).jGridContentMVC_Add(bean);
							});
						},
						metadata : $(this).attr('metadata')
					});
				});

	}

	var ClassRevocar = function() {
		this.Escritura = arguments[0];
		this.Concepto = arguments[1];
		this.Fecha = arguments[2];
		this.NoDoc = arguments[3];
		var bean = arguments[4];
		this.id_revoca_ep_pk = null;
		this.id_opoder_ep_fk = bean.id_opoder_ep_fk;
		this.id_ep_fk = bean.id_ep_fk;
		this.id_apod_ep_fk = bean.id_apod_ep_pk;

		this.ind_razonrevoca = arguments[1];
		this.des_razonrevoca = this.Concepto == 1 ? "Revocaci&oacute;n" 
				: this.Concepto == 2 ? "Muerte" 
						: this.Concepto == 3 ? "Se agot&oacute; el objeto"
								: "Renuncia";
						
		this.id_escriturarevoca_fk = this.Escritura;
		this.id_documentumrevoca = this.NoDoc;
		this.fec_revoca = this.Fecha;

		this.des_textorevoca = arguments[1];
		this.desc_apendicerevoca = 1;
		this.num_created_by = null;

	}

	var initDlgRevocacion = function() {
		dlgRevocacion = $(dlgRevocacion).dialog({
			autoOpen : false,
			height : "auto",
			width : 800,
			modal : true,
			buttons : {
				Cancelar : function() {
					var revocados = $("#PGForm_DlgRevocacion_grid_Revocados").jGridContentMVC_GetList();
					
					for ( var i in revocados) {
						var apod = revocados[i];
						if(apod.ind_status == 3){																			
							apod.ind_status = 2;
						}else if(apod.ind_status == 4){
							apod.ind_status = 1;
						}
					}
					dlgRevocacion.dialog('close');
				},
				"Aplicar Revocaciones" : dlgRevocacion_save
			}
		});

		WebKernel.newWebForm("PGForm_DlgRevocacion", "PGModule");

		$("#PGForm_DlgRevocacion_grid_Revocados")
				.jGridContentMVC(
						{
							gridId : "PGForm_DlgRevocacion_grid_Revocados",
							noDataMsg : 'no hay registros para mostrar',
							storage : [],
							rowClick : null,
							cellClick : function(iRow, iCell, bean, pty,
									gridId, td, event) {
								switch (pty) {
								case 'Quitar':
									if(bean.ind_status==2 || bean.ind_status == 4){
										bean.ind_status = 3;
										$("#PGForm_DlgRevocacion_grid_Revocados").jGridContentMVC_UpDate();
									}
									break;
								case 'Revocar':
								case '_Selecc':

									$("#PGForm_DlgRevocacion_divRevocar").data(
											'Bean', bean);

									dlgRevocacion.buttonsMain = dlgRevocacion
											.dialog("option", "buttons");
									$("#PGForm_DlgRevocacion_LblName").html(
											bean.desc_nom_empl);
									// Setter
									dlgRevocacion
											.dialog(
													"option",
													"buttons",
													[
															{
																text : "cancelar",
																icons : {
																	primary : "ui-icon-circle-arrow-w"
																},
																click : function() {
																	$("#PGForm_DlgRevocacion_divRevocar").hide();
																	$("#PGForm_DlgRevocacion_divGrid").show();
																	dlgRevocacion.dialog("option","buttons",dlgRevocacion.buttonsMain);
																	
																}
															},
															{
																text : "Revocar",
																icons : {
																	primary : "ui-icon-circle-check"
																},
																click : function() {
																	// [iRow,
																	// iCell,
																	// bean,
																	// pty,
																	// gridId]
																	var bean = $("#PGForm_DlgRevocacion_divRevocar").data('Bean');
																	bean.ind_aprevoca = null;
																	bean.ind_status = 4;
																	bean.Revocar = new ClassRevocar(
																			$("#PGForm_DlgRevocacion_cmb_Escritura").val(),
																			$("#PGForm_DlgRevocacion_cmb_Escritura_cmb_razon").val(),
																			$("#PGForm_DlgRevocacion_cmb_Escritura_date_fecha").val(),
																			$("#PGForm_DlgRevocacion_doc_Documento_digital_text").val(),
																			bean);

																	$(
																			"#PGForm_DlgRevocacion_divRevocar")
																			.hide();
																	$(
																			"#PGForm_DlgRevocacion_divGrid")
																			.show();
																	dlgRevocacion
																			.dialog(
																					"option",
																					"buttons",
																					dlgRevocacion.buttonsMain);
																	$(
																			"#PGForm_DlgRevocacion_grid_Revocados")
																			.jGridContentMVC_UpDate();
																}
															} ]);

									if (bean.ind_status == 2 || bean.ind_status == 4) {
																	            
							            bean.Revocar.Escritura = bean.Revocar.Escritura == undefined ? bean.Revocar.id_escriturarevoca_fk : bean.Revocar.Escritura;
										bean.Revocar.Concepto = bean.Revocar.Concepto == undefined ? bean.Revocar.ind_razonrevoca : bean.Revocar.Concepto;
										bean.Revocar.Fecha = bean.Revocar.Fecha == undefined ? bean.Revocar.fec_revoca : bean.Revocar.Fecha;
										bean.Revocar.NoDoc = bean.Revocar.NoDoc == undefined ? bean.Revocar.id_documentumrevoca : bean.Revocar.NoDoc;
							            
										$(
														"#PGForm_DlgRevocacion_cmb_Escritura")
														.val(
																bean.Revocar.Escritura);
												$(
														"#PGForm_DlgRevocacion_cmb_Escritura_cmb_razon")
														.val(
																bean.Revocar.Concepto);
												$(
														"#PGForm_DlgRevocacion_cmb_Escritura_date_fecha")
														.val(bean.Revocar.Fecha);
												$(
														"#PGForm_DlgRevocacion_doc_Documento_digital_text")
														.val(bean.Revocar.NoDoc);
									}else{

										$(
										"#PGForm_DlgRevocacion_cmb_Escritura")
										.val(
												"-1"),
								$(
										"#PGForm_DlgRevocacion_cmb_Escritura_cmb_razon")
										.val(
												"-1"),
								$(
										"#PGForm_DlgRevocacion_cmb_Escritura_date_fecha")
										.val(""),
								$(
										"#PGForm_DlgRevocacion_doc_Documento_digital_text")
										.val("");
									
										
									}

									$("#PGForm_DlgRevocacion_LblName").html(
											bean.desc_nom_empl);

									$("#PGForm_DlgRevocacion_divGrid").hide();
									$("#PGForm_DlgRevocacion_divRevocar")
											.show();

									break;
									
								}
							},
							rowCssA : 'odd',
							rowCssB : 'even',
							rowFinal : null,
							rowAlign : 'center',
							Encabezados : true,
							HeadCss : "HHead",
							Heads : true,
							columns : [
									{
										HeadText : 'Apoderados',
										'width' : '30%',
										id : 'desc_nom_empl',
										type : 'action',
										'align' : 'left'
									},
									{
										HeadText : 'Grupo',
										'width' : '30%',
										id : 'des_grupo',
										type : 'action',
										'align' : 'left'
									},									
									{
										HeadText : '',
										'width' : '25%',
										id : '_Selecc',
										type : 'action',
										'align' : 'center',
										cellValue : 'eval',
										eval : function(bean) {
											return bean.ind_status == 2 || bean.ind_status == 4 ? 'revocado': bean.ind_status==3 ? "quitar revocaci\u00F3n" : "";
										}
									},
									{
										HeadText : 'Revocar',
										'width' : '5%',
										id : 'Revocar',
										type : 'action',
										'align' : 'center',
										cellValue : 'eval',
										eval : function(bean) {
											return '<div class="ui-state-default ui-corner-all" style="width:15px;"><span class="ui-icon ui-icon-circle-close"/></div>';
										}
									},{
										HeadText : 'Quitar',
										'width' : '5%',
										id : 'Quitar',
										type : 'action',
										'align' : 'center',
										cellValue : 'eval',
										eval : function(bean) {
											return bean.ind_status == 2 || bean.ind_status == 4 ? '<div title="Quitar Revocaci\u00F3n" class="ui-state-default ui-corner-all" style="width:15px;"><span class="ui-icon ui-icon-arrowreturnthick-1-w""/></div>' : '';
										}
									} ],
							tblCssClass : "tablesorter",
							tblStyle : "width:100%",
							cellpadding : '0',
							cellspacing : '0',
							border : '0',
							afterDrawing : function() {

							}
						});

		dlgRevocacion.open = function(workBean) {
			$("#PGForm_DlgRevocacion_grid_Revocados").jGridContentMVC_UpDate(workBean.Apoderados);
			
			Request.EscrituraPoderes.getListEscRev(MetaSession.SessionBean.idCurrentEmpresa,{
				response : function(rsp) {
					
						var list = Util.getBeanList(rsp);
												
						WebForm.updController("PGForm_DlgRevocacion_cmb_Escritura",list);
				},
				error : function(xhttp, e) {
                	  sweetAlert('No se pudo realizar la operacion', 'Error:' + e,'error');
				},
				complete : function() {
					
				}
			});
			// WebKernel.WebForm.updController('PGForm_DlgRevocacion_cmb_Escritura',Grid_ERmain);
			
			$("#PGForm_DlgRevocacion_divRevocar").hide();
			$("#PGForm_DlgRevocacion_divGrid").show();
			dlgRevocacion.dialog("option","buttons",dlgRevocacion.buttonsMain ?dlgRevocacion.buttonsMain : dlgRevocacion.dialog("option", "buttons"));
			
			$(dlgRevocacion).data('WorkBean', workBean);
			dlgRevocacion.dialog('open');
		}
	}
	
	var getApendice = function(rev){
		var poderes = $("#PGForm_Grid_apoderados").jGridContentMVC_GetList();
		var apendice = 0;
		for(var i in poderes){
			var poder = poderes[i];
			for(var a in poder.Apoderados){
				var apod = poder.Apoderados[a];
				if(apod.ind_status == 2 || apod.ind_status == '2')					
					apendice = apod.ind_aprevoca == null ? apendice : apendice < apod.ind_aprevoca ? apod.ind_aprevoca : apendice;
				
			}
		}
				
		if(rev.Revocar.Concepto=='1' || rev.Revocar.Concepto==1 ){
			for(var i in poderes){
				var poder = poderes[i];
				for(var a in poder.Apoderados){
					var apod = poder.Apoderados[a];
					if(apod.ind_status == 2 && apod.ind_aprevoca != null){
						 if(apod.Revocar.Concepto == '1' || apod.Revocar.Concepto==1){
							 if(apod.Revocar.Escritura == rev.Revocar.Escritura){
								 return apod.ind_aprevoca;								 
							 }
						 	}
						}
					}
				}
			
		}
			
		return parseInt(apendice)+1;
			
	}
	
	var dlgRevocacion_save = function() {

		var revocados = $("#PGForm_DlgRevocacion_grid_Revocados").jGridContentMVC_GetList();
		
		
				
		for ( var i in revocados) {
			var apod = revocados[i];
			if(apod.ind_status == 3){
				apod.Revocar = null;
				apod.ind_status = 1;
			}else if (apod.ind_status == 2 || apod.ind_status == 4) {
				var strAux = "";
				apod.ind_status = 2; 
				apod.Revocar.Concepto = apod.Revocar.Concepto == null ? apod.Revocar.ind_razonrevoca : apod.Revocar.Concepto; 
				apod.Revocar.Escritura = apod.Revocar.Escritura == null ? apod.Revocar.id_escriturarevoca_fk : apod.Revocar.Escritura;
				apod.Revocar.Fecha = apod.Revocar.Fecha == null ? apod.Revocar.fec_revoca : apod.Revocar.Fecha;
				apod.Revocar.NoDoc = apod.Revocar.NoDoc == null ? apod.Revocar.id_documentumrevoca : apod.Revocar.NoDoc;
				
				switch (apod.Revocar.Concepto) {
				case '1':
				case 1:
					
					
					strAux += ' Mandato termin\u00F3 por: Revocaci\u00F3n en Esc. No. <a docId="'
						+apod.Revocar.NoDoc+'" class="ApodDetail" title="click para ver escritura" href="#doc"> '
							+ apod.Revocar.Escritura
							+ '</a> del '
							+ apod.Revocar.Fecha;
					
					
					
					break;
				case '2':
				case 2:
					strAux += " Mandato Termin\u00F3 por: Muerte "
							+ apod.Revocar.Fecha;
					break;
				case '3':
				case 3:
					strAux += " Mandato Termin\u00F3 por Vigencia "
							+ apod.Revocar.Fecha;
					break;
				case '4':
				case 4:
					strAux += " Mandato termin\u00F3 por: Se agot\u00F3 el objeto";
					break;
				case '5':
				case 5:
					strAux += ' Mandato termin\u00F3 por: <a docId="'
						+apod.Revocar.NoDoc
						+'" class="ApodDetail" title="click para ver carta de renuncia" href="#doc"> Carta Renuncia </a> de fecha '
						+ apod.Revocar.Fecha;
					break;
				}
				apod.desc_revoca = strAux;

				if(apod.ind_aprevoca == null)
					apod.ind_aprevoca = getApendice(apod);				
				

				var apeIndex = '<label style="color:red;">' + apod.ind_aprevoca + '</label>';
				apod.desc_revoca = apeIndex + " " + apod.desc_revoca;
			}
		}

		var workBean = $(dlgRevocacion).data('WorkBean');
		var list = workBean.Apoderados;
		var ga = list.length == 0 ? "" : list[0].des_grupo;
		var names = ga == '' ? '' : '<div style="font-weight: bold;">' + ga
				+ '</div>';
		$(list).each(function(i, bean) {

							var g = bean.des_grupo;
							if (g != ga) {
								names += ga == '' ? ''
										: '<div style="font-weight: bold;">'
												+ g + '</div>';
								ga = g;
							}

							names += '&#9679; \t '
									+ (bean.value == undefined ? bean.desc_nom_empl
											: bean.value)
									+ (bean.ind_status == 2 ? ' <label style="color:red;">'
											+ bean.ind_aprevoca + '</label>'
											: '') + '<br /> ';

						});

		workBean.desc_apoderados = '<b>' + workBean.des_podertipo + '</b><br><br>' + names;

		$("#PGForm_Grid_apoderados").jGridContentMVC_UpDate();
		dlgRevocacion.dialog('close');
	}

	this.eChange_ind_delegado_por = function(bean, value, _this, ui, event) {

	}
	
	this.eEnterKeyPress_notario_publico = function(value, _this, event){				
		WebForm.setValue("PGForm_num_licenciado",value,'atributo1');
		$("#PGForm_num_licenciado").change();
	}
	
	this.eChange_num_licenciado = function(bean, value, _this, ui, event) {
		if (bean == null) {
			$("#PGForm_nom_notario_publico").val("");
			$("#PGForm_num_de").val(-1);
			return;
		}
		$("#PGForm_nom_notario_publico").val(bean.atributo1);
		var val = bean.atributo2 == '0' || !bean.atributo2
				|| bean.atributo2 == "" ? "-1" : bean.atributo2;

		$("#PGForm_num_de").val(val);

	}

	this.eChange_cmb_vigencia = function(bean, value, _this, ui, event) {

		var op = bean.key;
		switch (op) {
		case '0':
			$("#PGForm_DlgApoderados_spi_vigencia").spinner("option",
					"disabled", true);
			$("#PGForm_DlgApoderados_calendar_vigenciaInicio").datepicker(
					"option", {
						disabled : true
					});
			$("#PGForm_DlgApoderados_calendar_vigenciaFin").datepicker(
					"option", {
						disabled : true
					});
			$("#PGForm_DlgApoderados_calendar_vigenciaFin").val("");
			$("#PGForm_DlgApoderados_calendar_vigenciaInicio").val("");
			$("#PGForm_DlgApoderados_spi_vigencia").val("");
			break;
		case '3':
			$("#PGForm_DlgApoderados_calendar_vigenciaInicio").datepicker(
					"option", {
						disabled : false
					});
			$("#PGForm_DlgApoderados_spi_vigencia").spinner("option",
					"disabled", true);
			$("#PGForm_DlgApoderados_calendar_vigenciaFin").datepicker(
					"option", {
						disabled : false
					});
			$("#PGForm_DlgApoderados_calendar_vigenciaFin").val("");
			break;
		default:
			$("#PGForm_DlgApoderados_calendar_vigenciaInicio").datepicker(
					"option", {
						disabled : false
					});
			$("#PGForm_DlgApoderados_spi_vigencia").spinner("option",
					"disabled", false);
			$("#PGForm_DlgApoderados_calendar_vigenciaFin").datepicker(
					"option", {
						disabled : true
					});
			$("#PGForm_DlgApoderados_calendar_vigenciaFin").val("");
			break;
		}
	}

	this.eChange_cmb_Escritura = function(bean, value, _this, ui, event) {
		if (value == '-1') {
			$("#PGForm_DlgRevocacion_cmb_Escritura_txt_text").val("").attr(
					'disabled', false);
			$("#PGForm_DlgRevocacion_cmb_Escritura_date_fecha").val("")
					.datepicker("option", "disabled", false);
		} else {
			$("#PGForm_DlgRevocacion_doc_Documento_digital_text").val(
					bean.num_documentum_instr).attr('disabled', true);
			
			$("#PGForm_DlgRevocacion_cmb_Escritura_date_fecha").val(
					bean.fec_fecha).datepicker("option", "disabled", true);
		}

	}

	this.eChange_cmb_razonRevocacion = function(bean, value, _this, ui, event) {
		switch (value) { 
		case '-1':
        	$("#PGForm_DlgRevocacion_cmb_Escritura").val("-1").attr("disabled", true );
        	$("#PGForm_DlgRevocacion_doc_Documento_digital_text").val("").attr('disabled',true);
    		$("#PGForm_DlgRevocacion_cmb_Escritura_date_fecha").val("").datepicker( "option", "disabled", true );
        	break;
        case '1':
        	$("#PGForm_DlgRevocacion_cmb_Escritura").val("-1").attr( "disabled", false );
        	$("#PGForm_DlgRevocacion_doc_Documento_digital_text").val("").attr('disabled',true);
    		$("#PGForm_DlgRevocacion_cmb_Escritura_date_fecha").val("").datepicker( "option", "disabled", true );
        	break;        
        case '2':
        	$("#PGForm_DlgRevocacion_cmb_Escritura").val("-1").attr( "disabled", true );
        	$("#PGForm_DlgRevocacion_doc_Documento_digital_text").val("").attr('disabled',true);
    		$("#PGForm_DlgRevocacion_cmb_Escritura_date_fecha").val("").datepicker( "option", "disabled", false );
        	break;
        case '3':
        	$("#PGForm_DlgRevocacion_cmb_Escritura").val("-1").attr( "disabled", true );
        	$("#PGForm_DlgRevocacion_doc_Documento_digital_text").val("").attr('disabled',true);
    		$("#PGForm_DlgRevocacion_cmb_Escritura_date_fecha").val("").datepicker( "option", "disabled", false );
        	break;
        case '4':
        	$("#PGForm_DlgRevocacion_cmb_Escritura").val("-1").attr( "disabled", true );
        	$("#PGForm_DlgRevocacion_doc_Documento_digital_text").val("").attr('disabled',true);
    		$("#PGForm_DlgRevocacion_cmb_Escritura_date_fecha").val("").datepicker( "option", "disabled", true );
        	break;
        case '5':
        	$("#PGForm_DlgRevocacion_cmb_Escritura").val("-1").attr( "disabled", true );
        	$("#PGForm_DlgRevocacion_doc_Documento_digital_text").val("").attr('disabled',false);
    		$("#PGForm_DlgRevocacion_cmb_Escritura_date_fecha").val("").datepicker( "option", "disabled", false );
        	break;
	}
	}
	
	this.eChange_spn_vigenciatiempo  = function(bean, value, _this, ui, event) {
		var op = $("#PGForm_DlgApoderados_cmb_vigencia").val();
		if($("#PGForm_DlgApoderados_calendar_vigenciaInicio").val()=='')return;
		var element = $("#PGForm_DlgApoderados_calendar_vigenciaInicio").attr('toField');		
		var currentDate = $("#PGForm_DlgApoderados_calendar_vigenciaInicio").datepicker("getDate");
		
		switch (op) {
		case '1':
			
			var date = WebKernel.addMonths(currentDate, value * 12,
					'dd/mm/yyyy');
			$("#" + element).val(date);
			break;
		case '2':
			
			var date = WebKernel
					.addMonths(currentDate, value, 'dd/mm/yyyy');

			$("#" + element).val(date);
			break;
		default:
			$("#" + element).datepicker("option", "minDate",
					dpConfigTo.getDate(this));
		}
	}
		
	this.eChange_chk_GroupPoderes = function(check, input, ui, event){
		WorkGroups = [];
		
		if(check){
			if($("#PGForm_DlgApoderados_Grid_gapoderSelecc").jGridContentMVC_GetList().length == 0){
				$("#PGForm_DlgApoderados_Div_gapoderSelecc").html('');
				  $("#PGForm_DlgApoderados_Grid_gapoderSelecc").jGridContentMVC_UpDate([]);
				  return;
			}
			swal({
				title: "Cambio de agrupaci\u00F3n de apoderados seleccionados",
				  text: "Al cambiar de modo agrupaci\u00F3n a modo normal se perder\u00E1n los apoderados seleccionados, \u00BFDesea continuar?",
				  type: "warning",
				  showCancelButton: true,
				  confirmButtonColor: "#DD6B55",
				  confirmButtonText: "Si",
				  cancelButtonText: "No",
				  closeOnConfirm: true,
				  closeOnCancel: true
				},
				function(isConfirm){
				  if (isConfirm) {
					  $("#PGForm_DlgApoderados_Div_gapoderSelecc").html('');
					  $("#PGForm_DlgApoderados_Grid_gapoderSelecc").jGridContentMVC_UpDate([]);
				  }else
					  WebForm.updController('PGForm_DlgApoderados_chk_GroupPoderes', false);
				});
		}else{
			if($("#PGForm_DlgApoderados_Div_gapoderSelecc").html() == ''){
				$("#PGForm_DlgApoderados_Div_gapoderSelecc").html('');
				$("#PGForm_DlgApoderados_Grid_gapoderSelecc").jGridContentMVC_UpDate([]);
				return;
			}
			swal({
				  title: "Cambio de agrupaci\u00F3n de apoderados seleccionados",
				  text: "Al cambiar de modo normal a modo agrupaci\u00F3n se perder\u00E1n los apoderados seleccionados, \u00BFDesea continuar?",
				  type: "warning",
				  showCancelButton: true,
				  confirmButtonColor: "#DD6B55",
				  confirmButtonText: "Si",
				  cancelButtonText: "No",
				  closeOnConfirm: true,
				  closeOnCancel: true
				},
				function(isConfirm){
				  if (isConfirm) {
					  $("#PGForm_DlgApoderados_Div_gapoderSelecc").html('');
					  $("#PGForm_DlgApoderados_Grid_gapoderSelecc").jGridContentMVC_UpDate([]);
				  }else
					  WebForm.updController('PGForm_DlgApoderados_chk_GroupPoderes', true);
				});
		}
		
	}

	var initDlgCopy = function() {
		
		$("#PGForm_DlgCopy_CopyType").change(function(){
			if($(this).prop('checked')){
				$(".divCopyType").hide();
			}else{
				$(".divCopyType").show();
			}
		});
		
		dlgCopy = $(dlgCopy).dialog({
			autoOpen : false,
			height : "auto",
			width : 600,
			modal : true,
			buttons : {
				Cancelar : function() {
					dlgCopy.dialog('close');
				},
				"Copiar" : function() {
					var beanEscritura = dlgCopy.WorkEsc;
					var cnyList = new Array();
					var list = $("#PGForm_DlgCopy_gridSel").jGridContentMVC_GetList();
					for(var i in list)
						cnyList.push(list[i].id);
					var mtype = null;					
					var elementCheck = $("input[name='PGForm_DlgCopy_modeType']:checked");    				
    				   				
					if($("#PGForm_DlgCopy_CopyType").prop("checked")){
						type = "Import";
						mode = "Convert";
						mtype = beanEscritura.ind_tipo_escritura == "PG" ? "PE" : "PG";
					}else if(cnyList.length>0){
						type = "Export";
						mode = "Copy";						
						mtype = $(elementCheck).val();
					}else{
						type = "Import";
						mode = "Copy";
						mtype = $(elementCheck).val();
					}
					
					Request.EscrituraPoderes.cpyEscritura(beanEscritura,cnyList,type,mode,mtype,MetaSession.SessionBean.idUser,{
													response : function(rsp) {
														if(rsp[0]!='Export'){
															var list = Util.getBeanList(rsp[1]);
															try{
															switch(rsp[0]){															
																case 'PG': $("#gridPGmain").jGridContentMVC_UpDate(list); break;
																case 'PE': $("#gridPEmain").jGridContentMVC_UpDate(list); break;
																case 'CP': $("#gridCPmain").jGridContentMVC_UpDate(list); break;
																case 'ER': $("#gridERmain").jGridContentMVC_UpDate(list); break;																
															}
															}catch(e){
																//Modulo no inicializado
															}
														}
														swal("Correcto", "La Escritura Poder se copio correctamente", "success");
														$(".divCopyType").show();
													},
													error : function(xhttp, e) {
								                    	  sweetAlert('No se pudo realizar la operacion', 'Error:' + e,'error');
													},
													complete : function() {
														
													}
												});
					
					dlgCopy.dialog('close');
				}
			}
		});

		$("#PGForm_DlgCopy_btnSel").button({
			icon : "ui-icon-zoomin",
			showLabel : true
		}).click(
				function() {
					WebKernel.multiselectDialog('Seleccione las Empresas', 'EMPRESAS',
							'', {
								publish : function(data) {
									$.each(data, function(i, bean) {
										if (!$("#PGForm_DlgCopy_gridSel").jGridContentMVC_Exists('id',bean.id))
											$("#PGForm_DlgCopy_gridSel").jGridContentMVC_Add(bean);
									});
								},
								metadata : null
							});
				});

		$("#PGForm_DlgCopy_gridSel").jGridContentMVC({
			gridId : "PGForm_DlgCopy_gridSel",
			noDataMsg : 'Copiar en la misma empresa',
			storage : [],
			rowClick : null,
			cellClick : function(iRow, iCell, bean, pty, gridId, td, event) {
				switch (pty) {
					case 'del':
						// Si es Carta Poder, solo se permiten
						// borrar los que no son default
						$("#" + gridId).jGridContentMVC_Delete(iRow);
						break;
				}
			},
			rowCssA : '',
			rowCssB : '',
			rowFinal : null,
			rowAlign : 'center',
			Encabezados : true,
			HeadCss : "HHead",
			Heads : false,
			columns : [ 
{
				HeadText : 'Empresa',
				'width' : '98%',
				id : 'value',
				type : 'action',
				'align' : 'left'
			},
			{
				HeadText : 'Borrar',
				'width' : '2%',
				id : 'del',
				type : 'action',
				'align' : 'center',
				cellValue : 'eval',
				eval : function() {
					return '<div title="borrar documento" class="ui-state-default ui-corner-all" style="width:15px;cursor:pointer"><span class="ui-icon ui-icon-circle-close"/></div>';
				}
			}
						            
			],
			tblCssClass : "",
			tblStyle : "width:100%",
			cellpadding : '0',
			cellspacing : '1',
			border : '0',
			afterDrawing : function() {

			}
		});
	}

	initComponents();
}

var initConsultaPoderes = function() {
	PGModule = new ClassPoderGeneral();
}