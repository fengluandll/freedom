var CatalogoModule = null;

var NUM_CATALOGO_PODERES_ESPECIALES = 63;
var TIPO_CATALOGO_PODER = "PE";

var ClassCatalogoPoderes = function () {
    var _this = this;
    var txtCatalogoQuery = $("#txtCatalogoQuery");
        
    this.engaged = false;           
    
    var dlgCapturaCatalogoPoder = "#CatalogoForm_DialogoCaptura";
            
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
    	$("#btnCatalogoQuery").click(function(){
        	var nombreCatalogo = $(txtCatalogoQuery).val().trim();        	
        	Request.CatalogoPoderes.getCatalogosPoderes(TIPO_CATALOGO_PODER, nombreCatalogo,{
                response: function (rsp) {
                	var catalogos = Util.getBeanList(rsp); 
                	$("#gridCatalogoMain").jGridContentMVC_UpDate(catalogos);
                }, error: function (xhttp, e) {
              	  sweetAlert('No se pudo realizar la operacion', 'Error:' + e,'error');
                }, complete: function () {
                	
                }
            });
        	        	
         });
    	
    	$("#txtCatalogoQuery").keypress(function(e){    		
    		if(e.which == 13 || event.keyCode == 13) {
    			$("#btnCatalogoQuery").click();
    		}        	        	
         });
                
        $("#btnCatalogoAdd").click(function(){        	
        	dlgCapturaCatalogoPoder.open('new');        	
        });
    	
        $("#gridCatalogoMain").jGridContentMVC({ 
          gridId: "gridCatalogoMain",
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
		                        		  $("#gridCatalogoMain").jGridContentMVC_Delete(iRow);
		  								  $("#gridCatalogoMain").jGridContentMVC_UpDate();
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
            	  dlgCapturaCatalogoPoder.open("edit",bean); 	              	  
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
          HeadCss: 'tableHeads',
          Heads: true,
          columns: [		                              
		                              { HeadText: 'Nombre del poder', 'width': '10%', id: 'des_podertipo', 'align': 'center' }
		                            , { HeadText: 'Descripci&oacute;n', 'width': '15%', id: 'des_facultades', type: 'action', 'align': 'left' }		                           
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
        
        initForm();
    }
    
    var initForm = function(){
       	    	
    	initDialogos();
    	initTextEditors();
    }
                                     
	var initDialogos = function(){
	    	
		dlgCapturaCatalogoPoder = $(dlgCapturaCatalogoPoder).dialog({
	    		autoOpen:false
	    		,height:600
	    		,width:800
	    		,modal:true	    		
	    	});  
						
		dlgCapturaCatalogoPoder.btnCancelar = {text: "Cancelar",
            click: function() {	                            	
            	dlgCapturaCatalogoPoder.dialog( 'close' );
            }	                            
		};
	    	   	
	    dlgCapturaCatalogoPoder.btnSigPaso1 = {text: "Siguiente",           
                    click: function() {	                            	
                    	                    	
                    	dlgCapturaCatalogoPoder.dialog( "option", "title",'Captura de catalogo de Poder');                    	                    					    	
				    	$("#CatalogoForm_DialogoCaptura_FormaDeEjercerPoder").show();	
				    	$("#CatalogoForm_DialogoCaptura_Descripcion_Del_Nuevo_Poder").show();
				    	dlgCapturaCatalogoPoder.dialog('option','height',"auto");
                    	dlgCapturaCatalogoPoder.dialog('option','width',750);                    	
                    	dlgCapturaCatalogoPoder.dialog( "option", "buttons",[dlgCapturaCatalogoPoder.btnCancelar, dlgCapturaCatalogoPoder.btnGuardar]);                    	                     	
                    }
                                                
         };
	    	    	  
	    dlgCapturaCatalogoPoder.btnGuardar = {text: "Guardar",
                    click: function() {                           	
                                        	
                    	var WorkBean;
                    	if(dlgCapturaCatalogoPoder.Mode == 'new'){
                    		WorkBean = new Object();
                    	}
                    	else{
                    		WorkBean = dlgCapturaCatalogoPoder.WorkBean;
                    	}
                    		                    	         
                    	WorkBean.des_podertipo = $("#CatalogoForm_DialogoCaptura_txt_poder").val();                    	
                    	WorkBean.des_descripcion = CKEDITOR.instances.CatalogoForm_DialogoCaptura_txt_descripcion.getData();
                    	WorkBean.des_pe_caracteristicas = CKEDITOR.instances.CatalogoForm_DialogoCaptura_txt_caract.getData();
                        WorkBean.ind_pe_delegable = $("input[name='CatalogoForm_DialogoCaptura_Delegable']:checked").val();
                        WorkBean.ind_pe_individual = $("input[name='CatalogoForm_DialogoCaptura_Individual']:checked").val();                                                												                        					
                        WorkBean.des_pe_formaejercerlo = CKEDITOR.instances.CatalogoForm_DialogoCaptura_Forma_Ejercerlo.getData();                     
						                                                       							                    
						if(WorkBean.des_descripcion.length>0)
							WorkBean.des_facultades = WorkBean.des_descripcion;
						else
							WorkBean.des_facultades = "";
						
						WorkBean.des_facultades += "Caracter&iacute;sticas/Limitaciones:" + (WorkBean.des_pe_caracteristicas.length>0 ? WorkBean.des_pe_caracteristicas : "</br></br>")						
						+ (WorkBean.ind_pe_delegable == "Ninguno" ? "" : "<b>*" +WorkBean.ind_pe_delegable+ "</b><br/>")							
							+"<br/>Forma de ejercicio:<br/>" + (WorkBean.ind_pe_individual == "Ninguno" ? "" : "<b>*" +WorkBean.ind_pe_individual+ "</b><br/>") 
							+ WorkBean.des_pe_formaejercerlo;
							
						WorkBean.ind_podertipo = TIPO_CATALOGO_PODER;
		                  if (dlgCapturaCatalogoPoder.Mode == 'new') {
		                	  Request.CatalogoPoderes.newCatalogoPoder(WorkBean,NUM_CATALOGO_PODERES_ESPECIALES,MetaSession.SessionBean.idUser,{
			                      response: function (rsp) {
			                    	  var list = Util.getBeanList(rsp);
			                    	  $("#gridCatalogoMain").jGridContentMVC_UpDate(list);
			                      }, error: function (xhttp, e) {
			                    	  sweetAlert('No se pudo realizar la operacion', 'Error:' + e,'error');
			                      }, complete: function () {
			                    	  swal("Correcto", "El catalogo se guardo correctamente", "success");
			                      }
			                  });
		                  }
		                  else{		              
								Request.CatalogoPoderes.updCatalogoPoder(WorkBean, NUM_CATALOGO_PODERES_ESPECIALES, MetaSession.SessionBean.idUser,{
									response : function(rsp) {
										var list = Util.getBeanList(rsp);
										$("#gridCatalogoMain").jGridContentMVC_UpDate(list);
									},
									error : function(xhttp, e) {
										sweetAlert('No se pudo realizar la operacion', 'Error:' + e,'error');
									},
									complete : function() {
										swal("Correcto", "La Escritura Poder se modifico correctamente", "success");
									}
								});
		                  }


                    	dlgCapturaCatalogoPoder.dialog('close');
                    }                            
	    	    		                 
	    };	    
		
		dlgCapturaCatalogoPoder.open = function(mode,WorkBean){				
			dlgCapturaCatalogoPoder.WorkBean = WorkBean;
			dlgCapturaCatalogoPoder.Mode = mode;
									
			switch (mode) {
				case 'new':
					WebForm.cleanBean('CatalogoForm_DialogoCaptura');
					$('#CatalogoForm_DialogoCaptura_txt_poder').val('');
					CKEDITOR.instances.CatalogoForm_DialogoCaptura_txt_descripcion.setData('');
					CKEDITOR.instances.CatalogoForm_DialogoCaptura_txt_caract.setData('');									
					
					$.each($( "input[name=CatalogoForm_DialogoCaptura_Delegable]" ), function(i, button) {														
						if('Delegable'==$(button).val()){							
							$(button).prop("checked", true);
							return;
						}
					});
					$.each($( "input[name=CatalogoForm_DialogoCaptura_Individual]" ), function(i, button) {														
						if('Individual'==$(button).val()){							
							$(button).prop("checked", true);
							return;
						}
					});
					
					$("#CatalogoForm_DialogoCaptura_tabs").tabs( "option", "active", 0 );									
					CKEDITOR.instances.CatalogoForm_DialogoCaptura_Forma_Ejercerlo.setData('');
					
					dlgCapturaCatalogoPoder.btnSigPaso1.click();
					dlgCapturaCatalogoPoder.dialog('open');
				break;
					
				case 'edit':					
					WebForm.setBean('CatalogoForm_DialogoCaptura', WorkBean);
					$("#CatalogoForm_DialogoCaptura_tabs").tabs( "option", "active", 0 );				
					
					WebForm.updController('CatalogoForm_DialogoCaptura_txt_poder', WorkBean.des_podertipo);
					CKEDITOR.instances.CatalogoForm_DialogoCaptura_txt_descripcion.setData(WorkBean.des_descripcion);					
					CKEDITOR.instances.CatalogoForm_DialogoCaptura_txt_caract.setData(WorkBean.des_pe_caracteristicas);
					
					$.each($( "input[name=CatalogoForm_DialogoCaptura_Delegable]" ), function(i, button) {														
						if(WorkBean.ind_pe_delegable==$(button).val()){							
							$(button).prop("checked", true);
								return;
							}
					});
					
					$.each($( "input[name=CatalogoForm_DialogoCaptura_Individual]" ), function(i, button) {														
						if(WorkBean.ind_pe_individual==$(button).val()){							
							$(button).prop("checked", true);
								return;
						}
					});
							
					CKEDITOR.instances.CatalogoForm_DialogoCaptura_Forma_Ejercerlo.setData(WorkBean.des_pe_formaejercerlo);																
				
				dlgCapturaCatalogoPoder.btnSigPaso1.click();
				dlgCapturaCatalogoPoder.dialog('open');
				break;
			}
																			
		}
	
		$("#CatalogoForm_DialogoCaptura_tabs").tabs();
		
		WebKernel.newWebForm("CatalogoForm_DialogoCaptura","CatalogoModule");
							
        }
	    		
	 var initTextEditors = function(){
		 if ( CKEDITOR.env.ie && CKEDITOR.env.version < 9 )
				CKEDITOR.tools.enableHtml5Elements( document );
															
			/*var editorElement = CKEDITOR.document.getById( 'CatalogoForm_DialogoCaptura_txt_descripcion' );
			editorElement.setAttribute( 'contenteditable', 'true' );*/
			CKEDITOR.replace('CatalogoForm_DialogoCaptura_txt_descripcion');
			CKEDITOR.replace('CatalogoForm_DialogoCaptura_txt_caract');
			CKEDITOR.replace('CatalogoForm_DialogoCaptura_Forma_Ejercerlo');
												
	 };
	
	    initComponents();
	}


$(document).ready(function(){
	try{
	CatalogoModule = new ClassCatalogoPoderes();
	$("#divCatalogoMain").fadeIn('fast');
	$(".divCatalogoMain").fadeIn('fast');		
	}catch(e){	
		sweetAlert('No se pudo cargar el modulo', 'Error:' + e,'error');
	}
	
});