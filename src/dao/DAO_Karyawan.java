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
import model.Model_Karyawan;
import service.Service_Karyawan;

/**
 *
 * @author rafii
 */
public class DAO_Karyawan implements Service_Karyawan{
    private Connection conn;
    public DAO_Karyawan(){
        conn = (Connection) Koneksi.getConnection();
    }
    @Override
    public void addData(Model_Karyawan mdl_Karyawan) {
        PreparedStatement st = null;
        String sql = "INSERT INTO mst_Karyawan (id_karyawan,nama_lengkap,gender,nik,sts_active,created_by,created_date,updated_by,updated_date) VALUES(?,?,?,?,?,?,?,?,?) ";
        try{    
            st = conn.prepareStatement(sql);
            st.setString(1,mdl_Karyawan.getId_karyawan());
            st.setString(2,mdl_Karyawan.getNama_lengkap());
            st.setString(3,mdl_Karyawan.getGender());
            st.setString(4,mdl_Karyawan.getNik());
            st.setString(5,"1");
            st.setString(6,mdl_Karyawan.getCreated_by());
            st.setString(7,mdl_Karyawan.getCreated_date());
            st.setString(8,mdl_Karyawan.getUpdated_by());
            st.setString(9,mdl_Karyawan.getUpdated_date());
            st.executeUpdate(); 
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Failed to Add data");
            java.util.logging.Logger.getLogger(DAO_Karyawan.class.getName()).log(Level.SEVERE,null,ex);
        }finally{
            if(st!=null){
                try{
                    st.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Karyawan.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }        
    }
    

    @Override
    public void updateData(Model_Karyawan mdl_Karyawan) {
        PreparedStatement st = null;
        String sql = "Update mst_Karyawan set id_karyawan=?,nama_lengkap=?,nik=?,gender=?,updated_by=?,updated_by=?,updated_date=? where id_karyawan='"+mdl_Karyawan.getId_karyawan()+"'";
        try{    
            st = conn.prepareStatement(sql);
            st.setString(1,mdl_Karyawan.getId_karyawan());
            st.setString(2,mdl_Karyawan.getNama_lengkap());
            st.setString(3,mdl_Karyawan.getNik());
            st.setString(4,mdl_Karyawan.getGender());
            st.setString(5,"1");
            st.setString(6,mdl_Karyawan.getUpdated_by());
            st.setString(7,mdl_Karyawan.getUpdated_date());
            st.executeUpdate(); 
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Failed to Update data");
            java.util.logging.Logger.getLogger(DAO_Karyawan.class.getName()).log(Level.SEVERE,null,ex);
        }finally{
            if(st!=null){
                try{
                    st.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Karyawan.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }        
    }

    @Override
    public void deleteData(Model_Karyawan mdl_Karyawan) {
        PreparedStatement st= null;
        String sql="Update mst_karyawan set sts_active=? where id_karyawan='"+mdl_Karyawan.getId_karyawan()+"'";
        try{
            st = conn.prepareStatement(sql);
            st.setString(1,"0");
            
            st.executeUpdate(); 
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Failed to Delete data");
            java.util.logging.Logger.getLogger(DAO_Karyawan.class.getName()).log(Level.SEVERE,null,ex);
        }finally{
            if(st!=null){
                try{
                    st.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Karyawan.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }
    }


    @Override
    public List<Model_Karyawan> getData() {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs=null;
        String sql = "SELECT id_karyawan,nama_lengkap,nik,gender FROM mst_karyawan where sts_active='1' ORDER BY nama_lengkap ASC;";
        try{
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                Model_Karyawan mk = new Model_Karyawan();
                
                mk.setId_karyawan(rs.getString("id_karyawan"));
                mk.setNama_lengkap(rs.getString("nama_lengkap"));
                mk.setNik(rs.getString("nik"));
                mk.setGender(rs.getString("gender"));
                list.add(mk);
            }
            return list;
        }catch(SQLException ex){
            java.util.logging.Logger.getLogger(DAO_Karyawan.class.getName()).log(Level.SEVERE,null,ex);
            return null;
        }finally{
            if(st!=null){
                try{
                    st.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Karyawan.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
            if(rs!=null){
                try{
                    rs.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Karyawan.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }
        
    }

    @Override
    public List<Model_Karyawan> getData2() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
