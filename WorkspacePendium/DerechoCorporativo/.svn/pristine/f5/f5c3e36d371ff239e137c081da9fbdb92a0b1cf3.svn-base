var TIPO_ESCRITURA_PODER_PG = 'PG';

var MetaSession = null;
var tablaPoderesGeneralesBody = "#tablaPoderesGeneralesBody";

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
	
	
	$("#btnPGQuery").click(function(){		
		var filtroDeBusqueda = $("#txtPGQuery").val().trim();			
		cargarTablaDeEscriturasPG(filtroDeBusqueda);
	});
	
	cargarTablaDeEscriturasPG();
	
});

var cargarTablaDeEscriturasPG = function(filtroDeBusqueda){
	var filtro = "";
	
	if(filtroDeBusqueda != null)
		filtro = filtroDeBusqueda;
	
	var requestModel = new RequestModelConsultaPoderes(Request);
	requestModel.getEscrituraPoder(MetaSession.SessionBean.idCurrentEmpresa, TIPO_ESCRITURA_PODER_PG, filtro, {
		response : function(escrituras) {
			var escriturasBeanList = Util.getBeanList(escrituras[0]);
			dibujarTablaDeEscriturasPG(escriturasBeanList);								
		},
		error : function(xhttp, e) {
      	  sweetAlert('No se pudo realizar la operacion', 'Error:' + e,'error');
		},
		complete : function() {

		}
	});
};

var dibujarTablaDeEscriturasPG = function(escriturasBeanList){
	 $(tablaPoderesGeneralesBody).html("");
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
			
			$(row).append("<td align='center'> <img src='" + getPGSemaforoRP(escritura) + "'/> </td>");
			
			$(row).append("<td align='center'> <img src='" + getPGSemaforoRPPC(escritura) + "'/> </td>");
			
			$(row).append("<td align='center'><img src='/DerechoCorporativo/img/icons/List.png'/></td>").click(function(){
			    var left = screen.width - ((screen.width - 300) / 1);
			    var top = (screen.height - 700) / 1;  

				newwindow=window.open('', 'name', 'height=600,width=650,toolbar=0,menubar=0,location=0,status=0,scrollbars=1,resizable=1,left=' + left + ',top=' + top);

				if (window.focus) {newwindow.focus()}

			});
			
			$(tablaPoderesGeneralesBody).append(row);
										 			
		});	
};

var getPGSemaforoRP = function(bean) {
	
	if (!bean.ind_requiere_proto)
		return "/DerechoCorporativo/img/semaforo_gray.png";

	if (bean.des_escritura == null || bean.num_documentum_instr == null
			|| bean.fec_otorgamiento_instr == null || bean.num_licenciado == "-1")
		return "/DerechoCorporativo/img/semaforo_red.png";
	else
		return "/DerechoCorporativo/img/semaforo_green.png";
};

var getPGSemaforoRPPC = function(bean) {		
	if (!bean.ind_requiere_inscr_rppc || bean.ind_requiere_inscr_rppc == '0' || bean.ind_requiere_inscr_rppc == '' )
		return "/DerechoCorporativo/img/semaforo_gray.png";

	if (bean.fec_registro == null || bean.num_folio_merc == "")
		return "/DerechoCorporativo/img/semaforo_red.png";
	else
		return "/DerechoCorporativo/img/semaforo_green.png";
};