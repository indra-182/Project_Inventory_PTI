package dao;

import config.Koneksi;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.JOptionPane;
import model.Model_Cart_Outgoing;
import model.Model_Detail_Outgoing;
import model.Model_Item;
import model.Model_Outgoing;
import model.Model_Project;
import model.Model_Type;
import service.Service_Detail_Outgoing;

public class DAO_Detail_Outgoing implements Service_Detail_Outgoing {
    private Connection conn;

    public DAO_Detail_Outgoing() {
        conn = Koneksi.getConnection();
    }

    @Override
    public void addData(Model_Cart_Outgoing mdl_in) {
        String sql = "INSERT INTO tx_detail_outgoing (id_outgoing,id_project,id_items,quantity,sts_active,created_by,created_date,updated_by,updated_date) VALUES(?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, mdl_in.getModo().getMoou().getId_outgoing());
            st.setString(2, mdl_in.getMopr().getId_project());
            st.setString(3, mdl_in.getMoit().getId_items());
            st.setString(4, mdl_in.getModo().getQuantity());
            st.setString(5, "1");
            st.setString(6, mdl_in.getModo().getCreated_by());
            st.setString(7, mdl_in.getModo().getCreated_date());
            st.setString(8, mdl_in.getModo().getUpdated_by());
            st.setString(9, mdl_in.getModo().getUpdated_date());
            st.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Failed to Add data");
            java.util.logging.Logger.getLogger(DAO_Detail_Outgoing.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void sumTotal(Model_Detail_Outgoing mdl_det_in) {
        String sql = "SELECT SUM(total_price) FROM tx_cart_incoming";
        try (PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_Detail_Outgoing.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delCart(Model_Detail_Outgoing mdl_det_in) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Model_Detail_Outgoing> getData(String id) {
        List<Model_Detail_Outgoing> list = new ArrayList<>();
        String sql = "SELECT i.id_outgoing, di.id_items, d.project_name, t.type_name, it.items_name, it.unit, it.brand, di.quantity FROM tx_detail_outgoing di JOIN tx_outgoing i ON di.id_outgoing = i.id_outgoing JOIN mst_item it ON it.id_items = di.id_items JOIN mst_type t ON t.id_type = it.id_type JOIN mst_project d ON i.id_project = d.id_project WHERE i.sts_active = ? AND di.id_outgoing = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, "1");
            st.setString(2, id);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Model_Detail_Outgoing mdi = new Model_Detail_Outgoing();
                    Model_Outgoing min = new Model_Outgoing();
                    Model_Project md = new Model_Project();
                    Model_Item mi = new Model_Item();
                    Model_Type mt = new Model_Type();
                    min.setId_outgoing(rs.getString("id_outgoing"));
                    mi.setId_items(rs.getString("id_items"));
                    md.setProject_name(rs.getString("project_name"));
                    mi.setItems_name(rs.getString("items_name"));
                    mi.setBrand(rs.getString("brand"));
                    mt.setType_name(rs.getString("type_name"));
                    mi.setUnit(rs.getString("unit"));
                    mdi.setQuantity(rs.getString("quantity"));
                    mdi.setMoou(min);
                    mdi.setMoit(mi);
                    mdi.setMopr(md);
                    mdi.setMoty(mt);
                    list.add(mdi);
                }
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_Detail_Outgoing.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List<Model_Detail_Outgoing> getData2() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String nomor() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String nomor2() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void useStock(Model_Detail_Outgoing mdl_det) {
        String sql = "UPDATE mst_item SET stock = stock - ? WHERE id_items = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, mdl_det.getQuantity());
            st.setString(2, mdl_det.getMoit().getId_items());
            st.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Failed to Update data");
            java.util.logging.Logger.getLogger(DAO_Detail_Outgoing.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
