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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import model.Model_Reportincoming;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import service.Service_Reportincoming;

/**
 *
 * @author rafii
 */
public class DAO_Reportincoming implements Service_Reportincoming{
    private Connection conn;

    public DAO_Reportincoming() {
        conn = Koneksi.getConnection();
    }

    @Override
    public List<Model_Reportincoming> getData() {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs=null;
        String sql = "select * from v_incoming";
        try{
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                Model_Reportincoming min = new Model_Reportincoming();
                
                min.setId_incoming(rs.getString("id_incoming"));
                min.setId_distributor(rs.getString("id_distributor"));
                min.setDistributor_name(rs.getString("distributor_name"));
                min.setTransaction_date(rs.getString("transaction_date"));
                min.setId_items(rs.getString("id_items"));
                min.setItems_name(rs.getString("items_name"));
                min.setSubtotal(rs.getString("price"));
                min.setQuantity(rs.getString("quantity"));
                min.setTotal(rs.getString("total"));
                min.setNip(rs.getString("nip"));
                min.setPic(rs.getString("pic"));
                min.setType_transaction(rs.getString("type_transaction"));    
                
                
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
    public List<Model_Reportincoming> getData(Date fromDate, Date toDate) {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs=null;
        String sql = "SELECT * FROM v_incoming WHERE DATE(transaction_date) BETWEEN ? AND ?";
        try{
            st = conn.prepareStatement(sql);

        // convert java.util.Date → java.sql.Date
        java.sql.Date sqlFrom = new java.sql.Date(fromDate.getTime());
        java.sql.Date sqlTo   = new java.sql.Date(toDate.getTime());

        st.setDate(1, sqlFrom);
        st.setDate(2, sqlTo);

        rs = st.executeQuery();
            while(rs.next()){
                Model_Reportincoming min = new Model_Reportincoming();
                
                min.setId_incoming(rs.getString("id_incoming"));
                min.setId_distributor(rs.getString("id_distributor"));
                min.setDistributor_name(rs.getString("distributor_name"));
                min.setTransaction_date(rs.getString("transaction_date"));
                min.setId_items(rs.getString("id_items"));
                min.setItems_name(rs.getString("items_name"));
                min.setSubtotal(rs.getString("price"));
                min.setQuantity(rs.getString("quantity"));
                min.setTotal(rs.getString("total"));
                min.setNip(rs.getString("nip"));
                min.setPic(rs.getString("pic"));
                min.setType_transaction(rs.getString("type_transaction"));    
                
                
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
            String file = "src/report/Report_Incoming.jasper";
            Map<String, Object> param = new HashMap<>();
            param.put("user", user); // Ini nama param di Jasper
            JasperPrint print = JasperFillManager.fillReport(file, param,conn);
            JasperViewer.viewReport(print,false);
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null, e.getMessage());
        }
    }
    @Override
    public void Report(String user,Date fromDate, Date toDate) {
        try {
            // Set jam toDate ke 23:59:59
            Calendar cal = Calendar.getInstance();
            cal.setTime(toDate);
            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.MINUTE, 59);
            cal.set(Calendar.SECOND, 59);

            Map<String, Object> param = new HashMap<>();
            param.put("fromDate", new java.sql.Date(fromDate.getTime()));
            param.put("toDate", new java.sql.Timestamp(cal.getTimeInMillis()));
            param.put("user", user);

            JasperPrint print = JasperFillManager.fillReport("src/report/Report_Incoming_1.jasper", param, conn);
            JasperViewer.viewReport(print, false);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

}






