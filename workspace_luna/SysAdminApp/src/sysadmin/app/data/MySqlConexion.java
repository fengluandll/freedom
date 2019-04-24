package sysadmin.app.data;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlConexion {

	public MySqlConexion() {
		// TODO Auto-generated constructor stub
		try{
            Class.forName(strSqlDriver);
            sqlConn = DriverManager.getConnection(strSqlServer,strSqlUser,strSqlPassword);
        }
        catch (Exception e){
            e.printStackTrace();
        }
	}
	
	private static Connection sqlConn;
    static String strSqlBD  = "sysapps";
    static String strSqlUser  = "root";
    static String strSqlPassword  = "Lupe12121531";
    static String strSqlServer  = "jdbc:mysql://localhost/"+strSqlBD;
    static String strSqlDriver = "com.mysql.jdbc.Driver";



    public static void ConectionMySql(){
        
    }

    public Connection getConexion(){
        return sqlConn;
    }

}
