var ModeDebug = true;
var ClassWebKernel = function () {
    _this = this;

    var rootSite = '/DerechoCorporativo';

    var defaultDatepicker = {
        showOn: "button",
        buttonImage: rootSite + "/img/calendar.gif",
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
    };
    
    $.widget( "ui.timespinner", $.ui.spinner, {
        options: {
          // seconds
          step: 60 * 1000,
          // hours
          page: 60
        },
   
        _parse: function( value ) {
          if ( typeof value === "string" ) {
            // already a timestamp
            if ( Number( value ) == value ) {
              return Number( value );
            }
            return +Globalize.parseDate( value );
          }
          return value;
        },
   
        _format: function( value ) {
          return Globalize.format( new Date(value), "t" );
        }
      });
    
    this.getDefaultDatepicker = function () {

        return !arguments[0] ? defaultDatepicker : clone(defaultDatepicker);
    }

    var clone = function (obj) {
        var objc = new Object();
        for (var i in obj) {
            objc[i] = obj[i];
        }
        return objc;
    }

    var defaultSpinner = {
        spin: function (event, ui) {
            if (ui.value > 12) {
                $(this).spinner("value", 1);
                return false;
            } else if (ui.value < 1) {
                $(this).spinner("value", 12);
                return false;
            }
        }
    };
    _this.wEvent = new Object();
    
    var WC_cmb = {
    		init:function(){
    			
    		}
    		,get:function(){
    			
    		}
    };
    
    _this.WebForm = new Object();
    
    _this.WebForm.getBean = function(form){
    	var bean = arguments[1] ? arguments[1] : new Object();
    	$("." + form).each(function (i, element) {
    		var type = $(element).attr('controller');
    		var dataField = $(element).attr('dataField');
    		var dataFieldDesc = $(element).attr('dataFieldDesc');
    		switch( type ){
    			case "doc": 
	    			bean[dataField] = $("#"+element.id+'_text').val();
	    			
					break; 
    			case "grid":
    				bean[dataField]=$("#"+element.id).jGridContentMVC_GetList();
    				break;
    			case "check":
    				bean[dataField] = $(element).prop('checked');
    				
    				//alert($('input[name='+element.id+']').is('checked'));
    				break;
    			case "radioGrid":
    				var radioName = $(element).attr('radioName');
    				elementCheck = $('input[name='+radioName+']:checked'); 
    				bean[dataField] = $(elementCheck).val();
    				if(dataFieldDesc){
    					bean[dataFieldDesc] = $(elementCheck).attr('valueText');
    				}
    				
    				
    				break;
    			case "number":
    				bean[dataField]=$(element).val();
    				
    				break;
    			case "cmb":
    				bean[dataField]=$(element).val();
    				if(dataFieldDesc){
    					if(!$(element).val() || $(element).val()=="-1"){
    						bean[dataFieldDesc] = "";
    						break;
    					}
    					var cmb = document.getElementById(element.id);
        				var op = cmb.options[cmb.selectedIndex]; 
    					bean[dataFieldDesc] = op.text;
    				}
    				    				
    				break;
    			case "checkradio":
    				
    				
                    var dataField = $(element).attr('dataField');
                    
                    var setValue = null;
                    $("."+dataField).each(function(i,obj){
                    	var id = obj.id;                    	
                    		if($(obj).prop('checked'))
                    			setValue = id.replace(dataField+'_','');
                    });
                    bean[dataField] = setValue;
                    break;
    			case "texteditor":
    				bean[dataField] = CKEDITOR.instances[$(element).attr('id')].getData();    				
    				break;
    			default: 
    				bean[dataField]=$(element).val();
    				break;
    		}
    	});
    	return bean;	
    }
    
    _this.WebForm.getKeyValue = function(dataField){
    	var bean ={key:null,value:null};
    	var element = $("#"+dataField)
    	
    		var type = $(element).attr('controller');
    		var dataField = $(element).attr('dataField');
    		
    		switch( type ){
    			case "doc": 
    				bean.value = bean.key = $("#"+element.id+'_text').val();
					break; 
    			case "grid":
    				bean.value = bean.key = $("#"+element.id).jGridContentMVC_GetList();
    				break;
    			case "check":    				
    				bean.value = $(element).prop('checked');
    				bean.key = $(element).val();
    				//alert($('input[name='+element.id+']').is('checked'));
    				break;
    			case "radioGrid":
    				
    				var radioName = $(element).attr('radioName');
    				elementCheck = $('input[name='+radioName+']:checked');
    				if(arguments[1]==true){
    					var gridId = $(elementCheck).attr('class');
    					var iRow = $(elementCheck).attr('iRow');
    					return $("#"+gridId).jGridContentMVC_Get(iRow);
    				}
    				bean.value = $(elementCheck).attr('valueText');
    				bean.key = $(elementCheck).val();
    				break;
    			case "cmb":
    				var dataFieldDesc = $(element).attr('dataFieldDesc');
    				bean.key=$(element).val();
    				
    					if(!$(element).val() || $(element).val()=="-1"){
    						bean[dataFieldDesc] = "";
    						break;
    					}
    					var cmb = document.getElementById($(element).attr('id'));
        				var op = cmb.options[cmb.selectedIndex]; 
        				bean.value = op.text;
    				
    				    				
    				break;
    			default: 
    				bean[dataField]=$(element).val();
    			bean.key=$(element).val();
    			bean.value=$(element).val();
    				break;
    		}
    	
    	return bean;	
    }
    
    _this.WebForm.setBeanForReview = function(form,bean){    	
    	var carga = "";
    	$(".ui-datepicker-trigger").hide();
    	$("." + form).each(function (i, element) {
    		var type = $(element).attr('controller');
    		var dataField = $(element).attr('dataField');
    		dataField = dataField ? dataField : dataField;
    		carga+=element.id+' '+$(element).attr('field')+"={"+dataField+"}"+bean[dataField]+'\n';
    		
    		idLblRev = element.id+'_LblReview';
    		if ( $("#"+idLblRev).length <= 0 ) {
    			var parent = $("#"+element.id).parent();    			
    			$(parent).append('<label id="'+idLblRev+'"></label>');
    		}else
    			$("#"+idLblRev).show();
    		
    		switch( type ){
    			case "doc": 
    				$("#"+element.id+'_text').val(bean[dataField]);
    				$("#"+element.id+'_text').hide();    				
    				$("#"+idLblRev).html(bean[dataField]);
    				break; 
    			case "grid":
    				$("#"+element.id).jGridContentMVC_UpDate(bean[dataField]);
    				break;
    			case "check":
    				var isCheck = bean[dataField] == '1' || bean[dataField] == 1;
    				$("#"+element.id).prop('checked',isCheck);
    				$("#"+element.id).attr('disabled',true);
            		toggleDiv = $(element).attr('toggleDiv');
                    if (toggleDiv) {
                    	//Define field <... toggleDiv=".class || #id" , defaultShow="true|false" 
                    	var toggleDivXOr = $(element).attr('toggleDivXOr');
                    	if(isCheck) {                  		
                    		$(toggleDiv).show();
                    		if(toggleDivXOr)
                    			$(toggleDivXOr).hide();
                    	}else{                   		
                    		$(toggleDiv).hide();
                    		if(toggleDivXOr)
                    			$(toggleDivXOr).show();
                    	}
                    }
                
                break;
    			case "radioGrid":
    				var radioName = $(element).attr('radioName');
    				var keyValue = $(element).attr('keyValue');
    				
    				var i = $("#"+element.id).jGridContentMVC_Exists(keyValue,bean[dataField],true);
    				
    				$('#'+radioName+i).prop('checked',true);
    				break;
    				var dataField = $(element).attr('dataField');
                    var thisId = dataField+'_'+data;
                    $("."+dataField).each(function(i,obj){
                    	var id = obj.id;
                    	if(id == thisId)
                    		$(obj).prop('checked',true);
                    	else                    	
                    		$(obj).prop('checked',false);
                    });
    			case "checkradio":
                    var dataField = $(element).attr('dataField');
                    var thisId = dataField+'_'+bean[dataField];
                    $("."+dataField).each(function(i,obj){
                    	var id = obj.id;
                    	if(id == thisId)
                    		$(obj).prop('checked',true);
                    	else                    	
                    		$(obj).prop('checked',false);
                    	$(obj).attr('disabled',true);
                    });
    			default: 
    				
    				$("#"+element.id).hide();
    			
    				if($("#"+element.id).attr('reviewDataEmbedded')){
    					bkv = _this.WebForm.getKeyValue(element.id);
    					$("#"+idLblRev).html(bkv.value);
    					break;
    				}
    				$(element).val(bean[dataField]);    
    				bkv = _this.WebForm.getKeyValue(element.id);
    				$("#"+idLblRev).html(bkv.value);
    				
    				var eChange = $("#"+element.id).attr('eChange');
    				if(eChange)
    					$("#"+element.id).change();
    				
    				break;
    			
    				
    		}
    		var showReview = $("#"+element.id).attr('showReview');
    		if(showReview == 'false')
    			$("#"+idLblRev).hide();
    	});
    	
    	return bean;	
    }
    
    _this.WebForm.setBean = function(form,bean){    	
    	var carga = "";
    	$("." + form).each(function (i, element) {
    		var type = $(element).attr('controller');
    		var dataField = $(element).attr('dataField');
    		dataField = dataField ? dataField : dataField;
    		carga+=element.id+' '+$(element).attr('field')+"={"+dataField+"}"+bean[dataField]+'\n';
    		switch( type ){
    			case "doc": 
    				$("#"+element.id+'_text').val(bean[dataField]);
    				break; 
    			case "grid":
    				$("#"+element.id).jGridContentMVC_UpDate(bean[dataField]);
    				break;
    			case "check":
    				var isCheck = bean[dataField] == '1' || bean[dataField] == 1;
    				$("#"+element.id).prop('checked',isCheck);
            		toggleDiv = $(element).attr('toggleDiv');
                    if (toggleDiv) {
                    	//Define field <... toggleDiv=".class || #id" , defaultShow="true|false" 
                    	var toggleDivXOr = $(element).attr('toggleDivXOr');
                    	if(isCheck) {                  		
                    		$(toggleDiv).show();
                    		if(toggleDivXOr)
                    			$(toggleDivXOr).hide();
                    	}else{                   		
                    		$(toggleDiv).hide();
                    		if(toggleDivXOr)
                    			$(toggleDivXOr).show();
                    	}
                    }
                
                break;
    			case "radioGrid":
    				var radioName = $(element).attr('radioName');
    				var keyValue = $(element).attr('keyValue');
    				
    				var i = $("#"+element.id).jGridContentMVC_Exists(keyValue,bean[dataField],true);
    				
    				$('#'+radioName+i).prop('checked',true);
    				break;
    				var dataField = $(element).attr('dataField');
                    var thisId = dataField+'_'+data;
                    $("."+dataField).each(function(i,obj){
                    	var id = obj.id;
                    	if(id == thisId)
                    		$(obj).prop('checked',true);
                    	else                    	
                    		$(obj).prop('checked',false);
                    });
    			case "checkradio":
                    var dataField = $(element).attr('dataField');
                    var thisId = dataField+'_'+bean[dataField];
                    $("."+dataField).each(function(i,obj){
                    	var id = obj.id;
                    	if(id == thisId)
                    		$(obj).prop('checked',true);
                    	else                    	
                    		$(obj).prop('checked',false);
                    });
                    break;
    			case "texteditor":
    				CKEDITOR.instances[$(element).attr('id')].setData(bean[dataField]);
    				break;
    			default: 
    				$(element).val(bean[dataField]);
    				break;
    		}
    	});
    	
    	return bean;	
    }
    
    _this.WebForm.cleanBean = function(form){
    	var bean = new Object();
    	$("." + form).each(function (i, element) {
    		var type = $(element).attr('controller');
    		var field = $(element).attr('field');
    		switch( type ){
    		case "doc": 
				$("#"+element.id+'_text').val("");
				break; 
    		case "radioGrid":
    			var radioName = $(element).attr('radioName');
    			$('input[name='+radioName+']:checked').attr('checked',false);
    			break;
    		case "grid":
    			
    			var defaultList = $(element).attr('defaultList');
    			var dList = [];
    			
    			if(defaultList)
    				dList = eval('('+defaultList+')');
    			
    			$("#"+element.id).jGridContentMVC_UpDate(dList);
    			break;    		
    		case "cmb":    				
    				defaultValue = $(element).attr('defaultValue');
    				defaultValue = !defaultValue ? "-1" : defaultValue;
    				$(element).val(defaultValue);
    				break;
    		case "check":
        		toggleDiv = $(element).attr('toggleDiv');
                if (toggleDiv) {//Define field <... toggleDiv=".class || #id" , defaultShow="true|false" 
                	var toggleDivXOr = $(element).attr('toggleDivXOr');
                	                		
                	if($(element).attr('defaultShow')=="true"){
                		$(element).prop('checked',true);
                		$(toggleDiv).show();
                		if(toggleDivXOr)
                			$(toggleDivXOr).hide();
                	}else{
                		$(element).prop('checked',false);
                		$(toggleDiv).hide();
                		if(toggleDivXOr)
                			$(toggleDivXOr).show();
                	}   
                }
    		case "checkradio":
            	
            		
                    
                    var dataField = $(element).attr('dataField');
                    
                    $("."+dataField).each(function(i,obj){
                    	
                    		$(obj).prop('checked',false);
                    	
                    });
                                                                                       
            	break;
            	
    		case "texteditor":
    			CKEDITOR.instances[$(element).attr('id')].setData("");
    			break;
          
    			default:    				
    				$(element).val("");
    				break;
    		}
    	});
    	return bean;	
    }
    
    _this.WebForm.setValue = function(id,value,pty){
    	var element = "#"+id;
    	var type = $(element).attr('controller');
        var field = $(element).attr('field');
        var defaultValue = $(element).attr('defaultValue') == "" ? null : $(element).attr('defaultValue');
        var defaultText = $(element).attr('defaultText') == "" ? "" : $(element).attr('defaultText');
        var key = $(element).attr('keyValue').toLowerCase();
        
    	switch (type) {
        case "cmb":
        	if(pty==null){
        		$(element).val(value);
        		return;
        	}
        	
        	var cmb = document.getElementById(id);
        	for(var i=0; i<cmb.length; i++){
        		var op = cmb[i];                    		
        		var bean = $(op).data('bean');
        		if(bean==null)
        			continue;
        		if(bean[pty]==value){
        			var keyval = bean[key];
        			$(element).val(keyval);
        		}
        	}
            break;
    	}
    	
    }
    
    _this.WebForm.updController = function(id,data){
    	var element = "#"+id;
    	var type = $(element).attr('controller');
        var field = $(element).attr('field');
        var defaultValue = $(element).attr('defaultValue') == "" ? null : $(element).attr('defaultValue');
        var defaultText = $(element).attr('defaultText') == "" ? "" : $(element).attr('defaultText');
        
        
    	switch (type) {
        case "calendar":
            break;

        case "calendarRange":
            
            break;
        case "spinner":
            break;
        case "timespinner":
        	break;
        case "semaphore":
            
            break;
        case "cmb":
        case "cmbX":
            var cmb = document.getElementById(id);
            

            if (data != null) {
            	cmb.length = null;
                var key = $(element).attr('keyValue').toLowerCase();
                var value = $(element).attr('viewValue').toLowerCase();
                var index = 0;
                if (defaultText) {
                    cmb[index] = new Option(defaultText, "-1");
                    index++;
                }
                
                $.each(data, function (i, bean) {

                    op = new Option(bean[value], bean[key]);
                    $(op).data('bean', bean);
                    cmb[index] = op;
                    if (defaultValue != null && bean[key] == defaultValue)
                        cmb.selectedIndex = index;
                    index++;
                });

            }else{
            	for(var i=0; i<cmb.length; i++){
            		var op = cmb.options[i];                    		
            		$(op).data('bean',{'key':op.value,'value': op.text});
            	}
            	
            }
            
            if(type=='cmbX'){
            	$(element).selectmenu( "refresh" );
            }
            

            break;

       
        case "check":
			$(element).prop('checked',data);
    		var toggleDiv = $(element).attr('toggleDiv');
            if (toggleDiv) {
            	//Define field <... toggleDiv=".class || #id" , defaultShow="true|false" 
            	var toggleDivXOr = $(element).attr('toggleDivXOr');
            	if(data) {                  		
            		$(toggleDiv).show();
            		if(toggleDivXOr)
            			$(toggleDivXOr).hide();
            	}else{                   		
            		$(toggleDiv).hide();
            		if(toggleDivXOr)
            			$(toggleDivXOr).show();
            	}
            }
            break;
        case "texteditor":
        	CKEDITOR.instances[id].setData(data);
        	break;
        default:
        	$(element).val(data);
        	break;
    	}
    }
    
    _this.newWebForm = function (form,PKGModule, config) {
        $("." + form).each(function (i, element) {
            var type = $(element).attr('controller');            
            var id = $(element).attr('id');
            var field = $(element).attr('field');
            
            if(field == null && id==null){
            	id = form+'_'+i;
            	field = id;
            	$(element).attr('field',id);
            	$(element).attr('id',field);
            }else if(field == null){
            	field = id;
            	$(element).attr('field',id);
            }else if(id==null){
            	id = field;
            	$(element).attr('id',field);
            }
            
            var field = $(element).attr('field');
            $(element).attr("PKGModule", PKGModule);
            var cControl = config ? config[field] : null;
            var data = null;
           
            
            var defaultValue = $(element).attr('defaultValue') == "" ? null : $(element).attr('defaultValue');
            var defaultText = $(element).attr('defaultText') == "" ? null : $(element).attr('defaultText');
            
            if ($(element).attr('embeddedData') == "true") {
                try {
                    var jd = $.parseJSON($(element).html());
                    data = Util.getBeanList(jd);                    
                } catch (e) {
                    alert(e);
                }

                $(element).html('');
            }
            switch (type) {
                case "calendar":
                    cControl ? $(element).datepicker(cControl) : $(element).datepicker(defaultDatepicker);
                    $(element).keyup(function(){
                    	getMascaraFecha(this);
                    });
                    $(element).blur(function(){                    	
                    	if($.trim(this.value).length>0)
                    		validarFecha(this);
                    });
                case "calendarRange":
                    cControl ? $(element).datepicker(cControl) : $(element).datepicker(defaultDatepicker);
                    $(element).keyup(function(){
                    	getMascaraFecha(this);
                    });
                    $(element).blur(function(){
                    	if($.trim(this.value).length>0)
                    		validarFecha(this);
                    });
                    
                    var calendarRange_onchangeTo = function () {
                        $("#" + element.to).datepicker("option", "minDate", defaultDatepicker.getDate(this));
                    }

                    var calendarRange_onchangeFrom = function () {
                        $("#" + element.to).datepicker("option", "maxDate", defaultDatepicker.getDate(this));
                    }

                    if (!$(element).attr('toField')) {
                        element = $("#" + field).datepicker(!cControl ? defaultDatepicker : cControl).on("change", cControl ? cControl.change ? cControl.change : calendarRange_onchangeTo : calendarRange_onchangeTo);
                        element.to = $(element).attr('fromField');
                    } else {

                        element = $("#" + field).datepicker(!cControl ? defaultDatepicker : cControl).on("change", cControl ? cControl.change ? cControl.change : calendarRange_onchangeFrom : calendarRange_onchangeFrom);
                        element.to = $(element).attr('toField');
                    }

                    break;
                case "spinner":
                    cControl ? $(element).spinner(cControl) : $(element).spinner(defaultSpinner);
                    if ($(element).attr('eChange') != "") {

                    	$(element ).on( "spinchange", function( event, ui ) {
                            
                            var value = $(this).val();
                            var handler = $(element).attr('eChange');
                            if(handler == undefined)return;
                            var PKGModule = $(element).attr("PKGModule");
                            var bean = value;
                            try {
                                var module = window[PKGModule];
                                module[handler](bean, value, this, ui, event);
                            } catch (e) {
                                alert(e + " " + handler);
                            }
                        });
                    	
                    	$(element ).keyup(function(){                		
                    		soloNumeros(this);
                    	});
                    }                
                case "timespinner":
                    return cControl ? $(element).timespinner(cControl) : $(element).timespinner();
                case "semaphore":
                    $(element).addClass('Img_Semaforo_green');
                    break;
                case "cmb":
                case "cmbX":
                    var cmb = document.getElementById(field);
                    

                    if (data != null) {
                    	cmb.length = null;
                        var key = $(element).attr('keyValue').toLowerCase();
                        var value = $(element).attr('viewValue').toLowerCase();
                        var index = 0;
                        if (defaultText) {
                            cmb[index] = new Option(defaultText, "-1");
                            index++;
                        }
                        
                        $.each(data, function (i, bean) {

                            var op = new Option(bean[value], bean[key]);
                            $(op).data('bean', bean);
                            cmb[index] = op;
                            if (defaultValue != null && bean[key] == defaultValue)
                                cmb.selectedIndex = index;
                            index++;
                        });

                    }else{
                    	for(var i=0; i<cmb.length; i++){
                    		var op = cmb.options[i];                    		
                    		$(op).data('bean',{'key':op.value,'value': op.text});
                    		if (defaultValue != null && op.value == defaultValue){
                    			cmb.selectedIndex = i;
                    			$(op).prop('selected',true);
                    		}
                    			
                    	}
                    	
                    }
                    
                    if(type=='cmbX'){
                    	element = cControl ? $(element).selectmenu(cControl) : $(element).selectmenu();

                    if ($(element).attr('eChange') != "") {

                        $(element).on("selectmenuchange", function (event, ui) {
                            var bean = $(ui.item.element).data('bean');
                            var value = $(this).val();
                            var handler = $(element).attr('eChange');
                            if(handler == undefined)return;
                            var PKGModule = $(element).attr("PKGModule");
                            
                            try {
                                var module = window[PKGModule];
                                module[handler](bean, value, this, ui, event);
                            } catch (e) {
                                alert(e + " " + handler);
                            }
                        });


                    	}
		            }else{
		            	$(element).change(function (event) {
		            		
		            		var cmb = document.getElementById(this.id);
		            		var ui = cmb.options[cmb.selectedIndex];
                            var bean = $(ui).data('bean');
                            var value = $(this).val();
                            var handler = $(element).attr('eChange');
                            if(handler == undefined)return;
                            var PKGModule = $(element).attr("PKGModule");
                            
                            try {
                                var module = window[PKGModule];
                                module[handler](bean, value, this, event,ui);
                            } catch (e) {
                                alert(e + " " + handler);
                            }
                        });
		            }

                    return cControl;

                case "check":
                		toggleDiv = $(element).attr('toggleDiv');
                        if (toggleDiv) {//Define field <... toggleDiv=".class || #id" , defaultShow="true|false"
                        	var toggleDivXOr = $(element).attr('toggleDivXOr');
                        	if($(element).attr('defaultShow')=="true"){
                        		$(element).prop('checked',true);
                        		$(toggleDiv).show();
                        		if(toggleDivXOr)
                        			$(toggleDivXOr).hide();
                        	}else{
                        		$(element).prop('checked',false);
                        		$(toggleDiv).hide();
                        		if(toggleDivXOr)
                        			$(toggleDivXOr).show();
                        	}
                            
                            $(element).change(function (event, ui) {
                                var check = $(this).is(":checked");
                                toggleDiv = $(element).attr('toggleDiv');
                                var toggleDivXOr = $(element).attr('toggleDivXOr');
                                if (check) {
                                    $(toggleDiv).show();
                                    if(toggleDivXOr)
                            			$(toggleDivXOr).hide();
                                } else {
                                    $(toggleDiv).hide();
                                    if(toggleDivXOr)
                            			$(toggleDivXOr).show();
                                }
                                                                
                                var handler = $(element).attr('eChange');
                                if(handler == undefined)return;
                                var PKGModule = $(element).attr("PKGModule");
                                
                                try {
                                    var module = window[PKGModule];
                                    module[handler](check, this, ui, event);
                                } catch (e) {
                                    alert(e + " " + handler);
                                }
                            });
                        }
                    
                    break;
                case "doc":
                	
                	var text = $.newHTML('input',{attr:{'id':field+'_text','size':30,'type':'text'}});
                	
                	$(element).css('width','385px');
                	$(text).attr('size',35);
                	/*$(text).attr('size',35).keyup(function(){
                		getFormatDocumentum(this);
                	});*/
                	
                	$(text).mask("000-000-000-0000-0000-00000-00-00-000");	
                	
                	var div = $.newHTML('div',{attr:{'divField':field},css:{'float':'right'}});
                	
                	$(div).attr('for',field).attr('divField',field+'_text').attr('class','Img_Icon_Documentum').click(function(){               		
                		
                		getDocumentum($(div).attr('divField'));
                	});
                	                	                	
                	$(element).append(text).append(div);             	
                	break;
                case "texteditor":
                	
                	break;
                case "number":
                	$(element).keyup(function(){                		
                		soloNumeros(this);
                	});
                	
                	$(element).blur(function(){                		
                		maskEsc(this);
                	});                
                	break;
                case "radioGrid":
    				var radioName = $(element).attr('radioName');
    				var keyValue = $(element).attr('keyValue');
    				var viewValue = $(element).attr('viewValue');
    				
    				$("#"+id).jGridContentMVC({
    		            gridId: id,
    		            keyValue: keyValue,
    		            viewValue:viewValue,
    		            radioName:radioName,
    		            noDataMsg: 'no hay registros para mostrar',
    		            storage: data,
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
    		                                       {
    		                                           HeadText: '', 'width': '5%', id:keyValue , type: 'action', 'align': 'left', cellValue: 'eval', eval: function (bean, pty, column, iCell, iRow, config) {
    		                                               
    		                                        	   var id = config.radioName;
    		                                               return '<input iRow="'+iRow+'" type="radio" id="' + (id + iRow) + '" name="' + id + '" class="'+config.gridId+'" value="' + bean[config.keyValue] + '" valueText="'+bean[config.viewValue]+'"/><label for="' + (id + iRow) + '">' + bean[config.viewValue] + '</label>';
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
    				break;
                case "checkradio":
                	$(element).change(function (event, ui) {
                		var thisId = this.id;
                        var check = $(this).is(":checked");
                        var dataField = $(element).attr('dataField');
                        $("."+dataField).each(function(i,obj){
                        	var id = obj.id;
                        	if(id == thisId)
                        		return;                        	
                        	
                        	$(obj).prop('checked',false);
                        });
                                                        
                        var handler = $(element).attr('eChange');
                        if(handler == undefined)return;
                        var PKGModule = $(element).attr("PKGModule");
                        
                        try {
                            var module = window[PKGModule];
                            module[handler](check, this, ui, event);
                        } catch (e) {
                            alert(e + " " + handler);
                        }
                    });
                	break;
                case "text":
                	if ($(element).attr('eEnterKeyPress') != "") {
                	$(element).keyup(function(event){                		
                		if ( event.which == 13 ) {                			
                			var value = $(this).val();
                            var handler = $(this).attr('eEnterKeyPress');
                            if(handler == undefined)return;
                            var PKGModule = $(this).attr("PKGModule");
                            
                            try {
                                var module = window[PKGModule];
                                module[handler]( value, this, event);
                            } catch (e) {
                                alert(e + " " + handler);
                            }
                		 }
                		
                	});
                	}
                	break;
            }
        });
    }
    
    //data = [{id ... : ,value: ...},{
    this.publishMultiselectDialog = function (data) {
        try {
            _this.multiselectDialogHandler.publish(data, _this.multiselectDialogHandler);
        } catch (e) {
        	if(ModeDebug)
        		alert(e);
        }
        $("#WebKernel_Dlg_MultiSelect").dialog('close');
    }

    this.multiselectDialog = function (title, catalogId, currentValue, publish) {
        _this.multiselectDialogHandler = publish;

        //var href = '/DerechoCorporativo/jsp/components/multiselectPupUp/multiselect.jsp?catalogId=' + catalogId + '&useWebKernel=1' + '&currentValue=' + currentValue;
        var href = '/DerechoCorporativo/jsp/components/multiselectPupUp/multiselect.jsp?catalogId=' + catalogId + '&useWebKernel=1' + '&currentValue=' + currentValue;

        $("#WebKernel_Dlg_MultiSelect_IFrame").attr({ 'src': href, 'height': '430px', 'width': '416px' });

        $("#WebKernel_Dlg_MultiSelect").dialog('option', 'height', 495);
        $("#WebKernel_Dlg_MultiSelect").dialog('option', 'width', 430);
        $("#WebKernel_Dlg_MultiSelect").dialog('option', 'title', title);
        $("#WebKernel_Dlg_MultiSelect").dialog('option', 'buttons', []);
        $("#WebKernel_Dlg_MultiSelect").dialog('open');
    }

    $(document).ready(function () {

        $(document.body).append('<div id="DivWebKernel"></div>');



        $("#DivWebKernel").load('/DerechoCorporativo/js/jquery/WebKernel/webKernel.html', function () {
            $("#WebKernel_Dlg_MultiSelect").dialog({
                resizable: true,
                height: "auto",
                autoOpen: false,
                width: 400,
                modal: true,
                resizeStop: function( event, ui ) {
                	var h = $( "#WebKernel_Dlg_MultiSelect" ).dialog( "option", "height" );
                	var w = $( "#WebKernel_Dlg_MultiSelect" ).dialog( "option", "width" );
                	
                	h = (h * 300)/380;
                	w = (w * 400)/440;
                	
                	var eHeight = h+'px';
                	var eWidth = w+'px';
                	$("#WebKernel_Dlg_MultiSelect_IFrame").attr({ 'height': eHeight, 'width': eWidth });
                },
                title: 'WebKernel Dialog',
                buttons: {
                    "Delete all items": function () {
                        $(this).dialog("close");
                    },
                    Cancel: function () {
                        $(this).dialog("close");
                    }
                }
            });


        });
    });
}

var ClassRequestModel = function () {
    var _this = this;
    this.LastTimeTransaction = new Date();

    var handler = function (action) {
        return 'iJJCouncilProjects/HandlerAction.ashx?action=' + action;
    }

    var requestConfig = {
        dataType: 'json'
	                , type: 'POST'
	                , complete: function (xhttp, text) {

	                }
			        , error: function (e) {
			            alert(e);
			        }
    };

    var request = function (action, args, config) {

        for (var pty in requestConfig)
            if (config[pty] == null)
                config[pty] = requestConfig[pty];

        LastTimeTransaction = new Date();

        $.ajax({
            dataType: config.dataType,
            success: config.response,
            complete: config.complete,
            error: config.error,
            url: action,
            data: args,
            type: config.type
        });
    };

    this.request = request;
}

var XRequest = new ClassRequestModel();
var WebKernel = new ClassWebKernel();
WebKernel.XRequest = XRequest;
var webEvent = WebKernel.wEvent;
var WebForm = WebKernel.WebForm; 
WebKernel.isLeapYear = function (year) {
    return (((year % 4 === 0) && (year % 100 !== 0)) || (year % 400 === 0));
};

WebKernel.getDaysInMonth = function (date, year, month) {
    return [31, (date.isLeapYear(year) ? 29 : 28), 31, 30, 31, 30, 31, 31, 30, 31, 30, 31][month];
};

WebKernel.isLeapYear = function (date) {
    return date.isLeapYear(date.getFullYear());
};

WebKernel.getDaysInMonth = function (date) {
    return date.getDaysInMonth(WebKernel.getFullYear(), WebKernel.getMonth());
};

WebKernel.addMonths = function (isoDate, numberMonths,format) {
    format = format ? format : 'dd/mm/yyyy';
	var dateObject = new Date(isoDate),
        day = dateObject.getDate(); // returns day of the month number

    // avoid date calculation errors
    dateObject.setHours(20);

    // add months and set date to last day of the correct month
    dateObject.setMonth(dateObject.getMonth() + numberMonths + 1, 0);

    // set day number to min of either the original one or last day of month
    dateObject.setDate(Math.min(day, dateObject.getDate()));
    day = dateObject.getDate();
    day = (day < 10 ? "0" : "") + day;
    mth = dateObject.getMonth()+1;
    mth = (mth < 10 ? "0" : "") + mth;
    
    return format.replace('dd',day).replace('mm',mth).replace('yyyy',dateObject.getFullYear());
    
};

//$.newHTML('div',{attr:{'id':'div1',...},css:{'color':'blue',...},data:{'key',value,...}
$.newHTML = function (type, config) {
	if(!config)
		return document.createElement(type);
    
    var html = document.createElement(type);
    if(config.attr)
    	for(var i in config.attr){
    		html = $(html).attr(i,config.attr[i]);
    	}
    if(config.css)
    	for(var i in config.css){
    		html = $(html).css(i,config.css[i]);
    	}
    if(config.data)
    	for(var i in config.data){
    		$(html).data(i, config.data[i]);
    	}
    
    return html;
};


function getFormatDocumentum(pObjeto){

	 //18 ENERO 2016
	 var vSrc = pObjeto.value;
	 vSrc = vSrc.replace(/ /g, ''); //Quitar espacios en cadena
	 vSrc = vSrc.trim(); 
	 pObjeto.value = vSrc; 
	 
	 var vSeparador = '-';
	 var vPatron =  new Array(3,3,3,4,4,5,2,2,3);
	 var vNumerico = true;

	 if(pObjeto.valant != pObjeto.value){
	  val = pObjeto.value
	  largo = val.length
	  val = val.split(vSeparador)
	  val2 = ''

	  for(r=0;r<val.length;r++){
	   val2 += val[r] 
	  }

	  if(vNumerico){
	   for(z=0;z<val2.length;z++){
	    if(isNaN(val2.charAt(z))){
	     letra = new RegExp(val2.charAt(z),"g")
	     val2 = val2.replace(letra,"")
	    }
	   }
	  }

	  val = ''
	  val3 = new Array()

	  for(s=0; s<vPatron.length; s++){
	   val3[s] = val2.substring(0,vPatron[s])
	   val2 = val2.substr(vPatron[s])
	  }

	  for(q=0;q<val3.length; q++){
	   if(q ==0){
	    val = val3[q]
	   }
	   else{
	    if(val3[q] != ""){
	     val += vSeparador + val3[q]
	     }
	   }
	  }

	  pObjeto.value = val
	  pObjeto.valant = val

	  }

	}

function swal(config){
	alert(config.text);
}

function getDocumentum(id){
	 var doc = null;
	 doc = document.getElementById(id).value;
	 
	 if (doc == null||doc =='null'||doc == ''){
	  swal({ title: "Aviso",   
	      text: "El campo No. De Documento en DOCUMENTUM no puede estar vac\u00EDo",   
	      type: "warning",  
	      confirmButtonText: "Ok" });
	 }else if (doc.length != 37){
	  swal({ title: "Aviso",   
	      text: "El formato del Campo No. de Documento en DOCUMENTUM es incorrecto",   
	      type: "warning",  
	      confirmButtonText: "Ok" }); 
	 }else{
	    window.open("/DerechoCorporativo/faces/jsp/documentum/waitingPage.jsp?doc="+doc
	     ,null
	     ,"height=600,width=1100,status=yes,toolbar=no,menubar=no,location=no");
	 }
}

function maskEsc(valCampo){
	if(valCampo != null){
		var val2 = valCampo.value.trim();
		$(valCampo).val(commaSeparateNumber(val2));
	}

}

function commaSeparateNumber(val){

    while (/(\d+)(\d{3})/.test(val.toString())){
      val = val.toString().replace(/(\d+)(\d{3})/, '$1'+','+'$2');
    }
    return val;
}