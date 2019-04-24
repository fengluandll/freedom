package mx.com.televisa.derechocorporativo.daos;

import oracle.jdbc.OracleTypes;

public class SqlParameter {
	public String name;
	public int type;
	public Object value;
	public boolean output;
	public int outType;
	
	public SqlParameter(String name, int type){
		this.name = name;
		this.value = null;
		this.type = type;		
		this.outType=type;
		this.output=true;
	}
	
	public SqlParameter(String name, int type, Object value){
		this.name = name;
		this.value = value;
		this.type = type;	
		this.outType=0;
		this.output=false;
	}
}
