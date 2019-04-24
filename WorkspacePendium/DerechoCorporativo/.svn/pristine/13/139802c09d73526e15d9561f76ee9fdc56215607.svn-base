/*!
* Title:  jGridContentMVC 1.0
* Dependencies:  jQuery 1.0 +
* Author:  Tomás Bojórquez Alvarado
* Email:  tomas.bojorquez@kaz-consulting.com
*/

(function($){  
  $.fn.jGridContentMVC = function( config ) {
                      //config.effectlbltxt = false;
          $.fn.jGridContentMVC_GridStorage = $.fn.jGridContentMVC_GridStorage == null ? new Array() : $.fn.jGridContentMVC_GridStorage;      
          
          $.fn.jGridContentMVC_Redraw = $.fn.jGridContentMVC_Redraw != null ? $.fn.jGridContentMVC_Redraw : function(){
              var config = $( this[0] ).jGridContentMVC_GridStorage[ this[0].id ];
              if(config.drawIterated==null)
                  $( this[0] ).jGridContentMVC_GridDraw( config );  
              else
                  $( this[0] ).jGridContentMVC_GridDrawIterated( config );
          }
          
          $.fn.jGridContentMVC_GridDraw = $.fn.jGridContentMVC_GridDraw != null ? $.fn.jGridContentMVC_GridDraw : function(config){
              
              var datepickerCtrlHeadId = new Array();
              var datepickerCtrlId = new Array();
              
              if(config.columnPagination){
           	   //pages:[[0,1,2,3,4,5,6,7],[0,1,2,8,9,10,11]],initPage:0,allColumns:true
           	   if( !$("#"+config.gridId).data('columnPaginationInit') ){
           		   
           		   config.columnPagination.colpages = new Array();
           		   if(config.columnPagination.allColumns)
           			   config.columnPagination.colpages.push(config.columns);
           		   
           		   for(var cps in config.columnPagination.pages){
           			   var page = config.columnPagination.pages[cps];
           			   
           			   var configPage = new Array();
           			   
           			   for(var cpage in page){
           				   for(var c = 0; c < config.columns.length; c++){
               				   if(c == page[cpage]){
               					   configPage.push(config.columns[c]);
               					   break;
               				   }
               			   }
           			   }
           			   config.columnPagination.colpages.push(configPage);                			   
           		   }   
           		   
           		   config.columnPagination.showPage = config.columnPagination.initPage;                		   
           		   $("#"+config.gridId).data('columnPaginationInit',true);
           	   }
           	   
           	   config.columnPagination.showPage = config.columnPagination.showPage >= config.columnPagination.colpages.length ? 0 : config.columnPagination.showPage
           	   config.columns = config.columnPagination.colpages[config.columnPagination.showPage];
              }
              
              if(config.jQSortable){
            	  
            	  var tbl = '<ul class="jGridSortable" id="'+config.gridId+'_sortable">';
            	  
            	  for(var i = 0; i < config.storage.length; i++){
            		 var bean = config.storage[i];
            		 bean.iRow = i;
            		 var li = '<li id="'+config.gridId+'_sortable_li_'+i+'" gridId="'+config.gridId+'" iRow="'+i+'" class="ui-state-default '+config.gridId+'_sortable jGridSortable_li">';
            		  for(var c = 0; c < config.columns.length; c++){
                          var column = config.columns[c];
                          switch(column.id.toLowerCase()){
                          	case 'del':
                          		li+= '<span iRow="'+i+'" iParent="'+config.gridId+'_sortable_del" title="eliminar" class="ui-icon ui-icon-circle-close '+config.gridId+'_sortable_del"></span>';
                          		break;
                          	default:
                          		li+=bean[column.id]+'&nbsp;';
                          		break;
                          }                          
                          
            		  }  
            		  li+='</li>';
            		  tbl+=li;
            	  }
            	  
            	  
            	  tbl+='</ul>';
            	  $( '#'+config.gridId ).html( tbl );
            	  
            	  $( '#'+config.gridId+'_sortable' ).sortable();
            	  $( '#'+config.gridId+'_sortable' ).disableSelection();
            	  $("."+config.gridId+'_sortable_del').click(function(event){            		  
            		  var irow = $(this).attr('iRow');            		 
            		  var id = $(this).attr('iParent');
            		  id = id.replace('_sortable_del','_sortable_li_'+irow);
            		  gridId = id.replace('_sortable_li_'+irow,'');
            		  var config = $( "#"+gridId ).jGridContentMVC_GridStorage[ gridId ];                    
                      var auxBean = null;
            		  
            			  for(var index in config.storage){
            				  if(config.storage[index].iRow==irow){
            					  auxBean = config.storage[index];
            					  config.storage.splice(index,1);
            					  break;
            				  }
            			  }
            		  $("#"+id).remove();
            		  if(config.eRemove){
            			  config.eRemove(irow,auxBean,config.gridId,event);
            		  }
            	  });
            	  return;
              }
              
          //var nameTextLblEffect='';
              config.storage = config.storage == null ? new Array() : config.storage;
              var tblFinRow = "";
              var tbl = '<table';
              tbl += ' id="Tbl'+config.gridId+'"';
              tbl += config.cellpadding == null ? '' : ' cellpadding="'+config.cellpadding+'"';
              tbl += config.cellspacing == null ? '' : ' cellspacing="'+config.cellspacing+'"';
              tbl += config.rules == null ? '' : ' rules="'+config.rules+'"';
              tbl += config.border == null ? '' : ' border="'+config.border+'"';
              
              tbl += config.tblCssClass != null ? ' class="'+config.tblCssClass+'"' : '';
              tbl += config.tblStyle != null ? ' style="'+config.tblStyle+'"' : '';
              tbl += ' id="GridTbl'+config.gridId+'"';
              tbl += '>';             
              
              if(config.Heads){
                  if (config.Encabezados)
                  {
                      tbl += '<thead><tr ';
                      tbl += config.HeadCss == null ? '' : ' class="'+config.HeadCss+'"';
                      tbl += config.HeadAlign == null ? ' align="center"' : ' align="'+config.HeadAlign+'"';
                      tbl += '>'
                      for(var c = 0; c < config.columns.length; c++){
                          var column = config.columns[c];
                          if (column.colspan != null)
                          {
                              var td = '<th';
                              td += ' colspan="' + column.colspan + "'";
                                td += column.width == null ? '':' style="width:'+ column.width +'"' ;
                                td += column.HeadTitle == null ? '' : ' title="'+ column.HeadTitle +'"';
                                td += '>'                             
                            td += column.Encabezado;                                    
                                td += '</th>';
                                tbl += td;
                          }
                      }
                      tbl += '</tr></thead>';
                  }             
                  tbl += '<thead><tr id="rowHead_'+config.gridId+'" ';
                  tbl += config.HeadCss == null ? '' : ' class="'+config.HeadCss+'"';
                  tbl += config.HeadAlign == null ? ' align="center"' : ' align="'+config.HeadAlign+'"';
                  tbl += '>'
                   for(var c = 0; c < config.columns.length; c++){
                      var column = config.columns[c];
                      if( column.security != null ){
				        if(!column.security(column.actionKey,column,config.storage,config.gridId,config,true))
				            continue;
				        }
                      var td = '<th align="center" ';
                            td += column.width == null ? '':' style="width:'+ column.width +'"' ;
                            td += '>';
                            column.HeadTypeContent = column.HeadTypeContent == null ? 'DEFAULT' : column.HeadTypeContent;
                                switch( column.HeadTypeContent.toUpperCase() ){
                                    /** checkbox controller 
                                    * HeadTypeContent:'CHECKBOX'                            Define como Cabecera con controlador checkbox
                                    * HeadCheckBox_onChange(columnId,checked,value,gridId)  Delegado para el evento onChange de cualquier encabezado de tipo CHECKBOX
                                    * HeadText                                              Contiene el valor para "value"
                                    */
                                    case 'CHECKBOX':
                                        column.HeadValue = column.HeadValue == null ? false : column.HeadValue;
                                        var checked = column.HeadValue ? 'checked="checked"' : '';
                                        var onChange = '$(\'#'+config.gridId+'\').jGridContentMVC_HeadChecked(this)';
                                        td += '<label>'+column.HeadText+'</label><br/><input id="jGridContentMVC_HeadChecked_'+config.gridId+'_'+c+'" iCell="'+c+'" gridId="'+config.gridId+'" columnId="'+column.id+'" type="checkbox" value="'+column.HeadText+'" onClick="'+onChange+'" '+checked+'/>';
                                        break;
                                    case 'COMBOBOX':
                                        column.HeadValue = column.HeadValue == null ? '-1' : column.HeadValue;
                                        
                                        var onChange = '$(\'#'+config.gridId+'\').jGridContentMVC_HeadComboBox(this)';
                                        var workArgs = 'gridId="'+config.gridId+'" columnId="'+column.id+'" ptyValue="'+column.valueConfig.ptyValue+'" ptyKey="'+column.valueConfig.ptyKey+'" iCell="'+c+'"';                                       
                                        var options = '';
                                        for(var iCmbItem in column.valueConfig.list){
                                            var cmbItem = column.valueConfig.list[iCmbItem];
                                            var select = cmbItem.key==column.HeadValue ? 'selected="selected"' : '';
                                         options += '<option value="'+cmbItem.key+'" '+select+'>'+cmbItem.value+'</option>';
                                            }
                                    td+='<label>'+column.HeadText+'</label><br/>';                                    
                                    td+='<select '+workArgs+' onChange="'+onChange+'" style="'+column.valueConfig.style+'">'+options+'</select>';
                                        break;
                                    case 'DATEPICKER':
                                        column.HeadValue = column.HeadValue == null ? '' : column.HeadValue;
                                        var onChange = '$(\'#'+config.gridId+'\').jGridContentMVC_HeadDATEPICKER(this)';
                                        var workArgs = 'gridId="'+config.gridId+'" columnId="'+column.id+'" ptyValue="'+column.valueConfig.ptyValue+'" ptyKey="'+column.valueConfig.ptyKey+'" iCell="'+c+'"';                                       
                                        var datepickerId = 'jGridContentMVC_HeadDATEPICKER'+(config.gridId+column.id);
                                        td+='<p>'+column.HeadText+'<input id="'+datepickerId+'" type="text" value="'+column.HeadValue+'" size="12" maxlength="12" '+workArgs+' style="'+column.valueConfig.style+'"/></p>'
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
              for(var i = 0; i < config.storage.length; i++){
            	  var RowCSS = '';
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
                               var eventView = ' onclick="$(\'#'+config.gridId+'\').jGridContentMVC_GridRowDblClickRowDetail( \''+config.gridId+'\',\''+idRowDetail+'\',\''+ condition.IdClass + '\',\'' + bean[condition.idPty] + '\',\'' + Namegrid + '\' )"';
                               tr += eventView;
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
                	   RowCSS = config.rowCssA  == null ? '' : (( (i+1) % 2)==0 ? config.rowCssA : config.rowCssB );
                       tr += config.rowCssA  == null ? '' : ' class="'+ (( (i+1) % 2)==0 ? config.rowCssA : config.rowCssB ) + '"';
                   }else{
                       if(i == (config.storage.length-1)){
                    	   RowCSS = config.rowCssA  == null ? config.rowFinal : (( (i+1) % 2)==0 ? config.rowCssA : config.rowCssB ) + ' '+config.rowFinal;
                           tr += config.rowCssA  == null ? ' class="'+config.rowFinal+'"' : 'class="'+ (( (i+1) % 2)==0 ? config.rowCssA : config.rowCssB ) + ' '+config.rowFinal+'"';
                           flagTRFinal = true;
                       }else
                    	   RowCSS = config.rowCssA  == null ? '' : (( (i+1) % 2)==0 ? config.rowCssA : config.rowCssB );
                           tr += config.rowCssA  == null ? '' : ' class="'+ (( (i+1) % 2)==0 ? config.rowCssA : config.rowCssB ) + '"';                     
                       }
                   
                   tr += config.rowTooltip == null ? '' : ' title="'+config.rowTooltip+'" ';
                   
                   tr += '>';
                                      
                   
                   for(var c = 0; c < config.columns.length; c++){
                       var column = config.columns[c];
                       if( column.security != null ){
				        if(!column.security(column.actionKey,column,bean,config.gridId,config,false))
				            continue;
				        }   
                        
                       var td = '<td'; 
                        
                        if(column.cellValue=='SelectionClass')
                            td += ' id="td' + config.gridId + '_select' + c + '"';
                            
                        td += column.width == null ? '':' style="width:'+ column.width; 
                        td += column.indent == null ? '"': ';text-indent:' + column.indent + '"';
                        td += column.align == null ? '':' align="'+column.align+'"';
					    td += column.cssClass == null ? '':' class="'+column.cssClass+'"';
					    td += column.type == null ? '' : column.type == 'action' ? ' onClick="$(\'#'+config.gridId+'\').jGridContentMVC_GridCellClick( \''+config.gridId+'\',\''+i+'\',\''+c+'\',this,event)" style="cursor:pointer"' : '' ;										
					    td += column.titleBean == null ? '':' title="'+ bean[column.titleBean] +'"';
					    //td += column.idtd == null ? '':' id="'+c+'"td';
                        
                        
                        if (config.RowDetail == null)
                        {
                            td += column.cssClass == null ? '':' class="'+column.cssClass+'"';
                        }
                        else
                        {
                            if (inRowDetail)
                            {
                                td += column.cssClass == null ? '':' class="'+column.cssClass+'"';
                            }
                        }
                        
                        if(config.RowID != null){         
                            var condition = config.RowID.condition[icond];
                           if( bean[condition.idPty]==condition.value ){          
                               var idRowDetail='td' + config.gridId+'RowDetail'+ i + c;
                               td += ' id="' + idRowDetail + '" ';
                               td += config.rowCssA  == null ? '' : 'style="background-color:' + (( (i+1) % 2)==0 ? '#efefff;' : 'White' ) + '"';                   
                           }                 
                       }
                        td += column.title == null ? '':' title="'+column.title+'"';
                        td += column.valign == null ? '':' valign="'+column.valign+'"';          
                        
                        td += column.type == null ? '' : column.type == 'action' && !(flagTRFinal && config.rowFinal != null) && !(flagTRInit && config.rowInitNonClick != null) ? ' onClick="$(\'#'+config.gridId+'\').jGridContentMVC_GridCellClick( \''+config.gridId+'\',\''+i+'\',\''+c+'\')" style="cursor:pointer"' : '' ;                                                
                        td += '>';
                        
                        column.setMaxHeight = column.setMaxHeight == null ? true : column.setMaxHeight;
                        if(config.rowMaxHeight && column.setMaxHeight)
                        	td+='<div style="max-height:'+config.rowMaxHeight+'; overflow-x: auto;">';
                        
                        if(column.cellValue==null){  
                             if(flagTRFinal){
                             if(config.HasTextBoxLastEffect){
                             if(c == config.HasTextBoxLastEffectColumn){
                                /*nameTextLblEffect='jGCMVC'+config.gridId+'_lbl'+i;
                                td+='<label style="cursor:pointer;" id="'+nameTextLblEffect+'" class="tablesorterlabel">'+ bean[column.id] + '</label>';
                                */
                                config.nameTextBoxEffect = 'jGCMVC'+config.gridId+'_txt'+i;
                                td+='<input id="'+config.nameTextBoxEffect+'" type="textbox" style="display:inline;width:100%;" onBlur="$(\'#'+config.gridId+'\').jGridContentMVC_GridTextBoxLostFocusValue( \''+config.gridId+'\',\''+config.nameTextBoxEffect+'\',\''+i+'\')" />';
                                }else{                           
                                td+=bean[column.id];
                                }
                             }
                             else{
                             
                          if (column.id.constructor.toString().indexOf( "Array" ) == -1 ) 
                                if (column.height != null)
                                {
                                    if (bean[column.id].length > column.height)
                                        td+=bean[column.id].substring(0,column.height) + '...';
                                    else
                                        td+=bean[column.id];
                                }
                                else{
                                	var value = bean[column.id];
                                    
                                	if((value=='undefined' || !value) && column.isUndefined)
                                		value = column.isUndefined ? bean[column.isUndefined] : value;
                                	
                                	value = value == null || value == 'null' ? '' : value;	
                                	
                                	td+=value;
                                	
                                }else{
                                                    for(var iid = 0; iid < column.id.length; iid++){
                                                         td+=bean[column.id[iid] ];
                                                         td+= column.id.length==1 ? '' : iid==(column.id.length-1) ? '' : column.betweenValue == null ? " " : column.betweenValue;
                                                    }
                                }
                             }
                        }else{             
                        if (column.id.constructor.toString().indexOf( "Array" ) == -1 )   
                        {
                            if (column.height != null)
                            {
                                if (bean[column.id].length > column.height)
                                    td+=bean[column.id].substring(0,column.height) + '...';
                                else
                                    td+=bean[column.id];
                            }
                            else{
                            	var value = bean[column.id];
                                
                            	if((value=='undefined' || !value) && column.isUndefined)
                            		value = column.isUndefined ? bean[column.isUndefined] : value;
                            	
                            	value = value == null && column.isNull ? column.isNull : value;	
                            	
                            	td+=value;
                            }
                        }
                            else
                                for(var iid = 0; iid < column.id.length; iid++){
                                     td+=bean[column.id[iid] ];
                                     td+= column.id.length==1 ? '' : iid==(column.id.length-1) ? '' : column.betweenValue == null ? " " : column.betweenValue;
                                }
                        }}else if(column.cellValue=='_chk2'){
                            
                            var namecheck = 'jGCMVC'+config.gridId+'_chks'+i;                    
                            var disabledChek = column.disabled;
                            
                                if(bean[column.id] == 2){                                
                                    td+='<input ' + disabledChek + ' id="'+namecheck+'" type="checkbox" checked onClick="$(\'#'+config.gridId+'\').jGridContentMVC_GridChecked( \''+config.gridId+'\',\''+namecheck+'\',\''+i+'\',\''+c+'\',\''+column.id+'\')" />';
                                }else{
                                    td+='<input ' + disabledChek + ' id="'+namecheck+'" type="checkbox" onClick="$(\'#'+config.gridId+'\').jGridContentMVC_GridChecked( \''+config.gridId+'\',\''+namecheck+'\',\''+i+'\',\''+c+'\',\''+column.id+'\')" />';
                                }
                            
                        }else if(column.cellValue=='_chk3'){
                                if(bean[column.id] == 2){                                
                                    td+="SI";
                                }else{
                                    td+="NO";
                                }                        
                        }else if(column.cellValue=='_chk'){
                            var namecheck = 'jGCMVC'+config.gridId+'_chk'+i;                     
                                if(bean[column.id] == 1){                                
                                    td+='<input id="'+namecheck+'" type="checkbox" checked onClick="$(\'#'+config.gridId+'\').jGridContentMVC_GridChecked( \''+config.gridId+'\',\''+namecheck+'\',\''+i+'\',\''+c+'\',\''+column.id+'\')" />';
                                }else{
                                    td+='<input id="'+namecheck+'" type="checkbox" onClick="$(\'#'+config.gridId+'\').jGridContentMVC_GridChecked( \''+config.gridId+'\',\''+namecheck+'\',\''+i+'\',\''+c+'\',\''+column.id+'\')" />';
                                }
                            
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
                        td+='<input id="'+datepickerId+'" class="'+cssClass+' '+cssCtrl+'" type="text" value="'+value+'" size="12" maxlength="10" '+workArgs+' style="'+column.valueConfig.style+'"/>'
                                                                              
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
                            td+='<input id="'+textboxId+'" type="textbox" value="'+value+'" size="8" maxlength="12" '+workArgs+' style="'+column.valueConfig.style+';border:0px;border-style:solid;border-color:#EFEFFF; font-family: Arial;font-size: 11px;" onChange="'+onChange+'"/>'//onkeyup ='+BSM_ExpensesNew.FmtMoneda(value)+'
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

                        }else if(column.cellValue=='InputHTML'){
                                //ObjInputHTML type,name,id,text,value. Datos literales
                                //ObjInputHTML type,#name,#id,#text,#value. Datos dinamicos
                                //cellValue:'InputHTML',ObjInputHTML:{'type':'checkbox','name':'sPKGuestFilter_grid6_0_ChkCIiIdx',id:0,text:'hola'}
                                if(column.ObjInputHTML.type!=null){
                                    td+='<input type="'+column.ObjInputHTML.type+'" ';
                                    if(column.ObjInputHTML.name!=null){
                                      if(column.ObjInputHTML.name.indexOf("#")==0){
                                              if(column.ObjInputHTML.name.length>1){
                                                  td+=' name="'+bean[column.ObjInputHTML.name.substring(1, (column.ObjInputHTML.name).length)]+'"';
                                              }
                                      }else{
                                          td+=' name="'+column.ObjInputHTML.name+'"';
                                      }
                                    }
                                    if(column.ObjInputHTML.id!=null){
                                      if(column.ObjInputHTML.id.indexOf("#")==0){
                                              if(column.ObjInputHTML.id.length>1){
                                                  td+=' id="'+bean[column.ObjInputHTML.id.substring(1, (column.ObjInputHTML.id).length)]+'"';
                                              }
                                      }else{
                                          td+=' id="'+column.ObjInputHTML.id+'"';
                                      }                                   
                                    }          
                                   if(column.ObjInputHTML.type=='checkbox'){
                                       if(bean[column.id] == 1){
                                            td+=' checked';
                                        }
                                   }
                                   if(column.ObjInputHTML.value!=null){
                                       if(column.ObjInputHTML.value.indexOf("#")==0){
                                               if(column.ObjInputHTML.value.length>1){
                                                   td+=' value="'+bean[column.ObjInputHTML.value.substring(1, (column.ObjInputHTML.value).length)]+'"';
                                               }
                                       }else{
                                           td+=' value="'+column.ObjInputHTML.value+'"';
                                       }
                                   }                                
                                    td+='>'
                                }
                              if(column.ObjInputHTML.text!=null){
                                  if(column.ObjInputHTML.text.indexOf("#")==0){
                                          if(column.ObjInputHTML.text.length>1){
                                              td+=bean[column.ObjInputHTML.text.substring(1, (column.ObjInputHTML.text).length)];
                                          }
                                  }else{
                                      td+=column.ObjInputHTML.text;
                                  }
                              }  
                                
    //                               column.cellValueName;
    //                            var namecheck = 'jGCMVC'+config.gridId+'_chk'+i;                     
    //                               if(bean[column.id] == 1){                                
    //                                   td+='<input id="'+namecheck+'" type="checkbox" checked onClick="$(\'#'+config.gridId+'\').jGridContentMVC_GridChecked( \''+config.gridId+'\',\''+namecheck+'\',\''+i+'\',\''+c+'\',\''+column.id+'\')" />';
    //                               }else{
    //                                   td+='<input id="'+namecheck+'" type="checkbox" onClick="$(\'#'+config.gridId+'\').jGridContentMVC_GridChecked( \''+config.gridId+'\',\''+namecheck+'\',\''+i+'\',\''+c+'\',\''+column.id+'\')" />';
    //                               }
                            
                        }else if(column.cellValue=='_doubleicondocument' && column.type == 'actiondouble'){
                           var cssClass = 'ui-icon ui-icon-trash';
                           if(bean[column.id]==1){
                              td+='<div><div class="divLeft"><div style="cursor:pointer;" class="'+column.cssRangeEq.list[0].cssClass+'" onClick="$(\'#'+config.gridId+'\').jGridContentMVC_GridDocumentClick( \''+config.gridId+'\',\''+i+'\',\''+c+'\',\''+column.id+'\')"></div></div>';
                              td+='<div class="divRight"><div style="cursor:pointer;" class="'+cssClass+'" onClick="$(\'#'+config.gridId+'\').jGridContentMVC_GridDeleteDocumentClick( \''+config.gridId+'\',\''+i+'\',\''+c+'\',\''+column.id+'\')"></div></div></div>';
                           }else if(bean[column.id]==0){
                              td+='<div><div class="divLeft"><div style="cursor:pointer;" class="'+column.cssRangeEq.list[1].cssClass+'" onClick="$(\'#'+config.gridId+'\').jGridContentMVC_GridDocumentClick( \''+config.gridId+'\',\''+i+'\',\''+c+'\',\''+column.id+'\')"></div></div>';
                              td+='<div class="divRight"><div style="cursor:pointer;" class="'+cssClass+'" onClick="$(\'#'+config.gridId+'\').jGridContentMVC_GridDeleteDocumentClick( \''+config.gridId+'\',\''+i+'\',\''+c+'\',\''+column.id+'\')"></div></div></div>';
                           } 
                       }else if(column.cellValue=='Parent'){
                           var cssClass = 'ui-icon ui-icon-circle-plus';
                           var valueCell = parseInt(bean[column.id]);
                           if(valueCell == 0)
                               cssClass = 'ui-icon ui-icon-circle-minus';
                           td+='<div id="divParent' + i + '" style="cursor:pointer;" class="'+cssClass+'"></div>';
                       }else if(column.cellValue=='Bullet'){
                           if(bean[column.id] == 1)
                               td+= '<img src="images/indicators/Ind'+ bean[column.id] +'.png" />';
                        }else if(column.cellValue=='eval'){
					        td+= column.eval( bean,column.id,column,c,i,config,RowCSS );//eval=function( bean,id,column,iCell,iRow,config,RowCSS )
					    }else if(column.type == 'path'){
                            td += '<a title="download" target="_blank" href="'+bean[column.id]+'">'+column.cellValue+'</a>';
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
                        else if(column.cssRange!=null){
                            var valueCellR = bean[column.id];
                            var valueCell = parseInt(bean[column.id]);
                                valueCell = isNaN(valueCell) ? 0 : valueCell;
                            var cssRangeClass = "";
                           
                            var valueCellid = bean.Concept;

                            for(var cssRi in column.cssRange){//[{linf:0,lsup:100,cssClass:'ValueOK'},{linf:101,lsup:null,cssClass:'ValueBad'}]}
                                if((valueCellid == 'TOTAL $')||(valueCellid == 'TARGET $'))
                                 {
                                        if(valueCellid == 'VAR $')
                                        {
                                             if(column.cssRange[cssRi].lsup != null){
                                                    if(valueCell >= column.cssRange[cssRi].linf && valueCell <= column.cssRange[cssRi].lsup){
                                                        cssRangeClass = column.cssRange[cssRi].cssClass;
                                                        break;
                                                    }
                                                    if( valueCell >= column.cssRange[cssRi].linf ){
                                                        cssRangeClass = column.cssRange[cssRi].cssClass;
                                                        break;
                                                    }
                                               }
                                        }
                                 }
                                 else
                                 {
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
                            }
                            td+='<div class="'+cssRangeClass+'">'+valueCellR+'</div>'
                        }else if(column.cssRangeEq!=null){
                            /*if(flagTRFinal){
                                 if(config.HasTextBoxLastEffect){
                                     }
                                }
                             else{*/
                                    var valueCell = bean[column.id];                                                       
                                    var cssRangeClass = "";
                                    for(var cssRi in column.cssRangeEq.list){//{cssDefault:'',list:[{Eq:,cssClass:'ValueOK'},{linf:101,lsup:null,cssClass:'ValueBad'}]}                                
                                            cssRangeClass = column.cssRangeEq.cssDefault;
                                            if(valueCell == column.cssRangeEq.list[cssRi].Eq){
                                                cssRangeClass = column.cssRangeEq.list[cssRi].cssClass;
                                                break;
                                            }                         
                                        }
                                        if(column.cursorIcon)
                                            td+='<div style="cursor:pointer;" class="'+cssRangeClass+'">'+valueCell+'</div>'
                                        else
                                            td+='<div class="'+cssRangeClass+'">'+valueCell+'</div>'
                             //}
                        }else if(column.cellValue=='SelectClass'){
                            var SelectClassValue = bean[column.id];
                            //alert(SelectClassValue);
                            for(var iSClass in column.SelectClass){
                                if(bean[column.id].length == 2){
                                    SelectClassValue='<div style="width:100%; height:100%;" class="tdcolorgris">&nbsp</div>';
                                    break;
                                }
                                if(bean[column.id] == column.SelectClass[iSClass].Value){
                                    SelectClassValue='<div style="width:100%; height:100%;" class="'+column.SelectClass[iSClass].Class+'">&nbsp</div>';
                                    break;
                                }
                            }
                            td+=SelectClassValue;
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
                        }else if(column.cellValue=='SelectClasse'){
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
					}
                        else if(column.cellValue=='EvalFunction'){
                            td+=column.EvalFunction( bean,column,i,c,config );

                        }else if(column.cellValue=='SelectionClass'){
                            if (column.id.constructor.toString().indexOf( "Array" ) == -1 )                          
                                td+=bean[column.id];
                            else
                                for(var iid = 0; iid < column.id.length; iid++){
                                     td+=bean[column.id[iid] ];
                                     td+= column.id.length==1 ? '' : iid==(column.id.length-1) ? '' : column.betweenValue == null ? " " : column.betweenValue;
                                }
                        }else if(column.cellValue=='ImageIndicator'){
                            td+= '<img src="images/indicators/Ind'+ bean[column.id] +'.png" />';
                        }else if(column.cellValue=='ImageFile'){
                            td+='<div class="ui-icon ui-icon-document" />' ;
                        }else if(column.cellValue=='ValueImageIndicator'){
                             //td+='<table width="100%" cellpadding="0" cellspacing="0" border="0"><tr align="right"><td>' + bean[column.id[0]] + '</td><td><img src="images/indicators/Ind' + bean[column.id[1]] + '.png" /></td></tr></table>'; 
                            td+='<div style="width:100%;float: right;">'+ bean[column.id[0]] +'<img src="images/indicators/Ind'+ bean[column.id[1]] +'.png" style="margin-left: 10px;"/></div>';
                        }else if(column.cellValue == 'iconLower'){
                            var valueCell = bean[column.id];                                                      
                            var cssRangeClass = 'ui-icon ui-icon-arrowthick-1-s';

                            td+='<div class="'+cssRangeClass+'">'+valueCell+'</div>'
                        }else if(column.cellValue == 'icon'){
                            var valueCell = bean[column.id];                                                      
                            var cssRangeClass = 'ui-icon ui-icon-check';

                            td+='<div class="'+cssRangeClass+'">'+valueCell+'</div>'
                        }else if(column.cellValue == 'iconAction'){
                            if(flagTRFinal){
                             if(config.HasTextBoxLastEffect){
                                    
                                     }
                                 else{
                                        var cssClass = 'ui-icon '+column.iconAction;
                                        if(column.cursorIcon)
                                            td+='<div style="cursor:pointer;" class="'+cssClass+'"></div>';
                                        else
                                        td+='<div class="'+cssClass+'"></div>';
                                }
                                }
                             else{
                                   var cssClass = 'ui-icon '+column.iconAction;
                                    if(column.cursorIcon)
                                        td+='<div style="cursor:pointer;" class="'+cssClass+'"></div>';
                                    else
                                    td+='<div class="'+cssClass+'"></div>';
                            }
                        }else if(column.cellValue == 'ActionCellValue'){
                            td+='<div style="text-decoration:underline; color:blue">'+column.id+'</div>';
                        }else
                            td+=column.cellValue;
                        
                        if(config.rowMaxHeight && column.setMaxHeight)
                        	td+='</div>';
                        td+='</td>';
                        td = td.replace('null','');
                        tr+=td;
                   }             
                   tr+='</tr>';
                   if(config.RowDetail!=null){//(config {..., RowDetail:{condition:[{value:'X',idPty:'',idGrid=''},...]
                            for( icond in config.RowDetail.condition ){
                                var condition = config.RowDetail.condition[icond];
                                if( bean[condition.idPty]!=condition.value ){                                  
                                    //var RowDetail = '<tr id="'+config.gridId+'RowDetail'+ i +'" style="display:none"><td colspan="'+config.columns.length+'"><div id="'+config.gridId+'RowDetail'+ i +'Content" style="width:100%;margin-left:.5cm;"></div></td></tr>';                         
                                    var RowDetail = '<tr id="'+config.gridId+'RowDetail'+ i +'" style="display:none"><td colspan="'+config.columns.length+'"><div id="'+config.gridId+'RowDetail'+ i +'Content" style="width:100%;"><div id="'+condition.idGrid + i +'" style="width:100%;"></div></div></td></tr>';                         
                                    tr+=RowDetail;
                                    break;
                                }
                            }
                        }
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
              config.showHeadigns = config.showHeadigns == null ? true : config.showHeadigns;
              config.showNoDataMsg = config.showNoDataMsg == null ? false : config.showNoDataMsg;
              config.noDataMsg = !config.showNoDataMsg ? '' : config.noDataMsg == null ? 'no hay registros para mostrar' : config.noDataMsg;
              tbl = config.storage.length > 0 ? tbl : ((config.showHeadigns ? tbl : '') + config.noDataMsg);
              tbl += config.rowFinalSeparate != null ? tblFinRow : "";
              $( '#'+config.gridId ).html( tbl );
              if(config.afterDrawing){
            	  config.afterDrawing(config);
              }
              
              $("#Tbl"+config.gridId).data('ready',true);
              
              if(config.columnPagination){
            	  $('#rowHead_'+config.gridId).attr('title','click para ver la siguientes lista de columnas').css('cursor','pointer');
            	  $('#rowHead_'+config.gridId).click(function(){
            		  var gridId = this.id.replace('rowHead_','');
            		  var config = $( '#'+gridId ).jGridContentMVC_GridStorage[ gridId ];
            		  config.columnPagination.showPage++;
            		  $( '#'+gridId ).jGridContentMVC_GridDraw( config );
            	  });
              }
              
                  for(var iDCHId in datepickerCtrlHeadId){
                      var id = datepickerCtrlHeadId[iDCHId];
                      $("#"+id).datepicker({
                          dateFormat:'dd/mm/yy'
                        ,showOn: "button"
                        ,buttonImage: "images/iconDatePicker.gif"
                        ,buttonImageOnly: true
                          ,showOtherMonths: true
                          ,selectOtherMonths: true
                        ,onSelect: function(){
                            var gridId = $(this).attr('gridId');
                            $( "#"+gridId ).jGridContentMVC_HeadDatepicker(this);
                            }
                        });
                    }
                  
                  for(var iDCHId in datepickerCtrlId){
                      var id = datepickerCtrlId[iDCHId];
                      $("#"+id).datepicker({
                          dateFormat:'dd-mm-yy'
                        //,showOn: "button"
                        //,buttonImage: "images/iconDatePicker.gif"
                        //,buttonImageOnly: true
                          ,showOtherMonths: true
                          ,selectOtherMonths: true
                        ,onSelect: function(){
                            var gridId = $(this).attr('gridId');
                            $( "#"+gridId ).jGridContentMVC_onChangeDatepicker(this);
                            }
                        });
                        }
          }
          
          $.fn.jGridContentMVC_GridDrawIterated = $.fn.jGridContentMVC_GridDrawIterated != null ? $.fn.jGridContentMVC_GridDrawIterated : function(config){
          
          //var nameTextLblEffect='';
              config.storage = config.storage == null ? new Array() : config.storage;
              var tblFinRow = "";
              var tbl = '<table';
              tbl += ' id="Tbl'+config.gridId+'"';
              tbl += config.cellpadding == null ? '' : ' cellpadding="'+config.cellpadding+'"';
              tbl += config.cellspacing == null ? '' : ' cellspacing="'+config.cellspacing+'"';
              tbl += config.rules == null ? '' : ' rules="'+config.rules+'"';
              tbl += config.border == null ? '' : ' border="'+config.border+'"';
              
              tbl += config.tblCssClass != null ? ' class="'+config.tblCssClass+'"' : '';
              tbl += config.tblStyle != null ? ' style="'+config.tblStyle+'"' : '';
              tbl += ' id="GridTbl'+config.gridId+'"';
              tbl += '>';             
              
              if(config.Heads){
                  if (config.Encabezados)
                  {
                      tbl += '<thead><tr';
                      tbl += config.HeadCss == null ? '' : ' class="'+config.HeadCss+'"';
                      tbl += config.HeadAlign == null ? ' align="center"' : ' align="'+config.HeadAlign+'"';
                      tbl += '>'
                      for(var c = 0; c < config.columns.length; c++){
                          var column = config.columns[c];
                          if (column.colspan != null)
                          {
                              var td = '<th';
                              td += ' colspan="' + column.colspan + "'";
                                td += column.width == null ? '':' style="width:'+ column.width +'"' ;
                                td += column.HeadTitle == null ? '' : ' title="'+ column.HeadTitle +'"';
                                td += '>'
                                td += column.Encabezado;
                                td += '</th>';
                                tbl += td;
                          }
                      }
                      tbl += '</tr></thead>';
                  }             
                  tbl += '<thead><tr';
                  tbl += config.HeadCss == null ? '' : ' class="'+config.HeadCss+'"';
                  tbl += config.HeadAlign == null ? ' align="center"' : ' align="'+config.HeadAlign+'"';
                  tbl += '>'
                  for(var c = 0; c < config.columns.length; c++){
                      var column = config.columns[c];
                      var td = '<th';
                            td += column.width == null ? '':' style="width:'+ column.width +'"' ;
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
              for(var iterate in config.storage){
                  i++;
                   var bean = config.storage[iterate];   
                   if(bean==null)          
                       continue;
                   flagTRInit = i == 0;    
                   flagTRFinal = i == (config.storage.length-1);   
                   var tr = '<tr';                   
                  tr += config.rowClick == null || (flagTRFinal && config.rowFinal != null) || (flagTRInit && config.rowInitNonClick != null) ? '' : ' onClick="$(\'#'+config.gridId+'\').jGridContentMVC_GridRowClick( \''+config.gridId+'\',\''+i+'\' )"';
                   var inRowDetail = false;
                   if(config.RowDetail != null){//(config {..., RowDetail:{condition:[{value:'X',idPty:'',idGrid=''},...]
                            for( icond in config.RowDetail.condition ){
                                var condition = config.RowDetail.condition[icond];
                                if( bean[condition.idPty]==condition.value ){                                  
                                    var idRowDetail=config.gridId+'RowDetail'+ i;
                                    var eventView = ' ondblclick="$(\'#'+config.gridId+'\').jGridContentMVC_GridRowDblClickRowDetail( \''+config.gridId+'\',\''+idRowDetail+'\' )"';
                                    tr += eventView;
                                    inRowDetail = true;
                                    break;
                                }
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
                   
                   for(var c = 0; c < config.columns.length; c++){
                       var column = config.columns[c];                       
                        var td = '<td';    
                        
                        if(column.cellValue=='SelectionClass')
                            td += ' id="td' + config.gridId + '_select' + c + '"';
                            
                        td += column.width == null ? '':' style="width:'+ column.width;                
                        td += column.indent == null ? '"': ';text-indent:' + column.indent + '"';
                        td += column.align == null ? '':' align="'+column.align+'"';
                        if (config.RowDetail == null)
                        {
                            td += column.cssClass == null ? '':' class="'+column.cssClass+'"';
                        }
                        else
                        {
                            if (inRowDetail)
                            {
                                td += column.cssClass == null ? '':' class="'+column.cssClass+'"';
                            }
                        }
                        
                        if(config.RowID != null){         
                            var condition = config.RowID.condition[icond];
                           if( bean[condition.idPty]==condition.value ){          
                               var idRowDetail='td' + config.gridId+'RowDetail'+ i + c;
                               td += ' id="' + idRowDetail + '" ';
                               td += config.rowCssA  == null ? '' : 'style="background-color:' + (( (i+1) % 2)==0 ? '#efefff;' : 'White' ) + '"';                   
                           }                 
                       }
                        td += column.title == null ? '':' title="'+column.title+'"';
                        td += column.valign == null ? '':' valign="'+column.valign+'"';          
                        
                        td += column.type == null ? '' : column.type == 'action' && !(flagTRFinal && config.rowFinal != null) && !(flagTRInit && config.rowInitNonClick != null) ? ' onClick="$(\'#'+config.gridId+'\').jGridContentMVC_GridCellClick( \''+config.gridId+'\',\''+iterate+'\',\''+c+'\')" style="cursor:pointer"' : '' ;                                               
                        
                        td += '>';
     
                        if(column.cellValue==null){  
                             if(flagTRFinal){
                             if(config.HasTextBoxLastEffect){
                             if(c == config.HasTextBoxLastEffectColumn){
                                /*nameTextLblEffect='jGCMVC'+config.gridId+'_lbl'+i;
                                td+='<label style="cursor:pointer;" id="'+nameTextLblEffect+'" class="tablesorterlabel">'+ bean[column.id] + '</label>';
                                */
                                config.nameTextBoxEffect = 'jGCMVC'+config.gridId+'_txt'+i;
                                td+='<input id="'+config.nameTextBoxEffect+'" type="textbox" style="display:inline;width:100%;" onBlur="$(\'#'+config.gridId+'\').jGridContentMVC_GridTextBoxLostFocusValue( \''+config.gridId+'\',\''+config.nameTextBoxEffect+'\',\''+i+'\')" />';
                                }else{                           
                                td+=bean[column.id];
                                }
                             }
                             else{
                             
                          if (column.id.constructor.toString().indexOf( "Array" ) == -1 )                          
                                                    td+=bean[column.id];
                                                else
                                                    for(var iid = 0; iid < column.id.length; iid++){
                                                         td+=bean[column.id[iid] ];
                                                         td+= column.id.length==1 ? '' : iid==(column.id.length-1) ? '' : column.betweenValue == null ? " " : column.betweenValue;
                                                    }
                             }
                        }else{             
                        if (column.id.constructor.toString().indexOf( "Array" ) == -1 )                          
                                td+=bean[column.id];
                            else
                                for(var iid = 0; iid < column.id.length; iid++){
                                     td+=bean[column.id[iid] ];
                                     td+= column.id.length==1 ? '' : iid==(column.id.length-1) ? '' : column.betweenValue == null ? " " : column.betweenValue;
                                }
                        }}else if(column.cellValue=='_chk2'){
                            var namecheck = 'jGCMVC'+config.gridId+'_chk'+i;                     
                                if(bean[column.id] == 2){                                
                                    td+='<input id="'+namecheck+'" type="checkbox" checked onClick="$(\'#'+config.gridId+'\').jGridContentMVC_GridChecked( \''+config.gridId+'\',\''+namecheck+'\',\''+i+'\',\''+c+'\',\''+column.id+'\')" />';
                                }else{
                                    td+='<input id="'+namecheck+'" type="checkbox" onClick="$(\'#'+config.gridId+'\').jGridContentMVC_GridChecked( \''+config.gridId+'\',\''+namecheck+'\',\''+i+'\',\''+c+'\',\''+column.id+'\')" />';
                                }
                            
                        }else if(column.cellValue=='_chk3'){
                                if(bean[column.id] == 2){                                
                                    td+="SI";
                                }else{
                                    td+="NO";
                                }                        
                        }else if(column.cellValue=='_chk'){
                            var namecheck = 'jGCMVC'+config.gridId+'_chk'+i;                     
                                if(bean[column.id] == 1){                                
                                    td+='<input id="'+namecheck+'" type="checkbox" checked onClick="$(\'#'+config.gridId+'\').jGridContentMVC_GridChecked( \''+config.gridId+'\',\''+namecheck+'\',\''+i+'\',\''+c+'\',\''+column.id+'\')" />';
                                }else{
                                    td+='<input id="'+namecheck+'" type="checkbox" onClick="$(\'#'+config.gridId+'\').jGridContentMVC_GridChecked( \''+config.gridId+'\',\''+namecheck+'\',\''+i+'\',\''+c+'\',\''+column.id+'\')" />';
                                }
                            
                        }else if(column.cellValue=='InputHTML'){
                                //ObjInputHTML type,name,id,text,value. Datos literales
                                //ObjInputHTML type,#name,#id,#text,#value. Datos dinamicos
                                //cellValue:'InputHTML',ObjInputHTML:{'type':'checkbox','name':'sPKGuestFilter_grid6_0_ChkCIiIdx',id:0,text:'hola'}
                                if(column.ObjInputHTML.type!=null){
                                    td+='<input type="'+column.ObjInputHTML.type+'" ';
                                    if(column.ObjInputHTML.name!=null){
                                      if(column.ObjInputHTML.name.indexOf("#")==0){
                                              if(column.ObjInputHTML.name.length>1){
                                                  td+=' name="'+bean[column.ObjInputHTML.name.substring(1, (column.ObjInputHTML.name).length)]+'"';
                                              }
                                      }else{
                                         td+=' name="'+column.ObjInputHTML.name+'"';
                                      }
                                    }
                                    if(column.ObjInputHTML.id!=null){
                                      if(column.ObjInputHTML.id.indexOf("#")==0){
                                              if(column.ObjInputHTML.id.length>1){
                                                  td+=' id="'+bean[column.ObjInputHTML.id.substring(1, (column.ObjInputHTML.id).length)]+'"';
                                              }
                                      }else{
                                          td+=' id="'+column.ObjInputHTML.id+'"';
                                      }                                   
                                    }          
                                   if(column.ObjInputHTML.type=='checkbox'){
                                       if(bean[column.id] == 1){
                                            td+=' checked';
                                        }
                                   }
                                   if(column.ObjInputHTML.value!=null){
                                       if(column.ObjInputHTML.value.indexOf("#")==0){
                                               if(column.ObjInputHTML.value.length>1){
                                                   td+=' value="'+bean[column.ObjInputHTML.value.substring(1, (column.ObjInputHTML.value).length)]+'"';
                                               }
                                       }else{
                                           td+=' value="'+column.ObjInputHTML.value+'"';
                                       }
                                   }                                
                                    td+='>'
                                }
                              if(column.ObjInputHTML.text!=null){
                                  if(column.ObjInputHTML.text.indexOf("#")==0){
                                          if(column.ObjInputHTML.text.length>1){
                                              td+=bean[column.ObjInputHTML.text.substring(1, (column.ObjInputHTML.text).length)];
                                          }
                                  }else{
                                      td+=column.ObjInputHTML.text;
                                  }
                              }  
                                
    //                               column.cellValueName;
    //                            var namecheck = 'jGCMVC'+config.gridId+'_chk'+i;                     
    //                               if(bean[column.id] == 1){                                
    //                                   td+='<input id="'+namecheck+'" type="checkbox" checked onClick="$(\'#'+config.gridId+'\').jGridContentMVC_GridChecked( \''+config.gridId+'\',\''+namecheck+'\',\''+i+'\',\''+c+'\',\''+column.id+'\')" />';
    //                               }else{
    //                                   td+='<input id="'+namecheck+'" type="checkbox" onClick="$(\'#'+config.gridId+'\').jGridContentMVC_GridChecked( \''+config.gridId+'\',\''+namecheck+'\',\''+i+'\',\''+c+'\',\''+column.id+'\')" />';
    //                               }
                            
                        }else if(column.cellValue=='_doubleicondocument' && column.type == 'actiondouble'){
                           var cssClass = 'ui-icon ui-icon-trash';
                           if(bean[column.id]==1){
                              td+='<div><div class="divLeft"><div style="cursor:pointer;" class="'+column.cssRangeEq.list[0].cssClass+'" onClick="$(\'#'+config.gridId+'\').jGridContentMVC_GridDocumentClick( \''+config.gridId+'\',\''+i+'\',\''+c+'\',\''+column.id+'\')"></div></div>';
                              td+='<div class="divRight"><div style="cursor:pointer;" class="'+cssClass+'" onClick="$(\'#'+config.gridId+'\').jGridContentMVC_GridDeleteDocumentClick( \''+config.gridId+'\',\''+i+'\',\''+c+'\',\''+column.id+'\')"></div></div></div>';
                           }else if(bean[column.id]==0){
                              td+='<div><div class="divLeft"><div style="cursor:pointer;" class="'+column.cssRangeEq.list[1].cssClass+'" onClick="$(\'#'+config.gridId+'\').jGridContentMVC_GridDocumentClick( \''+config.gridId+'\',\''+i+'\',\''+c+'\',\''+column.id+'\')"></div></div>';
                              td+='<div class="divRight"><div style="cursor:pointer;" class="'+cssClass+'" onClick="$(\'#'+config.gridId+'\').jGridContentMVC_GridDeleteDocumentClick( \''+config.gridId+'\',\''+i+'\',\''+c+'\',\''+column.id+'\')"></div></div></div>';
                           } 
                        }else if(column.type == 'path'){
                            td += '<a title="download" target="_blank" href="'+bean[column.id]+'">'+column.cellValue+'</a>';
                        }else if(column.cssRange!=null){
                            var valueCellR = bean[column.id];
                            var valueCell = parseInt(bean[column.id]);
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
                            /*if(flagTRFinal){
                                     }
                                }
                             else{*/
                                    var valueCell = bean[column.id];                                                       
                                    var cssRangeClass = "";
                                    for(var cssRi in column.cssRangeEq.list){//{cssDefault:'',list:[{Eq:,cssClass:'ValueOK'},{linf:101,lsup:null,cssClass:'ValueBad'}]}                                
                                            cssRangeClass = column.cssRangeEq.cssDefault;
                                            if(valueCell == column.cssRangeEq.list[cssRi].Eq){
                                                cssRangeClass = column.cssRangeEq.list[cssRi].cssClass;
                                                break;
                                            }                         
                                        }
                                        if(column.cursorIcon)
                                            td+='<div style="cursor:pointer;" class="'+cssRangeClass+'">'+valueCell+'</div>'
                                        else
                                            td+='<div class="'+cssRangeClass+'">'+valueCell+'</div>'
                             //}
                        }else if(column.cellValue=='SelectClass'){
                            var SelectClassValue = bean[column.id];
                            for(var iSClass in column.SelectClass){
                                if(bean[column.id] == column.SelectClass[iSClass].Value){
                                    SelectClassValue='<div class="'+column.SelectClass[iSClass].Class+'"></div>';
                                    break;
                                }
                            }
                            td+=SelectClassValue;
                        }else if(column.cellValue=='SelectionClass'){
                            if (column.id.constructor.toString().indexOf( "Array" ) == -1 )                          
                                td+=bean[column.id];
                            else
                                for(var iid = 0; iid < column.id.length; iid++){
                                     td+=bean[column.id[iid] ];
                                     td+= column.id.length==1 ? '' : iid==(column.id.length-1) ? '' : column.betweenValue == null ? " " : column.betweenValue;
                                }
                        }else if(column.cellValue=='ImageIndicator'){
                            td+= '<img src="images/indicators/Ind'+ bean[column.id] +'.png" />';
                        }else if(column.cellValue=='ImageFile'){
                            td+='<div class="ui-icon ui-icon-document" />' ;
                        }else if(column.cellValue=='ValueImageIndicator'){
                             //td+='<table width="100%" cellpadding="0" cellspacing="0" border="0"><tr align="right"><td>' + bean[column.id[0]] + '</td><td><img src="images/indicators/Ind' + bean[column.id[1]] + '.png" /></td></tr></table>'; 
                            td+='<div style="width:100%;float: right;">'+ bean[column.id[0]] +'<img src="images/indicators/Ind'+ bean[column.id[1]] +'.png" style="margin-left: 10px;"/></div>';
                        }else if(column.cellValue == 'iconLower'){
                            var valueCell = bean[column.id];                                                      
                            var cssRangeClass = 'ui-icon ui-icon-arrowthick-1-s';

                            td+='<div class="'+cssRangeClass+'">'+valueCell+'</div>'
                        }else if(column.cellValue == 'icon'){
                            var valueCell = bean[column.id];                                                      
                            var cssRangeClass = 'ui-icon ui-icon-check';

                            td+='<div class="'+cssRangeClass+'">'+valueCell+'</div>'
                        }else if(column.cellValue == 'iconAction'){
                            if(flagTRFinal){
                             if(config.HasTextBoxLastEffect){
                                    
                                     }
                                 else{
                                        var cssClass = 'ui-icon '+column.iconAction;
                                        if(column.cursorIcon)
                                            td+='<div style="cursor:pointer;" class="'+cssClass+'"></div>';
                                        else
                                        td+='<div class="'+cssClass+'"></div>';
                                }
                                }
                             else{
                                    var cssClass = 'ui-icon '+column.iconAction;
                                    if(column.cursorIcon)
                                        td+='<div style="cursor:pointer;" class="'+cssClass+'"></div>';
                                    else
                                    td+='<div class="'+cssClass+'"></div>';
                            }
                        }else
                            td+=column.cellValue;
                        td+='</td>';
                        tr+=td;
                   }             
                   tr+='</tr>';
                   if(config.RowDetail!=null){//(config {..., RowDetail:{condition:[{value:'X',idPty:'',idGrid=''},...]
                            for( icond in config.RowDetail.condition ){
                                var condition = config.RowDetail.condition[icond];
                                if( bean[condition.idPty]==condition.value ){                                  
                                    var RowDetail = '<tr id="'+config.gridId+'RowDetail'+ i +'" style="display:none"><td colspan="'+config.columns.length+'"><div id="'+config.gridId+'RowDetail'+ i +'Content" style="width:100%;margin-left:.5cm;"></div></td></tr>';                         
                                    tr+=RowDetail;
                                    break;
                                }
                            }
                        }
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
              tbl = i > -1 ? tbl : 'no record to display';
              tbl += config.rowFinalSeparate != null ? tblFinRow : "";
              $( '#'+config.gridId ).html( tbl );        
          }
          
          $.fn.jGridContentMVC_GridRowClick = function(GridId,index){
              var config = $( '#'+GridId ).jGridContentMVC_GridStorage[ GridId ];
              if(config.ClickDocument != null && config.ClickDeleteDocument != null){
                  if(config.ClickDocument == true && config.ClickDeleteDocument == false){
                       config.ClickDocument=null;
                      config.ClickDeleteDocument=null;
                  }else if(config.ClickDeleteDocument == true && config.ClickDocument == false){
                       config.ClickDocument=null;
                       config.ClickDeleteDocument=null;                   
                  }else{
                      config.rowClick( index ,config.storage[index],GridId); 
                  }
            }else if(config.ClickCheck != null){
                config.ClickCheck = null;
            }else{
                config.rowClick( index ,config.storage[index],GridId);
            }
              return true;
          }
          $.fn.jGridContentMVC_GridTextBoxLostFocusValue = function(GridId,nameTextBox,iRow){
              var config = $( '#'+GridId ).jGridContentMVC_GridStorage[ GridId ];          
              
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
              if(config.cellClick != null)
                config.cellTextBoxLostFocusValue(config.storage[iRow],$('#'+nameTextBox)[0].value,iRow);
              return true;
          }
          
          $.fn.jGridContentMVC_GridChecked = function(GridId,namecheck,iRow,iCell,columnId){
              var config = $( '#'+GridId ).jGridContentMVC_GridStorage[ GridId ];     
              var FlagChecked = $('#'+namecheck)[0].checked;
              var Bean = config.storage[iRow];
              if (FlagChecked){
                  Bean[columnId] = 1;
               }else{
                  Bean[columnId] = 0;
              }
              config.ClickCheck = true;
              if(config.cellCheckedValue!=null)          
                config.cellCheckedValue(Bean,FlagChecked,iRow,iCell,GridId, config.columns[iCell].id);
              return true;
          }
          
          $.fn.jGridContentMVC_GridRowDblClickRowDetail = function(GridId,idRowDetail){
              debugger;
              if( $("#"+idRowDetail).css('display')=='none' )
                  $("#"+idRowDetail).fadeIn('slow');                
              else
                  $("#"+idRowDetail).fadeOut('slow');
                            
              return true;
          }
          
          $.fn.jGridContentMVC_GridRowDblClickRowDetail = function(GridId, idRowDetail, IdClass, idRequest, GridParent){
              var div = "#divParent" + idRowDetail.replace("gridRequestsEventsRowDetail","") ;
              if( $("#"+idRowDetail).css('display')=='none' )
              {
                  $("#"+idRowDetail).fadeIn('slow');   
                  $(div).removeClass().addClass('ui-icon ui-icon-minus');
                  PKRequest.Parent(idRequest, GridParent);    
              }
              else
              {
                  $("#"+idRowDetail).fadeOut('slow');
                  $(div).removeClass().addClass('ui-icon ui-icon-circle-plus');
              }
                            
              return true;
          }
          
          $.fn.jGridContentMVC_GridRowDClickRowDetail = function(GridIdP,GridIdC){
              if( $("#"+GridIdC).css('display')=='none' )
              {
                  $("#ChargingFeesSales_BtnBack").show('slow', function(){$("#"+GridIdC).show('slow'); $("#"+GridIdP).hide('slow'); });             
              }
              else
              {
                  $("#ChargingFeesSales_BtnBack").fadeOut('slow', function(){$("#"+GridIdP).fadeIn('slow'); $("#"+GridIdC).fadeOut('slow');}); 
              }
                            
              return true;
          }
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
          }
          
          $.fn.jGridContentMVC_GridCellClick = function(GridId,iRow,iCell,td,e){              
              var config = $( '#'+GridId ).jGridContentMVC_GridStorage[ GridId ];
              
              if(config.HasTextBoxLastEffect)
              {
                  if(iRow == (config.storage.length-1))
                  {
                      var txt = $('#jGCMVC'+GridId+'_txt'+iRow);
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
              if(config.cellClick != null)
                config.cellClick( iRow ,iCell,config.storage[iRow],config.columns[iCell].id,GridId,td,e);
              return true;
          }
          
          $.fn.jGridContentMVC_UpDate = $.fn.jGridContentMVC_UpDate != null ? $.fn.jGridContentMVC_UpDate : function( storage ){          
              var config = $( this[0] ).jGridContentMVC_GridStorage[ this[0].id ];
              if (storage == null)
                    $( this[0] ).jGridContentMVC_GridDraw( config );
              else{                 
                  if(arguments[1]==null)
                      config.storage = storage;              
                  else if(storage < 0 || storage >= config.storage.length) 
                      return;             
                  else 
                      config.storage[storage] = arguments[1];
              
                  $( this[0] ).jGridContentMVC_GridDraw( config );
              }
              return;
          }
          
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
          }
          
          $.fn.jGridContentMVC_Add = $.fn.jGridContentMVC_Add != null ? $.fn.jGridContentMVC_Add : function( bean ){
              var config = $( this[0] ).jGridContentMVC_GridStorage[ this[0].id ];
              var addIni = arguments[1] == null ? false : true;
              if(config.jQSortable)
            	  bean.iRow = config.storage.length;
              if(addIni)
                  config.storage.unshift(bean);
              else
                  config.storage.push(bean);                 
              
              $( this[0] ).jGridContentMVC_GridDraw( config );
              return;
          }
          
          $.fn.jGridContentMVC_AddList = $.fn.jGridContentMVC_AddList != null ? $.fn.jGridContentMVC_AddList : function( list ){
              var config = $( this[0] ).jGridContentMVC_GridStorage[ this[0].id ];
              
              for(var i in list){
                  var bean = list[i];
                  if(config.jQSortable)
                	  bean.iRow = config.storage.length;
                  
            	  config.storage.push(bean);
              }
              
              $( this[0] ).jGridContentMVC_GridDraw( config );
              return;
          }
          
          $.fn.jGridContentMVC_AddIndex = $.fn.jGridContentMVC_AddIndex != null ? $.fn.jGridContentMVC_AddIndex : function( bean,index,replace ){
              var config = $( this[0] ).jGridContentMVC_GridStorage[ this[0].id ];          
            if(index < 0)
                config.storage.unshift(bean);
            else if(index > config.storage.length)
                config.storage.push(bean);
            else
                config.storage.splice(index,replace,bean);
                
            $( this[0] ).jGridContentMVC_GridDraw( config );
              return;
          }
          
          $.fn.jGridContentMVC_Delete = $.fn.jGridContentMVC_Delete != null ? $.fn.jGridContentMVC_Delete : function( index ){
              var config = $( this[0] ).jGridContentMVC_GridStorage[ this[0].id ];
              if(index < 0 || index >= config.storage.length) 
                  return;             
              if(config.drawIterated==null){
                  config.storage.splice(index,1);
                  $( this[0] ).jGridContentMVC_GridDraw( config );  
              }else{
                  config.storage[index]=null;
                  $( this[0] ).jGridContentMVC_GridDrawIterated( config );
              }
              return;
          }
          $.fn.jGridContentMVC_Get = $.fn.jGridContentMVC_Get != null ? $.fn.jGridContentMVC_Get : function( index ){
              var config = $( this[0] ).jGridContentMVC_GridStorage[ this[0].id ];
              if(index < 0 || index >= config.storage.length) 
                  return null;             
              return config.storage[index];
          }    
          $.fn.jGridContentMVC_GetList = $.fn.jGridContentMVC_GetList != null ? $.fn.jGridContentMVC_GetList : function( ){
              var config = $( this[0] ).jGridContentMVC_GridStorage[ this[0].id ];                    
              return config.storage;
          }
          
          $.fn.jGridContentMVC_GetListQSortable = $.fn.jGridContentMVC_GetListQSortable != null ? $.fn.jGridContentMVC_GetListQSortable : function( ){
              var config = $( this[0] ).jGridContentMVC_GridStorage[ this[0].id ];                    
              var storage = new Array();
              $("#"+this[0].id+'_sortable').children('li').each(function(i,li){
    			  var iRow = $(li).attr('iRow');
    			  for(var x in config.storage){
    				  if(config.storage[x].iRow==iRow){
    					  storage.push(config.storage[x]);
    					  return;
    				  }
    			  }
              });
    		      		  
              
              return storage;
          }
          
          
          $.fn.jGridContentMVC_ToString = $.fn.jGridContentMVC_ToString != null ? $.fn.jGridContentMVC_ToString : function( pty,separator,bullet ){        	  
              var config = $( this[0] ).jGridContentMVC_GridStorage[ this[0].id ];
              var toStr = "";
              var bullet = bullet ? bullet: "";
              separator = separator ? separator : ",";
              $.each(config.storage,function(i,bean){
            	  toStr += bean[pty]?bullet+" "+bean[pty]+separator:"";
            	  });
              return toStr;
          }
          
          $.fn.jGridContentMVC_Chk = $.fn.jGridContentMVC_Chk != null ? $.fn.jGridContentMVC_Chk : function( ){
              var config = $( this[0] ).jGridContentMVC_GridStorage[ this[0].id ];
              var list = config.storage;
              var ChkBeans = new Array();
              $.each(list,function(i,bean){
                  var chk = $('#jGCMVC'+config.gridId+'_chk'+i).attr( 'checked' );
                  var ChkBean = {'Index':i,'Bean':bean,'Checked':chk};
                  ChkBeans.push( ChkBean );
              });
              return ChkBeans;
          }
          
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
          }
          
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
          }
          
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
          }
          
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
          }
          
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
            
          }
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
            
          }
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
          }
          
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
          }
          
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
          }
          
          $.fn.jGridContentMVC_back = $.fn.jGridContentMVC_back != null ? $.fn.jGridContentMVC_back : function( ){
              var config = $( this[0] ).jGridContentMVC_GridStorage[ this[0].id ];
              var list = config.storage;
              var ChkBeans = new Array();
              $.each(list,function(i,bean){
                  var chk = $('#jGCMVC'+config.gridId+'_chk'+i);
                  var ChkBean = {'Index':i,'Bean':bean,'Checked':chk};
                  ChkBeans.push( ChkBean );
              });
              alert(ChkBeans);
          }
          
          $.fn.jGridContentMVC_Exists = $.fn.jGridContentMVC_Exists != null ? $.fn.jGridContentMVC_Exists : function( prty , value ){
              var config = $( this[0] ).jGridContentMVC_GridStorage[ this[0].id ];
              var exists = false;
              var i = 0;
              var bean = null;
              if(config.storage.length>0)
                  for( i in config.storage){
                      bean = config.storage[i];
                      if(bean[prty]==value){
                          exists = true;
                          break;    
                      }                  
                  }   
              if(arguments[2]){
            	  switch(arguments[2]){
            	  	case 'bean':
            	  		return exists ? bean : null;
            	  	default:            	  
            	  		return exists ? i : -1;
            	  }
              }
              return exists;
          }
          
          $.fn.jGridContentMVC_SetConfig = $.fn.jGridContentMVC_SetConfig != null ? $.fn.jGridContentMVC_SetConfig : function( config ){
            $( this[0] ).jGridContentMVC_GridStorage[ this[0].id ] = config;
            $( this[0] ).jGridContentMVC_GridDraw( config );  
        }
        
          $.fn.jGridContentMVC_GetConfig = $.fn.jGridContentMVC_GetConfig != null ? $.fn.jGridContentMVC_GetConfig : function(  ){
            return $( this[0] ).jGridContentMVC_GridStorage[ this[0].id ];
        }
        
          $.fn.jGridContentMVC_GridDocumentClick = function(GridId,iRow,iCell,columnId){
              var config = $( '#'+GridId ).jGridContentMVC_GridStorage[ GridId ];     
              var Bean = config.storage[iRow];
              config.ClickDeleteDocument = false;
              config.ClickDocument = true;
              if(config.documentClick != null)
               config.documentClick(Bean,iRow,iCell,columnId);
              return true;
          }      
          $.fn.jGridContentMVC_GridDeleteDocumentClick = function(GridId,iRow,iCell,columnId){
              var config = $( '#'+GridId ).jGridContentMVC_GridStorage[ GridId ];     
              var Bean = config.storage[iRow];
             config.ClickDocument = false;
              config.ClickDeleteDocument = true;              
              if(config.deleteDocumentClick != null)
                config.deleteDocumentClick(Bean,iRow,iCell,columnId);            
              return true;
          }       
          
          $.fn.jGridContentMVC_SetColumns = $.fn.jGridContentMVC_SetColumns != null ? $.fn.jGridContentMVC_SetColumns : function( columns ){		    
		    var config = $( this[0] ).jGridContentMVC_GridStorage[ this[0].id ];
		    config.columns = columns;
            $( this[0] ).jGridContentMVC_GridDraw( config );		    
		}      
          
          
          
          $.fn.jGridContentMVC_OrderBy = $.fn.jGridContentMVC_OrderBy != null ? $.fn.jGridContentMVC_OrderBy : function( pty ){		    
  		    var config = $( this[0] ).jGridContentMVC_GridStorage[ this[0].id ];
  		    
  		    
  		    
  		    var gOrder = new Array();
  		    for(var i in config.storage){
  		    	var bean = config.storage[i];
  		    	if(bean[pty])
  		    		 value = bean[pty];
  		    	else
  		    		value = bean[arguments[1]];
  		    	
  		    	value = value.replaceAll("Á","A").replaceAll("á","a").replaceAll("É","E").replaceAll("Í","I")
  		    		.replaceAll("Ó","O").replaceAll("Ú","U").replaceAll("é","e").replaceAll("í","i")
  		    		.replaceAll("ó","o").replaceAll("ú","u");
  		    	gOrder.push( value );
  		    }
  		    
  		    gOrder.sort();
  		    
  		    var auxSge = new Array();
  		    for(var a in gOrder){ 
  		    	value = gOrder[a];
  		    		
	  		    for(var i in config.storage){	  		    	
			    	var bean = config.storage[i];
			    	var valueAux = bean[pty] ? bean[pty] : bean[arguments[1]];
			    	valueAux = valueAux.replaceAll("Á","A").replaceAll("á","a").replaceAll("É","E").replaceAll("Í","I")
  		    		.replaceAll("Ó","O").replaceAll("Ú","U").replaceAll("é","e").replaceAll("í","i")
  		    		.replaceAll("ó","o").replaceAll("ú","u");
			    	if( value == valueAux )
			    		auxSge.push( bean );		    	
			    		
			    }
  		    }
  		  config.storage = auxSge;
  		    
            $( this[0] ).jGridContentMVC_GridDraw( config );	
            return config.storage;
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
               } */
          
          var wid = config.gridId;
          var wdid = this[0];    
          if(config.storage == null){
        	  var content = $( wdid ).html();
        	  if(content != ""){
        		  try{
        			  var jd = $.parseJSON(content);
        			  config.storage = Util.getBeanList(jd);  
        		  }catch(e){
        			  config.storage = new Array();
        			  alert(e);
        		  }
        	  }else
        		  config.storage = new Array();
          }
                   
          $( wdid ).jGridContentMVC_GridStorage[ config.gridId ] = config ;          
          if(config.drawIterated==null)
              $( wdid ).jGridContentMVC_GridDraw( config );    
          else
              $( wdid ).jGridContentMVC_GridDrawIterated( config );
          
  };  
})(jQuery);
