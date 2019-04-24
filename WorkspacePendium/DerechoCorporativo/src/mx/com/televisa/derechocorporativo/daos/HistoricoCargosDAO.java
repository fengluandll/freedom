package mx.com.televisa.derechocorporativo.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.StringTokenizer;

import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;

public class HistoricoCargosDAO {

   private PreparedStatement puPreparedStatement;
   private ResultSet         puResultSet;
   private ConnectionWrapper puConnectionWrapper;

   public HistoricoCargosDAO(){
		 puPreparedStatement = null;
		 puResultSet = null;
		 puConnectionWrapper = null;
		
   }

   public String getNomFlex(String tsIdsFlexTable){

	   String[] lsIdsFlexTable = tsIdsFlexTable.split(",");

	   String lsSql = "SELECT  NOM_FLEX "              +
                      "FROM    DERCORP_FLEX_TBLS_TAB " +
                      "WHERE   1=1 "                   +
                      "AND     ID_FLEX_TBL IN (?) "
			          ;

	   StringBuilder lsNomFlex = new StringBuilder();

	   try{
			puConnectionWrapper = new ConnectionWrapper();
			puPreparedStatement = puConnectionWrapper.prepareStatement(lsSql);
			
            for(String lsIdFlxTbl : lsIdsFlexTable){
            	if(lsIdFlxTbl.trim().equals("99"))
            		lsNomFlex.append("Apoderados\n");
            	else{
	    			puPreparedStatement.setInt(1, Integer.parseInt(lsIdFlxTbl.trim()) );
	    			puResultSet = puPreparedStatement.executeQuery();
	
	    			while(puResultSet.next()){
	    				lsNomFlex.append(puResultSet.getString("NOM_FLEX")+"\n");
	    			}
            	}
            }
			


	   }catch(SQLException sqle){
			sqle.printStackTrace();
			sqle.getSQLState();

	   }catch(Exception e){
			e.printStackTrace();
			e.getMessage();

	   }finally{
		   puConnectionWrapper.close();
		   try {
				puPreparedStatement.close();
				//puResultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	   }

	   return lsNomFlex.toString();

   }

}