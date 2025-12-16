/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import main.Menu_utama;
import config.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Model_Login;
import service.Service_Login;
import view.Form_Regist;
import view.Form_login;

/**
 *
 * @author rafii
 */
public class DAO_Login implements Service_Login{
    private Connection conn;
    public DAO_Login(){
        conn = (Connection) Koneksi.getConnection();
    }

    @Override
    public void proccesLogin(Model_Login login) {
        PreparedStatement st = null;
        ResultSet rs = null;
        String id=null,name=null,groupName=null,gender=null;
        String sql = "SELECT u.id_karyawan, k.nama_lengkap,k.gender, g.group_name FROM mst_user u JOIN mst_karyawan k ON u.id_karyawan = k.id_karyawan JOIN mst_group g ON u.id_group = g.id_group WHERE u.username = '"+login.getUsername()+"'"
                + "AND u.password='"+Encrypt.getmd5java(login.getPassword())+"'";
        
        //untuk encrypt pass
        //Encrypt.getmd5java(login.getPassword())+"'";
        
        try{
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            if(rs.next()){
                id=rs.getString("id_karyawan");
                name=rs.getString("nama_lengkap");
                groupName=rs.getString("group_name");
                gender=rs.getString("gender");
                
                String updateLogin = "UPDATE mst_user SET last_login = NOW() WHERE username = ?";
                PreparedStatement stUpdate = conn.prepareStatement(updateLogin);
                stUpdate.setString(1, login.getUsername());
                stUpdate.executeUpdate();
                stUpdate.close();
                
                Menu_utama menu = new Menu_utama(id,name,groupName,gender);
                menu.setVisible(true);
                menu.revalidate();
            }else{
                JOptionPane.showMessageDialog(null,"Invalid username or password","Message",JOptionPane.INFORMATION_MESSAGE);
                Form_login lg = new Form_login();
                lg.setVisible(true);
            }
            
        }catch(SQLException ex){
            Logger.getLogger(DAO_Login.class.getName()).log(Level.SEVERE,null,ex);
        }finally{
            if(st!=null){
                try{
                    st.close();
                }catch(SQLException ex){
                    Logger.getLogger(DAO_Login.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }
    }

    @Override
    public void registLogin(Model_Login login,Form_login fl,Form_Regist fr) {
        PreparedStatement st = null;
        String sql = "INSERT INTO mst_user (id_karyawan,id_group,username,password,sts_active,created_by,created_date,updated_by,updated_date,last_login) VALUES(?,?,?,?,?,?,?,?,?,?) ";
        try{    
            st = conn.prepareStatement(sql);
            
            st.setString(1,login.getMokr().getId_karyawan());
            st.setString(2,login.getMogr().getId_group());
            st.setString(3,login.getUsername());
            st.setString(4,Encrypt.getmd5java(login.getPassword()));
            st.setString(5,"1");
            st.setString(6,login.getCreated_by());
            st.setString(7,login.getCreated_date());
            st.setString(8,login.getUpdated_by());
            st.setString(9,login.getUpdated_date());
            st.setString(10,login.getLast_active());
            st.executeUpdate(); 
            JOptionPane.showMessageDialog(null, "Registration successful. You can now log in");
            fl.setVisible(true);
            
        }catch(SQLException ex){
            fr.setVisible(true);
            JOptionPane.showMessageDialog(null, "Registration failed.");
            java.util.logging.Logger.getLogger(DAO_Login.class.getName()).log(Level.SEVERE,null,ex);
        }finally{
            if(st!=null){
                try{
                    st.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Login.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }   
    }
}
