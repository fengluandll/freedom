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
	
	//******** Carga del catálogo "Poderes Especiales" index:0
	initArgs.add(MngDataPoderes.queryCatalogosDePoderes("PE"));	
	//******** Carga registros de Escritura Poder Tipo Poder Especial "PE" :1
	initArgs.add( MngDataPoderes.queryESCRITURA_PODER(Integer.parseInt(sessionBean.getIdCurrentEmpresa()), "PE"));
	//******** Carga del catálogo "Grupo de Apoderados" con id:45 :2
	initArgs.add( MngDataPoderes.queryCATALOGOS(45));	
	//****** Carga registros de Revocaciones "ER" :3
	GenericDataBean listER = MngDataPoderes.queryESCRITURA_PODER(Integer.parseInt(sessionBean.getIdCurrentEmpresa()), "ER" );
	GenericDataBean listEP = MngDataPoderes.queryESCRITURA_PODER(Integer.parseInt(sessionBean.getIdCurrentEmpresa()), "PE");
		listEP.beans.addAll(listER.beans);
	
%>

<div id="divPEMain" style="display:none">
	<table width="100%">
		<tr>
			<td><input type="text" id="txtPEQuery" field="txtPEQuery" controller="text"/>
				<button openFormType="PE" id="btnPEQuery" class="ui-button ui-corner-all ui-widget">Buscar</button>
			</td>
			<td>
				<div openFormMode="new" openFormType="PE" divHide="#divPEMain"
					id="btnPEAdd" title="Crear Poder Especial" class="Img_Icon_new"></div>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<div id="gridPEmain">
					<%=gson.toJson(initArgs.get(1)) %>
				</div>
			</td>
		</tr>
	</table>
</div>


<div id="divPEOtorgarPoderes" class="divSeccContent" style="display:none">
       		<table width="100%">
       			<tr>
       				<td style="width:10%">Poder:</td>
       				<td style="width:80%"><div id="PEForm_Grid_poderSelecc"></div></td>
       				<td style="width:10%"><div id="PEForm_Btn_poderSelecc" class="Img_Icon_new" style="float:right"></div></td>       				
       			</tr>   
       			<tr>
       				<td></td>
       				<td colspan="2"><button type="button" id="PEForm_Btn_poderOrder" style="float:left;">Ordenar</button></td>       				
       			</tr>    		
       			<tr>
       				<td colspan="2">
       					<div id="PEForm_Btn_poderAddCarta" class="Img_Icon_new"  style="display:none"></div>
       				</td>
       				<td></td>       				
       			</tr>
       			<tr>
       				<td colspan="3">
       					<div id="PEForm_Grid_apoderados" field="PEForm_Grid_apoderados" dataField="Apoderados" controller="grid" class="divPEForm"></div>
       				</td>
       			</tr>
       			<tr>
       				<td colspan="2">
       					<div id="PEForm_lbl_apoderados_revocados" class="ApendiceRevocados"></div>
       				</td>
       				<td><button type="button" id="PEForm_Btn_poderSave" style="float:left">Guardar</button></td>       				
       			</tr>
       		</table>
       	</div>
       	
<div id="PEForm_DlgApoderadosOtros" title="Captura de poderes" style="display:none">
	<div id="PEForm_DlgPoderes" title="Seleccionar Poderes">
	<table width="100%">		
		<tr>			
			<td><label for="PEForm_DlgPoderes_Txt_poder">Poder:</label>  <input type="text" id="PEForm_DlgPoderes_Txt_poder" size="10"></input><button type="button" id="PEForm_DlgPoderes_Btn_buscarPoder">Buscar</button></td>
		</tr>
		<tr>
			<td></td>
		</tr>
		<tr>
			<td>
				<div id="PEForm_DlgPoderes_Grid_poderes" radioName="PEForm_Grid_apoderados_radio" field="PEForm_DlgPoderes_Grid_poderes" dataField="num_podertipo" dataFieldDesc="des_podertipo" keyValue="id_poder_pk" viewValue="des_podertipo" embeddedData="true" controller="radioGrid" class="PEForm_DlgApoderados" defaultValue="-1">
				<%=gson.toJson(initArgs.get(0)) %>
				</div>
			</td>
		</tr>
	</table>
</div>
<div id="PEForm_DlgApoderados" title="Captura de poderes">
	
	<fieldset>
    <legend style="font-weight:bold">Apoderados</legend>
    
	<div>
	<input type="checkbox" field="PEForm_DlgApoderados_chk_GroupPoderes" toggleDivXOr=".PEForm_DlgApoderados_div_NotGroupPoderes" toggleDiv=".PEForm_DlgApoderados_div_GroupPoderes" defaultShow="false" controller="check" class="PEForm_DlgApoderados"  eChange="eChange_chk_GroupPoderes"/>
	  <label for="PEForm_DlgApoderados_chk_GroupPoderes">Agrupar Apoderados</label>
	  <button id="PEForm_DlgApoderados_btn_orderAllapoder" type="button">Ordenar</button>
	  <div class="PEForm_DlgApoderados_div_GroupPoderes">
		<label for="PEForm_DlgApoderados_gapoder">Grupos de apoderados</label>
		<select field="PEForm_DlgApoderados_gapoder" keyValue="ID_CATALOGO_VALOR" viewValue="NOM_CAT_VAL" dataField="id_grupo_fk" controller="cmb" embeddedData="true" defaultValue="0" defaultText="" class="PEForm_DlgApoderados">
			<%=gson.toJson(initArgs.get(2)) %>
		</select>
	</div>
	<button id="PEForm_DlgApoderados_btn_addAllapoder" type="button">Seleccionar...</button>
	</div>
	
	<div style="font-weight:bold">Seleccionados:</div>
	<div id="PEForm_DlgApoderados_Div_gapoderSelecc" class="PEForm_DlgApoderados_div_GroupPoderes"></div>
	<div id="PEForm_DlgApoderados_Grid_gapoderSelecc" dataField="Apoderados" controller="grid" class="PEForm_DlgApoderados PEForm_DlgApoderados_div_NotGroupPoderes"></div>
    </fieldset>
	
	<fieldset>
    <legend style="font-weight:bold">Vigencia</legend>
	<table width="100%">
		<tr>
			<td style="width:20%">Vigencia</td>
			<td style="width:30%">
				<select field="PEForm_DlgApoderados_cmb_vigencia" dataField="num_vigenciatipo" controller="cmb" class="PEForm_DlgApoderados" eChange="eChange_cmb_vigencia">
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
			<input maxlength="3" field="PEForm_DlgApoderados_spi_vigencia" dataField="num_vigenciatiempo" controller="spinner" class="PEForm_DlgApoderados" eChange="eChange_spn_vigenciatiempo"/>
			</td>
			<td></td>
		</tr>
		<tr>
			<td>Fecha Inicio</td>
			<td>
				<input type="text" field="PEForm_DlgApoderados_calendar_vigenciaInicio" dataField="fec_vigenciainicio" toField="PEForm_DlgApoderados_calendar_vigenciaFin" controller="calendarRange" class="PEForm_DlgApoderados" style="z-index:99"/>
			</td>
			<td></td>
		</tr>
		<tr>
			<td>Vencimiento</td>
			<td>			
				<input type="text" field="PEForm_DlgApoderados_calendar_vigenciaFin" dataField="fec_vigenciafin" fromField="PEForm_DlgApoderados_calendar_vigenciaInicio" controller="calendarRange" class="PEForm_DlgApoderados"/>
			</td>
			<td></td>
		</tr>
	</table>
	</fieldset>
</div>

<div id="PEForm_DlgApoderadosOtros_FormaDeEjercerPoder" style="display:none">

	<fieldset>
	    <legend style="font-weight:bold">Apoderados</legend>
		<div id="PEForm_DlgApoderadosOtros_Grid_ApSelecc"></div>
	</fieldset>
		
  <div id="PEForm_DlgApoderadosOtros_tabs">
  	<ul>
		<li><a href="#PEForm_DlgApoderadosOtros_FormaDeEjercerPoderTab-1">Poder y descripcion</a></li>
		<li><a href="#PEForm_DlgApoderadosOtros_FormaDeEjercerPoderTab-2">Formas de Ejercer el Poder</a></li>
	</ul>
  <div id="PEForm_DlgApoderadosOtros_FormaDeEjercerPoderTab-1">
  	<table style="margin:5px;width:100%;">
		<tr>
			<td><label>Poder:</label></td>
			<td><input type="text" size="44" field="PEForm_DlgApoderados_txt_poder" dataField="des_podertipo" controller="text" class="PEForm_DlgApoderados"></input></td>
		</tr>
		<tr>
			<td><label>Descripci&oacute;n:</label></td>
			<td><textarea rows="5" cols="42" id="PEForm_DlgApoderados_txt_descripcion" field="PEForm_DlgApoderados_txt_descripcion" dataField="desc_descripcion" controller="texteditor" class="PEForm_DlgApoderados"></textarea></td>
		</tr>
		<tr>
			<td style="width:20%">Caracter&iacute;sticas/<br>Limitaciones:</td>
			<td rowspan="4"><textarea rows="4" cols="42" id="PEForm_DlgApoderados_txt_caract" field="PEForm_DlgApoderados_txt_caract" dataField="desc_caracteristicas" controller="texteditor" class="PEForm_DlgApoderados"></textarea></td>
		</tr>
	</table>
  </div>
  
  <div id="PEForm_DlgApoderadosOtros_FormaDeEjercerPoderTab-2">
  	<input type="checkbox" field="PEForm_DlgApoderadosOtros_chk_aA"  defaultShow="true" controller="check" class="PEForm_DlgApoderados" style="display:none"/>
  	<label for="PEForm_DlgApoderadosOtros_chk_aA"></label>
    <table width="100%" id="PEForm_DlgApoderadosOtros_chk_aA_Table">
    	<tr>
    		<td style="width:20%" valign="top">
    			<div>
    				<input type="radio" name="PEForm_DlgApoderadosOtros_Deleg1" id="PEForm_DlgApoderadosOtros_Delegable" value="Delegable" checked="checked" />
    				<label for="PEForm_DlgApoderadosOtros_Delegable">Delegable</label>
    			</div>
    			<div>
    				<input type="radio" name="PEForm_DlgApoderadosOtros_Deleg1" id="PEForm_DlgApoderadosOtros_NoDelegable" value="No Delegable" />
    				<label for="PEForm_DlgApoderadosOtros_NoDelegable">No Delegable</label>
    			</div>
    			<div>
    				<input type="radio" name="PEForm_DlgApoderadosOtros_Deleg1" id="PEForm_DlgApoderadosOtros_Ninguno" value="Ninguno" />
    				<label for="PEForm_DlgApoderadosOtros_Ninguno">Ninguno</label>
    			</div>
    		</td>
    		<td style="width:30%" valign="top">
    			<div>
    				<input type="radio" name="PEForm_DlgApoderadosOtros_Deleg2" id="PEForm_DlgApoderadosOtros_Individual" value="Individual" checked="checked"/>
    				<label for="PEForm_DlgApoderadosOtros_Individual">Individual</label>
    			</div>
    			<div>
    				<input type="radio" name="PEForm_DlgApoderadosOtros_Deleg2" id="PEForm_DlgApoderadosOtros_Conjuntamente" value="Conjuntamente" />
    				<label for="PEForm_DlgApoderadosOtros_Conjuntamente">Conjuntamente</label>
    			</div>
    			<div>
    				<input type="radio" name="PEForm_DlgApoderadosOtros_Deleg2" id="PEForm_DlgApoderadosOtros_Conjunta_Separadamente" value="Conjunta o separadamente" />
    				<label for="PEForm_DlgApoderadosOtros_Conjunta_Separadamente">Conjunta o separadamente</label>
    			</div>
    			<div>
    				<input type="radio" name="PEForm_DlgApoderadosOtros_Deleg2" id="PEForm_DlgApoderadosOtros_Ninguno" value="Ninguno" />
    				<label for="PEForm_DlgApoderadosOtros_Ninguno">Ninguno</label>
    			</div>
    		</td>
    	</tr>
    	<tr>
    		<td style="width:25%" valign="top">
    			<div><label for="PEForm_DlgApoderadosOtros_txt_formas_ejercerlo">Formas de Ejercerlo:</label></div>
    			<textarea rows="5" cols="40" id="PEForm_DlgApoderadosOtros_txt_formas_ejercerlo" controller="texteditor" class="PEForm_DlgApoderadosOtros"></textarea>
    			     			
    		</td>
    		<td style="width:25%" valign="top">
    				<input type="checkbox" id="PEForm_DlgApoderadosOtros_ApodA" field="PEForm_DlgApoderadosOtros_ApodA" toggleDiv="#PEForm_DlgApoderadosOtros_Apod_DivA" defaultShow="false" controller="check" class="PEForm_DlgApoderados"></input>
    				
    				<label for="PEForm_DlgApoderadosOtros_ApodA">Mancomunado</label>
    		
    			<div id="PEForm_DlgApoderadosOtros_Apod_DivA">
    			<button type="button" metadata="A" class="PEForm_DlgApoderadosOtros_BtnCon">Seleccione...</button>
    			<div id="PEForm_DlgApoderadosOtros_grid_A"></div>
    			</div>
    		</td>
    	</tr>
    </table>
  </div>  
  </div>
</div>
	 
</div>  

<div id="PEForm_DlgRevocacion" title="Revocaci&oacute;n" style="display:none">
	
	<div id="PEForm_DlgRevocacion_divGrid">
	<fieldset>
    <legend style="font-weight:bold">Revocar</legend>    
    <div id="PEForm_DlgRevocacion_grid_Revocados" class="DlgRevocacion_grid_Revocados"></div>
    </fieldset>
    </div>
    
    <div  id="PEForm_DlgRevocacion_divRevocar" style="display:none">     
    <label>Revocar a:</label><label id="PEForm_DlgRevocacion_LblName" style="font-weight:bold"></label>
    <br />
    <fieldset>
    	<legend style="font-weight:bold">Campos para Revocar</legend>
    	<table width="100%">
    		<tr>
    			<td>El poder termin&oacute; por:</td>
    			<td><select field="PEForm_DlgRevocacion_cmb_Escritura_cmb_razon" controller="cmb" class="PEForm_DlgRevocacion" eChange="eChange_cmb_razonRevocacion">
						<option value="" selected="selected">---Seleccione---</option>
						<option value="1">Revocaci&oacute;n</option>
						<option value="2">Muerte</option>
						<option value="4">Se agot&oacute; el objeto</option>
						<option value="5">Renuncia</option>
					</select></td>
    			<td>De Fecha:</td>
    			<td><input type="text" field="PEForm_DlgRevocacion_cmb_Escritura_date_fecha" controller="calendar" class="PEForm_DlgRevocacion"></input></td>
    		</tr>
    		<tr>
    			<td width="15%">Escritura</td>
    			<td width="35%">
    				<select field="PEForm_DlgRevocacion_cmb_Escritura"  embeddedData="true"  keyValue="des_escritura" viewValue="des_escritura" defaultValue="N/D" defaultText="---Seleccione---" controller="cmb" class="PEForm_DlgRevocacion"  eChange="eChange_cmb_Escritura">						
						<%=gson.toJson(listEP) %>
					</select>	
				</td>
				<td width="15%"></td>
				<td width="35%"></td>
    		</tr>
    		<tr>
    			<td>Documento digitalizado: </td>
    			<td><div field="PEForm_DlgRevocacion_doc_Documento_digital" controller="doc" class="PEForm_DlgRevocacion"></div></td>
    		</tr>
    	</table>
    </fieldset>
    </div>
    
<div id="PEForm_DlgCopy" title="COPIAR ESCRITURA">
	<fieldset>
    	<legend style="font-weight:bold"></legend>
    	<table width="100%">
    		<tr>
    			<td><input id="PEForm_DlgCopy_CopyType" type="checkbox" />Integrar Escritura Poder Especial a General</td>
    		</tr>
    		<tr class="divCopyType">
    			<td width="25%"><input name="PEForm_DlgCopy_modeType" id="PEForm_DlgCopy_modeType" value="PG" type="radio" />General</td>
    			<td width="25%"><input name="PEForm_DlgCopy_modeType" id="PEForm_DlgCopy_modeType" value="PE" type="radio" />Especial</td>
    			<td width="25%"><input name="PEForm_DlgCopy_modeType" id="PEForm_DlgCopy_modeType" value="CP" type="radio" />Carta Poder</td>
    			<td width="25%"><input name="PEForm_DlgCopy_modeType" id="PEForm_DlgCopy_modeType" value="ER" type="radio" />Revocaci&oacute;n</td>
    		</tr>
    		<tr>
    			<td class="divCopyType" colspan="3">
    				<button type="button" id="PEForm_DlgCopy_btnSel">Seleccione la empresa o empresas a copiar</button>
    			</td>    			
    		</tr>
    		<tr>
    			<td class="divCopyType" colspan="3">
    				<div id="PEForm_DlgCopy_gridSel"></div>
    			</td>
    		</tr>
    	</table>
    </fieldset>
    
</div>	
    
</div>

<div id="MetaSession" style="display:none">	
	<%=jsonMS %>
</div>
<input type="hidden" id="MetaSessionHdn" style="display:none" value="<%=jsonMS %>"/>