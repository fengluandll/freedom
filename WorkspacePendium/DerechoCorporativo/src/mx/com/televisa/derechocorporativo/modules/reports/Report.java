package mx.com.televisa.derechocorporativo.modules.reports;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;
import mx.com.televisa.derechocorporativo.data.packages.DERCORP_REPORTS_PKG;
import mx.com.televisa.derechocorporativo.reflexion.ReflexionUtil;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

public class Report {

	int idReporte;
	String nomReporte;
	String desReporte;
	String desUrl;

	public Report(ResultSet set, ResultSetMetaData metaData) throws Exception {

		ReflexionUtil.fillObject(set, metaData, this, Report.class);
	}

	public static ArrayList<Report> getReports(int idRol) {

		// String idEmpresa = FacesUtils.getSessionBean().getIdCurrentEmpresa();

		ArrayList<Report> rows = new ArrayList<Report>();

		ConnectionWrapper connect = null;
		CallableStatement stmt = null;
		ResultSet set = null;

		try {

			connect = new ConnectionWrapper();

			stmt = connect.prepareCall(DERCORP_REPORTS_PKG.GET_REPORTES_PR);

			stmt.setInt(1, idRol);
			stmt.registerOutParameter(2, OracleTypes.CURSOR);

			stmt.execute();

			set = ((OracleCallableStatement) stmt).getCursor(2);

			while (set.next()) {

				rows.add(new Report(set, set.getMetaData()));
			}

		} catch (Exception ex) {

			// TODO - Pendiente - Gestion de Excepciones
			ex.printStackTrace();

		} finally {

			ConnectionWrapper.closeAll(set, stmt, connect);
		}

		return rows;
	}

	public int getIdReporte() {
		return idReporte;
	}

	public void setIdReporte(int idReporte) {
		this.idReporte = idReporte;
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

	public String getDesUrl() {
		return desUrl;
	}

	public void setDesUrl(String desUrl) {
		this.desUrl = desUrl;
	}

}
