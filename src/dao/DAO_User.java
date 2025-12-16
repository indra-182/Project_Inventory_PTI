package dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import config.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import model.Model_Group;
import model.Model_User;
import model.Model_Karyawan;
import model.Model_Project;
import model.Model_Vendor;
import service.Service_User;

/**
 *
 * @author rafii
 */
public class DAO_User implements Service_User{
    private Connection conn;
    public DAO_User(){
        conn = (Connection) Koneksi.getConnection();
    }
    @Override
    public void addData(Model_User mdl_User) {
        PreparedStatement st = null;
        String sql = "INSERT INTO mst_user (username,password,id_group,id_karyawan,sts_active,created_by,created_date,updated_by,updated_date,last_login) VALUES(?,?,?,?,?,?,?,?,?,?) ";
        try{    
            st = conn.prepareStatement(sql);
            st.setString(1,mdl_User.getUsername());
            st.setString(2,Encrypt.getmd5java(mdl_User.getPassword()));
            st.setString(3,mdl_User.getMogr().getId_group());
            st.setString(4,mdl_User.getId_karyawan());
            st.setString(5,"1");
            st.setString(6,mdl_User.getCreated_by());
            st.setString(7,mdl_User.getCreated_date());
            st.setString(8,mdl_User.getUpdated_by());
            st.setString(9,mdl_User.getUpdated_date());
            st.setString(10,mdl_User.getLast_login());
            st.executeUpdate(); 
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Failed to Add data");
            java.util.logging.Logger.getLogger(DAO_User.class.getName()).log(Level.SEVERE,null,ex);
        }finally{
            if(st!=null){
                try{
                    st.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_User.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }        
    }

    @Override
    public void updateData(Model_User mdl_User) {
        PreparedStatement st = null;
        String sql = "Update mst_user set id_group=?,updated_by=?,updated_date=?  where username='"+mdl_User.getUsername()+"'";
        try{
            st = conn.prepareStatement(sql);            
            st.setString(1,mdl_User.getMogr().getId_group());
            st.setString(2,mdl_User.getUpdated_by());
            st.setString(3,mdl_User.getUpdated_date());
            st.executeUpdate(); 
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Failed to Update data");
            java.util.logging.Logger.getLogger(DAO_User.class.getName()).log(Level.SEVERE,null,ex);
        }finally{
            if(st!=null){
                try{
                    st.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_User.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }
    }

    @Override
    public void deleteData(Model_User mdl_User) {
        PreparedStatement st= null;
        String sql="Update mst_user set sts_active=? where username='"+mdl_User.getUsername()+"'";
        try{
            st = conn.prepareStatement(sql);
            st.setString(1,"0");
            
            st.executeUpdate(); 
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Failed to Delete data");
            java.util.logging.Logger.getLogger(DAO_User.class.getName()).log(Level.SEVERE,null,ex);
        }finally{
            if(st!=null){
                try{
                    st.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_User.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }
    }


    @Override
    public List<Model_User> getData() {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs=null;
        String sql = "SELECT \n" +
"    a.id_karyawan,\n" +
"    b.nama_lengkap AS nama_karyawan,\n" +
"    a.username,\n" +
"    a.last_login,\n" +
"    MD5(a.password) AS password,\n" +
"    c.group_name\n" +
"FROM \n" +
"    mst_user a\n" +
"LEFT JOIN \n" +
"    mst_karyawan b ON a.id_karyawan = b.id_karyawan\n" +
"LEFT JOIN \n" +
"    mst_group c ON a.id_group = c.id_group\n" +
"WHERE \n" +
"    a.sts_active = 1\n" +
"ORDER BY \n" +
"    b.nama_lengkap ASC;";
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
                mk.setPassword(rs.getString("password"));
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

//    @Override
//    public List<Model_Karyawan> search(String str) {
//        PreparedStatement st = null;
//        List list = new ArrayList();
//        ResultSet rs=null;
//        String sql = "SELECT p.id_project, p.project_manager ,p.project_name, p.project_site, v.vendor_name, v.id_vendor FROM mst_project p JOIN mst_vendor v ON p.id_vendor = v.id_vendor where p.sts_active= '1' AND p.project_name LIKE '%"+str+"%' OR p.project_site LIKE '%"+str+"%' OR p.project_manager LIKE '%"+str+"%';";
//        try{
//            st = conn.prepareStatement(sql);
//            rs = st.executeQuery();
//            while(rs.next()){
//                Model_Project mp = new Model_Project();
//                Model_Vendor mv = new Model_Vendor();
//                mp.setId_project(rs.getString("id_project"));
//                mp.setProject_manager(rs.getString("project_manager"));
//                mp.setProject_name(rs.getString("project_name"));
//                mp.setProject_site(rs.getString("project_site"));
//                mv.setId_vendor(rs.getString("id_vendor"));
//                mv.setVendor_name(rs.getString("vendor_name"));
//                mp.setMovr(mv);
//                list.add(mp);
//            }
//            return list;
//        }catch(SQLException ex){
//            java.util.logging.Logger.getLogger(DAO_Project.class.getName()).log(Level.SEVERE,null,ex);
//            return null;
//        }finally{
//            if(st!=null){
//                try{
//                    st.close();
//                }catch(SQLException ex){
//                    java.util.logging.Logger.getLogger(DAO_Project.class.getName()).log(Level.SEVERE,null,ex);
//                }
//            }
//            if(rs!=null){
//                try{
//                    rs.close();
//                }catch(SQLException ex){
//                    java.util.logging.Logger.getLogger(DAO_Project.class.getName()).log(Level.SEVERE,null,ex);
//                }
//            }
//        }
//    }
    
}

