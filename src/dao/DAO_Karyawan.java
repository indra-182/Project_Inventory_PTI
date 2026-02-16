package dao;

import config.Koneksi;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.JOptionPane;
import model.Model_Karyawan;
import service.Service_Karyawan;

public class DAO_Karyawan implements Service_Karyawan{
    private Connection conn;
    
    public DAO_Karyawan(){
        conn = Koneksi.getConnection();
    }
    @Override
    public void addData(Model_Karyawan mdl_Karyawan) {
        String sql = "INSERT INTO mst_Karyawan (id_karyawan,nama_lengkap,gender,nik,sts_active,created_by,created_date,updated_by,updated_date) VALUES(?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, mdl_Karyawan.getId_karyawan());
            st.setString(2, mdl_Karyawan.getNama_lengkap());
            st.setString(3, mdl_Karyawan.getGender());
            st.setString(4, mdl_Karyawan.getNik());
            st.setString(5, "1");
            st.setString(6, mdl_Karyawan.getCreated_by());
            st.setString(7, mdl_Karyawan.getCreated_date());
            st.setString(8, mdl_Karyawan.getUpdated_by());
            st.setString(9, mdl_Karyawan.getUpdated_date());
            st.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Failed to Add data");
            java.util.logging.Logger.getLogger(DAO_Karyawan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @Override
    public void updateData(Model_Karyawan mdl_Karyawan) {
        String sql = "UPDATE mst_Karyawan SET id_karyawan=?, nama_lengkap=?, nik=?, gender=?, updated_by=?, updated_by=?, updated_date=? WHERE id_karyawan=?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, mdl_Karyawan.getId_karyawan());
            st.setString(2, mdl_Karyawan.getNama_lengkap());
            st.setString(3, mdl_Karyawan.getNik());
            st.setString(4, mdl_Karyawan.getGender());
            st.setString(5, "1");
            st.setString(6, mdl_Karyawan.getUpdated_by());
            st.setString(7, mdl_Karyawan.getUpdated_date());
            st.setString(8, mdl_Karyawan.getId_karyawan());
            st.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Failed to Update data");
            java.util.logging.Logger.getLogger(DAO_Karyawan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteData(Model_Karyawan mdl_Karyawan) {
        String sql = "UPDATE mst_karyawan SET sts_active=? WHERE id_karyawan=?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, "0");
            st.setString(2, mdl_Karyawan.getId_karyawan());
            st.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Failed to Delete data");
            java.util.logging.Logger.getLogger(DAO_Karyawan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    public List<Model_Karyawan> getData() {
        List<Model_Karyawan> list = new ArrayList<>();
        String sql = "SELECT id_karyawan, nama_lengkap, nik, gender FROM mst_karyawan WHERE sts_active='1' ORDER BY nama_lengkap ASC";
        
        try (PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            
            while (rs.next()) {
                Model_Karyawan mk = new Model_Karyawan();
                mk.setId_karyawan(rs.getString("id_karyawan"));
                mk.setNama_lengkap(rs.getString("nama_lengkap"));
                mk.setNik(rs.getString("nik"));
                mk.setGender(rs.getString("gender"));
                list.add(mk);
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_Karyawan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List<Model_Karyawan> getData2() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
