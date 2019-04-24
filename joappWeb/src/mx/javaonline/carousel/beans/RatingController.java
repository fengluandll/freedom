package mx.javaonline.carousel.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.javaonline.beans.StudentPersonalBean;
import mx.javaonline.model.ConectionWrapper;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RateEvent;

public class RatingController {
	
	private static org.apache.log4j.Logger logger = Logger.getLogger(RatingController.class);
	private Integer valorRating;
	ConectionWrapper conectionWrapper;
	Connection con;
	FacesContext facesContext = FacesContext.getCurrentInstance();
	HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
	private String commentFiveStarts;

	public RatingController() {
		conectionWrapper = new ConectionWrapper();
	}
	
	public void howManyRates(RateEvent rateEvent){
		logger.debug("Entro al rates "+ ((Integer) rateEvent.getRating()).intValue());
	}
	
	 public void onrate(RateEvent rateEvent) {
		     int rateValue = ((Integer) rateEvent.getRating()).intValue();
			 StudentPersonalBean studentPersonalBean = (StudentPersonalBean)session.getAttribute("studentPersonalBean");
			 int studentPersonalId = studentPersonalBean.getStudentPersonalId();
			 String isPerOrStudent = studentPersonalBean.getSegment1();
			 //Map<String,String> params = facesContext.getExternalContext().getRequestParameterMap();
			 String unitId = (String) session.getAttribute("unitId");
			  
			 String exiteUniPerStu = null;
			 String insertUniPerStu = null;
			 String updateUniPerStu = null;
			 String updatefiveStarts = null;
			  try {
				con = conectionWrapper.getConexion();
				if(isPerOrStudent.equals("P")){
					exiteUniPerStu = "SELECT  count(status_unit_id) \n"+
							   " FROM status_unit_personal\n"+
								"WHERE personal_id = ? \n"+
								"AND   unit_id = ?";
					insertUniPerStu	= "INSERT INTO status_unit_personal (personal_id, \n"+
									  "					 unit_id,\n"+
									  "					 rate_unit)\n"+
						              " VALUES (?,?,?)";
					updateUniPerStu = " UPDATE status_unit_personal \n"+
									  "	 SET rate_unit=? \n"+
									  "	 WHERE unit_id=? \n"+
									  "	 AND personal_id = ?";
					updatefiveStarts = " UPDATE status_unit_personal \n"+
									  "	 SET segment1=? \n"+
									  "	 WHERE unit_id=? \n"+
									  "	 AND student_id = ?";
				}else if(isPerOrStudent.equals("S")){
					exiteUniPerStu = "SELECT  count(status_unit_id) \n"+
							   " FROM status_unit_student\n"+
								"WHERE student_id = ? \n"+
								"AND   unit_id = ?";
					insertUniPerStu	= "INSERT INTO status_unit_student (student_id, \n"+
							  "					 unit_id,\n"+
							  "					 rate_unit)\n"+
				              " VALUES (?,?,?)";
					updateUniPerStu = " UPDATE status_unit_student \n"+
							  "	 SET rate_unit=? \n"+
							  "	 WHERE unit_id=? \n"+
							  "	 AND student_id = ?";
					updatefiveStarts = " UPDATE status_unit_student \n"+
							  "	 SET segment1=? \n"+
							  "	 WHERE unit_id=? \n"+
							  "	 AND student_id = ?";
				}
				
				PreparedStatement psmt =  con.prepareStatement(exiteUniPerStu);
				psmt.setInt(1,studentPersonalId);
				psmt.setString(2,unitId);
				ResultSet rs = psmt.executeQuery();
				int existe = 0;
				while(rs.next()){
					existe = rs.getInt(1);
				}
				/*logger.debug("existe "+existe);
				logger.debug("studentPersonalId "+studentPersonalId);
				logger.debug("unitId "+unitId);
				logger.debug("rateValue "+rateValue);*/
				if (existe == 0){
					PreparedStatement psmtIntert =  con.prepareStatement(insertUniPerStu);
					psmtIntert.setInt(1,studentPersonalId);
					psmtIntert.setString(2,unitId);
					psmtIntert.setInt(3,rateValue);
					psmtIntert.executeUpdate();
				}else if (existe > 0){
					PreparedStatement psmtUpdate =  con.prepareStatement(updateUniPerStu);
					psmtUpdate.setInt(1,rateValue);
					psmtUpdate.setString(2,unitId);
					psmtUpdate.setInt(3,studentPersonalId);
					psmtUpdate.executeUpdate();
				}
				 logger.debug("rateValue " +rateValue);
				if(rateValue == 5){
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Gracias","Nos alegra que te haya gustado." ); 
				      FacesContext.getCurrentInstance().addMessage("thanksFiveStarts", message);
				        PreparedStatement psmtUpdate =  con.prepareStatement(updatefiveStarts);
						psmtUpdate.setString(1,"Excelente");
						psmtUpdate.setString(2,unitId);
						psmtUpdate.setInt(3,studentPersonalId);
						psmtUpdate.executeUpdate();
				      /*
					  ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
					  HttpServletResponse response = (HttpServletResponse) context.getResponse();
					  response.setStatus(307);
					  */
				}else if(rateValue < 5){
					RequestContext.getCurrentInstance().execute("window.parent.dlgRate.show();");
				}
				
			} catch (NamingException | SQLException e) {
				logger.error(e);
			}finally{
				try {
					con.close();
				} catch (SQLException e) {
					logger.error(e);
				}
			}
			 
		 		  		
	   }
	
	 
	 public void oncancel() {  
		 StudentPersonalBean studentPersonalBean = (StudentPersonalBean)session.getAttribute("studentPersonalBean");
		 int studentPersonalId = studentPersonalBean.getStudentPersonalId();
		 String isPerOrStudent = studentPersonalBean.getSegment1();
		 //Map<String,String> params = facesContext.getExternalContext().getRequestParameterMap();
		 String unitId = (String) session.getAttribute("unitId");
		 String updateUniPerStu = null;
		 try {
				con = conectionWrapper.getConexion();
				if(isPerOrStudent.equals("P")){
					updateUniPerStu = " UPDATE status_unit_personal \n"+
									  "	 SET rate_unit=? \n"+
									  "	 WHERE unit_id=? \n"+
									  "	 AND personal_id = ?";
				}else if(isPerOrStudent.equals("S")){
					updateUniPerStu = " UPDATE status_unit_student \n"+
							  "	 SET rate_unit=? \n"+
							  "	 WHERE unit_id=? \n"+
							  "	 AND student_id = ?";
				}
			PreparedStatement psmtUpdate =  con.prepareStatement(updateUniPerStu);
			psmtUpdate.setInt(1,0);
			psmtUpdate.setString(2,unitId);
			psmtUpdate.setInt(3,studentPersonalId);
			psmtUpdate.executeUpdate();
		 }catch (NamingException | SQLException e) {
				logger.error(e);
			}finally{
				try {
					con.close();
				} catch (SQLException e) {
					logger.error(e);
				}
			}
	    }

	public Integer getValorRating() {
		StudentPersonalBean studentPersonalBean = (StudentPersonalBean)session.getAttribute("studentPersonalBean");
		 int studentPersonalId = studentPersonalBean.getStudentPersonalId();
		 String isPerOrStudent = studentPersonalBean.getSegment1();
		// Map<String,String> params = facesContext.getExternalContext().getRequestParameterMap();
		 logger.debug("session.getAttributeUnidID "+session.getAttribute("unitId"));
		 String unitId = (String) session.getAttribute("unitId");
		 String exiteUniPerStu = null;
		 
		 try{
		    con = conectionWrapper.getConexion();
			if(isPerOrStudent.equals("P")){
				exiteUniPerStu = "SELECT  rate_unit \n"+
						   " FROM status_unit_personal\n"+
							"WHERE personal_id = ? \n"+
							"AND   unit_id = ?";
			}else if(isPerOrStudent.equals("S")){
				exiteUniPerStu = "SELECT  rate_unit \n"+
						   " FROM status_unit_student\n"+
							"WHERE student_id = ? \n"+
							"AND   unit_id = ?";
			}
			
			PreparedStatement psmt =  con.prepareStatement(exiteUniPerStu);
			psmt.setInt(1,studentPersonalId);
			psmt.setString(2,unitId);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()){
				valorRating = rs.getInt(1);
			}
		 }catch (NamingException | SQLException e) {
				logger.error(e);
			}finally{
				try {
					con.close();
				} catch (SQLException e) {
					logger.error(e);
				}
			}
		return valorRating;
	}
	
	public void sendWhyNotFiveStarts(){
		logger.debug("this.commentFiveStarts "+this.commentFiveStarts.length());
	if(this.commentFiveStarts.length() > 0){
		StudentPersonalBean studentPersonalBean = (StudentPersonalBean)session.getAttribute("studentPersonalBean");
		int studentPersonalId = studentPersonalBean.getStudentPersonalId();
		String unitId = (String) session.getAttribute("unitId");
		String isPerOrStudent = studentPersonalBean.getSegment1();
		String updatefiveStarts = null;
		if(isPerOrStudent.equals("P")){ 
		updatefiveStarts = " UPDATE status_unit_personal \n"+
				  "	 SET segment1=? \n"+
				  "	 WHERE unit_id=? \n"+
				  "	 AND personal_id = ?";
		}else if(isPerOrStudent.equals("S")){
			updatefiveStarts = " UPDATE status_unit_student \n"+
					  "	 SET segment1=? \n"+
					  "	 WHERE unit_id=? \n"+
					  "	 AND student_id = ?";
		}
		try{
		con = conectionWrapper.getConexion();
		PreparedStatement psmtUpdate =  con.prepareStatement(updatefiveStarts);
		psmtUpdate.setString(1,this.commentFiveStarts);
		psmtUpdate.setString(2, unitId);
		psmtUpdate.setInt(3, studentPersonalId);
		psmtUpdate.executeUpdate();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Comentario enviado","Gracias lo tomaremos en cuenta." ); 
        FacesContext.getCurrentInstance().addMessage(null, message);  
		
		}catch (NamingException | SQLException e) {
			logger.error(e);
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				logger.error(e);
			}
		}
	}
	}

	public void setValorRating(Integer valorRating) {
		this.valorRating = valorRating;
	}

	public String getCommentFiveStarts() {
		return commentFiveStarts;
	}

	public void setCommentFiveStarts(String commentFiveStarts) {
		this.commentFiveStarts = commentFiveStarts;
	}

	
	
	

}
