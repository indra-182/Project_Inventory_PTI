package dao;

import config.Koneksi;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.JOptionPane;
import model.Model_Group;
import service.Service_Group;

public class DAO_Group implements Service_Group {
    private Connection conn;

    public DAO_Group() {
        conn = Koneksi.getConnection();
    }
    @Override
    public void addData(Model_Group mdl_group) {
        String sql = "INSERT INTO mst_group (id_group, group_name, sts_active, created_by, created_date, updated_by, updated_date) VALUES (?,?,?,?,?,?,?)";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, mdl_group.getId_group());
            st.setString(2, mdl_group.getGroup_name());
            st.setString(3, "1");
            st.setString(4, mdl_group.getCreated_by());
            st.setString(5, mdl_group.getCreated_date());
            st.setString(6, mdl_group.getUpdated_by());
            st.setString(7, mdl_group.getUpdated_date());
            st.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Failed to Add data");
            java.util.logging.Logger.getLogger(DAO_Group.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateData(Model_Group mdl_group) {
        String sql = "UPDATE mst_group SET group_name = ?, sts_active = ?, updated_by = ?, updated_date = ? WHERE id_group = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, mdl_group.getGroup_name());
            st.setString(2, "1");
            st.setString(3, mdl_group.getUpdated_by());
            st.setString(4, mdl_group.getUpdated_date());
            st.setString(5, mdl_group.getId_group());
            st.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Failed to Update data");
            java.util.logging.Logger.getLogger(DAO_Group.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteData(Model_Group mdl_group) {
        String sql = "UPDATE mst_group SET sts_active = ? WHERE id_group = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, "0");
            st.setString(2, mdl_group.getId_group());
            st.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Failed to Delete data");
            java.util.logging.Logger.getLogger(DAO_Group.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Model_Group> getData() {
        List<Model_Group> list = new ArrayList<>();
        String sql = "SELECT id_group, group_name FROM mst_group WHERE sts_active = '1'";
        
        try (PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            
            while (rs.next()) {
                Model_Group mk = new Model_Group();
                mk.setId_group(rs.getString("id_group"));
                mk.setGroup_name(rs.getString("group_name"));
                list.add(mk);
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_Group.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List<Model_Group> getData2() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
