package mx.gob.tsjdf.cfdi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import mx.gob.tsjdf.cfdi.dto.PagoEmpleadoDto;
import mx.gob.tsjdf.db.ConnectionDB;

public class PagoEmpleadoDao
{
	
	ConnectionDB connectionDB;
	Connection   conn;
	
  public ArrayList<PagoEmpleadoDto> consultarPagosEmpleado(String numeroEmpleado, String anio){
	  
    ArrayList<PagoEmpleadoDto> listaPagosEmpleado = new ArrayList<>();
    connectionDB = new ConnectionDB();
    
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try{
    	conn = connectionDB.getConexionCFDI();
      if (conn != null)
      {
        pstmt = conn.prepareStatement(Queries.getQueryPagosEmpleado());
        
        pstmt.setString(1, numeroEmpleado);
        pstmt.setString(2, anio);
        pstmt.setString(3, numeroEmpleado);
        pstmt.setString(4, anio);
        
        rs = pstmt.executeQuery();
        while (rs.next())
        {
          PagoEmpleadoDto dao = new PagoEmpleadoDto();
          dao.setFechaPago(rs.getString(1));
          dao.setDescripcion(rs.getString(2));
          dao.setFechaInicio(rs.getString(3));
          dao.setFechaFin(rs.getString(4));
          listaPagosEmpleado.add(dao);
        }
        rs.close();
        pstmt.close();
        conn.close();
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      if (rs != null) {
        try
        {
          rs.close();
        }
        catch (Exception localException1) {}
      }
      if (pstmt != null) {
        try
        {
          pstmt.close();
        }
        catch (Exception localException2) {}
      }
      if (conn != null) {
        try
        {
          conn.close();
        }
        catch (Exception localException3) {}
      }
    }
    finally
    {
      if (rs != null) {
        try
        {
          rs.close();
        }
        catch (Exception localException4) {}
      }
      if (pstmt != null) {
        try
        {
          pstmt.close();
        }
        catch (Exception localException5) {}
      }
      if (conn != null) {
        try
        {
          conn.close();
        }
        catch (Exception localException6) {}
      }
    }
    return listaPagosEmpleado;
  }
}
