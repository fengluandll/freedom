package mx.com.televisa.derechocorporativo.modules.flextabs;

import java.awt.BufferCapabilities.FlipContents;

import mx.com.televisa.derechocorporativo.model.Catalog;
import mx.com.televisa.derechocorporativo.model.CatalogElement;
import mx.com.televisa.derechocorporativo.model.empresa.EmpresaValores;
import mx.com.televisa.derechocorporativo.modules.dynhtml.CheckBox;
import mx.com.televisa.derechocorporativo.modules.dynhtml.CustomTable;
import mx.com.televisa.derechocorporativo.modules.dynhtml.DateField;
import mx.com.televisa.derechocorporativo.modules.dynhtml.Image;
import mx.com.televisa.derechocorporativo.modules.dynhtml.MultiSelectList;
import mx.com.televisa.derechocorporativo.modules.dynhtml.Radio;
import mx.com.televisa.derechocorporativo.modules.dynhtml.RefDocumentum;
import mx.com.televisa.derechocorporativo.modules.dynhtml.SelectList;
import mx.com.televisa.derechocorporativo.modules.dynhtml.TextArea;
import mx.com.televisa.derechocorporativo.modules.dynhtml.TextField;
import mx.com.televisa.derechocorporativo.modules.dynhtml.TextNumero;
import mx.com.televisa.derechocorporativo.modules.reportsFlex.ReportFlex;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import mx.com.televisa.derechocorporativo.bean.EjercicioBean;
import mx.com.televisa.derechocorporativo.bean.SessionBean;
import mx.com.televisa.derechocorporativo.bean.reformas.AprobEjerSocBean;
import mx.com.televisa.derechocorporativo.components.JSCal;
import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;
import mx.com.televisa.derechocorporativo.modules.captura.Catalogo;
import mx.com.televisa.derechocorporativo.modules.captura.CatalogoValor;
import mx.com.televisa.derechocorporativo.reflexion.ReflexionUtil;
import mx.com.televisa.derechocorporativo.util.ExcelUtil;
import mx.com.televisa.derechocorporativo.util.FacesUtils;
import mx.com.televisa.derechocorporativo.util.NumberFormatter;
import mx.com.televisa.derechocorporativo.util.NumbersUtil;
import mx.com.televisa.derechocorporativo.util.Html;
import mx.com.televisa.derechocorporativo.util.StringUtils;
import mx.com.televisa.derechocorporativo.util.TextFormatter;
import mx.com.televisa.derechocorporativo.daos.AdmVigDAO;
import mx.com.televisa.derechocorporativo.daos.AprobEjerSocDAO;
import mx.com.televisa.derechocorporativo.daos.ConsultaDAO;
import mx.com.televisa.derechocorporativo.daos.ContratosDAO;
import mx.com.televisa.derechocorporativo.daos.EjercicioSocialDao;

public class FlexTable {

	private final static Logger log = Logger.getLogger(FlexTable.class);

	private static final String TYPE_TEXTAREA = "TEXTAREA";
	private static final String TYPE_RADIO = "RADIO";
	private static final String TYPE_RADIO_V = "RADIO_V";

	private static final String TYPE_SELECT = "SELECT";
	private static final String TYPE_MULTISELECT = "MULTISELECT";
	private static final String TYPE_CUSTOM_TABLE = "CUSTOM_TABLE";

	private static final String TYPE_FLEXTABLE = "FLEXTABLE";
	private static final String TYPE_DATE = "DATE";

	private static final String DISABLED_TEXT = "DISABLED_TEXT";

	// ECM 25 Agosto 2015
	// private static String stEditable = "";
	private String stEditable = "";

	// ECM 02 Septiembre 2015
	private static final String TYPE_SELECT_ACCIONISTAS = "SELECT_ACCIONISTAS";

	// ECM 04 Septiembre 2015
	private static final String TYPE_CHECKBOX = "CHECKBOX";

	private static final String TYPE_TITLE = "TITLE";

	// ECM 08 Septiembre 2015
	private static final String TYPE_IMG = "IMG";

	private static final String TYPE_DOCUMENTUM = "DOCUMENTUM";

	private static final String TYPE_TEXTNUM = "TEXTNUM";
	private static final String TYPE_SELECT_MONEDA = "SELECT_MONEDA";

	private static final String TYPE_HIDDEN = "HIDDEN";

	public int idflexTbl;
	public String codFlex;
	public String nomFlex;
	public String desFlex;
	public String atributo1; // CHECK
	public String atributo2; // HORIZONTAL / VERTICAL
	public String atributo3; // Ordenar Columnas de vista rapida en Poderes,
								// Reformas, Contratos y Escrituras Otros. ���
								// No Insertar nada mas. ECM 08 Febrero 2016
								// !!!.
	public String atributo4; // align
	public String atributo5; // border
	public String atributo6; // cellspacing
	public String atributo7; // cellpadding
	public String atributo8; // addSearch
	public String atributo9; // Ordenar Fecha Poderes Generales y Poderes
								// Especiales
	public String atributo10; // Reformas
	public String atributo11; // Mostrar Link Detalle en Consulta
	public String atributo12; // Secciones Agrupables en Detalle
	public String atributo13; // Ordenar por Cargo (Administraci�n y Vigilancia)
	public String atributo14; // VISTA ALTERNA (Pestania Control)

	public String TAB_STYLE;

	public ReportFlex reportFlexReference = null;

	public boolean showTitle = true;

	public boolean showEditableCombosECS = true;

	public String specificFlexColumnsCond = "";

	public boolean filterRowsForReport = false;

	public String specificFlexColumnsList = "";

	public int cantReg = 0;

	// ECM 12 AGOSTO 2015
	// String desFormula;

	ArrayList<FlexColumn> flexColumns;
	private FacesContext facesContext = FacesContext.getCurrentInstance();
	private HttpSession session;

	// ECM 07 Septiembre 2015
	String lstFSApoderados = "";
	String lstFSConseAsamb = "";
	String lstFSReqProto = "";

	private int piSuperindice;
	int isAC;
	String tipoSoc = "";

	public FlexTable(String idflexTbl) {

		try {
			init(Integer.parseInt(idflexTbl));

		} catch (NumberFormatException ex) {

			System.out
					.println("Se identifico NumberFormatException controlado. "
							+ "Se instancio FlexTable con idflexTbl = "
							+ idflexTbl);
		}
	}

	public FlexTable(int idflexTbl) {

		init(idflexTbl);
	}

	public FlexTable(ResultSet set) throws SQLException {

		ReflexionUtil.fillObject(set, set.getMetaData(), this, FlexTable.class);
	}

	private void init(int idflexTbl) {

		this.idflexTbl = idflexTbl;
		//
		try {
			String lstIdEmpresa = FacesUtils.getSessionBean()
					.getIdCurrentEmpresa();
			if (lstIdEmpresa != null) {
				isAC = EstructuraCapitalSocial.isAsociacionCivil(Integer
						.parseInt(lstIdEmpresa));// JJAQ SABER SI ES UNA AC O NO
				tipoSoc = EstructuraCapitalSocial.getTipoSoc(Integer
						.parseInt(lstIdEmpresa));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		ConnectionWrapper conn = null;
		ResultSet set = null;
		PreparedStatement psmt = null;

		try {
			conn = new ConnectionWrapper();

			String lstSql = "SELECT * FROM DERCORP_FLEX_TBLS_TAB "
					+ "WHERE ID_FLEX_TBL = #ID_FLEXTBL#";

			lstSql = lstSql.replaceAll("#ID_FLEXTBL#",
					String.valueOf(this.idflexTbl));

			// set = connect.executeQuery(sqlCampos);
			psmt = conn.prepareStatement(lstSql);
			set = psmt.executeQuery();

			set.next();

			ReflexionUtil.fillObject(set, set.getMetaData(), this,
					FlexTable.class);

		} catch (Exception ex) {

			// TODO - Pendiente - Gestion de Excepciones

			ex.printStackTrace();

		} finally {

			ConnectionWrapper.closeAll(set, psmt, conn);
		}

	}

	public static ArrayList<FlexTable> getReformas() {

		ArrayList<FlexTable> fx = new ArrayList<FlexTable>();

		ConnectionWrapper conn = null;
		ResultSet set = null;
		PreparedStatement psmt = null;

		String lstIdEmpresa = FacesUtils.getSessionBean().getIdCurrentEmpresa();
		String psEditable = FacesUtils.getSessionBean().getEditCon();

		try {
			conn = new ConnectionWrapper();

			String lstSql = "SELECT DERCORP_FLEX_TBLS_TAB.*, '' TAB_STYLE FROM DERCORP_FLEX_TBLS_TAB "
					+ "WHERE ATRIBUTO10 = 'REFORMAS'";

			if (!lstIdEmpresa.equals("248") && !lstIdEmpresa.equals("2122")
					&& !lstIdEmpresa.equals("2031")) {
				lstSql = lstSql + " AND ID_FLEX_TBL NOT IN(41) "; // JAMS se
																	// oculta
																	// comites
																	// para las
																	// que no es
																	// TElevisa
				// se requiere quitar condicion para empaquetar
			}

			if (psEditable != null && psEditable.equals("disabled")) {

				lstSql = lstSql
						.replace(
								"'' TAB_STYLE",
								"( "
										+ "CASE  "
										+ "WHEN ( "
										+ "      SELECT COUNT(*) "
										+ "      FROM DERCORP_METATBL_TAB "
										+ "      WHERE ID_EMPRESA = "
										+ lstIdEmpresa
										+ "  "
										+ "      AND ID_FLEX_TBL = DERCORP_FLEX_TBLS_TAB.ID_FLEX_TBL "
										+ "       " + "    ) > 0 "
										+ "    OR ATRIBUTO14 IS NOT NULL "
										+ "  THEN " + "    '' " + "  ELSE "
										+ "    'style=''display:none''' "
										+ "END " + ") " + "TAB_STYLE");
			}

			psmt = conn.prepareStatement(lstSql);
			set = psmt.executeQuery();
			System.out.println("������ " + lstSql);

			while (set.next()) {

				fx.add(new FlexTable(set));
			}

			return fx;

		} catch (Exception ex) {

			// TODO - Pendiente - Gestion de Excepciones

			ex.printStackTrace();

			return null;

		} finally {

			ConnectionWrapper.closeAll(set, psmt, conn);
		}

	}

	public String toHTML(HttpServletRequest request) {

		// stEditable = FacesUtils.getSessionBean().getEditCon();
		session = (HttpSession) facesContext.getExternalContext().getSession(
				true);
		SessionBean sessionBean = (SessionBean) session
				.getAttribute("sessionBean");
		stEditable = sessionBean.getEditCon();

		if (this.atributo2 != null && this.atributo2.equals("HORIZONTAL")) {
			
			return toHTMLHorizontal(request);

		} else {

			return toHTMLVertical(request);
		}
	}

	public int toExcel(XSSFSheet sheet, ConnectionWrapper connect,
			HttpServletRequest request, int rowIndex) {
		HttpSession session = (HttpSession) request.getSession(true);
		SessionBean sessionBean = (SessionBean) session
				.getAttribute("sessionBean");
		stEditable = sessionBean.getEditCon();

		if (this.atributo2.equals("HORIZONTAL")) {

			return toExcelHorizontal(sheet, connect, request, rowIndex);

		} else {

			return rowIndex;
			// return toExcelVertical(sheet, connect, request, rowIndex);
		}
	}

	public String toHTMLHorizontal(HttpServletRequest request) {
		String moneda = null;
		StringBuilder sb = new StringBuilder();
		ConnectionWrapper conn = null;
		ResultSet set = null;
		PreparedStatement psmt = null;

		String groupColumn = "";
		String groupColumnOrderBy = "";
		boolean isGrouped = false;
		boolean isConsulta = false;

		ConsultaDAO luConsultaDAO = null;
		int liCountRegistros = 0;
		AdmVigDAO admVigDAO = new AdmVigDAO();
		int numSuplentes = -1; // punto 63

		try {
			String lstIdEmpresa = FacesUtils.getSessionBean()
					.getIdCurrentEmpresa();

			conn = new ConnectionWrapper();
			boolean reporte = false;
			if ((stEditable == null) || (stEditable.equals(""))) {
				flexColumns = FlexColumn.getFlexColumns(this.idflexTbl, conn,
						true, request, "0", this.specificFlexColumnsCond, true,
						reporte);
				isConsulta = false;
			} else {
				flexColumns = FlexColumn.getFlexColumns(this.idflexTbl, conn,
						true, request, "0", this.specificFlexColumnsCond, true,
						this.atributo13, reporte);
				isConsulta = true;
			}

			// ECM 09 Mayo 2016 Consulta Adm y Vig - No mostrar flex que no
			// tengan registros.
			if (isConsulta && this.atributo13 != null
					&& this.atributo13.equals("ORDER_BY_CARGO")) {
				luConsultaDAO = new ConsultaDAO();
				// Tabla temporal DERCORP_CON_ADM_VIG_TMP
				log.info("TABLA A INSERTAR: " + this.idflexTbl);
				luConsultaDAO.ejecutar_Pr_Adm_Vig(lstIdEmpresa, this.idflexTbl);
				liCountRegistros = luConsultaDAO.consultar_Adm_Vig(
						lstIdEmpresa, this.idflexTbl);

				// Revisar si hay suplentes en flex con id
				// 9,11,12,13,14,15,16,26,50 punto 63
				if (this.idflexTbl == 9 || this.idflexTbl == 11
						|| this.idflexTbl == 12 || this.idflexTbl == 13
						|| this.idflexTbl == 14 || this.idflexTbl == 15
						|| this.idflexTbl == 40 || this.idflexTbl == 16
						|| this.idflexTbl == 26 || this.idflexTbl == 43
						|| this.idflexTbl == 46 || this.idflexTbl == 44
						|| this.idflexTbl == 45) {
					numSuplentes = luConsultaDAO.getNumSuplentes(
							this.idflexTbl, Integer.parseInt(lstIdEmpresa));
				}
			}

			if (isConsulta && this.atributo13 != null
					&& this.atributo13.equals("ORDER_BY_CARGO")
					&& liCountRegistros == 0) {

			} else {
				// ULR para agregar borde de 30 px a las flex tab de admon y vig
				if (idflexTbl == 8 || idflexTbl == 39 || idflexTbl == 11
						|| idflexTbl == 13 || idflexTbl == 40
						|| idflexTbl == 12 || idflexTbl == 14
						|| idflexTbl == 15 || idflexTbl == 44
						|| idflexTbl == 16 || idflexTbl == 43
						|| idflexTbl == 46 || idflexTbl == 26
						|| idflexTbl == 25 || idflexTbl == 9 || idflexTbl == 45) {
					log.info("idflexTbl para agregar 30px: " + idflexTbl);
					sb.append("<table style='padding-top: 30px;' width='"
							+ atributo3 + "' align='" + atributo4
							+ "' class='flexTable' border='" + atributo5
							+ "' cellspacing='" + atributo6 + "' cellpadding='"
							+ atributo7 + "'>");
				} else {

					sb.append("<table width='" + atributo3 + "' align='"
							+ atributo4 + "' class='flexTable' border='"
							+ atributo5 + "' cellspacing='" + atributo6
							+ "' cellpadding='" + atributo7 + "'>");
				}
				// sb.append("<table style='padding-top: 40px;' width='" +
				// atributo3 + "' align='" + atributo4 +
				// "' class='flexTable' border='" + atributo5 +
				// "' cellspacing='" + atributo6 + "' cellpadding='" + atributo7
				// + "'>");

				// ICL 10-11-2015 Corregir encoding en filtro de flex
				// String filter = request.getParameter("filter");
				String filter = null;
				try {
					filter = new String(request.getParameter("filter")
							.getBytes("ISO-8859-1"), "UTF-8");
				} catch (Exception e) {
					filter = "";
				}

				String modo = "";
				try {
					SessionBean sessionBean = (SessionBean) request
							.getSession().getAttribute("sessionBean");

					modo = sessionBean.getPaginaModo();

					if (modo == null) {

						modo = "";
					}
				} catch (Exception ex) {

				}

				// addSearch
				if (atributo8 != null && atributo8.equals("1")
						&& (!modo.equals("PRINT")) && this.TAB_STYLE !=null) {

					String txtId = "txtSearch_flex_" + this.idflexTbl;

					// String searchImage = "<a onclick=\"loadFlexTabFilter('" +
					// this.idflexTbl + "'," + txtId + ".value)\"><img src='" +
					// request.getContextPath() + "/img/search.png'></a>";
					String searchImage = "<input type='button' value='Buscar' onclick=\"loadFlexTabFilter('"
							+ this.idflexTbl + "'," + txtId + ".value)\" >";

					sb.append("<tr><td colspan='" + (flexColumns.size() + 2)
							+ "'>");

					// onkeyup=\"loadFlexTabFilter('" + this.idflexTbl + "'," +
					// txtId + ".value)\"
					sb.append("<input type='text' id='"
							+ txtId
							+ "' onkeydown=\"if(event.keyCode == 13){loadFlexTabFilter('"
							+ this.idflexTbl
							+ "',"
							+ txtId
							+ ".value); return false;}\""
							+ " value='"
							+ ((filter != null) ? filter : "")
							+ "' title='Puede utilizar varios criterios de b�squeda haciendo uso del caracter: | Ej. Asamblea|2015|543456' > "
							+ searchImage);

					sb.append("</td></tr>");

				}

				if (showTitle) {
					// ULR Se agrego una etiqueta h4 a los titulos de las flex
					// de admon y vigilancia
					if (idflexTbl == 8 || idflexTbl == 39 || idflexTbl == 11
							|| idflexTbl == 13 || idflexTbl == 40
							|| idflexTbl == 12 || idflexTbl == 14
							|| idflexTbl == 15 || idflexTbl == 44
							|| idflexTbl == 16 || idflexTbl == 43
							|| idflexTbl == 26 || idflexTbl == 25
							|| idflexTbl == 9 || idflexTbl == 45
							|| idflexTbl == 46) {// se agregar 46 JAMS
						log.info("Nombre de la flex: " + nomFlex);
						sb.append("<tr><th colspan='"
								+ (flexColumns.size() + 1) + "'><h2>" + nomFlex
								+ "</h2></th>");
					} else {
						sb.append("<tr><th colspan='"
								+ (flexColumns.size() + 1) + "'></th>");
					}
					// sb.append("<tr><th colspan='"+ (flexColumns.size()+1)
					// +"'>"+ nomFlex + "</th>");
				}

				String newImage = "<img src='" + request.getContextPath()
						+ "/img/icons/new.png'>";

				if ((stEditable == null) || (stEditable.equals(""))) {
					// sb.append("<td align='center'><a onClick=\"closeForm();sleep(1500);setFlexTableId('"
					// + this.idflexTbl + "');loadFlexTableForm('" +
					// this.idflexTbl + "-0" + "');\">" + newImage +
					// "</a></td>");
					// sb.append("<td align='center'><a onClick=\"closeForm();sleep(2000);setFlexTableId('"
					// + this.idflexTbl + "');loadFlexTableForm('" +
					// this.idflexTbl + "-0" + "');\">" + newImage +
					// "</a></td>");

					if (atributo14 == null) {

						// sb.append("<td align='center'><a onClick=\"setAlterFlexTableId('"
						// + this.idflexTbl +
						// "');closeCurrentAndLoadFlexTabForm('" +
						// this.idflexTbl + "','" + this.idflexTbl + "-0" +
						// "');\">" + newImage + "</a></td>");
						sb.append("<td align='center'><a onClick=\"setAlterFlexTableId('"
								+ this.idflexTbl
								+ "');nuevo_fn('"
								+ this.idflexTbl
								+ "','"
								+ this.idflexTbl
								+ "-0" + "');\">" + newImage + "</a></td>");
						// sb.append("<td align='center'><a onClick=\"closeCurrentAndLoadFlexTabForm('"
						// + this.idflexTbl + "','" + this.idflexTbl + "-0" +
						// "');\">" + newImage + "</a></td>");
					}

				} else {
					sb.append("<td align='center'><a onClick=\"closeForm();sleep(2000);setFlexTableId('"
							+ this.idflexTbl
							+ "');loadFlexTableForm('"
							+ this.idflexTbl
							+ "-0"
							+ "');\">"
							+ ""
							+ "</a></td>");
				}

				sb.append("</tr>");

				sb.append("<tr>");

				boolean showTotalsRow = false;
				Map<String, Double> totalsMap = new HashMap<String, Double>();
				// Elimia Columna de Suplentes punto 63
				if (numSuplentes == 0 && isConsulta && this.atributo13 != null
						&& this.atributo13.equals("ORDER_BY_CARGO")) {
					flexColumns.remove(2);
				}
				
				ArrayList<FlexTable> reformas = FlexTable.getReformas();
				
				List<Integer> listBorradas = new ArrayList<Integer>();
				
				for(FlexTable tab : reformas) { 
	    			
					if(tab.TAB_STYLE != null) {
						listBorradas.add(tab.idflexTbl);
					}
				}
				int conta = 0;
			
				for (FlexColumn flexColumn : flexColumns) {
					
				

					// ECM 27 Abril 2016 Consulta - Adm y Vig - Redimensionar
					// los headers.
					// JJAQ 19/10/16 rediemnsionar para la impresion PDF
					if (isConsulta && this.atributo13 != null
							&& this.atributo13.equals("ORDER_BY_CARGO")) {
						if (flexColumns.size() == 3) {
							sb.append("<th class='tableHeader' width='35%'>");
						} else if (flexColumns.size() == 2) {
							sb.append("<th class='tableHeader' width='55%'>");
						} else {
							sb.append("<th class='tableHeader' width='25%'>");
						}
						sb.append(flexColumn.NOM_FLEX_COLUM);

					} else if (this.idflexTbl == 30
							&& flexColumn.ATRIBUTO3.equals("3")) {
						sb.append("<th class='tableHeader' width='"
								+ flexColumn.DES_CELL_WIDTH + "'>");
						sb.append("Caracteristicas");

					}
					// CULR se redimenciono observaciones generales
					else if (this.idflexTbl == 36
							&& flexColumn.ID_FLEX_COLUM == 1032
							&& isConsulta == true) {
						sb.append("<th class='tableHeader' width='100%'>");
						sb.append(flexColumn.NOM_FLEX_COLUM);
					}
					// CULR se redimenciono denominaciones anteriores 03/01/2016
					else if (this.idflexTbl == 2
							&& flexColumn.ID_FLEX_COLUM == 6
							&& isConsulta == true) {
						sb.append("<th class='tableHeader' width='100%'>");
						sb.append(flexColumn.NOM_FLEX_COLUM);
					}
					// CULR 16/12/2016 se redimenciono columna objeto social a
					// 100% en consulta e impresion
					else if (this.idflexTbl == 5
							&& flexColumn.ID_FLEX_COLUM == 15
							&& isConsulta == true) {
						if (!stEditable.equals("disabled")) {
							sb.append("<th class='tableHeader' width='100%'>");
							sb.append(flexColumn.NOM_FLEX_COLUM);
						} else {
							sb.append(" ");
						}
					} else {
						if (this.idflexTbl == 7
								&& flexColumn.ID_FLEX_COLUM == 22 && isAC > 0) {// JJAQ
																				// campo
																				// Accionista
							sb.append("<th class='tableHeader' width='95%'>");
						} else {
							sb.append("<th class='tableHeader' width='"
									+ flexColumn.DES_CELL_WIDTH + "'>");
						}

						// ECM 31 AGOSTO 2015
						/*
						 * if(flexColumn.NOM_FLEX_COLUM.equals("Valor Acciones"))
						 * { sb.append(flexColumn.NOM_FLEX_COLUM);
						 * //sb.append("\n");
						 * //sb.append(getMonedaSetLabel(conn, lstIdEmpresa));
						 * }else{ sb.append(flexColumn.NOM_FLEX_COLUM); }
						 */

						sb.append(flexColumn.NOM_FLEX_COLUM);

					}
					sb.append("</th>");
					// ECM 08 Septiembre 2016 - Contratos - Agregar Header
					// "Celebrado entre partes".
					if (this.idflexTbl == 30
							&& flexColumn.ATRIBUTO3.equals("3")) {
						sb.append("<th class='tableHeader' width='"
								+ flexColumn.DES_CELL_WIDTH + "'>");
						sb.append("Celebrado entre partes");
						sb.append("</th>");
					}

					if (flexColumn.DES_GROUP != null
							&& flexColumn.DES_GROUP.equals("GROUP")) {
						groupColumn = flexColumn.COD_FLEX_COLUM;
						groupColumnOrderBy = "OUTER_Q."
								+ flexColumn.COD_FLEX_COLUM + ", ";
						isGrouped = true;
					}

					if (flexColumn.DES_TOTALS != null
							&& flexColumn.DES_TOTALS.equals("TOTAL")) {

						showTotalsRow = true;
						totalsMap.put(flexColumn.COD_FLEX_COLUM, new Double(0));
					}
				
				}
				// ECM 27 AGOSTO 2015
				if ((stEditable == null) || (stEditable.equals(""))) {
					sb.append("<th class='tableHeader' width='10%'>Editar</th>");
					if (atributo14 == null) {
						sb.append("<th class='tableHeader' width='10%'>Borrar</th>");
					}

					if (idflexTbl == 34 || idflexTbl == 23) {
						sb.append("<th class='tableHeader' width='10%'>Copiar</th>");
						sb.append("<input type='hidden' id='contextPath' value='"
								+ facesContext.getExternalContext()
										.getRequestContextPath() + "'>");
					}

				} else {

					if (atributo11 != null && atributo11.equals("DETALLE")) {
						sb.append("<th class='tableHeader' width='10%'>Detalle</th>");
					}
				}
				sb.append("</tr>");

				// ECM 14 AGOSTO 2015
				// Agregar Combos
				if (this.idflexTbl == EstructuraCapitalSocial.ECS_FLEX_TABLE_ID) {

					if (stEditable == null || stEditable.equals("")) {

					} else {

						showEditableCombosECS = false;
					}
					/*
					 * ULR 04/01/2017 condicion para ocultar la fila cuando acc.
					 * cap fijo y acc. cap var sean N/A en flex Est. cap. soc
					 */
					String testVal = EstructuraCapitalSocial.getTableSubHeader(
							flexColumns, conn, lstIdEmpresa,
							showEditableCombosECS, specificFlexColumnsList)
							.toString();
					if (testVal.contains("<div id='DIVC24'>N/A</div>")
							&& testVal.contains("<div id='DIVC25'>N/A</div>")) {
						sb.append("");
					} else {
						/*
						 * ULR 04/01/2017 se a�adio un replace para no mostrar
						 * el N/A en caso de se seleccione uno del combo de la
						 * flex estr. capt. soc
						 */
						if (isConsulta == true) {
							sb.append(testVal.replaceAll("N/A", ""));
						} else {
							sb.append(testVal);
						}

					}

				}

				String sqlCampos = null;

				if (isGrouped) {
					// sqlCampos =
					// "SELECT OUTER_Q.*, (select count(*) FROM DERCORP_METATBL_TAB INNER_Q  WHERE INNER_Q.ID_EMPRESA = OUTER_Q.ID_EMPRESA AND INNER_Q.ID_FLEX_TBL = OUTER_Q.ID_FLEX_TBL AND INNER_Q."
					// + groupColumn + " = OUTER_Q." + groupColumn +
					// ") COUNT_GRP " +
					sqlCampos = "SELECT OUTER_Q.*, (select count(*) FROM DERCORP_METATBL_TAB INNER_Q  WHERE INNER_Q.ID_EMPRESA = OUTER_Q.ID_EMPRESA AND INNER_Q.ID_FLEX_TBL = OUTER_Q.ID_FLEX_TBL AND NVL(INNER_Q."
							+ groupColumn
							+ ",'*') = NVL(OUTER_Q."
							+ groupColumn
							+ ",'*')) COUNT_GRP "
							+ "FROM DERCORP_METATBL_TAB OUTER_Q "
							+ "WHERE OUTER_Q.ID_EMPRESA = "
							+ lstIdEmpresa
							+ " "
							+ "AND OUTER_Q.ID_FLEX_TBL = "
							+ this.idflexTbl
							+ " ORDER BY "
							+ groupColumnOrderBy + "OUTER_Q.ID_META_ROW";
				} else {
					// ECM 14 Abril 2016 - Poderes Generales y Especiales -
					// Ordenar por campo Fecha
					if (this.atributo9 != null
							&& this.atributo9.equals("VAL_C9")) {
						sqlCampos = "SELECT META.* "
								+ "FROM DERCORP_METATBL_TAB META "
								+ "WHERE META.ID_EMPRESA = "
								+ lstIdEmpresa
								+ " "
								+ "AND META.ID_FLEX_TBL = "
								+ this.idflexTbl
								+ "  ORDER BY APP_COMMON_PKG.GET_AS_DATE_FOR_ORDER(VAL_C3)  DESC, ID_META_ROW";

					} else if (this.atributo13 != null
							&& atributo13.equals("ORDER_BY_CARGO")) {

						// ICL 24/02/2016
						if ((stEditable == null) || (stEditable.equals(""))) {
							// ICL 24/02/2016
							sqlCampos = "SELECT META.* "
									+ "FROM DERCORP_METATBL_TAB META "
									+ "WHERE META.ID_EMPRESA = "
									+ lstIdEmpresa
									+ " "
									+ "AND META.ID_FLEX_TBL = "
									+ this.idflexTbl
									+ "  ORDER BY TO_NUMBER(META.VAL_C15)"
									+ ", DERCORP_CATALOGS_PKG.GET_FUNCIONARIOS_FN(META.VAL_C1)";
						} else {
							sqlCampos = luConsultaDAO.getRows_Adm_Vig(
									lstIdEmpresa, this.idflexTbl);

						}
					} else if (this.idflexTbl == 30) {
						sqlCampos = " SELECT * "
								+ " FROM DERCORP_METATBL_TAB "
								+ " WHERE ID_EMPRESA = " 
								+ lstIdEmpresa
								+ " AND ID_FLEX_TBL = "
								+ this.idflexTbl
								+ " ORDER BY APP_COMMON_PKG.GET_AS_DATE_FOR_ORDER(VAL_C4) DESC, ID_META_ROW" // Contratos
						;

					} else if (this.idflexTbl == 27) { // se ordena
														// cronologicamente para
														// Escrituras Otros JAMS
														// 28/09/2017
						sqlCampos = " SELECT * "
								+ " FROM DERCORP_METATBL_TAB "
								+ " WHERE ID_EMPRESA = " 
								+  lstIdEmpresa 
								+ " AND ID_FLEX_TBL = "
								+ this.idflexTbl
								+ " ORDER BY APP_COMMON_PKG.GET_AS_DATE_FOR_ORDER(VAL_C18) DESC, ID_META_ROW";
					} else {

						sqlCampos = " SELECT * "
								+ " FROM DERCORP_METATBL_TAB "
								+ " WHERE ID_EMPRESA = " 
								+  lstIdEmpresa 
								+ " AND ID_FLEX_TBL = "
								+ this.idflexTbl
								+ " ORDER BY APP_COMMON_PKG.GET_AS_DATE_FOR_ORDER(VAL_C3) DESC, ID_META_ROW" // Reformas
																												// y
																												// Movimientos
						;

					}
				}

				// CONTROL
				if (this.atributo14 != null) {

					sqlCampos = "SELECT * "
							+ "FROM "
							+ this.atributo14
							+ " "
							+ "WHERE ID_EMPRESA = "
							+ lstIdEmpresa
							+ " "
							+ " ORDER BY APP_COMMON_PKG.GET_AS_DATE_FOR_ORDER(VAL_C1)  DESC, ID_META_ROW";
				}

				if (filterRowsForReport) {

					Map<String, String> map = FacesUtils.getSessionBean()
							.getDynamicParams();

					for (Entry<String, String> entry : map.entrySet()) {

						String key = entry.getKey();
						String value = entry.getValue();

						// FlexFields
						if (Integer.parseInt(key) >= 10000) {

							int idFlexColum = Integer.parseInt(key) / 10000;

							FlexColumn fc = FlexColumn.getFlexColumn(
									idFlexColum, conn);

							String code = fc.COD_FLEX_COLUM;

							if (this.idflexTbl == fc.ID_FLEX_TBL) {

								sqlCampos = sqlCampos
										.replace(
												"ORDER BY",
												"AND APP_COMMON_PKG.SIN_ACENTOS_FN(UPPER(',"
														+ value
														+ ",')) LIKE APP_COMMON_PKG.SIN_ACENTOS_FN(UPPER('%,' || "
														+ code
														+ " || ',%')) ORDER BY");
							}
						}
					}
				}

				System.out.println("Testing: " + sqlCampos);
				// sqlCampos =
				// "SELECT * FROM DERCORP_CONTROL_VW WHERE ID_EMPRESA = 248 and id_meta_row in (11028,6711,6712,6713,6707,6277,7246,7247,7342,6953,6874,7976)";

				psmt = conn.prepareStatement(sqlCampos,
						ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				set = psmt.executeQuery();

				boolean alterClass = false;
				String trStyle = "";

				String lastValue = "UNDEFINED";

				ArrayList<Integer> matchCountByRow = new ArrayList<Integer>();
				int maxMatchRow = 0;
				/*
				 * WHILE DE REVISION DE FILTROS
				 */

				while (set.next()) {
					int matchCountThisRow = 0;

					// boolean showThisRecord = true;

					// addSearch
					if (atributo8 != null && atributo8.equals("1")
							&& filter != null && !filter.equals("")) {

						// showThisRecord = false;

						// String lstAllColumnValues = "";
						// int columnIndex = 0;
						// int matchedColumnCount = 0;

						for (FlexColumn flexColumn : flexColumns) {
							// columnIndex++;

							try {

								String value = set
										.getString(flexColumn.COD_FLEX_COLUM);
								if (flexColumn.ID_CATALOGO != 0) {
									value = getCatalogValue(conn, flexColumn,
											value);

								}

								String comparableValue = "";

								if (value != null) {

									comparableValue = TextFormatter
											.removeAccents(value.toUpperCase());

								}

								String[] filterWords = StringUtils.split(
										filter, " ");

								words: for (String filterWord : filterWords) {

									if (!filterWord.equals("")) {

										String comparableWord = TextFormatter
												.removeAccents(filterWord
														.toUpperCase());
										int liIdMetaRow = set
												.getInt("ID_META_ROW");
										/*
										 * if(liIdMetaRow == 6277 || liIdMetaRow
										 * == 7965 || liIdMetaRow == 7933 ||
										 * liIdMetaRow == 7968 || liIdMetaRow ==
										 * 11028 || liIdMetaRow == 7935 ||
										 * liIdMetaRow == 9391){
										 * System.out.println("Entro 1"); }
										 */
										if (comparableValue
												.contains(comparableWord)) {

											// matchedColumnCount++;

											// showThisRecord = true;

											// break words;
											// break columns;

											matchCountThisRow++;
										}

									}
								}

							} catch (Exception ex) {

								System.out.println(ex.toString());
							}

							/*
							 * 
							 * 
							 * lstAllColumnValues = lstAllColumnValues + " " +
							 * value;
							 * 
							 * //ICL 24/09/2015 Se agrega funcionalidad de
							 * multiples criterios de b�squeda // if(value !=
							 * null && filter != null &&
							 * value.toLowerCase().contains
							 * (filter.toLowerCase())) { // showThisRecord =
							 * true; // }
							 */

						}

						if (matchCountThisRow > maxMatchRow) {

							maxMatchRow = matchCountThisRow;
						}

						matchCountByRow.add(matchCountThisRow);

					} else {

						matchCountByRow.add(0);
					}

					/*
					 * if(lstAllColumnValues != null && filter != null) {
					 * Pattern p = Pattern.compile("("+filter+")"); Matcher m =
					 * p.matcher(lstAllColumnValues); while (m.find()) { String
					 * matched = m.group(1); showThisRecord = true; } }
					 */

					/*
					 * if(!showThisRecord) {
					 * 
					 * continue; }
					 */
				}

				set.beforeFirst();
				ContratosDAO contratosDAO = new ContratosDAO();

				/**
				 * WHILE DE IMPRESION EN LA TABLA (checar)
				 * */
				int rowIndex = 0;
				int liIdMetaRow = 0;
				SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
				// Date fecha = new java.util.Date();
				// System.out.println(idflexTbl+" Ini While: "+sdf.format(fecha));
				boolean entroRows = false;
				String tmpRow = "##";
				String tmpValue = "##";
				while (set.next()) {
					// System.out.println(idflexTbl+" Ini While: "+new
					// java.util.Date());
					liIdMetaRow = set.getInt("ID_META_ROW");
					// if(liIdMetaRow == 6277 || liIdMetaRow == 7965 ||
					// liIdMetaRow == 7933 || liIdMetaRow == 7968 ||
					// liIdMetaRow == 11028 || liIdMetaRow == 7935 ||
					// liIdMetaRow == 9391){
					// System.out.println("Entro");
					// }
					int matchCountThisRow = matchCountByRow.get(rowIndex);
					rowIndex++;
					// JJAQ Se comenta porque no buscaba correctamente
					/*
					 * if(matchCountThisRow != maxMatchRow) {
					 * 
					 * continue; }
					 */
					if (!filter.equals("") && matchCountThisRow == 0) {

						continue;
					}

					/*
					 * if(!showThisRecord) {
					 * 
					 * continue; }
					 */

					cantReg++;

					alterClass = !alterClass;
					trStyle = (alterClass) ? "class=\"tableRow2\"" : "";

					sb.append("<tr " + trStyle + ">");

					String cellClass = "";
					// fecha = new java.util.Date();
					// System.out.println(idflexTbl+" Ini For: "+sdf.format(fecha));
					if (idflexTbl == 11) {
						// System.out.println("******Flex 11 ******");
					}

					for (FlexColumn flexColumn : flexColumns) {
						// System.out.println(idflexTbl+" Inici for "+new
						// java.util.Date());
						String value = set.getString(flexColumn.COD_FLEX_COLUM);

						String rowSpan = "";

						cellClass = "";

						if (flexColumn.DES_GROUP != null
								&& flexColumn.DES_GROUP.equals("GROUP")) {

							rowSpan = " rowspan='" + set.getString("COUNT_GRP")
									+ "'";

							String valC8 = set.getString("VAL_C8") == null ? ""
									: set.getString("VAL_C8");

							if (!tmpValue.equals(valC8)) {
								entroRows = false;
								tmpRow = set.getString("COUNT_GRP");
								tmpValue = valC8;

							}

							cellClass = " class='tableHeaderAlfa'";

							if (lastValue == null) {

								lastValue = "";
							}

							if (lastValue.equals(value)) {
								continue;
							}

							lastValue = value;

						}

						if (flexColumn.DES_TOTALS != null
								&& flexColumn.DES_TOTALS.equals("TOTAL")) {

							Double currentTotal = totalsMap
									.get(flexColumn.COD_FLEX_COLUM);

							if (value != null
									&& NumbersUtil.isNumeric(value.replace(",",
											"."))) {

								currentTotal += Double.parseDouble(value
										.replace(",", "."));

								totalsMap.put(flexColumn.COD_FLEX_COLUM,
										currentTotal);
							}
						}
						// Cambio Nava 20/12/2016 punto 61
						boolean vacio = false;

						if (flexColumn.DES_FORMAT == null
								&& this.idflexTbl == 7
								&& flexColumn.ID_FLEX_COLUM == 26) {

							sb.append("<td " + rowSpan + cellClass + " align='"
									+ flexColumn.DES_ALIGN + "'>");
							vacio = true;

						} else // se a�adio esta condicion para justificar el
								// texto de objeto social
						if (this.idflexTbl == 5
								&& flexColumn.ID_FLEX_COLUM == 15) {
							sb.append("<td " + rowSpan + cellClass
									+ " align='justify'>");
							vacio = true;
						} else {
							// se agrega condicion para que aga el agrupado de
							// forma correcta JAMS 08/03/2018
							if (entroRows == false
									&& (flexColumn.DES_GROUP != null && flexColumn.DES_GROUP
											.equals("GROUP"))) {
								sb.append("<td " + rowSpan + cellClass
										+ " align='" + flexColumn.DES_ALIGN
										+ "'>&nbsp;");
								vacio = true;
								entroRows = true;
							} else {
								// sb.append("<td " + rowSpan + cellClass+
								// " align='" + flexColumn.DES_ALIGN + "'>");
								
								if (rowSpan.isEmpty()) {
									sb.append("<td " + cellClass + " align='"
											+ flexColumn.DES_ALIGN + "'>");
									vacio = true;
								}

							}
						}
						/*
						 * //Cambio Nava 20/12/2016 if(flexColumn.DES_FORMAT ==
						 * null){ sb.append("<td " + rowSpan + cellClass+
						 * " align='center'>"); }else{ sb.append("<td " +
						 * rowSpan + cellClass+ " align='" +
						 * flexColumn.DES_ALIGN + "'>"); } //sb.append("<td " +
						 * rowSpan + cellClass+ " align='" +
						 * flexColumn.DES_ALIGN + "'>");
						 */
						if (value == null)
							value = "";

						if (flexColumn.ID_CATALOGO == 0) {
							if (flexColumn.DES_FORMAT != null
									&& flexColumn.DES_FORMAT.equals("NUMBER")) {

								sb.append(NumberFormatter.conComas(value));

							} else if (flexColumn.DES_FORMAT != null
									&& flexColumn.DES_FORMAT.equals("INTEGER")) {

								if ((flexColumn.NOM_FLEX_COLUM
										.contains("valor")
										|| flexColumn.NOM_FLEX_COLUM
												.contains("Valor")
										|| flexColumn.NOM_FLEX_COLUM
												.contains("Value")
										|| flexColumn.NOM_FLEX_COLUM
												.contains("value")
										|| flexColumn.NOM_FLEX_COLUM
												.contains("Participations") || flexColumn.NOM_FLEX_COLUM
											.contains("participations"))) {
									sb.append("$ "
											+ NumberFormatter
													.conComas(value, 2));
								} else {
									sb.append(NumberFormatter
											.conComas(value, 0));
								}

							} else if (flexColumn.DES_FORMAT != null
									&& flexColumn.DES_FORMAT.equals("AMOUNT")) { // KAZ-NAVA-26Oct

								// SessionBean sessionBean = (SessionBean)
								// request.getSession().getAttribute("sessionBean");

								// modo = sessionBean.getPaginaModo();
								// String moneda =
								// sessionBean.getIdCurrentEmpresaMoneda();
								// String moneda =
								// EmpresaValores.getMonedaECS(lstIdEmpresa);

								if (moneda == null) {

									// moneda = "";
									moneda = EmpresaValores
											.getMonedaECS(lstIdEmpresa);
								}

								// sb.append("$ " +
								// NumberFormatter.conComas(value) + " " +
								// moneda); //KAZ-NAVA-26Oct
								sb.append("$ "
										+ NumberFormatter.conComas(value, 2));

							} else if (flexColumn.DES_FORMAT != null
									&& (flexColumn.DES_FORMAT
											.equals("AMOUNT_SC"))) { // KAZ-NAVA-26Oct

								// SessionBean sessionBean = (SessionBean)
								// request.getSession().getAttribute("sessionBean");

								// modo = sessionBean.getPaginaModo();
								// String moneda =
								// sessionBean.getIdCurrentEmpresaMoneda();
								// String moneda =
								// EmpresaValores.getMonedaECS(lstIdEmpresa);

								if (moneda == null) {

									// moneda = "";
									moneda = EmpresaValores
											.getMonedaECS(lstIdEmpresa);
								}

								// sb.append("$ " +
								// NumberFormatter.conComas(value) + " " +
								// moneda); //KAZ-NAVA-26Oct
								sb.append(NumberFormatter.conComas(value));

							} else if (flexColumn.DES_FORMAT != null
									&& /*
										 * flexColumn.DES_FORMAT.equals("AMOUNT_SC"
										 * ) &&
										 */
									(flexColumn.NOM_FLEX_COLUM
											.contains("valor")
											|| flexColumn.NOM_FLEX_COLUM
													.contains("Valor")
											|| flexColumn.NOM_FLEX_COLUM
													.contains("Value")
											|| flexColumn.NOM_FLEX_COLUM
													.contains("value")
											|| flexColumn.NOM_FLEX_COLUM
													.contains("Participations") || flexColumn.NOM_FLEX_COLUM
												.contains("participations"))) {// JJAQ
																				// 15/02/2017
																				// solo
																				// pesos
																				// en
																				// valor
																				// con
																				// S.C.
								if (moneda == null) {

									moneda = EmpresaValores
											.getMonedaECS(lstIdEmpresa);
								}

								sb.append("$ "
										+ NumberFormatter.conComas(value, 2));

							} else if (flexColumn.DES_TIPO_COLUM != null
									&& flexColumn.DES_TIPO_COLUM
											.equals(TYPE_IMG)) {// ECM 09
																// Septiembre
																// 2015
								sb.append(Image.getIMG(flexColumn, value,
										this.idflexTbl));

							} else if (flexColumn.DES_TIPO_COLUM != null
									&& flexColumn.DES_TIPO_COLUM
											.equals(TYPE_HIDDEN)) {
								sb.append(Html.textToHTML(value));

							} else {

								if (flexColumn.NOM_FLEX_COLUM.contains("%")
										|| flexColumn.NOM_FLEX_COLUM
												.contains("%")) {
									DecimalFormat df = new DecimalFormat(
											"#.000000");
									if (value == null || value.isEmpty()) {
										value = "0";
									}
									sb.append(Html.textToHTML(df.format(Double
											.parseDouble(value))));
									// sb.append(Html.textToHTML(NumberFormatter.conComas(value,6)));
								} else {
									sb.append(Html.textToHTML(value));
								}
								// sb.append(Html.textToHTML(value));
							}
							// ECM 21 Septiembre 2015
						} else if (flexColumn.DES_TIPO_COLUM != null
								&& flexColumn.DES_TIPO_COLUM
										.equals(TYPE_SELECT_ACCIONISTAS)) {

							boolean forceShowRFC = (reportFlexReference != null
									&& reportFlexReference.getAtributo1() != null && reportFlexReference
									.getAtributo1().equals("SI"));

							boolean showPais = (reportFlexReference != null
									&& reportFlexReference.getAtributo2() != null && reportFlexReference
									.getAtributo2().equals("SI"));

							if ((stEditable == null) || (stEditable.equals(""))
									&& forceShowRFC) {
								if (showPais) {
									sb.append(Html.textToHTML(SelectList
											.getCatalogValueAccionistas(conn,
													flexColumn, value)));
								} else {
									sb.append(Html.textToHTML(SelectList
											.getCatalogValueAccionistasSinPais(
													conn, flexColumn, value)));
								}

							} else if ((!stEditable.equals("")) && forceShowRFC) {
								if (showPais) {
									sb.append(Html.textToHTML(SelectList
											.getCatalogValueAccionistas(conn,
													flexColumn, value)));
								} else {
									sb.append(Html.textToHTML(SelectList
											.getCatalogValueAccionistasSinPais(
													conn, flexColumn, value)));
								}
							} else {
								if (showPais) {
									sb.append(Html.textToHTML(SelectList
											.getCatalogValueAccionistasSinRFCConPais(
													conn, flexColumn, value)));
								} else {
									sb.append(Html.textToHTML(SelectList
											.getCatalogValueAccionistasSinRFC(
													conn, flexColumn, value)));
								}
							}
						} else {
							if ((stEditable == null) || (stEditable.equals(""))) {
								// fecha = new java.util.Date();
								// System.out.println(idflexTbl+" init catalogo value: "+sdf.format(fecha));
								sb.append(getCatalogValue(conn, flexColumn,
										value));
								// fecha = new java.util.Date();
								// System.out.println(idflexTbl+" Fin catalogo value: "+sdf.format(fecha));
							} else {
								sb.append(getCatalogValue(conn, flexColumn,
										value, liIdMetaRow, admVigDAO, modo));
							}
						}
						if (vacio) {
							sb.append("</td>");
						}

						// ECM 08 Septiembre 2016 - Contratos - Agregar Valores
						// "Celebrado entre partes".
						if (this.idflexTbl == 30
								&& flexColumn.ATRIBUTO3.equals("3")) {
							sb.append("<td " + rowSpan + cellClass + " align='"
									+ flexColumn.DES_ALIGN + "'>");
							sb.append("<pre class='wrapForPre' "
									+ "style='font-size: 11px;"
									+ " font-family:Verdana, Arial, Helvetica, sans-serif'>"
									+ contratosDAO.getCelebradoEntrePartes(
											liIdMetaRow, conn) + "</pre>");
							sb.append("</td>");
						}
						// System.out.println(idflexTbl+" Fin for "+new
						// java.util.Date());
					}
					// fecha = new java.util.Date();
					// System.out.println(idflexTbl+" Fin For: "+sdf.format(fecha));
					String key = set.getString("ID_META_ROW");

					String editImage = "<img src='" + request.getContextPath()
							+ "/img/icons/edit.png'>";
					String deleteImage = "<img src='"
							+ request.getContextPath()
							+ "/img/icons/delete.png'>";
					String copyImage = "<img src='" + request.getContextPath()
							+ "/img/icons/edit-copy.png'>";
					String detailImage = "<img src='"
							+ request.getContextPath()
							+ "/img/icons/List.png'>";

					// ECM 25 AGOSTO 2015
					if ((stEditable == null) || (stEditable.equals(""))) {
						// sb.append("<td align='center'><a onClick=\"closeForm();sleep(2000);setFlexTableId('"
						// + this.idflexTbl + "');loadFlexTableForm('" +
						// this.idflexTbl + "-" + key + "');\">" + editImage +
						// "</a></td>");

						String flexTbl = set.getString("ID_FLEX_TBL");

						if (atributo14 == null) {
							// sb.append("<td align='center'><a onClick=\"setAlterFlexTableId('"
							// + this.idflexTbl +
							// "');closeCurrentAndLoadFlexTabForm('" +
							// this.idflexTbl + "','" + this.idflexTbl + "-" +
							// key + "');\">" + editImage + "</a></td>");
							sb.append("<td align='center'><a onClick=\"setAlterFlexTableId('"
									+ this.idflexTbl
									+ "');editar_fn('"
									+ this.idflexTbl
									+ "','"
									+ this.idflexTbl
									+ "-"
									+ key
									+ "');\">"
									+ editImage
									+ "</a></td>");

						} else {
							// sb.append("<td align='center'><a onClick=\"setAlterFlexTableId('"
							// + flexTbl + "');closeCurrentAndLoadFlexTabForm('"
							// + this.idflexTbl + "','" + flexTbl + "-" + key +
							// "');\">" + editImage + "</a></td>");
							if(set.getString("NOM_FLEX").equals("Poderes")){
								sb.append("<td align='center'><a onClick=\"selectTabName('Poderes',',{ defaultTab: 5 }');go('/DerechoCorporativo/faces/jsp/captura/poderesGEC/indexPoderes.jsp?idEscritura=" + set.getInt("ID_ESCRITURA")+ 
										"&tipoEscritura=" + set.getString("IND_TIPO_ESCRITURA") + "&idEmp=" + set.getString("ID_EMPRESA") +"&idSeccion=28');\">" + editImage + "</a></td>");
							}
							else{
								sb.append("<td align='center'><a onClick=\"setAlterFlexTableId('"
										+ flexTbl
										+ "');editar_fn('"
										+ this.idflexTbl
										+ "','"
										+ flexTbl
										+ "-"
										+ key + "');\">" + editImage + "</a></td>");
							}
							// sb.append("<td align='center'><a onClick=\"closeCurrentAndLoadFlexTabForm('"
							// + this.idflexTbl + "','" + flexTbl + "-" + key +
							// "');\">" + editImage + "</a></td>");
						}
						// sb.append("<td align='center'><a onClick=\"if(confirm('&iquest;Est&aacute; seguro que desea borrar este registro?')) {closeForm();setFlexTableId('"
						// + this.idflexTbl + "');deleteFlexRow('" + key +
						// "');}\">" + deleteImage + "</a></td>");

						if (atributo14 == null) {
							sb.append("<td align='center'><a onClick=\" swal({ title: 'Confirmar', text: '&iquest;Est&aacute; seguro que desea borrar este registro?',type: 'warning', showCancelButton: true, confirmButtonColor: '#DD6B55', confirmButtonText: 'Si', closeOnConfirm: true }, function(){ closeForm();  setFlexTableId('"
									+ this.idflexTbl
									+ "'); deleteFlexRow('"
									+ key
									+ "','"
									+ flexTbl
									+ "-"
									+ key
									+ "'); });\">" + deleteImage + "</a></td>");
						}

						// Fusion
						if (idflexTbl == 34) {
							sb.append("<td align='center'><a onClick=\"openFusionCopy('"
									+ this.idflexTbl
									+ "','"
									+ key
									+ "');\">"
									+ copyImage + "</a></td>");
						}

						// Copiar en Aprobacion del Ejercicio Social
						if (idflexTbl == 23) {

							sb.append("<td align='center'><a onClick=\"openAESCopy('"
									+ this.idflexTbl
									+ "','"
									+ key
									+ "','"
									+ lstIdEmpresa
									+ "' );\">"
									+ copyImage
									+ "</a></td>");
						}
					} else {
						if (atributo11 != null && atributo11.equals("DETALLE")) {
							if (atributo14 == null) {
								sb.append("<td align='center'><a onClick=\"openFlexTableDetail('"
										+ this.idflexTbl
										+ "','"
										+ key
										+ "');\">" + detailImage + "</a></td>");
							} else {
								if(set.getString("NOM_FLEX").equals("Poderes")){
									sb.append("<td align='center'><a onClick=\"window.open('/DerechoCorporativo/faces/jsp/consulta/poderesGEC/consultaPoderesGenerales/Detalle.jsp?ID_Escritura=" + set.getInt("ID_ESCRITURA")
											+"', 'name','height=450,width=800,toolbar=0,menubar=0,location=0,status=0,scrollbars=1,resizable=1');\">" 
											+ detailImage + "</a></td>");
								}
								else{
									String flexTbl = set.getString("ID_FLEX_TBL");
									sb.append("<td align='center'><a onClick=\"openFlexTableDetail('"
											+ flexTbl
											+ "','"
											+ key
											+ "');\">"
											+ detailImage + "</a></td>");
								}
							}
						}
						// sb.append("<td align='center'><a onClick=\"setFlexTableId('"
						// + this.idflexTbl + "');loadFlexTableForm('" +
						// this.idflexTbl + "-" + key + "');\">" + "" +
						// "</a></td>");
						// sb.append("<td align='center'><a onClick=\"if(confirm('&iquest;Est&aacute; seguro que desea borrar este registro?')) {setFlexTableId('"
						// + this.idflexTbl + "');deleteFlexRow('" + key +
						// "');}\">" + "" + "</a></td>");
					}

					sb.append("</tr>");

					// System.out.println(idflexTbl+" - "+key+": "+new
					// java.util.Date()+" Fin While");
				}
				// fecha = new java.util.Date();
				// System.out.println(idflexTbl+" Fin While: "+sdf.format(fecha));
				if (showTotalsRow) {

					sb.append("<tr><td colspan='" + (flexColumns.size())
							+ "'></td></tr>");

					sb.append("<tr>");
					int i = 0;
					for (FlexColumn flexColumn : flexColumns) {
						// Nava 20/12/2016
						// if(flexColumn.NOM_FLEX_COLUM.equals("Socio")||flexColumn.NOM_FLEX_COLUM.equals("Accionista")){
						if (isAC == 0 && i == 1) {
							sb.append("<th></th><th class='tableHeader' align='right'>Total:&nbsp;&nbsp;</th>");
						} else {

							// sb.append("<th>");

							if (flexColumn.DES_TOTALS != null
									&& flexColumn.DES_TOTALS.equals("TOTAL")) {
								sb.append("<th ");
								Double value = totalsMap
										.get(flexColumn.COD_FLEX_COLUM);

								/*
								 * JJAQ 15/02/2017 Se comenta porque no debe de
								 * multiplicar en el total
								 * if(flexColumn.ID_FLEX_COLUM == 24 ||
								 * flexColumn.ID_FLEX_COLUM == 25) {
								 * 
								 * double mult = EmpresaValores.
								 * getValorTeoricoNominalMultiplicador
								 * (lstIdEmpresa); value *= mult; }
								 */

								if (flexColumn.DES_FORMAT != null
										&& flexColumn.DES_FORMAT
												.equals("NUMBER")) {
									// Nava 20/12/2016
									sb.append(" align='right'>");
									sb.append(NumberFormatter.conComas(value));

								} else if (flexColumn.DES_FORMAT != null
										&& flexColumn.DES_FORMAT
												.equals("INTEGER")) {
									// Nava 20/12/2016
									sb.append(" align='right'>");
									if ((flexColumn.NOM_FLEX_COLUM
											.contains("valor")
											|| flexColumn.NOM_FLEX_COLUM
													.contains("Valor")
											|| flexColumn.NOM_FLEX_COLUM
													.contains("Value")
											|| flexColumn.NOM_FLEX_COLUM
													.contains("value")
											|| flexColumn.NOM_FLEX_COLUM
													.contains("Participations") || flexColumn.NOM_FLEX_COLUM
												.contains("participations"))) {
										sb.append("$ "
												+ NumberFormatter.conComas(
														value, 2));
									} else {
										sb.append(NumberFormatter.conComas(
												value, 0));
									}
									// sb.append(NumberFormatter.conComas(value,0));

								} else if (flexColumn.DES_FORMAT != null
										&& flexColumn.DES_FORMAT
												.equals("AMOUNT")) { // KAZ-NAVA-26Oct

									SessionBean sessionBean = (SessionBean) request
											.getSession().getAttribute(
													"sessionBean");

									// modo = sessionBean.getPaginaModo();
									// String moneda =
									// sessionBean.getIdCurrentEmpresaMoneda();
									// String moneda =
									// EmpresaValores.getMonedaECS(lstIdEmpresa);

									if (moneda == null) {

										// moneda = "";
										moneda = EmpresaValores
												.getMonedaECS(lstIdEmpresa);
									}

									sb.append(" align='right'>");
									// sb.append("$" +
									// NumberFormatter.conComas(value) + " " +
									// moneda); //KAZ-NAVA-26Oct
									sb.append("$"
											+ NumberFormatter.conComas(value));

								} else {

									if (flexColumn.NOM_FLEX_COLUM.equals("%"))
										sb.append("align='center'>");
									else
										sb.append("align='right'>");
									String sValue = Double.toString(value);

									if (sValue.startsWith("99.99")
											|| sValue.startsWith("100.00")
											|| sValue.startsWith("100.0")) {

										sb.append("100%");
									} else {

										sb.append(value);
									}
								}
							}

							sb.append("</th>");
						}
						i++;
					}

					sb.append("</tr>");
				}

				sb.append("</table>");

				if (moneda != null && !moneda.equals("")) {

					sb.append("<br><br>Valores en " + moneda);
				} else if (this.desFlex.equals("Estructura Capital Social")) {
					if (isAC == 0) {
						moneda = EmpresaValores.getMonedaECS(lstIdEmpresa);
						sb.append("<br><br>Valores en " + moneda);
					}

				}

				// ECM ADM Y VIG - Consulta - Agregar referencia de nota al pie
				// de los superindices.
				if (isConsulta && this.atributo13 != null
						&& this.atributo13.equals("ORDER_BY_CARGO")) {

					if (this.piSuperindice > 0) {
						sb.append("<pre class='refNotaPie' "
								// + "style='font-size: 11px;"
								// + " font-family:Verdana, Arial, Helvetica,
								// sans-serif'
								+ ">"
								+ "Suplencia:"
								+ admVigDAO.getReferenciaNotaPie(
										this.idflexTbl,
										Integer.parseInt(lstIdEmpresa))
								+ "</pre>");
					}
				}

			}
		} catch (Exception ex) {
			// TODO - Pendiente - Gestion de Excepciones
			ex.printStackTrace();
		} finally {
			conn.close();
			/*
			 * try { //set.close(); //psmt.close(); //conn.close(); } catch
			 * (SQLException e) { e.printStackTrace(); }
			 */
			// ConnectionWrapper.closeAll(set, psmt, conn);
		}
		log.info("\"javascript:getFormatDocumentum(this);\"");
		if (sb.toString().contains(
				"onkeyup=\"javascript:getFormatDocumentum(this);\"")) {
			log.info("si se encuentra");
		}

		return sb.toString();
	}

	public String toHTMLVertical(HttpServletRequest request) {

		StringBuilder sb = new StringBuilder();

		ConnectionWrapper conn = null;

		ResultSet set = null;
		PreparedStatement psmt = null;

		try {

			conn = new ConnectionWrapper();
			boolean reporte = false;
			flexColumns = FlexColumn.getFlexColumns(this.idflexTbl, conn,
					false, request, "0", this.specificFlexColumnsCond, false,
					reporte);

			sb.append("<table width='" + atributo3 + "' align='" + atributo4
					+ "' class='flexTable' border='" + atributo5
					+ "' cellspacing='" + atributo6 + "' cellpadding='"
					+ atributo7 + "'>");
			sb.append("<tr><th>" + nomFlex + "</th>");
			// sb.append("<tr><th colspan='"+ flexColumns.size()
			// +"' class='tableHeaderBig'>"+ this.getFlexTableName(conn) +
			// "</th></tr>");

			String newImage = "<img src='" + request.getContextPath()
					+ "/img/icons/new.png'>";

			// ECM 27 Agosto 2015
			if ((stEditable == null) || (stEditable.equals(""))) {

				// sb.append("<td align='center' colspan='2'><a onClick=\"closeForm();sleep(1500);setFlexTableId('"
				// + this.idflexTbl + "');loadFlexTableForm('" + this.idflexTbl
				// + "-0" + "');\">" + newImage + "</a></td>");
				// sb.append("<td align='center' colspan='2'><a onClick=\"closeForm();sleep(2000);setFlexTableId('"
				// + this.idflexTbl + "');loadFlexTableForm('" + this.idflexTbl
				// + "-0" + "');\">" + newImage + "</a></td>");

				sb.append("<td align='center'><a onClick=\"closeCurrentAndLoadFlexTabForm('"
						+ this.idflexTbl
						+ "','"
						+ this.idflexTbl
						+ "-0"
						+ "');\">" + newImage + "</a></td>");

			} else {
				sb.append("<td align='center' colspan='2'><a onClick=\"closeForm();sleep(2000);setFlexTableId('"
						+ this.idflexTbl
						+ "');loadFlexTableForm('"
						+ this.idflexTbl + "-0" + "');\">" + "" + "</a></td>");
			}

			sb.append("</tr>");

			sb.append("<tr>");

			sb.append("<th class='tableHeader'>");
			sb.append("</th>");

			/*
			 * for (FlexColumn flexColumn : flexColumns) {
			 * 
			 * sb.append("<th class='tableHeader' width='" +
			 * flexColumn.atributo1 + "'>");
			 * 
			 * sb.append(flexColumn.NOM_FLEX_COLUM);
			 * 
			 * sb.append("</th>");
			 * 
			 * }
			 */
			// ECM 27 Agosto 2015
			if ((stEditable == null) || (stEditable.equals(""))) {
				sb.append("<th class='tableHeader' width='10%'>Editar</th>");
				sb.append("<th class='tableHeader' width='10%'>Borrar</th>");
			}
			sb.append("</tr>");

			String idEmpresa = FacesUtils.getSessionBean()
					.getIdCurrentEmpresa();

			String sqlCampos = "";
			if (this.idflexTbl == 23) {
				sqlCampos = "SELECT * FROM DERCORP_METATBL_TAB "// JAMS
																// 18/10/2017 se
																// ordena por
																// ejercicio
																// social para
																// aprobacion
						+ "WHERE ID_EMPRESA = " + idEmpresa
						+ " "
						+ "AND ID_FLEX_TBL = "
						+ this.idflexTbl
						+ " ORDER BY VAL_C5 DESC";
			} else {
				sqlCampos = "SELECT * FROM DERCORP_METATBL_TAB "// JAMS
																// 18/10/2017
						+ "WHERE ID_EMPRESA = " + idEmpresa
						+ " "
						+ "AND ID_FLEX_TBL = "
						+ this.idflexTbl
						+ " ORDER BY ID_META_ROW";
			}

			psmt = conn.prepareStatement(sqlCampos);
			set = psmt.executeQuery();

			boolean alterClass = false;
			String trStyle = "";

			while (set.next()) {

				alterClass = !alterClass;
				trStyle = (alterClass) ? "class=\"tableRow2\"" : "";

				sb.append("<tr " + trStyle + ">");
				sb.append("<td>");

				cantReg++;

				sb.append("<table width='100%' align='left' border='0' cellspacing='"
						+ atributo6 + "' cellpadding='" + atributo7 + "'>");

				for (FlexColumn flexColumn : flexColumns) {

					sb.append("<tr>");

					String value = set.getString(flexColumn.COD_FLEX_COLUM);

					// ECM 07 Septiembre 2015
					// Validar Consejo Asamblea Apoderados p/Poderes Generales y
					// Especiales

					if (this.idflexTbl == 17
							&& flexColumn.COD_FLEX_COLUM.equals("VAL_C1")
							&& value.equals("12340")) {
						this.lstFSConseAsamb = " style='display:block'";
						this.lstFSApoderados = " style='display:none'";
					} else if (this.idflexTbl == 17
							&& flexColumn.COD_FLEX_COLUM.equals("VAL_C1")
							&& value.equals("12341")) {
						this.lstFSConseAsamb = " style='display:block'";
						this.lstFSApoderados = " style='display:none'";

					} else if (this.idflexTbl == 17
							&& flexColumn.COD_FLEX_COLUM.equals("VAL_C1")
							&& value.equals("12342")) {
						this.lstFSConseAsamb = " style='display:none'";
						this.lstFSApoderados = " style='display:block'";
					}
					// END ECM

					if (value == null)
						value = "";

					sb.append("<td width='40%'>");
					if (this.idflexTbl == 17
							&& flexColumn.COD_FLEX_COLUM.equals("VAL_C2")) {
						sb.append("<div id='DIVC_" + this.idflexTbl + "_"
								+ flexColumn.COD_FLEX_COLUM + "_1' "
								+ this.lstFSApoderados + ">");
						sb.append(flexColumn.NOM_FLEX_COLUM + ": ");
					} else if (this.idflexTbl == 17
							&& flexColumn.COD_FLEX_COLUM.equals("VAL_C3")) {
						sb.append("<div id='DIVC_" + this.idflexTbl + "_"
								+ flexColumn.COD_FLEX_COLUM + "_1' "
								+ this.lstFSConseAsamb + ">");
						sb.append(flexColumn.NOM_FLEX_COLUM + ": ");

					}
					// else if(flexColumn.COD_FLEX_COLUM.equals("VAL_C149")){}
					else {
						sb.append(flexColumn.NOM_FLEX_COLUM + ": ");
					}

					sb.append("</div>");

					// sb.append(flexColumn.NOM_FLEX_COLUM + ":");
					sb.append("</td>");

					sb.append("<td width='60%'>");
					if (flexColumn.ID_CATALOGO == 0) {
						// ECM 07 Septiembre 2015
						if (this.idflexTbl == 17
								&& flexColumn.COD_FLEX_COLUM.equals("VAL_C2")) {
							sb.append("<div id='DIVC_" + this.idflexTbl + "_"
									+ flexColumn.COD_FLEX_COLUM + "_2'"
									+ this.lstFSApoderados + ">");
							sb.append(value);
							sb.append("</div>");
						} else if (this.idflexTbl == 17
								&& flexColumn.COD_FLEX_COLUM.equals("VAL_C3")) {
							sb.append("<div id='DIVC_" + this.idflexTbl + "_"
									+ flexColumn.COD_FLEX_COLUM + "_2'"
									+ this.lstFSConseAsamb + ">");
							sb.append(value);
							sb.append("</div>");

							// ECM 08 Septiembre 2015 modificado JJAQ 02 Agosto
							// 2016
						} else if (flexColumn.DES_TIPO_COLUM != null
								&& flexColumn.DES_TIPO_COLUM
										.equalsIgnoreCase("IMG")) {
							sb.append(Image.getIMG(flexColumn, value,
									this.idflexTbl));

						} else if (flexColumn.DES_TIPO_COLUM != null
								&& flexColumn.DES_TIPO_COLUM
										.equals(TYPE_TEXTNUM)) {
							if (value == null || value.equals("")) {
							} else
								sb.append(TextNumero.getTextNumeroDisabled(
										value, idEmpresa, conn));
						}

						// 06/03/2018 JAMS se agrega para imprimir tabla de
						// documentum en el repote html y excel
						// 21 || 28 || 23 || 20 || 22 || 27 || 29 || 30 || 31 ||
						// 32 || 33 || 34 || 35 || 41
						else if ((this.idflexTbl == 21 || this.idflexTbl == 28
								|| this.idflexTbl == 23 || this.idflexTbl == 20
								|| this.idflexTbl == 22 || this.idflexTbl == 27
								|| this.idflexTbl == 29 || this.idflexTbl == 30
								|| this.idflexTbl == 31 || this.idflexTbl == 32
								|| this.idflexTbl == 33 || this.idflexTbl == 34
								|| this.idflexTbl == 35 || this.idflexTbl == 41)
								&& flexColumn.COD_FLEX_COLUM.equals("VAL_C139")) {

							if (value != null && !value.isEmpty()) {

								sb.append(CustomTable
										.getCustomTableEjercicioReportePerzonalizado(
												flexColumn,
												Integer.parseInt(value), 30,
												false));
							}

						}else if ( this.idflexTbl == 27 && flexColumn.COD_FLEX_COLUM.equals("VAL_C151")){
							sb.append(CustomTable
									.getCustomTableAgregarOtros(conn,
											flexColumn,
											Integer.parseInt(value), 27,
											false));
							
						}else {
							sb.append(value);
						}
					} else {
						// ECM 07 Septiembre 2015
						if (this.idflexTbl == 17
								&& flexColumn.COD_FLEX_COLUM.equals("VAL_C2")) {
							sb.append("<div id='DIVC_" + this.idflexTbl + "_"
									+ flexColumn.COD_FLEX_COLUM + "_2'"
									+ this.lstFSApoderados + ">");
							sb.append(getCatalogValue(conn, flexColumn, value));
							sb.append("</div>");
						} else if (this.idflexTbl == 17
								&& flexColumn.COD_FLEX_COLUM.equals("VAL_C3")) {
							sb.append("<div id='DIVC_" + this.idflexTbl + "_"
									+ flexColumn.COD_FLEX_COLUM + "_2'"
									+ this.lstFSConseAsamb + ">");
							sb.append(getCatalogValue(conn, flexColumn, value));
							sb.append("</div>");
						} else {
							sb.append(getCatalogValue(conn, flexColumn, value));

						}
					}
					sb.append("</td>");

					sb.append("</tr>");
				}
				sb.append("</table>");

				sb.append("</td>");

				String key = set.getString("ID_META_ROW");

				String editImage = "<img src='" + request.getContextPath()
						+ "/img/icons/edit.png'>";
				String deleteImage = "<img src='" + request.getContextPath()
						+ "/img/icons/delete.png'>";

				// ECM 27 Agosto 2015
				if ((stEditable == null) || (stEditable.equals(""))) {
					// sb.append("<td align='center' valign='top'><a onClick=\"closeForm();sleep(2000);setFlexTableId('"
					// + this.idflexTbl + "');loadFlexTableForm('" +
					// this.idflexTbl + "-" + key + "');\">" + editImage +
					// "</a></td>");
					sb.append("<td align='center' valign='top'><a onClick=\"closeCurrentAndLoadFlexTabForm('"
							+ this.idflexTbl
							+ "','"
							+ this.idflexTbl
							+ "-"
							+ key + "');\">" + editImage + "</a></td>");

					// sb.append("<td align='center' valign='top'><a onClick=\"if(confirm('¿Esta seguro que desea borrar este registro?')) {closeForm();setFlexTableId('"
					// + this.idflexTbl + "');deleteFlexRow('" + key + "');}\">"
					// + deleteImage + "</a></td>");
					sb.append("<td align='center'><a onClick=\" swal({ title: 'Confirmar', text: '&iquest;Est&aacute; seguro que desea borrar este registro?',type: 'warning', showCancelButton: true, confirmButtonColor: '#DD6B55', confirmButtonText: 'Si', closeOnConfirm: true }, function(){ closeForm();  setFlexTableId('"
							+ this.idflexTbl
							+ "'); deleteFlexRow('"
							+ key
							+ "','"
							+ this.idflexTbl
							+ "-"
							+ key
							+ "'); });\">"
							+ deleteImage + "</a></td>");
				} else {
					if (this.filterRowsForReport) {

					} else {
						sb.append("<td align='center' valign='top'><a onClick=\"closeForm();sleep(2000);setFlexTableId('"
								+ this.idflexTbl
								+ "');loadFlexTableForm('"
								+ this.idflexTbl
								+ "-"
								+ key
								+ "');\">"
								+ ""
								+ "</a></td>");
						sb.append("<td align='center' valign='top'><a onClick=\"if(confirm('�Esta seguro que desea borrar este registro?')) {closeForm();setFlexTableId('"
								+ this.idflexTbl
								+ "');deleteFlexRow('"
								+ key
								+ "','"
								+ this.idflexTbl
								+ "-"
								+ key
								+ "');}\">" + "" + "</a></td>");
					}
				}
				sb.append("</tr>");

			}

			sb.append("</table>");

		} catch (Exception ex) {
			// TODO - Pendiente - Gestion de Excepciones
			ex.printStackTrace();
		} finally {
			ConnectionWrapper.closeAll(set, psmt, conn);
		}
		log.info("\"javascript:getFormatDocumentum(this);\"");
		if (sb.toString().contains(
				"onkeyup=\"javascript:getFormatDocumentum(this);\"")) {
			log.info("si se encuentra");
		}

		return sb.toString();
	}

	public void deleteFromControlMeta(String key) {
		ConnectionWrapper conn = null;

		PreparedStatement psmt = null;

		try {

			conn = new ConnectionWrapper();

			String idUser = FacesUtils.getSessionBean().getIdUser();
			String idEmpresa = FacesUtils.getSessionBean()
					.getIdCurrentEmpresa();

			if (!key.equals("0")) {

				String sqlCampos = "DELETE DERCORP_CONTROL_META_ROW "
						+ " WHERE " + "ID_USER = ?";

				psmt = conn.prepareStatement(sqlCampos);
				psmt.setString(1, idUser);
				psmt.executeUpdate();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			ConnectionWrapper.closeAll(psmt, conn);
		}

	}

	public String allowToOpenForm(String key, String globalFlexTableId,
			String alterFlexTableId) {

		ConnectionWrapper conn = null;

		ResultSet set = null;
		PreparedStatement psmt = null;
		PreparedStatement psmt2 = null;
		ResultSet set2 = null;
		PreparedStatement psmt3 = null;

		try {

			conn = new ConnectionWrapper();

			String idUser = FacesUtils.getSessionBean().getIdUser();
			String idEmpresa = FacesUtils.getSessionBean()
					.getIdCurrentEmpresa();

			if (!key.equals("0")) {

				String sqlCampos = "SELECT count(*) ROW_COUNT FROM DERCORP_CONTROL_META_ROW "
						+ "WHERE ID_EMPRESA = "
						+ idEmpresa
						+ " "
						+ "AND ID_META_ROW = " + key;

				psmt = conn.prepareStatement(sqlCampos);
				set = psmt.executeQuery();

				set.next();

				int rowCount = set.getInt("ROW_COUNT");

				if (rowCount == 0) {

					psmt2 = conn.prepareCallProcedure(
							"DERCORP_CONTROL_META_ROW_PKG.INSERT_PR",
							idEmpresa, key, idUser);
					psmt2.execute();

					return "OK";
				} else {
					String sqlQuery = "SELECT ID_USER FROM DERCORP_CONTROL_META_ROW"
							+ " WHERE ID_EMPRESA = "
							+ idEmpresa
							+ " "
							+ "AND ID_META_ROW = " + key;
					psmt3 = conn.prepareStatement(sqlQuery);
					set2 = psmt3.executeQuery();
					int idUserOcupaReforma = 0;
					while (set2.next()) {
						idUserOcupaReforma = set2.getInt(1);
					}

					return "NOK," + idUserOcupaReforma;
				}

			} else {

				return "OK";
			}

		} catch (Exception ex) {

			return "NOK-" + ex.toString();
		} finally {

			ConnectionWrapper.closeAll(set, psmt, psmt2, conn);
		}
	}

	public String toHTMLForm(String key, String globalFlexTableId,
			String alterFlexTableId) {
		int liHeight = 0;
		StringBuilder lsValign = new StringBuilder();

		String lstIdEmpresa = FacesUtils.getSessionBean().getIdCurrentEmpresa();
		StringBuilder sb = new StringBuilder();

		ConnectionWrapper conn = null;

		ResultSet set = null;
		PreparedStatement psmt = null;

		String psEditable = "";
		boolean reporte = false;

		try {
			psEditable = FacesUtils.getSessionBean().getEditCon();
		} catch (Exception ex) {
			// OK
		}

		try {
			conn = new ConnectionWrapper();
			String idEmpresa = FacesUtils.getSessionBean()
					.getIdCurrentEmpresa();

			if (!key.equals("0")) {

				String sqlCampos = "SELECT * FROM DERCORP_METATBL_TAB "
						+ "WHERE ID_EMPRESA = " + idEmpresa + " "
						+ "AND ID_FLEX_TBL = " + this.idflexTbl
						+ " AND ID_META_ROW = " + key;
log.info("SQL CAMPOS FLEXTABLE: "+sqlCampos);
				psmt = conn.prepareStatement(sqlCampos);
				set = psmt.executeQuery();

				set.next();

			} else {

			}
			String value = "";

			boolean addCollapsibleBehavior = false;
			int foundCollapseSection = 0;
			boolean addFinalTBody = false;
			String collapseSectionId = "";

			if ((psEditable == null) || (psEditable.equals(""))) {
				addCollapsibleBehavior = false;

			} else {
				addCollapsibleBehavior = true;
			}

			// ECM 11 ABRIL 2016 Consulta detalle Aprobacion de Ejercicio Fiscal
			// - Protocolizacion
			if (addCollapsibleBehavior == true
					&& ((this.idflexTbl != 0 && this.idflexTbl == 23) && ((set
							.getString("VAL_C101") != null && set.getString(
							"VAL_C101").equals("No")) && (set
							.getString("VAL_C102") != null && set.getString(
							"VAL_C102").equals("No"))))) {
				flexColumns = FlexColumn.getFlexColumns(this.idflexTbl, conn,
						true, null, lstIdEmpresa, this.specificFlexColumnsCond,
						true, this, stEditable, key, "APRO_ProIns", set);
			} else if (addCollapsibleBehavior == true
					&& ((this.idflexTbl != 0 && this.idflexTbl == 23) && (set
							.getString("VAL_C101") != null && set.getString(
							"VAL_C101").equals("No")))) {

				flexColumns = FlexColumn.getFlexColumns(this.idflexTbl, conn,
						true, null, lstIdEmpresa, this.specificFlexColumnsCond,
						true, this, stEditable, key, "APRO_PROTO", set);

			} else if (addCollapsibleBehavior == true
					&& ((this.idflexTbl != 0 && this.idflexTbl == 23) && (set
							.getString("VAL_C102") != null && set.getString(
							"VAL_C102").equals("No")))) {
				flexColumns = FlexColumn.getFlexColumns(this.idflexTbl, conn,
						true, null, lstIdEmpresa, this.specificFlexColumnsCond,
						true, this, stEditable, key, "APRO_INS", set);

				// ECM 11 ABRIL 2016 Consulta detalle Remormas, Escrituras y
				// Contrato - Protocolizacion
			} else if (addCollapsibleBehavior == true
					&& ((this.atributo9 != null && this.atributo9
							.equals("VAL_C1")) && ((set.getString("VAL_C81") != null && set
							.getString("VAL_C81").equals("No")) && (set
							.getString("VAL_C82") != null && (set.getString(
							"VAL_C82").equals("No") || set.getString("VAL_C82")
							.equalsIgnoreCase("N/A")))))) {
				flexColumns = FlexColumn.getFlexColumns(this.idflexTbl, conn,
						true, null, lstIdEmpresa, this.specificFlexColumnsCond,
						true, this, stEditable, key, "REF_PROTO_INS", set);
			} else if (addCollapsibleBehavior == true
					&& ((this.atributo9 != null && this.atributo9
							.equals("VAL_C1"))
							&& set.getString("VAL_C81").equals("No") || set
							.getString("VAL_C81").equalsIgnoreCase("N/A"))) {

				flexColumns = FlexColumn.getFlexColumns(this.idflexTbl, conn,
						true, null, lstIdEmpresa, this.specificFlexColumnsCond,
						true, this, stEditable, key, "REF_PROTO", set);

			} else if (addCollapsibleBehavior == true
					&& ((this.atributo9 != null && this.atributo9
							.equals("VAL_C1")) && (set.getString("VAL_C82") != null && (set
							.getString("VAL_C82").equals("No") || set
							.getString("VAL_C82").equalsIgnoreCase("N/A"))))) {
				flexColumns = FlexColumn.getFlexColumns(this.idflexTbl, conn,
						true, null, lstIdEmpresa, this.specificFlexColumnsCond,
						true, this, stEditable, key, "REF_INS", set);

				// /////////Poderes Generales Y Especiales - Protocolizacion
			} else if (addCollapsibleBehavior == true
					&& ((this.atributo9 != null && this.atributo9
							.equals("VAL_C9")) && (set.getString("VAL_C4")
							.equals("No") && set.getString("VAL_C5").equals(
							"No")))) {
				flexColumns = FlexColumn.getFlexColumns(this.idflexTbl, conn,
						true, null, lstIdEmpresa, this.specificFlexColumnsCond,
						true, this, stEditable, key, "PGPE", set);
			} else if (addCollapsibleBehavior == true
					&& ((this.atributo9 != null && this.atributo9
							.equals("VAL_C9")) && set.getString("VAL_C4")
							.equals("No"))) {
				flexColumns = FlexColumn.getFlexColumns(this.idflexTbl, conn,
						true, null, lstIdEmpresa, this.specificFlexColumnsCond,
						true, this, stEditable, key, "PG", set);

			} else if (addCollapsibleBehavior == true
					&& ((this.atributo9 != null && this.atributo9
							.equals("VAL_C9")) && set.getString("VAL_C5")
							.equals("No"))) {
				flexColumns = FlexColumn.getFlexColumns(this.idflexTbl, conn,
						true, null, lstIdEmpresa, this.specificFlexColumnsCond,
						true, this, stEditable, key, "PE", set);

				// ECM 12 Abril 2016 - Cuando Protocolizacion e Inscripci�n son
				// si en Poderes Reformas Escrituras y Contratos, y Status es
				// No.
			} else if (addCollapsibleBehavior == true
					&& ((this.atributo9 != null && this.atributo9
							.equals("VAL_C9")) && (set.getString("VAL_C76") != null && set
							.getString("VAL_C76").equals("No")))) {
				flexColumns = FlexColumn.getFlexColumns(this.idflexTbl, conn,
						lstIdEmpresa, this, key, set, "Stat_Pode");

			} else if (addCollapsibleBehavior == true
					&& ((this.idflexTbl != 0 && this.idflexTbl == 20) && (set
							.getString("VAL_C59") != null && set.getString(
							"VAL_C59").equals("No")))) {
				flexColumns = FlexColumn.getFlexColumns(this.idflexTbl, conn,
						lstIdEmpresa, this, key, set, "Stat_RTot");

			} else if (addCollapsibleBehavior == true
					&& ((this.idflexTbl != 0 && this.idflexTbl == 21) && (set
							.getString("VAL_C49") != null && set.getString(
							"VAL_C49").equals("No")))) {
				flexColumns = FlexColumn.getFlexColumns(this.idflexTbl, conn,
						lstIdEmpresa, this, key, set, "Stat_RPar");

			} else if (addCollapsibleBehavior == true
					&& ((this.idflexTbl != 0 && this.idflexTbl == 22) && (set
							.getString("VAL_C55") != null && set.getString(
							"VAL_C55").equals("No")))) {
				flexColumns = FlexColumn.getFlexColumns(this.idflexTbl, conn,
						lstIdEmpresa, this, key, set, "Stat_Tran");

			} else if (addCollapsibleBehavior == true
					&& ((this.idflexTbl != 0 && this.idflexTbl == 23) && (set
							.getString("VAL_C98") != null && set.getString(
							"VAL_C98").equals("No")))) {
				flexColumns = FlexColumn.getFlexColumns(this.idflexTbl, conn,
						lstIdEmpresa, this, key, set, "Stat_AES");

			} else if (addCollapsibleBehavior == true
					&& ((this.idflexTbl != 0 && this.idflexTbl == 27) && (set
							.getString("VAL_C17") != null && set.getString(
							"VAL_C17").equals("No")))) {
				flexColumns = FlexColumn.getFlexColumns(this.idflexTbl, conn,
						lstIdEmpresa, this, key, set, "Stat_EscOtros");

			} else if (addCollapsibleBehavior == true
					&& ((this.idflexTbl != 0 && this.idflexTbl == 28) && (set
							.getString("VAL_C26") != null && set.getString(
							"VAL_C26").equals("No")))) {
				flexColumns = FlexColumn.getFlexColumns(this.idflexTbl, conn,
						lstIdEmpresa, this, key, set, "Stat_ActOtros");

			} else if (addCollapsibleBehavior == true
					&& ((this.idflexTbl != 0 && this.idflexTbl == 29) && (set
							.getString("VAL_C45") != null && set.getString(
							"VAL_C45").equals("No")))) {
				flexColumns = FlexColumn.getFlexColumns(this.idflexTbl, conn,
						lstIdEmpresa, this, key, set, "Stat_AumCap");

			} else if (addCollapsibleBehavior == true
					&& ((this.idflexTbl != 0 && this.idflexTbl == 30) && (set
							.getString("VAL_C28") != null && set.getString(
							"VAL_C28").equals("No")))) {
				flexColumns = FlexColumn.getFlexColumns(this.idflexTbl, conn,
						lstIdEmpresa, this, key, set, "Stat_Cont");

			} else if (addCollapsibleBehavior == true
					&& ((this.idflexTbl != 0 && this.idflexTbl == 31) && (set
							.getString("VAL_C25") != null && set.getString(
							"VAL_C25").equals("No")))) {
				flexColumns = FlexColumn.getFlexColumns(this.idflexTbl, conn,
						lstIdEmpresa, this, key, set, "Stat_DecDiv");

			} else if (addCollapsibleBehavior == true
					&& ((this.idflexTbl != 0 && this.idflexTbl == 32) && (set
							.getString("VAL_C45") != null && set.getString(
							"VAL_C45").equals("No")))) {
				flexColumns = FlexColumn.getFlexColumns(this.idflexTbl, conn,
						lstIdEmpresa, this, key, set, "Stat_DisCap");

			} else if (addCollapsibleBehavior == true
					&& ((this.idflexTbl != 0 && this.idflexTbl == 33) && (set
							.getString("VAL_C47") != null && set.getString(
							"VAL_C47").equals("No")))) {
				flexColumns = FlexColumn.getFlexColumns(this.idflexTbl, conn,
						lstIdEmpresa, this, key, set, "Stat_Esc");

			} else if (addCollapsibleBehavior == true
					&& ((this.idflexTbl != 0 && this.idflexTbl == 34) && (set
							.getString("VAL_C68") != null && set.getString(
							"VAL_C68").equals("No")))) {
				flexColumns = FlexColumn.getFlexColumns(this.idflexTbl, conn,
						lstIdEmpresa, this, key, set, "Stat_Fusion");

			} else if (addCollapsibleBehavior == true
					&& ((this.idflexTbl != 0 && this.idflexTbl == 35) && (set
							.getString("VAL_C25") != null && set.getString(
							"VAL_C25").equals("No")))) {
				flexColumns = FlexColumn.getFlexColumns(this.idflexTbl, conn,
						lstIdEmpresa, this, key, set, "Stat_SesCon");

			} else if (addCollapsibleBehavior == true
					&& ((this.idflexTbl != 0 && this.idflexTbl == 41) && (set
							.getString("VAL_C25") != null && set.getString(
							"VAL_C25").equals("No")))) {
				flexColumns = FlexColumn.getFlexColumns(this.idflexTbl, conn,
						lstIdEmpresa, this, key, set, "Stat_SesCon");

				// ECM 14 Junio 2016 Consulta - Fusion - Ocultar aumento,
				// disminucion.
			} else if (addCollapsibleBehavior == true && this.idflexTbl == 34) {
				flexColumns = FlexColumn.getFlexColumns(this.idflexTbl, conn,
						false, null, lstIdEmpresa, "", false,
						Integer.parseInt(key));
			} else {
				// Muestre Todo en el Modulo de Consulta
				if (addCollapsibleBehavior == true) {
					flexColumns = FlexColumn.getFlexColumns(this.idflexTbl,
							conn, lstIdEmpresa, this, key, set, "Nothing");

				} else {
					flexColumns = FlexColumn
							.getFlexColumns(this.idflexTbl, conn, false, null,
									lstIdEmpresa, "", false, reporte);
				}

			}

			sb.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'>");

			String fieldIds = "";

			for (FlexColumn flexColumn : flexColumns) {

				String linkHideShowSection = "";

				if (addCollapsibleBehavior) {// ||
												// flexColumn.NOM_FLEX_COLUM.contains("Referencia Documentum")&&
												// flexColumn.DES_TIPO_COLUM.equals(TYPE_TITLE)))
												// {

					if (foundCollapseSection > 1
							&& flexColumn.DES_TR_CLASS != null
							&& flexColumn.DES_TR_CLASS
									.equals("tableHeaderAlfa2")) {

						sb.append("</tbody>");
						addFinalTBody = false;
					}

					if (foundCollapseSection == 1) {

						sb.append("<tbody id='" + collapseSectionId
								+ "' style='display:none'>");

						// System.out.println("collapseSectionId + <tbody id='"
						// + collapseSectionId + "' style='display:none'>");
						addFinalTBody = true;
					}

					if (foundCollapseSection >= 1) {

						foundCollapseSection++;
					}

					if (((atributo12 != null && atributo12.contains("*"
							+ flexColumn.NOM_FLEX_COLUM + "*")) || (atributo13 != null && atributo13
							.contains("*" + flexColumn.NOM_FLEX_COLUM + "*")))
							&& flexColumn.DES_TR_CLASS != null
							&& flexColumn.DES_TR_CLASS
									.equals("tableHeaderAlfa2")) {

						foundCollapseSection = 1;
						collapseSectionId = "tbody_"
								+ flexColumn.COD_FLEX_COLUM;

						String btnMinId = "btn_min_"
								+ flexColumn.COD_FLEX_COLUM;
						String btnMaxId = "btn_max_"
								+ flexColumn.COD_FLEX_COLUM;

						linkHideShowSection = " <label style='cursor:pointer;' onClick=\"hideShow('"
								+ collapseSectionId
								+ "','"
								+ btnMaxId
								+ "','"
								+ btnMinId
								+ "')\">"
								+ "<img id='"
								+ btnMinId
								+ "' src='"
								+ FacesUtils.getContextPath()
								+ "/img/fleca_arriba_32.png' style='display:none'>"
								// + "<img id='" + btnMinId + "' src='" +
								// FacesUtils.getContextPath() +
								// "/img/btn_min.png' style='display:none'>"
								+ "<img id='"
								+ btnMaxId
								+ "' src='"
								+ FacesUtils.getContextPath()
								+ "/img/fleca_abajo_32.png'>"
								// + "<img id='" + btnMaxId + "' src='" +
								// FacesUtils.getContextPath() +
								// "/img/btn_max.png'>"
								+ "</label>";
					}

				} else {
					// captura aplicar funcionamiento del boton +/-
					if (foundCollapseSection > 1
							&& flexColumn.DES_TR_CLASS != null
							&& flexColumn.DES_TR_CLASS
									.equals("tableHeaderAlfa2")) {

						sb.append("</tbody>");
						addFinalTBody = false;
					}

					if (foundCollapseSection == 1) {

						sb.append("<tbody id='" + collapseSectionId
								+ "' style='display:none'>");

						// System.out.println("collapseSectionId + <tbody id='"
						// + collapseSectionId + "' style='display:none'>");
						addFinalTBody = true;
					}

					if (foundCollapseSection >= 1) {

						foundCollapseSection++;
					}

					if (atributo13 != null
							&& atributo13.contains("*"
									+ flexColumn.NOM_FLEX_COLUM + "*")
							&& flexColumn.DES_TR_CLASS != null
							&& flexColumn.DES_TR_CLASS
									.equals("tableHeaderAlfa2")) {

						foundCollapseSection = 1;
						collapseSectionId = "tbody_"
								+ flexColumn.COD_FLEX_COLUM;

						String btnMinId = "btn_min_"
								+ flexColumn.COD_FLEX_COLUM;
						String btnMaxId = "btn_max_"
								+ flexColumn.COD_FLEX_COLUM;

						linkHideShowSection = " <label style='cursor:pointer;' onClick=\"hideShow('"
								+ collapseSectionId
								+ "','"
								+ btnMaxId
								+ "','"
								+ btnMinId
								+ "')\">"
								+ "<img id='"
								+ btnMinId
								+ "' src='"
								+ FacesUtils.getContextPath()
								+ "/img/fleca_arriba_32.png' style='display:none'>"
								// + "<img id='" + btnMinId + "' src='" +
								// FacesUtils.getContextPath() +
								// "/img/btn_min.png' style='display:none'>"
								+ "<img id='"
								+ btnMaxId
								+ "' src='"
								+ FacesUtils.getContextPath()
								+ "/img/fleca_abajo_32.png'>"
								// + "<img id='" + btnMaxId + "' src='" +
								// FacesUtils.getContextPath() +
								// "/img/btn_max.png'>"
								+ "</label>";
					}
				}
				// aqui termina el else de addCollapsibleBehavior
				String cssClass = "";
				if (flexColumn.DES_TR_CLASS != null) {

					cssClass = " class='" + flexColumn.DES_TR_CLASS + "'";
				}

				String valueAux = "";
				if (psEditable.equals("disabled") && this.idflexTbl == 23)
					valueAux = set.getString("VAL_C101");

				if (psEditable.equals("disabled")
						&& (flexColumn.COD_FLEX_COLUM.equals("VAL_C81")
								|| flexColumn.COD_FLEX_COLUM.equals("VAL_C82")
								|| flexColumn.COD_FLEX_COLUM.equals("VAL_C101")
								|| flexColumn.COD_FLEX_COLUM.equals("VAL_C102")
								|| flexColumn.COD_FLEX_COLUM.equals("VAL_C84") || flexColumn.COD_FLEX_COLUM
									.equals("VAL_C85"))) {
					sb.append("");
				} else if (!valueAux.equals("")
						&& valueAux.equals("No")
						&& (flexColumn.COD_FLEX_COLUM.equals("VAL_C100")
								|| flexColumn.COD_FLEX_COLUM.equals("VAL_C103")
								|| flexColumn.COD_FLEX_COLUM.equals("VAL_C114")
								|| flexColumn.COD_FLEX_COLUM.equals("VAL_C115")
								|| flexColumn.COD_FLEX_COLUM.equals("VAL_C116")
								|| flexColumn.COD_FLEX_COLUM.equals("VAL_C102") || flexColumn.COD_FLEX_COLUM
									.equals("VAL_C117"))) {
					sb.append("");
				} else {

					sb.append("<tr " + cssClass + ">");

					if (flexColumn.DES_TIPO_COLUM != null
							&& flexColumn.DES_TIPO_COLUM.equals(TYPE_TITLE)) {

						sb.append("<th colspan='2' align='left'><br>");
						// punto 24 cambios NAVA
						if (flexColumn.ID_FLEX_TBL == 28
								|| flexColumn.ID_FLEX_TBL == 23
								|| flexColumn.ID_FLEX_TBL == 29
								|| flexColumn.ID_FLEX_TBL == 31
								|| flexColumn.ID_FLEX_TBL == 32
								|| flexColumn.ID_FLEX_TBL == 33
								|| flexColumn.ID_FLEX_TBL == 34
								|| flexColumn.ID_FLEX_TBL == 21
								|| flexColumn.ID_FLEX_TBL == 20
								|| flexColumn.ID_FLEX_TBL == 35
								|| flexColumn.ID_FLEX_TBL == 22
								|| flexColumn.ID_FLEX_TBL == 30
								|| flexColumn.ID_FLEX_TBL == 27
								|| flexColumn.ID_FLEX_TBL == 41) {
							if (flexColumn.NOM_FLEX_COLUM.contains("Sem")) {
								sb.append("Instrumento" + " "
										+ linkHideShowSection);
							} else {
								sb.append(flexColumn.NOM_FLEX_COLUM + " "
										+ linkHideShowSection);
							}
						} else {
							sb.append(flexColumn.NOM_FLEX_COLUM + " "
									+ linkHideShowSection);
						}
						sb.append("<br><br></th>");

						continue;

					} else {

						// ECM 13 Julio 2016
						liHeight = flexColumn.IND_HEIGHT == 0 ? 0
								: flexColumn.IND_HEIGHT;

						if (flexColumn.DES_VALIGN == null)
							lsValign.append("");
						else
							lsValign.append(flexColumn.DES_VALIGN);

						// DIV 1
						// sb.append("<td>");
						sb.append("<td HEIGHT=" + liHeight + " VALIGN='"
								+ lsValign.toString() + "'>");

						if (flexColumn.NOM_FLEX_COLUM != null) {
							// ECM 05 ABRIL 2016 - Quitar validaci�n solicitada
							// por el usuario.
							if (psEditable.equals("disabled")
									&& (flexColumn.ATRIBUTO6 != null && flexColumn.ATRIBUTO6
											.equals("Ocultar"))) {
								sb.append("");
							} else if ((this.idflexTbl == 17 || this.idflexTbl == 18)
									&& flexColumn.COD_FLEX_COLUM
											.equals("VAL_C2")) {
								sb.append("<div id='DIVC_" + this.idflexTbl
										+ "_" + flexColumn.COD_FLEX_COLUM
										+ "_1' " + this.lstFSApoderados + ">");
								sb.append(flexColumn.NOM_FLEX_COLUM + ": ");
								sb.append("</div>");
							} else {
								boolean first = ((this.idflexTbl == 23) && (!(psEditable
										.equals("")) || psEditable == null));
								boolean second = (flexColumn.ID_FLEX_COLUM == 371
										|| flexColumn.ID_FLEX_COLUM == 1053
										|| flexColumn.ID_FLEX_COLUM == 385
										|| flexColumn.ID_FLEX_COLUM == 386
										|| flexColumn.ID_FLEX_COLUM == 387
										|| flexColumn.ID_FLEX_COLUM == 388
										|| flexColumn.ID_FLEX_COLUM == 389
										|| flexColumn.ID_FLEX_COLUM == 390
										|| flexColumn.ID_FLEX_COLUM == 392 || flexColumn.ID_FLEX_COLUM == 393);
								if (first && second) {
									sb.append("<div id='DIVC_" + this.idflexTbl
											+ "_" + flexColumn.COD_FLEX_COLUM
											+ "_1'>");
									sb.append("");
									sb.append("</div>");
								} else {
									EjercicioSocialDao ejerDao = new EjercicioSocialDao();
									if (this.idflexTbl == 23
											&& flexColumn.COD_FLEX_COLUM
													.equals("VAL_C139")
											&& first
											&& (ejerDao.findEjerciciosMetaRow(
													Integer.parseInt(key))
													.size() == 0)) {

										sb.append("<div id='DIVC_"
												+ this.idflexTbl + "_"
												+ flexColumn.COD_FLEX_COLUM
												+ "_1'>");
										sb.append("");// JAMS 12/
										sb.append("</div>");
									} else {
										sb.append("<div id='DIVC_"
												+ this.idflexTbl + "_"
												+ flexColumn.COD_FLEX_COLUM
												+ "_1'>");
										sb.append(flexColumn.NOM_FLEX_COLUM
												+ ": ");
										sb.append("</div>");
									}

								}
							}
						}

						sb.append("</td>");
					}

					// DIV 2
					sb.append("<td width=\"70%\">");

					if (!key.equals("0")) {

						value = set.getString(flexColumn.COD_FLEX_COLUM);
					}

					if ((this.idflexTbl == 17 || this.idflexTbl == 18)
							&& flexColumn.COD_FLEX_COLUM.equals("VAL_C1")
							&& value.equals("12342")) {
						this.lstFSApoderados = " style='display:block'";
					}

					if (flexColumn.DES_TIPO_COLUM == null) {
						flexColumn.DES_TIPO_COLUM = "";
					}

					// Revisar si esta condi
					if (flexColumn.DES_TIPO_COLUM.equals(TYPE_DATE)) {

						fieldIds += this.idflexTbl + "__"
								+ flexColumn.COD_FLEX_COLUM + "_FLEX_"
								+ flexColumn.ID_FLEX_COLUM + "|";

					} else {
						// if(flexColumn.COD_FLEX_COLUM.equals("VAL_C149")){System.out.println("Entro VAL_C149");}else{
						fieldIds += this.idflexTbl + "__"
								+ flexColumn.COD_FLEX_COLUM + "|";
						// }

					}

					if (value == null) {
						value = "";
					}
					// para mostrar solo el semaforo en status para todas las
					// reformas
					if (psEditable.equals("disabled")
							&& flexColumn.ATRIBUTO6 != null
							&& flexColumn.ATRIBUTO6.equals("Ocultar")) {
						sb.append("");
					} else if (flexColumn.ID_CATALOGO == 0) {
						if (flexColumn.DES_TIPO_COLUM.equals(TYPE_TEXTAREA)) {
							sb.append("<div id='DIVC_" + this.idflexTbl + "_"
									+ flexColumn.COD_FLEX_COLUM + "_2'>");
							// sb.append("<div id='DIVC_"+globalFlexTableId+"_"+flexColumn.COD_FLEX_COLUM+"_2'>");

							if ((psEditable == null) || (psEditable.equals(""))) {
								sb.append(TextArea.getTextArea(flexColumn,
										value, this.idflexTbl));
								// sb.append(TextArea.getTextArea(flexColumn,
								// value, globalFlexTableId));
							} else {
								log.info("TEXTO: "+value);
								sb.append("<pre class='wrapForPrePrint' style='font-size: 11px; font-family:Verdana, Arial, Helvetica, sans-serif'>"
										+ value + "</pre>");
							/*	sb.append("<textarea class='wrapForPre' style='font-size: 11px; font-family:Verdana, Arial, Helvetica, sans-serif'>"
										+ value + "</textarea>");*/
							}
							sb.append("</div>");

						} else if (flexColumn.DES_TIPO_COLUM.equals(TYPE_DATE)) {
							// ECM 07 Septiembre 2015
							String lstStyle = flexColumn.COD_FLEX_COLUM
									.equals("VAL_C3") ? this.lstFSConseAsamb
									: "";
							sb.append("<div id='DIVC_" + this.idflexTbl + "_"
									+ flexColumn.COD_FLEX_COLUM + "_2' "
									+ lstStyle + ">");
							if ((psEditable == null) || (psEditable.equals(""))) {
								sb.append(DateField.getDate(flexColumn, value,
										this.idflexTbl));
								// sb.append(DateField.getDate(flexColumn,
								// value,
								// this.idflexTbl,globalFlexTableId,alterFlexTableId));
							} else {
								sb.append(value);
							}
							sb.append("</div>");

						} else if (flexColumn.DES_TIPO_COLUM
								.equals(DISABLED_TEXT)) {

							if ((psEditable == null) || (psEditable.equals(""))) {
								sb.append(TextField.getTextFieldDisabled(
										flexColumn, value, this.idflexTbl));
								// sb.append(TextField.getTextFieldDisabled(flexColumn,
								// value, globalFlexTableId));
							} else {
								sb.append(value);
							}

							// ECM 04 Septiembre 2015
						} else if (flexColumn.DES_TIPO_COLUM
								.equals(TYPE_CHECKBOX)) {
							sb.append("<div id='DIVC_" + this.idflexTbl + "_"
									+ flexColumn.COD_FLEX_COLUM + "_2' >");

							if ((psEditable == null) || (psEditable.equals(""))) {
								sb.append(CheckBox.getCheckbox(flexColumn,
										value, stEditable, this.idflexTbl));
								// sb.append(CheckBox.getCheckbox(flexColumn,
								// value, stEditable, globalFlexTableId));

							} else if (this.idflexTbl == 23) {
								if (flexColumn.DES_GROUP != null
										&& flexColumn.DES_GROUP
												.equals("otrosAcuerdos"))
									sb.append(flexColumn.NOM_FLEX_COLUM);
								else
									sb.append((value.equals("Si") || value
											.equals("No")) ? "" : value);
							} else {
								sb.append((value.equals("Si") || value
										.equals("No")) ? value : "");
							}
							sb.append("</div>");

							// ECM 08 Septiembre 2015
						} else if (flexColumn.DES_TIPO_COLUM.equals(TYPE_IMG)) {
							sb.append(Image.getIMG(flexColumn, value,
									this.idflexTbl));
							// sb.append(Image.getIMGGlobal(flexColumn, value,
							// globalFlexTableId));

						} else if (flexColumn.DES_TIPO_COLUM
								.equals(TYPE_DOCUMENTUM)) {
							sb.append("<div id='DIVC_" + this.idflexTbl + "_"
									+ flexColumn.COD_FLEX_COLUM + "_2'>");
							if ((psEditable == null) || (psEditable.equals(""))) {
								sb.append(RefDocumentum.getHref(flexColumn,
										value, this.idflexTbl));
								// sb.append(RefDocumentum.getHref(flexColumn,
								// value, globalFlexTableId));
							} else {
								sb.append(RefDocumentum.getHrefConsulta(
										flexColumn, value, this.idflexTbl));
								// sb.append(RefDocumentum.getHrefConsulta(flexColumn,
								// value, globalFlexTableId));
							}
							sb.append("</div>");

						} else if (flexColumn.DES_TIPO_COLUM
								.equals(TYPE_TEXTNUM)) {
							sb.append("<div id='DIVC_" + this.idflexTbl + "_"
									+ flexColumn.COD_FLEX_COLUM + "_2'>");

							if ((psEditable == null) || (psEditable.equals(""))) {
								sb.append(TextNumero.getTextNumero(flexColumn,
										value, this.idflexTbl, lstIdEmpresa,
										conn));
								// sb.append(TextNumero.getTextNumero(flexColumn,
								// value, globalFlexTableId, lstIdEmpresa,
								// conn));
							} else {
								sb.append("<div style='text-align:right'>");
								// sb.append("<div>");
								if (value == null || value.equals("")) {
								} else
									sb.append(TextNumero.getTextNumeroDisabled(
											value, lstIdEmpresa, conn));
								// sb.append(value);
								sb.append("</div>");
							}
							sb.append("</div>");
							// ULR 09-03-2017 Se a�adio condicion para leer
							// nuevacustom table para agregar mas ejercicios
						} else if (flexColumn.DES_TIPO_COLUM != null
								&& flexColumn.DES_TIPO_COLUM
										.equals(TYPE_CUSTOM_TABLE)) {
							if ((psEditable == null) || (psEditable.equals(""))) {
								// ULR 26-04-2017 para administrar los
								// diferentes tipos de custom table
								switch (flexColumn.COD_FLEX_COLUM) {
								case "VAL_C139":
									sb.append(CustomTable
											.getCustomTableEjercicio(conn,
													flexColumn,
													Integer.parseInt(key),
													this.idflexTbl, true));
									break;
								case "VAL_C140":
									sb.append(CustomTable.getCustomTableAsunto(
											conn, flexColumn,
											Integer.parseInt(key),
											this.idflexTbl, true));
									break;
								case "VAL_C141":
									sb.append(CustomTable.getCustomTableAsunto(
											conn, flexColumn,
											Integer.parseInt(key),
											this.idflexTbl, true));
									break;
								case "VAL_C151":
									sb.append(CustomTable.getCustomTableAgregarOtros(
											conn, flexColumn,
											Integer.parseInt(key),
											this.idflexTbl, true));
									break;
								}
							} else {
								// ULR 26-04-2017 para administrar los
								// diferentes tipos de custom table
								switch (flexColumn.COD_FLEX_COLUM) {
								case "VAL_C139":
									sb.append(CustomTable
											.getCustomTableEjercicio(conn,
													flexColumn,
													Integer.parseInt(key),
													this.idflexTbl, false));
									break;
								case "VAL_C140":
									sb.append(CustomTable.getCustomTableAsunto(
											conn, flexColumn,
											Integer.parseInt(key),
											this.idflexTbl, false));
									break;
								case "VAL_C141":
									sb.append(CustomTable.getCustomTableAsunto(
											conn, flexColumn,
											Integer.parseInt(key),
											this.idflexTbl, false));
									break;
								case "VAL_C151":
									sb.append(CustomTable.getCustomTableAgregarOtros(
											conn, flexColumn,
											Integer.parseInt(key),
											this.idflexTbl, false));
									break;
								}
							}
						} else if (flexColumn.DES_TIPO_COLUM != null
								&& flexColumn.DES_TIPO_COLUM
										.equals(TYPE_HIDDEN)) {

						} else {
							sb.append("<div id='DIVC_" + this.idflexTbl + "_"
									+ flexColumn.COD_FLEX_COLUM + "_2'>");
							if ((psEditable == null) || (psEditable.equals(""))) {
								sb.append(TextField.getTextField(flexColumn,
										value, this.idflexTbl));
								// sb.append(TextField.getTextField(flexColumn,
								// value, globalFlexTableId));
							} else {
								sb.append(value);
							}
							sb.append("</div>");
						}

					} else if (flexColumn.DES_TIPO_COLUM.equals(TYPE_RADIO)
							|| flexColumn.DES_TIPO_COLUM.equals(TYPE_RADIO_V)) {

						if ((psEditable == null) || (psEditable.equals(""))) {
							sb.append(Radio.getRadioButtonList(conn,
									flexColumn, value, this.idflexTbl));
							// sb.append(Radio.getRadioButtonList(conn,
							// flexColumn, value, globalFlexTableId));
						} else {
							sb.append(Radio.getRadioButtonListValue(conn,
									flexColumn, value, this.idflexTbl));
							// sb.append(Radio.getRadioButtonListValue(conn,
							// flexColumn, value, globalFlexTableId));
						}
					} else if (flexColumn.DES_TIPO_COLUM.equals(TYPE_SELECT)) {
						sb.append("<div id='DIVC_" + this.idflexTbl + "_"
								+ flexColumn.COD_FLEX_COLUM + "_2'>");
						if ((psEditable == null) || (psEditable.equals(""))) {
							sb.append(SelectList.getSelectList(conn,
									flexColumn, value, this.idflexTbl));
							// sb.append(SelectList.getSelectList(conn,
							// flexColumn, value, globalFlexTableId));
						} else {
							sb.append(Radio.getRadioButtonListValue(conn,
									flexColumn, value, this.idflexTbl));
							// sb.append(Radio.getRadioButtonListValue(conn,
							// flexColumn, value, globalFlexTableId));
						}
						sb.append("</div>");

					} else if (flexColumn.DES_TIPO_COLUM
							.equals(TYPE_MULTISELECT)) {
						// ECM 07 Septiembre 2015
						String lstStyle = flexColumn.COD_FLEX_COLUM
								.equals("VAL_C2") ? this.lstFSApoderados : "";
						sb.append("<div id='DIVC_" + this.idflexTbl + "_"
								+ flexColumn.COD_FLEX_COLUM + "_2' " + lstStyle
								+ ">");
						if ((psEditable == null) || (psEditable.equals(""))) {

							// sb.append(MultiSelectList.getMultiSelectList(conn,
							// flexColumn, value, this.idflexTbl));
							// NAVA-Enero2016
							sb.append(MultiSelectList
									.getMultiSelectUnorderedList(conn,
											flexColumn, value, this.idflexTbl));
							// sb.append(MultiSelectList.getMultiSelectUnorderedList(conn,
							// flexColumn, value, globalFlexTableId));

						} else {
							sb.append(MultiSelectList
									.getMultiSelectListValuesUnorderedList(
											conn, flexColumn, value,
											this.idflexTbl));
							// sb.append(MultiSelectList.getMultiSelectListValuesUnorderedList(conn,
							// flexColumn, value, globalFlexTableId));
						}
						sb.append("</div>");

					} else if (flexColumn.DES_TIPO_COLUM
							.equals(TYPE_CUSTOM_TABLE)) {

						if ((psEditable == null) || (psEditable.equals(""))) {

							sb.append(CustomTable.getCustomTable(conn,
									flexColumn, value, this.idflexTbl, true));
							// sb.append(CustomTable.getCustomTable(conn,
							// flexColumn, value, globalFlexTableId, true));
						} else {

							sb.append(CustomTable.getCustomTable(conn,
									flexColumn, value, this.idflexTbl, false));
							// sb.append(CustomTable.getCustomTable(conn,
							// flexColumn, value, globalFlexTableId, false));
						}

					} else if (flexColumn.DES_TIPO_COLUM
							.equals(TYPE_SELECT_ACCIONISTAS)) {
						if ((psEditable == null) || (psEditable.equals(""))) {
							sb.append(SelectList.getSelectListAccionista(conn,
									flexColumn, value, this.idflexTbl));
						} else {
							sb.append(Radio.getRadioButtonListValue(conn,
									flexColumn, value, this.idflexTbl));
							// sb.append(Radio.getRadioButtonListValue(conn,
							// flexColumn, value,globalFlexTableId));
						}
					} else if (flexColumn.DES_TIPO_COLUM
							.equals(TYPE_SELECT_MONEDA)) {
						sb.append("<div id='DIVC_" + this.idflexTbl + "_"
								+ flexColumn.COD_FLEX_COLUM + "_2'>");
						if ((psEditable == null) || (psEditable.equals(""))) {
							sb.append(SelectList.getSelectListMoneda(conn,
									flexColumn, value, this.idflexTbl,
									lstIdEmpresa));
							// sb.append(SelectList.getSelectListMoneda(conn,
							// flexColumn, value, globalFlexTableId,
							// lstIdEmpresa));
						} else {
							sb.append(Radio.getRadioButtonListValue(conn,
									flexColumn, value, this.idflexTbl));
							// sb.append(Radio.getRadioButtonListValue(conn,
							// flexColumn, value, globalFlexTableId));
							// sb.append("&nbsp<b>"+TextNumero.getMoneda(lstIdEmpresa,
							// conn)+"</b>");
						}
						sb.append("</div>");
					}

					sb.append("</td>");
					sb.append("</tr>");
				}
			}

			if (addFinalTBody) {

				sb.append("</tbody>");
			}

			sb.append("<input type='hidden' name='idMetaRow_" + this.idflexTbl
					+ "' id='idMetaRow_" + this.idflexTbl + "' value='" + key
					+ "'>");
			// sb.append("<input type='hidden' name='idMetaRow_"+globalFlexTableId+"' id='idMetaRow_"+globalFlexTableId+"' value='"
			// + key + "'>");
			sb.append("<input type='hidden' name='fieldIds_" + this.idflexTbl
					+ "' id='fieldIds_" + this.idflexTbl + "' value='"
					+ fieldIds + "'>");
			// sb.append("<input type='hidden' name='fieldIds_"+globalFlexTableId+"' id='fieldIds_"+globalFlexTableId+"' value='"
			// + fieldIds + "'>");

		} catch (Exception ex) {

			// TODO - Pendiente - Gestion de Excepciones

			ex.printStackTrace();

		} finally {

			ConnectionWrapper.closeAll(set, psmt, conn);

		}

		sb.append("</table>");

		String finalHTML = sb.toString();

		if (globalFlexTableId != null) {
			finalHTML = finalHTML.replaceAll("DIVC_" + this.idflexTbl, "DIVC_"
					+ globalFlexTableId);
		}

		log.info("\"javascript:getFormatDocumentum(this);\"");
		if (finalHTML
				.contains("onkeyup=\"javascript:getFormatDocumentum(this);\"")) {

			finalHTML = finalHTML.replace(
					"onkeyup=\"javascript:getFormatDocumentum(this);\"",
					" class='mascaraDocumentum'");
			log.info("si se encuentra");
		}

		return finalHTML;

	}

	public String toFormScript(String key, String globalFlexTableId,
			String alterFlexTableId) {

		StringBuilder sb = new StringBuilder();

		ConnectionWrapper conn = null;

		try {

			String lstIdEmpresa = FacesUtils.getSessionBean()
					.getIdCurrentEmpresa();

			conn = new ConnectionWrapper();
			boolean reporte = false;
			flexColumns = FlexColumn.getFlexColumns(this.idflexTbl, conn,
					false, null, lstIdEmpresa, "", false, reporte);

			boolean iniSemReq = false;

			for (FlexColumn flexColumn : flexColumns) {

				if (flexColumn.DES_TIPO_COLUM != null
						&& flexColumn.DES_TIPO_COLUM.equals(TYPE_DATE)) {

					sb.append(DateField.getDateScriptOnly(flexColumn,
							this.idflexTbl));
					// sb.append(DateField.getDateScriptOnly(flexColumn,
					// this.idflexTbl,globalFlexTableId,alterFlexTableId));

				} else if (flexColumn.DES_TIPO_COLUM != null
						&& flexColumn.DES_TIPO_COLUM.equals(TYPE_CHECKBOX)) {
					// sb.append("IniSemReq();");
					iniSemReq = true;
				}

			}

			if (iniSemReq) {

				sb.append("IniSemReq();");
			}

		} catch (Exception ex) {
			// TODO - Pendiente - Gestion de Excepciones
			ex.printStackTrace();
		} finally {
			ConnectionWrapper.closeAll(null, null, conn);
		}

		return sb.toString();
	}

	public int toExcelHorizontal(XSSFSheet sheet, ConnectionWrapper conn,
			HttpServletRequest request, int rowIndex) {
		String moneda = null;
		rowIndex += 1;

		int colIndex = 3;

		ResultSet set = null;
		PreparedStatement psmt = null;

		String groupColumn = "";
		String groupColumnOrderBy = "";
		boolean isGrouped = false;
		boolean reporte = false;
		String nomFlex = "";
		try {

			SessionBean sessionBean = (SessionBean) request.getSession()
					.getAttribute("sessionBean");

			String lstIdEmpresa = sessionBean.getIdCurrentEmpresa();

			// flexColumns = FlexColumn.getFlexColumns(this.idflexTbl, conn,
			// false, request);JAMS CHECAR FLEXCOLUMNS
			// flexColumns = FlexColumn.getFlexColumns(this.idflexTbl, conn,
			// false, request, "0", this.specificFlexColumnsCond,
			// false,reporte);
			if ((stEditable == null) || (stEditable.equals(""))) {

				flexColumns = FlexColumn.getFlexColumns(this.idflexTbl, conn,
						true, request, "0", this.specificFlexColumnsCond, true,
						reporte);
				// isConsulta = false;
			} else {
				flexColumns = FlexColumn.getFlexColumnsExcel(this.idflexTbl,
						conn, false, request, "0",
						this.specificFlexColumnsCond, true, this.atributo13,
						reporte);
				// isConsulta = true;
			}
			// //////flexColumns =
			// FlexColumn.getSelectedFlexColumns(this.idflexTbl, conn, false,
			// request, this.specificFlexColumnsCond);

			// if(showTitle) {
			// sb.append("<tr><th colspan='"+ (flexColumns.size()+1) +"'>"+
			// nomFlex + "</th>");
			// }

			boolean showTotalsRow = false;
			Map<String, Double> totalsMap = new HashMap<String, Double>();

			for (FlexColumn flexColumn : flexColumns) {

				log.info("flexColumn " + flexColumn.NOM_FLEX_COLUM);
				nomFlex = flexColumn.NOM_FLEX_COLUM;
				nomFlex = nomFlex.replace("<big>", "");
				nomFlex = nomFlex.replace("<b>", "");
				nomFlex = nomFlex.replace("</b>", "");
				nomFlex = nomFlex.replace("</big>", "");
				nomFlex = nomFlex.replace("<br>", "");

				ExcelUtil.setCellValue(sheet, rowIndex, colIndex, nomFlex);
				ExcelUtil.setCellStyleBold(sheet, rowIndex, colIndex);

				colIndex++;

				if (flexColumn.DES_GROUP != null
						&& flexColumn.DES_GROUP.equals("GROUP")) {

					groupColumn = flexColumn.COD_FLEX_COLUM;
					groupColumnOrderBy = "OUTER_Q." + flexColumn.COD_FLEX_COLUM
							+ ", ";
					isGrouped = true;
				}

				if (flexColumn.DES_TOTALS != null
						&& flexColumn.DES_TOTALS.equals("TOTAL")) {

					showTotalsRow = true;
					totalsMap.put(flexColumn.COD_FLEX_COLUM, new Double(0));
				}
			}

			rowIndex++;

			colIndex = 3;

			/*
			 * if(this.idflexTbl == EstructuraCapitalSocial.ECS_FLEX_TABLE_ID){
			 * 
			 * sb.append(EstructuraCapitalSocial.getTableSubHeader(flexColumns,
			 * conn, lstIdEmpresa, showEditableCombosECS)); }
			 */

			String sqlCampos = null;

			if (isGrouped) {
				sqlCampos = "SELECT OUTER_Q.*, (select count(*) FROM DERCORP_METATBL_TAB INNER_Q  WHERE INNER_Q.ID_EMPRESA = OUTER_Q.ID_EMPRESA AND INNER_Q.ID_FLEX_TBL = OUTER_Q.ID_FLEX_TBL AND INNER_Q."
						+ groupColumn
						+ " = OUTER_Q."
						+ groupColumn
						+ ") COUNT_GRP "
						+ "FROM DERCORP_METATBL_TAB OUTER_Q "
						+ "WHERE OUTER_Q.ID_EMPRESA = "
						+ lstIdEmpresa
						+ " "
						+ "AND OUTER_Q.ID_FLEX_TBL = "
						+ this.idflexTbl
						+ " ORDER BY "
						+ groupColumnOrderBy
						+ "OUTER_Q.ID_META_ROW";
			} else {
				// ECM 24 Septiembre 2015
				if (this.atributo9 != null) {
					sqlCampos = "SELECT * " + "FROM DERCORP_METATBL_TAB "
							+ "WHERE ID_EMPRESA = " + lstIdEmpresa + " "
							+ "AND ID_FLEX_TBL = " + this.idflexTbl
							+ "  ORDER BY TO_DATE(NVL(" + this.atributo9
							+ ", SYSDATE),'dd/mm/yyyy') DESC";
				} else if (this.atributo13 != null
						&& this.atributo13.equals("ORDER_BY_CARGO")) {

					sqlCampos = "SELECT * "
							+ "FROM DERCORP_METATBL_TAB META "
							+ "WHERE ID_EMPRESA = "
							+ lstIdEmpresa
							+ " "
							+ "AND VAL_C15 IS NOT NULL "// JAMS 29/08/2017
							//+ "AND ( (VAL_C4 IS NULL ) OR (VAL_C7 IS NULL ) ) "// JJAQ 11/01/2019 Se comenta para el punto prioritario numero 16
																				
							+ "AND ID_FLEX_TBL = "
							+ this.idflexTbl
							+ " ORDER BY TO_NUMBER(META.VAL_C15), DERCORP_CATALOGS_PKG.GET_FUNCIONARIOS_FN(META.VAL_C1)";

				} else {
					sqlCampos = "SELECT * " + "FROM DERCORP_METATBL_TAB "
							+ "WHERE ID_EMPRESA = " + lstIdEmpresa + " "
							+ "AND ID_FLEX_TBL = " + this.idflexTbl
							+ " ORDER BY ID_META_ROW";

				}
				if (filterRowsForReport) {
					SessionBean sessionBeanRep = (SessionBean) request
							.getSession().getAttribute("sessionBean");
					Map<String, String> map = sessionBeanRep.getDynamicParams();
					String key = "";
					String value = "";
					FlexColumn fc = null;
					String code = "";

					for (Entry<String, String> entry : map.entrySet()) {

						key = entry.getKey();
						value = entry.getValue();

						// FlexFields
						if (Integer.parseInt(key) >= 10000) {

							int idFlexColum = Integer.parseInt(key) / 10000;

							fc = FlexColumn.getFlexColumn(idFlexColum, conn);

							code = fc.COD_FLEX_COLUM;

							if (this.idflexTbl == fc.ID_FLEX_TBL) {

								sqlCampos = sqlCampos
										.replace(
												"ORDER BY",
												"AND APP_COMMON_PKG.SIN_ACENTOS_FN(UPPER(',"
														+ value
														+ ",')) LIKE APP_COMMON_PKG.SIN_ACENTOS_FN(UPPER('%,' || "
														+ code
														+ " || ',%')) ORDER BY");
							}
						}
					}
				}
			}
			log.info("sqlCampos: " + sqlCampos);
			psmt = conn.prepareStatement(sqlCampos);
			set = psmt.executeQuery();

			boolean alterClass = false;
			String trStyle = "";

			String lastValue = "UNDEFINED";

			while (set.next()) {

				colIndex = 3;

				/***
				 * String lstAllColumnValues = ""; for (FlexColumn flexColumn :
				 * flexColumns) {
				 * 
				 * String value = set.getString(flexColumn.COD_FLEX_COLUM); if
				 * (flexColumn.ID_CATALOGO != 0) { value = getCatalogValue(conn,
				 * flexColumn, value); } lstAllColumnValues = lstAllColumnValues
				 * + " " + value; }
				 * 
				 * if(lstAllColumnValues != null && filter != null) { Pattern p
				 * = Pattern.compile("("+filter+")"); Matcher m =
				 * p.matcher(lstAllColumnValues); while (m.find()) { String
				 * matched = m.group(1); showThisRecord = true; } }
				 * 
				 * 
				 * if(!showThisRecord) {
				 * 
				 * continue; }
				 */

				String cellClass = "";
				String value = "";
				for (FlexColumn flexColumn : flexColumns) {

					value = set.getString(flexColumn.COD_FLEX_COLUM);

					// String rowSpan = "";

					// cellClass = "";

					if (flexColumn.DES_GROUP != null
							&& flexColumn.DES_GROUP.equals("GROUP")) {

						// rowSpan = " rowspan='" + set.getString("COUNT_GRP") +
						// "'";

						// cellClass = " class='tableHeaderAlfa'";

						if (lastValue == null) {

							lastValue = "";
						}

						if (lastValue.equals(value)) {
							colIndex++;
							continue;
						}

						lastValue = value;

					}

					if (flexColumn.DES_TOTALS != null
							&& flexColumn.DES_TOTALS.equals("TOTAL")) {

						Double currentTotal = totalsMap
								.get(flexColumn.COD_FLEX_COLUM);

						if (value != null
								&& NumbersUtil.isNumeric(value
										.replace(",", "."))) {

							currentTotal += Double.parseDouble(value.replace(
									",", "."));

							totalsMap.put(flexColumn.COD_FLEX_COLUM,
									currentTotal);
						}
					}

					// sb.append("<td " + rowSpan + cellClass+ " align='" +
					// flexColumn.DES_ALIGN + "'>");

					if (value == null)
						value = "";

					if (flexColumn.ID_CATALOGO == 0) {
						if (flexColumn.DES_FORMAT != null
								&& flexColumn.DES_FORMAT.equals("NUMBER")) {

							value = NumberFormatter.conComas(value);

						} else if (flexColumn.DES_FORMAT != null
								&& flexColumn.DES_FORMAT.equals("INTEGER")) {
							value = NumberFormatter.conComas(value, 0);

						} else if (flexColumn.DES_FORMAT != null
								&& flexColumn.DES_FORMAT.equals("AMOUNT")) { // KAZ-NAVA-26Oct
							value = "$ " + NumberFormatter.conComas(value, 2); // KAZ-NAVA-26Oct
							if (moneda == null) {

								// moneda = "";
								moneda = EmpresaValores
										.getMonedaECS(lstIdEmpresa);
							}

							// ECM 09 Septiembre 2015
						} else if (flexColumn.DES_TIPO_COLUM != null
								&& flexColumn.DES_TIPO_COLUM.equals(TYPE_IMG)) {

							if (value.contains("green")) {

								value = "VERDE";

							} else if (value.contains("yellow")) {

								value = "AMARILLO";

							} else {

								value = "ROJO";
							}

							// value = Image.getIMG(flexColumn, value,
							// this.idflexTbl);

						} else if (flexColumn.DES_TIPO_COLUM != null
								&& flexColumn.DES_TIPO_COLUM
										.equals(TYPE_TEXTNUM)) {
							if (value == null || value.equals("")) {
							} else
								value = TextNumero.getTextNumeroDisabled(value,
										lstIdEmpresa, conn);
							value = value.replace("<big>", "");
							value = value.replace("<b>", "");
							value = value.replace("</b>", "");
							value = value.replace("</big>", "");
							value = value.replace("<br>", "");
							value = value.replace("&nbsp", "");

						} else {
							value = Html.textToHTML(value);
							value = value.replace("<br>", "\n");
							value = value.replace("&quot;", "\"");
							value = value.replace("&amp;", "&");

						}
						// ECM 21 Septiembre 2015
					} else if (flexColumn.DES_TIPO_COLUM != null
							&& flexColumn.DES_TIPO_COLUM
									.equals(TYPE_SELECT_ACCIONISTAS)) {

						// value =
						// Html.textToHTML(SelectList.getCatalogValueAccionistas(conn,
						// flexColumn, value));
						System.out.println("11");
						value = Html.textToHTML(SelectList
								.getCatalogValueAccionistasSinRFC(conn,
										flexColumn, value));
						value = value.replace("<br>", "\n");
						value = value.replace("&quot;", "\"");
						value = value.replace("&amp;", "&");

					} else {
						value = getCatalogValue(conn, flexColumn, value);
						// System.out.println("flex value: "+value);
					}

					if (value.contains("@televisa")) {

						value = value.substring(0, value.indexOf("@"))
								+ "@televisa.com.mx";

					}
					if (value.contains("&#39;")) {

						value = value.replace("&#39;", "'");

					}

					// 06/03/2018 JAMS se agrega para imprimir tabla de
					// documentum en el repote de excel
					// 21 || 28 || 23 || 20 || 22 || 27 || 29 || 30 || 31 || 32
					// || 33 || 34 || 35 || 41
					if ((this.idflexTbl == 21 || this.idflexTbl == 28
							|| this.idflexTbl == 23 || this.idflexTbl == 20
							|| this.idflexTbl == 22 || this.idflexTbl == 27
							|| this.idflexTbl == 29 || this.idflexTbl == 30
							|| this.idflexTbl == 31 || this.idflexTbl == 32
							|| this.idflexTbl == 33 || this.idflexTbl == 34
							|| this.idflexTbl == 35 || this.idflexTbl == 41)
							&& flexColumn.NOM_FLEX_COLUM
									.equals("Agregar mas documentos")) {

						EjercicioSocialDao ejerDao = new EjercicioSocialDao();
						ArrayList<EjercicioBean> listEjercicio = (ArrayList<EjercicioBean>) ejerDao
								.findEjerciciosMetaRow(Integer.parseInt(value));
						int i = 0;
						int rowIndexTemp = rowIndex;
						int colIndexTemp = colIndex;
						String tabla = "";
						if (listEjercicio.size() > 0) {

							tabla += "                 Nombre Documento                 |          Fecha Documento           |              Fecha Recibido            |            Doc. Digitalizado        \r\n";

							for (EjercicioBean beanEjercicio : listEjercicio) {

								beanEjercicio.setTipoDoc(beanEjercicio
										.getTipoDoc() == null ? ""
										: beanEjercicio.getTipoDoc());
								beanEjercicio.setFechaDocumentum(beanEjercicio
										.getFechaDocumentum() == null ? ""
										: beanEjercicio.getFechaDocumentum());
								beanEjercicio.setFechaEntrega(beanEjercicio
										.getFechaEntrega() == null ? ""
										: beanEjercicio.getFechaEntrega());
								beanEjercicio.setNoDocumentum(beanEjercicio
										.getNoDocumentum() == null ? ""
										: beanEjercicio.getNoDocumentum());
								int tamanioNombre = beanEjercicio.getTipoDoc()
										.length();
								int tamanioFechaDdocumento = beanEjercicio
										.getFechaDocumentum().length();
								int tamanioFechaRecibido = beanEjercicio
										.getFechaEntrega().length();
								int tamanioDocDigitalizado = beanEjercicio
										.getNoDocumentum().length();
								int espacioNombre = 70 - tamanioNombre;
								int espacioFechaDdocumento = 50 - tamanioFechaDdocumento;
								int espacioFechaRecibido = 50 - tamanioFechaRecibido;
								int espacioDocDigitalizado = 70 - tamanioDocDigitalizado;
								String nombreDocumento = "    "
										+ String.format("%-" + espacioNombre
												+ "s",
												beanEjercicio.getTipoDoc());
								String fechaDocumento = "    "
										+ String.format("%-"
												+ espacioFechaDdocumento + "s",
												beanEjercicio
														.getFechaDocumentum());
								String fechaRecibido = "    "
										+ String.format("%-"
												+ espacioFechaRecibido + "s",
												beanEjercicio.getFechaEntrega());
								String docDigitalizado = "    "
										+ String.format("%-"
												+ espacioDocDigitalizado + "s",
												beanEjercicio.getNoDocumentum());

								tabla += nombreDocumento + "|" + fechaDocumento
										+ "|" + fechaRecibido + "|"
										+ docDigitalizado + "\r\n";

							}

						}

						// ExcelUtil.setMergeRegion(sheet,rowIndexTemp,rowIndexTemp,colIndexTemp,rowIndex);

						ExcelUtil
								.setCellValue(sheet, rowIndex, colIndex, tabla);

					} else {
						ExcelUtil
								.setCellValue(sheet, rowIndex, colIndex, value);
					}

					colIndex++;

				}

				rowIndex++;

			}

			colIndex = 3;

			if (showTotalsRow) {

				String strValue = "";
				Double value = 0.0;
				for (FlexColumn flexColumn : flexColumns) {

					if (flexColumn.DES_TOTALS != null
							&& flexColumn.DES_TOTALS.equals("TOTAL")) {

						value = totalsMap.get(flexColumn.COD_FLEX_COLUM);

						if (flexColumn.DES_FORMAT != null
								&& flexColumn.DES_FORMAT.equals("NUMBER")) {

							strValue = NumberFormatter.conComas(value);

						} else if (flexColumn.DES_FORMAT != null
								&& flexColumn.DES_FORMAT.equals("INTEGER")) {
							strValue = NumberFormatter.conComas(value, 0);

						} else if (flexColumn.DES_FORMAT != null
								&& flexColumn.DES_FORMAT.equals("AMOUNT")) { // KAZ-NAVA-26Oct
							strValue = "$ "
									+ NumberFormatter.conComas(value, 2); // KAZ-NAVA-26Oct

							if (moneda == null) {

								// moneda = "";
								moneda = EmpresaValores
										.getMonedaECS(lstIdEmpresa);
							}

						} else {

							strValue = Double.toString(value);
						}
					}

					if (moneda != null && !moneda.equals("")) {
						rowIndex++;
						ExcelUtil.setCellValue(sheet, rowIndex, 0,
								"Valores en " + moneda);
						rowIndex--;

					}
					ExcelUtil.setCellValue(sheet, rowIndex, colIndex, strValue);

					colIndex++;

				}

			}

		} catch (Exception ex) {
			// TODO - Pendiente - Gestion de Excepciones
			ex.printStackTrace();
		} finally {
			// ConnectionWrapper.closeAll(set, psmt);
			try {
				set.close();
				psmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return rowIndex;
	}

	/**
	 * OPTIMIZAR
	 * 
	 * @throws Exception
	 */
	public String getCatalogValue(ConnectionWrapper connect,
			FlexColumn flexColumn, String value) throws Exception {

		if (flexColumn.DES_TIPO_COLUM.equals(TYPE_MULTISELECT)) {

			if (flexColumn.ID_CATALOGO == 6969) {// JJAQ 19/02/2018 se agrega if
													// para el catalogo 6969
				return getMultiCatalogValue6969(connect, flexColumn, value);
			} else {
				return getMultiCatalogValue(connect, flexColumn, value);
			}

		}

		// StringBuilder sb = new StringBuilder();

		Catalogo cat = new Catalogo(flexColumn.ID_CATALOGO);

		if (this.atributo13 != null && atributo13.equals("ORDER_BY_CARGO")) {
			for (CatalogoValor catElem : cat.getElementosAdminVig(connect,
					Integer.parseInt(value.equals("") ? "0" : value))) {

				if (Integer.toString(catElem.idCatalogoValor).equals(value)) {
					return catElem.nomCatVal;
				}
			}
		} else {
			for (CatalogoValor catElem : cat.getElementos(connect)) {

				if (Integer.toString(catElem.idCatalogoValor).equals(value)) {
					return catElem.nomCatVal;
				}
			}
		}

		return "";
	}

	public String getNomCatVal(CatalogoValor catElem, FlexColumn flexColumn,
			String modo) {
		if (this.stEditable.equals("disabled") && this.stEditable != null) {
			/*JJAQ 27-12-2018 SE comenta porque no quieren que aparezca la palabra miembro,punto numero 4 de los prioritarios
			if ((modo.equals("") || modo.equals("PRINT") || modo
					.equals("PRINT_ALL"))
					&& catElem.idCatalogo == 11
					&& (catElem.nomCatVal.contains("Miembro"))) {
				return "Miembro";
			} else {
				return catElem.nomCatVal;
			}*/
			return catElem.nomCatVal;
		} else
			return catElem.nomCatVal;
	}

	public String getCatalogValue(ConnectionWrapper connect,
			FlexColumn flexColumn, String value, int tiIdMetaRow,
			AdmVigDAO tuAdmVigDAO, String modo) {

		int liSuperindice = 0;
		String lsSuperindice;

		if (flexColumn.DES_TIPO_COLUM.equals(TYPE_MULTISELECT)) {
			return getMultiCatalogValue(connect, flexColumn, value);
		}

		// StringBuilder sb = new StringBuilder();

		Catalogo cat = new Catalogo(flexColumn.ID_CATALOGO);
		String lstIdEmpresa = FacesUtils.getSessionBean().getIdCurrentEmpresa();
		if (this.atributo13 != null && atributo13.equals("ORDER_BY_CARGO")) {
			if (value == null || value.equals("")) {
				value = "0";
			}
			for (CatalogoValor catElem : cat.getElementosAdminVig(connect,
					Integer.parseInt(value))) {
				if (Integer.toString(catElem.idCatalogoValor).equals(value)) {
					if (this.atributo13 != null
							&& this.atributo13.equals("ORDER_BY_CARGO")
							&& flexColumn.ATRIBUTO5 != null
							&& flexColumn.COD_FLEX_COLUM.equals("VAL_C5")) {

						// JJAQ inserta Nota al pe en la nva tabla
						tuAdmVigDAO.insertNotaPie(this.idflexTbl,
								Integer.parseInt(lstIdEmpresa));
						liSuperindice = tuAdmVigDAO.getSuperindice(tiIdMetaRow,
								Integer.parseInt(lstIdEmpresa), this.idflexTbl);
						this.piSuperindice = liSuperindice;
						lsSuperindice = liSuperindice == 0 ? "" : String
								.valueOf(liSuperindice);
						return getNomCatVal(catElem, flexColumn, modo)
								+ "<sup class='superIndice'>" + lsSuperindice
								+ "</sup>";
					} else
						return getNomCatVal(catElem, flexColumn, modo);
				}
			}

		} else {
			for (CatalogoValor catElem : cat.getElementos(connect)) {
				if (Integer.toString(catElem.idCatalogoValor).equals(value)) {
					if (this.atributo13 != null
							&& this.atributo13.equals("ORDER_BY_CARGO")
							&& flexColumn.ATRIBUTO5 != null
							&& flexColumn.COD_FLEX_COLUM.equals("VAL_C5")) {
						liSuperindice = tuAdmVigDAO.getSuperindice(tiIdMetaRow,
								Integer.parseInt(lstIdEmpresa), this.idflexTbl);
						this.piSuperindice = liSuperindice;
						lsSuperindice = liSuperindice == 0 ? "" : String
								.valueOf(liSuperindice);

						return catElem.nomCatVal + "<sup class='superIndice'>"
								+ lsSuperindice + "</sup>";
					} else
						return catElem.nomCatVal;
				}
			}
		}

		return "";
	}

	public String getMultiCatalogValue(ConnectionWrapper connect,
			FlexColumn flexColumn, String value) {

		StringBuilder sb = new StringBuilder();
		boolean foundOne = false;

		Catalogo cat = new Catalogo(flexColumn.ID_CATALOGO);

		for (CatalogoValor catElem : cat.getElementos(connect)) {

			String commaValues = "," + value + ",";

			String searchedValue = ","
					+ Integer.toString(catElem.idCatalogoValor) + ",";

			if (commaValues.contains(searchedValue)) {

				if (foundOne) {

					sb.append(", ");
				}

				sb.append(catElem.nomCatVal);

				foundOne = true;
			}
		}
		return sb.toString();
	}

	/**
	 * Metodo sobrecargado para el catalogo especial 6969
	 * 
	 * @param connect
	 * @param flexColumn
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public String getMultiCatalogValue6969(ConnectionWrapper connect,
			FlexColumn flexColumn, String value) throws Exception {

		StringBuilder sb = new StringBuilder();
		boolean foundOne = false;

		// Catalogo cat = new Catalogo(flexColumn.ID_CATALOGO);
		List<CatalogElement> cat = new Catalog("DERCORP_CAT_PERSONAS_TOTAL_TAB")
				.getList("PERSON_ID, NOMBRE",
						"WHERE 1=1 ORDER BY UPPER(APP_COMMON_PKG.SIN_ACENTOS_FN(NOMBRE))");

		for (CatalogElement catElem : cat) {

			String commaValues = "," + value + ",";

			String searchedValue = "," + Integer.toString(catElem.getId())
					+ ",";

			if (commaValues.contains(searchedValue)) {

				if (foundOne) {

					sb.append(", ");
				}

				sb.append(catElem.getName());

				foundOne = true;
			}
		}
		return sb.toString();
	}

	public static String getValValor(ConnectionWrapper conn,
			String pstIdEmpresa, String pstIdAddCampo) {
		ResultSet set = null;
		PreparedStatement psmt = null;
		String lstValValor = "";
		try {
			String sqlQuery = "SELECT  VAL_VALOR "
					+ "FROM    DERCORP_ADD_CAMPO_VALOR_TAB " + "WHERE   1=1 "
					+ "AND     ID_EMPRESA = " + pstIdEmpresa + " "
					+ "AND     ID_ADD_CAMPO = " + pstIdAddCampo;
			psmt = conn.prepareStatement(sqlQuery);
			set = psmt.executeQuery();

			while (set.next()) {
				lstValValor = set.getString(1);
			}
			return lstValValor;
		} catch (Exception ex) {
			ex.printStackTrace();
			return ex.toString();
		} finally {
			try {
				psmt.close();
				set.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// ECM 01 Septiembre 2015
	public String getMonedaSetLabel(ConnectionWrapper conn, String pstIdEmpresa) {
		String lstValValor = getValValor(conn, pstIdEmpresa, "520"/* ID_ADD_CAMPO */);
		String lstNomCatVal = null;
		Catalogo cat = new Catalogo(20);
		for (CatalogoValor catElem : cat.getElementos(conn))
			if (Integer.toString(catElem.idCatalogoValor).equals(lstValValor))
				return lstNomCatVal = catElem.nomCatVal;

		return lstNomCatVal = "";

	}

}