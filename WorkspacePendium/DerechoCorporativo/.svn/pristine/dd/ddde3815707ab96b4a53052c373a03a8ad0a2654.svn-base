var PGModule = null;

$(document).ready(function () {
    PGModule = new ClassPoderGeneral();
});

var ClassPoderGeneral = function () {
    var _this = this;
    var txtPGQuery = $("#txtPGQuery");
    var Animation = false;
    
    this.engaged = false;
    var Grid_apoderados = new Array();
    
    var dlgPoderes = "#PGForm_DlgPoderes";
    var dlgApoderados = "#PGForm_DlgApoderados";
    var dlgApoderadosOtros = "#PGForm_DlgApoderadosOtros";
    var dlgRevocacion = "#PGForm_DlgRevocacion";
    
    var btnPGAdd_click = function(){
    	$("#divPGMain").hide();
    	$("#divPGForm").show();
    }
    
    var initComponents = function () {
        $("#tabsPoderes").tabs();
        $("#btnPGQuery").button();
        $("#btnPGAdd").click(btnPGAdd_click);
        $("#gridPGmain").jGridContentMVC({ gridId: "gridPGmain",
          noDataMsg: 'no hay registros para mostrar',
          storage: [{ esc: '55,554',C1:'31/01/2015', C2: 'Abraham Alberto L\u00F3pez Nava<br /> Adolfo Carlos Castell<br />Adriana DEDaz Galindo<br />Adriana Paulina L\u00F3pez Qui\u00F1ones', C3: '&#9679; VIP <br /> &#9679; Gerentes Generales', C4: 'Si', C5: 'N/A', C6: 'N/A', C7: 'N/A', C8: '1' }
          , { esc: '55,554',C1:'25/03/2015', C2: 'Adriana D\u00EDaz Galindo<br />Adriana Paulina L\u00F3pez Qui\u00F1ones', C3: '&#9679; VIP', C4: 'No', C5: 'N/A', C6: 'N/A', C7: 'N/A', C8: '1'}],
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
          rowCssA: 'odd',
          rowCssB: 'even',
          rowFinal: null,
          rowAlign: 'center',
          Encabezados: true,
          HeadCss: "HHead",
          Heads: true,
          columns: [
		                              { HeadText: 'Escritura No.', 'width': '10%', id: 'esc', 'align': 'left' }
		                            , { HeadText: 'Fecha', 'width': '10%', id: 'C1', 'align': 'center' }
		                            , { HeadText: 'Apoderados', 'width': '35%', id: 'C2', type: 'action', 'align': 'left' }
                                    , { HeadText: 'Asunto', 'width': '15%', id: 'C3', type: 'action', 'align': 'left' }
                                    , { HeadText: 'RPPC', 'width': '5%', id: 'C4', type: 'action', 'align': 'left' }
                                    , { HeadText: 'Estatus', 'width': '5%', id: 'C4', type: 'action', 'align': 'center', cellValue: 'eval', eval: function (bean) {
                                        var sem = bean.C4 == 1 ? 'green' : bean.C4 == 2 ? 'red' : 'yellow';
                                        return '<div class="Img_Semaforo_'+sem+'"></div>';
                                    }
                                    }         
                                    , { HeadText: 'Esc.', 'width': '5%', id: 'C8', type: 'action', 'align': 'center', cellValue: 'eval', eval: function (bean) {
                                        var sem = bean.C8 == 1 ? 'green' : bean.C8 == 2 ? 'red' : 'yellow';
                                        return '<div class="Img_Semaforo_'+sem+'"></div>';
                                    }
                                    }
                                    , { HeadText: 'Copiar', 'width': '5%', id: 'edit', type: 'action', 'align': 'center', cellValue: 'eval', eval: function () {
                                        return '<div class="Img_Icon_edit"></div>';
                                    }
                                    }
                                    , { HeadText: 'Editar', 'width': '5%', id: 'edit', type: 'action', 'align': 'center', cellValue: 'eval', eval: function () {
                                        return '<div class="Img_Icon_edit"></div>';
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
    }
    
    var initForm = function(){
    	$(".divSeccHead").click(function(){
    		if($(this).attr("toggleContent")=="false")return;
    		$("#"+this.id+"Content").toggle();
    	});    	
    	
    	$("#divPGFormBtnClose").button().click(function(){
    		$("#divPGForm").hide();
        	$("#divPGMain").show(); 		
    	});
    	
    	$(".divPGForm").each(function(){
    		
    	});
    	
    	$("#PGForm_Btn_poderSelecc").click(function(){
    		
    		var list = $("#PGForm_Grid_poderSelecc").jGridContentMVC_GetList();
	    		$(".PGForm_Grid_apoderados").each(function(i,input){
	    			$(input).prop("checked",false); 
	    		});
	    		
    			$(".PGForm_Grid_apoderados").each(function(i,input){
    				$(list).each(function(i,bean){
	    			if($(input).val() == bean.C1 )
	    				$(input).prop("checked",true);    				
    				});
    			});
    		
    		dlgPoderes.dialog('open');
    	});
    	
    	WebKernel.newWebForm("divPGForm",{
    		ind_requiere_proto:{ toggleDiv:'#divPGForm_RP'}
    		,ind_requiere_inscr_rppc:{ toggleDiv:'#divPGForm_RP'}
    		,ind_aplica_status:{ toggleDiv:'#divPGFormStatus'}});
    	    	
    	
    	$("#PGForm_Grid_poderSelecc").jGridContentMVC({ gridId: "PGForm_Grid_poderSelecc",
            noDataMsg: 'ningun poder seleccionado, por favor seleccione un poder',
            storage: [],
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
                                       { HeadText: 'Poderes', 'width': '5%', id: 'C1', 'align': 'left' }         
                                      
                                      ],
            tblCssClass: "",
            tblStyle: "width:100%",
            cellpadding: '0',
            cellspacing: '1',
            border: '0',
            afterDrawing: function () {
                
            }
        });
    	
    	$("#PGForm_Grid_apoderados").jGridContentMVC({ gridId: "PGForm_Grid_apoderados",
            noDataMsg: 'ningun poder seleccionado, por favor seleccione un poder',
            storage: [],
            rowClick: null,
            cellClick: function (iRow, iCell, bean, pty, gridId, td, event) {
                switch (pty) {
                    case 'del':
                        if (_this.engaged) return;                                          

                        break;
                    case 'otorgar':
                    	$(dlgApoderados).data('WorkBean',bean);
                    	$(dlgApoderados).dialog('open');
                    	break;
                    case 'revocar':                    	
                    	dlgRevocacion.open(bean); 	
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
            rowCssA: 'odd',
            rowCssB: 'even',
            rowFinal: null,
            rowAlign: 'center',
            Encabezados: true,
            HeadCss: "HHead",
            Heads: true,
            columns: [
  		                              { HeadText: 'Orden No.', 'width': '5%', id: 'C2', 'align': 'left' }  		                            
  		                            , { HeadText: 'Apoderados', 'width': '20%', id: 'C3', type: 'action', 'align': 'left', cellValue: 'eval', eval: function (bean,pty,column,iCell,iRow,config) {
  		                            	
  		                            	var names = '';  		                            	
  		                            	$(bean.Apoderados).each(function(i,apod){
  		                            		apod.apeIndex = (iRow+1)+'.'+(i+1);
  		                            		names += apod.C1 + (apod.Revocar== null ? "" : ' <label style="color:red;">'+apod.apeIndex+'</label>') + "<br />";
  		                            	});
  		                            	return names;
                                    }
                                    }
                                      , { HeadText: 'Actos de Dominio', 'width': '10%', id: 'C4', type: 'action', 'align': 'left' }
                                      , { HeadText: 'Actos de Administraci\u00F3n', 'width': '10%', id: 'C5', type: 'action', 'align': 'left' }
                                      , { HeadText: 'T\u00EDtulos de Cr\u00E9dito', 'width': '10%', id: 'C6', type: 'action', 'align': 'left' }
                                      , { HeadText: 'Pleitos y Cobranzas', 'width': '14%', id: 'C7', type: 'action', 'align': 'left' }
                                      , { HeadText: 'Caracter\u00EDsticas', 'width': '14%', id: 'C8', type: 'action', 'align': 'left' }
                                      , { HeadText: 'Vigencia', 'width': '10%', id: 'C9', type: 'action', 'align': 'left' }                                      
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
            	/*
            		 * var getText = function(apod){
              		var txt = "";
              		
              		return txt;
              	}
            		 * var ClassRevocar = function(){
this.Escritura=arguments[0];
this.Concepto=arguments[1];
this.Fecha=arguments[2];
this.NoDoc=arguments[3];
}
            		 * */
            	var  apendices = "";
            	$.each(config.storage,function(i,bean){
            		if(bean.Apoderados){
            			$(bean.Apoderados).each(function(i,apod){                      		
                      		if(apod.Revocar!= null){
                      			var apeIndex = '<label style="color:red;">'+apod.apeIndex+'</label>'
                      			switch(apod.Revocar.Concepto){
                      			case '1': apendices+="<br />"+apeIndex+" Mandato por termin\u00F3 por: Revocaci\u00F3n "+apod.Revocar.Escritura; break;
                      			case '2': apendices+="<br />"+apeIndex+" Mandato Termin\u00F3 por: Muerte "+apod.Revocar.Fecha; break;
                      			case '3': apendices+="<br />"+apeIndex+" Mandato Termin\u00F3 por Vigencia "+apod.Revocar.Fecha; break;
                      			case '4': apendices+="<br />"+apeIndex+" Mandato por termin\u00F3 por: Se agot\u00F3 el objeto"+apod.Revocar.Fecha; break;
                      			}
                      		}
                      	});
            		}
            	});
            	
            	$("#PGForm_lbl_apoderados_revocados").html(apendices);
            	
              	
            }
        });
    	
    	initDlgPoderes();
    	initDlgApoderados();
    	initDlgApoderadosOtros();
    	initDlgRevocacion();
    }
    
    
    
    var initDlgPoderes = function(){
    	dlgPoderes = $(dlgPoderes).dialog({
    		autoOpen:false
    		,height:400
    		,width:400
    		,modal:true
    		,buttons:{    			
    			Cancelar:function(){
    				$("#PGForm_Btn_poderAddCarta").hide();
        			$("#PGForm_DlgPoderes_Chk_selecTodos").prop("checked",false);
                	$(".PGForm_Grid_apoderados").each(function(i,input){            			    				
            				$(input).prop("checked",false);
            		});	
    				dlgPoderes.dialog('close'); 				
    			}
    			,"Aceptar Selecci\u00F3n":dlgPoderes_accept
    		}
    	});    	
    	
    	$("#PGForm_Btn_poderAddCarta").click(function(){ 
    		var bean = $("#PGForm_Grid_apoderados").jGridContentMVC_Get(0);
    		$(dlgApoderadosOtros).data('WorkBean',bean);
    		dlgApoderadosOtros.dialog('open'); 	
    	});
    	
    	
    	$("#PGForm_DlgPoderes_Chk_selecTodos").change(function(){
    		var check = $(this).is(":checked");
    		$("#PGForm_Btn_poderAddCarta").hide();
    		$(".PGForm_Grid_apoderados").each(function(i,input){
    			if($(input).val() == "Otro" )
    				$(input).prop("checked",false);
    			else
    				$(input).prop("checked",check);
    		});		
    		
    	});   	
    	
    	
    	$("#PGForm_DlgPoderes_Grid_poderes").jGridContentMVC({ gridId: "PGForm_DlgPoderes_Grid_poderes",
            noDataMsg: 'no hay registros para mostrar',
            storage: [{ C1: 'VIP'},{ C1: 'STAFF'},{ C1: 'Gerentes Generales'},{ C1: 'Gerentes Especiales'},{ C1: 'Otro'}],
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
                                       { HeadText: 'Poderes', 'width': '5%', id: 'C1', type: 'action', 'align': 'left', cellValue: 'eval', eval: function (bean) {
                                          var id = 'PGForm_Grid_apoderados'+bean.C1;
                                          return '<input type="checkbox" id="'+id+'" name="'+id+'" class="PGForm_Grid_apoderados" value="'+bean.C1+'"/><label for="'+id+'">'+bean.C1+'</label>';
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
    	
    	$("#PGForm_Grid_apoderadosOtro").change(function(){
        	var check = $(this).is(":checked");
    		if(check){ 
    			$("#PGForm_Btn_poderAddCarta").show();
    			$("#PGForm_DlgPoderes_Chk_selecTodos").prop("checked",false);
            	$(".PGForm_Grid_apoderados").each(function(i,input){
        			if($(input).val() != "Otro" )            				
        				$(input).prop("checked",false);
        		});	
    		}else
    			$("#PGForm_Btn_poderAddCarta").hide();
        });
    }
    
    var ClassPoder = function(){
    	var _this = this;
    	this.C1 = arguments[0];
    	this.C2 = "";
    	this.C3 = "";
    	this.C4 = "No tienen";
    	this.C5 = this.C1 == "VIP" ? "Para ejercerlo ante autoridades administrativas judiciales, penales, fiscales o laborales."
    			: "Para ejercerlo ante autoridades administrativas judiciales, penales, fiscales o laborales. No delegable";
    	this.C6 = "No tienen";
    	this.C7 = "Excepto hacer cesi\u00F3n de bienes";
    	this.C8 = "";
    	this.C9 = "";
    
    }
    
    var ClassApoderado = function(){
    	var _this = this;
    	this.C1 = arguments[0];
    	this.C2 = arguments[1];
    	this.C3 = arguments[2];
    	this.C4 = arguments[3];
    	this.C5 = arguments[4];
    
    }
            
    var dlgPoderes_accept = function(){
    	Grid_apoderados = new Array();
    	$(".PGForm_Grid_apoderados").each(function(i,input){
			if($(input).val() == "Otro" ){
				if($(input).prop("checked")){
					Grid_apoderados.push(new ClassPoder("Otro"));
					$("#PGForm_Btn_poderAddCarta").show();
					return;
				}
			}
			else
				if($(input).prop("checked")){
					Grid_apoderados.push(new ClassPoder($(input).val()));
					$("#PGForm_Btn_poderAddCarta").hide();
				}
		});
    	$("#PGForm_Grid_poderSelecc").jGridContentMVC_UpDate(Grid_apoderados);
    	$("#PGForm_Grid_apoderados").jGridContentMVC_UpDate(Grid_apoderados);
    	dlgPoderes.dialog('close'); 
    }
    
    var initDlgApoderados = function(){
    	
    	dlgApoderados = $(dlgApoderados).dialog({
    		autoOpen:false
    		,height:600
    		,width:800
    		,modal:true
    		,buttons:{    			
    			Cancelar:function(){
    				
                	dlgApoderados.dialog('close'); 				
    			}
    			,"Guardar":dlgApoderados_save
    		}
    	});    	
    	
    	$("#PGForm_DlgApoderados_Grid_gapoder").jGridContentMVC({ gridId: "PGForm_DlgApoderados_Grid_gapoder",
            noDataMsg: 'no hay registros para mostrar',
            storage: [{ C1: 'Abraham Alberto L\u00F3pez Nava'},{ C1: 'Adolfo Carlos Castell'},{ C1: 'Adriana D\u00EDaz Galindo'},{ C1: 'Adriana Paulina L\u00F3pez Qui\u00F1ones'},{ C1: 'Alba Cecilia Ortega'}],
            rowClick: null,
            cellClick: function (iRow, iCell, bean, pty, gridId, td, event) {
                switch (pty) {
                    case 'add':
                        if(!$("#PGForm_DlgApoderados_Grid_gapoderSelecc").jGridContentMVC_Exists('C1' , bean.C1))
                        	$("#PGForm_DlgApoderados_Grid_gapoderSelecc").jGridContentMVC_Add(bean);
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
            columns: [  		       { HeadText: 'Poderes', 'width': '5%', id: 'C1', type: 'action', 'align': 'left'}                     
            ,{ HeadText: 'Poderes', 'width': '5%', id: 'add', type: 'action', 'align': 'left', cellValue: 'eval', eval: function (bean) {
               
               return '<div class="ui-state-default ui-corner-all" style="width:15px;"><span class="ui-icon ui-icon-circle-plus"/></div>';         
            }}
           ],
            tblCssClass: "",
            tblStyle: "width:100%",
            cellpadding: '0',
            cellspacing: '1',
            border: '0',
            afterDrawing: function () {
                
            }
        });
    	
    	
    	$("#PGForm_DlgApoderados_Grid_gapoderSelecc").jGridContentMVC({ gridId: "PGForm_DlgApoderados_Grid_gapoderSelecc",
            noDataMsg: 'no hay registros para mostrar',
            storage: [],
            rowClick: null,
            cellClick: function (iRow, iCell, bean, pty, gridId, td, event) {
                switch (pty) {
                    case 'del':                    		
                        	$("#PGForm_DlgApoderados_Grid_gapoderSelecc").jGridContentMVC_Delete(iRow);                                      

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
            columns: [  		       { HeadText: 'Poderes', 'width': '5%', id: 'C1', type: 'action', 'align': 'left'}                     
                                       ,{ HeadText: 'Poderes', 'width': '5%', id: 'del', type: 'action', 'align': 'left', cellValue: 'eval', eval: function (bean) {                                                                                    
                                          return '<div class="ui-state-default ui-corner-all" style="width:15px;"><span class="ui-icon ui-icon-circle-close"/></div>';
                                       }}
                                      ],
            tblCssClass: "",
            tblStyle: "width:100%",
            cellpadding: '0',
            cellspacing: '1',
            border: '0',
            afterDrawing: function () {
                
            }
        });
    	
    	
    	$("#PGForm_DlgApoderados_btn_addAllapoder").button( {
            icon: "ui-icon-arrowthickstop-1-s",
            showLabel: false
          } );
    	$("#PGForm_DlgApoderados_btn_delAllapoder").button( {
            icon: "ui-icon-arrowthickstop-1-n",
            showLabel: false
          } );
    	
    	var dpConfigFrom = WebKernel.getDefaultDatepicker(true);
    	var dpConfigTo = WebKernel.getDefaultDatepicker(true);
    	dpConfigFrom.change = function() {
    		var element = $(this).attr('toField');
    		var currentDate = $( this ).datepicker( "getDate" );
    		var op = $("#PGForm_DlgApoderados_cmb_vigencia").val();
    		
    		switch(op){
	    		case '1': 
	    			var value = $( "#PGForm_DlgApoderados_spi_vigencia" ).spinner( "value" );
	    			var date = WebKernel.addMonths(currentDate,value*12);  		    			
	    			$("#"+element).val(date);
	    			break;
	    		case '2': 
	    			var value = $( "#PGForm_DlgApoderados_spi_vigencia" ).spinner( "value" );
	    			var date = WebKernel.addMonths(currentDate,value);  	    			
	    			$("#"+element).val(date);
	    			break;
	    		default:
	    			$("#"+element).datepicker( "option", "minDate", dpConfigTo.getDate( this ) );
    		}
        	
        }
    	dpConfigTo.change = function() {
    		var element = $(this).attr('fromField');
    		
        	$("#"+element).datepicker( "option", "maxDate", dpConfigTo.getDate( this ) );
        }
    	
    	WebKernel.newWebForm("PGForm_DlgApoderados",{
    		 PGForm_DlgApoderados_calendar_vigenciaInicio:dpConfigFrom
    		,PGForm_DlgApoderados_calendar_vigenciaFin:dpConfigTo
    		,PGForm_DlgApoderados_cmb_vigencia:{
    		      change: function( event, data ) {    		          
    		            var op = data.item.value;   		            
    		            switch(op){
    		            case 'Rango':
    		            	$("#PGForm_DlgApoderados_spi_vigencia").spinner( "option", "disabled", true );
    		            	$("#PGForm_DlgApoderados_calendar_vigenciaFin").datepicker( "option", { disabled: false } );
    		            	$("#PGForm_DlgApoderados_calendar_vigenciaFin").val("");
    		            	break;
    		            default:
    		            	$("#PGForm_DlgApoderados_spi_vigencia").spinner( "option", "disabled", false );
    		            	$("#PGForm_DlgApoderados_calendar_vigenciaFin").datepicker( "option", { disabled: true } );
    		            	$("#PGForm_DlgApoderados_calendar_vigenciaFin").val("");
    		            	break;
    		            }
    		        }
    		       }});
    	
    	$("#PGForm_DlgApoderados_spi_vigencia").spinner( "option", "disabled", false );
    	$("#PGForm_DlgApoderados_calendar_vigenciaFin").datepicker( "option", { disabled: true } );
    	$("#PGForm_DlgApoderados_calendar_vigenciaFin").val("");
    	
    }
    
    var dlgApoderados_save = function(){
    	var list = $("#PGForm_DlgApoderados_Grid_gapoderSelecc").jGridContentMVC_GetList();
    	var selecc = new Array();
    	var v = $("#PGForm_DlgApoderados_cmb_vigencia").val();
    	var c = $("#PGForm_DlgApoderados_txt_caract").val();
    	var t = $("#PGForm_DlgApoderados_spi_vigencia").val();
    	var vi = $("#PGForm_DlgApoderados_calendar_vigenciaInicio").val();
    	var vf =$("#PGForm_DlgApoderados_calendar_vigenciaFin").val();
    	var names = "";
    	$(list).each(function(i,bean){
    		names += bean.C1 + "<br />";
    		selecc.push( new ClassApoderado(bean.C1,v,t,vi,vf,c));
		});
    	var WorkBean = $(dlgApoderados).data('WorkBean');
    	WorkBean.C3 = names;
    	WorkBean.C8 = c;
    	WorkBean.C9 = t+" "+v;
    	WorkBean.Apoderados = list;
    	
    	$("#PGForm_Grid_apoderados").jGridContentMVC_UpDate();
    	dlgApoderados.dialog('close'); 
    }
    
	var initDlgApoderadosOtros = function(){
	    	
		dlgApoderadosOtros = $(dlgApoderadosOtros).dialog({
	    		autoOpen:false
	    		,height:600
	    		,width:800
	    		,modal:true
	    		,buttons:{    			
	    			Cancelar:function(){
	    				
	    				dlgApoderadosOtros.dialog('close'); 				
	    			}
	    			,"Guardar":dlgApoderadosOtros_save
	    		}
	    	});  
		
		$("#PGForm_DlgApoderadosOtros_tabs").tabs();
		
		WebKernel.newWebForm("PGForm_DlgApoderadosOtros");
		
		$(".PGForm_DlgApoderadosOtros_chk").change(function(){
			var check = $(this).is(":checked");
    		if(check)
    			$("#"+this.id+"_Table").show();
    		else
    			$("#"+this.id+"_Table").hide()
		
		});
		
		$(".PGForm_DlgApoderadosOtros_chk_Table").hide();
	}
    
	var dlgApoderadosOtros_save = function(){	
		
		var WorkBean = $(dlgApoderadosOtros).data('WorkBean');
    	    	
    	
		if($("#PGForm_DlgApoderadosOtros_chk_pC").is(":checked")){
			var pC = new Object();
			pC.delegable = $("input[name='PGForm_DlgApoderadosOtros_Deleg1D']:checked").val();
			pC.individual = $("input[name='PGForm_DlgApoderadosOtros_Deleg2D']:checked").val();
			pC.conjunto = $("#PGForm_DlgApoderadosOtros_cmb_conjD").val();
			pC.otro = $("#PGForm_DlgApoderadosOtros_txt_otroD").val();
			pC.caract = $("#PGForm_DlgApoderadosOtros_txt_caractD").val();
			WorkBean.C7 = "*" + pC.delegable + "<br />" + "*" + pC.individual + "<br />"+ ( pC.conjunto ==""? pC.conjunto : ">>" + pC.conjunto + "<br />");
		}
		if($("#PGForm_DlgApoderadosOtros_chk_aA").is(":checked")){
			var aA = new Object();
			aA.delegable = $("input[name='PGForm_DlgApoderadosOtros_Deleg1']:checked").val();
			aA.individual = $("input[name='PGForm_DlgApoderadosOtros_Deleg2']:checked").val();
			aA.conjunto = $("#PGForm_DlgApoderadosOtros_cmb_conj").val();
			aA.otro = $("#PGForm_DlgApoderadosOtros_txt_otro").val();
			aA.caract = $("#PGForm_DlgApoderadosOtros_txt_caract").val();
			WorkBean.C5 = "*" + aA.delegable + "<br />" + "*" + aA.individual + "<br />"+ (aA.conjunto == "" ? "" : ">>" + aA.conjunto + "<br />");
				}
		if($("#PGForm_DlgApoderadosOtros_chk_aD").is(":checked")){
			var aD = new Object();
			aD.delegable = $("input[name='PGForm_DlgApoderadosOtros_Deleg1B']:checked").val();
			aD.individual = $("input[name='PGForm_DlgApoderadosOtros_Deleg2B']:checked").val();
			aD.conjunto = $("#PGForm_DlgApoderadosOtros_cmb_conjB").val();
			aD.otro = $("#PGForm_DlgApoderadosOtros_txt_otroB").val();
			aD.caract = $("#PGForm_DlgApoderadosOtros_txt_caractB").val();
			WorkBean.C4 = "*" + aD.delegable + "<br />" + "*" + aD.individual + "<br />"+ (aD.conjunto==""? "" : ">>" + aD.conjunto + "<br />") + aD.otro;
		}
		if($("#PGForm_DlgApoderadosOtros_chk_tC").is(":checked")){
			var tC = new Object();
			tC.delegable = $("input[name='PGForm_DlgApoderadosOtros_Deleg1C']:checked").val();
			tC.individual = $("input[name='PGForm_DlgApoderadosOtros_Deleg2C']:checked").val();
			tC.conjunto = $("#PGForm_DlgApoderadosOtros_cmb_conjC").val();
			tC.otro = $("#PGForm_DlgApoderadosOtros_txt_otroC").val();
			tC.caract = $("#PGForm_DlgApoderadosOtros_txt_caractC").val();
			WorkBean.C6  = "*" + tC.delegable + "<br />" + "*" + tC.individual + "<br />"+ (tC.conjunto == "" ? "" : ">>" + tC.conjunto + "<br />");
		}
		
		$("#PGForm_Grid_apoderados").jGridContentMVC_UpDate();
		dlgApoderadosOtros.dialog('close'); 
	}
	
	var ClassRevocar = function(){
		this.Escritura=arguments[0];
		this.Concepto=arguments[1];
		this.Fecha=arguments[2];
		this.NoDoc=arguments[3];
	}
	
	var initDlgRevocacion = function(){		 
		 dlgRevocacion = $(dlgRevocacion).dialog({
    		autoOpen:false
    		,height:600
    		,width:800
    		,modal:true
    		,buttons:{    			
    			Cancelar:function(){    				
    				dlgRevocacion.dialog('close'); 				
    			}
    			,"Aplicar Revocaciones":dlgRevocacion_save
    		}
    	});  
		 
		 
		 WebKernel.newWebForm("PGForm_DlgRevocacion");
		 
		 $("#PGForm_DlgRevocacion_grid_Revocados").jGridContentMVC({ gridId: "PGForm_DlgRevocacion_grid_Revocados",
	            noDataMsg: 'no hay registros para mostrar',
	            storage: [],
	            rowClick: null,
	            cellClick: function (iRow, iCell, bean, pty, gridId, td, event) {
	                switch (pty) {
	                    case 'Revocar':                    		
	                    case '_Selecc':
	                    	
	                    	
	                        $("#PGForm_DlgRevocacion_divRevocar").data('Bean',bean);
	                        
	                        dlgRevocacion.buttonsMain = dlgRevocacion.dialog( "option", "buttons" );
	                        
	                        // Setter
	                        dlgRevocacion.dialog( "option", "buttons",[{
	                            text: "cancelar",
	                            icons: {
	                              primary: "ui-icon-circle-arrow-w"
	                            },
	                            click: function() {
	                            	$("#PGForm_DlgRevocacion_divRevocar").hide();	                                      
	    	                        $("#PGForm_DlgRevocacion_divGrid").show();
	    	                        dlgRevocacion.dialog( "option", "buttons",dlgRevocacion.buttonsMain);
	                            }	                            
	                          },{
		                            text: "Revocar",
		                            icons: {
		                              primary: "ui-icon-circle-check"
		                            },
		                            click: function() {
		                            	//[iRow, iCell, bean, pty, gridId]
		                            	var bean = $("#PGForm_DlgRevocacion_divRevocar").data('Bean');
		                            	bean._Selecc = true;
		                            	bean.Revocar = new ClassRevocar(
		                            			$("#PGForm_DlgRevocacion_cmb_Escritura").val()
		                            			,$("#PGForm_DlgRevocacion_cmb_Escritura_cmb_razon").val()
		                            			,$("#PGForm_DlgRevocacion_cmb_Escritura_date_fecha").val()
		                            			,$("#PGForm_DlgRevocacion_cmb_Escritura_txt_doc").val());
		                            	
		                            	
		                            	$("#PGForm_DlgRevocacion_divRevocar").hide();	                                      
		    	                        $("#PGForm_DlgRevocacion_divGrid").show();
		    	                        dlgRevocacion.dialog( "option", "buttons",dlgRevocacion.buttonsMain);
		    	                        $("#PGForm_DlgRevocacion_grid_Revocados").jGridContentMVC_UpDate();
		                            }	                            
		                          }]);
	                        
	                        if(bean._Selecc){	                        	
	                        	$("#PGForm_DlgRevocacion_cmb_Escritura").val(bean.Revocar.Escritura)
	                        	$("#PGForm_DlgRevocacion_cmb_Escritura_cmb_razon").val(bean.Revocar.Concepto)
	                        	$("#PGForm_DlgRevocacion_cmb_Escritura_date_fecha").val(bean.Revocar.Fecha)
	                        	$("#PGForm_DlgRevocacion_cmb_Escritura_txt_doc").val(bean.Revocar.NoDoc);
	                        }
	                        
	                        $("#GForm_DlgRevocacion_LblName").html(bean.C1);
	                        
	                        $("#PGForm_DlgRevocacion_divGrid").hide();	                                      
	                        $("#PGForm_DlgRevocacion_divRevocar").show();
	                        
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
	            columns: [{ HeadText: 'Apoderados', 'width': '70%', id: 'C1', type: 'action', 'align': 'left'}                     
	            		,{ HeadText: '', 'width': '20%', id: '_Selecc', type: 'action', 'align': 'left', cellValue: 'eval', eval: function (bean) {                                                                                    
	            			return bean._Selecc == true ? 'revocado' : "";
	            		}}                           
	            		,{ HeadText: 'Revocar', 'width': '10%', id: 'Revocar', type: 'action', 'align': 'left', cellValue: 'eval', eval: function (bean) {                                                                                    
	            			return '<div class="ui-state-default ui-corner-all" style="width:15px;"><span class="ui-icon ui-icon-circle-close"/></div>';
	            		}}
	            		],
	            tblCssClass: "tablesorter",
	            tblStyle: "width:100%",
	            cellpadding: '0',
	            cellspacing: '1',
	            border: '0',
	            afterDrawing: function () {
	                
	            }
	        });
		 
		 dlgRevocacion.open = function(workBean){
			 $("#PGForm_DlgRevocacion_grid_Revocados").jGridContentMVC_UpDate( workBean.Apoderados);
			$(dlgRevocacion).data('WorkBean',workBean);
         	dlgRevocacion.dialog('open'); 	
		 }
	}
	
	var dlgRevocacion_save = function(){
		$("#PGForm_Grid_apoderados").jGridContentMVC_UpDate();
		dlgRevocacion.dialog('close');
	}
	
    initComponents();
}
