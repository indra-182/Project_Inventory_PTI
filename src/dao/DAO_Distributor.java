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
import model.Model_Distributor;
import service.Service_Distributor;

/**
 *
 * @author rafii
 */
public class DAO_Distributor implements Service_Distributor{
    private Connection conn;
    public DAO_Distributor(){
        conn = (Connection) Koneksi.getConnection();
    }
    @Override
    public void addData(Model_Distributor mdl_Distributor) {
        PreparedStatement st = null;
        String sql = "INSERT INTO mst_distributor (id_distributor,distributor_name,location,no_telp,pic_distributor,sts_active,created_by,created_date,updated_by,updated_date) VALUES(?,?,?,?,?,?,?,?,?,?) ";
        try{    
            st = conn.prepareStatement(sql);
            st.setString(1,mdl_Distributor.getId_distributor());
            st.setString(2,mdl_Distributor.getDistributor_name());
            st.setString(3,mdl_Distributor.getLocation());
            st.setString(4,mdl_Distributor.getNo_telp());
            st.setString(5,mdl_Distributor.getPic_distributor());
            st.setString(6,"1");
            st.setString(7,mdl_Distributor.getCreated_by());
            st.setString(8,mdl_Distributor.getCreated_date());
            st.setString(9,mdl_Distributor.getUpdated_by());
            st.setString(10,mdl_Distributor.getUpdated_date());
            st.executeUpdate(); 
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Failed to Add data");
            java.util.logging.Logger.getLogger(DAO_Distributor.class.getName()).log(Level.SEVERE,null,ex);
        }finally{
            if(st!=null){
                try{
                    st.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Distributor.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }        
    }

    @Override
    public void updateData(Model_Distributor mdl_Distributor) {
        PreparedStatement st = null;
        String sql = "Update mst_distributor set distributor_name=?,location=?,no_telp=?,pic_distributor=?,sts_active=?,updated_by=?,updated_date=? where id_distributor='"+mdl_Distributor.getId_distributor()+"'";
        try{    
            st = conn.prepareStatement(sql);
            st.setString(1,mdl_Distributor.getDistributor_name());
            st.setString(2,mdl_Distributor.getLocation());
            st.setString(3,mdl_Distributor.getNo_telp());
            st.setString(4,mdl_Distributor.getPic_distributor());
            st.setString(5,"1");
            st.setString(6,mdl_Distributor.getUpdated_by());
            st.setString(7,mdl_Distributor.getUpdated_date());
            st.executeUpdate(); 
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Failed to Update data");
            java.util.logging.Logger.getLogger(DAO_Distributor.class.getName()).log(Level.SEVERE,null,ex);
        }finally{
            if(st!=null){
                try{
                    st.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Distributor.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }        
    }

    @Override
    public void deleteData(Model_Distributor mdl_Distributor) {
     PreparedStatement st= null;
        String sql="Update mst_distributor set sts_active=? where id_distributor='"+mdl_Distributor.getId_distributor()+"'";
        try{
            st = conn.prepareStatement(sql);
            st.setString(1,"0");
            
            st.executeUpdate(); 
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Failed to Delete data");
            java.util.logging.Logger.getLogger(DAO_Distributor.class.getName()).log(Level.SEVERE,null,ex);
        }finally{
            if(st!=null){
                try{
                    st.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Distributor.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }
    }

    @Override
    public List<Model_Distributor> getData() {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs=null;
        String sql = "SELECT id_distributor,distributor_name,location,no_telp,pic_distributor FROM mst_distributor where sts_active='1';";
        try{
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                Model_Distributor md = new Model_Distributor();
                
                md.setId_distributor(rs.getString("id_distributor"));
                md.setDistributor_name(rs.getString("distributor_name"));
                md.setLocation(rs.getString("location"));
                md.setNo_telp(rs.getString("no_telp"));
                md.setPic_distributor(rs.getString("pic_distributor"));
                list.add(md);
            }
            return list;
        }catch(SQLException ex){
            java.util.logging.Logger.getLogger(DAO_Distributor.class.getName()).log(Level.SEVERE,null,ex);
            return null;
        }finally{
            if(st!=null){
                try{
                    st.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Distributor.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
            if(rs!=null){
                try{
                    rs.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Distributor.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }
        
    }

    @Override
    public List<Model_Distributor> getData2() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Model_Distributor> search(String str) {
        
     {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs=null;        
        String sql = "SELECT id_distributor, distributor_name, location, no_telp, pic_distributor FROM mst_distributor where sts_active= '1' AND (id_distributor LIKE '%"+str+"%' OR distributor_name LIKE '%"+str+"%')";
        try{
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                Model_Distributor md = new Model_Distributor();
                md.setId_distributor(rs.getString("id_distributor"));
                md.setDistributor_name(rs.getString("distributor_name"));
                md.setLocation(rs.getString("location"));
                md.setNo_telp(rs.getString("no_telp"));
                md.setPic_distributor(rs.getString("pic_distributor"));
                list.add(md);
            }
            return list;
        }catch(SQLException ex){
            java.util.logging.Logger.getLogger(DAO_Distributor.class.getName()).log(Level.SEVERE,null,ex);
            return null;
        }finally{
            if(st!=null){
                try{
                    st.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Distributor.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
            if(rs!=null){
                try{
                    rs.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Distributor.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }
    }
    }
    
}
