package mx.com.televisa.derechocorporativo.bean;

import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import mx.com.televisa.derechocorporativo.bean.reformas.AprobEjerSocBean;
import mx.com.televisa.derechocorporativo.daos.SqlParameter;

public class GenericDataBean
{
    public List<String> propertys;
    public List<String> datatypes;

    public List<Object[]> beans;
    
    final static Logger logger = Logger.getLogger(GenericDataBean.class);

    public GenericBean get(int index)
    {
        return new GenericBean(propertys, datatypes, beans.get(index));
    }

    public void addProperty(String pty){
    	for(int i=0; i<propertys.size(); i++){    		
    		if(propertys.get(i).toLowerCase().equals(pty.toLowerCase()))
    			return;
    	}
    	propertys.add(pty);
        datatypes.add("Object");
        for(int i=0; i<beans.size(); i++){  
        	Object[] values = beans.get(i);
        	values = Arrays.copyOf(values,values.length+1);
            values[values.length-1] = null;
    	}
    }
    	
    public GenericDataBean() { 
    	propertys = new ArrayList<String>();
        datatypes = new ArrayList<String>();
        beans = new ArrayList<Object[]>();
    }
    public GenericDataBean(ResultSet rs) throws Exception
    {
    	 
    	ResultSetMetaData rsmd = rs.getMetaData();   	 
    	 
    	
        propertys = new ArrayList<String>();
        datatypes = new ArrayList<String>();
        beans = new ArrayList<Object[]>();
        
        for (int i =1;i<=rsmd.getColumnCount(); i++)
        {
            propertys.add(rsmd.getColumnName(i).toLowerCase());
            datatypes.add(rsmd.getColumnTypeName(i));
        }
        
        while(rs.next()){       		
        	Object[] bean = new Object[rsmd.getColumnCount()]; 
        	for(int i =1;i<=rsmd.getColumnCount(); i++){
        		switch(datatypes.get(i-1)){
        			case "CLOB": 
        				if(rs.getObject(i)!=null)
        					bean[i-1] = convertClob2String(rs.getObject(i));
        				else
        					bean[i-1] = "";        			
        			break;
        			default: bean[i-1] = rs.getObject(i); break;
        		}
        		
        	}
			beans.add(bean);
        }
    }
    
    public String convertClob2String(final Object value)
    {
        final Clob clobValue = (Clob) value;
        String result = null;

        try
        {
            long clobLength = clobValue.length();

            if (clobLength < Integer.MIN_VALUE || clobLength > Integer.MAX_VALUE)
            {
            	logger.error("CLOB demasiado grande para String!");                    	
            }
            else
            {
                result = clobValue.getSubString(1, (int) clobValue.length());
            }
        }
        catch (SQLException ex)
        {
        	//System.out.println("tryClob2String ERROR:" + e);
        	logger.error(ex.getMessage());        
        }
        finally
        {
            if (clobValue != null)
            {
                try
                {
                    clobValue.free();
                }
                catch (SQLException ex)
                {
                	logger.error(ex.getMessage());        
                }
            }
        }

        return result;
    }
    
    public boolean exists(String property, Object value)
    {
        boolean rexists = false;
        for (int i = 0; i < beans.size(); i++)
        {
            if(get(i).getProperty(property).toString() == value.toString())
                return true; 
        }
        return rexists;
    }
}

