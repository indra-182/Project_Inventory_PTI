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
import java.util.List;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import model.Model_Project;
import model.Model_Vendor;
import service.Service_Project;

/**
 *
 * @author rafii
 */
public class DAO_Project implements Service_Project{
    private Connection conn;
    public DAO_Project(){
        conn = (Connection) Koneksi.getConnection();
    }
    @Override
    public void addData(Model_Project mdl_Project) {
        PreparedStatement st = null;
        String sql = "INSERT INTO mst_Project (id_project,project_manager,project_name,project_site,id_vendor,status,sts_active,created_by,created_date,updated_by,updated_date) VALUES(?,?,?,?,?,?,?,?,?,?,?) ";
        try{    
            st = conn.prepareStatement(sql);
            st.setString(1,mdl_Project.getId_project());
            st.setString(2,mdl_Project.getProject_manager());
            st.setString(3,mdl_Project.getProject_name());
            st.setString(4,mdl_Project.getProject_site());
            st.setString(5,mdl_Project.getMovr().getId_vendor());
            st.setString(6,mdl_Project.getStatus());
            st.setString(7,"1");
            st.setString(8,mdl_Project.getCreated_by());
            st.setString(9,mdl_Project.getCreated_date());
            st.setString(10,mdl_Project.getUpdated_by());
            st.setString(11,mdl_Project.getUpdated_date());
            st.executeUpdate(); 
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Failed to Add data");
            java.util.logging.Logger.getLogger(DAO_Project.class.getName()).log(Level.SEVERE,null,ex);
        }finally{
            if(st!=null){
                try{
                    st.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Project.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }        
    }

    @Override
    public void updateData(Model_Project mdl_Project) {
        PreparedStatement st = null;
        String sql = "Update mst_Project set project_manager=?,project_name=?,project_site=?,id_vendor=?,status=?,updated_by=?,updated_date=? where id_project='"+mdl_Project.getId_project()+"'";
        try{
            st = conn.prepareStatement(sql);
            st.setString(1,mdl_Project.getProject_manager());
            st.setString(2,mdl_Project.getProject_name());
            st.setString(3,mdl_Project.getProject_site());
            st.setString(4,mdl_Project.getMovr().getId_vendor());
            st.setString(5,mdl_Project.getStatus());
            st.setString(6,mdl_Project.getUpdated_by());
            st.setString(7,mdl_Project.getUpdated_date());
            st.executeUpdate(); 
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Failed to Update data");
            java.util.logging.Logger.getLogger(DAO_Project.class.getName()).log(Level.SEVERE,null,ex);
        }finally{
            if(st!=null){
                try{
                    st.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Project.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }
    }

    @Override
    public void deleteData(Model_Project mdl_Project) {
        PreparedStatement st= null;
        String sql="Update mst_Project set sts_active=? where id_project='"+mdl_Project.getId_project()+"'";
        try{
            st = conn.prepareStatement(sql);
            st.setString(1,"0");
            
            st.executeUpdate(); 
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Failed to Delete data");
            java.util.logging.Logger.getLogger(DAO_Project.class.getName()).log(Level.SEVERE,null,ex);
        }finally{
            if(st!=null){
                try{
                    st.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Project.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }
    }

    @Override
    public List<Model_Project> getData() {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs=null;
        String sql = "SELECT p.id_project, p.project_manager,p.status ,p.project_name, p.project_site, v.vendor_name, v.id_vendor FROM mst_project p JOIN mst_vendor v ON p.id_vendor = v.id_vendor where p.sts_active= '1';";
        try{
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                Model_Project mp = new Model_Project();
                Model_Vendor mv = new Model_Vendor();
                mp.setId_project(rs.getString("id_project"));
                mp.setProject_manager(rs.getString("project_manager"));
                mp.setStatus(rs.getString("status"));
                mp.setProject_name(rs.getString("project_name"));
                mp.setProject_site(rs.getString("project_site"));
                mv.setId_vendor(rs.getString("id_vendor"));
                mv.setVendor_name(rs.getString("vendor_name"));
                mp.setMovr(mv);
                list.add(mp);
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
    public List<Model_Project> getDataVendor() {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs=null;
        String sql = "SELECT id_vendor,vendor_name FROM mst_vendor where sts_active='1';";
        try{
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                Model_Project mp = new Model_Project();
                Model_Vendor mv = new Model_Vendor();
                mv.setId_vendor(rs.getString("id_vendor"));
                mv.setVendor_name(rs.getString("vendor_name"));
                mp.setMovr(mv);
                list.add(mp);
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
    public List<Model_Project> search(String str) {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs=null;
        
        String sql = "SELECT p.id_project,p.status, p.project_manager ,p.project_name, p.project_site, v.vendor_name, v.id_vendor FROM mst_project p JOIN mst_vendor v ON p.id_vendor = v.id_vendor where p.sts_active= '1' AND (p.project_name LIKE '%"+str+"%' OR p.project_site LIKE '%"+str+"%' OR p.project_manager LIKE '%"+str+"%');";
        try{
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                Model_Project mp = new Model_Project();
                Model_Vendor mv = new Model_Vendor();
                mp.setId_project(rs.getString("id_project"));
                mp.setProject_manager(rs.getString("project_manager"));
                mp.setProject_name(rs.getString("project_name"));
                mp.setProject_site(rs.getString("project_site"));
                mp.setStatus(rs.getString("status"));
                mv.setId_vendor(rs.getString("id_vendor"));
                mv.setVendor_name(rs.getString("vendor_name"));
                mp.setMovr(mv);
                list.add(mp);
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
    
}
