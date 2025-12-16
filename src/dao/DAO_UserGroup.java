/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import config.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import model.Model_UserGroup;
import service.Service_UserGroup;

/**
 *
 * @author rafii
 */
public class DAO_UserGroup implements Service_UserGroup{
    private Connection conn;
    public DAO_UserGroup(){
        conn = (Connection) Koneksi.getConnection();
    }
    @Override
    public void addData(Model_UserGroup mdl_usergroup) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateData(Model_UserGroup mdl_usergroup) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteData(Model_UserGroup mdl_usergroup) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Model_UserGroup> getData() {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs=null;
        String sql = "select a.username,a.id_group,a.id_menu,upper(b.username)username,c.group_name,upper(d.name_menu)name_menu,view_akses,add_akses,edit_akses,delete_akses from mst_usergroup a \n" +
"inner join mst_user b on a.username=b.username\n" +
"inner join mst_group c on a.id_group=c.id_group\n" +
"inner join mst_menu d on a.id_menu=d.id_menu ;";
        try{
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                Model_UserGroup mk = new Model_UserGroup();
                
                mk.setId_user(rs.getString("username"));
                mk.setId_menu(rs.getString("id_menu"));
                mk.setId_group(rs.getString("id_group"));
//                mk.setUsername(rs.getString("username"));
                mk.setGroup_name(rs.getString("group_name"));
                mk.setName_menu(rs.getString("name_menu"));
                                list.add(mk);
            }
            return list;
        }catch(SQLException ex){
            java.util.logging.Logger.getLogger(DAO_UserGroup.class.getName()).log(Level.SEVERE,null,ex);
            return null;
        }finally{
            if(st!=null){
                try{
                    st.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_UserGroup.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
            if(rs!=null){
                try{
                    rs.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_UserGroup.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }
        
    }

    @Override
    public List<Model_UserGroup> getData2() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
