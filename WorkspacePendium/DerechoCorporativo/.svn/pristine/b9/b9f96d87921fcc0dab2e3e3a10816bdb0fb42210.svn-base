package mx.com.televisa.derechocorporativo.modules.reports.ecs;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;


import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;
import mx.com.televisa.derechocorporativo.data.packages.DERCORP_REPORTFLEX_PKG;
import mx.com.televisa.derechocorporativo.reflexion.ReflexionUtil;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

import org.apache.log4j.Logger;

public class ReporteECSField {
	
	public int idCampo; 
	public int idSeccionRow;
	public int idAddCampo;
	public int idOrder;
	
	public String atributo1;
	
	public String nomCampo;
	public String desTipoCampo;
	public int idCatalogo;
	public int idFlexTbl;
	 
     
	final static Logger log = Logger.getLogger(ReporteECSField.class);
	

	public ReporteECSField(ResultSet set, ResultSetMetaData metaData) throws Exception {

		ReflexionUtil.fillObject(set, metaData, this, ReporteECSField.class);
	}

	
	public static ArrayList<ReporteECSField> getFields(int idSeccionRow, ConnectionWrapper connect,int idEmpresa) {

		ArrayList<ReporteECSField> rows = new ArrayList<ReporteECSField>();

		
		CallableStatement stmt = null;
		ResultSet set = null;

		try {

			stmt = connect.prepareCall(DERCORP_REPORTFLEX_PKG.GET_FIELD_ECS_PR);

			stmt.registerOutParameter(1, OracleTypes.CURSOR);
			
			
			stmt.setInt(2, idSeccionRow);
			stmt.setInt(3, idEmpresa);
			

			stmt.execute();

			set = ((OracleCallableStatement) stmt).getCursor(1);

			while (set.next()) {

				rows.add(new ReporteECSField(set, set.getMetaData()));
			}

		} catch (Exception ex) {

			// TODO - Pendiente - Gestion de Excepciones
			ex.printStackTrace();

		} finally {
			
			ConnectionWrapper.closeAll(set, stmt);
		}
		return rows;
	}

	
	public static ReporteECSField getFieldById(String idCampo) {

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
			
				return new ReporteECSField(set, set.getMetaData());
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
