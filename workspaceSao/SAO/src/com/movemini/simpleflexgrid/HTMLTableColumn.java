package com.movemini.simpleflexgrid;

public class HTMLTableColumn {
	
	private String headerText;
	private String code;
	private String align;
	private String type;
	private String jsCode;
	private String attributes;
	private String catalog;
	
	public HTMLTableColumn(String headerText, String code, String align,
			String type, String jsCode) {
		
		
		this.headerText = headerText;
		this.code = code;
		this.align = align;
		this.type = type;
		this.jsCode = jsCode;
	}

	public HTMLTableColumn(String headerText, String code, String align,
			String type, String jsCode, String attributes) {
		
		this.headerText = headerText;
		this.code = code;
		this.align = align;
		this.type = type;
		this.jsCode = jsCode;
		this.attributes = attributes;
	}
	
	public HTMLTableColumn(String headerText, String code, String align,
			String type, String jsCode, String attributes, String catalog) {
		
		
		this.headerText = headerText;
		this.code = code;
		this.align = align;
		this.type = type;
		this.jsCode = jsCode;
		this.attributes = attributes;
		this.catalog = catalog;
	}


	public String getHeaderText() {
		return headerText;
	}


	public void setHeaderText(String headerText) {
		this.headerText = headerText;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getAlign() {
		return align;
	}


	public void setAlign(String align) {
		this.align = align;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getJsCode() {
		return jsCode;
	}


	public void setJsCode(String jsCode) {
		this.jsCode = jsCode;
	}
	
	public String getAttributes() {
		return attributes;
	}
	
	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}
	
	
	public String getCatalog() {
		return catalog;
	}
	
	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}
}
