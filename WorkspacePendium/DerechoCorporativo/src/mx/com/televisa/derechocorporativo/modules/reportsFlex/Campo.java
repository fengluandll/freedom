package mx.com.televisa.derechocorporativo.modules.reportsFlex;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;
import mx.com.televisa.derechocorporativo.data.packages.DERCORP_REPORTFLEX_PKG;
import mx.com.televisa.derechocorporativo.modules.reports.tenecascada.TenenciaCascada;
import mx.com.televisa.derechocorporativo.reflexion.ReflexionUtil;

public class Campo {
	final static Logger log = Logger.getLogger(Campo.class);
	private int idAddCampo;
	private String nomCampo;
	private String desTipoCampo;
	private int idFlexTbl;
	private int idCatalogo;
	private String nomSeccion;
	private String nomSubseccion;

	

	public Campo(ResultSet set, ResultSetMetaData metaData) throws Exception {

		ReflexionUtil.fillObject(set, metaData, this, Campo.class);
	}

	public static ArrayList<Campo> getCampos(int idSeccion, int idSubSeccion, String filter, boolean showFlexTabs, boolean flexColumns) {
		

		ArrayList<Campo> rows = new ArrayList<Campo>();

		ConnectionWrapper connect = null;
		CallableStatement stmt = null;
		ResultSet set = null;

		try {

			connect = new ConnectionWrapper();
			
			stmt = connect.prepareCall(DERCORP_REPORTFLEX_PKG.GET_CAMPOS_PR);

			stmt.registerOutParameter(1, OracleTypes.CURSOR);
			
			stmt.setInt(2, idSeccion);
			stmt.setInt(3, idSubSeccion);
			log.info("idSeccion "+idSubSeccion);
			log.info("idSubSeccion "+idSubSeccion);
			stmt.setString(4, filter);
			
			stmt.setString(5, (showFlexTabs)?"SI":"NO");
			stmt.setString(6, (flexColumns)?"SI":"NO");
			
			stmt.execute();

			set = ((OracleCallableStatement) stmt).getCursor(1);

			while (set.next()) {

				rows.add(new Campo(set, set.getMetaData()));
			}

		} catch (Exception ex) {

			// TODO - Pendiente - Gestion de Excepciones
			ex.printStackTrace();

		} finally {
			
			ConnectionWrapper.closeAll(set, stmt, connect);
		}
		return rows;
	}
	
	
	public int getIdAddCampo() {
		return idAddCampo;
	}

	public void setIdAddCampo(int idAddCampo) {
		this.idAddCampo = idAddCampo;
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

	public int getIdFlexTbl() {
		return idFlexTbl;
	}

	public void setIdFlexTbl(int idFlexTbl) {
		this.idFlexTbl = idFlexTbl;
	}

	public int getIdCatalogo() {
		return idCatalogo;
	}

	public void setIdCatalogo(int idCatalogo) {
		this.idCatalogo = idCatalogo;
	}

	public String getNomSeccion() {
		return nomSeccion;
	}

	public void setNomSeccion(String nomSeccion) {
		this.nomSeccion = nomSeccion;
	}

	public String getNomSubseccion() {
		return nomSubseccion;
	}

	public void setNomSubseccion(String nomSubseccion) {
		this.nomSubseccion = nomSubseccion;
	}
}
