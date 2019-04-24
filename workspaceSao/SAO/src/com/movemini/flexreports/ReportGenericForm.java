package com.movemini.flexreports;

import java.util.ArrayList;

import javax.servlet.ServletRequest;

import com.movemini.data.DataArray;
import com.movemini.data.Record;
import com.movemini.simpleflexgrid.HTMLForm;
import com.movemini.simpleflexgrid.HTMLTableColumn;

public class ReportGenericForm {

	private ServletRequest request;
	
	public ReportGenericForm(ServletRequest request) {

		this.request = request;
		init();
	}
	
	private void init() {

	}
	
	public String getFormHtml() {
		
		String id_report = request.getParameter("id_report");
		
		ArrayList<Record> params = DataArray.getArrayList("select * from flex_report_param where id_report = " + id_report);
	
		StringBuilder sb = new StringBuilder();

		ArrayList<HTMLTableColumn> columns = new ArrayList<HTMLTableColumn>();
	
        for(Record param : params) {
        
        	columns.add(new HTMLTableColumn(param.get("label")+":", param.get("code"), "left", 	param.get("type"), 	"","", param.get("catalog")));
        }

		String additionalAttributes = "";
		
		sb.append(HTMLForm.getFormHtml(columns, additionalAttributes));

		return sb.toString();
	}
		
}
