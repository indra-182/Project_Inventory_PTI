package dao;

import config.Koneksi;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.JOptionPane;
import model.Model_Cart_Outgoing;
import model.Model_Karyawan;
import model.Model_Outgoing;
import model.Model_Project;
import service.Service_Outgoing;
public class DAO_Outgoing implements Service_Outgoing {
    private final Connection conn;

    public DAO_Outgoing() {
        conn = Koneksi.getConnection();
    }

    @Override
    public List<Model_Outgoing> getData() {
        List<Model_Outgoing> list = new ArrayList<>();
        String sql = "SELECT i.id_outgoing, i.transaction_date, d.id_project, k.nama_lengkap FROM tx_outgoing i JOIN mst_project d ON d.id_project = i.id_project JOIN mst_karyawan k ON k.id_karyawan = i.id_karyawan WHERE i.sts_active = '1'";
        
        try (PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            
            while (rs.next()) {
                Model_Outgoing min = new Model_Outgoing();
                Model_Project md = new Model_Project();
                Model_Karyawan mk = new Model_Karyawan();
                
                min.setId_outgoing(rs.getString("id_outgoing"));
                min.setTransaction_date(rs.getString("transaction_date"));
                md.setId_project(rs.getString("id_project"));
                mk.setNama_lengkap(rs.getString("nama_lengkap"));
                min.setMopr(md);
                min.setMokr(mk);
                list.add(min);
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_Outgoing.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }


    @Override
    public List<Model_Outgoing> getData2() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String nomor() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String nomor2() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updData(Model_Outgoing mdl_in) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delData(Model_Outgoing mdl_in) {
        {
        PreparedStatement st= null;
        String sql="Update tx_outgoing set sts_active=? where id_outgoing='"+mdl_in.getId_outgoing()+"'";
        try{
            st = conn.prepareStatement(sql);
            st.setString(1,"0");
            
            st.executeUpdate(); 
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Failed to Delete data");
            java.util.logging.Logger.getLogger(DAO_Project.class.getName()).log(Level.SEVERE,null,ex);
        }finally{
            if(st!=null){
                try{
                    st.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Project.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }
    }
    }

    @Override
    public void addData(Model_Cart_Outgoing mdl_in) {
        String sql = "INSERT INTO tx_outgoing (id_outgoing, id_project, transaction_date, id_karyawan, sts_active, created_by, created_date, updated_by, updated_date) VALUES (?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, mdl_in.getModo().getMoou().getId_outgoing());
            st.setString(2, mdl_in.getMopr().getId_project());
            st.setString(3, mdl_in.getModo().getMoou().getTransaction_date());
            st.setString(4, mdl_in.getModo().getMokr().getId_karyawan());
            st.setString(5, "1");
            st.setString(6, mdl_in.getModo().getCreated_by());
            st.setString(7, mdl_in.getModo().getCreated_date());
            st.setString(8, mdl_in.getModo().getUpdated_by());
            st.setString(9, mdl_in.getModo().getUpdated_date());
            st.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Failed to Add data");
            java.util.logging.Logger.getLogger(DAO_Outgoing.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
