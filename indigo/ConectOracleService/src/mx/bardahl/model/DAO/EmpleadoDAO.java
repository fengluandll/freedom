package mx.bardahl.model.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import mx.bardahl.beans.CustomerAddressBean;
import mx.bardahl.beans.CustomerBean;
import mx.bardahl.beans.EmployeeBean;
import mx.bardahl.beans.GpsBean;
import mx.bardahl.model.ConectionWrapper;

public class EmpleadoDAO {
	
	ConectionWrapper conectionWrapper;
	Connection con;
	EmployeeBean employeeBean;
	CustomerBean customerBean;
	CustomerAddressBean customerAddressBean;
	Gson gson;
	List<CustomerBean> listCustomers;
	List<String> listAddres;
	
	public EmpleadoDAO(){
		conectionWrapper = new ConectionWrapper();
		gson = new Gson();
	}
	
	public String dameEmpleado(String employeeNumber){
		String sql = "select full_name,town_of_birth from per_all_people_f where EMPLOYEE_NUMBER = ?";
		try {
			con = conectionWrapper.getConexion();
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, employeeNumber);
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()){
				employeeBean = new EmployeeBean();
				employeeBean.setFullName(rs.getString(1));
				employeeBean.setFecNaci(rs.getString(2));
			}
		} catch (SQLException | NamingException e) {
			e.printStackTrace();
		}
		finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		String objJson = gson.toJson(employeeBean);
		return objJson;
	}
	
	public String dameCustomer(){
		listCustomers = new ArrayList<>();
		String sql = "select account_number,account_name,attribute6 from HZ_CUST_ACCOUNTS where rownum <= 10";
		try {
			con = conectionWrapper.getConexion();
			PreparedStatement psmt = con.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()){
				customerBean = new CustomerBean();
				customerBean.setAccountNumber(rs.getInt(1));
				customerBean.setAccountName(rs.getString(2));
				customerBean.setDescripcion(rs.getString(3));
				listCustomers.add(customerBean);
			}
		} catch (SQLException | NamingException e) {
			e.printStackTrace();
		}
		finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		String objJson = gson.toJson(listCustomers);
		return objJson;
	}
	
	public String misClientes(String vendedor){
		listCustomers = new ArrayList<>();
		String sql = "SELECT   num_cliente                       \n " +
					 "	      ,nombre_cliente                    \n " +
					 "FROM   Bolinf.Xxhz_Clientestoandroid_Vw    \n " + 
					 "WHERE  vendedor    = ?                     \n " +
					 "GROUP BY num_cliente, nombre_cliente          ";	
		try {
			con = conectionWrapper.getConexion();
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, vendedor);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()){
				customerBean = new CustomerBean();
				customerBean.setAccountNumber(rs.getInt(1));
				customerBean.setAccountName(rs.getString(2));
				listCustomers.add(customerBean);
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(con != null){
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		String objJson = gson.toJson(listCustomers);
		System.out.println("Gson "+objJson);
		return objJson;
	}
	
	public String getDirecciones(String vendedor){
		listCustomers = new ArrayList<>();
		String sql = "SELECT num_cliente, direccion \n"+
					 "	FROM   Bolinf.Xxhz_Clientestoandroid_Vw \n"+ 
					 "	WHERE  vendedor    = ? ";	
		try {
			con = conectionWrapper.getConexion();
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, vendedor);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()){
				customerBean = new CustomerBean();
				customerBean.setAccountNumber(rs.getInt(1));
				customerBean.setDireccion(rs.getString(2));
				listCustomers.add(customerBean);
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(con != null){
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		String objJson = gson.toJson(listCustomers);
		System.out.println("Gson "+objJson);
		return objJson;
	}
	
	public String getDireccionesCliente(String numCliente){
		listAddres = new ArrayList<>();
		String sql = "SELECT direccion \n "+
					 "	FROM   Bolinf.Xxhz_Clientestoandroid_Vw \n "+ 
					 "	WHERE  num_cliente    = ?";
		try {
			con = conectionWrapper.getConexion();
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, numCliente);
			ResultSet rs = psmt.executeQuery();
			String direccion = "";
			while(rs.next()){
				
				direccion = rs.getString(1);
				listAddres.add(direccion);
				
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(con != null){
					con.close();
				}				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		String objJson = gson.toJson(listAddres);
		return objJson;
		
	}
	
	public String insertGPS(String datos){
		System.out.println("Entro a insertGPS");
		System.out.println("datos "+datos);
		String bandera = "false";
		System.out.println("Antes del Gson ");
		List<GpsBean> listGps = gson.fromJson(datos, new TypeToken<List<GpsBean>>(){}.getType());
		System.out.println("Despues del Gson ");
		String delete = "DELETE FROM BOLINF.XXADR_GEOLOCALIZACION_TAB";
		String sql = "INSERT INTO  BOLINF.XXADR_GEOLOCALIZACION_TAB (NUM_LATITUD, \n"+
					 "      NUM_LONGITUD,\n"+
					 "      DES_DIRECCION,\n"+
					 "      DES_ID_DISPOSITIVO,\n"+
					 "      FEC_FECHA)\n"+
					 "      VALUES(?,?,?,?,to_date(?,'DD-MM-YY HH24:MI:SS'))";
		try {
			con = conectionWrapper.getConexion();
			PreparedStatement psmt = con.prepareStatement(sql);
//			PreparedStatement psmt2 = con.prepareStatement(delete);
//			psmt2.executeUpdate();
//			System.out.println("borro la tabla");
			 java.util.Date fechaEnviar = null;
			for(GpsBean gpsBean : listGps){
				psmt.setFloat(1, Float.parseFloat(gpsBean.getLatitud()));
				psmt.setFloat(2, Float.parseFloat(gpsBean.getLongitud()));
				psmt.setString(3, StringUtils.replace(gpsBean.getDireccion(), "Direcci�n:", ""));
				psmt.setString(4, gpsBean.getIdDispositivo());
				psmt.setString(5, gpsBean.getFecha());
				psmt.executeUpdate();
			}
			
			bandera = "true";
			
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}finally {
				try {
					if(con != null){
						con.close();
					}				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println(bandera);
		return bandera;
	}

}
