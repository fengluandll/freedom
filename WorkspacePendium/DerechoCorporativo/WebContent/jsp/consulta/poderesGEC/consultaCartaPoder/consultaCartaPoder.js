var TIPO_ESCRITURA_PODER_CP = 'CP';

var MetaSession = null;
var tablaCartaPoderBody = "#tablaCartaPoderBody";

$(document).ready(function() {
	
	try {
		var mstr = $("#MetaSession").html();		
		var msAux = $.parseJSON(mstr);
		MetaSession = new Object();
		MetaSession.SessionBean = msAux[0];
		MetaSession.Context = msAux[1];
	} catch (e) {
		alert(e);
	}
	
	
	$("#btnCPQuery").click(function(){		
		var filtroDeBusqueda = $("#txtCPQuery").val().trim();			
		cargarTablaDeEscrituras(filtroDeBusqueda);
	});
	
	cargarTablaDeEscrituras();
	
});

var cargarTablaDeEscrituras = function(filtroDeBusqueda){
	var filtro = "";
	
	if(filtroDeBusqueda != null)
		filtro = filtroDeBusqueda;
	
	var requestModel = new RequestModelConsultaPoderes(Request);
	requestModel.getEscrituraPoder(MetaSession.SessionBean.idCurrentEmpresa, TIPO_ESCRITURA_PODER_CP, filtro, {
		response : function(escrituras) {
			var escriturasBeanList = Util.getBeanList(escrituras[0]);
			dibujarTablaDeEscrituras(escriturasBeanList);								
		},
		error : function(xhttp, e) {
      	  sweetAlert('No se pudo realizar la operacion', 'Error:' + e,'error');
		},
		complete : function() {

		}
	});
};

var dibujarTablaDeEscrituras = function(escriturasBeanList){
	 $(tablaCartaPoderBody).html("");
	 $.each(escriturasBeanList, function(i, escritura) {
			var row = $("<tr>");
			if(i%2 == 0)
				row.addClass("tableRow2");
													
			var fecha = "<td>" + (escritura.fec_fecha == null ?  "" : escritura.fec_fecha) + "</td>";
			$(row).append(fecha);
			
			var listaDeApoderados = "<td>" + (escritura.desc_apoderados == null ?  "" : escritura.desc_apoderados) + "</td>";
			$(row).append(listaDeApoderados);
			
			var caracteristicas = "<td>" + (escritura.des_caracteristicas == null ?  "" : escritura.des_caracteristicas) + "</td>";
			$(row).append(caracteristicas);
			
			var asunto = "<td>" + (escritura.desc_asunto == null ?  "" : escritura.desc_asunto) + "</td>";
			$(row).append(asunto);
			
			$(row).append("<td align='center'> <img src='" + getSemaforoCartaPoder(escritura) + "'/> </td>");						
			
			$(row).append("<td align='center'><img src='/DerechoCorporativo/img/icons/List.png'/></td>").click(function(){
				
			});
			
			$(tablaCartaPoderBody).append(row);
										 			
		});	
};

var getSemaforoCartaPoder = function(bean) {			
	if (bean.num_documentum_instr == null || bean.fec_fecha == null || bean.ind_delegado_por == "-1"){			
		return "/DerechoCorporativo/img/semaforo_red.png";
}
	else{
		return "/DerechoCorporativo/img/semaforo_green.png";
	}
}


