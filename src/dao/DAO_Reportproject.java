package dao;

import config.Koneksi;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.logging.*;
import javax.swing.JOptionPane;
import model.Model_Project;
import model.Model_Vendor;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import service.Service_Reportproject;

public class DAO_Reportproject implements Service_Reportproject {
    private Connection conn;

    public DAO_Reportproject() {
        conn = Koneksi.getConnection();
    }
    @Override
    public List<Model_Project> getData() {
        List<Model_Project> list = new ArrayList<>();
        String sql = "SELECT * FROM v_project";
        
        try (PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            
            while (rs.next()) {
                Model_Project mp = new Model_Project();
                Model_Vendor mv = new Model_Vendor();
                
                mp.setId_project(rs.getString("id_project"));
                mp.setProject_name(rs.getString("project_name"));
                mp.setProject_manager(rs.getString("project_manager"));
                mp.setStatus(rs.getString("status"));
                mp.setProject_site(rs.getString("project_site"));
                mv.setVendor_name(rs.getString("vendor_name"));
                mp.setMovr(mv);
                list.add(mp);
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_Reportproject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    @Override
    public List<Model_Project> getData(String str) {
        List<Model_Project> list = new ArrayList<>();
        String sql = "SELECT * FROM v_project WHERE status = ?";
        
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, str);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Model_Project mp = new Model_Project();
                    Model_Vendor mv = new Model_Vendor();
                    
                    mp.setId_project(rs.getString("id_project"));
                    mp.setProject_name(rs.getString("project_name"));
                    mp.setProject_manager(rs.getString("project_manager"));
                    mp.setProject_site(rs.getString("project_site"));
                    mp.setStatus(rs.getString("status"));
                    mv.setVendor_name(rs.getString("vendor_name"));
                    mp.setMovr(mv);
                    list.add(mp);
                }
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_Reportproject.class.getName()).log(Level.SEVERE, null, ex);
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
    public void Report(String user, String status) {
        try {
            String file = "src/report/Report_Project.jasper";
            Map<String, Object> param = new HashMap<>();
            param.put("user", user);
            param.put("status", status);
            JasperPrint print = JasperFillManager.fillReport(file, param, conn);
            JasperViewer.viewReport(print, false);
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e.getMessage());
        }
    }
@Override
    public void Report(String user) {
        try {
            String file = "src/report/Report_Project_1.jasper";
            Map<String, Object> param = new HashMap<>();
            param.put("user", user);
            JasperPrint print = JasperFillManager.fillReport(file, param, conn);
            JasperViewer.viewReport(print, false);
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e.getMessage());
        }
    }
}






