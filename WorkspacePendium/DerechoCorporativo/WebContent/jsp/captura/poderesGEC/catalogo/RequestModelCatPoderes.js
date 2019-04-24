var RequestModelCatalogoPoderes = function (_parent) {
    var _this = this;
    var parent = _parent;
    var request = _parent.request;
    
    this.handler = '/DerechoCorporativo/Abc_PoderesGenerales?action=';

    var handler = function (action) {
        return _this.handler + action;
    }

    this.newCatalogoPoder = function (beanPoder, idCatalogo, idUsuarioActual, listeners) {
           
        var gdbCatalogoPoder = Util.newGenericBean(beanPoder);
        var args = "CatalogoPoder=" + $.URLEncode($.toJSON(gdbCatalogoPoder));
        args += "&ID_Catalogo=" + $.URLEncode($.toJSON( parseInt(idCatalogo))); 
        args += "&ID_Usuario_Actual=" + $.URLEncode($.toJSON( parseInt(idUsuarioActual)));
              
        listeners.engaged={msg:'Guardando catalogo.'};
        request(handler('newCatalogoPoder'), args, listeners);
    }
    
    this.updCatalogoPoder = function (beanPoder, idCatalogo, idUsuarioActual, listeners) {
        
    	 var gdbCatalogoPoder = Util.newGenericBean(beanPoder);
         var args = "CatalogoPoder=" + $.URLEncode($.toJSON(gdbCatalogoPoder));
         args += "&ID_Catalogo=" + $.URLEncode($.toJSON( parseInt(idCatalogo))); 
         args += "&ID_Usuario_Actual=" + $.URLEncode($.toJSON( parseInt(idUsuarioActual)));
               
         listeners.engaged={msg:'Guardando catalogo.'};
         request(handler('updCatalogoPoder'), args, listeners);
    }
       
    this.deleteCatalogoPoder = function (idCatalogo, idUsuarioActual, listeners){
    	var args = "ID_Catalogo_Poder=" + $.URLEncode($.toJSON( parseInt(idCatalogo))) + "&ID_Usuario_Actual=" + $.URLEncode($.toJSON( parseInt(idUsuarioActual)));
    	listeners.engaged={msg:'Eliminando catalogo.'};
    	request(handler('deleteCatalogoPoder'), args, listeners);
    }
    
    this.getCatalogosPoderes = function (tipoCatalogo, nombreCatalogoPoder, listeners){
    	var args = "Tipo_Catalogo_Poder=" + $.URLEncode($.toJSON(tipoCatalogo)) + "&Nombre_Catalogo_Poder=" + $.URLEncode($.toJSON(nombreCatalogoPoder));
    	listeners.engaged={msg:'Obteniendo catalogos.'};
    	request(handler('getCatalogosPoderes'), args, listeners);
    }
  
}

Request.CatalogoPoderes = new RequestModelCatalogoPoderes(Request);