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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import model.Model_Project;
import model.Model_Reportoutgoing;
import model.Model_Vendor;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import service.Service_Reportoutgoing;
import service.Service_Reportproject;

/**
 *
 * @author rafii
 */
public class DAO_Reportproject implements Service_Reportproject{
    private Connection conn;

    public DAO_Reportproject() {
        conn = Koneksi.getConnection();
    }
    @Override
    public List<Model_Project> getData() {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs=null;
        String sql = "select * from v_project";
        try{
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                Model_Project mp = new Model_Project();
                Model_Vendor mv = new Model_Vendor();
                
                mp.setId_project(rs.getString("id_project"));
                mp.setProject_name(rs.getString("project_name"));
                mp.setProject_manager(rs.getString("project_manager"));
                                              mp.setStatus(rs.getString("status"));
                              mp.setProject_site(rs.getString("project_site"));
                               mv.setVendor_name(rs.getString("vendor_name"));
                
                mp.setMovr(mv);
                
                
                list.add(mp);
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
    public List<Model_Project> getData(String str) {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs=null;
        String sql = "select * from v_project where status='"+str+"'";
        try{
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                Model_Project mp = new Model_Project();
                Model_Vendor mv = new Model_Vendor();
                
                mp.setId_project(rs.getString("id_project"));
                mp.setProject_name(rs.getString("project_name"));
                mp.setProject_manager(rs.getString("project_manager"));
                              mp.setProject_site(rs.getString("project_site"));
                              mp.setStatus(rs.getString("status"));
                               mv.setVendor_name(rs.getString("vendor_name"));
                
                mp.setMovr(mv);
                
                
                list.add(mp);
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
    public void Report(String user,String status) {
       try{
           
            String file = "src/report/Report_Project.jasper";
            Map<String, Object> param = new HashMap<>();
            param.put("user", user); // Ini nama param di Jasper
            param.put("status", status); // Ini nama param di Jasper
            JasperPrint print = JasperFillManager.fillReport(file, param,conn);
            JasperViewer.viewReport(print,false);
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null, e.getMessage());
        }
    }
public void Report(String user) {
       try{
           
            String file = "src/report/Report_Project_1.jasper";
            Map<String, Object> param = new HashMap<>();
            param.put("user", user); // Ini nama param di Jasper
            JasperPrint print = JasperFillManager.fillReport(file, param,conn);
            JasperViewer.viewReport(print,false);
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null, e.getMessage());
        }
    }


}






