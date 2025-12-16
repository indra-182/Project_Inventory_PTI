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
import model.Model_Group;
import service.Service_Group;

/**
 *
 * @author rafii
 */
public class DAO_Group implements Service_Group{
    private Connection conn;
    public DAO_Group(){
        conn = (Connection) Koneksi.getConnection();
    }
    @Override
    public void addData(Model_Group mdl_group) {
        PreparedStatement st = null;
        String sql = "INSERT INTO mst_group (id_group,group_name,sts_active,created_by,created_date,updated_by,updated_date) VALUES(?,?,?,?,?,?,?) ";
        try{    
            st = conn.prepareStatement(sql);
            st.setString(1,mdl_group.getId_group());
            st.setString(2,mdl_group.getGroup_name());
            st.setString(3,"1");
            st.setString(4,mdl_group.getCreated_by());
            st.setString(5,mdl_group.getCreated_date());
            st.setString(6,mdl_group.getUpdated_by());
            st.setString(7,mdl_group.getUpdated_date());
            st.executeUpdate(); 
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Failed to Add data");
            java.util.logging.Logger.getLogger(DAO_Group.class.getName()).log(Level.SEVERE,null,ex);
        }finally{
            if(st!=null){
                try{
                    st.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Group.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }        
    }

    @Override
    public void updateData(Model_Group mdl_group) {
        PreparedStatement st = null;
        String sql = "Update mst_group set group_name=?,sts_active=?,updated_by=?,updated_date=? where id_group='"+mdl_group.getId_group()+"'";
        try{    
            st = conn.prepareStatement(sql);
            st.setString(1,mdl_group.getGroup_name());
            st.setString(2,"1");
            st.setString(3,mdl_group.getUpdated_by());
            st.setString(4,mdl_group.getUpdated_date());
            st.executeUpdate(); 
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Failed to Update data");
            java.util.logging.Logger.getLogger(DAO_Group.class.getName()).log(Level.SEVERE,null,ex);
        }finally{
            if(st!=null){
                try{
                    st.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Group.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }        
    }

    @Override
    public void deleteData(Model_Group mdl_group) {
        
     PreparedStatement st= null;
        String sql="Update mst_group set sts_active=? where id_group='"+mdl_group.getId_group()+"'";
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
    public List<Model_Group> getData() {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs=null;
        String sql = "select id_group,group_name from mst_group where sts_active='1'; ";
        try{
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                Model_Group mk = new Model_Group();
                
                mk.setId_group(rs.getString("id_group"));
                mk.setGroup_name(rs.getString("group_name"));
                
                list.add(mk);
            }
            return list;
        }catch(SQLException ex){
            java.util.logging.Logger.getLogger(DAO_Group.class.getName()).log(Level.SEVERE,null,ex);
            return null;
        }finally{
            if(st!=null){
                try{
                    st.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Group.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
            if(rs!=null){
                try{
                    rs.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Group.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }
        
    }

    @Override
    public List<Model_Group> getData2() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
