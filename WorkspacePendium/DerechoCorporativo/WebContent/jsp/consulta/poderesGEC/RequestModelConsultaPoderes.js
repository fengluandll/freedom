var RequestModelConsultaPoderes = function (_parent) {
    var _this = this;
    var parent = _parent;
    var request = _parent.request;
    
    this.handler = '/DerechoCorporativo/Abc_PoderesGenerales?action=';

    var handler = function (action) {
        return _this.handler + action;
    }

    this.getListEscRev = function (id, listeners) {
        var args = "IdCia=" + id;
        
        request(handler('getListEscRev'), args, listeners);
    }
          
    this.getDetailsEscritura = function (id, listeners) {
    
        var args = "ID_Escritura=" + id;
        listeners.engaged={msg:'Descargando detalles de la escritura.'};
        request(handler('getDetailsEscritura'), args, listeners);
    }
    
    this.getEscrituraPoder = function (idEmpresa, tipoDeEscritura, paramDeBusqueda, listeners){
    	var args = "ID_Empresa=" + idEmpresa + "&Tipo_De_Escritura=" + tipoDeEscritura + "&Busqueda=" + paramDeBusqueda;
    	request(handler('getEscrituraPoder'), args, listeners);
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