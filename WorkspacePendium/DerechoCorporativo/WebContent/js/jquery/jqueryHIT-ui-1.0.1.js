﻿/*!
 * jqueryHIT JavaScript Library v1.8.3
 * author tbojorqu
 * Date: 2013-04-08
 * jComboBoxSupport.1.0.1
 * jGridContentMVC-1.0.1
 * jGridDAOMVC.1.0.1
 * Util
 */
 
/*!
* Title:  jComboBoxSupport.3MVC 1.0
* Dependencies:  jQuery 1.0 +
* Author:  Tomás Bojórquez Alvarado
*/

(function($){  
  $.fn.jComboBoxMVC = function( config ) {
	  	/*	  	    
	  	    jComboBoxMVC_LoadData(data)
	  	    jComboBoxMVC_GetSelectIndex()
	  	    jComboBoxMVC_GetSelectBean()
	  	    jComboBoxMVC_GetSelectKey()
	  	    jComboBoxMVC_GetSelectValue()
	  	    jComboBoxMVC_GetSelectKeyValue()
	  	    
	  	    jComboBoxMVC_SetSelectIndex(index)
	  	    jComboBoxMVC_SetSelectKey(key)
	  	*/
	  	$.fn.jComboBoxMVC_Storage = $.fn.jComboBoxMVC_Storage == null ? new Array() : $.fn.jComboBoxMVC_Storage;	  	
		
		$.fn.jComboBoxMVC_LoadData = $.fn.jComboBoxMVC_LoadData != null ? $.fn.jComboBoxMVC_LoadData : function(){
		    var config = $( this[0] ).jComboBoxMVC_Storage[ this[0].id ];
			config.data = arguments[0]==null ? config.data : arguments[0];
			var cmb = document.getElementById( config.cmb );
										    	
			cmb.length = null;			
			cmb[0] = new Option(config.defaultValue,"-1");    				
			
			for(var i = 0; i < config.data.length; i++){
			    var index = config.defaultValue == '' || config.defaultValue != null ? i+1 : i;
			    var bean = config.data[i];
				cmb[index] = new Option(bean[config.value],bean[config.key]);
				if(arguments[1]!=null && bean[config.key]==arguments[1])
				    cmb.selectedIndex=index;				    
				}	
		    switch(config.mode){
		        case 'TRAIN':
			        $(config.Lbl).fadeOut('fast',function(){
		                $(this).html( cmb.options[cmb.selectedIndex].text ).fadeIn('slow');
		            });		            
		            break;
		    }	    
		};
		
		$.fn.jComboBoxMVC_OnChange = $.fn.jComboBoxMVC_OnChange != null ? $.fn.jComboBoxMVC_OnChange :  function(){
		    var id = this.id.substring(6,this.id.length );
			var config = $( "#"+id ).jComboBoxMVC_Storage[ id ];
			var bean = $( "#"+id ).jComboBoxMVC_GetSelectBean();
			config.change( bean );
		};
		
		$.fn.jComboBoxMVC_GetSelectIndex = $.fn.jComboBoxMVC_GetSelectIndex != null ? $.fn.jComboBoxMVC_GetSelectIndex :  function(){
			var config = $( this[0] ).jComboBoxMVC_Storage[ this[0].id ];
			var cmb = document.getElementById("cmbMvc"+config.cmbId);//combo.options[combo.selectedIndex].text
			return cmb.selectedIndex;
		};
		
		$.fn.jComboBoxMVC_GetSelectBean = $.fn.jComboBoxMVC_GetSelectBean != null ? $.fn.jComboBoxMVC_GetSelectBean : function( ){		    
		     var config = $( this[0] ).jComboBoxMVC_Storage[ this[0].id ];
		     var cmb = document.getElementById("cmbMvc"+config.cmbId);
		     var bean=null;
		     for(var i = 0; i < config.data.length; i++){
			    bean = config.data[i];
		        if(cmb.value  == bean[config.key])
		            break;
		        else
		            bean=null;
		        }
		    return bean;
		};
		
		$.fn.jComboBoxMVC_GetSelectKey = $.fn.jComboBoxMVC_GetSelectKey != null ? $.fn.jComboBoxMVC_GetSelectKey : function( ){
		    var config = $( this[0] ).jComboBoxMVC_Storage[ this[0].id ];
		    var cmb = document.getElementById("cmbMvc"+config.cmbId);
		    return cmb.value;
		};
		
		$.fn.jComboBoxMVC_GetSelectValue = $.fn.jComboBoxMVC_GetSelectValue != null ? $.fn.jComboBoxMVC_GetSelectValue : function( ){
		    var config = $( this[0] ).jComboBoxMVC_Storage[ this[0].id ];
			var cmb = document.getElementById("cmbMvc"+config.cmbId);
			return cmb.options[cmb.selectedIndex].text;
		    
		};
		$.fn.jComboBoxMVC_GetSelectKeyValue = $.fn.jComboBoxMVC_GetSelectKeyValue != null ? $.fn.jComboBoxMVC_GetSelectKeyValue : function( ){
		    var config = $( this[0] ).jComboBoxMVC_Storage[ this[0].id ];
		    var cmb = document.getElementById("cmbMvc"+config.cmbId);
			return {key:cmb.value ,value: cmb.options[cmb.selectedIndex].text};
		};
		
		$.fn.jComboBoxMVC_SetSelectIndex = $.fn.jComboBoxMVC_SetSelectIndex != null ? $.fn.jComboBoxMVC_SetSelectIndex : function( index ){
		    var config = $( this[0] ).jComboBoxMVC_Storage[ this[0].id ];
		    var cmb = document.getElementById("cmbMvc"+config.cmbId);
			cmb.selectedIndex = index;
			
			switch(config.mode){
		        case 'TRAIN':
			        $(config.Lbl).fadeOut('fast',function(){
		                $(this).html( cmb.options[cmb.selectedIndex].text ).fadeIn('slow');
		            });		            
		            break;
		    }
		};
		
		$.fn.jComboBoxMVC_SetSelectKey = $.fn.jComboBoxMVC_SetSelectKey != null ? $.fn.jComboBoxMVC_SetSelectKey : function( key ){
		    var config = $( this[0] ).jComboBoxMVC_Storage[ this[0].id ];
		    var cmb = document.getElementById("cmbMvc"+config.cmbId);
		    for(var i=0; i<cmb.options.length; i++)
		        if(cmb.options[i].value == key){
			        cmb.selectedIndex = i;
			        break;
			        }
			 switch(config.mode){
		        case 'TRAIN':
			        $(config.Lbl).fadeOut('fast',function(){
		                $(this).html( cmb.options[cmb.selectedIndex].text ).fadeIn('slow');
		            });		            
		            break;
		    }
		};
		
		$.fn.jComboBoxMVC_GetConfig = $.fn.jComboBoxMVC_GetConfig != null ? $.fn.jComboBoxMVC_GetConfig : function( ){
		    return $( this[0] ).jComboBoxMVC_Storage[ this[0].id ];
		};
		
		/* Init jComboBoxMVC
		    config Object
		    {   
		        data:[],		        
		        key:"",
		        value:"",
		        change:function,
		        keySelect:"",
		        defaultValue:"",
		        enabled:bool,
		        cssClass:"",
		        style:""		        
		     }		
		*/
		
		var newHTML = function(type,config){
            var configAux = {attr:null,css:null,cssClass:null,listeners:null,data:[]};
            
            if( config != null ){
                $.each(configAux,function(pty,value){
                    if(config[pty]==null)
                        config[pty]=configAux[pty];
                });
            }else
                config = configAux;
            
            var html = document.createElement(type);
            if (config.attr)$(html).attr(config.attr);
            if (config.css)$(html).css(config.css);
            if (config.cssClass)$(html).addClass(config.cssClass);
            if (config.listeners)$(html).bind(config.listeners);
                    $.each(config.data,function(pty,value){
                        $(html).data(pty,value);
                    });
            return html;
        };
		
		config.mode = config.mode == null ? 'COMBO' : config.mode;
		
		config.cmbId = this[0].id;
		config.data = config.data == null ? new Array() : config.data;		
		$( this[0] ).jComboBoxMVC_Storage[ config.cmbId ] = config ;
		
		var cmb = document.createElement("select");
		
		cmb.id = "cmbMvc"+config.cmbId;
		if(config.cssClass!=null)
		    $(cmb).addClass(config.cssClass);
		if(config.style!=null)
		    for(var sCss in config.style)
		        $(cmb).css( config.style[sCss].style, config.style[sCss].value );
		if(config.change!=null)
		    $(cmb).change( $.fn.jComboBoxMVC_OnChange );
		cmb.disabled = cmb.enabled != null ? !cmb.enabled : false;
		
		switch(config.mode){
		    case 'COMBO': $( this[0] ).html( cmb ); break;
		    case 'TRAIN': 
		        $(cmb).css('display','none');
		        $( this[0] ).html( cmb );
		        
		        
		        var lbl = newHTML("label",{data:{'config':config}});
		        
		        config.Lbl = lbl;
		        if(config.labelClick){
		            $(config.Lbl).click( function(){
		                var config = $(this).data('config');		            		            
		                var bean = $("#"+config.cmbId).jComboBoxMVC_GetSelectBean(  );
		                config.labelClick(bean);}).css('cursor','pointer');
		        }
		        
		        var btnBack = newHTML("a",{data:{'config':config},html:'back',attr:{'data-role':'button','data-icon':'arrow-l','data-iconpos':'notext'}});
		        var btnNext = newHTML("a",{data:{'config':config},html:'next',attr:{'data-role':'button','data-icon':'arrow-r','data-iconpos':'notext'}});
		        
		        $(btnBack).button({icons: {primary: "ui-icon-triangle-1-w"},text: false}).click( function(){
		            var config = $(this).data('config');		            		            
		            var current = $("#"+config.cmbId).jComboBoxMVC_GetSelectIndex( );
		            var index = current-1 < 0 ? cmb.length-1 : current-1;
		            $("#"+config.cmbId).jComboBoxMVC_SetSelectIndex( index );
		            var bean = $("#"+config.cmbId).jComboBoxMVC_GetSelectBean(  );
		            
		            $(config.Lbl).fadeOut('fast',function(){
		                $(this).html( bean[config.value] ).fadeIn('slow');
		            });
		            
                    if(config.change)
		                config.change( bean );		            
		        } );
		        
		        $(btnNext).button({icons: {primary: "ui-icon-triangle-1-e"},text: false}).click( function(){
		            var config = $(this).data('config');
		            var current = $("#"+config.cmbId).jComboBoxMVC_GetSelectIndex( );
		            var index = current+1 >= cmb.length ? 0 : current+1;
		            $("#"+config.cmbId).jComboBoxMVC_SetSelectIndex( index );
		            var bean = $("#"+config.cmbId).jComboBoxMVC_GetSelectBean(  );
		            
		            $(config.Lbl).html( bean[config.value] );
		            if (config.change)
		                config.change(bean);		            
		        } );
		        
		        var tbl = newHTML("table",{'css':{'width':'100%'}});
		        var tr = newHTML("tr");
		        var td1 = newHTML("td",{'css':{'width':'35px'},attr:{'align':'right','valign':'middle'}});
		        var td2 = newHTML("td",{'css':{'width':'95%'},attr:{'align':'center','valign':'middle'}});
		        var td3 = newHTML("td",{'css':{'width':'35px'},attr:{'align':'left','valign':'middle'}});
		        
		        $(td1).append(btnBack);
		        $(td2).append(lbl);
		        $(td3).append(btnNext);
		        
		        $(tr).append(td1).append(td2).append(td3);
		        $(tbl).append( tr );
		        
		        $( this[0] ).append( tbl );		        
		        break;
		}
		
			
		
		config.cmb = cmb.id;
			
		$( this[0] ).jComboBoxMVC_LoadData( config.data,config.keySelect );	
		
  };  
})(jQuery);
		
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
			
            if(config.afterDrawing)
                config.afterDrawing(config);
            
			
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

/*!
* Title:  jGridDAOMVC.1.0.1.js
* Dependencies:  jQuery 1.0 +
* Author:  Tomás Bojórquez Alvarado
* Email:  tomas.bojorquez@kaz-consulting.com
* .jGridDAOMVC(config);
*  {
    @param type         Tipo de lectura 
                        $.jGridDAOMVC_Type.Directly sin configurar columnas
                        $.jGridDAOMVC_Type.Config   configurar columnas
    @param storage      DataRowCollection
    @param table        { id:'Tbl'+id,cellpadding:0,cellspacing:0,border:0,class:'',style:'',rowA:'',rowB:'' }
    @param columns      : [] / [0:{HeadText:'Brand','width':'32%',id:'description','align':'center',cssClass:'RowTextValue',cellValue:'_styleSubray',type:'action'}]
    @param cellDefault  : null / {HeadText:'Brand','width':'32%',id:'description','align':'center',cssClass:'RowTextValue',cellValue:'_styleSubray',type:'action'}
    @param cellDraws    : [] / {1:true,2:true} indices de las columnas a dibujar
    @param defaultMsg   mensaje por defecto en caso de no haber registros si no entonces tomas 'no record to display'
    @param rowClick     
    @param cellClick    function(iRow,iCell,cell,bean,grid,event){}
   }
*   
*    
*   Methods
*   
*   get_Column
*   
*       .jGridDAOMVC('get_Column',columndIndex) []jQueryRresult
*   
*           @param columndIndex int     Indice de la columna a obtener (0-n)
*   
*           @return []jQueryRresult     Conjunto de td de la columna
*
*
*   Ej.
*      var column = $(gridId).jGridDAOMVC('get_Column',0);
*      $(column).css('color','red');
*   
*   headersMetaData:{
       metaData:headers,tooltip:'Value',textHead:'Key',events:{click:function(e){
            var meta = $(this).data('MetaIdentity') 
                meta -> content metadata of type {grid,cell,value,tooltip}
                alert( meta.value );
        
        },events:listeners...}
        },
*/
(function($){  
    $.jGridDAOMVC_Type = { Directly:0,Config:1,ConfigEvenOrOdd:2 };

    
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
       
    //Head Draw
    var jGridDAOMVC_DrawHeadsDirectly = function( row, config ){
        
        var cicd = 0;
        for(var icd in config.cellDraws)
            cicd++;        
        
        var cellDraws = config.cellDraws == null ? row.ItemArray.length : cicd;
        var width = (100 / cellDraws)+'%';
        
        var thead = $.newHTML('thead');
        var tr = $.newHTML('tr');
        var i=0;
        for(var pty in row.Table[0]){            
            if( config.cellDraws != null )
                if( !config.cellDraws[i] ){
                    i++;
                    continue;
                    }
                        
            var th = $.newHTML('th',{attr:[],css:{width:width}});
            $(th).html( pty );
            $(tr).append( th );
            i++;
        }
        
        $(thead).append( tr );        
        return thead;
    };
    
    var jGridDAOMVC_DrawHeadsConfig = function( row, config ){
        
        var cellDefault = {css:{width:0},attr:[],cssClass:[],listeners:[],data:[]};
        config.cellDefault = config.cellDefault == null ? cellDefault : config.cellDefault;
        for(var pty in cellDefault){
            if( config.cellDefault[pty] == null ){
                config.cellDefault[pty] = cellDefault[pty];
            }
        }
              
        var columns = config.columns;
        var wPD = 0;
        
        var cClm = 0;
        $.each(columns,function(i,cln){
            if( cln.css == null )
                wPD += 0;
            else if( isNaN(cln.css.width) )
                wPD += 0;
            else{
                wPD += cln.css.width;
                cClm++;
            } 
        });
        
        var cicd = 0;
        for(var icd in config.cellDraws)
            cicd++;
        
        
        var cellDraws = config.cellDraws == null ? row.ItemArray.length : cicd;
        
        var disp = 100 - wPD;
        var cClm = wPD==0 ? 0 : cClm ;
        var width = (disp / (cellDraws-cClm))+'%';
        
        
        var thead = $.newHTML('thead');
        var tr = $.newHTML('tr');
        
        var i=0;
        for(var pty in row.Table[0]){            
            if( config.cellDraws != null )
                if( !config.cellDraws[i] ){
                    i++;
                    continue;
                    }
                        
            var cellConfig = config.cellDefault;
            
            if( config.columns[i] != null ){
                cellConfig = config.columns[i];
                cellConfig.css.width = cellConfig.css.width == null ? width : cellConfig.css.width+'%';
            }else
                cellConfig.css.width = width;
            
            
            var th = $.newHTML('th',{attr:cellConfig.attr,css:cellConfig.css,cssClass:cellConfig.cssClass,listeners:cellConfig.listeners,data:cellConfig.data});
            $(th).html( pty );
            $(tr).append( th );
            i++;
        }
        
        $(thead).append( tr );        
        return thead;
    };
    
    var jGridDAOMVC_DrawHeadsConfigEvenOrOdd = function( row,config ){
        var cellDefault = {cssHead:{width:0},attrHead:[],cssClassHead:[],listeners:[],data:[]};
        config.cellDefault = config.cellDefault == null ? cellDefault : config.cellDefault;
        for(var pty in cellDefault){
            if( config.cellDefault[pty] == null ){
                config.cellDefault[pty] = cellDefault[pty];
            }
        }
                
        var columns = config.columns;
        var wPD = 0;
        
        var cClm = 0;
        $.each(columns,function(i,cln){
                if( cln.cssHead == null )
                    wPD += 0;
                else if( isNaN(cln.cssHead.width) )
                    wPD += 0;
                else{
                    wPD += cln.cssHead.width;
                    cClm++;
                }   
                    
        });
        
        var cicd = 0;
        for(var icd in config.cellDraws)
            cicd++;
        
        
        var cellDraws = config.cellDraws == null ? config.storage.tables.length / 2 : cicd;
        
        var disp = 100 - wPD;
        var cClm = wPD==0 ? 0 : cClm ;
        var width = (disp / (cellDraws-cClm))+'%';
        
                
        var thead = $.newHTML('thead');
        var theadShadow = $.newHTML('thead');
        var tr = $.newHTML('tr',{attr:{align:'center'},cssClass:'Head'});
        var trShadow = $.newHTML('tr',{attr:{align:'center'},cssClass:'Head'});
        
        var even = config.evenInit;
        
        config.totalColumns = 0;
        
        var i=0;
        for(var pty in config.storage.tables){            
            if( config.maxColumns == config.totalColumns )
                break;
                
            if( config.cellDraws != null )
                if( !config.cellDraws[i] ){
                    i++;
                    continue;
                    }
                        
            var cellConfig = config.cellDefault;
            
            if( config.columns[i] != null ){            
                cellConfig = config.columns[i];
                cellConfig.draw = cellConfig.draw == null ? true : cellConfig.draw;
                if(!cellConfig.draw){
                    i++;
                    continue;
                }
                cellConfig.cssHead.width = cellConfig.cssHead.width == null ? width : cellConfig.cssHead.width+'%';
                pty = cellConfig.textHead == null ? pty : cellConfig.textHead;                                
            }else{
                if( even != i){
                    i++;
                    continue;
                    }
                even += 2;     
                cellConfig.cssHead.width = width;
            }
            
            if( config.headersMetaData != null ){
                var metaData = config.headersMetaData.metaData[ config.totalColumns ];
                cellConfig.attrHead = cellConfig.attrHead == null ? new Object() : cellConfig.attrHead;
                cellConfig.attrHead['title'] = metaData[ config.headersMetaData.tooltip ];
                pty = metaData[ config.headersMetaData.textHead ];
                if( config.headersMetaData.cssEval != null )
                   cellConfig.cssHead = config.headersMetaData.cssEval( metaData,cellConfig.cssHead,config );                 
            }
            
            var th = $.newHTML('th',{attr:cellConfig.attrHead,css:cellConfig.cssHead,cssClass:config.cssClassHead,listeners:cellConfig.listeners,data:cellConfig.data});
            var thShadow = $.newHTML('th',{attr:cellConfig.attrHead,css:cellConfig.cssHead,cssClass:config.cssClassHead,listeners:cellConfig.listeners,data:cellConfig.data});
            
            textValue = pty;
            for(var rpl in cellConfig.textHeadReplace)
                textValue = textValue.replace(rpl,cellConfig.textHeadReplace[rpl]);
            
            if( cellConfig.headDynamic == null ){
                $(th).html( textValue );
                if( config.headersMetaData != null)
                    if(config.headersMetaData.events != null){
                    $(th).data( 'MetaIdentity',{'grid':config.grid,'cell':'cellConfig','value':textValue,'tooltip':cellConfig.attrHead['title']} ).css({'cursor':'pointer'});
                    $.each(config.headersMetaData.events,function(event,listener){
                        /** event this
                        *   $(this).data('MetaIdentity') content metadata of type {grid,cell,value,tooltip}
                        */
                        $(th).bind(event,listener);//Meta info content The var $(th).data( 'MetaIdentity' )
                    });
                }
            }else
                $(th).html(cellConfig.headDynamic( textValue,cellConfig ));
            
            $(thShadow).html('&nbsp;');
            
            $(tr).append( th );
            $(trShadow).append( thShadow );
            i++;
            config.totalColumns++;
        }
        
        $(thead).append( tr ); 
        $(theadShadow).append(trShadow);
        
        if( config.scroll != null )
            return [thead,theadShadow];
            
        return thead;
    };
    
    
    //Row draw
    var jGridDAOMVC_DrawRowDirectly = function( iRow,row, config ){
        var tr = $.newHTML( 'tr' );
        
        for(var c in row.ItemArray){
            if( config.cellDraws != null )
                if( !config.cellDraws[c] )
                    continue;
            
            var td = $.newHTML('td');
            $(td).html( row.ItemArray[c] );
            $(tr).append( td );
        }
        
        return tr;
    };
            
    var jGridDAOMVC_DrawRowConfig = function( iRow,row, config ){
        var tr = $.newHTML( 'tr' );
        
        for(var c in row.ItemArray){
            if( config.cellDraws != null )
                if( !config.cellDraws[c] )
                    continue;
            
            var cellConfig = config.columns[c] == null ? config.cellDefault : config.columns[c]; 
            
            var td = jGridDAOMVC_GetCellConfig( iRow, c, row,cellConfig,row.ItemArray[c] );
            
            $(td).html( row.ItemArray[c] );
            $(tr).append( td );
        }
        
        return tr;
    };
    
    var jGridDAOMVC_DrawRowConfigEvenOrOdd = function(iRow, row, config){
        var tr = $.newHTML( 'tr' );
        
        var even = config.evenInit;
        
        
        var drawColumns = 0;
        var i=0;
        for(var pty in row.Table[0]){                        
            if( config.maxColumns == config.totalColumns )
                break;
            
            value = row.ItemArray[i];
            if( config.cellDraws != null )
                if( !config.cellDraws[i] ){
                    i++;
                    continue;
                    }
                        
            var cellConfig = config.cellDefault;
            
            if( config.columns[i] != null ){            
                cellConfig = config.columns[i];
                cellConfig.draw = cellConfig.draw == null ? true : cellConfig.draw;
                if(!cellConfig.draw){
                    i++;
                    continue;
                }
            }else{
                if( even != i){
                    i++;
                    continue;
                    }
                even += 2;     
            }
            
            var td = jGridDAOMVC_GetCellConfig(iRow ,i ,row,cellConfig  ,pty ,value,config,drawColumns);
                                 
            $(tr).append( td );
            i++;
            drawColumns++;
        }
        
        return tr;        
    };
    /***********************************************CELL CONFIG*************************************************************/
    /***********************************************************************************************************************/
    /***********************************************************************************************************************/
    
    $.jGridDAOMVC_CellType = { Text:0,Bullet:1 };
    
    var jGridDAOMVC_GetCellConfig = function( iRow, iCell, row ,cellConfig ,cell ,value,config,iCellDraw ){
        var cellConfigAux = { listeners:new Object() };
        
        var css = new Object();
        for(var pty in cellConfig.css)
            if( pty != 'width' )
                css[pty]=cellConfig.css[pty];
        for(var pty in cellConfigAux)
            if( cellConfig[pty]==null )
                cellConfig[pty] = cellConfigAux[pty];
                
        if(config.cellClick != null && cellConfig.clicked)
            cellConfig.listeners['click'] = jGridDAOMVC_cellClick;
        else if(config.rowClick != null )            
            cellConfig.listeners['click'] = jGridDAOMVC_rowClick;
         
        var data = {'jGridDAOMVC_data':{ iRow:iRow,iCell:iCell,cell:cell,value:value,grid:config.grid }};
        var td = $.newHTML('td',{attr:cellConfig.attr,css:css,cssClass:cellConfig.cssClass,listeners:cellConfig.listeners,data:data});        
        
        $(td).addClass('jGridDAOMVC_'+config.grid.id+'ColumnPty'+iCellDraw);
        
        switch( cellConfig.type ){
            case $.jGridDAOMVC_CellType.Bullet:
                return jGridDAOMVC_CellType_Bullet(td,value,iCell,cellConfig,row,config);
            default:   
                $(td).html( value );
                return td;
        }   
    };
    /* BulletToolTip
    *  @param tooltipEvenPty bool   Tooltip para una propiedad Par/Impar Par columna sig, impar columna anterior
    */
    var jGridDAOMVC_CellType_Bullet = function(td,value,iCell,cellConfig,row,config){
        var attr = new Object();
        if(cellConfig.tooltipEvenPty){//Tooltip para una propiedad Par/Impar
            var tooltip = row[ iCell+( config.evenType=='odd' ? -1 : +1 ) ];
            attr['title']=tooltip;
        }
        val = value >= 0 && value <= 4 ? value : 0;
        attr['src'] = 'images/Paneles/ind'+ val +'.png';
        
        var img = $.newHTML('img',{attr:attr});        
        $(td).append( img );
        
        return td;
    };
    /***********************************************************************************************************************/
    /***********************************************CELL CONFIG*************************************************************/
    /** jGridDAOMVC_rowClick(iRow,iCell,cell,bean,value,grid,event,dataRow)
    *   
    */
    var jGridDAOMVC_rowClick = function(event){
        if( this.tagName == 'TH' )
            return;
        var data = $(this).data( 'jGridDAOMVC_data' );
        var config = $(data.grid).data('jGridDAOMVC');
        var dataRow = $(this.parentElement).data( 'dataRow' );
        var bean = new Object();
        
        var i=0;
        for(var it in config.storage.tables){
            var pty = config.storage.tables[it];
            bean[pty] = dataRow[i];
            i++;
        }
        
        /*for( var i in dataRow.ItemArray )
            bean['column'+i] = dataRow.ItemArray[i];*/
        
        //alert('fila = '+depto+'column: '+data.cell);
        if(config.rowDetail && data.iCell==config.cellRowDetail){
            $('#jGridDAOMVC_'+data.grid.id+data.iRow).toggle('slow',function(){
                var open = !($(this).data('open'));
                if( open )                    
                    config.rowDetailClick( $('#jGridDAOMVC_'+data.grid.id+data.iRow+"Div"),data.iRow,data.iCell,data.cell,bean,data.value,data.grid,event );            
                $(this).data('open',open);
            });
        }
            
        config.rowClick(data.iRow,data.iCell,data.cell,bean,data.value,data.grid,event,dataRow);
    };
    
    var jGridDAOMVC_cellClick = function(event){
        var data = $(this).data( 'jGridDAOMVC_data' );
        var config = $(data.grid).data('jGridDAOMVC');
        var bean = $(this.parentElement).data( 'dataRow' );
        if(config.rowDetail && data.iCell==config.cellRowDetail){
            $('#jGridDAOMVC_'+data.grid.id+data.iRow).toggle('slow',function(){
                var open = !($(this).data('open'));
                if( open )                    
                    config.rowDetailClick( $('#jGridDAOMVC_'+data.grid.id+data.iRow+"Div"),data.iRow,data.iCell,data.cell,bean,data.value,data.grid,event );            
                $(this).data('open',open);
            });
        }
        config.cellClick(data.iRow,data.iCell,data.cell,bean,data.value,data.grid,event);
    };
        
    /***********************************************************************************************************************/
       
    var jGridDAOMVC_paginationToSetting = function(config){
        settings = config.paginationToSetting;
        settings.page = settings.pageInit;
        settings.gridId = config.grid.id;
                                        
        var tbl = $.newHTML('table',{attr:{'width':'100%','id':settings.gridId+'_paginationToSetting'}});
        
        $(tbl).data('settings',settings).data('engaged',false);
        
        var tr = $.newHTML('tr');
                        
        var tdback = $.newHTML('td',{attr:{'align':'right'},css:{'width':'10%','cursor':'pointer'}});
            if($.mobile!=null)
                $( tdback ).data('type','back').data('parent',settings.gridId+'_paginationToSetting').html( '<button class="ui-state-default ui-corner-all" title="back page" data-role="button" data-icon="arrow-l" data-iconpos="notext">back</button>' ).click( jGridDAOMVC_paginationToSetting_btnBackNext_click );                
            else
                $( tdback ).data('type','back').data('parent',settings.gridId+'_paginationToSetting').html( '<button class="ui-state-default ui-corner-all" title="back page" data-icon="arrow-l"><span class="ui-icon ui-icon-triangle-1-w">&#60;</span></button>' ).click( jGridDAOMVC_paginationToSetting_btnBackNext_click );
        
        var tdPagA = $.newHTML('td',{attr:{'align':'center'},css:{'width':'35%'}});
        var tdPagB = $.newHTML('td',{attr:{'align':'center'},css:{'width':'35%'}});
        
        if( settings.gotoPage != null ){
            tdPags = jGridDAOMVC_gotoPageToSetting( tdPagA,tdPagB,settings );
            tdPagA = tdPags.A;
            tdPagB = tdPags.B;
        }
        
        var tdcenter = $.newHTML('td',{attr:{'align':'center'},css:{'width':'10%','cursor':'pointer'}});
            $( tdcenter ).attr('id',config.grid.id+'_pageCounter').html( (settings.pageTotal == 0 ? '0' : settings.pageInit) + ' / ' + settings.pageTotal );
        
        var tdnext = $.newHTML('td',{attr:{'align':'left'},css:{'width':'10%','cursor':'pointer'}});
            if($.mobile!=null)
                $( tdnext ).data('type','next').data('parent',settings.gridId+'_paginationToSetting').html( '<button class="ui-state-default ui-corner-all" title="back page" data-role="button" data-icon="arrow-r" data-iconpos="notext">next</button>' ).click( jGridDAOMVC_paginationToSetting_btnBackNext_click );                
            else
            $( tdnext ).data('type','next').data('parent',settings.gridId+'_paginationToSetting').html( '<button class="ui-state-default ui-corner-all" title="next page" data-icon="arrow-r"><span class="ui-icon ui-icon-triangle-1-e">&#62;</span></button>' ).click(jGridDAOMVC_paginationToSetting_btnBackNext_click);
            
        $(tr).append( tdPagA );
        $(tr).append( tdback );
        $(tr).append( tdcenter );
        $(tr).append( tdnext );
        $(tr).append( tdPagB );
        
        $(tbl).append( tr );
        return tbl;
    }; 
    
    /** gotoPageToSetting
    *   gotoPage:           function(page,pageTotal,config,parent){
            $( parent ).data( "engaged",false ); //end to request
        }
    *   gotoPageData:       [bean,bean]
    *   gotoPagePtyLbl:     bean[gotoPagePtyLbl] | bean.gotoPagePtyLbl
    *   gotoPagePtyPage:    bean[gotoPagePtyPage] | bean.gotoPagePtyPage
    *   gotoPageCssClass:   style items selected
    */
    
    var jGridDAOMVC_gotoPageToSetting = function(tdPagA,tdPagB,settings){
        
        var mid = settings.gotoPageData.length / 2;
        
        $.each( settings.gotoPageData,function(i,bean){
            var label = bean[ settings.gotoPagePtyLbl ];
            var page = bean[ settings.gotoPagePtyPage ];
            
            var item = $.newHTML('a',{attr:{'class':settings.gotoPageCssClass,'ref':'#','title':'page:'+page}});
            $( item ).data('gotoPage',page).data('parent',settings.gridId+'_paginationToSetting').html( label ).click(jGridDAOMVC_paginationToSetting_gotoPage_click);
            
            if( i < mid )
                $( tdPagA ).append( item );
            else
                $( tdPagB ).append( item );
                
        });
        
        return {'A':tdPagA,'B':tdPagB};
    };
    
    var jGridDAOMVC_paginationToSetting_gotoPage_click = function(){
        var gotoPage = parseInt($(this).data( 'gotoPage' ));
        var parent = "#"+$(this).data('parent');
        var settings = $(parent).data('settings');        
                                                
        if( $( parent ).data( "engaged" ) )
            return;
            
        if(settings.pageTotal == 0)
            return;
        
        
        var page = settings.page;
        var pageTotal = settings.pageTotal;   
        var gridId = "#"+settings.gridId;             
        var config = $( gridId ).data( 'jGridDAOMVC');                
        
        try{
            if( gotoPage < 0 || gotoPage > settings.pageTotal )
                throw "none page";
            page = gotoPage;
            settings.gotoPage( page,pageTotal,config,parent );
        }catch(e){
            page = settings.page;
        }
        
        $( gridId+'_pageCounter' ).html( page + ' / ' + pageTotal );
        settings.page = page;
        $(parent).data('settings',settings);
    };
    
    var jGridDAOMVC_paginationToSetting_btnBackNext_click = function(){        
        var parent = "#"+$(this).data('parent');
        var settings = $(parent).data('settings');        
                                                
        if( $( parent ).data( "engaged" ) )
            return;
            
        if(settings.pageTotal == 0)
            return;                          
                
        var type = $(this).data('type');
        var page = settings.page;
        var pageTotal = settings.pageTotal;   
        var gridId = "#"+settings.gridId;             
        var config = $( gridId ).data( 'jGridDAOMVC');                
        
        try{
            switch(type){
                case 'next':
                    page = page < pageTotal ? page+1:1;
                    settings.next( page,pageTotal,config,parent );
                    break;
                case 'back':
                    page = page == 1 ? pageTotal:page-1;                
                    settings.back( page,pageTotal,config,parent );
                    break;
            }
        }catch(e){
            page = settings.page;
        }
        
        $( gridId+'_pageCounter' ).html( page + ' / ' + pageTotal );
        settings.page = page;
        $(parent).data('settings',settings);
    };
    
    /*******************************************************************************/
    /*                              RowDraw Options                                */ 
    /*                                                                             */
    /*                                                                             */
    /*******************************************************************************/
    /*******************************************************************************/
    
    var jGridDAOMVC_rowDrawGrouper = function(config){
        var tbody = $.newHTML('tbody');
        
        var showLevels = $(config.grid).data( 'showLevels' );
            showLevels = showLevels == null ? [] : showLevels;
        $(config.grid).data( 'showLevels',showLevels );
        
        var level0 = 1;
        var levelCount = 1;
        
        for(var iRow=0;iRow<config.storage.rows.length;iRow++){
            var row = config.storage.rows[iRow];
            var tr = null;
                    
            switch(config.type){                        
                        case $.jGridDAOMVC_Type.ConfigEvenOrOdd:
                            tr = jGridDAOMVC_rowDrawGrouperEvenOrOdd( iRow,row,config );
                            break;
            }
            $(tr).data('dataRow',row);
            
            var metaRow = $(tr).data( 'metaRow' );
            
            
            var parentClass = 'jGridDAOMVC_'+config.grid.id+'_'+metaRow.parent;
            var rowDrawGrouperId = 'jGridDAOMVC_'+config.grid.id+'_'+metaRow.id;
            
            $(tr).attr( {'id':rowDrawGrouperId,'rowDrawGrouperId':rowDrawGrouperId,'gridParentId':config.grid.id,'rowDrawGrouperId_Parent':parentClass} );
            $(tr).addClass( parentClass  );
            
            if( metaRow.level == 0 ){            
                level0++;
                levelCount++;   
                $(tr).addClass( (level0%2)==0 ? config.table.rowA : config.table.rowB  );                                
            }else{
                $(tr).addClass( (levelCount%2)==0 ? config.table.rowA : config.table.rowB  );
                $(tr).addClass( 'rowDrawGrouperId_'+config.grid.id );  
                var show = showLevels[parentClass] == null ? false : showLevels[parentClass];
                if(!show)
                    $(tr).css('display','none');
                levelCount++;                                       
            }                            
            
            
            $(tr).click( function(event){
                var show = $(event.target).attr( 'showGroup' );
                if( !show )
                    return;
                
                var id = $(this).attr( 'rowDrawGrouperId' );
                var grid = "#"+$(this).attr( 'gridParentId' );
                var metaRow = $(this).data( 'metaRow' );
                
                var showLevels = $(grid).data( 'showLevels' );
                var show = showLevels[id] == null ? true : !showLevels[id];
                
                if( show )
                    $("."+id).show();
                else{
                    var hideDownParent = function( parent ){
                        var downParent = $("."+parent);                                      
                        $.each(downParent,function(i,item){
                            hideDownParent( $(item).attr('rowDrawGrouperId') );
                            $(item).hide();
                            });
                        return;                        
                        };
                        
                    hideDownParent( id );
                };
                
                showLevels[ id ] = show;
                
                $(grid).data( 'showLevels',showLevels );                
                
            });
            
            $(tbody).append( tr );
            }
        return tbody;
    };
    
    var jGridDAOMVC_rowDrawGrouperEvenOrOdd = function(iRow, row, config){
        var tr = $.newHTML( 'tr' );
        
        var even = config.evenInit;
        
        var metaRow = {'id':0,'level':0,'parent':0};
        
        var drawColumns = 0;
        var i=0;
        for(var pty in config.storage.tables){                        
            if( config.maxColumns == config.totalColumns )
                break;
            
            value = row[i];
                        
            switch( i ){
                case 0: metaRow.id = value; i++; continue;
                case 1: metaRow.level = value; i++; continue;
                case 2: metaRow.parent = value; i++; continue;                   
            }
            
            if( config.cellDraws != null )
                if( !config.cellDraws[i] ){
                    i++;
                    continue;
                    }
                        
            var cellConfig = config.cellDefault;
            
            if( config.columns[i] != null ){            
                cellConfig = config.columns[i];
                cellConfig.draw = cellConfig.draw == null ? true : cellConfig.draw;
                if(!cellConfig.draw){
                    i++;
                    continue;
                }
            }else{
                if( even != i){
                    i++;
                    continue;
                    }
                even += 2;     
            }
            
            var td = jGridDAOMVC_GetCellConfig(iRow ,i ,row,cellConfig  ,config.storage.tables[pty] ,value,config,drawColumns);
            $(td).addClass( 'jGridDAOMVC_rowDrawGrouper_Level'+metaRow.level );
                                 
            $(tr).append( td );
            i++;
            drawColumns++;
        }
        
        $(tr).data( 'metaRow',metaRow );
        
        return tr;        
    };
    
    var jGridDAOMVC_rowDrawSimple = function(config){
        var tbody = $.newHTML('tbody');
        
        for(var iRow=0;iRow<config.storage.length;iRow++){
            var row = config.storage[iRow];
            var tr = null;
                    
            switch(config.type){
                        case $.jGridDAOMVC_Type.Directly:
                            tr = jGridDAOMVC_DrawRowDirectly( iRow,row,config );
                            break;
                        case $.jGridDAOMVC_Type.Config:
                            tr = jGridDAOMVC_DrawRowConfig( iRow,row,config );
                            break;
                        case $.jGridDAOMVC_Type.ConfigEvenOrOdd:
                            tr = jGridDAOMVC_DrawRowConfigEvenOrOdd( iRow,row,config );
                            break;
            }
            $(tr).data('dataRow',row);//.css('height','3px');
            $(tr).addClass( (iRow%2)==0 ? config.table.rowA : config.table.rowB  );
        
            $(tbody).append( tr );
                    
            if(config.rowDetail){
                        //[total columns] id jGridDAOMVC_ + gridId + iRow  
                        var idRowDetail = 'jGridDAOMVC_'+config.grid.id+iRow;
                        var trAux = $.newHTML('tr',{attr:{'id':idRowDetail},css:{'display':'none'},data:{'open':false}});
                        var td = $.newHTML('td',{attr:{'colspan':config.totalColumns}});
                        var div = $.newHTML('div',{attr:{id:idRowDetail+"Div"}});
                        $(td).append(div);
                        $(trAux).append(td);
                        $(tbody).append( trAux );                    
                }
            }
        return tbody;
    };
    
    var TypeRowDraw = { Simple:1, Grouper:2 };
    
    var jGridDAOMVC_rowDrawManager = function( config ){        
        switch( config.typeRowDraw ){
            case TypeRowDraw.Grouper:                
                /**
                    Dibujar registros de la forma 
                        id Level parent
                        [
                            {id:1, Level:0, parent:0},
                            {id:2, Level:1, parent:1},
                            {id:3, Level:2, parent:2}                            
                        ]
                */
                return jGridDAOMVC_rowDrawGrouper( config );
            default:
                /**
                    Dibujar registros de forma normal
                */
                return jGridDAOMVC_rowDrawSimple( config );
        }
    };
    
    var DrawType = { All:1,Update:2 };
    
    
    var jGridDAOMVC_Draw = function( config ){
        var drawType = arguments[1]==null ? DrawType.All :arguments[1];
        
        if( config.storage.length == 0 ){
            $( grid ).html( config.defaultMsg );
            return;
        }
        
        //crear y configurar tabla primaria
            var tbl = $.newHTML('table',{attr:config.table.attr,css:config.table.css,cssClass:config.table.cssClass});
            
            var configTableShadow = new Object();
            for(var pty in config.table.attr){
                if(pty == 'id')
                    continue;
                configTableShadow[pty]=config.table.attr[pty];
            }
            var tblShadow = $.newHTML('table',{attr:configTableShadow,css:config.table.css,cssClass:config.table.cssClass});
        
        //Dibujar cabeceras        
            var thead = null;
            switch(config.type){
                case $.jGridDAOMVC_Type.Directly:
                    thead = jGridDAOMVC_DrawHeadsDirectly( config.storage[0],config );
                    break;
                case $.jGridDAOMVC_Type.Config:
                    thead = jGridDAOMVC_DrawHeadsConfig( config.storage[0],config );
                    break;
                case $.jGridDAOMVC_Type.ConfigEvenOrOdd:
                    thead = jGridDAOMVC_DrawHeadsConfigEvenOrOdd( config.storage.rows[0],config );
                    break;
            }
        if(!config.head)
            $(thead).css('display','none');
            
        if( config.scroll != null ){    
            $(tbl).append( thead[1] );            
            $(tblShadow).append( thead[0] );
        }else
            $(tbl).append( thead );
        
        //Dibujar registros
         tbody = jGridDAOMVC_rowDrawManager( config );
        
        $(tbl).append( tbody );
         
        var addLoadDiv = function(div){
            var divLoading = $.newHTML('div',{ attr:{id:config.grid.id+'_dataTableLoading',title:'please wait, loading...'}, css:{'width':'100%','height': '100%','display':'none','position':'absolute','top':'1px'},cssClass:'ui-widget-overlay' });
            var divLoadingImg = $.newHTML('div',{ css:{'top':'40%','position':'absolute','width':'100%'} });
            $( divLoadingImg ).html( HIT.loadingViewB );
            $( divLoading ).html( divLoadingImg );
            $( div ).append(divLoading);
        };
        
        switch( drawType ){
            case DrawType.All:
                $(config.grid).html( '' );
                if( config.paginationToSetting != null ){
                    pageCtrl = jGridDAOMVC_paginationToSetting( config );
                    $(config.grid).append( pageCtrl );   
                }
                
                var divTbl = $.newHTML('div',{attr:{id:config.grid.id+'_dataTable'},css:{'position':'relative'}});
                
                var tableDivConfig = null;                
                if( config.scroll != null ){
                    tableDivConfig = {css:{'height':config.scroll.height, 'overflow':'auto'}};
                    var headDivShadow = $.newHTML('div',{cssClass:'HeadShadowScroll'});
                    $(headDivShadow).append( tblShadow );
                    $(divTbl).append( headDivShadow );                    
                }
                
                //div con la tabla contenido
                var tableDiv = $.newHTML('div',tableDivConfig);
                $(tableDiv).append( tbl );
                
                $(divTbl).append( tableDiv );
                addLoadDiv( divTbl );
                $(config.grid).append( divTbl );
                if($.mobile!=null)
                    $( config.grid ).trigger( "create" );
                break;
            case DrawType.Update:
                var idDataTable = '#'+config.grid.id+'_dataTable';
                $( idDataTable ).html('');
                
                var tableDivConfig = null;                
                if( config.scroll != null ){
                    tableDivConfig = {css:{'height':config.scroll.height, 'overflow':'auto'}};
                    var headDivShadow = $.newHTML('div',{cssClass:'HeadShadowScroll'});
                    $(headDivShadow).append( tblShadow );
                    $(divTbl).append( headDivShadow );                    
                }
                
                //div con la tabla contenido
                var tableDiv = $.newHTML('div',tableDivConfig);
                $(tableDiv).append( tbl );
                
                $( idDataTable ).append(tableDiv);  
                
                addLoadDiv( idDataTable );              
                
                if($.mobile!=null)
                    $( idDataTable ).trigger( "create" );
                break;
        }
        
        
        
        if( config.sorter )
            $( "#"+config.table.attr.id ).tablesorter({widgets: ['zebra']});        
        
        
    };
        
    var jGridDAOMVC_Config = {        
         type           : $.jGridDAOMVC_Type.Directly
        ,defaultMsg     : 'no record to display'
        ,storage        : []
        ,table          : {  attr:{id:'Tbl',cellpadding:1,cellspacing:1,border:0,width:'100%'}
                            ,css:[]
                            ,cssClass:[]
                            ,rowA:'RowAGrid'
                            ,rowB:'RowBGrid' }
        ,columns        : []
        ,cellDefault     : null
        ,cellDraws       : null //{1:true,2:true}
        ,grid:null
        ,head:true
        ,typeRowDraw    :TypeRowDraw.Simple
        ,scroll         :null //{height:'px',}
    };
        
    $.fn.jGridDAOMVC = function( ) {
        var configAux = jGridDAOMVC_Config;
        var grid = this[0];   
        var config = $( grid ).data( 'jGridDAOMVC' );
        var action = 'INIT';
        try{
            action = config != null ? arguments[0].toUpperCase() : 'INIT';
        }catch(e){
            action = 'INIT';
        }
        
        switch(action){
            case 'INIT':
                config = arguments[0]==null ? new Object() : arguments[0]; 
                for(var pty in configAux)
                    if(config[pty]==null)
                        config[pty] = configAux[pty];
                
                
                for(var pty in configAux.table)
                    if(config.table[pty]==null)
                        config.table[pty] = configAux.table[pty];
                
                
                config.table.attr.id = "Tbl"+grid.id;
                config.grid = grid;
                
                if( config.storage.length == 0 ){
                    $( grid ).html( config.defaultMsg );
                    return;
                }
                
                jGridDAOMVC_Draw( config,DrawType.All );                
                $( grid ).data( 'jGridDAOMVC', config);                
                break;
            case 'LOADPAGE':
                var config = $( this[0] ).data( 'jGridDAOMVC' );
                config.storage = arguments[1].data;      
                config.headersMetaData.metaData = arguments[1].headersMetaData == null ? 
                    config.headersMetaData.metaData : arguments[1].headersMetaData;         
                jGridDAOMVC_Draw( config, DrawType.Update );                
                break;
            case 'GET_INFOPAGE':
                var pageId = '#'+grid.id+'_paginationToSetting';        
                return $(pageId).data('settings');
            case 'GET_COLUMN':
                var iCell = arguments[1];
                var tds = '.jGridDAOMVC_'+grid.id+'ColumnPty'+iCell;
                return $(tds);
            case 'SHOW_ALL':                
                var showLevels = $(config.grid).data( 'showLevels' );                
                
                var items = $('.rowDrawGrouperId_'+config.grid.id);                
                $.each(items,function(i,item){
                    var parentClass = $(item).attr( 'rowDrawGrouperId_Parent' );
                    showLevels[parentClass] = true;                    
                    $(item).show().removeClass( 'RowGridNoting' );
                });
                
                $('.jGridDAOMVC_'+config.grid.id+'_0').show().removeClass( 'RowGridNoting' );
                
                $(config.grid).data( 'showLevels',showLevels );                                       
                               
                break;
            case 'HIDE_ALL':                
                var showLevels = $(config.grid).data( 'showLevels' );                
                
                var items = $('.rowDrawGrouperId_'+config.grid.id);                
                $.each(items,function(i,item){
                    var parentClass = $(item).attr( 'rowDrawGrouperId_Parent' );
                    showLevels[parentClass] = false;                    
                    $(item).hide().removeClass( 'RowGridNoting' );
                });
                $('.jGridDAOMVC_'+config.grid.id+'_0').show().removeClass( 'RowGridNoting' );
                
                $(config.grid).data( 'showLevels',showLevels );  
                break;
            case 'ENGAGED':
                var engaged = arguments[1];
                var parent = '#'+config.grid.id+'_paginationToSetting';
                $( parent ).data( "engaged",engaged );
                
                if( engaged )
                    $( '#'+config.grid.id+'_dataTableLoading' ).show(  );
                else
                    $( '#'+config.grid.id+'_dataTableLoading' ).hide(  );
                    
                break;
            case 'FILTER':
                var filter = arguments[1];                               
                
                $('#'+config.grid.id+' tr').each(function(i,tr){
                    var dataRow = $(tr).data('dataRow');            
                    if(dataRow==null)
                        return;
                        
                    var metaRow = $(tr).data( 'metaRow' );
                    
                    var value = dataRow[filter.column].toUpperCase();
                    if(value.indexOf(filter.value.toUpperCase())>-1){
                        if( metaRow == null ){
                            $(tr).show();
                            
                            return;
                        }
                        
                        var showUpParent = function( tr ){
                            
                            var parent = $("#"+$(tr).attr('rowDrawGrouperId_Parent'));
                            if( parent.length > 0 )                                  
                                showUpParent( parent );
                            
                            $(tr).show();                            
                            return;                        
                        };
                    
                    
                    showUpParent( tr );
                    $(tr).addClass( 'RowGridNoting' );
                        
                    }else{
                        $(tr).hide();
                        $(tr).removeClass( 'RowGridNoting' );                        
                        }
                    
                });
                
                
            
                
                break;
        }
        
    };
})(jQuery);





var Util = new Object();

Util.RefClass=function( id ){
    return "." + id;
};

Util.RefId=function( id ){
    return "#" + id;
};

Util.RefTblSort =function( id ){
    return "#Tbl" + id;
};

Util.showPnl=function( id ){
    $( Util.RefId( id ) ).fadeIn( "slow" );
};

Util.hidePnl=function( id ){
    $( Util.RefId( id ) ).fadeOut( "slow" );
};

Util.tooglePnl =function( pnlShow,pnlHide ){
    Util.pnlShow = pnlShow;
    Util.pnlHide = pnlHide;
    $( Util.RefId( Util.pnlHide ) ).fadeOut( "slow",function(){
        $( Util.RefId( Util.pnlShow ) ).fadeIn( "slow" );
        Util.pnlShow = null;
        Util.pnlHide = null;
    });
};
/** MthLblFmt. Retorna la etiqueta para un mes en determinado idioma
* @param {int}      Mth         Mes
* @param {string}   lg          Lenguaje "SP" español
*/
Util.MthLblFmt = function( Mth , lg ){
    var ListSp = ["Ene","Feb","Mar","Abr","May","Jun","Jul","Ago","Sep","Oct","Nov","Dic"];
    var ListEn = ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];
    
    switch(lg.toUpperCase()){
        case "SP": return ListSp[ Mth-1 ];
        case "EN": return ListEn[ Mth-1 ];
    }     
};

Util.ClassgetId = function(cssStr,cssId){
    return cssId.replace( cssStr ,"" );
};
/** metaRequest. Genera los metadatos de una transaccion
* @param {string}   type        Tipo de dato que se espera de respuesta (json, html, text, xml)
* @param {bool}     cache       Indica si utiliza cache o no (true, false)
* @param {bool}     lock        Indica si la transaccion es sincrona o asincrona
* @param {string}   methot      Indica si los datos del request son POST/GET
* @param {[json]}   loadsignal  Arreglo de señales de carga de la forma {loading:function(){}, loaded:function(){}}
    Default DR DataRequest / BR BusinesRequest
*/
Util.metaRequest = function(){//type['json','html','xml','text'],cache,lock,method,loadsignal[{loading:function(){}, loaded:function(){}}]
    var args = arguments;
    return { type:args[0],cache:args[1],lock:args[2],method:args[3],loadsignal:args[4],TypeRequest:'DR' };//loading, loaded
};

Util.BusinesRequest = function(Action,VDetail){//type['json','html','xml','text'],cache,lock,method,loadsignal[{loading:function(){}, loaded:function(){}}]
    var args = arguments;
    return { type:'json',cache:false,lock:true,method:'post',loadsignal:args[2],Action:Action,TypeRequest:'BR',Detail:VDetail };//loading, loaded
};

Util.ValidateMsg = function( value,type,msg,DivMsg ){
    var validate = true;
    switch(type.toUpperCase()){
        case "INEQUALITY":
            validate = value != arguments[4];
            break;
        case "EQUALITY":
            validate = value == arguments[4];
            break;
        case "NULL":
            validate = value != null ;            
            break;
        case 'TEXT':
            validate = value != null && value != "";
            break;
        case 'BEMSG':
            validate = false;
            break;
        case 'LISTLENGTH':
            validate = value.length == null ? false : value.length > 0 ;
            break;
        case "ISNUMERIC":
            validate = !isNaN(parseFloat(value)) && isFinite(value) ;                                
            break;
        case "DATERANGE":
            //Valida que sea un rango de fechas donde value[0] Limite inferior y value[1] Limite superior
            var Linf = value[0];
            var Lsup = value[1];

     
            validate =  !(Linf.getYear()  >= Lsup.getYear())  ? true :
                        !(Linf.getYear()  == Lsup.getYear()) ? false :
                        !(Linf.getMonth() <= Lsup.getMonth())? false :
                        !(Linf.getMonth() == Lsup.getMonth()) ?  true:
                          Linf.getDate() < Lsup.getDate();      
            break;
    }
    
    var divMsg = DivMsg+"_msg";
    
    if(!validate){
        $(divMsg).html( msg );
        $(DivMsg).fadeIn('slow');
    }else{
        $(divMsg).html( "" );
        $(DivMsg).fadeOut('slow');
    }
    
    var xOut = eval( '('+'{"fx":function(){ $("'+divMsg+'").html( "" ); $("'+DivMsg+'").fadeOut("slow"); }}'+')' );
    
    setTimeout( xOut.fx,10000 );    
    
    return validate;
};

Util.clone = function(o){
	var c = new Object();
	$.each(o,function(i,item){
	    c[i] = item;
	});
	return c;
}; 

//Formato de Miles
Util.addCommas = function(nStr) {
    nStr += '';
    x = nStr.split('.');
    x1 = x[0];
    x2 = x.length > 1 ? '.' + x[1] : '.00';
    var rgx = /(\d+)(\d{3})/;
    while (rgx.test(x1)) {
        x1 = x1.replace(rgx, '$1' + ',' + '$2');
    }
    
    if ( x2.length == 2 )
        x2 += '0';
    
    return x1 + x2;
};

//Formato Porcentaje
Util.addPercent = function(valor) {
    valor = valor.replace('%','');
    
    if (isNaN(valor)) {
        valor = '0';
    }
    
    valor += '%';
    return valor;
};

//Solo captura de números
Util.OnlyNumbers = function (event) {
            
            // Backspace, tab, enter, end, home, left, right
            // We don't support the del key in Opera because del == . == 46.
            var controlKeys = [8, 9, 13, 35, 36, 37, 39];
            // IE doesn't support indexOf
            var isControlKey = controlKeys.join(",").match(new RegExp(event.which));
            // Some browsers just don't raise events for control keys. Easy.
            // e.g. Safari backspace.
            if (!event.which || // Control keys in most browsers. e.g. Firefox tab is 0
                    (49 <= event.which && event.which <= 57) || // Always 1 through 9
                    (48 == event.which && $(this).attr("value")) || // No 0 first digit
                    isControlKey) { // Opera assigns values for control keys.
                return;
            } else {
                event.preventDefault();
            }
        };

//Solo captura de números con decimales para un textbox
Util.OnlyNumbers_Decimal = function (event, txt) {
            
            // Backspace, tab, enter, end, home, left, right
            // We don't support the del key in Opera because del == . == 46.
            var controlKeys = [8, 9, 13, 35, 36, 37, 39];
            // IE doesn't support indexOf
            var isControlKey = controlKeys.join(",").match(new RegExp(event.which));
            // Some browsers just don't raise events for control keys. Easy.
            // e.g. Safari backspace.
                        
            //Se verifica que solo tenga un punto decimal
            var dato = $(txt).val();
            var HasDecimal = dato.split('.');
            HasDecimal = (HasDecimal.length > 1 ? true : false);
            //alert(HasDecimal);

            if (!event.which || // Control keys in most browsers. e.g. Firefox tab is 0
                    (49 <= event.which && event.which <= 57) || // Always 1 through 9
                    (48 == event.which )|| // No 0 first digit
                    (46 == event.which && !HasDecimal) ||
                    isControlKey) { // Opera assigns values for control keys.
                return;
            } else {
                event.preventDefault();
            }
        };

//Devuelve solo numeros de una cadena
Util.getOnlyNumbers = function(dato) {
    
    var numero = dato.split('.');
    var entero = numero[0];
    
    dato = entero.replace(/[^\d]/g,'') * 1;
    
    var decima_l = .00;
    if (numero.length > 1) {
        decima_l = numero[1].replace(/[^\d]/g,'');
        //decima_l = decima_l * 1 / 100;
        //return dato + decima_l;
    }
    return dato + '.' + decima_l;
};

//Redondea numero 
Util.roundNumber = function (num, dec) {
	var result = Math.round(num*Math.pow(10,dec))/Math.pow(10,dec);
	return result;
};

//Actuliza iFrame
Util.updIFrame = function(iFrame,src){
    try{
        $("#"+iFrame).attr('src',src);                     
        document.all(iFrame).src=src;
    }catch(e){
        
    }
};

Util.toType = function (obj) {
  if (typeof obj === "undefined") {
    return "undefined";
  }
  if (obj === null) {
    return "null";
  }
 
  var type = Object.prototype.toString.call(obj).match(/^\[object\s(.*)\]$/)[1] || '';
 
  switch (type) {
      case 'Number':
        if (isNaN(obj))
          return "nan";
        else
          return "number";        
      case 'String':
      case 'Boolean':
      case 'Array':
      case 'Date':
      case 'RegExp':
      case 'Function':
        return type.toUpperCase();
  }
  
  if (typeof obj === "object") {
    return "OBJECT";
  }
  return undefined;
};

Util.isObject = function(obj){
    var type = Util.toType( obj );
  
    switch(type){
      case 'Array':
      case 'Date':
      case 'RegExp':
      case 'Function':
        return true;      
    }
    
    return (typeof obj === "object");
};

Util.FtmDateTime = {ShortDateTime:0,Short:1};

Util.DateFormat = function(date,fmt){
    fmt = fmt == null ? 'short' : fmt;
    var day = date.getDate();
        day = day < 10 ? "0"+day : day;
    var mth = date.getMonth()+1;
        mth = mth < 10 ? "0"+mth : mth;
    var year = date.getFullYear();
    
    var smn = date.getDay() ;
    
    var hr = date.getHours();
        hr = hr < 10 ? "0"+hr : hr;
    var min = date.getMinutes();
        min = min < 10 ? "0"+min : min;
    var sec = date.getSeconds();
        sec = sec < 10 ? "0"+sec : sec;


    switch(fmt){
        case Util.FtmDateTime.ShortDateTime:
            return day+'/'+mth+'/'+year+' '+hr+':'+min;
        case Util.FtmDateTime.Short: 
        default:            
            return  day+'/'+mth+'/'+year;
    }
};

/** changeView
*       desaparace un elemento y muestra otro   
*
*   @param hideElement  id del elemento a ocultar
*   @param showElement  id del elemento a mostrar
*/

Util.changeView = function(hideElement,showElement){
    var hideElement = $(hideElement);
    if( hideElement.length == 0 ){
        $(showElement).show();
        return;
        }
    if( $(hideElement).css('display')=='none'){
        $(showElement).show();
        return;
        }
    
    $( hideElement ).hide(null,function(){
        $(showElement).show();
    });
};

/** togglev
*       desaparace un elemento y muestra otro   
*
*   @param  idElement   id del elemento operar
*   @return bool        true si quedo abierto 
*/

Util.toggle = function(idElement){
    $( idElement ).toggle();
    return !($(idElement).css('display')=='none');
};

/** loading
*       muestra el mensaje de carga a nivel app o div   
*
*   @param app bool               si true loading a nivel aplicación false a nivel div
*   @param div #    optional      id del elemento donde se colocara el msj de cargando
*/
var iconLoading = '<img title="please wait, loading..." alt="please wait, loading..." src="images/component_loading.gif" />';
var iconOk      = '<img title="OK!!" src="images/Icon_ok.png" />';
var iconError   = '<img title="Error!!" src="images/Icon_warning.png" />';
var iconException   = '<img title="Error!!" src="images/Icon_error.png" />';

$(document).ready(function(){
    var engagedDiv = "#AppEngaged";        
    $(engagedDiv).data('engaged',false);
    $(engagedDiv).dialog({
            height: 200,
            width:250,
			modal: true,
			resizable: false,
			autoOpen:false,
			     close: function(event, ui) { 
                    if($("#AppEngaged").data('engaged'))
                       $("#AppEngaged").dialog('open');			        
			     }
			});
});
Util.loading = function( app,div ){
    if( app ){
        var engagedDiv = "#AppEngaged";        
        switch(div){
            
            case 0:
                $(engagedDiv+'_Icon').html('');            
                $(engagedDiv+'_Msg').html('');
                $(engagedDiv).data('engaged',false);
                $(engagedDiv).dialog('close');
                break;
            case 1:
                $(engagedDiv).data('engaged',true);
                var msg = arguments[2]==null?'':arguments[2];
                $(engagedDiv+'_Icon').html(iconLoading);            
                $(engagedDiv+'_Msg').html(msg);          
                $(engagedDiv).dialog('open');
                break;
            case 2:
                var iconOk      = '<img title="OK!!" src="images/ok.png" />';
                var iconError   = '<img title="Error!!" src="images/error.png" />'; 
                $(engagedDiv).data('engaged',true);
                var msg = arguments[2]==null?'':arguments[2];
                var icon = arguments[3]=='OK'? iconOk : iconError;
                $(engagedDiv+'_Icon').html(icon);            
                $(engagedDiv+'_Msg').html(msg);          
                $(engagedDiv).dialog('open');
                break;
            }
            
    }else{
        $(div).html( '<table width="100%"><tr><td align="center"><img title="please wait, loading..." alt="please wait, loading..." src="images/component_loading.gif" /></td></tr></table>' );
        $(div).show();
    }
};

/** newHTML
*       crea un componente html
*
*   @param type     string                  si true loading a nivel aplicación false a nivel div
*   @param config   {json}    optional      {attr:[],css:[],cssClass:[],listeners:[],data:[]}
*/
Util.newHTML = function(type,config){
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
        if( config.html != null )
            $(html).html( config.html );
        return html;
        };

/** showErrorMsg
*       crea un componente html
*
*   @param div   idDOM    Identificador del elemento a mostrar
*   @param msg   string   mensaje a mostrar
*/        
Util.showErrorMsg = function(div,msg){
    $(div).hide();
    var html = '<div style="padding: 0 .7em; " class="ui-state-error ui-corner-all">';
        html+= '<p><span style="float: left; margin-right: .3em;">'+iconError+'</span>';
		html+= '<strong>Please check:</strong> '+msg+'</p>';
		html+= '</div>';
    $(div).html( html ).show();
    
    setTimeout( function(div){
        $(div).html( '' ).hide();        
    },5000,div );
};

/** showInfoMsg
*       crea un componente html
*
*   @param div   idDOM    Identificador del elemento a mostrar
*   @param msg   string   mensaje a mostrar
*   @param post  function ejecutar al ocultar
*/        
Util.showInfoMsg = function(div,msg,after){
    $(div).hide();
    var html = '<div style="margin-top: 20px; padding: 0 .7em; height:65px" class="ui-state-highlight ui-corner-all">';
        html+= '<p><span style="float: left; margin-right: .3em;">'+iconOk+'</span>';
		html+= '<strong>Ok!</strong>  &nbsp;&nbsp;'+msg+'</p>';
		html+= '</div>';
    $(div).html( html ).show();
    $(div).data( 'afterHide',after );
    
    action = eval( '('+'{after:function(args){  $("'+div+'").html( "" ).fadeOut("slow");var after = $("'+div+'").data( "afterHide" );if( after != null)after();}}'+')');
    
    setTimeout( action.after ,5000 );
};

/** getDate
*       devuelve la fecha en un determinado formato
*
*   @param date   Date      Objeto Fecha
*   @param format string    Formato string
*/        
Util.getDate = function(date,format){
    if(date==null)return null;
    switch(format.toUpperCase()){
        case 'YY': 
            date+="";
            return date.substring(2);
    }        
    var dateFmt = "";
    var day = date.getDate();
        day = (day < 10 ? "0" : "") + day;
    var mth = date.getMonth() +1;
        mth = (mth < 10 ? "0" : "") + mth;
    var year= date.getFullYear();
    
    var sepA = "/";
    var sepB = "-";
    
    var SEP = sepB;
    
    switch(format.toUpperCase()){
        case 'SHORT': dateFmt = day+SEP+mth+SEP+year; break;
        case 'SQL': dateFmt = year+SEP+mth+SEP+day; break;
        default: dateFmt = day+SEP+mth+SEP+year; break;
        
    }
    return dateFmt;
};

/** encodeText
*       valida y codifica el valor en texto para poder ser transportado via AJAX
*
*   @param value string    valor a valdiadr y codificar string
*/        
Util.encodeText = function(value){
    if( value == null )return value;
    value = value.replace(/"/g,"'");
    value = value.replace(/\\/g,"/");
    return encodeURIComponent( value );
};

/*!
* Title:  jGridContentMVC 1.0
* Dependencies:  jQuery 1.0 +
* Author:  Tomás Bojórquez Alvarado
* Email:  tomas.bojorquez@kaz-consulting.com
*/

(function ($) {
    $.fn.jGridContentMVC = function (config) {
        //config.effectlbltxt = false;
        $.fn.jGridContentMVC_GridStorage = $.fn.jGridContentMVC_GridStorage == null ? new Array() : $.fn.jGridContentMVC_GridStorage;

        $.fn.jGridContentMVC_Redraw = $.fn.jGridContentMVC_Redraw != null ? $.fn.jGridContentMVC_Redraw : function () {
            var config = $(this[0]).jGridContentMVC_GridStorage[this[0].id];
            if (config.drawIterated == null)
                $(this[0]).jGridContentMVC_GridDraw(config);
            else
                $(this[0]).jGridContentMVC_GridDrawIterated(config);
        }

        var numberFmt = function (numero, formatted) { // v2007-08-06
            var GlobalFormatNumber = $.fn.jGridContentMVC.GlobalFormatNumber;
            decimales = formatted.decimal == null ? GlobalFormatNumber.decimal : formatted.decimal;
            separador_decimal = formatted.separatorDec == null ? GlobalFormatNumber.separatorDec : formatted.separatorDec;
            separador_miles = formatted.separatorMil == null ? GlobalFormatNumber.separatorMil : formatted.separatorMil;
            zeroFmt = formatted.zeroFmt == null ? GlobalFormatNumber.zeroFmt : formatted.zeroFmt;

            if ((!zeroFmt) && numero == 0)
                return numero;

            numero = parseFloat(numero);
            if (isNaN(numero)) {
                return "";
            }

            if (decimales !== undefined) {
                // Redondeamos
                numero = numero.toFixed(decimales);
            }

            // Convertimos el punto en separador_decimal
            numero = numero.toString().replace(".", separador_decimal !== undefined ? separador_decimal : ",");

            if (separador_miles) {
                // Añadimos los separadores de miles
                var miles = new RegExp("(-?[0-9]+)([0-9]{3})");
                while (miles.test(numero)) {
                    numero = numero.replace(miles, "$1" + separador_miles + "$2");
                }
            }

            return numero;
        }

        var dateFmt = function (value, formatted) { // v2007-08-06
            switch (formatted.mode) {
                case 'strSQL':
                    if (value == null || value == 'null') return '';
                    return value.substring(0, 10);
                default:
                    return value;
            }
        }
        /** preformatted: formate los valores sobre la propiedad del bean
        *   number: formatted{type:'number',decimal:número de decimales requerido,separatorDec:separador de decimales,separatorMil:separador de miles}
        default: formatted = {type:'number', decimal:2,separatorDec:'.',separatorMil:','}
        */

        var preformatted = function (formatted, value) {

            try {
                switch (formatted.type) {
                    case 'number':
                        if (value == null)
                            return 0;
                        return numberFmt(value, formatted);
                    case 'date':
                        return dateFmt(value, formatted);
                    default:
                        if (value == null)
                            return value;
                }
            } catch (e) {

            }
            return value;
        }

        $.fn.jGridContentMVC_GridDraw = $.fn.jGridContentMVC_GridDraw != null ? $.fn.jGridContentMVC_GridDraw : function (config) {
            var jGrid_MaxLength = config.storage.length == 0 ? 0 : config.storage.length - 1;
            var datepickerCtrlHeadId = new Array();
            var datepickerCtrlId = new Array();

            //var nameTextLblEffect='';
            config.storage = config.storage == null ? new Array() : config.storage;
            var tblFinRow = "";
            var tbl = '<table';
            tbl += ' id="Tbl' + config.gridId + '"';
            tbl += config.cellpadding == null ? '' : ' cellpadding="' + config.cellpadding + '"';
            tbl += config.cellspacing == null ? '' : ' cellspacing="' + config.cellspacing + '"';
            tbl += config.rules == null ? '' : ' rules="' + config.rules + '"';
            tbl += config.border == null ? '' : ' border="' + config.border + '"';

            tbl += config.tblCssClass != null ? ' class="' + config.tblCssClass + '"' : '';
            tbl += config.tblStyle != null ? ' style="' + config.tblStyle + '"' : '';
            tbl += ' id="GridTbl' + config.gridId + '"';
            tbl += '>';

            if (config.Heads) {
                if (config.Encabezados) {
                    tbl += '<thead><tr';
                    tbl += config.HeadCss == null ? '' : ' class="' + config.HeadCss + '"';
                    tbl += config.HeadAlign == null ? ' align="center"' : ' align="' + config.HeadAlign + '"';
                    tbl += '>'
                    for (var c = 0; c < config.columns.length; c++) {
                        var column = config.columns[c];
                        if (column.colspan != null) {
                            var td = '<th';
                            td += ' colspan="' + column.colspan + '"';
                            td += column.width == null ? '' : ' style="width:' + column.width + '"';
                            td += column.cssHead1Class == null ? '' : ' class="' + column.cssHead1Class + '"';
                            td += column.HeadTitle == null ? '' : ' title="' + column.HeadTitle + '"';
                            td += '>'
                            td += column.Encabezado;
                            td += '</th>';
                            tbl += td;
                        }
                    }
                    tbl += '</tr></thead>';
                }
                tbl += '<thead><tr';
                tbl += config.HeadCss == null ? '' : ' class="' + config.HeadCss + '"';
                tbl += config.HeadAlign == null ? ' align="center"' : ' align="' + config.HeadAlign + '"';
                tbl += '>'
                for (var c = 0; c < config.columns.length; c++) {
                    var column = config.columns[c];
                    if (column.security != null) {
                        if (!column.security(column.actionKey, column, config.storage, config.gridId, config, true))
                            continue;
                    }
                    var td = '<th align="center" ';
                    td += column.width == null ? '' : ' style="width:' + column.width + '"';
                    td += column.HeadTitle == null ? '' : ' title="' + column.HeadTitle + '"';
                    td += '>';
                    column.HeadTypeContent = column.HeadTypeContent == null ? 'DEFAULT' : column.HeadTypeContent;
                    switch (column.HeadTypeContent.toUpperCase()) {
                        /** checkbox controller 
                        * HeadTypeContent:'CHECKBOX'                            Define como Cabecera con controlador checkbox
                        * HeadCheckBox_onChange(columnId,checked,value,gridId)  Delegado para el evento onChange de cualquier encabezado de tipo CHECKBOX
                        * HeadText                                              Contiene el valor para "value"
                        */ 
                        case 'CHECKBOX':
                            column.HeadValue = column.HeadValue == null ? false : column.HeadValue;
                            var checked = column.HeadValue ? 'checked="checked"' : '';
                            var onChange = '$(\'#' + config.gridId + '\').jGridContentMVC_HeadChecked(this)';
                            td += '<label>' + column.HeadText + '</label><br/><input id="jGridContentMVC_HeadChecked_' + config.gridId + '_' + c + '" iCell="' + c + '" gridId="' + config.gridId + '" columnId="' + column.id + '" type="checkbox" value="' + column.HeadText + '" onClick="' + onChange + '" ' + checked + '/>';
                            break;
                        case 'COMBOBOX':
                            column.HeadValue = column.HeadValue == null ? '-1' : column.HeadValue;

                            var onChange = '$(\'#' + config.gridId + '\').jGridContentMVC_HeadComboBox(this)';
                            var workArgs = 'gridId="' + config.gridId + '" columnId="' + column.id + '" ptyValue="' + column.valueConfig.ptyValue + '" ptyKey="' + column.valueConfig.ptyKey + '" iCell="' + c + '"';
                            var options = '';
                            for (var iCmbItem in column.valueConfig.list) {
                                var cmbItem = column.valueConfig.list[iCmbItem];
                                var select = cmbItem.key == column.HeadValue ? 'selected="selected"' : '';
                                options += '<option value="' + cmbItem.key + '" ' + select + '>' + cmbItem.value + '</option>';
                            }
                            td += '<label>' + column.HeadText + '</label><br/>';
                            td += '<select ' + workArgs + ' onChange="' + onChange + '" style="' + column.valueConfig.style + '">' + options + '</select>';
                            break;
                        case 'DATEPICKER':
                            column.HeadValue = column.HeadValue == null ? '' : column.HeadValue;
                            var onChange = '$(\'#' + config.gridId + '\').jGridContentMVC_HeadDATEPICKER(this)';
                            var workArgs = 'gridId="' + config.gridId + '" columnId="' + column.id + '" ptyValue="' + column.valueConfig.ptyValue + '" ptyKey="' + column.valueConfig.ptyKey + '" iCell="' + c + '"';
                            var datepickerId = 'jGridContentMVC_HeadDATEPICKER' + (config.gridId + column.id);
                            td += '<p>' + column.HeadText + '<input id="' + datepickerId + '" type="text" value="' + column.HeadValue + '" size="12" maxlength="12" ' + workArgs + ' style="' + column.valueConfig.style + '"/></p>'
                            datepickerCtrlHeadId.push(datepickerId);
                            break;
                        default:
                            td += column.HeadText;
                            break;
                    }
                    td += '</th>';
                    tbl += td;
                }
                tbl += '</tr></thead><tbody>';

            }
            var flagTRInit = false;
            var flagTRFinal = false;
            for (var i = 0; i < config.storage.length; i++) {
                var bean = config.storage[i];
                flagTRInit = i == 0;
                flagTRFinal = i == (config.storage.length - 1);
                var tr = '<tr';
                tr += config.rowClick == null || (flagTRFinal && config.rowFinal != null) || (flagTRInit && config.rowInitNonClick != null) ? '' : ' onClick="$(\'#' + config.gridId + '\').jGridContentMVC_GridRowClick( \'' + config.gridId + '\',\'' + i + '\' )"';
                var inRowDetail = false;
                if (config.RowDetail != null) {//(config {..., RowDetail:{condition:[{value:'X',idPty:'',idGrid=''},...]
                    for (icond in config.RowDetail.condition) {
                        var condition = config.RowDetail.condition[icond];
                        if (bean[condition.idPty] != condition.value) {
                            var idRowDetail = config.gridId + 'RowDetail' + i;
                            var Namegrid = condition.idGrid + i;
                            var eventView = ' onclick="$(\'#' + config.gridId + '\').jGridContentMVC_GridRowDblClickRowDetail( \'' + config.gridId + '\',\'' + idRowDetail + '\',\'' + condition.IdClass + '\',\'' + bean[condition.idPty] + '\',\'' + Namegrid + '\' )"';
                            tr += eventView;
                            inRowDetail = true;
                            break;
                        } condition.idGrid
                    }
                }
                if (config.RowID != null) {//(config {..., RowDetail:{condition:[{value:'X',idPty:'',idGrid=''},...]
                    for (icond in config.RowID.condition) {
                        var condition = config.RowID.condition[icond];
                        if (bean[condition.idPty] == condition.value) {
                            var idRowDetail = config.gridId + 'RowDetail' + i;
                            tr += ' id="' + idRowDetail + '" ';
                            tr += 'style=""';
                            break;
                        }
                    }
                }
                tr += config.rowClick != null || inRowDetail ? ' style="cursor:pointer"' : '';
                tr += config.rowAlign == null ? '' : ' align="' + config.rowAlign + '"';

                if (config.rowFinal == null) {
                    tr += config.rowCssA == null ? '' : ' class="' + (((i + 1) % 2) == 0 ? config.rowCssA : config.rowCssB) + '"';
                } else {
                    if (i == (config.storage.length - 1)) {
                        tr += config.rowCssA == null ? ' class="' + config.rowFinal + '"' : 'class="' + (((i + 1) % 2) == 0 ? config.rowCssA : config.rowCssB) + ' ' + config.rowFinal + '"';
                        flagTRFinal = true;
                    } else
                        tr += config.rowCssA == null ? '' : ' class="' + (((i + 1) % 2) == 0 ? config.rowCssA : config.rowCssB) + '"';
                }
                tr += '>';

                for (var c = 0; c < config.columns.length; c++) {
                    var column = config.columns[c];
                    if (column.security != null) {
                        if (!column.security(column.actionKey, column, bean, config.gridId, config, false))
                            continue;
                    }
                    var td = '<td';

                    if (column.cellValue == 'SelectionClass')
                        td += ' id="td' + config.gridId + '_select' + c + '"';

                    td += column.width == null ? '' : ' style="width:' + column.width;
                    td += column.indent == null ? '"' : ';text-indent:' + column.indent + '"';
                    td += column.align == null ? '' : ' align="' + column.align + '"';
                    td += column.cssClass == null ? '' : ' class="' + column.cssClass + '"';
                    td += column.type == null ? '' : column.type == 'action' ? ' onClick="$(\'#' + config.gridId + '\').jGridContentMVC_GridCellClick( \'' + config.gridId + '\',\'' + i + '\',\'' + c + '\',this,event)" style="cursor:pointer"' : '';
                    td += column.titleBean == null ? '' : ' title="' + bean[column.titleBean] + '"';
                    //td += column.idtd == null ? '':' id="'+c+'"td';


                    if (config.RowDetail == null) {
                        td += column.cssClass == null ? '' : ' class="' + column.cssClass + '"';
                    }
                    else {
                        if (inRowDetail) {
                            td += column.cssClass == null ? '' : ' class="' + column.cssClass + '"';
                        }
                    }

                    if (config.RowID != null) {
                        var condition = config.RowID.condition[icond];
                        if (bean[condition.idPty] == condition.value) {
                            var idRowDetail = 'td' + config.gridId + 'RowDetail' + i + c;
                            td += ' id="' + idRowDetail + '" ';
                            td += config.rowCssA == null ? '' : 'style="background-color:' + (((i + 1) % 2) == 0 ? '#efefff;' : 'White') + '"';
                        }
                    }
                    td += column.title == null ? '' : ' title="' + column.title + '"';
                    td += column.valign == null ? (config.valign == null ? '' : ' valign="' + config.valign + '"') : ' valign="' + column.valign + '"';

                    td += column.type == null ? '' : column.type == 'action' && !(flagTRFinal && config.rowFinal != null) && !(flagTRInit && config.rowInitNonClick != null) ? ' onClick="$(\'#' + config.gridId + '\').jGridContentMVC_GridCellClick( \'' + config.gridId + '\',\'' + i + '\',\'' + c + '\')" style="cursor:pointer"' : '';
                    td += '><div style="position:relative">';

                    tdAux = td;
                    td = "";

                    var centerRight = false;
                    //column.preformatted = {type:'number',...}
                    if (column.preformatted) {
                        bean[column.id] = preformatted(column.preformatted, bean[column.id]);
                        centerRight = column.preformatted.type == 'number' ? true : false;
                    }

                    if (column.cellValue == null) {
                        if (flagTRFinal) {
                            if (config.HasTextBoxLastEffect) {
                                if (c == config.HasTextBoxLastEffectColumn) {
                                    /*nameTextLblEffect='jGCMVC'+config.gridId+'_lbl'+i;
                                    td+='<label style="cursor:pointer;" id="'+nameTextLblEffect+'" class="tablesorterlabel">'+ bean[column.id] + '</label>';
                                    */
                                    config.nameTextBoxEffect = 'jGCMVC' + config.gridId + '_txt' + i;
                                    td += '<input id="' + config.nameTextBoxEffect + '" type="textbox" style="display:inline;width:100%;" onBlur="$(\'#' + config.gridId + '\').jGridContentMVC_GridTextBoxLostFocusValue( \'' + config.gridId + '\',\'' + config.nameTextBoxEffect + '\',\'' + i + '\')" />';
                                } else {
                                    td += bean[column.id];
                                }
                            }
                            else {

                                if (column.id.constructor.toString().indexOf("Array") == -1)
                                    if (column.height != null) {
                                        if (bean[column.id].length > column.height)
                                            td += bean[column.id].substring(0, column.height) + '...';
                                        else
                                            td += bean[column.id];
                                    }
                                    else
                                        td += bean[column.id];
                                else {
                                    for (var iid = 0; iid < column.id.length; iid++) {
                                        td += bean[column.id[iid]];
                                        td += column.id.length == 1 ? '' : iid == (column.id.length - 1) ? '' : column.betweenValue == null ? " " : column.betweenValue;
                                    }
                                }
                            }
                        } else {
                            if (column.id.constructor.toString().indexOf("Array") == -1) {
                                if (column.height != null) {
                                    if (bean[column.id].length > column.height)
                                        td += bean[column.id].substring(0, column.height) + '...';
                                    else
                                        td += bean[column.id];
                                }
                                else
                                    td += bean[column.id];
                            }
                            else
                                for (var iid = 0; iid < column.id.length; iid++) {
                                    td += bean[column.id[iid]];
                                    td += column.id.length == 1 ? '' : iid == (column.id.length - 1) ? '' : column.betweenValue == null ? " " : column.betweenValue;
                                }
                        }
                    } else if (column.cellValue == '_chk2') {

                        var namecheck = 'jGCMVC' + config.gridId + '_chks' + i;
                        var disabledChek = column.disabled;

                        if (bean[column.id] == 2) {
                            td += '<input ' + disabledChek + ' id="' + namecheck + '" type="checkbox" checked onClick="$(\'#' + config.gridId + '\').jGridContentMVC_GridChecked( \'' + config.gridId + '\',\'' + namecheck + '\',\'' + i + '\',\'' + c + '\',\'' + column.id + '\')" />';
                        } else {
                            td += '<input ' + disabledChek + ' id="' + namecheck + '" type="checkbox" onClick="$(\'#' + config.gridId + '\').jGridContentMVC_GridChecked( \'' + config.gridId + '\',\'' + namecheck + '\',\'' + i + '\',\'' + c + '\',\'' + column.id + '\')" />';
                        }

                    } else if (column.cellValue == '_chk3') {
                        if (bean[column.id] == 2) {
                            td += "SI";
                        } else {
                            td += "NO";
                        }
                    } else if (column.cellValue == '_chk') {
                        var namecheck = 'jGCMVC' + config.gridId + '_chk' + i;
                        if (bean[column.id] == 1) {
                            td += '<input id="' + namecheck + '" type="checkbox" checked onClick="$(\'#' + config.gridId + '\').jGridContentMVC_GridChecked( \'' + config.gridId + '\',\'' + namecheck + '\',\'' + i + '\',\'' + c + '\',\'' + column.id + '\')" />';
                        } else {
                            td += '<input id="' + namecheck + '" type="checkbox" onClick="$(\'#' + config.gridId + '\').jGridContentMVC_GridChecked( \'' + config.gridId + '\',\'' + namecheck + '\',\'' + i + '\',\'' + c + '\',\'' + column.id + '\')" />';
                        }

                    }
                    //*********Range********


                    //*******************
                    else if (column.cellValue.toUpperCase() == 'CHECKBOX') {
                        /**
                        * @param    cellValue 'CHECKBOX'
                        * @eventListener cellCheckedValue   Delegado de la función change por checked
                        */
                        var onChange = '$(\'#' + config.gridId + '\').jGridContentMVC_onChangeCheckboxCtrl( this )';
                        if (bean[column.id] == null)
                            bean[column.id] = false;

                        var checked = bean[column.id] ? 'checked="checked"' : '';
                        var listener_onchange = $.browser.msie ? 'onclick="' + onChange + '"' : 'onchange="' + onChange + '"';
                        var disabled = column.enabled == null ? '' : column.enabled ? '' : ' disabled="column.enabled" ';
                        td += '<input id="jGCMVC' + config.gridId + '_chk' + i + '" iRow="' + i + '" iCell="' + c + '" gridId="' + config.gridId + '" columnId="' + column.id + '" class="ChkClass' + (config.gridId + column.id) + '" type="checkbox" value="' + column.id + '" ' + listener_onchange + ' ' + checked + ' ' + disabled + '/>';
                    } else if (column.cellValue.toUpperCase() == 'COMBOBOX') {
                        /**
                        * @param    cellValue 'COMBOBOX'
                        * @eventListener cellChangeCombobox   Delegado de la función change por combobox
                        */
                        var onChange = '$(\'#' + config.gridId + '\').jGridContentMVC_onChangeComboBoxCtrl( this )';
                        var workArgs = ' iRow="' + i + '" iCell="' + c + '" gridId="' + config.gridId + '" columnId="' + column.id + '" ptyValue="' + column.valueConfig.ptyValue + '" ptyKey="' + column.valueConfig.ptyKey + '"';
                        var cssClass = ' class="CMBClass' + (config.gridId + column.id) + '" ';
                        var notReplace = false;
                        for (var iNotReplace in column.valueConfig.notReplace) {
                            if (bean[column.valueConfig.ptyKey] == column.valueConfig.notReplace[iNotReplace]) {
                                notReplace = true;
                                cssClass = '';
                                break;
                            }
                        }

                        var options = notReplace ? '<option value="' + bean[column.valueConfig.ptyKey] + '" selected="selected">' + bean[column.valueConfig.ptyValue] + '</option>' : '';
                        for (var iCmbItem in column.valueConfig.list) {
                            if (notReplace) break;
                            var cmbItem = column.valueConfig.list[iCmbItem];
                            var selected = bean[column.valueConfig.ptyKey] == cmbItem.key ? 'selected="selected"' : '';
                            options += '<option value="' + cmbItem.key + '" class="CMBClass' + (config.gridId + column.id) + '" ' + selected + ' >' + cmbItem.value + '</option>';
                        }
                        td += '<select ' + workArgs + ' ' + cssClass + ' onChange="' + onChange + '" style="' + column.valueConfig.style + '">' + options + '</select>';

                    } else if (column.cellValue.toUpperCase() == 'DATEPICKER') {
                        /**
                        * @param    cellValue 'DATEPICKER'
                        * @eventListener cellChangeCombobox   Delegado de la función change por combobox
                        */
                        var onChange = '$(\'#' + config.gridId + '\').jGridContentMVC_HeadDATEPICKER(this)';
                        var workArgs = ' iRow="' + i + '" iCell="' + c + '" gridId="' + config.gridId + '" columnId="' + column.id + '" ptyValue="' + column.valueConfig.ptyValue + '" ptyKey="' + column.valueConfig.ptyKey + '"';
                        var cssClass = 'jGridContentMVC_DATEPICKER' + (config.gridId + column.id);
                        var cssCtrl = column.valueConfig.cssCtrl == null ? "" : column.valueConfig.cssCtrl;
                        var datepickerId = cssClass + i + '_' + c;
                        var value = bean[column.valueConfig.ptyKey];
                        td += '<input id="' + datepickerId + '" class="' + cssClass + ' ' + cssCtrl + '" type="text" value="' + value + '" size="12" maxlength="10" ' + workArgs + ' style="' + column.valueConfig.style + '"/>'

                        datepickerCtrlId.push(datepickerId);
                        /****TextBox*****/
                    } else if (column.cellValue.toUpperCase() == 'TEXTBOX') {
                        /**
                        * @param    cellValue 'TEXTBOX'
                        * @eventListener cellChangeCombobox   Delegado de la función change por combobox
                        */
                        var onChange = '$(\'#' + config.gridId + '\').jGridContentMVC_onChangeTextBox(this)';
                        var workArgs = ' iRow="' + i + '" iCell="' + c + '" gridId="' + config.gridId + '" columnId="' + column.id + '" ptyValue="' + column.valueConfig.ptyValue + '" ptyKey="' + column.valueConfig.ptyKey + '"';
                        //var cssClass = 'jGridContentMVC_DATEPICKER'+(config.gridId+column.id);
                        //var datepickerId = cssClass+i+'_'+c;
                        var textboxId = i + '_' + c + '_' + config.gridId;
                        var value = bean[column.valueConfig.ptyKey];



                        if (bean[column.valueConfig.ptyValue] == 'R') {   //alert(value.length);
                            if (value.length == 2) {
                                td += '<label size="10" style="width: 4.8%; "></label>';
                            } else
                            //td+='<input type="textbox" value="'+value+'" size="10" maxlength="12" '+workArgs+' style="'+column.valueConfig.style+';border:0px;border-style:solid;border-color:#EFEFFF; font-family: Arial;font-size: 12px;"/>'
                            { td += '<label size="10" style="width: 4.8%; ">' + value + '</label>'; }

                        } else {
                            //$('#'+textboxId).mask('$999,999');
                            //$('#'+textboxId).val(value);
                            //value = 0;
                            // td+='<input  type="textbox" value="'+value+'" size="8" maxlength="12" '+workArgs+' style="'+column.valueConfig.style+';border:0px;border-style:solid;border-color:#EFEFFF; font-family: Arial;font-size: 11px;" onChange="'+onChange+'" onkeyup ="this.value=BSM_ExpensesNew.FmtMoneda(this.value)"/>'//
                            td += '<input id="' + textboxId + '" type="textbox" value="' + value + '" size="8" maxlength="12" ' + workArgs + ' style="' + column.valueConfig.style + ';border:0px;border-style:solid;border-color:#EFEFFF; font-family: Arial;font-size: 11px;" onChange="' + onChange + '"/>'//onkeyup ='+BSM_ExpensesNew.FmtMoneda(value)+'
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

                    } else if (column.cellValue == 'InputHTML') {
                        //ObjInputHTML type,name,id,text,value. Datos literales
                        //ObjInputHTML type,#name,#id,#text,#value. Datos dinamicos
                        //cellValue:'InputHTML',ObjInputHTML:{'type':'checkbox','name':'sPKGuestFilter_grid6_0_ChkCIiIdx',id:0,text:'hola'}
                        if (column.ObjInputHTML.type != null) {
                            td += '<input type="' + column.ObjInputHTML.type + '" ';
                            if (column.ObjInputHTML.name != null) {
                                if (column.ObjInputHTML.name.indexOf("#") == 0) {
                                    if (column.ObjInputHTML.name.length > 1) {
                                        td += ' name="' + bean[column.ObjInputHTML.name.substring(1, (column.ObjInputHTML.name).length)] + '"';
                                    }
                                } else {
                                    td += ' name="' + column.ObjInputHTML.name + '"';
                                }
                            }
                            if (column.ObjInputHTML.id != null) {
                                if (column.ObjInputHTML.id.indexOf("#") == 0) {
                                    if (column.ObjInputHTML.id.length > 1) {
                                        td += ' id="' + bean[column.ObjInputHTML.id.substring(1, (column.ObjInputHTML.id).length)] + '"';
                                    }
                                } else {
                                    td += ' id="' + column.ObjInputHTML.id + '"';
                                }
                            }
                            if (column.ObjInputHTML.type == 'checkbox') {
                                if (bean[column.id] == 1) {
                                    td += ' checked';
                                }
                            }
                            if (column.ObjInputHTML.value != null) {
                                if (column.ObjInputHTML.value.indexOf("#") == 0) {
                                    if (column.ObjInputHTML.value.length > 1) {
                                        td += ' value="' + bean[column.ObjInputHTML.value.substring(1, (column.ObjInputHTML.value).length)] + '"';
                                    }
                                } else {
                                    td += ' value="' + column.ObjInputHTML.value + '"';
                                }
                            }
                            td += '>'
                        }
                        if (column.ObjInputHTML.text != null) {
                            if (column.ObjInputHTML.text.indexOf("#") == 0) {
                                if (column.ObjInputHTML.text.length > 1) {
                                    td += bean[column.ObjInputHTML.text.substring(1, (column.ObjInputHTML.text).length)];
                                }
                            } else {
                                td += column.ObjInputHTML.text;
                            }
                        }

                        //                               column.cellValueName;
                        //                            var namecheck = 'jGCMVC'+config.gridId+'_chk'+i;                     
                        //                               if(bean[column.id] == 1){                                
                        //                                   td+='<input id="'+namecheck+'" type="checkbox" checked onClick="$(\'#'+config.gridId+'\').jGridContentMVC_GridChecked( \''+config.gridId+'\',\''+namecheck+'\',\''+i+'\',\''+c+'\',\''+column.id+'\')" />';
                        //                               }else{
                        //                                   td+='<input id="'+namecheck+'" type="checkbox" onClick="$(\'#'+config.gridId+'\').jGridContentMVC_GridChecked( \''+config.gridId+'\',\''+namecheck+'\',\''+i+'\',\''+c+'\',\''+column.id+'\')" />';
                        //                               }

                    } else if (column.cellValue == '_doubleicondocument' && column.type == 'actiondouble') {
                        var cssClass = 'ui-icon ui-icon-trash';
                        if (bean[column.id] == 1) {
                            td += '<div><div class="divLeft"><div style="cursor:pointer;" class="' + column.cssRangeEq.list[0].cssClass + '" onClick="$(\'#' + config.gridId + '\').jGridContentMVC_GridDocumentClick( \'' + config.gridId + '\',\'' + i + '\',\'' + c + '\',\'' + column.id + '\')"></div></div>';
                            td += '<div class="divRight"><div style="cursor:pointer;" class="' + cssClass + '" onClick="$(\'#' + config.gridId + '\').jGridContentMVC_GridDeleteDocumentClick( \'' + config.gridId + '\',\'' + i + '\',\'' + c + '\',\'' + column.id + '\')"></div></div></div>';
                        } else if (bean[column.id] == 0) {
                            td += '<div><div class="divLeft"><div style="cursor:pointer;" class="' + column.cssRangeEq.list[1].cssClass + '" onClick="$(\'#' + config.gridId + '\').jGridContentMVC_GridDocumentClick( \'' + config.gridId + '\',\'' + i + '\',\'' + c + '\',\'' + column.id + '\')"></div></div>';
                            td += '<div class="divRight"><div style="cursor:pointer;" class="' + cssClass + '" onClick="$(\'#' + config.gridId + '\').jGridContentMVC_GridDeleteDocumentClick( \'' + config.gridId + '\',\'' + i + '\',\'' + c + '\',\'' + column.id + '\')"></div></div></div>';
                        }
                    } else if (column.cellValue == 'Parent') {
                        var cssClass = 'ui-icon ui-icon-circle-plus';
                        var valueCell = parseInt(bean[column.id]);
                        if (valueCell == 0)
                            cssClass = 'ui-icon ui-icon-circle-minus';
                        td += '<div id="divParent' + i + '" style="cursor:pointer;" class="' + cssClass + '"></div>';
                    } else if (column.cellValue == 'Bullet') {
                        if (bean[column.id] == 1)
                            td += '<img src="images/indicators/Ind' + bean[column.id] + '.png" />';
                    } else if (column.cellValue == 'eval') {
                        td += column.eval(bean, column.id, column, c, i, config); //eval=function( bean,id,column,iCell,iRow,config )
                    } else if (column.cellValue == 'ValueIndicator') {
                        td += '<table width="100%" cellpadding="0" cellspacing="0"><tr><td width="70%" align="right">' + bean[column.idValue] + '</td><td width="30%" align="center"><IMG SRC="images/Paneles/Ind' + bean[column.idIndicator] + '.png" /></td></tr></table>';
                    } else if (column.type == 'path') {
                        td += '<a title="download" target="_blank" href="' + bean[column.id] + '">' + column.cellValue + '</a>';
                    }
                    //                        else if(column.cssRange == 'RangeTotal'){
                    //                            var valueCellR = bean[column.id];
                    //                            var valueCell = parseInt(bean[column.id]);
                    //                                valueCell = isNaN(valueCell) ? 0 : valueCell;
                    //                            var cssRangeClass = "";
                    //                            var valueCellid = bean.Concept;
                    //                            for(var cssRi in column.cssRange){//[{linf:0,lsup:100,cssClass:'ValueOK'},{linf:101,lsup:null,cssClass:'ValueBad'}]}

                    //                                    if(valueCellid == 'VAR $')
                    //                                    {
                    //                                         if(column.cssRange[cssRi].lsup != null){
                    //                                                if(valueCell >= column.cssRange[cssRi].linf && valueCell <= column.cssRange[cssRi].lsup){
                    //                                                    cssRangeClass = column.cssRange[cssRi].cssClass;
                    //                                                    break;
                    //                                                }
                    //                                                if( valueCell >= column.cssRange[cssRi].linf ){
                    //                                                    cssRangeClass = column.cssRange[cssRi].cssClass;
                    //                                                    break;
                    //                                                }
                    //                                           }
                    //                                    }
                    //                            }
                    //                            td+='<div class="'+cssRangeClass+'">'+valueCellR+'</div>'
                    //                        }
                    //***
                    else if (column.cssRange != null) {
                        var valueCellR = bean[column.id];
                        var valueCell = parseInt(bean[column.id]);
                        valueCell = isNaN(valueCell) ? 0 : valueCell;
                        var cssRangeClass = "";

                        var valueCellid = bean.Concept;

                        for (var cssRi in column.cssRange) {//[{linf:0,lsup:100,cssClass:'ValueOK'},{linf:101,lsup:null,cssClass:'ValueBad'}]}
                            if ((valueCellid == 'TOTAL $') || (valueCellid == 'TARGET $')) {
                                if (valueCellid == 'VAR $') {
                                    if (column.cssRange[cssRi].lsup != null) {
                                        if (valueCell >= column.cssRange[cssRi].linf && valueCell <= column.cssRange[cssRi].lsup) {
                                            cssRangeClass = column.cssRange[cssRi].cssClass;
                                            break;
                                        }
                                        if (valueCell >= column.cssRange[cssRi].linf) {
                                            cssRangeClass = column.cssRange[cssRi].cssClass;
                                            break;
                                        }
                                    }
                                }
                            }
                            else {
                                if (column.cssRange[cssRi].lsup != null) {
                                    if (valueCell >= column.cssRange[cssRi].linf && valueCell <= column.cssRange[cssRi].lsup) {
                                        cssRangeClass = column.cssRange[cssRi].cssClass;
                                        break;
                                    }
                                } else {
                                    if (valueCell >= column.cssRange[cssRi].linf) {
                                        cssRangeClass = column.cssRange[cssRi].cssClass;
                                        break;
                                    }
                                }
                            }
                        }
                        td += '<div class="' + cssRangeClass + '">' + valueCellR + '</div>'
                    } else if (column.cssRangeEq != null) {
                        /*if(flagTRFinal){
                        if(config.HasTextBoxLastEffect){
                        }
                        }
                        else{*/
                        var valueCell = bean[column.id];
                        var cssRangeClass = "";
                        for (var cssRi in column.cssRangeEq.list) {//{cssDefault:'',list:[{Eq:,cssClass:'ValueOK'},{linf:101,lsup:null,cssClass:'ValueBad'}]}                                
                            cssRangeClass = column.cssRangeEq.cssDefault;
                            if (valueCell == column.cssRangeEq.list[cssRi].Eq) {
                                cssRangeClass = column.cssRangeEq.list[cssRi].cssClass;
                                break;
                            }
                        }
                        if (column.cursorIcon)
                            td += '<div style="cursor:pointer;" class="' + cssRangeClass + '">' + valueCell + '</div>'
                        else
                            td += '<div class="' + cssRangeClass + '">' + valueCell + '</div>'
                        //}
                    }
                    //******
                    else if (column.cssRangeEqPty != null) {
                        //cssRangeEqPty:{cssDefault:'cssClass',list[{Eq:EvalPtyValue,Range:[{Type:'RAN',linf:double,lsup:double,cssValue:'cssClass'}]}]}
                        var PtyValue = 0;
                        switch (column.cssRangeEqPty.Pty) {
                            case "jGrid_RowIndex":
                                PtyValue = i;
                                break;
                            default:
                                PtyValue = bean[column.cssRangeEqPty.Pty];
                                break;
                        }
                        PtyValue = parseInt(PtyValue);
                        PtyValue = isNaN(PtyValue) ? 0 : PtyValue;

                        var valueCell = bean[column.id];
                        var valueCellEval = parseInt(valueCell);
                        valueCellEval = isNaN(valueCellEval) ? 0 : valueCellEval;
                        var cssRangeClass = "";
                        for (var cssRi in column.cssRangeEqPty.list) {//{cssDefault:'',list:[{Eq:,cssClass:'ValueOK'},{linf:101,lsup:null,cssClass:'ValueBad'}]}					        
                            cssRangeClass = column.cssRangeEqPty.cssDefault;
                            var BeanEq = column.cssRangeEqPty.list[cssRi];
                            if (PtyValue == BeanEq.Eq || BeanEq.Eq == 'jGrid_Default' || (BeanEq.Eq == 'jGrid_MaxLength' && PtyValue == jGrid_MaxLength)) {
                                for (var iRange in BeanEq.Range) {
                                    var range = BeanEq.Range[iRange];
                                    var evalOK = false;
                                    switch (range.Type) {
                                        case "RAN": evalOK = valueCellEval >= range.linf && valueCellEval <= range.lsup; break;
                                        case "OUT": evalOK = valueCellEval < range.linf || valueCellEval > range.lsup; break;
                                        case "MAX": evalOK = valueCellEval >= range.linf; break;
                                        case "MIN": evalOK = valueCellEval <= range.linf; break;
                                        case "IDE": evalOK = valueCellEval == range.linf; break;
                                        case "DIF": evalOK = valueCellEval != range.linf; break;
                                    }
                                    if (evalOK) {
                                        cssRangeClass = range.cssValue;
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                        td += '<div class="' + cssRangeClass + '">' + valueCell + '</div>'
                    }
                    //************
                    else if (column.cellValue == 'SelectClass') {
                        var SelectClassValue = bean[column.id];
                        //alert(SelectClassValue);
                        for (var iSClass in column.SelectClass) {
                            if (bean[column.id].length == 2) {
                                SelectClassValue = '<div style="width:100%; height:100%;" class="tdcolorgris">&nbsp</div>';
                                break;
                            }
                            if (bean[column.id] == column.SelectClass[iSClass].Value) {
                                SelectClassValue = '<div class="' + column.SelectClass[iSClass].Class + '">&nbsp</div>';
                                break;
                            }
                        }
                        td += SelectClassValue;
                        //****
                        //                        else if(column.cellValue=='SelectClasse'){
                        //                            var SelectClassValue = bean[column.id];
                        //                            for(var iSClass in column.SelectClass){
                        //                                if(bean[column.id] == column.SelectClass[iSClass].Value){
                        //                                    //SelectClassValue=column.SelectClass[iSClass].Class;   
                        //					                SelectClassValue='class='+column.SelectClass[iSClass].Class;

                        //                                 
                        //                                   // SelectClassValue='<div class="'+column.SelectClass[iSClass].Class+'"></div>';
                        //                                    break;
                        //                                }
                        //                            }
                        //                            td+=SelectClassValue;
                        //****
                    } else if (column.cellValue == 'SelectClasse') {
                        var SelectClassValue = bean[column.id];
                        for (var iSClass in column.SelectClass) {
                            if (bean[column.id] == column.SelectClass[iSClass].Value) {
                                //SelectClassValue='<div class="'+column.SelectClass[iSClass].Class+'"></div>';
                                //break;
                                SelectClassValue = '<div class="' + column.SelectClass[iSClass].Class + '">&nbsp</div>';
                                break;
                            }
                        }
                        td += SelectClassValue;
                    }
                    else if (column.cellValue == 'EvalFunction') {
                        td += column.EvalFunction(bean, column, i, c, config);

                    } else if (column.cellValue == 'SelectionClass') {
                        if (column.id.constructor.toString().indexOf("Array") == -1)
                            td += bean[column.id];
                        else
                            for (var iid = 0; iid < column.id.length; iid++) {
                                td += bean[column.id[iid]];
                                td += column.id.length == 1 ? '' : iid == (column.id.length - 1) ? '' : column.betweenValue == null ? " " : column.betweenValue;
                            }
                    } else if (column.cellValue == 'ImageIndicator') {
                        td += '<img src="images/indicators/Ind' + bean[column.id] + '.png" />';
                    } else if (column.cellValue == 'ImageFile') {
                        td += '<div class="ui-icon ui-icon-document" />';
                    } else if (column.cellValue == 'ValueImageIndicator') {
                        //td+='<table width="100%" cellpadding="0" cellspacing="0" border="0"><tr align="right"><td>' + bean[column.id[0]] + '</td><td><img src="images/indicators/Ind' + bean[column.id[1]] + '.png" /></td></tr></table>'; 
                        td += '<div style="width:100%;float: right;">' + bean[column.id[0]] + '<img src="images/indicators/Ind' + bean[column.id[1]] + '.png" style="margin-left: 10px;"/></div>';
                    } else if (column.cellValue == 'iconLower') {
                        var valueCell = bean[column.id];
                        var cssRangeClass = 'ui-icon ui-icon-arrowthick-1-s';

                        td += '<div class="' + cssRangeClass + '">' + valueCell + '</div>'
                    } else if (column.cellValue == 'icon') {
                        var valueCell = bean[column.id];
                        var cssRangeClass = 'ui-icon ui-icon-check';

                        td += '<div class="' + cssRangeClass + '">' + valueCell + '</div>'
                    } else if (column.cellValue == 'iconAction') {
                        if (flagTRFinal) {
                            if (config.HasTextBoxLastEffect) {

                            }
                            else {
                                var cssClass = 'ui-icon ' + column.iconAction;
                                if (column.cursorIcon)
                                    td += '<div style="cursor:pointer;" class="' + cssClass + '"></div>';
                                else
                                    td += '<div class="' + cssClass + '"></div>';
                            }
                        }
                        else {
                            var cssClass = 'ui-icon ' + column.iconAction;
                            if (column.cursorIcon)
                                td += '<div style="cursor:pointer;" class="' + cssClass + '"></div>';
                            else
                                td += '<div class="' + cssClass + '"></div>';
                        }
                    } else if (column.cellValue == 'ActionCellValue') {
                        td += '<div style="text-decoration:underline; color:blue">' + column.id + '</div>';
                    } else
                        td += column.cellValue;

                    if (column.mark) {
                        var mark = column.mark(bean, column, config);
                        if (mark) {
                            td += '<div class="jGridContentMVC_MARK">' + mark + '</div>';
                        }
                    }

                    if (column.centerRight != false && centerRight)
                        td = '<table width="50%" cellspacing="0" cellpadding="0" border="0"><tr><td align="right">' + td + '</td></tr></table>';
                    tdAux += td;
                    tdAux += '</div></td>';
                    tr += tdAux;
                }
                tr += '</tr>';
                if (config.RowDetail != null) {//(config {..., RowDetail:{condition:[{value:'X',idPty:'',idGrid=''},...]
                    for (icond in config.RowDetail.condition) {
                        var condition = config.RowDetail.condition[icond];
                        if (bean[condition.idPty] != condition.value) {
                            //var RowDetail = '<tr id="'+config.gridId+'RowDetail'+ i +'" style="display:none"><td colspan="'+config.columns.length+'"><div id="'+config.gridId+'RowDetail'+ i +'Content" style="width:100%;margin-left:.5cm;"></div></td></tr>';                         
                            var RowDetail = '<tr id="' + config.gridId + 'RowDetail' + i + '" style="display:none"><td colspan="' + config.columns.length + '"><div id="' + config.gridId + 'RowDetail' + i + 'Content" style="width:100%;"><div id="' + condition.idGrid + i + '" style="width:100%;"></div></div></td></tr>';
                            tr += RowDetail;
                            break;
                        }
                    }
                }
                if (config.interLine != null)
                    tr += '<tr style="height: ' + config.interLine + ';"><td  colspan="' + config.columns.length + '"></td></tr>'; //<td style="width:25%; height: 30px; color:Black; font-weight:bold">
                if (i == (config.storage.length - 1) && config.rowFinalSeparate != null) {
                    var cssAUXTab = i % 2 != 0 ? "#FFFFFF" : " #FFFFFF";
                    tblFinRow = '<table';
                    tblFinRow += config.cellpadding == null ? '' : ' cellpadding="' + config.cellpadding + '"';
                    tblFinRow += config.cellspacing == null ? '' : ' cellspacing="' + config.cellspacing + '"';
                    tblFinRow += config.border == null ? '' : ' border="' + config.border + '"';
                    tblFinRow += config.tblCssClass != null ? ' class="' + config.tblCssClass + '"' : '';
                    tblFinRow += ' style="width:100%;font-weight:bold;background-color:' + cssAUXTab + '"';
                    tblFinRow += '>';
                    tblFinRow += tr;
                    tblFinRow += '</table>';
                } else
                    tbl += tr;
            }
            tbl += '</tbody></table>';
            tbl = config.storage.length > 0 ? tbl : (config.noDataMsg == null ? 'no record to display' : config.noDataMsg);
            tbl += config.rowFinalSeparate != null ? tblFinRow : "";
            
            if(config.afterDrawing){
                $('#' + config.gridId).html(tbl);
                config.afterDrawing(config);
            }else
                $('#' + config.gridId).html(tbl, function () { });

            for (var iDCHId in datepickerCtrlHeadId) {
                var id = datepickerCtrlHeadId[iDCHId];
                $("#" + id).datepicker({
                    dateFormat: 'dd/mm/yy'
                        , showOn: "button"
                        , buttonImage: "images/iconDatePicker.gif"
                        , buttonImageOnly: true
                          , showOtherMonths: true
                          , selectOtherMonths: true
                        , onSelect: function () {
                            var gridId = $(this).attr('gridId');
                            $("#" + gridId).jGridContentMVC_HeadDatepicker(this);
                        }
                });
            }

            for (var iDCHId in datepickerCtrlId) {
                var id = datepickerCtrlId[iDCHId];
                $("#" + id).datepicker({
                    dateFormat: 'dd-mm-yy'
                    //,showOn: "button"
                    //,buttonImage: "images/iconDatePicker.gif"
                    //,buttonImageOnly: true
                          , showOtherMonths: true
                          , selectOtherMonths: true
                        , onSelect: function () {
                            var gridId = $(this).attr('gridId');
                            $("#" + gridId).jGridContentMVC_onChangeDatepicker(this);
                        }
                });
            }
        }

        $.fn.jGridContentMVC_GridDrawIterated = $.fn.jGridContentMVC_GridDrawIterated != null ? $.fn.jGridContentMVC_GridDrawIterated : function (config) {

            //var nameTextLblEffect='';
            config.storage = config.storage == null ? new Array() : config.storage;
            var tblFinRow = "";
            var tbl = '<table';
            tbl += ' id="Tbl' + config.gridId + '"';
            tbl += config.cellpadding == null ? '' : ' cellpadding="' + config.cellpadding + '"';
            tbl += config.cellspacing == null ? '' : ' cellspacing="' + config.cellspacing + '"';
            tbl += config.rules == null ? '' : ' rules="' + config.rules + '"';
            tbl += config.border == null ? '' : ' border="' + config.border + '"';

            tbl += config.tblCssClass != null ? ' class="' + config.tblCssClass + '"' : '';
            tbl += config.tblStyle != null ? ' style="' + config.tblStyle + '"' : '';
            tbl += ' id="GridTbl' + config.gridId + '"';
            tbl += '>';

            if (config.Heads) {
                if (config.Encabezados) {
                    tbl += '<thead><tr';
                    tbl += config.HeadCss == null ? '' : ' class="' + config.HeadCss + '"';
                    tbl += config.HeadAlign == null ? ' align="center"' : ' align="' + config.HeadAlign + '"';
                    tbl += '>'
                    for (var c = 0; c < config.columns.length; c++) {
                        var column = config.columns[c];
                        if (column.colspan != null) {
                            var td = '<th';
                            td += ' colspan="' + column.colspan + "'";
                            td += column.width == null ? '' : ' style="width:' + column.width + '"';
                            td += column.HeadTitle == null ? '' : ' title="' + column.HeadTitle + '"';
                            td += '>'
                            td += column.Encabezado;
                            td += '</th>';
                            tbl += td;
                        }
                    }
                    tbl += '</tr></thead>';
                }
                tbl += '<thead><tr';
                tbl += config.HeadCss == null ? '' : ' class="' + config.HeadCss + '"';
                tbl += config.HeadAlign == null ? ' align="center"' : ' align="' + config.HeadAlign + '"';
                tbl += '>'
                for (var c = 0; c < config.columns.length; c++) {
                    var column = config.columns[c];
                    var td = '<th';
                    td += column.width == null ? '' : ' style="width:' + column.width + '"';
                    td += '>'
                    td += column.HeadText;
                    td += '</th>';
                    tbl += td;
                }
                tbl += '</tr></thead><tbody>';

            }
            var flagTRInit = false;
            var flagTRFinal = false;
            var i = -1;
            for (var iterate in config.storage) {
                i++;
                var bean = config.storage[iterate];
                if (bean == null)
                    continue;
                flagTRInit = i == 0;
                flagTRFinal = i == (config.storage.length - 1);
                var tr = '<tr';
                tr += config.rowClick == null || (flagTRFinal && config.rowFinal != null) || (flagTRInit && config.rowInitNonClick != null) ? '' : ' onClick="$(\'#' + config.gridId + '\').jGridContentMVC_GridRowClick( \'' + config.gridId + '\',\'' + i + '\' )"';
                var inRowDetail = false;
                if (config.RowDetail != null) {//(config {..., RowDetail:{condition:[{value:'X',idPty:'',idGrid=''},...]
                    for (icond in config.RowDetail.condition) {
                        var condition = config.RowDetail.condition[icond];
                        if (bean[condition.idPty] == condition.value) {
                            var idRowDetail = config.gridId + 'RowDetail' + i;
                            var eventView = ' ondblclick="$(\'#' + config.gridId + '\').jGridContentMVC_GridRowDblClickRowDetail( \'' + config.gridId + '\',\'' + idRowDetail + '\' )"';
                            tr += eventView;
                            inRowDetail = true;
                            break;
                        }
                    }
                }
                if (config.RowID != null) {//(config {..., RowDetail:{condition:[{value:'X',idPty:'',idGrid=''},...]
                    for (icond in config.RowID.condition) {
                        var condition = config.RowID.condition[icond];
                        if (bean[condition.idPty] == condition.value) {
                            var idRowDetail = config.gridId + 'RowDetail' + i;
                            tr += ' id="' + idRowDetail + '" ';
                            tr += 'style=""';
                            break;
                        }
                    }
                }
                tr += config.rowClick != null || inRowDetail ? ' style="cursor:pointer"' : '';
                tr += config.rowAlign == null ? '' : ' align="' + config.rowAlign + '"';

                if (config.rowFinal == null) {
                    tr += config.rowCssA == null ? '' : ' class="' + (((i + 1) % 2) == 0 ? config.rowCssA : config.rowCssB) + '"';
                } else {
                    if (i == (config.storage.length - 1)) {
                        tr += config.rowCssA == null ? ' class="' + config.rowFinal + '"' : 'class="' + (((i + 1) % 2) == 0 ? config.rowCssA : config.rowCssB) + ' ' + config.rowFinal + '"';
                        flagTRFinal = true;
                    } else
                        tr += config.rowCssA == null ? '' : ' class="' + (((i + 1) % 2) == 0 ? config.rowCssA : config.rowCssB) + '"';
                }
                tr += '>';

                for (var c = 0; c < config.columns.length; c++) {
                    var column = config.columns[c];
                    var td = '<td';

                    if (column.cellValue == 'SelectionClass')
                        td += ' id="td' + config.gridId + '_select' + c + '"';

                    td += column.width == null ? '' : ' style="width:' + column.width;
                    td += column.indent == null ? '"' : ';text-indent:' + column.indent + '"';
                    td += column.align == null ? '' : ' align="' + column.align + '"';
                    if (config.RowDetail == null) {
                        td += column.cssClass == null ? '' : ' class="' + column.cssClass + '"';
                    }
                    else {
                        if (inRowDetail) {
                            td += column.cssClass == null ? '' : ' class="' + column.cssClass + '"';
                        }
                    }

                    if (config.RowID != null) {
                        var condition = config.RowID.condition[icond];
                        if (bean[condition.idPty] == condition.value) {
                            var idRowDetail = 'td' + config.gridId + 'RowDetail' + i + c;
                            td += ' id="' + idRowDetail + '" ';
                            td += config.rowCssA == null ? '' : 'style="background-color:' + (((i + 1) % 2) == 0 ? '#efefff;' : 'White') + '"';
                        }
                    }
                    td += column.title == null ? '' : ' title="' + column.title + '"';
                    td += column.valign == null ? '' : ' valign="' + column.valign + '"';

                    td += column.type == null ? '' : column.type == 'action' && !(flagTRFinal && config.rowFinal != null) && !(flagTRInit && config.rowInitNonClick != null) ? ' onClick="$(\'#' + config.gridId + '\').jGridContentMVC_GridCellClick( \'' + config.gridId + '\',\'' + iterate + '\',\'' + c + '\')" style="cursor:pointer"' : '';

                    td += '>';

                    if (column.cellValue == null) {
                        if (flagTRFinal) {
                            if (config.HasTextBoxLastEffect) {
                                if (c == config.HasTextBoxLastEffectColumn) {
                                    /*nameTextLblEffect='jGCMVC'+config.gridId+'_lbl'+i;
                                    td+='<label style="cursor:pointer;" id="'+nameTextLblEffect+'" class="tablesorterlabel">'+ bean[column.id] + '</label>';
                                    */
                                    config.nameTextBoxEffect = 'jGCMVC' + config.gridId + '_txt' + i;
                                    td += '<input id="' + config.nameTextBoxEffect + '" type="textbox" style="display:inline;width:100%;" onBlur="$(\'#' + config.gridId + '\').jGridContentMVC_GridTextBoxLostFocusValue( \'' + config.gridId + '\',\'' + config.nameTextBoxEffect + '\',\'' + i + '\')" />';
                                } else {
                                    td += bean[column.id];
                                }
                            }
                            else {

                                if (column.id.constructor.toString().indexOf("Array") == -1)
                                    td += bean[column.id];
                                else
                                    for (var iid = 0; iid < column.id.length; iid++) {
                                        td += bean[column.id[iid]];
                                        td += column.id.length == 1 ? '' : iid == (column.id.length - 1) ? '' : column.betweenValue == null ? " " : column.betweenValue;
                                    }
                            }
                        } else {
                            if (column.id.constructor.toString().indexOf("Array") == -1)
                                td += bean[column.id];
                            else
                                for (var iid = 0; iid < column.id.length; iid++) {
                                    td += bean[column.id[iid]];
                                    td += column.id.length == 1 ? '' : iid == (column.id.length - 1) ? '' : column.betweenValue == null ? " " : column.betweenValue;
                                }
                        }
                    } else if (column.cellValue == '_chk2') {
                        var namecheck = 'jGCMVC' + config.gridId + '_chk' + i;
                        if (bean[column.id] == 2) {
                            td += '<input id="' + namecheck + '" type="checkbox" checked onClick="$(\'#' + config.gridId + '\').jGridContentMVC_GridChecked( \'' + config.gridId + '\',\'' + namecheck + '\',\'' + i + '\',\'' + c + '\',\'' + column.id + '\')" />';
                        } else {
                            td += '<input id="' + namecheck + '" type="checkbox" onClick="$(\'#' + config.gridId + '\').jGridContentMVC_GridChecked( \'' + config.gridId + '\',\'' + namecheck + '\',\'' + i + '\',\'' + c + '\',\'' + column.id + '\')" />';
                        }

                    } else if (column.cellValue == '_chk3') {
                        if (bean[column.id] == 2) {
                            td += "SI";
                        } else {
                            td += "NO";
                        }
                    } else if (column.cellValue == '_chk') {
                        var namecheck = 'jGCMVC' + config.gridId + '_chk' + i;
                        if (bean[column.id] == 1) {
                            td += '<input id="' + namecheck + '" type="checkbox" checked onClick="$(\'#' + config.gridId + '\').jGridContentMVC_GridChecked( \'' + config.gridId + '\',\'' + namecheck + '\',\'' + i + '\',\'' + c + '\',\'' + column.id + '\')" />';
                        } else {
                            td += '<input id="' + namecheck + '" type="checkbox" onClick="$(\'#' + config.gridId + '\').jGridContentMVC_GridChecked( \'' + config.gridId + '\',\'' + namecheck + '\',\'' + i + '\',\'' + c + '\',\'' + column.id + '\')" />';
                        }

                    } else if (column.cellValue == 'InputHTML') {
                        //ObjInputHTML type,name,id,text,value. Datos literales
                        //ObjInputHTML type,#name,#id,#text,#value. Datos dinamicos
                        //cellValue:'InputHTML',ObjInputHTML:{'type':'checkbox','name':'sPKGuestFilter_grid6_0_ChkCIiIdx',id:0,text:'hola'}
                        if (column.ObjInputHTML.type != null) {
                            td += '<input type="' + column.ObjInputHTML.type + '" ';
                            if (column.ObjInputHTML.name != null) {
                                if (column.ObjInputHTML.name.indexOf("#") == 0) {
                                    if (column.ObjInputHTML.name.length > 1) {
                                        td += ' name="' + bean[column.ObjInputHTML.name.substring(1, (column.ObjInputHTML.name).length)] + '"';
                                    }
                                } else {
                                    td += ' name="' + column.ObjInputHTML.name + '"';
                                }
                            }
                            if (column.ObjInputHTML.id != null) {
                                if (column.ObjInputHTML.id.indexOf("#") == 0) {
                                    if (column.ObjInputHTML.id.length > 1) {
                                        td += ' id="' + bean[column.ObjInputHTML.id.substring(1, (column.ObjInputHTML.id).length)] + '"';
                                    }
                                } else {
                                    td += ' id="' + column.ObjInputHTML.id + '"';
                                }
                            }
                            if (column.ObjInputHTML.type == 'checkbox') {
                                if (bean[column.id] == 1) {
                                    td += ' checked';
                                }
                            }
                            if (column.ObjInputHTML.value != null) {
                                if (column.ObjInputHTML.value.indexOf("#") == 0) {
                                    if (column.ObjInputHTML.value.length > 1) {
                                        td += ' value="' + bean[column.ObjInputHTML.value.substring(1, (column.ObjInputHTML.value).length)] + '"';
                                    }
                                } else {
                                    td += ' value="' + column.ObjInputHTML.value + '"';
                                }
                            }
                            td += '>'
                        }
                        if (column.ObjInputHTML.text != null) {
                            if (column.ObjInputHTML.text.indexOf("#") == 0) {
                                if (column.ObjInputHTML.text.length > 1) {
                                    td += bean[column.ObjInputHTML.text.substring(1, (column.ObjInputHTML.text).length)];
                                }
                            } else {
                                td += column.ObjInputHTML.text;
                            }
                        }

                        //                               column.cellValueName;
                        //                            var namecheck = 'jGCMVC'+config.gridId+'_chk'+i;                     
                        //                               if(bean[column.id] == 1){                                
                        //                                   td+='<input id="'+namecheck+'" type="checkbox" checked onClick="$(\'#'+config.gridId+'\').jGridContentMVC_GridChecked( \''+config.gridId+'\',\''+namecheck+'\',\''+i+'\',\''+c+'\',\''+column.id+'\')" />';
                        //                               }else{
                        //                                   td+='<input id="'+namecheck+'" type="checkbox" onClick="$(\'#'+config.gridId+'\').jGridContentMVC_GridChecked( \''+config.gridId+'\',\''+namecheck+'\',\''+i+'\',\''+c+'\',\''+column.id+'\')" />';
                        //                               }

                    } else if (column.cellValue == '_doubleicondocument' && column.type == 'actiondouble') {
                        var cssClass = 'ui-icon ui-icon-trash';
                        if (bean[column.id] == 1) {
                            td += '<div><div class="divLeft"><div style="cursor:pointer;" class="' + column.cssRangeEq.list[0].cssClass + '" onClick="$(\'#' + config.gridId + '\').jGridContentMVC_GridDocumentClick( \'' + config.gridId + '\',\'' + i + '\',\'' + c + '\',\'' + column.id + '\')"></div></div>';
                            td += '<div class="divRight"><div style="cursor:pointer;" class="' + cssClass + '" onClick="$(\'#' + config.gridId + '\').jGridContentMVC_GridDeleteDocumentClick( \'' + config.gridId + '\',\'' + i + '\',\'' + c + '\',\'' + column.id + '\')"></div></div></div>';
                        } else if (bean[column.id] == 0) {
                            td += '<div><div class="divLeft"><div style="cursor:pointer;" class="' + column.cssRangeEq.list[1].cssClass + '" onClick="$(\'#' + config.gridId + '\').jGridContentMVC_GridDocumentClick( \'' + config.gridId + '\',\'' + i + '\',\'' + c + '\',\'' + column.id + '\')"></div></div>';
                            td += '<div class="divRight"><div style="cursor:pointer;" class="' + cssClass + '" onClick="$(\'#' + config.gridId + '\').jGridContentMVC_GridDeleteDocumentClick( \'' + config.gridId + '\',\'' + i + '\',\'' + c + '\',\'' + column.id + '\')"></div></div></div>';
                        }
                    } else if (column.type == 'path') {
                        td += '<a title="download" target="_blank" href="' + bean[column.id] + '">' + column.cellValue + '</a>';
                    } else if (column.cssRange != null) {
                        var valueCellR = bean[column.id];
                        var valueCell = parseInt(bean[column.id]);
                        valueCell = isNaN(valueCell) ? 0 : valueCell;
                        var cssRangeClass = "";
                        for (var cssRi in column.cssRange) {//[{linf:0,lsup:100,cssClass:'ValueOK'},{linf:101,lsup:null,cssClass:'ValueBad'}]}
                            if (column.cssRange[cssRi].lsup != null) {
                                if (valueCell >= column.cssRange[cssRi].linf && valueCell <= column.cssRange[cssRi].lsup) {
                                    cssRangeClass = column.cssRange[cssRi].cssClass;
                                    break;
                                }
                            } else {
                                if (valueCell >= column.cssRange[cssRi].linf) {
                                    cssRangeClass = column.cssRange[cssRi].cssClass;
                                    break;
                                }
                            }
                        }
                        td += '<div class="' + cssRangeClass + '">' + valueCellR + '</div>'
                    } else if (column.cssRangeEq != null) {
                        /*if(flagTRFinal){
                        }
                        }
                        else{*/
                        var valueCell = bean[column.id];
                        var cssRangeClass = "";
                        for (var cssRi in column.cssRangeEq.list) {//{cssDefault:'',list:[{Eq:,cssClass:'ValueOK'},{linf:101,lsup:null,cssClass:'ValueBad'}]}                                
                            cssRangeClass = column.cssRangeEq.cssDefault;
                            if (valueCell == column.cssRangeEq.list[cssRi].Eq) {
                                cssRangeClass = column.cssRangeEq.list[cssRi].cssClass;
                                break;
                            }
                        }
                        if (column.cursorIcon)
                            td += '<div style="cursor:pointer;" class="' + cssRangeClass + '">' + valueCell + '</div>'
                        else
                            td += '<div class="' + cssRangeClass + '">' + valueCell + '</div>'
                        //}
                    } else if (column.cellValue == 'SelectClass') {
                        var SelectClassValue = bean[column.id];
                        for (var iSClass in column.SelectClass) {
                            if (bean[column.id] == column.SelectClass[iSClass].Value) {
                                SelectClassValue = '<div class="' + column.SelectClass[iSClass].Class + '"></div>';
                                break;
                            }
                        }
                        td += SelectClassValue;
                    } else if (column.cellValue == 'SelectionClass') {
                        if (column.id.constructor.toString().indexOf("Array") == -1)
                            td += bean[column.id];
                        else
                            for (var iid = 0; iid < column.id.length; iid++) {
                                td += bean[column.id[iid]];
                                td += column.id.length == 1 ? '' : iid == (column.id.length - 1) ? '' : column.betweenValue == null ? " " : column.betweenValue;
                            }
                    } else if (column.cellValue == 'ImageIndicator') {
                        td += '<img src="images/indicators/Ind' + bean[column.id] + '.png" />';
                    } else if (column.cellValue == 'ImageFile') {
                        td += '<div class="ui-icon ui-icon-document" />';
                    } else if (column.cellValue == 'ValueImageIndicator') {
                        //td+='<table width="100%" cellpadding="0" cellspacing="0" border="0"><tr align="right"><td>' + bean[column.id[0]] + '</td><td><img src="images/indicators/Ind' + bean[column.id[1]] + '.png" /></td></tr></table>'; 
                        td += '<div style="width:100%;float: right;">' + bean[column.id[0]] + '<img src="images/indicators/Ind' + bean[column.id[1]] + '.png" style="margin-left: 10px;"/></div>';
                    } else if (column.cellValue == 'iconLower') {
                        var valueCell = bean[column.id];
                        var cssRangeClass = 'ui-icon ui-icon-arrowthick-1-s';

                        td += '<div class="' + cssRangeClass + '">' + valueCell + '</div>'
                    } else if (column.cellValue == 'icon') {
                        var valueCell = bean[column.id];
                        var cssRangeClass = 'ui-icon ui-icon-check';

                        td += '<div class="' + cssRangeClass + '">' + valueCell + '</div>'
                    } else if (column.cellValue == 'iconAction') {
                        if (flagTRFinal) {
                            if (config.HasTextBoxLastEffect) {

                            }
                            else {
                                var cssClass = 'ui-icon ' + column.iconAction;
                                if (column.cursorIcon)
                                    td += '<div style="cursor:pointer;" class="' + cssClass + '"></div>';
                                else
                                    td += '<div class="' + cssClass + '"></div>';
                            }
                        }
                        else {
                            var cssClass = 'ui-icon ' + column.iconAction;
                            if (column.cursorIcon)
                                td += '<div style="cursor:pointer;" class="' + cssClass + '"></div>';
                            else
                                td += '<div class="' + cssClass + '"></div>';
                        }
                    } else
                        td += column.cellValue;
                    td += '</td>';
                    tr += td;
                }
                tr += '</tr>';
                if (config.RowDetail != null) {//(config {..., RowDetail:{condition:[{value:'X',idPty:'',idGrid=''},...]
                    for (icond in config.RowDetail.condition) {
                        var condition = config.RowDetail.condition[icond];
                        if (bean[condition.idPty] == condition.value) {
                            var RowDetail = '<tr id="' + config.gridId + 'RowDetail' + i + '" style="display:none"><td colspan="' + config.columns.length + '"><div id="' + config.gridId + 'RowDetail' + i + 'Content" style="width:100%;margin-left:.5cm;"></div></td></tr>';
                            tr += RowDetail;
                            break;
                        }
                    }
                }
                if (config.interLine != null)
                    tr += '<tr style="height: ' + config.interLine + ';"><td  colspan="' + config.columns.length + '"></td></tr>'; //<td style="width:25%; height: 30px; color:Black; font-weight:bold">
                if (i == (config.storage.length - 1) && config.rowFinalSeparate != null) {
                    var cssAUXTab = i % 2 != 0 ? "#FFFFFF" : " #FFFFFF";
                    tblFinRow = '<table';
                    tblFinRow += config.cellpadding == null ? '' : ' cellpadding="' + config.cellpadding + '"';
                    tblFinRow += config.cellspacing == null ? '' : ' cellspacing="' + config.cellspacing + '"';
                    tblFinRow += config.border == null ? '' : ' border="' + config.border + '"';
                    tblFinRow += config.tblCssClass != null ? ' class="' + config.tblCssClass + '"' : '';
                    tblFinRow += ' style="width:100%;font-weight:bold;background-color:' + cssAUXTab + '"';
                    tblFinRow += '>';
                    tblFinRow += tr;
                    tblFinRow += '</table>';
                } else
                    tbl += tr;
            }
            tbl += '</tbody></table>';
            tbl = i > -1 ? tbl : (config.noDataMsg == null ? 'no record to display' : config.noDataMsg);
            tbl += config.rowFinalSeparate != null ? tblFinRow : "";
            $('#' + config.gridId).html(tbl);
        }

        $.fn.jGridContentMVC_GridRowClick = function (GridId, index) {
            var config = $('#' + GridId).jGridContentMVC_GridStorage[GridId];
            if (config.ClickDocument != null && config.ClickDeleteDocument != null) {
                if (config.ClickDocument == true && config.ClickDeleteDocument == false) {
                    config.ClickDocument = null;
                    config.ClickDeleteDocument = null;
                } else if (config.ClickDeleteDocument == true && config.ClickDocument == false) {
                    config.ClickDocument = null;
                    config.ClickDeleteDocument = null;
                } else {
                    config.rowClick(index, config.storage[index], GridId);
                }
            } else if (config.ClickCheck != null) {
                config.ClickCheck = null;
            } else {
                config.rowClick(index, config.storage[index], GridId);
            }
            return true;
        }
        $.fn.jGridContentMVC_GridTextBoxLostFocusValue = function (GridId, nameTextBox, iRow) {
            var config = $('#' + GridId).jGridContentMVC_GridStorage[GridId];

            //if(config.effectlbltxt){
            //$('#'+nameTextBox).css('display','none');
            //$('#'+nameLabel).fadeIn('slow');
            //$('#'+nameLabel).fadeIn(5000,function(){});
            //$('#'+nameLabel)[0].innerText = $('#'+nameTextBox)[0].value;                      
            //config.effectlbltxt = false;
            //}
            //else{
            //$('#'+nameTextBox).fadeIn('slow');
            //$('#'+nameTextBox).focus();
            //$('#'+nameLabel).css('display','none');
            //config.effectlbltxt = true;
            //}   
            if (config.cellClick != null)
                config.cellTextBoxLostFocusValue(config.storage[iRow], $('#' + nameTextBox)[0].value, iRow);
            return true;
        }

        $.fn.jGridContentMVC_GridChecked = function (GridId, namecheck, iRow, iCell, columnId) {
            var config = $('#' + GridId).jGridContentMVC_GridStorage[GridId];
            var FlagChecked = $('#' + namecheck)[0].checked;
            var Bean = config.storage[iRow];
            if (FlagChecked) {
                Bean[columnId] = 1;
            } else {
                Bean[columnId] = 0;
            }
            config.ClickCheck = true;
            if (config.cellCheckedValue != null)
                config.cellCheckedValue(Bean, FlagChecked, iRow, iCell, GridId, config.columns[iCell].id);
            return true;
        }

        $.fn.jGridContentMVC_GridRowDblClickRowDetail = function (GridId, idRowDetail) {
            debugger;
            if ($("#" + idRowDetail).css('display') == 'none')
                $("#" + idRowDetail).fadeIn('slow');
            else
                $("#" + idRowDetail).fadeOut('slow');

            return true;
        }

        $.fn.jGridContentMVC_GridRowDblClickRowDetail = function (GridId, idRowDetail, IdClass, idRequest, GridParent) {
            var div = "#divParent" + idRowDetail.replace("gridRequestsEventsRowDetail", "");
            if ($("#" + idRowDetail).css('display') == 'none') {
                $("#" + idRowDetail).fadeIn('slow');
                $(div).removeClass().addClass('ui-icon ui-icon-minus');
                PKRequest.Parent(idRequest, GridParent);
            }
            else {
                $("#" + idRowDetail).fadeOut('slow');
                $(div).removeClass().addClass('ui-icon ui-icon-circle-plus');
            }

            return true;
        }

        $.fn.jGridContentMVC_GridRowDClickRowDetail = function (GridIdP, GridIdC) {
            if ($("#" + GridIdC).css('display') == 'none') {
                $("#ChargingFeesSales_BtnBack").show('slow', function () { $("#" + GridIdC).show('slow'); $("#" + GridIdP).hide('slow'); });
            }
            else {
                $("#ChargingFeesSales_BtnBack").fadeOut('slow', function () { $("#" + GridIdP).fadeIn('slow'); $("#" + GridIdC).fadeOut('slow'); });
            }

            return true;
        }
        $.fn.jGridContentMVC_Filter = function (Filters) {
            var config = $(this[0]).jGridContentMVC_GridStorage[this[0].id];

            var array = new Array();

            for (var i in config.storage) {
                var bean = config.storage[i];
                for (var f in Filters) {
                    filter = Filters[f];
                    var op = true;
                    for (var p in filter) {
                        op = bean[p] == filter[p];
                        if (!op)
                            break;
                    }
                    if (op) {
                        array.push(bean);
                        break;
                    }
                }
            }

            var configAux = new Object();
            for (var p in config)
                configAux[p] = config[p];
            configAux.storage = array;
            $(this[0]).jGridContentMVC_GridDraw(configAux);
        }

        $.fn.jGridContentMVC_GridCellClick = function (GridId, iRow, iCell, td, e) {
            var config = $('#' + GridId).jGridContentMVC_GridStorage[GridId];

            if (config.HasTextBoxLastEffect) {
                if (iRow == (config.storage.length - 1)) {
                    var txt = $('#jGCMVC' + GridId + '_txt' + iRow);
                    //var lbl = $('#jGCMVC'+GridId+'_lbl'+iRow);
                    //if(config.effectlbltxt){
                    /*txt.css('display','none');
                    lbl.fadeIn('slow');
                    lbl[0].innerText = txt[0].value;*/
                    //config.effectlbltxt = false;
                    //}
                    //else{
                    txt.fadeIn('slow');
                    txt.focus();
                    //lbl.css('display','none');
                    //config.effectlbltxt = true;
                    //}                           
                }
            }
            if (config.cellClick)
                config.cellClick(iRow, iCell, config.storage[iRow], config.columns[iCell].id, GridId, td, e);
            return true;
        }

        $.fn.jGridContentMVC_UpDate = $.fn.jGridContentMVC_UpDate != null ? $.fn.jGridContentMVC_UpDate : function (storage) {
            var config = $(this[0]).jGridContentMVC_GridStorage[this[0].id];
            if (storage == null)
                $(this[0]).jGridContentMVC_GridDraw(config);
            else {
                if (arguments[1] == null)
                    config.storage = storage;
                else if (storage < 0 || storage >= config.storage.length)
                    return;
                else
                    config.storage[storage] = arguments[1];

                $(this[0]).jGridContentMVC_GridDraw(config);
            }
            return;
        }

        $.fn.jGridContentMVC_Filters = $.fn.jGridContentMVC_Filters != null ? $.fn.jGridContentMVC_Filters : function (filters) {
            var config = $(this[0]).jGridContentMVC_GridStorage[this[0].id];
            config.storage = config.filter ? config.storageBackup : config.storage;

            if (filters == null) {
                $(this[0]).jGridContentMVC_GridDraw(config);
                return;
            }

            var storage = config.storage;
            config.storageBackup = config.storage;
            var filter = new Array();
            for (var i in storage) {
                var bean = storage[i];
                for (var f in filters) {
                    var ftr = filters[f];
                    if (bean[ftr.field] == ftr.value)
                        filter.push(bean);
                }
            }
            config.storage = filter;
            config.filter = true;
            $(this[0]).jGridContentMVC_GridDraw(config);
            return;
        }

        $.fn.jGridContentMVC_Add = $.fn.jGridContentMVC_Add != null ? $.fn.jGridContentMVC_Add : function (bean) {
            var config = $(this[0]).jGridContentMVC_GridStorage[this[0].id];
            var addIni = arguments[1] == null ? false : true;
            if (addIni)
                config.storage.unshift(bean);
            else
                config.storage.push(bean);

            $(this[0]).jGridContentMVC_GridDraw(config);
            return;
        }

        $.fn.jGridContentMVC_AddIndex = $.fn.jGridContentMVC_AddIndex != null ? $.fn.jGridContentMVC_AddIndex : function (bean, index, replace) {
            var config = $(this[0]).jGridContentMVC_GridStorage[this[0].id];
            if (index < 0)
                config.storage.unshift(bean);
            else if (index > config.storage.length)
                config.storage.push(bean);
            else
                config.storage.splice(index, replace, bean);

            $(this[0]).jGridContentMVC_GridDraw(config);
            return;
        }

        $.fn.jGridContentMVC_Delete = $.fn.jGridContentMVC_Delete != null ? $.fn.jGridContentMVC_Delete : function (index) {
            var config = $(this[0]).jGridContentMVC_GridStorage[this[0].id];
            if (index < 0 || index >= config.storage.length)
                return;
            if (config.drawIterated == null) {
                config.storage.splice(index, 1);
                $(this[0]).jGridContentMVC_GridDraw(config);
            } else {
                config.storage[index] = null;
                $(this[0]).jGridContentMVC_GridDrawIterated(config);
            }
            return;
        }
        $.fn.jGridContentMVC_Get = $.fn.jGridContentMVC_Get != null ? $.fn.jGridContentMVC_Get : function (index) {
            var config = $(this[0]).jGridContentMVC_GridStorage[this[0].id];
            if (index < 0 || index >= config.storage.length)
                return null;
            return config.storage[index];
        }
        $.fn.jGridContentMVC_GetList = $.fn.jGridContentMVC_GetList != null ? $.fn.jGridContentMVC_GetList : function () {
            var config = $(this[0]).jGridContentMVC_GridStorage[this[0].id];
            return config.storage;
        }

        $.fn.jGridContentMVC_Chk = $.fn.jGridContentMVC_Chk != null ? $.fn.jGridContentMVC_Chk : function () {
            var config = $(this[0]).jGridContentMVC_GridStorage[this[0].id];
            var list = config.storage;
            var ChkBeans = new Array();
            $.each(list, function (i, bean) {
                var chk = $('#jGCMVC' + config.gridId + '_chk' + i).attr('checked');
                var ChkBean = { 'Index': i, 'Bean': bean, 'Checked': chk };
                ChkBeans.push(ChkBean);
            });
            return ChkBeans;
        }

        /*****************************************Dynamic CtrlBox Listeners **************************************************/

        $.fn.jGridContentMVC_onChangeCheckboxCtrl = $.fn.jGridContentMVC_onChangeCheckboxCtrl != null ? $.fn.jGridContentMVC_onChangeCheckboxCtrl : function (chk) {
            var gridId = $(chk).attr('gridId');
            var columnId = $(chk).attr('columnId');
            var value = $(chk).attr('value');
            var checked = $(chk).attr('checked');
            var row = $(chk).attr('iRow');
            var cell = $(chk).attr('iCell');
            checked = checked == null ? false : true;

            var config = $("#" + gridId).jGridContentMVC_GridStorage[gridId];
            config.storage[row][columnId] = checked;

            if (config.cellCheckedValue != null)
                config.cellCheckedValue(config.storage[row], checked, row, cell, gridId, config.columns[cell].id);
            return true;
        }

        $.fn.jGridContentMVC_HeadChecked = $.fn.jGridContentMVC_HeadChecked != null ? $.fn.jGridContentMVC_HeadChecked : function (chk) {
            var gridId = $(chk).attr('gridId');
            var columnId = $(chk).attr('columnId');
            var value = $(chk).attr('value');
            var checked = $(chk).attr('checked');
            var row = $(chk).attr('iRow');
            var cell = $(chk).attr('iCell');
            checked = checked == null ? false : checked;

            var config = $("#" + gridId).jGridContentMVC_GridStorage[gridId];

            var chk = '.ChkClass' + (gridId + columnId);

            config.columns[cell].HeadValue = checked;
            $(chk).attr('checked', checked);

            for (var row in config.storage)
                config.storage[row][columnId] = checked;

            if (config.HeadCheckBox_onChange != null)
                config.HeadCheckBox_onChange(columnId, checked, value, gridId);
        }

        $.fn.jGridContentMVC_onChangeComboBoxCtrl = $.fn.jGridContentMVC_onChangeComboBoxCtrl != null ? $.fn.jGridContentMVC_onChangeComboBoxCtrl : function (cmb) {
            var gridId = $(cmb).attr('gridId');
            var columnId = $(cmb).attr('columnId');
            var ptyKey = $(cmb).attr('ptyKey');
            var ptyValue = $(cmb).attr('ptyValue');
            var key = $(cmb).attr('value');
            var value = cmb.options[cmb.selectedIndex].text;

            var row = $(cmb).attr('iRow');
            var cell = $(cmb).attr('iCell');

            var config = $("#" + gridId).jGridContentMVC_GridStorage[gridId];

            config.storage[row][ptyKey] = key;
            config.storage[row][ptyValue] = value;

            if (config.cellChangeComboBox != null)
                config.cellChangeComboBox(config.storage[row], { 'key': key, 'value': value }, columnId, gridId);
        }

        $.fn.jGridContentMVC_HeadComboBox = $.fn.jGridContentMVC_HeadComboBox != null ? $.fn.jGridContentMVC_HeadComboBox : function (cmb) {
            var gridId = $(cmb).attr('gridId');
            var columnId = $(cmb).attr('columnId');
            var ptyKey = $(cmb).attr('ptyKey');
            var ptyValue = $(cmb).attr('ptyValue');
            var key = $(cmb).attr('value');
            var cell = $(cmb).attr('iCell');

            var value = cmb.options[cmb.selectedIndex].text;

            var config = $("#" + gridId).jGridContentMVC_GridStorage[gridId];

            var cmbc = '.CMBClass' + (gridId + columnId);

            config.columns[cell].HeadValue = key;

            $(cmbc).each(function () {
                this.selectedIndex = cmb.selectedIndex;
            });

            for (var row in config.storage) {
                config.storage[row][ptyKey] = key;
                config.storage[row][ptyValue] = value;
            }

            if (config.HeadComboBox_onChange != null)
                config.HeadComboBox_onChange(columnId, checked, value, gridId);
        }

        $.fn.jGridContentMVC_onChangeDatepicker = $.fn.jGridContentMVC_onChangeDatepicker != null ? $.fn.jGridContentMVC_onChangeDatepicker : function (date) {
            var gridId = $(date).attr('gridId');
            var columnId = $(date).attr('columnId');
            var ptyKey = $(date).attr('ptyKey');
            var ptyValue = $(date).attr('ptyValue');
            var value = $(date).attr('value');
            var row = $(date).attr('iRow');
            var cell = $(date).attr('iCell');

            var config = $("#" + gridId).jGridContentMVC_GridStorage[gridId];

            config.storage[row][ptyKey] = value;
            config.storage[row][ptyValue] = value;

            if (config.Datepicker_onChange != null)
                config.Datepicker_onChange(config.storage[row], columnId, checked, value, gridId);

        }
        /****TextBox****/
        $.fn.jGridContentMVC_onChangeTextBox = $.fn.jGridContentMVC_onChangeTextBox != null ? $.fn.jGridContentMVC_onChangeTextBox : function (text) {
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

            var config = $("#" + gridId).jGridContentMVC_GridStorage[gridId];

            config.storage[row][ptyKey] = value;
            config.storage[row][ptyValue] = value;

            if (config.Datepicker_onChange != null)
                config.Datepicker_onChange(config.storage[row], columnId, checked, value, gridId);

        }
        /*****/

        $.fn.jGridContentMVC_HeadDatepicker = $.fn.jGridContentMVC_HeadDatepicker != null ? $.fn.jGridContentMVC_HeadDatepicker : function (date) {
            var gridId = $(date).attr('gridId');
            var columnId = $(date).attr('columnId');
            var ptyKey = $(date).attr('ptyKey');
            var ptyValue = $(date).attr('ptyValue');
            var value = $(date).attr('value');
            var cell = $(date).attr('iCell');

            var config = $("#" + gridId).jGridContentMVC_GridStorage[gridId];

            var dates = '.jGridContentMVC_DATEPICKER' + (gridId + columnId);

            config.columns[cell].HeadValue = value;

            try {
                $(dates).each(function () {
                    $(this).attr('value', value);
                });
                for (var row in config.storage) {
                    config.storage[row][ptyKey] = value;
                    config.storage[row][ptyValue] = value;
                }

                if (config.HeadDatepicker_onChange != null)
                    config.HeadDatepicker_onChange(columnId, checked, value, gridId);
            } catch (e) {
                alert(e);
            }
        }

        /*******************************************************************************************/

        $.fn.jGridContentMVC_Chks = $.fn.jGridContentMVC_Chks != null ? $.fn.jGridContentMVC_Chks : function () {
            var config = $(this[0]).jGridContentMVC_GridStorage[this[0].id];
            var list = config.storage;
            var ChkBeans = new Array();
            $.each(list, function (i, bean) {
                var obj = '#jGCMVC' + config.gridId + '_chk' + i;
                var chk = $(obj).is(':checked');
                var ChkBean = { 'Index': i, 'Bean': bean, 'Checked': chk };
                ChkBeans.push(ChkBean);
            });
            return ChkBeans;
        }

        $.fn.jGridContentMVC_SetChks = $.fn.jGridContentMVC_SetChks != null ? $.fn.jGridContentMVC_SetChks : function (checked) {
            var config = $(this[0]).jGridContentMVC_GridStorage[this[0].id];
            var list = config.storage;
            var ChkBeans = new Array();

            for (var c = 0; c < config.columns.length; c++) {
                var column = config.columns[c];
                if (column.HeadTypeContent.toUpperCase() == 'CHECKBOX') {
                    column.HeadValue = checked ? true : false;
                    $('#jGridContentMVC_HeadChecked_' + config.gridId + '_' + c).attr('checked', checked);
                }
            }

            $.each(list, function (i, bean) {
                var obj = '#jGCMVC' + config.gridId + '_chk' + i;
                var chk = $(obj).attr('checked', checked);
                var ChkBean = { 'Index': i, 'Bean': bean, 'Checked': checked };
                ChkBeans.push(ChkBean);
            });
            return ChkBeans;
        }

        $.fn.jGridContentMVC_back = $.fn.jGridContentMVC_back != null ? $.fn.jGridContentMVC_back : function () {
            var config = $(this[0]).jGridContentMVC_GridStorage[this[0].id];
            var list = config.storage;
            var ChkBeans = new Array();
            $.each(list, function (i, bean) {
                var chk = $('#jGCMVC' + config.gridId + '_chk' + i);
                var ChkBean = { 'Index': i, 'Bean': bean, 'Checked': chk };
                ChkBeans.push(ChkBean);
            });
            alert(ChkBeans);
        }

        $.fn.jGridContentMVC_Exists = $.fn.jGridContentMVC_Exists != null ? $.fn.jGridContentMVC_Exists : function (prty, value) {
            var config = $(this[0]).jGridContentMVC_GridStorage[this[0].id];
            var exists = false;
            if (config.storage.length > 0)
                for (var i in config.storage) {
                    var bean = config.storage[i];
                    if (bean[prty] == value) {
                        exists = true;
                        break;
                    }
                }
            return exists;
        }

        $.fn.jGridContentMVC_SetConfig = $.fn.jGridContentMVC_SetConfig != null ? $.fn.jGridContentMVC_SetConfig : function (config) {
            $(this[0]).jGridContentMVC_GridStorage[this[0].id] = config;
            $(this[0]).jGridContentMVC_GridDraw(config);
        }

        $.fn.jGridContentMVC_GetConfig = $.fn.jGridContentMVC_GetConfig != null ? $.fn.jGridContentMVC_GetConfig : function () {
            return $(this[0]).jGridContentMVC_GridStorage[this[0].id];
        }

        $.fn.jGridContentMVC_GridDocumentClick = function (GridId, iRow, iCell, columnId) {
            var config = $('#' + GridId).jGridContentMVC_GridStorage[GridId];
            var Bean = config.storage[iRow];
            config.ClickDeleteDocument = false;
            config.ClickDocument = true;
            if (config.documentClick != null)
                config.documentClick(Bean, iRow, iCell, columnId);
            return true;
        }
        $.fn.jGridContentMVC_GridDeleteDocumentClick = function (GridId, iRow, iCell, columnId) {
            var config = $('#' + GridId).jGridContentMVC_GridStorage[GridId];
            var Bean = config.storage[iRow];
            config.ClickDocument = false;
            config.ClickDeleteDocument = true;
            if (config.deleteDocumentClick != null)
                config.deleteDocumentClick(Bean, iRow, iCell, columnId);
            return true;
        }

        $.fn.jGridContentMVC_SetColumns = $.fn.jGridContentMVC_SetColumns != null ? $.fn.jGridContentMVC_SetColumns : function (columns) {
            var config = $(this[0]).jGridContentMVC_GridStorage[this[0].id];
            config.columns = columns;
            $(this[0]).jGridContentMVC_GridDraw(config);
        }
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
        $(wdid).jGridContentMVC_GridStorage[config.gridId] = config;
        if (config.drawIterated == null)
            $(wdid).jGridContentMVC_GridDraw(config);
        else
            $(wdid).jGridContentMVC_GridDrawIterated(config);

    };
})(jQuery);

$.fn.jGridContentMVC.GlobalFormatNumber = {
    decimal: 2,
    separatorDec: '.',
    separatorMil: ',',
    zeroFmt: false
};