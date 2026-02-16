package dao;

import config.Koneksi;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.JOptionPane;
import model.Model_Cart_Incoming;
import model.Model_Detail_Incoming;
import model.Model_Distributor;
import model.Model_Incoming;
import model.Model_Item;
import model.Model_Type;
import service.Service_Detail_Incoming;

public class DAO_Detail_Incoming implements Service_Detail_Incoming {
    private Connection conn;

    public DAO_Detail_Incoming() {
        conn = Koneksi.getConnection();
    }

    @Override
    public void addData(Model_Cart_Incoming mdl_in) {
        String sql = "INSERT INTO tx_detail_incoming (id_incoming,id_distributor,id_items,quantity,total_price,sts_active,created_by,created_date,updated_by,updated_date) VALUES(?,?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, mdl_in.getModi().getMoin().getId_incoming());
            st.setString(2, mdl_in.getModt().getId_distributor());
            st.setString(3, mdl_in.getMoit().getId_items());
            st.setString(4, mdl_in.getModi().getQuantity());
            st.setString(5, mdl_in.getModi().getTotal_price());
            st.setString(6, "1");
            st.setString(7, mdl_in.getCreated_by());
            st.setString(8, mdl_in.getCreated_date());
            st.setString(9, mdl_in.getUpdated_by());
            st.setString(10, mdl_in.getUpdated_date());
            st.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Failed to Add data");
            java.util.logging.Logger.getLogger(DAO_Detail_Incoming.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void sumTotal(Model_Detail_Incoming mdl_det_in) {
        String sql = "SELECT SUM(total_price) FROM tx_cart_incoming";
        try (PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            if (rs.next()) {
                mdl_det_in.setTotal_price(rs.getString(1));
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_Detail_Incoming.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delCart(Model_Detail_Incoming mdl_det_in) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Model_Detail_Incoming> getData(String id) {
        List<Model_Detail_Incoming> list = new ArrayList<>();
        String sql = "SELECT i.id_incoming, di.id_items, d.distributor_name, t.type_name, it.items_name, it.unit, it.price, it.brand, di.quantity, di.total_price FROM tx_detail_incoming di JOIN tx_incoming i ON di.id_incoming = i.id_incoming JOIN mst_item it ON it.id_items = di.id_items JOIN mst_type t ON t.id_type = it.id_type JOIN mst_distributor d ON i.id_distributor = d.id_distributor WHERE i.sts_active = ? AND di.id_incoming = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, "1");
            st.setString(2, id);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Model_Detail_Incoming mdi = new Model_Detail_Incoming();
                    Model_Incoming min = new Model_Incoming();
                    Model_Distributor md = new Model_Distributor();
                    Model_Item mi = new Model_Item();
                    Model_Type mt = new Model_Type();
                    min.setId_incoming(rs.getString("id_incoming"));
                    mi.setId_items(rs.getString("id_items"));
                    md.setDistributor_name(rs.getString("distributor_name"));
                    min.setTotal_price(rs.getString("total_price"));
                    mi.setItems_name(rs.getString("items_name"));
                    mi.setBrand(rs.getString("brand"));
                    mt.setType_name(rs.getString("type_name"));
                    mi.setPrice(rs.getString("price"));
                    mi.setUnit(rs.getString("unit"));
                    mdi.setQuantity(rs.getString("quantity"));
                    mdi.setMoin(min);
                    mdi.setMoit(mi);
                    mdi.setModt(md);
                    mdi.setMoty(mt);
                    list.add(mdi);
                }
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_Detail_Incoming.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List<Model_Detail_Incoming> getData2() {
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
    public void addStock(Model_Detail_Incoming mdl_det) {
        String sql = "UPDATE mst_item SET stock = stock + ? WHERE id_items = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, mdl_det.getQuantity());
            st.setString(2, mdl_det.getMoit().getId_items());
            st.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Failed to Update data");
            java.util.logging.Logger.getLogger(DAO_Detail_Incoming.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
