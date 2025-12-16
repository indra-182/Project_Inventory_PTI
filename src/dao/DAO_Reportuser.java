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
import model.Model_Distributor;
import model.Model_Group;
import model.Model_Item;
import model.Model_Karyawan;
import model.Model_Project;
import model.Model_Reportoutgoing;
import model.Model_Type;
import model.Model_User;
import model.Model_Vendor;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import service.Service_Reportitems;
import service.Service_Reportoutgoing;
import service.Service_Reportproject;
import service.Service_Reportuser;

/**
 *
 * @author rafii
 */
public class DAO_Reportuser implements Service_Reportuser{
    private Connection conn;

    public DAO_Reportuser() {
        conn = Koneksi.getConnection();
    }
    @Override
    public List<Model_User> getData() {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs=null;
        String sql = "SELECT a.id_karyawan, b.nama_lengkap AS nama_karyawan, a.username, a.last_login, c.group_name FROM mst_user a LEFT JOIN mst_karyawan b ON a.id_karyawan = b.id_karyawan LEFT JOIN mst_group c ON a.id_group = c.id_group WHERE a.sts_active = 1 ORDER BY b.nama_lengkap ASC;";
        try{
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                Model_User mk = new Model_User();
                Model_Karyawan mv = new Model_Karyawan();
                Model_Group mg = new Model_Group();
                mk.setId_karyawan(rs.getString("id_karyawan"));
                mk.setNama_lengkap(rs.getString("nama_karyawan"));
                mg.setGroup_name(rs.getString("Group_name"));
                mk.setUsername(rs.getString("username"));
                mk.setLast_login(rs.getString("last_login"));
//                mv.setId_karyawan(rs.getString("id_karyawan"));
                mk.setMovr(mv);
                mk.setMogr(mg);
                list.add(mk);
            }
            return list;
        }catch(SQLException ex){
            java.util.logging.Logger.getLogger(DAO_User.class.getName()).log(Level.SEVERE,null,ex);
            return null;
        }finally{
            if(st!=null){
                try{
                    st.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_User.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
            if(rs!=null){
                try{
                    rs.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_User.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }
        
    }

    @Override 
    public List<Model_User> getDataKaryawan() {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs=null;
        String sql = "SELECT id_karyawan,nama_lengkap FROM mst_karyawan where sts_active='1';";
        try{
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                Model_User mk = new Model_User();
                Model_Karyawan mv = new Model_Karyawan();
                mv.setId_karyawan(rs.getString("id_karyawan"));
                mv.setNama_lengkap(rs.getString("nama_lengkap"));
                mk.setMovr(mv);
                list.add(mk);
            }
            return list;
        }catch(SQLException ex){
            java.util.logging.Logger.getLogger(DAO_Project.class.getName()).log(Level.SEVERE,null,ex);
            return null;
        }finally{
            if(st!=null){
                try{
                    st.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Project.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
            if(rs!=null){
                try{
                    rs.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Project.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }
        
    }    
    @Override  
    public List<Model_User> getDataGroup() {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs=null;
        String sql = "SELECT id_group,group_name FROM mst_group where sts_active='1';";
        try{
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                Model_User mk = new Model_User();
                Model_Group mv = new Model_Group();
                mv.setId_group(rs.getString("id_group"));
                mv.setGroup_name(rs.getString("group_name"));
                mk.setMogr(mv);
                list.add(mk);
            }
            return list;
        }catch(SQLException ex){
            java.util.logging.Logger.getLogger(DAO_Project.class.getName()).log(Level.SEVERE,null,ex);
            return null;
        }finally{
            if(st!=null){
                try{
                    st.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Project.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
            if(rs!=null){
                try{
                    rs.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Project.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }
        
    }  
    @Override
    public void Report(String user) {
        try{
            String file = "src/report/Report_User.jasper";
            Map<String, Object> param = new HashMap<>();
            param.put("user", user); // Ini nama param di Jasper
            JasperPrint print = JasperFillManager.fillReport(file, param,conn);
            JasperViewer.viewReport(print,false);
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null, e.getMessage());
        }
    }
}






