package mx.com.televisa.derechocorporativo.modules.reports.tenecascada;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;
import mx.com.televisa.derechocorporativo.data.Record;
import mx.com.televisa.derechocorporativo.data.packages.XXTV_TEN_CASC_PKG;
import mx.com.televisa.derechocorporativo.modules.reportsFlex.ReportFlex;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

public class TenenciaCascada {
	final static Logger log = Logger.getLogger(TenenciaCascada.class);
	/**
	 * 
	 */
	public static String getTreeData(String empresa, String fecha,
														String paramConsolida, 
														String paramSegmento, 
														String paramClasificacion, 
														String paramPais, 
														String paranNumOracle, 
														String paramGiro,
														String paramPorcentaje,
														String paramSelectPorcentaje,
														String paramSelectPorcentajeCual,
														String selectPorcentajeVisualizar
														) {

		
		if(paramPorcentaje == null
				|| paramPorcentaje.equals("")){
			
			paramSelectPorcentaje = "2"; //Mayor que
			paramPorcentaje = "0";
			paramSelectPorcentajeCual = "Directo";
		}
		
		
		ConnectionWrapper connect = null;
		CallableStatement stmt = null;
		ResultSet set = null;

		try {

			connect = new ConnectionWrapper();

			stmt = connect.prepareCall(XXTV_TEN_CASC_PKG.GET_TREE_DATA);

			stmt.registerOutParameter(1, OracleTypes.CURSOR);
			
			stmt.setString(2, empresa);
			stmt.setString(3, fecha);
			
			stmt.setString(4, paramConsolida); 
			stmt.setString(5, paramSegmento); 
			stmt.setString(6, paramClasificacion); 
			stmt.setString(7, paramPais); 
			stmt.setString(8, paranNumOracle); 
			stmt.setString(9, paramGiro);
			stmt.setString(10, paramPorcentaje);
			stmt.setString(11, paramSelectPorcentaje);
			stmt.setString(12, paramSelectPorcentajeCual);
			stmt.setString(13, selectPorcentajeVisualizar);
			
			
			stmt.execute();
			
			set = ((OracleCallableStatement) stmt).getCursor(1);

			StringBuilder sb = new StringBuilder();
			
			while (set.next()) {

				sb.append(set.getString("TEXT"));
			}
			
			return sb.toString();
			
		} catch (Exception ex) {

			ex.printStackTrace();

			return null;
			
		} finally {

			ConnectionWrapper.closeAll(set, stmt, connect);
		}
	}
	
	
	public static ArrayList<TenCascRecord> getTreeData1(String empresa, String fecha,
											String paramConsolida, 
											String paramSegmento, 
											String paramClasificacion, 
											String paramPais, 
											String paranNumOracle, 
											String paramGiro,
											String paramPorcentaje,
											String paramSelectPorcentaje,
											String paramSelectPorcentajeCual,
											String selectPorcentajeVisualizar,
											String paramDivision
											) {


		if(paramPorcentaje == null
				|| paramPorcentaje.equals("")){
			
			paramSelectPorcentaje = "2"; //Mayor que
			paramPorcentaje = "0";
			paramSelectPorcentajeCual = "Directo";
		}
		long time_start, time_end;
		time_start = System.currentTimeMillis();
		
		ArrayList<TenCascRecord> rows = new ArrayList<TenCascRecord>();
		
		
		ConnectionWrapper connect = null;
		CallableStatement stmt = null;
		ResultSet set = null;

		try {

			connect = new ConnectionWrapper();
			time_start = System.currentTimeMillis();
			log.info("Ejecutando GET_TREE_DATA1: ");
			stmt = connect.prepareCall(XXTV_TEN_CASC_PKG.GET_TREE_DATA1);
			
			stmt.registerOutParameter(1, OracleTypes.CURSOR);
			if(paramConsolida.length()==0)
				paramConsolida = null;
			if(paramSegmento.length()==0)
				paramSegmento = null;
			if(paramClasificacion.length()==0)
				paramClasificacion = null;
			if(paramPais.length()==0)
				paramPais = null;
			if(paranNumOracle.length()==0)
				paranNumOracle = null;
			if(paramGiro.length()==0)
				paramGiro = null;
			if(paramDivision.length()==0)
				paramDivision = null;
			
			log.info("paramConsolida: "+paramConsolida);
			log.info("paramSegmento: "+paramSegmento);
			log.info("paramClasificacion: "+paramClasificacion);
			log.info("paramPais: "+paramPais);
			log.info("paranNumOracle: "+paranNumOracle);
			log.info("paramGiro: "+paramGiro);
			log.info("paramPorcentaje: "+paramPorcentaje);
			log.info("paramSelectPorcentaje: "+paramSelectPorcentaje);
			log.info("paramSelectPorcentajeCual: "+paramSelectPorcentajeCual);
			log.info("paramDivision: "+paramDivision);
			
			stmt.setString(2, empresa);
			stmt.setString(3, fecha);
			
			stmt.setString(4, paramConsolida); 
			stmt.setString(5, paramSegmento); 
			stmt.setString(6, paramClasificacion); 
			stmt.setString(7, paramPais); 
			stmt.setString(8, paranNumOracle); 
			stmt.setString(9, paramGiro);
			stmt.setString(10, paramPorcentaje);
			stmt.setString(11, paramSelectPorcentaje);
			stmt.setString(12, paramSelectPorcentajeCual);
			stmt.setString(13, selectPorcentajeVisualizar);
			stmt.setString(14, paramDivision);
			
			
			stmt.execute();
			time_end = System.currentTimeMillis();
			log.info("tiempo de ejecucion: "+ ( time_end - time_start ));
			set = ((OracleCallableStatement) stmt).getCursor(1);

			ResultSetMetaData metaData = set.getMetaData();
			
			time_start = System.currentTimeMillis();
			log.info("Ejecutando llenado de informacion: ");
			while (set.next()) {

				rows.add(new TenCascRecord(set, metaData));
			}
			time_end = System.currentTimeMillis();
			log.info("tiempo de ejecucion: "+ ( time_end - time_start ));
			return rows;
			
		} catch (Exception ex) {

			ex.printStackTrace();

			return null;
			
		} finally {

			ConnectionWrapper.closeAll(set, stmt, connect);
		}
		
	}

	public static ArrayList<Record> getTreeDataRows(String empresa, String fecha,
												String paramConsolida, 
												String paramSegmento,
												String paramClasificacion, 
												String paramPais, 
												String paranNumOracle,
												String paramGiro, 
												String paramPorcentaje,
												String paramSelectPorcentaje,
												String paramSelectPorcentajeCual) {

		if(paramPorcentaje == null
				|| paramPorcentaje.equals("")){
			
			paramSelectPorcentaje = "2"; //Mayor que
			paramPorcentaje = "0";
			paramSelectPorcentajeCual = "Directo";
		}
		
		ConnectionWrapper connect = null;
		CallableStatement stmt = null;
		ResultSet set = null;

		ArrayList<Record> records = new ArrayList<Record>(); 
		
		try {

			connect = new ConnectionWrapper();

			stmt = connect.prepareCall(XXTV_TEN_CASC_PKG.GET_TREE_DATA_RECORDS);

			stmt.registerOutParameter(1, OracleTypes.CURSOR);

			stmt.setString(2, empresa);
			stmt.setString(3, fecha);

			stmt.setString(4, paramConsolida);
			stmt.setString(5, paramSegmento);
			stmt.setString(6, paramClasificacion);
			stmt.setString(7, paramPais);
			stmt.setString(8, paranNumOracle);
			stmt.setString(9, paramGiro);
			stmt.setString(10, paramPorcentaje);
			stmt.setString(11, paramSelectPorcentaje);
			stmt.setString(12, paramSelectPorcentajeCual);

			stmt.execute();

			set = ((OracleCallableStatement) stmt).getCursor(1);


			while (set.next()) {
				
				records.add(new Record(set));				
			}

			return records;

		} catch (Exception ex) {

			ex.printStackTrace();

			return null;

		} finally {

			ConnectionWrapper.closeAll(set, stmt, connect);
		}
	}
	
	/**
	 * 
	 */
	public static String getTreeDataGrafico(String empresa, String fecha,
															String paramConsolida, 
															String paramSegmento, 
															String paramClasificacion, 
															String paramPais, 
															String paranNumOracle, 
															String paramGiro,
															String paramPorcentaje,
															String paramSelectPorcentaje,
															String paramSelectPorcentajeCual) {

		if(paramPorcentaje == null
				|| paramPorcentaje.equals("")){
			
			paramSelectPorcentaje = "2"; //Mayor que
			paramPorcentaje = "0";
			paramSelectPorcentajeCual = "Directo";
		}
		
		ConnectionWrapper connect = null;
		CallableStatement stmt = null;
		ResultSet set = null;

		try {

			connect = new ConnectionWrapper();

			stmt = connect.prepareCall(XXTV_TEN_CASC_PKG.GET_TREE_DATA2);

			stmt.registerOutParameter(1, OracleTypes.CURSOR);
			
			stmt.setString(2, empresa);
			stmt.setString(3, fecha);
			

			stmt.setString(4, paramConsolida); 
			stmt.setString(5, paramSegmento); 
			stmt.setString(6, paramClasificacion); 
			stmt.setString(7, paramPais); 
			stmt.setString(8, paranNumOracle); 
			stmt.setString(9, paramGiro);
			stmt.setString(10, paramPorcentaje);
			stmt.setString(11, paramSelectPorcentaje);	
			stmt.setString(12, paramSelectPorcentajeCual);
			
			stmt.execute();
			
			set = ((OracleCallableStatement) stmt).getCursor(1);

			StringBuilder sb = new StringBuilder();
			
			while (set.next()) {

				sb.append(set.getString("TEXT"));
			}
			
			return sb.toString();
			
		} catch (Exception ex) {

			ex.printStackTrace();

			return null;
			
		} finally {

			ConnectionWrapper.closeAll(set, stmt, connect);
		}
	}

	
	public static String getTreeDataOrgChart(String empresa, String fecha,
															String paramConsolida, 
															String paramSegmento, 
															String paramClasificacion, 
															String paramPais, 
															String paramNoEmpOracle, 
															String paramGiro,
															String paramPorcentaje,
															String paramSelectPorcentaje,
															String paramSelectPorcentajeCual) {
		
		System.out.println("empresa  "+empresa);
		System.out.println("fecha  "+fecha);
		System.out.println("paramConsolida  "+paramConsolida);
		System.out.println("paramSegmento  "+paramSegmento);
		System.out.println("paramClasificacion  "+paramClasificacion);
		System.out.println("paramPais  "+paramPais);
		System.out.println("paramNoEmpOracle  "+paramNoEmpOracle);
		System.out.println("paramGiro  "+paramGiro);
		System.out.println("paramPorcentaje  "+paramPorcentaje);
		System.out.println("paramSelectPorcentaje  "+paramSelectPorcentaje);
		System.out.println("paramSelectPorcentajeCual  "+paramSelectPorcentajeCual);


		if(paramPorcentaje == null
				|| paramPorcentaje.equals("")){
			
			paramSelectPorcentaje = "2"; //Mayor que
			paramPorcentaje = "0";
			paramSelectPorcentajeCual = "Directo";
		}
		
		ConnectionWrapper connect = null;
		CallableStatement stmt = null;
		ResultSet set = null;

		try {

			connect = new ConnectionWrapper();

			stmt = connect.prepareCall(XXTV_TEN_CASC_PKG.GET_TREE_DATA3);

			stmt.registerOutParameter(1, OracleTypes.CURSOR);
			
			stmt.setString(2, empresa);
			stmt.setString(3, fecha);
			

			stmt.setString(4, paramConsolida); 
			stmt.setString(5, paramSegmento); 
			stmt.setString(6, paramClasificacion); 
			stmt.setString(7, paramPais); 
			stmt.setString(8, paramNoEmpOracle); 
			stmt.setString(9, paramGiro);
			stmt.setString(10, paramPorcentaje);
			stmt.setString(11, paramSelectPorcentaje);
			stmt.setString(12, paramSelectPorcentajeCual);
			
			
			
			stmt.execute();
			
			set = ((OracleCallableStatement) stmt).getCursor(1);

			StringBuilder sb = new StringBuilder();
			
			while (set.next()) {

				sb.append(set.getString("TEXT"));
			}
			
			return sb.toString();
			
		} catch (Exception ex) {

			ex.printStackTrace();

			return null;
			
		} finally {

			ConnectionWrapper.closeAll(set, stmt, connect);
		}
	}

	public static String getTreeDataOrgChartGoogle(String empresa, String fecha,
			String paramConsolida, 
			String paramSegmento, 
			String paramClasificacion, 
			String paramPais, 
			String paramNoEmpOracle, 
			String paramGiro,
			String paramPorcentaje,
			String paramSelectPorcentaje,
			String paramSelectPorcentajeCual,
			String selectPorcentajeVisualizar) {

		if(paramPorcentaje == null
		|| paramPorcentaje.equals("")){
		
		paramSelectPorcentaje = "2"; //Mayor que
		paramPorcentaje = "0";
		paramSelectPorcentajeCual = "Directo";
		}
		
		ConnectionWrapper connect = null;
		CallableStatement stmt = null;
		ResultSet set = null;
		
		try {
		
			connect = new ConnectionWrapper();
			
			stmt = connect.prepareCall(XXTV_TEN_CASC_PKG.GET_TREE_DATA4);
			
			stmt.registerOutParameter(1, OracleTypes.CURSOR);
			
			log.info("empresa "+empresa);
			log.info("fecha "+fecha);
			log.info("paramConsolida "+paramConsolida);
			log.info("paramSegmento "+paramSegmento);
			log.info("paramClasificacion "+paramClasificacion);
			log.info("paramPais "+paramPais);
			log.info("paramNoEmpOracle "+paramNoEmpOracle);
			log.info("paramGiro "+paramGiro);
			log.info("paramPorcentaje "+paramPorcentaje);
			log.info("paramSelectPorcentaje "+paramSelectPorcentaje);
			log.info("paramSelectPorcentajeCual "+paramSelectPorcentajeCual);
			log.info("selectPorcentajeVisualizar "+selectPorcentajeVisualizar);
			
			stmt.setString(2, empresa);
			stmt.setString(3, fecha);
			
			
			stmt.setString(4, paramConsolida); 
			stmt.setString(5, paramSegmento); 
			stmt.setString(6, paramClasificacion); 
			stmt.setString(7, paramPais); 
			stmt.setString(8, paramNoEmpOracle); 
			stmt.setString(9, paramGiro);
			stmt.setString(10, paramPorcentaje);
			stmt.setString(11, paramSelectPorcentaje);
			stmt.setString(12, paramSelectPorcentajeCual);
			stmt.setString(13, selectPorcentajeVisualizar);
			
			
			stmt.execute();
			
			set = ((OracleCallableStatement) stmt).getCursor(1);
			
			StringBuilder sb = new StringBuilder();
			
			while (set.next()) {
				sb.append(set.getString("TEXT"));
				//System.out.println(set.getString("TEXT"));
			}
			
			return sb.toString();
			
		} catch (Exception ex) {
		
			ex.printStackTrace();
		
			return null;
		
		} finally {		
			ConnectionWrapper.closeAll(set, stmt, connect);
		}
		
	}
	
}
