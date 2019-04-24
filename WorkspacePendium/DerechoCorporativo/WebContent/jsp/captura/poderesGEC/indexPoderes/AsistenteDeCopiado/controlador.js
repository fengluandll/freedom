var CopyMng = null;

$(document).ready(function(){
	CopyMng = new ClassCopyManager();
});

var ClassCopyManager = function(){
	var _this = this;
	var dlgStep1 = null;
	var dlgStep2 = null;
	var dlgStep3 = null;
	var dlgStep4 = null;
	var dlgStep5 = null;
	
	var Type = 0;
	var CopyType = 0;
	var Head = false;
	var Status = false;
	var Docs = false;
	var Poderes = false;
	var CnyList = new Array();
	
	var clean = function(){
		Type = 0;
		CopyType = 0;
		Head = false;
		Status = false;
		Docs = false;
		Poderes = false;
		CnyList = new Array();
		
		$("[name=CpyMng_Paso1_radio-1]:checked").prop('checked',false);
		$("[name=CpyMng_Paso2_radio-1]:checked").prop('checked',false);
		$("[name=CpyMng_Paso3_radio-1]:checked").prop('checked',false);
		
		$("#CpyMng_Paso4_gridSel").jGridContentMVC_UpDate([]);
		
		$("#CpyMng_Paso5_divListCIA").jGridContentMVC_UpDate([]);
		$("#CpyMng_Paso5_divSummary").html('');
	}
	
	var BackStep = 0;
	
	var dlgBackStep = function(current){
		switch(current){
			case 2: dlgStep2.dialog('close'); break;
			case 3: dlgStep3.dialog('close'); break;
			case 4: dlgStep4.dialog('close'); break;
			case 5: dlgStep5.dialog('close'); break;			
		}
		switch(BackStep){
			case 1: 
				dlgStep1_open(); 
				break;
			case 2: 
				dlgStep2_open(); 
				BackStep = 1;
				break;
			case 3: 
				dlgStep3_open();
				BackStep = 2;
				break;
			case 4: 
				dlgStep4_open(); 
				if(CopyType != _this.WorkEsc.ind_tipo_escritura )
					BackStep = 2;
				else
					BackStep = 3;
				
				break;					
		}
	}
	
	var stepAction = function(){
		Type = $("[name=CpyMng_Paso1_radio-1]:checked").val();
		if(Type == '' || Type == null){
			swal({ title: "Aviso",   
			      text: "Debe seleccionar la operaci\u00F3n a realizar para poder continuar.",   
			      type: "warning",  
			      confirmButtonText: "Ok" });
			return;
		}
		dlgStep1.dialog('close');
		BackStep = 1;
		switch(Type){
		case '1':
			CopyType = _this.WorkEsc.ind_tipo_escritura == 'PG' ? 'PE' : 'PG';
			dlgStep5_open();
			break;
		case '2': 
		case '3': 
			dlgStep2_open();
			break;
		}
	}
	
	var dlgStep1_open = function(){
		dlgStep1.dialog('open');
	}
	
	var dlgStep2_open = function(){
		dlgStep2.dialog('open');
	}
	
	var dlgStep3_open = function(){
		dlgStep3.dialog('open');
	}
	
	var dlgStep4_open = function(){
		dlgStep4.dialog('open');
	}
	var dlgStep5_open = function(){
		switch(Type){
			case '1': 
				$("#CpyMng_Paso5_divListCIA").jGridContentMVC_UpDate([]);
				var TipoDesc = _this.WorkEsc.ind_tipo_escritura == 'PG' ? 'General' : 'Especial';
				var TipoDesc2 = _this.WorkEsc.ind_tipo_escritura == 'PG' ? 'Especial' : 'General';
				var noEsc = _this.WorkEsc.des_escritura == null ? '' : _this.WorkEsc.des_escritura;
				$("#CpyMng_Paso5_divSummary").html('Integrar la Escritura '+ noEsc + ' de tipo '+TipoDesc+' a '+TipoDesc2 );
				break;
			case '2': 
				$("#CpyMng_Paso5_divListCIA").jGridContentMVC_UpDate([]);
				var TipoDesc = _this.WorkEsc.ind_tipo_escritura == 'PG' ? 'General' : 'Especial';
				var TipoDesc2 = CopyType == 'PG' ? 'General' :CopyType == 'PE' ? 'Especial': CopyType == 'CP' ? 'Carta Poder' : 'Revocar' ;
				var noEsc = _this.WorkEsc.des_escritura == null ? '' : _this.WorkEsc.des_escritura;
				var str = 'Realizar una copia de la Escritura '+ noEsc + ' de tipo '+TipoDesc+' a '+TipoDesc2;
				if(_this.WorkEsc.ind_tipo_escritura == CopyType){
					str += '<br/>con el siguiente detalle:'
					if(Head)
						str+='<br/>* Cabecera';
					if(Status)
						str+='<br/>* Status';
					if(Docs)
						str+='<br/>* Documentos'; 
					if(Poderes)
						str+='<br/>* Poderes';					
				}
					
				$("#CpyMng_Paso5_divSummary").html(str);
				break;			
			case '3': 
				var TipoDesc = _this.WorkEsc.ind_tipo_escritura == 'PG' ? 'General' : 'Especial';
				var TipoDesc2 = CopyType == 'PG' ? 'General' :CopyType == 'PE' ? 'Especial': CopyType == 'CP' ? 'Carta Poder' : 'Revocar' ;
				var noEsc = _this.WorkEsc.des_escritura == null ? '' : _this.WorkEsc.des_escritura;
				var str = 'Exportar la Escritura '+ noEsc + ' de tipo '+TipoDesc+' a '+TipoDesc2;
				if(_this.WorkEsc.ind_tipo_escritura == CopyType){
					str += '<br/>con el siguiente detalle:'
					if(Head)
						str+='<br/>* Cabecera';
					if(Status)
						str+='<br/>* Status';
					if(Docs)
						str+='<br/>* Documentos'; 
					if(Poderes)
						str+='<br/>* Poderes';					
				}
				str+='<br/><br/> a las siguientes empresas:';
				$("#CpyMng_Paso5_divSummary").html(str);
				$("#CpyMng_Paso5_divListCIA").jGridContentMVC_UpDate( $("#CpyMng_Paso4_gridSel").jGridContentMVC_GetList() );
				break;
		}
		
		dlgStep5.dialog('open');
	}
	
	var stepCopyType = function(){
		CopyType = $("[name=CpyMng_Paso2_radio-1]:checked").val();
		if(CopyType == '' || CopyType == null){
			swal({ title: "Aviso",   
			      text: "Debe seleccionar un tipo de copiado para poder continuar.",   
			      type: "warning",  
			      confirmButtonText: "Ok" });
			return;
		}
		
		dlgStep2.dialog('close');
		BackStep = 2;
		if(CopyType == _this.WorkEsc.ind_tipo_escritura && CopyType == 'ER')
			$("#CpyMng_Paso3_radio-4").attr('disabled',true);
		else
			$("#CpyMng_Paso3_radio-4").attr('disabled',false);
		
		if(CopyType == _this.WorkEsc.ind_tipo_escritura)
			dlgStep3_open();
		else if(Type == '3')
			dlgStep4_open();
		else
			dlgStep5_open();
		
	}
	
	var stepComponentsCopy = function(){
		Head = $("#CpyMng_Paso3_radio-1").prop('checked');
		Status  = $("#CpyMng_Paso3_radio-2").prop('checked');
		Docs = $("#CpyMng_Paso3_radio-3").prop('checked');
		Poderes = $("#CpyMng_Paso3_radio-4").prop('checked');
		if(!Head && !Status && !Docs && !Poderes){
			swal({ title: "Aviso",   
			      text: "Debe seleccionar al menos una secci\u00F3n a copiar para poder continuar.",   
			      type: "warning",  
			      confirmButtonText: "Ok" });
			return;
		}
		
		dlgStep3.dialog('close');
		BackStep = 3;
		if(Type == '3')
			dlgStep4_open();
		else
			dlgStep5_open();
	}
	
	var stepCompanysList = function(){
		CnyList = new Array();
		var list = $("#CpyMng_Paso4_gridSel").jGridContentMVC_GetList();
		if(list.length <= 0){
			swal({ title: "Aviso",   
			      text: "Debe seleccionar al menos una empresa a copiar para poder continuar.",   
			      type: "warning",  
			      confirmButtonText: "Ok" });
			return;
		}
		
		for(var i in list)
			CnyList.push(list[i].id);
		dlgStep4.dialog('close');
		BackStep = 4;
		dlgStep5_open();
	}
	
	var stepFinish = function(){
		var type = Type == '1' ? 'Import' : Type == '2' ? 'Import' : 'Export';
		var mode = Type == '1' ? 'Convert' : 'Copy';	
		
		dlgStep5.dialog('close');		
		Request.EscrituraPoderes.cpyEscritura(_this.WorkEsc,CnyList,type,mode,CopyType
				,MetaSession.SessionBean.idUser
				,Head
				,Status
				,Docs
				,Poderes
				,{
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
	}
	
	var dlgStep1_open = function(bean){
		dlgStep1.dialog('open');
	}
	
	var initComponents = function(){
		
		
		
		dlgStep1 = $( "#CpyMng_Paso1" ).dialog({
		      autoOpen: false,
		      height: 400,
		      width: 450,
		      modal: true,
		      buttons: {
		        
		        Cancel: function() {
		        	dlgStep1.dialog( "close" );
		        },
		        "Siguiente": stepAction
		      },
		      close: function() {
		        
		      }
		    });
		
		dlgStep1.open =  dlgStep1_open;
				
		
		
		dlgStep2 = $( "#CpyMng_Paso2" ).dialog({
		      autoOpen: false,
		      height: 400,
		      width: 450,
		      modal: true,
		      buttons: {
		    	 Cancel: function() {
		    		 dlgStep2.dialog( "close" );
			     },
		        "Atras": function(){
		        	dlgBackStep(2);
		        },
		        "Siguiente": stepCopyType
		        
		      },
		      close: function() {
		        
		      }
		    });
		
		dlgStep2.open = dlgStep2_open;
		
		dlgStep3 = $( "#CpyMng_Paso3" ).dialog({
		      autoOpen: false,
		      height: 400,
		      width: 450,
		      modal: true,
		      buttons: {
		    	  Cancel: function() {
		    		  dlgStep3.dialog( "close" );
			     },
		        "Atras": function(){
		        	dlgBackStep(3);
		        	},
		    	  "Siguiente": stepComponentsCopy
		        
		      },
		      close: function() {
		        
		      }
		    });
		
		dlgStep3.open = dlgStep3_open;
		
		dlgStep4 = $( "#CpyMng_Paso4" ).dialog({
		      autoOpen: false,
		      height: 400,
		      width: 450,
		      modal: true,
		      buttons: {
		    	  Cancel: function() {
		    		  dlgStep4.dialog( "close" );
			     },
		        "Atras": function(){
		        	dlgBackStep(4);
		        	},
		        "Siguiente": stepCompanysList
		      },
		      close: function() {
		        
		      }
		    });
		
		$("#CpyMng_Paso4_btnSel").button({
			icon : "ui-icon-zoomin",
			showLabel : true
		}).click(
				function() {
					WebKernel.multiselectDialog('Seleccione las Empresas', 'EMPRESAS',
							'', {
								publish : function(data) {
									$.each(data, function(i, bean) {
										if (!$("#CpyMng_Paso4_gridSel").jGridContentMVC_Exists('id',bean.id))
											$("#CpyMng_Paso4_gridSel").jGridContentMVC_Add(bean);
									});
								},
								metadata : null
							});
				});

		$("#CpyMng_Paso4_gridSel").jGridContentMVC({
			gridId : "CpyMng_Paso4_gridSel",
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
		
		$("#CpyMng_Paso5_divListCIA").jGridContentMVC({
			gridId : "CpyMng_Paso5_divListCIA",
			noDataMsg : 'Copiar en la misma empresa',
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
			columns : [ 
{
				HeadText : 'Empresa',
				'width' : '98%',
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
		
		dlgStep5 = $( "#CpyMng_Paso5" ).dialog({
		      autoOpen: false,
		      height: 400,
		      width: 450,
		      modal: true,
		      buttons: {
		    	  Cancel: function() {
		    		  dlgStep5.dialog( "close" );
			     },
		        "Atras": function(){
		        	dlgBackStep(5);
		        	},
		        "Finalizar": stepFinish
		      },
		      close: function() {
		        
		      }
		    });
		
		dlgStep5.open = dlgStep5_open;
		
	}		
	
	this.open = function(bean,handler){
		_this.WorkEsc = bean;
		_this.Handler = handler;
		clean();
		dlgStep1.open(bean);
	}
	
	initComponents();
}

