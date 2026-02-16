package dao;

import config.Koneksi;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.JOptionPane;
import model.Model_Cart_Incoming;
import model.Model_Distributor;
import model.Model_Incoming;
import model.Model_Karyawan;
import service.Service_Incoming;

public class DAO_Incoming implements Service_Incoming{
    private Connection conn;

    public DAO_Incoming() {
        conn = Koneksi.getConnection();
    }

    @Override
    public List<Model_Incoming> getData() {
        List<Model_Incoming> list = new ArrayList<>();
        String sql = "SELECT i.id_incoming, i.transaction_date, i.total_price, d.id_distributor, k.nama_lengkap FROM tx_incoming i JOIN mst_distributor d ON d.id_distributor = i.id_distributor JOIN mst_karyawan k ON k.id_karyawan = i.id_karyawan WHERE i.sts_active = '1'";
        
        try (PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            
            while (rs.next()) {
                Model_Incoming min = new Model_Incoming();
                Model_Distributor md = new Model_Distributor();
                Model_Karyawan mk = new Model_Karyawan();
                
                min.setId_incoming(rs.getString("id_incoming"));
                min.setTransaction_date(rs.getString("transaction_date"));
                min.setTotal_price(rs.getString("total_price"));
                md.setId_distributor(rs.getString("id_distributor"));
                mk.setNama_lengkap(rs.getString("nama_lengkap"));
                min.setModr(md);
                min.setMokr(mk);
                
                list.add(min);
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_Incoming.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }


    @Override
    public List<Model_Incoming> getData2() {
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
    public void updData(Model_Incoming mdl_in) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delData(Model_Incoming mdl_in) {
        String sql = "UPDATE tx_incoming SET sts_active = ? WHERE id_incoming = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, "0");
            st.setString(2, mdl_in.getId_incoming());
            st.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Failed to Delete data");
            java.util.logging.Logger.getLogger(DAO_Incoming.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void addData(Model_Cart_Incoming mdl_in) {
        String sql = "INSERT INTO tx_incoming (id_incoming, id_distributor, transaction_date, total_price, id_karyawan, sts_active, created_by, created_date, updated_by, updated_date) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, mdl_in.getModi().getMoin().getId_incoming());
            st.setString(2, mdl_in.getModt().getId_distributor());
            st.setString(3, mdl_in.getModi().getMoin().getTransaction_date());
            st.setString(4, mdl_in.getModi().getMoin().getTotal_price());
            st.setString(5, mdl_in.getModi().getMokr().getId_karyawan());
            st.setString(6, "1");
            st.setString(7, mdl_in.getCreated_by());
            st.setString(8, mdl_in.getCreated_date());
            st.setString(9, mdl_in.getUpdated_by());
            st.setString(10, mdl_in.getUpdated_date());
            st.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Failed to Add data");
            java.util.logging.Logger.getLogger(DAO_Incoming.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}






