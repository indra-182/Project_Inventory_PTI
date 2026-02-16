package dao;

import config.Koneksi;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import model.Model_UserGroup;
import service.Service_UserGroup;

public class DAO_UserGroup implements Service_UserGroup{
    private Connection conn;
    
    public DAO_UserGroup(){
        conn = Koneksi.getConnection();
    }
    @Override
    public void addData(Model_UserGroup mdl_usergroup) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void updateData(Model_UserGroup mdl_usergroup) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteData(Model_UserGroup mdl_usergroup) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Model_UserGroup> getData() {
        List<Model_UserGroup> list = new ArrayList<>();
        String sql = "SELECT a.username, a.id_group, a.id_menu, UPPER(b.username) AS username, c.group_name, UPPER(d.name_menu) AS name_menu, view_akses, add_akses, edit_akses, delete_akses FROM mst_usergroup a INNER JOIN mst_user b ON a.username=b.username INNER JOIN mst_group c ON a.id_group=c.id_group INNER JOIN mst_menu d ON a.id_menu=d.id_menu";
        
        try (PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            
            while (rs.next()) {
                Model_UserGroup mk = new Model_UserGroup();
                mk.setId_user(rs.getString("username"));
                mk.setId_menu(rs.getString("id_menu"));
                mk.setId_group(rs.getString("id_group"));
                mk.setGroup_name(rs.getString("group_name"));
                mk.setName_menu(rs.getString("name_menu"));
                list.add(mk);
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_UserGroup.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List<Model_UserGroup> getData2() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
