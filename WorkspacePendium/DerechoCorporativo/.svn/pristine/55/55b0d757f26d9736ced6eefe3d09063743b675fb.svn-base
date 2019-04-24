package mx.com.televisa.derechocorporativo.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import mx.com.televisa.derechocorporativo.bean.GenericDataBean;
import mx.com.televisa.derechocorporativo.bean.reformas.AprobEjerSocBean;
import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;

class DAOModel
{

	final static Logger logger = Logger.getLogger(DAOModel.class);   

    public static SqlParameter createParam(String name, int type)
    {
    	return new SqlParameter(name,type);
    }    
    
    public static SqlParameter createParam(String name, int type, Object value)
    {
    	return new SqlParameter(name,type,value);
    }  
    
    public static List<Object> executeSp(String PKG,String spName, List<SqlParameter> args) throws Exception
    {
    	String sp = PKG+"."+ spName;
        String argsDebug = sp + " ";
        GenericDataBean gdb = null;
        ConnectionWrapper cnn = null;
        CallableStatement cmd = null;
        List<Object> results = new ArrayList<Object>();
        List<Integer> idsOut =  new ArrayList<Integer>();
        try
        {
        	cnn = new ConnectionWrapper();
        	cmd = cnn.prepareCall(sp,args.size());
        	
        	for(int i =0;i<args.size();i++){
        		SqlParameter arg = args.get(i);
        		if(arg.output){
        			cmd.registerOutParameter(i+1, arg.outType);
        			idsOut.add(i+1);
        		}else        		
        			cmd.setObject(i+1, arg.value);    
        		argsDebug+=arg.name+"=>"+arg.value+",";
        	}        	
			cmd.execute();	
					
			
			
			for(int i=0; i < idsOut.size(); i++)
				results.add(cmd.getObject(idsOut.get(i)));
			
			return results;
            
        }
        catch (Exception ex)
        {

            if (cmd != null)
            {
                cmd.close();                
            }
            logger.error("Exception running :"+argsDebug+"\n\n Details: "+ex.getMessage());
            throw new Exception("Exception DAO.execute sp: " + sp + " " + argsDebug + " details" + ex.getMessage(), ex);
        }
        finally
        {
            if (cmd != null)
            {
                cmd.close();
               
            }
            if(cnn != null){
            	cnn.close();
            }           
            
        }
        
    }

    //region GenericDataBeans

    public static GenericDataBean executeSpQueryGDB(String PKG,String sp, List<SqlParameter> args) throws Exception
    {
    	return executeSpQueryGDB(PKG+"."+ sp, args);
    }
    
    public static GenericDataBean executeSpQueryGDB(String sp, List<SqlParameter> args) throws Exception
    {
       
        String argsDebug = sp + " ";
        GenericDataBean gdb = null;
        ConnectionWrapper cnn = null;
        CallableStatement cmd = null;
        ResultSet resultSet = null;
        List<Object> results = new ArrayList<Object>();
        List<Integer> idsOut =  new ArrayList<Integer>();
        try
        {
        	cnn = new ConnectionWrapper();
        	cmd = args == null ? cnn.prepareCall(sp,0) : cnn.prepareCall(sp,args.size());
        	
        	for(int i =0;i<args.size();i++){
        		SqlParameter arg = args.get(i);        		
        		if(arg.output){
        			cmd.registerOutParameter(i+1, arg.outType);
        			idsOut.add(i+1);
        		}else        		
        			cmd.setObject(i+1, arg.value);    
        		argsDebug+=arg.name+"=>"+arg.value+",";
        	}        	
			cmd.execute();
			resultSet = ((OracleCallableStatement) cmd).getCursor(1);			
			
			for(int i=0; i < idsOut.size(); i++)
				results.add(cmd.getObject(idsOut.get(i)));
			
			gdb = new GenericDataBean(resultSet);
			return gdb;
            
        }
        catch (Exception ex)
        {

            if (cmd != null)
            {
                cmd.close();                
            }
            logger.error("Exception running :"+argsDebug+"\n\n Details: "+ex.getMessage());
            throw new Exception("Exception DAO.execute sp: " + sp + " " + argsDebug + " details" + ex.getMessage(), ex);
        }
        finally
        {
            if (cmd != null)
            {
                cmd.close();
               
            }
            if(cnn != null){
            	cnn.close();
            }
            if(!resultSet.isClosed()){
            	resultSet.close();
            }
            
        }
        
    }
    
    //endregion

}

