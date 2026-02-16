package dao;

import config.Koneksi;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.JOptionPane;
import model.Model_Menu;
import service.Service_Menu;

public class DAO_Menu implements Service_Menu{
    private Connection conn;
    
    public DAO_Menu(){
        conn = Koneksi.getConnection();
    }
    @Override
    public void addData(Model_Menu mdl_Menu) {
        String sql = "INSERT INTO mst_menu (id_menu,name_menu,sts_active,created_by,created_date,updated_by,updated_date) VALUES(?,?,?,?,?,?,?)";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, mdl_Menu.getId_menu());
            st.setString(2, mdl_Menu.getName_menu());
            st.setString(3, "1");
            st.setString(4, mdl_Menu.getCreated_by());
            st.setString(5, mdl_Menu.getCreated_date());
            st.setString(6, mdl_Menu.getUpdated_by());
            st.setString(7, mdl_Menu.getUpdated_date());
            st.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Failed to Add data");
            java.util.logging.Logger.getLogger(DAO_Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateData(Model_Menu mdl_Menu) {
        String sql = "UPDATE mst_menu SET name_menu=?, updated_by=?, updated_date=? WHERE id_menu=?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, mdl_Menu.getName_menu());
            st.setString(2, mdl_Menu.getUpdated_by());
            st.setString(3, mdl_Menu.getUpdated_date());
            st.setString(4, mdl_Menu.getId_menu());
            st.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Failed to Update data");
            java.util.logging.Logger.getLogger(DAO_Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteData(Model_Menu mdl_Menu) {
        String sql = "UPDATE mst_menu SET sts_active=? WHERE id_menu=?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, "0");
            st.setString(2, mdl_Menu.getId_menu());
            st.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Failed to Delete data");
            java.util.logging.Logger.getLogger(DAO_Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    public List<Model_Menu> getData() {
        List<Model_Menu> list = new ArrayList<>();
        String sql = "SELECT id_menu, UPPER(Name_menu) AS name_menu FROM mst_menu WHERE sts_active=1 ORDER BY id_menu ASC";
        
        try (PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            
            while (rs.next()) {
                Model_Menu mk = new Model_Menu();
                mk.setId_menu(rs.getString("id_menu"));
                mk.setName_menu(rs.getString("name_menu"));
                list.add(mk);
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List<Model_Menu> getData2() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
