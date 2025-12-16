/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import config.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import model.Model_Reportoutgoing;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import service.Service_Reportoutgoing;

/**
 *
 * @author rafii
 */
public class DAO_Reportoutgoing implements Service_Reportoutgoing{
    private Connection conn;

    public DAO_Reportoutgoing() {
        conn = Koneksi.getConnection();
    }

    public List<Model_Reportoutgoing> getData() {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs=null;
        String sql = "select * from v_outgoing";
        try{
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                Model_Reportoutgoing min = new Model_Reportoutgoing();
                
                min.setId_outgoing(rs.getString("id_outgoing"));
                min.setProject_name(rs.getString("project_name"));
                min.setTransaction_date(rs.getString("transaction_date"));
                min.setItems_name(rs.getString("items_name"));
                min.setQuantity(rs.getString("quantity"));
                
                min.setPic(rs.getString("pic"));
                
                
                list.add(min);
            }
            return list;
        }catch(SQLException ex){
            java.util.logging.Logger.getLogger(DAO_Reportincoming.class.getName()).log(Level.SEVERE,null,ex);
            return null;
        }finally{
            if(st!=null){
                try{
                    st.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Reportincoming.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
            if(rs!=null){
                try{
                    rs.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Reportincoming.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }
        
    }
    @Override
    public List<Model_Reportoutgoing> getData(Date fromDate, Date toDate) {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs=null;
        String sql = "SELECT * FROM v_outgoing WHERE DATE(transaction_date) BETWEEN ? AND ?";
        try{
            st = conn.prepareStatement(sql);

        // convert java.util.Date → java.sql.Date
        java.sql.Date sqlFrom = new java.sql.Date(fromDate.getTime());
        java.sql.Date sqlTo   = new java.sql.Date(toDate.getTime());

        st.setDate(1, sqlFrom);
        st.setDate(2, sqlTo);

        rs = st.executeQuery();
                while(rs.next()){
                Model_Reportoutgoing min = new Model_Reportoutgoing();
                
                min.setId_outgoing(rs.getString("id_outgoing"));
                min.setProject_name(rs.getString("project_name"));
                min.setTransaction_date(rs.getString("transaction_date"));
                min.setItems_name(rs.getString("items_name"));
                min.setQuantity(rs.getString("quantity"));
                
                min.setPic(rs.getString("pic"));
                
                
                list.add(min);
            }
            return list;
        }catch(SQLException ex){
            java.util.logging.Logger.getLogger(DAO_Reportincoming.class.getName()).log(Level.SEVERE,null,ex);
            return null;
        }finally{
            if(st!=null){
                try{
                    st.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Reportincoming.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
            if(rs!=null){
                try{
                    rs.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Reportincoming.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }
        
    }
    @Override
    public String nomor() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String nomor2() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void Report(String user) {
       try{
           
            String file = "src/report/Report_Outgoing.jasper";
            Map<String, Object> param = new HashMap<>();
            param.put("user", user); // Ini nama param di Jasper
            JasperPrint print = JasperFillManager.fillReport(file, param,conn);
            JasperViewer.viewReport(print,false);
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null, e.getMessage());
        }
    }
    @Override
    public void Report(String user, Date fromDate, Date toDate) {
    try {
        // Set jam fromDate ke 00:00:00
        Calendar calFrom = Calendar.getInstance();
        calFrom.setTime(fromDate);
        calFrom.set(Calendar.HOUR_OF_DAY, 0);
        calFrom.set(Calendar.MINUTE, 0);
        calFrom.set(Calendar.SECOND, 0);

        // Set jam toDate ke 23:59:59
        Calendar calTo = Calendar.getInstance();
        calTo.setTime(toDate);
        calTo.set(Calendar.HOUR_OF_DAY, 23);
        calTo.set(Calendar.MINUTE, 59);
        calTo.set(Calendar.SECOND, 59);

        Map<String, Object> param = new HashMap<>();
        param.put("fromDate", new java.sql.Timestamp(calFrom.getTimeInMillis()));
        param.put("toDate", new java.sql.Timestamp(calTo.getTimeInMillis()));
        param.put("user", user);

        JasperPrint print = JasperFillManager.fillReport("src/report/Report_Outgoing_1.jasper", param, conn);
        JasperViewer.viewReport(print, false);

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
}


}






