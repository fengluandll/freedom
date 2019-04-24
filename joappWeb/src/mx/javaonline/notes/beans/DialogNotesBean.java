package mx.javaonline.notes.beans;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import mx.javaonline.beans.StudentPersonalBean;
import mx.javaonline.model.ConectionWrapper;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;

public class DialogNotesBean {
	
	ConectionWrapper conectionWrapper;
	FacesContext facesContext = FacesContext.getCurrentInstance();
	HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
	private static org.apache.log4j.Logger logger = Logger.getLogger(DialogNotesBean.class);
	NotasBeans notasBean;
	List<NotasBeans> listNotas;
	String nomNota;
	StudentPersonalBean studentPersonalBean;
	String contentNote;
	Connection con;
	int identificadorNota;
	
	
	public String ligaAtras(){
		return "listadoNotas";
	}
	
	public DialogNotesBean(){
		
         conectionWrapper = new ConectionWrapper();
		 session = (HttpSession) facesContext.getExternalContext().getSession(true);
		 obtenNotas();
		
	}

	
	
	
	/**
	 * @deprecated
	 * Remover este metodo ya no se usa
	 */
	public void consultaNotas(){
		StudentPersonalBean studentPersonalBean = (StudentPersonalBean)session.getAttribute("studentPersonalBean");
/*		String sql = null;
		if(studentPersonalBean.getSegment1().equals("S")){
			sql = "SELECT count(*) \n "+
					 "	FROM student_notes \n "+
					 "	WHERE student_id = ?";
		}else if(studentPersonalBean.getSegment1().equals("P")){
			sql = "SELECT count(*) \n "+
					 "	FROM personal_notes \n "+
					 "	WHERE personal_id = ?";
		}
		
		Connection con = null;
	 	
			try {
				con = conectionWrapper.getConexion();
				PreparedStatement psmt = con.prepareStatement(sql);
				psmt.setInt(1,studentPersonalBean.getStudentPersonalId());
				ResultSet rs = psmt.executeQuery();
				int count = 0;
				while(rs.next()){
					count = rs.getInt(1);
				}
				if(count == 0){
					logger.debug("ES CERO");
				
				}
			} catch (NamingException | SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
*/
		RequestContext.getCurrentInstance().execute("dlg3.show()");
	}
	

	 
	 public void obtenNotas(){
		 listNotas = new ArrayList<>();
		 String selectStudent = "SELECT note_id, \n" 		+
							"		student_id, \n" 		+
								"		note_name, \n" 		+
								"		content_note, \n" 	+
								"		segment1, \n" 		+
								"		segment2, \n" 		+
								"		segment3, \n" 		+
								"		segment4, \n" 		+
								"		segment5, \n" 		+
								"		segment6, \n" 		+
								"		segment7, \n" 		+
								"		segment8, \n" 		+
								"		creation_date \n"  	+
								" FROM  student_notes \n " 	+
								" WHERE student_id = ? \n "	+
								" ORDER BY note_id desc";
		 String selectPersonal = "SELECT note_id, \n" +
				"		personal_id, \n" 		+
					"		note_name, \n" 		+
					"		content_note, \n" 	+
					"		segment1, \n" 		+
					"		segment2, \n" 		+
					"		segment3, \n" 		+
					"		segment4, \n" 		+
					"		segment5, \n"       +
					"		segment6, \n"       +
					"		segment7, \n"       +
					"		segment8, \n"       +
					"		creation_date \n"   +
					" FROM  personal_notes \n " +
					" WHERE personal_id = ? \n " +
					" ORDER BY note_id desc";
		 
		 	try {
				con = conectionWrapper.getConexion();
				ResultSet rs = null;
				studentPersonalBean = (StudentPersonalBean)session.getAttribute("studentPersonalBean");
				
				if(studentPersonalBean.getSegment1().equals("S")){
					
					PreparedStatement pstm = con.prepareStatement(selectStudent);
					pstm.setInt(1, studentPersonalBean.getStudentPersonalId());
					rs = pstm.executeQuery();
					 while(rs.next()){
						 notasBean = new NotasBeans();
						 notasBean.setNoteId(rs.getInt(1));
						 notasBean.setStudenPersonaltId(rs.getInt(2));
						 notasBean.setNoteName(rs.getString(3));
						 notasBean.setContentNote(rs.getString(4));
						 notasBean.setSegment1(rs.getString(5));
						 notasBean.setSegment2(rs.getString(6));
						 notasBean.setSegment3(rs.getString(7));
						 notasBean.setSegment4(rs.getString(8));
						 notasBean.setSegment5(rs.getString(9));
						 notasBean.setSegment6(rs.getInt(10));
						 notasBean.setSegment7(rs.getDate(11));
						 notasBean.setSegment8(rs.getBlob(12));
						 notasBean.setCreationDate(rs.getDate(13));
						 listNotas.add(notasBean);
					 }
					 
					
				}else if(studentPersonalBean.getSegment1().equals("P") || studentPersonalBean.getSegment1().equals("A")){
					
					PreparedStatement pstm = con.prepareStatement(selectPersonal);
					pstm.setInt(1, studentPersonalBean.getStudentPersonalId());
					 rs = pstm.executeQuery();
					 while(rs.next()){
						 notasBean = new NotasBeans();
						 notasBean.setNoteId(rs.getInt(1));
						 notasBean.setStudenPersonaltId(rs.getInt(2));
						 notasBean.setNoteName(rs.getString(3));
						 notasBean.setContentNote(rs.getString(4));
						 notasBean.setSegment1(rs.getString(5));
						 notasBean.setSegment2(rs.getString(6));
						 notasBean.setSegment3(rs.getString(7));
						 notasBean.setSegment4(rs.getString(8));
						 notasBean.setSegment5(rs.getString(9));
						 notasBean.setSegment6(rs.getInt(10));
						 notasBean.setSegment7(rs.getDate(11));
						 notasBean.setSegment8(rs.getBlob(12));
						 notasBean.setCreationDate(rs.getDate(13));
						 listNotas.add(notasBean);
					 }
					 

					
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
	 
	 public void generaNomNota(){
		 studentPersonalBean = (StudentPersonalBean)session.getAttribute("studentPersonalBean");
		 
		 String insertStd =   "INSERT INTO student_notes \n" +
							  "	(student_id, \n" +
							  "	note_name, \n" +
							  "	creation_date) \n" +
							  "	VALUES(? ,\n" +
							  "	       ?,\n" +
							  "	       sysdate()) ";
		 
		 String insertprs =   "INSERT INTO personal_notes \n" +
							  "	(personal_id, \n" +
							  "	note_name, \n" +
							  "	creation_date) \n" +
							  "	VALUES(? ,\n" +
							  "	       ?,\n" +
							  "	       sysdate()) ";
		 try {
			con = conectionWrapper.getConexion();
			if(studentPersonalBean.getSegment1().equals("S")){
				PreparedStatement pstmt =  con.prepareStatement(insertStd);
				pstmt.setInt(1,studentPersonalBean.getStudentPersonalId());
				pstmt.setString(2,nomNota);
				pstmt.executeUpdate();
				
			}else if(studentPersonalBean.getSegment1().equals("P") || studentPersonalBean.getSegment1().equals("A")){
				PreparedStatement pstmt =  con.prepareStatement(insertprs);
				pstmt.setInt(1,studentPersonalBean.getStudentPersonalId());
				pstmt.setString(2,nomNota);
				pstmt.executeUpdate();
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				logger.error(e);
			}
		}
		 this.setNomNota("");
		 obtenNotas();	
	 }
	 
	 public void guardaNota(){
		 this.identificadorNota = (int) session.getAttribute("idNote");
		 studentPersonalBean = (StudentPersonalBean)session.getAttribute("studentPersonalBean");
	     ConectionWrapper conectionWrapper = new ConectionWrapper();
	     Connection con = null;
		try {
			con = conectionWrapper.getConexion();
			CallableStatement cstmtCall = con.prepareCall("{CALL create_notes_pr(?,?,?,?,?)}");
			cstmtCall.setInt(1,studentPersonalBean.getStudentPersonalId());
			cstmtCall.setString(2,contentNote);
			cstmtCall.registerOutParameter(3, Types.VARCHAR);
			cstmtCall.setString(4,studentPersonalBean.getSegment1());
			cstmtCall.setInt(5, this.identificadorNota);
			cstmtCall.execute();
			String error = cstmtCall.getString(3);
			if(error == null){
				FacesContext context = FacesContext.getCurrentInstance();  
		        context.addMessage(null, new FacesMessage("Exitoso","Tu nota ha sido guardada"));
			}
			 
			
		} catch (SQLException | NamingException e) {
				logger.error(e);
			}finally{
				try {
					con.close();
				} catch (SQLException e) {
					logger.error(e);
				}
		
		
			}

	 }

	 public String getValueNotes(int idNote,String nombreNota) {
//			logger.debug("idNote "+idNote);
//			logger.debug("nombreNota "+nombreNota);
			session.setAttribute("idNote",idNote);
			session.setAttribute("nombreNota",nombreNota);
			StudentPersonalBean studentPersonalBean = (StudentPersonalBean)session.getAttribute("studentPersonalBean");
//			logger.debug("studentPersonalBean.getSegment1() "+studentPersonalBean.getSegment1());
	    	conectionWrapper = new ConectionWrapper();
			String notes = "";
			int noteId = 0;
			String redirect = null;
		try {
			con = conectionWrapper.getConexion();
			CallableStatement cstmtCall = con.prepareCall("{CALL getValueNotes_pr(?,?,?,?,?,?)}");
			cstmtCall.setInt(1,studentPersonalBean.getStudentPersonalId());
			cstmtCall.setString(2,studentPersonalBean.getSegment1());
			cstmtCall.setInt(3,idNote);
			cstmtCall.registerOutParameter(4, Types.LONGVARCHAR);
			cstmtCall.registerOutParameter(5, Types.VARCHAR);
			cstmtCall.registerOutParameter(6, Types.INTEGER);
			cstmtCall.execute();
			notes = cstmtCall.getString(4);
			String error = cstmtCall.getString(5);
			noteId = cstmtCall.getInt(6);
			if(error != null){
				logger.error(error);
			}
			
			redirect = "notas";
		} catch (SQLException | NamingException e) {
				logger.error(e);
			}finally{
				try {
					con.close();
				} catch (SQLException e) {
					logger.error(e);
				}
			}
			this.contentNote = notes;
			session.setAttribute("contentNote",notes);
			this.nomNota     = nombreNota;
//			this.identificadorNota      = noteId;
			return redirect;
		}
	 
	 public void borraNota(int idNota){
//		 logger.debug("idNota***** "+idNota);
		 StudentPersonalBean studentPersonalBean = (StudentPersonalBean)session.getAttribute("studentPersonalBean");
		 
		 String delete = "";
		 
		 if (studentPersonalBean.getSegment1().equals("S")){
			 delete = "DELETE FROM student_notes WHERE note_id = ?";
		 }else if(studentPersonalBean.getSegment1().equals("P") || studentPersonalBean.getSegment1().equals("A")){
			 delete = "DELETE FROM personal_notes WHERE note_id = ?";
		 }
		 try {
			con = conectionWrapper.getConexion();
			PreparedStatement psmt = con.prepareStatement(delete);
			psmt.setInt(1, idNota);
			psmt.executeUpdate();
			obtenNotas();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exitoso",  "Tu nota ha sido borrada");
		    FacesContext.getCurrentInstance().addMessage(null, message);  
		    RequestContext context = RequestContext.getCurrentInstance();
		    context.update("msgEliminar");
			
		} catch (NamingException | SQLException e) {
			logger.error(e);
		} finally{
			try {
				con.close();
			} catch (SQLException e) {
				logger.error(e);
			}
		}
		 
	 }
	 
	public List<NotasBeans> getListNotas() {
		
		return listNotas;
	}

	public void setListNotas(List<NotasBeans> listNotas) {
		this.listNotas = listNotas;
	}

	public String getNomNota() {
		return nomNota != null ? nomNota.toUpperCase() : nomNota;
	}

	public void setNomNota(String nomNota) {
		this.nomNota = nomNota;
	}

	public String getContentNote() {
		return contentNote;
	}

	public void setContentNote(String contentNote) {
		this.contentNote = contentNote;
	}

	public int getIdentificadorNota() {
		return identificadorNota;
	}

	public void setIdentificadorNota(int identificadorNota) {
		this.identificadorNota = identificadorNota;
	}

	 

}
