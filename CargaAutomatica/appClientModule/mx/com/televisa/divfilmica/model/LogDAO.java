package mx.com.televisa.divfilmica.model;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ResourceBundle;

import mx.com.televisa.divfilmica.beans.FileUriBean;
import mx.com.televisa.divfilmica.model.connection.ConnectionWrapper;

import org.apache.log4j.Logger;

public class LogDAO {
    
        static Logger logger = Logger.getLogger(LogDAO.class);
        ResourceBundle bundle;
        Connection con;
        PreparedStatement pstmt;
        ConnectionWrapper connectionWrapper;
            
    public LogDAO() {
        bundle = ResourceBundle.getBundle("DataConnection");
    }
    
    public String getDataLog(String reglaessbase,String tipo){
        connectionWrapper = new ConnectionWrapper();
        con = connectionWrapper.getConnection();
        String sql = bundle.getString("com.mx.televisa.divfilmica.select.des.proceso");
        ResultSet rs;
        String subProceso = null;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, reglaessbase);
            pstmt.setString(2, tipo);
            rs = pstmt.executeQuery();
            
            while(rs.next()){
                subProceso = rs.getString(1);
            }
            
            } catch (SQLException e) {
            logger.error(e.getMessage(),e);
            }finally{
                try{
                    con.close();
                    }catch(SQLException e){
                        logger.error(e.getMessage(),e);
                        e.printStackTrace();
                    }
            }
        return subProceso;
    }
    public FileUriBean getDataFileUri(String nomFileUri){
        logger.debug("nomFileUri "+nomFileUri);
        connectionWrapper = new ConnectionWrapper();
        con = connectionWrapper.getConnection();
        FileUriBean fileUriBean = new FileUriBean();
        String sql = bundle.getString("com.mx.televisa.divfilmica.select.regla.subproceso");
        ResultSet rs;
        String subProceso = null;
        String reglaCarga = null;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, nomFileUri);
            rs = pstmt.executeQuery();
            
            while(rs.next()){
                reglaCarga = rs.getString(1);
                subProceso = rs.getString(2);
                logger.debug("subProceso "+subProceso);
                fileUriBean.setReglaCarga(reglaCarga);
                fileUriBean.setSubproceso(subProceso);
            }
            
            } catch (SQLException e) {
            logger.error(e.getMessage(),e);
            }finally{
                try{
                    con.close();
                    }catch(SQLException e){
                        logger.error(e.getMessage(),e);
                        e.printStackTrace();
                    }
            }
        return fileUriBean;
    }
}
