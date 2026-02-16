package dao;

import config.Koneksi;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.JOptionPane;
import model.Model_Cart_Incoming;
import model.Model_Cart_Outgoing;
import model.Model_Detail_Incoming;
import model.Model_Detail_Outgoing;
import model.Model_Distributor;
import model.Model_Incoming;
import model.Model_Item;
import model.Model_Outgoing;
import model.Model_Project;
import model.Model_Type;
import service.Service_Cart_Incoming;
import service.Service_Cart_Outgoing;
import service.Service_Detail_Incoming;
import service.Service_Incoming;

public class DAO_Cart_Outgoing implements Service_Cart_Outgoing{
    private Connection conn;

    public DAO_Cart_Outgoing() {
        conn = Koneksi.getConnection();
    }

    @Override
    public void addData(Model_Cart_Outgoing mdl_cart_in) {
        String sql = "INSERT INTO tx_cart_outgoing (id_outgoing, id_project, id_items, quantity, unit, type, brand) VALUES (?,?,?,?,?,?,?)";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, mdl_cart_in.getModo().getMoou().getId_outgoing());
            st.setString(2, mdl_cart_in.getMopr().getId_project());
            st.setString(3, mdl_cart_in.getMoit().getId_items());
            st.setString(4, mdl_cart_in.getModo().getQuantity());
            st.setString(5, mdl_cart_in.getMoit().getUnit());
            st.setString(6, mdl_cart_in.getMoty().getType_name());
            st.setString(7, mdl_cart_in.getMoit().getBrand());
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data has been added successfully.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Failed to Add to Cart");
            java.util.logging.Logger.getLogger(DAO_Cart_Outgoing.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void sumTotal(Model_Cart_Outgoing mdl_cart_in) {
        
    }

    @Override
    public void delCart() {
        String sql = "DELETE FROM tx_cart_outgoing";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Failed to Update Cart");
            java.util.logging.Logger.getLogger(DAO_Cart_Outgoing.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Model_Cart_Outgoing> getData() {
        List<Model_Cart_Outgoing> list = new ArrayList<>();
        String sql = "SELECT ci.id_outgoing, ci.id_project, d.project_name, ci.id_items, ci.type, it.items_name, ci.quantity, ci.unit, ci.brand FROM tx_cart_outgoing ci JOIN mst_project d ON ci.id_project = d.id_project JOIN mst_item it ON ci.id_items = it.id_items";
        
        try (PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            
            while (rs.next()) {
                Model_Cart_Outgoing mci = new Model_Cart_Outgoing();
                Model_Outgoing min = new Model_Outgoing();
                Model_Detail_Outgoing mdi = new Model_Detail_Outgoing();
                Model_Item mi = new Model_Item();
                Model_Project md = new Model_Project();
                Model_Type mt = new Model_Type();
                
                min.setId_outgoing(rs.getString("id_outgoing"));
                mdi.setMoou(min);
                md.setId_project(rs.getString("id_project"));
                md.setProject_name(rs.getString("project_name"));
                mi.setId_items(rs.getString("id_items"));
                mi.setItems_name(rs.getString("items_name"));
                mt.setType_name(rs.getString("type"));
                mdi.setQuantity(rs.getString("quantity"));
                mi.setUnit(rs.getString("unit"));
                mi.setBrand(rs.getString("brand"));
                
                mci.setModo(mdi);
                mci.setMopr(md);
                mci.setMoit(mi);
                mci.setMoty(mt);
                list.add(mci);
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_Cart_Outgoing.class.getName()).log(Level.SEVERE, null, ex);
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
    public void updCart(Model_Cart_Outgoing mdl_cart_in) {
        String sql = "UPDATE tx_cart_outgoing SET id_outgoing=?, id_project=?, id_items=?, quantity=?, unit=?, type=?, brand=? WHERE id_items=?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, mdl_cart_in.getModo().getMoou().getId_outgoing());
            st.setString(2, mdl_cart_in.getMopr().getId_project());
            st.setString(3, mdl_cart_in.getMoit().getId_items());
            st.setString(4, mdl_cart_in.getModo().getQuantity());
            st.setString(5, mdl_cart_in.getMoit().getUnit());
            st.setString(6, mdl_cart_in.getMoty().getType_name());
            st.setString(7, mdl_cart_in.getMoit().getBrand());
            st.setString(8, mdl_cart_in.getMoit().getId_items());
            st.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Failed to Update Cart");
            java.util.logging.Logger.getLogger(DAO_Cart_Outgoing.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteData(String id) {
        String sql = "DELETE FROM tx_cart_outgoing WHERE id_items = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, id);
            st.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Failed to Delete data");
            java.util.logging.Logger.getLogger(DAO_Cart_Outgoing.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

