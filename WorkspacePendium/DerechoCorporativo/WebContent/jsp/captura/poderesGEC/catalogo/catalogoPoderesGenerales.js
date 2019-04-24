var CatalogoPGModule = null;

var NUM_CATALOGO_PODERES_GENERALES = 62;
var TIPO_CATALOGO_PODER = "PG";

var ClassCatalogoPGPoderes = function () {
    var _this = this;
    var txtCatalogoPGQuery = $("#txtCatalogoPGQuery");
        
    this.engaged = false;           
       
    var dlgCapturaCatalogoPoderPG  = "#CatalogoPGForm_DialogoCaptura";
        
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
    	Request.CatalogoPoderes.handler = Request.CatalogoPoderes.handler;
    	        	
    	//Boton de busqueda de un Poder Especial
    	$("#btnCatalogoPGQuery").click(function(){
        	var nombreCatalogo = $(txtCatalogoPGQuery).val().trim();        	
        	Request.CatalogoPoderes.getCatalogosPoderes(TIPO_CATALOGO_PODER, nombreCatalogo,{
                response: function (rsp) {
                	var catalogos = Util.getBeanList(rsp); 
                	$("#gridCatalogoPGmain").jGridContentMVC_UpDate(catalogos);
                }, error: function (xhttp, e) {
              	  sweetAlert('No se pudo realizar la operacion', 'Error:' + e,'error');
                }, complete: function () {
                	
                }
            });
        	        	
         });
    	
    	$("#txtCatalogoPGQuery").keypress(function(e){    		
    		if(e.which == 13 || event.keyCode == 13) {
    			$("#btnCatalogoPGQuery").click();
    		}        	        	
         });
                
        $("#btnCatalogoPGAdd").click(function(){
        	dlgCapturaCatalogoPoderPG.open('new');        	     	
        });
    	
        $("#gridCatalogoPGmain").jGridContentMVC({ 
          gridId: "gridCatalogoPGmain",
          noDataMsg: 'no hay registros para mostrar',
          storage: null,
          rowClick: null,
          cellClick: function (iRow, iCell, bean, pty, gridId, td, event) {
              switch (pty) {
              case 'del':                
              	swal({
					  title: "Eliminar catalogo",
					  text: "Estas seguro que quieres eliminar este catalogo?",
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
						  Request.CatalogoPoderes.deleteCatalogoPoder( bean.id_poder_pk, MetaSession.SessionBean.idUser, {
	                          response: function (rsp) {	                        	  
	                        	  if (_this.engaged)
	  								return;	                        	  
	                        	  if(rsp == true){
	                        		  $("#gridCatalogoPGmain").jGridContentMVC_Delete(iRow);
	  								  $("#gridCatalogoPGmain").jGridContentMVC_UpDate();
	  								alert("Se elimin\u00f3 el catalogo correctamente");
	                        	  }
	                        	  else{ 	                        		  
	                        		  alert("No se puede eliminar el catalogo porque est\u00e1 siendo utilizado");
	                        	  }	                          	                          
	                          }, error: function (xhttp, e) {
		                    	  sweetAlert('No se pudo realizar la operacion', 'Error:' + e,'error');
	                          }, complete: function () {
	                        	  swal("Correcto", "El catalogo se elimino correctamente", "success");
	                          }
	                      });
					  }else
						  return;
					});

                  break;
              case 'edit':            	              	              	 
            	  dlgCapturaCatalogoPoderPG.open("edit", bean);     	  
              	break;             
              default:

                  break;
              }
          },
          rowCssA: 'evenCeld',
          rowCssB: 'oddCeld',
          rowFinal: null,
          rowAlign: 'center',
          Encabezados: true,
          HeadCss: "tableHeads",
          Heads: true,
          columns: [		                              
		                              { HeadText: 'Nombre del poder', 'width': '10%', id: 'des_podertipo', 'align': 'center' }		                            
		                            , { HeadText: 'Actos de dominio', 'width': '15%', id: 'des_actosdominio', type: 'action', 'align': 'left' }
		                            , { HeadText: 'Actos de administraci&oacute;n', 'width': '15%', id: 'des_actosadmon', type: 'action', 'align': 'left' }
		                            , { HeadText: 'T&iacute;tulos de cr&eacute;dito', 'width': '15%', id: 'des_titulosdecreditos', type: 'action', 'align': 'left' }
		                            , { HeadText: 'Pleitos y cobranzas', 'width': '15%', id: 'des_pleitoscobranzas', type: 'action', 'align': 'left' }
		                            , { HeadText: 'Editar', 'width': '5%', id: 'edit', type: 'action', 'align': 'center', cellValue: 'eval', eval: function () {
                                        return '<div class="Img_Icon_edit"></div>';
                                    }
                                    }
                                    , { HeadText: 'Eliminar', 'width': '5%', id: 'del', type: 'action', 'align': 'center', cellValue: 'eval', eval: function () {
                                    	return '<div class="Img_Icon_delete"></div>';
                                    }
                                    }
                                    ],
          tblCssClass: "",
          tblStyle: "width:100%",
          cellpadding: '3',
          cellspacing: '3',
          border: '0',
          afterDrawing: function () {
              
          }
      });
        
        initForms();
    }
    
    var initForms = function(){       	    	
    	initDialogos();
    	initTextEditors();
    }
                                     
	var initDialogos = function(){
	    	
		dlgCapturaCatalogoPoderPG = $(dlgCapturaCatalogoPoderPG).dialog({
    		autoOpen:false
    		,height:600
    		,width:800
    		,modal:true	    		
    	});  
				
		dlgCapturaCatalogoPoderPG.btnCancelar = {text: "Cancelar",
	            click: function() {	                            	
	            	dlgCapturaCatalogoPoderPG.dialog( 'close' );
	            }	                            
			};
			    
	    dlgCapturaCatalogoPoderPG.btnSigPaso1 = {text: "Siguiente",           
                click: function() {	                            	
                	                    	
                	dlgCapturaCatalogoPoderPG.dialog( "option", "title",'Captura de catalogo de Poder General');                    	                    					    	
			    	$("#CatalogoPGForm_DialogoCaptura").show();				    	
			    	dlgCapturaCatalogoPoderPG.dialog('option','height',"auto");
			    	dlgCapturaCatalogoPoderPG.dialog('option','width',750);                    	
			    	dlgCapturaCatalogoPoderPG.dialog( "option", "buttons",[dlgCapturaCatalogoPoderPG.btnCancelar, dlgCapturaCatalogoPoderPG.btnGuardar]);                    	                     	
                }                                            
       };
	         	   	     
	    dlgCapturaCatalogoPoderPG.btnGuardar = {text: "Guardar",
                click: function() {                           	
                                    	
                	var WorkBean;
                	if(dlgCapturaCatalogoPoderPG.Mode == 'new'){
                		WorkBean = new Object();
                	}
                	else{
                		WorkBean = dlgCapturaCatalogoPoderPG.WorkBean;
                	}
                	//Descripcion del poder
                	WorkBean.des_podertipo = $("#CatalogoPGForm_DialogoCaptura_txt_poder").val();
                	WorkBean.des_descripcion = CKEDITOR.instances.CatalogoPGForm_DialogoCaptura_txt_descripcion.getData();

                	//Actos de dominio
                	WorkBean.ind_tiene_ad = $("#CatalogoPGForm_DialogoCapturaOtros_chk_Tiene_ad").is(":checked") ? 1 : 0; 
                	if(WorkBean.ind_tiene_ad == 1){
	                	WorkBean.ind_ad_delegable = $("input[name='CatalogoPGForm_DialogoCaptura_Delegable_ad']:checked").val();
	                	WorkBean.ind_ad_individual = $("input[name='CatalogoPGForm_DialogoCaptura_Individual_ad']:checked").val();
	                	WorkBean.des_ad_caracteristicas = CKEDITOR.instances.CatalogoPGForm_DialogoCapturaOtros_txt_ad_caracteristicas.getData();
	                	WorkBean.des_ad_formaejercerlo = CKEDITOR.instances.CatalogoPGForm_DialogoCapturaOtros_txt_ad_formaejercerlo.getData();
	                	WorkBean.des_actosdominio = "Caracter&iacute;sticas/Limitaciones:<br/>" + (WorkBean.des_ad_caracteristicas.length>0 ? WorkBean.des_ad_caracteristicas : "<br/>")
	                	+ (WorkBean.ind_ad_delegable == "Ninguno" ? "" : "<b>*" +WorkBean.ind_ad_delegable+ "</b>") 
	                	+ "<br><br>Forma de ejercicio:<br/>" + (WorkBean.ind_ad_individual == "Ninguno" ? "" : "<b>*" +WorkBean.ind_ad_individual+ "</b><br/><br/>") 
	                	+ WorkBean.des_ad_formaejercerlo;
                	}
                	else{
                		WorkBean.des_actosdominio = "Caracter&iacute;sticas/Limitaciones:<br/><br/><br/>" +                    	
                    	"No tienen estas facultades <br/><br/><br/>"+ "Forma de ejercicio:";
                	}
                	
                	
                	//Actos de administracion
                	WorkBean.ind_tiene_aa = $("#CatalogoPGForm_DialogoCapturaOtros_chk_Tiene_aa").is(":checked") ? 1 : 0;
                	if(WorkBean.ind_tiene_aa == 1){	                	 
	                	WorkBean.ind_aa_delegable = $("input[name='CatalogoPGForm_DialogoCaptura_Delegable_aa']:checked").val();
	                	WorkBean.ind_aa_individual = $("input[name='CatalogoPGForm_DialogoCaptura_Individual_aa']:checked").val();
	                	WorkBean.des_aa_caracteristicas = CKEDITOR.instances.CatalogoPGForm_DialogoCapturaOtros_txt_aa_caracteristicas.getData();
	                	WorkBean.des_aa_formaejercerlo = CKEDITOR.instances.CatalogoPGForm_DialogoCapturaOtros_txt_aa_formaejercerlo.getData();
	                	WorkBean.des_actosadmon = "Caracter&iacute;sticas/Limitaciones:<br/>" + (WorkBean.des_aa_caracteristicas.length>0 ? WorkBean.des_aa_caracteristicas : "<br/>")
	                	+ (WorkBean.ind_aa_delegable == "Ninguno" ? "" : "<b>*" +WorkBean.ind_aa_delegable+ "</b>") 
	                	+ "<br><br>Forma de ejercicio:<br/>" + (WorkBean.ind_aa_individual == "Ninguno" ? "" : "<b>*" +WorkBean.ind_aa_individual+ "</b><br/><br/>") 
	                	+ WorkBean.des_aa_formaejercerlo;
                	}
                	else{
                		WorkBean.des_actosadmon = "Caracter&iacute;sticas/Limitaciones:<br/><br/><br/>" +                    	
                    	"No tienen estas facultades <br/><br/><br/>"+ "Forma de ejercicio:";
                	}
                	
                	//Titulos de credito
                	WorkBean.ind_tiene_tc = $("#CatalogoPGForm_DialogoCapturaOtros_chk_Tiene_tc").is(":checked") ? 1 : 0;
                	if(WorkBean.ind_tiene_tc == 1){                		
	                	WorkBean.ind_tc_delegable = $("input[name='CatalogoPGForm_DialogoCaptura_Delegable_tc']:checked").val();
	                	WorkBean.ind_tc_individual = $("input[name='CatalogoPGForm_DialogoCaptura_Individual_tc']:checked").val();
	                	WorkBean.des_tc_caracteristicas = CKEDITOR.instances.CatalogoPGForm_DialogoCapturaOtros_txt_tc_caracteristicas.getData();
	                	WorkBean.des_tc_formaejercerlo = CKEDITOR.instances.CatalogoPGForm_DialogoCapturaOtros_txt_tc_formaejercerlo.getData();
	                	WorkBean.des_titulosdecreditos = "Caracter&iacute;sticas/Limitaciones:<br/>" + (WorkBean.des_tc_caracteristicas.length>0 ? WorkBean.des_tc_caracteristicas : "<br/>")
	                	+ (WorkBean.ind_tc_delegable == "Ninguno" ? "" : "<b>*" +WorkBean.ind_tc_delegable+ "</b>") 
	                	+ "<br><br>Forma de ejercicio:<br/>" + (WorkBean.ind_tc_individual == "Ninguno" ? "" : "<b>*" +WorkBean.ind_tc_individual+ "</b><br/><br/>")
	                	+ WorkBean.des_tc_formaejercerlo;
                	}
                	else{
                		WorkBean.des_titulosdecreditos = "Caracter&iacute;sticas/Limitaciones:<br/><br/><br/>" +                    	
                    	"No tienen estas facultades <br/><br/><br/>"+ "Forma de ejercicio:";
                	}
                	
                	//Pleitos y cobranzas
                	WorkBean.ind_tiene_pc = $("#CatalogoPGForm_DialogoCapturaOtros_chk_Tiene_pc").is(":checked") ? 1 : 0;
                	if(WorkBean.ind_tiene_pc == 1){
	                	WorkBean.ind_pc_delegable = $("input[name='CatalogoPGForm_DialogoCaptura_Delegable_pc']:checked").val();
	                	WorkBean.ind_pc_individual = $("input[name='CatalogoPGForm_DialogoCaptura_Individual_pc']:checked").val();
	                	WorkBean.des_pc_caracteristicas = CKEDITOR.instances.CatalogoPGForm_DialogoCapturaOtros_txt_pc_caracteristicas.getData();
	                	WorkBean.des_pc_formaejercerlo = CKEDITOR.instances.CatalogoPGForm_DialogoCapturaOtros_txt_pc_formaejercerlo.getData();
	                	WorkBean.des_pleitoscobranzas = "Caracter&iacute;sticas/Limitaciones:<br/>" + (WorkBean.des_pc_caracteristicas.length>0 ? WorkBean.des_pc_caracteristicas : "<br/>")
	                	+ (WorkBean.ind_pc_delegable == "Ninguno" ? "" : "<b>*" +WorkBean.ind_pc_delegable+ "</b>") 
	                	+ "<br><br>Forma de ejercicio:<br/>" + (WorkBean.ind_pc_individual == "Ninguno" ? "" : "<b>*" +WorkBean.ind_pc_individual+ "</b><br/><br/>") 
	                	+ WorkBean.des_pc_formaejercerlo;
                	}
                	else{
                		WorkBean.des_pleitoscobranzas = "Caracter&iacute;sticas/Limitaciones:<br/><br/><br/>" +                    	
                    	"No tienen estas facultades <br/><br/><br/>"+ "Forma de ejercicio:";
                	}
                	
                	WorkBean.ind_podertipo = TIPO_CATALOGO_PODER;						
	                 
						if (dlgCapturaCatalogoPoderPG.Mode == 'new') {
	                	  Request.CatalogoPoderes.newCatalogoPoder(WorkBean,NUM_CATALOGO_PODERES_GENERALES,MetaSession.SessionBean.idUser,{
		                      response: function (rsp) {
		                    	  var list = Util.getBeanList(rsp);
		                    	  $("#gridCatalogoPGmain").jGridContentMVC_UpDate(list);
		                      }, error: function (xhttp, e) {
		                    	  sweetAlert('No se pudo realizar la operacion', 'Error:' + e,'error');
		                      }, complete: function () {
		                    	  swal("Correcto", "El catalogo se guardo correctamente", "success");
		                      }
		                  });
	                  }
	                  else{
	              
							Request.CatalogoPoderes.updCatalogoPoder(WorkBean, NUM_CATALOGO_PODERES_GENERALES, MetaSession.SessionBean.idUser,{
								response : function(rsp) {
									var list = Util.getBeanList(rsp);
									$("#gridCatalogoPGmain").jGridContentMVC_UpDate(list);
								},
								error : function(xhttp, e) {
									sweetAlert('No se pudo realizar la operacion', 'Error:' + e,'error');
								},
								complete : function() {
									swal("Correcto", "El catalogo se modifico correctamente", "success");
								}
							});
	                  }

	                  dlgCapturaCatalogoPoderPG.dialog('close');
                } 
	    };
		
		dlgCapturaCatalogoPoderPG.open = function(mode,WorkBean){	
			dlgCapturaCatalogoPoderPG.WorkBean = WorkBean;
			dlgCapturaCatalogoPoderPG.Mode = mode;
									
			switch (mode) {
			case 'new':
				WebForm.cleanBean('CatalogoPGForm_DialogoCaptura');
				$('#CatalogoPGForm_DialogoCaptura_txt_poder').val('');
				CKEDITOR.instances.CatalogoPGForm_DialogoCaptura_txt_descripcion.setData('');
				CKEDITOR.instances.CatalogoPGForm_DialogoCapturaOtros_txt_aa_caracteristicas.setData('');
				CKEDITOR.instances.CatalogoPGForm_DialogoCapturaOtros_txt_aa_formaejercerlo.setData('');
				CKEDITOR.instances.CatalogoPGForm_DialogoCapturaOtros_txt_ad_caracteristicas.setData('');
				CKEDITOR.instances.CatalogoPGForm_DialogoCapturaOtros_txt_ad_formaejercerlo.setData('');
				CKEDITOR.instances.CatalogoPGForm_DialogoCapturaOtros_txt_tc_caracteristicas.setData('');
				CKEDITOR.instances.CatalogoPGForm_DialogoCapturaOtros_txt_tc_formaejercerlo.setData('');
				CKEDITOR.instances.CatalogoPGForm_DialogoCapturaOtros_txt_pc_caracteristicas.setData('');
				CKEDITOR.instances.CatalogoPGForm_DialogoCapturaOtros_txt_pc_formaejercerlo.setData('');
				
				$.each($( "input[name=CatalogoPGForm_DialogoCaptura_Delegable_aa]" ), function(i, button) {														
					if('Delegable'==$(button).val()){							
						$(button).prop("checked", true);
						return;
					}
				});
				$.each($( "input[name=CatalogoPGForm_DialogoCaptura_Individual_aa]" ), function(i, button) {														
					if('Individual'==$(button).val()){							
						$(button).prop("checked", true);
						return;
					}
				});
				$.each($( "input[name=CatalogoPGForm_DialogoCaptura_Delegable_ad]" ), function(i, button) {														
					if('Delegable'==$(button).val()){							
						$(button).prop("checked", true);
						return;
					}
				});
				$.each($( "input[name=CatalogoPGForm_DialogoCaptura_Individual_ad]" ), function(i, button) {														
					if('Individual'==$(button).val()){							
						$(button).prop("checked", true);
						return;
					}
				});
				$.each($( "input[name=CatalogoPGForm_DialogoCaptura_Delegable_tc]" ), function(i, button) {														
					if('Delegable'==$(button).val()){							
						$(button).prop("checked", true);
						return;
					}
				});
				$.each($( "input[name=CatalogoPGForm_DialogoCaptura_Individual_tc]" ), function(i, button) {														
					if('Individual'==$(button).val()){							
						$(button).prop("checked", true);
						return;
					}
				});
				$.each($( "input[name=CatalogoPGForm_DialogoCaptura_Delegable_pc]" ), function(i, button) {														
					if('Delegable'==$(button).val()){							
						$(button).prop("checked", true);
						return;
					}
				});
				$.each($( "input[name=CatalogoPGForm_DialogoCaptura_Individual_pc]" ), function(i, button) {														
					if('Individual'==$(button).val()){							
						$(button).prop("checked", true);
						return;
					}
				});
				
				$("#CatalogoPGForm_DialogoCaptura_tabs").tabs( "option", "active", 0 );				
				
				dlgCapturaCatalogoPoderPG.btnSigPaso1.click();
				dlgCapturaCatalogoPoderPG.dialog('open');
				break;
			case 'edit':
				WebForm.setBean('CatalogoPGForm_DialogoCaptura', WorkBean);
				$("#CatalogoPGForm_DialogoCaptura_tabs").tabs( "option", "active", 0 );
				
				WebForm.updController('CatalogoPGForm_DialogoCaptura_txt_poder', WorkBean.des_podertipo);
				CKEDITOR.instances.CatalogoPGForm_DialogoCaptura_txt_descripcion.setData(WorkBean.des_descripcion);
				
				//Actos de administracion
				if(WorkBean.ind_tiene_aa == 1){
					$("#CatalogoPGForm_DialogoCapturaOtros_chk_Tiene_aa").prop("checked", true);
					$("#CatalogoPGForm_DialogoCapturaOtros_chk_aA_Table").show();
				}
				else{
					$("#CatalogoPGForm_DialogoCapturaOtros_chk_Tiene_aa").prop("checked", false);
					$("#CatalogoPGForm_DialogoCapturaOtros_chk_aA_Table").hide();
				}
				
				CKEDITOR.instances.CatalogoPGForm_DialogoCapturaOtros_txt_aa_caracteristicas.setData(WorkBean.des_aa_caracteristicas);
				CKEDITOR.instances.CatalogoPGForm_DialogoCapturaOtros_txt_aa_formaejercerlo.setData(WorkBean.des_aa_formaejercerlo);
				
				$.each($( "input[name=CatalogoPGForm_DialogoCaptura_Delegable_aa]" ), function(i, button) {														
					if(WorkBean.ind_aa_delegable==$(button).val()){							
						$(button).prop("checked", true);
							return;
						}
				});				
				$.each($( "input[name=CatalogoPGForm_DialogoCaptura_Individual_aa]" ), function(i, button) {														
					if(WorkBean.ind_aa_individual==$(button).val()){							
						$(button).prop("checked", true);
							return;
					}
				});
						
				//Actos de dominio
				if(WorkBean.ind_tiene_ad == 1){
					$("#CatalogoPGForm_DialogoCapturaOtros_chk_Tiene_ad").prop("checked", true);
					$("#CatalogoPGForm_DialogoCapturaOtros_chk_aD_Table").show();
				}
				else{
					$("#CatalogoPGForm_DialogoCapturaOtros_chk_Tiene_ad").prop("checked", false);
					$("#CatalogoPGForm_DialogoCapturaOtros_chk_aD_Table").hide();
				}
				
				CKEDITOR.instances.CatalogoPGForm_DialogoCapturaOtros_txt_ad_caracteristicas.getData(WorkBean.des_ad_caracteristicas);
				CKEDITOR.instances.CatalogoPGForm_DialogoCapturaOtros_txt_ad_formaejercerlo.setData(WorkBean.des_ad_formaejercerlo);
				
				$.each($( "input[name=CatalogoPGForm_DialogoCaptura_Delegable_ad]" ), function(i, button) {														
					if(WorkBean.ind_ad_delegable==$(button).val()){							
						$(button).prop("checked", true);
							return;
						}
				});				
				$.each($( "input[name=CatalogoPGForm_DialogoCaptura_Individual_ad]" ), function(i, button) {														
					if(WorkBean.ind_ad_individual==$(button).val()){							
						$(button).prop("checked", true);
							return;
					}
				});
				
				//Titulos de credito
				if(WorkBean.ind_tiene_tc == 1){
					$("#CatalogoPGForm_DialogoCapturaOtros_chk_Tiene_tc").prop("checked", true);
					$("#CatalogoPGForm_DialogoCapturaOtros_chk_tC_Table").show();
				}
				else{
					$("#CatalogoPGForm_DialogoCapturaOtros_chk_Tiene_tc").prop("checked", false);
					$("#CatalogoPGForm_DialogoCapturaOtros_chk_tC_Table").hide();
				}
				
				CKEDITOR.instances.CatalogoPGForm_DialogoCapturaOtros_txt_tc_caracteristicas.setData(WorkBean.des_tc_caracteristicas);
				CKEDITOR.instances.CatalogoPGForm_DialogoCapturaOtros_txt_tc_formaejercerlo.setData(WorkBean.des_tc_formaejercerlo);
				
				$.each($( "input[name=CatalogoPGForm_DialogoCaptura_Delegable_tc]" ), function(i, button) {														
					if(WorkBean.ind_tc_delegable==$(button).val()){							
						$(button).prop("checked", true);
							return;
						}
				});				
				$.each($( "input[name=CatalogoPGForm_DialogoCaptura_Individual_tc]" ), function(i, button) {														
					if(WorkBean.ind_tc_individual==$(button).val()){							
						$(button).prop("checked", true);
							return;
					}
				});
				
				//Pleitos y cobranzas
				if(WorkBean.ind_tiene_pc == 1){
					$("#CatalogoPGForm_DialogoCapturaOtros_chk_Tiene_pc").prop("checked", true);
					$("#CatalogoPGForm_DialogoCapturaOtros_chk_pC_Table").show();
				}
				else{
					$("#CatalogoPGForm_DialogoCapturaOtros_chk_Tiene_pc").prop("checked", false);
					$("#CatalogoPGForm_DialogoCapturaOtros_chk_pC_Table").hide();
				}
				
				CKEDITOR.instances.CatalogoPGForm_DialogoCapturaOtros_txt_pc_caracteristicas.setData(WorkBean.des_pc_caracteristicas);
				CKEDITOR.instances.CatalogoPGForm_DialogoCapturaOtros_txt_pc_formaejercerlo.setData(WorkBean.des_pc_formaejercerlo);
				
				$.each($( "input[name=CatalogoPGForm_DialogoCaptura_Delegable_pc]" ), function(i, button) {														
					if(WorkBean.ind_pc_delegable==$(button).val()){							
						$(button).prop("checked", true);
							return;
						}
				});				
				$.each($( "input[name=CatalogoPGForm_DialogoCaptura_Individual_pc]" ), function(i, button) {														
					if(WorkBean.ind_pc_individual==$(button).val()){							
						$(button).prop("checked", true);
							return;
					}
				});
				
				dlgCapturaCatalogoPoderPG.btnSigPaso1.click();
				dlgCapturaCatalogoPoderPG.dialog('open');
				break;
			}
	}
		
		
		$("#CatalogoPGForm_DialogoCaptura_tabs").tabs();
		
		WebKernel.newWebForm("CatalogoPGForm_DialogoCaptura","CatalogoPGModule");
					
        }
	
	var initTextEditors = function(){
		 if ( CKEDITOR.env.ie && CKEDITOR.env.version < 9 )
				CKEDITOR.tools.enableHtml5Elements( document );
															
			/*var editorElement = CKEDITOR.document.getById( 'CatalogoForm_DialogoCaptura_txt_descripcion' );
			editorElement.setAttribute( 'contenteditable', 'true' );*/
			CKEDITOR.replace('CatalogoPGForm_DialogoCaptura_txt_descripcion');
			//Editores de texto en Actos de administracion
			CKEDITOR.replace('CatalogoPGForm_DialogoCapturaOtros_txt_aa_caracteristicas');
			CKEDITOR.replace('CatalogoPGForm_DialogoCapturaOtros_txt_aa_formaejercerlo');
			//Editores de texto en Actos de dominio
			CKEDITOR.replace('CatalogoPGForm_DialogoCapturaOtros_txt_ad_caracteristicas');
			CKEDITOR.replace('CatalogoPGForm_DialogoCapturaOtros_txt_ad_formaejercerlo');
			//Editores de texto en Tiutlos de credito
			CKEDITOR.replace('CatalogoPGForm_DialogoCapturaOtros_txt_tc_caracteristicas');
			CKEDITOR.replace('CatalogoPGForm_DialogoCapturaOtros_txt_tc_formaejercerlo');
			//Editores de texto en Pleitos y cobranza
			CKEDITOR.replace('CatalogoPGForm_DialogoCapturaOtros_txt_pc_caracteristicas');
			CKEDITOR.replace('CatalogoPGForm_DialogoCapturaOtros_txt_pc_formaejercerlo');
											
	 };
	
	    initComponents();
	}


$(document).ready(function(){
	try{
	CatalogoPGModule = new ClassCatalogoPGPoderes();
	$("#divCatalogoPGMain").fadeIn('fast');
	$(".divCatalogoPGMain").fadeIn('fast');		
	}catch(e){	
		sweetAlert('No se pudo cargar el modulo', 'Error:' + e,'error');
	}
	
});