var PEModule = null;
var  ID_CATALOGO_APODERADOS = '32';

var ClassPoderEspecial = function () {
    var _this = this;
    var txtPEQuery = $("#txtPEQuery");
    var Animation = false; 
     
    this.engaged = false;           
    var Grid_apoderados = new Array();    
    var Grid_ERmain = new Array();
    
    var dlgApoderadosOtros = "#PEForm_DlgApoderadosOtros";
    var dlgRevocacion = "#PEForm_DlgRevocacion";
    var dlgCopy = "#PEForm_DlgCopy";
        
    var loadDetails_Escritura = function(WorkBean) {
		Request.EscrituraPoderes.getDetailsEscritura(WorkBean.id_ep_pk, {
			response : function(details) {
				
				Grid_apoderados = Util.getBeanList(details[1]);
				
				var Revocados = Util.getBeanList(details[2]);

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

				$("#PEForm_Grid_poderSelecc").jGridContentMVC_UpDate(Grid_apoderados);
				$("#PEForm_Grid_apoderados").jGridContentMVC_UpDate(Grid_apoderados);	
				
			},
			error : function(xhttp, e) {
          	  sweetAlert('No se pudo realizar la operacion', 'Error:' + e,'error');
			},
			complete : function() {

			}
		});
	}

    
    var getSemaforoRP = function(bean){
    	
    	if(!bean.ind_requiere_proto){
    		return 'Img_Semaforo_gray';
    	}
    	else if(bean.des_escritura == null || bean.num_documentum_instr == null 
    		|| bean.fec_otorgamiento_instr == null || bean.num_licenciado == "-1"){ 
    		return 'Img_Semaforo_red';
    	}
    	else{
    		return 'Img_Semaforo_green';
    	}
    }
    
    var getSemaforoRPPC = function(bean){
    	
    	if (!bean.ind_requiere_inscr_rppc || bean.ind_requiere_inscr_rppc == '0' || bean.ind_requiere_inscr_rppc == '' )
    		return 'Img_Semaforo_gray';
    	
    	if (bean.fec_registro == "" || bean.fec_registro == null || bean.num_folio_merc == null || bean.num_folio_merc.trim() == "") 
    		return 'Img_Semaforo_red';
    	else
    		return 'Img_Semaforo_green';
    }
   
    var initComponents = function () {
    	var msAux = null;
    	try{
    		var mstr = $("#MetaSession").html();    		
    		msAux = $.parseJSON(mstr);
    	}catch(e){
    		var mstr = $("#MetaSessionHdn").val();
    		msAux = $.parseJSON(mstr);
    	}
    	MetaSession = new Object();
    	MetaSession.SessionBean = msAux[0];    	
    	MetaSession.Context = msAux[1];
    	
    	// Obtener contexto de la Sesion y asignarlo al RequestModel
    	Request.EscrituraPoderes.handler = Request.EscrituraPoderes.handler;
    	
    	$("#divPGForm").data('MetaWork',{WorkDiv:'#divPGMain',Type:'PG',Mode:'new',WorkBean:null});
    	
    	$("#PEForm_Btn_poderOrder").button({
	        icon: "ui-icon-arrowthickstop-1-s"
	      }).click(function(){
	    	  var list = $("#PEForm_Grid_apoderados").jGridContentMVC_GetList();
	    	  var listAux = new Array();
	    	  for(var i=0; i < list.length; i++){
	    		  var index = i+1;
	    		  for(var l in list){
	    			  if(index == parseInt(list[l].num_order)){
	    				  listAux.push(list[l]);
	    				  break;
	    			  }
	    				  
	    		  }
	    	  }
	    	  Grid_apoderados = listAux;
	    	  $("#PEForm_Grid_apoderados").jGridContentMVC_UpDate(Grid_apoderados);
	    	  $("#PEForm_Grid_poderSelecc").jGridContentMVC_UpDate(Grid_apoderados);
	    	  
	      });
    	
    	//Boton de busqueda de un Poder Especial
    	$("#btnPEQuery").click(function(){
        	var palabraABuscar = $(txtPEQuery).val().trim();        	
        	Request.EscrituraPoderes.getEscrituraPoder(MetaSession.SessionBean.idCurrentEmpresa,'PE', palabraABuscar,{
                response: function (rsp) {
                	var Grid_escrituras = Util.getBeanList(rsp[0]); 
                	$("#gridPEmain").jGridContentMVC_UpDate(Grid_escrituras);
                }, error: function (xhttp, e) {
              	  sweetAlert('No se pudo realizar la operacion', 'Error:' + e,'error');
                }, complete: function () {
                	
                }
            });
        	        	
         });
    	
    	//Deteccion de Enter al buscar en Poderes Especiales
		$("#txtPEQuery").keypress(function(e){    		
    		if(e.which == 13 || event.keyCode == 13) {
    			$("#btnPEQuery").click();
    		}        	        	
         });
                
        $("#btnPEAdd").click(function(){
        	var div = $(this).attr('divHide');
        	var mode = $(this).attr('openFormMode');
        	var type = $(this).attr('openFormType'); 
        	//Limpiar datos anteriores
        	$("#PEForm_Grid_apoderados").jGridContentMVC_UpDate([]);
        	Grid_apoderados = new Array();
        	$("#PEForm_Grid_poderSelecc").jGridContentMVC_UpDate([]);
        	$("#PEForm_lbl_apoderados_revocados").html('');
        	PGModule.openFormEscritura(mode,type,div);
        });
    	
        $("#gridPEmain").jGridContentMVC({ 
          gridId: "gridPEmain",
          noDataMsg: 'no hay registros para mostrar',
          storage: null,
          rowClick: null,
          rowMaxHeight:'400px',
          cellClick: function (iRow, iCell, bean, pty, gridId, td, event) {
              switch (pty) {
              case 'del':                
              	swal({
					  title: "Eliminar Poder Especial",
					  text: "Estas seguro que quieres eliminar este Poder Especial?",
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
						  Request.EscrituraPoderes.deleteEscrituraPoder(MetaSession.SessionBean.idCurrentEmpresa,'PE',bean.id_ep_pk, MetaSession.SessionBean.idUser, {
	                          response: function (rsp) {
	                        	  if (_this.engaged)
	  								return;
	  							$("#gridPEmain").jGridContentMVC_Delete(iRow);
	  							$("#gridPEmain").jGridContentMVC_UpDate();
	                          	                          	
	                          }, error: function (xhttp, e) {
		                    	  sweetAlert('No se pudo realizar la operacion', 'Error:' + e,'error');
	                          }, complete: function () {
	                        	  swal("Correcto", "La Escritura Poder se elimino correctamente", "success");
	                          }
	                      });
					  }else
						  return;
					});

                  break;
              case 'edit':
            	  PGModule.openFormEscritura('edit',bean.ind_tipo_escritura,'#div'+bean.ind_tipo_escritura+'Main',bean);            	              	  
            	  loadDetails_Escritura(bean);            	  
              	break;
              case 'copy':            	  
            	  CopyMng.open(bean,function(){});
              	break;
              default:

                  break;
              }
          },
          rowCssA: 'odd',
          rowCssB: 'even',
          rowFinal: null,
          rowAlign: 'center',
          Encabezados: true,
          HeadCss: "HHead",
          Heads: true,
          columns: [
		                              { HeadText: 'Escritura No.', 'width': '5%', id: 'des_escritura', 'align': 'left' }
		                            , { HeadText: 'Fecha', 'width': '10%', id: 'fec_fecha_escritura', 'align': 'center' }
		                            , { HeadText: 'Apoderados', 'width': '35%', id: 'desc_apoderados', type: 'action', 'align': 'left' }
                                    , { HeadText: 'Asunto', 'width': '15%', id: 'desc_asunto', type: 'action', 'align': 'left' }
                                    , { HeadText: 'Escritura', 'width': '5%', id: 'C8', type: 'action', 'align': 'center', cellValue: 'eval', eval: function (bean) {
                                    	 var sem = getSemaforoRP(bean);
                                    	 return '<div class="' + sem + '"></div>';
                                    }
                                    }
                                    , { HeadText: 'RPPC', 'width': '5%', id: 'C4', type: 'action', 'align': 'center', cellValue: 'eval', eval: function (bean) {
                                    	var sem = getSemaforoRPPC(bean);
                                    	return '<div class="' + sem + '"></div>';
                                    }
                                    }         
                                    , { HeadText: 'Copiar', 'width': '5%', id: 'copy', type: 'action', 'align': 'center', cellValue: 'eval', eval: function () {
                                        return '<div class="Img_Icon_update"></div>';
                                    }
                                    }
                                    , { HeadText: 'Editar', 'width': '5%', id: 'edit', type: 'action', 'align': 'center', cellValue: 'eval', eval: function (bean) {
                                    	return '<div id=editEsc'+bean.id_ep_pk+' class="Img_Icon_edit"></div>';
                                    }
                                    }
                                    , { HeadText: 'Borrar', 'width': '5%', id: 'del', type: 'action', 'align': 'center', cellValue: 'eval', eval: function () {
                                    	return '<div class="Img_Icon_delete"></div>';
                                    }
                                    }
                                    ],
          tblCssClass: "tablesorter",
          tblStyle: "width:100%",
          cellpadding: '0',
          cellspacing: '1',
          border: '0',
          afterDrawing: function () {
              
          }
      });
        
        initForm();
        initTextEditors();
    }
    
    var initForm = function(){
    	
    	$("#PEForm_Btn_poderSave").button({
            icon: "ui-icon-disk"}).click(function () {
                
            	  var mw =	$("#divPGForm").data('MetaWork');
                  var bean = mw.WorkBean;
                  if(mw.Mode=='new')
                  	bean = WebForm.getBean('divPGForm');
                  else
                  	bean = WebForm.getBean('divPGForm',bean);
                  
                  bean.C4 = $("#ind_requiere_inscr_rppc").is(":checked") ? "Si" : "No";
                  bean.esc = $("#des_escritura").val();
                  bean.documentum = $("#num_documentum_memo").val();
                  bean.desc_apoderados = '';
                  bean.desc_asunto = '';
               
                  if(Grid_apoderados.length > 0){
						var ga = Grid_apoderados.length == 0 ? ""
								: Grid_apoderados[0].des_podertipo;
						
						bean.desc_apoderados = '<div style="font-weight: bold;">'
							+ ga + '</div><br/>';
						
						$.each(Grid_apoderados,function(i, apo) {
											var g = apo.des_podertipo;
											if (g != ga) {
												bean.desc_apoderados += '<div style="font-weight: bold;">'
														+ g + '</div><br/>';
												ga = g;
											}

											bean.desc_asunto += '&#9679; '
													+ apo.des_podertipo
													+ '<br /> ';
											var desc_apoderados = apo.desc_apoderados;
											bean.desc_apoderados += '\t'
													+ desc_apoderados
													+ '<br /> ';
											bean.desc_apoderados = bean.desc_apoderados.replace('null','');
										});
						bean.desc_apoderados = bean.desc_apoderados.replace('null','');
						bean.desc_asunto = bean.desc_asunto.length > 2000 ? bean.desc_asunto.substring(0,1990)+'...':bean.desc_asunto;
					}
          		//Se guarda el grid de los apoderados en el bean 
                   bean.Apoderados = Grid_apoderados;
                   
                  if(mw.Mode=='new')
                  	$("#grid"+mw.Type+"main").jGridContentMVC_Add(bean);
                  else
                  	$("#grid"+mw.Type+"main").jGridContentMVC_UpDate();
                  
                  $("#divPGFormBtnClose").click();
                  
                  bean.id_empresa = MetaSession.SessionBean.idCurrentEmpresa;
                  bean.num_created_by = MetaSession.SessionBean.idUser;
                  var Grid_documentums = $("#PGForm_Grid_Documents").jGridContentMVC_GetList();
                  if (mw.Mode == 'new') {
	                  Request.EscrituraPoderes.newEscritura(bean,Grid_documentums,MetaSession.SessionBean.idUser,{
	                      response: function (rsp) {
	                    	  var list = Util.getBeanList(rsp);
	                    	  $("#gridPEmain").jGridContentMVC_UpDate(list);
	                      }, error: function (xhttp, e) {
	                    	  sweetAlert('No se pudo realizar la operacion', 'Error:' + e,'error');
	                      }, complete: function () {
	                    	  swal("Correcto", "La Escritura Poder se guardo correctamente", "success");
	                      }
	                  });
                  }
                  else{
                	  bean.ind_status_esc = 0;
						bean.ind_status_rppc = 0;
						Request.EscrituraPoderes.updEscritura(bean, Grid_documentums, MetaSession.SessionBean.idUser,{
							response : function(rsp) {
								var list = Util.getBeanList(rsp);
								$("#gridPEmain").jGridContentMVC_UpDate(list);
							},
							error : function(xhttp, e) {
								sweetAlert('No se pudo realizar la operacion', 'Error:' + e,'error');
							},
							complete : function() {
								swal("Correcto", "La Escritura Poder se modifico correctamente", "success");
							}
						});
                  }
    	});
    	
    	 $("#tabsPoderes").tabs({
       	  beforeActivate: function( event, ui ) {
       		  $("#divPEFormBtnClose").click();        		  
       	  }
       });    	       
       
       $("#btnPEAdd").click(function(){
       	var div = $(this).attr('divHide');
       	var mode = $(this).attr('openFormMode');
       	var type = $(this).attr('openFormType');        	
       	PGModule.openFormEscritura(mode,type,div);
       });
    	
    	$("#divPEFormBtnClose").button().click(function(){
    		$("#divPEForm").hide();
        	$("#divPEMain").show(); 		
    	});
    	 	
    	$("#PEForm_Btn_poderSelecc").click(function(){
    		
    		dlgApoderadosOtros.open('new');
    	});
   
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
            HeadCss: "HHead",
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
            rowMaxHeight:'400px',
            cellClick: function (iRow, iCell, bean, pty, gridId, td, event) {
                switch (pty) {
                    case 'del':
                        if (_this.engaged) return;                                          
                        $("#PEForm_Grid_apoderados").jGridContentMVC_Delete(iRow);
                        for(var i=0; i < Grid_apoderados.length; i++){
				    		  var index = i+1;
				    		  Grid_apoderados[i].num_order = index;							    		  
				    	  }
                        $("#PEForm_Grid_poderSelecc").jGridContentMVC_UpDate();
                        break;
                    case 'otorgar':
                    	var tieneRevocados = false;
						for (var i = 0; i < bean.Apoderados.length; i++) {
							var apod = bean.Apoderados[i];
							if(apod.ind_status == 2){
								tieneRevocados = true;
								break;
							}
						}
						if(!tieneRevocados)
							dlgApoderadosOtros.open('edit', bean);
						else{
							swal({
								  title: "Editar Poder con Revocados",
								  text: "Este poder contiene revocaciones, si continua se perder\u00E1n, \u00BFDesea continuar?",
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
									  dlgApoderadosOtros.open('edit', bean);
								  }else
									  return;
								});
						}
                    	break;
                    case 'revocar':
                    	var puedeRevocar = true;
						if(bean.id_opoder_ep_pk==null){										
							puedeRevocar = false;																
						}
						else{
							for (var i = 0; i < bean.Apoderados.length; i++) {
								var apod = bean.Apoderados[i];											
								if(apod.id_apod_ep_pk == null){
									puedeRevocar = false;	
									break;
								}
							}																						
						}
						if(puedeRevocar)
							dlgRevocacion.open(bean);
						else
							sweetAlert("No puede revocar apoderados sin guardar", "Este poder contiene apoderados que no se han guardado, guarde antes de revocar",'warning');
                    	break;
                    default:
                        
                        break;
                }
            },
            rowCssA: 'odd',
            rowCssB: 'even',
            rowFinal: null,
            rowAlign: 'center',
            Encabezados: true,
            HeadCss: "HHead",
            Heads: true,
            columns: [
									{
										HeadText : 'Orden No.',
										'width' : '3%',
										id : 'num_order',
										'align' : 'left',
										cellValue : 'eval',
										eval : function(bean,id,column,iCell,iRow,config) {
												//return bean.num_order;
												var classIdlbl = 'label_'+config.gridId+'_num_order';
												var classIdInp = 'text_'+config.gridId+'_num_order';
												var id = config.gridId+'_num_order' + '_' + iRow + iCell;
												var ref = ' iRow="'+iRow+'" iCell="'+iCell+'" gridId="'+config.gridId+'"';
												var out = '<div id="lbl'+id+'" '+ref+ ' class="'+classIdlbl+'">'+bean.num_order+'</div>';
												 out+='<input id="text'+id+'" '+ref+ ' class="'+classIdInp+'" type="text" style="width:15px; display:none" value="'+bean.num_order+'" />';
												return out;
											}
									}  		                              		                              		                            
                                    , { HeadText: 'Poder', 'width': '8%', id: 'des_podertipo', type: 'action', 'align': 'left' }
                                    , {HeadText: 'Apoderados', 'width': '22%', id: 'desc_apoderados', type: 'action', 'align': 'left'}
  		                            , { HeadText: 'Descripcion', 'width': '20%', id: 'des_poder', type: 'action', 'align': 'left' }                                  
                                   /* , { HeadText: 'Caracter\u00EDsticas', 'width': '16%', id: 'desc_caracteristicas', type: 'action', 'align': 'left' }*/
  		                          ,{
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
											
										}}                                      
                                    , { HeadText: 'Otorgar', 'width': '5%', id: 'otorgar', type: 'action', 'align': 'center', cellValue: 'eval', eval: function () {
                                          return '<div class="Img_Icon_edit"></div>';
                                    }
                                    }
                                    , { HeadText: 'Revocar', 'width': '5%', id: 'revocar', type: 'action', 'align': 'center', cellValue: 'eval', eval: function () {
                                          return '<div class="Img_Icon_update"></div>';
                                    }
                                    }
                                    , { HeadText: 'Borrar', 'width': '5%', id: 'del', type: 'action', 'align': 'center', cellValue: 'eval', eval: function () {
                                      	return '<div class="Img_Icon_delete"></div>';
                                    }
                                    }
                                    ],
            tblCssClass: "tablesorter",
            tblStyle: "width:100%",
            cellpadding: '0',
            cellspacing: '1',
            border: '0',
            afterDrawing: function (config) {      
            	$('.label_'+config.gridId+'_num_order').css('cursor','pointer').click(function(){
					var id = this.id.replace('lbl','text');
					$(this).hide();
					$("#"+id).show();
				});
				$('.text_'+config.gridId+'_num_order').css('cursor','pointer').keypress(function(event){
					var _this = this;
					var close = function(){
						var id = _this.id.replace('text','lbl');
						$(_this).hide();
						$("#"+id).show();
						$(_this).val($("#"+id).html());
					}
					
					if ( event.which == 13 ){
					    var value = $(this).val();									    
						var iRow = $(this).attr('iRow');
					    var gridId = $(this).attr('gridId');
					    var list = $("#"+gridId).jGridContentMVC_GetList();
					    if(isNaN(value) || list.length == 1 || value > list.length || value == list[iRow].num_order){
					    	close();
					    	return;
					    }
						
					    for(var i in list){
					    	if(list[i].num_order == value){
					    		list[i].num_order = list[iRow].num_order;
					    		list[iRow].num_order = value;
					    	}
					    }
					    
					    $("#"+gridId).jGridContentMVC_UpDate();
					}
				});
            	
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
				$("#PEForm_lbl_apoderados_revocados").html(strA);

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
    	
    	initDlgPoderes();
    	initDlgApoderados();
    	initDlgApoderadosOtros();
    	initDlgRevocacion();
    	initDlgCopy();
    }
    
    
    var initDlgPoderes = function(){
    	    	
    	$("#PEForm_Grid_apoderadosOtro").change(function(){
        	var check = $(this).is(":checked");
    		if(check){ 
    			$("#PEForm_Btn_poderAddCarta").show();
    			$("#PEForm_DlgPoderes_Chk_selecTodos").prop("checked",false);
            	$(".PEForm_Grid_apoderados").each(function(i,input){
        			if($(input).val() != "Otro" )            				
        				$(input).prop("checked",false);
        		});	
    		}else
    			$("#PEForm_Btn_poderAddCarta").hide();
        });
    }
    
    var ClassPoder = function(){
    	var _this = this;
		this.id_ep_fk = null;
		this.id_opoder_ep_pk = null;
		this.des_podertipo = arguments[0];
		this.des_poder = arguments[1] ? arguments[1] : arguments[0];
		this.num_order = $("#PEForm_Grid_apoderados").jGridContentMVC_GetList().length + 1;		
    	this.C3 = "";
    	this.C4 = arguments[1];
    	this.C6 = "No tienen";
    	this.C7 = "Excepto hacer cesi\u00F3n de bienes";
    	this.C8 = "";
    	this.C9 = "";
    
    }
    
    var ClassApoderado = function() {
		var _this = this;

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
		_this.pinnum_created_by = 0;
		_this.id_apod_ep_pk = 0;

	}
             
    var WorkGroups = [];
    
    var loadGroupApoderados = function(data) {
    	/*if(data.length>100){
			swal("Cantidad de Apoderados superada", "Supera la cantidad m\u00E1xima permitida de apoderados, por favor seleccione hasta 100", "warning");
			return;
		}*/
		var isgroup = WebForm.getKeyValue('PEForm_DlgApoderados_chk_GroupPoderes');
		var group = {
			key : 0,
			value : ''
		};
		if (isgroup.value)
			group = WebForm.getKeyValue('PEForm_DlgApoderados_gapoder');

		if(group.key==null){
			sweetAlert('No se pudo realizar la operacion', 'Debe seleccionar un grupo primero');
			return;
		}				

		var adds = 0;
		$.each(data, function(i, bean) {
			bean.id_grupo_fk = group.key;
			bean.des_grupo = group.value;
			if ($("#PEForm_DlgApoderados_Grid_gapoderSelecc").jGridContentMVC_Exists('id', bean.id))
				return;
			adds++;
			$("#PEForm_DlgApoderados_Grid_gapoderSelecc").jGridContentMVC_Add(bean);			

		});
		
		if (isgroup.value)
			drawGoupApoderados();
	}
    var WorkGroupsCount = 0;
    var drawGoupApoderados = function(){
		var group = {
				key : 0,
				value : ''
			};
		var list = $("#PEForm_DlgApoderados_Grid_gapoderSelecc").jGridContentMVC_GetList();
		for(var i in list){
			var bean = list[i];
			group.key=bean.id_grupo_fk;
			group.value=bean.des_grupo;
			
			if (!WorkGroups[group.key]) {
				WorkGroupsCount++;
				WorkGroups[group.key] = {grid:"#PEForm_DlgApoderados_Grid_gapoderSelecc_"	+ group.key,index:WorkGroupsCount};				
				$("#PEForm_DlgApoderados_Div_gapoderSelecc").append(
								'<div id="PEForm_DlgApoderados_Grid_groupDiv_'+group.key+'">'
										+ '<label id="PEForm_DlgApoderados_Grid_groupLabel_'+group.key+'" title="ocultar/mostrar" style="cursor:pointer;font-weight:bold;">'+(group.value==null?'':group.value)+'</label>'
										+ '</div><div id="PEForm_DlgApoderados_Grid_gapoderSelecc_'
										+ group.key + '"></div>');
				
				$("#PEForm_DlgApoderados_Grid_groupLabel_"+group.key).click(function(){
					var grid = this.id.replace('PEForm_DlgApoderados_Grid_groupLabel_','PEForm_DlgApoderados_Grid_gapoderSelecc_');
					$("#"+grid).toggle('slow');
				});
				
				$("#PEForm_DlgApoderados_Grid_gapoderSelecc_" + group.key)
						.jGridContentMVC(
								{
									gridId : "PEForm_DlgApoderados_Grid_gapoderSelecc_"
											+ group.key,
									noDataMsg : 'no hay registros para mostrar',
									storage : [],
									jQSortable:true,
									rowClick : null,
									eRemove : function(irow,bean,gridId,event) {
										
										var list = $("#"+gridId).jGridContentMVC_GetList();
										if(list.length <= 0){
											var divId = gridId.replace('PEForm_DlgApoderados_Grid_gapoderSelecc_','PEForm_DlgApoderados_Grid_groupDiv_');												
											$("#"+divId).remove();
											$("#"+gridId).remove();
											var groupKey = gridId.replace('PEForm_DlgApoderados_Grid_gapoderSelecc_','');
											
											if(WorkGroupsCount>1){																																						
												WorkGroupsCount--;
												var tempWorkGroups = WorkGroups;
												WorkGroups = [];
												var c = 1;
												for(key in tempWorkGroups){
													if(key!=groupKey){
														WorkGroups[key] = tempWorkGroups[key];
														WorkGroups[key].index = c;
														c++;
													}
												}
											}
											else{
												WorkGroups = [];
												WorkGroupsCount = 0;
											}
											return;
										}
										var lblId = gridId.replace('PEForm_DlgApoderados_Grid_gapoderSelecc_','PEForm_DlgApoderados_Grid_groupLabel_');											
										$("#"+lblId).html(list[0].des_grupo+'('+list.length+')');
																					
										
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
			
			if ($("#PEForm_DlgApoderados_Grid_gapoderSelecc_" + group.key).jGridContentMVC_Exists('id', bean.id))
				continue;
			$("#PEForm_DlgApoderados_Grid_gapoderSelecc_" + group.key).jGridContentMVC_Add(bean);
			}
		
		var listAux = new Array();
		for(var key in WorkGroups){

			var groupKey = WorkGroups[key].grid.replace('#PEForm_DlgApoderados_Grid_gapoderSelecc_','')
			var list = $(WorkGroups[key].grid).jGridContentMVC_GetList();
			
			if(list.length <= 0){
				$("#PEForm_DlgApoderados_Grid_groupDiv_"+ groupKey).remove();
				continue;
			}
			
			$("#PEForm_DlgApoderados_Grid_groupLabel_"+groupKey).html(list[0].des_grupo+'('+list.length+')');
			listAux = listAux.concat(list);
		}
		
		$("#PEForm_DlgApoderados_Grid_gapoderSelecc").jGridContentMVC_UpDate(listAux);
		
	}
    
    var initDlgApoderados = function () {
        $("#PEForm_DlgApoderados_Grid_gapoderSelecc").jGridContentMVC({
        	gridId: "PEForm_DlgApoderados_Grid_gapoderSelecc",
            noDataMsg: 'no hay registros para mostrar',
            jQSortable:true,
            storage: [],
            rowClick: null,
            cellClick: function (iRow, iCell, bean, pty, gridId, td, event) {
                switch (pty) {
                    case 'del':
                        $("#PEForm_DlgApoderados_Grid_gapoderSelecc").jGridContentMVC_Delete(iRow);

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
            HeadCss: "HHead",
            Heads: false,
            columns: [{ HeadText: 'Poderes', 'width': '95%', id: 'value',isUndefined:'desc_nom_empl', type: 'action', 'align': 'left' }
                     ,{ HeadText: 'Poderes', 'width': '5%', id: 'del', type: 'action', 'align': 'left', cellValue: 'eval', eval: function (bean) {
                        	return '<div class="ui-state-default ui-corner-all" style="width:15px;"><span class="ui-icon ui-icon-circle-close"/></div>';
                       		}
                      }
            ],
            tblCssClass: "",
            tblStyle: "width:100%",
            cellpadding: '0',
            cellspacing: '1',
            border: '0',
            afterDrawing: function () {

            }
         });
    	
        $("#PEForm_DlgApoderados_btn_orderAllapoder").button({
			icon : "ui-icon-zoomin",
			showLabel : true
		}).click(function(){
			var isgroup = WebForm.getKeyValue('PEForm_DlgApoderados_chk_GroupPoderes');			
			if (isgroup.value){	
				$("#PEForm_DlgApoderados_Grid_gapoderSelecc").jGridContentMVC_UpDate([]);														
				for(var key in WorkGroups){
					var list = $("#PEForm_DlgApoderados_Grid_gapoderSelecc_" + key).jGridContentMVC_OrderBy('value','desc_nom_empl');					
					$("#PEForm_DlgApoderados_Grid_gapoderSelecc").jGridContentMVC_AddList(list);
				}											
			}else
				$("#PEForm_DlgApoderados_Grid_gapoderSelecc").jGridContentMVC_OrderBy('value','desc_nom_empl');//id : 'value',			isUndefined : 'desc_nom_empl'
			
		});
        
    	 $("#PEForm_DlgApoderados_btn_addAllapoder").button({
    	       icon: "ui-icon-zoomin",
               showLabel: true
           }).click(function(){
           	WebKernel.multiselectDialog('Seleccione los apoderados', '32', '', {
                   publish: function (data) { 
                	   var isgroup = WebForm.getKeyValue('PEForm_DlgApoderados_chk_GroupPoderes');	
						var list = [];
						if (isgroup.value){	
							for(var i=0;i<=WorkGroupsCount;i++){						
								for(var g in WorkGroups){
									if(i==WorkGroups[g].index){
										var listAux = $(WorkGroups[g].grid).jGridContentMVC_GetListQSortable();
										$(WorkGroups[g].grid).jGridContentMVC_UpDate(listAux);
										list = list.concat(listAux);
										break;
									}
								}
							}
						}else
							list = $("#PEForm_DlgApoderados_Grid_gapoderSelecc").jGridContentMVC_GetListQSortable();
						$("#PEForm_DlgApoderados_Grid_gapoderSelecc").jGridContentMVC_UpDate(list);
                	   loadGroupApoderados(data);
                   }, metadata: null
               });
         });
    	
    	
    	var dpConfigFrom = WebKernel.getDefaultDatepicker(true);
    	var dpConfigTo = WebKernel.getDefaultDatepicker(true);
    	dpConfigFrom.change = function() {
    		var element = $(this).attr('toField');
    		var currentDate = $( this ).datepicker( "getDate" );
    		var op = $("#PEForm_DlgApoderados_cmb_vigencia").val();
    		
    		switch(op){
	    		case '1': 
	    			var value = $( "#PEForm_DlgApoderados_spi_vigencia" ).spinner( "value" );
	    			var date = WebKernel.addMonths(currentDate,value*12);  		    			
	    			$("#"+element).val(date);
	    			break;
	    		case '2': 
	    			var value = $( "#PEForm_DlgApoderados_spi_vigencia" ).spinner( "value" );
	    			var date = WebKernel.addMonths(currentDate,value);  	    			
	    			$("#"+element).val(date);
	    			break;
	    		default:
	    			$("#"+element).datepicker( "option", "minDate", dpConfigTo.getDate( this ) );
    		}
        	
        }
    	dpConfigTo.change = function() {
    		var element = $(this).attr('fromField');
        	$("#"+element).datepicker( "option", "maxDate",  dpConfigTo.getDate( this ));
        }
    	
    	WebKernel.newWebForm("PEForm_DlgApoderados","PEModule",{
    		 PEForm_DlgApoderados_calendar_vigenciaInicio:dpConfigFrom
    		,PEForm_DlgApoderados_calendar_vigenciaFin:dpConfigTo
    		,PEForm_DlgApoderados_cmb_vigencia:{
    		      change: function( event, data ) {    		          
    		            var op = data.item.value;   		            
    		            switch(op){
    		            case 'Rango':
    		            	$("#PEForm_DlgApoderados_spi_vigencia").spinner( "option", "disabled", true );
    		            	$("#PEForm_DlgApoderados_calendar_vigenciaFin").datepicker( "option", { disabled: false } );
    		            	$("#PEForm_DlgApoderados_calendar_vigenciaFin").val("");
    		            	break;
    		            default:
    		            	$("#PEForm_DlgApoderados_spi_vigencia").spinner( "option", "disabled", false );
    		            	$("#PEForm_DlgApoderados_calendar_vigenciaFin").datepicker( "option", { disabled: true } );
    		            	$("#PEForm_DlgApoderados_calendar_vigenciaFin").val("");
    		            	break;
    		            }
    		        }
    		       }});
    	
    	$("#PEForm_DlgApoderados_spi_vigencia").spinner( "option", "disabled", false );
    	$("#PEForm_DlgApoderados_calendar_vigenciaFin").datepicker( "option", { disabled: true } );
    	$("#PEForm_DlgApoderados_calendar_vigenciaFin").val("");
    }
        
	var initDlgApoderadosOtros = function(){
	    	
		dlgApoderadosOtros = $(dlgApoderadosOtros).dialog({
	    		autoOpen:false
	    		,height:600
	    		,width:800
	    		,modal:true	    		
	    	});  
		
		dlgApoderadosOtros.btnCancelar = {text: "Cancelar",
            click: function() {	                            	
            	dlgApoderadosOtros.dialog( 'close' );
            }	                            
		};
	    
	    dlgApoderadosOtros.btnAtrasPaso1 = {text: "Atras",
                    click: function() {	                    	
                    	$("#PEForm_DlgPoderes").show();
    				    $("#PEForm_DlgApoderados").hide();
    				    $("#PEForm_DlgApoderadosOtros_FormaDeEjercerPoder").hide();	
				    	$("#PEForm_DlgApoderadosOtros_Descripcion_Del_Nuevo_Poder").hide();
                    	dlgApoderadosOtros.dialog( "option", "title","Captura de Poder - Paso 1 Seleccione el tipo");
                    	dlgApoderadosOtros.dialog('option','height',"auto");
                    	dlgApoderadosOtros.dialog('option','width',750);
    				    dlgApoderadosOtros.dialog( "option", "buttons",[dlgApoderadosOtros.btnCancelar,dlgApoderadosOtros.btnSigPaso1]);
    					dlgApoderadosOtros.dialog('open');	
                    }	                            
                  };
	    dlgApoderadosOtros.btnAtrasPaso2 = {text: "Atras",
    			icon: "ui-icon-seek-back",
    			iconPosition: "end",
                click: function() {	
                	dlgApoderadosOtros.btnSigPaso1.click();
                }	                            
              };
	    
	    dlgApoderadosOtros.btnSigPaso1 = {text: "Siguiente",           
                    click: function() {	                            	
                    	var tipoDeLlenadoDePoder = WebForm.getKeyValue('PEForm_DlgPoderes_Grid_poderes').value;
                    	if(typeof tipoDeLlenadoDePoder != 'undefined' && tipoDeLlenadoDePoder != null){
	                    	dlgApoderadosOtros.dialog( "option", "title",'Captura de poder ('+tipoDeLlenadoDePoder+') - Paso 2 Selecciona los apoderados');                    	
	                    	$("#PEForm_DlgPoderes").hide();
					    	$("#PEForm_DlgApoderados").show();
					    	$("#PEForm_DlgApoderadosOtros_FormaDeEjercerPoder").hide();	
					    	$("#PEForm_DlgApoderadosOtros_Descripcion_Del_Nuevo_Poder").hide();
					    	dlgApoderadosOtros.dialog('option','height',"auto");
	                    	dlgApoderadosOtros.dialog('option','width',750);
	                    	switch(tipoDeLlenadoDePoder){
	                    		case 'Llenado libre': 
	                    			dlgApoderadosOtros.dialog( "option", "buttons",[dlgApoderadosOtros.btnCancelar,dlgApoderadosOtros.btnAtrasPaso1,dlgApoderadosOtros.btnSigPasoDescripcionDePoder]);
	                    			break; 
	                    		default:                    				
	                    			dlgApoderadosOtros.dialog( "option", "buttons",[dlgApoderadosOtros.btnCancelar,dlgApoderadosOtros.btnAtrasPaso1,dlgApoderadosOtros.btnSigPasoFormaDeEjercerPoder]);
	                    			break;
	                    	}
                    	}
                    	else{
                    		sweetAlert('No eligio el tipo de poder', 'Elija un tipo antes de continuar','warning');
                    	}
                    }                            
                  };
	    
	    dlgApoderadosOtros.btnSigPasoFormaDeEjercerPoder = {text: "Siguiente",
                click: function() {	                   	
                	var tipoDeLlenadoDePoder = WebForm.getKeyValue('PEForm_DlgPoderes_Grid_poderes').value;
                	
                	var isgroup = WebForm.getKeyValue('PEForm_DlgApoderados_chk_GroupPoderes');	
    				var list = [];
    				if (isgroup.value){	
    					for(var i=0;i<=WorkGroupsCount;i++){						
    						for(var g in WorkGroups){
    							if(i==WorkGroups[g].index){
    								var listAux = $(WorkGroups[g].grid).jGridContentMVC_GetListQSortable();
    								list = list.concat(listAux);
    								break;
    							}
    						}
    					}
    				}else
    					list = $("#PEForm_DlgApoderados_Grid_gapoderSelecc").jGridContentMVC_GetListQSortable();
    				$("#PEForm_DlgApoderados_Grid_gapoderSelecc").jGridContentMVC_UpDate(list);
    				$("#PEForm_DlgApoderadosOtros_Grid_ApSelecc").jGridContentMVC_UpDate(list);
                	
                	dlgApoderadosOtros.dialog( "option", "title",'Captura de poder ('+tipoDeLlenadoDePoder+') - Paso 3 Formas de Ejercer el poder');
                	var poderesGridBean = WebForm.getKeyValue('PEForm_DlgPoderes_Grid_poderes',true);
                	                   	               
	                	$("#PEForm_DlgApoderados_txt_poder").attr('disabled',true).val(poderesGridBean.des_podertipo);
	        			CKEDITOR.instances.PEForm_DlgApoderados_txt_descripcion.setData(poderesGridBean.des_facultades);
	        			CKEDITOR.instances.PEForm_DlgApoderados_txt_descripcion.setReadOnly(true);	        			
	        			
	        			if(!dlgApoderadosOtros.Mode == 'new')	        				
	        				$("#PEForm_DlgApoderados_txt_caract").val(dlgApoderadosOtros.WorkBean.desc_caracteristicas);
	        				
						$.each($( "input[name=PEForm_DlgApoderadosOtros_Deleg1]" ), function(i, button) {														
							if(poderesGridBean.ind_pe_delegable==$(button).val()){							
								$(button).prop("checked", true);
									return;
								}							
						});
						$( "input[name=PEForm_DlgApoderadosOtros_Deleg1]" ).attr('disabled',true);
						
						$.each($( "input[name=PEForm_DlgApoderadosOtros_Deleg2]" ), function(i, button) {														
							if(poderesGridBean.ind_pe_individual==$(button).val()){							
								$(button).prop("checked", true);
									return;
							}
							
						});
						$( "input[name=PEForm_DlgApoderadosOtros_Deleg2]" ).attr('disabled',true);
						
						CKEDITOR.instances.PEForm_DlgApoderadosOtros_txt_formas_ejercerlo.setData(poderesGridBean.des_pe_formaejercerlo);						
						CKEDITOR.instances.PEForm_DlgApoderadosOtros_txt_formas_ejercerlo.setReadOnly(true);						
                	                        			        			        			
                	$("#PEForm_DlgPoderes").hide();
				    $("#PEForm_DlgApoderados").hide();
				    $("#PEForm_DlgApoderadosOtros_FormaDeEjercerPoder").show();	  
				    
				    dlgApoderadosOtros.dialog('option','height',"auto");
				    dlgApoderadosOtros.dialog('option','width',750);		                    				
				    dlgApoderadosOtros.dialog( "option", "buttons",[dlgApoderadosOtros.btnCancelar,dlgApoderadosOtros.btnAtrasPaso2,dlgApoderadosOtros.btnFinalizar]);
                }                                                   
	    };
	     
	    dlgApoderadosOtros.btnSigPasoDescripcionDePoder = {text: "Siguiente",
                click: function() {	                            	                	
                   	var tipoDeLlenadoDePoder = WebForm.getKeyValue('PEForm_DlgPoderes_Grid_poderes').value;
                   
                   	var isgroup = WebForm.getKeyValue('PEForm_DlgApoderados_chk_GroupPoderes');	
    				var list = [];
    				if (isgroup.value){	
    					for(var i=0;i<=WorkGroupsCount;i++){						
    						for(var g in WorkGroups){
    							if(i==WorkGroups[g].index){
    								var listAux = $(WorkGroups[g].grid).jGridContentMVC_GetListQSortable();
    								list = list.concat(listAux);
    								break;
    							}
    						}
    					}
    				}else
    					list = $("#PEForm_DlgApoderados_Grid_gapoderSelecc").jGridContentMVC_GetListQSortable();
    				$("#PEForm_DlgApoderados_Grid_gapoderSelecc").jGridContentMVC_UpDate(list);
    				$("#PEForm_DlgApoderadosOtros_Grid_ApSelecc").jGridContentMVC_UpDate(list);
                   	                   	                   
                   	dlgApoderadosOtros.dialog( "option", "title",'Captura de poder ('+tipoDeLlenadoDePoder+') - Paso 3 Descripci\u00F3n del poder');
                   	$("#PEForm_DlgApoderados_txt_poder").attr('disabled',false);
                   	CKEDITOR.instances.PEForm_DlgApoderados_txt_descripcion.setReadOnly(false);
        			$( "input[name=PEForm_DlgApoderadosOtros_Deleg1]" ).attr('disabled',false);
        			$( "input[name=PEForm_DlgApoderadosOtros_Deleg2]" ).attr('disabled',false);
        			CKEDITOR.instances.PEForm_DlgApoderadosOtros_txt_formas_ejercerlo.setReadOnly(false);
                   	$("#PEForm_DlgPoderes").hide();
				    $("#PEForm_DlgApoderados").hide();
				    $("#PEForm_DlgApoderadosOtros_FormaDeEjercerPoder").show();	                   	
				    dlgApoderadosOtros.dialog('option','height',"auto");
				    dlgApoderadosOtros.dialog('option','width',750);		                    				
                   	dlgApoderadosOtros.dialog( "option", "buttons",[dlgApoderadosOtros.btnCancelar,dlgApoderadosOtros.btnAtrasPaso2,dlgApoderadosOtros.btnFinalizar]);
               }                                                   
       };
	    
	    dlgApoderadosOtros.btnFinalizar = {text: "Finalizar",
                    click: function() {  
                    	if($("#PEForm_DlgApoderados_txt_poder").val().trim().length>0){
	                    	/** ****************** Generar Poder * */
	                    	var poderesGridBean = WebForm.getKeyValue('PEForm_DlgPoderes_Grid_poderes',true);
	                    	var tipoDeLlenadoDePoder = poderesGridBean.des_podertipo;
	                    	var descripcionDelPoder = poderesGridBean.des_descripcion;
	                    	
	                    	var WorkBean;
	                    	if(dlgApoderadosOtros.Mode == 'new'){
	                    		WorkBean = new ClassPoder(tipoDeLlenadoDePoder,descripcionDelPoder);
	                    	}
	                    	else{
	                    		WorkBean = dlgApoderadosOtros.WorkBean;
	                    	}
	                    		// Es necesario guardar el tipo de llenado de poder en el WorkBean
	                    		WorkBean.tipoDeLlenadoDePoder = tipoDeLlenadoDePoder;
	                    	               	
	                    	/******************** Obtener Apoderador*****************************/
	                    		
	                    	var list = $("#PEForm_DlgApoderados_Grid_gapoderSelecc").jGridContentMVC_GetList();
	                    	var selecc = new Array();                    
	                        WorkBean = WebForm.getBean('PEForm_DlgApoderados',WorkBean);
	
	                        var ga = list.length == 0 ? "" : list[0].des_grupo;
	        				var names = ga == '' ? '' : '<div style="font-weight: bold;">' + ga + '</div>';
	                                                
	                        $(list).each(function (i, bean) {
	                        	bean.value = bean.value.trim(); 
	                        	var g=bean.des_grupo;
	                        	if(g != ga){
	                            	names += ga == '' ? '' : '<br/><div style="font-weight: bold;">' + g + '</div>';
	                            	ga = g;
	                            }
	
	                            names += '&#9679; \t ' + bean.value + '<br /> ';                        
	                			
	                            selecc.push(new ClassApoderado(bean.id,bean.value, 1,0,0,bean.id_grupo_fk,bean.des_grupo));
	                        });
	                                      
	                        WorkBean.desc_apoderados = names;                
	                        WorkBean.C8 = WorkBean.desc_caracteristicas;                        
	                        WorkBean.C9 = WorkBean.num_vigenciatipo == 0
							|| WorkBean.num_vigenciatipo == null ? ""
							: WorkBean.fec_vigenciafin;                       
	                        WorkBean.Apoderados = selecc;
	                        	                       
							WorkBean.des_poder = "";
							
							 WorkBean.Facultades = new Array();
																	
							switch (tipoDeLlenadoDePoder) {
								case 'Llenado libre':
																		
				                        var pC = new Object();                        
				                        pC.delegable = $("input[name='PEForm_DlgApoderadosOtros_Deleg1']:checked").val();
				                        pC.individual = $("input[name='PEForm_DlgApoderadosOtros_Deleg2']:checked").val();                                                							
									
				                        pC.mancomunado = $("#PEForm_DlgApoderadosOtros_ApodA").is(":checked");								
				                        pC.formae = CKEDITOR.instances.PEForm_DlgApoderadosOtros_txt_formas_ejercerlo.getData();                     
										          
				                        pC.otro = $("#PEForm_DlgApoderadosOtros_grid_A").jGridContentMVC_ToString('value', '<BR/>','&#9679;'); 
				                        	
										WorkBean.Facultades.push({id_ep_fk : 0
											,ind_tipo : 4
											,des_tipo : 'Formas de ejercer'
											,ind_delegable : pC.delegable
											,ind_individual : pC.individual
											,caracteristicas : ''
											,des_formae : pC.formae
											,mancomunado : pC.mancomunado
											,listMancomunados : $("#PEForm_DlgApoderadosOtros_grid_A").jGridContentMVC_GetList()
											,id_opoder_ep_fk : 0
											,id_ep_fk : 0
											,pinnum_created_by : 0
											,id_apod_ep_pk : 0});				                     
									
									if(CKEDITOR.instances.PEForm_DlgApoderados_txt_descripcion.getData().length>0)
										WorkBean.des_poder += CKEDITOR.instances.PEForm_DlgApoderados_txt_descripcion.getData();
									
									WorkBean.des_poder += 
										"Caracter&iacute;sticas/Limitaciones:"
										+ WorkBean.desc_caracteristicas																	
										+ (pC.delegable	== "Ninguno" ? "": "<br/>*<b>" + pC.delegable + "</b>")						
										+ "<br/>"							
										+ "<br/>Forma de ejercicio:<br/>"
										+ (pC.individual == "Ninguno" ? "": "*<b>" + pC.individual + "</b>")										
										+ "<br/>"															
										+ (pC.formae.length>0 ? pC.formae + "<br/>" : "")									
										+ (pC.mancomunado ? "Mancomunado con:<br/>" + pC.otro : "");									
									break;
								default:
									var tieneMancomunados = $("#PEForm_DlgApoderadosOtros_ApodA").is(":checked");								
		                            var listaMancoumandos = $("#PEForm_DlgApoderadosOtros_grid_A").jGridContentMVC_ToString('value', '<BR/>','&#9679;'); 
									
									if(poderesGridBean.des_descripcion != null)
										if(poderesGridBean.des_descripcion.length>0)
											WorkBean.des_poder += poderesGridBean.des_descripcion;										
									
									WorkBean.des_poder += 
										"Caracter&iacute;sticas/Limitaciones:<br/>"
										+ (poderesGridBean.des_pe_caracteristicas != null ? poderesGridBean.des_pe_caracteristicas  : "")							
										+ (WorkBean.desc_caracteristicas.trim().length>0 ? WorkBean.desc_caracteristicas : "")
										+ "<br/>"
										+ (poderesGridBean.ind_pe_delegable == "Ninguno" ? "" : "*<b>" + poderesGridBean.ind_pe_delegable + "</b>")						
										+ "<br/>"							
										+ "<br/>Forma de ejercicio:<br/>"
										+ (poderesGridBean.ind_pe_individual == "Ninguno" ? "" : "*<b>" + poderesGridBean.ind_pe_individual + "</b><br/>")										
										+ "<br/>"															
										+ poderesGridBean.des_pe_formaejercerlo
										+ "<br/>"
										+ (tieneMancomunados ? "Mancomunado con:<br/>" + listaMancoumandos : "");
									
									WorkBean.Facultades.push({id_ep_fk : 0
										,ind_tipo : 4
										,des_tipo : 'Formas de ejercer'
										,ind_delegable : poderesGridBean.ind_pe_delegable
										,ind_individual :poderesGridBean.ind_pe_individual
										,caracteristicas : ''
										,des_formae : poderesGridBean.des_pe_formaejercerlo
										,mancomunado : tieneMancomunados
										,listMancomunados : $("#PEForm_DlgApoderadosOtros_grid_A").jGridContentMVC_GetList()
										,id_opoder_ep_fk : 0
										,id_ep_fk : 0
										,pinnum_created_by : 0
										,id_apod_ep_pk : 0});		
																											
									break;
						}
																										
	                    if(dlgApoderadosOtros.Mode == 'new')
	                    	Grid_apoderados.push(WorkBean);
	                    $("#PEForm_Grid_poderSelecc").jGridContentMVC_UpDate(Grid_apoderados);
	                    $("#PEForm_Grid_apoderados").jGridContentMVC_UpDate(Grid_apoderados);
	                    dlgApoderadosOtros.dialog('close');
                    }       
                    else{
                    	sweetAlert('No eligio el nombre del poder', 'Introduzca un nombre para el poder antes de continuar','warning');
                    }
                   }
                 };
		
		dlgApoderadosOtros.open = function(mode,WorkBean){	
		dlgApoderadosOtros.WorkBean = WorkBean;
		dlgApoderadosOtros.Mode = mode;
		
		WorkGroups = [];
		WorkGroupsCount = 0;
		
		$("#PEForm_DlgApoderados_Div_gapoderSelecc").html('');
		$("#PEForm_DlgApoderados_Grid_gapoderSelecc").jGridContentMVC_UpDate([]);
				
		$("#PEForm_Grid_poderSelecc").jGridContentMVC_UpDate();	
		
		switch (mode) {
		case 'new':
			WebForm.cleanBean('PEForm_DlgApoderados');
			$("#PEForm_DlgApoderados_Grid_gapoderSelecc").jGridContentMVC_GetConfig().jQSortable = true;			
			$.each($( "input[name=PEForm_DlgApoderadosOtros_Deleg1]" ), function(i, button) {														
				if('Delegable'==$(button).val()){							
					$(button).prop("checked", true);
					return;
				}
			});
			$.each($( "input[name=PEForm_DlgApoderadosOtros_Deleg2]" ), function(i, button) {														
				if('Individual'==$(button).val()){							
					$(button).prop("checked", true);
					return;
				}
			});
			
			$("#PEForm_DlgApoderadosOtros_tabs").tabs( "option", "active", 0 );
			$('#PEForm_Grid_apoderados_radio').val('');	
			CKEDITOR.instances.PEForm_DlgApoderadosOtros_txt_formas_ejercerlo.setData('');
			$('#PEForm_DlgApoderadosOtros_grid_A').jGridContentMVC_UpDate([]);
			$('#PEForm_DlgApoderadosOtros_grid_A').val('');
			$("#PEForm_DlgApoderados_spi_vigencia").spinner("option","disabled", true);
			$("#PEForm_DlgApoderados_calendar_vigenciaInicio").datepicker("option", {disabled : true});
			$("#PEForm_DlgApoderados_calendar_vigenciaFin").datepicker(	"option", {	disabled : true	});
			dlgApoderadosOtros.btnAtrasPaso1.click();
			dlgApoderadosOtros.dialog('open');
			break;
		case 'edit':
			WebForm.setBean('PEForm_DlgApoderados', WorkBean);
			$("#PEForm_DlgApoderadosOtros_tabs").tabs( "option", "active", 0 );
						
			/** * Cargar Facultades */
			if (WorkBean.Facultades.length > 0) {
				for (var i = 0; i < WorkBean.Facultades.length; i++) {
					var facultad = WorkBean.Facultades[i];
					$.each($( "input[name=PEForm_DlgApoderadosOtros_Deleg1]" ), function(i, button) {														
						if(facultad.ind_delegable==$(button).val()){							
							$(button).prop("checked", true);
							return;
						}
					});
					$.each($( "input[name=PEForm_DlgApoderadosOtros_Deleg2]" ), function(i, button) {														
						if(facultad.ind_individual==$(button).val()){							
							$(button).prop("checked", true);
							return;
						}						
					});
					
					$("#PEForm_DlgApoderadosOtros_grid_A").jGridContentMVC_UpDate(facultad.listMancomunados);
					CKEDITOR.instances.PEForm_DlgApoderadosOtros_txt_formas_ejercerlo.setData(facultad.des_formae);										
					WebForm.updController('PEForm_DlgApoderadosOtros_ApodA', facultad.mancomunado);										
				}
			}
			else{
				$('#PEForm_DlgApoderadosOtros_grid_A').jGridContentMVC_UpDate([]);
				$('#PEForm_DlgApoderadosOtros_grid_A').val('');			
			}
			
			/** * Cargar Apoderados */
			$('#PEForm_Grid_apoderados_radio').val(WorkBean.C1);

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
				WebForm.updController('PEForm_DlgApoderados_chk_GroupPoderes', false);
				$("#PEForm_DlgApoderados_Grid_gapoderSelecc").jGridContentMVC_GetConfig().jQSortable = true;
			}else {
				WebForm.updController('PEForm_DlgApoderados_chk_GroupPoderes', true);
				$("#PEForm_DlgApoderados_Grid_gapoderSelecc").jGridContentMVC_GetConfig().jQSortable = false;
				drawGoupApoderados();
			}
			
			$("#PEForm_DlgApoderados_Grid_gapoderSelecc").jGridContentMVC_UpDate(WorkBean.Apoderados);
			
			if(WorkBean.num_vigenciatipo==null){
				$("#PEForm_DlgApoderados_spi_vigencia").spinner("option","disabled", true);
				$("#PEForm_DlgApoderados_calendar_vigenciaInicio").datepicker("option", {disabled : true});
				$("#PEForm_DlgApoderados_calendar_vigenciaFin").datepicker(	"option", {	disabled : true	});
			}else{						
				switch ( WorkBean.num_vigenciatipo+'' ) {					
				case '0':
					$("#PEForm_DlgApoderados_spi_vigencia").spinner("option","disabled", true);
					$("#PEForm_DlgApoderados_calendar_vigenciaInicio").datepicker("option", {disabled : true});
					$("#PEForm_DlgApoderados_calendar_vigenciaFin").datepicker(	"option", {	disabled : true	});
					
					break;
				case '3':
					$("#PEForm_DlgApoderados_calendar_vigenciaInicio").datepicker(
							"option", {
								disabled : false
							});
					$("#PEForm_DlgApoderados_spi_vigencia").spinner("option",
							"disabled", true);
					$("#PEForm_DlgApoderados_calendar_vigenciaFin").datepicker(
							"option", {
								disabled : false
							});
					
					break;
				default:
					$("#PEForm_DlgApoderados_calendar_vigenciaInicio").datepicker("option", {disabled : false});
					$("#PEForm_DlgApoderados_spi_vigencia").spinner("option","disabled", false);
					$("#PEForm_DlgApoderados_calendar_vigenciaFin").datepicker(	"option", {disabled : true});			
					break;
				}
			}
			
			dlgApoderadosOtros.btnAtrasPaso2.click();
			dlgApoderadosOtros.dialog('open');
			break;
		}
	}
		
		$("#PEForm_DlgApoderadosOtros_tabs").tabs();
		
		WebKernel.newWebForm("PEForm_DlgApoderadosOtros","PEModule");
		
		$("#PEForm_DlgApoderadosOtros_Grid_ApSelecc").jGridContentMVC({ gridId: "PEForm_DlgApoderadosOtros_Grid_ApSelecc",
            noDataMsg: 'no hay registros para mostrar',
            storage: [],
            rowClick: null,
            cellClick: function (iRow, iCell, bean, pty, gridId, td, event) {
                
            },
            rowCssA: '',
            rowCssB: '',
            rowFinal: null,
            rowAlign: 'center',
            Encabezados: true,
            HeadCss: "HHead",
            Heads: false,
            columns: [ { HeadText: 'Apoderados', 'width': '35%', id: 'value', isUndefined:'desc_nom_empl', type: 'action', 'align': 'left' }
            		  ,{ HeadText: 'Apoderados', 'width': '65%', id: 'des_grupo', isNull:'', type: 'action', 'align': 'left' }  		                         
            ],
            tblCssClass: "",
            tblStyle: "width:100%",
            cellpadding: '0',
            cellspacing: '1',
            border: '0',
            afterDrawing: function () {
                
            }
        });
		
		$(".PEForm_DlgApoderadosOtros_chk").change(function(){
			var check = $(this).is(":checked");
    		if(check)
    			$("#"+this.id+"_Table").show();
    		else
    			$("#"+this.id+"_Table").hide()
		
		});
		
		$(".PEForm_DlgApoderadosOtros_chk_Table").hide();
		
		var listMd = ['A','B','C','D'];
        for(var i in listMd){
        	var id = "PEForm_DlgApoderadosOtros_grid_"+listMd[i]
        	$("#"+id).jGridContentMVC({
                gridId: id,
                noDataMsg: 'no hay registros para mostrar',
                storage: [],
                rowClick: null,
                cellClick: function (iRow, iCell, bean, pty, gridId, td, event) {

                },
                rowCssA: '',
                rowCssB: '',
                rowFinal: null,
                rowAlign: 'center',
                Encabezados: true,
                HeadCss: "HHead",
                Heads: false,
                columns: [{ HeadText: 'Nombre', 'width': '100%', id: 'value', type: 'action', 'align': 'left' }

                ],
                tblCssClass: "",
                tblStyle: "width:100%",
                cellpadding: '0',
                cellspacing: '1',
                border: '0',
                afterDrawing: function () {

                }
            });
        }
		
		$(".PEForm_DlgApoderadosOtros_BtnCon").button().click(function(){
			 var mancomunadosIds = new Array();
				$.each($("#PEForm_DlgApoderadosOtros_grid_A").jGridContentMVC_GetList(),function(i,mancomunado){
					mancomunadosIds.push(mancomunado.id);				
				});
				
			WebKernel.multiselectDialog('Seleccione los mancomunados...','32', mancomunadosIds,{
				publish:function(data){				
					 var id = "#PEForm_DlgApoderadosOtros_grid_" + this.metadata;					 
					 $(id).jGridContentMVC_UpDate([]);
	                 $.each(data, function (i, bean) { 
	                	 $(id).jGridContentMVC_Add(bean);	                 	
	                 });                    	                 
	             }, metadata: $(this).attr('metadata')
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
						: this.Concepto == 3 ? "Vigencia"
						: this.Concepto == 4 ? "Se agot&oacute; el objeto"
								: "Renuncia";
						
		this.id_escriturarevoca_fk = this.Escritura;
		this.id_documentumrevoca = this.NoDoc;
		this.fec_revoca = this.Fecha;

		this.des_textorevoca = arguments[1];
		this.desc_apendicerevoca = 1;
		this.num_created_by = null;

	}
	
	var validarRevocacion = function(){
		var value = $("#PEForm_DlgRevocacion_cmb_Escritura_cmb_razon").val();
		valido = '';
		switch (value+'') { 
		case '1':
        	if($("#PEForm_DlgRevocacion_cmb_Escritura").val()=='-1')
        		return "Debe seleccionar una escritura";
			valido = $("#PEForm_DlgRevocacion_doc_Documento_digital_text").val() != ""
        	 			&& $("#PEForm_DlgRevocacion_cmb_Escritura_date_fecha").val() != "";
        	if(!valido)
        		return "La escritura que intenta utilizar para revocar est\u00E1 incompleta, debe tener fecha de otorgamiento y documento digitalizado.";
        	break;        
        case '2':
        case '3':
        case '4':
        	valido = $("#PEForm_DlgRevocacion_cmb_Escritura_date_fecha").val() != "";
        	if(!valido)
        		return "Debe seleccionar la fecha del suceso para poder revocar.";
        	break;        
        case '5':
        	valido = $("#PEForm_DlgRevocacion_doc_Documento_digital_text").val() != ""
	 			&& $("#PEForm_DlgRevocacion_cmb_Escritura_date_fecha").val() != "";
        	if(!valido)
        		return "Debe ingresar el documento digitalizado y seleccionar la fecha del suceso para poder revocar.";
        	break;	
        default:
        	return "Debe seleccionar el concepto para poder revocar";
        	
		}
		return "";
	}
	
	var initDlgRevocacion = function(){		 
		 dlgRevocacion = $(dlgRevocacion).dialog({
    		autoOpen:false
    		,height:"auto"
    		,width:800
    		,modal:true
    		,buttons:{    			
    			Cancelar : function() {
					var revocados = $("#PEForm_DlgRevocacion_grid_Revocados").jGridContentMVC_GetList();
					
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
		 
		 WebKernel.newWebForm("PEForm_DlgRevocacion", "PEModule");

	        $("#PEForm_DlgRevocacion_grid_Revocados").jGridContentMVC({
	            gridId: "PEForm_DlgRevocacion_grid_Revocados",
	            noDataMsg: 'no hay registros para mostrar',
	            storage: [],
	            rowClick: null,
	            cellClick: function (iRow, iCell, bean, pty, gridId, td, event) {
	                switch (pty) {
					case 'Quitar':
						if(bean.ind_status==2 || bean.ind_status == 4){
							bean.ind_status = 3;
							$("#PEForm_DlgRevocacion_grid_Revocados").jGridContentMVC_UpDate();
						}
						break;
					case 'Revocar':
					case '_Selecc':

						$("#PEForm_DlgRevocacion_divRevocar").data(
								'Bean', bean);

						dlgRevocacion.buttonsMain = dlgRevocacion
								.dialog("option", "buttons");

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
														$("#PEForm_DlgRevocacion_divRevocar").hide();
														$("#PEForm_DlgRevocacion_divGrid").show();
														dlgRevocacion.dialog("option","buttons",dlgRevocacion.buttonsMain);
														
													}
												},
												{
													text : "Revocar",
													icons : {
														primary : "ui-icon-circle-check"
													},
													click : function() {
														var valido = validarRevocacion();
														if(valido != ''){
															sweetAlert('No se puedo revocar', valido,'error');
															return;
														}
														var bean = $("#PEForm_DlgRevocacion_divRevocar").data('Bean');
														bean.ind_aprevoca = null;
														bean.ind_status = 4;
														bean.Revocar = new ClassRevocar(
																$("#PEForm_DlgRevocacion_cmb_Escritura").val(),
																$("#PEForm_DlgRevocacion_cmb_Escritura_cmb_razon").val(),
																$("#PEForm_DlgRevocacion_cmb_Escritura_date_fecha").val(),
																$("#PEForm_DlgRevocacion_doc_Documento_digital_text").val(),
																bean);

														$("#PEForm_DlgRevocacion_divRevocar").hide();
														$("#PEForm_DlgRevocacion_divGrid").show();
														dlgRevocacion
																.dialog(
																		"option",
																		"buttons",
																		dlgRevocacion.buttonsMain);
														$("#PEForm_DlgRevocacion_grid_Revocados")
																.jGridContentMVC_UpDate();
													}
												} ]);

						if (bean.ind_status == 2 || bean.ind_status == 4) {
														            
				            bean.Revocar.Escritura = bean.Revocar.Escritura == undefined ? bean.Revocar.id_escriturarevoca_fk : bean.Revocar.Escritura;
							bean.Revocar.Concepto = bean.Revocar.Concepto == undefined ? bean.Revocar.ind_razonrevoca : bean.Revocar.Concepto;
							bean.Revocar.Fecha = bean.Revocar.Fecha == undefined ? bean.Revocar.fec_revoca : bean.Revocar.Fecha;
							bean.Revocar.NoDoc = bean.Revocar.NoDoc == undefined ? bean.Revocar.id_documentumrevoca : bean.Revocar.NoDoc;
				            
							$("#PEForm_DlgRevocacion_cmb_Escritura").val(bean.Revocar.Escritura);
							$("#PEForm_DlgRevocacion_cmb_Escritura_cmb_razon").val(bean.Revocar.Concepto);
							$("#PEForm_DlgRevocacion_cmb_Escritura_date_fecha").val(bean.Revocar.Fecha);
							$("#PEForm_DlgRevocacion_doc_Documento_digital_text").val(bean.Revocar.NoDoc);
							
							switch (bean.Revocar.Concepto+'') { 
								case '-1':
						        	$("#PEForm_DlgRevocacion_cmb_Escritura").attr("disabled", true );
						        	$("#PEForm_DlgRevocacion_doc_Documento_digital_text").attr('disabled',true);
						    		$("#PEForm_DlgRevocacion_cmb_Escritura_date_fecha").datepicker( "option", "disabled", true );
						        	break;
						        case '1':
						        	$("#PEForm_DlgRevocacion_cmb_Escritura").attr( "disabled", false );
						        	$("#PEForm_DlgRevocacion_doc_Documento_digital_text").attr('disabled',true);
						    		$("#PEForm_DlgRevocacion_cmb_Escritura_date_fecha").datepicker( "option", "disabled", true );
						        	break;        
						        case '2':
						        	$("#PEForm_DlgRevocacion_cmb_Escritura").attr( "disabled", true );
						        	$("#PEForm_DlgRevocacion_doc_Documento_digital_text").attr('disabled',true);
						    		$("#PEForm_DlgRevocacion_cmb_Escritura_date_fecha").datepicker( "option", "disabled", false );
						        	break;
						        case '3':
						        	$("#PEForm_DlgRevocacion_cmb_Escritura").attr( "disabled", true );
						        	$("#PEForm_DlgRevocacion_doc_Documento_digital_text").attr('disabled',true);
						    		$("#PEForm_DlgRevocacion_cmb_Escritura_date_fecha").datepicker( "option", "disabled", false );
						        	break;
						        case '4':
						        	$("#PEForm_DlgRevocacion_cmb_Escritura").attr( "disabled", true );
						        	$("#PEForm_DlgRevocacion_doc_Documento_digital_text").attr('disabled',true);
						    		$("#PEForm_DlgRevocacion_cmb_Escritura_date_fecha").datepicker( "option", "disabled", false );
						        	break;
						        case '5':
						        	$("#PEForm_DlgRevocacion_cmb_Escritura").attr( "disabled", true );
						        	$("#PEForm_DlgRevocacion_doc_Documento_digital_text").attr('disabled',false);
						    		$("#PEForm_DlgRevocacion_cmb_Escritura_date_fecha").datepicker( "option", "disabled", false );
						        	break;
							}
						}else{
							$("#PEForm_DlgRevocacion_cmb_Escritura").val("-1"),
							$("#PEForm_DlgRevocacion_cmb_Escritura_cmb_razon").val("-1"),
							$("#PEForm_DlgRevocacion_cmb_Escritura_date_fecha").val(""),
							$("#PEForm_DlgRevocacion_doc_Documento_digital_text").val("");
							
							$("#PEForm_DlgRevocacion_cmb_Escritura").attr("disabled", true );
				        	$("#PEForm_DlgRevocacion_doc_Documento_digital_text").attr('disabled',true);
				    		$("#PEForm_DlgRevocacion_cmb_Escritura_date_fecha").datepicker( "option", "disabled", true );
						}

						$("#PEForm_DlgRevocacion_LblName").html(
								bean.desc_nom_empl);

						$("#PEForm_DlgRevocacion_divGrid").hide();
						$("#PEForm_DlgRevocacion_divRevocar")
								.show();

						break;
	                }
	            },
	            rowCssA: 'odd',
	            rowCssB: 'even',
	            rowFinal: null,
	            rowAlign: 'center',
	            Encabezados: true,
	            HeadCss: "HHead",
	            Heads: true,
	            columns : [
							{
										HeadText : 'Apoderados',
										'width' : '40%',
										id : 'desc_nom_empl',
										type : 'action',
										'align' : 'left',
										cellValue : 'eval',
										eval : function(bean) {
											var des_razonrevoca = bean.Revocar == null ? "" : bean.Revocar.ind_razonrevoca == 1 ? "Revocaci&oacute;n" 
													: bean.Revocar.ind_razonrevoca == 2 ? "Muerte" 
															: bean.Revocar.ind_razonrevoca == 3 ? "Vigencia"
															: bean.Revocar.ind_razonrevoca == 4 ? "Se agot&oacute; el objeto"
																	: "Renuncia";
											
												return bean.desc_nom_empl + 
													(bean.ind_status == 2 || bean.ind_status == 4 ?' <b>('+ des_razonrevoca + (parseInt(bean.Revocar.ind_razonrevoca) == 1 ? ' Esc. '+bean.Revocar.id_escriturarevoca_fk : '') +')</b>' :'' );
											}
									},
							{
								HeadText : 'Grupo',
								'width' : '30%',
								id : 'des_grupo',
								type : 'action',
								'align' : 'center'
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

	        dlgRevocacion.open = function (workBean) {
	        	$("#PEForm_DlgRevocacion_grid_Revocados").jGridContentMVC_UpDate(workBean.Apoderados);
				
				Request.EscrituraPoderes.getListEscRev(MetaSession.SessionBean.idCurrentEmpresa,{
					response : function(rsp) {
						
							var list = Util.getBeanList(rsp);
													
							WebForm.updController("PEForm_DlgRevocacion_cmb_Escritura",list);
					},
					error : function(xhttp, e) {
	                	  sweetAlert('No se pudo realizar la operacion', 'Error:' + e,'error');
					},
					complete : function() {
						
					}
				});
				
				$("#PEForm_DlgRevocacion_divRevocar").hide();
				$("#PEForm_DlgRevocacion_divGrid").show();
		
				dlgRevocacion.dialog(
								"option",
								"buttons",
								dlgRevocacion.buttonsMain ?dlgRevocacion.buttonsMain : dlgRevocacion.dialog("option", "buttons"));
				
				
				$(dlgRevocacion).data('WorkBean', workBean);
				dlgRevocacion.dialog('open');
	        }
	    }
	
	var getApendice = function(rev){
		var poderes = $("#PEForm_Grid_apoderados").jGridContentMVC_GetList();
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
	
	    var dlgRevocacion_save = function () {

			var revocados = $("#PEForm_DlgRevocacion_grid_Revocados").jGridContentMVC_GetList();
			 
			 //Grid_apoderados = $("#PEForm_Grid_apoderados").jGridContentMVC_UpDate();
			
			/****************** Aplicar Revocaciones *****************************/		
			
			$.each(Grid_apoderados,function(i, bean) {
				if (bean.Apoderados) {
					$(bean.Apoderados).each(
									function(i,apod) {
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
												
												
												strAux += ' Mandato Termin\u00F3 por: Revocaci\u00F3n en Esc. No. <a docId="'
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
												apod.Revocar = null;
												apod.ind_status = 1;
												//strAux += " Mandato Termin\u00F3 por Vigencia " + apod.Revocar.Fecha;
												break;
											case '4':
											case 4:
												
												strAux += " Mandato Termin\u00F3 por: Se agot\u00F3 el objeto "+ apod.Revocar.Fecha;					
												
												break;
											case '5':
											case 5:
												strAux += ' Mandato Termin\u00F3 por: <a docId="'
													+apod.Revocar.NoDoc
													+'" class="ApodDetail" title="click para ver carta de renuncia" href="#doc"> Carta Renuncia </a> de fecha '
													+ apod.Revocar.Fecha;
												break;
											}
											apod.desc_revoca = strAux;

											
										}
									});
				}
			});
			
			/****************** Agrupar  *****************************/
			var GroupApendices = [];
			var GroupApendicesDates = [];
			
			$.each(Grid_apoderados,function(i, bean) {
				if (bean.Apoderados) {
					$(bean.Apoderados).each(function(i,apod) {									
						if(apod.ind_status == 2){
							var exists = false;
							for(var g in GroupApendices){
								if(GroupApendices[g].concept == apod.desc_revoca){
									exists = true;
									break;
								}					
							}
							if(!exists){					
								GroupApendices.push({concept:apod.desc_revoca,date:Util.parseStrFmtDate(apod.Revocar.Fecha,'SHORT','SQL')+(GroupApendices.length+1)});
								GroupApendicesDates.push(Util.parseStrFmtDate(apod.Revocar.Fecha,'SHORT','SQL')+GroupApendices.length);
							}			
							}
						});
					}});
		
			
			/****************** Ordenar por fecha DESC *****************************/
			var GroupApendicesAux = [];
			GroupApendicesDates.sort();
			
			for(var g=GroupApendicesDates.length-1; g>=0;g--){
				for(var gi in GroupApendices){
					if(GroupApendicesDates[g]==GroupApendices[gi].date){
						GroupApendicesAux.push(GroupApendices[gi]);
						break;
					}
				}
			}
			GroupApendices = GroupApendicesAux;
			/****************** Aplicar Apendice *****************************/
			
			$.each(Grid_apoderados,function(i, bean) {
				if (bean.Apoderados) {
					$(bean.Apoderados).each(function(i,apod) {									
						if(apod.ind_status == 2){
					for(var g=1; g <= GroupApendices.length;g++){
						if(GroupApendices[g-1].concept == apod.desc_revoca){
							apod.ind_aprevoca = g;
							var apeIndex = '<label style="color:red;">' + apod.ind_aprevoca + '</label>';
							apod.desc_revoca = apeIndex + " " + apod.desc_revoca;
						}
					}
						}});
					}});

			var workBean = $(dlgRevocacion).data('WorkBean');
			var list = workBean.Apoderados;
			var ga = list.length == 0 ? "" : list[0].des_grupo;
			var names = ga == '' ? '' : '<div style="font-weight: bold;">' + ga + '</div>';
			$.each(Grid_apoderados,function(i, workBean) {
				//var workBean = $(dlgRevocacion).data('WorkBean');
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
											+ (bean.value == undefined ? (bean.desc_nom_empl.trim())
													: (bean.value.trim()))
											+ (bean.ind_status == 2 ? '<sup style="font-size:8pt;"><label style="color:red;">'
													+ bean.ind_aprevoca + '</label></sup>'
													: '') + '<br /> ';
		
								});
		
				workBean.desc_apoderados = names;
			});

			

			$("#PEForm_Grid_apoderados").jGridContentMVC_UpDate();
			dlgRevocacion.dialog('close');
	    }

	    this.eChange_cmb_vigencia = function(bean, value, _this, ui, event) {
		    
		        var op = bean.key;
		        $("#PEForm_DlgApoderados_calendar_vigenciaFin").val("");
				$("#PEForm_DlgApoderados_calendar_vigenciaInicio").val("");
				$("#PEForm_DlgApoderados_spi_vigencia").val("");
				$("#PEForm_DlgApoderados_calendar_vigenciaInicio").datepicker("option", "maxDate",null);
		        switch (op) {
		        case '0':
		        		$("#PEForm_DlgApoderados_spi_vigencia").spinner("option", "disabled", true);
		        		$("#PEForm_DlgApoderados_calendar_vigenciaInicio").datepicker("option", { disabled: true });
		        		$("#PEForm_DlgApoderados_calendar_vigenciaFin").datepicker("option", { disabled: true });
		        		
		        		break;
		        case '3':
		        		$("#PEForm_DlgApoderados_calendar_vigenciaInicio").datepicker("option", { disabled: false });
		        		$("#PEForm_DlgApoderados_spi_vigencia").spinner("option", "disabled", true);
		                $("#PEForm_DlgApoderados_calendar_vigenciaFin").datepicker("option", { disabled: false });
		                
		                break;
		            default:
		            	$("#PEForm_DlgApoderados_calendar_vigenciaInicio").datepicker("option", { disabled: false });
		                $("#PEForm_DlgApoderados_spi_vigencia").spinner("option", "disabled", false);
		                $("#PEForm_DlgApoderados_calendar_vigenciaFin").datepicker("option", { disabled: true });
		                
		                break;
		        }
		    }
		
	    this.eChange_cmb_Escritura = function(bean, value, _this, ui, event){
			if (value == '-1') {
				$("#PEForm_DlgRevocacion_cmb_Escritura_txt_text").val("").attr('disabled', false);
				$("#PEForm_DlgRevocacion_cmb_Escritura_date_fecha").val("").datepicker("option", "disabled", false);
			} else {
				$("#PEForm_DlgRevocacion_doc_Documento_digital_text").val(bean.num_documentum_instr).attr('disabled', true);
				$("#PEForm_DlgRevocacion_cmb_Escritura_date_fecha").val(bean.fec_otorgamiento_instr).datepicker("option", "disabled", true);
			}
	    }
	    
	    this.eChange_cmb_razonRevocacion = function(bean, value, _this, ui, event){
	    	switch (value) { 
				case '1':
		        	$("#PEForm_DlgRevocacion_cmb_Escritura").val("-1").attr( "disabled", false );
		        	$("#PEForm_DlgRevocacion_doc_Documento_digital_text").val("").attr('disabled',true);
		    		$("#PEForm_DlgRevocacion_cmb_Escritura_date_fecha").val("").datepicker( "option", "disabled", true );
		        	break;        
		        case '2':
		        	$("#PEForm_DlgRevocacion_cmb_Escritura").val("-1").attr( "disabled", true );
		        	$("#PEForm_DlgRevocacion_doc_Documento_digital_text").val("").attr('disabled',true);
		    		$("#PEForm_DlgRevocacion_cmb_Escritura_date_fecha").val("").datepicker( "option", "disabled", false );
		        	break;
		        case '3':
		        	$("#PEForm_DlgRevocacion_cmb_Escritura").val("-1").attr( "disabled", true );
		        	$("#PEForm_DlgRevocacion_doc_Documento_digital_text").val("").attr('disabled',true);
		    		$("#PEForm_DlgRevocacion_cmb_Escritura_date_fecha").val("").datepicker( "option", "disabled", false );
		        	break;
		        case '4':
		        	$("#PEForm_DlgRevocacion_cmb_Escritura").val("-1").attr( "disabled", true );
		        	$("#PEForm_DlgRevocacion_doc_Documento_digital_text").val("").attr('disabled',true);
		    		$("#PEForm_DlgRevocacion_cmb_Escritura_date_fecha").val("").datepicker( "option", "disabled", false );
		        	break;
		        case '5':
		        	$("#PEForm_DlgRevocacion_cmb_Escritura").val("-1").attr( "disabled", true );
		        	$("#PEForm_DlgRevocacion_doc_Documento_digital_text").val("").attr('disabled',false);
		    		$("#PEForm_DlgRevocacion_cmb_Escritura_date_fecha").val("").datepicker( "option", "disabled", false );
		        	break;
		        default:
		        	$("#PEForm_DlgRevocacion_cmb_Escritura").val("-1").attr("disabled", true );
		        	$("#PEForm_DlgRevocacion_doc_Documento_digital_text").val("").attr('disabled',true);
		    		$("#PEForm_DlgRevocacion_cmb_Escritura_date_fecha").val("").datepicker( "option", "disabled", true );
		        	break;
		    }
	    }
	    
	    this.eChange_spn_vigenciatiempo  = function(bean, value, _this, ui, event) {
			var op = $("#PEForm_DlgApoderados_cmb_vigencia").val();
			if($("#PEForm_DlgApoderados_calendar_vigenciaInicio").val()=='')return;
			var element = $("#PEForm_DlgApoderados_calendar_vigenciaInicio").attr('toField');		
			var currentDate = $("#PEForm_DlgApoderados_calendar_vigenciaInicio").datepicker("getDate");
			if(value==''){
				$("#" + element).val('');	
				return;
			}
			
			value = parseInt(value);
			switch (op) {
			case '1':			
				var date = WebKernel.addMonths(currentDate, value * 12,'dd/mm/yyyy');
				$("#" + element).val(date);
				break;
			case '2':			
				var date = WebKernel.addMonths(currentDate, value, 'dd/mm/yyyy');
				$("#" + element).val(date);
				break;
			default:
				$("#" + element).datepicker("option", "minDate",dpConfigTo.getDate(this));
			}
		}
	    
	    this.eChange_chk_GroupPoderes = function(check, input, ui, event){
			WorkGroups = [];
			WorkGroupsCount = 0;
			
			if(check){
				if($("#PEForm_DlgApoderados_Grid_gapoderSelecc").jGridContentMVC_GetList().length == 0){
					$("#PEForm_DlgApoderados_Div_gapoderSelecc").html('');
					  $("#PEForm_DlgApoderados_Grid_gapoderSelecc").jGridContentMVC_UpDate([]);
					  $("#PEForm_DlgApoderados_Grid_gapoderSelecc").jGridContentMVC_GetConfig().jQSortable = false;
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
						  $("#PEForm_DlgApoderados_Div_gapoderSelecc").html('');
						  $("#PEForm_DlgApoderados_Grid_gapoderSelecc").jGridContentMVC_UpDate([]);
						  $("#PEForm_DlgApoderados_Grid_gapoderSelecc").jGridContentMVC_GetConfig().jQSortable = false;
					  }else
						  WebForm.updController('PEForm_DlgApoderados_chk_GroupPoderes', false);
					  		
					  
					});
			}else{
				if($("#PEForm_DlgApoderados_Div_gapoderSelecc").html() == ''){
					$("#PEForm_DlgApoderados_Div_gapoderSelecc").html('');
					$("#PEForm_DlgApoderados_Grid_gapoderSelecc").jGridContentMVC_UpDate([]);
					$("#PEForm_DlgApoderados_Grid_gapoderSelecc").jGridContentMVC_GetConfig().jQSortable = true;					
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
						  $("#PEForm_DlgApoderados_Div_gapoderSelecc").html('');
						  $("#PEForm_DlgApoderados_Grid_gapoderSelecc").jGridContentMVC_UpDate([]);
						  $("#PEForm_DlgApoderados_Grid_gapoderSelecc").jGridContentMVC_GetConfig().jQSortable = true;
					  }else
						  WebForm.updController('PEForm_DlgApoderados_chk_GroupPoderes', true);
					  	  
					});
			}
			
		}
	    
	    var initDlgCopy = function(){
	    	
	    	$("#PEForm_DlgCopy_CopyType").change(function(){
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
						var list = $("#PEForm_DlgCopy_gridSel").jGridContentMVC_GetList();
						for(var i in list)
							cnyList.push(list[i].id);
						var mtype = null;
						
						var elementCheck = $("input[name='PEForm_DlgCopy_modeType']:checked");    				
	    				
	    				
						
						if($("#PEForm_DlgCopy_CopyType").prop("checked")){
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

			$("#PEForm_DlgCopy_btnSel").button({
				icon : "ui-icon-zoomin",
				showLabel : true
			}).click(
					function() {
						WebKernel.multiselectDialog('Seleccione las Empresas', 'EMPRESAS',
								'', {
									publish : function(data) {
										$.each(data, function(i, bean) {
											if (!$("#PEForm_DlgCopy_gridSel").jGridContentMVC_Exists('id',bean.id))
												$("#PEForm_DlgCopy_gridSel").jGridContentMVC_Add(bean);
										});
									},
									metadata : null
								});
					});

			$("#PEForm_DlgCopy_gridSel").jGridContentMVC({
				gridId : "PEForm_DlgCopy_gridSel",
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
	    
	    var initTextEditors = function(){
			 if ( CKEDITOR.env.ie && CKEDITOR.env.version < 9 )
					CKEDITOR.tools.enableHtml5Elements( document );
																				
				CKEDITOR.replace('PEForm_DlgApoderados_txt_descripcion');
				CKEDITOR.replace('PEForm_DlgApoderados_txt_caract');
				CKEDITOR.replace('PEForm_DlgApoderadosOtros_txt_formas_ejercerlo');
													
		 };
	    
	    initComponents();
	}


var initPackagePK_PEModule = function(){
	try{
	PEModule = new ClassPoderEspecial();
	$("#divPEMain").fadeIn('fast');
	$(".divPEMain").fadeIn('fast');
		return {init:true};
	}catch(e){	
		return {init:false,error:e};
	}
	
}