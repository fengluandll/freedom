var RequestModelEscrituraPoderes = function (_parent) {
    var _this = this;
    var parent = _parent;
    var request = _parent.request;
    
    this.handler = '/Abc_PoderesGenerales?action=';

    var handler = function (action) {
        return _this.handler + action;
    }

    this.getListEscRev = function (id, listeners) {
        var args = "IdCia=" + id;
        
        request(handler('getListEscRev'), args, listeners);
    }
    
    this.getCatalogo = function (id, listeners) {
        var args = "Id=" + id;
        
        request(handler('getCATALOGOS'), args, listeners);
    }

    this.newEscritura = function (beanEscritura,listDocs,idUsuarioActual, listeners) {
    
    	var gdbLstA = Util.fillGenericDataBeans(listDocs);
        var gdbLstB = Util.fillGenericDataBeans(beanEscritura.Apoderados);        
        var gdbEsc = Util.newGenericBean(beanEscritura);
        var args = "HeadEscritura=" + $.URLEncode($.toJSON(gdbEsc));
        args += "&ListDocumentos=" + $.URLEncode($.toJSON(gdbLstA));
        args += "&ListPoderes=" + $.URLEncode($.toJSON(gdbLstB));
        
        for(var i in beanEscritura.Apoderados){
    		var poder = beanEscritura.Apoderados[i];
    		var listApoderados = Util.fillGenericDataBeans(poder.Apoderados);    		 
    		args += "&ListApoderados"+i+"=" + $.URLEncode($.toJSON(listApoderados));
    		var listFacultades = Util.fillGenericDataBeans(poder.Facultades);    		 
    		args += "&Facultades"+i+"=" + $.URLEncode($.toJSON(listFacultades));
    		for(var j in poder.Facultades){
    			var facultad = poder.Facultades[j];
    			var listMancomunados = Util.fillGenericDataBeans(facultad.listMancomunados); 
    			args += "&ListMancomunados"+i+"_"+j+"=" + $.URLEncode($.toJSON(listMancomunados));
    		}
    		
    	}
        args += "&ID_Usuario_Actual=" + $.URLEncode($.toJSON( parseInt(idUsuarioActual)));
        
      //beanEscritura.Apoderados = null;
        listeners.engaged={msg:'Guardando escritura.'};
        request(handler('newEscritura'), args, listeners);
    }
    
    this.updEscritura = function (beanEscritura,listDocs,idUsuarioActual, listeners) {
        
    	var gdbLstA = Util.fillGenericDataBeans(listDocs);
        var gdbLstB = Util.fillGenericDataBeans(beanEscritura.Apoderados);        
        var gdbEsc = Util.newGenericBean(beanEscritura);
        var args = "HeadEscritura=" + $.URLEncode($.toJSON(gdbEsc));
        args += "&ListDocumentos=" + $.URLEncode($.toJSON(gdbLstA));
        args += "&ListPoderes=" + $.URLEncode($.toJSON(gdbLstB));
        
        var listRevocados = new Array();
        for(var i in beanEscritura.Apoderados){
    		var poder = beanEscritura.Apoderados[i];
    		var listApoderados = Util.fillGenericDataBeans(poder.Apoderados);  
    		for(var a in poder.Apoderados){
    			var apod = poder.Apoderados[a];
    			if(apod.ind_status==2)
    				listRevocados.push(apod.Revocar);
    		}
    		args += "&ListApoderados"+i+"=" + $.URLEncode($.toJSON(listApoderados));
    		var listFacultades = Util.fillGenericDataBeans(poder.Facultades);    		 
    		args += "&Facultades"+i+"=" + $.URLEncode($.toJSON(listFacultades));
    		for(var j in poder.Facultades){
    			var facultad = poder.Facultades[j];
    			var listMancomunados = Util.fillGenericDataBeans(facultad.listMancomunados); 
    			args += "&ListMancomunados"+i+"_"+j+"=" + $.URLEncode($.toJSON(listMancomunados));
    		}
    	}
        args += "&ID_Usuario_Actual=" + $.URLEncode($.toJSON(parseInt(idUsuarioActual)));
        
        var listRevo = Util.fillGenericDataBeans(listRevocados);
        args += "&ListRevocados=" + $.URLEncode($.toJSON(listRevo));
      //beanEscritura.Apoderados = null;
        listeners.engaged={msg:'Actualizando escritura.'};
        request(handler('updEscritura'), args, listeners);
    }
    
    this.cpyEscritura = function (beanEscritura,cnyList,type,mode,mtype,idUsuarioActual
    		,Head
    		,Status
    		,Docs
    		,Poderes
    		,listeners) {
        
    	        
        var gdbEsc = Util.newGenericBean(beanEscritura);
        var args = "HeadEscritura=" + $.URLEncode($.toJSON(gdbEsc));
        args += "&CompanyList=" + $.URLEncode($.toJSON(cnyList));
        args += "&CopyType=" + $.URLEncode(type);
        args += "&CopyMode=" + $.URLEncode(mode);
        args += "&ModeType=" + $.URLEncode(mtype);
        args += "&ID_Usuario_Actual=" + $.URLEncode(idUsuarioActual);
        args += "&Head=" + $.URLEncode(Head);
        args += "&Status=" + $.URLEncode(Status);
        args += "&Docs=" + $.URLEncode(Docs);
        args += "&Poderes=" + $.URLEncode(Poderes);
                
      //beanEscritura.Apoderados = null;
        listeners.engaged={msg:'Copiando escritura.'};
        request(handler('cpyEscritura'), args, listeners);
    }
    
    
    this.getDetailsEscritura = function (id, listeners) {
    
        var args = "ID_Escritura=" + id;
        listeners.engaged={msg:'Descargando detalles de la escritura.'};
        request(handler('getDetailsEscritura'), args, listeners);
    }
    
    this.consultarEscrituras = function (idEmpresa, tipoDeEscritura, paramDeBusqueda, listeners){
    	var args = "ID_Empresa=" + idEmpresa + "&Tipo_De_Escritura=" + tipoDeEscritura + "&Busqueda=" + paramDeBusqueda;
    	request(handler('consultarEscrituras'), args, listeners);
    }
    
    this.deleteEscrituraPoder = function (idEmpresa, tipoDeEscritura,idEscrituraPoder, idUsuarioActual, listeners){
    	var args = "ID_Empresa=" + idEmpresa + "&Tipo_De_Escritura=" + tipoDeEscritura + "&ID_Escritura_Poder=" + idEscrituraPoder + "&ID_Usuario_Actual=" + idUsuarioActual;
    	listeners.engaged={msg:'Eliminando escritura.'};
    	request(handler('deleteEscrituraPoder'), args, listeners);
    }
    
    this.getDocumentums = function (id, listeners) {
        var args = "ID_Escritura=" + id;
        
        request(handler('getDocumentums'), args, listeners);
    }
    
    this.getOtorgaPoder = function (id, listeners) {
        var args = "ID_Escritura=" + id;
        
        request(handler('getOtorgaPoder'), args, listeners);
    }
      
}
Request.EscrituraPoderes = new RequestModelEscrituraPoderes(Request);