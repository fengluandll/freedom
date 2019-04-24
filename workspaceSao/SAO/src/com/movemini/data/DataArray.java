package com.movemini.data;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.movemini.debug.DebugTools;

public class DataArray {


	public static ArrayList<Record> getArrayList(String sql) {

		ArrayList<Record> list = new ArrayList<Record>();

		ConnectionWrapper conn = null;
		//Callable
		Statement stmt = null;

		ResultSet set = null;
		DebugTools.println("\tEjecutando SQL: " + sql,DataArray.class);
		try {

			conn = new ConnectionWrapper();
			conn.executeQuery("set @@sql_mode='';");
			//stmt = conn.prepareCallProcedure(procedure, params);

			set = conn.executeQuery(sql);

			while(set.next()) {

				list.add(new Record(set));
			}


			return list;

		}catch(Exception ex){

			//LOG EXCEPTION
			ex.printStackTrace();

			return null;

		} finally {

			ConnectionWrapper.closeAll(set, stmt, conn);

		}
	}





	public static ArrayList<Record> getArrayList(String procedure, Object ... params) {

		DebugTools.println("\tEjecutando procedure: " + procedure,DataArray.class);

		ArrayList<Record> list = new ArrayList<Record>();

		ConnectionWrapper conn = null;
		CallableStatement stmt = null;

		ResultSet set = null;

		try {

			conn = new ConnectionWrapper();

			stmt = conn.prepareCallProcedure(procedure, params);

			set = stmt.executeQuery();

			while(set.next()) {

				list.add(new Record(set));
			}


			return list;

		}catch(Exception ex){

			//LOG EXCEPTION
			ex.printStackTrace();

			return null;

		} finally {

			ConnectionWrapper.closeAll(set, stmt, conn);

		}
	}







	public static ArrayList<Record> getArrayList(ConnectionWrapper conn, String procedure, Object ... params) {

		ArrayList<Record> list = new ArrayList<Record>();


		CallableStatement stmt = null;

		ResultSet set = null;

		try {



			stmt = conn.prepareCallProcedure(procedure, params);

			set = stmt.executeQuery();

			while(set.next()) {

				list.add(new Record(set));
			}


			return list;

		}catch(Exception ex){

			//LOG EXCEPTION
			ex.printStackTrace();

			return null;

		} finally {

			//ConnectionWrapper.closeAll(set, stmt, conn);

		}
	}


}
