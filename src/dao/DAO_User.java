package dao;

import config.Koneksi;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.JOptionPane;
import model.Model_Group;
import model.Model_User;
import model.Model_Karyawan;
import model.Model_Project;
import model.Model_Vendor;
import service.Service_User;

public class DAO_User implements Service_User{
    private Connection conn;
    
    public DAO_User(){
        conn = Koneksi.getConnection();
    }
    @Override
    public void addData(Model_User mdl_User) {
        String sql = "INSERT INTO mst_user (username,password,id_group,id_karyawan,sts_active,created_by,created_date,updated_by,updated_date,last_login) VALUES(?,?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, mdl_User.getUsername());
            st.setString(2, Encrypt.getmd5java(mdl_User.getPassword()));
            st.setString(3, mdl_User.getMogr().getId_group());
            st.setString(4, mdl_User.getId_karyawan());
            st.setString(5, "1");
            st.setString(6, mdl_User.getCreated_by());
            st.setString(7, mdl_User.getCreated_date());
            st.setString(8, mdl_User.getUpdated_by());
            st.setString(9, mdl_User.getUpdated_date());
            st.setString(10, mdl_User.getLast_login());
            st.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Failed to Add data");
            java.util.logging.Logger.getLogger(DAO_User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateData(Model_User mdl_User) {
        String sql = "UPDATE mst_user SET id_group=?, updated_by=?, updated_date=? WHERE username=?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, mdl_User.getMogr().getId_group());
            st.setString(2, mdl_User.getUpdated_by());
            st.setString(3, mdl_User.getUpdated_date());
            st.setString(4, mdl_User.getUsername());
            st.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Failed to Update data");
            java.util.logging.Logger.getLogger(DAO_User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteData(Model_User mdl_User) {
        String sql = "UPDATE mst_user SET sts_active=? WHERE username=?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, "0");
            st.setString(2, mdl_User.getUsername());
            st.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Failed to Delete data");
            java.util.logging.Logger.getLogger(DAO_User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    public List<Model_User> getData() {
        List<Model_User> list = new ArrayList<>();
        String sql = "SELECT a.id_karyawan, b.nama_lengkap AS nama_karyawan, a.username, a.last_login, MD5(a.password) AS password, c.group_name FROM mst_user a LEFT JOIN mst_karyawan b ON a.id_karyawan = b.id_karyawan LEFT JOIN mst_group c ON a.id_group = c.id_group WHERE a.sts_active = 1 ORDER BY b.nama_lengkap ASC";
        
        try (PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            
            while (rs.next()) {
                Model_User mk = new Model_User();
                Model_Karyawan mv = new Model_Karyawan();
                Model_Group mg = new Model_Group();
                mk.setId_karyawan(rs.getString("id_karyawan"));
                mk.setNama_lengkap(rs.getString("nama_karyawan"));
                mg.setGroup_name(rs.getString("group_name"));
                mk.setUsername(rs.getString("username"));
                mk.setPassword(rs.getString("password"));
                mk.setLast_login(rs.getString("last_login"));
                mk.setMovr(mv);
                mk.setMogr(mg);
                list.add(mk);
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List<Model_User> getDataKaryawan() {
        List<Model_User> list = new ArrayList<>();
        String sql = "SELECT id_karyawan, nama_lengkap FROM mst_karyawan WHERE sts_active='1'";
        
        try (PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            
            while (rs.next()) {
                Model_User mk = new Model_User();
                Model_Karyawan mv = new Model_Karyawan();
                mv.setId_karyawan(rs.getString("id_karyawan"));
                mv.setNama_lengkap(rs.getString("nama_lengkap"));
                mk.setMovr(mv);
                list.add(mk);
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_Project.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    @Override
    public List<Model_User> getDataGroup() {
        List<Model_User> list = new ArrayList<>();
        String sql = "SELECT id_group, group_name FROM mst_group WHERE sts_active='1'";
        
        try (PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            
            while (rs.next()) {
                Model_User mk = new Model_User();
                Model_Group mv = new Model_Group();
                mv.setId_group(rs.getString("id_group"));
                mv.setGroup_name(rs.getString("group_name"));
                mk.setMogr(mv);
                list.add(mk);
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_Project.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}

