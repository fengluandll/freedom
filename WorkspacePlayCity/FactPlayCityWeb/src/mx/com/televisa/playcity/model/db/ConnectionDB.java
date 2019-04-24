/*
* Project: AISA
*
* File: ConnectionDB.java
*
* Created on: Abril 5, 2019 at 11:00
*
* Copyright (c) - Kaz Consulting - 2019
*/

package mx.com.televisa.playcity.model.db;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/*
* Conexión a BD   
* 
* @author Kaz Consulting / Jesus Argumedo
*
* @version 1.0.0
*
* @date Abril 5, 2019 at 11:00 am
*/

public class ConnectionDB {
	
	Connection poConnection;
	
	//Obtiene conexión con la BD
    public Connection getConexion() throws NamingException, SQLException{        
           Context loEnvContext = new InitialContext();
           DataSource loDataSource = 
        		   (DataSource)loEnvContext.lookup("jdbc/playCityDatasource");
           poConnection = loDataSource.getConnection();
        return poConnection;
       }
    
}
