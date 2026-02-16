package dao;

import config.Koneksi;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.JOptionPane;
import model.Model_Distributor;
import model.Model_Type;
import model.Model_Item;
import service.Service_Item;

public class DAO_Item implements Service_Item{
    private Connection conn;
    
    public DAO_Item(){
        conn = Koneksi.getConnection();
    }

    @Override
    public void addData(Model_Item mdl_Item) {
        String sql = "INSERT INTO mst_item (id_items,id_distributor,id_type,items_name,brand,unit,stock,price,sts_active,created_by,created_date,updated_by,updated_date) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, mdl_Item.getId_items());
            st.setString(2, mdl_Item.getModt().getId_distributor());
            st.setString(3, mdl_Item.getMoty().getId_type());
            st.setString(4, mdl_Item.getItems_name());
            st.setString(5, mdl_Item.getBrand());
            st.setString(6, mdl_Item.getUnit());
            st.setString(7, mdl_Item.getStock());
            st.setString(8, mdl_Item.getPrice());
            st.setString(9, "1");
            st.setString(10, mdl_Item.getCreated_by());
            st.setString(11, mdl_Item.getCreated_date());
            st.setString(12, mdl_Item.getUpdated_by());
            st.setString(13, mdl_Item.getUpdated_date());
            st.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Failed to Add data");
            java.util.logging.Logger.getLogger(DAO_Item.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateData(Model_Item mdl_Item) {
        String sql = "UPDATE mst_item SET items_name=?, brand=?, unit=?, stock=?, price=?, updated_by=?, updated_date=? WHERE id_items=?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, mdl_Item.getItems_name());
            st.setString(2, mdl_Item.getBrand());
            st.setString(3, mdl_Item.getUnit());
            st.setString(4, mdl_Item.getStock());
            st.setString(5, mdl_Item.getPrice());
            st.setString(6, mdl_Item.getUpdated_by());
            st.setString(7, mdl_Item.getUpdated_date());
            st.setString(8, mdl_Item.getId_items());
            st.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Failed to Update data");
            java.util.logging.Logger.getLogger(DAO_Item.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteData(Model_Item mdl_item) {
        String sql = "UPDATE mst_item SET sts_active=? WHERE id_items=?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, "0");
            st.setString(2, mdl_item.getId_items());
            st.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Failed to Delete data");
            java.util.logging.Logger.getLogger(DAO_Item.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Model_Item> getData() {
        List<Model_Item> list = new ArrayList<>();
        String sql = "SELECT i.id_items, t.type_name, i.items_name, d.distributor_name, i.brand, i.unit, i.stock, i.price FROM mst_item i JOIN mst_type t ON i.id_type = t.id_type JOIN mst_distributor d ON i.id_distributor = d.id_distributor WHERE i.sts_active='1'";
        
        try (PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            
            while (rs.next()) {
                Model_Item mi = new Model_Item();
                Model_Type mt = new Model_Type();
                Model_Distributor md = new Model_Distributor();
                mt.setType_name(rs.getString("type_name"));
                mi.setMoty(mt);
                mi.setItems_name(rs.getString("items_name"));
                mi.setId_items(rs.getString("id_items"));
                md.setDistributor_name(rs.getString("distributor_name"));
                mi.setModt(md);
                mi.setPrice(rs.getString("price"));
                mi.setBrand(rs.getString("brand"));
                mi.setUnit(rs.getString("unit"));
                mi.setStock(rs.getString("stock"));
                list.add(mi);
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_Item.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List<Model_Item> getData2(String idDist) {
        List<Model_Item> list = new ArrayList<>();
        String sql = "SELECT i.id_items, t.type_name, i.items_name, d.distributor_name, i.brand, i.unit, i.stock, i.price FROM mst_item i JOIN mst_type t ON i.id_type = t.id_type JOIN mst_distributor d ON i.id_distributor = d.id_distributor WHERE i.id_distributor=? AND i.sts_active='1'";
        
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, idDist);
            
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Model_Item mi = new Model_Item();
                    Model_Type mt = new Model_Type();
                    Model_Distributor md = new Model_Distributor();
                    mi.setId_items(rs.getString("id_items"));
                    mt.setType_name(rs.getString("type_name"));
                    mi.setMoty(mt);
                    mi.setItems_name(rs.getString("items_name"));
                    md.setDistributor_name(rs.getString("distributor_name"));
                    mi.setModt(md);
                    mi.setPrice(rs.getString("price"));
                    mi.setBrand(rs.getString("brand"));
                    mi.setUnit(rs.getString("unit"));
                    mi.setStock(rs.getString("stock"));
                    list.add(mi);
                }
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_Item.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List<Model_Item> getData3() {
        List<Model_Item> list = new ArrayList<>();
        String sql = "SELECT i.id_items, t.type_name, i.items_name, d.distributor_name, i.brand, i.unit, i.stock, i.price FROM mst_item i JOIN mst_type t ON i.id_type = t.id_type JOIN mst_distributor d ON i.id_distributor = d.id_distributor WHERE i.sts_active='1'";
        
        try (PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            
            while (rs.next()) {
                Model_Item mi = new Model_Item();
                Model_Type mt = new Model_Type();
                Model_Distributor md = new Model_Distributor();
                mi.setId_items(rs.getString("id_items"));
                mt.setType_name(rs.getString("type_name"));
                mi.setMoty(mt);
                mi.setItems_name(rs.getString("items_name"));
                md.setDistributor_name(rs.getString("distributor_name"));
                mi.setModt(md);
                mi.setPrice(rs.getString("price"));
                mi.setBrand(rs.getString("brand"));
                mi.setUnit(rs.getString("unit"));
                mi.setStock(rs.getString("stock"));
                list.add(mi);
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_Item.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
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
    public List<Model_Item> search(String id, String str) {
        List<Model_Item> list = new ArrayList<>();
        String sql = "SELECT i.id_items, t.type_name, i.items_name, i.brand, i.unit, i.stock, i.price FROM mst_item i JOIN mst_type t ON i.id_type = t.id_type JOIN mst_distributor d ON i.id_distributor = d.id_distributor WHERE i.id_distributor=? AND i.sts_active='1' AND (i.id_items LIKE ? OR i.items_name LIKE ?)";
        
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            String searchPattern = "%" + str + "%";
            st.setString(1, id);
            st.setString(2, searchPattern);
            st.setString(3, searchPattern);
            
            try (ResultSet rs = st.executeQuery()) {
                System.out.println(str);
                while (rs.next()) {
                    Model_Item mi = new Model_Item();
                    Model_Type mt = new Model_Type();
                    mi.setId_items(rs.getString("id_items"));
                    mt.setType_name(rs.getString("type_name"));
                    mi.setMoty(mt);
                    mi.setItems_name(rs.getString("items_name"));
                    mi.setPrice(rs.getString("price"));
                    mi.setBrand(rs.getString("brand"));
                    mi.setUnit(rs.getString("unit"));
                    mi.setStock(rs.getString("stock"));
                    list.add(mi);
                }
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_Distributor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List<Model_Item> getDataDistributor() {
        List<Model_Item> list = new ArrayList<>();
        String sql = "SELECT id_distributor, distributor_name FROM mst_distributor WHERE sts_active='1'";
        
        try (PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            
            while (rs.next()) {
                Model_Item mi = new Model_Item();
                Model_Distributor md = new Model_Distributor();
                md.setId_distributor(rs.getString("id_distributor"));
                md.setDistributor_name(rs.getString("distributor_name"));
                mi.setModt(md);
                list.add(mi);
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_Distributor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }


    @Override
    public List<Model_Item> getDataType() {
        List<Model_Item> list = new ArrayList<>();
        String sql = "SELECT id_type, type_name FROM mst_type WHERE sts_active='1'";
        
        try (PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            
            while (rs.next()) {
                Model_Item mi = new Model_Item();
                Model_Type mt = new Model_Type();
                mt.setId_type(rs.getString("id_type"));
                mt.setType_name(rs.getString("type_name"));
                mi.setMoty(mt);
                list.add(mi);
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_Item.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List<Model_Item> search(String str) {
        List<Model_Item> list = new ArrayList<>();
        String sql = "SELECT i.id_items, t.type_name, i.items_name, i.brand, i.unit, i.stock, i.price FROM mst_item i JOIN mst_type t ON i.id_type = t.id_type JOIN mst_distributor d ON i.id_distributor = d.id_distributor WHERE i.sts_active='1' AND (i.id_items LIKE ? OR i.items_name LIKE ?)";
        
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            String searchPattern = "%" + str + "%";
            st.setString(1, searchPattern);
            st.setString(2, searchPattern);
            
            try (ResultSet rs = st.executeQuery()) {
                System.out.println(str);
                while (rs.next()) {
                    Model_Item mi = new Model_Item();
                    Model_Type mt = new Model_Type();
                    mi.setId_items(rs.getString("id_items"));
                    mt.setType_name(rs.getString("type_name"));
                    mi.setMoty(mt);
                    mi.setItems_name(rs.getString("items_name"));
                    mi.setPrice(rs.getString("price"));
                    mi.setBrand(rs.getString("brand"));
                    mi.setUnit(rs.getString("unit"));
                    mi.setStock(rs.getString("stock"));
                    list.add(mi);
                }
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_Distributor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }


}
