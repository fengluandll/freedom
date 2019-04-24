var ClassRevocacion = function(dlgRevocacion){
	
	var ClassRevocar = function() {
		this.Escritura = arguments[0];
		this.Concepto = arguments[1];
		this.Fecha = arguments[2];
		this.NoDoc = arguments[3];

		this.id_revoca_ep_pk = arguments[0];
		this.id_opoder_ep_fk = arguments[0];
		this.id_ep_fk = arguments[0];
		this.id_apod_ep_fk = arguments[0];

		this.ind_razonrevoca = arguments[0];
		this.des_razonrevoca = this.Concepto;
		this.id_escriturarevoca_fk = this.Escritura;
		this.id_documentumrevoca = this.NoDoc;
		this.fec_revoca = this.Fecha;

		this.des_textorevoca = arguments[0];
		this.desc_apendicerevoca = arguments[0];
		this.num_created_by = arguments[0];

	}

	var initDlgRevocacion = function() {
		dlgRevocacion = $(dlgRevocacion).dialog({
			autoOpen : false,
			height : "auto",
			width : 800,
			modal : true,
			buttons : {
				Cancelar : function() {
					dlgRevocacion.dialog('close');
				},
				"Aplicar Revocaciones" : dlgRevocacion_save
			}
		});

		WebKernel.newWebForm("PGForm_DlgRevocacion", "PGModule");

		$("#PGForm_DlgRevocacion_grid_Revocados")
				.jGridContentMVC(
						{
							gridId : "PGForm_DlgRevocacion_grid_Revocados",
							noDataMsg : 'no hay registros para mostrar',
							storage : [],
							rowClick : null,
							cellClick : function(iRow, iCell, bean, pty,
									gridId, td, event) {
								switch (pty) {
								case 'Revocar':
								case '_Selecc':

									$("#PGForm_DlgRevocacion_divRevocar").data(
											'Bean', bean);

									dlgRevocacion.buttonsMain = dlgRevocacion
											.dialog("option", "buttons");

									// Setter
									dlgRevocacion
											.dialog(
													"option",
													"buttons",
													[
															{
																text : "cancelar",
																icons : {
																	primary : "ui-icon-circle-arrow-w"
																},
																click : function() {
																	$(
																			"#PGForm_DlgRevocacion_divRevocar")
																			.hide();
																	$(
																			"#PGForm_DlgRevocacion_divGrid")
																			.show();
																	dlgRevocacion
																			.dialog(
																					"option",
																					"buttons",
																					dlgRevocacion.buttonsMain);
																}
															},
															{
																text : "Revocar",
																icons : {
																	primary : "ui-icon-circle-check"
																},
																click : function() {
																	// [iRow,
																	// iCell,
																	// bean,
																	// pty,
																	// gridId]
																	var bean = $(
																			"#PGForm_DlgRevocacion_divRevocar")
																			.data(
																					'Bean');
																	bean.ind_status = 2;
																	bean.Revocar = new ClassRevocar(
																			$(
																					"#PGForm_DlgRevocacion_cmb_Escritura")
																					.val(),
																			$(
																					"#PGForm_DlgRevocacion_cmb_Escritura_cmb_razon")
																					.val(),
																			$(
																					"#PGForm_DlgRevocacion_cmb_Escritura_date_fecha")
																					.val(),
																			$(
																					"#PGForm_DlgRevocacion_doc_Documento_digital")
																					.val());

																	$(
																			"#PGForm_DlgRevocacion_divRevocar")
																			.hide();
																	$(
																			"#PGForm_DlgRevocacion_divGrid")
																			.show();
																	dlgRevocacion
																			.dialog(
																					"option",
																					"buttons",
																					dlgRevocacion.buttonsMain);
																	$(
																			"#PGForm_DlgRevocacion_grid_Revocados")
																			.jGridContentMVC_UpDate();
																}
															} ]);

									if (bean.ind_status == 2) {
												$(
														"#PGForm_DlgRevocacion_cmb_Escritura")
														.val(
																bean.Revocar.Escritura),
												$(
														"#PGForm_DlgRevocacion_cmb_Escritura_cmb_razon")
														.val(
																bean.Revocar.Concepto),
												$(
														"#PGForm_DlgRevocacion_cmb_Escritura_date_fecha")
														.val(bean.Revocar.Fecha),
												$(
														"#PGForm_DlgRevocacion_doc_Documento_digital")
														.val(bean.Revocar.NoDoc);
									}

									$("#GForm_DlgRevocacion_LblName").html(
											bean.desc_nom_empl);

									$("#PGForm_DlgRevocacion_divGrid").hide();
									$("#PGForm_DlgRevocacion_divRevocar")
											.show();

									break;
								}
							},
							rowCssA : 'odd',
							rowCssB : 'even',
							rowFinal : null,
							rowAlign : 'center',
							Encabezados : true,
							HeadCss : "HHead",
							Heads : true,
							columns : [
									{
										HeadText : '',
										'width' : '10%',
										id : 'des_grupo',
										type : 'action',
										'align' : 'left'
									},
									{
										HeadText : 'Apoderados',
										'width' : '60%',
										id : 'desc_nom_empl',
										type : 'action',
										'align' : 'left'
									},
									{
										HeadText : '',
										'width' : '25%',
										id : '_Selecc',
										type : 'action',
										'align' : 'left',
										cellValue : 'eval',
										eval : function(bean) {
											return bean.ind_status == 2 ? 'revocado'
													: "";
										}
									},
									{
										HeadText : 'Revocar',
										'width' : '5%',
										id : 'Revocar',
										type : 'action',
										'align' : 'left',
										cellValue : 'eval',
										eval : function(bean) {
											return '<div class="ui-state-default ui-corner-all" style="width:15px;"><span class="ui-icon ui-icon-circle-close"/></div>';
										}
									} ],
							tblCssClass : "tablesorter",
							tblStyle : "width:100%",
							cellpadding : '0',
							cellspacing : '0',
							border : '0',
							afterDrawing : function() {

							}
						});

		dlgRevocacion.open = function(workBean) {
			$("#PGForm_DlgRevocacion_grid_Revocados").jGridContentMVC_UpDate(workBean.Apoderados);
			
			Request.EscrituraPoderes.getListEscRev(MetaSession.SessionBean.idCurrentEmpresa,{
				response : function(rsp) {
					
						var list = Util.getBeanList(rsp);
												
						WebForm.updController("PGForm_DlgRevocacion_cmb_Escritura",list);
				},
				error : function(xhttp, e) {
                	  sweetAlert('No se pudo realizar la operacion', 'Error:' + e,'error');
				},
				complete : function() {
					
				}
			});
			// WebKernel.WebForm.updController('PGForm_DlgRevocacion_cmb_Escritura',Grid_ERmain);
			$(dlgRevocacion).data('WorkBean', workBean);
			dlgRevocacion.dialog('open');
		}
	}

	var dlgRevocacion_save = function() {

		var revocados = $("#PGForm_DlgRevocacion_grid_Revocados")
				.jGridContentMVC_GetList();
		var apenIndex = 0;
		for ( var i in revocados) {
			var apod = revocados[i];
			if (apod.ind_status == 2) {
				var strAux = "";
				switch (apod.Revocar.Concepto) {
				case '1':
					strAux += ' Mandato termin\u00F3 por: Revocaci\u00F3n en Esc. No.<input type="hidden" id="Apod_'
							+ i
							+ '" value="'
							+ apod.Revocar.NoDoc
							+ '"/> <a class="ApodDetail" idHdd="Apod_'
							+ i
							+ '" title="click para ver escritura" href="#"> '
							+ apod.Revocar.Escritura
							+ '</a> del '
							+ apod.Revocar.Fecha;
					break;
				case '2':
					strAux += " Mandato Termin\u00F3 por: Muerte "
							+ apod.Revocar.Fecha;
					break;
				case '3':
					strAux += " Mandato Termin\u00F3 por Vigencia "
							+ apod.Revocar.Fecha;
					break;
				case '4':
					strAux += " Mandato termin\u00F3 por: Se agot\u00F3 el objeto";
					break;
				case '5':
					strAux += ' Mandato termin\u00F3 por: <input type="hidden" id="Apod_'
							+ i
							+ '" value="'
							+ apod.Revocar.NoDoc
							+ '"/> <a class="ApodDetail" idHdd="Apod_'
							+ i
							+ '" title="click para ver carta de renuncia" href="#"> Carta Renuncia </a> de fecha '
							+ apod.Revocar.Fecha;
					break;
				}
				apod.desc_revoca = strAux;

				if (apod.ind_aprevoca) {
					var apeIndex = '<label style="color:red;">'
							+ apod.ind_aprevoca + '</label>'
					apod.desc_revoca = apeIndex + " " + apod.desc_revoca;
					continue;
				}
				var similitud = false;
				for ( var ai in revocados) {
					var apodAux = revocados[ai];
					if (ai != i)
						if (apod.desc_revoca == apodAux.desc_revoca) {
							apod.ind_aprevoca = apodAux.ind_aprevoca;
							similitud = true;
							break;
						}

				}
				if (!similitud)
					apod.ind_aprevoca = ++apenIndex;

				var apeIndex = '<label style="color:red;">' + apod.ind_aprevoca
						+ '</label>'
				apod.desc_revoca = apeIndex + " " + apod.desc_revoca;
			}
		}

		var workBean = $(dlgRevocacion).data('WorkBean');
		var list = workBean.Apoderados;
		var ga = list.length == 0 ? "" : list[0].des_grupo;
		var names = ga == '' ? '' : '<div style="font-weight: bold;">' + ga
				+ '</div>';
		$(list)
				.each(
						function(i, bean) {

							var g = bean.des_grupo;
							if (g != ga) {
								names += ga == '' ? ''
										: '<div style="font-weight: bold;">'
												+ g + '</div>';
								ga = g;
							}

							names += '&#9679; \t '
									+ (bean.value == undefined ? bean.desc_nom_empl
											: bean.value)
									+ (bean.ind_status == 2 ? ' <label style="color:red;">'
											+ bean.ind_aprevoca + '</label>'
											: '') + '<br /> ';

						});

		workBean.desc_apoderados = names;

		$("#PGForm_Grid_apoderados").jGridContentMVC_UpDate();
		dlgRevocacion.dialog('close');
	}
}