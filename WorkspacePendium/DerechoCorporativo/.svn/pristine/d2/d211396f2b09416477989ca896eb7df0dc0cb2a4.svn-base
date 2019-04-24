var TIPO_ESCRITURA_PODER_PE = 'PE';

var MetaSession = null;
var tablaPoderesEspecialesBody = "#tablaPoderesEspecialesBody";

$(document).ready(function() {
	
	try {
		var mstr = $("#MetaSession").html();		
		var msAux = $.parseJSON(mstr);
		MetaSession = new Object();
		MetaSession.SessionBean = msAux[0];
		MetaSession.Context = msAux[1];
	} catch (e) {
		//alert(e);
	}	
	
	$("#btnPEQuery").click(function(){		
		var filtroDeBusqueda = $("#txtPEQuery").val().trim();			
		cargarTablaDeEscriturasPE(filtroDeBusqueda);
	});
	
	cargarTablaDeEscriturasPE();
	
});

var cargarTablaDeEscriturasPE = function(filtroDeBusqueda){
	var filtro = "";
	
	if(filtroDeBusqueda != null)
		filtro = filtroDeBusqueda;
	
	var requestModel = new RequestModelConsultaPoderes(Request);
	requestModel.getEscrituraPoder(MetaSession.SessionBean.idCurrentEmpresa, TIPO_ESCRITURA_PODER_PE, filtro, {
		response : function(escrituras) {
			var escriturasBeanList = Util.getBeanList(escrituras[0]);
			dibujarTablaDeEscriturasPE(escriturasBeanList);								
		},
		error : function(xhttp, e) {
      	  sweetAlert('No se pudo realizar la operacion', 'Error:' + e,'error');
		},
		complete : function() {

		}
	});
};

var dibujarTablaDeEscriturasPE = function(escriturasBeanList){
	 $(tablaPoderesEspecialesBody).html("");
	 $.each(escriturasBeanList, function(i, escritura) {
			var row = $("<tr>");
			if(i%2 == 0)
				row.addClass("tableRow2");
							
			var numeroDeEscritura = "<td>" + (escritura.des_escritura == null ?  "" : escritura.des_escritura) + "</td>";
			$(row).append(numeroDeEscritura);
			
			var fecha = "<td>" + (escritura.fec_fecha == null ?  "" : escritura.fec_fecha) + "</td>";
			$(row).append(fecha);
			
			var listaDeApoderados = "<td>" + (escritura.desc_apoderados == null ?  "" : escritura.desc_apoderados) + "</td>";
			$(row).append(listaDeApoderados);
			
			var asunto = "<td>" + (escritura.desc_asunto == null ?  "" : escritura.desc_asunto) + "</td>";
			$(row).append(asunto);
			
			$(row).append("<td align='center'> <img src='" + getPESemaforoRP(escritura) + "'/> </td>");
			
			$(row).append("<td align='center'> <img src='" + getPESemaforoRPPC(escritura) + "'/> </td>");
			
			$(row).append("<td align='center'><img src='/DerechoCorporativo/img/icons/List.png'/></td>").click(function(){
				
			});
			
			$(tablaPoderesEspecialesBody).append(row);
										 			
		});	
};

var getPESemaforoRP = function(bean){
	
	if(!bean.ind_requiere_proto){
		return "/DerechoCorporativo/img/semaforo_gray.png";
	}
	else if(bean.des_escritura == null || bean.num_documentum_instr == null 
		|| bean.fec_otorgamiento_instr == null || bean.num_licenciado == "-1"){ 
		return "/DerechoCorporativo/img/semaforo_red.png";
	}
	else{
		return "/DerechoCorporativo/img/semaforo_green.png";
	}
};

var getPESemaforoRPPC = function(bean){
	
	if (!bean.ind_requiere_inscr_rppc || bean.ind_requiere_inscr_rppc == '0' || bean.ind_requiere_inscr_rppc == '' )
		return "/DerechoCorporativo/img/semaforo_gray.png";
	
	if(bean.fec_registro == null || bean.num_folio_merc == "") 
		return "/DerechoCorporativo/img/semaforo_red.png";
	else
		return "/DerechoCorporativo/img/semaforo_green.png";
};