package mx.com.televisa.derechocorporativo.modules.reports.adminvig;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import mx.com.televisa.derechocorporativo.bean.SessionBean;
import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;
import mx.com.televisa.derechocorporativo.data.packages.DERCORP_REPORTFLEX_PKG;
import mx.com.televisa.derechocorporativo.modules.reportsFlex.Section;
import mx.com.televisa.derechocorporativo.reflexion.ReflexionUtil;
import mx.com.televisa.derechocorporativo.util.ExcelUtil;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReporteAdmVigExcel {
	
	private int idReportFlex;  
	private String nomReporte; 
	private String desReporte; 
	private String desType; 
	private String desQuery; 
	
	private String atributo1; // Mostrar RFC en ECS
	private String atributo2; // Mostrar Pais en ECS
	private String atributo15; //Mostrar Salto de Pagina por Empresa


	public ReporteAdmVigExcel(ResultSet set, ResultSetMetaData metaData) throws Exception {

		ReflexionUtil.fillObject(set, metaData, this, ReporteAdmVigExcel.class);
	}
	
	public static ReporteAdmVigExcel getReport(String idReporte) {

		// String idEmpresa = FacesUtils.getSessionBean().getIdCurrentEmpresa();

		ArrayList<ReporteAdmVigExcel> rows = new ArrayList<ReporteAdmVigExcel>();

		ConnectionWrapper connect = null;
		CallableStatement stmt = null;
		ResultSet set = null;

		try {

			connect = new ConnectionWrapper();

			stmt = connect.prepareCall(DERCORP_REPORTFLEX_PKG.GET_REPORTE_PR);

			stmt.registerOutParameter(1, OracleTypes.CURSOR);

			stmt.setInt(2, Integer.parseInt(idReporte));
			
			stmt.execute();

			set = ((OracleCallableStatement) stmt).getCursor(1);

			while (set.next()) {

				return new ReporteAdmVigExcel(set, set.getMetaData());
			}

		} catch (Exception ex) {

			// TODO - Pendiente - Gestion de Excepciones
			ex.printStackTrace();

		} finally {

			ConnectionWrapper.closeAll(set, stmt, connect);
		}

		return null;
	}

	public static ArrayList<ReporteAdmVigExcel> getReports(int idRol) {

		// String idEmpresa = FacesUtils.getSessionBean().getIdCurrentEmpresa();

		ArrayList<ReporteAdmVigExcel> rows = new ArrayList<ReporteAdmVigExcel>();

		ConnectionWrapper connect = null;
		CallableStatement stmt = null;
		ResultSet set = null;

		try {

			connect = new ConnectionWrapper();

			stmt = connect.prepareCall(DERCORP_REPORTFLEX_PKG.GET_REPORTES_PR);

			stmt.setInt(1, idRol);
			stmt.registerOutParameter(2, OracleTypes.CURSOR);

			stmt.execute();

			set = ((OracleCallableStatement) stmt).getCursor(2);

			while (set.next()) {

				rows.add(new ReporteAdmVigExcel(set, set.getMetaData()));
			}

		} catch (Exception ex) {

			// TODO - Pendiente - Gestion de Excepciones
			ex.printStackTrace();

		} finally {

			ConnectionWrapper.closeAll(set, stmt, connect);
		}

		return rows;
	}
	
	
	public static void insertNewReport(String name, String desc, String rfcNuevoReporte, String paisNuevoReporte, int idRol) {

		// String idEmpresa = FacesUtils.getSessionBean().getIdCurrentEmpresa();

		ArrayList<ReporteAdmVigExcel> rows = new ArrayList<ReporteAdmVigExcel>();

		ConnectionWrapper connect = null;
		CallableStatement stmt = null;
		ResultSet set = null;

		try {

			connect = new ConnectionWrapper();

			stmt = connect.prepareCall(DERCORP_REPORTFLEX_PKG.INSERT_REPORT_PR);

			stmt.setString(1, name);
			stmt.setString(2, desc);
			stmt.setString(3, rfcNuevoReporte);
			stmt.setString(4, paisNuevoReporte);
			stmt.setInt(5, idRol);
			
			stmt.execute();

		} catch (Exception ex) {

			// TODO - Pendiente - Gestion de Excepciones
			ex.printStackTrace();

		} finally {

			ConnectionWrapper.closeAll(set, stmt, connect);
		}
	}
	
	
	public static void deleteReport(String id) {

		// String idEmpresa = FacesUtils.getSessionBean().getIdCurrentEmpresa();

		ArrayList<ReporteAdmVigExcel> rows = new ArrayList<ReporteAdmVigExcel>();

		ConnectionWrapper connect = null;
		CallableStatement stmt = null;
		ResultSet set = null;

		try {

			connect = new ConnectionWrapper();

			stmt = connect.prepareCall(DERCORP_REPORTFLEX_PKG.DELETE_REPORT_PR);

			stmt.setString(1, id);
			
			stmt.execute();

		} catch (Exception ex) {

			// TODO - Pendiente - Gestion de Excepciones
			ex.printStackTrace();

		} finally {

			ConnectionWrapper.closeAll(set, stmt, connect);
		}
	}

	

	


	
	
	
	
	public static XSSFWorkbook getReportAsExcel(HttpServletRequest request,String idEmpresas,String[] arrayIdFlex,String nomFuncionario) {

		
		XSSFWorkbook wb = new XSSFWorkbook();
		
		SessionBean sessionBean = (SessionBean)request.getSession().getAttribute("sessionBean");
		
		//Map<String,String> map = sessionBean.getDynamicParams();
		
		ConnectionWrapper conn = null;
		Statement stmt 			= null;
		ResultSet set 			= null;
		XSSFSheet sheet 		= null; 
		String idEmpresa 		= "";
		String nomEmpresa 		= "";
		int intIdEmpresa 		= 0;
		ArrayList<Section> seccs = null;

		
		try {
			conn = new ConnectionWrapper();
			
			/*String sqlRevoEmp = "SELECT ATRIBUTO1 FROM SS_ROL_TAB WHERE ID_ROL ="+ idRol;
			stmt = conn.createStatement();
			set = stmt.executeQuery(sqlRevoEmp);
			
			String lstIn = "0";
			while(set.next()) {
				lstIn = set.getString("ATRIBUTO1");
				if (lstIn == null ||lstIn.equals("")){
					lstIn = "0";
				}
				
			}*/
			//String sql = "SELECT ID_EMPRESA, NOM_EMPRESA FROM DERCORP_EMPRESA_TAB WHERE 1=1 ";
			String sql = "select * from (SELECT ID_EMPRESA,  \n"  +
					 "(SELECT val_cat_val \n" +
				     "     FROM dercorp_add_campo_cat_val_tab\n" + 
				     "     WHERE id_catalogo = 1				\n" +
				     "     AND   id_catalogo_valor = (SELECT ac.val_valor  \n" + 
				     "                                FROM dercorp_add_campo_valor_tab ac\n"+ 
				     "                                WHERE ac.id_empresa = emp.id_empresa \n"+
				     "                                AND id_add_campo = 500))AS NOM_EMPRESA \n"+		
					 "FROM DERCORP_EMPRESA_TAB emp WHERE 1=1 and ID_EMPRESA in ("+idEmpresas+") ) tmp \n"
					 		+ "ORDER BY TMP.NOM_EMPRESA";

			/*
			for(Entry<String, String> entry : map.entrySet()) {

				String key = entry.getKey();
			    String value = entry.getValue();
			    
			      //sql += "AND DERCORP_REPORTFLEX_PKG.GET_FIELD_VALUE(" + key + ", ID_EMPRESA) LIKE '%" + value + "%' ";
			      sql += "AND '," + value + ",' LIKE '%,' || DERCORP_REPORTFLEX_PKG.GET_FIELD_VALUE(" + key + ", ID_EMPRESA) || ',%' ";
			}*/
		/*	
			for(Entry<String, String> entry : map.entrySet()) {

				String key = entry.getKey();
			    String value = entry.getValue();
			    
			    //FlexFields
			    if(Integer.parseInt(key) < 10000) {
			    	//sql += "AND DERCORP_REPORTFLEX_PKG.GET_FIELD_VALUE(" + key + ", ID_EMPRESA) LIKE '%" + value + "%' ";
				      sql += "AND '," + value + ",' LIKE '%,' || DERCORP_REPORTFLEX_PKG.GET_FIELD_VALUE(" + key + ", ID_EMPRESA) || ',%' ";
				      
				      
			    } else {
			    	//FlexCols			    	
			    	int idFlexColum = Integer.parseInt(key) / 10000;			    	
			    	ReporteECSFlexColumn fc = ReporteECSFlexColumn.getFlexColumn(idFlexColum, conn);			    	
			    	String code = fc.COD_FLEX_COLUM;
				      sql += "AND ID_EMPRESA IN (SELECT ID_EMPRESA FROM DERCORP_METATBL_TAB where '," + value + ",' LIKE '%,' || " + code + " || ',%')";
			    }
			    

			}
			sql += " AND ID_EMPRESA NOT IN ("+lstIn +") ORDER BY NOM_EMPRESA";			
			*/
			stmt = conn.createStatement();
			set = stmt.executeQuery(sql);
			
			/*
			String sql = "SELECT ID_EMPRESA, NOM_EMPRESA FROM DERCORP_EMPRESA_TAB WHERE 1=1 ";

			
			for(Entry<String, String> entry : map.entrySet()) {

				String key = entry.getKey();
			    String value = entry.getValue();
			    
			    if(value.contains(",")){
			      value = "'" + value.replace(",", "','") +"'";
			      sql += "AND DERCORP_REPORTFLEX_PKG.GET_FIELD_VALUE(" + key + ", ID_EMPRESA) IN (" + value + ") ";
				} else{
			      sql += "AND DERCORP_REPORTFLEX_PKG.GET_FIELD_VALUE(" + key + ", ID_EMPRESA) LIKE '%" + value + "%' ";
				}
			}
			
			sql += "ORDER BY NOM_EMPRESA";
			
			
			stmt = conn.createStatement();
			set = stmt.executeQuery(sql);
			*/
			int c = 0;
			int count = 0;
			int rowIndex = 0;
			int colIndex = 0;
			
			sheet = wb.createSheet(); 
			
			while(set.next()) {
				count++;
				idEmpresa = set.getString("ID_EMPRESA");
				nomEmpresa = set.getString("NOM_EMPRESA");
				System.out.println("count "+count);
				System.out.println("idEmpresa "+idEmpresa);
				System.out.println("nomEmpresa "+nomEmpresa);
				
				intIdEmpresa = Integer.parseInt(idEmpresa);
				
				sessionBean.setIdCurrentEmpresa(idEmpresa);
				sessionBean.setEditCon("disabled");
				
				
				//ICL 11/11/2015
				//XSSFSheet sheet = wb.createSheet(); //wb.getSheetAt(0);
				

				//wb.setSheetName(c, idEmpresa);
				wb.setSheetName(0, "Hoja1");
				
				//int rowIndex = 0;
				//int colIndex = 0;
				
				rowIndex += 2;
				colIndex= 0;
				
	
				ExcelUtil.setCellValue(sheet, rowIndex, colIndex, nomEmpresa);
				ExcelUtil.setCellStyleTitle(sheet, rowIndex, colIndex);
				sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, colIndex, colIndex + 6));

				rowIndex += 2;
				

				//sb.append(getReportHTML(reportId, false, idEmpresa, request));
				
				
				ConnectionWrapper connect = null;
				
				try {

					connect = new ConnectionWrapper();


					Map<String, String> empresaValuesMap = getEmpresaMap(intIdEmpresa, connect);
					
					ReporteAdmVigSeccionRow row = new ReporteAdmVigSeccionRow();
					rowIndex = row.toExcel(sheet, connect, request, empresaValuesMap, rowIndex,intIdEmpresa,arrayIdFlex,nomFuncionario);
					
					/*seccs = Section.getSecciones(reportId, connect);
					
					for (Section section : seccs) {
						
						rowIndex = section.toExcel(sheet, connect, request, empresaValuesMap, rowIndex);
						
						rowIndex += 2;
					}
					*/
				
				} catch (Exception ex) {

					// TODO - Pendiente - Gestion de Excepciones
					ex.printStackTrace();

				} finally {

					ConnectionWrapper.closeAll(connect);
				}
				
				
				for(int i = 2; i <= 15; i++) {
					
					try {
					
						sheet.autoSizeColumn(i);
					
					}catch(Exception ex) {
						
						System.out.println(ex.toString());
					}
				}
				
				c++;
				
			}

			
			return wb;
			
		} catch(Exception ex) {
			
			ex.printStackTrace();
			
			return null;
		} finally {
			
			ConnectionWrapper.closeAll(set, stmt, conn);
		}
	}
	
	
	
	
	public static Map<String, String> getEmpresaMap(int idEmpresa, ConnectionWrapper connect) {

		Map<String, String> fieldValues = new HashMap<String, String>();

		CallableStatement stmt = null;
		ResultSet set = null;

		try {

			

			stmt = connect.prepareCall(DERCORP_REPORTFLEX_PKG.GET_INFO_MAP_ECS_PR);

			stmt.registerOutParameter(1, OracleTypes.CURSOR);

			stmt.setInt(2, idEmpresa);
			
			stmt.execute();

			set = ((OracleCallableStatement) stmt).getCursor(1);

			while (set.next()) {

				String key = set.getString("ID_ADD_CAMPO");
				String value = set.getString("VALOR_TEXTUAL");
				
				fieldValues.put(key, value);
				
			}
			
			return fieldValues;

		} catch (Exception ex) {

			// TODO - Pendiente - Gestion de Excepciones
			ex.printStackTrace();

			
		}  finally {
			
			ConnectionWrapper.closeAll(set, stmt);
			
		}

		return null;
	}
	
	
	
	
	public int getIdReportFlex() {
		return idReportFlex;
	}

	public void setIdReportFlex(int idReportFlex) {
		this.idReportFlex = idReportFlex;
	}

	public String getNomReporte() {
		return nomReporte;
	}

	public void setNomReporte(String nomReporte) {
		this.nomReporte = nomReporte;
	}

	public String getDesReporte() {
		return desReporte;
	}

	public void setDesReporte(String desReporte) {
		this.desReporte = desReporte;
	}

	public String getDesType() {
		return desType;
	}

	public void setDesType(String desType) {
		this.desType = desType;
	}

	public String getDesQuery() {
		return desQuery;
	}

	public void setDesQuery(String desQuery) {
		this.desQuery = desQuery;
	}

	public String getAtributo1() {
		return atributo1;
	}

	public void setAtributo1(String atributo1) {
		this.atributo1 = atributo1;
	}

	public String getAtributo2() {
		return atributo2;
	}

	public void setAtributo2(String atributo2) {
		this.atributo2 = atributo2;
	}


}