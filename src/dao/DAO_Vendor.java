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
import model.Model_Vendor;
import service.Service_Vendor;

/**
 *
 * @author rafii
 */
public class DAO_Vendor implements Service_Vendor{
    private Connection conn;
    public DAO_Vendor(){
        conn = (Connection) Koneksi.getConnection();
    }
    @Override
    public void addData(Model_Vendor mdl_vendor) {
        PreparedStatement st = null;
        String sql = "INSERT INTO mst_vendor (id_vendor,vendor_name,location,no_telp,pic_vendor,sts_active,created_by,created_date,updated_by,updated_date) VALUES(?,?,?,?,?,?,?,?,?,?) ";
        try{    
            st = conn.prepareStatement(sql);
            st.setString(1,mdl_vendor.getId_vendor());
            st.setString(2,mdl_vendor.getVendor_name());
            st.setString(3,mdl_vendor.getLocation());
            st.setString(4,mdl_vendor.getNo_telp());
            st.setString(5,mdl_vendor.getPic_vendor());
            st.setString(6,"1");
            st.setString(7,mdl_vendor.getCreated_by());
            st.setString(8,mdl_vendor.getCreated_date());
            st.setString(9,mdl_vendor.getUpdated_by());
            st.setString(10,mdl_vendor.getUpdated_date());
            st.executeUpdate(); 
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Failed to Add data");
            java.util.logging.Logger.getLogger(DAO_Vendor.class.getName()).log(Level.SEVERE,null,ex);
        }finally{
            if(st!=null){
                try{
                    st.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Vendor.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }        
    }

    @Override
    public void updateData(Model_Vendor mdl_Vendor) {
        PreparedStatement st = null;
        String sql = "Update mst_Vendor set vendor_name=?,location=?,no_telp=?,pic_vendor=?,sts_active=?,updated_by=?,updated_date=? where id_vendor='"+mdl_Vendor.getId_vendor()+"'";
        try{    
            st = conn.prepareStatement(sql);
            st.setString(1,mdl_Vendor.getVendor_name());
            st.setString(2,mdl_Vendor.getLocation());
            st.setString(3,mdl_Vendor.getNo_telp());
            st.setString(4,mdl_Vendor.getPic_vendor());
            st.setString(5,"1");
            st.setString(6,mdl_Vendor.getUpdated_by());
            st.setString(7,mdl_Vendor.getUpdated_date());
            st.executeUpdate(); 
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Failed to Update data");
            java.util.logging.Logger.getLogger(DAO_Vendor.class.getName()).log(Level.SEVERE,null,ex);
        }finally{
            if(st!=null){
                try{
                    st.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Vendor.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }        
    }

    @Override
    public void deleteData(Model_Vendor mdl_Vendor) {
     PreparedStatement st= null;
        String sql="Update mst_Vendor set sts_active=? where id_vendor='"+mdl_Vendor.getId_vendor()+"'";
        try{
            st = conn.prepareStatement(sql);
            st.setString(1,"0");
            
            st.executeUpdate(); 
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Failed to Delete data");
            java.util.logging.Logger.getLogger(DAO_Vendor.class.getName()).log(Level.SEVERE,null,ex);
        }finally{
            if(st!=null){
                try{
                    st.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Vendor.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }
    }


    @Override
    public List<Model_Vendor> getData() {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs=null;
        String sql = "SELECT id_vendor,vendor_name,location,no_telp,pic_vendor FROM mst_vendor where sts_active='1';";
        try{
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                Model_Vendor mv = new Model_Vendor();
                
                mv.setId_vendor(rs.getString("id_vendor"));
                mv.setVendor_name(rs.getString("vendor_name"));
                mv.setLocation(rs.getString("location"));
                mv.setNo_telp(rs.getString("no_telp"));
                mv.setPic_vendor(rs.getString("pic_vendor"));
                list.add(mv);
            }
            return list;
        }catch(SQLException ex){
            java.util.logging.Logger.getLogger(DAO_Vendor.class.getName()).log(Level.SEVERE,null,ex);
            return null;
        }finally{
            if(st!=null){
                try{
                    st.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Vendor.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
            if(rs!=null){
                try{
                    rs.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Vendor.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }
        
    }

    @Override
    public List<Model_Vendor> getData2() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
