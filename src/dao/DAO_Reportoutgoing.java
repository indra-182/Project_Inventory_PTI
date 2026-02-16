package dao;

import config.Koneksi;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.logging.*;
import javax.swing.JOptionPane;
import model.Model_Reportoutgoing;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import service.Service_Reportoutgoing;
public class DAO_Reportoutgoing implements Service_Reportoutgoing {
    private Connection conn;

    public DAO_Reportoutgoing() {
        conn = Koneksi.getConnection();
    }

    @Override
    public List<Model_Reportoutgoing> getData() {
        List<Model_Reportoutgoing> list = new ArrayList<>();
        String sql = "SELECT * FROM v_outgoing";
        
        try (PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            
            while (rs.next()) {
                Model_Reportoutgoing min = new Model_Reportoutgoing();
                min.setId_outgoing(rs.getString("id_outgoing"));
                min.setProject_name(rs.getString("project_name"));
                min.setTransaction_date(rs.getString("transaction_date"));
                min.setItems_name(rs.getString("items_name"));
                min.setQuantity(rs.getString("quantity"));
                min.setPic(rs.getString("pic"));
                list.add(min);
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_Reportoutgoing.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    @Override
    public List<Model_Reportoutgoing> getData(Date fromDate, Date toDate) {
        List<Model_Reportoutgoing> list = new ArrayList<>();
        String sql = "SELECT * FROM v_outgoing WHERE DATE(transaction_date) BETWEEN ? AND ?";
        
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            java.sql.Date sqlFrom = new java.sql.Date(fromDate.getTime());
            java.sql.Date sqlTo = new java.sql.Date(toDate.getTime());
            st.setDate(1, sqlFrom);
            st.setDate(2, sqlTo);
            
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Model_Reportoutgoing min = new Model_Reportoutgoing();
                    min.setId_outgoing(rs.getString("id_outgoing"));
                    min.setProject_name(rs.getString("project_name"));
                    min.setTransaction_date(rs.getString("transaction_date"));
                    min.setItems_name(rs.getString("items_name"));
                    min.setQuantity(rs.getString("quantity"));
                    min.setPic(rs.getString("pic"));
                    list.add(min);
                }
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_Reportoutgoing.class.getName()).log(Level.SEVERE, null, ex);
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
    public void Report(String user) {
        try {
            String file = "src/report/Report_Outgoing.jasper";
            Map<String, Object> param = new HashMap<>();
            param.put("user", user);
            JasperPrint print = JasperFillManager.fillReport(file, param, conn);
            JasperViewer.viewReport(print, false);
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e.getMessage());
        }
    }
    @Override
    public void Report(String user, Date fromDate, Date toDate) {
        try {
            Calendar calFrom = Calendar.getInstance();
            calFrom.setTime(fromDate);
            calFrom.set(Calendar.HOUR_OF_DAY, 0);
            calFrom.set(Calendar.MINUTE, 0);
            calFrom.set(Calendar.SECOND, 0);

            Calendar calTo = Calendar.getInstance();
            calTo.setTime(toDate);
            calTo.set(Calendar.HOUR_OF_DAY, 23);
            calTo.set(Calendar.MINUTE, 59);
            calTo.set(Calendar.SECOND, 59);

            Map<String, Object> param = new HashMap<>();
            param.put("fromDate", new java.sql.Timestamp(calFrom.getTimeInMillis()));
            param.put("toDate", new java.sql.Timestamp(calTo.getTimeInMillis()));
            param.put("user", user);

            JasperPrint print = JasperFillManager.fillReport("src/report/Report_Outgoing_1.jasper", param, conn);
            JasperViewer.viewReport(print, false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}






