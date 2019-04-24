<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
String nonCache = false ? "" : "?lastversion=" + new java.util.Random().nextInt(); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta content="text/html" http-equiv="content-type" charset="utf-8"></meta>
    <title></title>
    <link href="../../../css/webCore.css" rel="stylesheet" type="text/css" />
    <link href="../../../css/jquery-ui-css/pendium/jquery-ui.css" rel="stylesheet" type="text/css" />
    <script src="../../../js/jquery/jquery.js" type="text/javascript; charset=iso-8859-1"></script>
    <script src="../../../css/jquery-ui-css/pendium/jquery-ui.js" type="text/javascript; charset=iso-8859-1"></script>
    
        
    <link href="../../../css/jquery-ui-css/blue-moon/GridSort.css" rel="stylesheet" type="text/css" />
    <script src="../../../js/jquery/plugins/jGridContentMVC-1.0.2.js" type="text/javascript; charset=iso-8859-1"></script>
    <script src="../../../js/jquery/plugins/jquery.tablesorter.js" type="text/javascript; charset=iso-8859-1"></script>
    
    <script src="../../../js/jquery/WebKernel/webKernel.js<%=nonCache %>" type="text/javascript; charset=iso-8859-1"></script>
    
    <link href="indexPoderes/indexPoderes.css<%=nonCache %>" rel="stylesheet" type="text/css" />
    <script src="indexPoderes/indexPoderes.js<%=nonCache %>" type="text/javascript; charset=iso-8859-1"></script>
    
</head>
<body>
    <div id="tabsPoderes">
	<ul>
		<li><a href="#tabsPoderes-1">Generales</a></li>
		<li><a href="#tabsPoderes-2">Especiales</a></li>
		<li><a href="#tabsPoderes-3">Carta Poder</a></li>
	</ul>
	<div id="tabsPoderes-1">
		<div id="divPGMain">
        <table width="100%">        
            <tr>        
                <td>        
                    <input type="text" id="txtPGQuery" /><button type="button" id="btnPGQuery">Buscar</button>
                </td>
                <td style="width:50%">
                    <div id="btnPGAdd" title="Crear Poder General" class="Img_Icon_new"></div>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <div id="gridPGmain"></div>
                </td>
            </tr>
        </table>  
       </div>   
       <div id="divPGForm" style="display:none">
       	<div id="divSeccH1" class="divSeccH" toggleContent="false">
       		Poder General &#45; Edita informaci&oacute;n <button id="divPGFormBtnClose">Cerrar</button>
       	</div>
       	<div id="divSeccH1Content" class="divSeccHContent">
       		
       		<table width="100%">
       			<tr>
       				<td style="width:50%">Delegado por</td>
       				<td><select type="text" field="ind_delegado_por" controller="cmb" class="divPGForm">
       					<option value="0">(Seleccione)</option>
       					<option value="Resoluciones del Administrador">Resoluciones del Administrador</option>
<option value="Sesi&oacute;n del Consejo de Administraci&oacute;n">Sesi&oacute;n del Consejo de Administraci&oacute;n</option>
<option value="Asamblea Especial de Accionistas de la Serie D">Asamblea Especial de Accionistas de la Serie "D"</option>
<option value="Asamblea General Ordinaria de Socios">Asamblea General Ordinaria de Socios</option>
<option value="Asamblea Extraordinaria de Socios">Asamblea Extraordinaria de Socios</option>
<option value="Asamblea General Extraordinaria de Socios">Asamblea General Extraordinaria de Socios</option>
<option value="Asamblea de Asociados">Asamblea de Asociados</option>
<option value="Asamblea General de Asociados">Asamblea General de Asociados</option>
<option value="Asamblea Ordinaria de Asociados">Asamblea Ordinaria de Asociados</option>
<option value="Asamblea General Ordinaria de Asociados">Asamblea General Ordinaria de Asociados</option>
<option value="Asamblea Extraordinaria de Asociados">Asamblea Extraordinaria de Asociados</option>
<option value="Asamblea General Extraordinaria de Asociados">Asamblea General Extraordinaria de Asociados</option>
<option value="Resoluciones por Votaci&oacute;n Un&aacute;nime de los Accionistas">Resoluciones por Votaci&oacute;n Un&aacute;nime de los Accionistas</option>
<option value="Resoluciones por Votaci&oacute;n Un&aacute;nime de los Socios">Resoluciones por Votaci&oacute;n Un&aacute;nime de los Socios</option>
<option value="Resoluciones por Votaci&oacute;n Un&aacute;nime de los Asociados">Resoluciones por Votaci&oacute;n Un&aacute;nime de los Asociados</option>
<option value="Resoluciones por Votaci&oacute;n Un&aacute;nime de los Consejeros">Resoluciones por Votaci&oacute;n Un&aacute;nime de los Consejeros</option>
<option value="Asamblea Ordinaria de Accionistas">Asamblea Ordinaria de Accionistas</option>
<option value="Asamblea General Ordinaria de Accionistas">Asamblea General Ordinaria de Accionistas</option>
<option value="Asamblea de Socios">Asamblea de Socios</option>
<option value="Asamblea General Extraordinaria de Accionistas">Asamblea General Extraordinaria de Accionistas</option>
<option value="N/A">N/A</option>
<option value="Asamblea General Ordinaria y Extraordinaria de Accionistas">Asamblea General Ordinaria y Extraordinaria de Accionistas</option>
       				</select></td>
       			</tr>
       			<tr>
       				<td>Fecha</td>
       				<td><input type="text" field="fec_fecha" controller="calendar" class="divPGForm"></input></td>
       			</tr>
       			<tr>
       				<td>Hora</td>
       				<td><input type="text" field="fec_hora" controller="sppiner" class="divPGForm"></input></td>
       			</tr>
       		</table>
       	</div>
       	<form id="divPGForm" action="/Abc_PoderesGenerales" method="post">
       	<div id="divSeccA" class="divSeccHead" toggleContent="false">
       		Instrumento 
       	</div>
       	<div id="divSeccAContent" class="divSeccContent">
       		
       		<table width="100%">       			
       			<tr>
       				<td style="width:50%">Requiere Protocolizaci&oacute;n:
       				</td>
       				<td>
       					<input type="checkbox" field="ind_requiere_proto" controller="check" class="divPGForm"></input>
       				</td>
       			</tr>
       			<tr>
       				<td>Requiere Inscripci&oacute;n RPPC:</td>
       				<td><input type="checkbox" field="ind_requiere_inscr_rppc" controller="check" class="divPGForm"></input></td>
       			</tr>
       			<tr>
       				<td>Sem&aacute;foro:</td>
       				<td><div field="nom_semaforo" controller="semaphore" class="divPGForm"></div></td>
       			</tr>
       		</table>
       			
       			<table id="divPGForm_RP" width="100%" style="display:none">
       			
       			<tr>
       				<td style="width:50%">Licenciado:</td>
       				<td><div field="num_licenciado" controller="cmb" class="divPGForm"></div></td>
       			</tr>
       			<tr>
       				<td>Notario P&uacute;blico:</td>
       				<td><input type="text" field="nom_notario_publico" controller="text" class="divPGForm"></input></td>
       			</tr>
       			<tr>
       				<td>Escritura No.</td>
       				<td><input type="text" field="des_escritura" controller="text" class="divPGForm"></input></td>
       			</tr>
       			<tr>
       				<td>Escritura Digitalizada:</td>
       				<td><input type="text" field="num_documentum_instr" controller="text" class="divPGForm"></input></td>
       			</tr>
       			<tr>
       				<td>Fecha Otorgamiento:</td>
       				<td><input type="text" field="fec_otorgamiento_instr" controller="calendar" class="divPGForm"></input></td>
       			</tr>
       			<tr>
       				<td>De:</td>
       				<td><select field="num_de" controller="cmb" class="divPGForm">
       					<option value="0">(Seleccione)</option>
       					<option> 	Dr. Campo Elias Mu&ntilde;oz Rubio </option>
						<option> Dr. Cornelio Salazar</option>
						<option> Dr. Jos&eacute; Vicente Troya Jaramillo </option>
						<option> Dra. Gisela Rangel</option>
       				</select></td>
       			</tr>
       			<tr>
       				<td>Suplencia / Asociado
       				</td>
       				<td><input type="text" field="des_suplencia_asociado" controller="text" class="divPGForm"></input></td>
       			</tr>
       			</table>
       			
       			
       			<table id="divPGForm_RPPC" width="100%" style="display:none">
       			<tr>
       				<td style="width:50%">Inscrita en el registro P&uacute;blico de comercio de:
       				</td>
       				<td><div field="num_inscrita_registro_publico" controller="cmb" class="divPGForm"></div></td>
       			</tr>
       			<tr>
       				<td>Fecha de Registro:
       				</td>
       				<td><input type="text" field="fec_registro" controller="calendar" class="divPGForm"></input></td>
       			</tr>
       			<tr>
       				<td> Folio Mercantil / Folio Mercantil Electr&oacute;nico</td>
       				<td><input type="text" field="num_folio_merc" controller="text" class="divPGForm"></input></td>
       			</tr>
       			<tr>
       				<td>Otros Datos de Registro</td>
       				<td><input type="text" field="des_otros_datos_registro" controller="text" class="divPGForm"></input></td>
       			</tr>
       		</table>
       		
       	</div>
       	<div id="divSeccB" class="divSeccHead" toggleContent="true">
       		Referencia Documentum 
       	</div>
       	<div id="divSeccBContent" class="divSeccContent">       		
       		<table width="100%">
       			<tr>
       				<td style="width:50%"><div class="SubTitleHead">Memo</div></td>
       				<td><input type="text" field="num_documentum_memo" controller="text" class="divPGForm" /></td>
       			</tr>
       			<tr>
       				<td>Solicitado por:</td>
       				<td><select field="num_solicitado_por" controller="cmb" class="divPGForm">
       					<option value="0">(Seleccione)</option><option value="13242" title="Abel P&eacute;rez Mart&iacute;nez ">Abel P&eacute;rez Mart&iacute;nez </option><option value="15366" title="&Aacute;driana N&aacute;jera Aramburu">&Aacute;driana N&aacute;jera Aramburu</option><option value="15343" title="Aisha P. Nasser">Aisha P. Nasser</option><option value="15340" title="Alberto Rivas Gonz&aacute;lez">Alberto Rivas Gonz&aacute;lez</option><option value="16389" title="Alfonso Aragon Buendia">Alfonso Aragon Buendia</option><option value="13175" title="Alicia Gasga Tapia">Alicia Gasga Tapia</option><option value="13254" title="Alicia Lebrija Hirschfeld ">Alicia Lebrija Hirschfeld </option><option value="13256" title="Ana Miriam Martinez Delgadillo">Ana Miriam Martinez Delgadillo</option><option value="16157" title="Angel Antonio Arratia Alonso">Angel Antonio Arratia Alonso</option><option value="14886" title="Armando Jos&eacute; German Hernandez">Armando Jos&eacute; German Hernandez</option><option value="13249" title="Armando P&eacute;rez S&aacute;nchez ">Armando P&eacute;rez S&aacute;nchez </option><option value="15328" title="Carlos Manuel Iturriaga Salguero">Carlos Manuel Iturriaga Salguero</option><option value="13248" title="Claudia Elodia Dom&iacute;nguez Cruz ">Claudia Elodia Dom&iacute;nguez Cruz </option><option value="13171" title="David Magdaleno Cort&eacute;s  ">David Magdaleno Cort&eacute;s  </option><option value="13246" title="David T&eacute;llez Navarrete ">David T&eacute;llez Navarrete </option><option value="15360" title="Eduardo Gallardo Islas">Eduardo Gallardo Islas</option><option value="16819" title="Empresa Alamos">Empresa Alamos</option><option value="16820" title="Empresa Andromaco">Empresa Andromaco</option><option value="16813" title="Empresa Atlixcoyotl">Empresa Atlixcoyotl</option><option value="16821" title="Empresa Balderas">Empresa Balderas</option><option value="16817" title="Empresa Benito Ju&aacute;rez">Empresa Benito Ju&aacute;rez</option><option value="16827" title="Empresa Bosques de Reforma">Empresa Bosques de Reforma</option><option value="16818" title="Empresa Chilaque">Empresa Chilaque</option><option value="16823" title="Empresa Fundidora">Empresa Fundidora</option><option value="16815" title="Empresa Independencia">Empresa Independencia</option><option value="16830" title="Empresa Leibnitz">Empresa Leibnitz</option><option value="16811" title="Empresa Lucio Blanco">Empresa Lucio Blanco</option><option value="16834" title="Empresa Luz Savi&ntilde;&oacute;n">Empresa Luz Savi&ntilde;&oacute;n</option><option value="16822" title="Empresa Montecito">Empresa Montecito</option><option value="16824" title="Empresa Porfirio D&iacute;az">Empresa Porfirio D&iacute;az</option><option value="16825" title="Empresa Presa Salinillas">Empresa Presa Salinillas</option><option value="16826" title="Empresa Rio Churubusco">Empresa Rio Churubusco</option><option value="16816" title="Empresa Rub&eacute;n Dar&iacute;o">Empresa Rub&eacute;n Dar&iacute;o</option><option value="16832" title="Empresa San Angel">Empresa San Angel</option><option value="16814" title="Empresa Tlalnepantla">Empresa Tlalnepantla</option><option value="16812" title="Empresa Tlalpan 3000">Empresa Tlalpan 3000</option><option value="16840" title="Empresa Tlalpan Santa Ursula">Empresa Tlalpan Santa Ursula</option><option value="16795" title="Empresa Vasco de Quiroga">Empresa Vasco de Quiroga</option><option value="16833" title="Empresa Yucatan">Empresa Yucatan</option><option value="16205" title="Enrique Gonz&aacute;lez Rodr&iacute;guez">Enrique Gonz&aacute;lez Rodr&iacute;guez</option><option value="13257" title="Federico Garrido Olvera ">Federico Garrido Olvera </option><option value="15530" title="Francisco de As&iacute;s Gerardo Pozo de la Tijera">Francisco de As&iacute;s Gerardo Pozo de la Tijera</option><option value="16175" title="Gerardo Mu&ntilde;oz de Cote Amescua">Gerardo Mu&ntilde;oz de Cote Amescua</option><option value="13239" title="Gonzalo Ortiz Garrido ">Gonzalo Ortiz Garrido </option><option value="14934" title="Guillermo Rivero Alvarez">Guillermo Rivero Alvarez</option><option value="13253" title="Gustavo Luis Pesce ">Gustavo Luis Pesce </option><option value="13255" title="Hilario Guti&eacute;rrez Escobedo   ">Hilario Guti&eacute;rrez Escobedo   </option><option value="13174" title="Hugo Morales Morante ">Hugo Morales Morante </option><option value="15334" title="Humberto Perea Maya">Humberto Perea Maya</option><option value="16582" title="Ignacio Gallardo Islas">Ignacio Gallardo Islas</option><option value="14669" title="Jose Armando Germ&aacute;n Hern&aacute;ndez">Jose Armando Germ&aacute;n Hern&aacute;ndez</option><option value="13177" title="Jose Luis Maldonado Maldonado">Jose Luis Maldonado Maldonado</option><option value="14938" title="Jos&eacute; Pedro Arias Garc&iacute;a">Jos&eacute; Pedro Arias Garc&iacute;a</option><option value="15320" title="Juan Eduardo Gallardo Islas">Juan Eduardo Gallardo Islas</option><option value="16167" title="Julio Barba Fern&aacute;ndez">Julio Barba Fern&aacute;ndez</option><option value="16165" title="Julio Barba Hurtado">Julio Barba Hurtado</option><option value="13250" title="Liuba C&aacute;rdenas Pulido ">Liuba C&aacute;rdenas Pulido </option><option value="16171" title="Luis Alejandro Bustos Olivares">Luis Alejandro Bustos Olivares</option><option value="14887" title="Luis Maldonado Palomares">Luis Maldonado Palomares</option><option value="13245" title="Magdalena Anastacia Cruz Zurita ">Magdalena Anastacia Cruz Zurita </option><option value="12904" title="Mar&iacute;a Azucena Dom&iacute;nguez Cobi&aacute;n">Mar&iacute;a Azucena Dom&iacute;nguez Cobi&aacute;n</option><option value="13173" title="Mar&iacute;a de la Luz Rodr&iacute;guez Carrillo">Mar&iacute;a de la Luz Rodr&iacute;guez Carrillo</option><option value="15541" title="Mar&iacute;a del Mar Ch&aacute;vez Rozada">Mar&iacute;a del Mar Ch&aacute;vez Rozada</option><option value="14666" title="Mart&iacute;n Villafuerte Osorio">Mart&iacute;n Villafuerte Osorio</option><option value="13238" title="M&oacute;nica Romero Arellano ">M&oacute;nica Romero Arellano </option><option value="13251" title="Noe Alvarez S&aacute;nchez ">Noe Alvarez S&aacute;nchez </option><option value="13178" title="Noemi Hern&aacute;ndez Resendiz">Noemi Hern&aacute;ndez Resendiz</option><option value="15357" title="Norma M&eacute;ndoza Venegas">Norma M&eacute;ndoza Venegas</option><option value="15335" title="Pablo Ayala Reyes">Pablo Ayala Reyes</option><option value="13240" title="Porfirio Pulido Tarinda ">Porfirio Pulido Tarinda </option><option value="14563" title="Rafael Oliveros Delgado ">Rafael Oliveros Delgado </option><option value="13176" title="Rafael S&aacute;nchez Becerril">Rafael S&aacute;nchez Becerril</option><option value="13172" title="Ranulfo Romero Rosas ">Ranulfo Romero Rosas </option><option value="14702" title="Raquel Bernal Cilia      ">Raquel Bernal Cilia      </option><option value="15342" title="Raul Lopez Maza">Raul Lopez Maza</option><option value="14660" title="Ricardo Medina Orozco">Ricardo Medina Orozco</option><option value="13241" title="Ricardo Solano Vazquez ">Ricardo Solano Vazquez </option><option value="16473" title="Roberto Vargas Vazquez">Roberto Vargas Vazquez</option><option value="13252" title="Rocio Barajas Rangel ">Rocio Barajas Rangel </option><option value="16324" title="Rodrigo Ruelas Reverter">Rodrigo Ruelas Reverter</option><option value="14946" title="Rodrigo Salazar">Rodrigo Salazar</option><option value="14667" title="Rogelio Yudho Simon">Rogelio Yudho Simon</option><option value="16476" title="Rolando Colchado Valenzuela">Rolando Colchado Valenzuela</option><option value="15869" title="Roxane Rodr&iacute;guez Aldape">Roxane Rodr&iacute;guez Aldape</option><option value="12901" title="Salvador Almar&aacute;z Campos  ">Salvador Almar&aacute;z Campos  </option><option value="15920" title="Salvador Rocha Cito">Salvador Rocha Cito</option><option value="16478" title="Salvador Vargas Alc&aacute;ntara">Salvador Vargas Alc&aacute;ntara</option><option value="15358" title="Sandra Perea Tamiz">Sandra Perea Tamiz</option><option value="13244" title="Sergio Flores Melgarejo ">Sergio Flores Melgarejo </option><option value="13247" title="Tannya Estela Torres Bernal ">Tannya Estela Torres Bernal </option><option value="13243" title="Ximena Bedoya Romero">Ximena Bedoya Romero</option>
       				</select></td>
       			</tr>
       			<tr>
       				<td>Fecha de Documento:</td>
       				<td><input type="text" field="fec_documento_memo" controller="calendar" class="divPGForm"></input></td>
       			</tr>
       			<tr>
       				<td>Fecha Recibido:
       				</td>
       				<td>
       					<input type="text" field="fec_recibido_memo" controller="calendar" class="divPGForm"></input>
       				</td>
       			</tr>
       			<tr>
       				<td>Folio No:</td>
       				<td><input type="text" field="num_folio" controller="text" class="divPGForm"></input></td>
       			</tr>
       			
       			<tr>
       				<td><div class="SubTitleHead">Documento de Entrega:</div></td>
       				<td><input type="text" field="num_documentum_entrega" controller="text" class="divPGForm"></input></td>
       			</tr>
       			<tr>
       				<td>Fecha de Documento:</td>
       				<td><input type="text" field="fec_documento_entrega" controller="calendar" class="divPGForm"></input></td>
       			</tr>
       			<tr>
       				<td>Fecha Recibido:</td>
       				<td><input type="text" field="fec_recibido_entrega" controller="calendar" class="divPGForm"></input></td>
       			</tr>
       			
       			<tr>
       				<td><div class="SubTitleHead">Otros:</div></td>
       				<td><input type="text" field="num_documentum_otros" controller="text" class="divPGForm"></input></td>
       			</tr>
       			<tr>
       				<td>Fecha de Documento:       				</td>
       				<td><input type="text" field="fec_documento_otros" controller="calendar" class="divPGForm"></input></td>
       			</tr>    			   
       			<tr>
       				<td>Fecha Recibido:</td>
       				<td><input type="text" field="fec_recibido_otros" controller="calendar" class="divPGForm"></input></td>
       			</tr>      			
       		</table>
       	</div>
       	<div id="divSeccC" class="divSeccHead" toggleContent="false">
       		Status
       	</div>
       	<div id="divSeccCContent" class="divSeccContent">
       		 
       		<table width="100%">
       			<tr>
       				<td style="width:50%">Aplica:</td>
       				<td><input type="checkbox" field="ind_aplica_status" controller="check" class="divPGForm"></input></td>
       			</tr>
       		</table>
       		<table id="divPGFormStatus" width="100%">
       			<tr>
       				<td style="width:50%">Sem&aacute;foro Status:</td>
       				<td><input type="text" field="nom_semaforo_status" controller="semaphore" class="divPGForm"></input></td>
       			</tr>
       			<tr>
       				<td>Fecha Programada de entrega:</td>
       				<td><input type="text" field="fec_prog_entrega_status" controller="calendar" class="divPGForm"></input></td>
       			</tr>
       			<tr>
       				<td><div class="SubTitleHead">Redactada:</div></td>
       				<td>
       					<input type="checkbox" field="ind_redactada" controller="check" class="divPGForm"></input>
       				</td>
       			</tr>
       			<tr>
       				<td>Responsable:</td>
       				<td><select field="num_resp_redactada" controller="cmb" class="divPGForm">
       					<option value="0">(Seleccione)</option>
       					<option>Blanca Elena Rodr&iacute;guez C&aacute;zares 	</option>
						<option>Blanca Nora Garc&iacute;a Leal</option>
						<option>Carlos Alberto Ballesteros Adalid</option>
						<option>Carlos Enrique Chavez Paredes</option>
						<option>Carlos Ernesto Orci Berea</option>
       				</select></td>
       			</tr>
       			<tr>
       				<td>Cumplimiento:</td>
       				<td><input type="text" field="fec_cumplimiento_redactada" controller="calendar" class="divPGForm"></input></td>
       			</tr>
       			 
       			<tr>
       				<td><div class="SubTitleHead">Revisi&oacute;n Gerente:</div></td>
       				<td>
       					<input type="checkbox" field="ind_revision_gerente" controller="check" class="divPGForm"></input>
       				</td>
       			</tr>
       			<tr>
       				<td>Responsable:</td>
       				<td><select field="num_resp_gerente" controller="cmb" class="divPGForm">
       					<option value="0">(Seleccione)</option>
       					<option>Blanca Elena Rodr&iacute;guez C&aacute;zares 	</option>
						<option>Blanca Nora Garc&iacute;a Leal</option>
						<option>Carlos Alberto Ballesteros Adalid</option>
						<option>Carlos Enrique Chavez Paredes</option>
						<option>Carlos Ernesto Orci Berea</option>
       				</select></td>
       			</tr>
       			<tr>
       				<td>Cumplimiento:</td>
       				<td><input type="text" field="fec_cumplimiento_gerente" controller="calendar" class="divPGForm"></input></td>
       			</tr>
       			
       			 
       			<tr>
       				<td><div class="SubTitleHead">Correcciones:</div></td>
       				<td>
       					<input type="checkbox" field="ind_correcciones" controller="check" class="divPGForm"></input>
       				</td>
       			</tr>
       			<tr>
       				<td>Responsable:</td>
       				<td><select field="num_resp_correcciones" controller="cmb" class="divPGForm">
       					<option value="0">(Seleccione)</option>
       					<option>Blanca Elena Rodr&iacute;guez C&aacute;zares 	</option>
						<option>Blanca Nora Garc&iacute;a Leal</option>
						<option>Carlos Alberto Ballesteros Adalid</option>
						<option>Carlos Enrique Chavez Paredes</option>
						<option>Carlos Ernesto Orci Berea</option>
       				</select></td>
       			</tr>
       			<tr>
       				<td>Cumplimiento:</td>
       				<td><input type="text" field="fec_cumplimiento_correcciones" controller="calendar" class="divPGForm"></input></td>
       			</tr>
       			
       			<tr>
       				<td><div class="SubTitleHead">Aut. Direcci&oacute;n:</div></td>
       				<td>
       					<input type="checkbox" field="ind_aut_direccion" controller="check" class="divPGForm"></input>
       				</td>
       			</tr>
       			<tr>
       				<td>Responsable:</td>
       				<td><select field="num_resp_aut" controller="cmb" class="divPGForm">
       					<option value="0">(Seleccione)</option>
       					<option>Blanca Nora Garc&iacute;a Leal</option>
						<option>Carlos Alberto Ballesteros Adalid</option>
						<option>Carlos Enrique Chavez Paredes</option>
						<option>Carlos Ernesto Orci Berea</option>
       				</select></td>
       			</tr>
       			<tr>
       				<td>Cumplimiento:</td>
       				<td><input type="text" field="fec_cumplimiento_aut" controller="calendar" class="divPGForm"></input></td>
       			</tr>
       			 
       			<tr>
       				<td><div class="SubTitleHead">En Firmas:</div></td>
       				<td>
       					<input type="checkbox" field="ind_firmas" controller="check" class="divPGForm"></input>
       				</td>
       			</tr>
       			<tr>
       				<td>Responsable:</td>
       				<td><select field="num_resp_firmas" controller="cmb" class="divPGForm">
       					<option value="0">(Seleccione)</option>
       					<option>Blanca Nora Garc&iacute;a Leal</option>
						<option>Carlos Alberto Ballesteros Adalid</option>
						<option>Carlos Enrique Chavez Paredes</option>
						<option>Carlos Ernesto Orci Berea</option>
       				</select></td>
       			</tr>
       			<tr>
       				<td>Cumplimiento:</td>
       				<td><input type="text" field="fec_cumplimiento_firmas" controller="calendar" class="divPGForm"></input></td>
       			</tr>       			 
       			<tr>
       				<td><div class="SubTitleHead">Entregada:</div></td>
       				<td>
       					<input type="checkbox" field="ind_entregada" controller="check" class="divPGForm"></input>
       				</td>
       			</tr>
       			<tr>
       				<td>Responsable:</td>
       				<td><select field="num_resp_entregada" controller="cmb" class="divPGForm">
       					<option value="0">(Seleccione)</option>
       					<option>Blanca Nora Garc&iacute;a Leal</option>
						<option>Carlos Alberto Ballesteros Adalid</option>
						<option>Carlos Enrique Chavez Paredes</option>
						<option>Carlos Ernesto Orci Berea</option>
       				</select></td>
       			</tr>
       			<tr>
       				<td>Cumplimiento:</td>
       				<td><input type="text" field="fec_cumplimiento_entregada" controller="calendar" class="divPGForm"></input></td>
       			</tr>
       			
       			<tr>
       				<td>Enviada a Notaria N°:</td>
       				<td><input type="text" field="num_enviada_notaria" controller="text" class="divPGForm"></input></td>
       			</tr>
       			<tr>
       				<td>Fecha de envi&oacute; a NP:</td>
       				<td><input type="text" field="fec_envio_notaria" controller="calendar" class="divPGForm"></input></td>
       			</tr>
       		</table>
       	</div>
       	<div id="divSeccD" class="divSeccHead" toggleContent="true">
       		Otorgar Poderes
       	</div>
       	<div id="divSeccDContent" class="divSeccContent">
       		<table width="100%">
       			<tr>
       				<td style="width:10%">Poder:</td>
       				<td style="width:80%"><div id="PGForm_Grid_poderSelecc"></div></td>
       				<td style="width:10%"><button type="button" id="PGForm_Btn_poderSelecc" style="float:right">Seleccionar</button></td>       				
       			</tr>
       			<tr>
       				<td><button type="button" id="PGForm_Btn_poderUpdate">Actualizar</button></td>
       				<td colspan="2"><button type="button" id="PGForm_Btn_poderOrder" style="float:left">Ordenar</button></td>       				
       			</tr>
       			<tr>
       				<td colspan="2">
       					<div id="PGForm_Btn_poderAddCarta" class="Img_Icon_new" style="display:none"></div>
       				</td>
       				<td></td>       				
       			</tr>
       			<tr>
       				<td colspan="3">
       					<div id="PGForm_Grid_apoderados"></div>
       				</td>
       			</tr>
       			<tr>
       				<td colspan="2">
       					<div id="PGForm_lbl_apoderados_revocados"></div>
       				</td>
       				<td><button type="button" id="PGForm_Btn_poderSave" style="float:left">Guardar</button></td>       				
       			</tr>
       		</table>
       	</div>
       	</form>
       </div>   
    </div>
	<div id="tabsPoderes-2">
        
    </div>
	<div id="tabsPoderes-3"></div>
</div>

<div id="PGForm_DlgPoderes" title="Seleccionar Poderes">
	<table width="100%">		
		<tr>			
			<td><label for="PGForm_DlgPoderes_Txt_poder">Poder:</label>  <input type="text" id="PGForm_DlgPoderes_Txt_poder" size="10"></input><button type="button" id="PGForm_DlgPoderes_Btn_buscarPoder">Buscar</button></td>
		</tr>
		<tr>
			<td><input type="checkbox" id="PGForm_DlgPoderes_Chk_selecTodos" name="PGForm_DlgPoderes_Chk_selecTodos" /><label for="PGForm_DlgPoderes_Chk_selecTodos">Seleccionar todos</label><br /></td>
		</tr>
		<tr>
			<td>
				<div id="PGForm_DlgPoderes_Grid_poderes"></div>
			</td>
		</tr>
	</table>
</div>

<div id="PGForm_DlgApoderados" title="Captura de poderes">
	<div><label for="PGForm_DlgApoderados_gapoder">Grupos de apoderados</label><select id="PGForm_DlgApoderados_gapoder">
		<option value="abogados" selected="selected">abogados</option>		
	</select></div>
	<fieldset>
    <legend style="font-weight:bold">Apoderados</legend>
    <div id="PGForm_DlgApoderados_Grid_gapoder"></div>
	<div>
	
	<button id="PGForm_DlgApoderados_btn_addAllapoder" type="button">Agregar Todos</button>
	
	<button id="PGForm_DlgApoderados_btn_delAllapoder" type="button">Quitar Todos</button>
	</div>
	<div style="font-weight:bold">Seleccionados:</div>
	<div id="PGForm_DlgApoderados_Grid_gapoderSelecc"></div>
    </fieldset>
	
	<fieldset>
    <legend style="font-weight:bold">Vigencia</legend>
	<table width="100%">
		<tr>
			<td style="width:20%">Vigencia</td>
			<td style="width:30%">
			<select field="PGForm_DlgApoderados_cmb_vigencia" controller="cmb" class="PGForm_DlgApoderados">
				<option value="1">A&ntilde;os</option>
				<option value="2">Meses</option>
				<option value="Rango">Rango</option>
			</select></td>
			<td style="width:20%">Caracteristicas</td>
			<td rowspan="4" style="width:30%"><textarea rows="5" cols="20" id="PGForm_DlgApoderados_txt_caract" class="PGForm_DlgApoderados"></textarea></td>			
		</tr>
		<tr>
			<td>Tiempo</td>
			<td>
			<input field="PGForm_DlgApoderados_spi_vigencia" controller="spinner" class="PGForm_DlgApoderados"/>
			</td>
			<td></td>
		</tr>
		<tr>
			<td>Fecha Inicio</td>
			<td>
			<input type="text" field="PGForm_DlgApoderados_calendar_vigenciaInicio" toField="PGForm_DlgApoderados_calendar_vigenciaFin" controller="calendarRange" class="PGForm_DlgApoderados"/></td>
			<td></td>
		</tr>
		<tr>
			<td>Vencimiento</td>
			<td>
			
			<input type="text" field="PGForm_DlgApoderados_calendar_vigenciaFin" fromField="PGForm_DlgApoderados_calendar_vigenciaInicio" controller="calendarRange" class="PGForm_DlgApoderados"/></td>
			<td></td>
		</tr>
	</table>
	</fieldset>
</div>

<div id="PGForm_DlgApoderadosOtros" title="Captura de poderes">
	<div id="PGForm_DlgApoderadosOtros_tabs">
  <ul>
    <li><a href="#PGForm_DlgApoderadosOtros_1">Actos de Administraci&oacute;n</a></li>
    <li><a href="#PGForm_DlgApoderadosOtros_2">Actos de Dominio</a></li>
    <li><a href="#PGForm_DlgApoderadosOtros_3">T&iacute;tulos de Cr&eacute;dito</a></li>
    <li><a href="#PGForm_DlgApoderadosOtros_4">Pleitos y Cobranzas</a></li>    
    
  </ul>
  <div id="PGForm_DlgApoderadosOtros_1">
  	<input type="checkbox" id="PGForm_DlgApoderadosOtros_chk_aA" class="PGForm_DlgApoderadosOtros_chk"/><label for="PGForm_DlgApoderadosOtros_chk_aA">Actos de Administraci&oacute;n</label>
    <table width="100%" id="PGForm_DlgApoderadosOtros_chk_aA_Table" class="PGForm_DlgApoderadosOtros_chk_Table">
    	<tr>
    		<td style="33%" valign="top">
    			<div>
    				<input type="radio" name="PGForm_DlgApoderadosOtros_Deleg1" id="PGForm_DlgApoderadosOtros_DelegA1" value="Delegable" checked="checked" />
    				<label for="PGForm_DlgApoderadosOtros_Deleg1">Delegable</label>
    			</div>
    			<div>
    				<input type="radio" name="PGForm_DlgApoderadosOtros_Deleg1" id="PGForm_DlgApoderadosOtros_DelegB1" value="No Delegable" />
    				<label for="PGForm_DlgApoderadosOtros_Deleg1">No Delegable</label>
    			</div>
    		</td>
    		<td style="33%" valign="top">
    			<div>
    				<input type="radio" name="PGForm_DlgApoderadosOtros_Deleg2" id="PGForm_DlgApoderadosOtros_DelegA2" value="Individual" checked="checked"/>
    				<label for="PGForm_DlgApoderadosOtros_DelegA2">Individual</label>
    			</div>
    			<div>
    				<input type="radio" name="PGForm_DlgApoderadosOtros_Deleg2" id="PGForm_DlgApoderadosOtros_DelegB2" value="Conjuntamente" />
    				<label for="PGForm_DlgApoderadosOtros_DelegB2">Conjuntamente</label>
    			</div>
    			<select field="PGForm_DlgApoderadosOtros_cmb_conj" controller="cmb" class="PGForm_DlgApoderadosOtros">
					<option value="" selected="selected">Seleccione</option>
					<option value="Adolfo Carlos Castell">Adolfo Carlos Castell</option>
					<option value="Alba Cecilia Ortega">Alba Cecilia Ortega</option>
				</select>
    			<div>
    				<input type="radio" name="PGForm_DlgApoderadosOtros_Deleg2" id="PGForm_DlgApoderadosOtros_DelegC2" value="Mancomunado" />
    				<label for="PGForm_DlgApoderadosOtros_DelegC2">Mancomunado</label>
    			</div>
    			<div>
    				<input type="radio" name="PGForm_DlgApoderadosOtros_Deleg2" id="PGForm_DlgApoderadosOtros_DelegD2" value="Otro" />
    				<label for="PGForm_DlgApoderadosOtros_DelegD2">Otro</label>
    			</div>
    			<textarea rows="5" cols="15" id="PGForm_DlgApoderadosOtros_txt_otro" class="PGForm_DlgApoderadosOtros"></textarea>
    		</td>
    		<td style="33%" valign="top">
    			<div><label for="PGForm_DlgApoderadosOtros_txt_caract">Caracter&iacute;sticas</label></div>
    			<textarea rows="5" cols="15" id="PGForm_DlgApoderadosOtros_txt_caract" class="PGForm_DlgApoderadosOtros"></textarea>
    		</td>
    	</tr>
    </table>
  </div>  
  
  <div id="PGForm_DlgApoderadosOtros_2">
  <input type="checkbox" id="PGForm_DlgApoderadosOtros_chk_aD" class="PGForm_DlgApoderadosOtros_chk"/><label for="PGForm_DlgApoderadosOtros_chk_aD">Actos de Dominio</label>
    <table width="100%" id="PGForm_DlgApoderadosOtros_chk_aD_Table" class="PGForm_DlgApoderadosOtros_chk_Table">
    	<tr>
    		<td style="33%" valign="top">
    			<div>
    				<input type="radio" name="PGForm_DlgApoderadosOtros_Deleg1B" id="PGForm_DlgApoderadosOtros_DelegA2" value="Delegable" checked="checked" />
    				<label for="PGForm_DlgApoderadosOtros_Deleg1">Delegable</label>
    			</div>
    			<div>
    				<input type="radio" name="PGForm_DlgApoderadosOtros_Deleg1B" id="PGForm_DlgApoderadosOtros_DelegB2" value="No Delegable" />
    				<label for="PGForm_DlgApoderadosOtros_Deleg1">No Delegable</label>
    			</div>
    		</td>
    		<td style="33%" valign="top">
    			<div>
    				<input type="radio" name="PGForm_DlgApoderadosOtros_Deleg2B" id="PGForm_DlgApoderadosOtros_DelegA2B" value="Individual" checked="checked"/>
    				<label for="PGForm_DlgApoderadosOtros_DelegA2">Individual</label>
    			</div>
    			<div>
    				<input type="radio" name="PGForm_DlgApoderadosOtros_Deleg2B" id="PGForm_DlgApoderadosOtros_DelegB2B" value="Conjuntamente" />
    				<label for="PGForm_DlgApoderadosOtros_DelegB2">Conjuntamente</label>
    			</div>
    			<select field="PGForm_DlgApoderadosOtros_cmb_conjB" controller="cmb" class="PGForm_DlgApoderadosOtros">
					<option value="" selected="selected">Seleccione</option>
					<option value="Adolfo Carlos Castell">Adolfo Carlos Castell</option>
					<option value="Alba Cecilia Ortega">Alba Cecilia Ortega</option>
				</select>
    			<div>
    				<input type="radio" name="PGForm_DlgApoderadosOtros_Deleg2B" id="PGForm_DlgApoderadosOtros_DelegC2B" value="Mancomunado" />
    				<label for="PGForm_DlgApoderadosOtros_DelegC2">Mancomunado</label>
    			</div>
    			<div>
    				<input type="radio" name="PGForm_DlgApoderadosOtros_Deleg2B" id="PGForm_DlgApoderadosOtros_DelegD2B" value="Otro" />
    				<label for="PGForm_DlgApoderadosOtros_DelegD2">Otro</label>
    			</div>
    			<textarea rows="5" cols="15" id="PGForm_DlgApoderadosOtros_txt_otroB" class="PGForm_DlgApoderadosOtros"></textarea>
    		</td>
    		<td style="33%" valign="top">
    			<div><label for="PGForm_DlgApoderadosOtros_txt_caract">Caracter&iacute;sticas</label></div>
    			<textarea rows="5" cols="15" id="PGForm_DlgApoderadosOtros_txt_caractB" class="PGForm_DlgApoderadosOtros"></textarea>
    		</td>
    	</tr>
    </table>
  </div>  
  <div id="PGForm_DlgApoderadosOtros_3">
  <input type="checkbox" id="PGForm_DlgApoderadosOtros_chk_tC" class="PGForm_DlgApoderadosOtros_chk"/><label for="PGForm_DlgApoderadosOtros_chk_tC">T&iacute;tulos de Cr&eacute;dito</label>
    <table width="100%" id="PGForm_DlgApoderadosOtros_chk_tC_Table" class="PGForm_DlgApoderadosOtros_chk_Table">
    	<tr>
    		<td style="33%" valign="top">
    			<div>
    				<input type="radio" name="PGForm_DlgApoderadosOtros_Deleg1C" id="PGForm_DlgApoderadosOtros_DelegA3" value="Delegable" checked="checked" />
    				<label for="PGForm_DlgApoderadosOtros_Deleg1">Delegable</label>
    			</div>
    			<div>
    				<input type="radio" name="PGForm_DlgApoderadosOtros_Deleg1C" id="PGForm_DlgApoderadosOtros_DelegB3" value="No Delegable" />
    				<label for="PGForm_DlgApoderadosOtros_Deleg1">No Delegable</label>
    			</div>
    		</td>
    		<td style="33%" valign="top">
    			<div>
    				<input type="radio" name="PGForm_DlgApoderadosOtros_Deleg2C" id="PGForm_DlgApoderadosOtros_DelegA2C" value="Individual" checked="checked"/>
    				<label for="PGForm_DlgApoderadosOtros_DelegA2">Individual</label>
    			</div>
    			<div>
    				<input type="radio" name="PGForm_DlgApoderadosOtros_Deleg2C" id="PGForm_DlgApoderadosOtros_DelegB2C" value="Conjuntamente" />
    				<label for="PGForm_DlgApoderadosOtros_DelegB2">Conjuntamente</label>
    			</div>
    			<select field="PGForm_DlgApoderadosOtros_cmb_conjC" controller="cmb" class="PGForm_DlgApoderadosOtros">
					<option value="" selected="selected">Seleccione</option>
					<option value="Adolfo Carlos Castell">Adolfo Carlos Castell</option>
					<option value="Alba Cecilia Ortega">Alba Cecilia Ortega</option>
				</select>
    			<div>
    				<input type="radio" name="PGForm_DlgApoderadosOtros_Deleg2C" id="PGForm_DlgApoderadosOtros_DelegC2C" value="Mancomunado" />
    				<label for="PGForm_DlgApoderadosOtros_DelegC2">Mancomunado</label>
    			</div>
    			<div>
    				<input type="radio" name="PGForm_DlgApoderadosOtros_Deleg2C" id="PGForm_DlgApoderadosOtros_DelegD2C" value="Otro" />
    				<label for="PGForm_DlgApoderadosOtros_DelegD2">Otro</label>
    			</div>
    			<textarea rows="5" cols="15" id="PGForm_DlgApoderadosOtros_txt_otroC" class="PGForm_DlgApoderadosOtros"></textarea>
    		</td>
    		<td style="33%" valign="top">
    			<div><label for="PGForm_DlgApoderadosOtros_txt_caract">Caracter&iacute;sticas</label></div>
    			<textarea rows="5" cols="15" id="PGForm_DlgApoderadosOtros_txt_caractC" class="PGForm_DlgApoderadosOtros"></textarea>
    		</td>
    	</tr>
    </table>
  </div>  
  
  <div id="PGForm_DlgApoderadosOtros_4" title="Apoderados a la carta">
  <input type="checkbox" id="PGForm_DlgApoderadosOtros_chk_pC" class="PGForm_DlgApoderadosOtros_chk"/><label for="PGForm_DlgApoderadosOtros_chk_pC">Pleitos y Cobranzas</label>
    <table width="100%" id="PGForm_DlgApoderadosOtros_chk_pC_Table" class="PGForm_DlgApoderadosOtros_chk_Table">
    	<tr>
    		<td style="33%" valign="top">
    			<div>
    				<input type="radio" name="PGForm_DlgApoderadosOtros_Deleg1D" id="PGForm_DlgApoderadosOtros_DelegA4" value="Delegable" checked="checked" />
    				<label for="PGForm_DlgApoderadosOtros_Deleg1">Delegable</label>
    			</div>
    			<div>
    				<input type="radio" name="PGForm_DlgApoderadosOtros_Deleg1D" id="PGForm_DlgApoderadosOtros_DelegB4" value="No Delegable" />
    				<label for="PGForm_DlgApoderadosOtros_Deleg1">No Delegable</label>
    			</div>
    		</td>
    		<td style="33%" valign="top">
    			<div>
    				<input type="radio" name="PGForm_DlgApoderadosOtros_Deleg2D" id="PGForm_DlgApoderadosOtros_DelegA2D" value="Individual" checked="checked"/>
    				<label for="PGForm_DlgApoderadosOtros_DelegA2">Individual</label>
    			</div>
    			<div>
    				<input type="radio" name="PGForm_DlgApoderadosOtros_Deleg2D" id="PGForm_DlgApoderadosOtros_DelegB2D" value="Conjuntamente" />
    				<label for="PGForm_DlgApoderadosOtros_DelegB2">Conjuntamente</label>
    			</div>
    			<select field="PGForm_DlgApoderadosOtros_cmb_conjD" controller="cmb" class="PGForm_DlgApoderadosOtros">
					<option value="" selected="selected">Seleccione</option>
					<option value="Adolfo Carlos Castell">Adolfo Carlos Castell</option>
					<option value="Alba Cecilia Ortega">Alba Cecilia Ortega</option>
				</select>
    			<div>
    				<input type="radio" name="PGForm_DlgApoderadosOtros_Deleg2D" id="PGForm_DlgApoderadosOtros_DelegC2D" value="Mancomunado" />
    				<label for="PGForm_DlgApoderadosOtros_DelegC2">Mancomunado</label>
    			</div>
    			<div>
    				<input type="radio" name="PGForm_DlgApoderadosOtros_Deleg2D" id="PGForm_DlgApoderadosOtros_DelegD2D" value="Otro" />
    				<label for="PGForm_DlgApoderadosOtros_DelegD2">Otro</label>
    			</div>
    			<textarea rows="5" cols="15" id="PGForm_DlgApoderadosOtros_txt_otroC" class="PGForm_DlgApoderadosOtros"></textarea>
    		</td>
    		<td style="33%" valign="top">
    			<div><label for="PGForm_DlgApoderadosOtros_txt_caract">Caracter&iacute;sticas</label></div>
    			<textarea rows="5" cols="15" id="PGForm_DlgApoderadosOtros_txt_caractC" class="PGForm_DlgApoderadosOtros"></textarea>
    		</td>
    	</tr>
    </table>
  </div> 
</div>
</div>

<div id="PGForm_DlgRevocacion" title="Revocaci&oacute;n">
	
	<div id="PGForm_DlgRevocacion_divGrid">
	<fieldset>
    <legend style="font-weight:bold">Revocar</legend>    
    <div id="PGForm_DlgRevocacion_grid_Revocados"></div>
    </fieldset>
    </div>
    <div  id="PGForm_DlgRevocacion_divRevocar" style="display:none">     
    
    <label>Revocar a:</label><label id="GForm_DlgRevocacion_LblName" style="font-weight:bold"></label>
    <br />
    <fieldset>
    	<legend style="font-weight:bold">Campos para Revocar</legend>
    	<table width="100%">
    		<tr>
    			<td width="15%">Escritura</td>
    			<td width="35%">
    				<select field="PGForm_DlgRevocacion_cmb_Escritura" controller="cmb" class="PGForm_DlgRevocacion">
						<option value="" selected="selected">---Seleccione---</option>
						<option value="Esc. No 55,555 del 27-07-2016, NP 45 D.F.">Esc. No 55,555 del 27-07-2016, NP 45 D.F.</option>						
					</select></td>
				<td width="15%">
				
				</td>
				<td width="35%"></td>
    		</tr>
    		<tr>
    			<td>El poder termino por:</td>
    			<td><select field="PGForm_DlgRevocacion_cmb_Escritura_cmb_razon" controller="cmb" class="PGForm_DlgRevocacion">
						<option value="" selected="selected">---Seleccione---</option>
						<option value="1">Revocaci&oacute;n</option>
						<option value="2">Muerte</option>
						<option value="3">Vigencia</option>
						<option value="4">Se agot&oacute; el objeto</option>
					</select></td>
    			<td>De Fecha:</td>
    			<td><input type="text" field="PGForm_DlgRevocacion_cmb_Escritura_date_fecha" controller="calendar" class="PGForm_DlgRevocacion"></input></td>
    		</tr>
    		<tr>
    			<td>No. de Docuento en Documentum: </td>
    			<td><input type="text" field="PGForm_DlgRevocacion_cmb_Escritura_txt_doc" controller="text" class="PGForm_DlgRevocacion"></input></td>
    			<td></td>
    			<td></td>
    		</tr>
    	</table>
    </fieldset>
    </div>
    
</div>

</body>
</html>
