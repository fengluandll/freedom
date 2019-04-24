package mx.com.televisa.derechocorporativo.modules.reportsFlex;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import mx.com.televisa.derechocorporativo.bean.SessionBean;
import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;
import mx.com.televisa.derechocorporativo.data.packages.DERCORP_REPORTFLEX_PKG;
//import mx.com.televisa.derechocorporativo.modules.captura.Campo;
import mx.com.televisa.derechocorporativo.modules.captura.Catalogo;
import mx.com.televisa.derechocorporativo.modules.captura.CatalogoValor;
import mx.com.televisa.derechocorporativo.modules.captura.Pagina;
import mx.com.televisa.derechocorporativo.modules.captura.Seccion;
import mx.com.televisa.derechocorporativo.modules.captura.SubSeccion;
import mx.com.televisa.derechocorporativo.reflexion.ReflexionUtil;
import mx.com.televisa.derechocorporativo.util.FacesUtils;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

public class Field {

	
	public int idCampo; 
	public int idSeccionRow;
	public int idAddCampo;
	public int idOrder;
	
	public String atributo1;
	
	public String nomCampo;
	public String desTipoCampo;
	public int idCatalogo;
	public int idFlexTbl;
	 
     
	final static Logger log = Logger.getLogger(Field.class);
	

	public Field(ResultSet set, ResultSetMetaData metaData) throws Exception {

		ReflexionUtil.fillObject(set, metaData, this, Field.class);
	}

	
	public static ArrayList<Field> getFields(int idSeccionRow, ConnectionWrapper connect) {

		ArrayList<Field> rows = new ArrayList<Field>();

		
		CallableStatement stmt = null;
		ResultSet set = null;

		try {

			stmt = connect.prepareCall(DERCORP_REPORTFLEX_PKG.GET_FIELDS_PR);

			stmt.registerOutParameter(1, OracleTypes.CURSOR);
			
			log.info("idSeccionRow: "+idSeccionRow);
			
			stmt.setInt(2, idSeccionRow);

			stmt.execute();

			set = ((OracleCallableStatement) stmt).getCursor(1);

			while (set.next()) {

				rows.add(new Field(set, set.getMetaData()));
			}

		} catch (Exception ex) {

			// TODO - Pendiente - Gestion de Excepciones
			ex.printStackTrace();

		} finally {
			
			ConnectionWrapper.closeAll(set, stmt);
		}
		return rows;
	}

	
	public static Field getFieldById(String idCampo) {

		//ArrayList<Field> rows = new ArrayList<Field>();

		ConnectionWrapper connect = null;		
		CallableStatement stmt = null;
		ResultSet set = null;

		try {
			connect = new ConnectionWrapper(); 
					
			stmt = connect.prepareCall(DERCORP_REPORTFLEX_PKG.GET_FIELD_PR);

			stmt.registerOutParameter(1, OracleTypes.CURSOR);
			
			stmt.setInt(2, Integer.parseInt(idCampo));

			stmt.execute();

			set = ((OracleCallableStatement) stmt).getCursor(1);

			while (set.next()) {

				//rows.add(new Field(set, set.getMetaData()));
			
				return new Field(set, set.getMetaData());
			}
			
			return null;

		} catch (Exception ex) {

			// TODO - Pendiente - Gestion de Excepciones
			ex.printStackTrace();

			return null;
		} finally {
			
			ConnectionWrapper.closeAll(set, stmt, connect);
		}
		
	}
	
	
	
	public static String toHTMLForm(String fieldId, HttpServletRequest request){
		
		StringBuilder sb = new StringBuilder();
		
		
		//String lsIdRol   = FacesUtils.getSessionBean().getIdRol();
		SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
		String lsIdRol =   sessionBean.getIdRol();
		
		
		ConnectionWrapper connect = null;
		//CallableStatement stmt = null;
		//ResultSet set = null;

		try {

			connect = new ConnectionWrapper();

			/*
			stmt = connect.prepareCall(DERCORP_REPORTFLEX_PKG.GET_FIELD_PR);

			stmt.registerOutParameter(1, OracleTypes.CURSOR);
			
			stmt.setInt(2, Integer.parseInt(fieldId));

			stmt.execute();

			set = ((OracleCallableStatement) stmt).getCursor(1);

			set.next();

			Field field = new Field(set, set.getMetaData());
			*/
			
			
			sb.append("<table width='100%' style='background: ../../img/borders/page_border/border05.png'>");
			
			sb.append("<tr><th colspan='2' class='tableHeaderAlfa2'>Configuraci&oacute;n del Campo</th><tr>");
			//sb.append("<tr><td colspan='2'>&nbsp;</td><tr>");
			sb.append("<tr><td>Secci&oacute;n:</td><td>");
			
		
			ArrayList<Seccion> secciones = Pagina.getSeccionesRepo(connect,lsIdRol, "*");
			
			sb.append("<input type='hidden' name='fieldId' id='fieldId' value='" + fieldId + "'>");
			sb.append("<select name='selectSeccion' id='selectSeccion' style='width:180px' onchange='selectSeccionChange()'>");
			sb.append("<option value='0'>(Seleccione)</option>");
		
			for (Seccion secc : secciones) {

				 sb.append("<option value='" + secc.getIdSeccion() + "'");
				 
				 //if (Integer.toString(catElem.idCatalogoValor).equals(value)) {
					// sb.append("selected"); 
				 //}
				 
				 sb.append(" >" + secc.getNomSeccion() + "</option>");
			}
			 
			sb.append("</select>");
			
			sb.append("</td></tr>");
			
			
			sb.append("<tr><td>SubSecci&oacute;n:</td><td><div id='detalleSelectSubSeccion'></div></td><td>");

			sb.append("<tr><td>Nombre Campo:</td><td><input name='filterSearch' id='filterSearch' onkeyUp='searchCampos()'></td><td>");
			
			
			sb.append("<tr><td colspan='2'>&nbsp;</td><tr>");
			sb.append("<tr><td colspan='2' class='tableHeaderAlfa2'>Seleccionar Campo:</td><tr>");
			sb.append("<tr><td colspan='2'><div id='detalleSelectCampo'></div></td><tr>");
			
			
			sb.append("</table>");
			

			return sb.toString();
			
		} catch (Exception ex) {

			// TODO - Pendiente - Gestion de Excepciones
			ex.printStackTrace();

			return ex.toString();
		} finally {
			
			ConnectionWrapper.closeAll(connect);
		}
	}
	
	
	public static String getSubSeccionHTML(String fieldId, String seccionId){
		
		StringBuilder sb = new StringBuilder();
		
		ConnectionWrapper connect = null;
		
		try {

			connect = new ConnectionWrapper();
		
			ArrayList<SubSeccion> subSecciones = Seccion.getSubSecciones(Integer.parseInt(seccionId), connect);
			
			
			sb.append("<select name='selectSubSeccion' id='selectSubSeccion' style='width:180px' onchange='selectSubSeccionChange()'>");
			sb.append("<option value='0'>(Seleccione)</option>");
		
			for (SubSeccion secc : subSecciones) {

				 sb.append("<option value='" + secc.getIdSubSeccion() + "'");
				 
				 //if (Integer.toString(catElem.idCatalogoValor).equals(value)) {
					// sb.append("selected"); 
				 //}
				 
				 sb.append(" >" + secc.getNomSubSeccion() + "</option>");
			}
			 
			sb.append("</select>");

			return sb.toString();
			
		} catch (Exception ex) {

			// TODO - Pendiente - Gestion de Excepciones
			ex.printStackTrace();

			return ex.toString();
		} finally {
			
			ConnectionWrapper.closeAll(connect);
		}
	}
	

	public static String getCamposTableHTML(int idSeccion, int idSubSeccion, String filter, boolean showFlexTabs, boolean flexColumns){
		
		StringBuilder sb = new StringBuilder();
		
		//ConnectionWrapper connect = null;
		
		try {

			//connect = new ConnectionWrapper();
		
			ArrayList<Campo> campos = Campo.getCampos(idSeccion, idSubSeccion, filter, showFlexTabs, flexColumns);
			
			
			sb.append("<table width='90%'>");
		
			sb.append("<tr><th class='tableHeaderAlfa2'>Secci&oacute;n</th><th class='tableHeaderAlfa2'>SubSecci&oacute;n</th><th class='tableHeaderAlfa2'>Campo</th></tr>");
		
			for (Campo campo : campos) {

				sb.append("<tr><td>" + campo.getNomSeccion() + "</td>");
				sb.append("<td>" + campo.getNomSubseccion() + "</td>");
				sb.append("<td><a href='#' onclick='selectCampo(" + campo.getIdAddCampo() + ")'>" + campo.getNomCampo() + "</a></td></td>");
			}
			 
			sb.append("</table>");

			return sb.toString();
			
		} catch (Exception ex) {

			// TODO - Pendiente - Gestion de Excepciones
			ex.printStackTrace();

			return ex.toString();
		} 
		/*finally {
			ConnectionWrapper.closeAll(connect);
		}*/
	}
	
	
	

	
	
	
	public int getIdCampo() {
		return idCampo;
	}

	public void setIdCampo(int idCampo) {
		this.idCampo = idCampo;
	}

	public int getIdSeccionRow() {
		return idSeccionRow;
	}

	public void setIdSeccionRow(int idSeccionRow) {
		this.idSeccionRow = idSeccionRow;
	}

	public int getIdAddCampo() {
		return idAddCampo;
	}

	public void setIdAddCampo(int idAddCampo) {
		this.idAddCampo = idAddCampo;
	}

	public int getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}


	public String getNomCampo() {
		return nomCampo;
	}


	public void setNomCampo(String nomCampo) {
		this.nomCampo = nomCampo;
	}


	public String getDesTipoCampo() {
		return desTipoCampo;
	}


	public void setDesTipoCampo(String desTipoCampo) {
		this.desTipoCampo = desTipoCampo;
	}


	public int getIdCatalogo() {
		return idCatalogo;
	}


	public void setIdCatalogo(int idCatalogo) {
		this.idCatalogo = idCatalogo;
	}


	public int getIdFlexTbl() {
		return idFlexTbl;
	}


	public void setIdFlexTbl(int idFlexTbl) {
		this.idFlexTbl = idFlexTbl;
	}


	public String getAtributo1() {
		return atributo1;
	}


	public void setAtributo1(String atributo1) {
		this.atributo1 = atributo1;
	}
	
	
}
