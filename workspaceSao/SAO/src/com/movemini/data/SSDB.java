/*
             * To change this license header, choose License Headers in Project Properties.
             * To change this template file, choose Tools | Templates
             * and open the template in the editor.
 */
package com.movemini.data;


import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Rafa
 */
public class SSDB {

//    public static String getConfiById(int id) {
//        ConnectionWrapper connect = null;
//        PreparedStatement stmt = null;
//        ResultSet resul = null;
//        String columna = null;
//        try {
//            connect = new ConnectionWrapper();
//            stmt = connect.prepareStatement("SELECT dat_confi FROM configuracion WHERE id_conf = ?");
//            stmt.setInt(1, id);
//            resul = stmt.executeQuery();
//            if (resul.first()) {
//                columna = resul.getString("dat_confi");
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        } finally {
//            ConnectionWrapper.closeAll(resul, stmt, connect);
//        }
//        return columna;
//    }

}
