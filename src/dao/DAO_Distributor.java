package dao;

import config.Koneksi;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.JOptionPane;
import model.Model_Distributor;
import service.Service_Distributor;

public class DAO_Distributor implements Service_Distributor{
    private Connection conn;
    
    public DAO_Distributor(){
        conn = Koneksi.getConnection();
    }
    @Override
    public void addData(Model_Distributor mdl_Distributor) {
        String sql = "INSERT INTO mst_distributor (id_distributor,distributor_name,location,no_telp,pic_distributor,sts_active,created_by,created_date,updated_by,updated_date) VALUES(?,?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, mdl_Distributor.getId_distributor());
            st.setString(2, mdl_Distributor.getDistributor_name());
            st.setString(3, mdl_Distributor.getLocation());
            st.setString(4, mdl_Distributor.getNo_telp());
            st.setString(5, mdl_Distributor.getPic_distributor());
            st.setString(6, "1");
            st.setString(7, mdl_Distributor.getCreated_by());
            st.setString(8, mdl_Distributor.getCreated_date());
            st.setString(9, mdl_Distributor.getUpdated_by());
            st.setString(10, mdl_Distributor.getUpdated_date());
            st.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Failed to Add data");
            java.util.logging.Logger.getLogger(DAO_Distributor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateData(Model_Distributor mdl_Distributor) {
        String sql = "UPDATE mst_distributor SET distributor_name=?, location=?, no_telp=?, pic_distributor=?, sts_active=?, updated_by=?, updated_date=? WHERE id_distributor=?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, mdl_Distributor.getDistributor_name());
            st.setString(2, mdl_Distributor.getLocation());
            st.setString(3, mdl_Distributor.getNo_telp());
            st.setString(4, mdl_Distributor.getPic_distributor());
            st.setString(5, "1");
            st.setString(6, mdl_Distributor.getUpdated_by());
            st.setString(7, mdl_Distributor.getUpdated_date());
            st.setString(8, mdl_Distributor.getId_distributor());
            st.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Failed to Update data");
            java.util.logging.Logger.getLogger(DAO_Distributor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteData(Model_Distributor mdl_Distributor) {
        String sql = "UPDATE mst_distributor SET sts_active=? WHERE id_distributor=?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, "0");
            st.setString(2, mdl_Distributor.getId_distributor());
            st.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Failed to Delete data");
            java.util.logging.Logger.getLogger(DAO_Distributor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Model_Distributor> getData() {
        List<Model_Distributor> list = new ArrayList<>();
        String sql = "SELECT id_distributor, distributor_name, location, no_telp, pic_distributor FROM mst_distributor WHERE sts_active='1'";
        
        try (PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            
            while (rs.next()) {
                Model_Distributor md = new Model_Distributor();
                md.setId_distributor(rs.getString("id_distributor"));
                md.setDistributor_name(rs.getString("distributor_name"));
                md.setLocation(rs.getString("location"));
                md.setNo_telp(rs.getString("no_telp"));
                md.setPic_distributor(rs.getString("pic_distributor"));
                list.add(md);
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_Distributor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List<Model_Distributor> getData2() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Model_Distributor> search(String str) {
        List<Model_Distributor> list = new ArrayList<>();
        String sql = "SELECT id_distributor, distributor_name, location, no_telp, pic_distributor FROM mst_distributor WHERE sts_active='1' AND (id_distributor LIKE ? OR distributor_name LIKE ?)";
        
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            String searchPattern = "%" + str + "%";
            st.setString(1, searchPattern);
            st.setString(2, searchPattern);
            
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Model_Distributor md = new Model_Distributor();
                    md.setId_distributor(rs.getString("id_distributor"));
                    md.setDistributor_name(rs.getString("distributor_name"));
                    md.setLocation(rs.getString("location"));
                    md.setNo_telp(rs.getString("no_telp"));
                    md.setPic_distributor(rs.getString("pic_distributor"));
                    list.add(md);
                }
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_Distributor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
}
