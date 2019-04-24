package mx.bardahl.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import mx.bardahl.beans.EmployeeBean;
import mx.bardahl.model.DAO.EmpleadoDAO;

/**
 * Servlet implementation class TestConServlet
 */
@WebServlet("/TestConServlet")
public class TestConServlet extends HttpServlet {
	
	Gson gson;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestConServlet() {
        super();
    }
    
    protected void procesaRequest(HttpServletRequest request, HttpServletResponse response){
    	EmpleadoDAO emp = new EmpleadoDAO();
		String sObject = emp.dameEmpleado("111112");
		gson = new Gson();
		EmployeeBean employeeBean = gson.fromJson(sObject, EmployeeBean.class);
		if(employeeBean != null){
			System.out.println(employeeBean.getFullName());
			System.out.println(employeeBean.getFecNaci());
		}
		emp.insertGPS("[{direccion:Toluqueños calle el coyoteCojo,latitud:9.24232,longitud:12.3023}]");
		
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		procesaRequest(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		procesaRequest(request,response);
	}

}
