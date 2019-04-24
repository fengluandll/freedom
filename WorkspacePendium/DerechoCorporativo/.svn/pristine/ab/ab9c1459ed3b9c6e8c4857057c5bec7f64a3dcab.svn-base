/*!
* Title:  jGridContentMVC 1.0
* Dependencies:  jQuery 1.0 +
* Author:  Tomás Bojórquez Alvarado
*/

(function($){  
  $.fn.jGridContentMVC = function( config ) {
	  	$.newHTML = function(type,config){
            var configAux = {attr:[],css:[],cssClass:[],listeners:[],data:[]};
            
            if( config != null ){
                $.each(configAux,function(pty,value){
                    if(config[pty]==null)
                        config[pty]=configAux[pty];
                });
            }else
                config = configAux;
            
            var html = document.createElement(type);
                html = $(html).attr(config.attr).css(config.css).addClass(config.cssClass).bind(config.listeners);
                    $.each(config.data,function(pty,value){
                        $(html).data(pty,value);
                    });
            return html;
        };
            	  	
	  	var jGridContentMVC_updatePagination = function(config){
	  	    var page = config.paginationSettings.pageTotal==0 ? 0 : config.paginationSettings.page == null ? 1 : config.paginationSettings.page;
            $( '#'+config.gridId+'_pageCounter' ).html( page + ' / ' + config.paginationSettings.pageTotal );
	  	};
	  	
	  	var jGridContentMVC_paginationSettings = function(config){
            settings = config.paginationSettings;
            settings.page = settings.pageInit;
            settings.gridId = config.gridId;
            
            var totalReg = config.storage.length;
            
            var headWidth = settings.headWidth != null ? settings.headWidth : '100%';
                                            
            var tbl = $.newHTML('table',{attr:{'width':headWidth,'id':settings.gridId+'_paginationSettings'}});
            
            $(tbl).data('settings',settings).data('engaged',false);
            
            var tr = $.newHTML('tr');
                            
            var tdback = $.newHTML('td',{attr:{'align':'right'},css:{'width':'40%'}});
                //$( tdback ).data('type','back').data('parent',config.gridId).html( '<div class="ui-icon ui-icon-triangle-1-w"></div>' ).click( jGridContentMVC_paginationSettings_btnBackNext_click );
                $( tdback ).data('type','back').data('parent',config.gridId).html( '<button class="ui-state-default ui-corner-all" title="back" data-icon="arrow-l" style="cursor:pointer"><span class="ui-icon ui-icon-triangle-1-w">&#60;</span></button>' ).click( jGridContentMVC_paginationSettings_btnBackNext_click );
            
            var tdcenter = $.newHTML('td',{attr:{'align':'center'},css:{'width':'20%'}});
                $( tdcenter ).attr('id',config.gridId+'_pageCounter').html( (settings.pageTotal == 0 ? '0' : settings.pageInit) + ' / ' + settings.pageTotal );
            
            var tdnext = $.newHTML('td',{attr:{'align':'left'},css:{'width':'40%'}});
                //$( tdnext ).data('type','next').data('parent',config.gridId).html( '<div class="ui-icon ui-icon-triangle-1-e"></div>' ).click(jGridContentMVC_paginationSettings_btnBackNext_click);
                $( tdnext ).data('type','next').data('parent',config.gridId).html( '<button class="ui-state-default ui-corner-all" title="next" data-icon="arrow-r" style="cursor:pointer"><span class="ui-icon ui-icon-triangle-1-e">&#62;</span></button>' ).click(jGridContentMVC_paginationSettings_btnBackNext_click);
                
            $(tr).append( tdback );
            $(tr).append( tdcenter );
            $(tr).append( tdnext );
            
            $(tbl).append( tr );
            return tbl;
        };
	  	
	  	var jGridContentMVC_paginationSettings_btnBackNext_click = function(){        
            var parent = "#"+$(this).data('parent');
            var config = $(parent).jGridContentMVC_GetConfig();
            var settings = config.paginationSettings;
                
            if(settings.pageTotal == 0)
                return;
                
            var type = $(this).data('type');
            var page = settings.page;
            var pageTotal = settings.pageTotal;                            
            
            if( settings.loadingMsg )
                $( parent+'_divTable' ).html( 'loading..' );
            
            try{
                switch(type){
                    case 'next':
                        page = page < pageTotal ? page+1:1;
                        if( settings.next != null )
                            settings.next( page,pageTotal,config,parent,parent+'_divTable' );
                        break;
                    case 'back':
                        page = page == 1 ? pageTotal:page-1;                
                        if( settings.next != null )
                            settings.back( page,pageTotal,config,parent,parent+'_divTable' );
                        break;
                }
            }catch(e){
                page = settings.page;
            }
            
            $( config.gridId+'_pageCounter' ).html( page + ' / ' + pageTotal );
            settings.page = page;
            config.paginationSettings = settings;
            $(parent).jGridContentMVC_SetConfig(config);
            
            if( config.paginationSettings.raiseevent != null )
                config.paginationSettings.raiseevent( type )
        };	  	
	  	
	  	var afterDOMRegister = function( config ){
	  	    
	  	    if( config.grid_change!=null )
                config.grid_change(config);
            
            if( config.editableHeaders ){
                var css= '.jGridContentMVC_'+config.gridId+'_editableHeaders'; 
                $( css ).click( editableHeaders_click );
                $( css+'_text' ).focusout( editableHeaders_focusout );
                $( css+'_text' ).keypress( editableHeaders_keypress );                
            }
	  	};
	  		  	
	  	$.fn.jGridContentMVC_GridStorage = $.fn.jGridContentMVC_GridStorage == null ? new Array() : $.fn.jGridContentMVC_GridStorage;	  	
		
		$.fn.jGridContentMVC_GridDraw = $.fn.jGridContentMVC_drawGrid != null ? $.fn.jGridContentMVC_drawGrid : function(config){
			var jGrid_MaxLength = config.storage.length == 0 ? 0 : config.storage.length-1;
			var column_edit = false;                
			var tbl = '<table';
			tbl += ' id="Tbl'+config.gridId+'"';
			tbl += config.cellpadding == null ? '' : ' cellpadding="'+config.cellpadding+'"';
			tbl += config.cellspacing == null ? '' : ' cellspacing="'+config.cellspacing+'"';
			tbl += config.border == null ? '' : ' border="'+config.border+'"';
			
			tbl += config.tblCssClass != null ? ' class="'+config.tblCssClass+'"' : '';
			tbl += config.tblStyle != null ? ' style="'+config.tblStyle+'"' : '';
			tbl += ' id="GridTbl'+config.gridId+'"';
			tbl += '>';			
			
			if( config.drawHeads != null ){
			    tbl += '<thead><tr';
			    tbl += config.HeadCss == null ? '' : ' class="'+config.HeadCss+'"';
			    tbl += '>';
			    var drhead = '';
			    $.each(config.drawHeads,function(dih,dhead){
			        drhead += '<td align="center" colspan="'+dhead.colspan+'">'+dhead.HeadText+'</td>';
			    });
			    tbl += drhead + '</tr></thead>';
			}
			
			if(config.Heads){		
                tbl += '<thead>';
                if(config.hasOwnProperty('HeadTitle')){
                    tbl += '<tr>';
                    for (var itemObj in config.HeadTitle){                    
                        tbl += '<td';
                        for(var attr in config.HeadTitle[itemObj]){
                            if( attr != 'Title')
                                tbl += ' ' + attr + '="' + config.HeadTitle[itemObj][attr] + '"';
                        }
                        if(config.HeadTitle[itemObj].hasOwnProperty('Title'))
                            tbl += '>' + config.HeadTitle[itemObj].Title+'</td>';
                        else
                            tbl += '></td>';
                    }
                    tbl += '</tr>';                    
                }
			    tbl += '<tr';
			    tbl += config.HeadCss == null ? '' : ' class="'+config.HeadCss+'"';
			    tbl += config.HeadAlign == null ? ' align="center"' : ' align="'+config.HeadAlign+'"';
			    tbl += '>';
			    for(var c = 0; c < config.columns.length; c++){
			        var column = config.columns[c];
			        var td = '<th';
					    td += column.width == null ? '':' style="width:'+ column.width +'"' ;
					    td += column.cssClass == null ? '':' class="'+column.cssClass+'"';
					    td += column.cssClassH == null ? '':' class="'+column.cssClassH+'"';
					    td += column.HeadTitle == null ? '' : ' title="'+ column.HeadTitle +'" ';
					    
					    if( column.HeadClick != null ){
					        var _param = column.HeadClick.Value;
                            td += ' onClick="$(\'#'+config.gridId+'\').jGridContentMVC_GridHeadClick( \''+config.gridId+'\',\''+_param+'\' )" '
					    }
					    
					    
					    
					    td += '>';
					    td += column.HeadText;
					    td += '</th>';
					    tbl += td;
			    }
			    tbl += '</tr>';
			    
			    tbl +='</thead><tbody>';
			    }

			var flagTRInit = false;
			var flagTRFinal = false;
			var flagAgency = true;
			
			var indexRegisterEnd = config.storage.length;
			var indexRegisterIni = 0;
			
			if( config.paginationSettings != null ){	
			    config.paginationSettings.page = config.paginationSettings.page == null ? 1 : config.paginationSettings.page;			    
			    var page = config.paginationSettings.page;
			    var regPage = config.paginationSettings.pageRegisters;
			    
			    if(config.storage.length > 0){
			        var tp=parseInt(config.storage.length/regPage);
			        var mod=config.storage.length%regPage;
			    }
			    
			    config.paginationSettings.pageTotal = config.storage.length == 0 ? 0 : config.storage.length <= regPage ? 1 :
                    parseInt( config.storage.length / regPage )  + ( config.storage.length % regPage > 0 ? 1 : 0 ) ;
                    
			    var totalPage = config.paginationSettings.pageTotal;
			    
			    indexRegisterIni = ( (page-1) * regPage );
			    indexRegisterEnd = page == totalPage ? config.storage.length :
			        ( indexRegisterIni + regPage );
                indexRegisterEnd = config.storage.length == 0 ? 0 : config.storage.length < regPage ? config.storage.length : indexRegisterEnd;
			};	
						
//			for(var i = 0; i < config.storage.length; i++){
//				var bean = config.storage[i];				
//				var tr = '<tr';				
//				tr += config.rowAlign == null ? '' : ' align="'+config.rowAlign+'"';
//				tr += config.rowClick == null ? '' : ' onClick="$(\'#'+config.gridId+'\').jGridContentMVC_GridRowClick( \''+config.gridId+'\',\''+i+'\' )" style="cursor:pointer"';
//				if(config.rowFinal == null)
//				    tr += config.rowCssA  == null ? '' : 'class="'+ (( (i+1) % 2)==0 ? config.rowCssA : config.rowCssB ) + '"';
//				else{
//				    if(i == (config.storage.length-1))
//				        tr += config.rowCssA  == null ? 'class="'+config.rowFinal+'"' : 'class="'+ (( (i+1) % 2)==0 ? config.rowCssA : config.rowCssB ) + ' '+config.rowFinal+'"';
//				    else
//				        tr += config.rowCssA  == null ? '' : 'class="'+ (( (i+1) % 2)==0 ? config.rowCssA : config.rowCssB ) + '"';				    
//				    }
//				tr += '>';
			for(var i = indexRegisterIni; i < indexRegisterEnd; i++){
				var bean = config.storage[i];				

				flagTRInit = i == 0;	
				flagTRFinal = i == (config.storage.length-1);

				var tr = '<tr';				

			    tr += config.rowClick == null || (flagTRFinal && config.rowFinal != null) || (flagTRInit && config.rowInitNonClick != null) ? '' : ' onClick="$(\'#'+config.gridId+'\').jGridContentMVC_GridRowClick( \''+config.gridId+'\',\''+i+'\' )"';

				var inRowDetail = false;
				if(config.RowDetail != null){//(config {..., RowDetail:{condition:[{value:'X',idPty:'',idGrid=''},...]
				    for( icond in config.RowDetail.condition ){
				        var condition = config.RowDetail.condition[icond];
				        if( bean[condition.idPty]!=condition.value ){					            
				            var idRowDetail=config.gridId+'RowDetail'+ i;
				            var Namegrid = condition.idGrid + i;
//				            var eventView = ' onclick="$(\'#'+config.gridId+'\').jGridContentMVC_GridRowDblClickRowDetail( \''+config.gridId+'\',\''+idRowDetail+'\',\''+ condition.IdClass + '\',\'' + bean[condition.idPty] + '\',\'' + Namegrid + '\',\''+ config.columns[0].id + '\' )"';
//				            tr += eventView;
				            inRowDetail = true;
				            break;
				        }condition.idGrid
				    }
				}
				if(config.RowID != null){//(config {..., RowDetail:{condition:[{value:'X',idPty:'',idGrid=''},...]
				    for( icond in config.RowID.condition ){
				        var condition = config.RowID.condition[icond];
				        if( bean[condition.idPty]==condition.value ){					            
				            var idRowDetail=config.gridId+'RowDetail'+ i;
				            tr += ' id="' + idRowDetail + '" ';
				            tr += 'style=""';
				            break;
				        }
				    }
				}
			    tr += config.rowClick != null || inRowDetail ? ' style="cursor:pointer"' : '' ;
				tr += config.rowAlign == null ? '' : ' align="'+config.rowAlign+'"';
				



				if(config.rowFinal == null){
				    tr += config.rowCssA  == null ? '' : ' class="'+ (( (i+1) % 2)==0 ? config.rowCssA : config.rowCssB ) + '"';
				}else{
				    if(i == (config.storage.length-1)){
				        tr += config.rowCssA  == null ? ' class="'+config.rowFinal+'"' : 'class="'+ (( (i+1) % 2)==0 ? config.rowCssA : config.rowCssB ) + ' '+config.rowFinal+'"';
				        flagTRFinal = true;
				    }else
				        tr += config.rowCssA  == null ? '' : ' class="'+ (( (i+1) % 2)==0 ? config.rowCssA : config.rowCssB ) + '"';				    
				    }
				tr += '>';
				
				/*============*/
				
								
				for(var c = 0; c < config.columns.length; c++){
				    var column = config.columns[c];	
				    var _styleTD = '';
				    
				    				
					var td = '<td ';	/*'<td style="height:100%"';*/
					/*td += column.width == null ? '':' style="width:'+ column.width +'"' ;	*/
					_styleTD += column.width == null ? '':';width:'+ column.width +'' ;	
								
					td += column.align == null ? '':' align="'+column.align+'"';
					
					var column_cssClass = column.cssClass;
					if( column.edit ){
                            column_cssClass = (column_cssClass != null ? column.cssClass+' ' : '') + config.gridId+'_edit';
                            td += ' irow="'+i+'" icell="'+c+'" ptyid="'+column.id+'" gridid="'+config.gridId+'"';
                            column_edit = true;
                        }
					td += column_cssClass == null ? '':' class="'+column_cssClass+'"';
					/*td += column.type == null ? '' : column.type == 'action' ? ' onClick="$(\'#'+config.gridId+'\').jGridContentMVC_GridCellClick( \''+config.gridId+'\',\''+i+'\',\''+c+'\',this,event)" style="cursor:pointer"' : '' ;*/
					td += column.type == null ? '' : column.type == 'action' ? ' onClick="$(\'#'+config.gridId+'\').jGridContentMVC_GridCellClick( \''+config.gridId+'\',\''+i+'\',\''+c+'\',this,event)" ' : '' ;
                    _styleTD += column.type == null ? '' : column.type == 'action' ? ';cursor:pointer' : '' ;					

					td += column.titleBean == null ? '':' title="'+ bean[column.titleBean] +'"';
					
					td += ' style="' + _styleTD + '">';
					
					
					
					if(column.cellValue==null){					
                        if (column.id.constructor.toString().indexOf( "Array" ) == -1 )                          
                                td+=bean[column.id];
					    else
					        for(var iid = 0; iid < column.id.length; iid++){
						        td+=bean[column.id[iid] ];
						        td+= column.id.length==1 ? '' : iid==(column.id.length-1) ? '' : column.betweenValue == null ? " " : column.betweenValue;
					        }
					}else if(column.cellValue=='ValueIndicator'){ // idValue:value , idIndicator:indicator 
					        td+='<table width="100%" class="ValueIndicator" cellpadding="0" cellspacing="0"><tr><td width="70%" align="right">'+bean[column.idValue]+'</td><td width="30%" align="center"><IMG SRC="images/Paneles/Ind'+bean[column.idIndicator]+'.png" /></td></tr></table>';
					}else if(column.cellValue=='ImageIndicator'){
					    td+= '<img src="images/Paneles/Ind'+ bean[column.id] +'.png" />';
					}else if(column.cellValue=='ValueImageIndicator'){
					    if ( bean[column.id] == 0)
					        td+='<div style="width:100%;float: right;"><img src="images/Paneles/Ind1.png" style="margin-left: 10px; width:7px; height: 7px"/></div>';
					    else if ( bean[column.id] > 0)
					        td+='<div style="width:100%;float: right;"><img src="images/Paneles/Ind4.png" style="margin-left: 10px; width:7px; height: 7px"/></div>';
					}else if(column.cellValue=='ValueImageIndicatorAR'){
					    if ( bean[column.id] <= 0)
					        td+='<div style="width:100%;float: right;"><img src="images/Paneles/Ind1.png" style="margin-left: 10px; width:7px; height: 7px"/></div>';
					    else if ( bean[column.id] > 0)
					        td+='<div style="width:100%;float: right;"><img src="images/Paneles/Ind4.png" style="margin-left: 10px; width:7px; height: 7px"/></div>';
					}else if(column.cellValue=='ValueImageIndicatorPrice'){
					    if ( bean[column.id] == 0)
					        td+='<div style="width:100%;float: right;"><img src="images/Paneles/Ind1.png" style="margin-left: 10px; width:7px; height: 7px"/></div>';
					    else if ( bean[column.id] > 0)
					        td+='<div style="width:100%;float: right;"><img src="images/Paneles/Ind' + bean[column.id] + '.png" style="margin-left: 10px; width:7px; height: 7px"/></div>';
					}else if(column.cellValue=='_chk'){
					    td+='<input id="chkDEstimated1" type="checkbox" '+(bean[column.id]?'checked="checked"':'')+'/>';
					
					}else if(column.cellValue=='_chkValue'){
					    td+='<table width="100%" cellpadding="0" cellspacing="0"><tr><td id="tdValueItem" width="90%" align="left" class="ValveItem">'+bean[column.idValue]+'</td><td width="10%" align="right"><input onClick="$(\'#'+config.gridId+'\').jGridContentMVC_GridChkChange( event,this, \''+config.gridId+'\',\''+i+'\',\''+c+'\' )" id="chkDEstimated'+i+c+'" type="checkbox" '+(bean[column.id]?'checked="checked"':'')+'/></td></tr></table>';
					
					}else if(column.cellValue=='_chkid'){
					    td+='<input onClick="$(\'#'+config.gridId+'\').jGridContentMVC_GridChkChange( event,this, \''+config.gridId+'\',\''+i+'\',\''+c+'\' )" id="chkDEstimated'+i+c+'" type="checkbox" '+(bean[column.id]?'checked="checked"':'')+'/>';
					    }
                     else if(column.cellValue=='chkidIf'){
                            //Visualiza el checkbox si la propiedad #If esta en true('if' aplica para valor estatico, '#if' para una columna del bean)
                            //{HeadText:'',cellValue:'chkidIf',id:'CheckAll','chkidIfProperty':{'if':'1'}},                                    
                            //{HeadText:'',cellValue:'chkidIf',id:'CheckAll','chkidIfProperty':{'#if':'IsDetail'}},                                    
                            if(column.hasOwnProperty('chkidIfProperty')){
                                for (var itemObj in column.chkidIfProperty){
                                    if(itemObj == '#if'){            
                                        if( bean[ column.chkidIfProperty[itemObj] ] == 1 )      
                                            td+='<input onClick="$(\'#'+config.gridId+'\').jGridContentMVC_GridChkChange( event,this, \''+config.gridId+'\',\''+i+'\',\''+c+'\' )" id="chkDEstimated'+i+c+'" type="checkbox" '+(bean[column.id]?'checked="checked"':'')+'/>';                                                            
    					            }else if(itemObj == 'if'){      
                                            if(column.chkidIfProperty[itemObj] == 1)
                                                td+='<input onClick="$(\'#'+config.gridId+'\').jGridContentMVC_GridChkChange( event,this, \''+config.gridId+'\',\''+i+'\',\''+c+'\' )" id="chkDEstimated'+i+c+'" type="checkbox" '+(bean[column.id]?'checked="checked"':'')+'/>';                                                      
    					            }	
    					        }
                            }else{
                                td+='<input onClick="$(\'#'+config.gridId+'\').jGridContentMVC_GridChkChange( event,this, \''+config.gridId+'\',\''+i+'\',\''+c+'\' )" id="chkDEstimated'+i+c+'" type="checkbox" '+(bean[column.id]?'checked="checked"':'')+'/>';
                            }                                                    
                        }
					else if(column.cellValue=='_chkidic'){
					    td+='<input id="chkDEstimated'+i+c+'" type="checkbox" '+(bean[column.id]?'checked="checked"':'')+'/>';
					    }
					else if(column.cellValue=='_chk2idic'){
					    td+='<input id="chk2DEstimated'+i+c+'" type="checkbox" '+(bean[column.id]?'checked="checked"':'')+'/>';
					    }
					else if(column.cellValue=='_styleSubray') 
					{
					    td+='<div style="text-decoration:underline;">'+ bean[column.id] +'</div>';	
					    //td+='<style type="text/css"> A:link, A:visited { text-decoration: none }A:hover {  }</style>';
					}else if( column.cellValue=='CustomFunction' ){
					    td+= column.Function( bean, column );
					}else if(column.cellValue=='InputStyleHtml'){
					    /*las propiedades de los estilos que inicien con # se toman como parametros dinamicos*/
					    //bean[column.id]
					    // ejemplo cellValue:'InputStyleHtml',ObjInputStyleHtml:{'#color':'CoverageLetterColor','#background-color':'CoverageBackgroundColor','font-weight':'bolder'}
                        var objtag='';
                        //
                        if(column.hasOwnProperty('ObjInputStyleHtml')){
					        for (var itemObj in column.ObjInputStyleHtml){
                                if(itemObj.indexOf("#")==0){
                                        if(itemObj.length>1){
                                            if($.trim(objtag)==''){
                                                objtag=' '+itemObj.substring(1, itemObj.length)+':'+bean[column.ObjInputStyleHtml[itemObj]];
                                            }else{
                                                objtag=objtag+';'+itemObj.substring(1, itemObj.length)+':'+bean[column.ObjInputStyleHtml[itemObj]];
                                            }
                                        }
                                }else{
                                    if($.trim(objtag)==''){
                                        objtag=itemObj+':'+column.ObjInputStyleHtml[itemObj];                                
                                    }else{
                                        objtag=objtag+';'+itemObj+':'+column.ObjInputStyleHtml[itemObj];
                                    }
                                }	
					        }
					        if($.trim(objtag)!=''){
                                td+='<div style="'+objtag+';width:100%;height:100%">'+bean[column.id]+'</div>';					    
                            }
                        }
                        //
                        if(column.hasOwnProperty('ObjInputCssHtml')){
                            //ObjInputCssHtml : {Column:'ALG_IsJanssenDefault',Css:{'0':'CSS0','1':'CSS1'}}
                            for (var itemObj in column.ObjInputCssHtml.Css){
                                if(bean[column.ObjInputCssHtml.Column] == itemObj){
                                    td += "<div style=\"width:100%; height:100%;\" class=\""+column.ObjInputCssHtml.Css[itemObj]+"\">"+bean[column.id]+"</div>";                            
                                    break;
                                }
                            }
                        }  
                        if(column.hasOwnProperty('ObjInputIndicatorStyle')){
                            //rojo #FF0000 ,amarillo #FFFF00 y verde #00FF00
                            var ObjInputIndicatorStyle_Indicartor = '';
                            var ObjInputIndicatorStyle_Indicartor_width = 'width:10px';
                            var Css = '';
					        for (var itemObj in column.ObjInputIndicatorStyle){
                                if(itemObj.indexOf("#")==0){
                                        if(itemObj.length>1){
                                            if( itemObj.substring(1, itemObj.length) == 'Indicator-color' ){
                                                if( bean[ column.ObjInputIndicatorStyle[itemObj] ] == '#00FF00' ){
                                                    ObjInputIndicatorStyle_Indicartor = '../images/Paneles/Ind1.png';
                                                }else if( bean[ column.ObjInputIndicatorStyle[itemObj] ] == '#FFFF00' ){
                                                    ObjInputIndicatorStyle_Indicartor = '../images/Paneles/Ind2.png';
                                                }else if( bean[ column.ObjInputIndicatorStyle[itemObj] ] == '#FF0000' ){
                                                    ObjInputIndicatorStyle_Indicartor = '../images/Paneles/Ind4.png';
                                                }else{
                                                    ObjInputIndicatorStyle_Indicartor_width = 'width:0px';
                                                }
                                            }                                        
                                            if($.trim(objtag)==''){
                                                objtag=' '+itemObj.substring(1, itemObj.length)+':'+bean[column.ObjInputIndicatorStyle[itemObj]];
                                            }else{
                                                objtag=objtag+';'+itemObj.substring(1, itemObj.length)+':'+bean[column.ObjInputIndicatorStyle[itemObj]];
                                            }
                                        }
                                }else{
                                    if( itemObj == 'Indicator-color' ){
                                        if( column.ObjInputIndicatorStyle[itemObj] == '#00FF00' ){
                                            ObjInputIndicatorStyle_Indicartor = '../images/Paneles/Ind1.png';
                                        }else if( column.ObjInputIndicatorStyle[itemObj] == '#FFFF00' ){
                                            ObjInputIndicatorStyle_Indicartor = '../images/Paneles/Ind2.png';
                                        }else if( column.ObjInputIndicatorStyle[itemObj] == '#FF0000' ){
                                            ObjInputIndicatorStyle_Indicartor = '../images/Paneles/Ind3.png';
                                        }
                                    }                
                                    if( itemObj.toUpperCase() == 'CLASS'){
                                        Css = itemObj+'="' + column.ObjInputIndicatorStyle[itemObj] + '"';
                                    }else{
                                        if($.trim(objtag)==''){
                                            objtag=itemObj+':'+column.ObjInputIndicatorStyle[itemObj];                                
                                        }else{
                                            objtag=objtag+';'+itemObj+':'+column.ObjInputIndicatorStyle[itemObj];
                                        }
                                    }
                                }	
					        }
					        if($.trim(objtag)!=''){
                                //td+='<div style="'+objtag+';width:100%;height:100%">'+bean[column.id]+'</div>';					    
                                //por implementar
                                //td+= '<table cellpadding="0" cellspacing="0" border="0"><tr><td style="'+ objtag +'">'+bean[column.id]+'</td><td style="width:10px;"><span style="background-image:url(\'../images/Paneles/Ind1.png\');width:10px;height:10px;background-repeat:no-repeat;background-position:center;"></span></td></tr></table>'
                                td+= '<table cellpadding="0" cellspacing="0" border="0" '+Css+'><tr><td style="'+ objtag +'">'+bean[column.id]+'</td><td style="'+ObjInputIndicatorStyle_Indicartor_width+'"><div style="background-image:url(\''+ ObjInputIndicatorStyle_Indicartor +'\');width:10px;height:10px;background-repeat:no-repeat;background-position:center;"></div></td></tr></table>'
                            }
                        }                                              
					}
					/*else if(column.cellValue=='_cmb'){
					    td+='<input id="chkDEstimated1" type="combobox" '+(bean[column.id]?'checked="checked"':'')+'/>';
					}*/
					else if(column.type == 'path'){
					    td += '<a title="download" target="_blank" href="'+bean[column.id]+'">'+column.cellValue+'</a>';
					}else if(column.cssRange!=null){
					    var valueCellR = bean[column.id];
					    var valueCell = parseFloat(bean[column.id]);
					        valueCell = isNaN(valueCell) ? 0 : valueCell;
					    var cssRangeClass = "";
					    for(var cssRi in column.cssRange){//[{linf:0,lsup:100,cssClass:'ValueOK'},{linf:101,lsup:null,cssClass:'ValueBad'}]}
					        if(column.cssRange[cssRi].lsup != null){
					            if(valueCell >= column.cssRange[cssRi].linf && valueCell <= column.cssRange[cssRi].lsup){
					                cssRangeClass = column.cssRange[cssRi].cssClass;
					                break;
					            }
					        }else{
					            if( valueCell >= column.cssRange[cssRi].linf ){
					                cssRangeClass = column.cssRange[cssRi].cssClass;
					                break;
					            }
					        }
					    }
					    td+='<div class="'+cssRangeClass+'">'+valueCellR+'</div>'
					}else if(column.cssRangeEq!=null){
					    var valueCell = bean[column.id];					    					        
					    var cssRangeClass = "";
					    for(var cssRi in column.cssRangeEq.list){//{cssDefault:'',list:[{Eq:,cssClass:'ValueOK'},{linf:101,lsup:null,cssClass:'ValueBad'}]}					        
					            cssRangeClass = column.cssRangeEq.cssDefault;
					            if(valueCell == column.cssRangeEq.list[cssRi].Eq){
					                cssRangeClass = column.cssRangeEq.list[cssRi].cssClass;
					                break;
					            }				        
					        }					    
					    td+='<div class="'+cssRangeClass+'">'+valueCell+'</div>'
					}else if(column.cssRangeEqPty!=null){
					    //cssRangeEqPty:{cssDefault:'cssClass',list[{Eq:EvalPtyValue,Range:[{Type:'RAN',linf:double,lsup:double,cssValue:'cssClass'}]}]}
					    var PtyValue=0;
					    switch(column.cssRangeEqPty.Pty){
					        case "jGrid_RowIndex":
					            PtyValue = i;
					            break;					        
					        default:
					            PtyValue = bean[ column.cssRangeEqPty.Pty ];
					            break;
					    }					    					    
                        PtyValue = parseInt(PtyValue);
					    PtyValue = isNaN(PtyValue) ? 0 : PtyValue;
					    
					    var valueCell = bean[column.id];
					    var valueCellEval = parseInt( valueCell );
					        valueCellEval = isNaN(valueCellEval) ? 0 : valueCellEval;
					    var cssRangeClass = "";
					    for(var cssRi in column.cssRangeEqPty.list){//{cssDefault:'',list:[{Eq:,cssClass:'ValueOK'},{linf:101,lsup:null,cssClass:'ValueBad'}]}					        
					            cssRangeClass = column.cssRangeEqPty.cssDefault;
					            var BeanEq = column.cssRangeEqPty.list[cssRi];
					            if(PtyValue == BeanEq.Eq || BeanEq.Eq == 'jGrid_Default' || (BeanEq.Eq == 'jGrid_MaxLength' && PtyValue == jGrid_MaxLength) ){
					                for(var iRange in BeanEq.Range){
					                    var range = BeanEq.Range[iRange];
					                    var evalOK = false;
					                    switch(range.Type){
					                        case "RAN": evalOK = valueCellEval >= range.linf && valueCellEval <= range.lsup; break;
					                        case "OUT": evalOK = valueCellEval < range.linf || valueCellEval > range.lsup; break;
					                        case "MAX": evalOK = valueCellEval >= range.linf; break;
					                        case "MIN": evalOK = valueCellEval <= range.linf; break;
					                        case "IDE": evalOK = valueCellEval == range.linf; break;
					                        case "DIF": evalOK = valueCellEval != range.linf; break;
					                    }
					                    if(evalOK){
					                        cssRangeClass = range.cssValue;
					                        break;
					                    }					                        
					                }
					                break;
					            }				        
					        }					    
					    td+='<div class="'+cssRangeClass+'">'+valueCell+'</div>'
					}else if(column.cellValue.toUpperCase()=='CHECKBOX'){
                            /**
                            * @param    cellValue 'CHECKBOX'
                            * @eventListener cellCheckedValue   Delegado de la función change por checked
                            */
                            var onChange = '$(\'#'+config.gridId+'\').jGridContentMVC_onChangeCheckboxCtrl( this )';
                            if( bean[column.id]==null )
                                bean[column.id] = false;
                            
                            var checked = bean[column.id] ? 'checked="checked"' : '';
                            var listener_onchange = $.browser.msie ? 'onclick="'+onChange+'"' : 'onchange="'+onChange+'"';
                            var disabled = column.enabled == null ? '' : column.enabled ? '' : ' disabled="column.enabled" ';
                            td += '<input id="jGCMVC'+config.gridId+'_chk'+i+'" iRow="'+i+'" iCell="'+c+'" gridId="'+config.gridId+'" columnId="'+column.id+'" class="ChkClass'+(config.gridId+column.id)+'" type="checkbox" value="'+column.id+'" '+listener_onchange+' '+checked+' '+disabled+'/>';
                        }else if(column.cellValue.toUpperCase()=='COMBOBOX'){
                            /**
                            * @param    cellValue 'COMBOBOX'
                            * @eventListener cellChangeCombobox   Delegado de la función change por combobox
                            */
                            var onChange = '$(\'#'+config.gridId+'\').jGridContentMVC_onChangeComboBoxCtrl( this )';
                        var workArgs = ' iRow="'+i+'" iCell="'+c+'" gridId="'+config.gridId+'" columnId="'+column.id+'" ptyValue="'+column.valueConfig.ptyValue+'" ptyKey="'+column.valueConfig.ptyKey+'"';                                     
                        var cssClass = ' class="CMBClass'+(config.gridId+column.id)+'" ';
                        var notReplace = false;
                        for(var iNotReplace in column.valueConfig.notReplace){
                            if(bean[column.valueConfig.ptyKey]==column.valueConfig.notReplace[iNotReplace]){
                                notReplace=true;
                                cssClass = '';
                                break;
                                }
                        }
                                                                 
                        var options = notReplace?'<option value="'+bean[column.valueConfig.ptyKey]+'" selected="selected">'+bean[column.valueConfig.ptyValue]+'</option>':'';
                        for(var iCmbItem in column.valueConfig.list){
                            if(notReplace)break;
                            var cmbItem = column.valueConfig.list[iCmbItem];
                            var selected = bean[column.valueConfig.ptyKey]==cmbItem.key ? 'selected="selected"':'';                            
                            options += '<option value="'+cmbItem.key+'" class="CMBClass'+(config.gridId+column.id)+'" '+selected+' >'+cmbItem.value+'</option>';
                        }
                        td+='<select '+workArgs+' '+cssClass+' onChange="'+onChange+'" style="'+column.valueConfig.style+'">'+options+'</select>';
                        
                        }else if(column.cellValue.toUpperCase()=='DATEPICKER'){
                        /**
                            * @param    cellValue 'DATEPICKER'
                            * @eventListener cellChangeCombobox   Delegado de la función change por combobox
                            */
                        var onChange = '$(\'#'+config.gridId+'\').jGridContentMVC_HeadDATEPICKER(this)';
                        var workArgs = ' iRow="'+i+'" iCell="'+c+'" gridId="'+config.gridId+'" columnId="'+column.id+'" ptyValue="'+column.valueConfig.ptyValue+'" ptyKey="'+column.valueConfig.ptyKey+'"';                                      
                        var cssClass = 'jGridContentMVC_DATEPICKER'+(config.gridId+column.id);
                        var cssCtrl = column.valueConfig.cssCtrl == null ? "" : column.valueConfig.cssCtrl;
                        var datepickerId = cssClass+i+'_'+c;
                        var value = bean[column.valueConfig.ptyKey];
                        td+='<input id="'+datepickerId+'" class="'+cssClass+' '+cssCtrl+'" type="text" value="'+value+'" size="12" maxlength="10" '+workArgs+' style="'+column.valueConfig.style+'"/>';
                                                                              
                        datepickerCtrlId.push(datepickerId);
                        /****TextBox*****/
                        }else if(column.cellValue.toUpperCase()=='TEXTBOX'){
                        /**
                            * @param    cellValue 'TEXTBOX'
                            * @eventListener cellChangeCombobox   Delegado de la función change por combobox
                            */
                        var onChange = '$(\'#'+config.gridId+'\').jGridContentMVC_onChangeTextBox(this)';
                        var workArgs = ' iRow="'+i+'" iCell="'+c+'" gridId="'+config.gridId+'" columnId="'+column.id+'" ptyValue="'+column.valueConfig.ptyValue+'" ptyKey="'+column.valueConfig.ptyKey+'"';                                      
                        //var cssClass = 'jGridContentMVC_DATEPICKER'+(config.gridId+column.id);
                        //var datepickerId = cssClass+i+'_'+c;
                        var textboxId= i+'_'+c+'_'+config.gridId;
                        var value = bean[column.valueConfig.ptyKey];
                        
                        
                        
                        if(bean[column.valueConfig.ptyValue]=='R')
                        {   //alert(value.length);
                            if(value.length == 2)
                            {td+='<label size="10" style="width: 4.8%; "></label>';
                            }else
                            //td+='<input type="textbox" value="'+value+'" size="10" maxlength="12" '+workArgs+' style="'+column.valueConfig.style+';border:0px;border-style:solid;border-color:#EFEFFF; font-family: Arial;font-size: 12px;"/>'
                            {td+='<label size="10" style="width: 4.8%; ">'+value+'</label>';}
                        
                        }else
                        {
                            //$('#'+textboxId).mask('$999,999');
                            //$('#'+textboxId).val(value);
                            //value = 0;
                           // td+='<input  type="textbox" value="'+value+'" size="8" maxlength="12" '+workArgs+' style="'+column.valueConfig.style+';border:0px;border-style:solid;border-color:#EFEFFF; font-family: Arial;font-size: 11px;" onChange="'+onChange+'" onkeyup ="this.value=BSM_ExpensesNew.FmtMoneda(this.value)"/>'//
                            td+='<input id="'+textboxId+'" type="textbox" value="'+value+'" size="8" maxlength="12" '+workArgs+' style="'+column.valueConfig.style+';border:0px;border-style:solid;border-color:#EFEFFF; font-family: Arial;font-size: 11px;" onChange="'+onChange+'"/>';//onkeyup ='+BSM_ExpensesNew.FmtMoneda(value)+'
                            ///td+='<input id="'+textboxId+'" type="textbox" value="'+value+'" size="8" maxlength="12" '+workArgs+' style="'+column.valueConfig.style+';border:0px;border-style:solid;border-color:#EFEFFF; font-family: Arial;font-size: 11px;" onChange="'+onChange+'" onkeyup ="this.value=BSM_ExpensesNew.FmtMoneda(this.value)"/>'//
                            
                           // td+='<input  type="textbox" value="'+value+'" size="8" maxlength="12" '+workArgs+' style="'+column.valueConfig.style+';border:0px;border-style:solid;border-color:#EFEFFF; font-family: Arial;font-size: 11px;"/>'//
                            
                            //$("#"+textboxId).mask("999-99-9999");
//                            $("#"+textboxId).mask("99/99/9999",{completed:function(){alert("Se ha completado la carga: "+this.val());}});
//                            });
                            //****
                           //$("#"+textboxId).mask("$ 999,999,999",{completed:function(){alert("Se ha completado la carga: "+this.val());}});
                            //**
                        }
                        datepickerCtrlId.push(datepickerId);
                        /******/

                        }else if(column.cellValue=='SelectClass'){
					    var SelectClassValue = bean[column.id];
					    for(var iSClass in column.SelectClass){
					        if(bean[column.id] == column.SelectClass[iSClass].Value){
					            //SelectClassValue='<div class="'+column.SelectClass[iSClass].Class+'"></div>';
					            //break;
                                SelectClassValue='<div style="width:100%; height:100%;" class="'+column.SelectClass[iSClass].Class+'">&nbsp</div>';
                                break;
					        }
					    }
					    td+=SelectClassValue;
					}else if(column.cellValue == 'iconAction'){
                            var cssClass = 'ui-icon '+column.iconAction;
                            if(column.cursorIcon)
                                td+='<div style="cursor:pointer;" class="'+cssClass+'"></div>';
                            else
                                td+='<div class="'+cssClass+'"></div>';
                    }else if(column.cellValue == 'bullet'){
                        td+= '<img style="cursor:pointer" src="images/Bullet'+ bean[column.id] +'_25px.png" />';                        
                    }else
					    td+=column.cellValue;
					td+='</td>';
					tr+=td;
				}			
				tr+='</tr>';
				var tblFinRow = '';
				if(config.interLine!=null)
				    tr+='<tr style="height: '+config.interLine+';"><td  colspan="'+config.columns.length+'"></td></tr>';//<td style="width:25%; height: 30px; color:Black; font-weight:bold">
				if( i == (config.storage.length-1) && config.rowFinalSeparate != null ){
				        var cssAUXTab = i%2!=0?"#FFFFFF":" #FFFFFF";
				        tblFinRow = '<table';
			            tblFinRow += config.cellpadding == null ? '' : ' cellpadding="'+config.cellpadding+'"';
			            tblFinRow += config.cellspacing == null ? '' : ' cellspacing="'+config.cellspacing+'"';
			            tblFinRow += config.border == null ? '' : ' border="'+config.border+'"';            			
			            tblFinRow += config.tblCssClass != null ? ' class="'+config.tblCssClass+'"' : '';
			            tblFinRow += ' style="width:100%;font-weight:bold;background-color:'+cssAUXTab+'"';            			
			            tblFinRow += '>';
			            tblFinRow += tr;
			            tblFinRow+='</table>';
				}else        
				    tbl+=tr;
			}			
			tbl+='</tbody></table>';
			tbl = config.storage.length > 0 ? tbl : 'no record to display';
			tbl += config.rowFinalSeparate != null ? tblFinRow == undefined ? "" : tblFinRow : "";
			
			
			if( config.paginationSettings == null){
			    $( '#'+config.gridId ).html( tbl );
			}else{
			/*==============*/
			var idDivTable = config.gridId +'_divTable';
			var divTable = $('#'+idDivTable);
			if( divTable.length == 0 ){
			    var divTable = $.newHTML('div',{attr:{'id':idDivTable}});			
			    $(divTable).html( tbl );			
			    $( '#'+config.gridId ).html('');
			    if( config.paginationSettings != null ){
			        var ctrlPage = jGridContentMVC_paginationSettings(config);
			        $( '#'+config.gridId ).append( ctrlPage );
			    }
			    
			    $( '#'+config.gridId ).append( divTable );
			}else{
			    if( config.paginationSettings != null )			    
			        jGridContentMVC_updatePagination( config );
			    $(divTable).html( tbl );
			}
			afterDOMRegister( config );
			/*==============*/
			};
			
			
			var addLoadDiv = function(div){
                var divLoading = $.newHTML('div',{ attr:{id:config.gridId+'_dataTableLoading',title:'please wait, loading...'}, css:{'width':'100%','height': '100%','display':'none','position':'absolute','top':'1px'},cssClass:'ui-widget-overlay' });
                var divLoadingImg = $.newHTML('div',{ css:{'top':'40%','position':'absolute','width':'100%'} });
                config.loading = config.loading != null ? config.loading : '<table width="100%"><tr><td align="center"><img src="images/component_loading.gif" /></td></tr></table>';
                $( divLoadingImg ).html( config.loading );
                $( divLoading ).html( divLoadingImg );
                $( div ).css('position','relative').append(divLoading)
            };
			
			if(config.loading != null)
			    addLoadDiv( '#'+config.gridId );
			
			if( column_edit )
                    jGridContentMVC_CellEdit( config );  
            
            if(config.tooltip != null)
		        $('#'+config.gridId).tooltip(config.tooltip);     
                
		};
						
		$.fn.jGridContentMVC_GridDrawCss = $.fn.jGridContentMVC_drawGrid != null ? $.fn.jGridContentMVC_drawGrid : function(config,meta){
			var jGrid_MaxLength = config.storage.length == 0 ? 0 : config.storage.length-1;
			
			var tbl = '<table';
			tbl += ' id="Tbl'+config.gridId+'"';
			tbl += config.cellpadding == null ? '' : ' cellpadding="'+config.cellpadding+'"';
			tbl += config.cellspacing == null ? '' : ' cellspacing="'+config.cellspacing+'"';
			tbl += config.border == null ? '' : ' border="'+config.border+'"';
			
			tbl += config.tblCssClass != null ? ' class="'+config.tblCssClass+'"' : '';
			tbl += config.tblStyle != null ? ' style="'+config.tblStyle+'"' : '';
			tbl += ' id="GridTbl'+config.gridId+'"';
			tbl += '>';			
			
			if(config.Heads){			    
			    tbl += '<thead><tr';
			    tbl += config.HeadCss == null ? '' : ' class="'+config.HeadCss+'"';
			    tbl += config.HeadAlign == null ? ' align="center"' : ' align="'+config.HeadAlign+'"';
			    tbl += '>';
			    for(var c = 0; c < config.columns.length; c++){
			        var column = config.columns[c];
			        var td = '<th';
					    td += column.width == null ? '':' style="width:'+ column.width +'"' ;
					    td += '>';
					    td += column.HeadText;
					    td += '</th>';
					    tbl += td;
			    }
			    tbl += '</tr></thead><tbody>';
			    
			    }
			
			for(var i = 0; i < config.storage.length; i++){
				var bean = config.storage[i];				
				var tr = '<tr';				
				tr += config.rowAlign == null ? '' : ' align="'+config.rowAlign+'"';
				tr += config.rowClick == null ? '' : ' onClick="$(\'#'+config.gridId+'\').jGridContentMVC_GridRowClick( \''+config.gridId+'\',\''+i+'\' )" style="cursor:pointer"';
				if(config.rowFinal == null)
				    tr += config.rowCssA  == null ? '' : 'class="'+ (( (i+1) % 2)==0 ? config.rowCssA : config.rowCssB ) + '"';
				else{
				    if(i == (config.storage.length-1))
				        tr += config.rowCssA  == null ? 'class="'+config.rowFinal+'"' : 'class="'+ (( (i+1) % 2)==0 ? config.rowCssA : config.rowCssB ) + ' '+config.rowFinal+'"';
				    else
				        tr += config.rowCssA  == null ? '' : 'class="'+ (( (i+1) % 2)==0 ? config.rowCssA : config.rowCssB ) + '"';				    
				    }
				tr += '>';
				
				for(var c = 0; c < config.columns.length; c++){
				    var column = config.columns[c];					
					var td = '<td';	
					td += column.width == null ? '':' style="width:'+ column.width +'"' ;				
					td += column.align == null ? '':' align="'+column.align+'"';
										
					td += column.cssClass == null ? '':' class="'+column.cssClass+(meta.column == c?" "+meta.css:"")+'"';
					td += column.cssClass == null && meta.column == c ? ' class="'+meta.css+'"':'';
					td += column.titleBean == null ? '':' title="'+ bean[column.titleBean] +'"';
					td += column.type == null ? '' : column.type == 'action' ? ' onClick="$(\'#'+config.gridId+'\').jGridContentMVC_GridCellClick( \''+config.gridId+'\',\''+i+'\',\''+c+'\')" style="cursor:pointer"' : '' ;										
					td += '>';
					
					
					
					if(column.cellValue==null){					
                        if (column.id.constructor.toString().indexOf( "Array" ) == -1 )                          
                                td+=bean[column.id];
					    else
					        for(var iid = 0; iid < column.id.length; iid++){
						        td+=bean[column.id[iid] ];
						        td+= column.id.length==1 ? '' : iid==(column.id.length-1) ? '' : column.betweenValue == null ? " " : column.betweenValue;
					        }
					}else if(column.cellValue=='_chk'){
					    td+='<input id="chkDEstimated1" type="checkbox" '+(bean[column.id]?'checked="checked"':'')+'/>';
					}else if(column.type == 'path'){
					    td += '<a title="download" target="_blank" href="'+bean[column.id]+'">'+column.cellValue+'</a>';
					}else if(column.cssRange!=null){
					    var valueCellR = bean[column.id];
					    var valueCell = parseFloat(bean[column.id]);
					        valueCell = isNaN(valueCell) ? 0 : valueCell;
					    var cssRangeClass = "";
					    for(var cssRi in column.cssRange){//[{linf:0,lsup:100,cssClass:'ValueOK'},{linf:101,lsup:null,cssClass:'ValueBad'}]}
					        if(column.cssRange[cssRi].lsup != null){
					            if(valueCell >= column.cssRange[cssRi].linf && valueCell <= column.cssRange[cssRi].lsup){
					                cssRangeClass = column.cssRange[cssRi].cssClass;
					                break;
					            }
					        }else{
					            if( valueCell >= column.cssRange[cssRi].linf ){
					                cssRangeClass = column.cssRange[cssRi].cssClass;
					                break;
					            }
					        }
					    }
					    td+='<div class="'+cssRangeClass+'">'+valueCellR+'</div>'
					}else if(column.cssRangeEq!=null){
					    var valueCell = bean[column.id];					    					        
					    var cssRangeClass = "";
					    for(var cssRi in column.cssRangeEq.list){//{cssDefault:'',list:[{Eq:,cssClass:'ValueOK'},{linf:101,lsup:null,cssClass:'ValueBad'}]}					        
					            cssRangeClass = column.cssRangeEq.cssDefault;
					            if(valueCell == column.cssRangeEq.list[cssRi].Eq){
					                cssRangeClass = column.cssRangeEq.list[cssRi].cssClass;
					                break;
					            }				        
					        }					    
					    td+='<div class="'+cssRangeClass+'">'+valueCell+'</div>'
					}else if(column.cssRangeEqPty!=null){
					    //cssRangeEqPty:{cssDefault:'cssClass',list[{Eq:EvalPtyValue,Range:[{Type:'RAN',linf:double,lsup:double,cssValue:'cssClass'}]}]}
					    var PtyValue=0;
					    switch(column.cssRangeEqPty.Pty){
					        case "jGrid_RowIndex":
					            PtyValue = i;
					            break;					        
					        default:
					            PtyValue = bean[ column.cssRangeEqPty.Pty ];
					            break;
					    }					    					    
                        PtyValue = parseInt(PtyValue);
					    PtyValue = isNaN(PtyValue) ? 0 : PtyValue;
					    
					    var valueCell = bean[column.id];
					    var valueCellEval = parseInt( valueCell );
					        valueCellEval = isNaN(valueCellEval) ? 0 : valueCellEval;
					    var cssRangeClass = "";
					    for(var cssRi in column.cssRangeEqPty.list){//{cssDefault:'',list:[{Eq:,cssClass:'ValueOK'},{linf:101,lsup:null,cssClass:'ValueBad'}]}					        
					            cssRangeClass = column.cssRangeEqPty.cssDefault;
					            var BeanEq = column.cssRangeEqPty.list[cssRi];
					            if(PtyValue == BeanEq.Eq || BeanEq.Eq == 'jGrid_Default' || (BeanEq.Eq == 'jGrid_MaxLength' && PtyValue == jGrid_MaxLength) ){
					                for(var iRange in BeanEq.Range){
					                    var range = BeanEq.Range[iRange];
					                    var evalOK = false;
					                    switch(range.Type){
					                        case "RAN": evalOK = valueCellEval >= range.linf && valueCellEval <= range.lsup; break;
					                        case "OUT": evalOK = valueCellEval < range.linf || valueCellEval > range.lsup; break;
					                        case "MAX": evalOK = valueCellEval >= range.linf; break;
					                        case "MIN": evalOK = valueCellEval <= range.linf; break;
					                        case "IDE": evalOK = valueCellEval == range.linf; break;
					                        case "DIF": evalOK = valueCellEval != range.linf; break;
					                    }
					                    if(evalOK){
					                        cssRangeClass = range.cssValue;
					                        break;
					                    }					                        
					                }
					                break;
					            }				        
					        }					    
					    td+='<div class="'+cssRangeClass+'">'+valueCell+'</div>'
					}else if(column.cellValue=='SelectClass'){
					    var SelectClassValue = bean[column.id];
					    for(var iSClass in column.SelectClass){
					        if(bean[column.id] == column.SelectClass[iSClass].Value){
					            //SelectClassValue='<div class="'+column.SelectClass[iSClass].Class+'"></div>';
					            //break;
                                SelectClassValue='<div style="width:100%; height:100%;" class="'+column.SelectClass[iSClass].Class+'">&nbsp</div>';
                                break;
					        }
					    }
					    td+=SelectClassValue;
					}else
					    td+=column.cellValue;
					td+='</td>';
					tr+=td;
				}			
				tr+='</tr>';
				if(config.interLine!=null)
				    tr+='<tr style="height: '+config.interLine+';"><td  colspan="'+config.columns.length+'"></td></tr>';//<td style="width:25%; height: 30px; color:Black; font-weight:bold">
				    
				tbl+=tr;
			}			
			tbl+='</tbody></table>';
			tbl = config.storage.length > 0 ? tbl : 'no record to display';
			$( '#'+config.gridId ).html( tbl );
			
		};
		
		var jGridContentMVC_CellEdit = function(config){
            $('.'+config.gridId+'_edit').click(function(){
                if($(this).data('edit'))return;
                
                var gridid = $(this).attr('gridid');                
                var irow = $(this).attr('irow');
                var icell = $(this).attr('icell');
                var ptyid = $(this).attr('ptyid');
                
                var config = $( '#'+gridid ).jGridContentMVC_GridStorage[ gridid ];
                bean = config.storage[irow];
                bean.gridid = gridid;
                bean.irow = irow;
                bean.icell = icell;
                bean.ptyid = ptyid;
                
                value = bean[ptyid];
                
                var saveChange = function( md, value ){
                    var config = $( '#'+md.bean.gridid ).jGridContentMVC_GridStorage[ md.bean.gridid ];
                    bean = config.storage[md.bean.irow];
                    bean[md.bean.ptyid] = value;
                    column = config.columns[md.bean.icell];
                    column.edit_save( bean,md );
                    $(md.parent).html( value ).data('edit',false);
                };
                
                var input = document.createElement('input');
                $(input).css('width','100%').val(value).data('MD',{'parent':this,'bean':bean,'config':config,'value':value}).keypress(function(event){
                        var md = $(this).data('MD');                        
                        switch(event.which){
                            case 0: 
                                $(md.parent).html( md.value ).data('edit',false);
                                break;
                            case 13: 
                                saveChange( md, $(this).val() );
                                break;
                            
                        }
                   }).focusout(function(){
                        if(!$(this).data('edit'))return;
                        var md = $(this).data('MD');
                        saveChange( md, $(this).val() );
                   });
                $(this).html( input );
                $(input).select().focus();
                $(this).data('edit',true);
            });            
          };
		
		$.fn.jGridContentMVC_GridRowClick = function(GridId,index){
			var config = $( '#'+GridId ).jGridContentMVC_GridStorage[ GridId ];
			config.rowClick( index ,config.storage[index],GridId);
			return true;	
		};
        $.fn.jGridContentMVC_GridHeadClick = function( GridId, Param ){
           
			var config = $( '#'+GridId ).jGridContentMVC_GridStorage[ GridId ];
			config.HeadClick( GridId, Param );
			return true;	
        };
        
        
		$.fn.jGridContentMVC_GridChkChange = function(e,chkob,GridId,index,cell){
			var config = $( '#'+GridId ).jGridContentMVC_GridStorage[ GridId ];
			var chk = $(chkob).attr('checked');
			config.chkChange( index,cell ,config.storage[index],chk);
			return true;
		};
		
		$.fn.jGridContentMVC_Filter = function( Filters ){
			var config = $( this[0] ).jGridContentMVC_GridStorage[ this[0].id ];
			
			var array = new Array();
			
			for(var i in config.storage){
                var bean = config.storage[i];            
                for(var f in Filters){
                    filter = Filters[f];
                    var op=true;
                    for(var p in filter){
                        op = bean[p] == filter[p];
                        if(!op) 
                            break;
                    }
                    if(op){
                      array.push( bean );  
                      break;
                      }
                }
            }
			
			var configAux = new Object();
			for(var p in config)
			    configAux[p] = config[p];
			configAux.storage = array;
			$( this[0] ).jGridContentMVC_GridDraw( configAux );		
		};
		
//		$.fn.jGridContentMVC_GridCellClick = function(GridId,iRow,iCell){
//			var config = $( '#'+GridId ).jGridContentMVC_GridStorage[ GridId ];
//			config.cellClick( iRow ,iCell,config.storage[iRow],config.columns[iCell].id);
//			return true;
//		}
        $.fn.jGridContentMVC_GridCellClick = function(GridId,iRow,iCell,td,e){       
               
              var config = $( '#'+GridId ).jGridContentMVC_GridStorage[ GridId ];

              if(config.HasTextBoxLastEffect)
              {
                  if(iRow == (config.storage.length-1))
                  {
                      var txt = $('#jGCMVC'+GridId+'_txt'+iRow);
                          txt.fadeIn('slow');
                          txt.focus();
                  }
              }
              config.cellClick( iRow ,iCell,config.storage[iRow],config.columns[iCell].id,GridId,td,e,config);
              return true;
        };
		
		
		$.fn.jGridContentMVC_UpDate = $.fn.jGridContentMVC_UpDate != null ? $.fn.jGridContentMVC_UpDate : function( storage ){		    
		    var config = $( this[0] ).jGridContentMVC_GridStorage[ this[0].id ];
		    /*====*/
            if( config.paginationSettings != null )
                config.paginationSettings.page=1;
            /*====*/                
		    if(storage==null){
		        if(arguments[2]==null)                                  // @example $( divId ).jGridContentMVC_UpDate()
		            $( this[0] ).jGridContentMVC_GridDraw( config );
		        else                                                    // @example $( divId ).jGridContentMVC_UpDate(null,null,cssConfig)
		            $( this[0] ).jGridContentMVC_GridDrawCss( config ,arguments[2]);		        
		        return;    
		        }
		    if(arguments[1]==null)
		        config.storage = storage;		        
		    else if(storage < 0 || storage >= config.storage.length) 
		        return;		        
		    else 
		        config.storage[storage] = arguments[1];
		    if(arguments[2]==null)
		        $( this[0] ).jGridContentMVC_GridDraw( config );
		    else
		        $( this[0] ).jGridContentMVC_GridDrawCss( config ,arguments[2]);
		    
		    return;
		};
		
		$.fn.jGridContentMVC_Filters = $.fn.jGridContentMVC_Filters != null ? $.fn.jGridContentMVC_Filters : function( filters ){          
              var config = $( this[0] ).jGridContentMVC_GridStorage[ this[0].id ];
              config.storage = config.filter ? config.storageBackup : config.storage;
              
              if( filters==null ){
                $( this[0] ).jGridContentMVC_GridDraw( config );                
                return;
              }
              
              var storage = config.storage;
              config.storageBackup = config.storage;
              var filter = new Array();
              for( var i in  storage){
                var bean = storage[i];
                for( var f in filters ){
                    var ftr = filters[f];
                    if(bean[ftr.field]==ftr.value)
                        filter.push( bean );
                    }                    
                }
                config.storage = filter;
                config.filter = true;
                $( this[0] ).jGridContentMVC_GridDraw( config );
              return;
          };
		
		$.fn.jGridContentMVC_Add = $.fn.jGridContentMVC_Add != null ? $.fn.jGridContentMVC_Add : function( bean ){
		    var config = $( this[0] ).jGridContentMVC_GridStorage[ this[0].id ];
		    var addIni = arguments[1] == null ? false : true;
		    if(addIni)
		        config.storage.unshift(bean);
		    else
		        config.storage.push(bean);		        
		    
		    $( this[0] ).jGridContentMVC_GridDraw( config );
		    return;
		};
		$.fn.jGridContentMVC_Delete = $.fn.jGridContentMVC_Delete != null ? $.fn.jGridContentMVC_Delete : function( index ){
		    var config = $( this[0] ).jGridContentMVC_GridStorage[ this[0].id ];
		    if(index < 0 || index >= config.storage.length) 
		        return;
		    config.storage.splice(index,1);
		    $( this[0] ).jGridContentMVC_GridDraw( config );
		    return;
		};
		$.fn.jGridContentMVC_Get = $.fn.jGridContentMVC_Get != null ? $.fn.jGridContentMVC_Get : function( index ){
		    var config = $( this[0] ).jGridContentMVC_GridStorage[ this[0].id ];
		    if(index < 0 || index >= config.storage.length) 
		        return null;		    
		    return config.storage[index];
		};	
		$.fn.jGridContentMVC_GetList = $.fn.jGridContentMVC_GetList != null ? $.fn.jGridContentMVC_GetList : function( ){
		    var config = $( this[0] ).jGridContentMVC_GridStorage[ this[0].id ];		    		    
		    return config.storage;
		};
		
		$.fn.jGridContentMVC_Exists = $.fn.jGridContentMVC_Exists != null ? $.fn.jGridContentMVC_Exists : function( prty , value ){
		    var config = $( this[0] ).jGridContentMVC_GridStorage[ this[0].id ];
		    var exists = false;
		    if(config.storage.length>0)
		        for(var i in config.storage){
		            var bean = config.storage[i];
		            if(bean[prty]==value){
		                exists = true;
		                break;    
		            }		            
		        }		        		    		    
		    return exists;
		};
		
		$.fn.jGridContentMVC_SetLoading = $.fn.jGridContentMVC_SetLoading != null ? $.fn.jGridContentMVC_SetLoading : function( view ){
		    var config = $( this[0] ).jGridContentMVC_GridStorage[ this[0].id ];
		    if(view)
		        $("#"+config.gridId+'_dataTableLoading').show();
		    else
		        $("#"+config.gridId+'_dataTableLoading').hide();
        };
		
		$.fn.jGridContentMVC_SetConfig = $.fn.jGridContentMVC_SetConfig != null ? $.fn.jGridContentMVC_SetConfig : function( config ){
            $( this[0] ).jGridContentMVC_GridStorage[ this[0].id ] = config;
            $( this[0] ).jGridContentMVC_GridDraw( config );	
        };	
        
        $.fn.jGridContentMVC_GetConfig = $.fn.jGridContentMVC_GetConfig != null ? $.fn.jGridContentMVC_GetConfig : function( ){
            return $( this[0] ).jGridContentMVC_GridStorage[ this[0].id ];
        };      
        
        /*****************************************Dynamic CtrlBox Listeners **************************************************/
          
          $.fn.jGridContentMVC_onChangeCheckboxCtrl = $.fn.jGridContentMVC_onChangeCheckboxCtrl != null ? $.fn.jGridContentMVC_onChangeCheckboxCtrl : function( chk ){
              var gridId = $(chk).attr('gridId');
              var columnId = $(chk).attr('columnId');
              var value = $(chk).attr('value');
              var checked = $(chk).attr('checked');
              var row = $(chk).attr('iRow');
              var cell = $(chk).attr('iCell');
              checked = checked==null?false:true;
              
              var config = $( "#"+gridId ).jGridContentMVC_GridStorage[ gridId ];         
              config.storage[row][columnId]=checked;
              
              if(config.cellCheckedValue!=null)           
                config.cellCheckedValue(config.storage[row],checked,row,cell,gridId, config.columns[cell].id);
              return true;
          };
          
          $.fn.jGridContentMVC_HeadChecked = $.fn.jGridContentMVC_HeadChecked != null ? $.fn.jGridContentMVC_HeadChecked : function( chk ){
              var gridId = $(chk).attr('gridId');
              var columnId = $(chk).attr('columnId');
              var value = $(chk).attr('value');
              var checked = $(chk).attr('checked');
              var row = $(chk).attr('iRow');
              var cell = $(chk).attr('iCell');
              checked = checked==null?false:checked;
              
              var config = $( "#"+gridId ).jGridContentMVC_GridStorage[ gridId ];
              
              var chk = '.ChkClass'+(gridId+columnId);
              
              config.columns[cell].HeadValue = checked;            
              $(chk).attr('checked',checked);
                
              for(var row in config.storage)
                  config.storage[row][columnId]=checked;
              
              if(config.HeadCheckBox_onChange != null)
                  config.HeadCheckBox_onChange(columnId,checked,value,gridId);           
          };
          
          $.fn.jGridContentMVC_onChangeComboBoxCtrl = $.fn.jGridContentMVC_onChangeComboBoxCtrl != null ? $.fn.jGridContentMVC_onChangeComboBoxCtrl : function( cmb ){
              var gridId = $(cmb).attr('gridId');
              var columnId = $(cmb).attr('columnId');
              var ptyKey = $(cmb).attr('ptyKey');
              var ptyValue = $(cmb).attr('ptyValue');
              var key = $(cmb).attr('value');             
              var value = cmb.options[cmb.selectedIndex].text;
              
              var row = $(cmb).attr('iRow');
              var cell = $(cmb).attr('iCell');
                        
              var config = $( "#"+gridId ).jGridContentMVC_GridStorage[ gridId ];
              
            config.storage[row][ptyKey]=key;
            config.storage[row][ptyValue]=value;                        
              
              if(config.cellChangeComboBox != null)
                  config.cellChangeComboBox(config.storage[row],{'key':key,'value':value},columnId,gridId);
          };
          
          $.fn.jGridContentMVC_HeadComboBox = $.fn.jGridContentMVC_HeadComboBox != null ? $.fn.jGridContentMVC_HeadComboBox : function( cmb ){
            var gridId = $(cmb).attr('gridId');
              var columnId = $(cmb).attr('columnId');
              var ptyKey = $(cmb).attr('ptyKey');
              var ptyValue = $(cmb).attr('ptyValue');
              var key = $(cmb).attr('value');             
              var cell = $(cmb).attr('iCell');
              
              var value = cmb.options[cmb.selectedIndex].text;
              
              var config = $( "#"+gridId ).jGridContentMVC_GridStorage[ gridId ];
              
              var cmbc = '.CMBClass'+(gridId+columnId);
              
              config.columns[cell].HeadValue = key;           
              
              $(cmbc).each(function(){
                  this.selectedIndex = cmb.selectedIndex;
              });
              
              for(var row in config.storage){
                  config.storage[row][ptyKey]=key;
                  config.storage[row][ptyValue]=value;
                  }
              
              if(config.HeadComboBox_onChange != null)
                  config.HeadComboBox_onChange(columnId,checked,value,gridId);
          };
          
          $.fn.jGridContentMVC_onChangeDatepicker = $.fn.jGridContentMVC_onChangeDatepicker != null ? $.fn.jGridContentMVC_onChangeDatepicker : function( date ){
            var gridId = $(date).attr('gridId');
              var columnId = $(date).attr('columnId');
              var ptyKey = $(date).attr('ptyKey');
              var ptyValue = $(date).attr('ptyValue');
              var value = $(date).attr('value');          
              var row = $(date).attr('iRow');
              var cell = $(date).attr('iCell');
                  
              var config = $( "#"+gridId ).jGridContentMVC_GridStorage[ gridId ];
              
            config.storage[row][ptyKey]=value;
            config.storage[row][ptyValue]=value;
              
            if(config.Datepicker_onChange != null)
                config.Datepicker_onChange(config.storage[row],columnId,checked,value,gridId);
            
          };
          /****TextBox****/
          $.fn.jGridContentMVC_onChangeTextBox = $.fn.jGridContentMVC_onChangeTextBox != null ? $.fn.jGridContentMVC_onChangeTextBox : function( text ){
            var gridId = $(text).attr('gridId');
              var columnId = $(text).attr('columnId');
              var ptyKey = $(text).attr('ptyKey');
              var ptyValue = $(text).attr('ptyValue');
//              var val = BSM_ExpensesNew.FmtMoneda($(text).attr('value'));
//              alert(val);
              //$(text).mask("$ 9,999,999",{completed:function(){alert("Se ha completado la carga: "+this.val());}});

              var value = $(text).attr('value');          
              var row = $(text).attr('iRow');
              var cell = $(text).attr('iCell');
                  
              var config = $( "#"+gridId ).jGridContentMVC_GridStorage[ gridId ];
              
            config.storage[row][ptyKey]=value;
            config.storage[row][ptyValue]=value;
              
            if(config.Datepicker_onChange != null)
                config.Datepicker_onChange(config.storage[row],columnId,checked,value,gridId);
            
          };
          /*****/

          $.fn.jGridContentMVC_HeadDatepicker = $.fn.jGridContentMVC_HeadDatepicker != null ? $.fn.jGridContentMVC_HeadDatepicker : function( date ){
            var gridId = $(date).attr('gridId');
              var columnId = $(date).attr('columnId');
              var ptyKey = $(date).attr('ptyKey');
              var ptyValue = $(date).attr('ptyValue');
              var value = $(date).attr('value');          
              var cell = $(date).attr('iCell');
                  
              var config = $( "#"+gridId ).jGridContentMVC_GridStorage[ gridId ];
              
              var dates = '.jGridContentMVC_DATEPICKER'+(gridId+columnId);
              
              config.columns[cell].HeadValue = value;         
              
              try{
                  $(dates).each(function(){
                      $(this).attr('value',value);
                  });
                  for(var row in config.storage){
                      config.storage[row][ptyKey]=value;
                      config.storage[row][ptyValue]=value;
                      }
              
                  if(config.HeadDatepicker_onChange != null)
                      config.HeadDatepicker_onChange(columnId,checked,value,gridId);
            }catch(e){
                alert(e);
            }
          };
          
          /*******************************************************************************************/
          
          $.fn.jGridContentMVC_Chks = $.fn.jGridContentMVC_Chks != null ? $.fn.jGridContentMVC_Chks : function( ){
              var config = $( this[0] ).jGridContentMVC_GridStorage[ this[0].id ];
              var list = config.storage;
              var ChkBeans = new Array();
              $.each(list,function(i,bean){
                  var obj = '#jGCMVC'+config.gridId+'_chk'+i;
                  var chk = $(obj).is( ':checked' );
                  var ChkBean = {'Index':i,'Bean':bean,'Checked':chk};
                  ChkBeans.push( ChkBean );
              });
              return ChkBeans;
          };
          
          $.fn.jGridContentMVC_SetChks = $.fn.jGridContentMVC_SetChks != null ? $.fn.jGridContentMVC_SetChks : function( checked ){
              var config = $( this[0] ).jGridContentMVC_GridStorage[ this[0].id ];
              var list = config.storage;
              var ChkBeans = new Array();
              
              for(var c = 0; c < config.columns.length; c++){
                    var column = config.columns[c];
                    if(column.HeadTypeContent.toUpperCase() == 'CHECKBOX'){
                        column.HeadValue = checked ? true : false;
                        $('#jGridContentMVC_HeadChecked_'+config.gridId+'_'+c).attr( 'checked',checked );
                        }
              }
              
              $.each(list,function(i,bean){
                  var obj = '#jGCMVC'+config.gridId+'_chk'+i;
                  var chk = $(obj).attr( 'checked',checked );
                  var ChkBean = {'Index':i,'Bean':bean,'Checked':checked};
                  ChkBeans.push( ChkBean );
              });
              return ChkBeans;
          };
		/* Init Grid
		    config Object
		    {   
		        gridId:'DivId',
		        storage:null,
		        rowClick:DivRow_click,
		        columns:[
		                 {width:'50%',id:'prop1'},
		                 {width:'50%',id:'prop2'}
		        ],
		        tblCssClass:'TblClass',
		        tblStyle:'width:100%',
		        cellpadding:'1', 
		        cellspacing:'1',
		        border:'1'
		     }
		*/
		
		var wid = config.gridId;
	  	var wdid = this[0];    	
		config.storage = config.storage == null ? new Array() : config.storage;		
		$( wdid ).jGridContentMVC_GridStorage[ config.gridId ] = config ;		
		$( wdid ).jGridContentMVC_GridDraw( config );	
		
  
  
        /*====*/
		var paginationSettingsDefault = {
			         page:1
			        ,pageRegisters:20
			        ,pageTotal:1
			        ,next:null
			        ,back:null			            
			        };
        
        config.addBefore = config.addBefore == null ? false : config.addBefore;
        
        if( config.paginationSettings != null ){
            for( var pty in paginationSettingsDefault ){
                if(config.paginationSettings[pty] == null)
                    config.paginationSettings[pty] = paginationSettingsDefault[pty];
            }
            config.paginationSettings.page=1;
        }
        /*====*/
  
  
  };  
})(jQuery);