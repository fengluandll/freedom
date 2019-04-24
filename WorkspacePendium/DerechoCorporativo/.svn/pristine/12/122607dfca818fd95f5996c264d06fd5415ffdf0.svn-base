package mx.com.televisa.derechocorporativo.modules.reports.adminvig;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mx.com.televisa.derechocorporativo.bean.SessionBean;
import mx.com.televisa.derechocorporativo.daos.ConsultaDAO;
import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;
import mx.com.televisa.derechocorporativo.modules.flextabs.consulta.AprobEjerSocCon;
import mx.com.televisa.derechocorporativo.modules.flextabs.consulta.Fusion;
import mx.com.televisa.derechocorporativo.modules.flextabs.consulta.RefDocNew;
import mx.com.televisa.derechocorporativo.modules.flextabs.consulta.ReferenciaDocumentum;
import mx.com.televisa.derechocorporativo.modules.reports.ecs.ReporteECSEstructuraCapitalSocial;
import mx.com.televisa.derechocorporativo.reflexion.ReflexionUtil;

import org.apache.log4j.Logger;

public class ReporteAdmVigFlexColumn {
	
private final static Logger log = Logger.getLogger(ReporteAdmVigFlexColumn.class);
	
	
	//ECM 13 Julio 2016 - Agregar Height y Valign
	public int IND_HEIGHT;     //Altura
	public String DES_VALIGN;  //Posición

	public int ID_FLEX_COLUM;
	public int ID_FLEX_TBL;
	public String COD_FLEX_COLUM;
	public String NOM_FLEX_COLUM;
	public String DES_FLEX_COLUM;
	public String DES_TIPO_COLUM;
	public int CAN_TAMANN_COLUM;
	public String DES_FORMULA;
	public String DES_URL;
	public int ID_CATALOGO;
	public int ID_SECCION;
	public int ID_SUBSECCION;
	public int ID_AGRUPACION;
	public int ID_ORDEN;
	  
	  
	public String DES_CELL_WIDTH; // cellwidth;
	public String DES_GROUP; // group;
	  
	public String DES_TOTALS; // mostrar Totales
	  
	  //el atrr 4 se usa en queries
	public String DES_RULE;
	  
	public String DES_ALIGN; //Align
	  
	public String DES_FORMAT; // Format
	  
	public String DES_SHOW_IN_TABLE; // Mostrar en la consulta general
	  
	public String DES_TR_CLASS; // TR_CLASS
	
	public String ATRIBUTO1; //ReferenciaDocumentum
	
	//ECM 05 Noviembre 2015
	public String ATRIBUTO2; //Formula InputText Documentum
	
	public String ATRIBUTO3; //Ocupado en la Tabla DERCORP_FLEX_COLUMS_TAB
	
	public String ATRIBUTO4; //Columnas de CustomTable
	
	public String ATRIBUTO5; //ECM 05 Abril 2016 - Columnas consulta adm y vig
	  
	public String ATRIBUTO6; //ULR bandera para ocultar campos status reformas
	  /*
	   * 
	   * 
	  
	  ATRIBUTO1 DES_CELL_WIDTH,
	  ATRIBUTO2 DES_GROUP,
	  ATRIBUTO3 DES_TOTALS,
	  ATRIBUTO4 DES_RULE,
	  ATRIBUTO5 DES_ALIGN,
	  ATRIBUTO6 DES_FORMAT,
	  ATRIBUTO7 DES_SHOW_IN_TABLE,
	  ATRIBUTO8 DES_TR_CLASS,
	  
	  
	  
	  String atributo1; // cellwidth;
	  String atributo2; // group;
	  
	  String atributo3; // mostrar Totales
	  
	  //el atrr 4 se usa en queries
	  
	  String atributo5; //Align
	  
	  String atributo6; // Format
	  
	  String atributo7; // Mostrar en la consulta general
	  
	  String atributo8; // TR_CLASS
	  
	  */

	  public ReporteAdmVigFlexColumn(String COD_FLEX_COLUM) {
		  
		  this.COD_FLEX_COLUM = COD_FLEX_COLUM;
		  
		}

	  @Override
		public boolean equals(Object obj) {
			
			return this.COD_FLEX_COLUM.equals(((ReporteAdmVigFlexColumn) obj).COD_FLEX_COLUM);
		}
	  
	  
	  
		public ReporteAdmVigFlexColumn(ResultSet set, ResultSetMetaData metaData) throws Exception {

			ReflexionUtil.fillObject(set, metaData, this, ReporteAdmVigFlexColumn.class);
		}
		

		
		public static ArrayList<ReporteAdmVigFlexColumn> getFlexColumns(String strIdFlexTbl, boolean tbIsHorizontal, boolean modoConfiguracioDeReportes) {
			
			ConnectionWrapper connect = null;
			
			try {
				connect = new ConnectionWrapper();
				
				int  idFlexTbl = Integer.parseInt(strIdFlexTbl);
				
				return getFlexColumns(idFlexTbl, connect, false, null, "0", "", tbIsHorizontal, modoConfiguracioDeReportes);
				
			} catch(Exception ex) {
				
				ex.printStackTrace();
				
				return null;
			}finally {
				
				ConnectionWrapper.closeAll(connect);
			}
		}
		

		/*
		public static ArrayList<ReporteECSFlexColumn> getFlexColumns(int  idFlexTbl, ConnectionWrapper connect) {
			
			return getFlexColumns(idFlexTbl, connect, false);
		}
		
		public static ArrayList<ReporteECSFlexColumn> getSelectedFlexColumns(int  idFlexTbl, ConnectionWrapper connect, boolean consultaGeneral, String otherCondition) {

			String idEmpresa = FacesUtils.getSessionBean().getIdCurrentEmpresa();
			
			return getFlexColumns(idFlexTbl, connect, consultaGeneral, idEmpresa, otherCondition);
		}
			

		public static ArrayList<ReporteECSFlexColumn> getFlexColumns(int  idFlexTbl, ConnectionWrapper connect, boolean consultaGeneral) {

			String idEmpresa = FacesUtils.getSessionBean().getIdCurrentEmpresa();
			
			return getFlexColumns(idFlexTbl, connect, consultaGeneral, idEmpresa);
		}

		
		public static ArrayList<ReporteECSFlexColumn> getFlexColumns(int  idFlexTbl, ConnectionWrapper connect, boolean consultaGeneral, HttpServletRequest request) {

			SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
			
			String idEmpresa = sessionBean.getIdCurrentEmpresa();
			
			return getFlexColumns(idFlexTbl, connect, consultaGeneral, idEmpresa);
		}
		
		
		private static ArrayList<ReporteECSFlexColumn> getFlexColumns(int  idFlexTbl, String otherCondition, ConnectionWrapper connect, boolean consultaGeneral, String idEmpresa) {
			
			return getFlexColumns(idFlexTbl, connect, consultaGeneral, idEmpresa, otherCondition);
		}
		

		private static ArrayList<ReporteECSFlexColumn> getFlexColumns(int  idFlexTbl, ConnectionWrapper connect, boolean consultaGeneral, String idEmpresa) {
			
			return getFlexColumns(idFlexTbl, connect, consultaGeneral, idEmpresa, "");
		}*/
		

		
		public static ArrayList<ReporteAdmVigFlexColumn> getFlexColumns(int  idFlexTbl, ConnectionWrapper connect, 
													boolean consultaResumen, 
													HttpServletRequest request,
													String idEmpresa, 
													String otherCondition,
													boolean tbIsHorizontal,
													boolean modoConfiguracioDeReportes
                                                           ) {

			/*if(request != null) {
				SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
				idEmpresa = sessionBean.getIdCurrentEmpresa();
			}*/

			ArrayList<ReporteAdmVigFlexColumn> flexColumns = new ArrayList<ReporteAdmVigFlexColumn>();
			ResultSet set = null;
			PreparedStatement psmt = null;
			String sqlCampos = "";

			try {
				String exceptionalCondition = "";

				if(consultaResumen) {
					exceptionalCondition = " AND DES_SHOW_IN_TABLE = '1' ";
				}
				int isCS = ReporteECSEstructuraCapitalSocial.isAsociacionCivil(Integer.parseInt(idEmpresa));//JJAQ
				
				//ECM 08 Febrero 2016 Agregar ATRIBUTO3 al Order By. TRUE = Flextables Poderes, Reformas, Contratos y Escrituras Otros.
				if(tbIsHorizontal){
				    //sqlCampos = "SELECT * FROM DERCORP_FLEX_COLUMS_TAB WHERE ID_FLEX_TBL = " + idFlexTbl + exceptionalCondition + otherCondition + " AND DES_FLEX_COLUM IS NOT NULL ORDER BY DECODE(DES_GROUP,'GROUP',1,2), TO_NUMBER(ATRIBUTO3), ID_ORDEN, TO_NUMBER(REPLACE(COD_FLEX_COLUM,'VAL_C',''))";
					//Argu 06/09/2016
					
				
					if(idFlexTbl == 7 && isCS > 0){//JJAQ SE AGREGO IF Estructura C.S.
						sqlCampos = "SELECT * FROM DERCORP_FLEX_COLUMS_TAB WHERE ID_FLEX_TBL = " + idFlexTbl + exceptionalCondition + otherCondition + " AND DES_FLEX_COLUM IS NOT NULL AND ATRIBUTO1 = 'ISAC' ORDER BY TO_NUMBER(ATRIBUTO3)";
					}else if(idFlexTbl == 37){
						sqlCampos = "SELECT * FROM DERCORP_FLEX_COLUMS_TAB WHERE ID_FLEX_TBL = " + idFlexTbl + exceptionalCondition + otherCondition + " AND (IND_SHOW_TBL_CON <> 0 OR IND_SHOW_TBL_CON IS NULL) AND DES_FLEX_COLUM IS NOT NULL ORDER BY ID_ORDER_CON";
					}else{
						sqlCampos = "SELECT * FROM DERCORP_FLEX_COLUMS_TAB WHERE ID_FLEX_TBL = " + idFlexTbl + exceptionalCondition + otherCondition + " AND (IND_SHOW_TBL_CON <> 0 OR IND_SHOW_TBL_CON IS NULL) AND DES_FLEX_COLUM IS NOT NULL ORDER BY TO_NUMBER(ATRIBUTO3)";
					}
					
				}else{
					if(idFlexTbl == 7 && isCS > 0){//JJAQ SE AGREGO IF Estructura C.S.
						sqlCampos = "SELECT * FROM DERCORP_FLEX_COLUMS_TAB WHERE ID_FLEX_TBL = " + idFlexTbl + exceptionalCondition + otherCondition + " AND DES_FLEX_COLUM IS NOT NULL AND ATRIBUTO1 = 'ISAC' ORDER BY DECODE(DES_GROUP,'GROUP',1,2), ID_ORDEN, TO_NUMBER(REPLACE(COD_FLEX_COLUM,'VAL_C',''))";
					}else{
                      sqlCampos = "SELECT * FROM DERCORP_FLEX_COLUMS_TAB WHERE ID_FLEX_TBL = " + idFlexTbl + exceptionalCondition + otherCondition + " AND (IND_SHOW_TBL_CON <> 0 OR IND_SHOW_TBL_CON IS NULL) AND DES_FLEX_COLUM IS NOT NULL ORDER BY DECODE(DES_GROUP,'GROUP',1,2), ID_ORDEN, TO_NUMBER(REPLACE(COD_FLEX_COLUM,'VAL_C',''))";
					}
				}

				log.info("sqlCampos222: "+sqlCampos);
				psmt= connect.prepareStatement(sqlCampos);
				set = psmt.executeQuery();
				while (set.next()) {
					ReporteAdmVigFlexColumn newFlexColum = new ReporteAdmVigFlexColumn(set, set.getMetaData());
					flexColumns.add(newFlexColum);

				}
				/*
				if(idFlexTbl == 7 && !modoConfiguracioDeReportes) {
					
					ReporteECSEstructuraCapitalSocial ecs = new ReporteECSEstructuraCapitalSocial(connect, idEmpresa);
					
					flexColumns = ecs.customizeFlexColumns(flexColumns, modoConfiguracioDeReportes);
				}*/

			} catch (Exception ex) {
				ex.printStackTrace();

			} finally {

				//ConnectionWrapper.closeAll(set, psmt);
				/*
				try{
					set.close();
					psmt.close();
				} catch (Exception ex){ 
					ex.printStackTrace();
				}*/

			}
			
			return flexColumns;
		}

		//ECM 14 Junio 2016 Consulta - Fusion - Ocultar Aumento Disminucion.
		public static ArrayList<ReporteAdmVigFlexColumn> getFlexColumns(
				int  idFlexTbl
				,ConnectionWrapper connect 
				,boolean consultaResumen
				,HttpServletRequest request
				,String idEmpresa
				,String otherCondition
				,boolean tbIsHorizontal
				,int tsIdMetaRow
         ){
			if(request != null) {
				SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
				idEmpresa = sessionBean.getIdCurrentEmpresa();
			}

			ArrayList<ReporteAdmVigFlexColumn> flexColumns = new ArrayList<ReporteAdmVigFlexColumn>();
			ResultSet set = null;
			PreparedStatement psmt = null;
			String sqlCampos = "";

			ConsultaDAO consultaDAO = new ConsultaDAO();
			//ReferenciaDocumentum refDoc = new ReferenciaDocumentum(connect);
			RefDocNew refDocNew = new RefDocNew();
			Fusion fusion = new Fusion();
 
			try {
				String exceptionalCondition = "";

			if(consultaResumen) {
				exceptionalCondition = " AND DES_SHOW_IN_TABLE = '1' ";
			}

			//ECM 08 Febrero 2016 Agregar ATRIBUTO3 al Order By. TRUE = Flextables Poderes, Reformas, Contratos y Escrituras Otros.
			sqlCampos = "SELECT * FROM DERCORP_FLEX_COLUMS_TAB WHERE ID_FLEX_TBL = " + idFlexTbl + exceptionalCondition + otherCondition + " AND DES_FLEX_COLUM IS NOT NULL";

			if(tbIsHorizontal)
				sqlCampos += "ORDER BY DECODE(DES_GROUP,'GROUP',1,2), TO_NUMBER(ATRIBUTO3), ID_ORDEN, TO_NUMBER(REPLACE(COD_FLEX_COLUM,'VAL_C',''))";
			else{
				sqlCampos += refDocNew.getCamposFusion(tsIdMetaRow, connect);
				sqlCampos += fusion.getCapFijVarSocAumDisPr(tsIdMetaRow, connect);
				sqlCampos += fusion.getCamposValoresFusion(tsIdMetaRow, connect);
				sqlCampos += "ORDER BY DECODE(DES_GROUP,'GROUP',1,2), ID_ORDEN, TO_NUMBER(REPLACE(COD_FLEX_COLUM,'VAL_C',''))";
			}
			
			psmt= connect.prepareStatement(sqlCampos);
			set = psmt.executeQuery();

			while (set.next()) {
				ReporteAdmVigFlexColumn newFlexColum = new ReporteAdmVigFlexColumn(set, set.getMetaData());
				flexColumns.add(newFlexColum);

			}
			/*
			if(idFlexTbl == 7) {
				ReporteECSEstructuraCapitalSocial ecs = new ReporteECSEstructuraCapitalSocial(connect, idEmpresa);
				flexColumns = ecs.customizeFlexColumns(flexColumns);
			}*/
			
			} catch (Exception ex) {
				ex.printStackTrace();
			
			} finally {

				//ConnectionWrapper.closeAll(set, psmt);
				/*
				try{
				set.close();
				psmt.close();
				} catch (Exception ex){ 
				ex.printStackTrace();
				}*/

			}
			
			return flexColumns;
        }		

        //ECM 06 ABRIL 2016 - Sobrecargar Métodos. Consulta Adm y Vig
		public static ArrayList<ReporteAdmVigFlexColumn> getFlexColumns(int  idFlexTbl, ConnectionWrapper connect, 
                boolean consultaResumen, 
                HttpServletRequest request,
                String idEmpresa, 
                String otherCondition,
                boolean tbIsHorizontal,
                String tsAtributo13,
                boolean reporte
                ) {
          
             // String sConsulta = FacesUtils.getSessionBean().getEditCon();
			String sConsulta ="";
              
        

                if(request != null) {
                     //sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
                	 HttpSession session = (HttpSession) request.getSession(true);
               		SessionBean sessionBean = (SessionBean)session.getAttribute("sessionBean"); 
               		sConsulta = sessionBean.getEditCon();
               		sConsulta = "disabled";
                     //idEmpresa = sessionBean.getIdCurrentEmpresa();
                }

                ArrayList<ReporteAdmVigFlexColumn> flexColumns = new ArrayList<ReporteAdmVigFlexColumn>();
                ResultSet set = null;
                PreparedStatement psmt = null;
                String sqlCampos = "";

                try {

                     String exceptionalCondition = "";
                     String lsOrderBy = "";

                     if(consultaResumen) {
                    	 exceptionalCondition = " AND IND_SHOW_TBL_CON = 1";
                    	 lsOrderBy = "ORDER BY ID_ORDER_CON";
                     }else{
                    	 exceptionalCondition = " AND DES_SHOW_IN_TABLE = '1' ";
                    	 lsOrderBy = "ORDER BY ID_ORDEN";
                     }
                     int isCS = ReporteECSEstructuraCapitalSocial.isAsociacionCivil(Integer.parseInt(idEmpresa));//JJAQ
                     //ECM 08 Febrero 2016 Agregar ATRIBUTO3 al Order By. TRUE = Flextables Poderes, Reformas, Contratos y Escrituras Otros.
                     if(tbIsHorizontal){
                           //sqlCampos = "SELECT * FROM DERCORP_FLEX_COLUMS_TAB WHERE ID_FLEX_TBL = " + idFlexTbl + exceptionalCondition + otherCondition + " AND DES_FLEX_COLUM IS NOT NULL ORDER BY DECODE(DES_GROUP,'GROUP',1,2), TO_NUMBER(ATRIBUTO3), ID_ORDEN, TO_NUMBER(REPLACE(COD_FLEX_COLUM,'VAL_C',''))";
                    	 //Argu 06/08/2016
                    	 if(idFlexTbl == 30){
                    		 sqlCampos = "SELECT * FROM DERCORP_FLEX_COLUMS_TAB WHERE ID_FLEX_TBL = " + idFlexTbl + exceptionalCondition + otherCondition + " AND DES_FLEX_COLUM IS NOT NULL "+lsOrderBy;
                    	 }else if(idFlexTbl == 7 && isCS > 0){
                    		 sqlCampos = "SELECT * FROM DERCORP_FLEX_COLUMS_TAB WHERE ID_FLEX_TBL = " + idFlexTbl + exceptionalCondition + otherCondition + " AND DES_FLEX_COLUM IS NOT NULL AND ATRIBUTO1 = 'ISAC' "+lsOrderBy;
                    	 }else{
                    		 sqlCampos = "SELECT * FROM DERCORP_FLEX_COLUMS_TAB WHERE ID_FLEX_TBL = " + idFlexTbl + exceptionalCondition + otherCondition + " AND DES_FLEX_COLUM IS NOT NULL "+lsOrderBy;
                    	 }
                     }else{
                    	 if(idFlexTbl == 7 && isCS > 0){//JJAQ SE AGREGO IF Estructura C.S.
                    		 sqlCampos = "SELECT * FROM DERCORP_FLEX_COLUMS_TAB WHERE ID_FLEX_TBL = " + idFlexTbl + exceptionalCondition + otherCondition + " AND DES_FLEX_COLUM IS NOT NULL AND ATRIBUTO1 = 'ISAC' ORDER BY DECODE(DES_GROUP,'GROUP',1,2), ID_ORDEN, TO_NUMBER(REPLACE(COD_FLEX_COLUM,'VAL_C',''))";
                    	 }else{
                    		 sqlCampos = "SELECT * FROM DERCORP_FLEX_COLUMS_TAB WHERE ID_FLEX_TBL = " + idFlexTbl + exceptionalCondition + otherCondition + " AND DES_FLEX_COLUM IS NOT NULL ORDER BY DECODE(DES_GROUP,'GROUP',1,2), ID_ORDEN, TO_NUMBER(REPLACE(COD_FLEX_COLUM,'VAL_C',''))";
                    	 }
                           
                     }

                     //ECM 06 ABRIL 2016 Consulta Adm y Vig.
                     if(sConsulta == null || sConsulta.equals("") ){

                     }else{
                           if(tsAtributo13 != null && tsAtributo13.equals("ORDER_BY_CARGO"))
                                 sqlCampos = "SELECT * FROM  DERCORP_FLEX_COLUMS_TAB WHERE 1=1 AND ID_FLEX_TBL = "+idFlexTbl+" AND ATRIBUTO5 IS NOT NULL ORDER BY ATRIBUTO5";
                        	  // sqlCampos = "SELECT * FROM  DERCORP_FLEX_COLUMS_TAB WHERE 1=1 AND ID_FLEX_TBL = "+idFlexTbl+" AND (ATRIBUTO5 IS NOT NULL or ID_FLEX_COLUM = 1312) ORDER BY ATRIBUTO5";
                        	   
                     }
                     psmt= connect.prepareStatement(sqlCampos);
                     set = psmt.executeQuery();

                     while (set.next()) {
                    	 ReporteAdmVigFlexColumn newFlexColum = new ReporteAdmVigFlexColumn(set, set.getMetaData());
                           flexColumns.add(newFlexColum);

                     }
                     /*
                     if(idFlexTbl == 7) {
                    	 ReporteECSEstructuraCapitalSocial ecs = new ReporteECSEstructuraCapitalSocial(connect, idEmpresa);
                           flexColumns = ecs.customizeFlexColumns(flexColumns);
                     }
                     */

                } catch (Exception ex) {

                     // TODO - Pendiente - Gestion de Excepciones

                     ex.printStackTrace();

                } finally {

                }

                return flexColumns;

}
		
		//ECM 08 ABRIL 2016 - Consulta Poderes Generales y Especiales - Protocolización.
		public static ArrayList<ReporteAdmVigFlexColumn> getFlexColumns(int  idFlexTbl
				, ConnectionWrapper connect, 
				boolean consultaResumen, 
				HttpServletRequest request,
				String idEmpresa,
				String otherCondition,
				boolean tbIsHorizontal,
				ReporteAdmVigFlexTable tFlexTable,
				String tsConsulta,
				String tsIdMetaRow,
				String tipoPoder,
				ResultSet TresultSet) {

				if(request != null) {
					SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");				
					idEmpresa = sessionBean.getIdCurrentEmpresa();
				}

				ArrayList<ReporteAdmVigFlexColumn> flexColumns = new ArrayList<ReporteAdmVigFlexColumn>();
				ResultSet set = null;
				PreparedStatement psmt = null;
				String sqlCampos = "SELECT * FROM DERCORP_FLEX_COLUMS_TAB WHERE 1=1 AND (IND_SHOW_TBL_CON <> 0 OR IND_SHOW_TBL_CON IS NULL)";
				Fusion fusion = new Fusion();

				try {

					ConsultaDAO luConsultaDAO = new ConsultaDAO();
					ReferenciaDocumentum luRefDoc = new ReferenciaDocumentum(connect);
					RefDocNew refDocNew = new RefDocNew();
					AprobEjerSocCon aprobEjerSocCon = new AprobEjerSocCon();

					//Protocolizacion
					if(tipoPoder.equals("PG")){
                        sqlCampos += luConsultaDAO.doQueriesPG(idEmpresa, idFlexTbl, tsIdMetaRow);   //Poderes Generales y Especiales

					}else if(tipoPoder.equals("PE")){
						sqlCampos += luConsultaDAO.doQueriesPE(idEmpresa, idFlexTbl, tsIdMetaRow);

					}else if(tipoPoder.equals("PGPE")){
						sqlCampos += luConsultaDAO.doQueriesPGPE(idEmpresa, idFlexTbl, tsIdMetaRow);

					}else if(tipoPoder.equals("REF_PROTO_INS")){
						sqlCampos += luConsultaDAO.doQueriesRefPGPE(idEmpresa, idFlexTbl, tsIdMetaRow); //Reformas Protocolizacion e Inscripcion

					}else if(tipoPoder.equals("REF_PROTO")){
						sqlCampos += luConsultaDAO.doQueriesRefProto(idEmpresa, idFlexTbl, tsIdMetaRow);

					}else if(tipoPoder.equals("REF_INS")){
						sqlCampos += luConsultaDAO.doQueriesRefIns(idEmpresa, idFlexTbl, tsIdMetaRow);

					}else if(tipoPoder.equals("APRO_ProIns")){
						sqlCampos += luConsultaDAO.doQueriesAproProIns(idEmpresa, idFlexTbl, tsIdMetaRow); //Aprobacion del Ejercicio Social
					}else if(tipoPoder.equals("APRO_PROTO")){
						sqlCampos += luConsultaDAO.doQueriesAproPro(idEmpresa, idFlexTbl, tsIdMetaRow);
					}else if(tipoPoder.equals("APRO_INS")){
						sqlCampos += luConsultaDAO.doQueriesAproIns(idEmpresa, idFlexTbl, tsIdMetaRow);
					}

					//Status
					//System.out.println("Set:  "+TresultSet.getString("VAL_C76"));
					if(idFlexTbl == 17 || idFlexTbl == 18){
						if(TresultSet.getString("VAL_C76") != null && TresultSet.getString("VAL_C76").equals("No") ){
							sqlCampos += luConsultaDAO.addQueryStatusPoderes();
						}
						sqlCampos += luRefDoc.doQueryPodGenEspRefDoc(Integer.parseInt(tsIdMetaRow), idFlexTbl, Integer.parseInt(idEmpresa));

					}else if(idFlexTbl == 20){
						if(TresultSet.getString("VAL_C59") != null && TresultSet.getString("VAL_C59").equals("No") ){
							sqlCampos += luConsultaDAO.addQueryStatusRef_RTot();
						}
						sqlCampos += luRefDoc.doQueryRefTotRefDoc( Integer.parseInt(tsIdMetaRow), idFlexTbl, Integer.parseInt(idEmpresa) );

					}else if(idFlexTbl == 21){
						if(TresultSet.getString("VAL_C49") != null && TresultSet.getString("VAL_C49").equals("No") ){
							sqlCampos += luConsultaDAO.addQueryStatusRef_RPar();
						}
						sqlCampos += luRefDoc.doQueryRefParRefDoc( Integer.parseInt(tsIdMetaRow), idFlexTbl, Integer.parseInt(idEmpresa) );

					}else if(idFlexTbl == 22){
						if(TresultSet.getString("VAL_C55") != null && TresultSet.getString("VAL_C55").equals("No") ){
							sqlCampos += luConsultaDAO.addQueryStatusRef_RTrans();
						}
						sqlCampos += luRefDoc.doQueryTransfRefDoc( Integer.parseInt(tsIdMetaRow), idFlexTbl, Integer.parseInt(idEmpresa) );

					}else if(idFlexTbl == 23){
						if(TresultSet.getString("VAL_C98") != null && TresultSet.getString("VAL_C98").equals("No") ){
							sqlCampos += luConsultaDAO.addQueryStatusRef_R_AES();
						}
						sqlCampos += luRefDoc.doQueryAprobEjerSocRefDoc( Integer.parseInt(tsIdMetaRow), idFlexTbl, Integer.parseInt(idEmpresa) );
						sqlCampos += aprobEjerSocCon.getResultado(Integer.parseInt(tsIdMetaRow), connect);
						sqlCampos += aprobEjerSocCon.getOtrosAcuerdos(Integer.parseInt(tsIdMetaRow), connect);

					}else if(idFlexTbl == 27){
						if(TresultSet.getString("VAL_C17") != null && TresultSet.getString("VAL_C17").equals("No") ){
							sqlCampos += luConsultaDAO.addQueryStatusRef_R_EscOtros();
						}
						sqlCampos += refDocNew.getCamposEscrituraOtros(Integer.parseInt(tsIdMetaRow), connect);

					}else if(idFlexTbl == 28){
						if(TresultSet.getString("VAL_C26") != null && TresultSet.getString("VAL_C26").equals("No") ){
							sqlCampos += luConsultaDAO.addQueryStatusRef_R_ActaOtros();
						}
						sqlCampos += refDocNew.getCamposActaOtros(Integer.parseInt(tsIdMetaRow), connect);

					}else if(idFlexTbl == 29){
						if(TresultSet.getString("VAL_C45") != null && TresultSet.getString("VAL_C45").equals("No") ){
							sqlCampos += luConsultaDAO.addQueryStatusRef_R_AumCap();
						}
						sqlCampos += refDocNew.getCamposAumentoCapital(Integer.parseInt(tsIdMetaRow), connect);

					}else if(idFlexTbl == 30){
						if(TresultSet.getString("VAL_C28") != null && TresultSet.getString("VAL_C28").equals("No") ){
							sqlCampos += luConsultaDAO.addQueryStatusRef_R_Cont();
						}
						sqlCampos += refDocNew.getCamposContratos(Integer.parseInt(tsIdMetaRow), connect);

					}else if(idFlexTbl == 31){
						if(TresultSet.getString("VAL_C25") != null && TresultSet.getString("VAL_C25").equals("No") ){
							sqlCampos += luConsultaDAO.addQueryStatusRef_R_DecDiv();
						}
						sqlCampos += refDocNew.getCamposDecretoDividendos(Integer.parseInt(tsIdMetaRow), connect);

					}else if(idFlexTbl == 32){
						if(TresultSet.getString("VAL_C45") != null && TresultSet.getString("VAL_C45").equals("No") ){
							sqlCampos += luConsultaDAO.addQueryStatusRef_R_DisCap();
						}
						sqlCampos += refDocNew.getCamposDisminucionCapital(Integer.parseInt(tsIdMetaRow), connect);

					}else if(idFlexTbl == 33){
						if(TresultSet.getString("VAL_C47") != null && TresultSet.getString("VAL_C47").equals("No") ){
							sqlCampos += luConsultaDAO.addQueryStatusRef_R_Escision();
						}
						sqlCampos += refDocNew.getCamposEscision(Integer.parseInt(tsIdMetaRow), connect);

					}else if(idFlexTbl == 34){
						if(TresultSet.getString("VAL_C68") != null && TresultSet.getString("VAL_C68").equals("No") ){
							sqlCampos += luConsultaDAO.addQueryStatusRef_R_Fusion();
						}
						sqlCampos += fusion.getCapFijVarSocAumDisPr(Integer.parseInt(tsIdMetaRow), connect);
						sqlCampos += fusion.getCamposValoresFusion(Integer.parseInt(tsIdMetaRow), connect);
						sqlCampos += refDocNew.getCamposFusion(Integer.parseInt(tsIdMetaRow), connect);

					}else if(idFlexTbl == 35 || idFlexTbl == 41){
						if(TresultSet.getString("VAL_C25") != null && TresultSet.getString("VAL_C25").equals("No") ){
							sqlCampos += luConsultaDAO.addQueryStatusRef_R_SesionCons();
						}
						sqlCampos += refDocNew.getCamposSesionConsejo(Integer.parseInt(tsIdMetaRow), connect);
					}

					//ECM 20 Abril 2016 - Consulta Apoderados. Muestra o no. Poderes Generales y Especiales.
					if(idFlexTbl == 17 || idFlexTbl == 18){
							sqlCampos += luConsultaDAO.addQueryApoderados(idFlexTbl, idEmpresa, tsIdMetaRow);
					}

					sqlCampos += "ORDER BY DECODE(DES_GROUP,'GROUP',1,2), ID_ORDEN, TO_NUMBER(REPLACE(COD_FLEX_COLUM,'VAL_C',''))";

					//System.out.println("FlexColumn line 566: "+sqlCampos);
					
					psmt= connect.prepareStatement(sqlCampos);
					set = psmt.executeQuery();

					while (set.next()) {
						ReporteAdmVigFlexColumn newFlexColum = new ReporteAdmVigFlexColumn(set, set.getMetaData());
						flexColumns.add(newFlexColum);

					}
					/*
					if(idFlexTbl == 7) {
						ReporteECSEstructuraCapitalSocial ecs = new ReporteECSEstructuraCapitalSocial(connect, idEmpresa);
						flexColumns = ecs.customizeFlexColumns(flexColumns);
					}*/

				} catch (Exception ex) {

					// TODO - Pendiente - Gestion de Excepciones

					ex.printStackTrace();

				} finally {

				}

				return flexColumns;

		}

		//ECM 08 ABRIL 2016 - Consulta Poderes Generales y Especiales - Status.
		public static ArrayList<ReporteAdmVigFlexColumn> getFlexColumns(
				int  idFlexTbl,
				ConnectionWrapper connect,
				String idEmpresa,
				ReporteAdmVigFlexTable tFlexTable,
				String tsIdMetaRow,
				ResultSet TresultSet,
				String tTipoPoder) {

				ArrayList<ReporteAdmVigFlexColumn> flexColumns = new ArrayList<ReporteAdmVigFlexColumn>();
				ResultSet set = null;
				PreparedStatement psmt = null;
				String sqlCampos =  "SELECT * FROM DERCORP_FLEX_COLUMS_TAB WHERE 1=1 ";
				sqlCampos += 		"AND ID_FLEX_TBL = " + idFlexTbl+" "       +
						            "AND DES_FLEX_COLUM IS NOT NULL AND (IND_SHOW_TBL_CON <> 0 OR IND_SHOW_TBL_CON IS NULL)";

				Fusion fusion = new Fusion();
				
				try {

					ConsultaDAO luConsultaDAO = new ConsultaDAO();
					ReferenciaDocumentum refDoc = new ReferenciaDocumentum(connect);
					RefDocNew refDocNew = new RefDocNew();
					AprobEjerSocCon aprobEjerSocCon = new AprobEjerSocCon();

					if(tTipoPoder.equals("Stat_Pode")){
						sqlCampos += luConsultaDAO.doQueriesStatPode(idFlexTbl);
						sqlCampos += refDoc.doQueryPodGenEspRefDoc(Integer.parseInt(tsIdMetaRow), idFlexTbl, Integer.parseInt(idEmpresa) );

					}else if(tTipoPoder.equals("Stat_RTot")){
						sqlCampos += luConsultaDAO.doQueriesStatRTot(idFlexTbl);
					}else if(tTipoPoder.equals("Stat_RPar")){
						sqlCampos += luConsultaDAO.doQueriesStatRPar(idFlexTbl);
					}else if(tTipoPoder.equals("Stat_Tran")){
						sqlCampos += luConsultaDAO.doQueriesStatRTrans(idFlexTbl);
					}else if(tTipoPoder.equals("Stat_AES")){
						sqlCampos += luConsultaDAO.doQueriesStatRAES(idFlexTbl);
					}else if(tTipoPoder.equals("Stat_EscOtros")){
						sqlCampos += luConsultaDAO.doQueriesStatEscOtros(idFlexTbl);
					}else if(tTipoPoder.equals("Stat_ActOtros")){
						sqlCampos += luConsultaDAO.doQueriesStatActaOtros(idFlexTbl);
					}else if(tTipoPoder.equals("Stat_AumCap")){
						sqlCampos += luConsultaDAO.doQueriesStatAumCap(idFlexTbl);
					}else if(tTipoPoder.equals("Stat_Cont")){
						sqlCampos += luConsultaDAO.doQueriesStatCont(idFlexTbl);
					}else if(tTipoPoder.equals("Stat_DecDiv")){
						sqlCampos += luConsultaDAO.doQueriesStatDecDiv(idFlexTbl);
					}else if(tTipoPoder.equals("Stat_DisCap")){
						sqlCampos += luConsultaDAO.doQueriesStatDisCap(idFlexTbl);
					}else if(tTipoPoder.equals("Stat_Esc")){
						sqlCampos += luConsultaDAO.doQueriesStatEscision(idFlexTbl);
					}else if(tTipoPoder.equals("Stat_Fusion")){
						sqlCampos += luConsultaDAO.doQueriesStatFusion(idFlexTbl);
						sqlCampos += fusion.getCapFijVarSocAumDisPr(Integer.parseInt(tsIdMetaRow), connect);
						sqlCampos += fusion.getCamposValoresFusion(Integer.parseInt(tsIdMetaRow), connect);

					}else if(tTipoPoder.equals("Stat_SesCon")){
						sqlCampos += luConsultaDAO.doQueriesStatSesionCons(idFlexTbl);
					}

					//ECM 20 Abril 2016 - Consulta Apoderados. Muestra o no. Poderes Generales y Especiales.
					if(idFlexTbl == 17 || idFlexTbl == 18){
							sqlCampos += luConsultaDAO.addQueryApoderados(idFlexTbl, idEmpresa, tsIdMetaRow);
							sqlCampos += refDoc.doQueryPodGenEspRefDoc(Integer.parseInt(tsIdMetaRow), idFlexTbl, Integer.parseInt(idEmpresa));
					}

                    //ECM 15 Junio 2016 - Consulta Reforma Total de Estatutos. Referencia Documentum.
					else if(idFlexTbl == 20){
						sqlCampos += refDoc.doQueryRefTotRefDoc(Integer.parseInt(tsIdMetaRow), idFlexTbl, Integer.parseInt(idEmpresa));

					}else if(idFlexTbl == 21){
						sqlCampos += refDoc.doQueryRefParRefDoc(Integer.parseInt(tsIdMetaRow), idFlexTbl, Integer.parseInt(idEmpresa));

					}else if(idFlexTbl == 23){
						sqlCampos += refDoc.doQueryAprobEjerSocRefDoc( Integer.parseInt(tsIdMetaRow), idFlexTbl, Integer.parseInt(idEmpresa) );
						sqlCampos += aprobEjerSocCon.getResultado(Integer.parseInt(tsIdMetaRow), connect);
						sqlCampos += aprobEjerSocCon.getOtrosAcuerdos(Integer.parseInt(tsIdMetaRow), connect);

					}else if(idFlexTbl == 22){
						sqlCampos += refDoc.doQueryTransfRefDoc( Integer.parseInt(tsIdMetaRow), idFlexTbl, Integer.parseInt(idEmpresa) );

					}else if(idFlexTbl == 28){
						sqlCampos += refDocNew.getCamposActaOtros(Integer.parseInt(tsIdMetaRow), connect);

					}else if(idFlexTbl == 29){
						sqlCampos += refDocNew.getCamposAumentoCapital(Integer.parseInt(tsIdMetaRow), connect);

					}else if(idFlexTbl == 31){
						sqlCampos += refDocNew.getCamposDecretoDividendos(Integer.parseInt(tsIdMetaRow), connect);

					}else if(idFlexTbl == 32){
						sqlCampos += refDocNew.getCamposDisminucionCapital(Integer.parseInt(tsIdMetaRow), connect);

					}else if(idFlexTbl == 33){
						sqlCampos += refDocNew.getCamposEscision(Integer.parseInt(tsIdMetaRow), connect);

					}else if(idFlexTbl == 34){
						sqlCampos += refDocNew.getCamposFusion(Integer.parseInt(tsIdMetaRow), connect);
						sqlCampos += fusion.getCapFijVarSocAumDisPr(Integer.parseInt(tsIdMetaRow), connect);
						sqlCampos += fusion.getCamposValoresFusion(Integer.parseInt(tsIdMetaRow), connect);

					}else if(idFlexTbl == 35 || idFlexTbl == 41){
						sqlCampos += refDocNew.getCamposSesionConsejo(Integer.parseInt(tsIdMetaRow), connect);

					}else if(idFlexTbl == 27){
						sqlCampos += refDocNew.getCamposEscrituraOtros(Integer.parseInt(tsIdMetaRow), connect);

					}else if(idFlexTbl == 30){
						sqlCampos += refDocNew.getCamposContratos(Integer.parseInt(tsIdMetaRow), connect);

					}

					sqlCampos += "ORDER BY DECODE(DES_GROUP,'GROUP',1,2), ID_ORDEN, TO_NUMBER(REPLACE(COD_FLEX_COLUM,'VAL_C',''))";

					psmt= connect.prepareStatement(sqlCampos);
					set = psmt.executeQuery();

					while (set.next()) {
						ReporteAdmVigFlexColumn newFlexColum = new ReporteAdmVigFlexColumn(set, set.getMetaData());
						flexColumns.add(newFlexColum);
					}

				} catch (Exception ex) {
					ex.printStackTrace();

				} finally {

				}

				return flexColumns;

		}
		
		public static ReporteAdmVigFlexColumn getFlexColumn(int  id, ConnectionWrapper connect) {

			ResultSet set = null;
			PreparedStatement psmt = null;

			try {
				
				String exceptionalCondition = "";
				

				String sqlCampos = "SELECT * FROM DERCORP_FLEX_COLUMS_TAB WHERE ID_FLEX_COLUM = " + id;

				psmt= connect.prepareStatement(sqlCampos);

				set = psmt.executeQuery();

				while (set.next()) {

					return new ReporteAdmVigFlexColumn(set, set.getMetaData());
				}

			} catch (Exception ex) {

				ex.printStackTrace();

			} finally {
				
				try{
					set.close();
					psmt.close();
				} catch (Exception ex){ 
					ex.printStackTrace();
				}

			}

			return null;
		}
		
        //JAMS 25 SEP 2017 - Sobrecargar Métodos. Consulta Adm y Vig
		public static ArrayList<ReporteAdmVigFlexColumn> getFlexColumnsExcel(int  idFlexTbl, ConnectionWrapper connect, 
                boolean consultaResumen, 
                HttpServletRequest request,
                String idEmpresa, 
                String otherCondition,
                boolean tbIsHorizontal,
                String tsAtributo13,
                boolean reporte
                ) {
          
             // String sConsulta = FacesUtils.getSessionBean().getEditCon();
			String sConsulta ="";
              
        

                if(request != null) {
                     //sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
                	 HttpSession session = (HttpSession) request.getSession(true);
               		SessionBean sessionBean = (SessionBean)session.getAttribute("sessionBean"); 
               		sConsulta = sessionBean.getEditCon();
                     idEmpresa = sessionBean.getIdCurrentEmpresa();
                }

                ArrayList<ReporteAdmVigFlexColumn> flexColumns = new ArrayList<ReporteAdmVigFlexColumn>();
                ResultSet set = null;
                PreparedStatement psmt = null;
                String sqlCampos = "";

                try {

                     String exceptionalCondition = "";
                     String lsOrderBy = "";
/*
                     if(consultaResumen) {
                    	 exceptionalCondition = " AND IND_SHOW_TBL_CON = 1";
                    	 lsOrderBy = "ORDER BY ID_ORDER_CON";
                     }else{
                    	 exceptionalCondition = " AND DES_SHOW_IN_TABLE = '1' ";
                    	 lsOrderBy = "ORDER BY ID_ORDEN";
                     }*/
                     if(consultaResumen) {
     					exceptionalCondition = " AND DES_SHOW_IN_TABLE = '1' ";
     					lsOrderBy = "ORDER BY ID_ORDEN";
     				}
                     int isCS = ReporteECSEstructuraCapitalSocial.isAsociacionCivil(Integer.parseInt(idEmpresa));//JJAQ
                     //ECM 08 Febrero 2016 Agregar ATRIBUTO3 al Order By. TRUE = Flextables Poderes, Reformas, Contratos y Escrituras Otros.
                     if(tbIsHorizontal){
                           //sqlCampos = "SELECT * FROM DERCORP_FLEX_COLUMS_TAB WHERE ID_FLEX_TBL = " + idFlexTbl + exceptionalCondition + otherCondition + " AND DES_FLEX_COLUM IS NOT NULL ORDER BY DECODE(DES_GROUP,'GROUP',1,2), TO_NUMBER(ATRIBUTO3), ID_ORDEN, TO_NUMBER(REPLACE(COD_FLEX_COLUM,'VAL_C',''))";
                    	 //Argu 06/08/2016
                    	 if(idFlexTbl == 30){
                    		 sqlCampos = "SELECT * FROM DERCORP_FLEX_COLUMS_TAB WHERE ID_FLEX_TBL = " + idFlexTbl + exceptionalCondition + otherCondition + " AND DES_FLEX_COLUM IS NOT NULL "+lsOrderBy;
                    	 }else if(idFlexTbl == 7 && isCS > 0){
                    		 sqlCampos = "SELECT * FROM DERCORP_FLEX_COLUMS_TAB WHERE ID_FLEX_TBL = " + idFlexTbl + exceptionalCondition + otherCondition + " AND DES_FLEX_COLUM IS NOT NULL AND ATRIBUTO1 = 'ISAC' "+lsOrderBy;
                    	 }else{
                    		 sqlCampos = "SELECT * FROM DERCORP_FLEX_COLUMS_TAB WHERE ID_FLEX_TBL = " + idFlexTbl + exceptionalCondition + otherCondition + " AND DES_FLEX_COLUM IS NOT NULL "+lsOrderBy;
                    	 }
                     }else{
                    	 if(idFlexTbl == 7 && isCS > 0){//JJAQ SE AGREGO IF Estructura C.S.
                    		 sqlCampos = "SELECT * FROM DERCORP_FLEX_COLUMS_TAB WHERE ID_FLEX_TBL = " + idFlexTbl + exceptionalCondition + otherCondition + " AND DES_FLEX_COLUM IS NOT NULL AND ATRIBUTO1 = 'ISAC' ORDER BY DECODE(DES_GROUP,'GROUP',1,2), ID_ORDEN, TO_NUMBER(REPLACE(COD_FLEX_COLUM,'VAL_C',''))";
                    	 }else{
                    		 sqlCampos = "SELECT * FROM DERCORP_FLEX_COLUMS_TAB WHERE ID_FLEX_TBL = " + idFlexTbl + exceptionalCondition + otherCondition + " AND DES_FLEX_COLUM IS NOT NULL ORDER BY DECODE(DES_GROUP,'GROUP',1,2), ID_ORDEN, TO_NUMBER(REPLACE(COD_FLEX_COLUM,'VAL_C',''))";
                    	 }
                           
                     }

                     //ECM 06 ABRIL 2016 Consulta Adm y Vig.
                     if(sConsulta == null || sConsulta.equals("") ){

                     }else{
                           if(tsAtributo13 != null && tsAtributo13.equals("ORDER_BY_CARGO"))
                                 //sqlCampos = "SELECT * FROM  DERCORP_FLEX_COLUMS_TAB WHERE 1=1 AND ID_FLEX_TBL = "+idFlexTbl+" AND ATRIBUTO5 IS NOT NULL ORDER BY ATRIBUTO5";
                        	   sqlCampos = "SELECT * FROM  DERCORP_FLEX_COLUMS_TAB WHERE 1=1 AND ID_FLEX_TBL = "+idFlexTbl+" AND (ATRIBUTO5 IS NOT NULL or COD_FLEX_COLUM = 'VAL_C8') ORDER BY ATRIBUTO5";
                        	   
                     }
                     //ordenamiento para el reporte de excel JAMS 26/02/2018
                     if(!sqlCampos.contains("ORDER BY")){
                    	 sqlCampos += "ORDER BY DECODE(DES_GROUP,'GROUP',1,2), ID_ORDEN, TO_NUMBER(REPLACE(COD_FLEX_COLUM,'VAL_C',''))";
                     }
                     
                     
                     psmt= connect.prepareStatement(sqlCampos);
                     set = psmt.executeQuery();

                     while (set.next()) {
                           ReporteAdmVigFlexColumn newFlexColum = new ReporteAdmVigFlexColumn(set, set.getMetaData());
                           flexColumns.add(newFlexColum);

                     }
                     /*
                     if(idFlexTbl == 7) {
                    	 ReporteECSEstructuraCapitalSocial ecs = new ReporteECSEstructuraCapitalSocial(connect, idEmpresa);
                           flexColumns = ecs.customizeFlexColumns(flexColumns);
                     }*/

                } catch (Exception ex) {

                     // TODO - Pendiente - Gestion de Excepciones

                     ex.printStackTrace();

                } finally {

                }

                return flexColumns;

}


}
