package mx.javaonline.model;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConectionWrapper {
	
	Connection con;
/*
    public Connection getConexion() throws NamingException, SQLException{
        
           Context envContext = new InitialContext();
           DataSource ds = (DataSource)envContext.lookup("java:/comp/env/jdbc/startonl_joapp");
           con = ds.getConnection();
        return con;
    }
    
  */
 /*   
    public Connection getConexionPre() throws NamingException, SQLException{
        
        Context envContext = new InitialContext();
        DataSource ds = (DataSource)envContext.lookup("java:/comp/env/jdbc/pre_db");
        con = ds.getConnection();
     return con;
    }
*/


    public Connection getConexion() throws NamingException, SQLException{
        
        Context envContext = new InitialContext();
        DataSource ds = (DataSource)envContext.lookup("java:/comp/env/jdbc/startonl_joapp");
        con = ds.getConnection();
     return con;
    }

 /*
	public Connection getConexionPre() throws NamingException, SQLException{
	     
	     Context envContext = new InitialContext();
	     DataSource ds = (DataSource)envContext.lookup("java:/comp/env/jdbc/startonl_pre_db");
	     con = ds.getConnection();
	  return con;
	 }
*/
}
