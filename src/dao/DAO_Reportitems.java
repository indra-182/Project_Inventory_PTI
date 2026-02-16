package dao;

import config.Koneksi;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.logging.*;
import javax.swing.JOptionPane;
import model.Model_Distributor;
import model.Model_Item;
import model.Model_Type;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import service.Service_Reportitems;
public class DAO_Reportitems implements Service_Reportitems {
    private Connection conn;

    public DAO_Reportitems() {
        conn = Koneksi.getConnection();
    }
    @Override
    public List<Model_Item> getData() {
        List<Model_Item> list = new ArrayList<>();
        String sql = "SELECT * FROM v_items";
        
        try (PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            
            while (rs.next()) {
                Model_Item mi = new Model_Item();
                Model_Distributor md = new Model_Distributor();
                Model_Type mt = new Model_Type();
                
                mi.setId_items(rs.getString("id_items"));
                mt.setType_name(rs.getString("type_name"));
                mi.setItems_name(rs.getString("item_name"));
                md.setDistributor_name(rs.getString("distributor_name"));
                mi.setUnit(rs.getString("unit"));
                mi.setBrand(rs.getString("brand"));
                mi.setStock(rs.getString("stock"));
                mi.setPrice(rs.getString("price"));
                mi.setMoty(mt);
                mi.setModt(md);
                list.add(mi);
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_Reportitems.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    @Override
    public List<Model_Item> getData(String str) {
        List<Model_Item> list = new ArrayList<>();
        String sql = "SELECT * FROM v_items WHERE type_name = ?";
        
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, str);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Model_Item mi = new Model_Item();
                    Model_Distributor md = new Model_Distributor();
                    Model_Type mt = new Model_Type();
                    mi.setId_items(rs.getString("id_items"));
                    mt.setType_name(rs.getString("type_name"));
                    mi.setItems_name(rs.getString("item_name"));
                    md.setDistributor_name(rs.getString("distributor_name"));
                    mi.setUnit(rs.getString("unit"));
                    mi.setBrand(rs.getString("brand"));
                    mi.setStock(rs.getString("stock"));
                    mi.setPrice(rs.getString("price"));
                    mi.setMoty(mt);
                    mi.setModt(md);
                    list.add(mi);
                }
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_Reportitems.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    @Override
    public String nomor() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String nomor2() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void Report(String user, String type) {
        try {
            String file = "src/report/Report_Item.jasper";
            Map<String, Object> param = new HashMap<>();
            param.put("user", user);
            param.put("type", type);
            JasperPrint print = JasperFillManager.fillReport(file, param, conn);
            JasperViewer.viewReport(print, false);
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e.getMessage());
        }
    }
@Override
    public void Report(String user) {
        try {
            String file = "src/report/Report_Item.jasper";
            Map<String, Object> param = new HashMap<>();
            param.put("user", user);
            JasperPrint print = JasperFillManager.fillReport(file, param, conn);
            JasperViewer.viewReport(print, false);
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e.getMessage());
        }
    }
}






