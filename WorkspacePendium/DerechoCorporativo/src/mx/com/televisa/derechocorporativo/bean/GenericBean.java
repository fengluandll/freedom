package mx.com.televisa.derechocorporativo.bean;

import java.util.Calendar;
import java.util.Date;
import java.util.Arrays;
import java.util.List;

import mx.com.televisa.derechocorporativo.daos.SqlParameter;

public class GenericBean<T>
{
    public List<String> propertys;
    public List<String> datatypes;

    public Object[] values;

    public Class<?> getTypeProperty(String property)
    {
        for (int i = 0; i < propertys.size(); i++)
        {
            if (propertys.get(i).toLowerCase().equals(property.toLowerCase()))
                return datatypes.get(i).getClass();
        }
        return null;
    }

    public T getProperty(Class<?> type,String property)
    {

        for (int i = 0; i < propertys.size(); i++)
        {
            if (propertys.get(i).toLowerCase().equals(property.toLowerCase()))
            {
                if (values[i] == null)
                {
                    return (T)null;
                }else
                return (T)values[i];

            }
        }
        return (T)null;
    }

    

    public void setProperty(String property, T value)
    {
    	if(values.length < propertys.size())
    		values = Arrays.copyOf(values,propertys.size());
    	
        for (int i = 0; i < propertys.size(); i++)
        {
        	
            if (propertys.get(i).toLowerCase().equals(property.toLowerCase()))
            {            		
                values[i] = value;
                return;
            }
        }
        
        propertys.add(property);
        datatypes.add("Object");
        values = Arrays.copyOf(values,values.length+1);
        values[values.length-1] = value;
    }

    public GenericBean(List<String> propertys, List<String> datatypes, Object[] values)
    {
        this.propertys = propertys;
        this.datatypes = datatypes;
        this.values = values;
    }

    public Object getProperty(String property)
    {
        for (int i = 0; i < propertys.size(); i++)
        {
        	String pty = propertys.get(i);
            if ( pty.toLowerCase().equals(property.toLowerCase()) )
                return values[i];
        }
        return null;
    }
    
    @SuppressWarnings("deprecation")
	public Date getStrToDateProperty(String property,String format)
    {
    	Object objdate = getProperty(property);
    	if(objdate==null)
    		return null;  
    	String date = objdate.toString();
        int day = 0,mth=0, year = 0;
        String sday ="", smth="", syear="";
        switch(format){
        	case "SHORT": 
        		sday = date.substring(0,2);    		
        		smth = date.substring(3,5);
        		syear =date.substring(6,10);
        		
        		day = Integer.parseInt(sday);    		
        		mth = Integer.parseInt(smth);
        		year = Integer.parseInt(syear);
        		   	
        		break;
        	case "SQL": 
        		day = Integer.parseInt(date.substring(10,2));    		
        		mth = Integer.parseInt(date.substring(7,2));
        		year = Integer.parseInt(date.substring(1,4));
        		 
        		break;    	
        }
        
        Calendar c = Calendar.getInstance();
        c.set(year, mth-1, day);
        Date d = c.getTime();
        
        return d;  	
        
    }

    public void addProperty(String property, SqlParameter sqlParameter)
    {
        boolean exists = false;
        for (int i = 0; i < propertys.size(); i++)
        {
            if (propertys.get(i).toCharArray().equals(property.toLowerCase()))
            {
                values[i] = sqlParameter.value;
                exists = true;
                break;
            }
        }
        if (!exists)
        {
            propertys.add(property);
            datatypes.add(String.valueOf(sqlParameter.type ));
            
            values = Arrays.copyOf(values, values.length + 1);
            values[values.length - 1] = sqlParameter.value;
        }

    }
}