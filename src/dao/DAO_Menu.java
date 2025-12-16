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
import model.Model_Menu;
import service.Service_Menu;

/**
 *
 * @author rafii
 */
public class DAO_Menu implements Service_Menu{
    private Connection conn;
    public DAO_Menu(){
        conn = (Connection) Koneksi.getConnection();
    }
    @Override
    public void addData(Model_Menu mdl_Menu) {
        PreparedStatement st = null;
        String sql = "INSERT INTO mst_menu (id_menu,name_menu,sts_active,created_by,created_date,updated_by,updated_date) VALUES(?,?,?,?,?,?,?) ";
        try{    
            st = conn.prepareStatement(sql);
            st.setString(1,mdl_Menu.getId_menu());
            st.setString(2,mdl_Menu.getName_menu());
            st.setString(3,"1");
            st.setString(4,mdl_Menu.getCreated_by());
            st.setString(5,mdl_Menu.getCreated_date());
            st.setString(6,mdl_Menu.getUpdated_by());
            st.setString(7,mdl_Menu.getUpdated_date());
            st.executeUpdate(); 
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Failed to Add data");
            java.util.logging.Logger.getLogger(DAO_Menu.class.getName()).log(Level.SEVERE,null,ex);
        }finally{
            if(st!=null){
                try{
                    st.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Menu.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }        
    }

    @Override
    public void updateData(Model_Menu mdl_Menu) {
        PreparedStatement st = null;
        String sql = "Update mst_menu set name_menu=?,updated_by=?,updated_date=?  where id_menu='"+mdl_Menu.getId_menu()+"'";
        try{
            st = conn.prepareStatement(sql);            
            st.setString(1,mdl_Menu.getName_menu());
            st.setString(2,mdl_Menu.getUpdated_by());
            st.setString(3,mdl_Menu.getUpdated_date());
            st.executeUpdate(); 
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Failed to Update data");
            java.util.logging.Logger.getLogger(DAO_Menu.class.getName()).log(Level.SEVERE,null,ex);
        }finally{
            if(st!=null){
                try{
                    st.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Menu.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }
    }

    @Override
    public void deleteData(Model_Menu mdl_Menu) {
        PreparedStatement st= null;
        String sql="Update mst_menu set sts_active=? where id_menu='"+mdl_Menu.getId_menu()+"'";
        try{
            st = conn.prepareStatement(sql);
            st.setString(1,"0");
            
            st.executeUpdate(); 
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Failed to Delete data");
            java.util.logging.Logger.getLogger(DAO_Menu.class.getName()).log(Level.SEVERE,null,ex);
        }finally{
            if(st!=null){
                try{
                    st.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Menu.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }
    }


    @Override
    public List<Model_Menu> getData() {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs=null;
        String sql = "select id_menu,upper(Name_menu) as name_menu from mst_menu where sts_active=1 order by id_menu asc;";
        try{
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                Model_Menu mk = new Model_Menu();
                
                mk.setId_menu(rs.getString("id_menu"));
                mk.setName_menu(rs.getString("name_menu"));
                
                list.add(mk);
            }
            return list;
        }catch(SQLException ex){
            java.util.logging.Logger.getLogger(DAO_Menu.class.getName()).log(Level.SEVERE,null,ex);
            return null;
        }finally{
            if(st!=null){
                try{
                    st.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Menu.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
            if(rs!=null){
                try{
                    rs.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Menu.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }
        
    }

    @Override
    public List<Model_Menu> getData2() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
