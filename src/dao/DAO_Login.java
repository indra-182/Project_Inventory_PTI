package dao;
import main.Menu_utama;
import config.Koneksi;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.JOptionPane;
import model.Model_Login;
import service.Service_Login;
import view.Form_Regist;
import view.Form_login;

public class DAO_Login implements Service_Login{
    private Connection conn;
    
    public DAO_Login(){
        conn = Koneksi.getConnection();
    }

    @Override
    public void proccesLogin(Model_Login login) {
        String sql = "SELECT u.id_karyawan, k.nama_lengkap, k.gender, g.group_name FROM mst_user u JOIN mst_karyawan k ON u.id_karyawan = k.id_karyawan JOIN mst_group g ON u.id_group = g.id_group WHERE u.username = ? AND u.password = ?";
        
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, login.getUsername());
            st.setString(2, Encrypt.getmd5java(login.getPassword()));
            
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    String id = rs.getString("id_karyawan");
                    String name = rs.getString("nama_lengkap");
                    String groupName = rs.getString("group_name");
                    String gender = rs.getString("gender");
                    
                    String updateLogin = "UPDATE mst_user SET last_login = NOW() WHERE username = ?";
                    try (PreparedStatement stUpdate = conn.prepareStatement(updateLogin)) {
                        stUpdate.setString(1, login.getUsername());
                        stUpdate.executeUpdate();
                    }
                    
                    Menu_utama menu = new Menu_utama(id, name, groupName, gender);
                    menu.setVisible(true);
                    menu.revalidate();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password", "Message", JOptionPane.INFORMATION_MESSAGE);
                    Form_login lg = new Form_login();
                    lg.setVisible(true);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void registLogin(Model_Login login, Form_login fl, Form_Regist fr) {
        String sql = "INSERT INTO mst_user (id_karyawan,id_group,username,password,sts_active,created_by,created_date,updated_by,updated_date,last_login) VALUES(?,?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, login.getMokr().getId_karyawan());
            st.setString(2, login.getMogr().getId_group());
            st.setString(3, login.getUsername());
            st.setString(4, Encrypt.getmd5java(login.getPassword()));
            st.setString(5, "1");
            st.setString(6, login.getCreated_by());
            st.setString(7, login.getCreated_date());
            st.setString(8, login.getUpdated_by());
            st.setString(9, login.getUpdated_date());
            st.setString(10, login.getLast_active());
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registration successful. You can now log in");
            fl.setVisible(true);
        } catch (SQLException ex) {
            fr.setVisible(true);
            JOptionPane.showMessageDialog(null, "Registration failed.");
            java.util.logging.Logger.getLogger(DAO_Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
