﻿var Util = new Object();
var NonCache = true;
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
    
    $( hideElement ).hide();
    $( showElement ).show();
    
}

/** togglev
*       desaparace un elemento y muestra otro   
*
*   @param  idElement   id del elemento operar
*   @return bool        true si quedo abierto 
*/
Util.toggle = function(idElement){
    $( idElement ).toggle();
    return !($(idElement).css('display')=='none');
}

/** loading
*       muestra el mensaje de carga a nivel app o div   
*
*   @param app bool               si true loading a nivel aplicación false a nivel div
*   @param div #    optional      id del elemento donde se colocara el msj de cargando
*/
var iconLoading = '<div class="Waiting" style="width: 60px;height:64px;"></div>';
var iconOk      = '<img title="OK!!" src="images/Icon_ok.png" />';
var iconError   = '<img title="Error!!" src="images/Icon_warning.png" />';
var iconException   = '<img title="Error!!" src="images/Icon_error.png" />';

$(document).ready(function(){
    var engagedDiv = "#AppEngaged";        
    $(engagedDiv).data('engaged',false);
    $(engagedDiv).dialog({
            height: 250,
            width:300,
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
}

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
            config = configAux
        
        var html = document.createElement(type);
            html = $(html).attr(config.attr).css(config.css).addClass(config.cssClass).bind(config.listeners);
                $.each(config.data,function(pty,value){
                    $(html).data(pty,value);
                });
        if( config.html != null )
            $(html).html( config.html );
        return html;
        }

/** showErrorMsg
*       crea un componente html
*
*   @param div   idDOM    Identificador del elemento a mostrar
*   @param msg   string   mensaje a mostrar
*/        
Util.showErrorMsg = function(div,msg){
    $(div).hide();
    var html = '<div style="padding: 0 .7em; " class="ui-state-error ui-corner-all">'
        html+= '<p><span style="float: left; margin-right: .3em;">'+iconError+'</span> '
		html+= '<strong>Please check:</strong> '+msg+'</p>'
		html+= '</div>'
    $(div).html( html ).show();
    
    var callBack = arguments[2]!=null? arguments[2]: function(div){
            $(div).html( '' ).hide();        
        };
    
    setTimeout( callBack,5000,div );
}

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
		html+= '<strong>Ok!</strong>  &nbsp;&nbsp;'+msg+'</p>'
		html+= '</div>'
    $(div).html( html ).show();
    $(div).data( 'afterHide',after );
    
    action = eval( '('+'{after:function(args){  $("'+div+'").html( "" ).fadeOut("slow");var after = $("'+div+'").data( "afterHide" );if( after != null)after();}}'+')');
    
    setTimeout( action.after ,5000 );
}   

/** getDate
*       devuelve la fecha en un determinado formato
*
*   @param date   Date      Objeto Fecha
*   @param format string    Formato string
*/        
Util.getDate = function(date,format){
    if(date==null)return null;        
    var dateFmt = "";
    var day = date.getDate();
        day = (day < 10 ? "0" : "") + day;
    var mth = date.getMonth() +1;
        mth = (mth < 10 ? "0" : "") + mth;
    var year= date.getFullYear();
    
    var sepA = "/";
    var sepB = "-";
    
    var SEP = arguments[2] ? arguments[2] : sepB;
    
    switch(format){
        case 'SHORT': dateFmt = day+SEP+mth+SEP+year; break;
        case 'SQL': dateFmt = year+SEP+mth+SEP+day; break;
        default: dateFmt = day+SEP+mth+SEP+year; break;
        
    }
    return dateFmt;
}

/** parseFmtDate
*       devuelve la fecha en un determinado formato
*
*   @param date   Date      Objeto Fecha
*   @param format string    Formato string
*/        
Util.parseStrFmtDate = function(date,formatA,formatB){
    if(date==null)return null;        
    var dateFmt = "";
    switch(formatA){
    	case 'SHORT': 
    		day = date.substr(0,2);    		
    		mth = date.substr(3,2);
    		year = date.substr(6,4);
    		SEP = date.substr(2,1);    	
    		break;
    	case 'SQL': 
    		day = date.substr(9,2);    		
    		mth = date.substr(6,2);
    		year = date.substr(0,4);
    		SEP = date.substr(2,1); 
    		break;    	
    }
    
    switch(formatB){
        case 'SHORT': dateFmt = day+SEP+mth+SEP+year; break;
        case 'SQL': dateFmt = year+SEP+mth+SEP+day; break;
        default: dateFmt = day+SEP+mth+SEP+year; break;
        
    }
    return dateFmt;
} 

/** parseFmtDate
*       devuelve la fecha en un determinado formato
*
*   @param date   Date      Objeto Fecha
*   @param format string    Formato string
*/        
Util.toDate = function(date,formatA){
    if(date==null)return null;        
    var dateFmt = "";
    switch(formatA){
    	case 'SHORT': 
    		day = date.substr(0,2);    		
    		mth = date.substr(3,2);
    		year = date.substr(6,4);
    		   	
    		break;
    	case 'SQL': 
    		day = date.substr(9,2);    		
    		mth = parseInt(date.substr(6,2));
    		year = date.substr(0,4);
    		 
    		break;    	
    }
    
    
    return new Date(year,mth-1,day);
} 

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
}

/** ToolTip
*       Customiza los tooltips
*   @param elements jQueryElements  Conjunto de elementos aplicar la customización
*   @param type string              Tipo de costomización
*/
Util.ToolTip = function(elements,type){
    var config = null;
    switch(type){
        default:
            config = {
            position: {
                my: "center bottom-20",
                at: "center top",
                using: function( position, feedback ) {
                    $( this ).css( position );
                    $( "<div>" )
                        .addClass( "arrow" )
                        .addClass( feedback.vertical )
                        .addClass( feedback.horizontal )
                        .appendTo( this );
                }}};
            break;
    }
    
    $( elements ).tooltip(config);
}

/** loadPkg
*       Carga un paquete
*   @param config json string   JSON con las rutas de los elementos a cargar y comprobar
*   {    id:    id del elemento a cargar
        ,html:  ruta del archivo html
        ,args:  argumentos a enviar al html
        ,css:   ruta del archivo css
        ,js:    ruta del archivo js
        ,check: nombre de la función de comprobación
        ,after: function(load,config,thread){} función de ejecución despues de la comprobación
        ,error: function(load,config,thread){} función de error
        ,complete: function(load,config,thread) función despues de after o error
        ,scripts: [] arreglo de scripts no comprobables
        ,PKPath: Ruta general del paquete
        ,fragments: [{from:"#id",to:"#id || null lo agrega al apartado fragments"}]
    }
*   @return Thread  hilo de ejecución;
*/
Util.loadPkg = function(config){
    
	if($(config.id).data('isLoad'))return;
	
	if ( $("#divFragments").length == 0 ) {
		$(document.body).append('<div id="divFragments"></div>');
	}
	
    var Thread = function(config){
        var _this = this;
        var _config = config;
        
        if(_config.PKPath != null){
            _config.html = _config.PKPath+'.jsp';
            _config.css = _config.PKPath+'.css';
            _config.js = _config.PKPath+'.js';            
        }
        
        this.ThreadStatus = {'HTML':1,'CHECK':2,'OK':3,'ERROR':4};
        this.Status = 0;
        this.count = 0;
        this.config = function(){
            return config;
        }
        
        
        var requestScript = function( Path , Type ){
            Type = Type == 'js' ? 'script' : 'link';
            var script  = document.createElement( Type );
            script.type =  Type == 'script' ? "text/javascript" : "text/css";
            
            Path += NonCache ? "?lastversion=" + Math.floor(Math.random() * 999999999999999) :"";
            if( Type == 'script' )
                script.src  = Path;
            else{
                script.href = Path;
                script.rel="stylesheet";
            }
            document.getElementsByTagName("head")[0].appendChild( script );
        }
        
        var checking = function(){
            try{
                var load = null;
                if(_config.check != null)
                    load = eval( _config.check + "(  )" );
                
                if( !load.init && _config.error != null ){
                    _config.error( load,_config,_this );
                    _this.Status = _this.ThreadStatus.ERROR;                 
                }else if( config.after != null){
                    _config.after( load,_config,_this );
                    _this.Status = _this.ThreadStatus.OK;                                           
                    }
                
                if(_config.complete != null)
                    _config.complete(load,_config,_this);
                
                $(_config.id).data('isLoad',true);
            }catch(e){
                _this.count++;
                if( _this.count >= 200 ){
                    if( _config.error != null )
                        _config.error( {"init":false,"reference":'TimeOutException'},_config,_this );
                
                    if(_config.complete != null)
                        _config.complete(load,_config,_this);   
                        
                    _this.Status = _this.ThreadStatus.ERROR;                       
                    
                }else
                    setTimeout( checking ,200 );
                
            }
            return;
        }
        
        if(config.css!=null)requestScript( config.css,'css' );
        if(config.js!=null)requestScript( config.js,'js' );
        
        for( var i in config.scripts ){
            var script = config.scripts[i];
            requestScript( script.path,script.type );
        }
        
        this.Status = this.ThreadStatus.HTML;
        if(config.engaged==null || config.engaged)
        	Util.loading(true,1,config.engaged==null?"Iniciando Módulo":config.engaged.msg==null?"Iniciando Módulo":config.engaged.msg);
        
        $.ajax({
		       dataType:'html',
		       success: function(data){
                    $(_config.id).html( data );
                    _this.Status = _this.ThreadStatus.CHECK;                   
                    
                    if(_config.fragments){                    	
                    	$.each(_config.fragments,function(i,fragment){
                    		if(fragment.to){
                    			$(fragment.from).appendTo(fragment.to);
                    		}else{
                    			$(fragment.from).appendTo('#divFragments');
                    		}             		
                    	});                    
                    }
                    checking();
		       },	   
		       error: function(xhttp,e){
		           if( _config.error != null )
                        _config.error( {"init":false,"reference":e},_config,_this );
                
                    if(_config.complete != null)
                        _config.complete(load,_config,_this); 
                    
                    _this.Status = _this.ThreadStatus.ERROR;                       
		       },
		       complete: function(){
	            	Util.loading(true,0,"");
	            },
		       url: config.html,
		       data: config.args,
		       type: 'POST'
		       });   
    }
    
    return new Thread( config );
}

/*Centraliza la conversión entre JSON DIV y HDN*/
var viewFactoryParseJSON = function(pryDiv,hdn){
    try{
        return $.parseJSON( $("#"+pryDiv).html() );
    }catch(e){
        return $.parseJSON( $("#"+hdn).attr("value") );            
    }
}

function request(action,args,response){//(action,args,response,Appload,LoadDiv)	
	
	if(arguments[3]==null){//Parcial
		$("#load").show('slow');
	}else if(arguments[3]){//General
		$("#Appload").show('slow');
	}else if(arguments[4]!=null){//Div
		var div = arguments[4];
		$("#"+div).html( '<table style="width:100%"><tr><td align="center"> <IMG SRC="../../images/ajax-loader.gif" /> </td></tr></table>' );		
		divload = "#"+div;
	}
	
	dType = arguments[5] == null ? 'json' :  arguments[5];	
	
	
	$.ajax({
		   dataType:dType,
		   success: response,
		   complete: function(xhttp,text){
			   $("#load").hide('slow');
			   $("#Appload").hide('slow');
			   },		   
		   error: function(xhttp,text,errorThrown,errorThrown){
			   $("#load").hide('slow');
			   $("#Appload").hide('slow');
			   $(div).html("");
			   var text = "";
			   for(var i in xhttp)
			        text+=i+" "+xhttp[i]+"\n";
			        //alert("Exception no controlada: "+text+" " + errorThrown +"\n" + text); 
			   },
		   url: action,
		   data: args,
		   type: "POST"
		   });
	try{
        if(heartBeat!=null)
            heartBeat.lastRequest = new Date();
    }catch(e){
        
    }
}

Util.fillGenericDataBeans = function(beanList){
	var gdb = {propertys:new Array(),datatypes:new Array(),beans:new Array()};
	
	var exists = function(pty,list){
		for(var i in list){
			if(list[i]==pty)
				return true;
		}
		return false;
	}
	
	for(var i in beanList){
		var bean = beanList[i];
		for(var pty in bean){
			if(!exists(pty,gdb.propertys)){
				gdb.propertys.push(pty);
				gdb.datatypes.push('Object');
			}
		}		
	}
	
	for(var i in beanList){
		var bean = beanList[i];
		var gbean = new Array();
		for(var ip=0;ip< gdb.propertys.length;ip++){
			var pty = gdb.propertys[ip];
			var value = bean[pty];
			gbean[ip]=value==undefined?null:value;
		}
		gdb.beans.push(gbean);
	}
	
	return gdb;
}

Util.newBean = function (data, pty) {
    var bean = new Object();
    bean.propertys = pty.propertys;
    bean.datatypes = pty.datatypes;
    bean.values = data;
    return bean;
}

Util.getBean = function (data, pty) {
    var bean = new Object();
    for (var i in data)
        bean[pty[i]] = data[i];
    return bean;
}

Util.getBeanList = function (gdb) {
    var lstBean = new Array();
    for (var i in gdb.beans)
        lstBean.push(Util.getBean(gdb.beans[i], gdb.propertys));
    return lstBean;
}

Util.fillBean = function (obj, pty) {
    var data = new Array();
    for (var i in pty.propertys)
        data[i] = obj[pty.propertys[i]];
    var bean = new Object();
    bean.propertys = pty.propertys;
    bean.datatypes = pty.datatypes;
    bean.values = data;
    return bean;
}

var GenericDataBeans = function (gdb) {
    var _this = this;
    this.propertys = gdb.propertys;
    this.datatypes = gdb.datatypes;

    this.beans = gdb.beans;

    this.get = function (index) {
        if (index > _this.beans.length - 1)
            return null;
        return new GenericBean(_this.propertys, _this.datatypes, _this.beans[index]);
    }
}

var GenericBean = function (propertys, datatypes, bean) {
    var _this = this;
    if (datatypes == null && bean == null) {
        datatypes = propertys.datatypes;
        bean = propertys.values;
        propertys = propertys.propertys;
    }
    this.propertys = propertys;
    this.datatypes = datatypes;
    this.values = bean;

    this.getTypeProperty = function (property) {
        for (var i = 0; i < _this.propertys.length; i++) {
            if (_this.propertys[i] == property)
                return _this.datatypes[i];
        }
        return null;
    }

    this.getProperty = function (property) {

        for (var i = 0; i < _this.propertys.length; i++) {
            if (_this.propertys[i] == property)
                return _this.values[i];
        }
        return null;
    }

    this.setProperty = function (property, value) {
        for (var i = 0; i < propertys.length; i++) {
            if (_this.propertys[i] == property) {
                _this.values[i] = value;
                break;
            }
        }
    }


    this.getProperty = function (property) {
        for (var i = 0; i < _this.propertys.length; i++) {
            if (_this.propertys[i] == property)
                return _this.values[i];
        }
        return null;
    }


}

Util.newGenericBean = function (bean) {
    var gb = new Object();
    gb.propertys = new Array();
    gb.datatypes = new Array();
    gb.values = new Array();

    for (var pty in bean) {
        gb.propertys.push(pty);
        gb.values.push(bean[pty]);
        gb.datatypes.push("Object");
    }

    return gb;
}

Util.newRegConv = function () {
    return { id: null, wwid: null, FKYear: null, conversation: null, commentA: null, commentB: null, 'class': null, registerType: null, status: null, startDate: null, endDate: null, registerDate: null, registerUser: null, lastUpdate: null, updateUser: null }
}

Util.clone = function (base) {
    var bean = new Object();
    for (var pty in base)
        bean[pty] = base[pty];
    return bean;
}

function soloNumeros(object){
	var texto  = object.value;
	if(texto!=null)
		for(c=0;c<texto.length;c++){
			if(isNaN(texto.charAt(c)))
				object.value = object.value.replace(texto.charAt(c),"");
		}
}

//ICL 03122015
function getMascaraFecha(pObjeto){
	var vSeparador = '/';
	var vPatron =  new Array(2,2,4);
	var vNumerico = true;

	if(pObjeto.valant != pObjeto.value){
		val = pObjeto.value;		
		val = val.split(vSeparador);
		val2 = '';

		for(r=0;r<val.length;r++){
			val2 += val[r];	
		}

		if(vNumerico){
			for(z=0;z<val2.length;z++){
				if(isNaN(val2.charAt(z))){
					letra = new RegExp(val2.charAt(z),"g");
					val2 = val2.replace(letra,"");
				}
			}
		}

		val = '';
		val3 = new Array();

		for(s=0; s<vPatron.length; s++){
			val3[s] = val2.substring(0,vPatron[s]);
			val2 = val2.substr(vPatron[s]);
		}

		for(q=0;q<val3.length; q++){
			if(q ==0){
				val = val3[q];
			}
			else{
				if(val3[q] != ""){
					val += vSeparador + val3[q];
					}
			}
		}

		pObjeto.value = val;
		pObjeto.valant = val;

		}
}

function validarFecha(fecha){
	//con la expresion regular "/\D/g" se busca cualquier caracter que no sea un número y los borra con el replace
	var fechaString=fecha.value.replace(/\D/g,"");
	valFecha=/^\d*$/.test(fechaString);
	if(valFecha==true && fechaString!=""){
		var fechaArr = fecha.value.split('/');
		var dia = fechaArr[0];
		var mes = fechaArr[1];
		var aho = fechaArr[2];
		var plantilla = new Date(aho, mes - 1, dia);//mes empieza de cero Enero = 0
		 if(fechaArr.length == 3){
		var longAho=aho.length;
		if(!plantilla || plantilla.getFullYear() == aho && plantilla.getMonth() == mes -1 && plantilla.getDate() == dia && longAho>3){
		 	//alert("Correcto");
		 	//Fecha correcta continuar
		 	return true;
		}else{
			swal({ title: "Aviso",   
				   text: "La fecha es incorrecta",   
				   type: "warning",  
				   confirmButtonText: "Ok" });
		 	fecha.value="";
		 	return false;
		}
		
	}else{
		swal({ title: "Aviso",   
			   text: "La fecha es incorrecta",   
			   type: "warning",  
			   confirmButtonText: "Ok" });
	 	fecha.value="";
	 	return false;
	}
	}else{
		swal({ title: "Aviso",   
			   text: "La fecha es incorrecta",   
			   type: "warning",  
			   confirmButtonText: "Ok" });
	 	fecha.value="";
	 	return false;
	}
}

function getMascaraHora(pObjeto){
	var vSeparador = ':';
	var vPatron =  new Array(2,2);
	var vNumerico = true;

	if(pObjeto.valant != pObjeto.value){
		val = pObjeto.value;
		val = val.split(vSeparador);
		val2 = '';

		for(r=0;r<val.length;r++){
			val2 += val[r];	
		}

		if(vNumerico){
			for(z=0;z<val2.length;z++){
				if(isNaN(val2.charAt(z))){
					letra = new RegExp(val2.charAt(z),"g");
					val2 = val2.replace(letra,"");
				}
			}
		}

		val = '';
		val3 = new Array();

		for(s=0; s<vPatron.length; s++){
			val3[s] = val2.substring(0,vPatron[s]);
			val2 = val2.substr(vPatron[s]);
		}

		for(q=0;q<val3.length; q++){
			if(q ==0){
				val = val3[q];
			}
			else{
				if(val3[q] != ""){
					val += vSeparador + val3[q];
					}
			}
		}

		pObjeto.value = val;
		pObjeto.valant = val;

		}
}

function go(url){
	window.location=url;
}