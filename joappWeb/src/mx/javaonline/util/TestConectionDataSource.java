package mx.javaonline.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class TestConectionDataSource
 */
@WebServlet("/TestConectionDataSource")
public class TestConectionDataSource extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestConectionDataSource() {
       
        
    }
    
    public void doAction(HttpServletRequest request, HttpServletResponse response){
    	
        
    	System.out.println("Entro!!");
    	    Context envContext;
			try {
				PrintWriter out = response.getWriter();
		        response.setContentType("text/html");
				    envContext = new InitialContext();
				    DataSource ds = (DataSource)envContext.lookup("java:/comp/env/jdbc/joapp");
		            //DataSource ds = (DataSource)envContext.lookup("java:/comp/env/jdbc/testDB");
		            Connection con = ds.getConnection();
		            String sql = "SELECT username FROM login";
		            PreparedStatement st = con.prepareStatement(sql);
		            ResultSet rs = st.executeQuery();
		            
		            out.print("<center><h1>Conexion establecida</h1></center>");
		            out.print("<html><body>");
		            
		            
		            while(rs.next()){
		            	System.out.println(rs.getString(1));
		            	out.print("<h1>" + rs.getString(1) + "</h1>");
		            }
		            out.print("</body></html>");
			} catch (NamingException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
           
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request,response);
	}

}
