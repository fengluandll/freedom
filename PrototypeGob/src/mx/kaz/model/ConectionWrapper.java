package mx.kaz.model;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class ConectionWrapper {

	Connection con;
	
    public Connection getConexion() throws NamingException, SQLException{
        
           Context envContext = new InitialContext();
           DataSource ds = (DataSource)envContext.lookup("java:/comp/env/jdbc/startonl_sct_app");
           con = ds.getConnection();
        return con;
       }
}
