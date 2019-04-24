package com.movemini.flexreports;

import java.io.OutputStream;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletRequest;

import com.movemini.data.ConnectionWrapper;
import com.movemini.data.DataArray;
import com.movemini.data.OneRecordFactory;
import com.movemini.data.Record;
import com.movemini.simpleflexgrid.HTMLTable;
import com.movemini.simpleflexgrid.HTMLTableColumn;
import com.movemini.util.CSVUtil;
import com.movemini.util.DateUtils;
import com.movemini.util.StringUtils;

public class ReportGenericTable {


	private ServletRequest request;

	public ReportGenericTable(ServletRequest request) {

		this.request = request;
	}


	public String getHtml() {




		String id_report = request.getParameter("id_report");

		Record report = OneRecordFactory.getFirstRecord("select * from flex_report where id_report = " + id_report);


		ArrayList<String> paramValues = new ArrayList<String>();


		ArrayList<Record> paramDefs = DataArray.getArrayList("select * from flex_report_param where id_report = " + id_report);

		for (Record paramDef : paramDefs) {

			String code =  paramDef.get("code");

			paramValues.add(request.getParameter(code));

		}



		String[] paramValuesArray = new String[paramValues.size()];
		paramValuesArray = paramValues.toArray(paramValuesArray);












		StringBuilder sb = new StringBuilder();


		sb.append("<table class=\"table table-bordered\">");


		ConnectionWrapper conn = null;
		CallableStatement stmt = null;

		ResultSet set = null;

		try {

			conn = new ConnectionWrapper();
			System.out.println(report.get("stored_procedure"));
			stmt = conn.prepareCallProcedure(report.get("stored_procedure"), paramValuesArray);

			set = stmt.executeQuery();




			String columNamesLine = "<tr>";
			for (int colIndex = 1; colIndex <= set.getMetaData().getColumnCount(); colIndex++) {

				String colName = set.getMetaData().getColumnLabel(colIndex);

				columNamesLine += "<th>" + StringUtils.tipoTitulo(colName.trim().replace("_", " ")) + "" + "</th>";

			}

			columNamesLine += "</tr>";

			sb.append(columNamesLine);





			while(set.next()) {



				String recordTextLine = "<tr>";
				for (int colIndex = 1; colIndex <= set.getMetaData().getColumnCount(); colIndex++) {


					String value = set.getString(colIndex);

					if(value == null) {

						value = "";
					}

					recordTextLine += "<td>" + value.trim() + "</td>";
				}

				recordTextLine += "</tr>";

				sb.append(recordTextLine);



			}


			sb.append("</table>");




		}catch(Exception ex){

			//LOG EXCEPTION
			ex.printStackTrace();



		} finally {

			ConnectionWrapper.closeAll(set, stmt, conn);

		}











		return sb.toString();

	}




}
