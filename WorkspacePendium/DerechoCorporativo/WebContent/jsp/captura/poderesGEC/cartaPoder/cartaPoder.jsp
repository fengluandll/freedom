<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@page import="mx.com.televisa.derechocorporativo.daos.MngDataPoderes"%>
<%@page import="mx.com.televisa.derechocorporativo.bean.GenericDataBean"%>
<%@page import="mx.com.televisa.derechocorporativo.bean.SessionBean"%>

<%@page import="com.google.gson.Gson"%>
<%@page import="com.google.gson.GsonBuilder"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%
String nonCache = false ? "" : "?lastversion=" + new java.util.Random().nextInt(); 
List<Object> initArgs = new ArrayList();	
List<Object> metaSession = new ArrayList();
Gson gson = new Gson();

String idSeccion = request.getParameter("idSeccion");	
SessionBean sessionBean = (SessionBean)session.getAttribute("sessionBean");	 

metaSession.add(sessionBean);	
metaSession.add(request.getContextPath());
metaSession.add(session.getAttribute("idEscritura"));
metaSession.add(session.getAttribute("tipoEscritura"));

String jsonMS=gson.toJson(metaSession);
	
//******** Carga del catálogo "Carta Poder" index:0
initArgs.add(MngDataPoderes.queryCatalogosDePoderes("PE"));	
//******** Carga registros de Escritura Poder Tipo Poder Especial "CP" :1
initArgs.add( MngDataPoderes.queryESCRITURA_PODER(Integer.parseInt(sessionBean.getIdCurrentEmpresa()), "CP"));
//******** Carga del catálogo "Grupo de Apoderados" con id:45 :2
initArgs.add( MngDataPoderes.queryCATALOGOS(45));
//****** Carga registros de Revocaciones "ER" :3
	GenericDataBean listER = MngDataPoderes.queryESCRITURA_PODER(Integer.parseInt(sessionBean.getIdCurrentEmpresa()), "ER" );
	GenericDataBean listEP = MngDataPoderes.queryESCRITURA_PODER(Integer.parseInt(sessionBean.getIdCurrentEmpresa()), "CP");
		listEP.beans.addAll(listER.beans);
	
%>

<div id="divCPMain" style="display:none" class="divCPMain">
	<table width="100%">
		<tr>
			<td><input type="text" id="txtCPQuery" />
				<button openFormType="CP" id="btnCPQuery" class="ui-button ui-corner-all ui-widget">Buscar</button>
			</td>
			<td>
				<div openFormMode="new" openFormType="CP" divHide="#divCPMain"
					id="btnCPAdd" title="Crear Carta Poder" class="Img_Icon_new"></div>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<div id="gridCPmain">
				<%=gson.toJson(initArgs.get(1)) %>
				</div>
			</td>
		</tr>
	</table>
</div>


<div id="divCPOtorgarPoderes" class="divSeccContent divCPMain"  style="display:none">
       		<table width="100%">
       			<tr>
       				<td style="width:10%">Poder:</td>
       				<td style="width:80%"><div id="CPForm_Grid_poderSelecc"></div></td>
       				<td style="width:10%"><div id="CPForm_Btn_poderSelecc" class="Img_Icon_new" style="float:right"></div></td>       				
       			</tr>    
       			<tr>
       				<td></td>
       				<td colspan="2"><button type="button" id="CPForm_Btn_poderOrder" style="float:left;">Ordenar</button></td>       				
       			</tr>    			
       			<tr>
       				<td colspan="2">
       					<div id="CPForm_Btn_poderAddCarta" class="Img_Icon_new"  style="display:none"></div>
       				</td>
       				<td></td>       				
       			</tr>
       			<tr>
       				<td colspan="3">
       					<div id="CPForm_Grid_apoderados"></div>
       				</td>
       			</tr>
       			<tr>
       				<td colspan="2">
       					<div id="CPForm_lbl_apoderados_revocados" class="ApendiceRevocados"></div>
       				</td>
       				<td><button type="button" id="CPForm_Btn_poderSave" style="float:left">Guardar</button></td>       				
       			</tr>
       		</table>
       	</div>
       	
<div id="CPForm_DlgApoderadosOtros" title="Captura de poderes" style="display:none" class="divCPMain">
	<div id="CPForm_DlgPoderes" title="Seleccionar Poderes">
	<table width="100%">		
		<tr>			
			<td><label for="CPForm_DlgPoderes_Txt_poder">Poder:</label>  <input type="text" id="CPForm_DlgPoderes_Txt_poder" size="10"></input><button type="button" id="CPForm_DlgPoderes_Btn_buscarPoder">Buscar</button></td>
		</tr>
		<tr>
			<td></td>
		</tr>
		<tr>
			<td>
				<div id="CPForm_DlgPoderes_Grid_poderes" field="CPForm_DlgPoderes_Grid_poderes"  radioName="CPForm_Grid_apoderados_radio" field="CPForm_DlgPoderes_Grid_poderes" dataField="num_podertipo" dataFieldDesc="des_podertipo" keyValue="id_poder_pk" viewValue="des_podertipo" embeddedData="true" controller="radioGrid" class="CPForm_DlgApoderados" defaultValue="-1">
				<%=gson.toJson(initArgs.get(0)) %>
				</div>
			</td>
		</tr>
	</table>
</div>
	<div id="CPForm_DlgApoderados" title="Captura de poderes">
	
	<fieldset>
    <legend style="font-weight:bold">Apoderados</legend>
    
	<div>
	<input type="checkbox" field="CPForm_DlgApoderados_chk_GroupPoderes" toggleDivXOr=".CPForm_DlgApoderados_div_NotGroupPoderes" toggleDiv=".CPForm_DlgApoderados_div_GroupPoderes" defaultShow="false" controller="check" class="CPForm_DlgApoderados" eChange="eChange_chk_GroupPoderes"/>
	  <label for="CPForm_DlgApoderados_chk_GroupPoderes">Agrupar Apoderados</label>
	  <button id="CPForm_DlgApoderados_btn_orderAllapoder" type="button">Ordenar</button>
	  <div class="CPForm_DlgApoderados_div_GroupPoderes">
		<label for="CPForm_DlgApoderados_gapoder">Grupos de apoderados</label>
		<select field="CPForm_DlgApoderados_gapoder" keyValue="ID_CATALOGO_VALOR" viewValue="NOM_CAT_VAL" dataField="id_grupo_fk" controller="cmb" embeddedData="true" defaultValue="0" defaultText="" class="CPForm_DlgApoderados">
			<%=gson.toJson(initArgs.get(2)) %>
		</select>
	</div>
	<button id="CPForm_DlgApoderados_btn_addAllapoder" type="button">Seleccionar...</button>
	</div>
	
	<div style="font-weight:bold">Seleccionados:</div>
	<div id="CPForm_DlgApoderados_Div_gapoderSelecc" class="CPForm_DlgApoderados_div_GroupPoderes"></div>
	<div id="CPForm_DlgApoderados_Grid_gapoderSelecc" dataField="Apoderados" controller="grid" class="CPForm_DlgApoderados CPForm_DlgApoderados_div_NotGroupPoderes"></div>
    </fieldset>
	
	<fieldset>
    <legend style="font-weight:bold">Vigencia</legend>
	<table width="100%">
		<tr>
			<td style="width:20%">Vigencia</td>
			<td style="width:30%">
				<select field="CPForm_DlgApoderados_cmb_vigencia" dataField="num_vigenciatipo" controller="cmb" class="CPForm_DlgApoderados" eChange="eChange_cmb_vigencia">
					<option value="0">Sin Vigencia</option>
					<option value="1">A&ntilde;os</option>
					<option value="2">Meses</option>
					<option value="3">Rango</option>
				</select>				
			</td>
		</tr>
		<tr>
			<td>Tiempo</td>
			<td>
			<input maxlength="3" field="CPForm_DlgApoderados_spi_vigencia" dataField="num_vigenciatiempo" controller="spinner" class="CPForm_DlgApoderados" eChange="eChange_spn_vigenciatiempo"/>
			</td>
			<td></td>
		</tr>
		<tr>
			<td>Fecha Inicio</td>
			<td>
			<input type="text" field="CPForm_DlgApoderados_calendar_vigenciaInicio" dataField="fec_vigenciainicio" toField="CPForm_DlgApoderados_calendar_vigenciaFin" controller="calendarRange" class="CPForm_DlgApoderados" style="z-index:99"/></td>
			<td></td>
		</tr>
		<tr>
			<td>Vencimiento</td>
			<td>
			
			<input type="text" field="CPForm_DlgApoderados_calendar_vigenciaFin" dataField="fec_vigenciafin" fromField="CPForm_DlgApoderados_calendar_vigenciaInicio" controller="calendarRange" class="CPForm_DlgApoderados"/></td>
			<td></td>
		</tr>
	</table>
	</fieldset>
</div>
			
	<!-- <div id="CPForm_DlgApoderadosOtros_Descripcion_Del_Poder" style="display:none">
		<fieldset>
			<legend style="font-weight:bold">Apoderados</legend>
			<div id="CPForm_DlgApoderadosOtros_Grid_ApSelecc"></div>
		</fieldset>
		<fieldset>	
		<legend style="font-weight:bold;">Poder y descripcion</legend>
			<table style="margin:5px;width:100%;">
				<tr>
					<td><label>Poder:</label></td>
					<td><input type="text" size="42" field="CPForm_DlgApoderados_txt_poder" dataField="des_podertipo" controller="text" class="CPForm_DlgApoderados"></input></td>
				</tr>
				<tr>
					<td><label>Descripci&oacute;n:</label></td>
					<td><textarea rows="10" cols="42" id="CPForm_DlgApoderados_txt_descripcion" field="CPForm_DlgApoderados_txt_descripcion" dataField="desc_descripcion" controller="texteditor" class="CPForm_DlgApoderados"></textarea></td>
				</tr>
				<tr>
					<td style="width:20%">Caracter&iacute;sticas/<br>Limitaciones:</td>
					<td rowspan="4"><textarea rows="4" cols="42" id="CPForm_DlgApoderados_txt_caract" field="CPForm_DlgApoderados_txt_caract" dataField="desc_caracteristicas" controller="texteditor" class="CPForm_DlgApoderados"></textarea></td>
				</tr>
			</table>
		</fieldset>
	  </div> -->
  	  
  <div id="CPForm_DlgApoderadosOtros_FormaDeEjercerPoder" style="display:none">

	<fieldset>
	    <legend style="font-weight:bold">Apoderados</legend>
		<div id="CPForm_DlgApoderadosOtros_Grid_ApSelecc"></div>
	</fieldset>
		
  <div id="CPForm_DlgApoderadosOtros_tabs">
  	<ul>
		<li><a href="#CPForm_DlgApoderadosOtros_FormaDeEjercerPoderTab-1">Poder y descripcion</a></li>
		<li><a href="#CPForm_DlgApoderadosOtros_FormaDeEjercerPoderTab-2">Formas de Ejercer el Poder</a></li>
	</ul>
  <div id="CPForm_DlgApoderadosOtros_FormaDeEjercerPoderTab-1">
  	<table style="margin:5px;width:100%;">
		<tr>
			<td><label>Poder:</label></td>
			<td><input type="text" size="44" field="CPForm_DlgApoderados_txt_poder" dataField="des_podertipo" controller="text" class="CPForm_DlgApoderados"></input></td>
		</tr>
		<tr>
			<td><label>Descripci&oacute;n:</label></td>
			<td><textarea rows="5" cols="42" id="CPForm_DlgApoderados_txt_descripcion" field="CPForm_DlgApoderados_txt_descripcion" dataField="desc_descripcion" controller="texteditor" class="CPForm_DlgApoderados"></textarea></td>
		</tr>
		<tr>
			<td style="width:20%">Caracter&iacute;sticas/<br>Limitaciones:</td>
			<td rowspan="4"><textarea rows="4" cols="42" id="CPForm_DlgApoderados_txt_caract" field="CPForm_DlgApoderados_txt_caract" dataField="desc_caracteristicas" controller="texteditor" class="CPForm_DlgApoderados"></textarea></td>
		</tr>
	</table>
  </div>
  
  <div id="CPForm_DlgApoderadosOtros_FormaDeEjercerPoderTab-2">
  	<input type="checkbox" field="CPForm_DlgApoderadosOtros_chk_aA"  defaultShow="true" controller="check" class="CPForm_DlgApoderados" style="display:none"/>
  	<label for="CPForm_DlgApoderadosOtros_chk_aA"></label>
    <table width="100%" id="CPForm_DlgApoderadosOtros_chk_aA_Table">
    	<tr>
    		<td style="width:20%" valign="top">
    			<div>
    				<input type="radio" name="CPForm_DlgApoderadosOtros_Deleg1" id="CPForm_DlgApoderadosOtros_Delegable" value="Delegable" checked="checked" />
    				<label for="CPForm_DlgApoderadosOtros_Delegable">Delegable</label>
    			</div>
    			<div>
    				<input type="radio" name="CPForm_DlgApoderadosOtros_Deleg1" id="CPForm_DlgApoderadosOtros_NoDelegable" value="No Delegable" />
    				<label for="CPForm_DlgApoderadosOtros_NoDelegable">No Delegable</label>
    			</div>
    			<div>
    				<input type="radio" name="CPForm_DlgApoderadosOtros_Deleg1" id="CPForm_DlgApoderadosOtros_Ninguno" value="Ninguno" />
    				<label for="CPForm_DlgApoderadosOtros_Ninguno">Ninguno</label>
    			</div>
    		</td>
    		<td style="width:30%" valign="top">
    			<div>
    				<input type="radio" name="CPForm_DlgApoderadosOtros_Deleg2" id="CPForm_DlgApoderadosOtros_Individual" value="Individual" checked="checked"/>
    				<label for="CPForm_DlgApoderadosOtros_Individual">Individual</label>
    			</div>
    			<div>
    				<input type="radio" name="CPForm_DlgApoderadosOtros_Deleg2" id="CPForm_DlgApoderadosOtros_Conjuntamente" value="Conjuntamente" />
    				<label for="CPForm_DlgApoderadosOtros_Conjuntamente">Conjuntamente</label>
    			</div>
    			<div>
    				<input type="radio" name="CPForm_DlgApoderadosOtros_Deleg2" id="CPForm_DlgApoderadosOtros_Conjunta_Separadamente" value="Conjunta o separadamente" />
    				<label for="CPForm_DlgApoderadosOtros_Conjunta_Separadamente">Conjunta o separadamente</label>
    			</div>
    			<div>
    				<input type="radio" name="CPForm_DlgApoderadosOtros_Deleg2" id="CPForm_DlgApoderadosOtros_Ninguno" value="Ninguno" />
    				<label for="CPForm_DlgApoderadosOtros_Ninguno">Ninguno</label>
    			</div>
    		</td>
    	</tr>
    	<tr>
    		<td style="width:25%" valign="top">
    			<div><label for="CPForm_DlgApoderadosOtros_txt_formas_ejercerlo">Formas de Ejercerlo:</label></div>
    			<textarea rows="5" cols="40" id="CPForm_DlgApoderadosOtros_txt_formas_ejercerlo" controller="texteditor" class="CPForm_DlgApoderadosOtros"></textarea>
    			     			
    		</td>
    		<td style="width:25%" valign="top">
    				<input type="checkbox" id="CPForm_DlgApoderadosOtros_ApodA" field="CPForm_DlgApoderadosOtros_ApodA" toggleDiv="#CPForm_DlgApoderadosOtros_Apod_DivA" defaultShow="false" controller="check" class="CPForm_DlgApoderados"></input>
    				
    				<label for="CPForm_DlgApoderadosOtros_ApodA">Mancomunado</label>
    		
    			<div id="CPForm_DlgApoderadosOtros_Apod_DivA">
    			<button type="button" metadata="A" class="CPForm_DlgApoderadosOtros_BtnCon">Seleccione...</button>
    			<div id="CPForm_DlgApoderadosOtros_grid_A"></div>
    			</div>
    		</td>
    	</tr>
    </table>
  </div>  
  </div>
</div>
  	  
</div>

<div id="CPForm_DlgRevocacion" title="Revocaci&oacute;n" style="display:none" class="divCPMain">
	
	<div id="CPForm_DlgRevocacion_divGrid">
	<fieldset>
    <legend style="font-weight:bold">Revocar</legend>    
    <div id="CPForm_DlgRevocacion_grid_Revocados"  class="DlgRevocacion_grid_Revocados"></div>
    </fieldset>
    </div>
    
    <div  id="CPForm_DlgRevocacion_divRevocar" style="display:none">     
    <label>Revocar a:</label><label id="CPForm_DlgRevocacion_LblName" style="font-weight:bold"></label>
    <br />
    <fieldset>
    	<legend style="font-weight:bold">Campos para Revocar</legend>
    	<table width="100%">
    		<tr>
    			<td>El poder termin&oacute; por:</td>
    			<td><select field="CPForm_DlgRevocacion_cmb_Escritura_cmb_razon" controller="cmb" class="CPForm_DlgRevocacion" eChange="eChange_cmb_razonRevocacion">
						<option value="" selected="selected">---Seleccione---</option>
						<option value="1">Revocaci&oacute;n</option>
						<option value="2">Muerte</option>
						<option value="4">Se agot&oacute; el objeto</option>
						<option value="5">Renuncia</option>
					</select></td>
    			<td>De Fecha:</td>
    			<td><input type="text" field="CPForm_DlgRevocacion_cmb_Escritura_date_fecha" controller="calendar" class="CPForm_DlgRevocacion"></input></td>
    		</tr>
    		<tr>
    			<td width="15%">Escritura</td>
    			<td width="35%">
    				<select field="CPForm_DlgRevocacion_cmb_Escritura"  embeddedData="true"  keyValue="des_escritura" viewValue="des_escritura" defaultValue="N/D" defaultText="---Seleccione---" controller="cmb" class="CPForm_DlgRevocacion"  eChange="eChange_cmb_Escritura">						
						<%=gson.toJson(listEP) %>
					</select>
				</td>
				<td width="15%"></td>
				<td width="35%"></td>
    		</tr>
    		<tr>
    			<td>Documento digitalizado: </td>
    			<td><div field="CPForm_DlgRevocacion_doc_Documento_digital" controller="doc" class="CPForm_DlgRevocacion"></div></td>
    		</tr>
    	</table>
    </fieldset>
    </div>
    
<div id="CPForm_DlgCopy" title="COPIAR ESCRITURA">
	<fieldset>
    	<legend style="font-weight:bold"></legend>
    	<table width="100%">
    		<tr>
    			<!-- <td><input id="CPForm_DlgCopy_CopyType" type="checkbox" />Integrar Escritura</td> -->
    		</tr>
    		<tr>
    			<td width="25%"><input name="CPForm_DlgCopy_modeType" id="CPForm_DlgCopy_modeType" value="PG" type="radio" />General</td>
    			<td width="25%"><input name="CPForm_DlgCopy_modeType" id="CPForm_DlgCopy_modeType" value="PE" type="radio" />Especial</td>
    			<td width="25%"><input name="CPForm_DlgCopy_modeType" id="CPForm_DlgCopy_modeType" value="CP" type="radio" />Carta Poder</td>
    			<td width="25%"><input name="CPForm_DlgCopy_modeType" id="CPForm_DlgCopy_modeType" value="ER" type="radio" />Revocaci&oacute;n</td>
    		</tr>
    		<tr>
    			<td colspan="3">
    				<button type="button" id="CPForm_DlgCopy_btnSel">Seleccione la empresa o empresas a copiar</button>
    			</td>    			
    		</tr>
    		<tr>
    			<td colspan="3">
    				<div id="CPForm_DlgCopy_gridSel"></div>
    			</td>
    		</tr>
    	</table>
    </fieldset>
    
</div>	
    
</div>

<div id="MetaSession" style="display:none">	
	<%=jsonMS %>
</div>
<input type="hidden" id="MetaSessionHdn" value="<%=jsonMS %>"/>
