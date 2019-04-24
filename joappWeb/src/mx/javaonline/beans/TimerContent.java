package mx.javaonline.beans;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import mx.javaonline.model.ConectionWrapper;

import org.apache.log4j.Logger;

public class TimerContent implements Serializable{
	
	private org.apache.log4j.Logger logger = Logger.getLogger(TimerContent.class);
	FacesContext facesContext = FacesContext.getCurrentInstance();
	HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
	ConectionWrapper conectionWrapper;
	
	private String sCronometro = "00:00:00";
	private boolean stop;
	Integer hora = 0;
	Integer min = 0;
	Integer seg = 0;
	String sHora = "";
	String sMin  = "";
	String sSeg  = "";
//	int ds = 0;
	  
	   
	   
	      
	    public void increment(String accion) {  
	    	String topicId = (String)session.getAttribute("topicId");
	    	String linkTopic = (String)session.getAttribute("linkTopic");
	      logger.debug("topicId "+topicId);
	      logger.debug("accion "+accion);
	      /*  if (ds == 99) {
                ds = 0;
                seg++;
            }*/
	    if(stop){
	    	
	    	
            if (seg == 59) {
                seg = 0;
                min++;
                updatePorcentaje(topicId,min,accion);
            }
            if (min == 59) {
                min = 0;
                hora++;
            }
            //ds++;
            seg++;
            if(seg < 10) sSeg = "0" + seg;
            else sSeg = seg.toString();
            if(min < 10) sMin = "0" + min;
            else sMin = min.toString();
            if(hora < 10) sHora = "0" + hora;
            else sHora = hora.toString();
           this.setsCronometro(sHora + ":" + sMin + ":" + sSeg);
	    }
 }

	    private void updatePorcentaje(String topicId,Integer min,String accion){
	    	StudentPersonalBean studentPersonalBean = (StudentPersonalBean)session.getAttribute("studentPersonalBean");
	    	conectionWrapper = new ConectionWrapper();
			
			Connection con = null;
		try {
			logger.debug("studentPersonalBean.getSegment1() "+studentPersonalBean.getSegment1());
			con = conectionWrapper.getConexion();
			CallableStatement cstmtCall = con.prepareCall("{CALL insert_update_status_topic_pr(?,?,?,?,?,?)}");
			cstmtCall.setInt(1,studentPersonalBean.getStudentPersonalId());
			cstmtCall.setInt(2,Integer.parseInt(topicId));
			cstmtCall.setInt(3,min);
			cstmtCall.setString(4,studentPersonalBean.getSegment1());
			cstmtCall.setString(5,accion);
			cstmtCall.registerOutParameter(6, Types.VARCHAR);
			cstmtCall.execute();
			String error = cstmtCall.getString(6);
			logger.error(error);
			
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
		public String getsCronometro() {
			return sCronometro;
		}

		public void setsCronometro(String sCronometro) {
			this.sCronometro = sCronometro;
		}

		public boolean isStop() {
			return stop;
		}

		public void setStop(boolean stop) {
			this.stop = stop;
		}  
	    
	    public void pausaTimer(){
	    	logger.debug("PAUSO2");
	    	stop = false;
	    }
	    
	    public void playTimer(){
	    	logger.debug("PLAY2");
	    	stop = true;
	    }

}
